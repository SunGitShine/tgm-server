package com.juma.tgm.reportInfo.service.impl;

import com.alibaba.fastjson.JSON;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.driver.domain.ReportInfo;
import com.juma.tgm.driver.domain.ReportInfoDetails;
import com.juma.tgm.gaode.domain.AmapRegeoResponse;
import com.juma.tgm.reportInfo.dao.ReportInfoDetailsDao;
import com.juma.tgm.reportInfo.service.ReportInfoDetailService;
import com.juma.tgm.reportInfo.service.ReportInfoService;
import com.juma.tgm.waybill.service.GaoDeMapService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportInfoDetailServiceImpl implements ReportInfoDetailService {

    @Resource
    private ReportInfoDetailsDao reportInfoDetailsDao;
    @Resource
    private ReportInfoService reportInfoService;
    @Resource
    private GaoDeMapService gaoDeMapService;

    @Override
    public List<ReportInfoDetails> ListByReportId(Integer reportInfoId) {
        if (null == reportInfoId) {
            return new ArrayList<ReportInfoDetails>();
        }
        ReportInfoDetails example = new ReportInfoDetails();
        example.setReportInfoId(reportInfoId);
        List<ReportInfoDetails> result = reportInfoDetailsDao.findByExample(example);
        // 将JSON图片URL转换为list
        for (ReportInfoDetails reportInfoDetails : result) {
            if (StringUtils.isBlank(reportInfoDetails.getImageUrl())) {
                continue;
            }
            List<String> imageUrlList = JSON.parseArray(reportInfoDetails.getImageUrl(), String.class);
            if (CollectionUtils.isNotEmpty(imageUrlList)) {
                reportInfoDetails.setImageUrlList(imageUrlList);

                // 临时方法：由于没有显示所有图片的组件，先显示第一张
                reportInfoDetails.setImageUrl(imageUrlList.get(0));
            }
        }
        return result;
    }

    @Override
    public ReportInfoDetails getReportInfoDetail(Integer detailId) {
        if (null == detailId) {
            return null;
        }

        ReportInfoDetails reportInfoDetails = reportInfoDetailsDao.get(detailId);
        if (null == reportInfoDetails) {
            return null;
        }

        if (StringUtils.isNotBlank(reportInfoDetails.getImageUrl())) {
            List<String> imageUrlList = JSON.parseArray(reportInfoDetails.getImageUrl(), String.class);
            if (CollectionUtils.isNotEmpty(imageUrlList)) {
                reportInfoDetails.setImageUrlList(imageUrlList);

                // 临时方法：由于没有显示所有图片的组件，先显示第一张
                reportInfoDetails.setImageUrl(imageUrlList.get(0));
            }
        }
        return reportInfoDetails;
    }

    @Override
    public Integer findCountByReportId(Integer reportId) {
        if (null == reportId) {
            return 0;
        }
        PageCondition page = new PageCondition();
        page.getFilters().put("reportInfoId", reportId);
        return reportInfoDetailsDao.searchCount(page);
    }

    @Override
    public void insert(ReportInfoDetails reportInfoDetails, LoginUser loginUser) throws BusinessException {
        // 获取图片url的半地址
        List<String> simpleImageUrlList = new ArrayList<String>();
        for (String imageUrl : reportInfoDetails.getImageUrlList()) {
            String simpleImageUrl = BaseUtil.buildImgUrl(imageUrl);
            if (StringUtils.isNotBlank(simpleImageUrl)) {
                simpleImageUrlList.add(BaseUtil.buildImgUrl(simpleImageUrl));
            }
        }

        if (!simpleImageUrlList.isEmpty()) {
            reportInfoDetails.setImageUrl(JSON.toJSONString(simpleImageUrlList));
        }

        // 上报地址
        String coordinate = reportInfoDetails.getCoordinate();
        if (StringUtils.isNotBlank(coordinate)) {
            AmapRegeoResponse regeocode = gaoDeMapService.regeocode(coordinate);
            if (null != regeocode) {
                reportInfoDetails.setAddressDetail(regeocode.getRegeocode().getFormattedAddress());
            }
        }

        reportInfoDetails.setCreateUserId(loginUser.getUserId());
        reportInfoDetailsDao.insert(reportInfoDetails);
    }

    @Override
    public List<ReportInfoDetails> listByDriverIdAndWaybillId(Integer driverId, Integer waybillId, LoginUser loginUser) {
        ReportInfo reportInfo = reportInfoService.findByDriverIdAndWaybillId(null, driverId, waybillId, loginUser);
        if (null == reportInfo) {
            return new ArrayList<ReportInfoDetails>();
        }

        return ListByReportId(reportInfo.getReportInfoId());
    }

    @Override
    public List<ReportInfoDetails> listByWaybillId(Integer waybillId, String orderDesc) {
        ReportInfoDetails example = new ReportInfoDetails();
        example.setWaybillId(waybillId);
        example.setOrderDesc(StringUtils.isBlank(orderDesc) ? "asc" : orderDesc);

        List<ReportInfoDetails> result = reportInfoDetailsDao.findAllBy(example);
        // 将JSON图片URL转换为list
        for (ReportInfoDetails reportInfoDetails : result) {
            if (StringUtils.isBlank(reportInfoDetails.getImageUrl())) {
                continue;
            }
            List<String> imageUrlList = JSON.parseArray(reportInfoDetails.getImageUrl(), String.class);
            if (CollectionUtils.isNotEmpty(imageUrlList)) {
                reportInfoDetails.setImageUrlList(imageUrlList);
            }
        }
        return result;
    }
}
