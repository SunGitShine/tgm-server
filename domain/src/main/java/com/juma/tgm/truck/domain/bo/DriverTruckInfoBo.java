package com.juma.tgm.truck.domain.bo;

import com.juma.tgm.driver.domain.Driver;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by RX on 2016/5/12 0012. 车队显示VO
 */
public class DriverTruckInfoBo implements Serializable {

    private static final long serialVersionUID = 7239917982703052470L;
    private Integer deriverId;// 司机ID
    private String dervicerName;// 司机名字
    private String location;// 位置
    private Integer deriverStatus;// 司机状态
    private String deriverStatusName;// 状态名称
    private Integer truckId;// 货车ID
    private String plateNumber;// 车排号
    private Integer truckTypeId;// 货车类型ID
    // 厢长
    private Integer vehicleBoxLength;
    // 厢型
    private Integer vehicleBoxType;
    private String truckTypeName;// 货车类型名称
    private String truckInfo;// 货车信息
    private String contactPhone; // 联系电话
    private String header;// 头像
    private String starLevel; // 星级
    private Integer carings;// 正在派车数
    private Integer driverNotice;// 已通知司机数
    private Integer truckStaus;// 货车状态
    private Integer isAcceptAllocateOrders;// 是否允许接单
    private Integer entryLicense; // 入城证
    private String TruckNoMatchTypeStr;
    private Integer load;
    private BigDecimal volume;
    @Deprecated
    private String affName; // 符加功能名称

    public DriverTruckInfoBo() {
    }

    public DriverTruckInfoBo(Integer carings, Driver driver) {

        this.carings = carings;
        if (null != driver) {
            this.dervicerName = driver.getNickname();
            this.contactPhone = driver.getContactPhone();
        }
    }

    /**
     * 车辆不满足的类型
     */
    public enum TruckNoMatchType {
        STOP_SERVICE("车辆停运", 1),
        TRUCK_TYPE_NO_MATCH("车型不符", 2),
        ENTRY_LICENSE_NO_MATCH("入城证不符", 3),
        TRUCK_BUSY("车辆不空闲", 4),
        NO_BOUND_DRIVER("没有绑定司机", 5),
        NO_BOUND_VENDOR("没有绑定承运商", 6),
        VENDOR_DISABLED("承运商已停用", 7);

        private int code;
        private String desc;

        TruckNoMatchType(String desc, int code) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    public String getTruckNoMatchTypeStr() {
        return TruckNoMatchTypeStr;
    }

    public void setTruckNoMatchTypeStr(String truckNoMatchTypeStr) {
        TruckNoMatchTypeStr = truckNoMatchTypeStr;
    }

    public Integer getLoad() {
        return load;
    }

