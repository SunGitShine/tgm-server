package com.juma.tgm.waybill.domain;

import java.math.BigDecimal;
import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.giants.common.exception.BusinessException;
import com.juma.tgm.base.domain.BaseDomain;
import com.juma.tgm.base.domain.BaseEnumDomian;
import com.juma.tgm.project.domain.ValuationWay;
import io.swagger.annotations.ApiModelProperty;

/**
 * waybill - 运单
 *
 * @author 2016-05-09
 * @version 1.0
 */
public class Waybill extends BaseDomain {

    /**
     * 签约方
     */
    private Integer departmentId;

    /**
     * 签约方统一社会信用代码
     */
    private String contractToCompanyCreditCode;

    /**
     * 对帐单号
     */
    private String reconciliationNo;

    /**
     * 派车方式
     */
    private Integer receiveWay;

    /**
     * 运单id
     */
    private Integer waybillId;

    /**
     * 用车人 id
     */
    private Integer truckCustomerId;// 将被consignorAccountId替换

    /**
     * 货主帐号id 对应crm联系人帐号
     */
    private Integer consignorAccountId;// 落地配

    /**
     * 用车人姓名
     */
    private String truckCustomerName;

    /**
     * 货车id
     */
    private Integer truckId;

    /**
     * 货车车牌号
     */
    @ApiModelProperty(value = "货车车牌号")
    private String plateNumber;

    /**
     * 司机id
     */
    private Integer driverId;

    /**
     * 司机姓名
     */
    @ApiModelProperty(value = "司机姓名")
    private String driverName;

    /**
     * 司机类型 (外请，自营，加盟...)
     */
    private Integer driverType;

    /**
     * 企业客户id
     */
    private Integer customerId;

    /**
     * 项目 Id
     */
    private Integer projectId;

    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称")
    private String projectName;

    /**
     * 业务分支 0 专车 1 分公司
     */
    private Integer businessBranch;

    /**
     * 运单编号
     */
    @ApiModelProperty(value = "运单号")
    private String waybillNo;

    /**
     * 运单业务状态
     */
    private Integer status;

    /**
     * 取货地地区编码
     */
    private String regionCode;

    /**
     * 运单展示状态
     */
    @ApiModelProperty(value = "运单状态 1: 派车中, 2: 待配送, 3: 配送中, 4: 待支付, 5: 已完成, 6: 已取消")
    private Integer statusView;

    /**
     * 预估距离
     */
    private Integer estimateDistance;

    /**
     * 预估耗时
     */
    private Integer estimateTimeConsumption;

    /**
     * 税前费用
     */
    @ApiModelProperty(value = "客户税前费用")
    private BigDecimal estimateFreight;

    /**
     * 成本价：系统报价
     */
    private BigDecimal calculatedFreight;

    /**
     * 返点率
     */
    private BigDecimal rebateRate;

    /**
     * 税后运费
     */
    private BigDecimal afterTaxFreight;

    /**
     * 司机结算价
     */
    @ApiModelProperty(value = "司机结算价")
    private BigDecimal show4DriverFreight;

    /**
     * 计划配送时间
     */
    @ApiModelProperty(value = "用车时间")
    private Date planDeliveryTime;

    /**
     * 开始配送时间(离仓时间)
     */
    private Date deliveryTime;

    /**
     * 运单备注(异常运单)
     */
    private String waybillRemark;

    /**
     * 收款方式
     */
    private Integer receiptType;

    /**
     * 接单时间或被指派时间
     */
    private Date receivingTime;


    /**
     * 运单配送结束时间
     */
    @ApiModelProperty(value = "配送完成时间")
    private Date finishTime;

    /**
     * 到达取货地时间：离仓时间
     */
    private Date arriveDepotTime;

    /**
     * 置顶字段
     */
    private Integer orderNo;

    /**
     * 是否迟到
     */
    private Integer isLate;

    /**
     * 运单价格对比度
     */
    private BigDecimal compareResult;

    /**
     * 本人付款或代付
     */
    private Integer paymentRoute;

    /**
     * 改价备注
     */
    private String updateFreightRemark;

    /**
     * 订单取消原因
     */
    private String waybillCancelRemark;

    /**
     * 派车备注
     */
    private String assignWaybillRemark;

    /**
     * 运单来源
     */
    private Integer waybillSource;

    /**
     * 企业客户名称
     */
    @ApiModelProperty(value = "客户名称")
    private String customerName;

    /**
     * 回单状态
     */
    private Integer needReceipt;

    /**
     * 实际里程
     */
    private Integer actualMileage;

    /**
     * 人工干预原因（自动派单使用）
     */
    private String waybillUnbundlingReason;

    /**
     * 高速路费
     */
    private BigDecimal tolls;

    /**
     * 自动派车 是否需要再次提交
     */
    private Integer isSubmitMatch = 0;

    /**
     * 企业客户所属客户经理id
     */
    private Integer customerManagerId;

    /**
     * 企业客户所属客户经理名字
     */
    private String customerManagerName;

    /**
     * 派车反馈
     */
    private String assignCarFeedback;

    /**
     * 取消渠道
     */
    private Integer cancelChannel;

    /**
     * 入城证
     */
    private Integer entryLicense;

    /**
     * 货物重量
     */
    private Integer goodsWeight;

    /**
     * 货物体积
     */
    private BigDecimal goodsVolume;

    /**
     * 箱型
     */
    private Integer vehicleBoxType;

    /**
     * 修改路线状态(配送单状态)
     */
    private Integer isChangeDeliveryPoint;

    /**
     * 业务区域
     */
    private String areaCode;

    /**
     * 租户Id
     */
    private Integer tenantId;

    /**
     * 租户名字
     */
    private String tenantCode;

    /**
     * 班次使用记录ID
     */
    private Integer flightUsageId;

    /**
     * 是不是测试单子
     */
    private Boolean isTest;

    /**
     * 经济人预计完成时间
     */
    private Date cmEstimateFinishTime;

    /**
     * 是否在装货后第二天开始配送
     */
    private Integer onlyLoadCargo;

    /**
     * 是否需要上传配送单
     */
    private Integer needDeliveryPointNote;

    /**
     * 修改运单价格审核状态
     */
    private Integer updateFreightAuditStatus;

    /**
     * 待审核运费
     */
    private BigDecimal freightToBeAudited;

    /**
     * 改价审核备注
     */
    private String updateFreightAuditRemark;

    /**
     * 运单对账状态
     */
    private Integer reconciliationStatus;

    /**
     * 司机是否已读
     */
    private boolean isDriverHasRead;

    /**
     * 结算状态
     */
    private Integer settlementStatus;
    /**
     * 收款状态
     */
    private Integer receiptStatus;

    /**
     * 承运商id
     */
    private Integer vendorId;
    /**
     * 承运商名称
     */
    @ApiModelProperty(value = "承运商名称")
    private String vendorName;

    /**
     * 路线id
     */
    private Integer roadMapId;

    /**
     * 路线名称
     */
    private String roadMapName;

    /**
     * 应收对账单号
     */
    private String receivableReconcilicationNo;
    /**
     * 应收对账状态
     */
    private Integer receivableReconcilicationStatus;
    /**
     * 车辆类型
     */
    private Integer vehicleType;
    /**
     * 车辆所属承运商
     */
    private Integer vehicleToVendor;
    /**
     * 冗余：车辆所属承运商名称
     */
    private Integer vehicleToVendorName;
    /**
     * 冗余：运单来源
     */
    private String waybillSourceText;

    /**
     * 冗余: 企业客户所属客户经理电话
     */
    private String customerManagerMobile;

    /**
     * 冗余: 班次ID,不入库
     */
    private Integer flightId;

    /**
     * 冗余: 是否本人运单，不入库
     */
    private boolean selfWaybill = true;

    /**
     * 冗余：操作坐标
     */
    private String location;

    /**
     * 冗余：操作设备号
     */
    private String deviceNo;

    /**
     * 冗余：操作设备类型
     */
    private Integer deviceType;

    /**
     * 冗余：参考报价
     */
    private BigDecimal referenceFreight;

