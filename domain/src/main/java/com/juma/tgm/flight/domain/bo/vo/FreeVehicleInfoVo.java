package com.juma.tgm.flight.domain.bo.vo;

import java.io.Serializable;

/**
 *
 * @ClassName: FreeVehicleInfoVo
 * @Description:
 * @author: liang
 * @date: 2017-07-10 14:11
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class FreeVehicleInfoVo implements Serializable {


    /**
     * 箱型数量
     */
    private Integer boxTypeCount;

    /**
     * 箱型型
     */
    private Integer boxType;

    /**
     * 车型名称
     */
    private String boxTypeName;

    /**
     * 入城证数量
     */
    private Integer entryLicenseCount;



    public Integer getEntryLicenseCount() {
        return entryLicenseCount;
    }

    public void setEntryLicenseCount(Integer entryLicenseCount) {
        this.entryLicenseCount = entryLicenseCount;
    }


    public Integer getBoxTypeCount() {
        if(this.boxTypeCount == null) return 0;

        return boxTypeCount;
    }

    public void setBoxTypeCount(Integer boxTypeCount) {
        this.boxTypeCount = boxTypeCount;
    }

    public Integer getBoxType() {
        return boxType;
    }

    public void setBoxType(Integer boxType) {
        this.boxType = boxType;
    }

    public String getBoxTypeName() {
        return boxTypeName;
    }

    public void setBoxTypeName(String boxTypeName) {
        this.boxTypeName = boxTypeName;
    }

    /**
     * 增加车型数量统计
     * @param size
     */
    public void addBoxTypeCount(int size){
        if(this.boxTypeCount == null){
            this.boxTypeCount = 0;
        }
        this.boxTypeCount += size;
    }

    /**
     * 增加入城证数量统计
     * @param size
     */
    public void addEntryLicenseCount(int size){
        if(this.entryLicenseCount == null){
            this.entryLicenseCount = 0;
        }
        this.entryLicenseCount += size;
    }
}
