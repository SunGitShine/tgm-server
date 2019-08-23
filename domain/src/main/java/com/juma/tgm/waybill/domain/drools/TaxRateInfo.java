/**
* @Title: TaxRateInfo.java
* @Package com.juma.tgm.waybill.domain.drools
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年9月7日 下午8:05:28
* @version V1.0  
 */
package com.juma.tgm.waybill.domain.drools;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

import com.juma.tgm.common.Constants;
import com.juma.tgm.waybill.domain.TaxRate;

/**
 * @Description:
 * @author Administrator
 * @date 2016年9月7日 下午8:05:28
 * @version V1.0
 */
public class TaxRateInfo implements Serializable {

    private static final long serialVersionUID = 1437420338040175713L;
    // 是否计算税费
    private boolean flag = false;
    // 税率
    private BigDecimal rate = BigDecimal.ZERO;

    public TaxRateInfo() {
    }

    public TaxRateInfo(Map<String, Object> map) {
        Object obj = map.get(Constants.TAX_RATE);
        if (null != obj) {
            BigDecimal value = ((TaxRate) obj).getTaxRateValue();
            this.flag = true;
            this.rate = (new BigDecimal(String.valueOf(value == null ? 0f : value)));
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

    @Override
    public String toString() {
        return "TaxRateInfo [flag=" + flag + ", rate=" + rate + "]";
    }

}
