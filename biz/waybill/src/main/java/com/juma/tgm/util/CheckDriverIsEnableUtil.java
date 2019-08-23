package com.juma.tgm.util;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.PageCondition;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.service.WaybillCommonService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * 校验时间：同一个司机在同一个时间内不能重复接单
 */

@Component
public class CheckDriverIsEnableUtil {

    // 冗余时间：分钟
    private static final Integer REDUNDANCY_TIME = 60;

    @Resource
    private WaybillCommonService waybillCommonService;


    // 司机能否运送这个运单，true：可以；false：不可以
    public void canDriverTransportWaybill(Integer driverId, Date startTime, Date endTime) throws BusinessException {
        if (null == driverId || null == startTime || null == endTime) {
            throw new BusinessException("pleaseSelectDriver", "errors.common.prompt", "请选择司机");
        }

        boolean canReceiveWaybill = true;
        //  添加冗余时间
        endTime = DateUtil.addMinutes(endTime, REDUNDANCY_TIME);

        List<Integer> statusViewList = new ArrayList<>();
        statusViewList.add(Waybill.StatusView.WATING_DELIVERY.getCode());
        statusViewList.add(Waybill.StatusView.DELIVERYING.getCode());

        PageCondition cond = new PageCondition();
        Map<String, Object> filters = new HashMap<>();
        filters.put("driverId", driverId);
        filters.put("statusViewList", statusViewList);
        cond.setFilters(filters);
        cond.setPageNo(1);
        cond.setPageSize(Integer.MAX_VALUE);

        List<Waybill> list = waybillCommonService.search(cond);
        if (list.isEmpty()) {
            return;
        }

        for (Waybill w : list) {
            Date planDeliveryTime = w.getPlanDeliveryTime();
            Date cmEstimateFinishTime = w.getCmEstimateFinishTime() == null ?
                    (DateUtil.addMinutes(w.getPlanDeliveryTime(), w.getEstimateTimeConsumption() == null ?
                            0 : w.getEstimateTimeConsumption())) : w.getCmEstimateFinishTime();

            // 用车时间在需要派车的单的预估结束时间之后，可以派车
            if (endTime.getTime() < planDeliveryTime.getTime()) {
                continue;
            }

            // 预估结束时间在需要派车的单的用车时间之前，可以派车
            if (startTime.getTime() > cmEstimateFinishTime.getTime()) {
                continue;
            }

            canReceiveWaybill = false;
        }

        if (!canReceiveWaybill) {
            throw new BusinessException("driverCannotTransportThisWaybill", "errors.common.prompt", "此司机在指派运单的时间段内有运单，不能指派");
        }
    }
}
