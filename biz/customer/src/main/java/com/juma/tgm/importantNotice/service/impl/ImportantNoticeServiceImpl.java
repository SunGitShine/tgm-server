package com.juma.tgm.importantNotice.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.auth.user.service.UserService;
import com.juma.message.domain.MsgAppRecord;
import com.juma.message.record.service.MsgAppRecordService;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.driver.service.DriverService;
import com.juma.tgm.importantNotice.dao.ImportantNoticeDao;
import com.juma.tgm.importantNotice.domain.ImportantNotice;
import com.juma.tgm.importantNotice.service.ImportantNoticeService;
import com.juma.tgm.tools.service.MessagePushService;

@Service
public class ImportantNoticeServiceImpl implements ImportantNoticeService {
    @Resource
    private ImportantNoticeDao importantNoticeDao;
    @Resource
    private DriverService driverService;
    @Resource
    private UserService userService;
    @Resource
    private MessagePushService messagePushService;
    @Resource
    private MsgAppRecordService msgAppRecordService;

    @Override
    public Page<ImportantNotice> search(PageCondition pageCondition, LoginUser loginUser) {
        pageCondition.getFilters().put("tenantId", loginUser.getTenantId());
        int count = importantNoticeDao.searchCount(pageCondition);
        List<ImportantNotice> rows = importantNoticeDao.search(pageCondition);
        for (ImportantNotice importantNotice : rows) {
            // 单独通知需要获取通知人员姓名
            buildSingleUserNames(importantNotice);

            // 新建的公告15秒内标记为全部未读
            BigDecimal second = DateUtil.calDate(importantNotice.getCreateTime(), new Date(),
                    DateUtil.TimeUnitEnum.SECOND);
            if (null != second && second.compareTo(new BigDecimal("15")) != 1) {
                importantNotice.setHasNotReadUserNo(-1);
                continue;
            }

            try {
                importantNotice
                        .setHasNotReadUserNo(
                                msgAppRecordService
                                        .unreadRecordList(Constants.TGM_NOTICE_KEY,
                                                importantNotice.getNoticeId().longValue(), 1, Integer.MAX_VALUE)
                                        .getTotal());
            } catch (Exception e) {
            }
        }
        return new Page<ImportantNotice>(pageCondition.getPageNo(), pageCondition.getPageSize(), count, rows);
    }

    // 单独通知需要获取通知人员姓名
    private void buildSingleUserNames(ImportantNotice importantNotice) {
        if (null == importantNotice.getNoticePersonnelType()
                || NumberUtils.compare(Constants.TGM_SINGLE_SELECT, importantNotice.getNoticePersonnelType()) != 0) {
            return;
        }

        StringBuffer sf = new StringBuffer("");
        if (StringUtils.isBlank(importantNotice.getNoticePersonnelUserIds())) {
            return;
        }

        String[] userIds = importantNotice.getNoticePersonnelUserIds().split(",");
        for (String userId : userIds) {
            if (StringUtils.isBlank(userId)) {
                continue;
            }

            User user = userService.loadUser(Integer.parseInt(userId));
            if (null == user) {
                continue;
            }

            if (StringUtils.isBlank(user.getName())) {
                sf.append(user.getMobileNumber()).append("，");
                continue;
            }
            sf.append(user.getName()).append("，");
        }

        if (StringUtils.isNotBlank(sf.toString())) {
            importantNotice.setNoticeUserNames(sf.toString().substring(0, sf.toString().length() - 1));
        }
    }

    @Override
    public ImportantNotice getImportantNotice(Integer noticeId) {
        if (null == noticeId) {
            return null;
        }
        return importantNoticeDao.get(noticeId);
    }

