package com.juma.tgm.customerManager.service.impl;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.ManagerScheduleType;
import com.juma.tgm.customerManager.dao.ManagerScheduleDao;
import com.juma.tgm.customerManager.domain.ManagerSchedule;
import com.juma.tgm.customerManager.service.ManagerScheduleService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ManagerScheduleServiceImpl
 * @Description:
 * @author: liang
 * @date: 2017-06-15 18:52
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */

@Service
public class ManagerScheduleServiceImpl implements ManagerScheduleService {

    private static final Logger log = LoggerFactory.getLogger(ManagerScheduleServiceImpl.class);

    @Resource
    private ManagerScheduleDao managerScheduleDao;

    @Override
    public int add(ManagerSchedule managerSchedule, LoginUser loginUser) throws BusinessException {
        if (managerSchedule == null) {
            return -1;
        }

        if (managerSchedule.getCustomerManagerId() == null) {
            return -1;
        }

        if (managerSchedule.getWaybillId() == null) {
            throw new BusinessException("managerScheduleWaybillIdNull", "errors.paramCanNotNullWithName", "managerScheduleWaybillId");
        }

        if (managerSchedule.getType() == null) {
            throw new BusinessException("managerScheduleTypeNull", "errors.paramCanNotNullWithName", "managerScheduleType");
        }

        if (StringUtils.isBlank(managerSchedule.getContent())) {
            throw new BusinessException("managerScheduleContentNull", "errors.paramCanNotNullWithName", "managerScheduleContent");
        }

        //已有的不再添加
//        if (this.exist(managerSchedule.getWaybillId())) {
//            return -1;
//        }

        managerSchedule.setHandled(Integer.valueOf(Constants.flag_false));
        managerSchedule.setIsDelete(Integer.valueOf(Constants.flag_false));

        managerSchedule.setCreateTime(new Date());
        managerSchedule.setCreateUserId(loginUser.getUserId());

        managerScheduleDao.insert(managerSchedule);

        return managerSchedule.getManagerScheduleId();
    }

    @Override
    public void Handled(int managerScheduleId, LoginUser loginUser) throws BusinessException {
        ManagerSchedule managerSchedule = managerScheduleDao.get(managerScheduleId);
        if (managerSchedule == null) return;

        managerSchedule.setHandled(Integer.valueOf(Constants.flag_true));

        managerSchedule.setLastUpdateUserId(loginUser.getUserId());
        managerSchedule.setLastUpdateTime(new Date());

        managerScheduleDao.update(managerSchedule);
    }

    @Override
    public int unhandledCount(LoginEmployee loginEmployee) throws BusinessException {
        ManagerSchedule managerSchedule = new ManagerSchedule();
        managerSchedule.setCustomerManagerId(loginEmployee.getEmployeeId());
        managerSchedule.setHandled(Integer.valueOf(Constants.flag_false));
        managerSchedule.setIsDelete(Integer.valueOf(Constants.flag_false));

        return managerScheduleDao.countByParam(managerSchedule);
    }

    @Override
    public Page<ManagerSchedule> getUnhandledList(PageCondition pageCondition, LoginEmployee loginEmployee) throws BusinessException {
        Page<ManagerSchedule> pageData = new Page<>();
        pageData.setTotal(0);
        pageData.setPageSize(pageCondition.getPageSize());
        pageData.setPageNo(pageCondition.getPageNo());

        Map<String, Object> param = pageCondition.getFilters();
        param.put("isDelete", Constants.flag_false);
        param.put("handled", Constants.flag_false);
        param.put("customerManagerId", loginEmployee.getEmployeeId());

        pageCondition.setOrderBy("create_time desc");

        //count
        int count = managerScheduleDao.searchCount(pageCondition);

        if (count <= 0) return pageData;

        //data
        List<ManagerSchedule> datas = managerScheduleDao.search(pageCondition);

        pageData.setTotal(count);
        pageData.setResults(datas);

        return pageData;
    }

    @Override
    public String convertTemplate(String scheduleTemplate, String... args) throws BusinessException {
        return String.format(scheduleTemplate, args);
    }

