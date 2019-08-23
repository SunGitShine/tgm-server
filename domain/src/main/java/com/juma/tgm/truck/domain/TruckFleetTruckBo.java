package com.juma.tgm.truck.domain;

public class TruckFleetTruckBo extends TruckFleetTruck {

    private static final long serialVersionUID = 1436680823157302470L;
    /** 司机姓名 */
    private String nickname;
    /** 司机电话 */
    private String contactPhone;
    /** 车牌号码 */
    private String plateNumber;
    /** 司机ID */
    private Integer driverId;
    /** 司机状态 */
    private Integer status;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
