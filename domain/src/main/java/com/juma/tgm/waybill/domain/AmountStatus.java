package com.juma.tgm.waybill.domain;

/**
 * 功能 :
 * 新建运单,默认为未完成
 * 完成运单之后,设置运费状态为未确认
 * 运费状态:0未确认,1已确认,2超时未确认
 * @author : Bruce(刘正航) 09:45 2019-06-27
 */
public enum AmountStatus {
    UNCONFIRM(0,"未确认"),
    CONFIRMED(1,"已确认"),
    TIMEOUT_UNCONFIRM(2,"超时未确认");

    private Integer code;
    private String desc;

    AmountStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getByCode(Integer code){
        for (AmountStatus status : values()){
            if( status.getCode().equals(code) ){
                return status.getDesc();
            }
        }
        return null;
    }

}
