package com.juma.tgm.waybill.service;

public interface WaybillCheckService {

    /**
     * 
     * 判断是不是按工作量
     * 
     * @author Libin.Wei
     * @Date 2018年12月6日 下午4:36:09
     * @param waybillId
     * @return
     */
    boolean checkProjectIsWorkload(Integer waybillId);

    /**
     * 
     * 判断是不是司机填写工作量
     * 
     * @author Libin.Wei
     * @Date 2018年12月6日 下午4:36:21
     * @param waybillId
     * @return
     */
    boolean checkIsDriverWriteWork(Integer waybillId);

    /**
     * 获取由谁填写工作量
     *
     * @param waybillId
     * @return
     */
    int loadWhoWriteWork(Integer waybillId);
}
