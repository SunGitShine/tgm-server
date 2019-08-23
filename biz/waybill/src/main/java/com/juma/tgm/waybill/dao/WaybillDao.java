package com.juma.tgm.waybill.dao;

import com.giants.common.tools.PageCondition;
import com.giants.dal.dao.mybatis.MybatisDao;
import com.juma.tgm.base.domain.BaseIncomeInfo;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.domain.bo.WaybillMonthlyBillBo;
import com.juma.tgm.waybill.domain.example.WaybillExample;
import com.juma.tgm.waybill.domain.view.SumForWaybill;
import com.juma.tgm.waybill.domain.vo.CustomerManagerDebtDetailVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface WaybillDao extends MybatisDao<Waybill> {
    
    

    List<Waybill> selectEntryList(Map<String, Object> query);

    /**
     * 得到下个序列
     * 
     * @return
     */
    Integer getNextSequence(String dataBaseName);

    /**
     *
     * @Title: getWaybillIds
     * @Description: 根据条件获取运单id
     * @param pageCondition
     * @return Waybill
     */
    List<Waybill> getWaybillIds(PageCondition pageCondition);

    /**
     * 
     * @Title: getFreight
     * @Description: 获取税前税后运费
     * @param pageCondition
     * @return Waybill
     */
    List<Waybill> getFreight(PageCondition pageCondition);

    /**
     * 
     * @Description 批量获取税前税后运费
     * @author Libin.Wei
     * @Date 2016年12月27日 下午3:21:40
     * @param pageCondition
     * @return List<Waybill>
     */
    List<Waybill> getFreightList(PageCondition pageCondition);

    /**
     * 
     * @Title: getOvertimeWaybill
     * @Description: 获取超时运单
     * @return List<Waybill>
     */
    List<Waybill> getOvertimeWaybill();

    /**
     * 
     * @Title: getWillUseCarWaybill
     * @Description: 还有30分钟就需要配送的运单
     * @param waybill
     * @return List<Waybill>
     */
    List<Waybill> getWillUseCarWaybill(Waybill waybill);

    /**
     * 
     * @Title: getWillUseCarWaybill
     * @Description: 运单已经超过预计结束时间30分钟，但是司机还没有点击结束的运单
     * @param waybill
     * @return List<Waybill>
     */
    List<Waybill> getWaybillShouldFinish(Waybill waybill);

    /**
     * 
     * @Description 请写注释...
     * @author Libin.Wei
     * @Date 2017年2月6日 上午10:05:51
     * @param list
     * @return
     */
    List<Integer> getWaybillIdsCount(List<Integer> list);

    /**
     * 
     * @Description 获取搬运费
     * @author Libin.Wei
     * @Date 2017年2月6日 上午11:40:48
     * @param pageCondition
     * @return
     */
    WaybillParam findCountCostByParam(PageCondition pageCondition);

    /**
     * 
     * @Description 获取用户收入信息
     * @author Libin.Wei
     * @Date 2017年2月15日 上午10:18:46
     * @param pageCondition
     * @return BaseIncomeInfo
     */
    BaseIncomeInfo getIncomeInfo(PageCondition pageCondition);

    /**
     * 
     * @Description 删除运单的电子围栏ID
     * @author Libin.Wei
     * @Date 2017年2月22日 下午6:09:29
     * @param monitorId
     *            电子围栏ID
     */
    void removeMonitorId(Integer monitorId);

    /**
     * 
     * @Title: findWaybillPlan @Description: 预约单 @param: @return @return: List
     *         <Waybill> @throws
     */
    List<Waybill> findWaybillPlan();

    /**
     * 
     * @Description
     * @author Libin.Wei
     * @Date 2017年4月5日 下午6:31:33
     * @param waybill
     */
    void removeCustomerId(Integer waybillId);

    /**
     * 
     * 根据TMS系统的车辆ID获取待配送和配送中的运单
     * 
     * @author Libin.Wei
     * @Date 2017年4月14日 下午2:34:12
     * @param truckId
     *            TMS系统的车辆ID
     * @return List<Waybill>
     */
    List<Waybill> findRunningWaybillByTruckId(Integer truckId);

    /**
     * 
     * @Title: findLastRunningWaybillByTruckId @Description: 在途监控
     *         根据车牌查最新配送中订单 @param: @param truckId @param: @return @return:
     *         Waybill @throws
     */
    Waybill findLastRunningWaybillByTruckId(Integer truckId);

    /**
     * 
     * @Title: findFinishWaybillToErp @Description: 已结算的运单到ERP @return: List
     *         <Waybill> @throws
     */
    List<Waybill> findWaybillByIds(Waybill waybill);


    /**
     * 获取价格异常运单
     * 
     * @param pageCondition
     * @return
     */
    List<Waybill> findPriceException(PageCondition pageCondition);

    /**
     * 获取异常运单总数
     * 
     * @param pageCondition
     * @return
     */
    Integer searchPriceExceptionCount(PageCondition pageCondition);

    /**
     * 计算时间范围内的费用总数
     * 
     * @param filter
     *            时间参数
     * @return
     */
    BigDecimal countAmount(@Param("filters") Map<String, Object> filter);

    /**
     * 获取时间范围内的欠款运单
     * 
     * @param pageCondition
     * @return
     */
    Collection<Waybill> findDebtWaybill(PageCondition pageCondition);

    /**
     * 计算时间范围内的欠款运单的数量
     * 
     * @param pageCondition
     * @return
     */
    int countDebtWaybill(PageCondition pageCondition);

    /**
     * 按客户id分组统计一个时间段内，运单数量和欠款总数
     * 
     * @param pageCondition
     * @return
     */
    Collection<CustomerManagerDebtDetailVo> findCustomerDebtBill(PageCondition pageCondition);

    /**
     * 计算客户欠款运单数量
     * 
     * @param pageCondition
     * @return
     */
    int countCustomerDebtBill(PageCondition pageCondition);

    /**
     * 
     * 修改前查询
     * 
     * @author Libin.Wei
     * @Date 2017年9月4日 上午10:30:35
     * @param waybillId
     * @return
     */
    Waybill getForUpdate(Integer waybillId);

    /**
     * 根据条件批量更新数据
     * 
     * @param example
     * @param newValue
     */
    int updateByExample(@Param("example") Waybill example, @Param("newValue") Waybill newValue);

    /**
     * 
     * @Title: batchUpdate @Description: 批量更新 @param: @param
     *         waybills @param: @param loginUser @return: void @throws
     */
    void batchUpdate(List<Waybill> waybills);

    /**
     * 
     * @Title: selectByExample @Description: @param: @param
     *         example @param: @return @return: List<Waybill> @throws
     */
    List<Waybill> selectByExample(WaybillExample example);

    /**
     * 
     * 批量结束运单
     * 
     * @author Libin.Wei
     * @Date 2017年12月4日 下午4:42:43
     * @param waybills
     */
    void batchFinishWaybill(List<Waybill> waybills);

    /**
     * 
     * 批量将运单更改为已对账
     * 
     * @author Libin.Wei
     * @Date 2017年12月4日 下午4:42:43
     * @param waybills
     */
    void batchUpdateHasReconciliation(List<Waybill> waybills);

    /**
     * 
     * 根据对账单ID获取
     * 
     * @author Libin.Wei
     * @Date 2018年4月1日 下午2:24:22
     * @param reconciliationId
     * @return
     */
    List<Waybill> findByReconciliationId(Integer reconciliationId);

    /**
     *
     * 应付暂估月账单主体信息
     *@return
     */
    @Select({"SELECT waybill_id,customer_id customerId,customer_name customerName,\n" +
            "project_id projectId,project_name projectName,tenant_id tenantId,\n" +
            "area_code areaCode,vendor_id vendorId,vendor_name vendorName\n" +
            "FROM waybill\n" +
            "WHERE reconciliation_status = 2\n" +
            "AND finish_time >= #{firstDay}\n" +
            "AND finish_time <= #{lastDay}\n" +
            "AND customer_id IS NOT NULL\n" +
            "AND vendor_id IS NOT NULL\n" +
            "AND vendor_name IS NOT NULL\n" +
            "GROUP BY customer_id,project_id"})
    @ResultType(Waybill.class)
    List<Waybill> findPayableMonthlyBillMain(@Param("firstDay") String firstDay, @Param("lastDay") String lastDay);

    /**
     *
     * 应收暂估月账单主体信息
     *@return
     */
    @Select({"SELECT waybill_id,customer_id customerId,customer_name customerName,\n" +
            "project_id projectId,project_name projectName,tenant_id tenantId,\n" +
            "area_code areaCode,vendor_id vendorId,vendor_name vendorName\n" +
            "FROM waybill\n" +
            "WHERE reconciliation_status = 2\n" +
            "AND finish_time >= #{firstDay}\n" +
            "AND finish_time <= #{lastDay}\n" +
            "AND customer_id IS NOT NULL\n" +
            "AND vendor_id IS NULL\n" +
            "AND vendor_name IS NULL\n" +
            "GROUP BY customer_id,project_id"})
    @ResultType(Waybill.class)
    List<Waybill> findReceivableMonthlyBillMain(@Param("firstDay") String firstDay, @Param("lastDay") String lastDay);

    /**
     *
     * 应付暂估月账单明细
     *@return
     */
    @Select({"SELECT w.waybill_id waybillId,w.tenant_id tenantId,w.area_code areaCode,w.waybill_no waybillNo,w.project_id projectId,\n" +
            "w.project_name projectName,w.plan_delivery_time planDeliveryTime,w.finish_time finishTime,\n" +
            "w.estimate_freight estimateFreight,w.after_tax_freight afterTaxFreight,w.vendor_id vendorId,\n" +
            "w.vendor_name vendorName,w.customer_id customerId,w.customer_name customerName,\n" +
            "t.frame_no frameNo,tr.tax_rate_value taxRateValue\n" +
            "FROM waybill w\n" +
            "LEFT JOIN truck t ON w.truck_id = t.truck_id\n" +
            "LEFT JOIN truck_require tr ON w.waybill_id = tr.waybill_id\n" +
            "WHERE w.reconciliation_status = 2\n" +
            "AND w.finish_time >= #{firstDay}\n" +
            "AND w.finish_time <= #{lastDay}\n" +
            "AND w.vendor_id IS NOT NULL\n" +
            "AND w.vendor_name IS NOT NULL\n" +
            "AND w.customer_id is not null"})
    @ResultType(WaybillMonthlyBillBo.class)
    List<WaybillMonthlyBillBo> findPayableMonthlyBill(@Param("firstDay") String firstDay, @Param("lastDay") String lastDay);

    /**
     *
     * 应收暂估月账单明细
     *@return
     */
    @Select({"SELECT w.waybill_id waybillId,w.tenant_id tenantId,w.area_code areaCode,w.waybill_no waybillNo,w.project_id projectId,\n" +
            "w.project_name projectName,w.plan_delivery_time planDeliveryTime,w.finish_time finishTime,\n" +
            "w.estimate_freight estimateFreight,w.after_tax_freight afterTaxFreight,w.vendor_id vendorId,\n" +
            "w.vendor_name vendorName,w.customer_id customerId,w.customer_name customerName,\n" +
            "t.frame_no frameNo,tr.tax_rate_value taxRateValue\n" +
            "FROM waybill w\n" +
            "LEFT JOIN truck t ON w.truck_id = t.truck_id\n" +
            "LEFT JOIN truck_require tr ON w.waybill_id = tr.waybill_id\n" +
            "WHERE w.receivable_reconcilication_status = 2\n" +
            "AND w.finish_time >= #{firstDay}\n" +
            "AND w.finish_time <= #{lastDay}\n" +
            "AND w.vendor_id IS NULL\n" +
            "AND w.vendor_name IS NULL\n" +
            "AND w.customer_id is not null"})
    @ResultType(WaybillMonthlyBillBo.class)
    List<WaybillMonthlyBillBo> findReceivableMonthlyBill(@Param("firstDay") String firstDay, @Param("lastDay") String lastDay);

    /**
     * 批量更新应收对账
     * @param items
     */
    void batchUpdateReceivableWaybill(@Param("items") List<Waybill> items);

    /**
     * 
     * 临时添加，处理历史数，给运单的添加车辆的承运商
     * 
     * @author Libin.Wei
     * @Date 2018年12月6日 下午5:49:12
     */
    @Deprecated
    @Update({"update waybill set vehicle_to_vendor = #{vendorId} where truck_id = #{truckId} and status_view = 5 and reconciliation_status = 1 and (vehicle_to_vendor is null or vehicle_to_vendor = 0)"})
    void doWaybillAddVehicleTovendorByTruckId(@Param("truckId") Integer truckId, @Param("vendorId") Integer vendorId);

    /**
     * 根据应收对战单号修改收款状态
     * @param waybill
     */
    void updateWaybillByReceivableReconcilicationNo(Waybill waybill);
    
    /**
     * 车辆未对账数据
     */
    List<SumForWaybill> findUnReconciliationByPlateNumber(String plateNumber);

    /**
     * 批量更新运单承运商id
     * @param vendorId
     * @param waybillNos
     */
    void batchUpdateVendor(@Param("vendorId") Integer vendorId, @Param("waybillNos") List<String> waybillNos);

    /**
     * 根据承运商ID获取承运商未对账金额
     *
     * @param vendorId
     *
     * @return
     */
    BigDecimal findUnReconciliationByVendorId(Integer vendorId);

    /**
     *  统计运单状态数据，以条件时间节点
     * @param projectIds
     * @param dailyDate
     * @param endTime
     * @return
     */
    List<Integer> countWaybillStatus(@Param("projectIds") List<Integer> projectIds, @Param("dailyDate")Date dailyDate, @Param("endTime") Date endTime);


    /**
     *  获取时间段内运单信息，含：项目id，运单状态，最大完成时间，数据来源字段
     * @param startDate 查询开始时间
     * @param endTime 查询结束时间
     * @return
     */
    List<Waybill> checkWayBillStatsByDate(@Param("startDate") Date startDate, @Param("endTime") Date endTime);
}
