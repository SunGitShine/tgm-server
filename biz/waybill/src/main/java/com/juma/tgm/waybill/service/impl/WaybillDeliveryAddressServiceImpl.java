package com.juma.tgm.waybill.service.impl;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.conf.domain.Region;
import com.juma.conf.service.RegionService;
import com.juma.tgm.base.domain.RegionBo;
import com.juma.tgm.region.service.RegionTgmService;
import com.juma.tgm.waybill.dao.WaybillDeliveryAddressDao;
import com.juma.tgm.waybill.domain.*;
import com.juma.tgm.waybill.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

/*
 *
 * @author  2016-05-09
 * @version 1.0
 */
@Service
public class WaybillDeliveryAddressServiceImpl implements WaybillDeliveryAddressService {

    /**
     * 地址最大长度
     */
    private static final int ADDRESS_MAX_LENGTH = 255;

    @Autowired
    private WaybillDeliveryAddressDao waybillDeliveryAddressDao;
    @Autowired
    private RegionService regionService;
    @Autowired
    private RegionTgmService regionTgmService;
    @Autowired
    private WaybillAutoFenceServicve waybillAutoFenceServicve;
    @Autowired
    private TruckRequireService truckRequireService;
    @Autowired
    private WaybillService waybillService;
    @Autowired
    private WaybillReceiveAddressService waybillReceiveAddressService;
    @Resource
    private WaybillCommonService waybillCommonService;

    @Override
    public void batchInsert(List<WaybillDeliveryAddress> waybillDeliveryAddresses) {
        waybillDeliveryAddressDao.batchInsert(waybillDeliveryAddresses);
    }

    @Override
    public List<WaybillDeliveryAddress> selectEntryListByCondition(WaybillDeliveryAddress waybillDeliveryAddress) {
        return waybillDeliveryAddressDao.selectEntryListByCondition(waybillDeliveryAddress);
    }

    @Override
    public List<WaybillDeliveryAddress> findAllByWaybillId(Integer waybillId) {
        if (null != waybillId) {
            WaybillDeliveryAddress waybillDeliveryAddress = new WaybillDeliveryAddress();
            waybillDeliveryAddress.setWaybillId(waybillId);
            List<WaybillDeliveryAddress> list = selectEntryListByCondition(waybillDeliveryAddress);
            if (CollectionUtils.isNotEmpty(list)) {
                for (WaybillDeliveryAddress address : list) {
                    String simpleAddress = regionTgmService.buildDetailAddress(address.getRegionCode(),
                            address.getAddressDetail());
                    address.setSimpleAddress(simpleAddress);
                    address.setCityname(regionTgmService.findSimpleRegionName(address.getRegionCode(),
                            RegionBo.SimpleRegionKey.CITY));
                }
            }

            return list;
        }
        return new ArrayList<WaybillDeliveryAddress>();
    }

    @Override
    public List<WaybillDeliveryAddress> findAllByUserId(Integer userId) {
        if (null != userId) {
            return waybillDeliveryAddressDao.getWaybillDeliveryLastAddress(userId);
        }
        return null;
    }

    @Override
    public String getDeliveryAddressStr(Integer waybillId) {
        List<WaybillDeliveryAddress> list = findAllByWaybillId(waybillId);
        if (CollectionUtils.isNotEmpty(list)) {
            StringBuffer sb = new StringBuffer();
            for (WaybillDeliveryAddress waybillDeliveryAddress : list) {
                String addressDetail = waybillDeliveryAddress.getAddressDetail();
                String regionCode = waybillDeliveryAddress.getRegionCode();
                if (null != regionCode && null != addressDetail) {
                    addressDetail = addressDetail.trim();
                    regionCode = regionCode.substring(0, 5);
                    if (null != regionCode && regionCode.length() == 5) {
                        String regionName = regionTgmService.findRegionNameByCode(regionCode);
                        // 四个直辖市区别对待
                        if (regionCode.equals("00000") || regionCode.equals("01001") || regionCode.equals("08000")
                                || regionCode.equals("21000")) {
                            regionName = regionTgmService.findRegionNameByCode(regionCode);
                            regionName = regionName.substring(0, 3);
                        }
                        if (null != regionName) {
                            addressDetail = " ( " + addressDetail.substring(regionName.length()) + " )";
                        }
                    }
                }
                sb.append(waybillDeliveryAddress.getAddressName() + addressDetail);
                String contactName = waybillDeliveryAddress.getContactName();
                if (StringUtils.isNotBlank(contactName)) {
                    sb.append(" / ").append(contactName);
                }
                String contactPhone = waybillDeliveryAddress.getContactPhone();
                if (StringUtils.isNotBlank(contactPhone)) {
                    sb.append(" / ").append(contactPhone);
                }
                sb.append(" - ");
            }
            String deliveryAddress = sb.toString();
            if (StringUtils.isNotBlank(deliveryAddress)) {
                deliveryAddress = deliveryAddress.trim();
                return deliveryAddress.substring(0, deliveryAddress.length() - 1).trim();
            }
        }
        return null;
    }

