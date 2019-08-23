package com.juma.tgm.operateLog.enumeration;

/**
 * @ClassName OperateTypeEnum.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年10月30日 上午10:37:12
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public enum OperateTypeEnum {

    ADD_PROJECT((byte) 1, "添加项目"),
    MODIFY_PROJECT((byte) 2, "编辑项目"),
    DEL_PROJECT((byte) 3, "删除项目"),
    ADD_ROAD_MAP((byte) 4, "添加路线"),
    MODIFY_ROAD_MAP((byte) 5, "编辑路线"),
    DEL_ROAD_MAP((byte) 6, "删除路线"),
    PROJECT_IS_RECEIVABLE_FIRST((byte) 7, "更改对账权限"),
    RECONCILICATION_AP_DEL((byte) 8, "承运商撤销对账单"),
    RECONCILICATION_AP_RECALL((byte) 9, "承运商撤回审核"),
    PROJECT_TO_REAL_RUN((byte) 10, "转正式运行"),
    PROJECT_CHANGE_CONTRACT((byte) 11, "续签合同"),
    PROJECT_RENEWAL_CONTRACT((byte) 12, "变更合同"),
    ADD_OR_MOFIFY_PROJECT_MEMBER((byte) 13, "新增或编辑运营人员"),
    PROJECT_SUPPLEMENT((byte) 14, "项目信息补录"),
    MODIFY_PROFIT_RATE((byte) 15, "编辑承诺毛利率");

    private byte code;
    private String desc;

    private OperateTypeEnum(byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public byte getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getTypeDesc(Byte code) {
        if (null == code) {
            return null;
        }

        for (OperateTypeEnum t : OperateTypeEnum.values()) {
            if (code == t.getCode()) {
                return t.getDesc();
            }
        }
        return null;
    }
}
