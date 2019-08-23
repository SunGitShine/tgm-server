package com.juma.tgm.imageUploadManage.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FileUploadParameter implements Serializable {

    private Integer imageUploadManageSign;
    private Integer relationId;
    private List<UploadFile> listUploadFile = new ArrayList<>();

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

    public List<UploadFile> getListUploadFile() {
        return listUploadFile;
    }

    public void setListUploadFile(List<UploadFile> listUploadFile) {
        this.listUploadFile = listUploadFile;
    }

}
