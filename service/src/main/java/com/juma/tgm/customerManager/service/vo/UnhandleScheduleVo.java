package com.juma.tgm.customerManager.service.vo;

import com.juma.tgm.customerManager.domain.ManagerSchedule;

import java.io.Serializable;

/**
 * @ClassName: UnhandleScheduleVo
 * @Description:
 * @author: liang
 * @date: 2017-06-15 18:16
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class UnhandleScheduleVo extends ManagerSchedule implements Serializable {

    private String TypeName;

    private String viewDate;

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public String getViewDate() {
        return viewDate;
    }

    public void setViewDate(String viewDate) {
        this.viewDate = viewDate;
    }
}
