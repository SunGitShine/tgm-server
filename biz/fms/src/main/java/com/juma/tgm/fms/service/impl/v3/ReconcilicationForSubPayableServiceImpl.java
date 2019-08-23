package com.juma.tgm.fms.service.impl.v3;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.id.IdGeneratorTable;
import com.juma.tgm.fms.dao.v3.ReconcilicationForSubPayableMapper;
import com.juma.tgm.fms.domain.v3.ReconcilicationForSubPayable;
import com.juma.tgm.fms.domain.v3.ReconcilicationForSubPayableExample;
import com.juma.tgm.fms.domain.v3.enums.PayableSettlementStatusEnum;
import com.juma.tgm.fms.service.v3.ReconcilicationForSubPayableService;
import com.juma.tgm.id.service.IdGeneratorService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ReconcilicationForSubPayableServiceImpl implements ReconcilicationForSubPayableService {

    @Resource
    private ReconcilicationForSubPayableMapper reconcilicationForSubPayableMapper;
    @Resource
    private IdGeneratorService idGeneratorService;

    @Override
    public String insert(Integer reconcilicationId, Integer vendorId, LoginUser loginUser) throws BusinessException {
        if (null == reconcilicationId) {
            throw new BusinessException("reconcilicationIdReqired", "reconcilicationForSubPayable.error.reconcilicationIdReqired");
        }

        if (null == vendorId) {
            throw new BusinessException("vendorIdReqired", "reconcilicationForSubPayable.error.vendorIdReqired");
        }

        ReconcilicationForSubPayable subPayable = new ReconcilicationForSubPayable();
        subPayable.setReconcilicationId(reconcilicationId);
        subPayable.setVendorId(vendorId);
        subPayable.setSubReconcilicationNo(idGeneratorService.genId(IdGeneratorTable.IdType.APS));
        subPayable.setCreateUserId(loginUser.getUserId());
        subPayable.setCreateTime(new Date());
        subPayable.setSettleStatus(Byte.valueOf(String.valueOf(PayableSettlementStatusEnum.NOT_CLEAR.getCode())));
        reconcilicationForSubPayableMapper.insert(subPayable);
        return subPayable.getSubReconcilicationNo();
    }

    @Override
    public ReconcilicationForSubPayable findBySubReconcilicationNo(String subReconcilicationNo) {
        if (StringUtils.isBlank(subReconcilicationNo)) {
            return null;
        }

        ReconcilicationForSubPayableExample example = new ReconcilicationForSubPayableExample();
        ReconcilicationForSubPayableExample.Criteria criteria = example.createCriteria();
        criteria.andSubReconcilicationNoEqualTo(subReconcilicationNo);
        List<ReconcilicationForSubPayable> list = reconcilicationForSubPayableMapper.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<ReconcilicationForSubPayable> listByReconcilicationId(Integer reconcilicationId) {
        if (null == reconcilicationId) {
            return null;
        }

        ReconcilicationForSubPayableExample example = new ReconcilicationForSubPayableExample();
        ReconcilicationForSubPayableExample.Criteria criteria = example.createCriteria();
        criteria.andReconcilicationIdEqualTo(reconcilicationId);
        return reconcilicationForSubPayableMapper.selectByExample(example);
    }

    @Override
    public boolean checkIsAllHasClear(Integer reconcilicationId) {
        if (null == reconcilicationId) {
            return true;
        }

        List<ReconcilicationForSubPayable> list = this.listByReconcilicationId(reconcilicationId);
        for (ReconcilicationForSubPayable p : list) {
            if (NumberUtils.compare(p.getSettleStatus(), PayableSettlementStatusEnum.HAS_CLEAR.getCode()) != 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ReconcilicationForSubPayable findByReconcilicationIdAndVendorId(Integer reconcilicationId, Integer vendorId) {
        if (null == reconcilicationId || null == vendorId) {
            return null;
        }

        ReconcilicationForSubPayableExample example = new ReconcilicationForSubPayableExample();
        ReconcilicationForSubPayableExample.Criteria criteria = example.createCriteria();
        criteria.andReconcilicationIdEqualTo(reconcilicationId);
        criteria.andVendorIdEqualTo(vendorId);
        List<ReconcilicationForSubPayable> list = reconcilicationForSubPayableMapper.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }
}
