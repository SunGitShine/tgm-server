package com.juma.tgm.tools.service.impl;

import com.giants.common.exception.BusinessException;
import com.google.common.collect.Maps;
import com.juma.fms.v2.core.payment.payable.domain.WayBillStatus;
import com.juma.fms.v2.core.payment.payable.service.FmsPayableForAdjust;
import com.juma.tgm.tools.service.FmsCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * 功能 : 
 * fms系统调用公共类
 * @author : Bruce(刘正航) 13:58 2019-05-16
 */
@Service
public class FmsCommonServiceImpl implements FmsCommonService {

    @Autowired
    private FmsPayableForAdjust fmsPayableForAdjust;

    @Override
    public Map<String,WayBillStatus> canCreateAdjust(String reconcilicationNo, List<String> waybillNos) throws BusinessException {
        List<WayBillStatus> wayBillStatuses = fmsPayableForAdjust.isFrozen(reconcilicationNo,waybillNos);
        Map<String,WayBillStatus> wayBillStatusMap = Maps.newConcurrentMap();
        if(CollectionUtils.isEmpty(wayBillStatuses)){ return wayBillStatusMap; }
        for (WayBillStatus status : wayBillStatuses){
            WayBillStatus oldStatus = wayBillStatusMap.get(status.getWayBillNo());
            if( null == oldStatus ){
                wayBillStatusMap.put(status.getWayBillNo(),status);
            }else if(Integer.valueOf(1).equals(status.getIsFrozen())){
                wayBillStatusMap.put(status.getWayBillNo(),status);
            }
        }
        return wayBillStatusMap;
    }

    @Override
    public void frozenPayable(String reconcilicationNo, List<String> vendorIds, String adjustNo) throws BusinessException {
        fmsPayableForAdjust.setUsed(reconcilicationNo,vendorIds,adjustNo);
    }

    @Override
    public void unfrozenPayable(String reconcilicationNo, List<String> vendorIds) throws BusinessException {
        fmsPayableForAdjust.unsetUsed(reconcilicationNo,vendorIds);
    }

}
