/**
* @Title: SuperMileageInfo.java
* @Package com.juma.tgm.waybill.domain.drools
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年9月7日 下午8:02:58
* @version V1.0  
 */
package com.juma.tgm.waybill.domain.drools;

import java.io.Serializable;
import java.math.BigDecimal;

import com.juma.tgm.truck.domain.TruckTypeFreight;

/**
 * @Description:
 * @author Administrator
 * @date 2016年9月7日 下午8:02:58
 * @version V1.0
 */
public class SuperMileageInfo implements Serializable {

    private static final long serialVersionUID = -6751109074355759035L;
    // 是否超里程
    private boolean flag = false;
    // 超里程后每公里费用
    private BigDecimal price = BigDecimal.ZERO;

    public SuperMileageInfo() {
    }

    public SuperMileageInfo(TruckTypeFreight freight, Integer path) {
        Double distance = (path != null ? Double.valueOf(path) : 0D);
        Integer mileage = freight.getLowestMileage();
        this.flag = (Double.compare(distance, (mileage == null ? 0 : mileage.doubleValue())) == 1);
        this.price = freight.getBeyondUnitPrice();
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
