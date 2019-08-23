package com.juma.tgm.fms.service.impl.v2;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.giants.common.collections.CollectionUtils;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageQueryCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.tgm.fms.businessModule.ReconciliationFreightChangeUtil;
import com.juma.tgm.fms.dao.ReconciliationChangeLogMapper;
import com.juma.tgm.fms.dao.ReconciliationItemNewMapper;
import com.juma.tgm.fms.dao.ReconciliationNewMapper;
import com.juma.tgm.fms.domain.Reconciliation;
import com.juma.tgm.fms.domain.ReconciliationItem;
import com.juma.tgm.fms.domain.v2.*;
import com.juma.tgm.fms.domain.v2.enums.ChangeLogTypeEnums;
import com.juma.tgm.fms.domain.v2.vo.*;
import com.juma.tgm.fms.service.v2.ReconciliationChangeLogService;
import com.juma.tgm.fms.service.v2.ReconciliationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 对账单的调整日志
 */
@Service
public class ReconciliationChangeLogServiceImpl implements ReconciliationChangeLogService {

    @Resource
    private ReconciliationChangeLogMapper reconciliationChangeLogMapper;

    @Resource
    private ReconciliationService reconciliationService;

    @Resource
    private ReconciliationNewMapper reconciliationNewMapper;

    @Resource
    private ReconciliationFreightChangeUtil reconciliationFreightChangeUtil;


    @Resource
    private ReconciliationItemNewMapper reconciliationItemNewMapper;

    /**
     * 根据车辆去新增对账单调整日志
     *
     * @param reconciliationChangeByCarLogVo 车辆下的对账单
     * @param loginEmployee                  操作员工
     * @return
     */
    @Override
    public int addChangeLogByCar(ReconciliationChangeLogByCarVo reconciliationChangeByCarLogVo, LoginEmployee loginEmployee) throws BusinessException {
        if ((reconciliationChangeByCarLogVo == null) || (loginEmployee == null)) {
            throw new BusinessException("validate error", "新增数据不能为空");
        }

        if (reconciliationChangeByCarLogVo.getReconciliationId() == null) {
            throw new BusinessException("reconciliationIdNull", "errors.paramCanNotNullWithName", "对账单参数");
        }
        if (reconciliationChangeByCarLogVo.getPlateNumber() == null) {
            throw new BusinessException("plateNumberNull", "errors.paramCanNotNullWithName", "对账单参数");
        }
        if (reconciliationChangeByCarLogVo.getBeforeTaxFreight() == null) {
            throw new BusinessException("beforeTaxFeight", "errors.paramCanNotNullWithName", "对账单参数");
        }
        ReconciliationChangeLog reconciliationChangeLog = new ReconciliationChangeLog();

        reconciliationChangeLog.setType(ChangeLogTypeEnums.CAR.getCode());//类型为  车
        reconciliationChangeLog.setCreateTime(new Date());
        reconciliationChangeLog.setReconciliationId(reconciliationChangeByCarLogVo.getReconciliationId());//对账单id
        reconciliationChangeLog.setPlateNumber(reconciliationChangeByCarLogVo.getPlateNumber());//车牌号
        reconciliationChangeLog.setDriverName(reconciliationChangeByCarLogVo.getDriverName());//老司机的名字
        reconciliationChangeLog.setBeforeTaxFreight(reconciliationChangeByCarLogVo.getBeforeTaxFreight());//调整睡前价格
        reconciliationChangeLog.setVehicleUseTime(reconciliationChangeByCarLogVo.getVehicleUseTime());
//        ReconciliationNew reconciliationNew = reconciliationNewMapper.selectByPrimaryKey(reconciliationChangeByCarLogVo.getReconciliationId());
//        ReconciliationFreightChangeUtil.ReconciliationFreightChangeVo reconciliationFreightChangeVo = new ReconciliationFreightChangeUtil.ReconciliationFreightChangeVo();
//        reconciliationFreightChangeUtil.modifyCustomerReconciliation(reconciliationNew, reconciliationChangeByCarLogVo.getBeforeTaxFreight(), reconciliationFreightChangeVo);
        reconciliationChangeLog.setAfterTaxFreight(reconciliationChangeByCarLogVo.getAfterTaxFreight());//调整税后价格

        reconciliationChangeLog.setCreateUserId(loginEmployee.getUserId());//员工
        reconciliationChangeLog.setNote(reconciliationChangeByCarLogVo.getNote());//备注 原因
        reconciliationChangeLogMapper.insert(reconciliationChangeLog);

        if ((null == reconciliationChangeLog) || (reconciliationChangeLog.getReconciliationChangeLogId() == null)) {
            throw new BusinessException("insert error", "新增日志失败");
        }
        FreightAdjustVo freightAdjustVo = new FreightAdjustVo();
        freightAdjustVo.setReconciliationId(reconciliationChangeByCarLogVo.getReconciliationId());
        freightAdjustVo.setPlateNumber(reconciliationChangeByCarLogVo.getPlateNumber());
        freightAdjustVo.setAdjustAmount(reconciliationChangeByCarLogVo.getBeforeTaxFreight());
        freightAdjustVo.setVendorId( reconciliationChangeByCarLogVo.getVendorId());
        reconciliationService.updateReconciliationFreightForVehicle(freightAdjustVo, loginEmployee);
        return reconciliationChangeLog.getReconciliationChangeLogId();
    }

