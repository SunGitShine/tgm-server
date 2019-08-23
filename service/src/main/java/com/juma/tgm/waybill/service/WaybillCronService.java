package com.juma.tgm.waybill.service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName WaybillCronService.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年10月16日 下午6:27:24
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface WaybillCronService {

    /**
     * 
     * 重新计算昨天已完成运单的实际里程
     * 
     * @author Libin.Wei
     * @Date 2017年10月16日 下午6:28:42
     */
    void cronUpdateYesActualMileage(Date startTime, Date endTime);

    /**
     * 
     * 超期强制结束运单
     * 
     * @author Libin.Wei
     * @Date 2017年12月4日 下午4:29:15
     */
    void cronConstraintFinishWaybill();

    /**每天0点刷新前2天运单-运费确认状态**/
    void cronUpdateWaybillAmountStatus(List<Integer> waybillIds,Integer gap);
}
