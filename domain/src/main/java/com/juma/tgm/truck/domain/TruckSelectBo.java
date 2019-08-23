package com.juma.tgm.truck.domain;

import java.io.Serializable;

/**
 * 
 * @Description: 用户回显车队已经绑定的货车
 * @author weilibin
 * @date 2016年5月30日 下午4:07:01
 * @version V1.0
 */

public class TruckSelectBo implements Serializable {

    private static final long serialVersionUID = -5741013222055352020L;
    /** 货车ID */
    private Integer truckId;
    /** 车牌号 */
    private String plateNumber;
    /** 司机姓名 */
    private String nickname;
    /** 司机电话 */
    private String driverPhone;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

}
