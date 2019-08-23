package com.juma.tgm.cityManage.domain;

import com.juma.tgm.base.domain.BaseDomain;

/**
 * 城市和地区管理
 *
 * 地区下有多个城市
 */
public class CityManage extends BaseDomain {

    private static final long serialVersionUID = -5018705048051012464L;
    private Integer cityManageId;
    private Integer parentCityManageId;
    private String provinceName;
    private String provinceCode;
    private String cityName;
    private String cityCode;
    private Integer citySign;
    private Integer orderNo;

    public Integer getCityManageId() {
        return cityManageId;
    }

    public void setCityManageId(Integer cityManageId) {
        this.cityManageId = cityManageId;
    }

    public Integer getParentCityManageId() {
        return parentCityManageId;
    }

    public void setParentCityManageId(Integer parentCityManageId) {
        this.parentCityManageId = parentCityManageId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getCitySign() {
        return citySign;
    }

    public void setCitySign(Integer citySign) {
        this.citySign = citySign;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public enum Sign {
        START_FROM(0, "出发"), END_FORM(1, "到达"),AREA_MANAGE(2, "区域管理");

        private Sign(Integer code, String descr) {
            this.code = code;
            this.descr = descr;
        }

        private Integer code;
        private String descr;

        public Integer getCode() {
            return code;
        }

        public String getDescr() {
            return descr;
        }
    }
}
