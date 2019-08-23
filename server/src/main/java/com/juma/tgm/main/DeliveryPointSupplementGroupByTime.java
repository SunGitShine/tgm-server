package com.juma.tgm.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeliveryPointSupplementGroupByTime {
    
    private Date uploadTime;
    
    private List<String> imgList = new ArrayList<String>();

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

}
