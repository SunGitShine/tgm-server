package com.juma.tgm.common;

import org.apache.commons.lang.math.NumberUtils;

/**
 * @ClassName: ManagerScheduleType
 * @Description:
 * @author: liang
 * @date: 2017-06-16 16:29
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public enum ManagerScheduleType {
    /**
     * 模板-运单处理-增加运费
     */
    WAYBILL_TYPE_INCREASE_CARRYFEE_TPL(1, "运单处理","尾号%s的运单增加了%s元搬运费", 1),
    WAYBILL_TYPE_ASSIGN_FEEDBACK_TPL(1, "运单处理","运单无法派车，调度已添加派车反馈，请及时与货主沟通", 2);

    private Integer type;
    private String typeName;
    private String tpl;
    private Integer code;

    ManagerScheduleType(int type, String typeName, String tpl, int code) {
        this.type = type;
        this.typeName = typeName;
        this.tpl = tpl;
        this.code = code;
    }

    public static String getTypeName(int code){
        for (ManagerScheduleType mst : ManagerScheduleType.values()) {
            if(NumberUtils.compare(mst.code , code) == 0) {
                return mst.typeName;
            }
        }
        return null;
    }

    public Integer getType(){
        return type;
    }

    public Integer getCode(){
        return code;
    }

    public String getContent(){
        return this.tpl;
    }


}
