package com.juma.tgm.fms.domain.v3.vo;

import com.juma.tgm.fms.domain.v3.AdjustForMaster;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 功能 : 
 * 添加调整单:数据对象
 * @author : Bruce(刘正航) 17:10 2019-05-13
 */
@ApiModel("添加调整单数据对象")
public class AdjustForMasterAddVO extends AdjustForMaster implements Serializable {

    @ApiModelProperty("装卸凭证")
    private String carryProofAttach;

    @ApiModelProperty("工作量凭证")
    private String workloadProofAttach;

    @ApiModelProperty("上楼凭证")
    private String upstairsProofAttach;

    @ApiModelProperty("罚款凭证")
    private String fineProofAttach;

    @ApiModelProperty("货损货差凭证")
    private String cargoLossProofAttach;

    public String getCarryProofAttach() {
        return carryProofAttach;
    }

    public void setCarryProofAttach(final String carryProofAttach) {
        this.carryProofAttach = carryProofAttach;
    }

    public String getWorkloadProofAttach() {
        return workloadProofAttach;
    }

    public void setWorkloadProofAttach(final String workloadProofAttach) {
        this.workloadProofAttach = workloadProofAttach;
    }

    public String getUpstairsProofAttach() {
        return upstairsProofAttach;
    }

    public void setUpstairsProofAttach(final String upstairsProofAttach) {
        this.upstairsProofAttach = upstairsProofAttach;
    }

    public String getFineProofAttach() {
        return fineProofAttach;
    }

    public void setFineProofAttach(final String fineProofAttach) {
        this.fineProofAttach = fineProofAttach;
    }

    public String getCargoLossProofAttach() {
        return cargoLossProofAttach;
    }

    public void setCargoLossProofAttach(final String cargoLossProofAttach) {
        this.cargoLossProofAttach = cargoLossProofAttach;
    }
}
