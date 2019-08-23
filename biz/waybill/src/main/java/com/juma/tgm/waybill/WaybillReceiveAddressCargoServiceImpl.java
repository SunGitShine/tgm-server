package com.juma.tgm.waybill;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.giants.common.exception.BusinessException;
import com.juma.tgm.waybill.dao.WaybillReceiveAddressCargoMapper;
import com.juma.tgm.waybill.domain.WaybillReceiveAddressCargo;
import com.juma.tgm.waybill.domain.WaybillReceiveAddressCargoExample;
import com.juma.tgm.waybill.service.WaybillReceiveAddressCargoService;

@Service
public class WaybillReceiveAddressCargoServiceImpl implements WaybillReceiveAddressCargoService {

    @Resource
    private WaybillReceiveAddressCargoMapper waybillReceiveAddressCargoMapper;

    @Override
    public WaybillReceiveAddressCargo getWaybillReceiveAddressCargo(Integer cargoId) {
        return waybillReceiveAddressCargoMapper.selectByPrimaryKey(cargoId);
    }

    @Override
    public List<WaybillReceiveAddressCargo> listByAddressId(Integer addressId) {
        if (null == addressId) {
            return new ArrayList<WaybillReceiveAddressCargo>();
        }

        WaybillReceiveAddressCargoExample example = new WaybillReceiveAddressCargoExample();
        example.createCriteria().andAddressIdEqualTo(addressId);
        return waybillReceiveAddressCargoMapper.selectByExample(example);
    }

    @Override
    public void insert(WaybillReceiveAddressCargo cargo) throws BusinessException {
        if (null == cargo.getAddressId()) {
            return;
        }

        waybillReceiveAddressCargoMapper.insert(cargo);
    }

    @Override
    public void delete(Integer cargoId) throws BusinessException {
        if (null == cargoId) {
            return;
        }

        waybillReceiveAddressCargoMapper.deleteByPrimaryKey(cargoId);
    }

    @Override
    public void batchInsert(List<WaybillReceiveAddressCargo> listCargo) throws BusinessException {
        for (WaybillReceiveAddressCargo cargo : listCargo) {
            this.insert(cargo);
        }
    }

    @Override
    public void deleteByAddressId(Integer addressId) throws BusinessException {
        WaybillReceiveAddressCargoExample example = new WaybillReceiveAddressCargoExample();
        example.createCriteria().andAddressIdEqualTo(addressId);

        waybillReceiveAddressCargoMapper.deleteByExample(example);
    }

}
