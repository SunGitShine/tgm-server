package com.juma.tgm.waybill.service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.EmployeeInfo;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.fms.domain.v3.vo.WaybillStatisticsAmountVO;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.waybill.domain.*;
import com.juma.tgm.waybill.domain.example.WaybillExample;
import com.juma.tgm.waybill.domain.map.WaybillMapTracePoint;
import com.juma.tgm.waybill.domain.vo.DistanceAndPriceParamVo;
import com.juma.tgm.waybill.vo.ConfirmWaybillAmountVO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * waybillService 通用公共方法
 * @ClassName: WaybillCommonService
 * @Description:
 * @author: liang
 * @date: 2017-11-13 11:14
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public interface WaybillCommonService {

    /**
     * 运单联系人名称长度
     */
    int WAYBILL_CONTACT_NAME_LENGTH = 15;
    
    /**
     * 
     * @Title: loadEmployeeInfo   
     * @Description: 加载员工信息  
     * @param: @param employeeId
     * @param: @return      
     * @return: EmployeeInfo      
     * @throws
     */
    @Deprecated
    EmployeeInfo loadEmployeeInfo(Integer employeeId);

    //运单基础数据搜索
    List<Waybill> search(PageCondition pageCondition) throws BusinessException;
    
    //禁货区域和开通业务检查
    @Deprecated
    void checkBuzAreaAndForbiddenArea(List<WaybillDeliveryAddress> srcAddress,List<WaybillReceiveAddress> destAddress,LoginUser loginUser) throws BusinessException;
    

    /**
     * 通过运单id获取运单基础信息
     * @param waybillId
     * @return
     * @throws BusinessException
     */
    Waybill getWaybillById(Integer waybillId) throws BusinessException;
    
    /**
     * 通过运单id获取运单基础信息
     * @param waybillId
     * @return
     * @throws BusinessException
     */
    Waybill getForUpdate(int waybillId) throws BusinessException;

    /**
     * 按条件count
     * @param pageCondition
     * @return
     * @throws BusinessException
     */
    int searchCount(PageCondition pageCondition) throws BusinessException;

    /**
     * @throws
     * @Title: insertWaybill
     * @Description: 新增
     * @return: void
     */
    void insert(Waybill waybill, LoginUser loginUser) throws BusinessException;

    /**
     * 保存 truckrequire、配送地、历史联系人
     * @param waybillBo
     * @param loginUser
     */
    void insertLinkTable(WaybillBo waybillBo, LoginUser loginUser);

    /**
     * 修改运单信息
     *
     * @param waybill
     * @param loginUser
     */
    void update(Waybill waybill, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * @Title: batchUpdate   
     * @Description: 批量更新
     * @param: @param waybills
     * @param: @param loginUser      
     * @return: void      
     * @throws
     */
    void batchUpdate(List<Waybill> waybills);
    
    
    /**
     * 保存搬运费信息
     * @param waybillParam
     * @param waybill
     * @param loginUser
     */
    void saveWaybillParam(WaybillParam waybillParam, Waybill waybill, LoginUser loginUser);


    /**
     * 重新计算费用
     *
     * @param src 取货地 不能为空
     * @param dest 配送地 不能为空
     * @return
     */
    DistanceAndPriceData buildDistanceAndPriceData(WaybillDeliveryAddress src, List<WaybillReceiveAddress> dest) throws BusinessException;

    /**
     * @throws @Title: getWaybillNo
     * @Description: 运单编号
     * @param: @return
     * @return: String
     */
    String getWaybillNo();


    /**
     * 
     * @Title: findWaybillMapPoint   
     * @Description: 司机 已接订单未完成  地图点
     * @param: @param driverId
     * @param: @return      
     * @return: List<WaybillMapPoint>      
     * @throws
     */
    WaybillMapTracePoint findWaybillMapPoint(Integer driverId,Integer toDayOrTomorrow,LoginUser loginUser);


    /**
     * 更新企业客户订单数量
     * @param dbWaybill
     */
    void increaseWaybillCount(Waybill dbWaybill);
    
    /**
     * 
     * @Title: selectByExample   
     * @Description:  
     * @param: @param example
     * @param: @return      
     * @return: List<Waybill>      
     * @throws
     */
    List<Waybill> selectByExample(WaybillExample example);

    /**
     * 从高德获取地图线路相关信息
     * @param formAddress
     * @param toAddress
     * @return
     * @throws BusinessException
     */
    DistanceAndPriceData getGaodeMapInfo(CityAdressData formAddress, List<CityAdressData> toAddress) throws BusinessException;
    
    /**
     * 
     * @Title: truckLocation   
     * @Description:车的位置 车牌
     * @param: @param plateNumber
     * @param: @return      
     * @return: String      
     * @throws
     */
    String truckLocation(String plateNumber);

    /**
     * 
     * 请写注释...
     * @author Libin.Wei
     * @Date 2017年12月4日 上午10:41:16
     * @param pageCondition
     * @return
     */
    List<Waybill> listFreightGroupByDriverId(PageCondition pageCondition);


    /**
     *  删除原有的派车信息
     * @param receiveWay
     * @param loginUser
     * @param wb
     * @param driver
     * @param truck
     * @throws BusinessException
     */
    void doCancelAssign(int receiveWay, Waybill wb, Driver driver, Truck truck, LoginUser loginUser) throws BusinessException;

    /**
     * 重新指派车辆
     * @param receiveWay
     * @param remark
     * @param wb
     * @param driver
     * @param truck
     * @param loginUser
     * @throws BusinessException
     */
    void doAssignCarAgain(int receiveWay, String remark, Waybill wb, Driver driver, Truck truck, LoginUser loginUser) throws BusinessException;

    /**
     * 检查司机是否能被指派
     * @param driver
     * @param status
     * @param receiveWay
     * @throws BusinessException
     */
    void checkDriverCanbeAssign(Driver driver, Integer status, Integer receiveWay) throws BusinessException;

    /**
     * 运单指派司机操作：更改数据库运单信息，真正的人工派车操作
     * @param waybill
     * @param loginUser
     * @throws BusinessException
     */
    void changeToAssignedUpdateDb(Waybill waybill, LoginUser loginUser) throws BusinessException;


    /**
     *  取消运单
     * @param waybill
     * @param loginUser
     * @param statuEn
     * @throws BusinessException
     */
    void doCancelWaybill(Waybill waybill, LoginUser loginUser, Waybill.Status statuEn) throws BusinessException;


    /**
     * 标记运单迟到
     * @param waybill
     * @throws BusinessException
     */
    void markLate(Waybill waybill) throws BusinessException;

    /**
     * 更新司机状态为空闲
     * @param driverId
     * @throws BusinessException
     */
    void updateDriverStatusToFree(Integer driverId) throws BusinessException;


    /**
     * 处理后台运单搜索条件
     * @param pageCondition
     * @param loginUser
     * @throws BusinessException
     */
    void structPageCondition(PageCondition pageCondition, LoginUser loginUser) throws BusinessException;

    /**
     * 组装运单相关数据
     * @param waybill
     * @return
     * @throws BusinessException
     */
    WaybillMap buildWaybillMap(Waybill waybill) throws BusinessException;

    /**
     * 标准计价(不含税)
     * （希地方式）
     * @param dp
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    DistanceAndPriceData calculateStanderPrice(DistanceAndPriceParamVo dp, LoginUser loginUser) throws BusinessException;

    /**
     * 标准计价包含司机报价
     * @param dp
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    DistanceAndPriceData calculateStanderPriceWithDriverFreight(DistanceAndPriceParamVo dp, LoginUser loginUser) throws BusinessException;

    /**
     * 通过运单id获取标准计价
     * @param waybillId
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    DistanceAndPriceData calculateStanderPrice(int waybillId, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 根据对账单ID获取
     * @author Libin.Wei
     * @Date 2018年4月1日 下午2:24:22
     * @param reconciliationId
     * @return
     */
    @Deprecated
    List<Waybill> findByReconciliationId(Integer reconciliationId);

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
     * 批量将运单更改为已应收对账
     *
     * @param waybills
     */
    void batchUpdateReceivableReconcilicationNo(List<Waybill> waybills);

    /**
     * 取消订单下面的所有运单
     * @param waybills
     * @param loginUser
     * @throws BusinessException
     */
    void cancelWaybillForOrder(List<Waybill> waybills, LoginUser loginUser) throws BusinessException;

    /**
     * 通过对账单号获取运单列表
     * @param reconciliationNo
     * @return
     * @throws BusinessException
     */
    List<Waybill> findByReconciliationNo(String reconciliationNo) throws BusinessException;

    /**
     * 通过应收对账单号获取运单列表
     * @param receivableReconcilicationNo
     * @return
     * @throws BusinessException
     */
    List<Waybill> findByReceivableReconciliationNo(String receivableReconcilicationNo) throws BusinessException;

    /**
     * 通过对账单号更新货主收款状态
     * @param reconciliationNo
     * @param receiptStatus
     * @throws BusinessException
     */
    void modifyWaybillReceiptStatusByReconciliationNo(String reconciliationNo, Waybill.ReceiptStatus receiptStatus, LoginUser loginUser) throws BusinessException;

    /**
     * 通过对账单号更新结算状态
     * @param reconciliationNo
     * @param plateNumber
     * @param settlementStatus
     * @throws BusinessException
     */
    void modifyWaybillSettlementStatusByReconciliationNo(String reconciliationNo, String plateNumber,  Integer vendorId ,Waybill.SettlementStatus settlementStatus, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 根据承运商的运单ID获取被承运的运单信息
     * 
     * @author Libin.Wei
     * @Date 2018年8月31日 上午10:35:08
     * @param transformBillLinkId
     * @return
     */
    Waybill findWaybillByTransformBillId(Integer transformBillLinkId);

    /**
     * 
     * 临时添加，处理历史数，给运单的添加车辆的承运商
     * 
     * @author Libin.Wei
     * @Date 2018年12月6日 下午5:49:12
     */
    @Deprecated
    void doWaybillAddVehicleTovendorByTruckId(Integer truckId, Integer vendorId);

    /**
     * 根据应收对账单号修改运单收款状态
     * @param receivableReconcilicationNo
     * @param receiptStatus
     * @param loginUser
     */
    void modifyWaybillReceiptStatusByReceivableReconcilicationNo(String receivableReconcilicationNo, Integer receiptStatus, LoginUser loginUser);

    /**运单运费确认**/
    void confirmWaybillAmount(ConfirmWaybillAmountVO confirmWaybillAmountVO, LoginEmployee loginEmployee) throws BusinessException;

    /**刷新运单金额状态数据接口**/
    void freshWaybillAmountStatus(List<Integer> waybillIds,Integer gap) throws BusinessException;

    /**
     * 检查运单的客户侧含税金额是否超过设置的金额
     *
     * @param customerFreight 客户侧含税金额
     * @return true：超过了；false：没有超过
     */
    boolean checkCustomerPriceUpperLimit(BigDecimal customerFreight);

    /**
     * 根据承运商ID获取承运商未对账金额
     *
     * @param vendorId
     *
     * @return
     */
    BigDecimal findUnReconciliationByVendorId(Integer vendorId);

    /**
     * 根据运单号获取
     *
     * @param waybillNo
     * @return
     */
    Waybill findByWaybillNo(String waybillNo);

    /**
     * 判断运单用车时间是否在同一个月份
     *
     * @param waybills
     * @return
     */
    Boolean checkPlanDeliveryTimeInSameMonth(List<Waybill> waybills);

    /**
     * 按项目、时间统计整体毛利、毛利率
     *
     * @return
     */
    WaybillStatisticsAmountVO getMonthAmountInfo(Integer projectId, Date planDeliveryTime);


    /**
     *  根据时间查询运单状态信息
     * @param dailyDate
     * @return
     */
    List<Waybill> checkWayBillStatsByDate(Date dailyDate);


    /**
     * 查找未确认运单数量
     * @param projectIds 项目id
     * @param dailyDate 日报时间
     * @return
     */
    List<Integer> countWaybillStatus(List<Integer> projectIds, Date dailyDate);


}
