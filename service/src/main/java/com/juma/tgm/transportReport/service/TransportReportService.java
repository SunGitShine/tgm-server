package com.juma.tgm.transportReport.service;

import com.juma.tgm.transportReport.domain.TransportReport;

/**
 * @ClassName TransportReportService.java
 * @Description 运输报告
 * @author Libin.Wei
 * @Date 2018年8月15日 下午3:38:27
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface TransportReportService {

    /**
     * 
     * 运输报告-分租户
     * 
     * @author Libin.Wei
     * @Date 2018年8月15日 下午3:57:24
     * @param projectId
     * @param tenantId
     * @param startTime
     * @param endTime
     * @param pageNo
     * @param pageSize
     * @return
     */
    TransportReport loadTransportReport(Integer projectId, Integer tenantId, String startTime, String endTime,
            Integer pageNo, Integer pageSize);
}
