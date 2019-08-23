package com.juma.tgm.project.vo;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.customer.domain.TruckCustomer;
import com.juma.tgm.project.domain.Project;

/**
 * @ClassName: ProjectVo
 * @Description:
 * @author: liang
 * @date: 2017-09-28 10:58
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class ProjectVoAndInfo implements Serializable {

    private Project project;
    /**
     * 线路信息、价格信息
     */
    private List<RoadMapVo> roadMapVos;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<RoadMapVo> getRoadMapVos() {
        return roadMapVos;
    }

    public void setRoadMapVos(List<RoadMapVo> roadMapVos) {
        this.roadMapVos = roadMapVos;
    }
}