    /**
     * 根据车牌去查询对账单调整日志
     *
     * @param params 分页数据
     * @return
     */
    @Override
    public Page<ReconciliationChangeLogByCarVo> searchByCar(PageQueryCondition<ChangeLogQueryByCarVo> params) throws BusinessException {

        if ((params == null) || (params.getFilters() == null)) {
            throw new BusinessException("validate error", "查询数据不能为空");
        }
        ChangeLogQueryByCarVo changeLogQueryByCarVo = params.getFilters();
        if ((changeLogQueryByCarVo == null)||(changeLogQueryByCarVo.getReconciliationId() == null)) {
            throw new BusinessException("validate error", "对账单id不能为空");
        }


        int startOffSet = params.getStartOffSet();
        int size = params.getPageSize();

        String plateNumber = changeLogQueryByCarVo.getPlateNumber();
        Integer reconciliationId = changeLogQueryByCarVo.getReconciliationId();
        ReconciliationChangeLogExample example = new ReconciliationChangeLogExample();
        ReconciliationChangeLogExample.Criteria criteria = example.createCriteria();

        criteria.andReconciliationIdEqualTo(reconciliationId).andTypeEqualTo(ChangeLogTypeEnums.CAR.getCode());

        if (!(StringUtils.isEmpty(plateNumber)||StringUtils.isEmpty(plateNumber.trim()))) {
            criteria.andPlateNumberEqualTo(plateNumber.trim());
        }
        example.setStartOffSet(startOffSet);
        example.setSize(size);
        example.setOrderByClause("create_time desc");
        List<ReconciliationChangeLog> reconciliationChangeLogList = reconciliationChangeLogMapper.selectByExample(example);
        int count = reconciliationChangeLogMapper.countByExample(example);
        Page<ReconciliationChangeLogByCarVo> page = new Page<ReconciliationChangeLogByCarVo>();
        page.setPageSize(size);
        page.setTotal(count);
        if (reconciliationChangeLogList == null) {
            reconciliationChangeLogList = new ArrayList<ReconciliationChangeLog>();
        }
        List<ReconciliationChangeLogByCarVo> reconciliationChangeLogByCarVoList = this.buildReconciliationChangeLogByCarVos(reconciliationChangeLogList);
        page.setResults(reconciliationChangeLogByCarVoList);
        return page;
    }

    /**
     * 组装车辆改价vo
     *
     * @param reconciliationChangeLogList
     * @return
     */
    private List<ReconciliationChangeLogByCarVo> buildReconciliationChangeLogByCarVos(List<ReconciliationChangeLog> reconciliationChangeLogList) {

        if (CollectionUtils.isEmpty(reconciliationChangeLogList)) return new ArrayList<ReconciliationChangeLogByCarVo>(0);

        List<ReconciliationChangeLogByCarVo> reconciliationChangeLogByCarVoList = new ArrayList<ReconciliationChangeLogByCarVo>(reconciliationChangeLogList.size());
        for (ReconciliationChangeLog reconciliationChangeLog : reconciliationChangeLogList) {
            ReconciliationChangeLogByCarVo reconciliationChangeLogByCarVo = new ReconciliationChangeLogByCarVo();
            reconciliationChangeLogByCarVo.setCreateTime(reconciliationChangeLog.getCreateTime());//调整的时间
            reconciliationChangeLogByCarVo.setDriverName(reconciliationChangeLog.getDriverName());//老司机名称
            reconciliationChangeLogByCarVo.setPlateNumber(reconciliationChangeLog.getPlateNumber());//车牌号
            reconciliationChangeLogByCarVo.setBeforeTaxFreight(reconciliationChangeLog.getBeforeTaxFreight());
            reconciliationChangeLogByCarVo.setAfterTaxFreight(reconciliationChangeLog.getAfterTaxFreight());//调整后的
            reconciliationChangeLogByCarVo.setNote(reconciliationChangeLog.getNote());//备注
            reconciliationChangeLogByCarVo.setVehicleUseTime(reconciliationChangeLog.getVehicleUseTime());
            reconciliationChangeLogByCarVoList.add(reconciliationChangeLogByCarVo);
        }
        return reconciliationChangeLogByCarVoList;
    }

