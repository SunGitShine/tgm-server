/**
* @Title: AuthorityBo.java
* @Package com.juma.tgm.authority.domain
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年5月27日 下午3:00:51
* @version V1.0  
 */
package com.juma.tgm.authority.domain;

import java.io.Serializable;

/**
 *@Description: 
 *@author zxh
 *@date 2016年5月27日 下午3:00:51
 *@version V1.0  
 */
public class AuthorityBo implements Serializable {
	
	/**
	 */
	private static final long serialVersionUID = -1993788048329984872L;

	public AuthorityBo(){
		
	}

	public AuthorityBo(Integer userId, String authorizationCode) {
		super();
		this.userId = userId;
		this.authorizationCode = authorizationCode;
	}

	private Integer userId;
	
	private String authorizationCode;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAuthorizationCode() {
		return authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}
}
