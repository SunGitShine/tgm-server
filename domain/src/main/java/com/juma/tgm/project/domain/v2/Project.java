package com.juma.tgm.project.domain.v2;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



@ApiModel("项目")
public class Project implements Serializable {
    @ApiModelProperty("")
    private Integer projectId;

    @ApiModelProperty("项目编号")
    private String projectNo;

    @ApiModelProperty("项目类型 1试运行 2正式运行")
    private Integer projectType;

    @ApiModelProperty("项目状态 1未启动 2运行中 3已暂停 4已结束")
    private Integer projectStatus;

    @ApiModelProperty("运营主体")
    private Integer payToCompany;

    @ApiModelProperty("运作方统一社会信用代码")
    private String payToCompanyCreditCode;

    @ApiModelProperty("合同编号")
    private String contractNo;

    @ApiModelProperty("签约主体")
    private Integer contractToCompany;

    @ApiModelProperty("签约方统一社会信用代码")
    private String contractToCompanyCreditCode;

    @ApiModelProperty("项目经理")
    private Integer projectManagerUserId;

    @ApiModelProperty("试运行项目评估表")
    private String tryWorkPassAttachment;

    @ApiModelProperty("试运行协议")
    private String tryWorkProtocol;

    @ApiModelProperty("项目开始日期")
    private Date projectStartDate;

    @ApiModelProperty("项目截止日期")
    private Date projectEndDate;

    @ApiModelProperty("是否开跑")
    private Boolean isRunning;

    @ApiModelProperty("开跑时间")
    private Date runningTime;

    @ApiModelProperty("业务对接人")
    private String businessLinkman;

    @ApiModelProperty("业务对接人电话")
    private String businessLinktel;

    @ApiModelProperty("业务对接人邮箱")
    private String businessLinkemail;

    @ApiModelProperty("财务对接人")
    private String financeLinkman;

    @ApiModelProperty("财务对接人电话")
    private String financeLinktel;

    @ApiModelProperty("财务对接人邮箱")
    private String financeLinkemail;

    @ApiModelProperty("配送地类型")
    private String deliveryAddressType;

    @ApiModelProperty("")
    private Integer oldId;

    @ApiModelProperty("")
    private Integer tenantId;

    @ApiModelProperty("")
    private BigDecimal rebateRate;

    @ApiModelProperty("")
    private Integer estimateTimeConsumption;

    @ApiModelProperty("")
    private String truckRequireRemark;

    @ApiModelProperty("")
    private String additionalFunctionIds;

    @ApiModelProperty("")
    private Integer fixedNo;

    @ApiModelProperty("")
    private String tenantCode;

    @ApiModelProperty("")
    private String areaCode;

    @ApiModelProperty("项目名称  全局唯一")
    private String name;

    @ApiModelProperty("物流产品标签，来源CRM")
    private String logisticsLabel;

    @ApiModelProperty("项目类型   默认分公司业务  0 分公司 1专车")
    private Byte type;

    @ApiModelProperty("")
    private Integer customerId;

    @ApiModelProperty("")
    private String customerName;

    @ApiModelProperty("")
    private Integer truckCustomerId;

    @ApiModelProperty("")
    private Integer managerId;

    @ApiModelProperty("货物类型")
    private String goodsType;

    @ApiModelProperty("税率")
    private BigDecimal taxRateValue;

    @ApiModelProperty("是否优先应收对账")
    private Boolean isReceivableFirst;

    @ApiModelProperty("")
    private Boolean isEnable;

    @ApiModelProperty("隔天配送")
    private Byte onlyLoadCargo;

    @ApiModelProperty("上传配送单")
    private Byte needDeliveryPointNote;

    @ApiModelProperty("")
    private Date createTime;

    @ApiModelProperty("")
    private Integer createUserId;

    @ApiModelProperty("")
    private Boolean isReceiveDailySms;

    @ApiModelProperty("承诺毛利率")
    private BigDecimal profitRate;

    @ApiModelProperty("结算账期")
    private Integer billPeriod;

    private static final long serialVersionUID = 1L;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    public Integer getProjectType() {
        return projectType;
    }

    public void setProjectType(Integer projectType) {
        this.projectType = projectType;
    }

    public Integer getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Integer projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Integer getPayToCompany() {
        return payToCompany;
    }

    public void setPayToCompany(Integer payToCompany) {
        this.payToCompany = payToCompany;
    }

