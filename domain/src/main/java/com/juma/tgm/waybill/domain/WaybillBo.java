package com.juma.tgm.waybill.domain;

import com.juma.tgm.crm.domain.CrmCustomerInfo;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.customer.domain.TruckCustomer;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.imageUploadManage.domain.ImageUploadManage;
import com.juma.tgm.project.vo.v2.ProjectVo;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.domain.TruckType;
import com.juma.tgm.waybill.domain.transformbill.TransformBillMark;
import com.juma.tgm.waybill.domain.vo.WaybillCarrierVo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class WaybillBo extends Waybill implements TransformBillMark {

    private static final long serialVersionUID = -5061971986180160119L;
    private Waybill waybill;
    private List<WaybillDeliveryAddress> deliveryAddress = new ArrayList<WaybillDeliveryAddress>();
    private List<WaybillReceiveAddress> receiveAddress = new ArrayList<WaybillReceiveAddress>();
    // 配送地对应多货物信息
    private List<WaybillReceiveAddressResponse> listReceiveAddressResponse = new ArrayList<WaybillReceiveAddressResponse>();
    private List<DeliveryPointSupplement> deliveryPointSupplementList = new ArrayList<DeliveryPointSupplement>();
    private List<ImageUploadManage> receiptImageList = new ArrayList<ImageUploadManage>();
    
    private TruckRequire truckRequire;
    private Driver driver;
    private com.juma.vms.driver.domain.Driver vmsDriver;
    private TruckCustomer truckCustomer;
    // 发单的客户经理(建单人)
    private TruckCustomer manageCustomer;
    private Truck truck;
    private TruckType truckType;
    private CustomerInfo customerInfo;
    // crm的企业客户
    private List<CrmCustomerInfo> crmCustomerInfoList;
    // 项目信息
    private ProjectVo projectVo;
    
    private WaybillParam waybillParam;
    /** 客户经理姓名(企业客户) */
    private String customerManageName;
    /** 客户经理电话(企业客户) */
    private String customerManagePhone;
    /** 客服电话 */
    private String customerServiceTel;
    /** 匹配异常原因 */
    private String filterReason;
    /** 匹配状态 1:已匹配；2、未匹配 */
    private Integer matchStatus;
    /** 车型 */
    private String truckTypeName;
    /** 司机名字 */
    private String driverName;
    /** 车的位置*/
    private String coordinates;
    /** 司机电话 */
    private String driverPhone;
    /** 用车人名字 */
    private String truckCustomerName;
    /** 用车人电话 */
    private String truckCustomerPhone;
    /** 所属区域 */
    private String region;
    /** 所属区域 */
    private String createDate;
	/** 用车要求 */
    private String truckRequireStr;
    /** 货物信息 */
    private String goodsInfoStr;
    /** 重要信息 */
    private String importantInfoStr;
    /** 车辆信息 */
    private String truckInfo;
    /** 计划配送时间 */
    private String planDeliveryDate;
    /** 取货地 */
    private String deliveryAddressStr;
    /** 目的地 */
    private String receiveAddressStr;

    /** 是否搬运 */
    private String needCarry;
    /** 是否返仓 */
    private String needBackStorage;
    /** 是否代收货款 */
    private String collectionPayment;
    /** 客户用车时间起计算装货耗时 */
    private String loadingCostDate;
    /** 司机到达时间起计算装货耗时 */
    private String driverLoadingCostDate;
    /** 配送耗时 */
    private String deliveryCostDate;
    /** 货主填写地址的配送点数 */
    private Integer pointNos;
    /** 税前费用是否低于系统报价的（1-x）%*/
    private boolean lowerCalculatedFreight = false;
    /** 比较税率*/
    private BigDecimal compareRate;
    /** 结算方式 */
    private String receiptTypeStr;
    /** 是否能编辑搬运费*/
    private boolean allowAddHandlingCost = true;
    // 已完成
    private Integer completedPointNo;
    // 剩余
    private Integer surplusPointNo;
    /** 预估完成时间 */
    private Date estimateFinishTime;
    /** 是否修改车辆,默认没有修改 */
    private boolean hasReplaceCar = false;

    /**
     * 订单批量创建数量
     */
    private Integer createBatchAmount;

    /**
     * 计价方式名字
     */
    private String valuationWayName;
    
    //上传回单否
    private Boolean uploadedReceipt = false;

    /**
     * 转承运商vo
     */
    private WaybillCarrierVo waybillCarrierVo;

    public boolean isAllowAddHandlingCost() {
		return allowAddHandlingCost;
	}

	public void setAllowAddHandlingCost(boolean allowAddHandlingCost) {
		this.allowAddHandlingCost = allowAddHandlingCost;
	}

	public WaybillParam getWaybillParam() {
		return waybillParam;
	}

	public void setWaybillParam(WaybillParam waybillParam) {
		this.waybillParam = waybillParam;
	}

	public BigDecimal getCompareRate() {
		return compareRate;
	}

	public void setCompareRate(BigDecimal compareRate) {
		this.compareRate = compareRate;
	}

	public boolean isLowerCalculatedFreight() {
		return lowerCalculatedFreight;
	}

	public void setLowerCalculatedFreight(boolean lowerCalculatedFreight) {
		this.lowerCalculatedFreight = lowerCalculatedFreight;
	}

	public String getLoadingCostDate() {
		return loadingCostDate;
	}

	public void setLoadingCostDate(String loadingCostDate) {
		this.loadingCostDate = loadingCostDate;
	}

	public Integer getPointNos() {
        return pointNos;
    }

    public void setPointNos(Integer pointNos) {
        this.pointNos = pointNos;
    }

    public String getDeliveryCostDate() {
		return deliveryCostDate;
	}

	public void setDeliveryCostDate(String deliveryCostDate) {
		this.deliveryCostDate = deliveryCostDate;
	}

	public Waybill getWaybill() {
        return waybill;
    }

    public void setWaybill(Waybill waybill) {
        this.waybill = waybill;
    }

    public List<WaybillDeliveryAddress> getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(List<WaybillDeliveryAddress> deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<WaybillReceiveAddress> getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(List<WaybillReceiveAddress> receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public List<WaybillReceiveAddressResponse> getListReceiveAddressResponse() {
        return listReceiveAddressResponse;
    }

    public void setListReceiveAddressResponse(List<WaybillReceiveAddressResponse> listReceiveAddressResponse) {
        this.listReceiveAddressResponse = listReceiveAddressResponse;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public String getTruckTypeName() {
        return truckTypeName;
    }

    public void setTruckTypeName(String truckTypeName) {
        this.truckTypeName = truckTypeName;
    }

    public String getDriverPhone() {
        return StringUtils.isNotBlank(driverPhone) ? driverPhone : (driver == null ? null : driver.getContactPhone());
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getTruckCustomerPhone() {
        return StringUtils.isNotBlank(truckCustomerPhone) ? truckCustomerPhone
                : (truckCustomer == null ? null : truckCustomer.getContactPhone());
    }

    public void setTruckCustomerPhone(String truckCustomerPhone) {
        this.truckCustomerPhone = truckCustomerPhone;
    }

    public Long getSecond() {
        if (this.getPlanDeliveryTime() == null)
            return null;
        return (new Date().getTime() - this.getPlanDeliveryTime().getTime()) / 1000;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public TruckCustomer getTruckCustomer() {
        return truckCustomer;
    }

    public void setTruckCustomer(TruckCustomer truckCustomer) {
        this.truckCustomer = truckCustomer;
    }

    public String getDriverName() {
        return StringUtils.isNotBlank(driverName) ? driverName : (driver == null ? null : driver.getNickname());
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getTruckCustomerName() {
        return StringUtils.isNotBlank(truckCustomerName) ? truckCustomerName
                : (truckCustomer == null ? null : truckCustomer.getNickname());
    }

    public void setTruckCustomerName(String truckCustomerName) {
        this.truckCustomerName = truckCustomerName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public TruckType getTruckType() {
        return truckType;
    }

    public void setTruckType(TruckType truckType) {
        this.truckType = truckType;
    }

    public TruckRequire getTruckRequire() {
        return truckRequire;
    }

    public void setTruckRequire(TruckRequire truckRequire) {
        this.truckRequire = truckRequire;
    }

    public String getTruckRequireStr() {
        return truckRequireStr;
    }

    public void setTruckRequireStr(String truckRequireStr) {
        this.truckRequireStr = truckRequireStr;
    }

    public String getTruckInfo() {
        return truckInfo;
    }

    public void setTruckInfo(String truckInfo) {
        this.truckInfo = truckInfo;
    }

    public String getPlanDeliveryDate() {
        return planDeliveryDate;
    }

    public void setPlanDeliveryDate(String planDeliveryDate) {
        this.planDeliveryDate = planDeliveryDate;
    }

    public String getDeliveryAddressStr() {
        return deliveryAddressStr;
    }

    public void setDeliveryAddressStr(String deliveryAddressStr) {
        this.deliveryAddressStr = deliveryAddressStr;
    }

    public String getReceiveAddressStr() {
        return receiveAddressStr;
    }

    public void setReceiveAddressStr(String receiveAddressStr) {
        this.receiveAddressStr = receiveAddressStr;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public List<CrmCustomerInfo> getCrmCustomerInfoList() {
        return crmCustomerInfoList;
    }

    public void setCrmCustomerInfoList(List<CrmCustomerInfo> crmCustomerInfoList) {
        this.crmCustomerInfoList = crmCustomerInfoList;
    }

    public ProjectVo getProjectVo() {
        return projectVo;
    }

    public void setProjectVo(ProjectVo projectVo) {
        this.projectVo = projectVo;
    }

    public String getCustomerManageName() {
        return customerManageName;
    }

    public void setCustomerManageName(String customerManageName) {
        this.customerManageName = customerManageName;
    }

    public String getCustomerManagePhone() {
        return customerManagePhone;
    }

    public void setCustomerManagePhone(String customerManagePhone) {
        this.customerManagePhone = customerManagePhone;
    }

    public String getFilterReason() {
        return filterReason;
    }

    public void setFilterReason(String filterReason) {
        this.filterReason = filterReason;
    }

    public Integer getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(Integer matchStatus) {
        this.matchStatus = matchStatus;
    }

    public String getNeedCarry() {
        return needCarry;
    }

    public void setNeedCarry(String needCarry) {
        this.needCarry = needCarry;
    }

    public String getNeedBackStorage() {
        return needBackStorage;
    }

    public void setNeedBackStorage(String needBackStorage) {
        this.needBackStorage = needBackStorage;
    }

    public String getCollectionPayment() {
        return collectionPayment;
    }

    public void setCollectionPayment(String collectionPayment) {
        this.collectionPayment = collectionPayment;
    }

    public String getGoodsInfoStr() {
        return goodsInfoStr;
    }

    public void setGoodsInfoStr(String goodsInfoStr) {
        this.goodsInfoStr = goodsInfoStr;
    }

    public String getImportantInfoStr() {
        return importantInfoStr;
    }

    public void setImportantInfoStr(String importantInfoStr) {
        this.importantInfoStr = importantInfoStr;
    }

    public String getReceiptTypeStr() {
        return receiptTypeStr;
    }

    public void setReceiptTypeStr(String receiptTypeStr) {
        this.receiptTypeStr = receiptTypeStr;
    }

    public Integer getCompletedPointNo() {
        return completedPointNo;
    }

    public void setCompletedPointNo(Integer completedPointNo) {
        this.completedPointNo = completedPointNo;
    }

    public Integer getSurplusPointNo() {
        return surplusPointNo;
    }

    public void setSurplusPointNo(Integer surplusPointNo) {
        this.surplusPointNo = surplusPointNo;
    }

    public String getDriverLoadingCostDate() {
        return driverLoadingCostDate;
    }

    public void setDriverLoadingCostDate(String driverLoadingCostDate) {
        this.driverLoadingCostDate = driverLoadingCostDate;
    }

    public TruckCustomer getManageCustomer() {
        return manageCustomer;
    }

    public void setManageCustomer(TruckCustomer manageCustomer) {
        this.manageCustomer = manageCustomer;
    }

    public Integer getCreateBatchAmount() {
        return createBatchAmount;
    }

    public void setCreateBatchAmount(Integer createBatchAmount) {
        this.createBatchAmount = createBatchAmount;
    }

    public Date getEstimateFinishTime() {
        return estimateFinishTime;
    }

    public void setEstimateFinishTime(Date estimateFinishTime) {
        this.estimateFinishTime = estimateFinishTime;
    }

    public boolean isHasReplaceCar() {
        return hasReplaceCar;
    }

    public void setHasReplaceCar(boolean hasReplaceCar) {
        this.hasReplaceCar = hasReplaceCar;
    }

    public List<DeliveryPointSupplement> getDeliveryPointSupplementList() {
        return deliveryPointSupplementList;
    }

    public void setDeliveryPointSupplementList(List<DeliveryPointSupplement> deliveryPointSupplementList) {
        this.deliveryPointSupplementList = deliveryPointSupplementList;
    }

    public List<ImageUploadManage> getReceiptImageList() {
        return receiptImageList;
    }

    public void setReceiptImageList(List<ImageUploadManage> receiptImageList) {
        this.receiptImageList = receiptImageList;
    }

    public String getValuationWayName() {
        return valuationWayName;
    }

    public void setValuationWayName(String valuationWayName) {
        this.valuationWayName = valuationWayName;
    }

    public String getCustomerServiceTel() {
        return customerServiceTel;
    }

    public void setCustomerServiceTel(String customerServiceTel) {
        this.customerServiceTel = customerServiceTel;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public Boolean getUploadedReceipt() {
        return uploadedReceipt;
    }

    public void setUploadedReceipt(Boolean uploadedReceipt) {
        this.uploadedReceipt = uploadedReceipt;
    }

    public WaybillCarrierVo getWaybillCarrierVo() {
        return waybillCarrierVo;
    }

    public void setWaybillCarrierVo(WaybillCarrierVo waybillCarrierVo) {
        this.waybillCarrierVo = waybillCarrierVo;
    }

    public com.juma.vms.driver.domain.Driver getVmsDriver() {
        return vmsDriver;
    }

    public void setVmsDriver(com.juma.vms.driver.domain.Driver vmsDriver) {
        this.vmsDriver = vmsDriver;
    }
}
