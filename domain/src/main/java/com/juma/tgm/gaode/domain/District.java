/**
* @Title: District.java
* @Package com.juma.tgm.gaode.domain
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年6月14日 下午8:14:39
* @version V1.0  
 */
package com.juma.tgm.gaode.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author Administrator
 * @date 2016年6月14日 下午8:14:39
 * @version V1.0
 */
public class District implements Serializable {

    private static final long serialVersionUID = -1572721705170326186L;

    private String citycode;
    private String adcode;
    private String name;
    private String level;
    private List<District> districts = new ArrayList<District>();

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

}
