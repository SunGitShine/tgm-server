package com.juma.tgm.customer.domain.vo;

import java.io.Serializable;

/**
 * @ClassName: SearchEnterpriseUserAndCargoOwner
 * @Description:
 * @author: liang
 * @date: 2017-05-27 16:45
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class SearchEnterpriseUserAndCargoOwner implements Serializable {

    public static final int INFO_TYPE_ENTERPRISE = 1;

    public static final int INFO_TYPE_CARGO_OWNER = 2;

    private Integer customerId;
    private String customerName;
    /**
    * 企业客户：1，用车人：2
    * */
    private Integer type;

    private String typeName;

    private String sourceCode;

    private String sourceName;


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }
}
