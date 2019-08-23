package com.juma.tgm.waybill.external.service.impl;

import java.io.Serializable;
import me.about.poi.ExcelColumn;

/**
 * @ClassName WaybillExportVo
 * @Description TODO
 * @Author weilibin
 * @Date 2019-07-29 17:41
 * @Version 1.0.0
 */

public class WaybillExportVo implements Serializable {

    @ExcelColumn(name = "运单号")
    private String waybillNo;
    @ExcelColumn(name = "回单状态")
    private String needReceiptText;
    @ExcelColumn(name = "项目经理")
    private String projectManagerUserName;

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public String getNeedReceiptText() {
        return needReceiptText;
    }

    public void setNeedReceiptText(String needReceiptText) {
        this.needReceiptText = needReceiptText;
    }

    public String getProjectManagerUserName() {
        return projectManagerUserName;
    }

    public void setProjectManagerUserName(String projectManagerUserName) {
        this.projectManagerUserName = projectManagerUserName;
    }
}
