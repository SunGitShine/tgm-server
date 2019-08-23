package com.juma.tgm.fms.service.impl.v3;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.giants.common.exception.BusinessException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.juma.auth.conf.domain.BusinessArea;
import com.juma.auth.employee.domain.Department;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.crm.support.service.CrmService;
import com.juma.fms.v2.common.*;
import com.juma.fms.v2.mq.dto.FmsMQMessageDTO;
import com.juma.fms.v2.source.logistics.dto.SourceLogisticsDTO;
import com.juma.fms.v2.source.logistics.dto.SourceLogisticsDetailDTO;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.common.DateUtils;
import com.juma.tgm.common.id.IdGeneratorTable;
import com.juma.tgm.common.workflow.ApprovalStatus;
import com.juma.tgm.common.workflow.WorkflowConstants;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.fms.dao.v3.*;
import com.juma.tgm.fms.domain.bo.ReconciliationLog;
import com.juma.tgm.fms.domain.v3.*;
import com.juma.tgm.fms.domain.v3.enums.AdjustMasterType;
import com.juma.tgm.fms.domain.v3.enums.AdjustType;
import com.juma.tgm.fms.domain.v3.enums.PayableSettleAccountTypeEnum;
import com.juma.tgm.fms.domain.v3.vo.*;
import com.juma.tgm.fms.service.v3.*;
import com.juma.tgm.id.service.IdGeneratorService;
import com.juma.tgm.mq.domain.WorkFlowMessage;
import com.juma.tgm.mq.service.MqService;
import com.juma.tgm.project.domain.v2.Project;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.tool.domain.ExecuteWorkflowInfo;
import com.juma.tgm.tools.service.*;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillAmount;
import com.juma.tgm.waybill.domain.vo.WaybillAmountFilter;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.tgm.waybill.service.WaybillAmountService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillService;
import com.juma.vms.driver.enumeration.DriverTypeEnum;
import com.juma.vms.truck.domain.Truck;
import com.juma.vms.truck.enumeration.TruckRunTypeEnum;
import com.juma.vms.vendor.domain.Vendor;
import com.juma.vms.vendor.enumeration.VendorSourceEnum;
import com.juma.workflow.core.domain.ProcessInstance;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.mybatis.generator.my.mapper.Mapper;
import org.mybatis.generator.my.service.AbstractMybatisService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;

/**
 * 调整单功能实现
 * 功能 :
 * 1.添加调整单
 * @author : Bruce(刘正航) 21:56 2019-05-13
 */
@Slf4j
@Component
public class AdjustForMasterAddServiceImpl extends AbstractMybatisService<AdjustForMaster, AdjustForMasterExample> implements AdjustForMasterAddService {

    @Autowired
    private AdjustForItemMapper adjustForItemMapper;

    @Autowired
    private AdjustForMasterMapper adjustForMasterMapper;

    @Autowired
    private AdjustForFreightAttachMapper adjustForFreightAttachMapper;

    @Autowired
    private ReconcilicationForPayableLogMapper reconcilicationForPayableLogMapper;

    @Autowired
    private ReconciliationExtraForPayableMapper reconciliationExtraForPayableMapper;

    @Autowired
    private MqService mqService;

    @Autowired
    private CrmService crmService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private WaybillService waybillService;

    @Autowired
    private FmsCommonService fmsCommonService;

    @Autowired
    private VmsCommonService vmsCommonService;

    @Autowired
    private SendReceivableService sendService;

    @Autowired
    private SendPayableServiceV3 sendPayableService;

    @Autowired
    private AuthCommonService authCommonService;

    @Autowired
    private CustomerInfoService customerInfoService;

    @Autowired
    private IdGeneratorService idGeneratorService;

    @Autowired
    private AdjustForMasterService adjustForMasterService;

    @Autowired
    private TruckRequireService truckRequireService;

    @Autowired
    private InvoiceCommonService invoiceCommonService;

    @Autowired
    private AdjustForItemService adjustForItemService;

    @Autowired
    private WaybillCommonService waybillCommonService;

    @Autowired
    private WaybillAmountService waybillAmountService;

    @Autowired
    private WorkflowCommonService workflowCommonService;

    @Autowired
    private SendReceivableServiceV3 sendReceivableService;

    @Autowired
    private AdjustForWaybillService adjustForWaybillService;

    @Autowired
    private ReconciliationCommonService reconciliationCommonService;

    @Autowired
    private ReconcilicationForPayableService reconcilicationForPayableService;

    @Autowired
    private ReconcilicationForReceivableService reconcilicationForReceivableService;

    @Autowired
    private ReconcilicationForSubPayableService reconcilicationForSubPayableService;

    @Override
    public Mapper<AdjustForMaster, AdjustForMasterExample> getMapper() {
        return adjustForMasterMapper;
    }

    @Override
    public void createOrUpdateAdjust(AdjustItemValidHolder holder,AdjustForMasterAddVO vo,List<AdjustForWaybillTemp> temps, LoginEmployee loginUser) throws BusinessException {
        // 保存主表数据
        AdjustForMaster master = addAdjustForMaster(vo, holder, temps, loginUser);
        // 保存子表数据
        addAdjustForItem(vo, master, temps, loginUser);
        // 保存运单费用表
        addWaybillAmount(vo, master, temps, loginUser);
        // 保存附件数据
        addAdjustAttach(vo, master, loginUser);
        // 调用第三方系统校验
        notifyRemoteSystem(vo, master, holder, loginUser);
        // 走工作流审批逻辑
        executeWorkflow(vo ,master, temps, loginUser);
        // 更新主表信息
        updateAdjustForMaster(master, holder);
    }

    @Override
    public WaybillStatisticsAmountVO validBeforeSubmit(AdjustForMasterAddVO vo, LoginEmployee loginUser) throws BusinessException {
        adjustForWaybillService.validWaybillTempParams(vo,loginUser);
        AdjustItemValidHolder holder = new AdjustItemValidHolder();
        List<AdjustForWaybillTemp> temps = adjustForWaybillService.listTempInfos(vo, loginUser);
        // 再次校验调整单-运单数据数据
        adjustForWaybillService.validWaybillDatas(vo, holder, temps, loginUser);
        // 调整金额汇总
        setAdjustResultAmount(vo, holder, temps, loginUser);
        return holder.getWaybillStatisticsAmountVO();
    }

    @Override
    public List<AdjustForMaster> findByFilter(AdjustForMasterFilter filter, LoginEmployee loginUser) throws BusinessException {
        if( null == filter.getWaybillId()
                && null == filter.getAdjustId()
                && StringUtils.isBlank(filter.getAdjustNo())
                && null == filter.getAdjustMasterType()
                && null == filter.getApprovalStatus()
                && CollectionUtils.isEmpty(filter.getAdjustIds())
                && CollectionUtils.isEmpty(filter.getApprovalStatusList())){
            return Lists.newArrayList();
        }
        List<Integer> adjustMasterIds = Lists.newArrayList();
        if( null != filter.getWaybillId() ){
            AdjustForItemExample example = new AdjustForItemExample().createCriteria()
                    .andWaybillIdEqualTo(filter.getWaybillId())
                    .example();
            List<AdjustForItem> items = adjustForItemMapper.selectByExample(example);
            if( CollectionUtils.isEmpty(items) ){
                return Lists.newArrayList();
            }
            for (AdjustForItem item : items) {
                adjustMasterIds.add(item.getAdjustId());
            }
        }
        if( !CollectionUtils.isEmpty(filter.getAdjustIds()) ){
            adjustMasterIds.addAll(filter.getAdjustIds());
        }
        AdjustForMasterExample masterExample = new AdjustForMasterExample().createCriteria()
                .andAdjustIdIn(adjustMasterIds)
                .andAdjustIdEqualTo(filter.getAdjustId())
                .andAdjustNoEqualTo(filter.getAdjustNo())
                .andApprovalStatusEqualTo(filter.getApprovalStatus())
                .andApprovalStatusIn(filter.getApprovalStatusList())
                .andAdjustForWhoEqualTo(filter.getAdjustMasterType())
                .andIsDeleteEqualTo(0)
                .example();

        return selectByExample(masterExample);
    }

    @Override
    public Map<Integer, List<AdjustForMaster>> findMasterDictionaryByFilter(AdjustForMasterFilter filter) throws BusinessException {
        if( null == filter.getWaybillId() && CollectionUtils.isEmpty(filter.getWaybillIds()) ){
            return Maps.newConcurrentMap();
        }

        List<Integer> adjustMasterIds = Lists.newArrayList();
        AdjustForItemExample example = new AdjustForItemExample().createCriteria()
                .andWaybillIdEqualTo(filter.getWaybillId())
                .andWaybillIdIn(filter.getWaybillIds())
                .example();

        List<AdjustForItem> items = adjustForItemMapper.selectByExample(example);
        if( CollectionUtils.isEmpty(items) ){
            return Maps.newConcurrentMap();
        }
        Map<Integer,List<Integer>> waybillAdjustIdMaps = Maps.newConcurrentMap();
        for (AdjustForItem item : items) {
            adjustMasterIds.add(item.getAdjustId());
            List<Integer> waybillIds = waybillAdjustIdMaps.get(item.getAdjustId());
            if( CollectionUtils.isEmpty(waybillIds) ){
                waybillIds = Lists.newArrayList();
                waybillAdjustIdMaps.put(item.getAdjustId(),waybillIds);
            }
            waybillIds.add(item.getWaybillId());
        }

        AdjustForMasterExample masterExample = new AdjustForMasterExample().createCriteria()
                .andAdjustIdIn(adjustMasterIds)
                .andApprovalStatusEqualTo(filter.getApprovalStatus())
                .andApprovalStatusIn(filter.getApprovalStatusList())
                .andAdjustForWhoEqualTo(filter.getAdjustMasterType())
                .andIsDeleteEqualTo(0)
                .example();

        List<AdjustForMaster> masters = selectByExample(masterExample);
        if( CollectionUtils.isEmpty(masters) ){
            return Maps.newConcurrentMap();
        }
        Map<Integer, List<AdjustForMaster>> adjustForMasterMap = Maps.newConcurrentMap();
        for (AdjustForMaster adjustForMaster : masters){
            List<Integer> waybillIds = waybillAdjustIdMaps.get(adjustForMaster.getAdjustId());
            if( CollectionUtils.isEmpty(waybillIds) ){
                continue;
            }
            for (Integer waybillId : waybillIds){
                List<AdjustForMaster> adjustForMasters = adjustForMasterMap.get(waybillId);
                if( CollectionUtils.isEmpty(adjustForMasters) ){
                    adjustForMasters = Lists.newArrayList();
                    adjustForMasterMap.put(waybillId,adjustForMasters);
                }
                adjustForMasters.add(adjustForMaster);
            }
        }
        return adjustForMasterMap;
    }

    @Override
    public List<AdjustForItem> findItemByFilter(AdjustForItemFilter filter, LoginEmployee loginUser) throws BusinessException {
        if( null == filter.getAdjustId()
                && null == filter.getVendorId()
                && null == filter.getWaybillId()
                && CollectionUtils.isEmpty(filter.getWaybillIds())){
            return Lists.newArrayList();
        }
        AdjustForItemExample example = new AdjustForItemExample().createCriteria()
                .andAdjustIdEqualTo(filter.getAdjustId())
                .andVendorIdEqualTo(filter.getVendorId())
                .andWaybillIdEqualTo(filter.getWaybillId())
                .andWaybillIdIn(filter.getWaybillIds())
                .example();
        return adjustForItemMapper.selectByExample(example);
    }

    @Override
    public AdjustForMaster getAdjustForMasterById(Integer adjustId) throws BusinessException {
        if( null == adjustId ){
            return null;
        }
        return adjustForMasterMapper.selectByPrimaryKey(adjustId);
    }

    @Override
    public void doAdjustWorkflow(WorkFlowMessage message) throws BusinessException {
        AdjustForMaster master = getAdjustForMasterById(message);
        if( null == master ){
            log.warn("接收消息中,对账单ID为空,不继续往下执行工作流逻辑:{}", JSON.toJSONString(message));
            return;
        }
        // 更新流程状态
        updateApprovalStatus(message, master);
        // 2.删除临时表数据
        adjustForWaybillService.deleteByAdjustId(master.getAdjustId(),message.getLoginEmployee());
        // 客户
        if( AdjustMasterType.CUSTOMER.getCode().equals(master.getAdjustForWho()) ){
            doReceiveWorkflow(message, master);
        }
        // 承运商
        if( AdjustMasterType.VENDOR.getCode().equals(master.getAdjustForWho()) ){
            doPayableWorkflow(message, master);
        }
    }

