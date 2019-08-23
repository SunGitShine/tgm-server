/**
* @Title: ContactHistory.java
* @Package com.juma.tgm.waybill.service
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年7月4日 下午2:35:10
* @version V1.0  
 */
package com.juma.tgm.waybill.service;

import java.util.List;

import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybill.domain.AddressHistory;
import com.juma.tgm.waybill.domain.ContactHistory;

/**
 *@Description: 
 *@author Administrator
 *@date 2016年7月4日 下午2:35:10
 *@version V1.0  
 */
public interface ContactHistoryService {

	List<ContactHistory> getContactHistorys(PageCondition pageCondition);
	
	ContactHistory getContactHistory(ContactHistory example);
	
	void batchSave(List<ContactHistory> rows);
	
	void saveContactHistory(ContactHistory entity);
	
	void updateContactHistory(ContactHistory entity);

	/**
	 * 
	 * @Title: addOrUpdateContactHis
	 * @Description: 联系人历史
	 * @param address
	 * @param contactHistory
	 */
	void addOrUpdateContactHis(AddressHistory address, ContactHistory contactHistory);
	
	void delete(Integer contactId, LoginUser loginUser);
}
