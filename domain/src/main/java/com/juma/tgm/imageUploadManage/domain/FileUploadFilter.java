package com.juma.tgm.imageUploadManage.domain;

import java.io.Serializable;

public class FileUploadFilter implements Serializable {

    private Integer imageUploadManageSign;
    private Integer relationId;

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
}
