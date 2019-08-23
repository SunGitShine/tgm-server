package com.juma.tgm.waybill.service.impl;

import com.giants.common.collections.CollectionUtils;
import com.giants.common.exception.BusinessException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.juma.auth.user.domain.LoginUser;
import com.juma.conf.domain.ConfParamOption;
import com.juma.tgm.basicTruckType.service.ConfParamInfoService;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.common.Constants;
import com.juma.tgm.truck.domain.AdditionalFunction;
import com.juma.tgm.truck.service.AdditionalFunctionService;
import com.juma.tgm.truck.service.TruckTypeService;
import com.juma.tgm.waybill.dao.TruckRequireDao;
import com.juma.tgm.waybill.domain.*;
import com.juma.tgm.waybill.domain.vo.TruckRequireFilter;
import com.juma.tgm.waybill.service.TaxRateService;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillParamService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class TruckRequireServiceImpl implements TruckRequireService {

    @Resource
    private TruckRequireDao truckRequireDao;
    @Resource
    private TaxRateService taxRateService;
    @Resource
    private TruckTypeService truckTypeService;
    @Resource
    private AdditionalFunctionService additionalFunctionService;
    @Resource
    private ConfParamInfoService confParamInfoService;
    @Resource
    private WaybillParamService waybillParamService;
    @Resource
    private WaybillCommonService waybillCommonService;

    @Override
    public TruckRequire findTruckRequireByWaybillId(Integer waybillId, LoginUser loginUser) {
        if (null == waybillId) {
            return null;
        }

        TruckRequire truckRequire = truckRequireDao.getTruckRequireByWaybillId(waybillId);
        if (null == truckRequire) {
            return null;
        }
        TaxRate taxRate = taxRateService.findTaxRateBy(truckRequire.getTaxRateValue(), loginUser);
        truckRequire.setTaxRateId(taxRate ==null ? null : taxRate.getTaxRateId());
        return truckRequire;
//        return truckRequireDao.getTruckRequireByWaybillId(waybillId);
    }

    @Override
    public TruckRequire loadReuckRequireByWaybillId(Integer waybillId) {
        if (null == waybillId) {
            return null;
        }

        return truckRequireDao.getTruckRequireByWaybillId(waybillId);
    }

    @Override
    public void insert(TruckRequire truckRequire) {
        TaxRate taxRate = taxRateService.getTaxRate(truckRequire.getTaxRateId());
        if (null != taxRate) {
            truckRequire.setTaxRateValue(taxRate.getTaxRateValue());
        }
        truckRequireDao.insert(truckRequire);
    }

    @Override
    public String getTruckRequire(TruckRequire truckRequire, Integer waybillId) {
        StringBuffer sf = new StringBuffer("");
        if (null == truckRequire && null != waybillId) {
            truckRequire = truckRequireDao.getTruckRequireByWaybillId(waybillId);
        }
        String truckRequireStr = getTruckRequireString(truckRequire, sf);
        return truckRequireStr;
    }

    public String getTruckRequireString(TruckRequire truckRequire, StringBuffer sf) {
        if (truckRequire == null || sf == null) return "";
        // 车型
        String truckTypeName = truckTypeService.findTruckTypeNameByTypeId(truckRequire.getTruckTypeId());
        if (StringUtils.isNotBlank(truckTypeName)) {
            sf.append(truckTypeName.trim());
        }

        String functionIds = truckRequire.getAdditionalFunctionIds();
        List<AdditionalFunction> functions = null;
        if (StringUtils.isNotBlank(functionIds)) {
            // 获取所有的附加项目,优化查询速度
            functions = additionalFunctionService.getAdditionFunctionByIds(functionIds);
        }

        // 入城证
        appendFunction(sf, functions, "ENTRY_LICENSE", truckRequire.getWaybillId());
        // 冷链
        appendFunction(sf, functions, "COLD_CHAIN", truckRequire.getWaybillId());
        // 尾板
        Integer isTailBoard = truckRequire.getIsTailBoard();
        if (null != isTailBoard) {
            if (1 == isTailBoard) {
                sf.append(" | 尾板");
            }
        }
        // 是否搬运
        appendFunction(sf, functions, "NEED_CARRY", truckRequire.getWaybillId());
        // 是否返仓
        appendFunction(sf, functions, "NEED_BACK_STORAGE", truckRequire.getWaybillId());
        // 是否回单
        appendFunction(sf, functions, "NEED_RECEIPT", truckRequire.getWaybillId());
        // 是否代收货款
        appendFunction(sf, functions, "COLLECTION_PAYMENT", truckRequire.getWaybillId());
        // 司机搬运
        appendFunction(sf, functions, "DRIVER_CARRY", truckRequire.getWaybillId());
        // 小工搬运
        appendFunction(sf, functions, "LABORER_CARRY", truckRequire.getWaybillId());

        //温度
        WaybillParam waybillParam = waybillParamService.findByWaybillId(truckRequire.getWaybillId());
        if(waybillParam != null && waybillParam.getRequiredMinTemperature() != null && waybillParam.getRequiredMaxTemperature() != null) {
            sf.append(" | ");
            sf.append(waybillParam.getRequiredMinTemperature()+"℃/");
            sf.append(waybillParam.getRequiredMaxTemperature()+"℃");
        }

        // 配送单、隔天配送
        Waybill waybill = waybillCommonService.getWaybillById(truckRequire.getWaybillId());
        if (null != waybill) {
            if (null != waybill.getNeedDeliveryPointNote()
                    && NumberUtils.compare(waybill.getNeedDeliveryPointNote(), 1) == 0) {
                sf.append(" | ").append("配送单");
            }

            if (null != waybill.getOnlyLoadCargo() && NumberUtils.compare(waybill.getOnlyLoadCargo(), 1) == 0) {
                sf.append(" | ").append("隔天配送");
            }
        }

        // 整理，若开头有“|”，则去掉
        String truckRequireStr = sf.toString();
        if (StringUtils.isNotBlank(truckRequireStr)) {
            if (truckRequireStr.startsWith(" | ")) {
                truckRequireStr = truckRequireStr.substring(3, truckRequireStr.length()).trim();
            }
        }
        return truckRequireStr;
    }

    @Override
    public List<TruckRequire> findByFilter(TruckRequireFilter filter, LoginUser loginUser) throws BusinessException {
        if( CollectionUtils.isEmpty(filter.getWaybillIds()) ){
            return Lists.newArrayList();
        }
        return truckRequireDao.selectByExample(filter);
    }

    @Override
    public List<TruckRequire> findByWaybillIds(List<Integer> waybillIds) throws BusinessException {
        TruckRequireFilter filter = new TruckRequireFilter();
        filter.setWaybillIds(waybillIds);
        return this.findByFilter(filter,null);
    }

    @Override
    public Map<Integer, TruckRequire> findDictionaryByWaybillIds(List<Integer> waybillIds) throws BusinessException {
        List<TruckRequire> truckRequires = findByWaybillIds(waybillIds);
        Map<Integer, TruckRequire> truckRequireMap = Maps.newConcurrentMap();
        for (TruckRequire truckRequire : truckRequires){
            truckRequireMap.put(truckRequire.getWaybillId(),truckRequire);
        }
        return truckRequireMap;
    }

    @Override
    public boolean isNeedInvoice(Integer waybillId) {
        TruckRequire truckRequire = findTruckRequireByWaybillId(waybillId, null);
        if (null == truckRequire) {
            return false;
        }

        if (null != truckRequire.getTaxRateValue()) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isColdChain(TruckRequire truckRequire, Integer waybillId) {
        if (null == truckRequire && null != waybillId) {
            truckRequire = findTruckRequireByWaybillId(waybillId, null);
        }

        if (null == truckRequire) {
            return false;
        }

        String functionIds = truckRequire.getAdditionalFunctionIds();
        AdditionalFunction function = additionalFunctionService.findAdditionFunctionByKey(Constants.COLD_CHAIN);
        if (null == function) {
            return false;
        }

        Integer functionId = function.getAdditionalFunctionId();
        functionIds = "," + functionIds + ",";
        if (functionIds.contains("," + functionId + ",")) {
            return true;
        }
        return false;
    }

    @Override
    public String getImportantInfo(Integer waybillId) {
        if (null == waybillId) {
            return null;
        }

        TruckRequire truckRequire = truckRequireDao.getTruckRequireByWaybillId(waybillId);
        if (null == truckRequire) {
            return null;
        }

        StringBuffer buffer = new StringBuffer();
        // 车型
        String truckTypeName = truckTypeService.findTruckTypeNameByTypeId(truckRequire.getTruckTypeId());

        if (StringUtils.isNotBlank(truckTypeName)) {
            buffer.append(" | ").append(truckTypeName);
        }

        boolean hasEntryLicense = false;
        // 附加功能
        String functionIds = truckRequire.getAdditionalFunctionIds();
        if (StringUtils.isNotBlank(functionIds)) {
            List<AdditionalFunction> list = additionalFunctionService.getAdditionFunctionByIds(functionIds);
            for (AdditionalFunction function : list) {

                if (function.getParentFunctionId().equals(0)) continue;

                if (AdditionalFunction.FunctionKeys.ENTRY_LICENSE.toString().equals(function.getFunctionKey())) {
                    hasEntryLicense = true;
                    // 入城证
                    buffer.append(" | ").append(function.getFunctionName().trim());
                    // 重量 eg: | X吨
                    String goodsWeight = goodsWeight(truckRequire);
                    if (StringUtils.isNotBlank(goodsWeight)) {
                        buffer.append(" | ").append(goodsWeight);
                    }
                    // 体积 eg: | X方
                    String goodsVolume = goodsVolume(truckRequire);
                    if (StringUtils.isNotBlank(goodsVolume)) {
                        buffer.append(" | ").append(goodsVolume);
                    }
                } else if (AdditionalFunction.FunctionKeys.NEED_CARRY.toString().equals(function.getFunctionKey())) {
                    buffer.append(" | ").append(function.getFunctionName().trim());
                } else if (AdditionalFunction.FunctionKeys.NEED_BACK_STORAGE.toString()
                        .equals(function.getFunctionKey())) {
                    buffer.append(" | ").append(function.getFunctionName().trim()).append("");
                }
            }
        }
        if (!hasEntryLicense) {
            // 重量
            String goodsWeight = goodsWeight(truckRequire);
            if (StringUtils.isNotBlank(goodsWeight)) {
                buffer.append(" | ").append(goodsWeight);
            }
            // 体积
            String goodsVolume = goodsVolume(truckRequire);
            if (StringUtils.isNotBlank(goodsVolume)) {
                buffer.append(" | ").append(goodsVolume);
            }
        }

        if (buffer.length() > 2) {
            return buffer.substring(3);
        }
        return buffer.toString();
    }

    private String goodsWeight(TruckRequire truckRequire) {
        // 重量
        String goodsWeight = truckRequire.getGoodsWeight();
        if (StringUtils.isNotBlank(goodsWeight) && new BigDecimal(goodsWeight).compareTo(BigDecimal.ZERO) > 0) {
            return goodsWeight + "吨";
        }
        return null;
    }

    private String goodsVolume(TruckRequire truckRequire) {
        // 体积
        String goodsVolume = truckRequire.getGoodsVolume();
        if (StringUtils.isNotBlank(goodsVolume) && new BigDecimal(goodsVolume).compareTo(BigDecimal.ZERO) > 0) {
            return goodsVolume + "方";
        }
        return null;
    }

    // 遍历functionIds对应的AdditionalFunction，根据传入的functionKey判断 是否 追加此key对应的附加功能名
    private void appendFunction(StringBuffer sf, List<AdditionalFunction> functions, String functionKey,
            Integer waybillId) {
        if (org.apache.commons.collections.CollectionUtils.isEmpty(functions)) return;

        for (AdditionalFunction function : functions) {
            if (null != function && !function.getParentFunctionId().equals(0)
                    && function.getFunctionKey().toString().equals(functionKey)) {
                sf.append(" | ").append(function.getFunctionName().trim());
                if (functionKey.equals("COLLECTION_PAYMENT")) {
                    WaybillParam waybillParam = waybillParamService.findByWaybillId(waybillId);
                    if (null == waybillParam || null == waybillParam.getAgencyTakeFreight()) {
                        continue;
                    }
                    sf.append(" ￥").append(waybillParam.getAgencyTakeFreight());
                }
            }
        }
    }

    @Override
    public String getGoodsFullName(TruckRequire truckRequire, Integer waybillId) {
        if (null == truckRequire) {
            truckRequire = findTruckRequireByWaybillId(waybillId, null);
        }
        StringBuffer goodsInfoStr = new StringBuffer();
        if (null != truckRequire) {
            String goodsType = truckRequire.getGoodsType();
            String weight = truckRequire.getGoodsWeight();
            String volume = truckRequire.getGoodsVolume();
            if (StringUtils.isNotBlank(goodsType)) {
                goodsInfoStr.append(goodsType).append(" ");
            }
            if (StringUtils.isNotBlank(weight) && new BigDecimal(weight).compareTo(BigDecimal.ZERO) > 0) {
                goodsInfoStr.append(weight).append("吨 ");
            }
            if (StringUtils.isNotBlank(volume) && new BigDecimal(volume).compareTo(BigDecimal.ZERO) > 0) {
                goodsInfoStr.append(volume).append("方");
            }
        }
        return goodsInfoStr.toString();
    }

    @Override
    public void update(TruckRequire truckRequire) throws BusinessException {
        String functionIds = truckRequire.getAdditionalFunctionIds();
        if (StringUtils.isBlank(functionIds)) {
            truckRequire.setAdditionalFunctionIds("");
        }

        truckRequireDao.update(truckRequire);
    }

    @Override
    public void removeNullInfo(TruckRequire truckRequire) throws BusinessException {
        if (null != truckRequire) {
            truckRequireDao.removeNullInfo(truckRequire);
        }
    }

    @Override
    public boolean needCollectionPayment(TruckRequire truckRequire, Integer waybillId) {
        if (null == truckRequire) {
            truckRequire = findTruckRequireByWaybillId(waybillId, null);
        }
        if (null != truckRequire) {
            String functionIds = truckRequire.getAdditionalFunctionIds();
            if (StringUtils.isNotBlank(functionIds)) {
                AdditionalFunction function = additionalFunctionService
                        .findAdditionFunctionByKey(AdditionalFunction.FunctionKeys.COLLECTION_PAYMENT.toString());
                if (null != function) {
                    String[] split = functionIds.split(",");
                    for (String str : split) {
                        if (StringUtils.isNumeric(str) && BaseUtil.strToNum(str) == function.getAdditionalFunctionId()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean needNeedReceipt(TruckRequire truckRequire, Integer waybillId) {
        if (null == truckRequire) {
            truckRequire = findTruckRequireByWaybillId(waybillId, null);
        }
        if (null == truckRequire) {
            return false;
        }

        String functionIds = truckRequire.getAdditionalFunctionIds();
        if (StringUtils.isBlank(functionIds)) {
            return false;
        }

        AdditionalFunction function = additionalFunctionService
                .findAdditionFunctionByKey(AdditionalFunction.FunctionKeys.NEED_RECEIPT.toString());
        if (null == function) {
            return false;
        }

        List<String> list = Arrays.asList(functionIds.split(","));
        if (list.contains(function.getAdditionalFunctionId() + "")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean whetherGoodsTypeCustomized(String goodsType) {
        if (StringUtils.isBlank(goodsType)) {
            return false;
        }

        ConfParamInfo info = confParamInfoService.goodsTypeList();
        if (null == info || CollectionUtils.isEmpty(info.getOptionList())) {
            return true;
        }

        for (ConfParamOption param : info.getOptionList()) {
            if (param.getOptionName().equals(goodsType)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void deleteTruckRequiredByWaybill(Integer waybillId) throws BusinessException {
        if (waybillId == null) return;

        TruckRequire truckRequire = new TruckRequire();
        truckRequire.setWaybillId(waybillId);

        truckRequireDao.delete(truckRequire);
    }

    public static void main(String[] args) {

        String truckRequireStr = " | 123123213";
        System.out.println(truckRequireStr.substring(3, truckRequireStr.length()));
    }

}
