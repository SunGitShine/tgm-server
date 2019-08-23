package com.juma.tgm.util;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.user.domain.UserRoute;
import com.juma.tgm.user.domain.UserRouteDetail;
import com.juma.tgm.user.domain.UserRouteMaster;
import com.juma.tgm.user.service.UserRouteService;
import com.juma.tgm.waybill.domain.WaybillDeliveryAddress;
import com.juma.tgm.waybill.domain.WaybillReceiveAddress;
import com.juma.tgm.waybill.service.UserRouteBusinessService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName: UserRouteUtils
 * @Description:
 * @author: liang
 * @date: 2017-07-03 14:36
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
@Component
public class UserRouteUtils implements UserRouteBusinessService {

    private static final Logger log = LoggerFactory.getLogger(UserRouteUtils.class);

    @Resource
    private UserRouteService userRouteService;


    @Override
    public void addUserRouteForManager(List<WaybillDeliveryAddress> deliveryAddress, List<WaybillReceiveAddress> receiveAddress, UserRouteMaster.BusinessBranch type, int truckCustomerId) {
        return ;
    }

    /**
     * 前台用户使用
     * 微信下单时保存常用地址
     * @param deliveryAddress
     * @param receiveAddress
     * @param loginUser
     */
    @Override
    public void addUserRoute(List<WaybillDeliveryAddress> deliveryAddress, List<WaybillReceiveAddress> receiveAddress, UserRouteMaster.BusinessBranch type, LoginUser loginUser){

        try {
            UserRoute route = this.buildUserRoute(deliveryAddress, receiveAddress, type, loginUser);
            userRouteService.addRouteForNoException(route,loginUser);
        } catch (Exception e) {
            log.warn("保存用户常用路线错误", e);
            throw new BusinessException("saveWaybillUserRouteError", "waybill.error.user.route");
        }
    }


    private UserRoute buildUserRoute(List<WaybillDeliveryAddress> deliveryAddress, List<WaybillReceiveAddress> receiveAddress, UserRouteMaster.BusinessBranch type, LoginUser loginUser){

        if(CollectionUtils.isEmpty(deliveryAddress)){
            throw new BusinessException("deliveryAddressNull","errors.paramCanNotNullWithName","取货地");
        }

        if(CollectionUtils.isEmpty(receiveAddress)){
            throw new BusinessException("receiveAddressNull","errors.paramCanNotNullWithName","配送地");
        }

        UserRoute userRoute = new UserRoute();

        userRoute.setDeliverAddress(this.buildUserRouteMaster(deliveryAddress, type, loginUser));

        userRoute.setReceiveAddressList(this.buildUserRouteDetail(receiveAddress, loginUser));

        return userRoute;
    }

    private UserRouteMaster buildUserRouteMaster(List<WaybillDeliveryAddress> deliveryAddress, UserRouteMaster.BusinessBranch type, LoginUser loginUser){

        WaybillDeliveryAddress wda = deliveryAddress.get(0);

        UserRouteMaster userRouteMaster = new UserRouteMaster();
        userRouteMaster.setCity(wda.getCityname());
        userRouteMaster.setRegion(wda.getRegionCode());
        userRouteMaster.setCreateTime(new Date());
        userRouteMaster.setCreateUserId(loginUser.getUserId());
        userRouteMaster.setDeliveryAddress(wda.getAddressDetail());
        userRouteMaster.setDeliveryAddressName(wda.getAddressName());
        userRouteMaster.setDeliveryAddressContactName(wda.getContactName());
        userRouteMaster.setDeliveryAddressContactPhone(wda.getContactPhone());
        userRouteMaster.setLocation(wda.getCoordinates());
        userRouteMaster.setUserId(loginUser.getUserId());
        userRouteMaster.setBusinessBranch(type.getCode());

        return userRouteMaster;
    }

    private List<UserRouteDetail> buildUserRouteDetail(List<WaybillReceiveAddress> receiveAddress, LoginUser loginUser){

        List<UserRouteDetail> details = new ArrayList<>();
        UserRouteDetail urd = null;
        for(WaybillReceiveAddress wra : receiveAddress){
            urd = new UserRouteDetail();
            urd.setLocation(wra.getCoordinates());
            urd.setCreateUserId(loginUser.getUserId());
            urd.setCity(wra.getCityname());
            urd.setCreateTime(new Date());
            urd.setReceiveAddress(wra.getAddressDetail());
            urd.setReceiveAddressName(wra.getAddressName());
            urd.setReceiveAddressContactName(wra.getContactName());
            urd.setReceiveAddressContactPhone(wra.getContactPhone());
            urd.setRegion(wra.getRegionCode());

            details.add(urd);
        }

        return details;

    }



}
