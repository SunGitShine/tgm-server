package com.juma.tgm.base.domain;

import java.io.Serializable;

public class MaxAndMinNoDomain implements Serializable {

    private static final long serialVersionUID = 8039552940107920692L;
    private Integer maxNo = 0;
    private Integer minNo = 0;

    public Integer getMaxNo() {
        return maxNo;
    }

    public void setMaxNo(Integer maxNo) {
        this.maxNo = maxNo;
    }

    public Integer getMinNo() {
        return minNo;
    }

    public void setMinNo(Integer minNo) {
        this.minNo = minNo;
    }

}
