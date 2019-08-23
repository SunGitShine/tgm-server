/**
* @Title: AmapGeoResponse.java
* @Package com.juma.tgm.gaode.domain
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年9月21日 下午2:24:51
* @version V1.0  
 */
package com.juma.tgm.gaode.domain;

import java.io.Serializable;

/**
 * 逆地理编码
 * 
 * @author weilibin
 *
 */
public class AmapRegeoResponse implements Serializable {

    private static final long serialVersionUID = -1319799149342131704L;

    private Integer status;
    private Regeocode regeocode = new Regeocode();

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Regeocode getRegeocode() {
        return regeocode;
    }

    public void setRegeocode(Regeocode regeocode) {
        this.regeocode = regeocode;
    }

    @Override
    public String toString() {
        return "AmapRegeoResponse [status=" + status + ", regeocode=" + regeocode + "]";
    }

}
