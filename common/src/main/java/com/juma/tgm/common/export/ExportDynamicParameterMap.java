package com.juma.tgm.common.export;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ExportDynamicParameterMap.java
 * @Description 临时使用，若数据变更在数据库中获取，则此类可以删除
 * @author Libin.Wei
 * @Date 2018年5月17日 下午3:18:20
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class ExportDynamicParameterMap {

    public static Map<String, String> waybillExportParameterMap () {
        Map<String, String> result = new HashMap<String, String>();
        result.put("basic_waybillNo", "waybillNo");
        result.put("basic_statusView", "statusView"); // 特殊字段
        result.put("basic_businessBranch", "businessBranch");
        result.put("basic_areaCode", "selfAreaName"); // 特殊字段
        result.put("basic_shareAreaCode", "shareAreaName"); // 特殊字段
        result.put("basic_customerName", "customerName");
        result.put("basic_projectName", "projectName");
        result.put("basic_contactName", "contactName"); // 联系人
        result.put("basic_contactPhone", "contactPhone"); // 联系人电话
        result.put("basic_customerManagerName", "customerManagerName");
        result.put("basic_mobileNumber", "mobileNumber");
        result.put("basic_createTime", "createTime");
        result.put("basic_waybillSource", "waybillSource");
        result.put("basic_orderNum", "orderNum");
        result.put("goods_goodsType", "goodsType");
        result.put("goods_goodsWeight", "goodsWeight");
        result.put("goods_goodsVolume", "goodsVolume");
        result.put("goods_goodsAmount", "goodsAmount");
        result.put("address_addressDetail", "addressDetail");
        result.put("address_contactName", "contactName");
        result.put("address_contactPhone", "contactPhone");
        result.put("address_estimateDistance", "estimateDistance");
        result.put("address_actualMileage", "actualMileage");
        result.put("address_reserveAddressCount", "reserveAddressCount"); // 特殊字段
        result.put("address_reserveAddressDetails", "reserveAddressDetails"); // 特殊字段
        result.put("truckRequire_planDeliveryTime", "planDeliveryTime");
        result.put("truckRequire_requireType", "requireType"); // 特殊字段
        result.put("truckRequire_entryLicense", "entryLicense");
        result.put("truckRequire_needReceipt", "isRecallOrder"); // 特殊字段
        result.put("truckRequire_isNeedBackStorage", "isComeBackOrder"); // 特殊字段
        result.put("truckRequire_isCollectPayment", "isAgentCollectFreight"); // 特殊字段
        result.put("truckRequire_remark", "remark");
        result.put("capacity_driverName", "driverName");
        result.put("capacity_driverType", "driverType");
        result.put("capacity_contactPhone", "contactPhone"); // 司机电话
        result.put("capacity_plateNumber", "plateNumber");
        result.put("capacity_actualType", "actualType"); // 运力车型
        result.put("distribution_driverArrivedLoadingTime", "houseTimeConsume"); // 特殊字段
        result.put("distribution_loadingTime", "loadingTimeConsume"); // 特殊字段
        result.put("distribution_shippingTime", "deliveryTimeConsume"); // 特殊字段
        result.put("cost_estimateFreight", "estimateFreight");
        result.put("cost_taxRateValue", "taxRateValue");
        result.put("cost_afterTaxFreight", "afterTaxFreight");
        result.put("cost_rebateFee", "comeBackFreight"); // 特殊字段
        result.put("cost_show4DriverFreight", "show4DriverFreight");
        result.put("cost_receiptType", "receiptType");
        result.put("cost_updateFreightRemark", "updateFreightRemark");
        result.put("cancel_cancelChannel", "cancelChannel");
        result.put("cancel_waybillCancelRemark", "waybillCancelRemark");
        return result;
    }
}
