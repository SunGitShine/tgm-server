package com.juma.tgm.tool.domain;

import com.google.common.collect.Maps;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 功能 : 
 *
 * @author : Bruce(刘正航) 14:15 2019-05-16
 */
@Data
@Builder
public class ExecuteWorkflowInfo implements Serializable {

    /**工作流信息说明,方便日志查看**/
    private String workflowDesc;
    /**每一个工作流唯一的Key**/
    private String processDefinitionKey;
    /**实例id**/
    private String processInstanceId;
    /**工作流对应的业务数据唯一标识:比如订单ID,运单ID等**/
    private String businessKey;
    /**业务编码:比如订单编号**/
    private String number;
    /**工作流其他业务相关变量**/
    @Builder.Default
    private Map<String, Object> variables = Maps.newHashMap();

    /**操作原因: 撤销原因/放弃申请原因 等: 预留字段不一定适用**/
    private String reason;

    /**流程类型 重新申请:REAPPLY_OK 放弃申请:REAPPLY_DIS**/
    private String approvalKey;

    /**兼容项目显示处理中状态**/
    private String projectWorkflowId;

}
