package com.juma.tgm.receiptManage.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * receipt_manage - 回单管理
 * 
 * @author 2017-07-10
 * @version 1.0
 */
public class ReceiptManage implements Serializable {

    private static final long serialVersionUID = -1093345887223146571L;
    private Integer receiptManageId;
    private Integer waybillId;
    private String remark;
    private Date createTime;
    private Integer createUserId;

    /**
     * 冗余字段
     * @return
     */
    private List<String> imgUrls = new ArrayList<String>();

    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    public Integer getReceiptManageId() {
        return receiptManageId;
    }

    public void setReceiptManageId(Integer receiptManageId) {
        this.receiptManageId = receiptManageId;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

}