    /**
     * 根据客户去新增对账单调整日志
     *
     * @param reconciliationChangeLogByTenantVo 客户下的对账单
     * @param loginEmployee                     操作员工
     * @return
     */
    @Override
    public int addChangeLogByTenant(ReconciliationChangeLogByTenantVo reconciliationChangeLogByTenantVo, LoginEmployee loginEmployee) throws BusinessException {
        if ((reconciliationChangeLogByTenantVo == null)) {
            throw new BusinessException("validate error", "新增数据不能为空");
        }

        if (reconciliationChangeLogByTenantVo.getReconciliationId() == null) {
            throw new BusinessException("reconciliationIdNull", "errors.paramCanNotNullWithName", "对账单参数");
        }

        if (reconciliationChangeLogByTenantVo.getBeforeTaxFreight() == null) {
            throw new BusinessException("beforeTaxFeight", "errors.paramCanNotNullWithName", "对账单参数");
        }

        ReconciliationNew reconciliationNew = reconciliationNewMapper.selectByPrimaryKey(reconciliationChangeLogByTenantVo.getReconciliationId());

        ReconciliationChangeLog reconciliationChangeLog = new ReconciliationChangeLog();

        reconciliationChangeLog.setType(ChangeLogTypeEnums.TENANT.getCode());//类型为  车
        reconciliationChangeLog.setCreateTime(new Date());
        reconciliationChangeLog.setReconciliationId(reconciliationChangeLogByTenantVo.getReconciliationId());//对账单id
        reconciliationChangeLog.setCustomerId(reconciliationNew.getCustomerId());//客户的id
        reconciliationChangeLog.setCustomerName(reconciliationNew.getCustomerName());//客户的名字
        reconciliationChangeLog.setBeforeTaxFreight(reconciliationChangeLogByTenantVo.getBeforeTaxFreight());//调整睡前价格
//        ReconciliationFreightChangeUtil.ReconciliationFreightChangeVo reconciliationFreightChangeVo = new ReconciliationFreightChangeUtil.ReconciliationFreightChangeVo();
//        reconciliationFreightChangeUtil.modifyCustomerReconciliation(reconciliationNew, reconciliationChangeLogByTenantVo.getBeforeTaxFreight(), reconciliationFreightChangeVo);
        reconciliationChangeLog.setAfterTaxFreight(reconciliationChangeLogByTenantVo.getAfterTaxFreight());//调整税后价格
        reconciliationChangeLog.setCreateUserId(loginEmployee.getUserId());//员工
        reconciliationChangeLog.setNote(reconciliationChangeLogByTenantVo.getNote());//备注 原因
        reconciliationChangeLogMapper.insert(reconciliationChangeLog);
        if ((null == reconciliationChangeLog) || (reconciliationChangeLog.getReconciliationChangeLogId() == null)) {
            throw new BusinessException("insert error", "新增日志失败");
        }
        FreightAdjustVo freightAdjustVo = new FreightAdjustVo();
        freightAdjustVo.setReconciliationId(reconciliationChangeLogByTenantVo.getReconciliationId());
        freightAdjustVo.setPlateNumber(reconciliationChangeLogByTenantVo.getPlateNumber());
        freightAdjustVo.setAdjustAmount(reconciliationChangeLogByTenantVo.getBeforeTaxFreight());
        reconciliationService.updateReconciliationFreightForCustomer(freightAdjustVo, loginEmployee);
        return reconciliationChangeLog.getReconciliationChangeLogId();
    }

