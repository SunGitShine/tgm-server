package com.juma.tgm.customerManager.domain.vo.taskWaybill;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import com.juma.tgm.customerManager.domain.Task4Waybill;

/**
 * @ClassName: Task4WaybillQueryVo
 * @Description:
 * @author: liang
 * @date: 2018-10-08 17:08
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class Task4WaybillQueryVo extends Task4Waybill implements Serializable {

    //客户名称
//    private String customerName;
//    //用车人
//    private String truckCustomerName;
//    //项目名称
//    private String projectName;
    //今日任务
    private Boolean todayTask;

    private String key;

    private List<Integer> projectIds;


    public Boolean getTodayTask() {
        return todayTask;
    }

    public void setTodayTask(Boolean todayTask) {
        this.todayTask = todayTask;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Integer> getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(List<Integer> projectIds) {
        this.projectIds = projectIds;
    }

    /**
     * 获取daycode
     *
     * @return
     */
    public String getDayCode() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        int dayCode = calendar.get(Calendar.DAY_OF_WEEK);
        String day = TaskWaybillTemplateEnum.dayOfWeek.get(dayCode);
        return day;
    }
}
