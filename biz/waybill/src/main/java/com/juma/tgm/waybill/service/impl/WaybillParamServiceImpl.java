package com.juma.tgm.waybill.service.impl;

import com.giants.common.exception.BusinessException;
import com.google.common.collect.Lists;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.ManagerScheduleType;
import com.juma.tgm.customerManager.service.ManagerScheduleService;
import com.juma.tgm.tools.service.MessagePushService;
import com.juma.tgm.truck.service.TruckTypeFreightService;
import com.juma.tgm.waybill.dao.WaybillParamDao;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillAmount;
import com.juma.tgm.waybill.domain.WaybillBo;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.domain.erp.ErpWaybillResult;
import com.juma.tgm.waybill.domain.vo.WaybillParamFilter;
import com.juma.tgm.waybill.service.WaybillAmountService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillParamService;
import com.juma.tgm.waybill.service.WaybillService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class WaybillParamServiceImpl implements WaybillParamService {

    @Resource
    private WaybillParamDao waybillParamDao;
    @Resource
    private WaybillService waybillService;
    @Resource
    private MessagePushService messagePushService;
    @Resource
    private TruckTypeFreightService truckTypeFreightService;
    @KSession("ksession")
    private KieSession kSession;

    @Resource
    private ManagerScheduleService managerScheduleService;
    @Resource
    private WaybillCommonService waybillCommonService;
    @Resource
    private WaybillAmountService waybillAmountService;

    @Override
    public void insert(WaybillParam waybillParam, LoginUser loginUser) {
        waybillParam.setDriverRead(0);
        waybillParam.setIsCheckGoods(0);
        waybillParam.setCreateUserId(loginUser.getUserId());
        waybillParamDao.insert(waybillParam);
    }

    @Override
    public void addOrUpdateOnly(WaybillParam waybillParam, LoginUser loginUser) throws BusinessException {
        if (null != waybillParam) {
            WaybillParam param = findByWaybillId(waybillParam.getWaybillId());
            if (null != param) {
                waybillParam.setParamId(param.getParamId());
                update(waybillParam, loginUser);
            } else {
                insert(waybillParam, loginUser);
            }
        }
    }

    @Override
    public void addOrUpdate(WaybillParam waybillParam, LoginUser loginUser) throws BusinessException {
        if (null != waybillParam) {
            Integer waybillId = waybillParam.getWaybillId();
            WaybillParam param = findByWaybillId(waybillId);
            Waybill waybill = waybillService.getWaybill(waybillId);
            if (null != param) {
                waybillParam.setParamId(param.getParamId());
                update(waybillParam, loginUser);
                managerScheduleService.saveOrUpdate(
                        managerScheduleService.buildIncreaseCarryfeeSchedule(waybill, waybillParam, ManagerScheduleType.WAYBILL_TYPE_INCREASE_CARRYFEE_TPL), loginUser);
            } else {
                insert(waybillParam, loginUser);
                managerScheduleService.saveOrUpdate(
                        managerScheduleService.buildIncreaseCarryfeeSchedule(waybill, waybillParam, ManagerScheduleType.WAYBILL_TYPE_INCREASE_CARRYFEE_TPL), loginUser);
            }

            // 非项目运单需要更改司机价
            if (null == waybill.getProjectId()) {
                WaybillBo waybillBo = new WaybillBo();
                waybillBo.setWaybill(waybill);
                waybillService.settingExtraFee(waybillBo, waybillParam);
                waybillCommonService.update(waybillBo.getWaybill(), loginUser);
            }

            messagePushService.carryFeeMessage(waybillId, loginUser);
        }
    }

    @Override
    public void update(WaybillParam waybillParam, LoginUser loginUser) {
        waybillParam.setLastUpdateUserId(loginUser.getUserId());
        waybillParam.setLastUpdateTime(new Date());
        waybillParamDao.update(waybillParam);
    }

    @Override
    public void batchUpdateErpResult(List<ErpWaybillResult> rows, LoginUser loginUser) {
        waybillParamDao.batchUpdateErpResult(rows);
    }

    @Override
    public WaybillParam findByWaybillId(Integer waybillId) {
        if (null != waybillId) {
            return waybillParamDao.findByWaybillId(waybillId);
        }
        return null;
    }

    @Override
    public BigDecimal buildHandlingCost(Integer waybillId) {
        WaybillParam waybillParam = findByWaybillId(waybillId);
        if (null != waybillParam) {
            BigDecimal driverHandlingCost = waybillParam.getDriverHandlingCost();
            BigDecimal laborerHandlingCost = waybillParam.getLaborerHandlingCost();
            if (null != driverHandlingCost && null == laborerHandlingCost) {
                return driverHandlingCost;
            } else if (null == driverHandlingCost && null != laborerHandlingCost) {
                return laborerHandlingCost;
            } else if (null != driverHandlingCost && null != laborerHandlingCost) {
                return driverHandlingCost.add(laborerHandlingCost);
            }
        }
        return BigDecimal.ZERO;
    }

    @Override
    public WaybillParam findCountCost(List<Integer> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            return waybillParamDao.findCountCost(list);
        }
        return null;
    }

    @Override
    public void driverReadWaybill(Integer waybillId, LoginUser loginUser) {
        Waybill waybill = waybillService.getWaybill(waybillId);
        if (null == waybill) {
            return;
        }
        if(waybill.getStatus() == null || waybill.getStatus() != Waybill.Status.ASSIGNED.getCode()) return;

        WaybillParam waybillParam = findByWaybillId(waybillId);
        boolean sendMsgFlag = false;
        if (waybillParam == null) {
            WaybillParam newWaybillParam = new WaybillParam();
            newWaybillParam.setWaybillId(waybillId);
            newWaybillParam.setDriverRead(1);
            newWaybillParam.setDriverReadTime(new Date());
            insert(newWaybillParam, loginUser);
            sendMsgFlag = true;
        }
        if (waybillParam != null && (waybillParam.getDriverRead() == null || waybillParam.getDriverRead() == 0)) {
            WaybillParam newWaybillParam = new WaybillParam();
            newWaybillParam.setParamId(waybillParam.getParamId());
            newWaybillParam.setDriverRead(1);
            newWaybillParam.setDriverReadTime(new Date());
            update(newWaybillParam, loginUser);
            sendMsgFlag = true;
        }
        if (sendMsgFlag && Waybill.ReceiveWay.RECEIVED.getCode() != waybill.getReceiveWay()) {
            messagePushService.driverConfirmAssigned(waybillId, loginUser);
        }
        this.modifyTransformBillDriverRead(waybillId, 1, new Date(), loginUser);
    }

    // 甲方同步司机已读、未读状态
    private void modifyTransformBillDriverRead(Integer transformBillLinkId, Integer driverRead, Date driverReadTime,
            LoginUser loginUser) {
        if (null == driverRead) {
            return;
        }

        WaybillParam transformBillParam = this.findByTransformBillLinkId(transformBillLinkId);
        if (null == transformBillParam) {
            return;
        }

        transformBillParam.setDriverRead(driverRead);
        transformBillParam.setDriverReadTime(driverReadTime);
        this.update(transformBillParam, loginUser);
    }

    @Override
    public void driverUnReadWaybill(Integer waybillId, LoginUser loginUser) {
        Waybill waybill = waybillService.getWaybill(waybillId);
        if (null == waybill) {
            return;
        }

        WaybillParam waybillParam = findByWaybillId(waybillId);
        if (null == waybillParam) {
            return;
        }

        WaybillParam updateWaybillParam = new WaybillParam();
        updateWaybillParam.setParamId(waybillParam.getParamId());
        updateWaybillParam.setDriverRead(0);
        update(updateWaybillParam, loginUser);

        this.modifyTransformBillDriverRead(waybillId, 0, null, loginUser);
    }

    @Override
    public void confirmHasCheckGoods(Integer waybillId, LoginUser loginUser) throws BusinessException {
        waybillService.getWaybillAndCheckExist(waybillId);

        WaybillParam waybillParam = findByWaybillId(waybillId);
        if (null == waybillParam) {
            return;
        }

        WaybillParam updateWaybillParam = new WaybillParam();
        updateWaybillParam.setParamId(waybillParam.getParamId());
        updateWaybillParam.setIsCheckGoods(1);
        update(updateWaybillParam, loginUser);

        // 更改转运单信息
        this.modifyTransformBillCheckGoods(waybillId, loginUser);
    }

    // 更改转运单信息
    private void modifyTransformBillCheckGoods(Integer transformBillLinkId, LoginUser loginUser) {
        WaybillParam transformBillParam = findByTransformBillLinkId(transformBillLinkId);
        if (null == transformBillParam) {
            return;
        }

        WaybillParam updateWaybillParam = new WaybillParam();
        updateWaybillParam.setParamId(transformBillParam.getParamId());
        updateWaybillParam.setIsCheckGoods(1);
        update(updateWaybillParam, loginUser);
    }

    @Override
    public void doCompleteWaybillParam(Integer waybillId, String valuationConstJson, LoginUser loginUser) {
        if (null == waybillId) {
            return;
        }
        
        WaybillParam updateWaybillParam = findByWaybillId(waybillId);
        if (null == updateWaybillParam) {
            return;
        }

        if (StringUtils.isNotBlank(valuationConstJson)) {
            updateWaybillParam.setValuationConstJson(valuationConstJson);
            update(updateWaybillParam, loginUser);
        }

        // 判断是不是承运单
        WaybillParam transformBillParam = this.findByTransformBillLinkId(waybillId);
        // 不是承运单
        if (null == transformBillParam) {
            Waybill waybill = waybillService.getWaybill(waybillId);
            if (null == waybill) {
                return;
            }

            waybill = waybillService.calculateProjectFreight(waybillId, waybill);
            if (waybillCommonService.checkCustomerPriceUpperLimit(waybill.getEstimateFreight())) {
                throw new BusinessException("customerPriceTooUpper", "errors.common.prompt", "运单税前费用过高，请认真核对！");
            }

            waybillCommonService.update(waybill, loginUser);

            doCompleteWaybillAmount(waybillId, waybill.getEstimateFreight(), waybill.getShow4DriverFreight(), loginUser);
            return;
        }

        // 完善转运方的运单信息
        this.updateTransformBillParam(transformBillParam, valuationConstJson, loginUser);
        this.calculateTransformBillProjectFreight(waybillId, transformBillParam.getTransformBillVendorFeeRate(),
                loginUser);
    }

    // 完善运费信息
    private void doCompleteWaybillAmount(Integer waybillId, BigDecimal customerFreightWithTax,
        BigDecimal vendorFreightWithTax, LoginUser loginUser) {
        WaybillAmount amount = waybillAmountService.loadByWaybillId(waybillId);
        if (null == amount) {
            amount = new WaybillAmount();
        }

        amount.setCustomerFreightWithTax(customerFreightWithTax);
        amount.setVendorFreightWithTax(vendorFreightWithTax);
        amount.setLastCustomerFreightWithTax(customerFreightWithTax);
        amount.setLastVendorFreightWithTax(vendorFreightWithTax);
        waybillAmountService.addOrUpdate(amount, loginUser);
    }

    // 完善转运方的运单信息
    public void updateTransformBillParam(WaybillParam transformBillParam, String valuationConstJson,
            LoginUser loginUser) {
        if (null == transformBillParam) {
            return;
        }

        transformBillParam.setValuationConstJson(valuationConstJson);
        update(transformBillParam, loginUser);
    }

    // 完善转运方的运单价格信息
    public void calculateTransformBillProjectFreight(Integer vendorBillId, BigDecimal transformBillVendorFeeRate,
            LoginUser loginUser) {
        Waybill transformBill = waybillCommonService.findWaybillByTransformBillId(vendorBillId);
        if (null == transformBill) {
            return;
        }

        transformBill = waybillService.calculateProjectFreight(transformBill.getWaybillId(), transformBill);
        if (waybillCommonService.checkCustomerPriceUpperLimit(transformBill.getEstimateFreight())) {
            throw new BusinessException("customerPriceTooUpper", "errors.common.prompt", "运单税前费用过高，请认真核对！");
        }

        // 转运单若不按费率结算，则不改变甲方的司机结算价
        transformBill.setShow4DriverFreight(null);
        waybillCommonService.update(transformBill, loginUser);

        // 若按费率结算，则同步更改承运商的价格信息
        if (null == transformBill.getEstimateFreight() || null == transformBillVendorFeeRate) {
            return;
        }

        // 更改司机结算价为乙方的税前价
        BigDecimal vendorEstimateFreight = transformBill.getEstimateFreight()
            .multiply(BigDecimal.ONE.subtract(transformBillVendorFeeRate));
        transformBill.setShow4DriverFreight(vendorEstimateFreight);
        waybillCommonService.update(transformBill, loginUser);
        doCompleteWaybillAmount(transformBill.getWaybillId(), transformBill.getEstimateFreight(),
            transformBill.getShow4DriverFreight(), loginUser);

        // 更改乙方价格信息
        Waybill vendorWaybill = waybillCommonService.getWaybillById(vendorBillId);
        if (null == vendorWaybill) {
            return;
        }

        vendorWaybill.setEstimateFreight(vendorEstimateFreight);
        vendorWaybill.setAfterTaxFreight(truckTypeFreightService.getAfterTaxFright(null, vendorWaybill));
        vendorWaybill.setShow4DriverFreight(
            vendorWaybill.getAfterTaxFreight().subtract(vendorWaybill.getEstimateFreight().multiply(
                vendorWaybill.getRebateRate() == null ? BigDecimal.ZERO : vendorWaybill.getRebateRate())));
        waybillCommonService.update(vendorWaybill, loginUser);
        doCompleteWaybillAmount(vendorWaybill.getWaybillId(), vendorWaybill.getEstimateFreight(),
            vendorWaybill.getShow4DriverFreight(), loginUser);

    }

    @Override
    public WaybillParam findByTransformBillLinkId(Integer transformBillLinkId) {
        WaybillParam example = new WaybillParam();
        example.setTransformBillLinkId(transformBillLinkId);
        List<WaybillParam> list = waybillParamDao.findByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<WaybillParam> findByFilter(WaybillParamFilter filter) throws BusinessException {
        if( CollectionUtils.isEmpty(filter.getWaybillIds()) ){
            return Lists.newArrayList();
        }
        return waybillParamDao.selectByExample(filter);
    }

    @Override
    public List<WaybillParam> findByWaybillIds(List<Integer> waybillIds) throws BusinessException {
        WaybillParamFilter filter = new WaybillParamFilter();
        filter.setWaybillIds(waybillIds);
        return this.findByFilter(filter);
    }

}
