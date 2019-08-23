package com.juma.tgm.project.vo;

import com.alibaba.fastjson.JSONObject;
import com.juma.tgm.project.domain.Project;

import java.io.Serializable;


public class ProjectOpsVo extends Project implements Serializable {

    /**
     * 时间范围
     */
    private Integer timeRange;

    public enum TimeRangeEnum {

        DAY(1,"日"),WEEK(2,"周"),MONTH(3,"月");

        private int code;

        private String msg;

        private TimeRangeEnum(int code,String msg) {
            this.code = code;
            this.msg = msg;
        }
        public int getCode() {
            return code;
        }
        public void setCode(int code) {
            this.code = code;
        }
        public String getMsg() {
            return msg;
        }
        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    /**
     *开始时间
     */
    private String startDate;

    /**
     *结束时间
     */
    private String endDate;

    /**
     *运单统计
     */
    private JSONObject waybillData;

    /**
     *运费统计
     */
    private JSONObject freightData;

    public Integer getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(Integer timeRange) {
        this.timeRange = timeRange;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public JSONObject getWaybillData() {
        return waybillData;
    }

    public void setWaybillData(JSONObject waybillData) {
        this.waybillData = waybillData;
    }

    public JSONObject getFreightData() {
        return freightData;
    }

    public void setFreightData(JSONObject freightData) {
        this.freightData = freightData;
    }
}
