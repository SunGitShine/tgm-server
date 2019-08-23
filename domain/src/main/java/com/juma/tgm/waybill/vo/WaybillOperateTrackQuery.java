package com.juma.tgm.waybill.vo;

import com.juma.tgm.waybill.domain.WaybillOperateTrack;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateApplication;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateType;
import org.apache.commons.lang.StringUtils;

/**
 * @ClassName WaybillOperateTrackQuery
 * @Description TODO
 * @Author weilibin
 * @Date 2019-05-16 11:23
 * @Version 1.0.0
 */

public class WaybillOperateTrackQuery extends WaybillOperateTrack {

    private String distanceStr;
    private String OperateUserName;
    private String phone;
    private String operateApplicationDesc;
    private String operateTypeDesc;

    public String getDistanceStr() {
        return distanceStr;
    }

    public void setDistanceStr(String distanceStr) {
        this.distanceStr = distanceStr;
    }

    public String getOperateUserName() {
        return OperateUserName;
    }

    public void setOperateUserName(String operateUserName) {
        OperateUserName = operateUserName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOperateTypeDesc() {
        if (StringUtils.isNotBlank(operateTypeDesc)) {
            return operateTypeDesc;
        }

        OperateType operateType = OperateType.fromInt(super.getOperateType());
        return operateType == null ? operateTypeDesc : operateType.getDesc();
    }

    public void setOperateTypeDesc(String operateTypeDesc) {
        this.operateTypeDesc = operateTypeDesc;
    }

    public String getOperateApplicationDesc() {
        if (StringUtils.isNotBlank(operateApplicationDesc)) {
            return operateApplicationDesc;
        }

        OperateApplication application = OperateApplication.fromInt(super.getOperateApplication());
        return application == null ? operateApplicationDesc : application.getDesc();
    }

    public void setOperateApplicationDesc(String operateApplicationDesc) {
        this.operateApplicationDesc = operateApplicationDesc;
    }
}
