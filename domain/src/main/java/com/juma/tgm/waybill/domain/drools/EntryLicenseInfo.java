/**
* @Title: EntryLicenseInfo.java
* @Package com.juma.tgm.waybill.domain.drools
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年9月7日 下午8:06:55
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
 * @date 2016年9月7日 下午8:06:55
 * @version V1.0
 */
public class EntryLicenseInfo implements Serializable {

    private static final long serialVersionUID = 3085020182734271909L;
    // 是否计算入城证
    private boolean flag = false;
    // 入城证费用
    private BigDecimal price = BigDecimal.ZERO;

    public EntryLicenseInfo() {
    }

    public EntryLicenseInfo(AdditionalFunctionFreight freight, List<Integer> list) {
        if (null != freight) {
            BigDecimal lowestFreight = freight.getLowestFreight();
            this.flag = list.contains(freight.getAdditionalFunctionId());
            this.price = (lowestFreight == null ? BigDecimal.ZERO : lowestFreight);
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
