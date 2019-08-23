package com.juma.tgm.project.vo;

import java.io.Serializable;

public class ContractFilter implements Serializable {

    // TMS的客户ID
    private Integer customerId;
    // 合同名称
    private String contractName;
    // 返回条数
    private Integer backPageSize;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public Integer getBackPageSize() {
        return backPageSize;
    }

    public void setBackPageSize(Integer backPageSize) {
        this.backPageSize = backPageSize;
    }
}
