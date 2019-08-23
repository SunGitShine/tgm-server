package com.juma.tgm.crm.domain;

import com.juma.crm.customer.domain.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CrmConsignorInfo
 * @Description: crm 货主信息 bo
 * @author: liang
 * @date: 2017-03-09 14:08
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class CrmConsignorInfoBo {


    /**
     * 货主基础信息
     */
    private ConsignorBaseInfoVo consignorBaseInfoVo;

    /**
     * 发货概览
     */
    private ConsignorDeliveryOverview consignorDeliveryOverview;

    /**
     * 合同信息
     */
    private ConsignorContractInfo consignorContractInfo;

    public ConsignorBaseInfoVo getConsignorBaseInfoVo() {
        return consignorBaseInfoVo;
    }

    public void setConsignorBaseInfoVo(ConsignorBaseInfoVo consignorBaseInfoVo) {
        this.consignorBaseInfoVo = consignorBaseInfoVo;
    }

    public ConsignorDeliveryOverview getConsignorDeliveryOverview() {
        return consignorDeliveryOverview;
    }

    public void setConsignorDeliveryOverview(ConsignorDeliveryOverview consignorDeliveryOverview) {
        this.consignorDeliveryOverview = consignorDeliveryOverview;
    }

    public ConsignorContractInfo getConsignorContractInfo() {
        return consignorContractInfo;
    }

    public void setConsignorContractInfo(ConsignorContractInfo consignorContractInfo) {
        this.consignorContractInfo = consignorContractInfo;
    }

}
