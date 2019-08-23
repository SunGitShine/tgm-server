package com.juma.tgm.fms.service.v2;


import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.fms.domain.v2.bo.ReconciliationStatistics;
import com.juma.tgm.fms.domain.v2.vo.ReconciliationWaybillDetailVo;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybillReconciliation.domain.WaybillReconciliation;

import java.util.List;

/****
 *
 * @author huangxing
 *
 * @date 2018.6.05 17:19
 *
 * 对账单 创建 引导 接口，负责对对账单的创建 以及 用户 对账 统计的功能
 *
 * */
public interface ReconciliationBootstrapService {



    /****
     *
     *
     * 以用户为标准，查询统计，统计结果为这个用户的未对账 金额
     *
     * <li>
     * <li/> 客户名称
     *
     * <li/>业务范围
     *
     *@see com.juma.tgm.fms.domain.v2.bo.ReconciliationStatistics
     * */
    Page<ReconciliationStatistics> search(PageCondition pageCondition, LoginUser loginUser) throws BusinessException;



    /***
     *
     * 以项目为标准查询统计一个客户所拥有的项目的总体对账情况(未对账金额)
     *
     * @param customerId 客户的 id
     *
     * @param projectName 项目名称
     * */
    List<ReconciliationStatistics>  find(Integer customerId , String projectName , LoginUser loginUser) throws BusinessException;


    /**
     *
     * 修改运单价格,直接适配以前的接口实现，但是在这里重新定义 以免以后有修改
     *
     *
     * @Date 2017年7月26日 下午2:53:46
     *
     * @param waybillReconciliation 改价信息
     *
     * @param loginUser 登录信息
     *
     * @param  areaNodeList 区域信息,用于判断 区域分配的 运单
     *
     * @throws BusinessException
     *
     * @see com.juma.tgm.waybillReconciliation.service.WaybillReconciliationService
     */
    void update(WaybillReconciliation waybillReconciliation, List<String> areaNodeList, LoginUser loginUser) throws BusinessException;

    /**
     *
     * 修改运单价格,直接适配以前的接口实现，但是在这里重新定义 以免以后有修改
     *
     *
     * @Date 2017年7月26日 下午2:53:46
     *
     * @param waybillReconciliations 改价信息
     *
     * @param loginUser 登录信息
     *
     * @param  areaNodeList 区域信息,用于判断 区域分配的 运单
     *
     * @throws BusinessException
     *
     * @see com.juma.tgm.waybillReconciliation.service.WaybillReconciliationService
     */
    void update(List<WaybillReconciliation> waybillReconciliations, List<String> areaNodeList, LoginUser loginUser) throws BusinessException;


    /***
     *
     *
     *  查询 运单对账信息，需要注意的是 这里不是对账单，而是生成对账单之前的 运单查询
     *<br/>
     * <br/>
     *  查询维度:
     *  <li/><li>
     *  运单号<li/>
     *  用车时间范围，开始到结束<li/>
     *  车牌号<li/>
     *  司机姓名<li/>
     *  客户id<li/>
     *  项目id
     *
     *  <br/>
     *
     *  其中 客户id 和项目id 是必填，因为在生成对账单之前必须先选客户和项目
     *
     *  @param pageCondition 分页参数 和查询参数
     *
     * */
     Page<ReconciliationWaybillDetailVo> searchWaybills(LoginEmployee loginEmployee, PageCondition pageCondition) throws BusinessException;


    /***
     *
     * 按运单 ids 获取 运单列表
     *
     * @param waybillIds 运单 ids
     *
     * @param loginUser 登录用户
     * */
    List<Waybill> findByWaybillIds ( List<Integer> waybillIds , LoginUser loginUser ) throws  BusinessException;

}
