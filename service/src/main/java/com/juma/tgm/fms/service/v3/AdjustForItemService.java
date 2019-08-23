package com.juma.tgm.fms.service.v3;

import com.juma.tgm.fms.domain.v3.AdjustForItem;

import com.juma.tgm.fms.domain.v3.vo.AdjustFreightVo;
import java.util.List;

/**
 * Title: adjustForItemService
 * Description:
 * Created by gzq on 2019/5/14.
 */
public interface AdjustForItemService {

    /**
     * 根据adjustForItem筛选
     * @param adjust
     * @return
     */
    List<AdjustForItem> getByFilter(AdjustForItem adjust);

    /**
     * 根据对账单号和承运商ID获取调整金额: 审核通过的
     *
     * @param reconcilicationNo
     * @param vendorId
     *
     * @return
     */
    AdjustFreightVo loadAdjustFreightByReconNoAndVendorId(String reconcilicationNo, Integer vendorId);

    /**
     * 根据运单ID、调整阶段、调整主体获取调整金额: 审核通过的
     *
     * @param waybillId
     * @param adjustType
     * @param adjustForWho
     *
     * @return
     */
    AdjustFreightVo loadAdjustAbsFreightByWaybillIdAnd(Integer waybillId, Integer adjustType, Integer adjustForWho);

    /**
     * 批量更新调整单明细承运商id
     * @param vendorId
     * @param waybillNos
     */
    void batchUpdateVendor(Integer vendorId, List<String> waybillNos);
}
