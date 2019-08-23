package com.juma.tgm.base.domain;

import java.io.Serializable;

public class ViewInfo implements Serializable {

    private static final long serialVersionUID = -6225150454431765698L;
    // 设备号
    private String id;
    // 经度
    private Double longitude;
    // 纬度
    private Double latitude;
    // 设备类型
    private Integer type;
    private Integer status;
    // 坐标
    private String coordinate;
    // 地址
    private String address;
    // 上报时间
    private Long time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ViewInfo [id=" + id + ", longitude=" + longitude + ", latitude=" + latitude + ", type=" + type
                + ", status=" + status + ", coordinate=" + coordinate + ", address=" + address + ", time=" + time + "]";
    }

}
