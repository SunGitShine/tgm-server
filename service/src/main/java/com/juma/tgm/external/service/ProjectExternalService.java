package com.juma.tgm.external.service;

import com.juma.tgm.project.domain.Project;
import com.juma.tgm.project.domain.v2.ProjectDepot;

import java.util.List;

/**
 * @ClassName ProjectExternalService.java
 * @Description 项目对外类
 * @author Libin.Wei
 * @Date 2018年10月24日 下午3:14:18
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface ProjectExternalService {

    /**
     * 
     * 已废弃-----根据项目ID获取项目基础信息
     * 
     * @author Libin.Wei
     * @Date 2018年10月24日 下午3:18:41
     * @param projectId
     *            项目ID
     * @return Project，查询不到时返回null
     */
    @Deprecated
    Project getProject(Integer projectId);

    /**
     * @apiDefine ProjectSuccess
     * @apiSuccess (成功结果参数) {Integer} projectId 项目id
     * @apiSuccess (成功结果参数) {String} projectNo 项目编号
     * @apiSuccess (成功结果参数) {Integer} projectType 项目类型 1试运行 2正式运行
     * @apiSuccess (成功结果参数) {Integer} projectStatus 项目状态 1未启动 2运行中 3已暂停 4已结束
     * @apiSuccess (成功结果参数) {Integer} payToCompany 运营主体
     * @apiSuccess (成功结果参数) {String} payToCompanyCreditCode 运作方统一社会信用代码
     * @apiSuccess (成功结果参数) {String} contractNo 合同编号
     * @apiSuccess (成功结果参数) {Integer} contractToCompany 签约主体
     * @apiSuccess (成功结果参数) {String} contractToCompanyCreditCode 签约方统一社会信用代码
     * @apiSuccess (成功结果参数) {Integer} projectManagerUserId 项目经理
     * @apiSuccess (成功结果参数) {String} tryWorkPassAttachment 试运行项目评估表
     * @apiSuccess (成功结果参数) {String} tryWorkProtocol 试运行协议
     * @apiSuccess (成功结果参数) {Date} projectStartDate 项目开始日期
     * @apiSuccess (成功结果参数) {Date} projectEndDate 项目开始日期
     * @apiSuccess (成功结果参数) {Boolean} isRunning 是否开跑
     * @apiSuccess (成功结果参数) {Date} runningTime 开跑时间
     * @apiSuccess (成功结果参数) {String} businessLinkman 业务对接人
     * @apiSuccess (成功结果参数) {String} businessLinktel 业务对接人电话
     * @apiSuccess (成功结果参数) {String} businessLinkemail 业务对接人邮箱
     * @apiSuccess (成功结果参数) {String} financeLinkman 财务对接人
     * @apiSuccess (成功结果参数) {String} financeLinktel 财务对接人电话
     * @apiSuccess (成功结果参数) {String} financeLinkemail 财务对接人邮箱
     * @apiSuccess (成功结果参数) {String} deliveryAddressType 配送地类型
     * @apiSuccess (成功结果参数) {Integer} tenantId 租户id
     * @apiSuccess (成功结果参数) {BigDecimal} rebateRate 返点率
     * @apiSuccess (成功结果参数) {Integer} estimateTimeConsumption 预估耗时
     * @apiSuccess (成功结果参数) {String} truckRequireRemark 备注
     * @apiSuccess (成功结果参数) {String} additionalFunctionIds 用车要求
     * @apiSuccess (成功结果参数) {String} tenantCode 租户code
     * @apiSuccess (成功结果参数) {String} areaCode 业务区域
     * @apiSuccess (成功结果参数) {String} name 项目名称
     * @apiSuccess (成功结果参数) {String} logisticsLabel 物流产品标签，来源CRM
     * @apiSuccess (成功结果参数) {Byte} type 项目类型   默认分公司业务  0 分公司 1专车
     * @apiSuccess (成功结果参数) {Integer} customerId 客户id
     * @apiSuccess (成功结果参数) {String} customerName 客户名称
     * @apiSuccess (成功结果参数) {String} goodsType 货物类型
     * @apiSuccess (成功结果参数) {BigDecimal} taxRateValue 税率
     * @apiSuccess (成功结果参数) {Boolean} isReceivableFirst 是否优先应收对账
     * @apiSuccess (成功结果参数) {Boolean} isEnable 是否可用
     * @apiSuccess (成功结果参数) {Byte} onlyLoadCargo 隔天配送
     * @apiSuccess (成功结果参数) {Byte} needDeliveryPointNote 上传配送单
     * @apiSuccess (成功结果参数) {Date} createTime 创建时间
     * @apiSuccess (成功结果参数) {Integer} createUserId 创建人
     * @apiSuccess (成功结果参数) {Boolean} isReceiveDailySms 是否接收短信
     * @apiSuccess (成功结果参数) {BigDecimal} profitRate 承诺毛利率
     */

    /**
     * @api com.juma.tgm.external.service.ProjectExternalService.getProjectV2 根据项目ID获取项目基础信息
     * @apiDescription 根据项目ID获取项目基础信息
     * @apiVersion 1.0.0
     * @apiName getProjectV2
     * @apiGroup 项目查询
     * @apiParam (接口参数) {Integer} projectId 项目id(必填)
     * @apiUse ProjectSuccess
     */
    com.juma.tgm.project.domain.v2.Project getProjectV2(Integer projectId);

    /**
     * 项目仓库
     * @param projectId
     * @return
     */
    List<ProjectDepot> listProjectDepotByProject(Integer projectId);
}
