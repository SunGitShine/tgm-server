package com.juma.tgm.daily.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Data
public class ProjectDailyStatistic {
    private Integer projectDailyId;

    private Integer tenantId;

    private String areaCode;

    private String projectDaliyNo;

    private Date projectDailyDate;

    private Integer projectId;

    private Integer customerId;

    private Integer dailyStatus;

    private Integer standingBookStatus;

    private Integer freightStatus;

    private Integer waybillTotal;

    private Integer waybillNotFinishNo;

    private Integer waybillNotConfirmedNo;

    private BigDecimal customerFreightWithTax;

    private BigDecimal vendorFreightWithTax;

    private BigDecimal lastCustomerFreightWithTax;

    private BigDecimal lastVendorFreightWithTax;

    private Boolean isDelete;

    private Integer createUserId;

    private Date createTime;

    private Integer lastUpdateUserId;

    private Date lastUpdateTime;


}