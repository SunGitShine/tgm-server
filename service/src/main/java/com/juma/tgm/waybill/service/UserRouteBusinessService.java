package com.juma.tgm.waybill.service;

import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.user.domain.UserRouteMaster;
import com.juma.tgm.waybill.domain.WaybillDeliveryAddress;
import com.juma.tgm.waybill.domain.WaybillReceiveAddress;

import java.util.List;

/**
 * @ClassName: UserRouteBusinessService
 * @Description:
 * @author: liang
 * @date: 2017-11-15 17:14
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public interface UserRouteBusinessService {
    void addUserRoute(List<WaybillDeliveryAddress> deliveryAddress, List<WaybillReceiveAddress> receiveAddress, UserRouteMaster.BusinessBranch type, LoginUser loginUser);

    /**
     * 客户经理代发单添加常用线路
     * @param deliveryAddress
     * @param receiveAddress
     * @param type
     * @param truckCustomerId
     */
    void addUserRouteForManager(List<WaybillDeliveryAddress> deliveryAddress, List<WaybillReceiveAddress> receiveAddress, UserRouteMaster.BusinessBranch type, int truckCustomerId);
}
