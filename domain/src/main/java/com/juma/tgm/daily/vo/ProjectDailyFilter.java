package com.juma.tgm.daily.vo;

import com.juma.tgm.daily.domain.ProjectDaily;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ProjectDailyFilter
 * @Description 日报查询类
 * @Author weilibin
 * @Date 2019-07-18 20:02
 * @Version 1.0.0
 */

public class ProjectDailyFilter extends ProjectDaily {

    // 业务区域
    private List<String> areaCodeList;
    // 日报开始时间：YYYY-MM-DD
    private Date startProjectDailyDate;
    // 日报结束时间：YYYY-MM-DD
    private Date endProjectDailyDate;

    private List<Date> projectDailyDateList;

    public List<String> getAreaCodeList() {
        return areaCodeList;
    }

    public void setAreaCodeList(List<String> areaCodeList) {
        this.areaCodeList = areaCodeList;
    }

    public Date getStartProjectDailyDate() {
        return startProjectDailyDate;
    }

    public void setStartProjectDailyDate(Date startProjectDailyDate) {
        this.startProjectDailyDate = startProjectDailyDate;
    }

    public Date getEndProjectDailyDate() {
        return endProjectDailyDate;
    }

    public void setEndProjectDailyDate(Date endProjectDailyDate) {
        this.endProjectDailyDate = endProjectDailyDate;
    }


    public List<Date> getProjectDailyDateList() {
        return projectDailyDateList;
    }

    public void setProjectDailyDateList(List<Date> projectDailyDateList) {
        this.projectDailyDateList = projectDailyDateList;
    }
}
