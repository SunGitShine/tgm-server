package com.juma.tgm.waybill.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.giants.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juma.auth.user.domain.LoginUser;
import com.juma.conf.domain.Region;
import com.juma.conf.service.RegionService;
import com.juma.tgm.base.domain.RegionBo;
import com.juma.tgm.region.service.RegionTgmService;
import com.juma.tgm.waybill.dao.WaybillReceiveAddressDao;
import com.juma.tgm.waybill.domain.WaybillReceiveAddress;
import com.juma.tgm.waybill.domain.WaybillReceiveAddressBo;
import com.juma.tgm.waybill.domain.WaybillReceiveAddressCargo;
import com.juma.tgm.waybill.domain.WaybillReceiveAddressResponse;
import com.juma.tgm.waybill.service.WaybillReceiveAddressCargoService;
import com.juma.tgm.waybill.service.WaybillReceiveAddressService;

/*
 *
 * @author  2016-05-09
 * @version 1.0
 */

@Service
public class WaybillReceiveAddressServiceImpl implements WaybillReceiveAddressService {

    /**
     * 地址最大长度
     */
    private static final int ADDRESS_MAX_LENGTH = 255;

    @Autowired
    private WaybillReceiveAddressDao waybillReceiveAddressDao;
    @Autowired
    private RegionService regionService;
    @Autowired
    private RegionTgmService regionTgmService;
    @Autowired
    private WaybillReceiveAddressCargoService waybillReceiveAddressCargoService;

    @Override
    public void batchInsert(Integer waybillId, List<WaybillReceiveAddress> waybillReceiveAddressList, LoginUser loginUser) throws BusinessException {
        if (waybillId == null || waybillReceiveAddressList == null || waybillReceiveAddressList.isEmpty()) return;
        for ( WaybillReceiveAddress waybillReceiveAddress : waybillReceiveAddressList ) {
            String regionCode = waybillReceiveAddress.getRegionCode();
            if (StringUtils.isBlank(regionCode)) {
                regionCode = regionTgmService.findRegionCodeByCoordinate(waybillReceiveAddress.getCoordinates());
                if (regionCode.length() > 5) {
                    regionCode = regionCode.substring(0, 5);
                }
            }
            waybillReceiveAddress.setRegionCode(regionCode);
            waybillReceiveAddress.setAddressDetail(StringUtils.substring(waybillReceiveAddress.getAddressDetail(), 0, ADDRESS_MAX_LENGTH));

            waybillReceiveAddress.setWaybillId(waybillId);
            waybillReceiveAddress.setSequence(0);
            waybillReceiveAddress.setIsArrived(0);
            waybillReceiveAddress.setIsDelete(false);
            waybillReceiveAddress.setCreateTime(new Date());
            waybillReceiveAddress.setCreateUserId(loginUser.getUserId());
            waybillReceiveAddress.setLastUpdateUserId(loginUser.getUserId());
            waybillReceiveAddress.setLastUpdateTime(new Date());
        }
        waybillReceiveAddressDao.batchInsert(waybillReceiveAddressList);
    }

    @Override
    public void batchInsert(List<WaybillReceiveAddressResponse> listResponse) {
        List<WaybillReceiveAddressCargo> listCargoResponse = new ArrayList<WaybillReceiveAddressCargo>();
        for (WaybillReceiveAddressResponse response : listResponse) {
            int addressId = this.insert(response.getWaybillReceiveAddress(), null);
            List<WaybillReceiveAddressCargo> listCargo = response.getListWaybillReceiveAddressCargo();
            for (WaybillReceiveAddressCargo cargo : listCargo) {
                cargo.setAddressId(addressId);
                listCargoResponse.add(cargo);
            }
        }

        waybillReceiveAddressCargoService.batchInsert(listCargoResponse);
    }

    @Override
    public List<WaybillReceiveAddress> findAllByWaybillId(Integer waybillId) {
        if (null != waybillId) {
            WaybillReceiveAddress waybillReceiveAddress = new WaybillReceiveAddress();
            waybillReceiveAddress.setWaybillId(waybillId);
            List<WaybillReceiveAddress> list = waybillReceiveAddressDao.findByExample(waybillReceiveAddress);
            if (CollectionUtils.isNotEmpty(list)) {
                for (WaybillReceiveAddress address : list) {
                    String simpleAddress = regionTgmService.buildDetailAddress(address.getRegionCode(),
                            address.getAddressDetail());
                    address.setSimpleAddress(simpleAddress);
                    address.setCityname(regionTgmService.findSimpleRegionName(address.getRegionCode(),
                            RegionBo.SimpleRegionKey.CITY));
                }
            }
            return list;
        }
        return new ArrayList<WaybillReceiveAddress>();
    }

    @Override
    public List<WaybillReceiveAddress> findAllByUserId(Integer userId) {
        if (null != userId) {
            return waybillReceiveAddressDao.getWaybillReceiveLastAddress(userId);
        }
        return null;
    }