    public void setLoad(Integer load) {
        this.load = load;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public Integer getIsAcceptAllocateOrders() {
        return isAcceptAllocateOrders;
    }

    public void setIsAcceptAllocateOrders(Integer isAcceptAllocateOrders) {
        this.isAcceptAllocateOrders = isAcceptAllocateOrders;
    }

    public Integer getTruckStaus() {
        return truckStaus;
    }

    public void setTruckStaus(Integer truckStaus) {
        this.truckStaus = truckStaus;
    }

    public Integer getDeriverId() {
        return deriverId;
    }

    public void setDeriverId(Integer deriverId) {
        this.deriverId = deriverId;
    }

    public String getDervicerName() {
        return dervicerName;
    }

    public void setDervicerName(String dervicerName) {
        this.dervicerName = dervicerName;
    }

    public Integer getDeriverStatus() {
        return deriverStatus;
    }

    public void setDeriverStatus(Integer deriverStatus) {
        this.deriverStatus = deriverStatus;
    }

    public String getDeriverStatusName() {
        return deriverStatusName;
    }

    public void setDeriverStatusName(String deriverStatusName) {
        this.deriverStatusName = deriverStatusName;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Integer getVehicleBoxLength() {
        return vehicleBoxLength;
    }

    public void setVehicleBoxLength(Integer vehicleBoxLength) {
        this.vehicleBoxLength = vehicleBoxLength;
    }

    public Integer getVehicleBoxType() {
        return vehicleBoxType;
    }

    public void setVehicleBoxType(Integer vehicleBoxType) {
        this.vehicleBoxType = vehicleBoxType;
    }

    public Integer getTruckTypeId() {
        return truckTypeId;
    }

    public void setTruckTypeId(Integer truckTypeId) {
        this.truckTypeId = truckTypeId;
    }

    public String getTruckTypeName() {
        return truckTypeName;
    }

    public void setTruckTypeName(String truckTypeName) {
        this.truckTypeName = truckTypeName;
    }

    public String getAffName() {
        return affName;
    }

    public void setAffName(String affName) {
        this.affName = affName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(String starLevel) {
        this.starLevel = starLevel;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Integer getCarings() {
        return carings;
    }

    public void setCarings(Integer carings) {
        this.carings = carings;
    }

    public Integer getDriverNotice() {
        return driverNotice;
    }

    public void setDriverNotice(Integer driverNotice) {
        this.driverNotice = driverNotice;
    }

    public String getTruckInfo() {
        return truckInfo;
    }

    public void setTruckInfo(String truckInfo) {
        this.truckInfo = truckInfo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getEntryLicense() {
        return entryLicense;
    }

    public void setEntryLicense(Integer entryLicense) {
        this.entryLicense = entryLicense;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriverTruckInfoBo that = (DriverTruckInfoBo) o;
        return Objects.equals(deriverId, that.deriverId) &&
            Objects.equals(dervicerName, that.dervicerName) &&
            Objects.equals(location, that.location) &&
            Objects.equals(deriverStatus, that.deriverStatus) &&
            Objects.equals(deriverStatusName, that.deriverStatusName) &&
            Objects.equals(truckId, that.truckId) &&
            Objects.equals(plateNumber, that.plateNumber) &&
            Objects.equals(truckTypeId, that.truckTypeId) &&
            Objects.equals(truckTypeName, that.truckTypeName) &&
            Objects.equals(truckInfo, that.truckInfo) &&
            Objects.equals(contactPhone, that.contactPhone) &&
            Objects.equals(header, that.header) &&
            Objects.equals(starLevel, that.starLevel) &&
            Objects.equals(carings, that.carings) &&
            Objects.equals(driverNotice, that.driverNotice) &&
            Objects.equals(truckStaus, that.truckStaus) &&
            Objects.equals(isAcceptAllocateOrders, that.isAcceptAllocateOrders) &&
            Objects.equals(entryLicense, that.entryLicense) &&
            Objects.equals(TruckNoMatchTypeStr, that.TruckNoMatchTypeStr) &&
            Objects.equals(affName, that.affName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deriverId, dervicerName, location, deriverStatus, deriverStatusName, truckId, plateNumber, truckTypeId, truckTypeName, truckInfo, contactPhone, header, starLevel, carings, driverNotice, truckStaus, isAcceptAllocateOrders, entryLicense, TruckNoMatchTypeStr, affName);
    }

    @Override
    public String toString() {
        return "DriverTruckInfoBo [deriverId=" + deriverId + ", dervicerName=" + dervicerName + ", location=" + location
            + ", deriverStatus=" + deriverStatus + ", deriverStatusName=" + deriverStatusName + ", truckId="
            + truckId + ", plateNumber=" + plateNumber + ", truckTypeId=" + truckTypeId + ", vehicleBoxLength="
            + vehicleBoxLength + ", vehicleBoxType=" + vehicleBoxType + ", truckTypeName=" + truckTypeName
            + ", truckInfo=" + truckInfo + ", contactPhone=" + contactPhone + ", header=" + header + ", starLevel="
            + starLevel + ", carings=" + carings + ", driverNotice=" + driverNotice + ", truckStaus=" + truckStaus
            + ", isAcceptAllocateOrders=" + isAcceptAllocateOrders + ", entryLicense=" + entryLicense
            + ", TruckNoMatchTypeStr=" + TruckNoMatchTypeStr + ", load=" + load + ", volume=" + volume
            + ", affName=" + affName + "]";
    }
}
