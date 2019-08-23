package com.juma.tgm.fms.domain.v3.bo;

import com.juma.tgm.fms.domain.v3.ReconcilicationForReceivableItem;
import lombok.Data;

import java.io.Serializable;

@Data
public class ReconcilicationForReceivableItemBo extends ReconcilicationForReceivableItem implements Serializable {
    /**承运商id*/
    private Integer vendorId;
}