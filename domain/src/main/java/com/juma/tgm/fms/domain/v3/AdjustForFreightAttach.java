package com.juma.tgm.fms.domain.v3;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel("运费调整凭证")
public class AdjustForFreightAttach implements Serializable {
    private Integer attachId;

    private Integer adjustId;

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

    private Integer createUserId;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getAttachId() {
        return attachId;
    }

    public void setAttachId(Integer attachId) {
        this.attachId = attachId;
    }

    public Integer getAdjustId() {
        return adjustId;
    }

    public void setAdjustId(Integer adjustId) {
        this.adjustId = adjustId;
    }

    public String getCarryProofAttach() {
        return carryProofAttach;
    }

    public void setCarryProofAttach(String carryProofAttach) {
        this.carryProofAttach = carryProofAttach == null ? null : carryProofAttach.trim();
    }

    public String getWorkloadProofAttach() {
        return workloadProofAttach;
    }

    public void setWorkloadProofAttach(String workloadProofAttach) {
        this.workloadProofAttach = workloadProofAttach == null ? null : workloadProofAttach.trim();
    }

    public String getUpstairsProofAttach() {
        return upstairsProofAttach;
    }

    public void setUpstairsProofAttach(String upstairsProofAttach) {
        this.upstairsProofAttach = upstairsProofAttach == null ? null : upstairsProofAttach.trim();
    }

    public String getFineProofAttach() {
        return fineProofAttach;
    }

    public void setFineProofAttach(String fineProofAttach) {
        this.fineProofAttach = fineProofAttach == null ? null : fineProofAttach.trim();
    }

    public String getCargoLossProofAttach() {
        return cargoLossProofAttach;
    }

    public void setCargoLossProofAttach(String cargoLossProofAttach) {
        this.cargoLossProofAttach = cargoLossProofAttach == null ? null : cargoLossProofAttach.trim();
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        AdjustForFreightAttach other = (AdjustForFreightAttach) that;
        return (this.getAttachId() == null ? other.getAttachId() == null : this.getAttachId().equals(other.getAttachId()))
            && (this.getAdjustId() == null ? other.getAdjustId() == null : this.getAdjustId().equals(other.getAdjustId()))
            && (this.getCarryProofAttach() == null ? other.getCarryProofAttach() == null : this.getCarryProofAttach().equals(other.getCarryProofAttach()))
            && (this.getWorkloadProofAttach() == null ? other.getWorkloadProofAttach() == null : this.getWorkloadProofAttach().equals(other.getWorkloadProofAttach()))
            && (this.getUpstairsProofAttach() == null ? other.getUpstairsProofAttach() == null : this.getUpstairsProofAttach().equals(other.getUpstairsProofAttach()))
            && (this.getFineProofAttach() == null ? other.getFineProofAttach() == null : this.getFineProofAttach().equals(other.getFineProofAttach()))
            && (this.getCargoLossProofAttach() == null ? other.getCargoLossProofAttach() == null : this.getCargoLossProofAttach().equals(other.getCargoLossProofAttach()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAttachId() == null) ? 0 : getAttachId().hashCode());
        result = prime * result + ((getAdjustId() == null) ? 0 : getAdjustId().hashCode());
        result = prime * result + ((getCarryProofAttach() == null) ? 0 : getCarryProofAttach().hashCode());
        result = prime * result + ((getWorkloadProofAttach() == null) ? 0 : getWorkloadProofAttach().hashCode());
        result = prime * result + ((getUpstairsProofAttach() == null) ? 0 : getUpstairsProofAttach().hashCode());
        result = prime * result + ((getFineProofAttach() == null) ? 0 : getFineProofAttach().hashCode());
        result = prime * result + ((getCargoLossProofAttach() == null) ? 0 : getCargoLossProofAttach().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", attachId=").append(attachId);
        sb.append(", adjustId=").append(adjustId);
        sb.append(", carryProofAttach=").append(carryProofAttach);
        sb.append(", workloadProofAttach=").append(workloadProofAttach);
        sb.append(", upstairsProofAttach=").append(upstairsProofAttach);
        sb.append(", fineProofAttach=").append(fineProofAttach);
        sb.append(", cargoLossProofAttach=").append(cargoLossProofAttach);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        attachId,
        adjustId,
        carryProofAttach,
        workloadProofAttach,
        upstairsProofAttach,
        fineProofAttach,
        cargoLossProofAttach,
        createUserId,
        createTime;

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