    private void doPayableWorkflow(WorkFlowMessage message, AdjustForMaster master) throws BusinessException {
        if( AdjustType.BEFORE.getCode().equals(master.getAdjustType()) ){
            if( ApprovalStatus.APPROVAL_AGREE.equals(message.getStatus()) ){
                // 1.更新运单金额表数据
                updateWaybillAmount(master, message.getLoginEmployee());
                // 3.对账前,月账单应付调整单推送
                doSendPayableBeforeReconciliationToFMS(master, null, message.getLoginEmployee());
            }else if( ApprovalStatus.APPROVAL_REJECTED.equals(message.getStatus()) ){
                // 驳回
            }else if( ApprovalStatus.APPROVAL_GIVE_UP.equals(message.getStatus()) ){
                // 放弃
                // 2.调整单标记删除
                markDeleteMaster(master);
            }
        }else if( AdjustType.AFTER.getCode().equals(master.getAdjustType()) ){
            // 对账后
            if( ApprovalStatus.APPROVAL_AGREE.equals(message.getStatus()) ){
                // 通过
                // 1.解冻
                List<String> vendorIds = fetchVendorIds(master,message.getLoginEmployee());
                fmsCommonService.unfrozenPayable(master.getReconcilicationNo(),vendorIds);
                // 2.更新运单金额表数据
                updateWaybillAmount(master, message.getLoginEmployee());
                // 3.更新对账明细结算运费
//                freshReconciliationForPayableSettleFreight(master,message.getLoginEmployee());
                // 4.通知FMS创建对账单对应的应付单
                doSendPayableToFMS(master,message.getLoginEmployee());
            }else if( ApprovalStatus.APPROVAL_REJECTED.equals(message.getStatus()) ){
                // 驳回
            }else if( ApprovalStatus.APPROVAL_GIVE_UP.equals(message.getStatus()) ){
                // 放弃
                // 1.解冻
                List<String> vendorIds = fetchVendorIds(master,message.getLoginEmployee());
                fmsCommonService.unfrozenPayable(master.getReconcilicationNo(),vendorIds);
                // 3.调整单标记删除
                markDeleteMaster(master);
            }
        }
    }

    private void doReceiveWorkflow(WorkFlowMessage message, AdjustForMaster master) throws BusinessException {
        if( AdjustType.BEFORE.getCode().equals(master.getAdjustType()) ){
            if( ApprovalStatus.APPROVAL_AGREE.equals(message.getStatus()) ){
                updateWaybillAmount(master, message.getLoginEmployee());
                doSendReceiveBeforeReconciliationToFMS(master, null, message.getLoginEmployee());
            }else if( ApprovalStatus.APPROVAL_REJECTED.equals(message.getStatus()) ){
                // 驳回
            }else if( ApprovalStatus.APPROVAL_GIVE_UP.equals(message.getStatus()) ){
                // 放弃
                // 2.调整单标记删除
                markDeleteMaster(master);
            }
        }else if( AdjustType.AFTER.getCode().equals(master.getAdjustType()) ){
            if( ApprovalStatus.APPROVAL_AGREE.equals(message.getStatus()) ){
                // 通过
                // 1.更新运单金额表数据
                updateWaybillAmount(master, message.getLoginEmployee());
                // 2.向开票系统追加开票金额
                doSendAdjustReceiveToInvoice(master,message.getLoginEmployee());
                // 3.发送应收单消息给FMS
                doSendReceiveToFMS(master,message.getLoginEmployee());
                // 5.解冻
                invoiceCommonService.unfrozenReceivableInvoice(master.getReconcilicationNo());
            }else if( ApprovalStatus.APPROVAL_REJECTED.equals(message.getStatus()) ){
                // 驳回
            }else if( ApprovalStatus.APPROVAL_GIVE_UP.equals(message.getStatus()) ){
                // 放弃
                // 1.解冻
                invoiceCommonService.unfrozenReceivableInvoice(master.getReconcilicationNo());
                // 3.调整单标记删除
                markDeleteMaster(master);
            }
        }
    }

    @Override
    public void doResendBillDatasToFmsOld(List<Integer> adjustIds, List<Integer> adjustItemIds, LoginEmployee loginUser) throws BusinessException{
        if( CollectionUtils.isEmpty(adjustIds) ){ return; }
        AdjustForMasterFilter filter = new AdjustForMasterFilter();
        filter.setAdjustIds(adjustIds);
        List<AdjustForMaster> masters = findByFilter(filter,loginUser);
        if( CollectionUtils.isEmpty(masters) ){ return; }
        for (AdjustForMaster master : masters){
            if(AdjustType.BEFORE.getCode().equals(master.getAdjustType())){
                // 月账单
                if(AdjustMasterType.CUSTOMER.getCode().equals(master.getAdjustForWho())){
                    // 客户-对账前
                    doSendReceiveBeforeReconciliationToFMS(master,adjustItemIds,loginUser);
                }
                if(AdjustMasterType.VENDOR.getCode().equals(master.getAdjustForWho())){
                    // 承运商-对账前
                    doSendPayableBeforeReconciliationToFMS(master,adjustItemIds,loginUser);
                }
            }
            if(AdjustType.AFTER.getCode().equals(master.getAdjustType())){
                // 普通对账单
                if(AdjustMasterType.CUSTOMER.getCode().equals(master.getAdjustForWho())){
                    // 客户-对账后
                    doSendReceiveToFMS(master,loginUser);
                }
                if(AdjustMasterType.VENDOR.getCode().equals(master.getAdjustForWho())){
                    // 承运商-对账后
                    doSendPayableToFMS(master,loginUser);
                }
            }
        }
    }

    /**应付月账单调整单**/
    private void doSendPayableBeforeReconciliationToFMS(AdjustForMaster master, List<Integer> adjustItemIds, LoginEmployee loginEmployee) {
        SourceLogisticsDTO dto = new SourceLogisticsDTO();
        AdjustForItemFilter filter = new AdjustForItemFilter();
        filter.setAdjustId(master.getAdjustId());
        List<AdjustForItem> items = findItemByFilter(filter,loginEmployee);
        List<String> waybillNos = Lists.newArrayList();
        if( !CollectionUtils.isEmpty(adjustItemIds) ){
            for (AdjustForItem item: items){
                // 传入的ID,为需要屏蔽发送的ID
                if(adjustItemIds.contains(item.getAdjustItemId())){ continue; }
                waybillNos.add(item.getWaybillNo());
            }
        }else{
            for (AdjustForItem item: items){
                waybillNos.add(item.getWaybillNo());
            }
        }
        if( CollectionUtils.isEmpty(waybillNos) ){ return; }
        List<Waybill> waybills = waybillService.findByWaybillNos(waybillNos);
        List<Waybill> needSendWaybills = Lists.newArrayList();
        Map<Integer,Vendor> vendorMap = Maps.newConcurrentMap();
        for (Waybill waybill : waybills){
            if( null == waybill.getFinishTime() ){ continue; }
            Date finishDate = waybill.getFinishTime();
            DateTime monthEnd = DateUtils.getMonthOfEnd(DateUtils.create(finishDate));
            log.info("应付运单编号:{},运单完成时间:{},运单完成时间所在月月末:{},当前时间:{}",waybill.getWaybillNo(),finishDate,monthEnd,DateUtils.create());
            // 运单在当前月则,不推送发给FMS
            if( monthEnd.isAfterNow() ){ continue; }
            // 自营司机不发消息
            if( Integer.valueOf(PayableSettleAccountTypeEnum.DRIVER.getCode()).equals(waybill.getDriverType()) ){ continue; }
            Vendor vendor = null;
            if (!Integer.valueOf(Waybill.ReceiveWay.TRANSFORM_BILL.getCode()).equals(waybill.getReceiveWay())) {
                vendor = vmsCommonService.loadVendorByVendorId(waybill.getVehicleToVendor());
                if( null == vendor){
                    log.warn("承运侧-对账后-不发消息原因:未查询到运单对应的承运商,不向FMS发送消息,运单编号:{},承运商ID:{}",waybill.getWaybillNo(),waybill.getVehicleToVendor());
                    continue;
                }
                if(null == vendor.getVendorSource()){
                    log.warn("承运侧-对账后-不发消息原因:运单承运商来源为空,不向FMS发送消息,运单编号:{},承运商ID:{}",waybill.getWaybillNo(),waybill.getVehicleToVendor());
                    continue;
                }
                if (vendor.getVendorSource().equals(VendorSourceEnum.SELF_SUPPORT.getCode())) {
                    log.warn("承运侧-对账后-不发消息原因:运单承运商为自营,不向FMS发送消息,运单编号:{},承运商ID:{}",waybill.getWaybillNo(),waybill.getVehicleToVendor());
                    continue;
                }
            }else{
                vendor = vmsCommonService.loadVendorByVendorId(waybill.getVendorId());
                if( null == vendor){
                    log.warn("承运侧-对账后-不发消息原因:未查询到运单对应的承运商,不向FMS发送消息,运单编号:{},承运商ID:{}",waybill.getWaybillNo(),waybill.getVehicleToVendor());
                    continue;
                }
            }
            vendorMap.put(waybill.getWaybillId(),vendor);
            log.info("应付运单编号:{},运单完成时间:{},运单完成时间所在月月末:{},当前时间:{},准备推送到FMS",waybill.getWaybillNo(),finishDate,monthEnd,DateUtils.create());
            // 跨月调整单, 需要推送
            needSendWaybills.add(waybill);
        }

        if( CollectionUtils.isEmpty(needSendWaybills) ){ return; }
        Map<Integer,List<Waybill>> vendorWaybillMap = Maps.newConcurrentMap();
        for (Waybill waybill : needSendWaybills){
            Vendor vendor = vendorMap.get(waybill.getWaybillId());
            if( null == vendor ){ continue;}
            List<Waybill> vendorWaybills = vendorWaybillMap.get(vendor.getVendorId());
            if( CollectionUtils.isEmpty(vendorWaybills) ){
                vendorWaybills = Lists.newArrayList();
                vendorWaybillMap.put(vendor.getVendorId(),vendorWaybills);
            }
            vendorWaybills.add(waybill);
        }
        if( CollectionUtils.isEmpty(vendorWaybillMap) ){ return; }
        for (Map.Entry<Integer,List<Waybill>> entry : vendorWaybillMap.entrySet()){
            List<Waybill> waybillList = entry.getValue();
            //组装基础数据
            FmsMQMessageDTO fmsMQMessageDTO = new FmsMQMessageDTO();
            fmsMQMessageDTO.setBusinessSystem(BusinessSystemEnum.TMS.getCode());
            buildPayableBasic(waybillList.get(0), dto);
            //组装明细
            for (Waybill waybill : waybillList) {
                SourceLogisticsDetailDTO sourceDTO = buildPayableDetail(master,waybill);
                dto.getDetailList().add(sourceDTO);
                fmsMQMessageDTO.setData(dto);
            }
            // MQ
            log.info("应付-月账单-调整单 To FMS : {}", JSONObject.toJSONString(fmsMQMessageDTO));
            sendPayableService.send(fmsMQMessageDTO);
        }
    }

    /**应收月账单调整单**/
    private void doSendReceiveBeforeReconciliationToFMS(AdjustForMaster master, List<Integer> adjustItemIds, LoginEmployee loginEmployee) {
        SourceLogisticsDTO dto = new SourceLogisticsDTO();
        AdjustForItemFilter filter = new AdjustForItemFilter();
        filter.setAdjustId(master.getAdjustId());
        List<AdjustForItem> items = findItemByFilter(filter,loginEmployee);
        List<String> waybillNos = Lists.newArrayList();
        if( !CollectionUtils.isEmpty(adjustItemIds) ){
            for (AdjustForItem item: items){
                // 传入的ID,为需要屏蔽发送的ID
                if(adjustItemIds.contains(item.getAdjustItemId())){ continue; }
                waybillNos.add(item.getWaybillNo());
            }
        }else{
            for (AdjustForItem item: items){
                waybillNos.add(item.getWaybillNo());
            }
        }
        if( CollectionUtils.isEmpty(waybillNos) ){ return; }
        List<Waybill> waybills = waybillService.findByWaybillNos(waybillNos);
        List<Waybill> needSendWaybills = Lists.newArrayList();
        for (Waybill waybill : waybills){
            if( null == waybill.getFinishTime() ){ continue; }
            Date finishDate = waybill.getFinishTime();
            DateTime monthEnd = DateUtils.getMonthOfEnd(DateUtils.create(finishDate));
            log.info("应收运单编号:{},运单完成时间:{},运单完成时间所在月月末:{},当前时间:{}",waybill.getWaybillNo(),finishDate,monthEnd,DateUtils.create());
            // 运单在当前月则,不推送发给FMS
            if( monthEnd.isAfterNow() ){ continue; }
            log.info("应收运单编号:{},运单完成时间:{},运单完成时间所在月月末:{},当前时间:{},准备推送到FMS",waybill.getWaybillNo(),finishDate,monthEnd,DateUtils.create());
            needSendWaybills.add(waybill);
        }

        if( CollectionUtils.isEmpty(needSendWaybills) ){ return; }

        //组装基础数据
        FmsMQMessageDTO fmsMQMessageDTO = new FmsMQMessageDTO();
        fmsMQMessageDTO.setBusinessSystem(BusinessSystemEnum.TMS.getCode());
        //组装基础数据
        buildReceivableBasic(needSendWaybills.get(0), dto);
        //组装明细
        for (Waybill waybill : needSendWaybills) {
            SourceLogisticsDetailDTO sourceDTO = buildReceivableDetail(master,waybill);
            dto.getDetailList().add(sourceDTO);
            fmsMQMessageDTO.setData(dto);
        }
        // MQ
        log.info("应收-月账单-调整单 To FMS : {}", JSONObject.toJSONString(fmsMQMessageDTO));
        sendReceivableService.send(fmsMQMessageDTO);
    }

