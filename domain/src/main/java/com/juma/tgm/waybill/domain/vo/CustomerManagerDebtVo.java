package com.juma.tgm.waybill.domain.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: CustomerManagerDebtVo
 * @Description:
 * @author: liang
 * @date: 2017-08-22 15:23
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class CustomerManagerDebtVo implements Serializable {

    /**
     * 欠款定义：已完成，已对账未结算的税前费用
     *
     * 回款定义：已结算的税前费用
     */



    //总欠款
    private String totalDebt;

    //本周回款
    private String currentWeekGathering;

    //超期欠款
    private String overdueDebt;

    //分段超期欠款
    private List<CustomerManagerDebtOverviewVo> separationDebt;


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

    public String getOverdueDebt() {
        return overdueDebt;
    }

    public void setOverdueDebt(String overdueDebt) {
        this.overdueDebt = overdueDebt;
    }

    public List<CustomerManagerDebtOverviewVo> getSeparationDebt() {
        return separationDebt;
    }

    public void setSeparationDebt(List<CustomerManagerDebtOverviewVo> separationDebt) {
        this.separationDebt = separationDebt;
    }
}
