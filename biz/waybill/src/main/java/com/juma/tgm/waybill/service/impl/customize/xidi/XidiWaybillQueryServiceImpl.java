package com.juma.tgm.waybill.service.impl.customize.xidi;

import com.giants.common.exception.BusinessException;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillMap;
import com.juma.tgm.waybill.domain.WaybillOperateTrack;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateApplication;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateType;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillOperateTrackService;
import com.juma.tgm.waybill.service.WaybillTrackService;
import com.juma.tgm.waybill.service.customize.xidi.XidiWaybillQueryService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @ClassName: XidiWaybillQueryServiceImpl
 * @Description:
 * @author: liang
 * @date: 2018-03-14 19:24
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
@Service
public class XidiWaybillQueryServiceImpl implements XidiWaybillQueryService {

    @Resource
    private WaybillOperateTrackService waybillOperateTrackService;

    @Resource
    private WaybillCommonService waybillCommonService;

    @Resource
    private WaybillTrackService waybillTrackService;

    @Override
    public WaybillMap findWaybillMapById(Integer waybillId) throws BusinessException {
        Waybill waybill = waybillCommonService.getWaybillById(waybillId);
        waybillTrackService.getWaybillTraceInfo(waybill);
        WaybillMap map = waybillCommonService.buildWaybillMap(waybill);

        List<WaybillOperateTrack> arriveDepots = waybillOperateTrackService.listByWaybillId(waybillId);
        for (WaybillOperateTrack operateTrack : arriveDepots) {
            if (operateTrack.getOperateApplication() == null || operateTrack.getOperateType() == null) continue;
            // 电子围栏到仓
            if (operateTrack.getOperateApplication() == OperateApplication.FRNCE.getCode()
                    && operateTrack.getOperateType() == OperateType.ARRIVE_DEPOT.getCode()) {
                map.setFenceArriveDepotTime(operateTrack.getDeclareTime());
                map.setFenceArriveLocation(operateTrack.getOperateAddressCoordinates());
            }

            // 电子围栏到仓
            if (operateTrack.getOperateApplication() == OperateApplication.FRNCE.getCode()
                    && operateTrack.getOperateType() == OperateType.LEAVE_DEPOT.getCode()) {
                map.setFenceLeaveDepotTime(operateTrack.getCreateTime());
            }
        }

        return map;
    }
}