    private void doSendPayableToFMS(AdjustForMaster master, LoginEmployee loginEmployee) throws BusinessException {
        log.info("承运商-对账后-推送消息给FMS-告知生成应付单");
        ReconcilicationForPayable payable = null;
        try {
            payable = reconcilicationForPayableService.findByReconciliationNo(master.getReconcilicationNo());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if( null == payable ){ return; }
        // 以承运商为维度拆分
        Map<String, String> vendorNameMap = new HashMap<String, String>();
        Map<String, List<SourceLogisticsDetailDTO>> map = new HashMap<String, List<SourceLogisticsDetailDTO>>();
        // 暂存调整单详情
        Map<String,AdjustForItem> itemMap = Maps.newConcurrentMap();
        buildPayableVendorDictionary(payable, master, vendorNameMap, map, itemMap, loginEmployee);
        // 循环map，组装主体信息及承运商信息
        Map<String, FmsMQMessageDTO<SourceLogisticsDTO>> toFmsMap = new HashMap<String, FmsMQMessageDTO<SourceLogisticsDTO>>();
        // 以承运商为维度, 组装应付单消息推送数据
        buildPayableByVendor(payable, vendorNameMap, map, itemMap, toFmsMap, loginEmployee);

        // 循环map，发送FMS的MQ消息
        for (String key : toFmsMap.keySet()) {
            log.info("Send Adjust Payable Info To FMS : {}.", JSONObject.toJSONString(toFmsMap.get(key)));
            sendPayableService.send(toFmsMap.get(key));
        }
    }

    private void doSendReceiveToFMS(AdjustForMaster master, LoginEmployee loginEmployee) throws BusinessException {
        log.info("客户-对账后-推送消息给FMS-告知生成应收单");
        ReconcilicationForReceivable receivable = reconcilicationForReceivableService.findReconciliationByReconciliationNo(master.getReconcilicationNo());
        SourceLogisticsDTO sourceLogisticsDTO = new SourceLogisticsDTO();
        // 对账单主表信息
        fillReceiveMainSourceInfo(receivable, sourceLogisticsDTO);

        // 对账单详情信息组装
        List<SourceLogisticsDetailDTO> sourceLogisticsDetailDTOList = buildReceiveDetailInfo(master,sourceLogisticsDTO,receivable.getReconcilicationId(), loginEmployee);

        sourceLogisticsDTO.setDetailList(sourceLogisticsDetailDTOList);

        FmsMQMessageDTO<SourceLogisticsDTO> fmsMQMessageDTO = new FmsMQMessageDTO<SourceLogisticsDTO>();
        fmsMQMessageDTO.setBusinessSystem(BusinessSystemEnum.TMS.getCode());
        fmsMQMessageDTO.setData(sourceLogisticsDTO);
        log.info("Send Adjust Receive Info To FMS {}.", JSONObject.toJSONString(fmsMQMessageDTO));
        sendService.send(fmsMQMessageDTO);
        //签约、运营不一致  生成关联单据
        reconciliationCommonService.validAndCreateReconciliationAdjustLink(receivable,master);
    }

    /**调用开票系统和FMS系统**/
    private void notifyRemoteSystem(final AdjustForMasterAddVO vo, final AdjustForMaster master, AdjustItemValidHolder holder, final LoginEmployee loginUser) {
        // 是否有主键, 区分是编辑还是修改
        if( null != vo.getAdjustId() ){ return; }
        // 对账前不做校验
        if( AdjustType.BEFORE.getCode().equals(master.getAdjustType()) ){ return; }
        if( AdjustMasterType.CUSTOMER.getCode().equals(master.getAdjustForWho()) ){
            log.info("调用开票系统,冻结对账单对应的开票数据,对账单号:{}",master.getReconcilicationNo());
            if(!invoiceCommonService.frozenReceivableInvoice(master.getReconcilicationNo()) ){
                log.warn("冻结开票系统记录失败:{}",master.getReconcilicationNo());
                throw new BusinessException("FrozenFail","冻结开票系统记录失败");
            }
        }
        // 承运商-对账后
        if( AdjustMasterType.VENDOR.getCode().equals(master.getAdjustForWho()) && AdjustType.AFTER.getCode().equals(master.getAdjustType())){
            log.info("调用FMS冻结对账单对应的应付单,对账单号:{}, 运单号:{}",master.getReconcilicationNo(),JSON.toJSONString(holder.getVendorIds()));
            fmsCommonService.frozenPayable(master.getReconcilicationNo(),holder.getVendorIds(), master.getAdjustNo());
        }
    }

    /**更新运单金额**/
    private void updateWaybillAmount(final AdjustForMaster master, final LoginEmployee loginEmployee) {
        List<WaybillAmount> amounts = Lists.newArrayList();
        AdjustForItemFilter filter = new AdjustForItemFilter();
        filter.setAdjustId(master.getAdjustId());
        List<AdjustForItem> items = findItemByFilter(filter, loginEmployee);
        List<Integer> waybillIds = Lists.newArrayList();
        for (AdjustForItem item : items){
            waybillIds.add(item.getWaybillId());
        }
        WaybillAmountFilter amountFilter = new WaybillAmountFilter();
        amountFilter.setWaybillIds(waybillIds);
        List<WaybillAmount> oldAmounts = waybillAmountService.findByFilter(amountFilter,loginEmployee);
        Map<Integer,WaybillAmount> amountMap = Maps.newConcurrentMap();
        for (WaybillAmount amount : oldAmounts){
            amountMap.put(amount.getWaybillId(),amount);
        }
        for (AdjustForItem temp : items) {
            WaybillAmount amount = amountMap.get(temp.getWaybillId());
            if( null == amount ){
                log.warn("更新阶段, 运单金额出现空, 属于非正常情况, 调整单ID:{},运单ID:{}",temp.getAdjustId(),temp.getWaybillId());
                continue;
            }
            if(AdjustMasterType.CUSTOMER.getCode().equals(master.getAdjustForWho())){
                if( BigDecimal.ZERO.compareTo(amount.getCustomerFreightWithTax()) == 0 ){
                    amount.setCustomerFreightWithTax(validAndSetBigDecimalValue(temp.getFreightWithTax()));
                }
                amount.setLastCustomerFreightWithTax(validAndSetBigDecimalValue(temp.getFreightWithTax())
                        .add(validAndSetBigDecimalValue(temp.getAdjustForFreight()))
                        .add(validAndSetBigDecimalValue(temp.getAdjustForCarry()))
                        .add(validAndSetBigDecimalValue(temp.getAdjustForWorkload()))
                        .add(validAndSetBigDecimalValue(temp.getAdjustForUpstairs()))
                        .add(validAndSetBigDecimalValue(temp.getAdjustForFine()))
                        .add(validAndSetBigDecimalValue(temp.getAdjustForCargoLoss())));
            }else if(AdjustMasterType.VENDOR.getCode().equals(master.getAdjustForWho())){
                if( BigDecimal.ZERO.compareTo(amount.getVendorFreightWithTax()) == 0 ){
                    amount.setVendorFreightWithTax(validAndSetBigDecimalValue(temp.getFreightWithTax()));
                }
                amount.setLastVendorFreightWithTax(validAndSetBigDecimalValue(temp.getFreightWithTax())
                        .add(validAndSetBigDecimalValue(temp.getAdjustForFreight()))
                        .add(validAndSetBigDecimalValue(temp.getAdjustForCarry()))
                        .add(validAndSetBigDecimalValue(temp.getAdjustForWorkload()))
                        .add(validAndSetBigDecimalValue(temp.getAdjustForUpstairs()))
                        .add(validAndSetBigDecimalValue(temp.getAdjustForFine()))
                        .add(validAndSetBigDecimalValue(temp.getAdjustForCargoLoss())));
            }
            amount.setWaybillId(temp.getWaybillId());
            amounts.add(amount);
        }
        // 新增编辑 走同样的逻辑
        waybillAmountService.addOrUpdateBatch(amounts,loginEmployee);
    }

    /**更新调整单-审核状态**/
    private void updateApprovalStatus(final WorkFlowMessage message, final AdjustForMaster master) {
        master.setApprovalStatus(message.getStatus().getCode());
        if( ApprovalStatus.APPROVAL_AGREE.getCode().equals(message.getStatus().getCode()) ){
            master.setApprovalTime(new Date());
        }
        adjustForMasterMapper.updateByPrimaryKeySelective(master);
    }

    /**向开票系统追加调整单金额**/
    private void doSendAdjustReceiveToInvoice(AdjustForMaster master, LoginEmployee loginEmployee) {
        // 文档地址:http://conf.juma.com/pages/viewpage.action?pageId=14634388
        mqService.sendAdjustReceiveToInvoice(master.getReconcilicationNo(),master.getAdjustAmount());
    }

    /**以承运商为维度, 组装应付单消息推送数据**/
    private void buildPayableByVendor(final ReconcilicationForPayable payable, final Map<String, String> vendorNameMap, final Map<String, List<SourceLogisticsDetailDTO>> map, Map<String, AdjustForItem> itemMap, final Map<String, FmsMQMessageDTO<SourceLogisticsDTO>> toFmsMap, LoginEmployee loginUser) {
        for (String key : map.keySet()) {
            // MQ主信息
            FmsMQMessageDTO<SourceLogisticsDTO> fmsMQMessageDTO = new FmsMQMessageDTO<SourceLogisticsDTO>();
            fmsMQMessageDTO.setBusinessSystem(BusinessSystemEnum.TMS.getCode());
            // MQ承运商信息
            SourceLogisticsDTO dto = this.buildPayableSourceMainInfo(payable, loginUser);
            ReconcilicationForSubPayable subPayable = reconcilicationForSubPayableService.findByReconcilicationIdAndVendorId(payable.getReconcilicationId(),Integer.valueOf(key));
            // 单据来源编号 -- 子对账单号
            if( null != subPayable ){
                dto.setSourceNoteNo(subPayable.getSubReconcilicationNo());
            }else{
                // 创建子账单
                String subReconcilicationNo = reconcilicationForSubPayableService.insert(payable.getReconcilicationId(), Integer.valueOf(key), loginUser);
                dto.setSourceNoteNo(subReconcilicationNo);
            }
            // TMS对账单号
            dto.setSourceBizNo(payable.getReconcilicationNo());
            // 往来户编号
            dto.setTransactionalAccountNo(key);
            // 往来户名称
            dto.setTransactionalAccountName(vendorNameMap.get(key));
            Vendor vendor = vmsCommonService.loadVendorByVendorId(Integer.valueOf(key));
            dto.setEntityId(vendor.getJumaPin());

            List<SourceLogisticsDetailDTO> byVendorList = map.get(key);
            ReconciliationExtraForPayableExample example = new ReconciliationExtraForPayableExample();
            com.juma.tgm.fms.domain.v3.ReconciliationExtraForPayableExample.Criteria criteria = example.createCriteria();
            criteria.andReconciliationIdEqualTo(payable.getReconcilicationId());
            criteria.andVendorIdEqualTo(Integer.valueOf(key));
            List<ReconciliationExtraForPayable> payables = reconciliationExtraForPayableMapper.selectByExample(example);
            if( !CollectionUtils.isEmpty(payables) ){
                ReconciliationExtraForPayable extraForPayable = payables.get(0);
                // 是否开票
                dto.setTransactionalIsInvoice(extraForPayable.getIsInvoice()?1:0);
                if( extraForPayable.getIsInvoice() && BigDecimal.valueOf(-1).compareTo(extraForPayable.getVendorTaxRate()) != 0 ){
                    // 承运商开票税率
                    dto.setTransactionalInvoiceTaxRate(extraForPayable.getVendorTaxRate());
                }
                // 进项税率
                dto.setTransactionalInputTaxRate(extraForPayable.getTaxRate());
                // 承运商税额计算
                List<SourceLogisticsDetailDTO> extraInfos = this.buildPayableExtraSourceDetailInfo(byVendorList, payables, itemMap, loginUser);
                byVendorList.addAll(extraInfos);
            }
            dto.setDetailList(byVendorList);
            fmsMQMessageDTO.setData(dto);
            toFmsMap.put(key, fmsMQMessageDTO);
        }
    }

    /**构建承运商数据集**/
    private void buildPayableVendorDictionary(final ReconcilicationForPayable payable, AdjustForMaster master, final Map<String, String> vendorNameMap, final Map<String, List<SourceLogisticsDetailDTO>> map, Map<String, AdjustForItem> itemMap, final LoginEmployee loginEmployee) {
        // 获取应付对账详情
        ReconciliationPayableItemFilter filter = new ReconciliationPayableItemFilter();
        filter.setReconcilicationId(payable.getReconcilicationId());
        List<ReconcilicationForPayableItem> reconcilications = reconcilicationForPayableService.findItemByFilter(filter,loginEmployee);
        Map<Integer, ReconcilicationForPayableItem> payableItemMap = Maps.newConcurrentMap();
        for (ReconcilicationForPayableItem item : reconcilications) {
            payableItemMap.put(item.getWaybillId(),item);
        }
        AdjustForItemFilter itemFilter = new AdjustForItemFilter();
        itemFilter.setAdjustId(master.getAdjustId());
        List<AdjustForItem> items = findItemByFilter(itemFilter,loginEmployee);
        for (AdjustForItem adjustForItem : items) {
            ReconcilicationForPayableItem item = payableItemMap.get(adjustForItem.getWaybillId());
            if( null == item ){ continue; }
            if (Integer.valueOf(PayableSettleAccountTypeEnum.DRIVER.getCode()).equals(item.getSettleType())) {
                log.warn("承运侧-对账后-不发消息原因:结算类型为司机,不向FMS发送消息推送");
                continue;
            }

            Waybill waybill =  waybillCommonService.getWaybillById(adjustForItem.getWaybillId());
            if( null == waybill ){
                log.error("承运侧-对账后-不发消息原因:没有查询到对账单对应的运单, 不向FMS发送消息");
                continue;
            }

            if (null == waybill.getVehicleToVendor()) {
                log.warn("承运侧-对账后-不发消息原因:运单对应的承运商VehicleToVendor为空,不向FMS发送消息");
                continue;
            }

            if (!Integer.valueOf(Waybill.ReceiveWay.TRANSFORM_BILL.getCode()).equals(waybill.getReceiveWay())) {
                Vendor vendor = vmsCommonService.loadVendorByVendorId(waybill.getVehicleToVendor());
                if( null == vendor){
                    log.warn("承运侧-对账后-不发消息原因:未查询到运单对应的承运商,不向FMS发送消息");
                    continue;
                }
                if(null == vendor.getVendorSource()){
                    log.warn("承运侧-对账后-不发消息原因:运单承运商来源为空,不向FMS发送消息");
                    continue;
                }
                if (vendor.getVendorSource().equals(VendorSourceEnum.SELF_SUPPORT.getCode())) {
                    log.warn("承运侧-对账后-不发消息原因:运单承运商为自营,不向FMS发送消息");
                    continue;
                }
            }else{
                Vendor vendor = vmsCommonService.loadVendorByVendorId(waybill.getVendorId());
                if( null == vendor){
                    log.warn("承运侧-对账后-不发消息原因:未查询到运单对应的承运商,不向FMS发送消息,运单编号:{},承运商ID:{}",waybill.getWaybillNo(),waybill.getVehicleToVendor());
                    continue;
                }
            }
            itemMap.put(adjustForItem.getWaybillNo(),adjustForItem);
            SourceLogisticsDetailDTO detailInfo = this.buildPayableSourceDetailInfo(item, adjustForItem, waybill, loginEmployee);
            // 物流项目
            detailInfo.setLogisticsName(payable.getProjectName());

            String mapKey = item.getSettleAccountId().toString();
            if (map.containsKey(mapKey)) {
                map.get(mapKey).add(detailInfo);
            } else {
                List<SourceLogisticsDetailDTO> detailList = new ArrayList<SourceLogisticsDetailDTO>();
                detailList.add(detailInfo);
                map.put(mapKey, detailList);
                vendorNameMap.put(mapKey, item.getSettleAccountName());
            }
        }
    }

    // 组装承运商信息
    private SourceLogisticsDTO buildPayableSourceMainInfo(ReconcilicationForPayable payable, LoginUser loginUser) {
        SourceLogisticsDTO dto = new SourceLogisticsDTO();
        dto.setTransactionalAccountType(TransactionalAccountTypeEnum.CARRIER.getCode());
        // 来源单类型
        dto.setSourceType(SourceTypeEnum.LOGISTICS_CARRIER_ADJUSTMENT_BILL.getCode());
        // 账单类型
        dto.setBillType(BillTypeEnum.STATEMENT_ADJUSTMENT_BILL.getCode());
        // 统计类型
        dto.setStatisticsType(payable.getProjectName());
        dto.setTenantId(payable.getTenantId());
        dto.setAreaCode(payable.getAreaCode());
        // 备注
        ReconcilicationForPayableLogExample example = new ReconcilicationForPayableLogExample();
        example.setOrderByClause("create_time desc");
        ReconcilicationForPayableLogExample.Criteria criteria = example.createCriteria();
        criteria.andReconcilicationIdEqualTo(payable.getReconcilicationId());
        List<ReconcilicationForPayableLog> logs = reconcilicationForPayableLogMapper.selectByExampleWithBLOBs(example);
        String str = "";
        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(logs)) {
            ReconciliationLog reconciliationLog = JSON.parseObject(logs.get(0).getContent(), ReconciliationLog.class);
            str = "子公司已由" + reconciliationLog.getOldText() + "更改为" + reconciliationLog.getNewText();
        }
        dto.setRemark(str);
        BusinessArea businessArea = authCommonService.loadBusinessArea(payable.getAreaCode(), loginUser);
        if (null != businessArea) {
            dto.setAreaName(businessArea.getAreaName());
        }

        dto.setUniformSocialCreditCode(payable.getPayToCompanyCreditCode());
        Integer departmentId = payable.getDepartmentId();
        dto.setSubCompanyId(departmentId);
        Department department = authCommonService.loadDepartment(departmentId);
        dto.setSubCompanyCode(department == null ? "" : department.getDepartmentCode());
        dto.setSubCompanyName(department == null ? "" :department.getBusinessLicenceName());
        return dto;
    }

