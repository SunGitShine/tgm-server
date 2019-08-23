package com.juma.tgm.crm.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @Description: 大客户Resp
 * @author weilibin
 * @date 2016年6月30日 下午6:31:41
 * @version V1.0
 */

public class CustomerInfoResp implements Serializable {

    private static final long serialVersionUID = 7833396628962127246L;
    private List<CustomerInfo> customerInfoList;


    public List<CustomerInfo> getCustomerInfoList() {
        return customerInfoList;
    }

    public void setCustomerInfoList(List<CustomerInfo> customerInfoList) {
        this.customerInfoList = customerInfoList;
    }

}
