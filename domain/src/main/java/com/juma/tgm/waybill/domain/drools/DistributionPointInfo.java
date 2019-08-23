/**
* @Title: DistributionPointInfo.java
* @Package com.juma.tgm.waybill.domain.drools
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年9月7日 下午8:03:35
* @version V1.0  
 */
package com.juma.tgm.waybill.domain.drools;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description:
 * @author Administrator
 * @date 2016年9月7日 下午8:03:35
 * @version V1.0
 */
public class DistributionPointInfo implements Serializable {

    private static final long serialVersionUID = -7504632632691993463L;
    // 是否计算配送点费用
    private boolean flag = false;
    // 每个配送点的费用
    private BigDecimal price = BigDecimal.ZERO;

    public DistributionPointInfo() {
    }

    public DistributionPointInfo(Integer siteNo, BigDecimal price) {
        this.flag = (siteNo.compareTo(1) == 1);
        this.price = (price == null ? BigDecimal.ZERO : price);
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