    @Override
    public WaybillDeliveryAddress findByWaybillId(Integer waybillId) {
        WaybillDeliveryAddress deliveryAddress = waybillDeliveryAddressDao.findByWaybillId(waybillId);
        if (null != deliveryAddress) {
            String simpleAddress = regionTgmService.buildDetailAddress(deliveryAddress.getRegionCode(),
                    deliveryAddress.getAddressDetail());
            deliveryAddress.setSimpleAddress(simpleAddress);
        }
        return deliveryAddress;
    }

    @Override
    public WaybillDeliveryAddress findSimpleByWaybillId(Integer waybillId) {
        return waybillDeliveryAddressDao.findByWaybillId(waybillId);
    }

    @Override
    public List<WaybillDeliveryAddressBo> findAllBoByWaybillId(Integer waybillId) {
        List<WaybillDeliveryAddressBo> result = new ArrayList<WaybillDeliveryAddressBo>();
        List<WaybillDeliveryAddress> list = findAllByWaybillId(waybillId);
        if (CollectionUtils.isNotEmpty(list)) {
            for (WaybillDeliveryAddress address : list) {
                WaybillDeliveryAddressBo bo = new WaybillDeliveryAddressBo();
                bo.setDeliveryAddress(address);
                String regionCode = address.getRegionCode();
                if (StringUtils.isNotBlank(regionCode)) {
                    Region region = regionService.findByRegionCode(regionCode);
                    if (null != region) {
                        bo.setCity(region.getRegionName());
                    }
                }
                result.add(bo);
            }
        }
        return result;
    }

    @Override
    public void insert(List<WaybillDeliveryAddress> deliveryAddressList, Integer waybillId, LoginUser loginUser) {
        for (WaybillDeliveryAddress wbda : deliveryAddressList) {
            String regionCode = wbda.getRegionCode();
            if (StringUtils.isBlank(regionCode)) {
                regionCode = regionTgmService.findRegionCodeByCoordinate(wbda.getCoordinates());
                if (regionCode.length() > 5) {
                    regionCode = regionCode.substring(0, 5);
                }
                wbda.setRegionCode(regionCode);
            }
            wbda.setCreateUserId(loginUser.getUserId());
            wbda.setWaybillId(waybillId);
            //详细地址超长直接截取到数据库长度
            if(StringUtils.length(wbda.getAddressDetail()) > 255){
                wbda.setAddressDetail(StringUtils.substring(wbda.getAddressDetail(), 0, ADDRESS_MAX_LENGTH));
            }
            wbda.setIsDelete(false);
        }
        batchInsert(deliveryAddressList);
    }

