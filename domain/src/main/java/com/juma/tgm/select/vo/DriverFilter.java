package com.juma.tgm.select.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "司机过滤条件")
public class DriverFilter implements Serializable {

    @ApiModelProperty(value = "司机名字")
    private String driverName;

    @ApiModelProperty(value = "司机电话")
    private String driverContactPhone;

    private Integer pageSize = 15;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getDriverContactPhone() {
        return driverContactPhone;
    }

    public void setDriverContactPhone(String driverContactPhone) {
        this.driverContactPhone = driverContactPhone;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
}
