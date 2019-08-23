package com.juma.tgm.fms.domain.v3.enums;

/**
 * 功能 : 
 *
 * @author : Bruce(刘正航) 17:32 2019-05-13
 */
public enum AdjustMasterType {
    //
    CUSTOMER(1,"客户"),
    VENDOR(2,"承运商");
    private Integer code;
    private String desc;

    AdjustMasterType(final Integer code, final String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static AdjustMasterType getByCode(Integer code){
        if( null == code ){ return null; }
        for (AdjustMasterType type : values()){
            if( type.getCode().equals(code) ){
                return type;
            }
        }
        return null;
    }
}
