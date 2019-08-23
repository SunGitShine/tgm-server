package com.juma.tgm.project.vo;

import com.juma.tgm.project.domain.Project;

import java.io.Serializable;

/**
 * 项目查询vo
 *
 * @ClassName: ProjectQueryVo
 * @Description:
 * @author: liang
 * @date: 2018-08-20 15:14
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class ProjectQueryVo extends Project implements Serializable {

    /**
     * crmCustomerId
     */
    private Integer crmCustomerId;

    public Integer getCrmCustomerId() {
        return crmCustomerId;
    }

    public void setCrmCustomerId(Integer crmCustomerId) {
        this.crmCustomerId = crmCustomerId;
    }
}
