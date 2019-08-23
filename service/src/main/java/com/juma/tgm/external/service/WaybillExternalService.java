package com.juma.tgm.external.service;

import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateApplication;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateType;
import com.juma.tgm.waybill.external.WaybillExternalVo;
import com.juma.tgm.waybill.external.WaybillOperateTrackExternalVo;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.WaybillDeliveryAddress;
import com.juma.tgm.waybill.domain.WaybillReceiveAddress;
import java.math.BigDecimal;
import java.util.List;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.external.domain.WaybillExternalExample;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.vo.WaybillDeliveryAddressVo;
import com.juma.tgm.waybill.vo.WaybillToOmsOrder;

/**
 * @ClassName WaybillExternalService.java
 * @Description 运单对外接口
 * @author Libin.Wei
 * @Date 2018年7月20日 上午10:52:05
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface WaybillExternalService {


    /**调用方 OMS
     * 更新运单的配送地址
     * @param waybillId
     * @param waybillDeliveryAddressList
     * @param loginUser
     */
    void updateWaybillDeliveryAddress(Integer waybillId, List<WaybillDeliveryAddressVo> waybillDeliveryAddressList, LoginUser loginUser) throws BusinessException;


    /**调用方 OMS
     * 回单图片地址
     * @param waybillId
     * @return
     */
    List<String> receiptImgUrls(Integer waybillId);

    /**
     * 提供给OMS接口
     * @param waybillNo
     * @return
     */
    WaybillToOmsOrder loadWaybillForOms(String waybillNo);

    /**
     * 根据运单主键获取运单信息
     *
     * @param waybillId
     * @return
     */
    WaybillExternalVo loadWaybillByWaybillId(Integer waybillId);

    /**
     * @apiDefine WaybillSuccess
     * @apiSuccess (成功结果参数) {Integer} departmentId 签约方
     * @apiSuccess (成功结果参数) {String } contractToCompanyCreditCode 签约方统一社会信用代码
     * @apiSuccess (成功结果参数) {String } reconciliationNo 对帐单号
     * @apiSuccess (成功结果参数) {Integer} receiveWay 派车方式
     * @apiSuccess (成功结果参数) {Integer} waybillId 运单id
     * @apiSuccess (成功结果参数) {Integer} truckCustomerId 用车人id
     * @apiSuccess (成功结果参数) {Integer} consignorAccountId 货主帐号id
     * @apiSuccess (成功结果参数) {String } truckCustomerName 用车人姓名
     * @apiSuccess (成功结果参数) {Integer} truckId 货车id
     * @apiSuccess (成功结果参数) {String } plateNumber 货车车牌号
     * @apiSuccess (成功结果参数) {Integer} driverId 司机id
     * @apiSuccess (成功结果参数) {String } driverName 司机姓名
     * @apiSuccess (成功结果参数) {Integer} driverType 司机类型
     * @apiSuccess (成功结果参数) {Integer} customerId 企业客户id
     * @apiSuccess (成功结果参数) {Integer} projectId 项目 Id
     * @apiSuccess (成功结果参数) {String } projectName 项目名称
     * @apiSuccess (成功结果参数) {Integer} businessBranch 业务分支 0 专车 1 分公司
     * @apiSuccess (成功结果参数) {String } waybillNo 运单编号
     * @apiSuccess (成功结果参数) {Integer} status 运单业务状态
     * @apiSuccess (成功结果参数) {String } regionCode 取货地地区编码
     * @apiSuccess (成功结果参数) {Integer} statusView 运单展示状态
     * @apiSuccess (成功结果参数) {Integer} estimateDistance 预估距离
     * @apiSuccess (成功结果参数) {Integer} estimateTimeConsumption 预估耗时
     * @apiSuccess (成功结果参数) {BigDecimal} estimateFreight 税前费用
     * @apiSuccess (成功结果参数) {BigDecimal} calculatedFreight 成本价：系统报价
     * @apiSuccess (成功结果参数) {BigDecimal} rebateRate 返点率
     * @apiSuccess (成功结果参数) {BigDecimal} afterTaxFreight 税后运费
     * @apiSuccess (成功结果参数) {BigDecimal} show4DriverFreight 司机结算价
     * @apiSuccess (成功结果参数) {Date } planDeliveryTime 计划配送时间
     * @apiSuccess (成功结果参数) {Date } deliveryTime 开始配送时间(离仓时间)
     * @apiSuccess (成功结果参数) {String } waybillRemark 运单备注(异常运单)
     * @apiSuccess (成功结果参数) {Integer} receiptType 收款方式
     * @apiSuccess (成功结果参数) {Date } receivingTime 接单时间或被指派时间
     * @apiSuccess (成功结果参数) {Date } finishTime  运单配送结束时间
     * @apiSuccess (成功结果参数) {Date } arriveDepotTime 到达取货地时间：离仓时间
     * @apiSuccess (成功结果参数) {Integer} isLate 是否迟到
     * @apiSuccess (成功结果参数) {BigDecimal} compareResult 运单价格对比度
     * @apiSuccess (成功结果参数) {Integer} paymentRoute 本人付款或代付
     * @apiSuccess (成功结果参数) {String } updateFreightRemark 改价备注
     * @apiSuccess (成功结果参数) {String } waybillCancelRemark 订单取消原因
     * @apiSuccess (成功结果参数) {String } assignWaybillRemark 派车备注
     * @apiSuccess (成功结果参数) {Integer} waybillSource 运单来源
     * @apiSuccess (成功结果参数) {String } customerName 企业客户名称
     * @apiSuccess (成功结果参数) {Integer} needReceipt 回单状态
     * @apiSuccess (成功结果参数) {Integer} actualMileage 实际里程
     * @apiSuccess (成功结果参数) {String } waybillUnbundlingReason 人工干预原因
     * @apiSuccess (成功结果参数) {BigDecimal} tolls 高速路费
     * @apiSuccess (成功结果参数) {Integer} isSubmitMatch 自动派车
     * @apiSuccess (成功结果参数) {Integer} customerManagerId 企业客户所属客户经理id
     * @apiSuccess (成功结果参数) {String } customerManagerName 企业客户所属客户经理名字
     * @apiSuccess (成功结果参数) {String } assignCarFeedback 派车反馈
     * @apiSuccess (成功结果参数) {Integer} cancelChannel 取消渠道
     * @apiSuccess (成功结果参数) {Integer} entryLicense 入城证
     * @apiSuccess (成功结果参数) {Integer} goodsWeight 货物重量
     * @apiSuccess (成功结果参数) {BigDecimal} goodsVolume 货物体积
     * @apiSuccess (成功结果参数) {Integer} vehicleBoxType 箱型
     * @apiSuccess (成功结果参数) {Integer} isChangeDeliveryPoint 修改路线状态
     * @apiSuccess (成功结果参数) {String } areaCode 业务区域
     * @apiSuccess (成功结果参数) {Integer}  tenantId 租户Id
     * @apiSuccess (成功结果参数) {String } tenantCode 租户名字
     * @apiSuccess (成功结果参数) {Integer}  flightUsageId 班次使用记录ID
     * @apiSuccess (成功结果参数) {Boolean}  isTest 是不是测试单子
     * @apiSuccess (成功结果参数) {Date} cmEstimateFinishTimeStr 经济人预计完成时间
     * @apiSuccess (成功结果参数) {Integer} onlyLoadCargo 是否在装货后第二天开始配送
     * @apiSuccess (成功结果参数) {Integer} needDeliveryPointNote 是否需要上传配送单
     * @apiSuccess (成功结果参数) {Integer} updateFreightAuditStatus 修改运单价格审核状态
     * @apiSuccess (成功结果参数) {BigDecimal} freightToBeAudited 待审核运费
     * @apiSuccess (成功结果参数) {String } updateFreightAuditRemark 改价审核备注
     * @apiSuccess (成功结果参数) {Integer} reconciliationStatus 运单对账状态
     * @apiSuccess (成功结果参数) {boolean} isDriverHasRead 司机是否已读
     * @apiSuccess (成功结果参数) {Integer} settlementStatus 结算状态
     * @apiSuccess (成功结果参数) {Integer} receiptStatus 收款状态
     * @apiSuccess (成功结果参数) {Integer} vendorId 承运商id
     * @apiSuccess (成功结果参数) {String } vendorName 承运商名称
     * @apiSuccess (成功结果参数) {Integer}  roadMapId 路线id
     * @apiSuccess (成功结果参数) {String } roadMapName 路线名称
     * @apiSuccess (成功结果参数) {String } receivableReconcilicationNo 应收对账单号
     * @apiSuccess (成功结果参数) {Integer} receivableReconcilicationStatus 应收对账状态
     * @apiSuccess (成功结果参数) {Integer} vehicleType 车辆类型
     * @apiSuccess (成功结果参数) {Integer} vehicleToVendor 车辆所属承运商
     * @apiSuccess (成功结果参数) {Integer} vehicleToVendorName 车辆所属承运商名称
     * @apiSuccess (成功结果参数) {String } waybillSourceText  运单来源
     * @apiSuccess (成功结果参数) {String } customerManagerMobile 企业客户所属客户经理电话
     * @apiSuccess (成功结果参数) {Integer} flightId  班次ID
     * @apiSuccess (成功结果参数) {boolean}  selfWaybill 是否本人运单
     * @apiSuccess (成功结果参数) {String } location 操作坐标
     * @apiSuccess (成功结果参数) {String }  deviceNo 操作设备号
     * @apiSuccess (成功结果参数) {Integer}  deviceType 操作设备类型
     * @apiSuccess (成功结果参数) {BigDecimal} referenceFreight 参考报价
     * @apiSuccess (成功结果参数) {BigDecimal} miniFreight 最低报价
     * @apiSuccess (成功结果参数) {BigDecimal} rebateFee 返点费
     * @apiSuccess (成功结果参数) {Integer} vehicleId AMS系统的车辆ID
     * @apiSuccess (成功结果参数) {Integer} amsDriverId AMS系统的司机ID
     * @apiSuccess (成功结果参数) {String } areaName 业务区域中文名
     * @apiSuccess (成功结果参数) {String } receiptTypeText 结算方式中文名
     * @apiSuccess (成功结果参数) {String } statusViewText 运单展示状态中文名
     * @apiSuccess (成功结果参数) {String } driverTypeName 司机类型中文名
     * @apiSuccess (成功结果参数) {String } needReceiptText 回单状态中文名
     * @apiSuccess (成功结果参数) {String } cancelChannelText 取消渠道中文名
     * @apiSuccess (成功结果参数) {String } valuationConstJson 计价系数
     * @apiSuccess (成功结果参数) {String } valuationWayView 计价方式
     * @apiSuccess (成功结果参数) {String } cmEstimateFinishTimeStr 预估完成时间
     * @apiSuccess (成功结果参数) {boolean} priceExceptionFlag 价格异常条件标记
     * @apiSuccess (成功结果参数) {String } logicAreaCode 运单的业务区域的逻辑业务区域
     * @apiSuccess (成功结果参数) {String } logicAreaCodeName 运单的业务区域的逻辑业务区域名称
     * @apiSuccess (成功结果参数) {String } loadingTime 客户用车时间起计算装货耗时
     * @apiSuccess (成功结果参数) {String } driverArrivedLoadingTime 司机到达时间起计算装货耗时
     * @apiSuccess (成功结果参数) {String } shippingTime 配送耗时
     * @apiSuccess (成功结果参数) {boolean} ownerAreaCanOperate 本业务区域是否可操作
     * @apiSuccess (成功结果参数) {boolean} shareAreaCanOperate 分享业务区域是否可操作
     * @apiSuccess (成功结果参数) {boolean} allowCancel 运单能不能被取消
     * @apiSuccess (成功结果参数) {boolean} allowChangeCar 能不能更改车辆
     * @apiSuccess (成功结果参数) {String } actualMileageErrorStr 实际里程错误信息
     * @apiSuccess (成功结果参数) {String } tenantName 租户名称
     * @apiSuccess (成功结果参数) {boolean} showYourPrice 是否展示价格
     * @apiSuccess (成功结果参数) {boolean} asProjectWaybillHandle 是否作为项目单处理
     * @apiSuccess (成功结果参数) {Integer} whoWriteWork 谁填写工作量 1:司机 2:经济人
     * @apiSuccess (成功结果参数) {boolean} isCompleteWorkload 是否需要完善工作量
     * @apiSuccess (成功结果参数) {String } logisticsLabel 物流产品标签
     * @apiSuccess (成功结果参数) {String } logisticsName 物流产品标签名字
     * @apiSuccess (成功结果参数) {Integer} payToCompany 运营主体
     * @apiSuccess (成功结果参数) {String }  payToCompanyCreditCode 运营主体统一社会信用代码
     * @apiSuccess (成功结果参数) {Integer} projectManagerUserId 项目经理
     * @apiSuccess (成功结果参数) {String } createUserName 创建人名称
     * @apiSuccess (成功结果参数) {String } createUserPhone 创建人电话
     * @apiSuccess (成功结果参数) {String } projectManagerUserName 项目经理名称
     * @apiSuccess (成功结果参数) {String } projectManagerUserPhone 项目经理电话
     * @apiSuccess (成功结果参数) {BigDecimal} lastCustomerFreightWithTax 客户侧最终含税金额
     * @apiSuccess (成功结果参数) {BigDecimal} lastVendorFreightWithTax 承运侧最终含税金额
     */

    /**
     * @apiDefine WaybillDeliveryAddressSuccess
     * @apiSuccess (成功结果参数) {Integer} addressId 地址id
     * @apiSuccess (成功结果参数) {Integer} waybillId 运单id
     * @apiSuccess (成功结果参数) {String } regionCode 地区
     * @apiSuccess (成功结果参数) {String } addressName 地址名称
     * @apiSuccess (成功结果参数) {String } addressDetail 地址详情
     * @apiSuccess (成功结果参数) {String } contactName 联系人
     * @apiSuccess (成功结果参数) {String } contactPhone 联系人电话
     * @apiSuccess (成功结果参数) {String } spareContactPhone 备用电话
     * @apiSuccess (成功结果参数) {String } coordinates 坐标
     * @apiSuccess (成功结果参数) {String } simpleAddress 地址
     * @apiSuccess (成功结果参数) {String } cityname 城市名称
     * @apiSuccess (成功结果参数) {Integer} isArrived 是否到达
     * @apiSuccess (成功结果参数) {Integer} sequence 次序
     * @apiSuccess (成功结果参数) {Integer} fenceId 电子围栏ID
     * @apiSuccess (成功结果参数) {Integer} status 状态
     */

    /**
     * @apiDefine WaybillReceiveAddressSuccess
     * @apiSuccess (成功结果参数) {Integer} addressId 地址id
     * @apiSuccess (成功结果参数) {Integer} waybillId 运单id
     * @apiSuccess (成功结果参数) {String}  regionCode 地区
     * @apiSuccess (成功结果参数) {String}  addressName 地址名称
     * @apiSuccess (成功结果参数) {String}  addressDetail 地址详情
     * @apiSuccess (成功结果参数) {String}  contactName 联系人
     * @apiSuccess (成功结果参数) {String}  contactPhone 联系人电话
     * @apiSuccess (成功结果参数) {String}  coordinates 坐标
     * @apiSuccess (成功结果参数) {String}  simpleAddress 地址
     * @apiSuccess (成功结果参数) {String}  cityname 城市名称
     * @apiSuccess (成功结果参数) {Integer} isArrived 是否到达
     * @apiSuccess (成功结果参数) {Integer} sequence 次序
     * @apiSuccess (成功结果参数) {Integer} fenceId 电子围栏ID
     * @apiSuccess (成功结果参数) {Integer} status 状态
     * @apiSuccess (成功结果参数) {Boolean} isDelete 是否删除
     * @apiSuccess (成功结果参数) {Date}    createTime 创建时间
     * @apiSuccess (成功结果参数) {Integer} createUserId 创建人
     * @apiSuccess (成功结果参数) {Integer} lastUpdateUserId 更新人
     * @apiSuccess (成功结果参数) {Date}    lastUpdateTime 更新时间
     */

    /**
     * @apiDefine TruckRequireSuccess
     * @apiSuccess (成功结果参数) {Integer} truckRequireId 主键id
     * @apiSuccess (成功结果参数) {Integer} waybillId 运单id
     * @apiSuccess (成功结果参数) {Integer} truckTypeId 车辆类型id
     * @apiSuccess (成功结果参数) {String} additionalFunctionIds 用车要求
     * @apiSuccess (成功结果参数) {String} goodsType 货物类型
     * @apiSuccess (成功结果参数) {String} goodsWeight 重量
     * @apiSuccess (成功结果参数) {String} goodsVolume 体积
     * @apiSuccess (成功结果参数) {String} goodsAmount 价格
     * @apiSuccess (成功结果参数) {Integer} isTailBoard 尾板
     * @apiSuccess (成功结果参数) {String} remark 备注
     * @apiSuccess (成功结果参数) {BigDecimal} taxRateValue 税率
     * @apiSuccess (成功结果参数) {Integer} vehicleBoxType 厢型
     * @apiSuccess (成功结果参数) {Integer} taxRateId 税率id
     */

    /**
     * @api com.juma.tgm.external.service.WaybillExternalService.countWaybillByCustomer 根据客户ID获取指定时间范围内的运单数量
     * @apiDescription 根据客户ID获取指定时间范围内的运单数量
     * @apiVersion 1.0.0
     * @apiName countWaybillByCustomer
     * @apiGroup 运单查询
     * @apiParam (接口参数) {Object} WaybillExternalExample
     * @apiParam (接口参数) {Integer} WaybillExternalExample.crmCustomerId 客户ID(必填)
     * @apiParam (接口参数) {Integer} WaybillExternalExample.tenantId 租户ID(必填)
     * @apiParam (接口参数) {Date} WaybillExternalExample.startTime 租户ID(非必填)
     * @apiParam (接口参数) {Date} WaybillExternalExample.endTime 租户ID(非必填)
     * @apiParam (接口参数) {Integer[]} listStatusView 运单的状态列表(非必填)
     */
    int countWaybillByCustomer(WaybillExternalExample example) throws BusinessException;


    /**
     * @api com.juma.tgm.external.service.WaybillExternalService.listWaybillBy 根据条件获取运单列表
     * @apiDescription 根据条件获取运单列表
     * @apiVersion 1.0.0
     * @apiName listWaybillBy
     * @apiGroup 运单查询
     * @apiParam (接口参数) {Object} PageCondition
     * @apiParam (接口参数) {Integer} PageCondition.filters.amsDriverId 司机ID(非必填)
     * @apiParam (接口参数) {Integer} PageCondition.filters.driverMobileNumber 司机手机号(非必填)
     * @apiParam (接口参数) {Integer} PageCondition.filters.crmCustomerId 客户ID(非必填)
     * @apiParam (接口参数) {Integer} PageCondition.filters.startTime 开始时间(非必填)
     * @apiParam (接口参数) {Integer} PageCondition.filters.endTime 结束时间(非必填)
     * @apiParam (接口参数) {Object} loginUser 登陆用户(必填)
     * @apiUse WaybillSuccess
     */
    List<Waybill> listWaybillBy(PageCondition pageCondition, LoginUser loginUser) throws BusinessException;

    /**
     * @api com.juma.tgm.external.service.WaybillExternalService.loadVendorUnReconciliationByPlateNumber 根据车牌号和登录人信息获取当前车辆在当前租户下所绑定的承运商的未对账金额
     * @apiDescription 根据车牌号和登录人信息获取当前车辆在当前租户下所绑定的承运商的未对账金额
     * @apiVersion 1.0.0
     * @apiName loadVendorUnReconciliationByPlateNumber
     * @apiGroup 运单查询
     * @apiParam (接口参数) {String} plateNumber 车牌号(必填)
     * @apiParam (接口参数) {Object} loginUser 登陆用户(必填)
     */
    BigDecimal loadVendorUnReconciliationByPlateNumber(String plateNumber, LoginUser loginUser);

    /**
     * 获取运单的操作轨迹
     *
     * @param waybillId          运单ID，必填
     * @param operateApplication 操作应用，枚举，非必填
     * @param operateType        操作类型，枚举，非必填
     *
     * @return
     */
    List<WaybillOperateTrackExternalVo> listWaybillOperateTrackBy(Integer waybillId,
        OperateApplication operateApplication, OperateType operateType);

    /**
     * @api com.juma.tgm.external.service.WaybillExternalService.listWaybillDeliveryAddressByWaybillId 根据运单ID获取运单取货地信息
     * @apiDescription 根据运单ID获取运单取货地信息
     * @apiVersion 1.0.0
     * @apiName listWaybillDeliveryAddressByWaybillId
     * @apiGroup 运单查询
     * @apiParam (接口参数) {Integer} waybillId 运单id(必填)
     * @apiUse WaybillDeliveryAddressSuccess
     */
    List<WaybillDeliveryAddress> listWaybillDeliveryAddressByWaybillId(Integer waybillId);

    /**
     * @api com.juma.tgm.external.service.WaybillExternalService.listWaybillReceiveAddressByWaybillId 根据运单ID获取运单配送地信息
     * @apiDescription 根据运单ID获取运单配送地信息
     * @apiVersion 1.0.0
     * @apiName listWaybillReceiveAddressByWaybillId
     * @apiGroup 运单查询
     * @apiParam (接口参数) {Integer} waybillId 运单id(必填)
     * @apiUse WaybillReceiveAddressSuccess
     */
    List<WaybillReceiveAddress> listWaybillReceiveAddressByWaybillId(Integer waybillId);

    /**
     * @api com.juma.tgm.external.service.WaybillExternalService.loadTruckRequireByWaybillId 根据运单ID获取运单的用车要求
     * @apiDescription 根据运单ID获取运单的用车要求
     * @apiVersion 1.0.0
     * @apiName loadTruckRequireByWaybillId
     * @apiGroup 运单查询
     * @apiParam (接口参数) {Integer} waybillId 运单id(必填)
     * @apiUse TruckRequireSuccess
     */
    TruckRequire loadTruckRequireByWaybillId(Integer waybillId);
}
