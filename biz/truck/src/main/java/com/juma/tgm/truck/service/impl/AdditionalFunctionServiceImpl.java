package com.juma.tgm.truck.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.juma.tgm.truck.dao.AdditionalFunctionDao;
import com.juma.tgm.truck.dao.AdditionalFunctionFreightDao;
import com.juma.tgm.truck.domain.AdditionalFunction;
import com.juma.tgm.truck.domain.AdditionalFunctionBo;
import com.juma.tgm.truck.domain.AdditionalFunctionFreight;
import com.juma.tgm.truck.service.AdditionalFunctionService;

/**
 * @author weilibin
 * @version V1.0
 * @Description: 附加功能service实现
 * @date 2016/05/13  13:56
 */
@Service
public class AdditionalFunctionServiceImpl implements AdditionalFunctionService {

    @Resource
    private AdditionalFunctionDao additionalFunctionDao;
    @Resource
    private AdditionalFunctionFreightDao additionalFunctionFreightDao;

    @Override
    public AdditionalFunction getAdditionFunction(Integer functionId) {
        if (null != functionId) {
            return additionalFunctionDao.get(functionId);
        }
        return null;
    }

    @Override
    public List<AdditionalFunction> getAdditionalFunctionByTruckTypeId(Integer freightId) {
        List<AdditionalFunction> results = new ArrayList<>();
        AdditionalFunctionFreight example = new AdditionalFunctionFreight();
        example.setFreightId(freightId);
        List<AdditionalFunctionFreight> freightLists = additionalFunctionFreightDao.findByExample(example);
        for (AdditionalFunctionFreight freightList : freightLists) {
            results.add(additionalFunctionDao.get(freightList.getAdditionalFunctionId()));
        }
        return results;
    }

    @Override
    public List<AdditionalFunction> getAllAdditionalFunction() {
        AdditionalFunction additionalFunction = new AdditionalFunction();
        additionalFunction.setIsDelete(false);
        additionalFunction.setParentFunctionId(0);
        return additionalFunctionDao.findByExample(additionalFunction);
    }

    @Override
    public List<AdditionalFunctionBo> getFunctionList() {
        List<AdditionalFunctionBo> funList = new ArrayList<AdditionalFunctionBo>();

        // 获取所有前台显示的的附件功能
        AdditionalFunction example = new AdditionalFunction();
        example.setIsDelete(false);
        example.setParentFunctionId(0);
        example.setAdditionalFunctionSign(0);
        List<AdditionalFunction> list = additionalFunctionDao.findByExample(example);

        //专车使用的用车要求
        String[] targetFuns = new String[]{AdditionalFunction.FunctionKeys.COLD_CHAIN.name(), AdditionalFunction.FunctionKeys.ENTRY_LICENSE.name(), AdditionalFunction.FunctionKeys.NEED_BACK_STORAGE.name(),
                AdditionalFunction.FunctionKeys.NEED_RECEIPT.name(), AdditionalFunction.FunctionKeys.COLLECTION_PAYMENT.name(),AdditionalFunction.FunctionKeys.CARRY.name(), AdditionalFunction.FunctionKeys.LABORER_CARRY.name(),
                AdditionalFunction.FunctionKeys.DRIVER_CARRY.name(),AdditionalFunction.FunctionKeys.DELIVERY_RECEIPT.name(),
                AdditionalFunction.FunctionKeys.CARRY_COOLIE.name(),AdditionalFunction.FunctionKeys.LOADING.name(),AdditionalFunction.FunctionKeys.UNLOAD.name(),
                AdditionalFunction.FunctionKeys.UPSTAIRS.name()
        };
        Arrays.sort(targetFuns);

        for (AdditionalFunction additionalFunction : list) {
            if(Arrays.binarySearch(targetFuns, additionalFunction.getFunctionKey()) < 0) continue;

            AdditionalFunctionBo additionalFunctionBo = new AdditionalFunctionBo();
            additionalFunctionBo.setAdditionalFunctionId(additionalFunction.getAdditionalFunctionId());
            String prefixName = additionalFunction.getPrefixName();
            String suffixName = additionalFunction.getSuffixName();
            String functionName = additionalFunction.getFunctionName();
            if (StringUtils.isNotBlank(prefixName)) {
                functionName = prefixName + functionName;
            }
            if (StringUtils.isNotBlank(suffixName)) {
                functionName = functionName + suffixName;
            }
            additionalFunctionBo.setFunctionName(functionName);
            additionalFunctionBo.setFunctionKey(additionalFunction.getFunctionKey());
            funList.add(additionalFunctionBo);
        }
        return funList;
    }

    @Override
    public AdditionalFunction findAdditionFunctionByKey(String functionKey) {
        if (StringUtils.isNotBlank(functionKey)) {
            AdditionalFunction function = new AdditionalFunction();
            function.setFunctionKey(functionKey);
            List<AdditionalFunction> list = additionalFunctionDao.findByExample(function);
            if (CollectionUtils.isNotEmpty(list)) {
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public List<AdditionalFunction> getAdditionFunctionByIds(String functionIds) {
        if (StringUtils.isNotBlank(functionIds)) {
            String[] strings = functionIds.split(",");
            List<Integer> list = new ArrayList<Integer>();
            for (String string : strings) {
                if (StringUtils.isNotBlank(string)) {
                    list.add(Integer.valueOf(string));
                }
            }
            if (CollectionUtils.isNotEmpty(list)) {
                return additionalFunctionDao.findByIds(list);
            }
        }
        return additionalFunctionDao.loadAll();
    }
}
