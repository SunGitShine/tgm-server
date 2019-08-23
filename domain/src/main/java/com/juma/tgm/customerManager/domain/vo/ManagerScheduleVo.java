package com.juma.tgm.customerManager.domain.vo;

import com.juma.tgm.common.ManagerScheduleType;
import com.juma.tgm.customerManager.domain.ManagerSchedule;

/**
 * @ClassName: ManagerScheduleVo
 * @Description:
 * @author: liang
 * @date: 2017-06-16 14:13
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class ManagerScheduleVo extends ManagerSchedule {

    private Integer projectId;

    public String getTypeName() {
        return ManagerScheduleType.getTypeName(this.getType());
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

//    public String getDayAilias() {
//        if(this.getCreateTime() == null){
//            return "";
//        }
//        //今天
//        Date today = new Date();
//        if(DateUtils.truncatedCompareTo(today, this.getCreateTime(), Calendar.DATE) == 0) {
//            return "今天";
//        }
//        //昨天
//        Date yesterday = DateUtils.addDays(today, -1);
//        if(DateUtils.truncatedCompareTo(yesterday, this.getCreateTime(), Calendar.DATE) == 0){
//            return "昨天";
//        }
//        //日期
//       return DateFormatUtils.format(this.getCreateTime(), "MM-dd mm:ss");
//    }

}
