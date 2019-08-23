package com.juma.tgm.imageUploadManage.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * image_upload_manage - 图片上传管理
 * 
 * @author 2017-07-10
 * @version 1.0
 */
public class ImageUploadManage implements Serializable {

    private static final long serialVersionUID = -100397899600054594L;
    private Integer imageUploadManageId;
    private Integer imageUploadManageSign;
    private Integer relationId;
    private String imageUploadUrl;
    private String fileName;
    private Date createTime;
    private Integer createUserId;

    public Integer getImageUploadManageId() {
        return imageUploadManageId;
    }

    public void setImageUploadManageId(Integer imageUploadManageId) {
        this.imageUploadManageId = imageUploadManageId;
    }

    public Integer getImageUploadManageSign() {
        return imageUploadManageSign;
    }

    public void setImageUploadManageSign(Integer imageUploadManageSign) {
        this.imageUploadManageSign = imageUploadManageSign;
    }

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public String getImageUploadUrl() {
        return imageUploadUrl;
    }

    public void setImageUploadUrl(String imageUploadUrl) {
        this.imageUploadUrl = imageUploadUrl;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public enum ImageUploadManageSign {

        COST_REIMBURSED(1, "司机费用报销"),
        NEED_RECEIPT(2, "回单"),
        RECONCILIATION_PAYABLE(3, "应付对账单"),
        GOODS_CHECK(4, "货物检查图片"),
        RECONCILIATION_RECEIVABLE(5, "应收对账单"),
        RECONCILIATION_FOR_PAYABLE(6, "应付对账单"),
        PROJECT_DAILY_ACCOUNT(7, "项目日报运营台账");

        private int code;
        private String desc;

        private ImageUploadManageSign(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

    }
}