package com.juma.tgm.customerManager.domain.vo.taskWaybill;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.customerManager.domain.Task4Waybill;
import com.juma.tgm.customerManager.domain.Task4WaybillReport;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplate;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.project.domain.Project;

/**
 * @ClassName: Task4WaybillListVo
 * @Description:
 * @author: liang
 * @date: 2018-09-28 16:16
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class Task4WaybillListVo implements Serializable {

    /**
     * 项目
     */
    @JSONField(serialize = false)
    private Project project;
    /**
     * 司机
     */
    @JSONField(serialize = false)
    private List<Driver> drivers = new ArrayList<Driver>();
    /**
     * 模板
     */
    @JSONField(serialize = false)
    private TaskWaybillTemplate taskWaybillTemplate;
    /**
     * 任务信息
     */
    @JSONField(serialize = false)
    private Task4Waybill task4Waybill;

    /**
     * 最后一次执行结果
     */
    @JSONField(serialize = false)
    private Task4WaybillReport lastTask4WaybillReport;

    /**
     * 返回任务id
     *
     * @return
     */
    public Integer getTask4WaybillId() {
        if (this.task4Waybill == null) return null;

        return this.task4Waybill.getTaskId();
    }


    public Integer getTaskWaybillTemplateId() {
        if (this.taskWaybillTemplate == null) return null;

        return this.taskWaybillTemplate.getTaskWaybillTemplateId();
    }

    public String getProjectName() {
        if (this.project == null) return null;
        return this.project.getName();
    }

    public String getDriverName() {
        String none = "暂无固定司机";
        if(drivers == null || drivers.isEmpty()) return none;
        List<String> driverNames = new ArrayList<String>();
        for (Driver driver : this.drivers) {
            if(driver == null || driver.getNickname() == null || driver.getNickname().trim().length() == 0) continue;
            driverNames.add(driver.getNickname());
        }
        return StringUtils.join(driverNames, "|");
        /*StringBuilder stringBuilder = new StringBuilder();
        for (Driver driver : this.drivers) {
            if (driver != null && StringUtils.isNotEmpty(driver.getNickname())) {
                stringBuilder.append(driver.getNickname());
                stringBuilder.append("|");
            }
        }
        String rawStr = stringBuilder.toString();
        if (StringUtils.isBlank(rawStr)) return none;

        return StringUtils.removeEnd(rawStr, "|");*/
    }

    public String getDeliveryTimePoint() {
        if (this.taskWaybillTemplate == null) return null;

        return this.taskWaybillTemplate.getDeliveryTimePoint();
    }

    public String getNextExecuteTime() {
        return this.judgeNextExecuteTime();
    }

    /**
     * 处理下次执行时间
     *
     * @return
     */
    private String judgeNextExecuteTime() {

        //已执行时间点 17:05
        Calendar flagTime = Calendar.getInstance();
        flagTime.set(Calendar.HOUR_OF_DAY, 17);
        flagTime.set(Calendar.MINUTE, 5);
        flagTime.set(Calendar.SECOND, 0);
        Date flagTimePoint = flagTime.getTime();

        String noSetting = "没有设置执行周期";
        String noPlan = "无，未开始";
        String timePoint = " 17:00左右";
        String tomorrow = "明天 " + timePoint;
        String todayStr = "今日 " + timePoint;
        String failStr = "（上次执行失败）";
        boolean failFlag = false;

        //是否已执行
        boolean alreadyRunFlag = false;

        //上次失败
        if (this.lastTask4WaybillReport != null) {
            if (NumberUtils.compare(this.lastTask4WaybillReport.getTaskStatus(), TaskWaybillTemplateEnum.TaskStatusEnum.TASK_STATUS_FAIL.getCode()) == 0) {
                failFlag = true;
            }
        }
        if (this.task4Waybill == null) return "无，已结束";

        if (StringUtils.isEmpty(task4Waybill.getTaskWeekDays())) return noSetting;

        String[] availableDays = StringUtils.split(task4Waybill.getTaskWeekDays(), ",");

        if (ArrayUtils.isEmpty(availableDays)) return noSetting;
        Arrays.sort(availableDays);

        Date now = new Date();
        if (flagTimePoint.compareTo(new Date()) < 0) {
            now = DateUtils.addDays(now, 1);
            alreadyRunFlag = true;
        }
        //超过最大结束时间
        if (DateUtils.truncatedCompareTo(this.task4Waybill.getTaskEndDate(), DateUtils.addDays(now, 1), Calendar.DATE) < 0) {
            return "无，已结束";
        }
        //执行时间未到
        if (DateUtils.truncatedCompareTo(this.task4Waybill.getTaskStartDate(), DateUtils.addDays(now, 1), Calendar.DATE) > 0) {
            //明天执行
            if (DateUtils.truncatedCompareTo(DateUtil.addDays(this.task4Waybill.getTaskStartDate(), -1), now, Calendar.DATE) == 0) {
                //周期检查
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, 1);
                int dayCode = calendar.get(Calendar.DAY_OF_WEEK);
                String day = TaskWaybillTemplateEnum.dayOfWeek.get(dayCode);
                if (Arrays.binarySearch(availableDays, day) >= 0) {
                    return failFlag == true ? tomorrow + failStr : tomorrow;
                }

            } else {

            }
        }

        //在时间范围内
        //具体日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        //七天之内至少应该有一个
        int i = 0;
        if (alreadyRunFlag) {
            i = 1;
        }
        int gap = this.getGapOfDate(task4Waybill.getTaskStartDate(), task4Waybill.getTaskEndDate());
        for (; i < gap; i++) {
            calendar.add(Calendar.DATE, 1);//加一代表用车时间

            int dayCode = calendar.get(Calendar.DAY_OF_WEEK);
            String day = TaskWaybillTemplateEnum.dayOfWeek.get(dayCode);
            //daycode在周期中被包含且当前
            if (Arrays.binarySearch(availableDays, day) >= 0) {
                Calendar tmpNow = Calendar.getInstance();
                tmpNow.setTime(now);
                int tmpDayCode = tmpNow.get(Calendar.DAY_OF_WEEK);
//                String tmpDay = TaskWaybillTemplateEnum.dayOfWeek.get(tmpDayCode);

                if ((i == 0) && (dayCode - tmpDayCode == 1)) {
                    //今天
                    return failFlag == true ? todayStr + failStr : todayStr;
                } else if ((i == 1) && (dayCode - tmpDayCode == 1)) {
                    //明天
                    return failFlag == true ? tomorrow + failStr : tomorrow;
                }
                Date executeDate = DateUtils.addDays(calendar.getTime(), -1);
                SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
                String dateStr = YYYYMMDD.format(executeDate) + timePoint;

                return failFlag == true ? dateStr + failStr : dateStr;
            }

        }

        return "无";
    }

    /**
     * 计算间隔
     * @param start
     * @param end
     * @return
     */
    private int getGapOfDate(Date start, Date end) {
        if (start == null) return 0;

        if (end == null) return 0;

        Calendar startCal = Calendar.getInstance();
        startCal.setTime(start);
        int startDays= startCal.get(Calendar.DAY_OF_YEAR);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(end);
        int endDays = endCal.get(Calendar.DAY_OF_YEAR);

        int gap = endDays - startDays;

        if(gap < 0) return 0;

        return gap;


    }

    /**
     * 最后一次执行状态
     *
     * @return
     */
    public Byte getTaskExecuteStatus() {
        if (this.lastTask4WaybillReport == null) return null;

        return this.lastTask4WaybillReport.getTaskStatus();
    }


    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public TaskWaybillTemplate getTaskWaybillTemplate() {
        return taskWaybillTemplate;
    }

    public void setTaskWaybillTemplate(TaskWaybillTemplate taskWaybillTemplate) {
        this.taskWaybillTemplate = taskWaybillTemplate;
    }

    public Task4Waybill getTask4Waybill() {
        return task4Waybill;
    }

    public void setTask4Waybill(Task4Waybill task4Waybill) {
        this.task4Waybill = task4Waybill;
    }

    public Task4WaybillReport getLastTask4WaybillReport() {
        return lastTask4WaybillReport;
    }

    public void setLastTask4WaybillReport(Task4WaybillReport lastTask4WaybillReport) {
        this.lastTask4WaybillReport = lastTask4WaybillReport;
    }

    public static void main(String[] args) {
        Task4WaybillListVo waybillListVo = new Task4WaybillListVo();

        Driver d = new Driver();
        d.setNickname("A");
        Driver d2 = new Driver();
        d2.setNickname("B");
        waybillListVo.getDrivers().add(d);
        waybillListVo.getDrivers().add(d2);
        
        System.out.println(JSON.toJSONString(waybillListVo));
        
        Task4Waybill task4Waybill = new Task4Waybill();
        Date startDate = DateUtils.addDays(new Date(), -1);
        Date endDate = DateUtils.addDays(new Date(), 3);

        task4Waybill.setTaskStartDate(startDate);
        task4Waybill.setTaskEndDate(endDate);
        task4Waybill.setTaskWeekDays("tue,fri");

        waybillListVo.setTask4Waybill(task4Waybill);

        waybillListVo.getNextExecuteTime();
    }
}
