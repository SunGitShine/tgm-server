package com.juma.tgm.fms.domain.v2.vo;

import java.io.Serializable;

/**
 * @ClassName: CustomerStatisticsQueryVo
 * @Description:
 * @author: liang
 * @date: 2018-06-10 15:25
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class CustomerStatisticsQueryVo implements Serializable {

    private Integer customerId;

    private Integer projectId;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