    @Override
    public Page<User> unReadUserList(Integer noticeId, int pageNo, int pageSize) throws BusinessException {
        List<User> result = new ArrayList<User>();
        if (null == noticeId) {
            return new Page<User>(pageNo, pageSize, 0, result);
        }

        Page<MsgAppRecord> page = msgAppRecordService.unreadRecordList(Constants.TGM_NOTICE_KEY, noticeId.longValue(),
                pageNo, pageSize);
        for (MsgAppRecord msgAppRecord : page.getResults()) {
            User user = userService.loadUser(msgAppRecord.getReceiveUserId());
            if (null == user) {
                continue;
            }
            result.add(user);
        }
        return new Page<User>(pageNo, pageSize, page.getTotal(), result);
    }

    @Override
    public void insert(ImportantNotice importantNotice, LoginUser loginUser) throws BusinessException {
        importantNotice.setCreateUserId(loginUser.getUserId());
        importantNotice.setTenantId(loginUser.getTenantId());
        importantNotice.setTenantCode(loginUser.getTenantCode());
        importantNoticeDao.insert(importantNotice);

        // 推送公告,并计算通知的人数
        int pushNoticeUserNo = pushNoticeToDriver(importantNotice);
        importantNotice.setNoticeUserNo(pushNoticeUserNo);
        importantNotice.setLastUpdateUserId(loginUser.getUserId());
        importantNoticeDao.update(importantNotice);
    }

    // 推送公告
    private int pushNoticeToDriver(ImportantNotice importantNotice) {
        Map<String, Object> extras = new HashMap<String, Object>();
        extras.put("title", importantNotice.getNoticeTitle());
        extras.put("content", importantNotice.getNoticeContent());

        // 单独通知
        if (null != importantNotice.getNoticePersonnelType()
                && NumberUtils.compare(Constants.TGM_SINGLE_SELECT, importantNotice.getNoticePersonnelType()) == 0) {
            String[] tos = importantNotice.getNoticePersonnelUserIds().split(",");
            messagePushService.pushAppMessage("DRIVER_NOTICE", extras,
                    importantNotice.getIsMustRead() == null ? false : importantNotice.getIsMustRead(),
                    importantNotice.getNoticeId() + "", tos);
            return tos.length;
        }

        // 批量通知
        List<String> tos = new ArrayList<String>();
        List<Driver> list = driverService.listByAreaCodeLike(importantNotice.getTenantId(),
                importantNotice.getAreaCode(), importantNotice.getNoticePersonnelType());
        for (Driver driver : list) {
            tos.add(driver.getUserId() + "");
        }

        // 推送公告
        messagePushService.pushAppMessage("DRIVER_NOTICE", extras,
                importantNotice.getIsMustRead() == null ? false : importantNotice.getIsMustRead(),
                importantNotice.getNoticeId() + "", tos.toArray(new String[tos.size()]));
        
        return tos.size();
    }

    @Override
    public void updateToEnable(Integer noticeId, LoginUser loginUser) throws BusinessException {
        ImportantNotice importantNotice = new ImportantNotice();
        importantNotice.setNoticeId(noticeId);
        importantNotice.setIsDelete(false);
        importantNotice.setLastUpdateUserId(loginUser.getUserId());
        importantNoticeDao.update(importantNotice);

        // 通知消息中心
        try {
            msgAppRecordService.updateDeleteStatusByExtMsgId(noticeId, false);
        } catch (Exception e) {
            throw new BusinessException("importantNoticeAbleError", "importantNotice.error.importantNoticeAbleError");
        }
    }

    @Override
    public void updateToDisable(Integer noticeId, LoginUser loginUser) throws BusinessException {
        ImportantNotice importantNotice = new ImportantNotice();
        importantNotice.setNoticeId(noticeId);
        importantNotice.setIsDelete(true);
        importantNotice.setLastUpdateUserId(loginUser.getUserId());
        importantNoticeDao.update(importantNotice);

        // 通知消息中心
        try {
            msgAppRecordService.updateDeleteStatusByExtMsgId(noticeId, true);
        } catch (Exception e) {
            throw new BusinessException("importantNoticeAbleError", "importantNotice.error.importantNoticeAbleError");
        }
    }
}
