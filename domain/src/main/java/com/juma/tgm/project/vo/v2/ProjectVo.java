package com.juma.tgm.project.vo.v2;

import com.juma.tgm.project.domain.v2.Project;
import com.juma.tgm.project.domain.v2.ProjectWorkflow;
import com.juma.tgm.project.domain.v2.enums.ProjectEnum;
import com.juma.tgm.project.vo.ProjectButtonCtrl;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.StringUtils;

@ApiModel(value = "项目详情")
public class ProjectVo extends Project {

    private ProjectWorkflow projectWorkflow;
    // 列表页按钮控制
    private ProjectButtonCtrl projectButtonContrl;
    @ApiModelProperty(value="审核状态")
    private String auditStatusName;
    @ApiModelProperty(value="项目经理")
    private String projectManagerUserName;
    @ApiModelProperty(value="项目经理联系电话")
    private String projectManagerUserPhone;
    @ApiModelProperty(value="客户经理")
    private String customerManageName;
    @ApiModelProperty(value="业务范围")
    private String areaName;
    @ApiModelProperty(value="项目状态")
    private String projectStatusName;
    @ApiModelProperty(value="项目运作类型")
    private String projectTypeName;
    @ApiModelProperty(value="物流产品名称")
    private String logisticsLabelName;
    @ApiModelProperty(value="签约方名称")
    private String contractToCompanyName;
    @ApiModelProperty(value="运作方名称")
    private String payToCompanyName;
    @ApiModelProperty(value="合同名称")
    private String contractName;
    @ApiModelProperty(value="合同附件(签约方)")
    private String contractEnclosureUrl;
    @ApiModelProperty(value="合同附件(运作方)")
    private String payToCompanyEnclosureUrl;
    @ApiModelProperty(value="承诺毛利率")
    private String profitRateDesc;

    public String getProfitRateDesc() {
        return profitRateDesc;
    }

    public void setProfitRateDesc(String profitRateDesc) {
        this.profitRateDesc = profitRateDesc;
    }

    public ProjectWorkflow getProjectWorkflow() {
        return projectWorkflow;
    }

    public void setProjectWorkflow(ProjectWorkflow projectWorkflow) {
        this.projectWorkflow = projectWorkflow;
    }

    public ProjectButtonCtrl getProjectButtonContrl() {
        return projectButtonContrl;
    }

    public void setProjectButtonContrl(ProjectButtonCtrl projectButtonContrl) {
        this.projectButtonContrl = projectButtonContrl;
    }

    public String getAuditStatusName() {
        return auditStatusName;
    }

    public void setAuditStatusName(String auditStatusName) {
        this.auditStatusName = auditStatusName;
    }

    public String getProjectManagerUserName() {
        return projectManagerUserName;
    }

    public void setProjectManagerUserName(String projectManagerUserName) {
        this.projectManagerUserName = projectManagerUserName;
    }

    public String getProjectManagerUserPhone() {
        return projectManagerUserPhone;
    }

    public void setProjectManagerUserPhone(String projectManagerUserPhone) {
        this.projectManagerUserPhone = projectManagerUserPhone;
    }

    public String getCustomerManageName() {
        return customerManageName;
    }

    public void setCustomerManageName(String customerManageName) {
        this.customerManageName = customerManageName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getProjectStatusName() {
        return StringUtils.isBlank(projectStatusName) ?
                ProjectEnum.ProjectStatus.getdescByCode(super.getProjectStatus()) : projectStatusName;
    }

    public void setProjectStatusName(String projectStatusName) {
        this.projectStatusName = projectStatusName;
    }

    public String getProjectTypeName() {
        return StringUtils.isBlank(projectTypeName) ? ProjectEnum.ProjectType.getdescByCode(super.getProjectType()) :
                projectTypeName;
    }

    public void setProjectTypeName(String projectTypeName) {
        this.projectTypeName = projectTypeName;
    }

    public String getLogisticsLabelName() {
        return logisticsLabelName;
    }

    public void setLogisticsLabelName(String logisticsLabelName) {
        this.logisticsLabelName = logisticsLabelName;
    }

    public String getContractToCompanyName() {
        return contractToCompanyName;
    }

    public void setContractToCompanyName(String contractToCompanyName) {
        this.contractToCompanyName = contractToCompanyName;
    }

    public String getPayToCompanyName() {
        return payToCompanyName;
    }

    public void setPayToCompanyName(String payToCompanyName) {
        this.payToCompanyName = payToCompanyName;
    }

    public String getContractEnclosureUrl() {
        return contractEnclosureUrl;
    }

    public void setContractEnclosureUrl(String contractEnclosureUrl) {
        this.contractEnclosureUrl = contractEnclosureUrl;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getPayToCompanyEnclosureUrl() {
        return payToCompanyEnclosureUrl;
    }

    public void setPayToCompanyEnclosureUrl(String payToCompanyEnclosureUrl) {
        this.payToCompanyEnclosureUrl = payToCompanyEnclosureUrl;
    }
}
