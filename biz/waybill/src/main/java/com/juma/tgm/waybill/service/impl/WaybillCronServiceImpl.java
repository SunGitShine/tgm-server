package com.juma.tgm.waybill.service.impl;

import com.alibaba.fastjson.JSON;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.conf.domain.ConfParamOption;
import com.juma.conf.service.ConfParamService;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.cron.vo.WaybillMileageBo;
import com.juma.tgm.waybill.dao.WaybillDao;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillOperateTrack;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateApplication;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateType;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillCronService;
import com.juma.tgm.waybill.service.WaybillOperateTrackService;
import com.juma.tgm.waybill.service.WaybillTrackService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName WaybillCronServiceImpl.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年10月16日 下午6:29:37
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

@Service
public class WaybillCronServiceImpl implements WaybillCronService {

    private final Logger log = LoggerFactory.getLogger(WaybillCronServiceImpl.class);
    @Resource
    private WaybillDao waybillDao;
    @Resource
    private WaybillCommonService waybillCommonService;
    @Resource
    private WaybillTrackService waybillTrackService;
    @Resource
    private ConfParamService confParamService;
    @Resource
    private WaybillOperateTrackService waybillOperateTrackService;

    @Override
    public void cronUpdateYesActualMileage(Date startTime, Date endTime) {

        log.info("定时任务实际里程恢复start...");
        PageCondition pageCondition = new PageCondition();
        // 后台导入的运单不更新
        pageCondition.getFilters().put("startTime", startTime);
        pageCondition.getFilters().put("endTime", endTime);
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(Integer.MAX_VALUE);

        // 状态为配送完成或已完成的运单
        List<Integer> statusList = new ArrayList<Integer>();
        statusList.add(Waybill.Status.DELIVERIED.getCode());
        statusList.add(Waybill.Status.PAIED.getCode());
        pageCondition.getFilters().put("statusList", statusList);

        List<Waybill> list = waybillDao.search(pageCondition);

        log.info("定时任务实际里程恢复pageCondition:{},{}.", JSON.toJSONString(pageCondition), list.size());
        for (Waybill waybill : list) {
            try {
                if (null != waybill.getWaybillSource()
                    && Waybill.WaybillSource.BACKGROUND_IMPORT.getCode() == waybill.getWaybillSource()) {
                    continue;
                }

                // 获取里程信息
                WaybillMileageBo waybillMileageBo = waybillTrackService
                    .getMileageInfo(waybill.getWaybillId(), new LoginUser(1));
                // 更改里程信息
                waybillTrackService.changeActualMileage(waybillMileageBo, new LoginUser(1));

                log.info("定时任务实际里程恢复获取到的实际里程waybillId:{}，actualMileage:{}", waybill.getWaybillId(),
                    waybillMileageBo.getActualMileage());
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void cronConstraintFinishWaybill() {
        log.info("定时任务超期强制结束运单start...");
        PageCondition pageCondition = new PageCondition();
        // 后台导入的运单不更新
        pageCondition.getFilters().put("endTime", getEndTime());
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(Integer.MAX_VALUE);

        // 状态为配送中的运单
        pageCondition.getFilters().put("status", Waybill.Status.DELIVERYING.getCode());
        pageCondition.getFilters().put("statusView", Waybill.StatusView.DELIVERYING.getCode());

        List<Waybill> list = waybillDao.search(pageCondition);
        if (list.isEmpty()) {
            return;
        }

        for (Waybill waybill : list) {
            waybill.setStatus(Waybill.Status.DELIVERIED.getCode());
            waybill.setStatusView(Waybill.StatusView.WATING_PAY.getCode());
            waybill.setReconciliationStatus(Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode());
            waybill.setReceivableReconcilicationStatus(Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode());
        }
        waybillDao.batchFinishWaybill(list);

        // 记录日志
        addOperateLogs(list);
    }

    @Override
    public void cronUpdateWaybillAmountStatus(List<Integer> waybillIds,Integer gap) {
        waybillCommonService.freshWaybillAmountStatus(waybillIds,gap);
    }


    private Date getEndTime() {
        List<ConfParamOption> options = confParamService.findParamOptions(Constants.CONSTRAINT_FINISH_WAYBILL_DAY_KEY);

        int hour = -72;

        if (!options.isEmpty() && StringUtils.isNotBlank(options.get(0).getOptionValue())) {
            hour = -BaseUtil.strToNum(options.get(0).getOptionValue());
        }

        return DateUtil.addHours(new Date(), hour);
    }

    // 记录日志
    private void addOperateLogs(List<Waybill> list) {
        List<WaybillOperateTrack> example = new ArrayList<WaybillOperateTrack>();

        for (Waybill waybill : list) {
            WaybillOperateTrack track = new WaybillOperateTrack();
            track.setOperateApplication(OperateApplication.BACKGROUND_SYS.getCode());
            track.setOperateType(OperateType.CONSTRAINT_FINISH_WAYBILL.getCode());
            track.setWaybillId(waybill.getWaybillId());
            track.setDeclareTime(new Date());
            track.setCreateUserId(Constants.SYS_LOGIN_USER.getUserId());
            example.add(track);
        }
        waybillOperateTrackService.batchInsert(example);
    }
}
