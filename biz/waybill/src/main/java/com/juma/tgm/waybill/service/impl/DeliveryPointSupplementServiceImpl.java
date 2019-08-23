package com.juma.tgm.waybill.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.giants.common.tools.Page;
import com.juma.tgm.waybill.domain.vo.WaybillQueryVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginEcoUser;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.common.Constants;
import com.juma.tgm.waybill.dao.DeliveryPointSupplementDao;
import com.juma.tgm.waybill.domain.DeliveryPointSupplement;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillBindFence;
import com.juma.tgm.waybill.domain.WaybillBo;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.domain.WaybillReceiveAddress;
import com.juma.tgm.waybill.domain.WaybillReceiveAddressResponse;
import com.juma.tgm.waybill.service.DeliveryPointSupplementService;
import com.juma.tgm.waybill.service.MsgWithBusinessService;
import com.juma.tgm.waybill.service.WaybillAutoFenceServicve;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillParamService;
import com.juma.tgm.waybill.service.WaybillReceiveAddressService;
import com.juma.tgm.waybill.service.WaybillService;

/**
 * 运单线路修改
 *
 * @ClassName: DeliveryPointSupplementServiceImpl
 * @Description:
 * @author: liang
 * @date: 2017-04-27 16:02
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
@Service
public class DeliveryPointSupplementServiceImpl implements DeliveryPointSupplementService {

    private static final Logger log = LoggerFactory.getLogger(DeliveryPointSupplementServiceImpl.class);

    @Resource
    private DeliveryPointSupplementDao deliveryPointSupplementDao;

    @Resource
    private WaybillService waybillService;

    @Resource
    private WaybillAutoFenceServicve waybillAutoFenceServicve;
    
    @Resource
    private MsgWithBusinessService msgWithBusinessService;

    @Resource
    private WaybillParamService waybillParamService;
    
    @Resource
    private WaybillCommonService waybillCommonService;
    
    @Resource
    private WaybillReceiveAddressService waybillReceiveAddressService;

    @Override
    public Integer add(DeliveryPointSupplement deliveryPointSupplement, LoginEcoUser loginEcoUser)
            throws BusinessException {
        if (deliveryPointSupplement == null) {
            throw new BusinessException("DeliveryPointSupplementNull", "errors.paramCanNotNull");
        }

        if (deliveryPointSupplement.getWaybillId() == null) {
            throw new BusinessException("WaybillIdNull", "errors.paramCanNotNull");
        }
        if (deliveryPointSupplement.getImg() == null) {
            throw new BusinessException("ImgNull", "errors.paramCanNotNull");
        }

        deliveryPointSupplement.setIsDelete(Constants.flag_false);
        deliveryPointSupplement.setCreateTime(new Date());
        deliveryPointSupplement.setCreateUserId(loginEcoUser.getUserId());
        deliveryPointSupplement.setLastUpdateTime(deliveryPointSupplement.getCreateTime());
        deliveryPointSupplement.setLastUpdateUserId(loginEcoUser.getUserId());
        deliveryPointSupplementDao.insert(deliveryPointSupplement);

        // 在运单表中标记已上传配送点修改记录
        Waybill waybill = waybillService.getWaybill(deliveryPointSupplement.getWaybillId());

        if (waybill == null)
            return deliveryPointSupplement.getDeliveryPointSupplementId();

        if (NumberUtils.compare(waybill.getIsChangeDeliveryPoint(), Constants.flag_true) == 0) {
            // 如果已修改则不再更新数据
            return deliveryPointSupplement.getDeliveryPointSupplementId();
        }

        waybill.setIsChangeDeliveryPoint(Waybill.ChangeDeliveryPoint.HAS_UPLOAD.getCode());
        waybill.setLastUpdateTime(new Date());
        waybill.setLastUpdateUserId(loginEcoUser.getUserId());

        waybillCommonService.update(waybill, loginEcoUser);

        return deliveryPointSupplement.getDeliveryPointSupplementId();
    }

    @Override
    public List<Integer> addBatch(List<DeliveryPointSupplement> deliveryPointSupplements, LoginEcoUser loginEcoUser)
            throws BusinessException {
        List<Integer> ids = new ArrayList<>();
        for (DeliveryPointSupplement dps : deliveryPointSupplements) {
            ids.add(this.add(dps, loginEcoUser));
        }
        return ids;
    }

    @Override
    public void update(DeliveryPointSupplement deliveryPointSupplement, LoginEcoUser loginEcoUser)
            throws BusinessException {
        if (deliveryPointSupplement == null) {
            throw new BusinessException("DeliveryPointSupplementNull", "errors.paramCanNotNull");
        }

        if (deliveryPointSupplement.getWaybillId() == null) {
            throw new BusinessException("WaybillIdNull", "errors.paramCanNotNull");
        }

        deliveryPointSupplement.setLastUpdateTime(deliveryPointSupplement.getCreateTime());
        deliveryPointSupplement.setLastUpdateUserId(loginEcoUser.getUserId());
        deliveryPointSupplementDao.update(deliveryPointSupplement);
    }

    @Override
    public void del(DeliveryPointSupplement deliveryPointSupplement, LoginEmployee loginEmployee)
            throws BusinessException {

        deliveryPointSupplement.setIsDelete(Constants.flag_true);
        deliveryPointSupplement.setLastUpdateTime(deliveryPointSupplement.getCreateTime());
        deliveryPointSupplement.setLastUpdateUserId(loginEmployee.getUserId());
        deliveryPointSupplementDao.update(deliveryPointSupplement);
    }

    @Override
    public void invalid(Integer waybillId, LoginUser loginUser) throws BusinessException {
        Waybill waybill = waybillService.getWaybill(waybillId);
        if (null == waybill) {
            return;
        }
        waybill.setIsChangeDeliveryPoint(Waybill.ChangeDeliveryPoint.INVALID_UPLOAD.getCode());
        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybillId);
        waybillParam.setUpdateDeliveryPointSupplementTime(new Date());
        waybillParamService.update(waybillParam,loginUser);
        waybillCommonService.update(waybill, loginUser);

        // 推送通知
        msgWithBusinessService.pushDeliveryPointSupplementInvalidMsg(waybillId);

        // 修改转运方的运单地址信息
        this.modifyTransformBillInvalid(waybillId, loginUser);
    }

    // 修改转运方的运单地址信息
    private void modifyTransformBillInvalid(Integer transformBillLinkId, LoginUser loginUser) {
        if (null == transformBillLinkId) {
            return;
        }

        WaybillParam transformBillParam = waybillParamService.findByTransformBillLinkId(transformBillLinkId);
        if (null == transformBillParam) {
            return;
        }

        Waybill transformBill = waybillService.getWaybill(transformBillParam.getWaybillId());
        if (null == transformBill) {
            return;
        }

        transformBill.setIsChangeDeliveryPoint(Waybill.ChangeDeliveryPoint.INVALID_UPLOAD.getCode());
        transformBillParam.setUpdateDeliveryPointSupplementTime(new Date());
        waybillParamService.update(transformBillParam,loginUser);
        waybillCommonService.update(transformBill, loginUser);

        // 推送通知
        msgWithBusinessService.pushDeliveryPointSupplementInvalidMsg(transformBillParam.getWaybillId());
    }

    @Override
    public DeliveryPointSupplement get(int id) throws BusinessException {
        return deliveryPointSupplementDao.get(id);
    }

    @Override
    public List<DeliveryPointSupplement> getByWayBill(Integer id) throws BusinessException {
        if (null == id) {
            return new ArrayList<DeliveryPointSupplement>();
        }
        return deliveryPointSupplementDao.findByWayBill(id);
    }

    @Override
    public void updateWaybillReceiveAddress(List<WaybillReceiveAddress> addresses, LoginEmployee loginEmployee)
            throws BusinessException {
        if (CollectionUtils.isEmpty(addresses)) {
            throw new BusinessException("WaybillReceiveAddressNull", "errors.paramCanNotNull");
        }

        Integer waybillId = addresses.get(0).getWaybillId();

        if (waybillId == null) {
            throw new BusinessException("waybillIdNull", "errors.paramCanNotNull");
        }

        for (WaybillReceiveAddress address : addresses) {
            this.checkReceiveAddress(address);
        }

        // 获取原订单数据
        WaybillBo bo = new WaybillBo();
        bo.setWaybillId(waybillId);

        // 替换地址信息
        bo.setReceiveAddress(addresses);

        long start = System.currentTimeMillis();

        // 更新订单信息
        waybillService.updateWaybillByReceiveAddress(bo, loginEmployee);

        // 重新绑定电子围栏
        waybillAutoFenceServicve.removeAllFenceId(waybillId, loginEmployee);
        waybillAutoFenceServicve.bindWaybillIdAndFenceId(waybillId, WaybillBindFence.Sign.RECEIVE_ADDRESS,
                loginEmployee);

        // 推送消息
        msgWithBusinessService.pushDeliveryPointSupplementHasUpdateMsg(waybillId);

        long end = System.currentTimeMillis();

        if (log.isDebugEnabled()) {
            long rest = (end - start) / 1000;
            log.info("执行时间统计:" + rest);
        }

        // 修改转运方的运单地址信息
        this.modifyTransformBillReceiveAddress(waybillId, addresses, loginEmployee);
    }

    // 修改转运方的运单地址信息
    private void modifyTransformBillReceiveAddress(Integer transformBillLinkId, List<WaybillReceiveAddress> addresses,
            LoginUser loginUser) {
        if (null == transformBillLinkId) {
            return;
        }

        // 获取承运商的承运单
        Waybill vendorWaybill = waybillCommonService.getWaybillById(transformBillLinkId);
        if (null == vendorWaybill) {
            return;
        }

        // 获取转运方的运单扩展
        WaybillParam transformBillParam = waybillParamService.findByTransformBillLinkId(transformBillLinkId);
        if (null == transformBillParam) {
            return;
        }

        transformBillParam.setDistributionPointNo(addresses.size() - 1);
        waybillParamService.update(transformBillParam, loginUser);

        // 获取转运方的运单
        Waybill transformBill = waybillCommonService.getWaybillById(transformBillParam.getWaybillId());
        if (null == transformBill) {
            return;
        }

        transformBill.setEstimateDistance(vendorWaybill.getEstimateDistance());
        transformBill.setEstimateTimeConsumption(vendorWaybill.getEstimateTimeConsumption());
        transformBill.setIsChangeDeliveryPoint(vendorWaybill.getIsChangeDeliveryPoint());
        waybillCommonService.update(transformBill, loginUser);

        waybillReceiveAddressService.deleteByWaybillId(transformBill.getWaybillId());
        // 获取承运商的目的地信息，写入到转运方
        List<WaybillReceiveAddress> vendorReceiveAddress = waybillReceiveAddressService
                .findAllByWaybillId(transformBillLinkId);
        List<WaybillReceiveAddressResponse> listAddressResponse = new ArrayList<WaybillReceiveAddressResponse>();
        for (WaybillReceiveAddress address : vendorReceiveAddress) {
            address.setAddressId(null);
            listAddressResponse.add(new WaybillReceiveAddressResponse(address, null));
        }
        waybillReceiveAddressService.insert(listAddressResponse, transformBill.getWaybillId(), loginUser);
    }

    @Override
    public int countDeliveryPointSupplement(int waybillId) throws BusinessException {
        PageCondition pageCondition = new PageCondition();
        Map<String, Object> param = new HashMap<>();
        param.put("waybillId", waybillId);
        param.put("isDelete", Constants.flag_false);
        pageCondition.setFilters(param);

        return deliveryPointSupplementDao.searchCount(pageCondition);
    }

    private void checkReceiveAddress(WaybillReceiveAddress receiveAddress) {

        if (receiveAddress == null) {
            throw new BusinessException("ReceiveAddressNull", "errors.paramCanNotNull");
        }

        if (receiveAddress.getWaybillId() == null) {
            throw new BusinessException("ReceiveAddressNull", "errors.paramCanNotNull");
        }

        if (StringUtils.isBlank(receiveAddress.getAddressDetail())) {
            throw new BusinessException("AddressDetailNull", "errors.deliveryPointModify.addressNull");
        }

        if (StringUtils.isBlank(receiveAddress.getAddressName())) {
            throw new BusinessException("AddressNameNull", "errors.deliveryPointModify.addressNull");
        }

        if (StringUtils.isBlank(receiveAddress.getCoordinates())) {
            throw new BusinessException("CoordinatesNull", "errors.deliveryPointModify.addressNull");
        }

        if (StringUtils.isNotBlank(receiveAddress.getContactPhone())
                && !BaseUtil.checkMobilePhone(receiveAddress.getContactPhone())
                && !BaseUtil.checkTelephone(receiveAddress.getContactPhone())) {
            throw new BusinessException("contactPhoneFmtError", "errors.contactPhoneFmtError", "");
        }

    }

    @Override
    public Page<WaybillQueryVo> search(PageCondition pageCondition, LoginEmployee loginEmployee) throws BusinessException {
        List<WaybillQueryVo> result = new ArrayList<>();
        pageCondition.getFilters().put("needDeliveryPointNote", 1);
        pageCondition.getFilters().put("receiveWayNotEqual", Waybill.ReceiveWay.TRANSFORM_BILL.getCode());
        pageCondition.setOrderBy(" plan_delivery_time desc ");
        Page<Waybill> page = waybillService.search(loginEmployee, pageCondition);
        for (Waybill waybill : page.getResults()) {
            WaybillQueryVo vo = new WaybillQueryVo();
            List<DeliveryPointSupplement> row = getByWayBill(waybill.getWaybillId());
            List<String> imgList = new ArrayList<String>();
            for (DeliveryPointSupplement deliveryPointSupplement : row) {
                if (deliveryPointSupplement.getImg() != null) {
                    imgList.add(deliveryPointSupplement.getImg());
                }
            }
            vo.setImgUrlList(imgList);
            vo.setWaybillId(waybill.getWaybillId());
            vo.setWaybillNo(waybill.getWaybillNo());
            vo.setStatusViewText(waybill.getStatusView() == Waybill.StatusView.TEMP.getCode()
                    ? Waybill.StatusView.WATING_RECEIVE.getDescr() : waybill.getStatusViewText());
            vo.setPlanDeliveryTime(waybill.getPlanDeliveryTime());
            vo.setChangeDeliveryPoint(waybill.getIsChangeDeliveryPoint());
            vo.setChangeDeliveryPointText(Waybill.ChangeDeliveryPoint.getDesc(waybill.getIsChangeDeliveryPoint()));
            vo.setCustomerId(waybill.getCustomerId());
            vo.setCustomerName(waybill.getCustomerName());
            vo.setDriverId(waybill.getDriverId());
            vo.setDriverName(waybill.getDriverName());

            // 上传时间与修改时间
            WaybillParam waybillParam = waybillParamService.findByWaybillId(waybill.getWaybillId());
            if (null != waybillParam) {
                vo.setUploadDeliveryPointSupplementTime(waybillParam.getUploadDeliveryPointSupplementTime());
                vo.setUpdateDeliveryPointSupplementTime(waybillParam.getUpdateDeliveryPointSupplementTime());
            }
            result.add(vo);
        }
        return new Page<>(page.getPageNo(), page.getPageSize(), page.getTotal(), result);
    }
}