    @Override
    public String getPhone(Integer waybillId) {
        List<WaybillDeliveryAddress> list = findAllByWaybillId(waybillId);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0).getContactPhone();
        }
        return null;
    }

    @Override
    public void delete(Integer waybillId) {
        WaybillDeliveryAddress deliveryAddress = new WaybillDeliveryAddress();
        deliveryAddress.setWaybillId(waybillId);
        waybillDeliveryAddressDao.delete(deliveryAddress);
    }

    @Override
    public void insert(WaybillDeliveryAddress waybillDeliveryAddress, LoginUser loginUser) {
        waybillDeliveryAddress.setCreateTime(new Date());
        waybillDeliveryAddress.setCreateUserId(loginUser.getUserId());
        waybillDeliveryAddressDao.insert(waybillDeliveryAddress);
    }

    @Override
    public void update(WaybillDeliveryAddress waybillDeliveryAddress, LoginUser loginUser) {
        if (null != loginUser) {
            waybillDeliveryAddress.setLastUpdateUserId(loginUser.getUserId());
        }
        waybillDeliveryAddressDao.update(waybillDeliveryAddress);
    }

    @Override
    public WaybillDeliveryAddress findByAddressId(Integer addressId) {
        if (null == addressId) {
            return null;
        }
        return waybillDeliveryAddressDao.get(addressId);
    }

    @Override
    public void updateDeliveryAddress(WaybillDeliveryAddress waybillDeliveryAddress, LoginUser loginUser) {
        if (null == waybillDeliveryAddress.getAddressId()) {
            throw new BusinessException("addressIdRequire", "waybillAddress.error.addressIdRequire");
        }

        if (null == waybillDeliveryAddress.getWaybillId()) {
            throw new BusinessException("waybillIdRequire", "waybillAddress.error.waybillIdRequire");
        }

        if (StringUtils.isBlank(waybillDeliveryAddress.getAddressName())) {
            throw new BusinessException("addressNameRequire", "waybillAddress.error.addressNameRequire");
        }

        if (StringUtils.isBlank(waybillDeliveryAddress.getCoordinates())) {
            throw new BusinessException("coordinatesRequire", "waybillAddress.error.coordinatesRequire");
        }

        if (StringUtils.isBlank(waybillDeliveryAddress.getAddressDetail())) {
            throw new BusinessException("addressDetailRequire", "waybillAddress.error.addressDetailRequire");
        }

        if (StringUtils.isBlank(waybillDeliveryAddress.getContactName())) {
            throw new BusinessException("contactNameRequire", "waybillAddress.error.contactNameRequire");
        }

        if (StringUtils.isBlank(waybillDeliveryAddress.getContactPhone())) {
            throw new BusinessException("contactPhoneRequire", "waybillAddress.error.contactPhoneRequire");
        }

        if (StringUtils.isBlank(waybillDeliveryAddress.getRegionCode())) {
            String regionCode = regionTgmService.findRegionCodeByCoordinate(waybillDeliveryAddress.getCoordinates());
            if (regionCode.length() > 5) {
                regionCode = regionCode.substring(0, 5);
            }
            waybillDeliveryAddress.setRegionCode(regionCode);
        }
        update(waybillDeliveryAddress, loginUser);

        Waybill waybill = waybillCommonService.getWaybillById(waybillDeliveryAddress.getWaybillId());
        if (null == waybill) {
            return;
        }

        // 重新计算价格,承运商的运单不重新算价
        if (NumberUtils.compare(waybill.getWaybillSource(), Waybill.WaybillSource.TRANSFORM_BILL.getCode()) != 0) {
            calWaybillPrice(waybillDeliveryAddress, loginUser);
        }

        // 解绑电子围栏
        waybillAutoFenceServicve.removeAllFenceId(waybillDeliveryAddress.getWaybillId(), loginUser);
        // 绑定电子围栏
        waybillAutoFenceServicve.bindWaybillIdAndFenceId(waybillDeliveryAddress.getWaybillId(),
                WaybillBindFence.Sign.DELIVERY_ADDRESS, loginUser);
        // 修改甲方取货地信息
        this.modifyTransformBillDeliveryAddress(waybillDeliveryAddress, loginUser);
    }

    // 修改甲方取货地信息
    private void modifyTransformBillDeliveryAddress(WaybillDeliveryAddress vendorDeliveryAddress, LoginUser loginUser) {
        Waybill transformBill = waybillCommonService.findWaybillByTransformBillId(vendorDeliveryAddress.getWaybillId());
        if (null == transformBill) {
            return;
        }

        // 获取甲方的取货地信息
        WaybillDeliveryAddress deliveryAddress = this.findByWaybillId(transformBill.getWaybillId());
        if (null == deliveryAddress) {
            return;
        }

        vendorDeliveryAddress.setAddressId(deliveryAddress.getAddressId());
        vendorDeliveryAddress.setWaybillId(deliveryAddress.getWaybillId());
        this.update(vendorDeliveryAddress, loginUser);
    }

    // 重新计算价格
    private void calWaybillPrice(WaybillDeliveryAddress waybillDeliveryAddress, LoginUser loginUser) {
        CityAdressData formAddress = new CityAdressData();
        formAddress.setRegionCode(waybillDeliveryAddress.getRegionCode());
        formAddress.setCoordinate(waybillDeliveryAddress.getCoordinates());
        formAddress.setAddress(waybillDeliveryAddress.getAddressDetail());

        List<WaybillReceiveAddress> receiveAddressList = waybillReceiveAddressService
                .findAllByWaybillId(waybillDeliveryAddress.getWaybillId());
        List<CityAdressData> toAddress = new ArrayList<CityAdressData>();
        for (WaybillReceiveAddress receiveAddress : receiveAddressList) {
            CityAdressData address = new CityAdressData();
            address.setRegionCode(receiveAddress.getRegionCode());
            address.setCoordinate(receiveAddress.getCoordinates());
            toAddress.add(address);
        }

        WaybillBo bo = new WaybillBo();
        bo.setWaybill(waybillService.getWaybill(waybillDeliveryAddress.getWaybillId()));
        bo.setTruckRequire(truckRequireService.findTruckRequireByWaybillId(waybillDeliveryAddress.getWaybillId(), loginUser));
        DistanceAndPriceData waybillPrice = waybillService.calWaybillPrice(formAddress, toAddress, bo, loginUser);
        if (null != waybillPrice) {
            Waybill waybill = new Waybill();
            waybill.setWaybillId(waybillDeliveryAddress.getWaybillId());
            waybill.setEstimateDistance(waybillPrice.getDistance());
            waybill.setEstimateTimeConsumption(Integer.valueOf(waybillPrice.getDuration()));
            waybill.setCalculatedFreight(waybillPrice.getPrice());
            waybillCommonService.update(waybill, loginUser);
        }
    }

    @Override
    public int findCountByWaybillId(int waybillId) {
        return waybillDeliveryAddressDao.findCountByWaybillId(waybillId);
    }

    @Override
    public void batchUpdate(List<WaybillDeliveryAddress> waybillDeliveryAddresses) {
        waybillDeliveryAddressDao.batchUpdate(waybillDeliveryAddresses);
        
    }
}
