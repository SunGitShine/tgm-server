package com.juma.tgm.util;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.giants.common.exception.BusinessException;
import com.juma.server.vm.domain.Flight;
import com.juma.server.vm.domain.FlightUsage;
import com.juma.server.vm.service.vehicle.AmsServiceV2;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.waybill.domain.Waybill;

/**
 * 运单加跑班次占用优化提示
 *
 * @ClassName: CreateWaybillWithDriverTipUtil
 * @Description:
 * @author: liang
 * @date: 2017-08-24 16:43
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
@Component
public class CreateWaybillWithDriverTipUtil {

    private static final Logger log = LoggerFactory.getLogger(CreateWaybillWithDriverTipUtil.class);

    @Resource
    private AmsServiceV2 amsServiceV2;

    /**
     * 判断是否有计划用车时间和未配送完成运单预估结束时间冲突的运单
     *
     * @param driverId
     * @param waybill
     * @throws BusinessException
     */
    public void checkHasUnDeliverWaybill(int driverId, Waybill waybill) throws BusinessException {

        //获取当前司机班次占用情况
        //开始时间
        Date startTime = new Date();
        //结束时间
        Date endTime = DateUtils.addDays(startTime, 2);
        endTime = DateUtils.setHours(endTime, 23);
        endTime = DateUtils.setMinutes(endTime, 59);
        endTime = DateUtils.setSeconds(endTime, 59);

        LinkedHashMap<Flight, List<FlightUsage>> flightUsage = (LinkedHashMap<Flight, List<FlightUsage>>) amsServiceV2.findFlightAndFlightUsageBy(driverId, startTime, endTime);
        //通过ams获取当前司机已占用
        if (org.apache.commons.collections.MapUtils.isEmpty(flightUsage)) return;

        this.buildFlightTip(flightUsage, waybill);
    }


    /**
     * 判断是否有可用班次
     *
     * @param flights
     * @param currentWaybill
     */
    private void buildFlightTip(LinkedHashMap<Flight, List<FlightUsage>> flights, Waybill currentWaybill) {
        if (org.apache.commons.collections.MapUtils.isEmpty(flights)) {
            throw new BusinessException("noFlight", "waybill.error.noFlight");
        }
        for (Flight flight : flights.keySet()) {
            if (log.isDebugEnabled()) {
                log.info(DateUtil.format(flight.getStartTime(), DateUtil.YYYYMMDDHHMM));
            }

            //确定班次可用
            if (this.correctBillTimeForFlight(currentWaybill.getPlanDeliveryTime(), currentWaybill.getCmEstimateFinishTime(), flight)) {
                //找出合适的时间
                this.buildFlightUsageTip(flights.get(flight), currentWaybill);
            }
        }

    }

    /**
     * 是否有适当的班次
     * 判断运单时间是否在班次内
     */
    private boolean correctBillTimeForFlight(Date currentPlanTime, Date currentEstimateFinishTime, Flight flight) {
        Date flightStart = flight.getStartTime();
        Date flightEnd = flight.getEndTime();

        if (flightStart.before(currentPlanTime) && flightEnd.after(currentEstimateFinishTime)) {
            return true;
        }

        return false;
    }


    /**
     * 班次占用时间提示
     *
     * @param flightBos
     * @param currentWaybill
     * @describe 情况1：如果运单用车时间只与前一个班次且与后一个班次不冲突，且班次间隔时间大于用车时间，则提示运单用车时间建议
     * 否则：提示最后班次占用截止时间
     */
    private void buildFlightUsageTip(List<FlightUsage> flightBos, Waybill currentWaybill) {
        if (org.apache.commons.collections.CollectionUtils.isEmpty(flightBos)) return;
        Date lastFlightEndTime = null;
        FlightUsage currentFlightUsage = null;
        FlightUsage next = null;
        int next_squ = 0;
        for (int i = 0; i < flightBos.size(); i++) {
            currentFlightUsage = flightBos.get(i);
            next_squ = i + 1;

            if (flightBos.size() <= i + 1) {
                next = null;
            } else {
                next = flightBos.get(next_squ);
            }
            Date billStart = currentWaybill.getPlanDeliveryTime();
            Date billEnd = currentWaybill.getCmEstimateFinishTime();
            //新单时间在班次内
            lastFlightEndTime = this.flightUsageTimeCoverBillTime(billStart, billEnd, currentFlightUsage);
            if (lastFlightEndTime != null) continue;
            //新单时间包含班次
            lastFlightEndTime = this.billTimeCoverFlightUsageTime(billStart, billEnd, currentFlightUsage);
            if (lastFlightEndTime != null) continue;
            //时间交叉
            lastFlightEndTime = this.crossFlightUsageTime(billStart, billEnd, currentFlightUsage);
            //新单时间和下一班次开始时间交叉,且和前一半次无交叉
            String nextFlightError = this.billTimeCrossNextFlightUsageTime(billEnd, next);
            if (lastFlightEndTime != null && nextFlightError == null) {
                //和上一个班次有交叉且和下一个班次无交叉,提示班次可用时间
                //如果班次间空闲时间大于等于用车时间，提示当前班次后
                long flightInterval = this.flightIntervalLength(currentFlightUsage, next);
                long waybillTimeLength = this.billTimeLength(currentWaybill);
                if (flightInterval > waybillTimeLength) {
//                    throw new BusinessException("planDeliveryTime", "waybill.error.createWaybillTimeError", DateUtil.buildDateFormat(currentFlightUsage.getEndTime()) + ",完成时间请早于" + DateUtil.buildDateFormat(next.getStartTime()));
                    throw new BusinessException("planDeliveryTime", "waybill.error.createWaybillTimeError", DateUtil.buildDateFormat(currentFlightUsage.getEndTime()));
                }
                continue;
            }

            if (nextFlightError != null) {
                //只和下一班次有冲突，提示班次间可用时间
                //如果班次间空闲时间大于等于用车时间，提示当前班次后
//                long flightInterval = this.flightIntervalLength(currentFlightUsage, next);
//                long waybillTimeLength = this.billTimeLength(currentWaybill);
//                if(flightInterval > waybillTimeLength){
////                    throw new BusinessException("planDeliveryTime", "waybill.error.createWaybillTimeError", DateUtil.buildDateFormat(currentFlightUsage.getEndTime()) + ",完成时间请早于" + DateUtil.buildDateFormat(next.getStartTime()));
//                    throw new BusinessException("planDeliveryTime", "waybill.error.createWaybillTimeError", DateUtil.buildDateFormat(currentFlightUsage.getEndTime()));
//                }

                //否则，continue
                continue;
            }

            //全部违反条件都不满足，提示当前班次的结束时间
            //--下一班次为空只提示建议开始时间
            if (next == null) {
                throw new BusinessException("planDeliveryTime", "waybill.error.createWaybillTimeError", DateUtil.buildDateFormat(currentFlightUsage.getEndTime()));
            }
        }

        if (lastFlightEndTime != null)
            throw new BusinessException("planDeliveryTime", "waybill.error.createWaybillTimeError", DateUtil.buildDateFormat(lastFlightEndTime));
    }

    //计算班次间的间隔
    private long flightIntervalLength(FlightUsage currentUsage, FlightUsage nextUsage) {
        if (nextUsage == null) return 0l;

        long nextStartTime = nextUsage.getStartTime().getTime();
        long currentEndTime = currentUsage.getEndTime().getTime();

        return nextStartTime - currentEndTime;
    }

    //运单持续时间
    private long billTimeLength(Waybill waybill) {

        return waybill.getCmEstimateFinishTime().getTime() - waybill.getPlanDeliveryTime().getTime();

    }

    /**
     * 新单时间和班次时间交叉
     *
     * @param currentPlanTime
     * @param currentEstimateFinishTime
     * @param flight
     * @return 当前班次结束时间
     * @throws BusinessException
     */
    private Date crossFlightUsageTime(Date currentPlanTime, Date currentEstimateFinishTime, FlightUsage flight) throws BusinessException {
        Date flightStart = flight.getStartTime();
        //班次开始时间和用车时间交叉
        if (flightStart.after(currentPlanTime) && flightStart.before(currentEstimateFinishTime)) {
            return flight.getEndTime();
        }
        Date flightEnd = flight.getEndTime();
        //班次结束时间和用车时间交叉
        if (flightEnd.after(currentPlanTime) && flightEnd.before(currentEstimateFinishTime)) {
            return flight.getEndTime();
        }

        return null;
    }

    /**
     * 新单时间在班次内
     *
     * @param currentPlanTime
     * @param currentEstimateFinishTime
     * @param flight
     * @return 当前班次结束时间
     * @throws BusinessException
     */
    private Date flightUsageTimeCoverBillTime(Date currentPlanTime, Date currentEstimateFinishTime, FlightUsage flight) throws BusinessException {
        Date flightStart = flight.getStartTime();
        Date flightEnd = flight.getEndTime();

        if (flightStart.before(currentPlanTime) && flightEnd.after(currentEstimateFinishTime)) {
            return flight.getEndTime();
        }

        return null;
    }

    /**
     * 新单时间包含班次
     *
     * @param currentPlanTime
     * @param currentEstimateFinishTime
     * @param flight
     * @return 当前班次结束时间
     * @throws BusinessException
     */
    private Date billTimeCoverFlightUsageTime(Date currentPlanTime, Date currentEstimateFinishTime, FlightUsage flight) throws BusinessException {
        Date flightStart = flight.getStartTime();
        Date flightEnd = flight.getEndTime();

        if (currentPlanTime.before(flightStart) && currentEstimateFinishTime.after(flightEnd)) {
            return flight.getEndTime();
        }

        return null;
    }

    /**
     * 新单结束时间和下一个班次时间交叉
     *
     * @param currentEstimateFinishTime 新单结束时间
     * @param flight                    下一个班次
     * @return
     */
    private String billTimeCrossNextFlightUsageTime(Date currentEstimateFinishTime, FlightUsage flight) {
        //没有下次班次
        if (flight == null) return null;

        Date flightStart = flight.getStartTime();
        Date flightEnd = flight.getEndTime();
        //和下一班次交叉
        if (currentEstimateFinishTime.after(flightStart) && currentEstimateFinishTime.before(flightEnd)) {
            return "nextFlightError";
        }

        return null;
    }

}
