package com.juma.tgm.fms.service.impl.v2;

import com.alibaba.fastjson.JSONObject;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.giants.common.tools.PageQueryCondition;
import com.juma.auth.employee.domain.Employee;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.BigDecimalUtil;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.fms.businessModule.ReconciliationFreightChangeUtil;
import com.juma.tgm.fms.businessModule.ReconciliationWaybillUtils;
import com.juma.tgm.fms.dao.ReconciliationCustomerStatisticsMapper;
import com.juma.tgm.fms.dao.ReconciliationItemNewMapper;
import com.juma.tgm.fms.dao.ReconciliationNewMapper;
import com.juma.tgm.fms.domain.Reconciliation;
import com.juma.tgm.fms.domain.Task;
import com.juma.tgm.fms.domain.v2.ReconciliationItemNew;
import com.juma.tgm.fms.domain.v2.ReconciliationItemNewExample;
import com.juma.tgm.fms.domain.v2.ReconciliationNew;
import com.juma.tgm.fms.domain.v2.ReconciliationNewExample;
import com.juma.tgm.fms.domain.v2.bo.ReconciliationV2ToFms;
import com.juma.tgm.fms.domain.v2.bo.ReconciliationVehicleItemToFms;
import com.juma.tgm.fms.domain.v2.enums.ReconciliationEnums;
import com.juma.tgm.fms.domain.v2.vo.*;
import com.juma.tgm.fms.service.v2.ReconciliationChangeLogService;
import com.juma.tgm.fms.service.v2.ReconciliationService;
import com.juma.tgm.imageUploadManage.domain.ImageUploadManage;
import com.juma.tgm.imageUploadManage.service.ImageUploadManageService;
import com.juma.tgm.project.domain.Project;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.project.vo.ProjectForFmsVo;
import com.juma.tgm.project.vo.ProjectQueryVo;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillParamService;
import com.juma.vms.vendor.domain.Vendor;
import com.juma.workflow.core.domain.ProcessInstance;
import com.juma.workflow.core.service.ProcessService;
import com.juma.workflow.core.service.TaskService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 第二版对账实现类
 *
 * @ClassName: ReconciliationServiceImpl
 * @Description:
 * @author: liang
 * @date: 2018-06-05 21:02
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
@Service("reconciliationServiceImplV2")
public class ReconciliationServiceImpl implements ReconciliationService {

    private static Logger log = LoggerFactory.getLogger(ReconciliationServiceImpl.class);

    @Resource
    private ReconciliationNewMapper reconciliationNewMapper;

    @Resource
    private ReconciliationItemNewMapper reconciliationItemNewMapper;

    @Autowired
    private ReconciliationCustomerStatisticsMapper reconciliationCustomerStatisticsMapper;

    @Resource
    private ImageUploadManageService imageUploadManageService;

    @Resource
    private WaybillCommonService waybillCommonService;

    @Resource
    private WaybillParamService waybillParamService;

    @Autowired
    private CustomerInfoService customerInfoService;

    @Autowired
    private TruckRequireService truckRequireService;

    @Autowired
    private SendServiceV2 sendService;

    @Autowired
    private com.juma.tgm.fms.service.ReconciliationService reconciliationServiceV1;// 原来的版本 用来作为  不变的功能点适配

    @Autowired
    private ReconciliationFreightChangeUtil reconciliationFreightChangeUtil;

    @Resource
    private ProcessService processService;
    @Resource
    private TaskService taskService;
    @Autowired
    private ReconciliationWaybillUtils reconciliationWaybillUtils;
    @Autowired
    private ReconciliationChangeLogService reconciliationChangeLogService;
    @Autowired
    private ProjectService projectService;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private VmsCommonService vmsCommonService;

    @Override
    public String createReconciliation(List<Integer> waybillIds, LoginUser loginUser) throws BusinessException {
        //  如果 没有 运单id 则 忽略
        if (waybillIds == null || waybillIds.isEmpty()) {
            throw new BusinessException("waybills", "errors.reconciliation.waybills");//  一个合法的运单都没有
        }
//        List<Waybill> waybills = waybillQueryService.findWaybillByIds(waybillIds);
        List<Waybill> waybills = this.getWaybillList(waybillIds);
        Integer type = checkWaybills(waybills, loginUser.getTenantId());// 检测 运单是否属于同一个客户
        String reconciliationNo = reconciliationServiceV1.getReconciliationNo();
        BigDecimal taxRate = this.getTheBiggestTaxRateValue(waybills, loginUser);
        // 生成一个对账单
        ReconciliationNew reconciliationNew = this.save(type, reconciliationNo, loginUser);
        List<ReconciliationItemNew> reconciliationItemNews = this.getItemByWaybills(reconciliationNew.getReconciliationId(), waybills, loginUser.getTenantId(), taxRate);
        if (reconciliationItemNews.size() == 0) {
            reconciliationNewMapper.deleteByPrimaryKey(reconciliationNew.getReconciliationId());
            throw new BusinessException("waybills", "errors.reconciliation.waybills");//  一个合法的运单都没有
        }
        Integer customerId = reconciliationItemNews.get(0).getCustomerId();
        Integer projectId = reconciliationItemNews.get(0).getProjectId();
        // 保存 对账单详情
        this.calculateRebateFee(reconciliationItemNews, this.getRebateRate(customerId, projectId));
        this.insertAll(reconciliationItemNews);
        ReconciliationItemSum reconciliationItemSum = this.sum(reconciliationItemNews, waybills);
        reconciliationNew.setCustomerId(customerId);
        reconciliationNew.setCustomerName(reconciliationItemNews.get(0).getCustomerName());
        reconciliationNew.setProjectName(reconciliationItemNews.get(0).getProjectName());
        reconciliationNew.setProjectId(projectId);
        reconciliationNew.setTaxRateValue(taxRate);
        this.buildReconciliationAreaCode(reconciliationNew, customerId);
        // 更新 汇总费用
        updateTotalFreight(reconciliationNew, reconciliationItemSum);
        // 更新运单的 对账单号
        setReconciliationNoToWaybills(reconciliationNo, waybills, loginUser);
        return reconciliationNew.getReconciliationNo();
    }

    /**
     * 获取运单列表
     *
     * @param waybillIds
     * @return
     */
    private List<Waybill> getWaybillList(List<Integer> waybillIds) {
        if (CollectionUtils.isEmpty(waybillIds)) return null;

        List<Waybill> waybillList = new ArrayList<>();
        Waybill waybill = null;
        for (Integer id : waybillIds) {
            waybill = waybillCommonService.getWaybillById(id);
            if (waybill == null) continue;

            waybillList.add(waybill);
        }

        return waybillList;
    }

    /**
     * 计算返点费
     */
    private void calculateRebateFee(List<ReconciliationItemNew> reconciliationItemNews, BigDecimal rebateRate) {
        if (CollectionUtils.isEmpty(reconciliationItemNews)) return;
        if (rebateRate == null) return;

        for (ReconciliationItemNew itemNew : reconciliationItemNews) {
            if (BigDecimal.ZERO.equals(rebateRate)) {
                itemNew.setRebateFee(BigDecimal.ZERO);
            } else {
                itemNew.setRebateFee(itemNew.getDriverInitialBeforeTax().multiply(rebateRate));
            }
        }

    }

    /**
     * 组装对账单业务区域
     *
     * @param reconciliationNew
     * @param customerId
     */
    private void buildReconciliationAreaCode(ReconciliationNew reconciliationNew, Integer customerId) {
        if (reconciliationNew == null)
            throw new BusinessException("buildReconciliationAreaCode_err_reconciliation_null", "errors.paramCanNotNullWithName", "对账单");
        if (customerId == null)
            throw new BusinessException("buildReconciliationAreaCode_err_customerId_null", "errors.paramCanNotNullWithName", "对账单客户");

        //获取当前客户信息
        CustomerInfo customerInfo = customerInfoService.findCusInfoById(customerId);
        if (customerInfo == null)
            throw new BusinessException("buildReconciliationAreaCode_err_customer_null", "errors.common.prompt", "找到不客户信息");

        reconciliationNew.setAreaCode(customerInfo.getAreaCode());

    }


