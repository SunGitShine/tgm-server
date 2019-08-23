package com.juma.tgm.waybill.service.impl.customize.jumaPs;

import com.giants.common.exception.BusinessException;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.region.service.RegionTgmService;
import com.juma.tgm.truck.service.TruckTypeFreightService;
import com.juma.tgm.waybill.domain.*;
import com.juma.tgm.waybill.service.*;
import com.juma.tgm.waybill.service.customize.jumaPs.JumaPsWaybillService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 驹马配送运单定制需求
 * @ClassName: JumaPsWaybillServiceImpl
 * @Description:
 * @author: liang
 * @date: 2018-03-30 11:54
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
@Service
public class JumaPsWaybillServiceImpl implements JumaPsWaybillService {

    @Resource
    private WaybillCommonService waybillCommonService;
    @Autowired
    private WaybillDeliveryAddressService waybillDeliveryAddressService;
    @Autowired
    private WaybillReceiveAddressService waybillReceiveAddressService;
    @Resource
    private RegionTgmService regionTgmService;
    @Autowired
    private TruckRequireService truckRequireService;
    @Resource
    private WaybillService waybillService;
    @Resource
    private WaybillParamService waybillParamService;
    /**
     * 带业务逻辑的发送消息
     */
    @Resource
    private MsgWithBusinessService msgWithBusinessService;
    @Autowired
    private TruckTypeFreightService truckTypeFreightService;


    @Override
    public Waybill customerManagerModifyFreight(Waybill waybill, LoginEmployee loginEmployee) throws BusinessException {
        if (waybill == null) throw new BusinessException("waybillNull", "errors.paramCanNotNullWithName", "运单");

        if (waybill.getWaybillId() == null)
            throw new BusinessException("waybillIdNull", "errors.paramCanNotNullWithName", "运单id");

        // 审核中不能再次更改
        Waybill db = waybillCommonService.getWaybillById(waybill.getWaybillId());
        if (db.getUpdateFreightAuditStatus() != null
                && NumberUtils.compare(Waybill.UpdateFreightAuditStatus.WATING_AUDIT.getCode(),
                db.getUpdateFreightAuditStatus()) == 0) {
            throw new BusinessException("waybillPriceAuditError", "waybill.error.priceAudit");
        }

        if (waybill.getEstimateFreight() == null)
            throw new BusinessException("waybillNull", "errors.paramCanNotNullWithName", "运单价格");

        // 改价值必须大于等于0
        if (new BigDecimal(0).compareTo(waybill.getEstimateFreight()) > 0)
            throw new BusinessException("waybillPriceNull", "errors.paramErrorWithName", "运单价格");
        BigDecimal oldDriverFreight = db.getShow4DriverFreight();
        BigDecimal oldFreight = db.getEstimateFreight();

        WaybillBo bo = new WaybillBo();
        bo.setWaybill(db);
        // 派车前
        if (NumberUtils.compare(Waybill.StatusView.WATING_RECEIVE.getCode(), db.getStatusView()) == 0) {
            // 计算价格
            this.buildCalculateFreight(bo, loginEmployee);
            this.getNewFreight(waybill, db);
            // 保存数据
            waybillCommonService.update(db, loginEmployee);
            // 发送推送消息
//            msgWithBusinessService.modifyFreightPushMsg(db, oldDriverFreight, oldFreight, loginEmployee);
            return db;
        }
        // 已派车
        if (NumberUtils.compare(Waybill.StatusView.WATING_DELIVERY.getCode(), db.getStatusView()) == 0
                || NumberUtils.compare(Waybill.StatusView.DELIVERYING.getCode(), db.getStatusView()) == 0
                || NumberUtils.compare(Waybill.StatusView.WATING_PAY.getCode(), db.getStatusView()) == 0) {

            if (db.getEstimateFreight().compareTo(waybill.getEstimateFreight()) < 0) {
                // 已派车--改高
                this.buildCalculateFreight(bo, loginEmployee);
                this.getNewFreight(waybill, db);
                waybillCommonService.update(db, loginEmployee);

                // 发送推送消息
//                msgWithBusinessService.modifyFreightPushMsg(db, oldDriverFreight, oldFreight, loginEmployee);
                return db;
            } else if (db.getEstimateFreight().compareTo(waybill.getEstimateFreight()) > 0) {
                // 已派车--改低
                // 改低需要审核
                if (StringUtils.isBlank(waybill.getUpdateFreightAuditRemark())) {
                    throw new BusinessException("updateFreightAuditRemarkNull", "errors.paramCanNotNullWithName",
                            "改价备注");
                }
                db.setFreightToBeAudited(waybill.getEstimateFreight());
                db.setUpdateFreightAuditRemark(waybill.getUpdateFreightAuditRemark());
                db.setUpdateFreightAuditStatus(Waybill.UpdateFreightAuditStatus.WATING_AUDIT.getCode());

                waybillCommonService.update(db, loginEmployee);

                // 记录上一次税前费用
                WaybillParam waybillParam = waybillParamService.findByWaybillId(db.getWaybillId());
                if (null == waybillParam) {
                    return db;
                }
                waybillParam.setLastEstimateFreight(db.getEstimateFreight());
                waybillParamService.update(waybillParam, loginEmployee);

                return db;
            }
        }

        return db;
    }

