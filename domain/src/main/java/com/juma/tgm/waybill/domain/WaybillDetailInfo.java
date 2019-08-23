package com.juma.tgm.waybill.domain;

import com.juma.tgm.task.vo.manage.TaskLinkWaybill;
import com.juma.tgm.waybill.vo.WaybillCostInformationVo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.juma.tgm.configure.domain.FreightFactor;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.customer.domain.TruckCustomer;
import com.juma.tgm.customerManager.domain.FixedDemand;
import com.juma.tgm.customerManager.domain.FixedDemandTruck;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.driver.domain.ReportInfoDetails;
import com.juma.tgm.imageUploadManage.domain.ImageUploadManage;
import com.juma.tgm.project.domain.ProjectFreightRule;
import com.juma.tgm.project.domain.ValuationWay;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.domain.bo.DriverTruckInfoBo;
import com.juma.tgm.waybill.domain.bo.VendorBo;

public class WaybillDetailInfo implements Serializable {

    private static final long serialVersionUID = -3724920550707592614L;
    private static final Logger log = LoggerFactory.getLogger(WaybillDetailInfo.class);
    private Waybill waybill;
    private WaybillParam waybillParam;
    private List<WaybillDeliveryAddress> waybillDeliveryAddresses;
    private List<WaybillReceiveAddress> waybillReceiveAddresses;
    // 配送地与货物信息
    private List<WaybillReceiveAddressResponse> waybillReceiveAddressResponseList;
    private DriverTruckInfoBo driverTruckInfoBo;
    private TruckRequire truckRequire;
    private CustomerInfo customerInfo;
    private VendorBo vendorBo;
    private TaskLinkWaybill task;
    // 运单费用信息
    private WaybillCostInformationVo waybillCostInformationVo;
    /**
     * 发单人
     */
    @Deprecated
    private TruckCustomer truckCustomer;
    /**
     * 用车人
     */
    @Deprecated
    private TruckCustomer truckCustomerForUserCar;
    /**
     * 配送单
     */
    private List<DeliveryPointSupplement> deliveryPointSupplementList = new ArrayList<DeliveryPointSupplement>();
    /**
     * 回单
     */
    private List<ImageUploadManage> receiptImageList = new ArrayList<ImageUploadManage>();
    /**
     * 货物单
     */
    private List<ImageUploadManage> goodsImageList = new ArrayList<ImageUploadManage>();
    private Truck truck;
    private Driver driver;
    private Integer customerId;
    /**
     * 冷链是否选择
     */
    private Integer coldChain;
    /**
     * 已通知司机数
     */
    private Integer driverNotice;
    /**
     * 货物信息
     */
    private String goodsInfoStr;
    /**
     * 用车要求全称
     */
    private String truckRequireStr;
    /**
     * 车型
     */
    private String truckTypeName;

    /**
     * 箱型
     */
    private String vehicleBoxName;
    /**
     * 最新位置
     */
    private String position;
    /**
     * 预估完成时间
     */
    private String estimateFinishTime;

    /**
     * 运单等待超时长度
     */
    private Long expireTimeLength;

    /**
     * 能否使用客户信息
     */
    private Boolean canUseCustomerInfo;

    /**
     * 运单报备信息
     */
    private List<ReportInfoDetails> reportInfoList;

    /**
     * 回单信息
     */
    private List<ImageUploadManage> receiptManageList;

    /**
     * 兼容固定需求下单
     * 用车时间点
     */
    private String deliveryTimePoint;

    /**
     * 兼容固定需求下单
     * 预估完成时间点
     */
    private Integer finishTimePoint;

    /**
     * 兼容固定需求下单
     * 用车数量
     */
    private Integer vehicleCount;


    /**
     * 项目运单-计价规则
     */
    private ProjectFreightRule projectFreightRule;

    /**
     * 项目运单-计价方式
     */
    private List<ValuationWay> valuationWays = new ArrayList<ValuationWay>();

    /**
     * 项目  计价方式  系数
     */
    private float valuationConst;

    /**
     * 是否是自己的单子
     */
    private Integer currentLoginUserId;

    /**
     * 能否换车
     */
    private boolean allowChangeCar;

    private FixedDemand.BillStrategy billStrategy;

    /**
     * 是否自动下单
     * 1：否
     * 2：是
     */
    private Integer isAutoCreateBill;

    /**
     * 固定需车辆
     */
    private List<FixedDemandTruck> fixedDemandTrucks;

