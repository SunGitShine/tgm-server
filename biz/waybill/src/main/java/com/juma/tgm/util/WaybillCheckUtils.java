package com.juma.tgm.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.juma.tgm.project.domain.ProjectFreightRule;
import com.juma.tgm.project.enumeration.ValuationWayEnum;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.service.WaybillCheckService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillParamService;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;

@Service
public class WaybillCheckUtils implements WaybillCheckService {

    @Resource
    private WaybillCommonService waybillCommonService;
    @Resource
    private WaybillParamService waybillParamService;

    @Override
    public boolean checkProjectIsWorkload(Integer waybillId) {
        if (null == waybillId) {
            return false;
        }

        Waybill waybill = waybillCommonService.getWaybillById(waybillId);
        if (null == waybill) {
            return false;
        }

        WaybillParam waybillParam = null;
        // 转运单，查找原单
        if (NumberUtils.compare(waybill.getWaybillSource(), Waybill.WaybillSource.TRANSFORM_BILL.getCode()) == 0) {
            waybillParam = waybillParamService.findByTransformBillLinkId(waybillId);
        } else {
            waybillParam = waybillParamService.findByWaybillId(waybillId);
        }

        if (null == waybillParam || StringUtils.isBlank(waybillParam.getProjectFreightRuleJson())) {
            return false;
        }

        ProjectFreightRule freightRule = JSON.parseObject(waybillParam.getProjectFreightRuleJson(),
                ProjectFreightRule.class);
        if (null == freightRule) {
            return false;
        }

        Map<String, String> ruleMap = JSON.parseObject(freightRule.getFactorJson(), new TypeReference<HashMap<String,
                String>>() {
        });
        //只有起步价
        if (ruleMap.size() == 1 && ruleMap.containsKey("initiateRate")) {
            return false;
        }

        if (null != freightRule.getValuationWay()
                && NumberUtils.compare(freightRule.getValuationWay(), ValuationWayEnum.FIXED_PRICE.getCode()) == 0) {
            return false;
        }


        return true;
    }

    @Override
    public boolean checkIsDriverWriteWork(Integer waybillId) {
        return this.loadWhoWriteWork(waybillId) == 1;
    }

    @Override
    public int loadWhoWriteWork(Integer waybillId) {
        if (null == waybillId) {
            return 1;
        }

        Waybill waybill = waybillCommonService.getWaybillById(waybillId);
        if (null == waybill) {
            return 1;
        }

        WaybillParam waybillParam = null;
        // 转运单，查找原单
        if (NumberUtils.compare(waybill.getWaybillSource(), Waybill.WaybillSource.TRANSFORM_BILL.getCode()) == 0) {
            waybillParam = waybillParamService.findByTransformBillLinkId(waybillId);
        } else {
            waybillParam = waybillParamService.findByWaybillId(waybillId);
        }

        // 查不到默认司机填写
        if (null == waybillParam || StringUtils.isBlank(waybillParam.getProjectFreightRuleJson())) {
            return 1;
        }

        ProjectFreightRule projectFreightRule = JSON.parseObject(waybillParam.getProjectFreightRuleJson(),
                ProjectFreightRule.class);
        return (projectFreightRule == null || projectFreightRule.getWhoWriteWork() == null) ? 1 :
                projectFreightRule.getWhoWriteWork();
    }
}
