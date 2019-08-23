package com.juma.tgm.authority.service;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.customer.domain.TruckCustomer;
import com.juma.tgm.customer.domain.vo.CargoOwnerCustomerVo;

/**
 *
 * 用户中心包装服务
 * @ClassName: UserCenterService
 * @Description:
 * @author: liang
 * @date: 2017-05-18 10:48
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */

@Deprecated
public interface TgmUserCenterService {

    /**
     * 创建用户中心企业货主用户信息
     * @param truckCustomer
     * @param customerInfo
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    CargoOwnerCustomerVo createCargoOwnerBelongEnterprise(TruckCustomer truckCustomer, CustomerInfo customerInfo,
            LoginUser loginUser) throws BusinessException;
}
