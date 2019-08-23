package com.juma.tgm.imageUploadManage.domain;

import java.io.Serializable;

/**
 * 上传对象
 */

public class UploadFile implements Serializable {

    private String fileName;
    private String fileUrl;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