    /**
     * 用车数量
     */
    private Integer createBatchAmount;

    /**
     * 计价方式
     */
    private List<FreightFactor> allFactors;

    /**
     * 客服电话
     */
    private String customerServiceTel;


    /***
     *
     * 承运商联系人
     *
     * */
    private String contactUserName;

    /**
     * 计价方式
     */
    private Integer valuationWay;

    /**
     * 承运商联系人电话
     *
     * */
    private String contactUserPhone;

    /**
     * 谁填写工作量 1:司机  2:经济人
     */
    private Integer whoWriteWork;

    public TaskLinkWaybill getTask() {
        return task;
    }

    public void setTask(TaskLinkWaybill task) {
        this.task = task;
    }

    public Integer getWhoWriteWork() {
        return whoWriteWork;
    }

    public void setWhoWriteWork(Integer whoWriteWork) {
        this.whoWriteWork = whoWriteWork;
    }

    public String getContactUserName() {
        return contactUserName;
    }

    public void setContactUserName(String contactUserName) {
        this.contactUserName = contactUserName;
    }


    public String getCustomerServiceTel() {
        return customerServiceTel;
    }

    public String getContactUserPhone() {
        return contactUserPhone;
    }

    public void setContactUserPhone(String contactUserPhone) {
        this.contactUserPhone = contactUserPhone;
    }

    public void setCustomerServiceTel(String customerServiceTel) {
        this.customerServiceTel = customerServiceTel;
    }

    public Integer getCurrentLoginUserId() {
        return currentLoginUserId;
    }

    public void setCurrentLoginUserId(Integer currentLoginUserId) {
        this.currentLoginUserId = currentLoginUserId;
    }

    public boolean isAllowChangeCar() {
        return allowChangeCar;
    }

    public void setAllowChangeCar(boolean allowChangeCar) {
        this.allowChangeCar = allowChangeCar;
    }

    public ProjectFreightRule getProjectFreightRule() {
        return projectFreightRule;
    }

    public void setProjectFreightRule(ProjectFreightRule projectFreightRule) {
        this.projectFreightRule = projectFreightRule;
    }

    public Boolean getCanUseCustomerInfo() {
        return canUseCustomerInfo;
    }

    public void setCanUseCustomerInfo(Boolean canUseCustomerInfo) {
        this.canUseCustomerInfo = canUseCustomerInfo;
    }

    public Long getExpireTimeLength() {
        return expireTimeLength;
    }

    public void setExpireTimeLength(Long expireTimeLength) {
        this.expireTimeLength = expireTimeLength;
    }

    public Waybill getWaybill() {
        return waybill;
    }

    public void setWaybill(Waybill waybill) {
        this.waybill = waybill;
    }

    public WaybillParam getWaybillParam() {
        return waybillParam;
    }

    public void setWaybillParam(WaybillParam waybillParam) {
        this.waybillParam = waybillParam;
    }

    public List<WaybillDeliveryAddress> getWaybillDeliveryAddresses() {
        return waybillDeliveryAddresses;
    }

    public void setWaybillDeliveryAddresses(List<WaybillDeliveryAddress> waybillDeliveryAddresses) {
        this.waybillDeliveryAddresses = waybillDeliveryAddresses;
    }

    public List<WaybillReceiveAddress> getWaybillReceiveAddresses() {
        return waybillReceiveAddresses;
    }

    public void setWaybillReceiveAddresses(List<WaybillReceiveAddress> waybillReceiveAddresses) {
        this.waybillReceiveAddresses = waybillReceiveAddresses;
    }

    public List<WaybillReceiveAddressResponse> getWaybillReceiveAddressResponseList() {
        return waybillReceiveAddressResponseList;
    }

    public void setWaybillReceiveAddressResponseList(
            List<WaybillReceiveAddressResponse> waybillReceiveAddressResponseList) {
        this.waybillReceiveAddressResponseList = waybillReceiveAddressResponseList;
    }

    public DriverTruckInfoBo getDriverTruckInfoBo() {
        return driverTruckInfoBo;
    }

    public void setDriverTruckInfoBo(DriverTruckInfoBo driverTruckInfoBo) {
        this.driverTruckInfoBo = driverTruckInfoBo;
    }

    public TruckRequire getTruckRequire() {
        return truckRequire;
    }

    public void setTruckRequire(TruckRequire truckRequire) {
        this.truckRequire = truckRequire;
    }

    public TruckCustomer getTruckCustomer() {
        return truckCustomer;
    }

