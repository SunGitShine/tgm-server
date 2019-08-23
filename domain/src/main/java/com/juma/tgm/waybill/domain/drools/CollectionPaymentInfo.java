/**
* @Title: CollectionPaymentInfo.java
* @Package com.juma.tgm.waybill.domain.drools
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年9月7日 下午8:04:01
* @version V1.0  
 */
package com.juma.tgm.waybill.domain.drools;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.juma.tgm.truck.domain.AdditionalFunctionFreight;

/**
 * @Description:
 * @author Administrator
 * @date 2016年9月7日 下午8:04:01
 * @version V1.0
 */
public class CollectionPaymentInfo implements Serializable {

    private static final long serialVersionUID = -5562184721138978743L;
    // 是否代收货款
    private boolean flag = false;
    // 代收货款费用
    private BigDecimal price = BigDecimal.ZERO;

    public CollectionPaymentInfo() {
    }

    public CollectionPaymentInfo(AdditionalFunctionFreight freight, List<Integer> list) {
        if (null != freight) {
            this.flag = list.contains(freight.getAdditionalFunctionId());
            this.price = freight.getLowestFreight();
        }
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