    /**组装SourceLogisticsDetailDTO详情信息**/
    private SourceLogisticsDetailDTO buildPayableSourceDetailInfo(ReconcilicationForPayableItem item, AdjustForItem adjustForItem, Waybill waybill, LoginEmployee loginEmployee) {
        SourceLogisticsDetailDTO sourceDTO = new SourceLogisticsDetailDTO();
        // 车牌号
        sourceDTO.setPlateNumber(waybill.getPlateNumber());
        // 司机名称
        sourceDTO.setDriverName(waybill.getDriverName());
        // 司机类型
        sourceDTO.setDriverType(DriverTypeEnum.NOT_OWN_SALE.getDesc());

        // 赋值三个费用信息是保证waybillAmount表没有数据时的兼容
        // 客户运费
        sourceDTO.setCustomerFreight(BaseUtil.transferToCent(waybill.getEstimateFreight()).longValue());
        // 承运商运费
        sourceDTO.setVendorFreight(BaseUtil.transferToCent(waybill.getShow4DriverFreight()).longValue());
        // 不含税金额,单位分
        sourceDTO.setPriceExcludingTax(BaseUtil.transferToCent(waybill.getAfterTaxFreight()).longValue());

        // 用车时间
        sourceDTO.setUseCarTime(waybill.getPlanDeliveryTime());
        // 运单完成时间
        sourceDTO.setWaybillCompleteTime(waybill.getFinishTime());

        // 获取运单最新税率:修改后的税率/原税率
        BigDecimal newestWaybillTaxRate = fetchNewestWaybillTaxRate(loginEmployee, waybill);

        // 获取运单费用表信息，有数据则覆盖三个字段的取值
        WaybillAmount amount = waybillAmountService.loadByWaybillId(item.getWaybillId());
        if (null != amount) {
            // 客户运费
            sourceDTO.setCustomerFreight(BaseUtil.transferToCent(amount.getLastCustomerFreightWithTax()).longValue());
            // 承运商运费
            sourceDTO.setVendorFreight(BaseUtil.transferToCent(amount.getLastVendorFreightWithTax()).longValue());
        }

        // 调整金额组装数据
        BigDecimal priceIncludingTax = validAndSetBigDecimalValue(adjustForItem.getAdjustForFreight())
                .add(validAndSetBigDecimalValue(adjustForItem.getAdjustForCarry()))
                .add(validAndSetBigDecimalValue(adjustForItem.getAdjustForWorkload()))
                .add(validAndSetBigDecimalValue(adjustForItem.getAdjustForUpstairs()))
                .add(validAndSetBigDecimalValue(adjustForItem.getAdjustForFine()))
                .add(validAndSetBigDecimalValue(adjustForItem.getAdjustForCargoLoss()));

        sourceDTO.setPriceIncludingTax(BaseUtil.transferToCent(priceIncludingTax).longValue());

        // 调整未含税金额(调整幅度-不含税): 不含税金额,单位分
        BigDecimal priceExcludingTax = BaseUtil.calculateAmountWithoutTax(priceIncludingTax, newestWaybillTaxRate);
        sourceDTO.setPriceExcludingTax(priceExcludingTax.longValue());

        // 调整税金额(调整后应交的税)
        sourceDTO.setTax(BaseUtil.transferToCent(priceIncludingTax.subtract(priceExcludingTax)).longValue());
        // 运单最新税率
        sourceDTO.setTaxRate(newestWaybillTaxRate);

        // 业务类型
        sourceDTO.setSourceBizType(BusinessDetailTypeEnum.WAYBILL.getCode());
        // 运单号
        sourceDTO.setSourceBizNo(item.getWaybillNo());
        // 车架号
        sourceDTO.setVin(item.getVehicleFrameNo());

        return sourceDTO;
    }

    /**油卡、管理费、税费**/
    private List<SourceLogisticsDetailDTO> buildPayableExtraSourceDetailInfo(List<SourceLogisticsDetailDTO> byVendorList, Collection<ReconciliationExtraForPayable> payables, Map<String, AdjustForItem> itemMap, LoginUser loginUser) {
        List<SourceLogisticsDetailDTO> result = new ArrayList<SourceLogisticsDetailDTO>();
        // 基于承运商的调整金额求和
        BigDecimal vendorPriceIncludingTax = BigDecimal.ZERO;
        BigDecimal customerTaxRate = BigDecimal.ZERO;
        for (SourceLogisticsDetailDTO detailDTO : byVendorList){
            // 根据运单号获取数据
            AdjustForItem adjustForItem = itemMap.get(detailDTO.getSourceBizNo());
            if( null == adjustForItem ){ continue; }
            customerTaxRate = detailDTO.getTaxRate();
            // 调整金额组装数据
            BigDecimal priceIncludingTax = validAndSetBigDecimalValue(adjustForItem.getAdjustForFreight())
                    .add(validAndSetBigDecimalValue(adjustForItem.getAdjustForCarry()))
                    .add(validAndSetBigDecimalValue(adjustForItem.getAdjustForWorkload()))
                    .add(validAndSetBigDecimalValue(adjustForItem.getAdjustForUpstairs()))
                    .add(validAndSetBigDecimalValue(adjustForItem.getAdjustForFine()))
                    .add(validAndSetBigDecimalValue(adjustForItem.getAdjustForCargoLoss()));
            vendorPriceIncludingTax = vendorPriceIncludingTax.add(priceIncludingTax);
        }
        for (ReconciliationExtraForPayable extra : payables) {
            // 可抵扣进项税
            SourceLogisticsDetailDTO dtoTaxFee = new SourceLogisticsDetailDTO();
            dtoTaxFee.setSourceBizType(BusinessDetailTypeEnum.TAX.getCode());

            if( BigDecimal.valueOf(0).compareTo(extra.getTaxRate()) == 0 ){
                dtoTaxFee.setPriceIncludingTax(BigDecimal.ZERO.longValue());
                result.add(dtoTaxFee);
                continue;
            }

            // 开票:【调整金额  ÷（1+承运商开票税率）】—【调整金额  ÷（1+客户侧税率）】
            BigDecimal priceIncludingTax = BigDecimal.ZERO;
            if( extra.getIsInvoice() && BigDecimal.valueOf(-1).compareTo(extra.getVendorTaxRate()) != 0 ){
                priceIncludingTax = vendorPriceIncludingTax.divide(BigDecimal.ONE.add(extra.getVendorTaxRate()),2, RoundingMode.HALF_UP)
                        .subtract(
                                vendorPriceIncludingTax.divide(BigDecimal.ONE.add(customerTaxRate),2, RoundingMode.HALF_UP)
                        );
            }else{
                // 不开票: 调整金额 — 【调整金额 ÷（ 1+进项税率）】
                priceIncludingTax = vendorPriceIncludingTax
                        .subtract(vendorPriceIncludingTax.divide(BigDecimal.ONE.add(extra.getTaxRate()),2, RoundingMode.HALF_UP));
            }

            dtoTaxFee.setPriceIncludingTax(priceIncludingTax.multiply(BigDecimal.valueOf(-100L)).longValue());
            result.add(dtoTaxFee);
        }

        return result;
    }