    public void setTruckCustomer(TruckCustomer truckCustomer) {
        this.truckCustomer = truckCustomer;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public VendorBo getVendorBo() {
        return vendorBo;
    }

    public void setVendorBo(VendorBo vendorBo) {
        this.vendorBo = vendorBo;
    }

    public WaybillCostInformationVo getWaybillCostInformationVo() {
        return waybillCostInformationVo;
    }

    public void setWaybillCostInformationVo(WaybillCostInformationVo waybillCostInformationVo) {
        this.waybillCostInformationVo = waybillCostInformationVo;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getColdChain() {
        return coldChain;
    }

    public void setColdChain(Integer coldChain) {
        this.coldChain = coldChain;
    }

    public Integer getDriverNotice() {
        return driverNotice;
    }

    public void setDriverNotice(Integer driverNotice) {
        this.driverNotice = driverNotice;
    }

    public String getGoodsInfoStr() {
        return goodsInfoStr;
    }

    public void setGoodsInfoStr(String goodsInfoStr) {
        this.goodsInfoStr = goodsInfoStr;
    }

    public String getTruckRequireStr() {
        return truckRequireStr;
    }

    public void setTruckRequireStr(String truckRequireStr) {
        this.truckRequireStr = truckRequireStr;
    }

    public String getTruckTypeName() {
        return truckTypeName;
    }

    public void setTruckTypeName(String truckTypeName) {
        this.truckTypeName = truckTypeName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEstimateFinishTime() {
        return estimateFinishTime;
    }

    public void setEstimateFinishTime(String estimateFinishTime) {
        this.estimateFinishTime = estimateFinishTime;
    }

    public TruckCustomer getTruckCustomerForUserCar() {
        return truckCustomerForUserCar;
    }

    public void setTruckCustomerForUserCar(TruckCustomer truckCustomerForUserCar) {
        this.truckCustomerForUserCar = truckCustomerForUserCar;
    }

    public List<ImageUploadManage> getReceiptImageList() {
        return receiptImageList;
    }

    public void setReceiptImageList(List<ImageUploadManage> receiptImageList) {
        this.receiptImageList = receiptImageList;
    }

    public List<ImageUploadManage> getGoodsImageList() {
        return goodsImageList;
    }

    public void setGoodsImageList(List<ImageUploadManage> goodsImageList) {
        this.goodsImageList = goodsImageList;
    }

    public List<DeliveryPointSupplement> getDeliveryPointSupplementList() {
        return deliveryPointSupplementList;
    }

    public void setDeliveryPointSupplementList(List<DeliveryPointSupplement> deliveryPointSupplementList) {
        this.deliveryPointSupplementList = deliveryPointSupplementList;
    }

    public List<ReportInfoDetails> getReportInfoList() {
        if (CollectionUtils.isEmpty(this.reportInfoList)) return null;

        for (ReportInfoDetails ri : this.reportInfoList) {
            ri.setReportDetailId(null);
            ri.setReportInfoId(null);
            ri.setCreateUserId(null);
            ri.setAddressDetail(null);
            ri.setCoordinate(null);
            ri.setDeviceNo(null);
//            ri.setRemark(null);
            ri.setDeviceType(null);
//            ri.setImageUrl(null);
//            ri.setImageUrlList(null);
        }
        return reportInfoList;
    }

    public void setReportInfoList(List<ReportInfoDetails> reportInfoList) {
        this.reportInfoList = reportInfoList;
    }

    public List<ImageUploadManage> getReceiptManageList() {
        if (CollectionUtils.isEmpty(this.receiptManageList)) return null;

        for (ImageUploadManage rm : this.receiptManageList) {
            rm.setCreateTime(null);
            rm.setCreateUserId(null);
            rm.setRelationId(null);
            rm.setImageUploadManageId(null);
            rm.setImageUploadManageSign(null);

        }

        return receiptManageList;
    }

    public void setReceiptManageList(List<ImageUploadManage> receiptManageList) {
        this.receiptManageList = receiptManageList;
    }


    public String getDeliveryTimePoint() {
        return deliveryTimePoint;
    }

    public void setDeliveryTimePoint(String deliveryTimePoint) {
        this.deliveryTimePoint = deliveryTimePoint;
    }

    public Integer getVehicleCount() {
        return vehicleCount;
    }

    public void setVehicleCount(Integer vehicleCount) {
        this.vehicleCount = vehicleCount;
    }

    public Integer getFinishTimePoint() {
        return finishTimePoint;
    }

    public void setFinishTimePoint(Integer finishTimePoint) {
        this.finishTimePoint = finishTimePoint;
    }

    public float getValuationConst() {
        return valuationConst;
    }

    public void setValuationConst(float valuationConst) {
        this.valuationConst = valuationConst;
    }

    /**
     * 是否为自己发的单子
     *
     * @return
     */
    public Boolean isSelfPublish() {
        if (this.waybill == null) return false;
        if (this.getCurrentLoginUserId() == null) return false;

        if (NumberUtils.compare(this.waybill.getCreateUserId(), this.getCurrentLoginUserId()) == 0) return true;

        return false;
    }

    public List<ValuationWay> getValuationWays() {
        return valuationWays;
    }

    public void setValuationWays(List<ValuationWay> valuationWays) {
        this.valuationWays = valuationWays;
    }

    public FixedDemand.BillStrategy getBillStrategy() {
        return billStrategy;
    }

    public void setBillStrategy(FixedDemand.BillStrategy billStrategy) {
        this.billStrategy = billStrategy;
    }

    public Integer getIsAutoCreateBill() {
        return isAutoCreateBill;
    }

    public void setIsAutoCreateBill(Integer isAutoCreateBill) {
        this.isAutoCreateBill = isAutoCreateBill;
    }

    public List<FixedDemandTruck> getFixedDemandTrucks() {
        return fixedDemandTrucks;
    }

    public void setFixedDemandTrucks(List<FixedDemandTruck> fixedDemandTrucks) {
        this.fixedDemandTrucks = fixedDemandTrucks;
    }

    public Integer getCreateBatchAmount() {
        return createBatchAmount;
    }

    public void setCreateBatchAmount(Integer createBatchAmount) {
        this.createBatchAmount = createBatchAmount;
    }

    public List<FreightFactor> getAllFactors() {
        return allFactors;
    }

    public void setAllFactors(List<FreightFactor> allFactors) {
        this.allFactors = allFactors;
    }

    public String getVehicleBoxName() {
        return vehicleBoxName;
    }

    public void setVehicleBoxName(String vehicleBoxName) {
        this.vehicleBoxName = vehicleBoxName;
    }

    public Integer getValuationWay() {
        return valuationWay;
    }

    public void setValuationWay(Integer valuationWay) {
        this.valuationWay = valuationWay;
    }

    /**
     * 项目单运单状态
     *
     * @return
     */
    public String getProjectWayBillStatusView() {
        if (this.waybill == null) return null;
        if (this.waybill.getStatusView() == null) return null;

//        if (this.waybill.getStatus() == null) return null;

//        if (this.waybill.getStatusView() != null && this.waybill.getReconciliationStatus() != null) {

//            //已完成+未对账=未对账
////            if (NumberUtils.compare(this.waybill.getReconciliationStatus(), Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode()) == 0 && NumberUtils.compare(this.waybill.getStatus(), Waybill.Status.PAIED.getCode()) == 0) {
////                return Waybill.StatusView.UNCHECK.getCode();
////            }
////            //已完成+已对账=待支付
////            if ((NumberUtils.compare(this.waybill.getReconciliationStatus(), Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode()) == 0 && NumberUtils.compare(this.waybill.getStatus(), Waybill.Status.PAIED.getCode()) == 0 || (NumberUtils.compare(this.waybill.getStatus(), Waybill.Status.DELIVERIED.getCode()) == 0))) {
////                return Waybill.StatusView.WATING_PAY.getCode();
////            }
////            //已完成+已结算=已结算
////            if (NumberUtils.compare(this.waybill.getReceiptStatus(), Waybill.ReceiptStatus.HAS_COLLECTION.getCode()) == 0 && NumberUtils.compare(this.waybill.getStatus(), Waybill.Status.PAIED.getCode()) == 0) {
////                return Waybill.StatusView.CLOSED.getCode();
////            }

//        }

//        if (this.waybill.getStatusView() == null) return null;
//
//        //配送完成返回对账和收款状态
//        if ((NumberUtils.compare(this.waybill.getStatusView(), Waybill.StatusView.FINISH.getCode()) == 0) && this.waybill.getReconciliationStatus() != null) {
//            //未对账完成展示对账状态
//            if (NumberUtils.compare(this.waybill.getReconciliationStatus(), Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode()) != 0) {
//                Waybill.ReconciliationStatus reconciliationStatus = Waybill.ReconciliationStatus.getReconciliationStatusByCode(this.waybill.getReconciliationStatus());
//                if (reconciliationStatus == null) {
//                    log.info("对账状态码错误,运单号:{}", this.waybill.getWaybillId());
//                    return null;
//                }
//
//                return reconciliationStatus.getDesc();
//            }
//            //对账完成展示收款状态
//            if ((NumberUtils.compare(this.waybill.getReconciliationStatus(), Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode()) == 0) && this.waybill.getReceiptStatus() != null) {
//                Waybill.ReceiptStatus receiptStatus = Waybill.ReceiptStatus.getReceiptStatusByCode(this.waybill.getReceiptStatus());
//                if (receiptStatus == null) {
//                    log.info("收款状态码错误,运单号:{}", this.waybill.getWaybillId());
//                }
//
//                return receiptStatus.getDesc();
//            }
//        }

        //配送状态未完成则直接返回
        return Waybill.StatusView.fromInt(this.waybill.getStatusView()).getDescr();

    }


    /**
     * 承运商结算状态
     *
     * @return
     */
    public String getSettlementStatusName() {
        if (this.waybill == null) return null;
        if (this.waybill.getSettlementStatus() == null) return null;
        //对账完成展示结算状态
        if(NumberUtils.compare(this.waybill.getReconciliationStatus(), Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode()) != 0) return null;

        if (this.waybill.getSettlementStatus() == Waybill.SettlementStatus.INIT.getCode()) {
            return null;
        }
        
        Waybill.SettlementStatus settlementStatus = Waybill.SettlementStatus.getSettlementStatusByCode(this.waybill.getSettlementStatus());
        if (settlementStatus == null) {
            log.info("收款状态码错误,运单号:{}", this.waybill.getWaybillId());
            return null;
        }
        return settlementStatus.getDesc();
    }

    /**
     * 承运商对账状态
     *
     * @return
     */
    public String getReconciliationStatusName() {
        if (this.waybill == null)
            return null;
        if (this.waybill.getReconciliationStatus() == null)
            return null;
        // 配送完成之后才展示对账状态
        if (NumberUtils.compare(this.waybill.getStatusView(), Waybill.StatusView.FINISH.getCode()) != 0)
            return null;

        Waybill.ReconciliationStatus reconciliationStatus = Waybill.ReconciliationStatus
                .getReconciliationStatusByCode(this.waybill.getReconciliationStatus());
        if (reconciliationStatus == null) {
            log.info("对账状态码错误,运单号:{}", this.waybill.getWaybillId());
            return null;
        }

        if (reconciliationStatus.getCode() == Waybill.ReconciliationStatus.INIT.getCode()) {
            return null;
        }

        return reconciliationStatus.getDescr();
    }

    /**
     * 客户对账状态
     *
     * @return
     */
    public String getReceivableReconcilicationStatusName() {
        if (this.waybill == null)
            return null;
        if (this.waybill.getReceivableReconcilicationStatus() == null)
            return null;
        // 配送完成之后才展示对账状态
        if (NumberUtils.compare(this.waybill.getStatusView(), Waybill.StatusView.FINISH.getCode()) != 0)
            return null;

        Waybill.ReconciliationStatus reconciliationStatus = Waybill.ReconciliationStatus
                .getReconciliationStatusByCode(this.waybill.getReceivableReconcilicationStatus());
        if (reconciliationStatus == null) {
            log.info("对账状态码错误,运单号:{}", this.waybill.getWaybillId());
            return null;
        }

        if (reconciliationStatus.getCode() == Waybill.ReconciliationStatus.INIT.getCode()) {
            return null;
        }

        return reconciliationStatus.getDescr();
    }

    /**
     * 客户收款状态
     *
     * @return
     */
    public String getReceiptStatusName() {
        if (this.waybill == null) return null;
        if (this.waybill.getReceiptStatus() == null) return null;

        if(NumberUtils.compare(this.waybill.getReceivableReconcilicationStatus(), Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode()) != 0) return null;

        if (this.waybill.getReceiptStatus() == Waybill.ReceiptStatus.INIT.getCode()) {
            return null;
        }

        Waybill.ReceiptStatus receiptStatus = Waybill.ReceiptStatus.getReceiptStatusByCode(this.waybill.getReceiptStatus());
        if (receiptStatus == null) {
            log.info("收款状态码错误,运单号:{}", this.waybill.getWaybillId());
            return null;
        }

        return receiptStatus.getDesc();
    }



}
