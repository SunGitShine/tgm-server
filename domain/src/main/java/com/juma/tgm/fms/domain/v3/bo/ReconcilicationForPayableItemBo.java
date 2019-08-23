package com.juma.tgm.fms.domain.v3.bo;

import com.juma.tgm.fms.domain.v3.ReconcilicationForPayableItem;
import lombok.Data;

import java.io.Serializable;

@Data
public class ReconcilicationForPayableItemBo extends ReconcilicationForPayableItem implements Serializable {
    /**承运商id*/
    private Integer vendorId;
}