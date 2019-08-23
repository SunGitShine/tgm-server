package com.juma.tgm.customer.domain.vo;

import com.giants.common.exception.BusinessException;
import com.juma.crm.customer.domain.ConsignorContactsInfo;
import com.juma.tgm.crm.domain.CustomerInfo;
import org.apache.commons.lang.math.NumberUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 落地配客户管理
 * @ClassName: ScatteredCustomerVo
 * @Description:
 * @author: liang
 * @date: 2017-11-20 09:56
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class ScatteredCustomerVo implements Serializable {

    /**
     * 成单金额
     */
    private String totalCost;

    /**
     * 成单量
     */
    private Integer waybillAmount;

    /**
     * 客户信息
     */
    private CustomerInfo customerInfo;

    /**
     * 客户联系人
     */
    private List<ConsignorContactsInfo> consignorContactsInfos;

    public enum CustomerType{
        TYPE_DEAL(1, "已成单"),//客户存在运单状态为已完成的运单
        TYPE_NULL(2, "未成单");//客户没有运单状态为已完成的运单
        private Integer code;
        private String desc;
        CustomerType(Integer code, String desc){
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public static CustomerType getByCode(int code){
            for(CustomerType type : CustomerType.values()){
                if(NumberUtils.compare(code, type.getCode()) == 0) return type;

            }
            throw new BusinessException("codeNotExist","errors.paramError");
        }
    }

    /**
     * 统计信息
     */
    public static class OverViewDataVo implements Serializable{

        /**
         * 总客户数量
         */
        private Integer customerAmount;

        /**
         * 总成单量
         */
        private Integer totalWaybillAmount;

        public Integer getCustomerAmount() {
            return customerAmount;
        }

        public void setCustomerAmount(Integer customerAmount) {
            this.customerAmount = customerAmount;
        }

        public Integer getTotalWaybillAmount() {
            return totalWaybillAmount;
        }

        public void setTotalWaybillAmount(Integer totalWaybillAmount) {
            this.totalWaybillAmount = totalWaybillAmount;
        }
    }


    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public Integer getWaybillAmount() {
        return waybillAmount;
    }

    public void setWaybillAmount(Integer waybillAmount) {
        this.waybillAmount = waybillAmount;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public List<ConsignorContactsInfo> getConsignorContactsInfos() {
        return consignorContactsInfos;
    }

    public void setConsignorContactsInfos(List<ConsignorContactsInfo> consignorContactsInfos) {
        this.consignorContactsInfos = consignorContactsInfos;
    }
}
