package com.juma.tgm.landingWaybill.domain;

import com.alibaba.fastjson.annotation.JSONType;
import com.giants.common.exception.BusinessException;
import org.apache.commons.lang.math.NumberUtils;

import java.io.Serializable;

/**
 * 判断是否在围栏内结果vo
 *
 * @ClassName: AtFenceResultVo
 * @Description:
 * @author: liang
 * @date: 2017-11-27 11:13
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class AtFenceResultVo implements Serializable {

    @JSONType(serializeEnumAsJavaBean = true)
    public enum ForbiddenType {
        sourceArea(1, "取货地"),
        destinationArea(2, "配送地");
        private int code;
        private String desc;

        ForbiddenType(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public static ForbiddenType getByCode(int code) {
            for (ForbiddenType type : ForbiddenType.values()) {
                if (NumberUtils.compare(type.code, code) == 0) {
                    return type;
                }
            }
            throw new BusinessException("errors.common.prompt", "枚举类型不存在");
        }

        public int getCode() {
            return code;
        }


        public String getDesc() {
            return desc;
        }

    }

    private boolean atCity;

    private boolean atBusinessArea;

    private boolean atForbiddenArea;

    private ForbiddenType forBiddenType;

    private Integer forbiddenAreaIndex;

    public boolean getAtCity() {
        return atCity;
    }

    public void setAtCity(boolean atCity) {
        this.atCity = atCity;
    }

    public boolean getAtBusinessArea() {
        return atBusinessArea;
    }

    public void setAtBusinessArea(boolean atBusinessArea) {
        this.atBusinessArea = atBusinessArea;
    }

    public boolean isAtForbiddenArea() {
        return atForbiddenArea;
    }

    public void setAtForbiddenArea(boolean atForbiddenArea) {
        this.atForbiddenArea = atForbiddenArea;
    }

    public ForbiddenType getForBiddenType() {
        return forBiddenType;
    }

    public void setForBiddenType(ForbiddenType forBiddenType) {
        this.forBiddenType = forBiddenType;
    }

    public Integer getForbiddenAreaIndex() {
        return forbiddenAreaIndex;
    }

    public void setForbiddenAreaIndex(Integer forbiddenAreaIndex) {
        this.forbiddenAreaIndex = forbiddenAreaIndex;
    }
}