    /**填充对账单主表信息**/
    private void fillReceiveMainSourceInfo(final ReconcilicationForReceivable receivable, final SourceLogisticsDTO sourceLogisticsDTO) {
        // 常量信息
        sourceLogisticsDTO.setSourceType(SourceTypeEnum.LOGISTICS_SHIPPER_ADJUSTMENT_BILL.getCode());
        sourceLogisticsDTO.setBillType(BillTypeEnum.STATEMENT_ADJUSTMENT_BILL.getCode());
        sourceLogisticsDTO.setTransactionalAccountType(TransactionalAccountTypeEnum.OWNER.getCode());

        // 对账单主表信息
        sourceLogisticsDTO.setSourceNoteNo(receivable.getReconcilicationNo());
        // 调整单不传递sourceBizNo字段
        sourceLogisticsDTO.setTenantId(receivable.getTenantId());
        sourceLogisticsDTO.setAreaCode(receivable.getAreaCode());
        // 转化为crm客户id
        Integer crmCustomerId = null;
        if (receivable.getCustomerId() != null) {
            CustomerInfo customerInfo = customerInfoService.findCusInfoById(receivable.getCustomerId());
            if (customerInfo != null) {
                crmCustomerId = customerInfo.getCrmCustomerId();
            }
        }

        sourceLogisticsDTO.setTransactionalAccountNo(String.valueOf(crmCustomerId));
        sourceLogisticsDTO.setTransactionalAccountName(receivable.getCustomerName());

        sourceLogisticsDTO.setEntityId(getCustomerJumaPin(crmCustomerId));
        // sourceLogisticsDTO.setSourceNoteDate(master.getSubmitTime());
    }

    private List<SourceLogisticsDetailDTO> buildReceiveDetailInfo(AdjustForMaster master, SourceLogisticsDTO sourceLogisticsDTO, Integer reconciliationId, LoginEmployee loginEmployee) {

        List<SourceLogisticsDetailDTO> detailDTOS = new ArrayList<>();

        AdjustForItemFilter filter = new AdjustForItemFilter();
        filter.setAdjustId(master.getAdjustId());
        List<AdjustForItem> items = findItemByFilter(filter,loginEmployee);

        // 子公司id
        Integer departmentId = null;
        String contractToCompanyCreditCode = null;
            String projectName = "";
        for (AdjustForItem item : items) {
            Waybill waybill = waybillCommonService.getWaybillById(item.getWaybillId());
            contractToCompanyCreditCode = waybill.getContractToCompanyCreditCode();
            departmentId = waybill.getDepartmentId();
            projectName = waybill.getProjectName();

            SourceLogisticsDetailDTO detailDTO = new SourceLogisticsDetailDTO();
            // 车牌号
            detailDTO.setPlateNumber(waybill.getPlateNumber());
            // 司机名称
            detailDTO.setDriverName(waybill.getDriverName());
            // 司机类型
            detailDTO.setDriverType(waybill.getDriverType() == null ? "" : DriverTypeEnum.getDescByCode(waybill.getDriverType()));
            // 查询车架号
            String vehicleFrameNo = fetchVehicleFrameNo(waybill);
            detailDTO.setVin(vehicleFrameNo);
            detailDTO.setSourceBizType(BusinessDetailTypeEnum.WAYBILL.getCode());
            detailDTO.setSourceBizNo(waybill.getWaybillNo());
            detailDTO.setUseCarTime(waybill.getPlanDeliveryTime());
            detailDTO.setWaybillCompleteTime(waybill.getFinishTime());
            detailDTO.setLogisticsName(waybill.getProjectName());

            // 客户运费
            detailDTO.setCustomerFreight(BaseUtil.transferToCent(waybill.getEstimateFreight()).longValue());
            // 承运商运费
            detailDTO.setVendorFreight(BaseUtil.transferToCent(waybill.getShow4DriverFreight()).longValue());

            WaybillAmount amount = waybillAmountService.loadByWaybillId(waybill.getWaybillId());
            if( null != amount ){
                // 客户运费
                detailDTO.setCustomerFreight(BaseUtil.transferToCent(amount.getLastCustomerFreightWithTax()).longValue());
                // 承运商运费
                detailDTO.setVendorFreight(BaseUtil.transferToCent(amount.getLastVendorFreightWithTax()).longValue());
            }

            // 以下为和金额计算相关的逻辑

            // 常识:
            // 常识公式: 税后价 = 税前价 (1 - 税率)
            // ======>: 税前价 = 税后价 / (1 - 税率)

            // 反常识:
            // 财务公式: 税前价 = 税后价 (1 + 税率)
            // ======>: 税后价 = 税前价 / (1 + 税率)

            // 获取运单最新的含税价:修改后的含税价/原价
            BigDecimal newestWaybillAmountWithTax = validAndSetBigDecimalValue(item.getAdjustForFreight())
                    .add(validAndSetBigDecimalValue(item.getAdjustForCarry()))
                    .add(validAndSetBigDecimalValue(item.getAdjustForWorkload()))
                    .add(validAndSetBigDecimalValue(item.getAdjustForUpstairs()))
                    .add(validAndSetBigDecimalValue(item.getAdjustForFine()))
                    .add(validAndSetBigDecimalValue(item.getAdjustForCargoLoss()));

            // 原始含税价
//            BigDecimal originWaybillAmountWithTax = waybill.getEstimateFreight();

            // 获取运单最新税率:修改后的税率/原税率
            BigDecimal newestWaybillTaxRate = fetchNewestWaybillTaxRate(loginEmployee, waybill);

            // 调整金额: 运单最新的含税价 - 原始含税价
//            long adjustAmount = BaseUtil.transferToCent(newestWaybillAmountWithTax).subtract(
//                            BaseUtil.transferToCent(originWaybillAmountWithTax)).longValue();
            // 调整不含税金额: 运单最新的含税价/(1 + 运单最新税率)
            long adjustAmountWithoutTax = BaseUtil.calculateAmountWithoutTax(newestWaybillAmountWithTax,newestWaybillTaxRate).longValue();
            // 调整税金额: 运单最新的含税价 - 调整不含税金额
            long taxAmount = BaseUtil.transferToCent(newestWaybillAmountWithTax).subtract(new BigDecimal(adjustAmountWithoutTax)).longValue();

            // 原始含税价
//            detailDTO.setBeforePriceIncludingTax(BaseUtil.transferToCent(originWaybillAmountWithTax).longValue());
            // 调整金额(调整幅度)
//            detailDTO.setAdjustmentPrice(adjustAmount);
            // 调整未含税金额(调整幅度-不含税)
            detailDTO.setPriceExcludingTax(adjustAmountWithoutTax);
            // 运单最新的含税价(调整后价格)
            detailDTO.setPriceIncludingTax(BaseUtil.transferToCent(newestWaybillAmountWithTax).longValue());
            // 调整税金额(调整后应交的税)
            detailDTO.setTax(taxAmount);
            // 运单最新税率
            detailDTO.setTaxRate(newestWaybillTaxRate);

            detailDTOS.add(detailDTO);
        }

        sourceLogisticsDTO.setUniformSocialCreditCode(contractToCompanyCreditCode);
        // 设置子公司信息
        if (departmentId != null) {
            sourceLogisticsDTO.setSubCompanyId(departmentId);
            Department department = authCommonService.loadDepartment(departmentId);
            sourceLogisticsDTO.setSubCompanyCode(department == null ? "" : department.getDepartmentCode());
            sourceLogisticsDTO.setSubCompanyName(department == null ? "" : department.getBusinessLicenceName());
        }

        sourceLogisticsDTO.setStatisticsType(projectName);

        return detailDTOS;
    }