    public String getPayToCompanyCreditCode() {
        return payToCompanyCreditCode;
    }

    public void setPayToCompanyCreditCode(String payToCompanyCreditCode) {
        this.payToCompanyCreditCode = payToCompanyCreditCode == null ? null : payToCompanyCreditCode.trim();
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    public Integer getContractToCompany() {
        return contractToCompany;
    }

    public void setContractToCompany(Integer contractToCompany) {
        this.contractToCompany = contractToCompany;
    }

    public String getContractToCompanyCreditCode() {
        return contractToCompanyCreditCode;
    }

    public void setContractToCompanyCreditCode(String contractToCompanyCreditCode) {
        this.contractToCompanyCreditCode = contractToCompanyCreditCode == null ? null : contractToCompanyCreditCode.trim();
    }

    public Integer getProjectManagerUserId() {
        return projectManagerUserId;
    }

    public void setProjectManagerUserId(Integer projectManagerUserId) {
        this.projectManagerUserId = projectManagerUserId;
    }

    public String getTryWorkPassAttachment() {
        return tryWorkPassAttachment;
    }

    public void setTryWorkPassAttachment(String tryWorkPassAttachment) {
        this.tryWorkPassAttachment = tryWorkPassAttachment == null ? null : tryWorkPassAttachment.trim();
    }

    public String getTryWorkProtocol() {
        return tryWorkProtocol;
    }

    public void setTryWorkProtocol(String tryWorkProtocol) {
        this.tryWorkProtocol = tryWorkProtocol == null ? null : tryWorkProtocol.trim();
    }

    public Date getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public Date getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(Date projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public Boolean getIsRunning() {
        return isRunning;
    }

    public void setIsRunning(Boolean isRunning) {
        this.isRunning = isRunning;
    }

    public Date getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(Date runningTime) {
        this.runningTime = runningTime;
    }

    public String getBusinessLinkman() {
        return businessLinkman;
    }

    public void setBusinessLinkman(String businessLinkman) {
        this.businessLinkman = businessLinkman == null ? null : businessLinkman.trim();
    }

    public String getBusinessLinktel() {
        return businessLinktel;
    }

    public void setBusinessLinktel(String businessLinktel) {
        this.businessLinktel = businessLinktel == null ? null : businessLinktel.trim();
    }

    public String getBusinessLinkemail() {
        return businessLinkemail;
    }

    public void setBusinessLinkemail(String businessLinkemail) {
        this.businessLinkemail = businessLinkemail == null ? null : businessLinkemail.trim();
    }

    public String getFinanceLinkman() {
        return financeLinkman;
    }

    public void setFinanceLinkman(String financeLinkman) {
        this.financeLinkman = financeLinkman == null ? null : financeLinkman.trim();
    }

    public String getFinanceLinktel() {
        return financeLinktel;
    }

    public void setFinanceLinktel(String financeLinktel) {
        this.financeLinktel = financeLinktel == null ? null : financeLinktel.trim();
    }

    public String getFinanceLinkemail() {
        return financeLinkemail;
    }

    public void setFinanceLinkemail(String financeLinkemail) {
        this.financeLinkemail = financeLinkemail == null ? null : financeLinkemail.trim();
    }

    public String getDeliveryAddressType() {
        return deliveryAddressType;
    }

    public void setDeliveryAddressType(String deliveryAddressType) {
        this.deliveryAddressType = deliveryAddressType == null ? null : deliveryAddressType.trim();
    }

    public Integer getOldId() {
        return oldId;
    }

    public void setOldId(Integer oldId) {
        this.oldId = oldId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public BigDecimal getRebateRate() {
        return rebateRate;
    }

    public void setRebateRate(BigDecimal rebateRate) {
        this.rebateRate = rebateRate;
    }

    public Integer getEstimateTimeConsumption() {
        return estimateTimeConsumption;
    }

    public void setEstimateTimeConsumption(Integer estimateTimeConsumption) {
        this.estimateTimeConsumption = estimateTimeConsumption;
    }

    public String getTruckRequireRemark() {
        return truckRequireRemark;
    }

    public void setTruckRequireRemark(String truckRequireRemark) {
        this.truckRequireRemark = truckRequireRemark == null ? null : truckRequireRemark.trim();
    }

    public String getAdditionalFunctionIds() {
        return additionalFunctionIds;
    }

    public void setAdditionalFunctionIds(String additionalFunctionIds) {
        this.additionalFunctionIds = additionalFunctionIds == null ? null : additionalFunctionIds.trim();
    }

    public Integer getFixedNo() {
        return fixedNo;
    }

    public void setFixedNo(Integer fixedNo) {
        this.fixedNo = fixedNo;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode == null ? null : tenantCode.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLogisticsLabel() {
        return logisticsLabel;
    }

    public void setLogisticsLabel(String logisticsLabel) {
        this.logisticsLabel = logisticsLabel == null ? null : logisticsLabel.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public Integer getTruckCustomerId() {
        return truckCustomerId;
    }

    public void setTruckCustomerId(Integer truckCustomerId) {
        this.truckCustomerId = truckCustomerId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType == null ? null : goodsType.trim();
    }

    public BigDecimal getTaxRateValue() {
        return taxRateValue;
    }

    public void setTaxRateValue(BigDecimal taxRateValue) {
        this.taxRateValue = taxRateValue;
    }

    public Boolean getIsReceivableFirst() {
        return isReceivableFirst;
    }

    public void setIsReceivableFirst(Boolean isReceivableFirst) {
        this.isReceivableFirst = isReceivableFirst;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    public Byte getOnlyLoadCargo() {
        return onlyLoadCargo;
    }

    public void setOnlyLoadCargo(Byte onlyLoadCargo) {
        this.onlyLoadCargo = onlyLoadCargo;
    }

    public Byte getNeedDeliveryPointNote() {
        return needDeliveryPointNote;
    }

    public void setNeedDeliveryPointNote(Byte needDeliveryPointNote) {
        this.needDeliveryPointNote = needDeliveryPointNote;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Boolean getIsReceiveDailySms() {
        return isReceiveDailySms;
    }

    public void setIsReceiveDailySms(Boolean isReceiveDailySms) {
        this.isReceiveDailySms = isReceiveDailySms;
    }

    public BigDecimal getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    public Integer getBillPeriod() {
        return billPeriod;
    }

    public void setBillPeriod(Integer billPeriod) {
        this.billPeriod = billPeriod;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Project other = (Project) that;
        return (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getProjectNo() == null ? other.getProjectNo() == null : this.getProjectNo().equals(other.getProjectNo()))
            && (this.getProjectType() == null ? other.getProjectType() == null : this.getProjectType().equals(other.getProjectType()))
            && (this.getProjectStatus() == null ? other.getProjectStatus() == null : this.getProjectStatus().equals(other.getProjectStatus()))
            && (this.getPayToCompany() == null ? other.getPayToCompany() == null : this.getPayToCompany().equals(other.getPayToCompany()))
            && (this.getPayToCompanyCreditCode() == null ? other.getPayToCompanyCreditCode() == null : this.getPayToCompanyCreditCode().equals(other.getPayToCompanyCreditCode()))
            && (this.getContractNo() == null ? other.getContractNo() == null : this.getContractNo().equals(other.getContractNo()))
            && (this.getContractToCompany() == null ? other.getContractToCompany() == null : this.getContractToCompany().equals(other.getContractToCompany()))
            && (this.getContractToCompanyCreditCode() == null ? other.getContractToCompanyCreditCode() == null : this.getContractToCompanyCreditCode().equals(other.getContractToCompanyCreditCode()))
            && (this.getProjectManagerUserId() == null ? other.getProjectManagerUserId() == null : this.getProjectManagerUserId().equals(other.getProjectManagerUserId()))
            && (this.getTryWorkPassAttachment() == null ? other.getTryWorkPassAttachment() == null : this.getTryWorkPassAttachment().equals(other.getTryWorkPassAttachment()))
            && (this.getTryWorkProtocol() == null ? other.getTryWorkProtocol() == null : this.getTryWorkProtocol().equals(other.getTryWorkProtocol()))
            && (this.getProjectStartDate() == null ? other.getProjectStartDate() == null : this.getProjectStartDate().equals(other.getProjectStartDate()))
            && (this.getProjectEndDate() == null ? other.getProjectEndDate() == null : this.getProjectEndDate().equals(other.getProjectEndDate()))
            && (this.getIsRunning() == null ? other.getIsRunning() == null : this.getIsRunning().equals(other.getIsRunning()))
            && (this.getRunningTime() == null ? other.getRunningTime() == null : this.getRunningTime().equals(other.getRunningTime()))
            && (this.getBusinessLinkman() == null ? other.getBusinessLinkman() == null : this.getBusinessLinkman().equals(other.getBusinessLinkman()))
            && (this.getBusinessLinktel() == null ? other.getBusinessLinktel() == null : this.getBusinessLinktel().equals(other.getBusinessLinktel()))
            && (this.getBusinessLinkemail() == null ? other.getBusinessLinkemail() == null : this.getBusinessLinkemail().equals(other.getBusinessLinkemail()))
            && (this.getFinanceLinkman() == null ? other.getFinanceLinkman() == null : this.getFinanceLinkman().equals(other.getFinanceLinkman()))
            && (this.getFinanceLinktel() == null ? other.getFinanceLinktel() == null : this.getFinanceLinktel().equals(other.getFinanceLinktel()))
            && (this.getFinanceLinkemail() == null ? other.getFinanceLinkemail() == null : this.getFinanceLinkemail().equals(other.getFinanceLinkemail()))
            && (this.getDeliveryAddressType() == null ? other.getDeliveryAddressType() == null : this.getDeliveryAddressType().equals(other.getDeliveryAddressType()))
            && (this.getOldId() == null ? other.getOldId() == null : this.getOldId().equals(other.getOldId()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()))
            && (this.getRebateRate() == null ? other.getRebateRate() == null : this.getRebateRate().equals(other.getRebateRate()))
            && (this.getEstimateTimeConsumption() == null ? other.getEstimateTimeConsumption() == null : this.getEstimateTimeConsumption().equals(other.getEstimateTimeConsumption()))
            && (this.getTruckRequireRemark() == null ? other.getTruckRequireRemark() == null : this.getTruckRequireRemark().equals(other.getTruckRequireRemark()))
            && (this.getAdditionalFunctionIds() == null ? other.getAdditionalFunctionIds() == null : this.getAdditionalFunctionIds().equals(other.getAdditionalFunctionIds()))
            && (this.getFixedNo() == null ? other.getFixedNo() == null : this.getFixedNo().equals(other.getFixedNo()))
            && (this.getTenantCode() == null ? other.getTenantCode() == null : this.getTenantCode().equals(other.getTenantCode()))
            && (this.getAreaCode() == null ? other.getAreaCode() == null : this.getAreaCode().equals(other.getAreaCode()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getLogisticsLabel() == null ? other.getLogisticsLabel() == null : this.getLogisticsLabel().equals(other.getLogisticsLabel()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getCustomerName() == null ? other.getCustomerName() == null : this.getCustomerName().equals(other.getCustomerName()))
            && (this.getTruckCustomerId() == null ? other.getTruckCustomerId() == null : this.getTruckCustomerId().equals(other.getTruckCustomerId()))
            && (this.getManagerId() == null ? other.getManagerId() == null : this.getManagerId().equals(other.getManagerId()))
            && (this.getGoodsType() == null ? other.getGoodsType() == null : this.getGoodsType().equals(other.getGoodsType()))
            && (this.getTaxRateValue() == null ? other.getTaxRateValue() == null : this.getTaxRateValue().equals(other.getTaxRateValue()))
            && (this.getIsReceivableFirst() == null ? other.getIsReceivableFirst() == null : this.getIsReceivableFirst().equals(other.getIsReceivableFirst()))
            && (this.getIsEnable() == null ? other.getIsEnable() == null : this.getIsEnable().equals(other.getIsEnable()))
            && (this.getOnlyLoadCargo() == null ? other.getOnlyLoadCargo() == null : this.getOnlyLoadCargo().equals(other.getOnlyLoadCargo()))
            && (this.getNeedDeliveryPointNote() == null ? other.getNeedDeliveryPointNote() == null : this.getNeedDeliveryPointNote().equals(other.getNeedDeliveryPointNote()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getIsReceiveDailySms() == null ? other.getIsReceiveDailySms() == null : this.getIsReceiveDailySms().equals(other.getIsReceiveDailySms()))
            && (this.getProfitRate() == null ? other.getProfitRate() == null : this.getProfitRate().equals(other.getProfitRate()))
            && (this.getBillPeriod() == null ? other.getBillPeriod() == null : this.getBillPeriod().equals(other.getBillPeriod()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getProjectNo() == null) ? 0 : getProjectNo().hashCode());
        result = prime * result + ((getProjectType() == null) ? 0 : getProjectType().hashCode());
        result = prime * result + ((getProjectStatus() == null) ? 0 : getProjectStatus().hashCode());
        result = prime * result + ((getPayToCompany() == null) ? 0 : getPayToCompany().hashCode());
        result = prime * result + ((getPayToCompanyCreditCode() == null) ? 0 : getPayToCompanyCreditCode().hashCode());
        result = prime * result + ((getContractNo() == null) ? 0 : getContractNo().hashCode());
        result = prime * result + ((getContractToCompany() == null) ? 0 : getContractToCompany().hashCode());
        result = prime * result + ((getContractToCompanyCreditCode() == null) ? 0 : getContractToCompanyCreditCode().hashCode());
        result = prime * result + ((getProjectManagerUserId() == null) ? 0 : getProjectManagerUserId().hashCode());
        result = prime * result + ((getTryWorkPassAttachment() == null) ? 0 : getTryWorkPassAttachment().hashCode());
        result = prime * result + ((getTryWorkProtocol() == null) ? 0 : getTryWorkProtocol().hashCode());
        result = prime * result + ((getProjectStartDate() == null) ? 0 : getProjectStartDate().hashCode());
        result = prime * result + ((getProjectEndDate() == null) ? 0 : getProjectEndDate().hashCode());
        result = prime * result + ((getIsRunning() == null) ? 0 : getIsRunning().hashCode());
        result = prime * result + ((getRunningTime() == null) ? 0 : getRunningTime().hashCode());
        result = prime * result + ((getBusinessLinkman() == null) ? 0 : getBusinessLinkman().hashCode());
        result = prime * result + ((getBusinessLinktel() == null) ? 0 : getBusinessLinktel().hashCode());
        result = prime * result + ((getBusinessLinkemail() == null) ? 0 : getBusinessLinkemail().hashCode());
        result = prime * result + ((getFinanceLinkman() == null) ? 0 : getFinanceLinkman().hashCode());
        result = prime * result + ((getFinanceLinktel() == null) ? 0 : getFinanceLinktel().hashCode());
        result = prime * result + ((getFinanceLinkemail() == null) ? 0 : getFinanceLinkemail().hashCode());
        result = prime * result + ((getDeliveryAddressType() == null) ? 0 : getDeliveryAddressType().hashCode());
        result = prime * result + ((getOldId() == null) ? 0 : getOldId().hashCode());
        result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
        result = prime * result + ((getRebateRate() == null) ? 0 : getRebateRate().hashCode());
        result = prime * result + ((getEstimateTimeConsumption() == null) ? 0 : getEstimateTimeConsumption().hashCode());
        result = prime * result + ((getTruckRequireRemark() == null) ? 0 : getTruckRequireRemark().hashCode());
        result = prime * result + ((getAdditionalFunctionIds() == null) ? 0 : getAdditionalFunctionIds().hashCode());
        result = prime * result + ((getFixedNo() == null) ? 0 : getFixedNo().hashCode());
        result = prime * result + ((getTenantCode() == null) ? 0 : getTenantCode().hashCode());
        result = prime * result + ((getAreaCode() == null) ? 0 : getAreaCode().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getLogisticsLabel() == null) ? 0 : getLogisticsLabel().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
        result = prime * result + ((getTruckCustomerId() == null) ? 0 : getTruckCustomerId().hashCode());
        result = prime * result + ((getManagerId() == null) ? 0 : getManagerId().hashCode());
        result = prime * result + ((getGoodsType() == null) ? 0 : getGoodsType().hashCode());
        result = prime * result + ((getTaxRateValue() == null) ? 0 : getTaxRateValue().hashCode());
        result = prime * result + ((getIsReceivableFirst() == null) ? 0 : getIsReceivableFirst().hashCode());
        result = prime * result + ((getIsEnable() == null) ? 0 : getIsEnable().hashCode());
        result = prime * result + ((getOnlyLoadCargo() == null) ? 0 : getOnlyLoadCargo().hashCode());
        result = prime * result + ((getNeedDeliveryPointNote() == null) ? 0 : getNeedDeliveryPointNote().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getIsReceiveDailySms() == null) ? 0 : getIsReceiveDailySms().hashCode());
        result = prime * result + ((getProfitRate() == null) ? 0 : getProfitRate().hashCode());
        result = prime * result + ((getBillPeriod() == null) ? 0 : getBillPeriod().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", projectId=").append(projectId);
        sb.append(", projectNo=").append(projectNo);
        sb.append(", projectType=").append(projectType);
        sb.append(", projectStatus=").append(projectStatus);
        sb.append(", payToCompany=").append(payToCompany);
        sb.append(", payToCompanyCreditCode=").append(payToCompanyCreditCode);
        sb.append(", contractNo=").append(contractNo);
        sb.append(", contractToCompany=").append(contractToCompany);
        sb.append(", contractToCompanyCreditCode=").append(contractToCompanyCreditCode);
        sb.append(", projectManagerUserId=").append(projectManagerUserId);
        sb.append(", tryWorkPassAttachment=").append(tryWorkPassAttachment);
        sb.append(", tryWorkProtocol=").append(tryWorkProtocol);
        sb.append(", projectStartDate=").append(projectStartDate);
        sb.append(", projectEndDate=").append(projectEndDate);
        sb.append(", isRunning=").append(isRunning);
        sb.append(", runningTime=").append(runningTime);
        sb.append(", businessLinkman=").append(businessLinkman);
        sb.append(", businessLinktel=").append(businessLinktel);
        sb.append(", businessLinkemail=").append(businessLinkemail);
        sb.append(", financeLinkman=").append(financeLinkman);
        sb.append(", financeLinktel=").append(financeLinktel);
        sb.append(", financeLinkemail=").append(financeLinkemail);
        sb.append(", deliveryAddressType=").append(deliveryAddressType);
        sb.append(", oldId=").append(oldId);
        sb.append(", tenantId=").append(tenantId);
        sb.append(", rebateRate=").append(rebateRate);
        sb.append(", estimateTimeConsumption=").append(estimateTimeConsumption);
        sb.append(", truckRequireRemark=").append(truckRequireRemark);
        sb.append(", additionalFunctionIds=").append(additionalFunctionIds);
        sb.append(", fixedNo=").append(fixedNo);
        sb.append(", tenantCode=").append(tenantCode);
        sb.append(", areaCode=").append(areaCode);
        sb.append(", name=").append(name);
        sb.append(", logisticsLabel=").append(logisticsLabel);
        sb.append(", type=").append(type);
        sb.append(", customerId=").append(customerId);
        sb.append(", customerName=").append(customerName);
        sb.append(", truckCustomerId=").append(truckCustomerId);
        sb.append(", managerId=").append(managerId);
        sb.append(", goodsType=").append(goodsType);
        sb.append(", taxRateValue=").append(taxRateValue);
        sb.append(", isReceivableFirst=").append(isReceivableFirst);
        sb.append(", isEnable=").append(isEnable);
        sb.append(", onlyLoadCargo=").append(onlyLoadCargo);
        sb.append(", needDeliveryPointNote=").append(needDeliveryPointNote);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", isReceiveDailySms=").append(isReceiveDailySms);
        sb.append(", profitRate=").append(profitRate);
        sb.append(", billPeriod=").append(billPeriod);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        projectId,
        projectNo,
        projectType,
        projectStatus,
        payToCompany,
        payToCompanyCreditCode,
        contractNo,
        contractToCompany,
        contractToCompanyCreditCode,
        projectManagerUserId,
        tryWorkPassAttachment,
        tryWorkProtocol,
        projectStartDate,
        projectEndDate,
        isRunning,
        runningTime,
        businessLinkman,
        businessLinktel,
        businessLinkemail,
        financeLinkman,
        financeLinktel,
        financeLinkemail,
        deliveryAddressType,
        oldId,
        tenantId,
        rebateRate,
        estimateTimeConsumption,
        truckRequireRemark,
        additionalFunctionIds,
        fixedNo,
        tenantCode,
        areaCode,
        name,
        logisticsLabel,
        type,
        customerId,
        customerName,
        truckCustomerId,
        managerId,
        goodsType,
        taxRateValue,
        isReceivableFirst,
        isEnable,
        onlyLoadCargo,
        needDeliveryPointNote,
        createTime,
        createUserId,
        isReceiveDailySms,
        profitRate,
        billPeriod;

        public String asc() {
            return column() + " ASC";
        }

        public String desc() {
            return column() + " DESC";
        }

        private String column() {
            StringBuilder buffer = new StringBuilder();
            char[] charArray = this.name().toCharArray();
            for(char ch : charArray) {
                if(Character.isUpperCase(ch)){
                    buffer.append("_");
                    buffer.append(Character.toLowerCase(ch));
                } else {
                    buffer.append(ch);
                }
            }
            return buffer.toString();
        }
    }
}