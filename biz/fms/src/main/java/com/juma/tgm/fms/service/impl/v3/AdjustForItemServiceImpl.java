package com.juma.tgm.fms.service.impl.v3;

import com.google.common.collect.Lists;
import com.juma.tgm.common.workflow.ApprovalStatus;
import com.juma.tgm.fms.dao.v3.AdjustForItemMapper;
import com.juma.tgm.fms.dao.v3.ext.AdjustForItemExtMapper;
import com.juma.tgm.fms.domain.v3.AdjustForItem;
import com.juma.tgm.fms.domain.v3.AdjustForItemExample;
import com.juma.tgm.fms.domain.v3.vo.AdjustForItemExtFilter;
import com.juma.tgm.fms.domain.v3.vo.AdjustFreightVo;
import com.juma.tgm.fms.service.v3.AdjustForItemService;
import javax.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.mybatis.generator.my.mapper.Mapper;
import org.mybatis.generator.my.service.AbstractMybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Title: AdjustForItemServiceImpl
 * Description:
 * Created by gzq on 2019/5/14.
 */
@Service
public class AdjustForItemServiceImpl extends AbstractMybatisService<AdjustForItem, AdjustForItemExample> implements AdjustForItemService {

    @Autowired
    private AdjustForItemMapper adjustForItemMapper;
    @Resource
    private AdjustForItemExtMapper adjustForItemExtMapper;

    @Override
    public Mapper<AdjustForItem, AdjustForItemExample> getMapper() {
        return adjustForItemMapper;
    }

    @Override
    public List<AdjustForItem> getByFilter(AdjustForItem adjust) {
        if( null == adjust.getAdjustId()
                && null == adjust.getWaybillId()
                && StringUtils.isBlank(adjust.getWaybillNo())
                && null == adjust.getVendorId()){
            return Lists.newArrayList();
        }
        return selectByExample(new AdjustForItemExample().createCriteria()
                .andAdjustIdEqualTo(adjust.getAdjustId())
                .andWaybillIdEqualTo(adjust.getWaybillId())
                .andWaybillNoEqualTo(adjust.getWaybillNo())
                .andVendorIdEqualTo(adjust.getVendorId())
                .example());
    }

    @Override
    public AdjustFreightVo loadAdjustFreightByReconNoAndVendorId(String reconcilicationNo, Integer vendorId) {
        if (StringUtils.isBlank(reconcilicationNo) || null == vendorId) {
            return null;
        }

        AdjustForItemExtFilter filter = new AdjustForItemExtFilter();
        filter.setReconcilicationNo(reconcilicationNo);
        filter.setVendorId(vendorId);
        filter.setApprovalStatus(ApprovalStatus.APPROVAL_AGREE.getCode());
        return adjustForItemExtMapper.selectAdjustFreightByExample(filter);
    }

    @Override
    public AdjustFreightVo loadAdjustAbsFreightByWaybillIdAnd(Integer waybillId, Integer adjustType, Integer adjustForWho) {
        if (null == waybillId) {
            return null;
        }

        AdjustForItemExtFilter filter = new AdjustForItemExtFilter();
        filter.setWaybillId(waybillId);
        filter.setApprovalStatus(ApprovalStatus.APPROVAL_AGREE.getCode());
        filter.setAdjustType(adjustType);
        filter.setAdjustForWho(adjustForWho);
        return adjustForItemExtMapper.selectAdjustFreightByExample(filter);
    }

    @Override
    public void batchUpdateVendor(Integer vendorId, List<String> waybillNos) {
        if(null == vendorId || CollectionUtils.isEmpty(waybillNos)){
            return;
        }
        for (String waybillNo : waybillNos) {
            AdjustForItem adjustForItem = new AdjustForItem();
            adjustForItem.setWaybillNo(waybillNo);
            List<AdjustForItem> adjustForItems = this.getByFilter(adjustForItem);
            if(CollectionUtils.isEmpty(adjustForItems)){
                continue;
            }
            for (AdjustForItem item : adjustForItems) {
                item.setVendorId(vendorId);
                updateByPrimaryKeySelective(item);
            }
        }
    }
}
