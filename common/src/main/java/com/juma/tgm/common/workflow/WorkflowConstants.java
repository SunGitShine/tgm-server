package com.juma.tgm.common.workflow;

/**
 * 工作流相关常量
 * 功能 : 
 * 1.业务唯一标识
 * 2.工作流事件名称
 * @author : Bruce(刘正航) 15:30 2019-05-16
 */
public class WorkflowConstants {
    /**应付(承运商):调整单申请流程Key**/
    public static final String PROCESS_KEY_ADJUST_PAYABLE_CREATE = "adjust_payable_create";
    /**应付-对账前(承运商):调整单申请流程Key**/
    public static final String PROCESS_KEY_ADJUST_BEFORE_PAYABLE_CREATE = "adjust_before_payable_create";
    /**应收(客户):调整单申请流程Key**/
    public static final String PROCESS_KEY_ADJUST_RECEIVE_CREATE = "adjust_receive_create";
    /**应收-对账前(客户):调整单申请流程Key**/
    public static final String PROCESS_KEY_ADJUST_BEFORE_RECEIVE_CREATE = "adjust_before_receive_create";
    /**调整单-客户/承运侧-对账前/对账后**/
    public static final String PROCESS_KEY_ADJUST_CREATE = "adjust_create";
    /**应收对账单流程Key**/
    public static final String PROCESS_KEY_RECONCILICATION_RECEIVABLE = "reconcilication_receivable";
    /**应付对账单流程Key**/
    public static final String PROCESS_KEY_RECONCILICATION_PAYABLE = "reconcilication_payable";
    /**项目流程开始Key**/
    public static final String PROCESS_KEY_PROJECT_START = "project_start";
    /**项目流程暂停Key**/
    public static final String PROCESS_KEY_PROJECT_PAUSE = "project_pause";
    /**项目流程结束Key**/
    public static final String PROCESS_KEY_PROJECT_END = "project_end";
    /**项目流程恢复Key**/
    public static final String PROCESS_KEY_PROJECT_RECOVER = "project_recover";

}
