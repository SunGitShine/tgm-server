package com.juma.tgm.waybillReconciliation.service.impl;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.fms.domain.ReconciliationItem;
import com.juma.tgm.fms.service.ReconciliationService;
import com.juma.tgm.truck.service.TruckTypeFreightService;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillBo;
import com.juma.tgm.waybill.domain.WaybillOperateTrack;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateApplication;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateType;
import com.juma.tgm.waybill.service.MsgWithBusinessService;
import com.juma.tgm.waybill.service.TaxRateService;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillOperateTrackService;
import com.juma.tgm.waybill.service.WaybillParamService;
import com.juma.tgm.waybill.service.WaybillService;
import com.juma.tgm.waybillReconciliation.domain.WaybillReconciliation;
import com.juma.tgm.waybillReconciliation.service.WaybillReconciliationService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @ClassName WaybillReconciliationServiceImpl.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年7月26日 下午2:55:11
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

@Service
public class WaybillReconciliationServiceImpl implements WaybillReconciliationService {

    private final Logger log = LoggerFactory.getLogger(WaybillReconciliationServiceImpl.class);
    @Resource
    private WaybillService waybillService;
    @Resource
    private WaybillCommonService waybillCommonService;
    @Resource
    private WaybillParamService waybillParamService;
    @Resource
    private TruckRequireService truckRequireService;
    @Resource
    private TaxRateService taxRateService;
    @Resource
    private TruckTypeFreightService truckTypeFreightService;
    @Resource
    private MsgWithBusinessService msgWithBusinessService;
    @Resource
    private WaybillOperateTrackService waybillOperateTrackService;
    @Resource
    private ReconciliationService reconciliationService;

