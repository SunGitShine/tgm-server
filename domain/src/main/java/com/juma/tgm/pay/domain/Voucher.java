/**
* @Title: Voucher.java
* @Package com.juma.tgm.pay.domain
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年8月30日 下午4:42:07
* @version V1.0  
 */
package com.juma.tgm.pay.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description:
 * @author Administrator
 * @date 2016年8月30日 下午4:42:07
 * @version V1.0
 */
public class Voucher implements Serializable {

    private static final long serialVersionUID = 8558166937385137638L;

    private String voucherNum;

    private String merchantOrderNo;

    private BigDecimal amount;

    private String customData;

    public String getVoucherNum() {
        return voucherNum;
    }

    public void setVoucherNum(String voucherNum) {
        this.voucherNum = voucherNum;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public String getCustomData() {
        return customData;
    }

    public void setCustomData(String customData) {
        this.customData = customData;
    }

}
