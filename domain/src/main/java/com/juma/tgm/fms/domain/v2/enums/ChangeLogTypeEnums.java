package com.juma.tgm.fms.domain.v2.enums;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 对账单调整日志记录的类型
 */
public enum ChangeLogTypeEnums implements Serializable{

    /**
     * 1 车辆类型
     */
    CAR(1,"车类型"),
    /**
     * 2 客户类型
     */
    TENANT(2,"客户类型"),
    ;
    private ChangeLogTypeEnums(Integer code, String desc){
        this.setCode(code);
        this.setDesc(desc);
    }

    private static final Map<Integer,ChangeLogTypeEnums> CACHE = Collections.unmodifiableMap(new HashMap<Integer,ChangeLogTypeEnums>(){
        private static final long serialVersionUID = 1L;
        {
            for (ChangeLogTypeEnums rs : ChangeLogTypeEnums.values()) {
                put(rs.getCode(), rs);
            }
        }
    });

    public static ChangeLogTypeEnums toEnum(Integer code){
        if(code==null) code = 1;
        return CACHE.get(code);
    }

    private Integer code;

    private String desc;



    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
