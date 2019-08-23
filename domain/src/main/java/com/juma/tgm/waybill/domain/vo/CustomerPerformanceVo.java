package com.juma.tgm.waybill.domain.vo;

import java.io.Serializable;

/**
 * @ClassName: CustomerPerformanceVo
 * @Description:
 * @author: liang
 * @date: 2017-08-23 17:00
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class CustomerPerformanceVo implements Serializable{

    /**
     * 本周运费
     */
    private String currentWeekOrderMoney;

    /**
     * kpi
     */
    private String kpi;

    /**
     * 累计欠款
     */
    private String totalDebt;

    /**
     * 本周回款
     */
    private String currentWeekGathering;

    /**
     * 个人运费概览
     */
    private OverallAchieveMent overallAchieveMent;

    public String getCurrentWeekOrderMoney() {
        if(this.overallAchieveMent != null){
            ManagerAchieveMent cWeek = null;
            try {
                cWeek = overallAchieveMent.getWeekData().get(0);
                return cWeek.getOrderMoney().toString();
            } catch (Exception e) {
            }
        }
        return currentWeekOrderMoney;
    }

    public void setCurrentWeekOrderMoney(String currentWeekOrderMoney) {
        this.currentWeekOrderMoney = currentWeekOrderMoney;
    }

    public String getKpi() {
        return kpi;
    }

    public void setKpi(String kpi) {
        this.kpi = kpi;
    }

    public String getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(String totalDebt) {
        this.totalDebt = totalDebt;
    }

    public String getCurrentWeekGathering() {
        return currentWeekGathering;
    }

    public void setCurrentWeekGathering(String currentWeekGathering) {
        this.currentWeekGathering = currentWeekGathering;
    }

    public OverallAchieveMent getOverallAchieveMent() {
        return overallAchieveMent;
    }

    public void setOverallAchieveMent(OverallAchieveMent overallAchieveMent) {
        this.overallAchieveMent = overallAchieveMent;
    }
}
