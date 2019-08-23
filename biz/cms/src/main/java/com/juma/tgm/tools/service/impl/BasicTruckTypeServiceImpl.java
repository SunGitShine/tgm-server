package com.juma.tgm.tools.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.basicTruckType.service.BasicTruckTypeService;
import com.juma.tgm.truck.domain.AdditionalFunction;
import com.juma.tgm.truck.domain.AdditionalFunctionBo;
import com.juma.tgm.truck.service.AdditionalFunctionService;
import com.juma.tgm.truck.service.TruckTypeService;
import com.juma.tgm.waybill.domain.TruckRequireInfo;
import com.juma.tgm.waybill.service.TaxRateService;

@Service
public class BasicTruckTypeServiceImpl implements BasicTruckTypeService {

    @Resource
    private TruckTypeService truckTypeService;
    @Resource
    private TaxRateService taxRateService;
    @Resource
    private AdditionalFunctionService additionalFunctionService;

    @Override
    public TruckRequireInfo getBasicTruckRequireInfo(int tenantId, Integer userId) {
        TruckRequireInfo info = new TruckRequireInfo();
        // 车型
        info.setTruckTypeList(truckTypeService.listAllTruckTypeByOrderNoAsc(tenantId, false));
        // 税率
        info.setTaxRateList(taxRateService.loadByTenant(new LoginUser(tenantId, userId)));
        // 附加功能
        List<AdditionalFunctionBo> functionList = additionalFunctionService.getFunctionList();
        List<AdditionalFunctionBo> notNeedFunList = new ArrayList<AdditionalFunctionBo>();
        for (AdditionalFunctionBo f : functionList) {
            if (AdditionalFunction.FunctionKeys.DELIVERY_RECEIPT.toString().equals(f.getFunctionKey())
                    || AdditionalFunction.FunctionKeys.NEXT_DAY_DELIVERY.toString().equals(f.getFunctionKey())) {
                notNeedFunList.add(f);
            }
        }
        functionList.removeAll(notNeedFunList);
        info.setFunList(functionList);
        return info;
    }

}
