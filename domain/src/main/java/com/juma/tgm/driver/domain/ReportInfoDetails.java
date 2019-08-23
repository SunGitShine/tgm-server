package com.juma.tgm.driver.domain;

import com.giants.common.collections.CollectionUtils;
import com.juma.conf.domain.ConfParamOption;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName ReportInfoDetail.java 路况报备详情
 * @author Libin.Wei
 * @Date 2017年4月27日 下午5:00:57
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */
public class ReportInfoDetails implements Serializable {

    private static final long serialVersionUID = -2625890451316488364L;
    private Integer reportDetailId;
    private Integer reportInfoId;
    private Integer reportInfoType;
    private String imageUrl;
    private String coordinate;
    private String addressDetail;
    private String deviceNo;
    private Integer deviceType;
    private Date reportTime;
    private Integer createUserId;
    private String remark;

    // 非数据库字段：地图标注点类型
    private String labelPointType;
    // 非数据库字段：地图标注点title
    private String title;
    // 添加冗余字段：图片集合
    private List<String> imageUrlList = new ArrayList<String>();
    // 显示冗余
    private String reportInfoTypeView;
    
    // 查询冗余
    private String orderDesc;
    private Integer waybillId;

    private List<ConfParamOption> allReportTypes;

    public Integer getReportInfoType() {
        return reportInfoType;
    }

    public void setReportInfoType(Integer reportInfoType) {
        this.reportInfoType = reportInfoType;
    }

    public Integer getReportDetailId() {
        return reportDetailId;
    }

    public void setReportDetailId(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public Integer getReportInfoId() {
        return reportInfoId;
    }

    public void setReportInfoId(Integer reportInfoId) {
        this.reportInfoId = reportInfoId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLabelPointType() {
        return labelPointType;
    }

    public void setLabelPointType(String labelPointType) {
        this.labelPointType = labelPointType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImageUrlList() {
        return imageUrlList;
    }

    public void setImageUrlList(List<String> imageUrlList) {
        this.imageUrlList = imageUrlList;
    }

    public String getReportInfoTypeView() {
        return reportInfoTypeView;
    }

    public void setReportInfoTypeView(String reportInfoTypeView) {
        this.reportInfoTypeView = reportInfoTypeView;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public enum LabelPointType {
        DELIVERY_ADDRESS, ROAD_CONDITION_REPORT, RECEIVE_ADDRESS,TRUCK_PARKING,SIGN_INFO;
    }

    @Override
    public String toString() {
        return "ReportInfoDetail{" +
                "reportDetailId=" + reportDetailId +
                ", imageUrl='" + imageUrl + '\'' +
                ", coordinate='" + coordinate + '\'' +
                ", addressDetail='" + addressDetail + '\'' +
                ", deviceNo='" + deviceNo + '\'' +
                ", deviceType=" + deviceType +
                ", reportTime=" + reportTime +
                ", createUserId=" + createUserId +
                ", labelPointType='" + labelPointType + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReportInfoDetails that = (ReportInfoDetails) o;

        if (reportDetailId != null ? !reportDetailId.equals(that.reportDetailId) : that.reportDetailId != null)
            return false;
        if (reportInfoId != null ? !reportInfoId.equals(that.reportInfoId) : that.reportInfoId != null) return false;
        if (imageUrl != null ? !imageUrl.equals(that.imageUrl) : that.imageUrl != null) return false;
        if (coordinate != null ? !coordinate.equals(that.coordinate) : that.coordinate != null) return false;
        if (addressDetail != null ? !addressDetail.equals(that.addressDetail) : that.addressDetail != null)
            return false;
        if (deviceNo != null ? !deviceNo.equals(that.deviceNo) : that.deviceNo != null) return false;
        if (deviceType != null ? !deviceType.equals(that.deviceType) : that.deviceType != null) return false;
        if (reportTime != null ? !reportTime.equals(that.reportTime) : that.reportTime != null) return false;
        if (createUserId != null ? !createUserId.equals(that.createUserId) : that.createUserId != null) return false;
        if (labelPointType != null ? !labelPointType.equals(that.labelPointType) : that.labelPointType != null)
            return false;
        return title != null ? title.equals(that.title) : that.title == null;
    }

    @Override
    public int hashCode() {
        int result = reportDetailId != null ? reportDetailId.hashCode() : 0;
        result = 31 * result + (reportInfoId != null ? reportInfoId.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (coordinate != null ? coordinate.hashCode() : 0);
        result = 31 * result + (addressDetail != null ? addressDetail.hashCode() : 0);
        result = 31 * result + (deviceNo != null ? deviceNo.hashCode() : 0);
        result = 31 * result + (deviceType != null ? deviceType.hashCode() : 0);
        result = 31 * result + (reportTime != null ? reportTime.hashCode() : 0);
        result = 31 * result + (createUserId != null ? createUserId.hashCode() : 0);
        result = 31 * result + (labelPointType != null ? labelPointType.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    public String getReportTypeName() {
        if(CollectionUtils.isEmpty(this.allReportTypes)) return null;
        if(this.reportInfoType == null) return null;

        for(ConfParamOption conf : this.allReportTypes){
            if(StringUtils.isBlank(conf.getOptionValue())) continue;
            if(!NumberUtils.isNumber(conf.getOptionValue())) continue;

            if(NumberUtils.compare(NumberUtils.createDouble(conf.getOptionValue()), this.reportInfoType) == 0) return conf.getOptionName();
        }
        return null;
    }

    public void setAllReportTypes(List<ConfParamOption> allReportTypes) {
        this.allReportTypes = allReportTypes;
    }
}