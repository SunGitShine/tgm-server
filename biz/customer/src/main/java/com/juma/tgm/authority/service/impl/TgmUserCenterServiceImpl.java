package com.juma.tgm.authority.service.impl;

import com.giants.common.exception.BusinessException;
import com.juma.auth.authority.service.AuthorityService;
import com.juma.auth.user.domain.EcoUser;
import com.juma.auth.user.domain.EcoUserAuthInfo;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.auth.user.service.UserService;
import com.juma.tgm.authority.service.TgmUserCenterService;
import com.juma.tgm.common.Constants;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.customer.domain.TruckCustomer;
import com.juma.tgm.customer.domain.vo.CargoOwnerCustomerVo;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @ClassName: TgmUserCenterServiceImpl
 * @Description:
 * @author: liang
 * @date: 2017-05-18 10:54
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
@Deprecated
@Service
public class TgmUserCenterServiceImpl implements TgmUserCenterService {

    private static final Logger log = LoggerFactory.getLogger(TgmUserCenterServiceImpl.class);

    @Resource
    private AuthorityService authorityService;

    @Resource
    private UserService userService;

    @Override
    public CargoOwnerCustomerVo createCargoOwnerBelongEnterprise(TruckCustomer truckCustomer, CustomerInfo customerInfo,
                                                                 LoginUser loginUser) throws BusinessException {
        if (customerInfo == null) {
            log.info("新建用车人所属企业信息为空.");
            throw new BusinessException("customerInfoNull", "waybill.error.customerInfoNull");
        }
        CargoOwnerCustomerVo cargoOwnerCustomerVo = new CargoOwnerCustomerVo();

        String contactPhone = StringUtils.trim(truckCustomer.getContactPhone());
        truckCustomer.setContactPhone(contactPhone);
        this.checkTruckCustomerPhone(truckCustomer, customerInfo, cargoOwnerCustomerVo, loginUser);

        if (cargoOwnerCustomerVo.getFlag() != null
            && NumberUtils.compare(cargoOwnerCustomerVo.getFlag(), CargoOwnerCustomerVo.FLAG_EXSIT) == 0) {
            truckCustomer.setNickname(null);
        }

        // 货主用户中心账号
        EcoUser ecoUser = this.buildUserInfoForEnterprise(truckCustomer, customerInfo, loginUser);

        // tgm货主
        truckCustomer.setContactPhone(contactPhone);
        // 添加truckCustomer数据
        truckCustomer.setUserId(ecoUser.getUserId());
        // 设置默认地区
        truckCustomer.setRegionCode(customerInfo.getRegionCode());
        truckCustomer.setStatus(Integer.parseInt(Byte.toString(User.UserStatus.NORMAL.getStatus())));
        truckCustomer.setEcoUserId(ecoUser.getEcoUserId());

        truckCustomer.setAreaCode(ecoUser.getAreaCode());
        truckCustomer.setTenantCode(ecoUser.getTenantCode());
        truckCustomer.setTenantId(ecoUser.getTenantId());

        cargoOwnerCustomerVo.setTruckCustomer(truckCustomer);

        return cargoOwnerCustomerVo;
    }

    /**
     * 校验数据
     *
     * @param truckCustomer
     * @param customerInfo
     * @param cargoOwnerCustomerVo
     */
    private void checkTruckCustomerPhone(TruckCustomer truckCustomer, CustomerInfo customerInfo,
                                         CargoOwnerCustomerVo cargoOwnerCustomerVo, LoginUser loginUser) {
        if (truckCustomer == null) {
            log.info("新建用车人信息为空.");
            throw new BusinessException("truckCustomerNull", "waybill.error.truckCustomerNull");
        }

        if (StringUtils.isBlank(truckCustomer.getContactPhone())) {
            log.info("新建用车人电话为空.");
            throw new BusinessException("truckCustomerPhoneNull", "waybill.error.truckCustomerPhoneNull");
        }

    }

    /**
     * 组装用户中心数据
     *
     * @param truckCustomer
     * @return
     */
    private EcoUser buildUserInfoForEnterprise(TruckCustomer truckCustomer, CustomerInfo customerInfo,
                                               LoginUser loginUser) {
        EcoUserAuthInfo ecoUserAuthInfo = new EcoUserAuthInfo();
        User user = userService.loadUser(User.UserUniqueAttribute.mobileNumber, truckCustomer.getContactPhone());
        if (user == null) {
            user = new User();
            // 用户基础信息
            user.setMobileNumber(truckCustomer.getContactPhone());
            user.setName(truckCustomer.getNickname());
        }

        ecoUserAuthInfo.setUser(user);
        // 系统角色
        ecoUserAuthInfo.setAuthKey(Constants.AUTH_KEY_TGM_CUSTOMER);
        ecoUserAuthInfo.setRoleKey(Constants.ROLE_KEY_CARGO_OWNER);

        // 业务区域
        ecoUserAuthInfo.setAreaCode(customerInfo.getAreaCode());
        ecoUserAuthInfo.setRegionCode(customerInfo.getRegionCode());

        EcoUser ecoUser = null;
        try {
            ecoUser = authorityService.authorizationEcoUser(ecoUserAuthInfo, loginUser);
        } catch (BusinessException e) {
            log.warn("用户中心创建货主错误;", e);
            throw e;
        }
        return ecoUser;
    }
}
