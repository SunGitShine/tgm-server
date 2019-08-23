package com.juma.tgm.external.service;

import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.truck.enumeration.QueryTypeEnum;
import com.juma.tgm.truck.external.TruckTypeExternalFilter;
import com.juma.tgm.truck.external.TruckTypeExternalVo;
import java.util.List;

/**
 * @ClassName TruckTypeExternalService
 * @Description TODO
 * @Author weilibin
 * @Date 2019-07-09 09:39
 * @Version 1.0.0
 */

public interface TruckTypeExternalService {


    /**
     * 根据条件获取车型、厢型、厢长：司机平台化使用
     *
     * @param truckTypeExternalFilter 非必填
     * @param queryTypeEnum 查询类型，必填
     * @param loginUser 必填
     *
     * @return
     */
    List<TruckTypeExternalVo> listVehicleBoxTypeOrLengthByFilter(TruckTypeExternalFilter truckTypeExternalFilter,
        QueryTypeEnum queryTypeEnum, LoginUser loginUser);
}
