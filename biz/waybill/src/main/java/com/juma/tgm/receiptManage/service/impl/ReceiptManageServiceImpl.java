package com.juma.tgm.receiptManage.service.impl;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.imageUploadManage.domain.ImageUploadManage;
import com.juma.tgm.imageUploadManage.service.ImageUploadManageService;
import com.juma.tgm.receiptManage.dao.ReceiptManageDao;
import com.juma.tgm.receiptManage.domain.ReceiptManage;
import com.juma.tgm.receiptManage.service.ReceiptManageService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillParamService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ReceiptManageServiceImpl implements ReceiptManageService {

    @Resource
    private ReceiptManageDao receiptManageDao;
    @Resource
    private ImageUploadManageService imageUploadManageService;
    @Resource
    private WaybillCommonService waybillCommonService;
    @Resource
    private WaybillParamService waybillParamService;

    @Override
    public void insert(ReceiptManage receiptManage, LoginUser loginUser) throws BusinessException {
        if (receiptManage.getImgUrls().isEmpty()) {
            throw new BusinessException("receiptImageRequired", "receiptManage.error.receiptImageRequired");
        }

        // 回单上传记录
        receiptManage.setCreateUserId(loginUser.getUserId());
        receiptManageDao.insert(receiptManage);
        Integer receiptmanageId = receiptManage.getReceiptManageId();

        // 上传图片
        List<String> urlList = receiptManage.getImgUrls();
        for (String url : urlList) {
            ImageUploadManage imageUploadManage = new ImageUploadManage();
            imageUploadManage.setImageUploadUrl(BaseUtil.buildImgUrl(url));
            imageUploadManage.setRelationId(receiptmanageId);
            imageUploadManage.setCreateUserId(loginUser.getUserId());
            imageUploadManage.setImageUploadManageSign(ImageUploadManage.ImageUploadManageSign.NEED_RECEIPT.getCode());
            imageUploadManageService.insert(imageUploadManage, loginUser);
        }

        // 将运单回单状态修改为已上传
        this.updateWaybillNeedReceipt(receiptManage.getWaybillId(), Waybill.NeedReceipt.HAS_UPLOAD, loginUser);
    }

    @Override
    public void deleteAndInsert(ReceiptManage receiptManage, LoginUser loginUser) throws BusinessException {
        Integer receiptmanageId = receiptManage.getReceiptManageId();
        ReceiptManage manage = receiptManageDao.get(receiptmanageId);
        if (null == manage) {
            return;
        }

        if (receiptManage.getImgUrls().isEmpty()) {
            throw new BusinessException("receiptImageRequired", "receiptManage.error.receiptImageRequired");
        }

        imageUploadManageService.delete(receiptmanageId, ImageUploadManage.ImageUploadManageSign.NEED_RECEIPT,
                loginUser);
        // 上传图片
        List<String> urlList = receiptManage.getImgUrls();
        for (String url : urlList) {
            ImageUploadManage imageUploadManage = new ImageUploadManage();
            imageUploadManage.setImageUploadUrl(BaseUtil.buildImgUrl(url));
            imageUploadManage.setRelationId(receiptmanageId);
            imageUploadManage.setCreateUserId(loginUser.getUserId());
            imageUploadManage.setImageUploadManageSign(ImageUploadManage.ImageUploadManageSign.NEED_RECEIPT.getCode());
            imageUploadManageService.insert(imageUploadManage, loginUser);
        }

        // 将回单删除完修修改为未上传
        if (urlList.isEmpty()) {
            this.updateWaybillNeedReceipt(receiptManage.getWaybillId(), Waybill.NeedReceipt.NOT_HAVE_UPLOAD, loginUser);
            return;
        }

        this.updateWaybillNeedReceipt(receiptManage.getWaybillId(), Waybill.NeedReceipt.HAS_UPLOAD, loginUser);
    }

    // 修改运单回单状态
    private void updateWaybillNeedReceipt(Integer waybillId, Waybill.NeedReceipt needReceipt, LoginUser loginUser) {
        if (null == waybillId) {
            return;
        }

        Waybill waybill = new Waybill();
        waybill.setWaybillId(waybillId);
        waybill.setNeedReceipt(needReceipt.getCode());
        waybillCommonService.update(waybill, loginUser);

        // 判断运单是不是转运单
        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybillId);
        if (null == waybillParam || null == waybillParam.getTransformBillLinkId()) {
            return;
        }

        Waybill vendorWaybill = new Waybill();
        vendorWaybill.setWaybillId(waybillParam.getTransformBillLinkId());
        vendorWaybill.setNeedReceipt(needReceipt.getCode());
        waybillCommonService.update(vendorWaybill, loginUser);
    }

    @Override
    public List<ReceiptManage> listByWaybillId(Integer waybillId) {
        // 规则：所有的回单信息都管理原始运单，所以承运运单获取回单信息，则要根据原单的ID获取
        Waybill transformBill = waybillCommonService.findWaybillByTransformBillId(waybillId);
        if (null != transformBill) {
            waybillId = transformBill.getWaybillId();
        }

        ReceiptManage example = new ReceiptManage();
        example.setWaybillId(waybillId);
        List<ReceiptManage> receiptManageList = receiptManageDao.findByExample(example);
        for (ReceiptManage receiptManage : receiptManageList) {
            List<ImageUploadManage> imageUploadManageList = imageUploadManageService.listByRelationIdAndSign(
                    receiptManage.getReceiptManageId(), ImageUploadManage.ImageUploadManageSign.NEED_RECEIPT.getCode());
            for (ImageUploadManage imageUploadManage : imageUploadManageList) {
                receiptManage.getImgUrls().add(imageUploadManage.getImageUploadUrl());
            }
        }
        return receiptManageList;

    }

    @Override
    public List<ImageUploadManage> listReceiptImageByWaybillId(Integer waybillId) {
        // 规则：所有的回单信息都管理原始运单，所以承运运单获取回单信息，则要根据原单的ID获取
        Waybill transformBill = waybillCommonService.findWaybillByTransformBillId(waybillId);
        if (null != transformBill) {
            waybillId = transformBill.getWaybillId();
        }

        List<ImageUploadManage> result = new ArrayList<ImageUploadManage>();
        ReceiptManage example = new ReceiptManage();
        example.setWaybillId(waybillId);
        List<ReceiptManage> receiptManageList = receiptManageDao.findByExample(example);
        for (ReceiptManage receiptManage : receiptManageList) {
            List<ImageUploadManage> imageUploadManageList = imageUploadManageService.listByRelationIdAndSign(
                    receiptManage.getReceiptManageId(), ImageUploadManage.ImageUploadManageSign.NEED_RECEIPT.getCode());
            result.addAll(imageUploadManageList);
        }
        return result;
    }

    @Override
    public List<ImageUploadManage> listGoodsImageByWaybillId(Integer waybillId) {
        List<ImageUploadManage> imageUploadManageList = imageUploadManageService.listByRelationIdAndSign(waybillId,
                ImageUploadManage.ImageUploadManageSign.GOODS_CHECK.getCode());
        return imageUploadManageList;
    }
}