    /**
     * 冗余：最低报价
     */
    private BigDecimal miniFreight;

    /**
     * 冗余：返点费
     */
    private BigDecimal rebateFee;

    /**
     * 冗余：AMS系统的车辆ID
     */
    private Integer vehicleId;

    /**
     * 冗余：AMS系统的司机ID
     */
    private Integer amsDriverId;

    /**
     * 冗余：业务区域中文名
     */
    private String areaName;

    /**
     * 冗余： 结算方式中文名
     */
    private String receiptTypeText;

    /**
     * 冗余：运单展示状态中文名
     */
    private String statusViewText;

    /**
     * 冗余：司机类型中文名
     */
    private String driverTypeName;

    /**
     * 冗余：回单状态中文名
     */
    private String needReceiptText;

    /**
     * 冗余：取消渠道中文名
     */
    private String cancelChannelText;

    /**
     * 冗余:显示 计价系数
     */

    private String valuationConstJson;

    /**
     * 项目运单-计价方式
     */
    private List<ValuationWay> valuationWays = new ArrayList<ValuationWay>();

    /**
     * 计价方式显示
     */
    private String valuationWayView;

    /**
     * 冗余：查询
     */
    private List<Integer> waybillIds = new ArrayList<Integer>();

    /**
     * 冗余：预估完成时间
     */
    private String cmEstimateFinishTimeStr;

    /**
     * 冗余：价格异常条件标记 true：拼接需要条件 false：
     */
    private boolean priceExceptionFlag;

    /**
     * 运单的业务区域的逻辑业务区域
     */
    private String logicAreaCode;

    /**
     * 运单的业务区域的逻辑业务区域名称
     */
    private String logicAreaCodeName;

    /**
     * 冗余：客户用车时间起计算装货耗时
     */
    @Deprecated
    private String loadingTime;

    /**
     * 冗余：司机到达时间起计算装货耗时
     */
    private String driverArrivedLoadingTime;

    /**
     * 冗余：配送耗时
     */
    private String shippingTime;

    /**
     * 冗余：本业务区域是否可操作 true: 可操作；false: 不可操作
     */
    @Deprecated
    private boolean ownerAreaCanOperate = true;

    /**
     * 冗余：分享业务区域是否可操作 true: 可操作；false: 不可操作
     */
    @Deprecated
    private boolean shareAreaCanOperate = true;

    /**
     * 冗余：运单能不能被取消
     */
    private boolean allowCancel;

    /**
     * 冗余：能不能更改车辆
     */
    private boolean allowChangeCar;

    /**
     * 实际里程错误信息
     */
    private String actualMileageErrorStr;

    /**
     *
     */
    private boolean allowSendCar = true;

    /**
     * 冗余：租户名称
     */
    private String tenantName;

    /**
     * 是否展示价格，1展示
     */
    private boolean showYourPrice;

    /**
     * 是否作为项目单处理
     */
    private boolean asProjectWaybillHandle;

    /**
     * 谁填写工作量 1:司机 2:经济人
     */
    private Integer whoWriteWork;

    /**
     * 是否需要完善工作量
     */
    private boolean isCompleteWorkload;

    /**
     * 物流产品标签
     */
    private String logisticsLabel;

    /**
     * 物流产品标签名字
     */
    private String logisticsName;

    /**
     * 运营主体
     */
    private Integer payToCompany;

    /**
     * 运营主体统一社会信用代码
     */
    private String payToCompanyCreditCode;

    /**
     * 项目经理
     */
    private Integer projectManagerUserId;

    /**
     * 创建人名称   数据平台导单用  以后要去掉
     */
    private String createUserName;

    /**
     * 创建人电话  冗余字段  运单详情页使用
     */
    private String createUserPhone;

    /**
     * 项目经理名称 数据平台导单用  以后要去掉
     */
    private String projectManagerUserName;

    /**
     * 项目经理名称 冗余字段  运单详情页使用
     */
    private String projectManagerUserPhone;

    /**冗余：客户侧最终含税金额*/
    private BigDecimal lastCustomerFreightWithTax;

    /**冗余：承运侧最终含税金额*/
    private BigDecimal lastVendorFreightWithTax;

    public BigDecimal getLastCustomerFreightWithTax() {
        return lastCustomerFreightWithTax;
    }

    public void setLastCustomerFreightWithTax(BigDecimal lastCustomerFreightWithTax) {
        this.lastCustomerFreightWithTax = lastCustomerFreightWithTax;
    }

    public BigDecimal getLastVendorFreightWithTax() {
        return lastVendorFreightWithTax;
    }

    public void setLastVendorFreightWithTax(BigDecimal lastVendorFreightWithTax) {
        this.lastVendorFreightWithTax = lastVendorFreightWithTax;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
    }

    public String getLogisticsLabel() {
        return logisticsLabel;
    }

    public void setLogisticsLabel(String logisticsLabel) {
        this.logisticsLabel = logisticsLabel;
    }

    public boolean isCompleteWorkload() {
        return isCompleteWorkload;
    }

    public void setCompleteWorkload(boolean isCompleteWorkload) {
        this.isCompleteWorkload = isCompleteWorkload;
    }

    public Integer getWhoWriteWork() {
        return whoWriteWork;
    }

    public void setWhoWriteWork(Integer whoWriteWork) {
        this.whoWriteWork = whoWriteWork;
    }

    public boolean isShowYourPrice() {
        return showYourPrice;
    }

    public void setShowYourPrice(boolean showYourPrice) {
        this.showYourPrice = showYourPrice;
    }

    public boolean isAsProjectWaybillHandle() {
        return asProjectWaybillHandle;
    }

    public void setAsProjectWaybillHandle(boolean asProjectWaybillHandle) {
        this.asProjectWaybillHandle = asProjectWaybillHandle;
    }

    public String getReceiptTypeText() {
        if (null == receiptType) {
            return receiptTypeText;
        }
        return StringUtils.isBlank(receiptTypeText) ? Waybill.ReceiptType.getPayWayStr(receiptType) : receiptTypeText;
    }

    public void setReceiptTypeText(String receiptTypeText) {
        this.receiptTypeText = receiptTypeText;
    }

    public String getCancelChannelText() {
        if (null == cancelChannel) {
            return cancelChannelText;
        }
        return StringUtils.isBlank(cancelChannelText) ? Waybill.CancelChannel.buildCancelChannelStr(cancelChannel)
            : cancelChannelText;
    }

    public void setCancelChannelText(String cancelChannelText) {
        this.cancelChannelText = cancelChannelText;
    }

    public boolean isDriverHasRead() {
        return isDriverHasRead;
    }

    public void setDriverHasRead(boolean isDriverHasRead) {
        this.isDriverHasRead = isDriverHasRead;
    }

    public boolean isAllowChangeCar() {
        return allowChangeCar;
    }

    public void setAllowChangeCar(boolean allowChangeCar) {
        this.allowChangeCar = allowChangeCar;
    }

    public boolean isAllowCancel() {
        return allowCancel;
    }

    public void setAllowCancel(boolean allowCancel) {
        this.allowCancel = allowCancel;
    }

    public String getActualMileageErrorStr() {
        return actualMileageErrorStr;
    }

    public void setActualMileageErrorStr(String actualMileageErrorStr) {
        this.actualMileageErrorStr = actualMileageErrorStr;
    }

    public boolean isAllowSendCar() {
        return allowSendCar;
    }

    public void setAllowSendCar(boolean allowSendCar) {
        this.allowSendCar = allowSendCar;
    }

    public String getLogicAreaCode() {
        return logicAreaCode;
    }

    public void setLogicAreaCode(String logicAreaCode) {
        this.logicAreaCode = logicAreaCode;
    }

    public String getLogicAreaCodeName() {
        return logicAreaCodeName;
    }

    public void setLogicAreaCodeName(String logicAreaCodeName) {
        this.logicAreaCodeName = logicAreaCodeName;
    }

    public String getLoadingTime() {
        return loadingTime;
    }

    public void setLoadingTime(String loadingTime) {
        this.loadingTime = loadingTime;
    }

