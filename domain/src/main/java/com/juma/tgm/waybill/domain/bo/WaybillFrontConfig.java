/**
* @Title: WaybillFrontConfig.java
* @Package com.juma.tgm.waybill.domain.bo
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年9月5日 下午2:56:58
* @version V1.0  
 */
package com.juma.tgm.waybill.domain.bo;

import java.util.Date;

/**
 *@Description: 前端模板变量
 *@author Administrator
 *@date 2016年9月5日 下午2:56:58
 *@version V1.0  
 */
public class WaybillFrontConfig {

	private Date ctime = new Date();
	
	private Date planDeliveryTime;
	
	private Date finishDeliveryTime;

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getPlanDeliveryTime() {
		return planDeliveryTime;
	}

	public void setPlanDeliveryTime(Date planDeliveryTime) {
		this.planDeliveryTime = planDeliveryTime;
	}

	public Date getFinishDeliveryTime() {
		return finishDeliveryTime;
	}

	public void setFinishDeliveryTime(Date finishDeliveryTime) {
		this.finishDeliveryTime = finishDeliveryTime;
	}
	
}
