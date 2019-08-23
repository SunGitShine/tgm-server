package com.juma.tgm.user.service;

import java.util.List;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.user.domain.UserRoute;

public interface UserRouteService {

    UserRoute getUserRoute(Integer masterId);
    
    void addRouteForNoException(UserRoute userRoute,LoginUser loginUser);
    
    void addRoute(UserRoute userRoute,LoginUser loginUser) throws BusinessException;
    
    void updateRoute(UserRoute userRoute,LoginUser loginUser) throws BusinessException;
    
    void deleteRoute(Integer masterId);
    
    void deleteRouteDetail(Integer detailId);
    
    List<UserRoute> findRoute(LoginUser loginUser);

    /**
     * 分页获取常用线路
     * @param pageCondition
     * @return
     * @throws BusinessException
     */
    Page<UserRoute> search(PageCondition pageCondition) throws BusinessException;
}
