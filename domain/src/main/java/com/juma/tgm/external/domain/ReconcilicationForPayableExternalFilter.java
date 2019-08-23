package com.juma.tgm.external.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 对外类
 */

public class ReconcilicationForPayableExternalFilter implements Serializable {

    // 对账单号集合
    private List<String> listReconcilicationNo;
    // 是否需要crmCustomerId,若需要，因为组装数据，将会影响接口的返回速度
    private boolean isNeedCrmCustomerId;

    public List<String> getListReconcilicationNo() {
        return listReconcilicationNo;
    }

    public void setListReconcilicationNo(List<String> listReconcilicationNo) {
        this.listReconcilicationNo = listReconcilicationNo;
    }

    public boolean isNeedCrmCustomerId() {
        return isNeedCrmCustomerId;
    }

    public void setNeedCrmCustomerId(boolean needCrmCustomerId) {
        isNeedCrmCustomerId = needCrmCustomerId;
    }
}
