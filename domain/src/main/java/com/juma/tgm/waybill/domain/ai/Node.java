package com.juma.tgm.waybill.domain.ai;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Node implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6062913601130133337L;

    // 节点类型
    private Integer type;
    
    //订单Id
    private Integer waybillId;

    // 运单的计划用车时间 放在取货地节点上
    private Date time;

    // 运单的重量体积 放在取货地是'+' 收货地是'-'
    private BigDecimal weight;

    private BigDecimal volume;

    //前缀+存放地址Id  前缀d:取货地 r:收货地
    private String nodeId;
    
    private Double lng;

    private Double lat;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

   

}
