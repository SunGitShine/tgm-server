package com.juma.tgm.waybill.domain.ai;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName:   WaybillFeature   
 * @Description: 派车特征值
 * @author:      Administrator
 * @date:        2017年11月15日 下午3:22:21  
 *
 * @Copyright:   2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class Feature implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5442552164184095062L;

    private Integer type;
    
    private Integer waybillId;
    
    private Integer driverId;
    
    private Integer truckId;
    
    private Integer operate;
    
    private Date  deliveryTime;
    
    private List<TruckFeature> trucks = new ArrayList<TruckFeature>();
    
    private List<Node> nodes = new ArrayList<Node>();
    
    //通知数据平台
    public enum Operate {

        Dispatcher_Arrange(1, "调度安排"), Driver_No_Response(2, "司机未响应"), Dispatcher_Cancel(3, "调度取消"),Request_Match(4,"请求匹配")
        ,Start_Delivery(5,"开始配送"),Finish_Delivery(6,"结束配送");

        private int code;

        private String message;

        private Operate(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public Integer getOperate() {
        return operate;
    }

    public void setOperate(Integer operate) {
        this.operate = operate;
    }

    public List<TruckFeature> getTrucks() {
        return trucks;
    }

    public void setTrucks(List<TruckFeature> trucks) {
        this.trucks = trucks;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
    
    public void addTruck(TruckFeature tf) {
        trucks.add(tf);
    }
    
    public void addNode(Node node) {
        nodes.add(node);
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    
}