    @Override
    public Page<WaybillReconciliation> search(PageCondition pageCondition, List<String> areaCodeList,
            LoginUser loginUser) {
        List<WaybillReconciliation> result = new ArrayList<WaybillReconciliation>();
        
        // 组装固定查询条件
        pageCondition.getFilters().put("reconciliationStatus", Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode());
        pageCondition.getFilters().put("statusView", Waybill.StatusView.FINISH.getCode());
        pageCondition.setOrderBy(" planDeliveryTime desc ");

        Page<Waybill> page = waybillService.search(loginUser, pageCondition);
        for (Waybill waybill : page.getResults()) {
            WaybillReconciliation waybillReconciliation = new WaybillReconciliation();
            waybillReconciliation.setWaybillId(waybill.getWaybillId());
            waybillReconciliation.setWaybillNo(waybill.getWaybillNo());
            waybillReconciliation.setReconciliationNo(waybill.getReconciliationNo());
            waybillReconciliation.setEstimateFreight(waybill.getEstimateFreight());
            waybillReconciliation.setAfterTaxFreight(waybill.getAfterTaxFreight());
            waybillReconciliation.setShow4DriverFreight(waybill.getShow4DriverFreight());
            waybillReconciliation.setUpdateFreightRemark(waybill.getUpdateFreightRemark());
            waybillReconciliation.setUpdateFreightAuditStatus(waybill.getUpdateFreightAuditStatus());
            waybillReconciliation.setPlanDeliveryTime(waybill.getPlanDeliveryTime());
            waybillReconciliation.setCustomerManagerName(waybill.getCustomerManagerName());
            waybillReconciliation.setCustomerName(waybill.getCustomerName());
            waybillReconciliation.setProjectName(waybill.getProjectName());
            waybillReconciliation.setRebateFee(waybill.getRebateFee());
            waybillReconciliation.setDriverName(waybill.getDriverName());

            waybillReconciliation.setCustomerId(waybill.getCustomerId());
            waybillReconciliation.setCustomerManagerId(waybill.getCustomerManagerId());
            waybillReconciliation.setDriverId(waybill.getDriverId());
            waybillReconciliation.setProjectId(waybill.getProjectId());

            WaybillParam waybillParam = waybillParamService.findByWaybillId(waybill.getWaybillId());
            if (null != waybillParam) {
                waybillReconciliation.setDriverHandlingCost(waybillParam.getDriverHandlingCost());
                waybillReconciliation.setLaborerHandlingCost(waybillParam.getLaborerHandlingCost());
            }

            TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybill.getWaybillId(), loginUser);
            if (null == truckRequire) {
                result.add(waybillReconciliation);
                continue;
            }
            waybillReconciliation.setTaxRateValue(truckRequire.getTaxRateValue());
            result.add(waybillReconciliation);
        }
        return new Page<WaybillReconciliation>(page.getPageNo(), page.getPageSize(), page.getTotal(), result);
    }

    @Override
    public WaybillReconciliation findByWaybillId(Integer waybillId) {
        Waybill waybill = waybillService.getWaybill(waybillId);
        if (null == waybill) {
            return null;
        }
        WaybillReconciliation waybillReconciliation = new WaybillReconciliation();
        waybillReconciliation.setWaybillId(waybillId);
        waybillReconciliation.setEstimateFreight(waybill.getEstimateFreight());
        waybillReconciliation.setShow4DriverFreight(waybill.getShow4DriverFreight());
        waybillReconciliation.setUpdateFreightRemark(waybill.getUpdateFreightRemark());

        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybillId);
        if (null != waybillParam) {
            waybillReconciliation.setDriverHandlingCost(waybillParam.getDriverHandlingCost());
            waybillReconciliation.setLaborerHandlingCost(waybillParam.getLaborerHandlingCost());
        }

        TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybillId, null);
        if (null != truckRequire) {
            waybillReconciliation.setTaxRateValue(truckRequire.getTaxRateValue());
        }
        return waybillReconciliation;
    }

    @Override
    public void update(WaybillReconciliation waybillReconciliation, List<String> areaNodeList, LoginUser loginUser)
            throws BusinessException {
        if (null == waybillReconciliation.getWaybillId()) {
            return;
        }

        StringBuffer changeInfo = new StringBuffer("");
        Waybill waybill = waybillService.getWaybillAndCheckExist(waybillReconciliation.getWaybillId());

        // 原司机搬运费
        BigDecimal oldShow4DriverFreight = waybill.getShow4DriverFreight();
        // 原税前费用
        BigDecimal oldEstimateFreight = waybill.getEstimateFreight();

        // 改价前校验运单信息
        checkWaybillBeforeUpadteFreight(waybill, areaNodeList);

        // 费用合法性校验
        checkFright(waybillReconciliation.getEstimateFreight());
        checkFright(waybillReconciliation.getShow4DriverFreight());
        checkFright(waybillReconciliation.getDriverHandlingCost());
        checkFright(waybillReconciliation.getLaborerHandlingCost());

        // 修改税率
        TruckRequire truckRequire = updateTruckRequire(waybillReconciliation, changeInfo, loginUser);

        // 修改搬运费
        updateHandlingCost(waybillReconciliation, changeInfo, loginUser);

        // 修改运单信息
        waybill.setUpdateFreightRemark(waybillReconciliation.getUpdateFreightRemark());
        waybill.setEstimateFreight(waybillReconciliation.getEstimateFreight() == null ? BigDecimal.ZERO
                : waybillReconciliation.getEstimateFreight());
        waybill.setAfterTaxFreight(truckTypeFreightService.getAfterTaxFright(truckRequire, waybill));
        waybill.setShow4DriverFreight(waybillReconciliation.getShow4DriverFreight() == null ? BigDecimal.ZERO
                : waybillReconciliation.getShow4DriverFreight());

        waybillCommonService.update(waybill, loginUser);

        // ==== 同步更新对帐单 ========

        waybillReconciliation.setAfterTaxFreight(waybill.getAfterTaxFreight());
        changeReconciliationItem(waybillReconciliation);

        changeInfo.append("原税前费用").append(oldEstimateFreight).append(",新税前费用：").append(waybill.getEstimateFreight())
                .append(";");
        changeInfo.append("原司机价").append(oldShow4DriverFreight).append(",新原司机价：")
                .append(waybill.getShow4DriverFreight()).append(";");

        // 通知司机：司机结算价改变
        if ("是".equals(waybillReconciliation.getNoticeToDriver())
                && waybill.getShow4DriverFreight().compareTo(oldShow4DriverFreight) != 0) {
            log.info("司机结算价改变,发送信息：{}", waybill.getWaybillId());
            msgWithBusinessService.pushChangePriceMsgToDriver(waybill.getWaybillId(), oldShow4DriverFreight);
        }

        // 通知发单人： 税前费用该变
        if ("是".equals(waybillReconciliation.getNoticeToWaybillOwner())
                && waybill.getEstimateFreight().compareTo(oldEstimateFreight) != 0) {
            msgWithBusinessService.pushChangePricelMsgToWaybillOwner(waybill.getWaybillId(), oldEstimateFreight, loginUser);
        }

        // 操作轨迹
        addWaybillOperateTrack(waybill.getWaybillId(), oldEstimateFreight, changeInfo.toString(), loginUser);
    }

    private void changeReconciliationItem(WaybillReconciliation waybillReconciliation) {
        ReconciliationItem item = new ReconciliationItem();
        item.setWaybillId(waybillReconciliation.getWaybillId());
        item.setEstimateFreight(waybillReconciliation.getEstimateFreight());
        item.setAfterTaxFreight(waybillReconciliation.getAfterTaxFreight());
        item.setTaxRateValue(waybillReconciliation.getTaxRateValue());
        item.setDriverHandlingFee(waybillReconciliation.getDriverHandlingCost());
        item.setLaborerHandlingFee(waybillReconciliation.getLaborerHandlingCost());
        item.setShow4DriverFreight(waybillReconciliation.getShow4DriverFreight());
        item.setUpdateFreightRemark(waybillReconciliation.getUpdateFreightRemark());
        reconciliationService.updateReconciliationItemByWaybillId(item);
    }

    // 操作轨迹:由于只是简单的添加一条记录，故不使用异步
    private void addWaybillOperateTrack(Integer waybillId, BigDecimal oldEstimateFreight, String remark,
            LoginUser loginUser) {
        try {
            WaybillOperateTrack track = new WaybillOperateTrack();
            track.setWaybillId(waybillId);
            track.setOperateApplication(OperateApplication.BACKGROUND_SYS.getCode());
            track.setOperateType(OperateType.UPDATE_FREIGHT.getCode());
            track.setDeclareTime(new Date());
            track.setRemark(remark);
            waybillOperateTrackService.insert(track, loginUser);
        } catch (Exception e) {
        }
    }

    // 修改费率
    private TruckRequire updateTruckRequire(WaybillReconciliation waybillReconciliation, StringBuffer changeInfo,
            LoginUser loginUser) {
        TruckRequire truckRequire = truckRequireService
                .findTruckRequireByWaybillId(waybillReconciliation.getWaybillId(), loginUser);

        // 更改记录
        BigDecimal oldTaxRateValue = truckRequire.getTaxRateValue();
        BigDecimal newTaxRateValue = waybillReconciliation.getTaxRateValue();
        changeInfo.append("原税率").append(oldTaxRateValue).append(",新税率：").append(newTaxRateValue).append(";");

        // 更改操作
        if (null == waybillReconciliation.getTaxRateValue()) {
            // 清除税率
            truckRequire.setTaxRateValue(null);
            truckRequireService.removeNullInfo(truckRequire);
        } else {
            truckRequire.setTaxRateValue(newTaxRateValue);
            truckRequireService.update(truckRequire);
        }
        
        waybillReconciliation.setTaxRateValue(truckRequire.getTaxRateValue());
        return truckRequire;
    }

    // 修改搬运费
    private WaybillParam updateHandlingCost(WaybillReconciliation waybillReconciliation, StringBuffer changeInfo,
            LoginUser loginUser) {
        WaybillParam param = waybillParamService.findByWaybillId(waybillReconciliation.getWaybillId());

        // 更改操作
        WaybillParam waybillParam = new WaybillParam();
        waybillParam.setWaybillId(waybillReconciliation.getWaybillId());
        waybillParam.setDriverHandlingCost(waybillReconciliation.getDriverHandlingCost() == null ? BigDecimal.ZERO
                : waybillReconciliation.getDriverHandlingCost());
        waybillParam.setLaborerHandlingCost(waybillReconciliation.getLaborerHandlingCost() == null ? BigDecimal.ZERO
                : waybillReconciliation.getLaborerHandlingCost());
        waybillParamService.addOrUpdateOnly(waybillParam, loginUser);

        // 更改记录
        if (null != param) {
            changeInfo.append("原司机搬运费").append(param.getDriverHandlingCost()).append(",新司机搬运费：")
                    .append(waybillParam.getDriverHandlingCost()).append(";");
            changeInfo.append("原小工搬运费").append(param.getLaborerHandlingCost()).append(",新小工搬运费：")
                    .append(waybillParam.getLaborerHandlingCost()).append(";");

        }
        return waybillParam;
    }

    // 改价前校验运单信息
    private void checkWaybillBeforeUpadteFreight(Waybill waybill, List<String> areaNodeList) {
        // 只有已完成的运单才可以改价
        if (NumberUtils.compare(Waybill.StatusView.FINISH.getCode(), waybill.getStatusView()) != 0) {
            throw new BusinessException("waybillIsNotFinish", "waybill.error.waybillIsNotFinish");
        }

        // 有改价待审核的运单不能改价
        if (null != waybill.getUpdateFreightAuditStatus()
                && NumberUtils.compare(Waybill.UpdateFreightAuditStatus.WATING_AUDIT.getCode(),
                        waybill.getUpdateFreightAuditStatus()) == 0) {
            throw new BusinessException("waybillIsAuditStatus", "waybill.error.waybillIsAuditStatus");
        }

        // 对账完成的运单不能改价
        if (NumberUtils.compare(Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode(),
                waybill.getReconciliationStatus()) == 0) {
            throw new BusinessException("waybillHasReconciliation", "waybill.error.waybillHasReconciliation");
        }
    }

    @Override
    public BigDecimal calSysDriverFreight(WaybillReconciliation waybillReconciliation) {
        // 用车要求
        TruckRequire truckRequire = new TruckRequire();
        truckRequire.setTaxRateValue(waybillReconciliation.getTaxRateValue());

        // 税后费用
        Waybill waybill = new Waybill();
        waybill.setEstimateFreight(waybillReconciliation.getEstimateFreight() == null ? BigDecimal.ZERO
                : waybillReconciliation.getEstimateFreight());
        waybill.setAfterTaxFreight(truckTypeFreightService.getAfterTaxFright(truckRequire, waybill));

        // 搬运费信息
        WaybillParam waybillParam = new WaybillParam();
        waybillParam.setDriverHandlingCost(waybillReconciliation.getDriverHandlingCost());
        waybillParam.setLaborerHandlingCost(waybillReconciliation.getLaborerHandlingCost());

        // 重新计算司机结算价
        WaybillBo bo = new WaybillBo();
        bo.setWaybill(waybill);
        waybillService.settingExtraFee(bo, waybillParam);

        return waybill.getShow4DriverFreight();
    }

    // 校验费用
    private void checkFright(BigDecimal freight) {
        if (null == freight) {
            return;
        }

        if (freight.compareTo(new BigDecimal("999999.99")) == 1) {
            throw new BusinessException("freightTooMax", "waybill.error.freightTooMax");
        }
    }
   //v2版本
    @Deprecated
    @Override
    public void updateReconciliationStatus(int waybillId, LoginUser loginUser) throws BusinessException {
        Waybill waybill = new Waybill();
        waybill.setWaybillId(waybillId);
        waybill.setReconciliationStatus(Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode());
        waybill.setLastUpdateUserId(loginUser.getUserId());
        waybillCommonService.update(waybill, loginUser);

        // 操作轨迹：需要同步记录
        WaybillOperateTrack waybillOperateTrack = new WaybillOperateTrack();
        waybillOperateTrack.setWaybillId(waybill.getWaybillId());
        waybillOperateTrack.setOperateApplication(OperateApplication.BACKGROUND_SYS.getCode());
        waybillOperateTrack.setOperateType(OperateType.RECONCILIATION_FINISH.getCode());
        waybillOperateTrackService.insert(waybillOperateTrack, loginUser);
    }

}
