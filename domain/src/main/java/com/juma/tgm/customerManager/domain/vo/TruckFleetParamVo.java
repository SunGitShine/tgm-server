package com.juma.tgm.customerManager.domain.vo;

import java.io.Serializable;

/**
 * @ClassName: TruckFleetVo
 * @Description:
 * @author: liang
 * @date: 2018-03-28 16:23
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class TruckFleetParamVo implements Serializable {
    /**
     * 车型id
     */
    private Integer truckTypeId;

    /**
     * 项目id
     */
    private Integer projectId;
    
    private Integer pageNo = 1;
    
    private Integer pageSize = 1000;

    public Integer getTruckTypeId() {
        return truckTypeId;
    }

    public void setTruckTypeId(Integer truckTypeId) {
        this.truckTypeId = truckTypeId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    
}
