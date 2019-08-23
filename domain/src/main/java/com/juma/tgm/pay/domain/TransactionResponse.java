/**
* @Title: TransactionResponse.java
* @Package com.juma.tgm.gateway.pay.controller
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年8月1日 下午11:54:42
* @version V1.0  
 */
package com.juma.tgm.pay.domain;

import java.io.Serializable;

/**
 *@Description: 
 *@author Administrator
 *@date 2016年8月1日 下午11:54:42
 *@version V1.0  
 */
public class TransactionResponse implements Serializable {
	
	private static final long serialVersionUID = -1305312464039200804L;

	private String type;
	
	private RootResponse data;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public RootResponse getData() {
		return data;
	}

	public void setData(RootResponse data) {
		this.data = data;
	}

}
