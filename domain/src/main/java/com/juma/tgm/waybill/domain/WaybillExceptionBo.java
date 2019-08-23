package com.juma.tgm.waybill.domain;

public class WaybillExceptionBo extends WaybillException {

    private static final long serialVersionUID = -1727937275767173932L;

    /** 运单号 */
    private String waybillNo;
    /** 下单时间 */
    private String createDate;
    /** 用车人 */
    private String truckCustomerName;
    /** 用车人联系电话 */
    private String truckCustomerPhone;
    /** 司机 */
    private String driverName;
    /** 司机电话 */
    private String driverPhone;
    /** 司机ID */
    private Integer driverId;
    /** 货车ID */
    private Integer truckId;

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getTruckCustomerName() {
        return truckCustomerName;
    }

    public void setTruckCustomerName(String truckCustomerName) {
        this.truckCustomerName = truckCustomerName;
    }

    public String getTruckCustomerPhone() {
        return truckCustomerPhone;
    }

    public void setTruckCustomerPhone(String truckCustomerPhone) {
        this.truckCustomerPhone = truckCustomerPhone;
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

}