    /**
     * 根据客户去查询对账单日志
     *
     * @param params 分页数据
     * @return
     */
    @Override
    public Page<ReconciliationChangeLogByTenantVo> searchByTenant(PageQueryCondition<ChangeLogQueryByTenantVo> params) throws BusinessException {

        if ((params == null) || (params.getFilters() == null)) {
            throw new BusinessException("validate error", "查询数据不能为空");
        }
        ChangeLogQueryByTenantVo changeLogQueryByTenantVo = params.getFilters();
        if ((changeLogQueryByTenantVo == null)||(changeLogQueryByTenantVo.getReconciliationId() == null)) {
            throw new BusinessException("validate error", "对账单id不能为空");
        }

        int startOffSet = params.getStartOffSet();
        int size = params.getPageSize();

//        ReconciliationNew reconciliationNew = reconciliationNewMapper.selectByPrimaryKey(changeLogQueryByTenantVo.getReconciliationId());

//        Integer customerId  = reconciliationNew.getCustomerId();
        Integer reconciliationId = changeLogQueryByTenantVo.getReconciliationId();
        ReconciliationChangeLogExample example = new ReconciliationChangeLogExample();
        example.createCriteria().andReconciliationIdEqualTo(reconciliationId).andTypeEqualTo(ChangeLogTypeEnums.TENANT.getCode());
        example.setStartOffSet(startOffSet);
        example.setSize(size);
        example.setOrderByClause("create_time desc");
        List<ReconciliationChangeLog> reconciliationChangeLogList = reconciliationChangeLogMapper.selectByExample(example);
        int count = reconciliationChangeLogMapper.countByExample(example);
        Page<ReconciliationChangeLogByTenantVo> page = new Page<ReconciliationChangeLogByTenantVo>();
        page.setPageSize(size);
        page.setTotal(count);
        if (reconciliationChangeLogList == null) {
            reconciliationChangeLogList = new ArrayList<ReconciliationChangeLog>();
        }
        List<ReconciliationChangeLogByTenantVo> reconciliationChangeLogByCarVoList = this.buildReconciliationChangeLogByTenantVos(reconciliationChangeLogList);
        page.setResults(reconciliationChangeLogByCarVoList);
        return page;

    }

    private List<ReconciliationChangeLogByTenantVo> buildReconciliationChangeLogByTenantVos(List<ReconciliationChangeLog> reconciliationChangeLogList) {
        if (CollectionUtils.isEmpty(reconciliationChangeLogList))
            return new ArrayList<ReconciliationChangeLogByTenantVo>(0);

        List<ReconciliationChangeLogByTenantVo> reconciliationChangeLogByCarVoList = new ArrayList<ReconciliationChangeLogByTenantVo>(reconciliationChangeLogList.size());
        for (ReconciliationChangeLog reconciliationChangeLog : reconciliationChangeLogList) {
            ReconciliationChangeLogByTenantVo reconciliationChangeLogByTenantVo = new ReconciliationChangeLogByTenantVo();
            reconciliationChangeLogByTenantVo.setCreateTime(reconciliationChangeLog.getCreateTime());//调整的时间
            reconciliationChangeLogByTenantVo.setCustomerName(reconciliationChangeLog.getCustomerName());//客户名称
            reconciliationChangeLogByTenantVo.setCustomerId(reconciliationChangeLog.getCustomerId());//客户的id
            reconciliationChangeLogByTenantVo.setBeforeTaxFreight(reconciliationChangeLog.getBeforeTaxFreight());
            reconciliationChangeLogByTenantVo.setAfterTaxFreight(reconciliationChangeLog.getAfterTaxFreight());//调整后的
            reconciliationChangeLogByTenantVo.setCreateUserId(reconciliationChangeLog.getCreateUserId());//调整人
            reconciliationChangeLogByTenantVo.setNote(reconciliationChangeLog.getNote());//备注
            reconciliationChangeLogByCarVoList.add(reconciliationChangeLogByTenantVo);
        }
        return reconciliationChangeLogByCarVoList;
    }

    @Override
    public void deleteByReconciliationId(int reconciliationId) throws BusinessException {

        ReconciliationChangeLogExample example = new ReconciliationChangeLogExample();
        ReconciliationChangeLogExample.Criteria criteria = example.createCriteria();
        criteria.andReconciliationIdEqualTo(reconciliationId);
        reconciliationChangeLogMapper.deleteByExample(example);
    }

    @Override
    public List<ReconciliationChangeLogByCarVo> findCarReconciliationChangeLogByReconciliationId(int reconciliationId) throws BusinessException {
        ReconciliationChangeLogExample example = new ReconciliationChangeLogExample();
        example.createCriteria().andReconciliationIdEqualTo(reconciliationId).andTypeEqualTo(ChangeLogTypeEnums.CAR.getCode());
        List<ReconciliationChangeLog> changeLogs = reconciliationChangeLogMapper.selectByExample(example);

        return this.buildReconciliationChangeLogByCarVos(changeLogs);
    }

    @Override
    public List<ReconciliationChangeLogByTenantVo> findCustomerReconciliationChangeLogByReconciliationId(int reconciliationId) throws BusinessException {
        ReconciliationChangeLogExample example = new ReconciliationChangeLogExample();
        example.createCriteria().andReconciliationIdEqualTo(reconciliationId).andTypeEqualTo(ChangeLogTypeEnums.TENANT.getCode());
        List<ReconciliationChangeLog> changeLogs = reconciliationChangeLogMapper.selectByExample(example);
        return this.buildReconciliationChangeLogByTenantVos(changeLogs);
    }

