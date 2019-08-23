package com.juma.tgm.fms.service.v3;

import com.juma.tgm.fms.domain.v3.ReconciliationUpdateVendorMq;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-05-28 15:12
 **/
public interface ReconciliationUpdateVendorMqService {

	/**
	 * 新增fms更新对账单承运商消息
	 * @param vendorMq
	 */
	void insert(ReconciliationUpdateVendorMq vendorMq);
}
