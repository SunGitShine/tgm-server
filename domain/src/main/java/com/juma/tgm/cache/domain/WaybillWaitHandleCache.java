package com.juma.tgm.cache.domain;

import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName: WaybillWaitHandleCache
 * @Description:
 * @author: liang
 * @date: 2017-03-30 19:11
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class WaybillWaitHandleCache implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4706643735691728422L;
    /**
     * 订单id
     */
    private Integer waybillId;
    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 运单所属部门id
     */
    private String areaCode;
    /**
     * 是否处理过
     */
    private Boolean handled;

    /**
     * 额外的信息(请不要存过多数据)
     */
    private Map<String, String> extraData;


    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public Boolean getHandled() {
        return handled;
    }

    public void setHandled(Boolean handled) {
        this.handled = handled;
    }

    public Map<String, String> getExtraData() {
        return extraData;
    }

    public void setExtraData(Map<String, String> extraData) {
        this.extraData = extraData;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

}
