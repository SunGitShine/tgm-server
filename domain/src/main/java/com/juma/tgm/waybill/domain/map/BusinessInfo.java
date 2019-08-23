package com.juma.tgm.waybill.domain.map;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.juma.server.vm.domain.Vehicle;
import com.juma.tgm.driver.domain.Driver;

/**
 * 业务信息
 * 
 * @author weilibin
 *
 */

public class BusinessInfo implements Serializable {

    private static final long serialVersionUID = -306124959470589056L;
    private Map<Integer, Vehicle> truckMap;
    private List<Driver> amsDriverList;
    /** 车牌号 */
    private String plateNumber;
    /** 入城证 */
    private String entryLicense;
    /** 司机姓名 */
    private String driverName;
    /** 司机联系方式 */
    private String driverPhone;
    /** 任务状态 */
    private String taskStatus;
    /** 车辆停放地 */
    private String parkingLocation;
    /** GPS设备号 */
    private String equipmentNo;

    public Map<Integer, Vehicle> getTruckMap() {
        return truckMap;
    }

    public void setTruckMap(Map<Integer, Vehicle> truckMap) {
        this.truckMap = truckMap;
    }

    public List<Driver> getAmsDriverList() {
        return amsDriverList;
    }

    public void setAmsDriverList(List<Driver> amsDriverList) {
        this.amsDriverList = amsDriverList;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getEntryLicense() {
        return entryLicense;
    }

    public void setEntryLicense(String entryLicense) {
        this.entryLicense = entryLicense;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getParkingLocation() {
        return parkingLocation;
    }

    public void setParkingLocation(String parkingLocation) {
        this.parkingLocation = parkingLocation;
    }

    public String getEquipmentNo() {
        return equipmentNo;
    }

    public void setEquipmentNo(String equipmentNo) {
        this.equipmentNo = equipmentNo;
    }

}