    /**获取运单最新税率:修改后的税率/原税率**/
    private BigDecimal fetchNewestWaybillTaxRate(final LoginEmployee loginEmployee, final Waybill waybill) {
        TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybill.getWaybillId(), loginEmployee);
        BigDecimal taxRateAdjust = BigDecimal.ZERO;
        if (truckRequire != null) {
            taxRateAdjust = truckRequire.getTaxRateValue();
        }
        return taxRateAdjust;
    }

    /**查询车架号**/
    private String fetchVehicleFrameNo(final Waybill waybill) {
        String vehicleFrameNo = "";
        if (StringUtils.isNotBlank(waybill.getPlateNumber())) {
            Truck truck = vmsCommonService.loadTruckByPlateNumber(waybill.getPlateNumber());
            if (truck != null) {
                vehicleFrameNo = truck.getTruckIdentificationNo();
            }
        }
        return vehicleFrameNo;
    }

    private String getCustomerJumaPin(Integer crmCustomerId) {
        if (crmCustomerId == null) return null;
        com.juma.crm.customer.domain.CustomerInfo crmCustomerInfo = null;
        try {
            crmCustomerInfo = crmService.find(crmCustomerId, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return crmCustomerInfo == null ? null : crmCustomerInfo.getJumaPin();
    }

    /**保存运单费用表**/
    private void addWaybillAmount(AdjustForMasterAddVO vo, AdjustForMaster master, List<AdjustForWaybillTemp> temps, LoginUser loginUser) {
        List<Integer> waybillIds = Lists.newArrayList();
        for (AdjustForWaybillTemp temp : temps) {
            waybillIds.add(temp.getWaybillId());
        }
        if( CollectionUtils.isEmpty(waybillIds) ){ return; }
        WaybillAmountFilter filter = new WaybillAmountFilter();
        filter.setWaybillIds(waybillIds);
        List<WaybillAmount> oldAmounts = waybillAmountService.findByFilter(filter,loginUser);
        Map<Integer,WaybillAmount> amountMap = Maps.newConcurrentMap();
        for (WaybillAmount amount : oldAmounts){
            amountMap.put(amount.getWaybillId(),amount);
        }
        List<Waybill> waybills = waybillService.findByWaybillIds(waybillIds);
        Map<Integer, Waybill> waybillMap = Maps.newConcurrentMap();
        for (Waybill waybill : waybills){
            waybillMap.put(waybill.getWaybillId(),waybill);
        }
        List<WaybillAmount> amounts = Lists.newArrayList();
        for (AdjustForWaybillTemp temp : temps) {
            WaybillAmount amount = amountMap.get(temp.getWaybillId());
            // 更新不在这里发生
            if( null != amount ){ continue; }
            Waybill waybill = waybillMap.get(temp.getWaybillId());
            if( null == waybill ){ continue; }
            amount = new WaybillAmount();
            amount.setCustomerFreightWithTax(waybill.getEstimateFreight());
            amount.setVendorFreightWithTax(waybill.getShow4DriverFreight());
            amount.setLastCustomerFreightWithTax(waybill.getEstimateFreight());
            amount.setLastVendorFreightWithTax(waybill.getShow4DriverFreight());
            amount.setWaybillId(temp.getWaybillId());
            amounts.add(amount);
        }
        // 仅仅在运单费用表中,没有该运单数据的时候,用来初始化
        waybillAmountService.addOrUpdateBatch(amounts,loginUser);
    }

    /**提交工作流**/
    private void executeWorkflow(AdjustForMasterAddVO vo, AdjustForMaster master, List<AdjustForWaybillTemp> temps, LoginEmployee loginUser) {
        // 编辑逻辑 走重新申请
        String processDefinitionKey = "";

        //应收调整
        if( AdjustMasterType.CUSTOMER.getCode().equals(master.getAdjustForWho())){
            processDefinitionKey = WorkflowConstants.PROCESS_KEY_ADJUST_RECEIVE_CREATE;
        }
        //应付调整
        if( AdjustMasterType.VENDOR.getCode().equals(master.getAdjustForWho())){
            processDefinitionKey = WorkflowConstants.PROCESS_KEY_ADJUST_PAYABLE_CREATE;
        }

        Map<String,Object> variables = Maps.newConcurrentMap();
        if( AdjustType.BEFORE.getCode().equals(vo.getAdjustType()) ){
            variables.put("adjustType","before");
        }
        if( AdjustType.AFTER.getCode().equals(vo.getAdjustType()) ){
            variables.put("adjustType","after");
            WaybillStatisticsAmountVO waybillStatisticsAmountVO = new WaybillStatisticsAmountVO();
            BigDecimal profitRate = fetchProjectRate(temps);
            if( null != profitRate ){
                waybillStatisticsAmountVO.setProfitRate(profitRate);
            }else{
                waybillStatisticsAmountVO.setProfitRate(BigDecimal.ZERO);
            }

			BigDecimal profitRateDiffer = null;
            if(AdjustMasterType.CUSTOMER.getCode().equals(master.getAdjustForWho())){
                /**计算客户侧毛利率**/
                adjustForMasterService.calculateCustomerProportion(master,waybillStatisticsAmountVO);
                Double rateGap = fetchPercentage(waybillStatisticsAmountVO.getAfterProportionGap());
                if( null != rateGap ){
                    profitRateDiffer = BigDecimal.valueOf(rateGap);
                }
            }else if(AdjustMasterType.VENDOR.getCode().equals(master.getAdjustForWho())){
                /**计算承运侧毛利率**/
                adjustForMasterService.calculateVendorProportion(master,waybillStatisticsAmountVO);
                Double rateGap = fetchPercentage(waybillStatisticsAmountVO.getAfterProportionGap());
                if( null != rateGap ){
                    profitRateDiffer = BigDecimal.valueOf(rateGap);
                }
            }
            if( null == profitRate || profitRateDiffer == null || StringUtils.isBlank(waybillStatisticsAmountVO.getAfterProportionGap())){
                profitRateDiffer = BigDecimal.valueOf(-5);
            }
            profitRateDiffer = profitRateDiffer.multiply(new BigDecimal(100.00));
			variables.put("profitRateDiffer", profitRateDiffer);
        }
        if(null != vo.getAdjustId()){
            workflowCommonService.reopenWorkflow(ExecuteWorkflowInfo.builder()
                    .workflowDesc("调整单驳回后重新发起审批")
                    .processDefinitionKey(processDefinitionKey)
                    .businessKey(master.getAdjustId().toString())
                    .number(master.getAdjustNo())
                    .variables(variables)
                    .build(),loginUser);
        }else{
            // 新建逻辑, 走创建工作流
            ProcessInstance instance = workflowCommonService.startWorkflow(ExecuteWorkflowInfo.builder()
                    .workflowDesc("调整单创建审批流")
                    .processDefinitionKey(processDefinitionKey)
                    .businessKey(master.getAdjustId().toString())
                    .number(master.getAdjustNo())
                    .variables(variables)
                    .build(),loginUser);
            master.setProcessInstanceId(instance.getProcessInstanceId());
        }
    }

    private BigDecimal fetchProjectRate(List<AdjustForWaybillTemp> temps) {
        Waybill waybill = waybillService.getWaybill(temps.get(0).getWaybillId());
        // 没有项目的运单,不做项目相关的计算
        if( null != waybill && null == waybill.getProjectId() ){ return null; }
        // 项目承诺毛利
        BigDecimal projectProfitRate = BigDecimal.ZERO;
        Project project = projectService.getProjectV2(waybill.getProjectId());
        if( null == project ){ return null; }
        if( null == project.getProfitRate() ){ return null; }
        return project.getProfitRate();
    }

    private Double fetchPercentage(String key) {
        if( StringUtils.isBlank(key) ){
            return null;
        }
        try {
            NumberFormat nf= NumberFormat.getPercentInstance();
            Number optionAmount = nf.parse(key);
            return optionAmount.doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isOwnVendor(Waybill waybill) {
        if(null == waybill){
            return false;
        }
        Vendor vendor = null;
        if (!waybill.getReceiveWay().equals(Waybill.ReceiveWay.TRANSFORM_BILL.getCode())) {
            vendor = vmsCommonService.loadVendorByVendorId(waybill.getVehicleToVendor());
        }else{
            vendor = vmsCommonService.loadVendorByVendorId(waybill.getVendorId());
        }
        if (null != vendor) {
            if (Objects.equals(VendorSourceEnum.SELF_SUPPORT.getCode(), vendor.getVendorSource())) {
                return true;
            }
        }
        return false;
    }

    /**最后单独更新主表信息**/
    private void updateAdjustForMaster(AdjustForMaster master, AdjustItemValidHolder holder) {
        //1.工作流ID,保存
        //2.业务范围,保存
        if( !CollectionUtils.isEmpty(holder.getAreaCodes()) ){
            master.setAreaCode(holder.getAreaCodes().iterator().next());
        }
        adjustForMasterMapper.updateByPrimaryKeySelective(master);
    }

    /**获取承运商ID集合**/
    private List<String> fetchVendorIds(AdjustForMaster master, LoginEmployee loginEmployee) {
        AdjustForItemFilter filter = new AdjustForItemFilter();
        filter.setAdjustId(master.getAdjustId());
        List<AdjustForItem> items = findItemByFilter(filter,loginEmployee);
        List<String> vendorIds = Lists.newArrayList();
        for (AdjustForItem item : items){
            vendorIds.add(String.valueOf(item.getVendorId()));
        }
        return vendorIds;
    }

    /**添加调整单附件**/
    private void addAdjustAttach(AdjustForMasterAddVO vo, AdjustForMaster master, LoginUser loginUser) {
        AdjustForFreightAttach attach = new AdjustForFreightAttach();
        attach.setAdjustId(master.getAdjustId());
        attach.setCarryProofAttach(vo.getCarryProofAttach());
        attach.setWorkloadProofAttach(vo.getWorkloadProofAttach());
        attach.setUpstairsProofAttach(vo.getUpstairsProofAttach());
        attach.setFineProofAttach(vo.getFineProofAttach());
        attach.setCargoLossProofAttach(vo.getCargoLossProofAttach());
        attach.setCreateUserId(loginUser.getUserId());
        attach.setCreateTime(new Date());
        // 传入主键不为空 走更新逻辑
        if( null != vo.getAdjustId() ){
            AdjustForFreightAttachExample example = new AdjustForFreightAttachExample().createCriteria()
                    .andAdjustIdEqualTo(vo.getAdjustId())
                    .example();
            List<AdjustForFreightAttach> attaches = adjustForFreightAttachMapper.selectByExample(example);
            for (AdjustForFreightAttach attach1 : attaches){
                attach.setAttachId(attach1.getAttachId());
                adjustForFreightAttachMapper.updateByPrimaryKeySelective(attach);
            }
            return;
        }
        // 传入主键为空 走新增逻辑
        adjustForFreightAttachMapper.insert(attach);
    }

    /**添加调整单明细**/
    private void addAdjustForItem(AdjustForMasterAddVO vo, AdjustForMaster master, List<AdjustForWaybillTemp> temps, LoginEmployee loginUser) {
        List<AdjustForItem> items = Lists.newArrayList();
        for (AdjustForWaybillTemp temp : temps) {
            temp.setAdjustId(master.getAdjustId());
            AdjustForItem item = new AdjustForItem();
            BeanUtils.copyProperties(temp,item);
            item.setCreateUserId(loginUser.getUserId());
            item.setCreateTime(new Date());
            items.add(item);
        }
        // 传入有主键 走编辑更新逻辑 删除全部, 再批量插入
        if( null != vo.getAdjustId() ){
            AdjustForItemExample example = new AdjustForItemExample().createCriteria()
                    .andAdjustIdEqualTo(vo.getAdjustId())
                    .example();
            List<AdjustForItem> oldItems = adjustForItemMapper.selectByExample(example);
            for (AdjustForItem item : oldItems){
                adjustForItemMapper.deleteByPrimaryKey(item.getAdjustItemId());
            }
        }
        // 传入无主键 走新增逻辑
        adjustForItemMapper.insertBatch(items);

        if( null == vo.getAdjustId() ){
            // 保存完毕之后, 更新临时表adjustId字段
            adjustForWaybillService.addTempBatch(master.getAdjustId(), temps, loginUser);
        }
    }

    /**添加主表信息**/
    private AdjustForMaster addAdjustForMaster(AdjustForMasterAddVO vo, AdjustItemValidHolder holder, List<AdjustForWaybillTemp> temps, LoginUser loginUser) {
        AdjustForMaster master = new AdjustForMaster();
        master.setAdjustId(vo.getAdjustId());
        master.setAdjustNo(vo.getAdjustNo());
        master.setAdjustType(vo.getAdjustType());
        master.setAdjustForWho(vo.getAdjustForWho());
        if( null == vo.getAdjustId() ){
            master.setTenantId(vo.getTenantId());
            master.setAdjustNo(idGeneratorService.genAdjustFormMasterNo(IdGeneratorTable.IdType.TZD));
            master.setTenantId(loginUser.getTenantId());
            master.setTenantCode(loginUser.getTenantCode());
            master.setAreaCode("");
            master.setProcessInstanceId("");
            master.setCreateUserId(loginUser.getUserId());
            master.setCreateUserName(loginUser.getUserName());
            master.setCreateTime(new Date());
            master.setApprovalTime(new Date(0));
            master.setIsDelete(0);
        }
        master.setAdjustForReason(vo.getAdjustForReason());
        master.setApprovalStatus(ApprovalStatus.APPROVAL_SUBMIT.getCode());
        if(holder.getReconciliations().size() > 0){
            master.setReconcilicationNo(holder.getReconciliations().iterator().next());
        }else{
            master.setReconcilicationNo("");
        }

        // 计算金额逻辑
        BigDecimal totalAdjustAmount = BigDecimal.ZERO;
        BigDecimal totalAdjustAbsAmount = BigDecimal.ZERO;
        // 调整后的总金额
        BigDecimal totalAfterAdjustAmount = BigDecimal.ZERO;
        // 调整时固化金额-调整前含税-不含自营
        BigDecimal beforeWithoutSelfAmount = BigDecimal.ZERO;
        // 调整时固化金额-调整前含税-含自营
        BigDecimal beforeWithSelfAmount = BigDecimal.ZERO;
        // 调整时固化金额-调整前另一侧含税-不含自营
        BigDecimal otherSideBeforeWithoutSelfAmount = BigDecimal.ZERO;
        // 调整时固化金额-调整前另一侧含税-含自营
        BigDecimal otherSideBeforeWithSelfAmount = BigDecimal.ZERO;
        for (AdjustForWaybillTemp temp : temps) {
            Vendor vendor = vmsCommonService.loadVendorByVendorId(temp.getVendorId());
            //非营承运商
            if( null != vendor && !Byte.valueOf(VendorSourceEnum.SELF_SUPPORT.getCode()).equals(vendor.getVendorSource()) ){
                beforeWithoutSelfAmount = beforeWithoutSelfAmount.add(temp.getFreightWithTax());
                otherSideBeforeWithoutSelfAmount = otherSideBeforeWithoutSelfAmount.add(temp.getOtherSideWithTax());
            }
            beforeWithSelfAmount = beforeWithSelfAmount.add(temp.getFreightWithTax());
            otherSideBeforeWithSelfAmount = otherSideBeforeWithSelfAmount.add(temp.getOtherSideWithTax());

            totalAdjustAmount = totalAdjustAmount.add(validAndSetBigDecimalValue(temp.getAdjustForFreight()))
                    .add(validAndSetBigDecimalValue(temp.getAdjustForCarry()))
                    .add(validAndSetBigDecimalValue(temp.getAdjustForWorkload()))
                    .add(validAndSetBigDecimalValue(temp.getAdjustForUpstairs()))
                    .add(validAndSetBigDecimalValue(temp.getAdjustForFine()))
                    .add(validAndSetBigDecimalValue(temp.getAdjustForCargoLoss()));
            totalAfterAdjustAmount = totalAdjustAmount.add(temp.getFreightWithTax());
            totalAdjustAbsAmount = totalAdjustAbsAmount.add(validAndSetBigDecimalValue(temp.getAdjustForFreight()).abs()
                    .add(validAndSetBigDecimalValue(temp.getAdjustForCarry()).abs())
                    .add(validAndSetBigDecimalValue(temp.getAdjustForWorkload()).abs())
                    .add(validAndSetBigDecimalValue(temp.getAdjustForUpstairs()).abs())
                    .add(validAndSetBigDecimalValue(temp.getAdjustForFine()).abs())
                    .add(validAndSetBigDecimalValue(temp.getAdjustForCargoLoss()).abs()));
        }

        master.setAdjustAmount(totalAdjustAmount);
        master.setAdjustAbsAmount(totalAdjustAbsAmount);
        // 固化调整单创建时的值
        master.setBeforeAdjustWithselfAmount(beforeWithSelfAmount);
        master.setBeforeAdjustAmount(beforeWithoutSelfAmount);
        master.setOtherSideWithselfAmount(otherSideBeforeWithSelfAmount);
        master.setOtherSideAmount(otherSideBeforeWithoutSelfAmount);
        // 有主键 则更新
        if( null != vo.getAdjustId() ){
            adjustForMasterMapper.updateByPrimaryKeySelective(master);
            return master;
        }
        // 无主键 则新增
        adjustForMasterMapper.insert(master);
        return master;
    }

    private AdjustForMaster getAdjustForMasterById(WorkFlowMessage message) {
        return selectByPrimaryKey(Integer.parseInt(message.getBusinessKey()));
    }

    private BigDecimal validAndSetBigDecimalValue(BigDecimal amount) {
        return null != amount ? amount : BigDecimal.ZERO;
    }

    /**标记删除调整单**/
    private void markDeleteMaster(final AdjustForMaster master) {
        master.setIsDelete(1);
        adjustForMasterMapper.updateByPrimaryKeySelective(master);
    }

    /**更新对账明细结算运费**/
    private void freshReconciliationForPayableSettleFreight(AdjustForMaster master, LoginEmployee loginEmployee) {
        ReconcilicationForPayable payable = reconcilicationForPayableService.findByReconciliationNo(master.getReconcilicationNo());
        AdjustForItemExample example = new AdjustForItemExample().createCriteria()
                .andAdjustIdEqualTo(master.getAdjustId())
                .example();
        List<AdjustForItem> items = adjustForItemMapper.selectByExample(example);
        if( CollectionUtils.isEmpty(items) ){ return; }
        List<Integer> waybillIds = Lists.newArrayList();
        for (AdjustForItem item : items) {
            waybillIds.add(item.getWaybillId());
        }

        WaybillAmountFilter amountFilter = new WaybillAmountFilter();
        amountFilter.setWaybillIds(waybillIds);
        List<WaybillAmount> amounts = waybillAmountService.findByFilter(amountFilter,loginEmployee);
        if( CollectionUtils.isEmpty(amounts) ){ return; }
        Map<Integer,WaybillAmount> itemMap = Maps.newConcurrentMap();
        for (WaybillAmount amount : amounts) {
            itemMap.put(amount.getWaybillId(),amount);
        }

        ReconciliationPayableItemFilter filter = new ReconciliationPayableItemFilter();
        filter.setReconcilicationId(payable.getReconcilicationId());
        filter.setWaybillIds(waybillIds);
        List<ReconcilicationForPayableItem> payableItems = reconcilicationForPayableService.findItemByFilter(filter,loginEmployee);
        if( CollectionUtils.isEmpty(payableItems) ){ return; }
        for (ReconcilicationForPayableItem item : payableItems) {
            WaybillAmount waybillAmount = itemMap.get(item.getReconcilicationId());
            if( null == waybillAmount ){ continue; }
            reconcilicationForPayableService.updateSettleFreight(item.getReconcilicationId(),master.getAdjustForWho(),waybillAmount);
        }
    }

    private void buildPayableBasic(Waybill waybill, SourceLogisticsDTO dto) {
        // 月账单号uuid
        dto.setSourceNoteNo(String.valueOf(System.nanoTime()));
        // 往来户编号/往来户名称(承运商id/名称)
        if (Waybill.ReceiveWay.TRANSFORM_BILL.getCode() == waybill.getReceiveWay()) {
            if (null != waybill.getVendorId()) {
                dto.setTransactionalAccountNo(String.valueOf(waybill.getVendorId()));
                Vendor vendor = vmsCommonService.loadVendorByVendorId(waybill.getVendorId());
                if (null != vendor) {
                    dto.setTransactionalAccountName(vendor.getVendorName());
                    dto.setEntityId(vendor.getJumaPin());
                }
            }
        } else {
            if (null != waybill.getVehicleToVendor()) {
                dto.setTransactionalAccountNo(String.valueOf(waybill.getVehicleToVendor()));
                Vendor vendor = vmsCommonService.loadVendorByVendorId(waybill.getVehicleToVendor());
                if (null != vendor) {
                    dto.setTransactionalAccountName(vendor.getVendorName());
                    dto.setEntityId(vendor.getJumaPin());
                }
            }
        }
        // 往来户类型
        dto.setTransactionalAccountType(TransactionalAccountTypeEnum.CARRIER.getCode());
        // 账单类型
        dto.setBillType(BillTypeEnum.MONTHLY_ADJUSTMENT_BILL.getCode());
        // 来源单类型
        dto.setSourceType(SourceTypeEnum.LOGISTICS_CARRIER_ADJUSTMENT_BILL.getCode());
        // 统计类型
        dto.setStatisticsType(waybill.getProjectName());

        dto.setDetailList(new ArrayList<SourceLogisticsDetailDTO>());

        // 租户Id
        if (null != waybill.getTenantId()) {
            dto.setTenantId(waybill.getTenantId());
        }
        // 业务区域
        if (StringUtils.isNotBlank(waybill.getAreaCode())) {
            dto.setAreaCode(waybill.getAreaCode());
        }

        dto.setUniformSocialCreditCode(waybill.getPayToCompanyCreditCode());
        if (null != waybill.getPayToCompany()) {
            // 子公司编码（运营主体）
            Integer departmentId = waybill.getPayToCompany();
            dto.setSubCompanyId(departmentId);
            Department department = authCommonService.loadDepartment(departmentId);
            dto.setSubCompanyCode(department == null ? "" : department.getDepartmentCode());
            dto.setSubCompanyName(department == null ? "" :department.getBusinessLicenceName());
        }
    }

    private SourceLogisticsDetailDTO buildPayableDetail(AdjustForMaster master, Waybill waybill) {
        SourceLogisticsDetailDTO sourceDTO = new SourceLogisticsDetailDTO();

        // 车牌号
        sourceDTO.setPlateNumber(waybill.getPlateNumber());
        // 司机名称
        sourceDTO.setDriverName(waybill.getDriverName());
        // 司机类型
        sourceDTO.setDriverType(waybill.getDriverType() == null ? "" : DriverTypeEnum.getDescByCode(waybill.getDriverType()));

        // 客户运费
        sourceDTO.setCustomerFreight(BaseUtil.transferToCent(waybill.getEstimateFreight()).longValue());
        // 承运商运费
        sourceDTO.setVendorFreight(BaseUtil.transferToCent(waybill.getShow4DriverFreight()).longValue());

        WaybillAmount amount = waybillAmountService.loadByWaybillId(waybill.getWaybillId());
        if( null != amount ){
            // 客户运费
            sourceDTO.setCustomerFreight(BaseUtil.transferToCent(amount.getLastCustomerFreightWithTax()).longValue());
            // 承运商运费
            sourceDTO.setVendorFreight(BaseUtil.transferToCent(amount.getLastVendorFreightWithTax()).longValue());
        }

        // 业务类型
        sourceDTO.setSourceBizType(BusinessDetailTypeEnum.WAYBILL.getCode());
        // 运单号
        sourceDTO.setSourceBizNo(waybill.getWaybillNo());
        // 用车时间
        sourceDTO.setUseCarTime(waybill.getPlanDeliveryTime());
        // 运单完成时间
        sourceDTO.setWaybillCompleteTime(waybill.getFinishTime());
        // 物流项目
        sourceDTO.setLogisticsName(waybill.getProjectName());

        TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybill.getWaybillId(),null);
        BigDecimal taxRateValue = null;
        if(null != truckRequire){
            taxRateValue = truckRequire.getTaxRateValue();
        }
        //调整单金额
        AdjustForItem adjustForItem = new AdjustForItem();
        adjustForItem.setWaybillId(waybill.getWaybillId());
        adjustForItem.setAdjustId(master.getAdjustId());
        List<AdjustForItem> adjustForItemList = adjustForItemService.getByFilter(adjustForItem);
        BigDecimal sum = BigDecimal.valueOf(0);
        for (AdjustForItem item : adjustForItemList){
            sum = sum.add(item.getAdjustForFreight())
                    .add(item.getAdjustForCarry())
                    .add(item.getAdjustForWorkload())
                    .add(item.getAdjustForUpstairs())
                    .add(item.getAdjustForFine())
                    .add(item.getAdjustForCargoLoss());
        }
        sourceDTO.setPriceIncludingTax(sum.multiply(BigDecimal.valueOf(100)).longValue());
        BigDecimal priceIncludingTax = BaseUtil.calFreightWithNotTax(sum, taxRateValue);
        sourceDTO.setPriceExcludingTax(priceIncludingTax.multiply(BigDecimal.valueOf(100)).longValue());

        // 税,单位分
        sourceDTO.setTax(0L);
        // 税率,如0.1表示10%
        sourceDTO.setTaxRate(BigDecimal.ZERO);
        // 车架号
        Truck truck = vmsCommonService.loadTruckByTruckId(waybill.getTruckId());
        sourceDTO.setVin(truck == null ? null : truck.getTruckIdentificationNo());
        if (null != truck) {
            sourceDTO.setTruckType(truck.getTruckRunType() == null ? "" : TruckRunTypeEnum.getDescByCode(truck.getTruckRunType()));
        }
        return sourceDTO;
    }

    private void buildReceivableBasic(Waybill waybill, SourceLogisticsDTO dto) {
        // 月账单号uuid
        dto.setSourceNoteNo(String.valueOf(System.nanoTime()));
        // 往来户编号/往来户名称(承运商id/名称)
        CustomerInfo customerInfo = customerInfoService.findCusInfoById(waybill.getCustomerId());
        if (null != customerInfo) {
            dto.setTransactionalAccountNo(customerInfo.getCrmCustomerId().toString());
            // juma pin
            dto.setEntityId(getCustomerJumaPin(customerInfo.getCrmCustomerId()));
        }
        dto.setTransactionalAccountName(waybill.getCustomerName());
        // 往来户类型
        dto.setTransactionalAccountType(TransactionalAccountTypeEnum.OWNER.getCode());
        // 账单类型
        dto.setBillType(BillTypeEnum.MONTHLY_ADJUSTMENT_BILL.getCode());
        // 来源单类型
        dto.setSourceType(SourceTypeEnum.LOGISTICS_SHIPPER_ADJUSTMENT_BILL.getCode());
        // 统计类型
        dto.setStatisticsType(waybill.getProjectName());

        dto.setDetailList(new ArrayList<SourceLogisticsDetailDTO>());
        // 租户Id
        dto.setTenantId(waybill.getTenantId());
        // 业务区域
        dto.setAreaCode(waybill.getAreaCode());

        dto.setUniformSocialCreditCode(waybill.getContractToCompanyCreditCode());
        if (null != waybill.getDepartmentId()) {
            // 子公司编码（签约主体）
            Integer departmentId = waybill.getDepartmentId();
            dto.setSubCompanyId(departmentId);
            Department department = authCommonService.loadDepartment(departmentId);
            dto.setSubCompanyCode(department == null ? "" : department.getDepartmentCode());
            dto.setSubCompanyName(department == null ? "" :department.getBusinessLicenceName());
        }
    }

    private SourceLogisticsDetailDTO buildReceivableDetail(AdjustForMaster master, Waybill waybill) {
        SourceLogisticsDetailDTO sourceDTO = new SourceLogisticsDetailDTO();

        // 车牌号
        sourceDTO.setPlateNumber(waybill.getPlateNumber());
        // 司机名称
        sourceDTO.setDriverName(waybill.getDriverName());
        // 司机类型
        sourceDTO.setDriverType(
                waybill.getDriverType() == null ? "" : DriverTypeEnum.getDescByCode(waybill.getDriverType()));

        // 客户运费
        sourceDTO.setCustomerFreight(BaseUtil.transferToCent(waybill.getEstimateFreight()).longValue());
        // 承运商运费
        sourceDTO.setVendorFreight(BaseUtil.transferToCent(waybill.getShow4DriverFreight()).longValue());

        WaybillAmount amount = waybillAmountService.loadByWaybillId(waybill.getWaybillId());
        if( null != amount ){
            // 客户运费
            sourceDTO.setCustomerFreight(BaseUtil.transferToCent(amount.getLastCustomerFreightWithTax()).longValue());
            // 承运商运费
            sourceDTO.setVendorFreight(BaseUtil.transferToCent(amount.getLastVendorFreightWithTax()).longValue());
        }

        // 业务类型
        sourceDTO.setSourceBizType(BusinessDetailTypeEnum.WAYBILL.getCode());
        // 运单号
        sourceDTO.setSourceBizNo(waybill.getWaybillNo());
        // 用车时间
        sourceDTO.setUseCarTime(waybill.getPlanDeliveryTime());
        // 运单完成时间
        sourceDTO.setWaybillCompleteTime(waybill.getFinishTime());
        // 物流项目
        sourceDTO.setLogisticsName(waybill.getProjectName());
        // 税率,如0.1表示10%
        BigDecimal taxRateValue = null;
        TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybill.getWaybillId(), null);
        if (null != truckRequire) {
            taxRateValue = truckRequire.getTaxRateValue();
            sourceDTO.setTaxRate(taxRateValue);
        } else {
            sourceDTO.setTaxRate(BigDecimal.ZERO);
        }
        //调整单金额
        AdjustForItem adjustForItem = new AdjustForItem();
        adjustForItem.setWaybillId(waybill.getWaybillId());
        adjustForItem.setAdjustId(master.getAdjustId());
        List<AdjustForItem> adjustForItemList = adjustForItemService.getByFilter(adjustForItem);
        BigDecimal sum = BigDecimal.valueOf(0);
        for (AdjustForItem item : adjustForItemList){
            sum = sum.add(item.getAdjustForFreight()).add(item.getAdjustForCarry()).add(item.getAdjustForWorkload())
                    .add(item.getAdjustForUpstairs()).add(item.getAdjustForFine()).add(item.getAdjustForCargoLoss());
        }
        sourceDTO.setPriceIncludingTax(sum.multiply(BigDecimal.valueOf(100)).longValue());
        BigDecimal priceIncludingTax = BaseUtil.calFreightWithNotTax(sum, taxRateValue);
        sourceDTO.setPriceExcludingTax(priceIncludingTax.multiply(BigDecimal.valueOf(100)).longValue());
        // 税,单位分
        sourceDTO.setTax(sourceDTO.getPriceIncludingTax() - sourceDTO.getPriceExcludingTax());
        // 车架号
        com.juma.vms.truck.domain.Truck truck = vmsCommonService.loadTruckByTruckId(waybill.getTruckId());
        sourceDTO.setVin(truck == null ? null : truck.getTruckIdentificationNo());
        if(null != truck){
            sourceDTO.setTruckType(truck.getTruckRunType() == null ? "" : TruckRunTypeEnum.getDescByCode(truck.getTruckRunType()));
        }
        return sourceDTO;
    }

    /**设置调整后金额汇总**/
    private void setAdjustResultAmount(AdjustForMasterAddVO vo, AdjustItemValidHolder holder, List<AdjustForWaybillTemp> temps, LoginEmployee loginUser) {
        if( CollectionUtils.isEmpty(temps) ){ return; }

        WaybillStatisticsAmountVO waybillStatisticsAmountVO = new WaybillStatisticsAmountVO();
        waybillStatisticsAmountVO.setAdjustMasterType(vo.getAdjustForWho());
        waybillStatisticsAmountVO.setAdjustType(vo.getAdjustType());
        waybillStatisticsAmountVO.setCustomerBeforeHasTaxAmount(BigDecimal.ZERO);
        waybillStatisticsAmountVO.setCustomerAfterHasTaxAmount(BigDecimal.ZERO);
        waybillStatisticsAmountVO.setVendorHasTaxAmount(BigDecimal.ZERO);
        waybillStatisticsAmountVO.setVendorBeforeHasTaxAmount(BigDecimal.ZERO);
        waybillStatisticsAmountVO.setVendorAfterHasTaxAmount(BigDecimal.ZERO);
        waybillStatisticsAmountVO.setCustomerHasTaxAmount(BigDecimal.ZERO);
        waybillStatisticsAmountVO.setBeforeProfitAmount(BigDecimal.ZERO);
        waybillStatisticsAmountVO.setAfterProfitAmount(BigDecimal.ZERO);


        BigDecimal beforeAmount = BigDecimal.ZERO;
        BigDecimal afterAmount = BigDecimal.ZERO;
        BigDecimal otherSideAmount = BigDecimal.ZERO;

        BigDecimal beforeAmountSelf = BigDecimal.ZERO;
        BigDecimal afterAmountSelf = BigDecimal.ZERO;
        BigDecimal otherSideAmountSelf = BigDecimal.ZERO;
        Integer waybillId = null;
        for (AdjustForWaybillTemp temp : temps){
            waybillId = temp.getWaybillId();
            if( null == waybillId ){ continue; }
            Vendor vendor = vmsCommonService.loadVendorByVendorId(temp.getVendorId());
            if( null == vendor ){ continue; }
            // 客户侧调整前含税金额
            BigDecimal beforeItemAmount = validAndSetBigDecimalValue(temp.getFreightWithTax());
            // 承运侧含税金额
            BigDecimal otherSideItemAmount = validAndSetBigDecimalValue(temp.getOtherSideWithTax());

            // 客户侧调整后含税金额
            BigDecimal afterItemAmount = beforeItemAmount
                    .add(validAndSetBigDecimalValue(temp.getAdjustForFreight()))
                    .add(validAndSetBigDecimalValue(temp.getAdjustForCarry()))
                    .add(validAndSetBigDecimalValue(temp.getAdjustForWorkload()))
                    .add(validAndSetBigDecimalValue(temp.getAdjustForUpstairs()))
                    .add(validAndSetBigDecimalValue(temp.getAdjustForFine()))
                    .add(validAndSetBigDecimalValue(temp.getAdjustForCargoLoss()));

            // 部分(非自营)
            if( !new Byte(VendorSourceEnum.SELF_SUPPORT.getCode()).equals(vendor.getVendorSource()) ){
                beforeAmount = beforeAmount.add(beforeItemAmount);
                otherSideAmount = otherSideAmount.add(otherSideItemAmount);
                afterAmount = afterAmount.add(afterItemAmount);
            }
            // 全部(自营+非自营)
            beforeAmountSelf = beforeAmountSelf.add(beforeItemAmount);
            otherSideAmountSelf = otherSideAmountSelf.add(otherSideItemAmount);
            afterAmountSelf = afterAmountSelf.add(afterItemAmount);
        }

        Waybill waybill = waybillService.getWaybill(waybillId);
        if( null != waybill ){
            // 项目承诺毛利率获取
            Project project = projectService.getProjectV2(waybill.getProjectId());
            if( null != project && null != project.getProfitRate() ){
                BigDecimal projectProfitRate = project.getProfitRate();
                waybillStatisticsAmountVO.setProfitRate(projectProfitRate);
                waybillStatisticsAmountVO.setProfitRateDesc(BaseUtil.formatDecimal(projectProfitRate.doubleValue()));
            }
        }

        if( AdjustMasterType.CUSTOMER.getCode().equals(vo.getAdjustForWho()) ){
            waybillStatisticsAmountVO.setAfterProfitAmount(afterAmount.subtract(otherSideAmount));
            if(BigDecimal.ZERO.compareTo(afterAmount) != 0){
                BigDecimal afterProportion = waybillStatisticsAmountVO.getAfterProfitAmount().divide(afterAmount,4,BigDecimal.ROUND_HALF_UP);
                waybillStatisticsAmountVO.setAfterProportion(BaseUtil.formatDecimal(afterProportion.doubleValue()));
                if( null != waybillStatisticsAmountVO.getProfitRate() ){
                    waybillStatisticsAmountVO.setAfterProportionGap(BaseUtil.formatDecimal(afterProportion.subtract(waybillStatisticsAmountVO.getProfitRate()).doubleValue()));
                }
            }
            // 含自营
            waybillStatisticsAmountVO.setCustomerBeforeHasTaxAmount(beforeAmountSelf);
            waybillStatisticsAmountVO.setCustomerAfterHasTaxAmount(afterAmountSelf);
            waybillStatisticsAmountVO.setVendorHasTaxAmount(otherSideAmountSelf);

            //不含自营
            waybillStatisticsAmountVO.setCustomerBeforeHasTaxAmountWithoutSelf(beforeAmount);
            waybillStatisticsAmountVO.setCustomerAfterHasTaxAmountWithoutSelf(afterAmount);
            waybillStatisticsAmountVO.setVendorHasTaxAmountWithoutSelf(otherSideAmount);
        }
        if( AdjustMasterType.VENDOR.getCode().equals(vo.getAdjustForWho()) ){
            waybillStatisticsAmountVO.setAfterProfitAmount(otherSideAmount.subtract(afterAmount));
            if(BigDecimal.ZERO.compareTo(otherSideAmount) != 0){
                BigDecimal afterProportion = waybillStatisticsAmountVO.getAfterProfitAmount().divide(otherSideAmount,4,BigDecimal.ROUND_HALF_UP);
                waybillStatisticsAmountVO.setAfterProportion(BaseUtil.formatDecimal(afterProportion.doubleValue()));
                if( null != waybillStatisticsAmountVO.getProfitRate() ) {
                    waybillStatisticsAmountVO.setAfterProportionGap(BaseUtil.formatDecimal(afterProportion.subtract(waybillStatisticsAmountVO.getProfitRate()).doubleValue()));
                }
            }
            // 含自营
            waybillStatisticsAmountVO.setVendorBeforeHasTaxAmount(beforeAmountSelf);
            waybillStatisticsAmountVO.setVendorAfterHasTaxAmount(afterAmountSelf);
            waybillStatisticsAmountVO.setCustomerHasTaxAmount(otherSideAmountSelf);

            //不含自营
            waybillStatisticsAmountVO.setVendorBeforeHasTaxAmountWithoutSelf(beforeAmount);
            waybillStatisticsAmountVO.setVendorAfterHasTaxAmountWithoutSelf(afterAmount);
            waybillStatisticsAmountVO.setCustomerHasTaxAmountWithoutSelf(otherSideAmount);
        }

        holder.setWaybillStatisticsAmountVO(waybillStatisticsAmountVO);
    }

    /**月毛利率计算**/
    @Override
    public void calculateMonthProportion(Integer adjustType, Integer adjustMasterType, String reconciliationNo, WaybillStatisticsAmountVO waybillStatisticsAmountVO) {
        // 对账前,因不确定是否只有一个项目的运单,所以,不做项目相关的计算.
        if( AdjustType.BEFORE.getCode().equals(adjustType) ){ return; }
        List<Waybill> waybills = null;
        if( AdjustMasterType.CUSTOMER.getCode().equals(adjustMasterType) ){
            waybills = waybillCommonService.findByReceivableReconciliationNo(reconciliationNo);
        }
        if( AdjustMasterType.VENDOR.getCode().equals(adjustMasterType) ){
            waybills = waybillCommonService.findByReconciliationNo(reconciliationNo);
        }
        if( CollectionUtils.isEmpty(waybills) ){ return; }
        Waybill waybill = waybills.get(0);
        // 没有项目的运单,不做项目相关的计算
        if( null != waybill && null == waybill.getProjectId() ){ return; }
        // 项目承诺毛利
        BigDecimal projectProfitRate = BigDecimal.ZERO;
        Project project = projectService.getProjectV2(waybill.getProjectId());
        if( null == project ){ return; }
        if( null == project.getProfitRate() ){ return; }
        projectProfitRate = project.getProfitRate();
        waybillStatisticsAmountVO.setProfitRate(projectProfitRate);
        waybillStatisticsAmountVO.setProfitRateDesc(BaseUtil.formatDecimal(projectProfitRate.doubleValue()));
        boolean sameMonth = waybillCommonService.checkPlanDeliveryTimeInSameMonth(waybills);
        if( sameMonth ){
            Date planDeliveryTime = waybill.getPlanDeliveryTime();
            WaybillStatisticsAmountVO amountVO = waybillCommonService.getMonthAmountInfo(waybill.getProjectId(),planDeliveryTime);
            waybillStatisticsAmountVO.setTime(DateUtils.format(planDeliveryTime,DateUtils.Parttern.FORMAT_YYMM_ZH));
            waybillStatisticsAmountVO.setMonthProfitAmount(amountVO.getMonthProfitAmount());
            waybillStatisticsAmountVO.setMonthProportion(amountVO.getMonthProportion());
            if( null != amountVO.getMonthProportion() ){
                waybillStatisticsAmountVO.setMonthProportionDesc(BaseUtil.formatDecimal(amountVO.getMonthProportion().doubleValue()));
            }
            waybillStatisticsAmountVO.setMonthProportionGap(amountVO.getMonthProportionGap());
            if( null != amountVO.getMonthProportionGap() ){
                waybillStatisticsAmountVO.setMonthProportionGapDesc(BaseUtil.formatDecimal(amountVO.getMonthProportionGap().doubleValue()));
            }
            waybillStatisticsAmountVO.setTime(DateUtils.format(DateUtils.create(waybill.getPlanDeliveryTime()),DateUtils.Parttern.FORMAT_YYMM_ZH));
        }
    }


}
