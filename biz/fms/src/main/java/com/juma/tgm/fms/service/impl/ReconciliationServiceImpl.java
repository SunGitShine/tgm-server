package com.juma.tgm.fms.service.impl;

import com.alibaba.fastjson.JSON;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.fms.businessModule.ReconciliationWaybillUtils;
import com.juma.tgm.fms.dao.ReconciliationDao;
import com.juma.tgm.fms.dao.ReconciliationItemDao;
import com.juma.tgm.fms.domain.Reconciliation;
import com.juma.tgm.fms.domain.ReconciliationItem;
import com.juma.tgm.fms.domain.Task;
import com.juma.tgm.fms.domain.bo.ReconciliationItemToFms;
import com.juma.tgm.fms.domain.bo.ReconciliationMaster;
import com.juma.tgm.fms.domain.bo.ReconciliationToFms;
import com.juma.tgm.fms.service.ReconciliationService;
import com.juma.tgm.imageUploadManage.domain.ImageUploadManage;
import com.juma.tgm.imageUploadManage.service.ImageUploadManageService;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillParamService;
import com.juma.tgm.waybill.service.WaybillQueryService;
import com.juma.workflow.core.domain.ProcessInstance;
import com.juma.workflow.core.domain.TaskDetail;
import com.juma.workflow.core.service.ProcessService;
import com.juma.workflow.core.service.TaskService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ReconciliationServiceImpl implements ReconciliationService {

    private static Logger log = LoggerFactory.getLogger(ReconciliationServiceImpl.class);

    @Value("${dataBase.name}")
    private String dataBaseName;

    @Resource
    private ReconciliationDao reconciliationDao;

    @Resource
    private ReconciliationItemDao reconciliationItemDao;

    @Resource
    private WaybillQueryService waybillQueryService;

    @Resource
    private CustomerInfoService customerInfoService;

    @Resource
    private WaybillParamService waybillParamService;

    @Resource
    private WaybillCommonService waybillCommonService;

    @Resource
    private TruckRequireService truckRequireService;

    @Resource
    private TaskService taskService;

    @Resource
    private ProcessService processService;

    @Resource
    private SendService sendService;

    @Resource
    private ImageUploadManageService imageUploadManageService;

    @Autowired
    private ReconciliationWaybillUtils reconciliationWaybillUtils;

    public String getReconciliationNo() {
        StringBuilder wayNo = new StringBuilder("");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = dateFormat.format(new Date());
        wayNo.append(dateStr);
        Integer nextSeq = reconciliationDao.getNextSequence(dataBaseName);
        Integer random = ThreadLocalRandom.current().nextInt(10000, 99999);
        wayNo.append(random);
        DecimalFormat format = new DecimalFormat("000000");
        wayNo.append(format.format(nextSeq % 100000));
        return wayNo.toString();
    }

    @Override
    public Page<ReconciliationMaster> searchPage(PageCondition cond, LoginUser loginUser) {
        if (null == loginUser || null == loginUser.getTenantId()) {
            return new Page<ReconciliationMaster>(cond.getPageNo(), cond.getPageSize(), 0,
                    new ArrayList<ReconciliationMaster>());
        }
        cond.getFilters().put("tenantId", loginUser.getTenantId());
        int total = reconciliationDao.searchCount(cond);
        List<ReconciliationMaster> rows = reconciliationDao.searchMaster(cond);
        return new Page<ReconciliationMaster>(cond.getPageNo(), cond.getPageSize(), total, rows);
    }

    @Override
    public void createReconciliation(List<Integer> waybillIds, List<String> images, LoginUser loginUser)
            throws BusinessException {
        Reconciliation reconciliation = new Reconciliation();
        List<ReconciliationItem> reconciliationItems = new ArrayList<ReconciliationItem>();

        if (waybillIds == null || waybillIds.isEmpty())
            return;

        // 保存主表
        Date d = new Date();
        reconciliation.setCreateTime(d);
        reconciliation.setCreateUserId(loginUser.getUserId());
        reconciliation.setReconciliationNo(getReconciliationNo());
        reconciliation.setReconciliationStatus(Reconciliation.ReconciliationStatus.Append.getCode());
        reconciliation.setTenantId(loginUser.getTenantId());
        reconciliation.setTenantCode(loginUser.getTenantCode());
        reconciliationDao.insert(reconciliation);

        // 运单含税总金额
        BigDecimal totalFreight = BigDecimal.ZERO;

        List<Waybill> waybills = waybillQueryService.findWaybillByIds(waybillIds);
        for (Waybill waybill : waybills) {
            // ===保存对帐单号到运单表======
            Waybill w = new Waybill();
            w.setIsSubmitMatch(null);
            w.setWaybillId(waybill.getWaybillId());
            w.setReconciliationNo(reconciliation.getReconciliationNo());
            waybillCommonService.update(w, loginUser);

            // 计算运费含税总金额
            totalFreight = totalFreight.add(waybill.getEstimateFreight());

            ReconciliationItem item = new ReconciliationItem();
            TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybill.getWaybillId(), loginUser);
            if (truckRequire != null) {
                item.setTaxRateValue(truckRequire.getTaxRateValue());
            }

            BigDecimal rebateRate = customerInfoService.getRebateRate(waybill.getCustomerId());
            CustomerInfo customerInfo = customerInfoService.findCusInfoById(waybill.getCustomerId());
            waybill.setRebateRate(rebateRate);
            WaybillParam waybillParam = waybillParamService.findByWaybillId(waybill.getWaybillId());
            // ==== 以上信息都是同一个客户，提交过来的订单也只能是同一个客户的 =====
            item.setWaybillId(waybill.getWaybillId());
            item.setWaybillNo(waybill.getWaybillNo());
            item.setReconciliationId(reconciliation.getReconciliationId());
            item.setEstimateFreight(waybill.getEstimateFreight());
            item.setAfterTaxFreight(waybill.getAfterTaxFreight());
            item.setDriverHandlingFee(waybillParam.getDriverHandlingCost());
            item.setLaborerHandlingFee(waybillParam.getLaborerHandlingCost());
            item.setRebateFee(waybill.getRebateFee());
            item.setShow4DriverFreight(waybill.getShow4DriverFreight());
            item.setCustomerId(waybill.getCustomerId());
            item.setCustomerName(customerInfo.getCustomerName());
            item.setProjectId(waybill.getProjectId());
            item.setProjectName(waybill.getProjectName());
            item.setTruckId(waybill.getTruckId());
            item.setPlateNumber(waybill.getPlateNumber());
            item.setAreaCode(waybill.getAreaCode());
            item.setUpdateFreightRemark(waybill.getUpdateFreightRemark());
            reconciliationItems.add(item);
        }
        if (!reconciliationItems.isEmpty()) {
            // 保存明细表
            reconciliationItemDao.insertAll(reconciliationItems);
        }

        // 保存附件
        saveEnclosure(reconciliation.getReconciliationId(), images, loginUser);

        updateTotalFreight(reconciliation.getReconciliationId(), totalFreight, loginUser);
    }

    // 保存附件
    private void saveEnclosure(Integer reconciliationId, List<String> images, LoginUser loginUser) {
        for (String imageUrl : images) {
            ImageUploadManage imageUploadManage = new ImageUploadManage();
            imageUploadManage.setRelationId(reconciliationId);
            imageUploadManage.setImageUploadManageSign(
                    ImageUploadManage.ImageUploadManageSign.RECONCILIATION_PAYABLE.getCode());
            imageUploadManage.setImageUploadUrl(imageUrl);
            imageUploadManage.setCreateTime(new Date());

            imageUploadManageService.insert(imageUploadManage, loginUser);
        }
    }

    @Override
    public void adjustmentReconciliation(Integer reconciliationId, BigDecimal totalFee, List<String> images,
            LoginUser loginUser) throws BusinessException {
        Reconciliation reconciliation = reconciliationDao.get(reconciliationId);
        if (null == reconciliation) {
            return;
        }

        reconciliation.setTotalFreight(totalFee);
        reconciliationDao.update(reconciliation);

        // 删除附件
        imageUploadManageService.delete(reconciliationId,
                ImageUploadManage.ImageUploadManageSign.RECONCILIATION_PAYABLE, loginUser);

        // 保存附件
        saveEnclosure(reconciliation.getReconciliationId(), images, loginUser);
    }

    @Override
    public void updateTotalFreight(Integer reconciliationId, BigDecimal totalFreight, LoginUser loginUser)
            throws BusinessException {
        if (null == reconciliationId) {
            return;
        }

        Reconciliation reconciliation = new Reconciliation();
        reconciliation.setReconciliationId(reconciliationId);
        reconciliation.setTotalFreight(totalFreight);
        reconciliationDao.update(reconciliation);
    }

    @Override
    public void updateReconciliationItem(ReconciliationItem item) throws BusinessException {
        if (item.getReconciliationItemId() == null && item.getWaybillId() == null)
            return;
        reconciliationItemDao.update(item);
    }

    @Override
    public void updateReconciliationItemByCustomerId(ReconciliationItem item) throws BusinessException {
        if (item.getCustomerId() == null)
            return;
        reconciliationItemDao.updateReconciliationItemBatch(item);
    }

    @Override
    public void updateReconciliationItemByProjectId(ReconciliationItem item) throws BusinessException {
        if (item.getProjectId() == null)
            return;
        reconciliationItemDao.updateReconciliationItemBatch(item);
    }

    @Override
    public void batchUpdateReconciliationItem(List<ReconciliationItem> items) throws BusinessException {
        if (items == null || items.isEmpty())
            return;
        reconciliationItemDao.updateAll(items);
    }

    @Override
    public ReconciliationMaster sumItemByReconciliationId(ReconciliationMaster master) {
        Reconciliation reconciliation = reconciliationDao.get(master.getReconciliationId());
        ReconciliationMaster reconciliationMaster = reconciliationItemDao.sumItemByReconciliationId(master);
        if (null != reconciliation) {
            reconciliationMaster.setTotalFee(reconciliation.getTotalFreight());
        }
        return reconciliationMaster;
    }

    @Override
    public List<ReconciliationMaster> findItemsById(PageCondition cond) {
        return reconciliationDao.findItemsById(cond);
    }

    @Override
    public Page<ReconciliationMaster> findItemsPageById(PageCondition cond) {
        cond.getFilters().put("useLimit", true);
        int total = reconciliationDao.countItemsPageById(cond);
        List<ReconciliationMaster> rows = reconciliationDao.findItemsById(cond);
        return new Page<ReconciliationMaster>(cond.getPageNo(), cond.getPageSize(), total, rows);
    }

    @Override
    public void sumbitTask(Reconciliation reconciliation, LoginEmployee loginEmployee) throws BusinessException {
        if (reconciliation == null || reconciliation.getReconciliationIds() == null
                || reconciliation.getReconciliationIds().isEmpty())
            return;

        for (Integer id : reconciliation.getReconciliationIds()) {
            Reconciliation master = new Reconciliation();
            master.setSubmitter(loginEmployee.getUserId());
            master.setSubmitTime(new Date());
            master.setReconciliationStatus(Reconciliation.ReconciliationStatus.SUBMIT.getCode());
            master.setReconciliationId(id);

            Reconciliation reconciliationDB = reconciliationDao.get(id);
            if (reconciliationDB == null)
                continue;

            // 工作流程API
            Map<String, Object> variables = new HashMap<String, Object>();
            // variables.put("loginName",loginEmployee.getEmployeeId());
            // variables.put("departmentId",
            // loginEmployee.getLoginDepartment().getDepartmentId());
            // variables.put("loginEmployeeId",loginEmployee.getEmployeeId());
            variables.put("number", reconciliationDB.getReconciliationNo());
            // User user = userService.loadUser(loginEmployee.getUserId());
            // variables.put("applyUserName",user == null ? "":user.getName());
            ProcessInstance ins = processService.startProcessInstance("waybill", id + "", variables, loginEmployee);
            master.setProcessInstanceId(ins.getProcessInstanceId());
            /*
             * try { ProcessInstance ins =
             * processService.startProcessInstance("waybill",id+"",
             * variables,loginEmployee);
             * master.setProcessInstanceId(ins.getProcessInstanceId()); } catch
             * (Exception e) { log.error(e.getMessage(), e); throw new
             * BusinessException("workflow.error", "workflow.error"); }
             */
            reconciliationDao.update(master);
        }

    }

    private List<ReconciliationItem> findItemsByReconciliationId(Integer reconciliationId) {
        ReconciliationItem example = new ReconciliationItem();
        example.setReconciliationId(reconciliationId);
        return reconciliationItemDao.findByExample(example);
    }

    @Override
    public void cancelTask(Integer reconciliationId, LoginEmployee loginEmployee) throws BusinessException {

        Reconciliation reconciliation = reconciliationDao.get(reconciliationId);
        if (reconciliation == null)
            return;

        List<ReconciliationItem> items = findItemsByReconciliationId(reconciliationId);
        for (ReconciliationItem item : items) {
            Waybill w = new Waybill();
            w.setIsSubmitMatch(null);
            w.setWaybillId(item.getWaybillId());
            w.setReconciliationNo("");
            waybillCommonService.update(w, loginEmployee);
        }
        ReconciliationItem example = new ReconciliationItem();
        example.setReconciliationId(reconciliationId);
        reconciliationItemDao.delete(example);
        Reconciliation master = new Reconciliation();
        master.setReconciliationId(reconciliationId);
        reconciliationDao.delete(master);
        // 工作流程API TODO
        if (reconciliation.getProcessInstanceId() == null
                || reconciliation.getProcessInstanceId().trim().length() == 0) {
            return;
        }
        processService.deleteProcessInstance(reconciliation.getProcessInstanceId(), "发起人取消");
        /*
         * try { processService.deleteProcessInstance(reconciliation.
         * getProcessInstanceId(), "发起人取消"); } catch (Exception e) {
         * log.info("workflow.error:{}",e.getMessage()); throw new
         * BusinessException("workflow.error", "workflow.error"); }
         */
    }

    @Override
    public TaskDetail getWorkflowElement(String taskId, LoginEmployee loginEmployee) {
        return taskService.findTaskDetail(taskId, loginEmployee);
    }

    @Override
    public void completeTask(Task task, LoginEmployee loginEmployee) throws BusinessException {
        Map<String, Object> map = new HashMap<String, Object>();
        map = taskService.complete(task.getTaskId(), task.getApprovalKey(), task.getComment(), loginEmployee);
        log.info("task complete :{}", map);
        /*
         * try { map = taskService.complete(taskId,
         * approvalKey,task.getComment(), loginEmployee); log.info(
         * "task complete :{}",map); } catch (Exception e) {
         * log.info("workflow.error:{}",e.getMessage()); throw new
         * BusinessException("workflow.error", "workflow.error"); }
         */

        try {
            if (map != null && map.containsKey("status") && map.get("status") != null) {
                Reconciliation master = reconciliationDao.get(task.getReconciliationId());
                // master.setReconciliationId(task.getReconciliationId());
                master.setReconciliationStatus(Integer.valueOf(map.get("status").toString()));
                reconciliationDao.update(master);
                if (map.get("status").toString().equals(Reconciliation.ReconciliationStatus.AGREE.getCode() + "")) {
                    // 将运单的对账状态更改为已对账
                    this.updateToHasReconciliation(task.getReconciliationId());

                    // MQ
                    // 对账单信息
                    ReconciliationToFms toFms = new ReconciliationToFms();
                    toFms.setReconciliationId(master.getReconciliationId());
                    toFms.setReconciliationNo(master.getReconciliationNo());
                    toFms.setTenantId(loginEmployee.getTenantId());
                    toFms.setTenantCode(loginEmployee.getTenantCode());
                    toFms.setBeforeTaxCostReconciliation(master.getTotalFreight());
                    BigDecimal taxAmountReconciliation = BigDecimal.ZERO;

                    // 对账单详情
                    List<ReconciliationItemToFms> itemToFms = new ArrayList<ReconciliationItemToFms>();
                    PageCondition cond = new PageCondition();
                    cond.getFilters().put("reconciliationId", master.getReconciliationId());
                    List<ReconciliationMaster> rows = findItemsById(cond);
                    for (ReconciliationMaster s : rows) {
                        ReconciliationItemToFms rf = new ReconciliationItemToFms();
                        rf.setWaybillId(s.getWaybillId());
                        rf.setWaybillNo(s.getWaybillNo());
                        rf.setCustomerId(s.getCustomerId());
                        rf.setCustomerName(s.getCustomerName());
                        rf.setProjectId(s.getProjectId());
                        rf.setProjectName(s.getProjectName());
                        rf.setBeforeTaxCost(s.getEstimateFreight());
                        rf.setTaxRate(s.getTaxRateValue());
                        rf.setTaxAmount(s.getTaxFee());
                        rf.setAfterTaxCost(s.getAfterTaxFreight());
                        rf.setAreaCode(s.getAreaCode());
                        rf.setRemark(s.getUpdateFreightRemark());
                        rf.setPlateNumber(s.getPlateNumber());
                        rf.setCreateTime(s.getCreateTime());

                        CustomerInfo customerInfo = customerInfoService.findCusInfoById(s.getCustomerId());
                        if (null != customerInfo) {
                            rf.setCrmCustomerId(customerInfo.getCrmCustomerId());
                        }

                        taxAmountReconciliation = taxAmountReconciliation.add(s.getTaxFee());

                        itemToFms.add(rf);
                    }
                    toFms.setTaxAmountReconciliation(taxAmountReconciliation);
                    toFms.setAfterTaxCostReconciliation(
                            toFms.getBeforeTaxCostReconciliation().subtract(taxAmountReconciliation));
                    toFms.setListReconciliationItemToFms(itemToFms);

                    // mq 通知
                    log.info("Send To FMS : {}.", toFms.toString());
                    sendService.send(toFms);
                }
            }
        } catch (Exception e) {
            log.warn("unknow.error:{}", e.getMessage(), e);
        }
    }

    // 将运单的对账状态更改为已对账
    private void updateToHasReconciliation(Integer reconciliationId) {
        reconciliationWaybillUtils.updateWaybillToHasReconciliation(reconciliationId);
    }

    @Override
    public void updateReconciliationItemByWaybillId(ReconciliationItem item) throws BusinessException {
        if (item.getWaybillId() == null) {
            return;
        }

        reconciliationItemDao.updateReconciliationItemBatch(item);
    }

    @Override
    public void test() {
        Reconciliation master = reconciliationDao.get(126);
        // 对账单信息
        ReconciliationToFms toFms = new ReconciliationToFms();
        toFms.setReconciliationId(master.getReconciliationId());
        toFms.setReconciliationNo(master.getReconciliationNo());
        toFms.setTenantId(2);
        toFms.setTenantCode("000000000");
        toFms.setBeforeTaxCostReconciliation(master.getTotalFreight());

        BigDecimal taxAmountReconciliation = BigDecimal.ZERO;

        // 对账单详情
        List<ReconciliationItemToFms> itemToFms = new ArrayList<ReconciliationItemToFms>();
        PageCondition cond = new PageCondition();
        cond.getFilters().put("reconciliationId", master.getReconciliationId());
        List<ReconciliationMaster> rows = findItemsById(cond);
        for (ReconciliationMaster s : rows) {
            ReconciliationItemToFms rf = new ReconciliationItemToFms();
            rf.setWaybillId(s.getWaybillId());
            rf.setWaybillNo(s.getWaybillNo());
            rf.setCustomerId(s.getCustomerId());
            rf.setCustomerName(s.getCustomerName());
            rf.setProjectId(s.getProjectId());
            rf.setProjectName(s.getProjectName());
            rf.setBeforeTaxCost(s.getEstimateFreight());
            rf.setTaxRate(s.getTaxRateValue());
            rf.setTaxAmount(s.getTaxFee());
            rf.setAfterTaxCost(s.getAfterTaxFreight());
            rf.setAreaCode(s.getAreaCode());
            rf.setRemark(s.getUpdateFreightRemark());
            rf.setPlateNumber(s.getPlateNumber());
            rf.setCreateTime(s.getCreateTime());

            CustomerInfo customerInfo = customerInfoService.findCusInfoById(s.getCustomerId());
            if (null != customerInfo) {
                rf.setCrmCustomerId(customerInfo.getCrmCustomerId());
            }

            taxAmountReconciliation = taxAmountReconciliation.add(s.getTaxFee());

            itemToFms.add(rf);
        }

        toFms.setTaxAmountReconciliation(taxAmountReconciliation);
        toFms.setAfterTaxCostReconciliation(toFms.getBeforeTaxCostReconciliation().subtract(taxAmountReconciliation));
        toFms.setListReconciliationItemToFms(itemToFms);

        // mq 通知
        log.info("Send To FMS : {}.", JSON.toJSONString(toFms));
        sendService.send(toFms);
    }

    @Override
    public List<ReconciliationItem> findByReconciliationNo(String reconciliationNo) {
        if (StringUtils.isBlank(reconciliationNo)) {
            return new ArrayList<ReconciliationItem>();
        }
        
        Reconciliation example = new Reconciliation();
        example.setReconciliationNo(reconciliationNo);
        List<Reconciliation> list = reconciliationDao.findByExample(example);
        
        if (list.isEmpty()) {
            return new ArrayList<ReconciliationItem>();
        }
        
        return findItemsByReconciliationId(list.get(0).getReconciliationId());
    }

}
