/**
* @Title: AmapAroundResponse.java
* @Package com.juma.tgm.gaode.domain
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年7月21日 下午3:33:56
* @version V1.0  
 */
package com.juma.tgm.gaode.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *@Description: 附近
 *@author Administrator
 *@date 2016年7月21日 下午3:33:56
 *@version V1.0  
 */
public class AmapAroundResponse implements Serializable {

    	private static final long serialVersionUID = -78033171269841468L;

		private Integer count;
	    
	    private Integer status;
	    
	    private List<DriverLocation> datas = new ArrayList<DriverLocation>();

		public Integer getCount() {
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public List<DriverLocation> getDatas() {
			return datas;
		}

		public void setDatas(List<DriverLocation> datas) {
			this.datas = datas;
		}
	    
}
