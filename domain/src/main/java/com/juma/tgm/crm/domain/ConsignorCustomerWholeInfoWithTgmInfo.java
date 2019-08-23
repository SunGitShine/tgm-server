package com.juma.tgm.crm.domain;

import com.juma.crm.customer.domain.ConsignorCustomerElimination;
import com.juma.crm.customer.domain.ConsignorCustomerWholeInfoVo;

import java.util.List;

/**
 * @ClassName: ConsignorCustomerWholeInfoWithTgmInfo
 * @Description:
 * @author: liang
 * @date: 2017-03-13 17:19
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class ConsignorCustomerWholeInfoWithTgmInfo extends ConsignorCustomerWholeInfoVo {

    private Integer tgmCustomerId;

    /**
     * 替换用于展示的vo
     */
    private List<ConsignorVisitRecordTgmVo> consignorVisitRecordTgmVos;

    /**
     * 淘汰原因
     */
    private ConsignorCustomerElimination consignorCustomerElimination;

    public Integer getTgmCustomerId() {
        return tgmCustomerId;
    }

    public void setTgmCustomerId(Integer tgmCustomerId) {
        this.tgmCustomerId = tgmCustomerId;
    }

    public List<ConsignorVisitRecordTgmVo> getConsignorVisitRecordTgmVos() {
        return consignorVisitRecordTgmVos;
    }

    public void setConsignorVisitRecordTgmVos(List<ConsignorVisitRecordTgmVo> consignorVisitRecordTgmVos) {
        this.consignorVisitRecordTgmVos = consignorVisitRecordTgmVos;
    }

    public ConsignorCustomerElimination getConsignorCustomerElimination() {
        return consignorCustomerElimination;
    }

    public void setConsignorCustomerElimination(ConsignorCustomerElimination consignorCustomerElimination) {
        this.consignorCustomerElimination = consignorCustomerElimination;
    }
}
