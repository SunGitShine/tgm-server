/**
* @Title: RootResponse.java
* @Package com.juma.tgm.pay.domain
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年8月30日 下午4:45:25
* @version V1.0  
 */
package com.juma.tgm.pay.domain;

import java.io.Serializable;

/**
 *@Description: 
 *@author Administrator
 *@date 2016年8月30日 下午4:45:25
 *@version V1.0  
 */
public class RootResponse implements Serializable {
	
	private static final long serialVersionUID = -5612967245140804160L;

	private Voucher voucher;
	
	private String paymentVoucherNum;

	public Voucher getVoucher() {
		return voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}

	public String getPaymentVoucherNum() {
		return paymentVoucherNum;
	}

	public void setPaymentVoucherNum(String paymentVoucherNum) {
		this.paymentVoucherNum = paymentVoucherNum;
	}

}
