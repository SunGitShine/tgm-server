package com.juma.tgm.util;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSONObject;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.conf.service.BusinessAreaService;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.server.vm.domain.bo.FlightBo;
import com.juma.server.vm.service.vehicle.AmsServiceV2;
import com.juma.tgm.base.domain.BaseEnumDomian;
import com.juma.tgm.flight.domain.bo.TransportCapacityBo;
import com.juma.tgm.flight.domain.bo.vo.FreeVehicleOverViewVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @ClassName: TransportCapacityUtils
 * @Description:
 * @author: liang
 * @date: 2017-07-11 13:50
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
@Deprecated
@Component
public class TransportCapacityUtils {

    private static final Logger log = LoggerFactory.getLogger(TransportCapacityUtils.class);


    @Resource
    private AmsServiceV2 amsServiceAsync;

    @Resource
    private BusinessAreaService businessAreaService;

    /**
     * 过滤有限时段内具体的车辆数据
     *
     * @param transportCapacityBo
     * @return
     */
    public List<FreeVehicleOverViewVo> getFreeVehicleInfoByValidTimeLength(TransportCapacityBo transportCapacityBo, LoginEmployee loginEmployee) {
        List<FreeVehicleOverViewVo> allData = new ArrayList<>();

        //判断5小时内是否有运单
//        PageCondition pageCondition = new PageCondition();
//        pageCondition.setPageNo(1);
//        pageCondition.setPageSize(500);
//        Map<String, Object> filter = new HashMap<>();
//        pageCondition.setFilters(filter);
//        this.buildAreaCodeList(filter, loginEmployee);
//        //areaCodeLike
//        //获取当天的所有时段
//        List<Map<String, Object>> allPoints = this.getCurrentDayPointList(transportCapacityBo.getCountStartTime());
//        List<BaseEnumDomian> listVehicleBoxType = truckTypeService.listVehicleBoxType();
//        this.asyncFetchAvailableFlight(allPoints, listVehicleBoxType, pageCondition, allData, loginEmployee);

        return allData;
    }

    private void asyncFetchAvailableFlight(List<Map<String, Object>> allPoints, List<BaseEnumDomian> optionList, PageCondition pageCondition, List<FreeVehicleOverViewVo> allData, LoginEmployee loginEmployee) {
        //发起请求
        List<Future<Page<FlightBo>>> futures = new ArrayList<>();
        List<Date> startTimeList = new ArrayList<>();

        for (Map<String, Object> param : allPoints) {
            if (MapUtils.isEmpty(param)) break;

            pageCondition.getFilters().putAll(param);

            amsServiceAsync.getAvailableFlightByPage(pageCondition, loginEmployee);
            Future<Page<FlightBo>> future = RpcContext.getContext().getFuture();

            futures.add(future);
            startTimeList.add((Date) param.get("startTime"));
        }

        FreeVehicleOverViewVo overViewVo = null;
        if (CollectionUtils.isEmpty(futures)) return;
        long startLogTime = 0;
        //获取数据
        for (int i = 0; i < futures.size(); i++) {
            overViewVo = new FreeVehicleOverViewVo();
            Future<Page<FlightBo>> future = futures.get(i);

            Page<FlightBo> rawData = null;
            try {
                startLogTime = System.currentTimeMillis();
                rawData = future.get();
                if(log.isInfoEnabled()){
                    log.info("调用号:{}:=>{}ms", i, (System.currentTimeMillis() - startLogTime));
                }
            } catch (InterruptedException e) {
                log.warn("异步获取可用班次错误:线程中断", e);
            } catch (ExecutionException e) {
                log.warn("异步获取可用班次错误:执行错误", e);
            }

            Date startTime = startTimeList.get(i);

            this.doBuildFreeVehicleInfoVo(rawData.getResults(), overViewVo, optionList);
            overViewVo.setPoint(startTime);

            allData.add(overViewVo);
        }

    }

