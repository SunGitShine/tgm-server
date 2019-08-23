/**
* @Title: ColdChainInfo.java
* @Package com.juma.tgm.waybill.domain.drools
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年9月7日 下午8:04:29
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
 * @date 2016年9月7日 下午8:04:29
 * @version V1.0
 */
public class ColdChainInfo implements Serializable {

    private static final long serialVersionUID = -1546383625133299973L;
    // 是否计算冷链
    private boolean flag = false;
    // 冷链收费比率
    private BigDecimal rate = BigDecimal.ZERO;

    public ColdChainInfo() {
    }

    public ColdChainInfo(AdditionalFunctionFreight freight, List<Integer> list) {
        if (null != freight) {
            this.flag = list.contains(freight.getAdditionalFunctionId());
            this.rate = (freight.getLowestFreight() == null ? BigDecimal.ZERO : freight.getLowestFreight());
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

}