    /**
     * 获取运单中税率最大的作为对账单税率
     *
     * @param waybills
     * @return
     */
    private BigDecimal getTheBiggestTaxRateValue(List<Waybill> waybills, LoginUser loginUser) {
        if (CollectionUtils.isEmpty(waybills)) return BigDecimal.ZERO;
        BigDecimal finalTaxRate = null;
        TruckRequire truckRequire = null;
        for (Waybill wb : waybills) {
            truckRequire = truckRequireService.findTruckRequireByWaybillId(wb.getWaybillId(), loginUser);
            if (truckRequire == null) continue;

            if (truckRequire.getTaxRateValue() == null) continue;

            if (finalTaxRate == null) {
                finalTaxRate = truckRequire.getTaxRateValue();
                continue;
            }

            if (finalTaxRate.compareTo(truckRequire.getTaxRateValue()) < 0) {
                finalTaxRate = truckRequire.getTaxRateValue();
            }
        }

        if (finalTaxRate != null) {
            return finalTaxRate;
        } else {
            throw new BusinessException("finalTaxRate", "errors.common.prompt", "选择运单的税率均为空，请改价修改税率");
        }
    }

    private void updateTotalFreight(ReconciliationNew reconciliationNew, ReconciliationItemSum reconciliationItemSum) throws BusinessException {
        reconciliationNew.setDriverFinalBeforeTax(reconciliationItemSum.getDriverBeforeTaxSum());
        reconciliationNew.setDriverFinalAfterTax(reconciliationItemSum.getDriverAfterTaxSum());
        reconciliationNew.setDriverInitialBeforeTax(reconciliationItemSum.getDriverBeforeTaxSum());
        reconciliationNew.setDriverInitialAfterTax(reconciliationItemSum.getDriverAfterTaxSum());

        reconciliationNew.setCustomerFinalBeforeTax(reconciliationItemSum.getBeforeTaxSum());
        reconciliationNew.setCustomerFinalAfterTax(reconciliationItemSum.getAfterTaxSum());

        reconciliationNew.setCustomerInitialBeforeTax(reconciliationItemSum.getBeforeTaxSum());
        reconciliationNew.setCustomerInitialAfterTax(reconciliationItemSum.getAfterTaxSum());

        reconciliationNew.setDriverHandlingFee(reconciliationItemSum.getDriverHandlingCostSum());
        reconciliationNew.setLaborerHandlingFee(reconciliationItemSum.getLaborerHandlingCostSum());
        BigDecimalUtil.nullToZero(reconciliationNew);
        reconciliationNewMapper.updateByPrimaryKeySelective(reconciliationNew);
    }

    private void insertAll(List<ReconciliationItemNew> reconciliationItemNews) {
        for (ReconciliationItemNew reconciliationItemNew : reconciliationItemNews) {
            BigDecimalUtil.nullToZero(reconciliationItemNew);
            reconciliationItemNew.setPayStatus(Integer.valueOf(ReconciliationEnums.VehiclePayStatus.NONE.getCode()).byteValue());
            reconciliationItemNewMapper.insert(reconciliationItemNew);
        }
    }

    private ReconciliationNew save(Integer type, String reconciliationNo, LoginUser loginUser) {
        // 默认的 插入状态 ，这里需要做一个 兼容
        Byte reconciliationStatus = Integer.valueOf(Reconciliation.ReconciliationStatus.Append.getCode()).byteValue();
        ReconciliationNew reconciliationNew = new ReconciliationNew();
        reconciliationNew.setCreateTime(new Date());//当前 服务器时间
        reconciliationNew.setCreateUserId(loginUser.getUserId());
        reconciliationNew.setReconciliationNo(reconciliationNo);
        reconciliationNew.setReconciliationStatus(reconciliationStatus);
        reconciliationNew.setTenantId(loginUser.getTenantId());
        reconciliationNew.setTenantCode(loginUser.getTenantCode());
        reconciliationNew.setReconciliationType(type);
        reconciliationNew.setReceiveStatus(Integer.valueOf(ReconciliationEnums.ShipperPayStatus.NONE.getCode()).byteValue());
        reconciliationNewMapper.insert(reconciliationNew);
        return reconciliationNew;
    }