    /**
     * 设置当前运力查询的业务区域
     *
     * @param filter
     * @param loginEmployee
     */
    private void buildAreaCodeList(Map<String, Object> filter, LoginEmployee loginEmployee) {
        List<LoginEmployee.LoginDepartment.BusinessArea> businessAreaList = loginEmployee.getLoginDepartment().getBusinessAreas();
        log.info("经纪人业务部门所属业务区域:{}", JSONObject.toJSONString(businessAreaList));

        List<String> areaCodeList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(businessAreaList)) {
            for (LoginEmployee.LoginDepartment.BusinessArea area : businessAreaList) {
                com.juma.auth.conf.domain.BusinessArea logicBusinessArea = businessAreaService.loadLogicBusinessArea(area.getAreaCode(),
                        loginEmployee);
                if (logicBusinessArea == null) continue;

                areaCodeList.add(logicBusinessArea.getAreaCode());

            }
        }

        log.info("逻辑业务区域{}", JSONObject.toJSONString(areaCodeList));
        //如果没有就设置成成都
        if (CollectionUtils.isEmpty(areaCodeList)) {
            areaCodeList.add("22000");
        }
        filter.put("areaCodeLikeList", areaCodeList);
    }


    //车型数量/入城证数量
    private void doBuildFreeVehicleInfoVo(Collection<FlightBo> pointFreeList, FreeVehicleOverViewVo overViewVo, List<BaseEnumDomian> optionList) {
        for (FlightBo realFreeFlight : pointFreeList) {
            String boxTypeName = this.filterConfName(optionList, realFreeFlight.getVehicleBoxType());
            log.info("boxTypeCode:{};boxTypeName{}", realFreeFlight.getVehicleBoxType(), boxTypeName);
            if (boxTypeName == null) continue;

            overViewVo.addDetail(realFreeFlight, boxTypeName);
        }

    }

    /**
     * 获取配置名称
     *
     * @param configs
     * @param code
     * @return
     */
    private String filterConfName(Collection<BaseEnumDomian> configs, Object code) {
        if (CollectionUtils.isEmpty(configs)) {
            return null;
        }
        if (code == null) {
            return null;
        }
        for (BaseEnumDomian domain : configs) {
            if (NumberUtils.compare(Integer.parseInt(code.toString()), domain.getCode()) == 0) {
                return domain.getDesc();
            }
        }
        return null;
    }

    /**
     * 获取剩下的整点数
     *
     * @param startTime
     * @return
     */
    private List<Map<String, Object>> getCurrentDayPointList(Date startTime) {
        List<Map<String, Object>> points = new ArrayList<>();
        //从参数时间开始获取剩余的所有整点

        //从参数时间开始计算今天还有几个整数点
        long currentPoint = DateUtils.getFragmentInHours(startTime, Calendar.DATE);
        long remainsPoint = 24 - currentPoint;
        int i = 1;
        if (!DateUtils.truncatedEquals(startTime, new Date(), Calendar.DATE)) {
            i = 0;
        }
        Map<String, Object> point = null;
        for (; i <= remainsPoint; i++) {
            point = this.getTimeRange(startTime, i);
            if (point == null) break;

            points.add(point);
        }

        return points;
    }

    /**
     * 只计算当天的数据
     *
     * @param sequence
     * @return
     */
    private Map<String, Object> getTimeRange(Date startTime, int sequence) {

        Map<String, Object> dateFilter = new HashMap<>();
        //下一个整点
        Date begin = DateUtils.truncate(DateUtils.addHours(startTime, sequence), Calendar.HOUR);

        //下个整点已经是第二天，返回空
        if (!DateUtils.truncatedEquals(startTime, begin, Calendar.DATE)) {
            return null;
        }

        //只计算今天的日期
        Date end = DateUtils.addHours(begin, 5);

        if (!DateUtils.truncatedEquals(startTime, end, Calendar.DATE)) {
            end = DateUtils.setMinutes(DateUtils.setHours(begin, 23), 59);
        }

        dateFilter.put("startTime", begin);
        dateFilter.put("endTime", end);

        return dateFilter;
    }
}
