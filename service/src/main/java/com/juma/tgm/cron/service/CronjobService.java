package com.juma.tgm.cron.service;

import com.giants.common.exception.BusinessException;
import com.juma.tgm.customerManager.domain.Task4Waybill;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface CronjobService {

    /**
     * @throws
     * @Title: planDeliveryTimeRemind
     * @Description: 用车时间提醒
     * @return: void
     */
    void planDeliveryTimeRemind();

    /**
     * 用车人运输报告-短信
     *
     * @author Libin.Wei
     * @Date 2018年8月13日 下午4:13:25
     */
    void transportReportForTruckCustomerSms();


    /**
     * 定时下单任务
     * <b>注意:</b>请不要添加事务
     */
    void task4Waybill() throws BusinessException;


    /**
     * 迁移固定需求数据
     */
    @Deprecated
    void transformFixedDemandData(Boolean newFlag) throws BusinessException;


    /**
     * 指定迁移数据
     * @param fixedDemandIds
     * @throws BusinessException
     */
    @Deprecated
    void pointTransformFixedDemandData(List<Integer> fixedDemandIds) throws BusinessException;

    /**
     * 定时建单任务收到消息后执行建单
     * @param task4Waybill
     */
    void doTaskAutoCreateWaybill(Task4Waybill task4Waybill);

    void pushTaskReportMsg(Task4Waybill task4Waybill);
    
    /**
     * <p>Title: produceDailyReport</p>  
     * <p>Description: 定时任务-日报生成</p>
     */
    void produceDailyReport();
    
    /**
     * <p>Title: expireDailyReport</p>
     * <p>Description: 定时任务-日报过期</p>
     */
    void expireDailyReport();

    /**
     * <p>Title: confirmeAll</p>
     * <p>Description: 定时任务-日报由部分确认至已确认</p>
     */
    void confirmeAll();

    /**
     * 定时任务-逻辑删除无运单日报
     * @param dailyDate 选择删除的日期，不传默认为当前时间前一天
     */
    void cleanInvalidDaily(Date dailyDate);

}