    @Override
    public ManagerSchedule buildIncreaseCarryfeeSchedule(Waybill waybill, WaybillParam waybillParam, ManagerScheduleType type) throws BusinessException {
        if (waybill == null) return null;

        if (waybillParam == null) return null;

        //不是客户经理端的订单不用添加代办事项
        if(waybill.getCustomerManagerId() == null) return null;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        int waybillNoLength = waybill.getWaybillNo().length();
        String waybillNo = StringUtils.substring(waybill.getWaybillNo(), waybillNoLength - 4, waybillNoLength);
        ManagerSchedule ms = new ManagerSchedule();
        ms.setType(type.getCode());
        BigDecimal cost = waybillParam.getHandlingCost() == null ? new BigDecimal(0) : waybillParam.getHandlingCost();
        ms.setContent(this.convertTemplate(type.getContent(), waybillNo, decimalFormat.format(cost)));
        ms.setWaybillId(waybill.getWaybillId());
        ms.setCustomerManagerId(waybill.getCustomerManagerId());

        return ms;
    }

    @Override
    public ManagerSchedule buildAssignFeedbackSchedule(Waybill waybill, ManagerScheduleType type) throws BusinessException {

        //不是客户经理端的订单不用添加代办事项
        if(waybill.getCustomerManagerId() == null) return null;

        ManagerSchedule ms = new ManagerSchedule();
        ms.setType(type.getCode());
        ms.setWaybillId(waybill.getWaybillId());
        ms.setCustomerManagerId(waybill.getCustomerManagerId());
        ms.setContent(type.getContent());

        return ms;
    }

    @Override
    public void update(ManagerSchedule managerSchedule, LoginUser loginUser) throws BusinessException {

        if (managerSchedule == null) {
            return ;
        }

        if(managerSchedule.getManagerScheduleId() == null){
            throw new BusinessException("ManagerScheduleIdNull", "errors.paramCanNotNullWithName", "ManagerScheduleId");
        }

        if (managerSchedule.getCustomerManagerId() == null) {
            throw new BusinessException("CustomerManagerIdNull", "errors.paramCanNotNullWithName", "CustomerManagerId");
        }

        if (managerSchedule.getWaybillId() == null) {
            throw new BusinessException("managerScheduleWaybillIdNull", "errors.paramCanNotNullWithName", "managerScheduleWaybillId");
        }

        if (managerSchedule.getType() == null) {
            throw new BusinessException("managerScheduleTypeNull", "errors.paramCanNotNullWithName", "managerScheduleType");
        }

        managerSchedule.setLastUpdateTime(new Date());
        managerSchedule.setLastUpdateUserId(loginUser.getUserId());
        managerSchedule.setContent(managerSchedule.getContent());

        managerScheduleDao.update(managerSchedule);
    }

    private boolean exist(int waybillId) {
        ManagerSchedule managerSchedule = new ManagerSchedule();

        managerSchedule.setIsDelete(Integer.valueOf(Constants.flag_false));
        managerSchedule.setWaybillId(waybillId);

        int count = this.countByParam(managerSchedule);

        return count > 0 ? true : false;

    }

    private int countByParam(ManagerSchedule managerSchedule) {
        managerSchedule.setIsDelete(Integer.valueOf(Constants.flag_false));
        return managerScheduleDao.countByParam(managerSchedule);
    }

    @Override
    public void saveOrUpdate(ManagerSchedule managerSchedule, LoginUser loginUser) throws BusinessException {
        if (managerSchedule == null) {
            return ;
        }

        if (managerSchedule.getCustomerManagerId() == null) {
            throw new BusinessException("CustomerManagerIdNull", "errors.paramCanNotNullWithName", "CustomerManagerId");
        }

        if (managerSchedule.getWaybillId() == null) {
            throw new BusinessException("managerScheduleWaybillIdNull", "errors.paramCanNotNullWithName", "managerScheduleWaybillId");
        }

        if (managerSchedule.getType() == null) {
            throw new BusinessException("managerScheduleTypeNull", "errors.paramCanNotNullWithName", "managerScheduleType");
        }

        ManagerSchedule param = new ManagerSchedule();

        param.setCustomerManagerId(managerSchedule.getCustomerManagerId());
        param.setWaybillId(managerSchedule.getWaybillId());
        param.setType(managerSchedule.getType());

        List<ManagerSchedule> list = managerScheduleDao.findByExample(param);

        if (CollectionUtils.isEmpty(list)) {
            this.add(managerSchedule, loginUser);
        }else{
            ManagerSchedule ms = list.get(list.size() - 1);
            if(ms == null) return;
            ms.setContent(managerSchedule.getContent());
            this.update(ms, loginUser);
        }


    }
}