    /**
     * 专车计价方式
     * @param bo
     * @param loginUser
     */
    private void buildCalculateFreight(WaybillBo bo, LoginUser loginUser) {
        Waybill waybillDb = bo.getWaybill();
        if (waybillDb == null) {
            throw new BusinessException("waybillNull", "errors.paramErrorWithName", "waybill");
        }

        // 取货地
        WaybillDeliveryAddress start = waybillDeliveryAddressService.findByWaybillId(waybillDb.getWaybillId());
        // 配送地
        List<WaybillReceiveAddress> receiveAddressList = waybillReceiveAddressService
                .findAllByWaybillId(waybillDb.getWaybillId());

        DistanceAndPriceData result = this.getDistanceAndPriceData(bo, receiveAddressList, start, loginUser);

        waybillDb.setCalculatedFreight(result.getPrice());
    }

    /**
     * 重新计算费用
     *
     * @param waybillBo          waybill 不能为空
     * @param receiveAddressList 配送地 不能为空
     * @param start              取货地 不能为空
     * @return
     */
    private DistanceAndPriceData getDistanceAndPriceData(WaybillBo waybillBo,
                                                         List<WaybillReceiveAddress> receiveAddressList, WaybillDeliveryAddress start, LoginUser loginUser) {
        // 构造取货地
        CityAdressData startCityData = new CityAdressData();
        startCityData.setAddress(start.getAddressDetail());
        startCityData.setCoordinate(start.getCoordinates());
        startCityData.setRegionCode(regionTgmService.findRegionCodeByCoordinate(start.getCoordinates()));
        startCityData.setCity(regionTgmService.findRegionNameByCode(start.getRegionCode()));

        // 构造收货地
        List<CityAdressData> terminateCityList = new ArrayList<>();

        CityAdressData terminateCityData = null;
        for (WaybillReceiveAddress address : receiveAddressList) {
            terminateCityData = new CityAdressData();

            terminateCityData.setRegionCode(regionTgmService.findRegionCodeByCoordinate(address.getCoordinates()));
            terminateCityData.setAddress(address.getAddressDetail());
            terminateCityData.setCity(regionTgmService.findRegionNameByCode(address.getRegionCode()));
            terminateCityData.setCoordinate(address.getCoordinates());

            terminateCityList.add(terminateCityData);
        }

        TruckRequire truckRequire = truckRequireService
                .findTruckRequireByWaybillId(waybillBo.getWaybill().getWaybillId(), loginUser);
        if (null != truckRequire) {
            waybillBo.setTruckRequire(truckRequire);
        }

        return waybillService.calWaybillPrice(startCityData, terminateCityList, waybillBo, loginUser);
    }

    /**
     * 执行价格更新
     *
     * @param waybillVo 包含预估运费和运单id
     * @param waybillDb 数据库中的原值
     */
    private Waybill getNewFreight(Waybill waybillVo, Waybill waybillDb) {
        if (waybillVo.getEstimateFreight() == null) {
            throw new BusinessException("waybillEstimateFreightNull", "errors.paramErrorWithName", "预估价格");
        }
        // 预估价格
        waybillDb.setEstimateFreight(waybillVo.getEstimateFreight());
        // 税后
        waybillDb.setAfterTaxFreight(truckTypeFreightService.getAfterTaxFright(null, waybillDb));

        // 运单生成后税前费用和司机结算价相互独立
        // 司机结算价
        // WaybillBo bo = new WaybillBo();
        // bo.setWaybill(waybillDb);
        // WaybillParam oldParam =
        // waybillParamService.findByWaybillId(waybillDb.getWaybillId());
        // WaybillParam wbParam = oldParam == null ? new WaybillParam() :
        // oldParam;
        // settingExtraFee(bo, wbParam);

        // 返点费-通过饭店率计算不需要保存到数据库

        waybillDb.setCompareResult(
                BaseUtil.calCompareResult(waybillDb.getCalculatedFreight().multiply(new BigDecimal("1.1")),
                        waybillDb.getEstimateFreight(), true));
        return waybillDb;
    }
}
