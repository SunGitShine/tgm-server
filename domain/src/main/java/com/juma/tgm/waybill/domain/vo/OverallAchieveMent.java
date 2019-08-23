package com.juma.tgm.waybill.domain.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/8/23 0023.
 */
public class OverallAchieveMent implements Serializable {
    private static final long serialVersionUID = -4727999790060420719L;
    private List<ManagerAchieveMent> weekData;
    private List<ManagerAchieveMent> monthData;

    public List<ManagerAchieveMent> getWeekData() {
        return weekData;
    }

    public void setWeekData(List<ManagerAchieveMent> weekData) {
        this.weekData = weekData;
    }

    public List<ManagerAchieveMent> getMonthData() {
        return monthData;
    }

    public void setMonthData(List<ManagerAchieveMent> monthData) {
        this.monthData = monthData;
    }
}
