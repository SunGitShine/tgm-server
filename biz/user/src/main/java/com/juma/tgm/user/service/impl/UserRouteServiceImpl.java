package com.juma.tgm.user.service.impl;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.user.dao.UserRouteDetailDao;
import com.juma.tgm.user.dao.UserRouteMasterDao;
import com.juma.tgm.user.domain.UserRoute;
import com.juma.tgm.user.domain.UserRouteDetail;
import com.juma.tgm.user.domain.UserRouteMaster;
import com.juma.tgm.user.service.UserRouteService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserRouteServiceImpl implements UserRouteService {

    private final Logger log = LoggerFactory.getLogger(UserRouteServiceImpl.class);
    
    @Resource
    private UserRouteMasterDao userRouteMasterDao; 
    
    @Resource
    private UserRouteDetailDao userRouteDetailDao;
    
    @Override
    public void addRoute(UserRoute userRoute,LoginUser loginUser) throws BusinessException {
        String md5Hex = md5Hex(userRoute);
        if(md5Hex == null || md5Hex.length() == 0) return;
        
        UserRouteMaster example = new UserRouteMaster();
        example.setMd5Digest(md5Hex);
        List<UserRouteMaster> masterList =  userRouteMasterDao.findByExample(example);
        if(!masterList.isEmpty()) {
            log.info("add 线路重复{}.",md5Hex);
            throw new BusinessException("user.route.exist", "user.route.exist");
        }
        userRoute.getDeliverAddress().setTenantId(loginUser.getTenantId());
        addUserRoute(userRoute, md5Hex);
    }

    private void addUserRoute(UserRoute userRoute, String md5Hex) {
        if(userRoute == null || userRoute.getDeliverAddress() == null) return;
        userRoute.getDeliverAddress().setMd5Digest(md5Hex);
        userRouteMasterDao.insert(userRoute.getDeliverAddress());
        if(userRoute.getReceiveAddressList() == null || userRoute.getReceiveAddressList().isEmpty()) return;
        for(UserRouteDetail userRouteDetail : userRoute.getReceiveAddressList()) {
            userRouteDetail.setRouteMasterId(userRoute.getDeliverAddress().getRouteMasterId());
            userRouteDetailDao.insert(userRouteDetail);
        }
    }
    
    private String md5Hex(UserRoute userRoute) {
        StringBuilder buffer = new StringBuilder();
        if(userRoute != null && userRoute.getDeliverAddress() != null) {
            buffer.append(userRoute.getDeliverAddress().getDeliveryAddress());
            buffer.append(userRoute.getDeliverAddress().getDeliveryAddressContactName());
            buffer.append(userRoute.getDeliverAddress().getDeliveryAddressContactPhone());
            buffer.append(userRoute.getDeliverAddress().getUserId());
            buffer.append(userRoute.getDeliverAddress().getTenantId());
        }
        if(userRoute.getReceiveAddressList() != null) {
            for(UserRouteDetail userRouteDetail : userRoute.getReceiveAddressList()) {
                buffer.append(userRouteDetail.getReceiveAddress());
                buffer.append(userRouteDetail.getReceiveAddressContactName());
                buffer.append(userRouteDetail.getReceiveAddressContactPhone());
            }
        }
        return  DigestUtils.md5Hex(buffer.toString());
    }

    @Override
    public void updateRoute(UserRoute userRoute,LoginUser loginUser) throws BusinessException {
        if(userRoute == null) return;
        String md5Hex = md5Hex(userRoute);
        if(md5Hex == null || md5Hex.length() == 0) return;
        
        UserRouteMaster example = new UserRouteMaster();
        example.setMd5Digest(md5Hex);
        List<UserRouteMaster> masterList =  userRouteMasterDao.findByExample(example);
        if(!masterList.isEmpty()) {
            if(masterList.get(0).getRouteMasterId().compareTo(userRoute.getDeliverAddress().getRouteMasterId()) != 0) {
                log.info("update 线路重复{}.",md5Hex);
                throw new BusinessException("user.route.exist", "user.route.exist");
            }
        }
        
        if(userRoute.getDeliverAddress() != null && userRoute.getDeliverAddress().getRouteMasterId() !=null) {
            userRoute.getDeliverAddress().setMd5Digest(md5Hex);
            userRouteMasterDao.update(userRoute.getDeliverAddress());
        }
        if(userRoute.getReceiveAddressList() == null || userRoute.getReceiveAddressList().isEmpty()) return;
        //全删
        userRouteDetailDao.deleteByMasterId(userRoute.getDeliverAddress().getRouteMasterId());
        for(UserRouteDetail userRouteDetail : userRoute.getReceiveAddressList()) {
            if(userRouteDetail != null) {
                userRouteDetail.setRouteMasterId(userRoute.getDeliverAddress().getRouteMasterId());
                userRouteDetailDao.insert(userRouteDetail);
            }
        }
    }

    @Override
    public List<UserRoute> findRoute(LoginUser loginUser) {
        List<UserRoute> result = new ArrayList<UserRoute>();
        UserRouteMaster example = new UserRouteMaster();
        example.setTenantId(loginUser.getTenantId());
        example.setUserId(loginUser.getUserId());
        List<UserRouteMaster> masterList =  userRouteMasterDao.findByExample(example);
        for(UserRouteMaster master : masterList) {
            UserRoute route = new UserRoute();
            route.setDeliverAddress(master);
            
            UserRouteDetail detailExample = new UserRouteDetail();
            detailExample.setRouteMasterId(master.getRouteMasterId());
            List<UserRouteDetail> details = userRouteDetailDao.findByExample(detailExample);
            route.setReceiveAddressList(details);
            
            result.add(route);
        }
        return result;
    }

    @Override
    public void deleteRoute(Integer routeMasterId) {
        UserRouteMaster example = new UserRouteMaster();
        example.setRouteMasterId(routeMasterId);
        if(userRouteMasterDao.findByExample(example).isEmpty()) return;
        UserRouteDetail detailExample = new UserRouteDetail();
        detailExample.setRouteMasterId(routeMasterId);
        List<UserRouteDetail> details = userRouteDetailDao.findByExample(detailExample);
        for(UserRouteDetail detail : details) {
            userRouteDetailDao.delete(detail);
        }
        userRouteMasterDao.delete(example);
        
    }

    @Override
    public UserRoute getUserRoute(Integer routeMasterId) {
        UserRoute result = new UserRoute();
        UserRouteMaster example = new UserRouteMaster();
        example.setRouteMasterId(routeMasterId);
        List<UserRouteMaster> masters = userRouteMasterDao.findByExample(example);
        if(masters.isEmpty()) return null;
        result.setDeliverAddress(masters.get(0));
        UserRouteDetail detailExample = new UserRouteDetail();
        detailExample.setRouteMasterId(routeMasterId);
        List<UserRouteDetail> details = userRouteDetailDao.findByExample(detailExample);
        result.setReceiveAddressList(details);
        return result;
    }

    @Override
    public void deleteRouteDetail(Integer routeDetailId) {
        UserRouteDetail detailExample = new UserRouteDetail();
        detailExample.setRouteDetailId(routeDetailId);
        userRouteDetailDao.delete(detailExample);
    }

    @Override
    public void addRouteForNoException(UserRoute userRoute,LoginUser loginUser) {
        String md5Hex = md5Hex(userRoute);
        if(md5Hex == null || md5Hex.length() == 0) return;
        
        UserRouteMaster example = new UserRouteMaster();
        example.setMd5Digest(md5Hex);
        List<UserRouteMaster> masterList =  userRouteMasterDao.findByExample(example);
        if(!masterList.isEmpty()) {
            log.info("add 线路重复{}.",md5Hex);
            return;
        }
        userRoute.getDeliverAddress().setTenantId(loginUser.getTenantId());
        addUserRoute(userRoute, md5Hex);
    }


    @Override
    public Page<UserRoute> search(PageCondition pageCondition) throws BusinessException {
        Page<UserRoute> pageData = new Page<>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0);
        int count = userRouteMasterDao.searchCount(pageCondition);
        if(count <= 0) return pageData;

        pageData.setTotal(count);

        List<UserRouteMaster> masterList = userRouteMasterDao.search(pageCondition);

        List<UserRoute> result = new ArrayList<UserRoute>();
        for(UserRouteMaster master : masterList) {
            UserRoute route = new UserRoute();
            route.setDeliverAddress(master);

            UserRouteDetail detailExample = new UserRouteDetail();
            detailExample.setRouteMasterId(master.getRouteMasterId());
            List<UserRouteDetail> details = userRouteDetailDao.findByExample(detailExample);
            route.setReceiveAddressList(details);

            result.add(route);
        }
        pageData.setResults(result);

        return pageData;
    }
}
