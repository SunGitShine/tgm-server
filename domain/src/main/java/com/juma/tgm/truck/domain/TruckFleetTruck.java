package com.juma.tgm.truck.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;



@ApiModel("车队货车")
public class TruckFleetTruck implements Serializable {
    @ApiModelProperty("")
    private Integer truckFleetTruckId;

    @ApiModelProperty("")
    private Integer truckFleetId;

    @ApiModelProperty("")
    private Integer truckId;

    @ApiModelProperty("车牌号")
    private String plateNumber;

    @ApiModelProperty("")
    private String note;

    private static final long serialVersionUID = 1L;

    public Integer getTruckFleetTruckId() {
        return truckFleetTruckId;
    }

    public void setTruckFleetTruckId(Integer truckFleetTruckId) {
        this.truckFleetTruckId = truckFleetTruckId;
    }

    public Integer getTruckFleetId() {
        return truckFleetId;
    }

    public void setTruckFleetId(Integer truckFleetId) {
        this.truckFleetId = truckFleetId;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber == null ? null : plateNumber.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
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
        TruckFleetTruck other = (TruckFleetTruck) that;
        return (this.getTruckFleetTruckId() == null ? other.getTruckFleetTruckId() == null : this.getTruckFleetTruckId().equals(other.getTruckFleetTruckId()))
            && (this.getTruckFleetId() == null ? other.getTruckFleetId() == null : this.getTruckFleetId().equals(other.getTruckFleetId()))
            && (this.getTruckId() == null ? other.getTruckId() == null : this.getTruckId().equals(other.getTruckId()))
            && (this.getPlateNumber() == null ? other.getPlateNumber() == null : this.getPlateNumber().equals(other.getPlateNumber()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTruckFleetTruckId() == null) ? 0 : getTruckFleetTruckId().hashCode());
        result = prime * result + ((getTruckFleetId() == null) ? 0 : getTruckFleetId().hashCode());
        result = prime * result + ((getTruckId() == null) ? 0 : getTruckId().hashCode());
        result = prime * result + ((getPlateNumber() == null) ? 0 : getPlateNumber().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", truckFleetTruckId=").append(truckFleetTruckId);
        sb.append(", truckFleetId=").append(truckFleetId);
        sb.append(", truckId=").append(truckId);
        sb.append(", plateNumber=").append(plateNumber);
        sb.append(", note=").append(note);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        truckFleetTruckId,
        truckFleetId,
        truckId,
        plateNumber,
        note;

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