    @Override
    public String getReceiveAddressStr(Integer waybillId) {
        List<WaybillReceiveAddress> list = findAllByWaybillId(waybillId);
        if (CollectionUtils.isNotEmpty(list)) {
            StringBuffer sb = new StringBuffer();
            for (WaybillReceiveAddress waybillReceiveAddress : list) {
                String addressDetail = waybillReceiveAddress.getAddressDetail();
                String regionCode = waybillReceiveAddress.getRegionCode();
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
                        if (StringUtils.isNotBlank(regionName)) {
                            addressDetail = " ( " + addressDetail.substring(regionName.length()) + " )";
                        }
                    }
                }
                String addressName = waybillReceiveAddress.getAddressName();
                if (StringUtils.isNotBlank(addressName)) {
                    sb.append(addressName).append(StringUtils.isNotBlank(addressDetail) ? addressDetail : "")
                            .append(" - ");
                }
            }
            String receiveAddress = sb.toString();
            if (StringUtils.isNotBlank(receiveAddress)) {
                receiveAddress = receiveAddress.trim();
                return receiveAddress.substring(0, receiveAddress.length() - 1).trim();
            }
        }
        return null;
    }

    @Override
    public List<WaybillReceiveAddressBo> findAllBoByWaybillId(Integer waybillId) {
        List<WaybillReceiveAddressBo> result = new ArrayList<WaybillReceiveAddressBo>();
        List<WaybillReceiveAddress> list = findAllByWaybillId(waybillId);
        if (CollectionUtils.isNotEmpty(list)) {
            for (WaybillReceiveAddress address : list) {
                WaybillReceiveAddressBo bo = new WaybillReceiveAddressBo();
                bo.setReceiveAddress(address);
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
    public int findNumByWaybillId(Integer waybillId) {
        if (null != waybillId) {
            return waybillReceiveAddressDao.findNumByWaybillId(waybillId);
        }
        return 0;
    }

    @Override
    public void insert(List<WaybillReceiveAddressResponse> listAddressResponse, Integer waybillId, LoginUser loginUser) {
        for (WaybillReceiveAddressResponse response : listAddressResponse) {
            WaybillReceiveAddress wbra = response.getWaybillReceiveAddress();
            String regionCode = wbra.getRegionCode();
            if (StringUtils.isBlank(regionCode)) {
                regionCode = regionTgmService.findRegionCodeByCoordinate(wbra.getCoordinates());
                if (regionCode.length() > 5) {
                    regionCode = regionCode.substring(0, 5);
                }
                wbra.setRegionCode(regionCode);
            }
            wbra.setCreateUserId(loginUser.getUserId());
            wbra.setWaybillId(waybillId);
            // 详细地址超长直接截取到数据库长度
            if (StringUtils.length(wbra.getAddressDetail()) > 255) {
                wbra.setAddressDetail(StringUtils.substring(wbra.getAddressDetail(), 0, ADDRESS_MAX_LENGTH));
            }
        }
        batchInsert(listAddressResponse);
    }

    @Override
    public void delete(Integer addressId) {
        WaybillReceiveAddress receiveAddress = getWaybillReceiveAddress(addressId);
        waybillReceiveAddressDao.delete(receiveAddress);
    }

    @Override
    public void deleteByWaybillId(Integer waybillId) {
        waybillReceiveAddressDao.deleteByWaybillId(waybillId);
    }

    @Override
    public List<WaybillReceiveAddress> findNumListByWaybillId(List<Integer> waybillIdList) {
        if (CollectionUtils.isNotEmpty(waybillIdList)) {
            return waybillReceiveAddressDao.findNumListByWaybillId(waybillIdList);
        }
        return null;
    }

    @Override
    public int insert(WaybillReceiveAddress waybillReceiveAddress, LoginUser loginUser) {
        if (null != loginUser) {
            waybillReceiveAddress.setCreateUserId(loginUser.getUserId());
        }
        waybillReceiveAddress.setCreateTime(new Date());
        waybillReceiveAddress.setIsDelete(false);
        waybillReceiveAddressDao.insert(waybillReceiveAddress);
        return waybillReceiveAddress.getAddressId();
    }

    @Override
    public void update(WaybillReceiveAddress waybillReceiveAddress, LoginUser loginUser) {
        if (null != loginUser) {
            waybillReceiveAddress.setLastUpdateUserId(loginUser.getUserId());
        }
        waybillReceiveAddress.setLastUpdateTime(new Date());
        waybillReceiveAddressDao.update(waybillReceiveAddress);
    }

    @Override
    public WaybillReceiveAddress getWaybillReceiveAddress(Integer addressId) {
        if (null == addressId) {
            return null;
        }
        return waybillReceiveAddressDao.get(addressId);
    }

    @Override
    public void batchUpdate(List<WaybillReceiveAddressResponse> listResponse) {
        for (WaybillReceiveAddressResponse response : listResponse) {
            this.update(response.getWaybillReceiveAddress(), null);

            if (!response.getListWaybillReceiveAddressCargo().isEmpty()) {
                waybillReceiveAddressCargoService.deleteByAddressId(response.getWaybillReceiveAddress().getAddressId());
                waybillReceiveAddressCargoService.batchInsert(response.getListWaybillReceiveAddressCargo());
            }
        }
    }

    @Override
    public int updateBatchByPrimaryKeySelective(List<WaybillReceiveAddress> list) {
        return waybillReceiveAddressDao.updateBatchByPrimaryKeySelective(list);
    }

    @Override
    public List<WaybillReceiveAddress> listSimpleReceiveAddressByWaybillId(Integer waybillId) {
        if (null == waybillId) {
            return null;
        }

        WaybillReceiveAddress waybillReceiveAddress = new WaybillReceiveAddress();
        waybillReceiveAddress.setWaybillId(waybillId);
        return waybillReceiveAddressDao.findByExample(waybillReceiveAddress);
    }
}
