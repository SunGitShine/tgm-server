/**
* @Title: AddressHistoryService.java
* @Package com.juma.tgm.waybill.service
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年7月4日 下午2:34:18
* @version V1.0  
 */
package com.juma.tgm.waybill.service;

import java.util.List;

import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybill.domain.AddressHistory;
import com.juma.tgm.waybill.domain.WaybillDeliveryAddress;
import com.juma.tgm.waybill.domain.WaybillReceiveAddress;

/**
 *@Description: 
 *@author Administrator
 *@date 2016年7月4日 下午2:34:18
 *@version V1.0  
 */
public interface AddressHistoryService {

	List<AddressHistory> getAddressHistorys(PageCondition pageCondition, LoginUser loginUser);
	
	AddressHistory getAddressHistory(AddressHistory example);
	
	void batchSave(List<AddressHistory> rows);
	
	void saveAddressHistory(AddressHistory entity);
	
	void updateAddressHistory(AddressHistory entity);
	
	/**
	 * @Title: addOrUpdateressHistory
	 * @Description: 地址历史 联系人历史
	 * @param sourceAddr
	 * @param targetAddr
	 * @param loginEmployee
	 */
	void addOrUpdateressHistory(List<WaybillDeliveryAddress> sourceAddr, List<WaybillReceiveAddress> targetAddr, LoginUser loginUser);

    /**
     * 
     * 最近5个地址 5个地址对应的联系人:历史地址的坐标字段如果为空就不获取
     * @Date 2017年4月27日 上午9:32:53
     * @param pageCondition
     * @param loginEmployee
     * @return List<AddressHistory>
     */
    List<AddressHistory> getAddressAndContactHistory(PageCondition pageCondition, LoginUser loginUser);
}
