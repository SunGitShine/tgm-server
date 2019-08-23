package com.juma.tgm.fms.service.v2;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageQueryCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.tgm.fms.domain.v2.vo.ChangeLogQueryByCarVo;
import com.juma.tgm.fms.domain.v2.vo.ChangeLogQueryByTenantVo;
import com.juma.tgm.fms.domain.v2.vo.ReconciliationChangeLogByCarVo;
import com.juma.tgm.fms.domain.v2.vo.ReconciliationChangeLogByTenantVo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 对账单调整日志
 */
public interface ReconciliationChangeLogService {

    /**
     * 根据车辆去新增对账单调整日志
     * @param reconciliationChangeByCarLogVo  车辆下的对账单
     * @param loginEmployee 操作员工
     *
     * @return
     */
    int addChangeLogByCar(ReconciliationChangeLogByCarVo reconciliationChangeByCarLogVo, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 根据车牌去查询对账单调整日志
     * @param params 分页数据
     * @return
     */
    Page<ReconciliationChangeLogByCarVo> searchByCar(PageQueryCondition<ChangeLogQueryByCarVo> params) throws BusinessException;


    /**
     * 根据客户去新增对账单调整日志
     * @param reconciliationChangeLogByTenantVo 客户下的对账单
     * @param loginEmployee 操作员工
     * @return
     */
    int addChangeLogByTenant(ReconciliationChangeLogByTenantVo reconciliationChangeLogByTenantVo,LoginEmployee loginEmployee) throws BusinessException;


    /**
     * 根据客户去查询对账单日志
     * @param params 分页数据

     * @return
     */
    Page<ReconciliationChangeLogByTenantVo> searchByTenant(PageQueryCondition<ChangeLogQueryByTenantVo> params) throws BusinessException;


    /**
     * 删除改价记录
     * @param reconciliationId
     * @throws BusinessException
     */
    void deleteByReconciliationId(int reconciliationId) throws BusinessException;

    /**
     * 通过对账单id查找车辆改价记录
     * @param reconciliationId
     * @return
     * @throws BusinessException
     */
    List<ReconciliationChangeLogByCarVo> findCarReconciliationChangeLogByReconciliationId(int reconciliationId) throws BusinessException;

    /**
     * 通过对账单id查找客户改价记录
     * @param reconciliationId
     * @return
     * @throws BusinessException
     */
    List<ReconciliationChangeLogByTenantVo> findCustomerReconciliationChangeLogByReconciliationId(int reconciliationId) throws BusinessException;

    /**
     * 计算税后，根据客户
     * @param reconciliationId 对账单id
     * @param beforeTaxFreight 税前总费用
     * @param loginEmployee 登录用户
     * @return
     */
    BigDecimal calculateAfterTaxFreight(Integer reconciliationId, BigDecimal beforeTaxFreight, LoginEmployee loginEmployee)throws BusinessException;


    /**
     * 计算税后，根据项目
     * @param reconciliationId 项目id
     * @param plateNumber 车牌号
     * @param beforeTaxFreight 税前总费用
     * @return
     */
    BigDecimal calculateAfterTaxFreightByCar(Integer reconciliationId,String plateNumber, BigDecimal beforeTaxFreight)throws BusinessException;


    /**
     * 计算税后，根据项目
     * @param reconciliationId 项目id
     * @param vendorId 承运商id
     * @param beforeTaxFreight 税前总费用
     * @return
     */
    BigDecimal calculateAfterTaxFreightByCar(Integer reconciliationId,Integer vendorId, BigDecimal beforeTaxFreight)throws BusinessException;
}
