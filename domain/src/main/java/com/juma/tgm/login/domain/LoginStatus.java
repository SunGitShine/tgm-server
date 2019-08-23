/**
* @Title: LoginStatus.java
* @Package com.juma.tgm.login.domain
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年6月7日 上午9:46:50
* @version V1.0  
 */
package com.juma.tgm.login.domain;

/**
 *@author zxh
 *@date 2016年6月7日 上午9:46:50
 *@version V1.0  
 */
public class LoginStatus {

	private Integer userId;
	
	private String accessToken;
	
	public LoginStatus(){}
	
	public LoginStatus(Integer userId,String accessToken){
		this.userId = userId;
		this.accessToken = accessToken;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
}