    public String getDriverArrivedLoadingTime() {
        return driverArrivedLoadingTime;
    }

    public void setDriverArrivedLoadingTime(String driverArrivedLoadingTime) {
        this.driverArrivedLoadingTime = driverArrivedLoadingTime;
    }

    public String getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(String shippingTime) {
        this.shippingTime = shippingTime;
    }

    public String getTruckCustomerName() {
        return truckCustomerName;
    }

    public void setTruckCustomerName(String truckCustomerName) {
        this.truckCustomerName = truckCustomerName;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getCustomerManagerName() {
        return customerManagerName;
    }

    public void setCustomerManagerName(String customerManagerName) {
        this.customerManagerName = customerManagerName;
    }

    public boolean isOwnerAreaCanOperate() {
        return ownerAreaCanOperate;
    }

    public void setOwnerAreaCanOperate(boolean ownerAreaCanOperate) {
        this.ownerAreaCanOperate = ownerAreaCanOperate;
    }

    public boolean isShareAreaCanOperate() {
        return shareAreaCanOperate;
    }

    public void setShareAreaCanOperate(boolean shareAreaCanOperate) {
        this.shareAreaCanOperate = shareAreaCanOperate;
    }

    public boolean isPriceExceptionFlag() {
        return priceExceptionFlag;
    }

    public void setPriceExceptionFlag(boolean priceExceptionFlag) {
        this.priceExceptionFlag = priceExceptionFlag;
    }

    public String getCmEstimateFinishTimeStr() {
        return cmEstimateFinishTimeStr;
    }

    public void setCmEstimateFinishTimeStr(String cmEstimateFinishTimeStr) {
        this.cmEstimateFinishTimeStr = cmEstimateFinishTimeStr;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public Integer getTruckCustomerId() {
        return truckCustomerId;
    }

    public void setTruckCustomerId(Integer truckCustomerId) {
        this.truckCustomerId = truckCustomerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getDriverType() {
        return driverType;
    }

    public void setDriverType(Integer driverType) {
        this.driverType = driverType;
    }

    public String getDriverTypeName() {
        return driverTypeName;
    }

    public void setDriverTypeName(String driverTypeName) {
        this.driverTypeName = driverTypeName;
    }

    public String getNeedReceiptText() {
        return needReceiptText;
    }

    public void setNeedReceiptText(String needReceiptText) {
        this.needReceiptText = needReceiptText;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getEstimateDistance() {
        return estimateDistance;
    }

    public void setEstimateDistance(Integer estimateDistance) {
        this.estimateDistance = estimateDistance;
    }

    public Integer getEstimateTimeConsumption() {
        return estimateTimeConsumption;
    }

    public void setEstimateTimeConsumption(Integer estimateTimeConsumption) {
        this.estimateTimeConsumption = estimateTimeConsumption;
    }

    public BigDecimal getEstimateFreight() {
        return estimateFreight;
    }

    public void setEstimateFreight(BigDecimal estimateFreight) {
        this.estimateFreight = estimateFreight;
    }

    public BigDecimal getCalculatedFreight() {
        // 获取为4舍5入的值
        if (calculatedFreight != null) {
            return calculatedFreight.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return calculatedFreight;
    }

    public void setCalculatedFreight(BigDecimal calculatedFreight) {
        this.calculatedFreight = calculatedFreight;
    }

    public BigDecimal getAfterTaxFreight() {
        return afterTaxFreight;
    }

    public void setAfterTaxFreight(BigDecimal afterTaxFreight) {
        this.afterTaxFreight = afterTaxFreight;
    }

    public Date getPlanDeliveryTime() {
        return planDeliveryTime;
    }

    public void setPlanDeliveryTime(Date planDeliveryTime) {
        this.planDeliveryTime = planDeliveryTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getWaybillRemark() {
        return waybillRemark;
    }

    public void setWaybillRemark(String waybillRemark) {
        this.waybillRemark = waybillRemark;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public Integer getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(Integer receiptType) {
        this.receiptType = receiptType;
    }

    public Date getReceivingTime() {
        return receivingTime;
    }

    public void setReceivingTime(Date receivingTime) {
        this.receivingTime = receivingTime;
    }

    public Integer getStatusView() {
        return statusView;
    }

    public void setStatusView(Integer statusView) {
        this.statusView = statusView;
    }

    public String getStatusViewText() {
        if (StringUtils.isNotBlank(this.statusViewText)) {
            return statusViewText;
        }

        if (null == this.statusView) {
            return statusViewText;
        }

        return this.statusView == Waybill.StatusView.TEMP.getCode() ? Waybill.StatusView.WATING_DELIVERY.getDescr()
            : StatusView.fromInt(statusView).getDescr();
    }

    public void setStatusViewText(String statusViewText) {
        this.statusViewText = statusViewText;
    }

    public Integer getReceiveWay() {
        return receiveWay;
    }

    public void setReceiveWay(Integer receiveWay) {
        this.receiveWay = receiveWay;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getArriveDepotTime() {
        return arriveDepotTime;
    }

    public void setArriveDepotTime(Date arriveDepotTime) {
        this.arriveDepotTime = arriveDepotTime;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer isLate() {
        return isLate;
    }

    public void setLate(Integer isLate) {
        this.isLate = isLate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getCompareResult() {
        return compareResult;
    }

    public void setCompareResult(BigDecimal compareResult) {
        this.compareResult = compareResult;
    }

    public String getUpdateFreightRemark() {
        return updateFreightRemark;
    }

    public void setUpdateFreightRemark(String updateFreightRemark) {
        this.updateFreightRemark = updateFreightRemark;
    }

    public String getAssignWaybillRemark() {
        return assignWaybillRemark;
    }

    public void setAssignWaybillRemark(String assignWaybillRemark) {
        this.assignWaybillRemark = assignWaybillRemark;
    }

    public Integer getPaymentRoute() {
        return paymentRoute;
    }

    public void setPaymentRoute(Integer paymentRoute) {
        this.paymentRoute = paymentRoute;
    }

    public Integer getWaybillSource() {
        return waybillSource;
    }

    public void setWaybillSource(Integer waybillSource) {
        this.waybillSource = waybillSource;
    }

    public Integer getIsLate() {
        return isLate;
    }

    public void setIsLate(Integer isLate) {
        this.isLate = isLate;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getActualMileage() {
        return actualMileage;
    }

    public void setActualMileage(Integer actualMileage) {
        this.actualMileage = actualMileage;
    }

    public String getWaybillUnbundlingReason() {
        return waybillUnbundlingReason;
    }

    public void setWaybillUnbundlingReason(String waybillUnbundlingReason) {
        this.waybillUnbundlingReason = waybillUnbundlingReason;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public BigDecimal getTolls() {
        return tolls;
    }

    public void setTolls(BigDecimal tolls) {
        this.tolls = tolls;
    }

    public Integer getNeedReceipt() {
        return needReceipt;
    }

    public void setNeedReceipt(Integer needReceipt) {
        this.needReceipt = needReceipt;
    }

    public String getWaybillCancelRemark() {
        return waybillCancelRemark;
    }

    public void setWaybillCancelRemark(String waybillCancelRemark) {
        this.waybillCancelRemark = waybillCancelRemark;
    }

    public Integer getIsSubmitMatch() {
        return isSubmitMatch;
    }

    public void setIsSubmitMatch(Integer isSubmitMatch) {
        this.isSubmitMatch = isSubmitMatch;
    }

    public Integer getCustomerManagerId() {
        return customerManagerId;
    }

    public void setCustomerManagerId(Integer customerManagerId) {
        this.customerManagerId = customerManagerId;
    }

    public String getAssignCarFeedback() {
        return assignCarFeedback;
    }

    public void setAssignCarFeedback(String assignCarFeedback) {
        this.assignCarFeedback = assignCarFeedback;
    }

    public Integer getCancelChannel() {
        return cancelChannel;
    }

    public void setCancelChannel(Integer cancelChannel) {
        this.cancelChannel = cancelChannel;
    }

    public Integer getEntryLicense() {
        return entryLicense;
    }

    public void setEntryLicense(Integer entryLicense) {
        this.entryLicense = entryLicense;
    }

    public Integer getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(Integer goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public BigDecimal getGoodsVolume() {
        return goodsVolume;
    }

    public void setGoodsVolume(BigDecimal goodsVolume) {
        this.goodsVolume = goodsVolume;
    }

    public Integer getIsChangeDeliveryPoint() {
        return isChangeDeliveryPoint;
    }

    public void setIsChangeDeliveryPoint(Integer isChangeDeliveryPoint) {
        this.isChangeDeliveryPoint = isChangeDeliveryPoint;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getAmsDriverId() {
        return amsDriverId;
    }

    public void setAmsDriverId(Integer amsDriverId) {
        this.amsDriverId = amsDriverId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public String getRoadMapName() {
        return roadMapName;
    }

    public void setRoadMapName(String roadMapName) {
        this.roadMapName = roadMapName;
    }

    // 2-3 3-2(指派车辆前)
    public enum ReceiveWay {
        ASSIGNED(1, "自主派车"),
        RECEIVED(2, "司机抢单"),
        MANUAL_ASSIGN(3, "调度指派"),
        SHARE_BILL(4, "分享运单"),
        AUTO_ASSIGN(5, "自动派车"),
        TRANSFORM_BILL(6, "转承运商");

        private int code;

        private String descr;

        private ReceiveWay(int code, String descr) {
            this.code = code;
            this.descr = descr;
        }

        public int getCode() {
            return code;
        }

        public String getDescr() {
            return descr;
        }

        public static String buildReceiveWayStr(int code) {
            for (ReceiveWay receiveWay : ReceiveWay.values()) {
                if (code == receiveWay.getCode()) {
                    return receiveWay.getDescr();
                }
            }
            return null;
        }

        public static Map<String, String> map() {
            Map<String, String> map = new HashMap<String, String>();
            for (ReceiveWay receiveWay : values()) {
                map.put(receiveWay.getCode() + "", receiveWay.getDescr());
            }
            return map;
        }
    }

    public enum NeedReceipt {
        NO_NEED_RECEIPT(0, "不回单"),
        NOT_HAVE_UPLOAD(1, "未上传"),
        HAS_UPLOAD(2, "已上传"),
        HAS_NEED_RECEIPT(3, "已收回");

        private int code;

        private String descr;

        private NeedReceipt(int code, String descr) {
            this.code = code;
            this.descr = descr;
        }

        public int getCode() {
            return code;
        }

        public String getDescr() {
            return descr;
        }

        public static String buildNeedReceiptStr(int code) {
            for (NeedReceipt needReceipt : NeedReceipt.values()) {
                if (code == needReceipt.getCode()) {
                    return needReceipt.getDescr();
                }
            }
            return null;
        }

        public static Map<String, String> map() {
            Map<String, String> map = new HashMap<String, String>();
            for (NeedReceipt r : NeedReceipt.values()) {
                map.put(r.getCode() + "", r.getDescr());
            }
            return map;
        }
    }

    public enum WaybillSource {
        JUMA_CLIENT(1, "经纪人APP"),
        WECHAT_CLIENT(2, "微信"),
        BACKGROUND_IMPORT(3, "后台导入"),
        BACKGROUND_NEW(4, "后台下单"),
        CARGO_OWNER(5, "货主APP"),
        FIXED_DEMAND_AUTO_CREATE(6, "定时下单"),
        INTERFACE(7, "接口下单"),
        TRANSFORM_BILL(8, "转运单"),
        FORM_TASK(9,"任务下单");

        private int code;

        private String descr;

        private WaybillSource(int code, String descr) {
            this.code = code;
            this.descr = descr;
        }

        public int getCode() {
            return code;
        }

        public String getDescr() {
            return descr;
        }

        public static String buildWaybillSourceStr(int code) {
            for (WaybillSource waybillSource : WaybillSource.values()) {
                if (code == waybillSource.getCode()) {
                    return waybillSource.getDescr();
                }
            }
            return null;
        }

        public static WaybillSource buildWaybillSource(int code) {
            for (WaybillSource waybillSource : WaybillSource.values()) {
                if (code == waybillSource.getCode()) {
                    return waybillSource;
                }
            }
            return null;
        }

        public static Map<String, String> map() {
            Map<String, String> map = new HashMap<String, String>();
            for (WaybillSource waybillSource : values()) {
                map.put(waybillSource.getCode() + "", waybillSource.getDescr());
            }
            return map;
        }
    }

    // 取消渠道
    public enum CancelChannel {
        JUMA_CLIENT(1, "APP"),
        WECHAT_CLIENT(2, "微信"),
        BACKGROUND_IMPORT(3, "后台"),
        TASK_CALENDAR(4,"任务日历");


        private int code;

        private String descr;

        private CancelChannel(int code, String descr) {
            this.code = code;
            this.descr = descr;
        }

        public int getCode() {
            return code;
        }

        public String getDescr() {
            return descr;
        }

        public static String buildCancelChannelStr(int code) {
            for (CancelChannel cancelChannel : CancelChannel.values()) {
                if (code == cancelChannel.getCode()) {
                    return cancelChannel.getDescr();
                }
            }
            return null;
        }

        public static Map<String, String> map() {
            Map<String, String> map = new HashMap<String, String>();
            for (CancelChannel cancelChannel : values()) {
                map.put(cancelChannel.getCode() + "", cancelChannel.getDescr());
            }
            return map;
        }
    }

    /**
     * 配送状态
     */
    public enum StatusView {
        TEMP(-2, "非用户可见状态"),
        HAS_TIMED_OUT(-1, "已超时"),
        DEFAULT(0, "异常订单"),
        WATING_RECEIVE(1, "派车中"),
        WATING_DELIVERY(2, "待配送"),
        DELIVERYING(3, "配送中"),
        WATING_PAY(4, "待支付"),
        FINISH(5, "已完成"),
        CANCEL(6, "已取消"),
        // UNCHECK(7, "未对账"),
        // CHECKED(8, "已对账"),
        // CLOSED(9, "已收款")
        ;

        private int code;

        private String descr;

        private StatusView(int code, String descr) {
            this.code = code;
            this.descr = descr;
        }

        private static final Map<Integer, StatusView> intToEnum = new HashMap<Integer, StatusView>();

        static {
            for (StatusView statusView : values()) {
                intToEnum.put(statusView.getCode(), statusView);
            }
        }

        public static StatusView fromInt(Integer symbol) {
            return intToEnum.get(symbol);
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDescr() {
            return descr;
        }

        public void setDescr(String descr) {
            this.descr = descr;
        }

        public static String getDescrr(int statusView) {
            for (StatusView view : StatusView.values()) {
                if (statusView == view.getCode()) {
                    return view.getDescr();
                }
            }
            return "";
        }

        public static List<BaseEnumDomian> getStatusViewList() {
            List<BaseEnumDomian> list = new ArrayList<BaseEnumDomian>();
            for (StatusView statusView : StatusView.values()) {
                if (!StatusView.DEFAULT.equals(statusView)) {
                    BaseEnumDomian statusDomian = new BaseEnumDomian();
                    statusDomian.setCode(statusView.getCode());
                    statusDomian.setDesc(statusView.getDescr());
                    list.add(statusDomian);
                }
            }
            return list;
        }

        public static Map<String, String> map() {
            Map<String, String> map = new HashMap<String, String>();
            for (StatusView view : values()) {
                map.put(view.getCode() + "", view.getDescr());
            }
            return map;
        }
    }

    public enum Status {
        NO_DRIVER_ANSWER(-2, "没有匹配的司机或超时"),
        WAITINT_DRIVER_ANSWER(-1, "等待司机确认"),
        NEW(0, "新建"), // 中间状态
        WATING_RECEIVE(1, "待接单"),
        ASSIGNED(2, "已指派"),
        RECEIVED(3, "已接单"),
        DELIVERYING(4, "配送中"),
        DELIVERIED(5, "配送完成"),
        PAYING(6, "支付中"),
        PAIED(7, "支付完成"),
        DRIVER_ASK_FOR_CANCEL(8, "申请取消中"), // 没有启用
        CUSTOMER_APPROVE_CANCEL(9, "同意取消"), // 没有启用
        CUSTOMER_CANCEL(10, "取消"),
        SYS_CANCEL(11, "取消"),
        PENDING_EXAMINE(12, "微信待审核"),
        UNDETERMINED(99, "派车待定");

        private int code;

        private String descr;

        private Status(int code, String descr) {
            this.code = code;
            this.descr = descr;
        }

        public int getCode() {
            return code;
        }

        public String getDescr() {
            return descr;
        }

        public static List<BaseEnumDomian> getStatusList() {
            List<BaseEnumDomian> list = new ArrayList<BaseEnumDomian>();
            for (Status status : Status.values()) {
                BaseEnumDomian statusDomian = new BaseEnumDomian();
                statusDomian.setCode(status.getCode());
                statusDomian.setDesc(status.getDescr());
                list.add(statusDomian);
            }
            return list;
        }
    }

    // 支付方式
    public enum ReceiptType {

        WEIXINPAY(1, "微信支付"),
        OFFLINEPAY(2, "现金支付"),
        PROJECTPAY(3, "项目结算"),
        DRIVER_CHEQUES(4, "现金，司机收款"),
        CUSTOMER_CHEQUES(5, "现金，客户经理收款");

        private int code;
        private String descr;

        ReceiptType(int code, String descr) {
            this.code = code;
            this.descr = descr;
        }

        public int getCode() {
            return code;
        }

        public String getDescr() {
            return descr;
        }

        public static List<BaseEnumDomian> getPayWayList(Integer classId) {
            List<BaseEnumDomian> list = new ArrayList<BaseEnumDomian>();
            for (ReceiptType receiptType : ReceiptType.values()) {
                BaseEnumDomian statusDomian = new BaseEnumDomian();
                statusDomian.setCode(receiptType.getCode());
                statusDomian.setDesc(receiptType.getDescr());
                list.add(statusDomian);
            }
            return list;
        }

        public static String getPayWayStr(Integer code) {
            for (ReceiptType receiptType : ReceiptType.values()) {
                if (null != code && receiptType.getCode() == code) {
                    return receiptType.getDescr();
                }
            }
            return "";
        }

        public static Map<String, String> map() {
            Map<String, String> map = new HashMap<String, String>();
            for (ReceiptType receiptType : values()) {
                map.put(receiptType.getCode() + "", receiptType.getDescr());
            }
            return map;
        }
    }

    // 代付
    public enum PaymentRoute {

        OWN_PAYMENT(1, "本人付款"),
        OTHER_PAYMENT(2, "他人代付");

        private int code;
        private String descr;

        private PaymentRoute(int code, String descr) {
            this.code = code;
            this.descr = descr;
        }

        public int getCode() {
            return code;
        }

        public String getDescr() {
            return descr;
        }
    }

    // 迟到标记
    public enum Late {

        LATE_TIME(1, "迟到"),
        DISTANCE_TO_SOON(2, "距离大于2KM"),
        LATE_AND_DISTANCE(3, "迟到且距离大于2KM");

        private int code;
        private String descr;

        private Late(int code, String descr) {
            this.code = code;
            this.descr = descr;
        }

        public int getCode() {
            return code;
        }

        public String getDescr() {
            return descr;
        }
    }

    // 配送单上传修改标记
    public enum ChangeDeliveryPoint {

        HAS_UPDATE(1, "已修改"),
        HAS_UPLOAD(2, "已上传"),
        INVALID_UPLOAD(3, "无效上传"),
        NOT_UPLOAD(4, "未上传");

        private int code;
        private String descr;

        private ChangeDeliveryPoint(int code, String descr) {
            this.code = code;
            this.descr = descr;
        }

        public int getCode() {
            return code;
        }

        public String getDescr() {
            return descr;
        }

        public static String getDesc(int code) {
            for (ChangeDeliveryPoint point : ChangeDeliveryPoint.values()) {
                if (code == point.getCode()) {
                    return point.getDescr();
                }
            }
            return null;
        }
    }

    // 修改运单价格审核状态
    public enum UpdateFreightAuditStatus {

        WATING_AUDIT(1, "待审核"),
        HAS_PASS(2, "已通过"),
        NOT_PASS(3, "未通过");

        private int code;
        private String descr;

        private UpdateFreightAuditStatus(int code, String descr) {
            this.code = code;
            this.descr = descr;
        }

        public int getCode() {
            return code;
        }

        public String getDescr() {
            return descr;
        }
    }

    // 业务分支
    public enum BusinessBranch {

        @Deprecated SPECIAL_CAR(0, "专车"),
        @Deprecated BRANCH_COM(1, "分公司"),
        BRANCH_SCATTERED(2, "零担"),
        BRANCH_FULL(3, "整车");

        private int code;
        private String descr;

        private BusinessBranch(int code, String descr) {
            this.code = code;
            this.descr = descr;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDescr() {
            return descr;
        }

        public void setDescr(String descr) {
            this.descr = descr;
        }

        public static BusinessBranch getByCode(int code) {
            for (BusinessBranch type : values()) {
                if (NumberUtils.compare(type.getCode(), code) == 0) {
                    return type;
                }
            }
            throw new BusinessException("codeError", "errors.paramErrorWithName", "业务分支枚举");
        }

        public static Map<String, String> map() {
            Map<String, String> map = new HashMap<String, String>();
            for (BusinessBranch type : values()) {
                if (type.getCode() == 2 || type.getCode() == 3) {
                    map.put(type.getCode() + "", type.getDescr());
                }
            }
            return map;
        }
    }

    /**
     * 对账状态 应收和应付的对账共用
     */
    public enum ReconciliationStatus {

        INIT(0, "初始态"),
        NOT_RECONCILIATION(1, "未对账"),
        IN_THE_ACCOUNT(4, "对账中"),
        HAS_RECONCILIATION(2, "已对账");

        private int code;
        private String descr;

        private ReconciliationStatus(int code, String descr) {
            this.code = code;
            this.descr = descr;
        }

        public int getCode() {
            return code;
        }

        public String getDescr() {
            return descr;
        }

        public static ReconciliationStatus getReconciliationStatusByCode(int code) {
            for (ReconciliationStatus rs : ReconciliationStatus.values()) {
                if (NumberUtils.compare(code, rs.getCode()) == 0) {
                    return rs;
                }
            }
            return null;
        }

        public static Map<String, String> map() {
            Map<String, String> map = new HashMap<String, String>();
            for (ReconciliationStatus rs : values()) {
                map.put(rs.getCode() + "", rs.getDescr());
            }
            return map;
        }
    }

    /**
     * 结算状态
     */
    public enum SettlementStatus {
        INIT(0, "初始状态"),
        NOT_CLEAR(1, "未结算"),
        PREPARE_CLEAR(2, "预结算"),
        HAS_CLEAR(3, "已结算");

        private int code;
        private String desc;

        SettlementStatus(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        public static SettlementStatus getSettlementStatusByCode(int code) {
            for (SettlementStatus ss : SettlementStatus.values()) {
                if (NumberUtils.compare(ss.code, code) == 0) {
                    return ss;
                }
            }

            return null;
        }

        public static Map<String, String> map() {
            Map<String, String> map = new HashMap<String, String>();
            for (SettlementStatus ss : values()) {
                map.put(ss.getCode() + "", ss.getDesc());
            }
            return map;
        }
    }

    /**
     * 收款状态
     */
    public enum ReceiptStatus {
        INIT(0, "初始值"),
        NOT_COLLECTION(1, "未收款"),
        SEGMENT_COLLECTION(2, "部分收款"),
        HAS_COLLECTION(3, "已收款");

        private int code;

        private String desc;

        ReceiptStatus(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        public static ReceiptStatus getReceiptStatusByCode(int code) {
            for (ReceiptStatus rs : ReceiptStatus.values()) {
                if (NumberUtils.compare(rs.code, code) == 0) {
                    return rs;
                }
            }

            return null;
        }

        public static Map<String, String> map() {
            Map<String, String> map = new HashMap<String, String>();
            for (ReceiptStatus rs : values()) {
                map.put(rs.getCode() + "", rs.getDesc());
            }
            return map;
        }
    }

    /**
     * 车辆类型
     */
    public enum VehicleType {
        OWN_SALE(1, "自营"),
        JOIN(2, "加盟"),
        EMPLOY(3, "外请");

        private int code;

        private String desc;

        VehicleType(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        public static VehicleType getVehicleTypeByCode(int code) {
            for (VehicleType t : VehicleType.values()) {
                if (NumberUtils.compare(t.code, code) == 0) {
                    return t;
                }
            }

            return null;
        }

        public static Map<String, String> map() {
            Map<String, String> map = new HashMap<String, String>();
            for (VehicleType t : values()) {
                map.put(t.getCode() + "", t.getDesc());
            }
            return map;
        }
    }

    public String getWaybillSourceText() {
        if (null == waybillSource) {
            return waybillSourceText;
        }

        return StringUtils.isBlank(waybillSourceText) ? Waybill.WaybillSource.buildWaybillSourceStr(waybillSource)
            : waybillSourceText;
    }

    public void setWaybillSourceText(String waybillSourceText) {
        this.waybillSourceText = waybillSourceText;
    }

    public String getCustomerManagerMobile() {
        return customerManagerMobile;
    }

    public void setCustomerManagerMobile(String customerManagerMobile) {
        this.customerManagerMobile = customerManagerMobile;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public boolean isSelfWaybill() {
        return selfWaybill;
    }

    public void setSelfWaybill(boolean selfWaybill) {
        this.selfWaybill = selfWaybill;
    }

    public Integer getFlightUsageId() {
        return flightUsageId;
    }

    public void setFlightUsageId(Integer flightUsageId) {
        this.flightUsageId = flightUsageId;
    }

    public Boolean isTest() {
        return isTest;
    }

    public void setTest(Boolean isTest) {
        this.isTest = isTest;
    }

    public Integer getVehicleBoxType() {
        return vehicleBoxType;
    }

    public void setVehicleBoxType(Integer vehicleBoxType) {
        this.vehicleBoxType = vehicleBoxType;
    }

    public BigDecimal getShow4DriverFreight() {
        return show4DriverFreight;
    }

    public void setShow4DriverFreight(BigDecimal show4DriverFreight) {
        this.show4DriverFreight = show4DriverFreight;
    }

    public List<Integer> getWaybillIds() {
        return waybillIds;
    }

    public void setWaybillIds(List<Integer> waybillIds) {
        this.waybillIds = waybillIds;
    }

    public Integer getOnlyLoadCargo() {
        return onlyLoadCargo;
    }

    public void setOnlyLoadCargo(Integer onlyLoadCargo) {
        this.onlyLoadCargo = onlyLoadCargo;
    }

    public Integer getNeedDeliveryPointNote() {
        return needDeliveryPointNote;
    }

    public void setNeedDeliveryPointNote(Integer needDeliveryPointNote) {
        this.needDeliveryPointNote = needDeliveryPointNote;
    }

    public Integer getUpdateFreightAuditStatus() {
        return updateFreightAuditStatus;
    }

    public void setUpdateFreightAuditStatus(Integer updateFreightAuditStatus) {
        this.updateFreightAuditStatus = updateFreightAuditStatus;
    }

    public BigDecimal getFreightToBeAudited() {
        return freightToBeAudited;
    }

    public void setFreightToBeAudited(BigDecimal freightToBeAudited) {
        this.freightToBeAudited = freightToBeAudited;
    }

    public String getUpdateFreightAuditRemark() {
        return updateFreightAuditRemark;
    }

    public void setUpdateFreightAuditRemark(String updateFreightAuditRemark) {
        this.updateFreightAuditRemark = updateFreightAuditRemark;
    }

    public Integer getReconciliationStatus() {
        return reconciliationStatus;
    }

    public void setReconciliationStatus(Integer reconciliationStatus) {
        this.reconciliationStatus = reconciliationStatus;
    }

    public Date getCmEstimateFinishTime() {
        return cmEstimateFinishTime;
    }

    public void setCmEstimateFinishTime(Date cmEstimateFinishTime) {
        this.cmEstimateFinishTime = cmEstimateFinishTime;
    }

    public BigDecimal getReferenceFreight() {
        if (calculatedFreight != null) {
            this.referenceFreight = calculatedFreight.multiply(new BigDecimal(1.2)).setScale(2,
                BigDecimal.ROUND_HALF_UP);
        }
        return referenceFreight;
    }

    public void setReferenceFreight(BigDecimal referenceFreight) {
        this.referenceFreight = referenceFreight;
    }

    public BigDecimal getMiniFreight() {
        if (calculatedFreight != null) {
            this.miniFreight = calculatedFreight.multiply(new BigDecimal(1.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return miniFreight;
    }

    public void setMiniFreight(BigDecimal miniFreight) {
        this.miniFreight = miniFreight;
    }

    public BigDecimal getRebateFee() {
        if (estimateFreight != null && rebateRate != null) {
            this.rebateFee = estimateFreight.multiply(rebateRate).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return rebateFee;
    }

    public void setRebateFee(BigDecimal rebateFee) {
        this.rebateFee = rebateFee;
    }

    public BigDecimal getRebateRate() {
        return rebateRate;
    }

    public void setRebateRate(BigDecimal rebateRate) {
        this.rebateRate = rebateRate;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getBusinessBranch() {
        return businessBranch;
    }

    public void setBusinessBranch(Integer businessBranch) {
        this.businessBranch = businessBranch;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getReconciliationNo() {
        return reconciliationNo;
    }

    public void setReconciliationNo(String reconciliationNo) {
        this.reconciliationNo = reconciliationNo;
    }

    public Integer getConsignorAccountId() {
        return consignorAccountId;
    }

    public void setConsignorAccountId(Integer consignorAccountId) {
        this.consignorAccountId = consignorAccountId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public List<ValuationWay> getValuationWays() {
        return valuationWays;
    }

    public void setValuationWays(List<ValuationWay> valuationWays) {
        this.valuationWays = valuationWays;
    }

    public String getValuationConstJson() {
        return valuationConstJson;
    }

    public void setValuationConstJson(String valuationConstJson) {
        this.valuationConstJson = valuationConstJson;
    }

    public Integer getSettlementStatus() {
        return settlementStatus;
    }

    public void setSettlementStatus(Integer settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    public Integer getReceiptStatus() {
        return receiptStatus;
    }

    public void setReceiptStatus(Integer receiptStatus) {
        this.receiptStatus = receiptStatus;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Integer getRoadMapId() {
        return roadMapId;
    }

    public void setRoadMapId(Integer roadMapId) {
        this.roadMapId = roadMapId;
    }

    public String getReceivableReconcilicationNo() {
        return receivableReconcilicationNo;
    }

    public void setReceivableReconcilicationNo(String receivableReconcilicationNo) {
        this.receivableReconcilicationNo = receivableReconcilicationNo;
    }

    public Integer getReceivableReconcilicationStatus() {
        return receivableReconcilicationStatus;
    }

    public void setReceivableReconcilicationStatus(Integer receivableReconcilicationStatus) {
        this.receivableReconcilicationStatus = receivableReconcilicationStatus;
    }

    public Integer getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Integer vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getVehicleToVendor() {
        return vehicleToVendor;
    }

    public void setVehicleToVendor(Integer vehicleToVendor) {
        this.vehicleToVendor = vehicleToVendor;
    }

    public Integer getVehicleToVendorName() {
        return vehicleToVendorName;
    }

    public void setVehicleToVendorName(Integer vehicleToVendorName) {
        this.vehicleToVendorName = vehicleToVendorName;
    }

    public String getValuationWayView() {
        if (StringUtils.isNotBlank(this.valuationWayView)) {
            return this.valuationWayView;
        }

        boolean doComplete = true;
        for (ValuationWay valuationWay : valuationWays) {
            if (valuationWay.getValue() != null && "0".equals(valuationWay.getValue())
                && !"initiateRate".equals(valuationWay.getLabelInputName())) {
                doComplete = false;
                break;
            }
        }

        StringBuilder bufferPrev = new StringBuilder();
        StringBuilder bufferNext = new StringBuilder();
        if (doComplete) {
            bufferPrev.append("按");
            for (ValuationWay valuationWay : valuationWays) {
                bufferPrev.append(valuationWay.getLabelName());
                bufferPrev.append("+");
                if (valuationWay.getLabelName() != null && valuationWay.getLabelName().contains("起步")) {
                    if (NumberUtils.compare(this.waybillSource, Waybill.WaybillSource.TRANSFORM_BILL.getCode()) != 0) {
                        bufferNext.append(valuationWay.getLabelName());
                        bufferNext.append(valuationWay.getValue());
                        bufferNext.append("，");
                    }
                } else {
                    bufferNext.append(valuationWay.getValue());
                    bufferNext.append(valuationWay.getLabelName());
                    bufferNext.append("，");
                }
            }
            if (bufferPrev.length() > 0 && bufferNext.length() > 0) {
                bufferPrev.deleteCharAt(bufferPrev.length() - 1).append("计费，")
                    .append(bufferNext.deleteCharAt(bufferNext.length() - 1)).append("");
                valuationWayView = bufferPrev.toString();
            }
        } else {
            bufferNext.append("按");
            for (ValuationWay valuationWay : valuationWays) {
                bufferNext.append(valuationWay.getLabelName());
                bufferNext.append("+");
            }
            if (bufferNext.length() > 1) {
                bufferNext.deleteCharAt(bufferNext.length() - 1).append("计费，待填写工作量");
                valuationWayView = bufferNext.toString();
            }
        }
        return valuationWayView;
    }

    public void setValuationWayView(String valuationWayView) {
        this.valuationWayView = valuationWayView;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getContractToCompanyCreditCode() {
        return contractToCompanyCreditCode;
    }

    public void setContractToCompanyCreditCode(String contractToCompanyCreditCode) {
        this.contractToCompanyCreditCode = contractToCompanyCreditCode;
    }

    public String getPayToCompanyCreditCode() {
        return payToCompanyCreditCode;
    }

    public void setPayToCompanyCreditCode(String payToCompanyCreditCode) {
        this.payToCompanyCreditCode = payToCompanyCreditCode;
    }

    public static void main(String[] args) {
        Waybill waybill = new Waybill();
        List<ValuationWay> valuationWays = new ArrayList<ValuationWay>();

        ValuationWay w0 = new ValuationWay();
        w0.setLabelName("起步价");
        w0.setLabelInputName("initiateRate");
        w0.setValue("0");
        valuationWays.add(w0);

        ValuationWay w = new ValuationWay();
        w.setLabelName("吨");
        w.setValue("2");
        valuationWays.add(w);

        ValuationWay w1 = new ValuationWay();
        w1.setLabelName("点位");
        w1.setValue("3");
        valuationWays.add(w1);

        waybill.setWaybillSource(1);
        waybill.setValuationWays(valuationWays);

        System.out.println(waybill.getValuationWayView());
    }

    public Integer getPayToCompany() {
        return payToCompany;
    }

    public void setPayToCompany(Integer payToCompany) {
        this.payToCompany = payToCompany;
    }

    public Integer getProjectManagerUserId() {
        return projectManagerUserId;
    }

    public void setProjectManagerUserId(Integer projectManagerUserId) {
        this.projectManagerUserId = projectManagerUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getProjectManagerUserName() {
        return projectManagerUserName;
    }

    public void setProjectManagerUserName(String projectManagerUserName) {
        this.projectManagerUserName = projectManagerUserName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getCreateUserPhone() {
        return createUserPhone;
    }

    public void setCreateUserPhone(String createUserPhone) {
        this.createUserPhone = createUserPhone;
    }

    public String getProjectManagerUserPhone() {
        return projectManagerUserPhone;
    }

    public void setProjectManagerUserPhone(String projectManagerUserPhone) {
        this.projectManagerUserPhone = projectManagerUserPhone;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Waybill waybill = (Waybill) o;
        return isDriverHasRead == waybill.isDriverHasRead &&
                selfWaybill == waybill.selfWaybill &&
                priceExceptionFlag == waybill.priceExceptionFlag &&
                ownerAreaCanOperate == waybill.ownerAreaCanOperate &&
                shareAreaCanOperate == waybill.shareAreaCanOperate &&
                allowCancel == waybill.allowCancel &&
                allowChangeCar == waybill.allowChangeCar &&
                allowSendCar == waybill.allowSendCar &&
                showYourPrice == waybill.showYourPrice &&
                asProjectWaybillHandle == waybill.asProjectWaybillHandle &&
                isCompleteWorkload == waybill.isCompleteWorkload &&
                Objects.equals(departmentId, waybill.departmentId) &&
                Objects.equals(reconciliationNo, waybill.reconciliationNo) &&
                Objects.equals(receiveWay, waybill.receiveWay) &&
                Objects.equals(waybillId, waybill.waybillId) &&
                Objects.equals(truckCustomerId, waybill.truckCustomerId) &&
                Objects.equals(consignorAccountId, waybill.consignorAccountId) &&
                Objects.equals(truckCustomerName, waybill.truckCustomerName) &&
                Objects.equals(truckId, waybill.truckId) &&
                Objects.equals(plateNumber, waybill.plateNumber) &&
                Objects.equals(driverId, waybill.driverId) &&
                Objects.equals(driverName, waybill.driverName) &&
                Objects.equals(driverType, waybill.driverType) &&
                Objects.equals(customerId, waybill.customerId) &&
                Objects.equals(projectId, waybill.projectId) &&
                Objects.equals(projectName, waybill.projectName) &&
                Objects.equals(businessBranch, waybill.businessBranch) &&
                Objects.equals(waybillNo, waybill.waybillNo) &&
                Objects.equals(status, waybill.status) &&
                Objects.equals(regionCode, waybill.regionCode) &&
                Objects.equals(statusView, waybill.statusView) &&
                Objects.equals(estimateDistance, waybill.estimateDistance) &&
                Objects.equals(estimateTimeConsumption, waybill.estimateTimeConsumption) &&
                Objects.equals(estimateFreight, waybill.estimateFreight) &&
                Objects.equals(calculatedFreight, waybill.calculatedFreight) &&
                Objects.equals(rebateRate, waybill.rebateRate) &&
                Objects.equals(afterTaxFreight, waybill.afterTaxFreight) &&
                Objects.equals(show4DriverFreight, waybill.show4DriverFreight) &&
                Objects.equals(planDeliveryTime, waybill.planDeliveryTime) &&
                Objects.equals(deliveryTime, waybill.deliveryTime) &&
                Objects.equals(waybillRemark, waybill.waybillRemark) &&
                Objects.equals(receiptType, waybill.receiptType) &&
                Objects.equals(receivingTime, waybill.receivingTime) &&
                Objects.equals(finishTime, waybill.finishTime) &&
                Objects.equals(arriveDepotTime, waybill.arriveDepotTime) &&
                Objects.equals(orderNo, waybill.orderNo) &&
                Objects.equals(isLate, waybill.isLate) &&
                Objects.equals(compareResult, waybill.compareResult) &&
                Objects.equals(paymentRoute, waybill.paymentRoute) &&
                Objects.equals(updateFreightRemark, waybill.updateFreightRemark) &&
                Objects.equals(waybillCancelRemark, waybill.waybillCancelRemark) &&
                Objects.equals(assignWaybillRemark, waybill.assignWaybillRemark) &&
                Objects.equals(waybillSource, waybill.waybillSource) &&
                Objects.equals(customerName, waybill.customerName) &&
                Objects.equals(needReceipt, waybill.needReceipt) &&
                Objects.equals(actualMileage, waybill.actualMileage) &&
                Objects.equals(waybillUnbundlingReason, waybill.waybillUnbundlingReason) &&
                Objects.equals(tolls, waybill.tolls) &&
                Objects.equals(isSubmitMatch, waybill.isSubmitMatch) &&
                Objects.equals(customerManagerId, waybill.customerManagerId) &&
                Objects.equals(customerManagerName, waybill.customerManagerName) &&
                Objects.equals(assignCarFeedback, waybill.assignCarFeedback) &&
                Objects.equals(cancelChannel, waybill.cancelChannel) &&
                Objects.equals(entryLicense, waybill.entryLicense) &&
                Objects.equals(goodsWeight, waybill.goodsWeight) &&
                Objects.equals(goodsVolume, waybill.goodsVolume) &&
                Objects.equals(vehicleBoxType, waybill.vehicleBoxType) &&
                Objects.equals(isChangeDeliveryPoint, waybill.isChangeDeliveryPoint) &&
                Objects.equals(areaCode, waybill.areaCode) &&
                Objects.equals(tenantId, waybill.tenantId) &&
                Objects.equals(tenantCode, waybill.tenantCode) &&
                Objects.equals(flightUsageId, waybill.flightUsageId) &&
                Objects.equals(isTest, waybill.isTest) &&
                Objects.equals(cmEstimateFinishTime, waybill.cmEstimateFinishTime) &&
                Objects.equals(onlyLoadCargo, waybill.onlyLoadCargo) &&
                Objects.equals(needDeliveryPointNote, waybill.needDeliveryPointNote) &&
                Objects.equals(updateFreightAuditStatus, waybill.updateFreightAuditStatus) &&
                Objects.equals(freightToBeAudited, waybill.freightToBeAudited) &&
                Objects.equals(updateFreightAuditRemark, waybill.updateFreightAuditRemark) &&
                Objects.equals(reconciliationStatus, waybill.reconciliationStatus) &&
                Objects.equals(settlementStatus, waybill.settlementStatus) &&
                Objects.equals(receiptStatus, waybill.receiptStatus) &&
                Objects.equals(vendorId, waybill.vendorId) &&
                Objects.equals(vendorName, waybill.vendorName) &&
                Objects.equals(roadMapId, waybill.roadMapId) &&
                Objects.equals(roadMapName, waybill.roadMapName) &&
                Objects.equals(receivableReconcilicationNo, waybill.receivableReconcilicationNo) &&
                Objects.equals(receivableReconcilicationStatus, waybill.receivableReconcilicationStatus) &&
                Objects.equals(vehicleType, waybill.vehicleType) &&
                Objects.equals(vehicleToVendor, waybill.vehicleToVendor) &&
                Objects.equals(vehicleToVendorName, waybill.vehicleToVendorName) &&
                Objects.equals(waybillSourceText, waybill.waybillSourceText) &&
                Objects.equals(customerManagerMobile, waybill.customerManagerMobile) &&
                Objects.equals(flightId, waybill.flightId) &&
                Objects.equals(location, waybill.location) &&
                Objects.equals(deviceNo, waybill.deviceNo) &&
                Objects.equals(deviceType, waybill.deviceType) &&
                Objects.equals(referenceFreight, waybill.referenceFreight) &&
                Objects.equals(miniFreight, waybill.miniFreight) &&
                Objects.equals(rebateFee, waybill.rebateFee) &&
                Objects.equals(vehicleId, waybill.vehicleId) &&
                Objects.equals(amsDriverId, waybill.amsDriverId) &&
                Objects.equals(areaName, waybill.areaName) &&
                Objects.equals(receiptTypeText, waybill.receiptTypeText) &&
                Objects.equals(statusViewText, waybill.statusViewText) &&
                Objects.equals(driverTypeName, waybill.driverTypeName) &&
                Objects.equals(needReceiptText, waybill.needReceiptText) &&
                Objects.equals(cancelChannelText, waybill.cancelChannelText) &&
                Objects.equals(valuationConstJson, waybill.valuationConstJson) &&
                Objects.equals(valuationWays, waybill.valuationWays) &&
                Objects.equals(valuationWayView, waybill.valuationWayView) &&
                Objects.equals(waybillIds, waybill.waybillIds) &&
                Objects.equals(cmEstimateFinishTimeStr, waybill.cmEstimateFinishTimeStr) &&
                Objects.equals(logicAreaCode, waybill.logicAreaCode) &&
                Objects.equals(logicAreaCodeName, waybill.logicAreaCodeName) &&
                Objects.equals(loadingTime, waybill.loadingTime) &&
                Objects.equals(driverArrivedLoadingTime, waybill.driverArrivedLoadingTime) &&
                Objects.equals(shippingTime, waybill.shippingTime) &&
                Objects.equals(actualMileageErrorStr, waybill.actualMileageErrorStr) &&
                Objects.equals(tenantName, waybill.tenantName) &&
                Objects.equals(whoWriteWork, waybill.whoWriteWork) &&
                Objects.equals(logisticsLabel, waybill.logisticsLabel) &&
                Objects.equals(logisticsName, waybill.logisticsName) &&
                Objects.equals(payToCompany, waybill.payToCompany) &&
                Objects.equals(projectManagerUserId, waybill.projectManagerUserId) &&
                Objects.equals(createUserName, waybill.createUserName) &&
                Objects.equals(createUserPhone, waybill.createUserPhone) &&
                Objects.equals(projectManagerUserName, waybill.projectManagerUserName) &&
                Objects.equals(projectManagerUserPhone, waybill.projectManagerUserPhone) &&
                Objects.equals(lastCustomerFreightWithTax, waybill.lastCustomerFreightWithTax) &&
                Objects.equals(lastVendorFreightWithTax, waybill.lastVendorFreightWithTax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, reconciliationNo, receiveWay, waybillId, truckCustomerId, consignorAccountId, truckCustomerName, truckId, plateNumber, driverId, driverName, driverType, customerId, projectId, projectName, businessBranch, waybillNo, status, regionCode, statusView, estimateDistance, estimateTimeConsumption, estimateFreight, calculatedFreight, rebateRate, afterTaxFreight, show4DriverFreight, planDeliveryTime, deliveryTime, waybillRemark, receiptType, receivingTime, finishTime, arriveDepotTime, orderNo, isLate, compareResult, paymentRoute, updateFreightRemark, waybillCancelRemark, assignWaybillRemark, waybillSource, customerName, needReceipt, actualMileage, waybillUnbundlingReason, tolls, isSubmitMatch, customerManagerId, customerManagerName, assignCarFeedback, cancelChannel, entryLicense, goodsWeight, goodsVolume, vehicleBoxType, isChangeDeliveryPoint, areaCode, tenantId, tenantCode, flightUsageId, isTest, cmEstimateFinishTime, onlyLoadCargo, needDeliveryPointNote, updateFreightAuditStatus, freightToBeAudited, updateFreightAuditRemark, reconciliationStatus, isDriverHasRead, settlementStatus, receiptStatus, vendorId, vendorName, roadMapId, roadMapName, receivableReconcilicationNo, receivableReconcilicationStatus, vehicleType, vehicleToVendor, vehicleToVendorName, waybillSourceText, customerManagerMobile, flightId, selfWaybill, location, deviceNo, deviceType, referenceFreight, miniFreight, rebateFee, vehicleId, amsDriverId, areaName, receiptTypeText, statusViewText, driverTypeName, needReceiptText, cancelChannelText, valuationConstJson, valuationWays, valuationWayView, waybillIds, cmEstimateFinishTimeStr, priceExceptionFlag, logicAreaCode, logicAreaCodeName, loadingTime, driverArrivedLoadingTime, shippingTime, ownerAreaCanOperate, shareAreaCanOperate, allowCancel, allowChangeCar, actualMileageErrorStr, allowSendCar, tenantName, showYourPrice, asProjectWaybillHandle, whoWriteWork, isCompleteWorkload, logisticsLabel, logisticsName, payToCompany, projectManagerUserId, createUserName, createUserPhone, projectManagerUserName, projectManagerUserPhone, lastCustomerFreightWithTax, lastVendorFreightWithTax);
    }
}