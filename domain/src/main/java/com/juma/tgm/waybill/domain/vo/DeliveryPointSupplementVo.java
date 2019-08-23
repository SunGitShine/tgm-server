package com.juma.tgm.waybill.domain.vo;

import com.juma.tgm.driver.domain.ReportInfoParam;
import com.juma.tgm.waybill.domain.DeliveryPointSupplement;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: DeliveryPoinSupplementVo
 * @Description:
 * @author: liang
 * @date: 2017-06-20 18:21
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class DeliveryPointSupplementVo implements Serializable {

    /**
     * 线路修改信息
     */
    private List<DeliveryPointSupplement> deliveryPointSupplements;

    /**
     * 报备信息
     */
    private ReportInfoParam reportInfoParam;

    public List<DeliveryPointSupplement> getDeliveryPointSupplements() {
        return deliveryPointSupplements;
    }

    public void setDeliveryPointSupplements(List<DeliveryPointSupplement> deliveryPointSupplements) {
        this.deliveryPointSupplements = deliveryPointSupplements;
    }

    public ReportInfoParam getReportInfoParam() {
        return reportInfoParam;
    }

    public void setReportInfoParam(ReportInfoParam reportInfoParam) {
        this.reportInfoParam = reportInfoParam;
    }

    public List<String> getImgs(){
        if(CollectionUtils.isEmpty(this.deliveryPointSupplements)){
            return null;
        }

        List<String> imgs = new ArrayList<>();

        for(DeliveryPointSupplement dps : deliveryPointSupplements){
            imgs.add(dps.getImg());
        }
        return imgs;
    }
}
