package com.juma.tgm.fms.service.impl.v3;

import com.juma.tgm.fms.dao.v3.ReconcilicationForPayableItemMapper;
import com.juma.tgm.fms.dao.v3.ReconcilicationForSubPayableMapper;
import com.juma.tgm.fms.domain.v3.ReconcilicationForPayableItem;
import com.juma.tgm.fms.domain.v3.ReconcilicationForPayableItemExample;
import com.juma.tgm.fms.domain.v3.ReconcilicationForSubPayable;
import com.juma.tgm.fms.domain.v3.ReconcilicationForSubPayableExample;
import com.juma.tgm.fms.domain.v3.enums.PayableSettleAccountTypeEnum;
import com.juma.tgm.fms.domain.v3.enums.PayableSettlementStatusEnum;
import com.juma.tgm.fms.service.v3.ReconciliationForPayableSyncService;
import com.juma.tgm.fms.service.v3.ReconcilicationForSubPayableService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.service.WaybillCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReconciliationSyncForPayableServiceImpl implements ReconciliationForPayableSyncService {

    private static final Logger logger = LoggerFactory.getLogger(ReconciliationSyncForPayableServiceImpl.class);

    @Autowired
    private ReconcilicationForPayableItemMapper reconcilicationForPayableItemMapper;
    @Autowired
    private WaybillCommonService waybillCommonService;
    @Autowired
    private ReconcilicationForSubPayableService reconcilicationForSubPayableService;
    @Autowired
    private ReconcilicationForSubPayableMapper reconcilicationForSubPayableMapper;

    @Override
    public void settlement(String subReconciliationNo, String settlementStatus) {
        //部分结算时-更新子对账单表
        if(settlementStatus.equals(PayableSettlementStatusEnum.PORTION_CLEAR.getDesc())){
            ReconcilicationForSubPayable subPayables = reconcilicationForSubPayableService.findBySubReconcilicationNo(subReconciliationNo);
            if(null != subPayables){
                subPayables.setSettleStatus((byte) PayableSettlementStatusEnum.PORTION_CLEAR.getCode());
                reconcilicationForSubPayableMapper.updateByPrimaryKeySelective(subPayables);
            }
        }
        //已结算时-更新子对账单、运单、对账单
        if(settlementStatus.equals(PayableSettlementStatusEnum.HAS_CLEAR.getDesc())){
            ReconcilicationForSubPayable subPayables = reconcilicationForSubPayableService.findBySubReconcilicationNo(subReconciliationNo);
            if(null != subPayables){
                //更新子对账单
                subPayables.setSettleStatus((byte) PayableSettlementStatusEnum.HAS_CLEAR.getCode());
                reconcilicationForSubPayableMapper.updateByPrimaryKeySelective(subPayables);
                //更新运单表
                ReconcilicationForPayableItemExample example = new ReconcilicationForPayableItemExample();
                ReconcilicationForPayableItemExample.Criteria criteria = example.createCriteria();
                criteria.andReconcilicationIdEqualTo(subPayables.getReconcilicationId());
                criteria.andSettleAccountIdEqualTo(subPayables.getVendorId());
                criteria.andSettleTypeEqualTo(PayableSettleAccountTypeEnum.VENDOR.getCode());
                List<ReconcilicationForPayableItem> payableItems = reconcilicationForPayableItemMapper.selectByExample(example);
                for(ReconcilicationForPayableItem payableItem : payableItems){
                    Waybill waybill = waybillCommonService.getWaybillById(payableItem.getWaybillId());
                    waybill.setSettlementStatus(PayableSettlementStatusEnum.HAS_CLEAR.getCode());
                    waybillCommonService.update(waybill,null);
                    //更新对账单明细表
                    ReconcilicationForPayableItem item = new ReconcilicationForPayableItem();
                    item.setReconcilicationItemId(payableItem.getReconcilicationItemId());
                    item.setSettleStatus(PayableSettlementStatusEnum.HAS_CLEAR.getCode());
                    reconcilicationForPayableItemMapper.updateByPrimaryKeySelective(item);
                }
            }
        }
    }

    @Override
    public void updateSubPayableVendor(List<String> subReconciliationNos, Integer vendorId) {

        if(vendorId == null || subReconciliationNos == null || subReconciliationNos.isEmpty()){
            return;
        }

        ReconcilicationForSubPayableExample example = new ReconcilicationForSubPayableExample();
        ReconcilicationForSubPayableExample.Criteria criteria = example.createCriteria();
        criteria.andSubReconcilicationNoIn(subReconciliationNos);

        ReconcilicationForSubPayable record = new ReconcilicationForSubPayable();
        record.setVendorId(vendorId);

        reconcilicationForSubPayableMapper.updateByExampleSelective(record, example);
    }

    @Override
    public void updatePayableItemVendor(List<String> waybillNos, Integer vendorId, String vendorName) {

        if(vendorId == null || waybillNos == null || waybillNos.isEmpty()){
            return;
        }

        ReconcilicationForPayableItemExample example = new ReconcilicationForPayableItemExample();
        ReconcilicationForPayableItemExample.Criteria criteria = example.createCriteria();
        criteria.andSettleTypeEqualTo(PayableSettleAccountTypeEnum.VENDOR.getCode()).andWaybillNoIn(waybillNos);

        ReconcilicationForPayableItem record = new ReconcilicationForPayableItem();
        record.setSettleAccountId(vendorId);
        record.setSettleAccountName(vendorName);

        reconcilicationForPayableItemMapper.updateByExampleSelective(record, example);
    }
}