    /**
     * 计算税后总费用,根据客户
     *
     * @param reconciliationId 对账单id
     * @param beforeTaxFreight 税前总费用
     * @param loginEmployee    登录用户
     * @return
     */
    @Override
    public BigDecimal calculateAfterTaxFreight(Integer reconciliationId, BigDecimal beforeTaxFreight, LoginEmployee loginEmployee) {
        if(reconciliationId == null){
            throw new BusinessException("validate error", "对账单id不能为空");
        }
        if(beforeTaxFreight == null){
            throw new BusinessException("validate error", "税前总费用不能为空");
        }
        ReconciliationNew reconciliationNew = reconciliationNewMapper.selectByPrimaryKey(reconciliationId);
        if(reconciliationNew == null){
            throw new BusinessException("validate error", "对账单不存在");
        }
        ReconciliationFreightChangeUtil.ReconciliationFreightChangeVo reconciliationFreightChangeVo = new ReconciliationFreightChangeUtil.ReconciliationFreightChangeVo();
        reconciliationFreightChangeUtil.modifyCustomerReconciliation(reconciliationNew, beforeTaxFreight, reconciliationFreightChangeVo);
        return reconciliationFreightChangeVo.getAfterTaxChangeAmount();
    }

    /**
     * 计算税后，根据项目
     *
     * @param reconciliationId 项目id
     * @param plateNumber      车牌号
     * @param beforeTaxFreight 税前总费用
     * @return
     */
    @Override
    public BigDecimal calculateAfterTaxFreightByCar(Integer reconciliationId, String plateNumber, BigDecimal beforeTaxFreight) throws BusinessException{
        if(reconciliationId == null){
            throw new BusinessException("validate error","项目id为空");
        }

        if(beforeTaxFreight == null){
            throw new BusinessException("validate error","税前总费用为空");
        }

        if(StringUtils.isEmpty(plateNumber)||StringUtils.isEmpty(plateNumber.trim())){
            throw new BusinessException("validate error","车牌号为空");
        }

        ReconciliationItemNewExample example = new ReconciliationItemNewExample();
        ReconciliationItemNewExample.Criteria criteria = example.createCriteria();
        criteria.andReconciliationIdEqualTo(reconciliationId);
        criteria.andPlateNumberEqualTo(plateNumber.trim());
        List<ReconciliationItemNew> reconciliationItemNewList = reconciliationItemNewMapper.selectByExample(example);

//        ReconciliationItemNew  reconciliationItemNew = reconciliationItemNewMapper.selectByPrimaryKey(itemId);
        if(CollectionUtils.isEmpty(reconciliationItemNewList)){
            throw new BusinessException("validate error","车牌号不正确");
        }

        ReconciliationFreightChangeUtil.ReconciliationFreightChangeVo reconciliationFreightChangeVo = reconciliationFreightChangeUtil.calculateVehiclePriceSpread(reconciliationItemNewList.get(0), beforeTaxFreight);
        return reconciliationFreightChangeVo.getAfterTaxChangeAmount();
    }

    @Override
    public BigDecimal calculateAfterTaxFreightByCar(Integer reconciliationId, Integer vendorId, BigDecimal beforeTaxFreight) throws BusinessException {
        if(reconciliationId == null){
            throw new BusinessException("validate error","项目id为空");
        }

        if(beforeTaxFreight == null){
            throw new BusinessException("validate error","税前总费用为空");
        }

        if(vendorId == null){
            throw new BusinessException("validate error","承运商为空");
        }

        ReconciliationItemNewExample example = new ReconciliationItemNewExample();
        ReconciliationItemNewExample.Criteria criteria = example.createCriteria();
        criteria.andReconciliationIdEqualTo(reconciliationId);
        criteria.andVendorIdEqualTo(vendorId);
        List<ReconciliationItemNew> reconciliationItemNewList = reconciliationItemNewMapper.selectByExample(example);

//        ReconciliationItemNew  reconciliationItemNew = reconciliationItemNewMapper.selectByPrimaryKey(itemId);
        if(CollectionUtils.isEmpty(reconciliationItemNewList)){
            throw new BusinessException("validate error","车牌号不正确");
        }

        ReconciliationFreightChangeUtil.ReconciliationFreightChangeVo reconciliationFreightChangeVo = reconciliationFreightChangeUtil.calculateVehiclePriceSpread(reconciliationItemNewList.get(0), beforeTaxFreight);
        return reconciliationFreightChangeVo.getAfterTaxChangeAmount();
    }


}