    private ReconciliationItemNew getItemByWaybill(Integer reconciliationId, Waybill waybill, BigDecimal taxRate) {
        if (waybill.getEstimateFreight() == null) {
            throw new BusinessException("estimateFreight", "errors.reconciliation.waybills.estimateFreight");
        }
        ReconciliationItemNew reconciliationItemNew = new ReconciliationItemNew();
        CustomerInfo customerInfo = customerInfoService.findCusInfoById(waybill.getCustomerId());
        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybill.getWaybillId());
        // ==== 以上信息都是同一个客户，提交过来的订单也只能是同一个客户的 =====
        reconciliationItemNew.setReconciliationId(reconciliationId);
        reconciliationItemNew.setDriverHandlingFee(waybillParam == null ? BigDecimal.ZERO : waybillParam.getDriverHandlingCost());
        reconciliationItemNew.setLaborerHandlingFee(waybillParam == null ? BigDecimal.ZERO : waybillParam.getLaborerHandlingCost());
        reconciliationItemNew.setRebateFee(waybill.getRebateFee());
        reconciliationItemNew.setCustomerId(waybill.getCustomerId());
        reconciliationItemNew.setCustomerName(customerInfo.getCustomerName());
        reconciliationItemNew.setProjectId(waybill.getProjectId());
        reconciliationItemNew.setProjectName(waybill.getProjectName());
        reconciliationItemNew.setTruckId(waybill.getTruckId());
        reconciliationItemNew.setPlateNumber(waybill.getPlateNumber());
        reconciliationItemNew.setAreaCode(waybill.getAreaCode());
        reconciliationItemNew.setVendorId(waybill.getVendorId());// 承运商id
        LoginUser loginUser = new LoginUser();
        loginUser.setTenantId(waybill.getTenantId());
        Vendor vendor = vmsCommonService.loadVendorByVendorId(waybill.getVendorId());
        reconciliationItemNew.setVendorName(vendor == null ? "" : vendor.getVendorName());
        BigDecimal driverInitialAfterTax = new BigDecimal("0");
        BigDecimal driverFinalAfterTax = new BigDecimal("0");
        if (waybill.getShow4DriverFreight() != null) {
            driverInitialAfterTax = waybill.getShow4DriverFreight().divide(BigDecimal.ONE.add(taxRate), 2, BigDecimal.ROUND_HALF_UP);
            driverFinalAfterTax = driverInitialAfterTax;
        }
        reconciliationItemNew.setDriverInitialBeforeTax(waybill.getShow4DriverFreight());
        reconciliationItemNew.setDriverInitialAfterTax(driverInitialAfterTax);
        reconciliationItemNew.setDriverFinalBeforeTax(waybill.getShow4DriverFreight());
        reconciliationItemNew.setDriverFinalAfterTax(driverFinalAfterTax);
        reconciliationItemNew.setCreateTime(new Date());
        return reconciliationItemNew;
    }

    /**
     * 获取返点率
     *
     * @param customerId
     * @param projectId
     * @return
     */
    private BigDecimal getRebateRate(Integer customerId, Integer projectId) {
        //有项目的使用项目返点率
        BigDecimal proRebate = BigDecimal.ZERO;
        if (projectId != null) {
            Project project = projectService.getProject(projectId);
            if (project != null) {
                proRebate = project.getRebateRate();
            }
        }

        if (!BigDecimal.ZERO.equals(proRebate)) {
            return proRebate;
        }

        //没有项目返点率使用客户返点率
        BigDecimal cusRebate = customerInfoService.getRebateRate(customerId);
        return cusRebate;
    }


    private List<ReconciliationItemNew> getItemByWaybills(Integer reconciliationId, List<Waybill> waybills, Integer tenantId, BigDecimal taxRate) {
        //用于存储 车牌 号的 映射
        Map<Object, ReconciliationItemNew> reconciliationItemNewMap = new HashMap<>();
        for (Waybill waybill : waybills) {
            if (waybill.getReconciliationStatus().equals(Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode())// 未对账
                && waybill.getStatusView().equals(Waybill.StatusView.FINISH.getCode())  // 已完成
                && waybill.getTenantId().equals(tenantId) // 属于该租户
                ) {

                ReconciliationItemNew reconciliationItemNew = this.getItemByWaybill(reconciliationId, waybill, taxRate);
                reconciliationItemNew.setTaxRateValue(taxRate);
                // 没有 承运商  按 车牌号码合并
                if (reconciliationItemNew.getVendorId() == null) {
                    if (reconciliationItemNewMap.get(reconciliationItemNew.getPlateNumber()) == null) {
                        reconciliationItemNewMap.put(reconciliationItemNew.getPlateNumber(), reconciliationItemNew);
                    } else {
                        //已经存在的映射,则需要合并
                        ReconciliationItemNew reconciliationItemNew__ = reconciliationItemNewMap.get(reconciliationItemNew.getPlateNumber());
                        this.merge(reconciliationItemNew__, reconciliationItemNew);
                    }
                }
                // 存在 承运商 按 承运商id 合并
                else {
                    if (reconciliationItemNewMap.get(reconciliationItemNew.getVendorId()) == null) {
                        reconciliationItemNewMap.put(reconciliationItemNew.getVendorId(), reconciliationItemNew);
                    } else {
                        //已经存在的映射,则需要合并
                        ReconciliationItemNew reconciliationItemNew__ = reconciliationItemNewMap.get(reconciliationItemNew.getVendorId());
                        this.merge(reconciliationItemNew__, reconciliationItemNew);
                    }
                }
            }
        }
        return new ArrayList<>(reconciliationItemNewMap.values());
    }


    private void merge(ReconciliationItemNew reconciliationItemNew__, ReconciliationItemNew reconciliationItemNew) {
        BigDecimalUtil.nullToZero(reconciliationItemNew__, reconciliationItemNew);
        BigDecimal driverHandlingFeeSum = reconciliationItemNew__.getDriverHandlingFee().add(reconciliationItemNew.getDriverHandlingFee());
        BigDecimal laborerHandlingFeeSum = reconciliationItemNew__.getLaborerHandlingFee().add(reconciliationItemNew.getLaborerHandlingFee());
        BigDecimal driverInitialBeforeTaxSum = reconciliationItemNew__.getDriverInitialBeforeTax().add(reconciliationItemNew.getDriverInitialBeforeTax());
        BigDecimal driverInitialAfterTaxSum = reconciliationItemNew__.getDriverInitialAfterTax().add(reconciliationItemNew.getDriverInitialAfterTax());
        BigDecimal driverFinalBeforeTaxSum = reconciliationItemNew__.getDriverFinalBeforeTax().add(reconciliationItemNew.getDriverFinalBeforeTax());
        BigDecimal driverFinalAfterTaxSum = reconciliationItemNew__.getDriverFinalAfterTax().add(reconciliationItemNew.getDriverFinalAfterTax());
        reconciliationItemNew__.setDriverHandlingFee(driverHandlingFeeSum);
        reconciliationItemNew__.setLaborerHandlingFee(laborerHandlingFeeSum);
        reconciliationItemNew__.setDriverInitialBeforeTax(driverInitialBeforeTaxSum);
        reconciliationItemNew__.setDriverInitialAfterTax(driverInitialAfterTaxSum);
        reconciliationItemNew__.setDriverFinalBeforeTax(driverFinalBeforeTaxSum);
        reconciliationItemNew__.setDriverFinalAfterTax(driverFinalAfterTaxSum);
    }

    //  如果 检查通过 则返回对账单 的类型
    private Integer checkWaybills(List<Waybill> waybills, Integer tenantId) throws BusinessException {
        Integer customerId = null;
        Map<Integer, Integer> vendorMap = new HashMap<Integer, Integer>();
        for (Waybill waybill : waybills) {
            vendorMap.put(waybill.getVendorId(), waybill.getWaybillId());
            // 判断这些运单是否属于该租户
            if (!tenantId.equals(waybill.getTenantId())) {
                throw new BusinessException("waybill permission refusal", "errors.reconciliation.waybills.authority", waybill.getWaybillNo());
            }
            // 判断 不为空 则这个运单已经生成过对账单
            if (StringUtils.isNotBlank(waybill.getReconciliationNo())) {
                throw new BusinessException("waybill", "errors.reconciliation.repeat", waybill.getWaybillNo());
            }
            // 容错检测customerId 是否为null
            if (waybill.getCustomerId() == null) {
                throw new BusinessException("customerId", "errors.paramCanNotNullWithName", "运单客户id");
            }
            // 如果不为空则开始判断是否属于同一个客户
            else if (customerId == null) {
                customerId = waybill.getCustomerId();
            }
            // 如果后面的 和第一个 只要有一个不一样就抛出异常，证明这一批 不是一个 客户的
            else if (!customerId.equals(waybill.getCustomerId())) {
                throw new BusinessException("customerId", "errors.reconciliation.customer", "{" + customerId + "," + waybill.getCustomerId() + "}");
            }
        }
        if (vendorMap.size() == 1 && vendorMap.get(null) != null) {
            // 只有一种类型 但是 他们的承运商id全部为null 那么 对账单 类型 为 0  属于全部为司机单
            return ReconciliationNew.Type.ORDINARY.getCode();
        } else if (vendorMap.size() > 1 && vendorMap.get(null) != null) {
            // 如果 map 中key 值为null 的存在 value 的值 那么 证明肯定存在司机单，因为司机单承运商id 为null
            // 然后 map 的大小大于 1 那么就说明还存在 有承运商的单子所以为 2 混合
            return ReconciliationNew.Type.ENTRUSTED_BLEND.getCode();
        } else {
            return ReconciliationNew.Type.ENTRUSTED_TRANSPORT.getCode();
        }
    }

    private void setReconciliationNoToWaybills(String reconciliationNo, List<Waybill> waybills, LoginUser loginUser) {
        for (Waybill waybill : waybills) {
            Waybill w = new Waybill();
            w.setIsSubmitMatch(null);
            w.setWaybillId(waybill.getWaybillId());
            w.setReconciliationNo(reconciliationNo);
            //生成对账单后运单对账状态为对账中
            w.setReconciliationStatus(Waybill.ReconciliationStatus.IN_THE_ACCOUNT.getCode());
            waybillCommonService.update(w, loginUser);
        }
    }


    private ReconciliationItemSum sum(List<ReconciliationItemNew> reconciliationItemNews, List<Waybill> waybills) {
        ReconciliationItemSum reconciliationItemSum = new ReconciliationItemSum();

        //司机价格总和
        for (ReconciliationItemNew reconciliationItem : reconciliationItemNews) {
            if (reconciliationItem.getDriverHandlingFee() != null) {
                BigDecimal num = reconciliationItemSum.getDriverHandlingCostSum().add(reconciliationItem.getDriverHandlingFee());
                reconciliationItemSum.setDriverHandlingCostSum(num);
            }
            if (reconciliationItem.getLaborerHandlingFee() != null) {
                BigDecimal num = reconciliationItemSum.getLaborerHandlingCostSum().add(reconciliationItem.getLaborerHandlingFee());
                reconciliationItemSum.setLaborerHandlingCostSum(num);
            }
            if (reconciliationItem.getDriverFinalAfterTax() != null) {
                BigDecimal num = reconciliationItemSum.getDriverAfterTaxSum().add(reconciliationItem.getDriverFinalAfterTax());
                reconciliationItemSum.setDriverAfterTaxSum(num);
            }
            if (reconciliationItem.getDriverFinalBeforeTax() != null) {
                BigDecimal num = reconciliationItemSum.getDriverBeforeTaxSum().add(reconciliationItem.getDriverFinalBeforeTax());
                reconciliationItemSum.setDriverBeforeTaxSum(num);
            }

        }

        //客户价格总和
        for (Waybill waybill : waybills) {

            if (waybill.getEstimateFreight() != null) {
                BigDecimal num = reconciliationItemSum.getBeforeTaxSum().add(waybill.getEstimateFreight());
                reconciliationItemSum.setBeforeTaxSum(num);
            }
            if (waybill.getAfterTaxFreight() != null) {
                BigDecimal num = reconciliationItemSum.getAfterTaxSum().add(waybill.getAfterTaxFreight());
                reconciliationItemSum.setAfterTaxSum(num);
            }
        }
        return reconciliationItemSum;
    }


    @Override
    public Page<ReconciliationVo> reconciliationSearch(PageQueryCondition<ReconciliationQueryVo> params, LoginEmployee loginEmployee) throws BusinessException {
        //组装查询参数
        ReconciliationNewExample example = this.buildQueryParams(params, loginEmployee);
        //获取数量
        int count = reconciliationNewMapper.countByExample(example);
        Page<ReconciliationVo> rst = new Page<>(params.getPageNo(), params.getPageSize(), count);
        if (count <= 0) return rst;

        //获取数据
        List<ReconciliationNew> listData = reconciliationNewMapper.selectByExample(example);
        List<ReconciliationVo> voList = this.buildReconResult(listData);
        rst.setResults(voList);
        return rst;
    }

    private List<ReconciliationVo> buildReconResult(List<ReconciliationNew> listData) {
        if (CollectionUtils.isEmpty(listData)) return null;

        List<ReconciliationVo> rstData = new ArrayList<>();
        ReconciliationVo vo = null;
        for (ReconciliationNew recon : listData) {
            vo = new ReconciliationVo();
            vo.setReconciliationNew(recon);
            vo.setCustomerInfo(customerInfoService.findCusInfoById(recon.getCustomerId()));
            this.judgeEvidence(vo, recon);

            rstData.add(vo);
        }

        return rstData;

    }

    private void judgeEvidence(ReconciliationVo vo, ReconciliationNew recon) {
        //是否上传凭证
        List<ImageUploadManage> images = imageUploadManageService.listByRelationIdAndSign(recon.getReconciliationId(), ImageUploadManage.ImageUploadManageSign.RECONCILIATION_PAYABLE.getCode());

        if (CollectionUtils.isNotEmpty(images)) {
            vo.setEvidenceStatus(ReconciliationVo.EvidenceStatus.ALREADY_UPLOAD.name());
        } else {
            vo.setEvidenceStatus(ReconciliationVo.EvidenceStatus.NONE.name());
        }
    }

    /**
     * 组装查询参数
     *
     * @param params
     * @param loginEmployee
     * @return
     */
    private ReconciliationNewExample buildQueryParams(PageQueryCondition<ReconciliationQueryVo> params, LoginEmployee loginEmployee) {
        ReconciliationNewExample example = new ReconciliationNewExample();
        ReconciliationNewExample.Criteria criteria = example.createCriteria();

        if (params == null) return example;
        ReconciliationQueryVo param = params.getFilters();
        example.setSize(params.getPageSize());
        example.setStartOffSet(params.getStartOffSet());
        if (StringUtils.isNotBlank(params.getOrderBy()) && StringUtils.isNotBlank(params.getOrderSort())) {
            example.setOrderByClause(params.getOrderBy() + " " + params.getOrderSort());
        }

        criteria.andTenantIdEqualTo(loginEmployee.getTenantId());

        if (param == null) return example;

        //运单号
        if (StringUtils.isNotBlank(param.getReconciliationNo())) {
            criteria.andReconciliationNoLike(param.getReconciliationNo() + "%");
        }
        //审核状态
        if (param.getReconciliationStatus() != null) {
            criteria.andReconciliationStatusEqualTo(param.getReconciliationStatus());
        }
        //客户
        if (param.getCustomerId() != null) {
            criteria.andCustomerIdEqualTo(param.getCustomerId());
        }
        if (StringUtils.isNotBlank(param.getCustomerName())) {
            criteria.andCustomerNameLike(param.getCustomerName() + "%");
        }
        //项目
        if (param.getProjectId() != null) {
            criteria.andProjectIdEqualTo(param.getProjectId());
        }
        if (StringUtils.isNotBlank(param.getProjectName())) {
            criteria.andProjectNameLike(param.getProjectName() + "%");
        }
        //提交审核时间
        if (param.getStartTime() != null) {
            criteria.andSubmitTimeGreaterThanOrEqualTo(param.getStartTime());

            if (param.getEndTime() != null) {
                criteria.andSubmitTimeLessThanOrEqualTo(param.getEndTime());
            }

        }
        //开票状态
        if (param.getHasInvoice() != null) {
            if (param.getHasInvoice()) {
                criteria.andInvoiceNoIsNotNull();
            } else {
                criteria.andInvoiceNoIsNull();
            }
        }
        //收款状态
        if (param.getReceiveStatus() != null) {
            criteria.andReceiveStatusEqualTo(param.getReceiveStatus());
        }

        //业务区域
        if (CollectionUtils.isNotEmpty(param.getAreaCodeList())) {
            criteria.andAreaCodeOrLikeIn(param.getAreaCodeList());
        }

        return example;

    }

    @Override
    public void submitToWorkFlow(Reconciliation reconciliation, LoginEmployee loginEmployee) throws BusinessException {
        if (reconciliation == null || reconciliation.getReconciliationIds() == null
            || reconciliation.getReconciliationIds().isEmpty())
            return;

        //没有凭证不能提交审核
//        List<ImageUploadManage> evidenceList = this.getEvidence(reconciliation.getReconciliationId());
//        if (CollectionUtils.isEmpty(evidenceList))
//            throw new BusinessException("noEvidence", "errors.reconciliation.noEvidence");

        for (Integer id : reconciliation.getReconciliationIds()) {
            ReconciliationNew master = new ReconciliationNew();
            master.setSubmitter(loginEmployee.getUserId());
            master.setSubmitTime(new Date());
            master.setReconciliationStatus(Integer.valueOf(Reconciliation.ReconciliationStatus.SUBMIT.getCode()).byteValue());
            master.setReconciliationId(id);

            ReconciliationNew reconciliationDB = reconciliationNewMapper.selectByPrimaryKey(id);
            if (reconciliationDB == null)
                continue;

            // 工作流程API
            Map<String, Object> variables = new HashMap<String, Object>();
            variables.put("number", reconciliationDB.getReconciliationNo());
            ProcessInstance ins = processService.startProcessInstance("waybill", id + "", variables, loginEmployee);
            master.setProcessInstanceId(ins.getProcessInstanceId());
            this.updateReconciliationNew(master, loginEmployee);
        }
    }


    @Override
    public void finishWorkFlowTask(Task task, LoginEmployee loginEmployee) throws BusinessException {
        Map<String, Object> map = new HashMap<String, Object>();
        ReconciliationNew master = reconciliationNewMapper.selectByPrimaryKey(task.getReconciliationId());
        if( master.getReconciliationStatus() != null
                && Integer.valueOf( master.getReconciliationStatus()) .equals( Reconciliation.ReconciliationStatus.AGREE.getCode())) {
            throw new BusinessException("reconciliationStatus","该对账单已经审核通过");
        }
        map = taskService.complete(task.getTaskId(), task.getApprovalKey(), task.getComment(), loginEmployee);
        log.info("task complete :{}", map);
        this.handleWorkFlowMsg(task,master, loginEmployee, map);
    }

    /**
     * 通知fms
     *
     * @param task
     * @param loginEmployee
     * @param reconciliationNew
     */
    private void doSendWorkFlowPassMsg(Task task, LoginEmployee loginEmployee, ReconciliationNew reconciliationNew) {
        // 对账单信息
        ReconciliationV2ToFms reconciliationV2ToFms = new ReconciliationV2ToFms();
        // 相同属性名复制
        BeanUtils.copyProperties(reconciliationNew, reconciliationV2ToFms);
        //客户初始
        reconciliationV2ToFms.setCustomerWithTaxInitialFreight(reconciliationNew.getCustomerInitialBeforeTax());
        reconciliationV2ToFms.setCustomerWithoutTaxInitialFreight(reconciliationNew.getCustomerInitialAfterTax());
        //客户最终
        reconciliationV2ToFms.setCustomerWithTaxFinalFreight(reconciliationNew.getCustomerFinalBeforeTax());
        reconciliationV2ToFms.setCustomerWithoutTaxFinalFreight(reconciliationNew.getCustomerFinalAfterTax());
        //司机初始
        reconciliationV2ToFms.setDriverWithTaxInitialFreight(reconciliationNew.getDriverInitialBeforeTax());
        reconciliationV2ToFms.setDriverWithoutTaxInitialFreight(reconciliationNew.getDriverInitialAfterTax());
        // 司机最终
        reconciliationV2ToFms.setDriverWithTaxFinalFreight(reconciliationNew.getDriverFinalBeforeTax());
        reconciliationV2ToFms.setDriverWithoutTaxFinalFreight(reconciliationNew.getDriverFinalAfterTax());
        // 对账单详情
        List<ReconciliationVehicleItemToFms> reconciliationVehicleItemToFmsList = new ArrayList<>();
        List<ReconciliationItemNew> rows = findReconciliationItemByReconciliationId(reconciliationNew.getReconciliationId());
        for (ReconciliationItemNew reconciliationItemNew : rows) {
            ReconciliationVehicleItemToFms reconciliationVehicleItemToFms = new ReconciliationVehicleItemToFms();
            // 相同属性名复制
            BeanUtils.copyProperties(reconciliationItemNew, reconciliationVehicleItemToFms);
            reconciliationVehicleItemToFms.setTaxRate(reconciliationItemNew.getTaxRateValue());
            reconciliationVehicleItemToFms.setDriverCarryFee(reconciliationItemNew.getDriverHandlingFee());
            reconciliationVehicleItemToFms.setLaborCarryFee(reconciliationItemNew.getLaborerHandlingFee());
            //司机初始
            reconciliationVehicleItemToFms.setWithTaxInitialFreight(reconciliationItemNew.getDriverInitialBeforeTax());
            reconciliationVehicleItemToFms.setWithoutTaxInitialFreight(reconciliationItemNew.getDriverInitialAfterTax());
            //司机最终
            reconciliationVehicleItemToFms.setWithTaxFinalFreight(reconciliationItemNew.getDriverFinalBeforeTax());
            reconciliationVehicleItemToFms.setWithoutTaxFinalFreight(reconciliationItemNew.getDriverFinalAfterTax());
            reconciliationVehicleItemToFms.setVendorId(reconciliationItemNew.getVendorId());
            reconciliationVehicleItemToFmsList.add(reconciliationVehicleItemToFms);
        }
        reconciliationV2ToFms.setListReconciliationVehicleItemToFms(reconciliationVehicleItemToFmsList);
        List<Waybill> waybills = waybillCommonService.findByReconciliationNo(reconciliationNew.getReconciliationNo());
        reconciliationV2ToFms.setWaybills(waybills);
        // mq 通知
        //获取 crm 客户映射
        CustomerInfo customerInfo = customerInfoService.findCusInfoById(waybills.get(0).getCustomerId());
        if (customerInfo == null) {
            throw new BusinessException("customerNull", "errors.common.prompt", "客户不存在");
        }
        reconciliationV2ToFms.setCrmCustomerId(customerInfo.getCrmCustomerId());
        Employee employee = employeeService.loadEmployee(customerInfo.getCustomerManagerUserId());
        if (employee == null) {
            throw new BusinessException("customerNull", "errors.common.prompt", "客户经理不存在");
        }
        reconciliationV2ToFms.setCustomerManagerUserId(employee.getUserId());
        reconciliationV2ToFms.setType(reconciliationNew.getReconciliationType() == null ? ReconciliationNew.Type.ORDINARY.getCode() : reconciliationNew.getReconciliationType());
        //客户名称取生成对账单时的
//        reconciliationV2ToFms.setCustomerName( customerInfo.getCustomerName());
//        reconciliationV2ToFms.setAreaCode( customerInfo.getAreaCode() );
        log.info("Send To FMS : {}.", JSONObject.toJSONString(reconciliationV2ToFms));
        sendService.send(reconciliationV2ToFms);
    }


    @Override
    public void cancelWorkFlowTask(int reconciliationId, LoginEmployee loginEmployee) throws BusinessException {
        ReconciliationNew reconciliationNew = reconciliationNewMapper.selectByPrimaryKey(reconciliationId);

        if (reconciliationNew == null) return;
        if (reconciliationNew.getProcessInstanceId() == null) return;
        if (NumberUtils.compare(reconciliationNew.getReconciliationStatus(), Reconciliation.ReconciliationStatus.AGREE.getCode()) == 0)
            return;
        if (NumberUtils.compare(reconciliationNew.getReconciliationStatus(), Reconciliation.ReconciliationStatus.Append.getCode()) == 0)
            return;
        //撤回工作流审批
        processService.revokeProcessInstance(reconciliationNew.getProcessInstanceId(), "发起人撤回", loginEmployee);
        //修改对账单状态

        reconciliationNew.setReconciliationStatus(Integer.valueOf(Reconciliation.ReconciliationStatus.Append.getCode()).byteValue());

        this.updateReconciliationNew(reconciliationNew, loginEmployee);

    }

    @Override
    public void cancelReconciliation(int reconciliationId, LoginEmployee loginEmployee) throws BusinessException {
        ReconciliationNew reconciliationNew = reconciliationNewMapper.selectByPrimaryKey(reconciliationId);
        if (reconciliationNew == null) return;
        if (NumberUtils.compare(reconciliationNew.getReconciliationStatus(), Reconciliation.ReconciliationStatus.Append.getCode()) != 0 && NumberUtils.compare(reconciliationNew.getReconciliationStatus(), Reconciliation.ReconciliationStatus.REJECT.getCode()) != 0)
            throw new BusinessException("reconciliationStatusError", "errors.reconciliation.cancelStatusError");

        String processInstanceId = reconciliationNew.getProcessInstanceId();
        Byte reconciliationStatus = reconciliationNew.getReconciliationStatus();
        //回滚运单状态
        List<Waybill> waybills = waybillCommonService.findByReconciliationNo(reconciliationNew.getReconciliationNo());
        if (CollectionUtils.isEmpty(waybills))
            throw new BusinessException("reconciliationError", "运单为空");
        Waybill w = null;
        for (Waybill waybill : waybills) {
            w = new Waybill();
            w.setWaybillId(waybill.getWaybillId());
            w.setReconciliationNo("");
            w.setIsSubmitMatch(null);
            w.setReconciliationStatus(Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode());
            waybillCommonService.update(w, loginEmployee);
        }
        //删除对账单明细
        ReconciliationItemNewExample example = new ReconciliationItemNewExample();
        ReconciliationItemNewExample.Criteria criteria = example.createCriteria();
        criteria.andReconciliationIdEqualTo(reconciliationId);

        reconciliationItemNewMapper.deleteByExample(example);
        //删除对账单
        reconciliationNewMapper.deleteByPrimaryKey(reconciliationId);
        //删除明细日志
        reconciliationChangeLogService.deleteByReconciliationId(reconciliationId);

        //驳回状态要删除工作流记录
        if (NumberUtils.compare(reconciliationStatus, Reconciliation.ReconciliationStatus.REJECT.getCode()) == 0) {
            if( !reconciliationNew.getSubmitter().equals( loginEmployee.getUserId()) ) {
              throw  new BusinessException("cancelReconciliationError", "操作人必须和提交到工作流的是同一个人");
            }
            processService.deleteProcessInstance(processInstanceId, "发起人撤回", loginEmployee);
        }

    }

    /**
     * 处理工作流消息
     *
     * @param task
     * @param loginEmployee
     * @param map
     */
    private void handleWorkFlowMsg(Task task,ReconciliationNew master, LoginEmployee loginEmployee, Map<String, Object> map) {
        if (map != null && map.containsKey("status") && map.get("status") != null) {
            // master.setReconciliationId(task.getReconciliationId());
            master.setReconciliationStatus(Integer.valueOf(map.get("status").toString()).byteValue());
            this.updateReconciliationNew(master, loginEmployee);

            if (map.get("status").toString().equals(Reconciliation.ReconciliationStatus.AGREE.getCode() + "")) {
                // 将运单的对账状态更改为已对账
                reconciliationWaybillUtils.updateWaybillToHasReconciliation(master.getReconciliationNo());
                this.doSendWorkFlowPassMsg(task, loginEmployee, master);

            }
        }

    }

    @Override
    public List<ImageUploadManage> getEvidence(int reconciliationId) throws BusinessException {
        List<ImageUploadManage> imageUploadManageList = imageUploadManageService.listByRelationIdAndSign(reconciliationId, ImageUploadManage.ImageUploadManageSign.RECONCILIATION_PAYABLE.getCode());
        return imageUploadManageList;
    }

    @Override
    public ReconciliationVo getReconciliationVoById(int reconciliationId) throws BusinessException {
        ReconciliationNew recon = reconciliationNewMapper.selectByPrimaryKey(reconciliationId);
        ReconciliationVo vo = this.buildReconciliationVo(recon);
        return vo;
    }

    private ReconciliationVo buildReconciliationVo(ReconciliationNew recon) {
        ReconciliationVo vo = new ReconciliationVo();
        vo.setReconciliationNew(recon);
        this.judgeEvidence(vo, recon);

        return vo;
    }


    @Override
    public Page<ReconciliationForVehicleVo> reconciliationSearchForVehicle(PageQueryCondition<ReconciliationForVehicleQueryVo> params, LoginEmployee loginEmployee) throws BusinessException {
        //组装参数
        ReconciliationItemNewExample example = this.buildItemQueryParams(params, loginEmployee);

        //查询数量
        int count = reconciliationItemNewMapper.countByExample(example);

        Page<ReconciliationForVehicleVo> rstData = new Page<>(params.getPageNo(), params.getPageSize(), count);
        if (count <= 0) return rstData;
        //查询数据
        List<ReconciliationItemNew> rawList = reconciliationItemNewMapper.selectByExample(example);
        List<ReconciliationForVehicleVo> voList = this.buildItemResult(rawList);
        rstData.setResults(voList);

        return rstData;
    }

    /**
     * 组装结果集
     *
     * @param rawList
     * @return
     */
    private List<ReconciliationForVehicleVo> buildItemResult(List<ReconciliationItemNew> rawList) {
        if (CollectionUtils.isEmpty(rawList)) return null;

        List<ReconciliationForVehicleVo> voList = new ArrayList<>();
        ReconciliationForVehicleVo vo = null;

        for (ReconciliationItemNew itemNew : rawList) {
            vo = new ReconciliationForVehicleVo();
            vo.setReconciliationItemNew(itemNew);

            voList.add(vo);
        }

        return voList;
    }

    /**
     * 组装车辆对账查询参数
     *
     * @param params
     * @param loginEmployee
     * @return
     */
    private ReconciliationItemNewExample buildItemQueryParams(PageQueryCondition<ReconciliationForVehicleQueryVo> params, LoginEmployee loginEmployee) {
        ReconciliationItemNewExample example = new ReconciliationItemNewExample();
        example.setStartOffSet(params.getStartOffSet());
        example.setSize(params.getPageSize());
        if (StringUtils.isNotBlank(params.getOrderBy())) {
            example.setOrderByClause(params.getOrderBy() + " " + params.getOrderSort());
        }

        ReconciliationItemNewExample.Criteria criteria = example.createCriteria();

        ReconciliationForVehicleQueryVo queryVo = params.getFilters();
        if (queryVo == null)
            throw new BusinessException("reconciliationIdNull", "errors.paramCanNotNullWithName", "对账单参数");

        if (queryVo.getReconciliationId() == null) {
            throw new BusinessException("reconciliationIdNull", "errors.paramCanNotNullWithName", "所属对账单");
        }

        //固定对账单号
        criteria.andReconciliationIdEqualTo(queryVo.getReconciliationId());

        if (StringUtils.isNotBlank(queryVo.getPlateNumber())) {
            criteria.andPlateNumberLike(queryVo.getPlateNumber() + "%");
        }
        if (queryVo.getPayStatus() != null) {
            criteria.andPayStatusEqualTo(queryVo.getPayStatus());
        }

        return example;

    }

    @Override
    public Page<ReconciliationWaybillDetailVo> reconciliationSearchWaybill(PageQueryCondition<ReconciliationWaybillDetailQueryVo> params, LoginEmployee loginEmployee) throws BusinessException {
        return this.findWaybillInfo(params, loginEmployee);
    }

    private Page<ReconciliationWaybillDetailVo> findWaybillInfo(PageQueryCondition<ReconciliationWaybillDetailQueryVo> params, LoginEmployee loginEmployee) {
        if (params == null)
            throw new BusinessException("reconciliationWaybillParamNull", "errors.paramCanNotNullWithName", "参数");
        PageCondition pageCondition = new PageCondition();
        pageCondition.setPageNo(params.getPageNo());
        pageCondition.setPageSize(params.getPageSize());
        pageCondition.setOrderBy(params.getOrderBy());
        pageCondition.setOrderSort(params.getOrderSort());
        pageCondition.setFilters(new HashMap<String, Object>());
        ReconciliationWaybillDetailQueryVo queryVo = params.getFilters();
        if (queryVo == null)
            throw new BusinessException("reconciliationVehicleParamNull", "errors.paramCanNotNullWithName", "查询参数");
        //对账单号+车牌号
        pageCondition.getFilters().put("waybillNo", queryVo.getWaybillNo());
        if (StringUtils.isNotBlank(queryVo.getPlateNumber())) {
            this.buildWaybillFilterForVehicle(pageCondition.getFilters(), queryVo, loginEmployee);
        } else if (queryVo.getCustomerId() != null) {
            this.buildWaybillFilterForCustomer(pageCondition.getFilters(), queryVo, loginEmployee);
        } else if (queryVo.getVendorId() != null) {
            this.buildWaybillFilterForVendorId(pageCondition.getFilters(), queryVo, loginEmployee);
        } else {
            throw new BusinessException("reconciliationVehicleParamNull", "errors.common.prompt", "不支持的查询");
        }
        int count = waybillCommonService.searchCount(pageCondition);
        Page<ReconciliationWaybillDetailVo> rstData = new Page<>(params.getPageNo(), params.getPageSize(), count);
        if (count <= 0) return rstData;

        List<Waybill> waybills = waybillCommonService.search(pageCondition);
        List<ReconciliationWaybillDetailVo> voList = this.buildReconciliationWaybillDetailVo(waybills);
        rstData.setResults(voList);

        return rstData;
    }

    /**
     * 组装运单列表
     *
     * @param waybills
     * @return
     */
    private List<ReconciliationWaybillDetailVo> buildReconciliationWaybillDetailVo(List<Waybill> waybills) {
        if (CollectionUtils.isEmpty(waybills)) return null;
        List<ReconciliationWaybillDetailVo> list = new ArrayList<>();
        ReconciliationWaybillDetailVo vo = null;

        for (Waybill w : waybills) {
            vo = new ReconciliationWaybillDetailVo();
            vo.setWaybill(w);

            WaybillParam wp = waybillParamService.findByWaybillId(w.getWaybillId());
            vo.setWaybillParam(wp);
            TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(w.getWaybillId(), null);
            vo.setRequire(truckRequire);

            list.add(vo);
        }

        return list;
    }

    /**
     * 车辆对账-运单列表
     *
     * @param filter
     * @param queryVo
     */
    private void buildWaybillFilterForVehicle(Map<String, Object> filter, ReconciliationWaybillDetailQueryVo queryVo, LoginEmployee loginEmployee) {
        if (queryVo == null)
            throw new BusinessException("reconciliationVehicleParamNull", "errors.paramCanNotNullWithName", "查询参数");
        if (filter == null)
            throw new BusinessException("waybillFilterNull", "errors.paramCanNotNullWithName", "运单查询参数");
        if (queryVo.getReconciliationNo() == null)
            throw new BusinessException("reconciliationNoNull", "errors.paramCanNotNullWithName", "对账单号参数");
        if (StringUtils.isBlank(queryVo.getPlateNumber()))
            throw new BusinessException("plateNumberNull", "errors.paramCanNotNullWithName", "车牌号参数");
        //对账单号
        filter.put("reconciliationNo", queryVo.getReconciliationNo());
        //车牌号
        filter.put("plateNumber", queryVo.getPlateNumber());
        filter.put("tenantId", loginEmployee.getTenantId());

    }

    /**
     * 客户对账-运单列表
     *
     * @param filter
     * @param queryVo
     */
    private void buildWaybillFilterForCustomer(Map<String, Object> filter, ReconciliationWaybillDetailQueryVo queryVo, LoginEmployee loginEmployee) {
        if (queryVo == null)
            throw new BusinessException("reconciliationVehicleParamNull", "errors.paramCanNotNullWithName", "查询参数");
        if (filter == null)
            throw new BusinessException("wayBillFilterNull", "errors.paramCanNotNullWithName", "运单查询参数");
        if (loginEmployee == null)
            throw new BusinessException("loginEmployeeNull", "errors.paramCanNotNullWithName", "登录人信息");
        if (queryVo.getReconciliationNo() == null)
            throw new BusinessException("reconciliationNoNull", "errors.paramCanNotNullWithName", "对账单号参数");
        if (queryVo.getCustomerId() == null)
            throw new BusinessException("customerIdNull", "errors.paramCanNotNullWithName", "所属客户参数");
        //对账单号
        filter.put("reconciliationNo", queryVo.getReconciliationNo());

        //客户id
        filter.put("customerId", queryVo.getCustomerId());
        filter.put("tenantId", loginEmployee.getTenantId());
    }

    /***
     * 承运商id
     *
     *
     * */
    private void buildWaybillFilterForVendorId(Map<String, Object> filter, ReconciliationWaybillDetailQueryVo queryVo, LoginEmployee loginEmployee) {
        if (queryVo == null)
            throw new BusinessException("reconciliationVehicleParamNull", "errors.paramCanNotNullWithName", "查询参数");
        if (filter == null)
            throw new BusinessException("waybillFilterNull", "errors.paramCanNotNullWithName", "运单查询参数");

        if (queryVo.getReconciliationNo() == null)
            throw new BusinessException("reconciliationNoNull", "errors.paramCanNotNullWithName", "对账单号参数");
        //对账单号
        filter.put("reconciliationNo", queryVo.getReconciliationNo());
        //车牌号
        filter.put("vendorId", queryVo.getVendorId());
        filter.put("tenantId", loginEmployee.getTenantId());
    }

    /**
     * 判断是否能修改对账单
     *
     * @param reconciliationNew
     */
    private void checkCanModifyReconciliation(ReconciliationNew reconciliationNew) {
        if (reconciliationNew == null)
            throw new BusinessException("ReconciliationNewNull", "errors.common.prompt", "对账单不能为空");
        if (reconciliationNew.getReconciliationStatus() == null)
            throw new BusinessException("ReconciliationNewNull", "errors.common.prompt", "对账单状态为空");
        if (NumberUtils.compare(reconciliationNew.getReconciliationStatus(), Reconciliation.ReconciliationStatus.AGREE.getCode()) == 0)
            throw new BusinessException("ReconciliationNewStatusError", "errors.common.prompt", "当前状态不能修改");
        if (NumberUtils.compare(reconciliationNew.getReconciliationStatus(), Reconciliation.ReconciliationStatus.SUBMIT.getCode()) == 0)
            throw new BusinessException("ReconciliationNewStatusError", "errors.common.prompt", "当前状态不能修改");
    }

    @Override
    public BigDecimal updateReconciliationFreightForVehicle(FreightAdjustVo freightAdjustVo, LoginEmployee loginEmployee) throws BusinessException {
        if (freightAdjustVo == null)
            throw new BusinessException("freightAdjustVoNull", "errors.paramCanNotNullWithName", "费用修改参数");
        if (loginEmployee == null)
            throw new BusinessException("loginEmployeeNull", "errors.paramCanNotNullWithName", "登录人信息");
        if (StringUtils.isBlank(freightAdjustVo.getPlateNumber()))
            throw new BusinessException("PlateNumberNull", "errors.paramCanNotNullWithName", "车牌号");
        if (freightAdjustVo.getReconciliationId() == null)
            throw new BusinessException("reconciliationIdNull", "errors.paramCanNotNullWithName", "对账单ID");

        ReconciliationNew reconciliationNew = reconciliationNewMapper.selectByPrimaryKey(freightAdjustVo.getReconciliationId());
        if (reconciliationNew == null)
            throw new BusinessException("ReconciliationNewNotExist", "errors.common.prompt", "对账单不存在");
        //审核通过和审核中不能修改
        this.checkCanModifyReconciliation(reconciliationNew);
        //修改对账单明细价格
        ReconciliationItemNewExample itemExample = new ReconciliationItemNewExample();
//        itemExample.setSize(1);
        ReconciliationItemNewExample.Criteria itemCriteria = itemExample.createCriteria();
        itemCriteria.andReconciliationIdEqualTo(freightAdjustVo.getReconciliationId());
        if (freightAdjustVo.getVendorId() != null) {
            itemCriteria.andVendorIdEqualTo(freightAdjustVo.getVendorId());
        } else {
            itemCriteria.andPlateNumberEqualTo(freightAdjustVo.getPlateNumber());
        }

        List<ReconciliationItemNew> itemNewList = reconciliationItemNewMapper.selectByExample(itemExample);
        if (CollectionUtils.isEmpty(itemNewList))
            throw new BusinessException("ReconciliationItemNewNotExist", "errors.common.prompt", "车牌号或者承运商不正确");
        ReconciliationItemNew itemNew = itemNewList.get(0);

        //修改单个明细
        ReconciliationFreightChangeUtil.ReconciliationFreightChangeVo changeVo = reconciliationFreightChangeUtil.calculateVehiclePriceSpread(itemNew, freightAdjustVo.getAdjustAmount());
        this.updateReconciliationItemNew(itemNew, loginEmployee);

        //修改对账单价格
        ReconciliationItemNewExample example = new ReconciliationItemNewExample();
        ReconciliationItemNewExample.Criteria criteria = example.createCriteria();
        criteria.andReconciliationIdEqualTo(freightAdjustVo.getReconciliationId());
        List<ReconciliationItemNew> allItemList = reconciliationItemNewMapper.selectByExample(example);
        reconciliationFreightChangeUtil.modifyVehicleReconciliation(reconciliationNew, allItemList);

        this.updateReconciliationNew(reconciliationNew, loginEmployee);

        return changeVo.getPresentAfterTaxPrice();
    }

    /**
     * 修改车辆对账明细
     *
     * @param itemNew
     * @param loginEmployee
     */
    private void updateReconciliationItemNew(ReconciliationItemNew itemNew, LoginEmployee loginEmployee) {
        itemNew.setLastUpdateTime(new Date());
        itemNew.setLastUpdateUserId(loginEmployee.getUserId());

        reconciliationItemNewMapper.updateByPrimaryKeySelective(itemNew);
    }

    private void updateReconciliationNew(ReconciliationNew reconciliationNew, LoginEmployee loginEmployee) {
        reconciliationNew.setLastUpdateUserId(loginEmployee.getUserId());
        reconciliationNew.setLastUpdateTime(new Date());

        reconciliationNewMapper.updateByPrimaryKeySelective(reconciliationNew);
    }

    public void updateReconciliationItemNew(ReconciliationItemNew reconciliationItemNew) {
        reconciliationItemNewMapper.updateByPrimaryKeySelective(reconciliationItemNew);
    }

    @Override
    public void updateReconciliationNew(ReconciliationNew reconciliationNew) {
        reconciliationNewMapper.updateByPrimaryKeySelective(reconciliationNew);
    }


    @Override
    public void updateReconciliationFreightForCustomer(FreightAdjustVo freightAdjustVo, LoginEmployee loginEmployee) throws BusinessException {
        if (freightAdjustVo == null)
            throw new BusinessException("freightAdjustVoNull", "errors.paramCanNotNullWithName", "费用修改参数");
        if (loginEmployee == null)
            throw new BusinessException("loginEmployeeNull", "errors.paramCanNotNullWithName", "登录人信息");
        if (freightAdjustVo.getReconciliationId() == null)
            throw new BusinessException("reconciliationIdNull", "errors.paramCanNotNullWithName", "对账单ID");

        ReconciliationNew recon = reconciliationNewMapper.selectByPrimaryKey(freightAdjustVo.getReconciliationId());

        if (recon == null)
            throw new BusinessException("ReconciliationItemNewNotExist", "errors.common.prompt", "对账单不存在");
        //审核通过和审核中不能修改
        this.checkCanModifyReconciliation(recon);

        reconciliationFreightChangeUtil.modifyCustomerReconciliation(recon, freightAdjustVo.getAdjustAmount(), new ReconciliationFreightChangeUtil.ReconciliationFreightChangeVo());

        this.updateReconciliationNew(recon, loginEmployee);

    }

    @Override
    public List<ReconciliationItemNew> findReconciliationItemByReconciliationId(int reconciliationId) throws BusinessException {
        ReconciliationItemNewExample example = new ReconciliationItemNewExample();
        ReconciliationItemNewExample.Criteria criteria = example.createCriteria();
        criteria.andReconciliationIdEqualTo(reconciliationId);
        List<ReconciliationItemNew> itemNewList = reconciliationItemNewMapper.selectByExample(example);
        return itemNewList;
    }

    @Override
    public ReconciliationItemNew findReconciliationItemNewByPlateNumberAndReconciliationNo(String reconciliationNo, String plateNumber) {
        ReconciliationNew reconciliationNew = this.findReconciliationNewByReconciliationNo(reconciliationNo);
        List<ReconciliationItemNew> reconciliationItemNews = this.findReconciliationItemByReconciliationId(reconciliationNew.getReconciliationId());
        for (ReconciliationItemNew reconciliationItemNew : reconciliationItemNews) {
            if (StringUtils.equals(plateNumber, reconciliationItemNew.getPlateNumber())) {
                // 如果 车牌号相同 则返回 该详情
                return reconciliationItemNew;
            }
        }
        // 到这里 都还没有那么只能说明 该对账单号下面 没有对应车牌号的 对账单详情
        throw new BusinessException("ReconciliationItemNewNotExist", "errors.common.prompt", "对账单详情不存在");
    }

    @Override
    public ReconciliationItemNew findReconciliationItemNewByVendorIdAndReconciliationNo(String reconciliationNo, Integer vendorId) {
        ReconciliationNew reconciliationNew = this.findReconciliationNewByReconciliationNo(reconciliationNo);
        List<ReconciliationItemNew> reconciliationItemNews = this.findReconciliationItemByReconciliationId(reconciliationNew.getReconciliationId());
        for (ReconciliationItemNew reconciliationItemNew : reconciliationItemNews) {
            if (vendorId.equals(reconciliationItemNew.getVendorId())) {
                // 如果 承运商 id 相同 则返回 该详情
                return reconciliationItemNew;
            }
        }
        // 到这里 都还没有那么只能说明 该对账单号下面 没有对应车牌号的 对账单详情
        throw new BusinessException("ReconciliationItemNewNotExist", "errors.common.prompt", "对账单详情不存在");
    }

    public ReconciliationNew findReconciliationNewByReconciliationNo(String reconciliationNo) {
        ReconciliationNewExample reconciliationNewExample = new ReconciliationNewExample();
        ReconciliationNewExample.Criteria criteria = reconciliationNewExample.createCriteria();
        criteria.andReconciliationNoEqualTo(reconciliationNo);
        List<ReconciliationNew> reconciliationNewList = this.reconciliationNewMapper.selectByExample(reconciliationNewExample);
        if (reconciliationNewList == null || reconciliationNewList.size() == 0) {
            throw new BusinessException("reconciliationNewExamples", "errors.common.prompt", "对账单不存在");
        } else {
            return reconciliationNewList.get(0);
        }
    }

    @Override
    public ReconciliationOverviewVo getReconciliationOverView(int reconciliationId) throws BusinessException {
        ReconciliationNew reconciliationNew = reconciliationNewMapper.selectByPrimaryKey(reconciliationId);
        if (reconciliationNew == null) return null;
        ReconciliationOverviewVo vo = new ReconciliationOverviewVo();
        ReconciliationVo reconciliationVo = new ReconciliationVo();
        reconciliationVo.setReconciliationNew(reconciliationNew);

        vo.setReconciliationNo(reconciliationNew.getReconciliationNo());
        vo.setCustomerFinalAfterTax(reconciliationNew.getCustomerFinalAfterTax());
        vo.setCustomerFinalBeforeTax(reconciliationNew.getCustomerFinalBeforeTax());
        vo.setCustomerInitialAfterTax(reconciliationNew.getCustomerInitialAfterTax());
        vo.setCustomerInitialBeforeTax(reconciliationNew.getCustomerInitialBeforeTax());

        vo.setDriverFinalAfterTax(reconciliationNew.getDriverFinalAfterTax());
        vo.setDriverFinalBeforeTax(reconciliationNew.getDriverFinalBeforeTax());
        vo.setDriverInitialAfterTax(reconciliationNew.getDriverInitialAfterTax());
        vo.setDriverInitialBeforeTax(reconciliationNew.getDriverInitialBeforeTax());

        vo.setCustomerName(reconciliationNew.getCustomerName());
        vo.setProjectName(reconciliationNew.getProjectName());
        vo.setInvoice(reconciliationNew.getInvoiceNo());

        //收款状态
        vo.setReceiveStatusName(reconciliationVo.getReceiveStatusName());
        //车数量
        ReconciliationItemNewExample example = new ReconciliationItemNewExample();
        ReconciliationItemNewExample.Criteria criteria = example.createCriteria();
        criteria.andReconciliationIdEqualTo(reconciliationNew.getReconciliationId());
        int itemCount = reconciliationItemNewMapper.countByExample(example);
        vo.setVehicleCount(String.valueOf(itemCount));
        //运单数量
        PageCondition pageCondition = new PageCondition();
        HashMap<String, Object> filters = new HashMap<>();
        pageCondition.setFilters(filters);
        filters.put("reconciliationNo", reconciliationNew.getReconciliationNo());
        int count = waybillCommonService.searchCount(pageCondition);
        vo.setWaybillCount(String.valueOf(count));
        return vo;
    }

    @Override
    public CustomerStatisticsVo findCustomerStatistics(CustomerStatisticsQueryVo queryVo) throws BusinessException {
        if (queryVo == null) return null;
        if (queryVo.getCustomerId() == null) return null;
        //客户
        CustomerInfo customerInfo = customerInfoService.findCusInfoById(queryVo.getCustomerId());
        if (customerInfo == null) return null;

        CustomerStatisticsVo vo = reconciliationCustomerStatisticsMapper.countCustomerReconciliationFee(queryVo.getCustomerId(), queryVo.getProjectId());
        vo.setCustomerName(customerInfo.getCustomerName());

        //项目
        vo.setProjectName("");
        if (queryVo.getProjectId() != null) {
            Project project = projectService.getProject(queryVo.getProjectId());
            if (project != null) {
                vo.setProjectName(project.getName());

            }
        }

        return vo;
    }

    @Override
    public List<ReconciliationForVehicleVo> findReconciliationItemByReconciliationNewId(int reconciliationNewId) throws BusinessException {
        ReconciliationItemNewExample example = new ReconciliationItemNewExample();
        ReconciliationItemNewExample.Criteria criteria = example.createCriteria();
        criteria.andReconciliationIdEqualTo(reconciliationNewId);
        List<ReconciliationItemNew> itemNewList = reconciliationItemNewMapper.selectByExample(example);

        List<ReconciliationForVehicleVo> vehicleVoList = this.buildItemResult(itemNewList);

        return vehicleVoList;
    }

    public List<ReconciliationWaybillDetailVo> findReconciliationWaybillDetailVoByReconciliationId(int reconciliationNewId) throws BusinessException {
        ReconciliationNew reconciliationNew = reconciliationNewMapper.selectByPrimaryKey(reconciliationNewId);
        if (reconciliationNew == null) return null;
        List<Waybill> waybillList = waybillCommonService.findByReconciliationNo(reconciliationNew.getReconciliationNo());

        List<ReconciliationWaybillDetailVo> waybillDetailVos = this.buildReconciliationWaybillDetailVo(waybillList);
        return waybillDetailVos;
    }

    @Override
    public ReconciliationNew findReconciliationNewByProcessInstanceId(String processInstanceId) throws BusinessException {
        if (StringUtils.isBlank(processInstanceId)) return null;

        ReconciliationNewExample example = new ReconciliationNewExample();
        ReconciliationNewExample.Criteria criteria = example.createCriteria();
        criteria.andProcessInstanceIdEqualTo(processInstanceId);

        List<ReconciliationNew> reconciliationNewList = reconciliationNewMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(reconciliationNewList)) return null;
        return reconciliationNewList.get(0);
    }


    @Override
    public Page<ProjectForFmsVo> searchProjectForFms(PageQueryCondition<ProjectQueryVo> queryCondition, LoginUser loginUser) throws BusinessException {
        if (queryCondition == null) throw new BusinessException("queryConditionNull", "查询参数为空");
        if (queryCondition.getFilters() == null) throw new BusinessException("filterNull", "查询参数为空");
        ProjectQueryVo queryVo = queryCondition.getFilters();
        Page<ProjectForFmsVo> pageData = new Page<>(queryCondition.getPageNo(), queryCondition.getPageSize(), 0, null);
        if (queryVo.getCrmCustomerId() == null) {
            return pageData;
        }
        PageCondition pageCondition = new PageCondition();

        Map<String, Object> filters = new HashMap<>();
        pageCondition.setFilters(filters);
        filters.put("crmCustomerId", queryVo.getCrmCustomerId());
        filters.put("name", queryVo.getName());

        pageCondition.setPageNo(queryCondition.getPageNo());
        pageCondition.setPageSize(queryCondition.getPageSize());
        pageCondition.setOrderBy(queryCondition.getOrderBy());
        pageCondition.setOrderSort(queryCondition.getOrderSort());

        Page<Project> rawData = projectService.search(loginUser, pageCondition);

        if (rawData == null) return pageData;
        pageData.setTotal(rawData.getTotal());

        if (CollectionUtils.isEmpty(rawData.getResults())) return pageData;

        List<ProjectForFmsVo> results = new ArrayList<>();
        ProjectForFmsVo projectForFmsVo = null;
        for (Project project : rawData.getResults()) {
            projectForFmsVo = new ProjectForFmsVo();

            projectForFmsVo.setProject(project);

            results.add(projectForFmsVo);
        }
        pageData.setResults(results);

        return pageData;
    }

    @Override
    public void updateCustomerName(Integer customerId, String customerName) throws BusinessException {
        ReconciliationNewExample reconciliationNewExample = new ReconciliationNewExample();
        ReconciliationNewExample.Criteria criteria = reconciliationNewExample.createCriteria();
        criteria.andCustomerIdEqualTo(customerId);
        List<ReconciliationNew> reconciliationNewList = reconciliationNewMapper.selectByExample(reconciliationNewExample);
        if (CollectionUtils.isNotEmpty(reconciliationNewList)) {
            for (ReconciliationNew reconciliationNew : reconciliationNewList) {
                reconciliationNew.setCustomerName(customerName);
                reconciliationNewMapper.updateByPrimaryKeySelective(reconciliationNew);
            }
        }
    }
}
