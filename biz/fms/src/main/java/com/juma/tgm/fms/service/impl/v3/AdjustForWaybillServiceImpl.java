package com.juma.tgm.fms.service.impl.v3;

import com.alibaba.fastjson.JSON;
import com.bruce.tool.rpc.http.core.Https;
import com.giants.common.exception.BusinessException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.fms.v2.core.payment.payable.domain.WayBillStatus;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.fms.dao.v3.AdjustForWaybillTempMapper;
import com.juma.tgm.fms.domain.v3.*;
import com.juma.tgm.fms.domain.v3.enums.AdjustMasterType;
import com.juma.tgm.fms.domain.v3.enums.AdjustStatus;
import com.juma.tgm.fms.domain.v3.enums.AdjustType;
import com.juma.tgm.fms.domain.v3.vo.*;
import com.juma.tgm.fms.service.v3.AdjustForMasterAddService;
import com.juma.tgm.fms.service.v3.AdjustForWaybillService;
import com.juma.tgm.fms.service.v3.ReconcilicationForReceivableService;
import com.juma.tgm.project.domain.v2.Project;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.tools.service.AuthCommonService;
import com.juma.tgm.tools.service.FmsCommonService;
import com.juma.tgm.tools.service.InvoiceCommonService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillAmount;
import com.juma.tgm.waybill.domain.vo.WaybillAmountFilter;
import com.juma.tgm.waybill.service.WaybillAmountService;
import com.juma.tgm.waybill.service.WaybillService;
import com.juma.vms.driver.domain.Driver;
import com.juma.vms.transport.enumeration.ApprovalStatus;
import com.juma.vms.vendor.domain.Vendor;
import lombok.extern.slf4j.Slf4j;
import me.about.poi.reader.XlsxReader;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.mybatis.generator.my.page.Page;
import org.mybatis.generator.my.page.PageFunction;
import org.mybatis.generator.my.service.AbstractMybatisService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 临时表操作
 * 功能 : 
 * 1.列表查询
 * 2.批量添加
 * 3.单条删除
 * @author : Bruce(刘正航) 17:41 2019-05-10
 */
@Slf4j
@Component
public class AdjustForWaybillServiceImpl extends AbstractMybatisService<AdjustForWaybillTemp, AdjustForWaybillTempExample> implements AdjustForWaybillService {

    private static ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(10,
            new BasicThreadFactory.Builder().namingPattern("save-temp-data-%d").daemon(true).build());


    @Autowired
    private WaybillService waybillService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private FmsCommonService fmsCommonService;

    @Autowired
    private VmsCommonService vmsCommonService;

    @Autowired
    private AuthCommonService authCommonService;

    @Autowired
    private CustomerInfoService customerInfoService;

    @Autowired
    private InvoiceCommonService invoiceCommonService;

    @Autowired
    private WaybillAmountService waybillAmountService;

    @Autowired
    private AdjustForMasterAddService adjustForMasterAddService;

    @Autowired
    private ReconcilicationForReceivableService reconcilicationForReceivableService;

    @Autowired
    private AdjustForWaybillTempMapper adjustForWaybillTempMapper;

    @Override
    public AdjustForWaybillTempMapper getMapper() {
        return this.adjustForWaybillTempMapper;
    }

    @Override
    public Page<AdjustForWaybillVO> findPageByFilter(final QueryCond<AdjustForWaybillTempFilter> queryCond, final LoginEmployee loginUser) throws BusinessException {
        if( null == queryCond.getFilters().getAdjustId() ){
            queryCond.getFilters().setAdjustId(0);
        }
        final AdjustForWaybillTempExample example = new AdjustForWaybillTempExample().createCriteria()
                .andAdjustIdEqualTo(queryCond.getFilters().getAdjustId())
                .andCreateUserIdEqualTo(loginUser.getUserId())
                .andTenantIdEqualTo(loginUser.getTenantId())
                .example();
        example.setSize(queryCond.getPageSize());
        example.setStartOffSet(queryCond.getStartOffset());
        example.orderBy(AdjustForWaybillTemp.Column.validStatus.asc()+","+AdjustForWaybillTemp.Column.adjustTempId.desc());
        return pageOf(queryCond.getPageSize(),queryCond.getPageNo(),new PageFunction<AdjustForWaybillVO>() {
            @Override
            public int ofTotal() {
                return (int)countByExample(example);
            }

            @Override
            public Collection<AdjustForWaybillVO> ofResults() {
                return fillWaybillInfo(selectByExample(example),loginUser);
            }
        });
    }

    @Override
    public List<AdjustForWaybillTemp> findByFilter(QueryCond<AdjustForWaybillTempVO> filter, LoginEmployee loginUser) throws BusinessException {
        if( null == filter || null == filter.getFilters().getAdjustId() ){
            return Lists.newArrayList();
        }
        AdjustForWaybillTempExample example = new AdjustForWaybillTempExample().createCriteria()
                .andAdjustIdEqualTo(filter.getFilters().getAdjustId())
                .andCreateUserIdEqualTo(loginUser.getUserId())
                .andTenantIdEqualTo(loginUser.getTenantId())
                .example();
        return selectByExample(example);
    }

    /**添加临时表数据**/
    @Override
    public void addTemp(AdjustForWaybillTemp temp, LoginEmployee loginUser) throws BusinessException {
        if( null == temp ){ return;}
        temp.setCreateUserId(loginUser.getUserId());
        temp.setCreateTime(new Date());
        super.insert(temp);
    }

    /**批量添加临时表数据**/
    @Override
    public void addTempBatch(Integer adjustId, List<AdjustForWaybillTemp> list, LoginEmployee loginUser) throws BusinessException {
        if( CollectionUtils.isEmpty(list) ){ return; }
        for (AdjustForWaybillTemp temp : list) {
            temp.setAdjustId(adjustId);
            temp.setCreateUserId(loginUser.getUserId());
            temp.setCreateTime(new Date());
        }
        // 插入数据之前,删除上一次当前租户下的临时表数据
        AdjustForWaybillTempExample example = new AdjustForWaybillTempExample().createCriteria()
                .andAdjustIdEqualTo(adjustId)
                .andCreateUserIdEqualTo(loginUser.getUserId())
                .andTenantIdEqualTo(loginUser.getTenantId())
                .example();
        List<AdjustForWaybillTemp> temps = adjustForWaybillTempMapper.selectByExample(example);
        for (AdjustForWaybillTemp temp : temps){
            adjustForWaybillTempMapper.deleteByPrimaryKey(temp.getAdjustTempId());
        }
        super.insertBatch(list);
    }

    @Override
    public void updateTempBatch(Integer adjustId, List<AdjustForWaybillTemp> list, LoginEmployee loginUser) throws BusinessException {
        if( CollectionUtils.isEmpty(list) ){ return; }
        for (AdjustForWaybillTemp temp : list) {
            temp.setCreateUserId(loginUser.getUserId());
            temp.setCreateTime(new Date());
        }
        // 插入数据之前,删除上一次当前租户下的临时表数据
        AdjustForWaybillTempExample example = new AdjustForWaybillTempExample().createCriteria()
                .andCreateUserIdEqualTo(loginUser.getUserId())
                .andTenantIdEqualTo(loginUser.getTenantId())
                .andAdjustIdEqualTo(adjustId)
                .example();
        List<AdjustForWaybillTemp> temps = adjustForWaybillTempMapper.selectByExample(example);
        for (AdjustForWaybillTemp temp : temps){
            adjustForWaybillTempMapper.deleteByPrimaryKey(temp.getAdjustTempId());
        }
        super.insertBatch(list);
    }

    /**删除临时表数据**/
    @Override
    public void deleteTempById(Integer adjustTempId, LoginEmployee loginUser) throws BusinessException {
        adjustForWaybillTempMapper.deleteByPrimaryKey(adjustTempId);
    }

    /**删除临时表数据**/
    @Override
    public void deleteTempByWaybillNo(Integer adjustId, String waybillNo, LoginEmployee loginUser) throws BusinessException {
        AdjustForWaybillTempExample example = new AdjustForWaybillTempExample().createCriteria()
                .andCreateUserIdEqualTo(loginUser.getUserId())
                .andTenantIdEqualTo(loginUser.getTenantId())
                .andWaybillNoEqualTo(waybillNo)
                .andAdjustIdEqualTo(adjustId)
                .example();
        List<AdjustForWaybillTemp> temps = adjustForWaybillTempMapper.selectByExample(example);
        for (AdjustForWaybillTemp temp : temps){
            adjustForWaybillTempMapper.deleteByPrimaryKey(temp.getAdjustTempId());
        }
    }

    /**根据调整单主键删除临时表数据**/
    @Override
    public void deleteByAdjustId(Integer adjustId, LoginEmployee loginEmployee) throws BusinessException {
        AdjustForWaybillTempExample example = new AdjustForWaybillTempExample();
        example.createCriteria().andAdjustIdEqualTo(adjustId);
        List<AdjustForWaybillTemp> temps = adjustForWaybillTempMapper.selectByExample(example);
        for (AdjustForWaybillTemp temp : temps){
            deleteTempById(temp.getAdjustTempId(),loginEmployee);
        }
    }

    @Override
    public void coverTempByUser(Integer adjustId, LoginEmployee loginUser) throws BusinessException {
        if( null == adjustId ){
            throw new BusinessException("AdjustIdNotNull","调整单ID为空,终止数据覆盖操作");
        }
        AdjustForMasterFilter filter = new AdjustForMasterFilter();
        filter.setAdjustId(adjustId);
        List<AdjustForMaster> masters = adjustForMasterAddService.findByFilter(filter,loginUser);
        if( CollectionUtils.isEmpty(masters) ){
            log.info("覆盖数据权限提示:{}","没查到对应的调整单");
            return;
        }
        AdjustForMaster master = masters.get(0);
        if( !master.getCreateUserId().equals(loginUser.getUserId()) ){
            log.info("覆盖数据权限提示:{}","非当前调整单的创建者,不能编辑此调整单");
            return;
        }
        AdjustForItemFilter itemFilter = new AdjustForItemFilter();
        itemFilter.setAdjustId(adjustId);
        List<AdjustForItem> items = adjustForMasterAddService.findItemByFilter(itemFilter,loginUser);
        if( CollectionUtils.isEmpty(items) ){
            log.info("覆盖数据权限提示:{}","没查到对应的调整单明细信息");
            return;
        }
        List<AdjustForWaybillTemp> temps = Lists.newArrayList();
        for (AdjustForItem vo : items){
            AdjustForWaybillTemp temp = new AdjustForWaybillTemp();
            temp.setAdjustId(vo.getAdjustId());
            temp.setTenantId(loginUser.getTenantId());
            temp.setTenantCode(loginUser.getTenantCode());
            temp.setVendorId(vo.getVendorId());
            temp.setWaybillId(vo.getWaybillId());
            temp.setWaybillNo(vo.getWaybillNo());
            temp.setOtherSideWithTax(vo.getOtherSideWithTax());
            temp.setFreightWithTax(validAndSetBigDecimalValue(vo.getFreightWithTax()));
            temp.setAdjustForFreight(validAndSetBigDecimalValue(vo.getAdjustForFreight()));
            temp.setAdjustForCarry(validAndSetBigDecimalValue(vo.getAdjustForCarry()));
            temp.setAdjustForWorkload(validAndSetBigDecimalValue(vo.getAdjustForWorkload()));
            temp.setAdjustForUpstairs(validAndSetBigDecimalValue(vo.getAdjustForUpstairs()));
            temp.setAdjustForFine(validAndSetBigDecimalValue(vo.getAdjustForFine()));
            temp.setAdjustForCargoLoss(validAndSetBigDecimalValue(vo.getAdjustForCargoLoss()));
            temp.setAdjustForReason(validAndSetStringValue(vo.getAdjustForReason()));

            temp.setValidStatus(AdjustStatus.APPROVED.getCode());
            temp.setValidResult(AdjustStatus.APPROVED.getDesc());
            temps.add(temp);
        }

        updateTempBatch(adjustId,temps,loginUser);
    }

    @Override
    public void validWaybillTempInfo(AdjustForMasterAddVO vo, LoginEmployee loginUser) throws BusinessException {
        validWaybillTempParams(vo,loginUser);
        AdjustItemValidHolder holder = new AdjustItemValidHolder();
        List<AdjustForWaybillTemp> temps = listTempInfos(vo, loginUser);
        // 再次校验调整单-运单数据数据
        validWaybillDatas(vo, holder, temps, loginUser);
        adjustForMasterAddService.createOrUpdateAdjust(holder,vo,temps, loginUser);
    }

    /**参数校验**/
    @Override
    public void validWaybillTempParams(AdjustForMasterAddVO vo, final LoginEmployee loginUser) {
        if( null == vo.getAdjustType() ){
            throw new BusinessException("AdjustTypeNotNull", "调整阶段不能为空");
        }
        if( null == vo.getAdjustForWho() ){
            throw new BusinessException("AdjustMasterTypeNotNull", "调整主体不能为空");
        }
        if(StringUtils.isBlank(vo.getAdjustForReason())){
            throw new BusinessException("AdjustReasonNotNull", "调整单调整原因不能为空");
        }
        if( vo.getAdjustForReason().length() < 10 || vo.getAdjustForReason().length() > 500 ){
            throw new BusinessException("AdjustReasonTooLong", "调整单调整原因字符个数应该在10到500之间");
        }
        // 凭证中, 5个字段只要有一个字段有值, 即可通过校验(已和金鹏确认)
        if(StringUtils.isBlank(vo.getCarryProofAttach())
                && StringUtils.isBlank(vo.getWorkloadProofAttach())
                && StringUtils.isBlank(vo.getUpstairsProofAttach())
                && StringUtils.isBlank(vo.getFineProofAttach())
                && StringUtils.isBlank(vo.getCargoLossProofAttach())){
            throw new BusinessException("AdjustAttachNotNull", "调整凭证不能为空");
        }
        // 调整单编辑页面, 主键可用性校验
        if( null != vo.getAdjustId() ){
            AdjustForMaster master = adjustForMasterAddService.getAdjustForMasterById(vo.getAdjustId());
            if( null == master ){
                throw new BusinessException("AdjustForMasterNotNull", "错误的调整单ID, 不能走调整单编辑逻辑");
            }
            if( !master.getCreateUserId().equals(loginUser.getUserId()) ){
                throw new BusinessException("AdjustForMasterNotMe", "非当前调整单的创建者,不能编辑此调整单");
            }
            vo.setAdjustNo(master.getAdjustNo());
        }
    }

    /**获取临时表记录**/
    @Override
    public List<AdjustForWaybillTemp> listTempInfos(final AdjustForMasterAddVO vo, LoginEmployee loginUser) {
        // 查询当前租户下所有的待调整运单数据
        QueryCond<AdjustForWaybillTempVO> queryCond = new QueryCond<>();
        AdjustForWaybillTempVO filter = new AdjustForWaybillTempVO();
        filter.setAdjustId(vo.getAdjustId());
        queryCond.setFilters(filter);
        if( null == vo.getAdjustId() ){ queryCond.getFilters().setAdjustId(0); }
        List<AdjustForWaybillTemp> list =  findByFilter(queryCond,loginUser);
        if( CollectionUtils.isEmpty(list) ){
            // 临时表被异常清空,导致数据无法获取
            throw new BusinessException("AdjustWaybillNotNull", "没有需要调整的运单,请上传需要调整单运单后,再提交审核");
        }
        return list;
    }

    /**创建调整单之前,再次校验**/
    @Override
    public void validWaybillDatas(final AdjustForMasterAddVO vo, AdjustItemValidHolder holder, final List<AdjustForWaybillTemp> temps, final LoginEmployee loginUser) {
        AdjustAttachVO adjustAttachVO = new AdjustAttachVO();
        adjustAttachVO.setAdjustId(vo.getAdjustId());
        adjustAttachVO.setAdjustType(vo.getAdjustType());
        adjustAttachVO.setAdjustForWho(vo.getAdjustForWho());
        adjustAttachVO.setReconciliationNo(vo.getReconcilicationNo());
        holder.setSkipFreshTemp(true);
        validWaybillForAdjust(adjustAttachVO,temps, holder, loginUser);
        // 数据保存之后, 单行数据错误提示
        validAfterWaybillDataSave(holder);
    }

    /**非阻断性全局错误**/
    private void validAfterWaybillDataSave(final AdjustItemValidHolder holder) {
        if( !CollectionUtils.isEmpty(holder.getRepeatWaybillNos()) ) {
            if (holder.getRepeatWaybillNos().size() <= 3) {
                throw new BusinessException(Constants.NEED_POP_UP, "以下运单有重复，请将重复运单移除后再提交(" + StringUtils.join(holder.getRepeatWaybillNos(), ",") + ")");
            } else {
                throw new BusinessException(Constants.NEED_POP_UP, "以下运单有重复，请将重复运单移除后再提交(" + StringUtils.join(holder.getRepeatWaybillNos().subList(0, 2), ",") + ")");
            }
        }
        if( holder.isHasLineError() ){
            throw new BusinessException(Constants.NEED_POP_UP,"请移除不可调价的运单，再提交审核");
        }
    }

    /**为调整单校验运单数据**/
    @Override
    public AdjustItemValidHolder validWaybillForAdjust(AdjustAttachVO vo, List<AdjustForWaybillTemp> list, AdjustItemValidHolder holder, final LoginEmployee loginUser){
        if( CollectionUtils.isEmpty(list) && StringUtils.isNotBlank(vo.getAttachUrl()) ){
            list = fetchUploadExcelData(vo,loginUser);
        }
        if (CollectionUtils.isEmpty(list)) { return holder; }

        validParams(vo, list);
        // 单个运单, 数据合理性校验
        validAdjustWaybillData(vo, list, holder, loginUser);
        // 阻断性全局错误: 不依赖对账单号
        validGlobalErrorWithOutReconciliationNo(vo, holder);
        // 阻断性全局错误: 依赖对账单号
        validGlobalErrorWithReconciliationNo(vo, list, holder, loginUser);

        // 不管后续任务是否抛异常, 保存数据都要执行
        saveTempDataAnyway(vo, list, loginUser);

        return holder;
    }

    /**不管后续任务是否抛异常, 保存数据都要执行**/
    private void saveTempDataAnyway(final AdjustAttachVO vo, final List<AdjustForWaybillTemp> list, final LoginEmployee loginUser) {
        final List<AdjustForWaybillTemp> localList = list;
        Future<?> future = executorService.submit(new Runnable() {
            @Override
            public void run() {
                if( null != vo.getAdjustId() ){
                    addTempBatch(vo.getAdjustId(), localList, loginUser);
                }else{
                    addTempBatch(0, localList, loginUser);
                }
            }
        });
        try {
            future.get();
        } catch (InterruptedException| ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**不依赖对账单号的全局错误**/
    private void validGlobalErrorWithOutReconciliationNo(final AdjustAttachVO vo, final AdjustItemValidHolder holder) {
        // 客户/承运商-对账前运单客户数量校验
        if(AdjustType.BEFORE.getCode().equals(vo.getAdjustType()) && holder.getCustomerIds().size() > 1){
            throw new BusinessException(Constants.NEED_POP_UP,"对账前调整单，只能调整同一个客户下的运单。");
        }
        if( AdjustType.AFTER.getCode().equals(vo.getAdjustType()) && holder.getReconciliations().size() > 1 ){
            if( holder.getReconciliations().size() < 3 ){
                throw new BusinessException(Constants.NEED_POP_UP,AdjustMasterType.getByCode(vo.getAdjustForWho()).getDesc()+
                        "对账后一次只能调整一个对账单下的运单,对账单号:"+StringUtils.join(holder.getReconciliations(),",")+",请修改后再提交");
            }else{
                Set<String> localReconciliations = Sets.newConcurrentHashSet(Lists.newArrayList(holder.getReconciliations()).subList(0,2));
                throw new BusinessException(Constants.NEED_POP_UP,AdjustMasterType.getByCode(vo.getAdjustForWho()).getDesc()+
                        "对账后一次只能调整一个对账单下的运单,对账单号:"+StringUtils.join(localReconciliations,",")+",请修改后再提交");
            }
        }
    }

    private List<AdjustForWaybillTemp> fetchUploadExcelData(AdjustAttachVO attachVO, final LoginEmployee loginEmployee) {
        byte[] dataBytes = Https.create().url(attachVO.getAttachUrl()).download();
        if( null == dataBytes || dataBytes.length == 0 ){
            throw new BusinessException("ExcelAnalysisImportError","Excel附件读取失败,请重新上传");
        }

        if( AdjustMasterType.CUSTOMER.getCode().equals(attachVO.getAdjustForWho()) ){
            List<WaybillCustomerExportVO> list = null;
            try (ByteArrayInputStream bais = new ByteArrayInputStream(dataBytes)){
                list = XlsxReader.fromInputStream(bais, WaybillCustomerExportVO.class);
            } catch (Exception e) {
                throw new BusinessException("ExcelAnalysisImportError","调整单模板错误,请检查是否是"+AdjustMasterType.getByCode(attachVO.getAdjustForWho()).getDesc()+"调整单,请确认是否更改运单金额");
            }
            if(CollectionUtils.isEmpty(list)){ return null; }
            if( list.size() > 500 ){
                throw new BusinessException("MaxWaybillAmountError","单次可调整运单数量上限为500,请调整数量后再上传");
            }
            List<AdjustForWaybillTemp> temps = Lists.newArrayList();
            for (WaybillCustomerExportVO vo : list) {
                if( StringUtils.isBlank(vo.getWaybillNo()) ){
                    log.warn("运单号为空的数据,忽略,默认为用户不使用的数据");
                    continue;
                }
                AdjustForWaybillTemp temp = new AdjustForWaybillTemp();
                temp.setTenantId(loginEmployee.getTenantId());
                temp.setTenantCode(loginEmployee.getTenantCode());
                temp.setAdjustId(0);
                temp.setVendorId(0);
                temp.setWaybillId(0);

                temp.setWaybillNo(vo.getWaybillNo());
                temp.setOtherSideWithTax(BigDecimal.ZERO);
                temp.setFreightWithTax(validAndSetBigDecimalValue(vo.getFreightWithTax()));
                temp.setAdjustForFreight(validAndSetBigDecimalValue(vo.getAdjustForFreight()));
                temp.setAdjustForCarry(validAndSetBigDecimalValue(vo.getAdjustForCarry()));
                temp.setAdjustForWorkload(validAndSetBigDecimalValue(vo.getAdjustForWorkload()));
                temp.setAdjustForUpstairs(validAndSetBigDecimalValue(vo.getAdjustForUpstairs()));
                temp.setAdjustForFine(validAndSetBigDecimalValue(vo.getAdjustForFine()));
                temp.setAdjustForCargoLoss(validAndSetBigDecimalValue(vo.getAdjustForCargoLoss()));
                temp.setAdjustForReason(validAndSetStringValue(vo.getAdjustForReason()));

                temp.setValidStatus(AdjustStatus.REJECTED.getCode());
                temps.add(temp);
            }
            return temps;
        }
        if( AdjustMasterType.VENDOR.getCode().equals(attachVO.getAdjustForWho()) ){
            List<WaybillVendorExportVO> list = null;
            try (ByteArrayInputStream bais = new ByteArrayInputStream(dataBytes)){
                list = XlsxReader.fromInputStream(bais, WaybillVendorExportVO.class);
            } catch (Exception e) {
                throw new BusinessException("ExcelAnalysisImportError","调整单模板错误,请检查是否是"+AdjustMasterType.getByCode(attachVO.getAdjustForWho()).getDesc()+"调整单,请确认是否更改运单金额");
            }
            if(CollectionUtils.isEmpty(list)){ return null; }
            List<AdjustForWaybillTemp> temps = Lists.newArrayList();
            for (WaybillVendorExportVO vo : list) {
                if( StringUtils.isBlank(vo.getWaybillNo()) ){
                    log.error("运单号为空的数据,忽略,默认为用户不使用的数据");
                    continue;
                }
                AdjustForWaybillTemp temp = new AdjustForWaybillTemp();
                temp.setTenantId(loginEmployee.getTenantId());
                temp.setTenantCode(loginEmployee.getTenantCode());
                temp.setAdjustId(0);
                temp.setVendorId(0);
                temp.setWaybillId(0);

                temp.setWaybillNo(vo.getWaybillNo());
                temp.setOtherSideWithTax(BigDecimal.ZERO);
                temp.setFreightWithTax(validAndSetBigDecimalValue(vo.getFreightWithTax()));
                temp.setAdjustForFreight(validAndSetBigDecimalValue(vo.getAdjustForFreight()));
                temp.setAdjustForCarry(validAndSetBigDecimalValue(vo.getAdjustForCarry()));
                temp.setAdjustForWorkload(validAndSetBigDecimalValue(vo.getAdjustForWorkload()));
                temp.setAdjustForUpstairs(validAndSetBigDecimalValue(vo.getAdjustForUpstairs()));
                temp.setAdjustForFine(validAndSetBigDecimalValue(vo.getAdjustForFine()));
                temp.setAdjustForCargoLoss(validAndSetBigDecimalValue(vo.getAdjustForCargoLoss()));
                temp.setAdjustForReason(validAndSetStringValue(vo.getAdjustForReason()));

                temp.setValidStatus(AdjustStatus.REJECTED.getCode());
                temps.add(temp);
            }
            return temps;
        }
        return null;
    }

    /**依赖对账单号的全局错误**/
    private void validGlobalErrorWithReconciliationNo(AdjustAttachVO vo, List<AdjustForWaybillTemp> list, AdjustItemValidHolder holder, LoginEmployee loginUser) throws BusinessException{
        // 客户-对账后
        if( AdjustMasterType.CUSTOMER.getCode().equals(vo.getAdjustForWho()) && AdjustType.AFTER.getCode().equals(vo.getAdjustType()) ){
            if( holder.getReconciliations().size() == 0 ){
                throw new BusinessException(Constants.NEED_POP_UP,"客户侧，对账后，没有可用的对账单号，不能创建调整单");
            }
            String reconciliationNo = holder.getReconciliations().iterator().next();
            BigDecimal afterAdjustAmount = fetchReceivableReconciliationAmountAfterAdjust(reconciliationNo, list, loginUser);
            if (!invoiceCommonService.validReceivableInvoiceAmount(reconciliationNo,afterAdjustAmount)) {
                throw new BusinessException(Constants.NEED_POP_UP,"调整后的金额小于当前对账单已申请开票的金额，不能进行调价。</br>若需进行调整，</br>1、若当前对账单已申请开票但未开票成功，需将开票申请撤销并删除，</br>2、若当前对账单已开票成功，需将已开票的发票进行红冲或作废。");
            }
        }
        // 承运侧-对账后
        if( AdjustMasterType.VENDOR.getCode().equals(vo.getAdjustForWho()) && AdjustType.AFTER.getCode().equals(vo.getAdjustType()) ){
            if( holder.getReconciliations().size() == 0 ){
                throw new BusinessException(Constants.NEED_POP_UP,"承运侧，对账后，没有可用的对账单号，不能创建调整单");
            }
            // 根据FMS结算单状态, 确定是否可生成调整单
            String reconciliationNo = holder.getReconciliations().iterator().next();
            validFinalStatementForAdjust(vo, reconciliationNo, list, holder);
        }
    }

    /**整体校验调整单数据**/
    private void validAdjustWaybillData(AdjustAttachVO vo, List<AdjustForWaybillTemp> list, AdjustItemValidHolder holder, LoginEmployee loginUser) {
        Integer adjustMasterType = vo.getAdjustForWho();
        Integer adjustType = vo.getAdjustType();
        List<String> waybillNos = Lists.newArrayList();
        for (AdjustForWaybillTemp temp : list) {
            waybillNos.add(temp.getWaybillNo());
        }
        if( CollectionUtils.isEmpty(waybillNos) ){ return; }
        // 编辑时候, 运单条数完整性校验
        validEditWaybillChange(vo, loginUser, waybillNos);

        List<Waybill> waybills = waybillService.findByWaybillNos(waybillNos);
        Map<String,Waybill> waybillMap = Maps.newConcurrentMap();
        List<Integer> waybillIds = Lists.newArrayList();
        for (Waybill waybill : waybills) {
            waybillMap.put(waybill.getWaybillNo(),waybill);
            waybillIds.add(waybill.getWaybillId());
        }

        // 获取运单额外的调整单记录
        Map<Integer, Integer> waybillAdjustMasterMap = fetchWaybillAdjustRecords(vo, loginUser, waybillIds);

        // 运单金额获取
        WaybillAmountFilter filter = new WaybillAmountFilter();
        filter.setWaybillIds(waybillIds);
        List<WaybillAmount> amounts = waybillAmountService.findByFilter(filter,loginUser);
        Map<Integer,WaybillAmount> waybillAmountMap = Maps.newConcurrentMap();
        for (WaybillAmount amount : amounts){
            waybillAmountMap.put(amount.getWaybillId(),amount);
        }

        List<String> repeatWaybillNos = Lists.newArrayList();
        List<String> originWaybillNos = Lists.newArrayList();
        for (AdjustForWaybillTemp temp : list) {
            if( originWaybillNos.contains(temp.getWaybillNo()) ){
                originWaybillNos.add(temp.getWaybillNo());
                holder.getRepeatWaybillNos().add(temp.getWaybillNo());
                temp.setValidStatus(AdjustStatus.REJECTED.getCode());
                temp.setValidResult("本条运单已有重复运单存在, 请删除多余数据, 保留唯一运单数据");
                continue;
            }
            originWaybillNos.add(temp.getWaybillNo());

            Waybill waybill = waybillMap.get(temp.getWaybillNo());

            if(!validWaybillTenant(vo, temp, waybill, loginUser)){
                holder.setHasLineError(true);
                continue;
            }

            if(!validWaybillVendorInfo(vo, temp, holder, waybill, loginUser)){
                holder.setHasLineError(true);
                continue;
            }

            // 校验金额在合理范围
            if( !validAmountInReasonableScope(vo, temp, holder, waybill, waybillAmountMap, loginUser) ){
                holder.setHasLineError(true);
                continue;
            }

            // 缓存waybill信息作为下一步使用
            holdWaybillInfo(vo, temp, holder, waybill);

            if( !validReConciliationRepeat(vo, temp, holder, waybill) ){
                holder.setHasLineError(true);
                continue;
            }

            // 校验是否有正在审批中的调整单:没有正在审批中的(true)
            if (!validNonCurrAdjust(vo, temp, waybillAdjustMasterMap, loginUser)) {
                holder.setHasLineError(true);
                continue;
            }

            // 校验可新建调整单的日期:未过期(true)
            if (!validAdjustEffectiveDate(vo, temp, holder, waybill)) {
                holder.setHasLineError(true);
                continue;
            }

            // 调整原因 长度校验
            if (!validAdjustWaybillReason(temp, waybill)){
                holder.setHasLineError(true);
                continue;
            }

            if( AdjustMasterType.CUSTOMER.getCode().equals(adjustMasterType) && AdjustType.BEFORE.getCode().equals(adjustType) ){
                // 对账前
                // 校验运单客户状态:未对账(true)
                if (!validWaybillCustomerNonReconciliation(temp, waybill)) {
                    holder.setHasLineError(true);
                    continue;
                }
            }
            if( AdjustMasterType.CUSTOMER.getCode().equals(adjustMasterType) && AdjustType.AFTER.getCode().equals(adjustType) ){
                // 对账后
                // 校验运单客户状态:已对账(true)
                if (!validWaybillCustomerReconciliation(temp, waybill)) {
                    holder.setHasLineError(true);
                    continue;
                }
            }

            if( AdjustMasterType.VENDOR.getCode().equals(adjustMasterType) && AdjustType.BEFORE.getCode().equals(adjustType) ){
                // 对账前
                // 校验运单承运商状态:未对账(true)
                if (!validWaybillVendorNonReconsiliation(temp, waybill)) {
                    holder.setHasLineError(true);
                    continue;
                }
            }
            if( AdjustMasterType.VENDOR.getCode().equals(adjustMasterType) && AdjustType.AFTER.getCode().equals(adjustType) ){
                // 对账后
                // 校验运单承运商状态:已对账(true)
                if (!validWaybillVendorReconsiliation(temp, waybill)) {
                    holder.setHasLineError(true);
                    continue;
                }
            }
            temp.setValidStatus(AdjustStatus.APPROVED.getCode());
            temp.setValidResult("可调价");
        }
    }

    /**获取运单额外的调整单记录**/
    private Map<Integer, Integer> fetchWaybillAdjustRecords(AdjustAttachVO vo, LoginEmployee loginUser, List<Integer> waybillIds) {
        AdjustForItemFilter itemFilter = new AdjustForItemFilter();
        itemFilter.setWaybillIds(waybillIds);
        List<AdjustForItem> items = adjustForMasterAddService.findItemByFilter(itemFilter,loginUser);
        List<Integer> adjustIds = Lists.newArrayList();
        Map<Integer,List<Integer>> waybillAdjustMap = Maps.newConcurrentMap();
        for (AdjustForItem item : items){
            adjustIds.add(item.getAdjustId());
            List<Integer> localWaybillIds = waybillAdjustMap.get(item.getAdjustId());
            if( CollectionUtils.isEmpty(localWaybillIds) ){
                localWaybillIds = Lists.newArrayList();
                waybillAdjustMap.put(item.getAdjustId(),localWaybillIds);
            }
            localWaybillIds.add(item.getWaybillId());
        }

        AdjustForMasterFilter masterFilter = new AdjustForMasterFilter();
        masterFilter.setAdjustIds(adjustIds);
        masterFilter.setAdjustMasterType(vo.getAdjustForWho());
        masterFilter.setApprovalStatusList(Lists.newArrayList(ApprovalStatus.APPROVAL_SUBMIT.getCode(),ApprovalStatus.APPROVAL_REJECTED.getCode()));
        List<AdjustForMaster> masters = adjustForMasterAddService.findByFilter(masterFilter,loginUser);
        Map<Integer,Integer> waybillAdjustMasterMap = Maps.newConcurrentMap();
        for (AdjustForMaster master : masters){
            List<Integer> localWaybillIds = waybillAdjustMap.get(master.getAdjustId());
            if( CollectionUtils.isEmpty(localWaybillIds) ){ continue; }
            for (Integer waybillId : localWaybillIds){
                if( null == waybillId ){ continue; }
                waybillAdjustMasterMap.put(waybillId,master.getAdjustId());
            }
        }
        waybillAdjustMap.clear();
        adjustIds.clear();
        return waybillAdjustMasterMap;
    }

    /**校验编辑的时候,是否对原有运单有改变**/
    private void validEditWaybillChange(AdjustAttachVO vo, LoginEmployee loginUser, List<String> waybillNos) {
        if( null == vo.getAdjustId() ){ return; }
        AdjustForItemFilter itemFilter = new AdjustForItemFilter();
        itemFilter.setAdjustId(vo.getAdjustId());
        List<AdjustForItem> items = adjustForMasterAddService.findItemByFilter(itemFilter, loginUser);
        List<String> itemWaybillNos = Lists.newArrayList();
        for (AdjustForItem item : items){
            itemWaybillNos.add(item.getWaybillNo());
        }
        if( waybillNos.size() != itemWaybillNos.size() ){
            throw new BusinessException(Constants.NEED_POP_UP,"编辑调整单时,只能修改已有运单的数据,不能增加新运单,也不能删除已有运单");
        }
        for (String waybillNo : itemWaybillNos){
            if( !waybillNos.contains(waybillNo) ){
                throw new BusinessException(Constants.NEED_POP_UP,"编辑调整单时,只能修改已有运单的数据,不能增加新运单,也不能删除已有运单");
            }
        }
    }

    /**校验对账单重复**/
    private boolean validReConciliationRepeat(final AdjustAttachVO vo, final AdjustForWaybillTemp temp, final AdjustItemValidHolder holder, final Waybill waybill) {
        // 对账前,不做对账单号记录
        if( AdjustType.BEFORE.getCode().equals(vo.getAdjustType()) ){ return true; }

        if( AdjustMasterType.CUSTOMER.getCode().equals(vo.getAdjustForWho()) && StringUtils.isNotBlank(waybill.getReceivableReconcilicationNo())){
            if( StringUtils.isNotBlank(waybill.getReceivableReconcilicationNo())
                    && holder.getReconciliations().size() > 1
                    && !holder.getReconciliations().contains(waybill.getReceivableReconcilicationNo())){
                temp.setValidStatus(AdjustStatus.REJECTED.getCode());
                temp.setValidResult("对账单号已超出一个,请手动删除,当前对账单号"+waybill.getReceivableReconcilicationStatus());
                holder.getReconciliations().add(waybill.getReceivableReconcilicationNo());
                return false;
            }
            holder.getReconciliations().add(waybill.getReceivableReconcilicationNo());
        }

        if( AdjustMasterType.VENDOR.getCode().equals(vo.getAdjustForWho()) && StringUtils.isNotBlank(waybill.getReconciliationNo())){
            if( StringUtils.isNotBlank(waybill.getReconciliationNo())
                    && holder.getReconciliations().size() > 1
                    && !holder.getReconciliations().contains(waybill.getReconciliationNo())){
                temp.setValidStatus(AdjustStatus.REJECTED.getCode());
                temp.setValidResult("对账单号已超出一个,请手动删除,当前对账单号"+waybill.getReconciliationNo());
                holder.getReconciliations().add(waybill.getReconciliationNo());
                return false;
            }
            holder.getReconciliations().add(waybill.getReconciliationNo());
        }

        return true;
    }

    /**调整原因 长度校验**/
    private boolean validAdjustWaybillReason(final AdjustForWaybillTemp temp, final Waybill waybill) {
        if( StringUtils.isBlank(temp.getAdjustForReason()) ){
            return true;
        }
        if( temp.getAdjustForReason().length() < 10 || temp.getAdjustForReason().length() > 50 ){
            temp.setValidStatus(AdjustStatus.REJECTED.getCode());
            temp.setValidResult("运单金额调整原因字符个数应该在10到50之间");
            if( temp.getAdjustForReason().length() > 50 ){
                temp.setAdjustForReason(temp.getAdjustForReason().substring(0,50) + "...");
            }
            return false;
        }
        return true;
    }

    /**校验金额在合理范围**/
    private boolean validAmountInReasonableScope(final AdjustAttachVO vo, final AdjustForWaybillTemp temp, final AdjustItemValidHolder holder, final Waybill waybill, final Map<Integer, WaybillAmount> waybillAmountMap, final LoginEmployee loginUser) {
        BigDecimal adjustForFreight = validAndSetBigDecimalValue(temp.getAdjustForFreight());
        BigDecimal adjustForCarry = validAndSetBigDecimalValue(temp.getAdjustForCarry());
        BigDecimal adjustForWorkload = validAndSetBigDecimalValue(temp.getAdjustForWorkload());
        BigDecimal adjustForUpstairs = validAndSetBigDecimalValue(temp.getAdjustForUpstairs());
        BigDecimal adjustForFine = validAndSetBigDecimalValue(temp.getAdjustForFine());
        BigDecimal adjustForCargoLoss = validAndSetBigDecimalValue(temp.getAdjustForCargoLoss());

        BigDecimal adjustAmount = BigDecimal.ZERO
                .add(adjustForFreight)
                .add(adjustForCarry)
                .add(adjustForWorkload)
                .add(adjustForUpstairs)
                .add(adjustForFine)
                .add(adjustForCargoLoss);

        if( BigDecimal.ZERO.compareTo(adjustAmount) == 0 ){
            temp.setValidStatus(AdjustStatus.REJECTED.getCode());
            temp.setValidResult("本次调整金额=0，若不需要调价，请移除此运单");
            return false;
        }

        if( AdjustMasterType.CUSTOMER.getCode().equals(vo.getAdjustForWho())){
            if( null == waybill.getEstimateFreight() ){
                temp.setValidStatus(AdjustStatus.REJECTED.getCode());
                temp.setValidResult("运单客户税前金额为空，此运单数据异常，不能完成调价");
                return false;
            }
            WaybillAmount waybillAmount = waybillAmountMap.get(waybill.getWaybillId());
            BigDecimal beforeAmount = waybill.getEstimateFreight();
            BigDecimal othersideBeforeAmount = waybill.getShow4DriverFreight();
            if( null != waybillAmount ){
                beforeAmount = waybillAmount.getLastCustomerFreightWithTax();
                othersideBeforeAmount = waybillAmount.getLastVendorFreightWithTax();
            }
            temp.setFreightWithTax(beforeAmount);
            temp.setOtherSideWithTax(othersideBeforeAmount);
            if( BigDecimal.ZERO.compareTo(adjustAmount.add(beforeAmount)) > 0 ){
                temp.setValidStatus(AdjustStatus.REJECTED.getCode());
                temp.setValidResult("调整之后的运单客户税前金额不能小于0");
                return false;
            }
        }

        if( AdjustMasterType.VENDOR.getCode().equals(vo.getAdjustForWho())){
            if( null == waybill.getShow4DriverFreight() ){
                temp.setValidStatus(AdjustStatus.REJECTED.getCode());
                temp.setValidResult("运单承运商税前金额为空，此运单数据异常，不能完成调价");
                return false;
            }
            WaybillAmount waybillAmount = waybillAmountMap.get(waybill.getWaybillId());
            BigDecimal beforeAmount = waybill.getShow4DriverFreight();
            BigDecimal othersideBeforeAmount = waybill.getEstimateFreight();
            if( null != waybillAmount ){
                beforeAmount = waybillAmount.getLastVendorFreightWithTax();
                othersideBeforeAmount = waybillAmount.getLastCustomerFreightWithTax();
            }
            temp.setFreightWithTax(beforeAmount);
            temp.setOtherSideWithTax(othersideBeforeAmount);
            if( BigDecimal.ZERO.compareTo(adjustAmount.add(beforeAmount)) > 0 ){
                temp.setValidStatus(AdjustStatus.REJECTED.getCode());
                temp.setValidResult("调整之后的运单承运商税前金额不能小于0");
                return false;
            }
        }
        if( null == holder.getCeiling() ){
            BigDecimal ceiling = fetchAdjustWaybillAmountRange(Constants.ALLOW_CHANGE_PRICE_CEILING_LIMIT_KEY,"100000");
            holder.setCeiling(ceiling);
        }
        if( null == holder.getFloor() ){
            BigDecimal floor = fetchAdjustWaybillAmountRange(Constants.ALLOW_CHANGE_PRICE_FLOOR_LIMIT_KEY,"-100000");
            holder.setFloor(floor);
        }

        BigDecimal ceiling = holder.getCeiling();
        BigDecimal floor = holder.getFloor();
        boolean hasError = false;
        String errorReason = "";
        if( floor.compareTo(adjustForFreight) > 0 || ceiling.compareTo(adjustForFreight) < 0 ){
            hasError = true;
            temp.setAdjustForFreight(BigDecimal.ZERO);
            errorReason += "调整基础运费金额"+adjustForFreight.toPlainString() + ",";
        }

        if( floor.compareTo(adjustForCarry) > 0 || ceiling.compareTo(adjustForCarry) < 0 ){
            hasError = true;
            temp.setAdjustForCarry(BigDecimal.ZERO);
            errorReason += "装卸费金额"+adjustForCarry.toPlainString() + ",";
        }

        if( floor.compareTo(adjustForWorkload) > 0 || ceiling.compareTo(adjustForWorkload) < 0 ){
            hasError = true;
            temp.setAdjustForWorkload(BigDecimal.ZERO);
            errorReason += "超工作量运费金额"+adjustForWorkload.toPlainString() + ",";
        }

        if( floor.compareTo(adjustForUpstairs) > 0 || ceiling.compareTo(adjustForUpstairs) < 0 ){
            hasError = true;
            temp.setAdjustForUpstairs(BigDecimal.ZERO);
            errorReason += "上楼费金额"+adjustForUpstairs.toPlainString() + ",";
        }

        if( floor.compareTo(adjustForFine) > 0 || ceiling.compareTo(adjustForFine) < 0 ){
            hasError = true;
            temp.setAdjustForFine(BigDecimal.ZERO);
            errorReason += "罚款金额"+adjustForFine.toPlainString() + ",";
        }

        if( floor.compareTo(adjustForCargoLoss) > 0 || ceiling.compareTo(adjustForCargoLoss) < 0 ){
            hasError = true;
            temp.setAdjustForCargoLoss(BigDecimal.ZERO);
            errorReason += "货损货差费金额"+adjustForCargoLoss.toPlainString() + ",";
        }

        if( hasError ){
            temp.setValidStatus(AdjustStatus.REJECTED.getCode());
            temp.setValidResult(errorReason+"不在"+floor+"到+"+ceiling+"范围内,已重置为0,请调整金额到合理范围");
            return false;
        }

        temp.setAdjustForCargoLoss(adjustForCargoLoss);
        if( floor.compareTo(adjustAmount) > 0 || ceiling.compareTo(adjustAmount) < 0 ){
            hasError = true;
            temp.setValidStatus(AdjustStatus.REJECTED.getCode());
            temp.setValidResult("本次调整金额"+adjustAmount.toPlainString()+",不在"+floor+"到+"+ceiling+"范围内,已重置为0,请调整金额到合理范围");
            return false;
        }

        return true;
    }

    /**校验运单租户**/
    private boolean validWaybillTenant(final AdjustAttachVO vo, final AdjustForWaybillTemp temp, final Waybill waybill, final LoginEmployee loginUser) {
        if( null == waybill ){
            temp.setValidStatus(AdjustStatus.REJECTED.getCode());
            temp.setValidResult("没有查到对应的运单");
            return false;
        }
        temp.setWaybillId(waybill.getWaybillId());
        temp.setWaybillNo(waybill.getWaybillNo());
        if( !loginUser.getTenantId().equals(waybill.getTenantId()) ){
            temp.setValidStatus(AdjustStatus.REJECTED.getCode());
            temp.setValidResult("运单不属于当前登录租户,不能调整");
            return false;
        }
        return true;
    }

    /**
     * 校验运单承运商信息
     * 没有运单,直接结束
     * 承运商,对账后,没有承运商,则直接结束
     * */
    private boolean validWaybillVendorInfo(AdjustAttachVO vo, AdjustForWaybillTemp temp, AdjustItemValidHolder holder, Waybill waybill, LoginEmployee loginUser) {
        if( Waybill.ReceiveWay.TRANSFORM_BILL.getCode() == waybill.getReceiveWay() ) {
            temp.setVendorId(validAndSetIntegerValue(waybill.getVendorId()));
        }else{
            temp.setVendorId(validAndSetIntegerValue(waybill.getVehicleToVendor()));
        }
        // 非承运商不用考虑
        if( !AdjustMasterType.VENDOR.getCode().equals(vo.getAdjustForWho()) ){ return true; }

        // 对账后不用考虑
        if( !AdjustType.AFTER.getCode().equals(vo.getAdjustType()) ){ return true; }

        // 临时表 承运商设置逻辑
        if( Waybill.ReceiveWay.TRANSFORM_BILL.getCode() == waybill.getReceiveWay() ){
            if( null == waybill.getVendorId()){
                temp.setValidStatus(AdjustStatus.REJECTED.getCode());
                temp.setValidResult("运单没有承运商，请先补录运单的承运商信息");
                temp.setVendorId(0);
                return false;
            }
            holder.getVendorIds().add(String.valueOf(waybill.getVendorId()));
        }else{
            if( null == waybill.getVehicleToVendor() ){
                temp.setValidStatus(AdjustStatus.REJECTED.getCode());
                temp.setValidResult("运单没有承运商，请先补录运单的承运商信息");
                temp.setVendorId(0);
                return false;
            }
            holder.getVendorIds().add(String.valueOf(waybill.getVehicleToVendor()));
        }
        return true;
    }

    /**缓存waybill信息作为下一步使用**/
    private void holdWaybillInfo(AdjustAttachVO vo, AdjustForWaybillTemp temp, AdjustItemValidHolder holder, Waybill waybill) {
        holder.getAreaCodes().add(waybill.getAreaCode());
        // 记录没有对账单号的运单, 以及有对账单号的运单
        if( AdjustMasterType.CUSTOMER.getCode().equals(vo.getAdjustForWho())){
            if( StringUtils.isBlank(waybill.getReceivableReconcilicationNo()) ){
                holder.getNoReconciliationNos().add(waybill.getWaybillNo());
            }else{
                holder.getHasReconciliationNos().add(waybill.getWaybillNo());
            }
        }

        if( AdjustMasterType.VENDOR.getCode().equals(vo.getAdjustForWho())){
            if( StringUtils.isBlank(waybill.getReconciliationNo()) ){
                holder.getNoReconciliationNos().add(waybill.getWaybillNo());
            }else{
                holder.getHasReconciliationNos().add(waybill.getWaybillNo());
            }
        }

        // 对账前,不做对账单号记录
        if( AdjustType.BEFORE.getCode().equals(vo.getAdjustType()) ){
            // 客户侧/承运侧-对账前-统计客户ID
            holder.getCustomerIds().add(waybill.getCustomerId());
            return;
        }

        if( AdjustMasterType.CUSTOMER.getCode().equals(vo.getAdjustForWho()) && StringUtils.isNotBlank(waybill.getReceivableReconcilicationNo())){
            holder.getReconciliations().add(waybill.getReceivableReconcilicationNo());
        }

        if( AdjustMasterType.VENDOR.getCode().equals(vo.getAdjustForWho()) && StringUtils.isNotBlank(waybill.getReconciliationNo())){
            holder.getReconciliations().add(waybill.getReconciliationNo());
        }
    }

    /**校验:是否可生成结算单,可(true),不可(false)**/
    private void validFinalStatementForAdjust(AdjustAttachVO vo, String reconciliationNo, List<AdjustForWaybillTemp> list, AdjustItemValidHolder holder) {
        // 运单调整金额收集
        List<String> waybillNos = Lists.newArrayList();
        for (AdjustForWaybillTemp temp : list) {
            // 没通过校验的运单,不会发送给FMS去校验
            if(temp.getValidStatus() == 0){ continue; }
            waybillNos.add(temp.getWaybillNo());
        }

        Map<String,WayBillStatus> wayBillStatusMap = fmsCommonService.canCreateAdjust(reconciliationNo,waybillNos);

        for (AdjustForWaybillTemp temp : list){
            WayBillStatus wayBillStatus = wayBillStatusMap.get(temp.getWaybillNo());
            if( null == wayBillStatus ){ continue; }
            if( Integer.valueOf(1).equals(wayBillStatus.getIsFrozen()) ){
                temp.setValidStatus(AdjustStatus.REJECTED.getCode());
                temp.setValidResult("该运单在结算中或者已结算完毕,不可改价");
                holder.setHasLineError(true);
            }
        }
    }

    /**校验运单客户状态:未对账true**/
    private boolean validWaybillCustomerNonReconciliation(AdjustForWaybillTemp temp, Waybill waybill) {
        if(Integer.valueOf(Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode()).equals(waybill.getReceivableReconcilicationStatus())
                || Integer.valueOf(Waybill.ReconciliationStatus.IN_THE_ACCOUNT.getCode()).equals(waybill.getReceivableReconcilicationStatus())
                || StringUtils.isNotBlank(waybill.getReceivableReconcilicationNo())){
            temp.setValidStatus(AdjustStatus.REJECTED.getCode());
            temp.setValidResult("运单的对账状态与调整阶段不匹配");
            return false;
        }
        return true;
    }

    /**校验运单客户状态:已对账true**/
    private boolean validWaybillCustomerReconciliation(AdjustForWaybillTemp temp, Waybill waybill) {
        if(Integer.valueOf(Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode()).equals(waybill.getReceivableReconcilicationStatus())
                && StringUtils.isNotBlank(waybill.getReceivableReconcilicationNo())){
            return true;
        }
        temp.setValidStatus(AdjustStatus.REJECTED.getCode());
        temp.setValidResult("运单的对账状态与调整阶段不匹配");
        return false;
    }

    /**校验运单状态:未对账true**/
    private boolean validWaybillVendorNonReconsiliation(AdjustForWaybillTemp temp, Waybill waybill) {
        if(Integer.valueOf(Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode()).equals(waybill.getReconciliationStatus())
                || Integer.valueOf(Waybill.ReconciliationStatus.IN_THE_ACCOUNT.getCode()).equals(waybill.getReconciliationStatus())
                || StringUtils.isNotBlank(waybill.getReconciliationNo())){
            temp.setValidStatus(AdjustStatus.REJECTED.getCode());
            temp.setValidResult("运单的对账状态与调整阶段不匹配");
            return false;
        }
        return true;
    }

    /**校验运单状态:已对账true**/
    private boolean validWaybillVendorReconsiliation(AdjustForWaybillTemp temp, Waybill waybill) {
        if(Integer.valueOf(Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode()).equals(waybill.getReconciliationStatus())
                && StringUtils.isNotBlank(waybill.getReconciliationNo())){
            return true;
        }
        temp.setValidStatus(AdjustStatus.REJECTED.getCode());
        temp.setValidResult("运单的对账状态与调整阶段不匹配");
        return false;
    }

    /**校验可新建调整单的日期**/
    private boolean validAdjustEffectiveDate(AdjustAttachVO vo, AdjustForWaybillTemp temp, AdjustItemValidHolder holder, Waybill waybill) {
        if( null == waybill.getFinishTime() ){
            temp.setValidStatus(AdjustStatus.REJECTED.getCode());
            temp.setValidResult("运单为未完成运单，不需要使用调整单");
            return false;
        }
        // 对账后,不做此校验
        if( AdjustType.AFTER.getCode().equals(vo.getAdjustType()) ){ return true; }
        if( null == holder.getDayLimit() ){
            holder.setDayLimit(fetchAdjustDayLimit(String.valueOf(Constants.ALLOW_CHANGE_PRICE_DEFAULT_TIME_LIMIT)));
        }
        // 当前时间小于运单完成时间+1
        if( new Date().after(DateUtils.addDays(waybill.getFinishTime(),holder.getDayLimit())) ){
            return true;
        }
        temp.setValidStatus(AdjustStatus.REJECTED.getCode());
        temp.setValidResult("运单当前可在运单详情中调价，不需要使用调整单");
        return false;
    }

    /**获取全局调整单日期限制**/
    private Integer fetchAdjustDayLimit(String defaultValue) {
        // 运单完成日期判断
        String dayLimit = authCommonService.getOptionValue(Constants.ALLOW_CHANGE_PRICE_TIME_LIMIT_KEY,null,defaultValue);
        if( StringUtils.isBlank(dayLimit) || !NumberUtils.isNumber(dayLimit) ){
            // 如果没有配置运单可调整日期, 则默认为1天
            dayLimit = defaultValue;
        }
        return Integer.valueOf(dayLimit);
    }

    /**获取全局调整单日期限制**/
    private BigDecimal fetchAdjustWaybillAmountRange(String key, String defaultValue) {
        // 运单完成日期判断
        String amountCeiling = authCommonService.getOptionValue(key,null,defaultValue);
        if( StringUtils.isBlank(amountCeiling) || !NumberUtils.isNumber(amountCeiling) ){
            // 如果没有配置运单可调整日期, 则默认为1天
            amountCeiling = defaultValue;
        }
        return new BigDecimal(amountCeiling);
    }

    /**校验是否有正在审批中的调整单**/
    private boolean validNonCurrAdjust(AdjustAttachVO vo, AdjustForWaybillTemp temp, Map<Integer, Integer> waybillAdjustMasterMap, LoginEmployee loginUser) {
        if( Integer.valueOf(0).equals(temp.getWaybillId()) ){ return true; }
        Integer adjustId = waybillAdjustMasterMap.get(temp.getWaybillId());
        if( null == adjustId ){ return true; }
        // 当前调整单, 则跳过此校验
        if( adjustId.equals(vo.getAdjustId()) ){
            return true;
        }
        temp.setValidStatus(AdjustStatus.REJECTED.getCode());
        if( AdjustMasterType.CUSTOMER.getCode().equals(vo.getAdjustForWho()) ){
            temp.setValidResult("运单的客户侧运费正在调整中，请勿重复提交");
        }
        if( AdjustMasterType.VENDOR.getCode().equals(vo.getAdjustForWho()) ){
            temp.setValidResult("运单的承运侧运费正在调整中，请勿重复提交");
        }
        return false;
    }

    /**校验非0**/
    private boolean validNotZero(AdjustForWaybillTemp temp, BigDecimal adjustAmount) {
        if( BigDecimal.ZERO.compareTo(adjustAmount) != 0 ){ return true; }
        temp.setValidStatus(AdjustStatus.REJECTED.getCode());
        temp.setValidResult("本次调整金额=0，若不需要调价，请移除此运单");
        return false;
    }

    /**参数校验**/
    private void validParams(AdjustAttachVO vo,List<AdjustForWaybillTemp> list) {
        if( null == vo.getAdjustType() ){
            throw new BusinessException("adjustTypeNotNull","调整类型不能为空");
        }
        if( null == vo.getAdjustForWho() ){
            throw new BusinessException("adjustMasterTypeNotNull","调整主题类型不能为空");
        }
        if( CollectionUtils.isEmpty(list) ){
            throw new BusinessException("updataNotNull","运单数据为空,请补充数据后重新上传");
        }
    }

    /**补充运单信息**/
    private List<AdjustForWaybillVO> fillWaybillInfo(List<AdjustForWaybillTemp> list, LoginEmployee loginUser) {
        if(CollectionUtils.isEmpty(list) ){ return Lists.newArrayList(); }
        List<String> waybillNos = Lists.newArrayList();
        for (AdjustForWaybillTemp temp : list) {
            if( StringUtils.isNotBlank(temp.getWaybillNo()) ){
                waybillNos.add(temp.getWaybillNo());
            }
        }
        if( CollectionUtils.isEmpty(waybillNos) ){
            return Lists.newArrayList();
        }
        List<Waybill> waybills = waybillService.findByWaybillNos(waybillNos);
        if( CollectionUtils.isEmpty(waybills) ){
            return Lists.newArrayList();
        }

        Map<String,Waybill> waybillMap = Maps.newConcurrentMap();
        for (Waybill waybill : waybills) {
            waybillMap.put(waybill.getWaybillNo(),waybill);
        }

        List<AdjustForWaybillVO> result = Lists.newArrayList();
        for (AdjustForWaybillTemp temp : list) {
            AdjustForWaybillVO vo = new AdjustForWaybillVO();
            BeanUtils.copyProperties(temp,vo);
            // 运单信息: 运单金额
            Waybill waybill = waybillMap.get(temp.getWaybillNo());
            if( null == waybill ){
                result.add(vo);
                continue;
            }
            vo.setCustomerName(waybill.getCustomerName());
            vo.setProjectName(waybill.getProjectName());
            vo.setPlateNumber(waybill.getPlateNumber());
            vo.setDriverName(waybill.getDriverName());
            vo.setVendorName(waybill.getVendorName());
            vo.setAdjustFreight(BigDecimal.ZERO
                    .add(validAndSetBigDecimalValue(temp.getAdjustForFreight()))
                    .add(validAndSetBigDecimalValue(temp.getAdjustForCarry()))
                    .add(validAndSetBigDecimalValue(temp.getAdjustForWorkload()))
                    .add(validAndSetBigDecimalValue(temp.getAdjustForUpstairs()))
                    .add(validAndSetBigDecimalValue(temp.getAdjustForFine()))
                    .add(validAndSetBigDecimalValue(temp.getAdjustForCargoLoss())));
            vo.setAfterAdjustFreight(vo.getFreightWithTax().add(vo.getAdjustFreight()));
            // 客户名称
            if( null != waybill.getCustomerId() && StringUtils.isBlank(waybill.getCustomerName()) ){
                CustomerInfo customerInfo = customerInfoService.findCusInfoById(waybill.getCustomerId());
                if( null != customerInfo ){
                    vo.setCustomerName(customerInfo.getCustomerName());
                }
            }
            // 项目名称
            if( null != waybill.getProjectId() && StringUtils.isBlank(waybill.getProjectName()) ){
                Project project = projectService.getProjectV2(waybill.getProjectId());
                if( null != project ){
                    vo.setProjectName(project.getName());
                }
            }
            // 承运商名称
            if( null != temp.getVendorId() && StringUtils.isBlank(waybill.getVendorName()) ){
                Vendor vendor = vmsCommonService.loadVendorByVendorId(temp.getVendorId());
                if( null != vendor ){
                    vo.setVendorName(vendor.getVendorName());
                }
            }
            // 司机名称
            if( null != waybill.getDriverId() && StringUtils.isBlank(waybill.getDriverName()) ) {
                Driver driver = vmsCommonService.loadDriverByDriverId(waybill.getDriverId());
                if (null != driver) {
                    vo.setDriverName(driver.getName());
                }
            }
            result.add(vo);
        }
        return result;
    }

    /**获取对账单调整后的金额**/
    private BigDecimal fetchReceivableReconciliationAmountAfterAdjust(String reconciliationNo, List<AdjustForWaybillTemp> list, LoginEmployee loginUser) {
        log.info("获取对账单调整后金额,对账单号:{},调整单信息:{}",reconciliationNo, JSON.toJSONString(list));
        ReconcilicationForReceivable receivable = reconcilicationForReceivableService.findReconciliationByReconciliationNo(reconciliationNo);
        List<ReconcilicationForReceivableItem> items = reconcilicationForReceivableService.findReceivableItemsByReconciliationId(receivable.getReconcilicationId());
        List<Integer> waybillIds = Lists.newArrayList();
        // 运单ID集合收集
        for (ReconcilicationForReceivableItem item : items){
            waybillIds.add(item.getWaybillId());
        }
        // 运单金额收集
        WaybillAmountFilter filter = new WaybillAmountFilter();
        filter.setWaybillIds(waybillIds);
        List<WaybillAmount> amounts = waybillAmountService.findByFilter(filter,loginUser);
        Map<Integer,WaybillAmount> amountMap = Maps.newConcurrentMap();
        for (WaybillAmount amount : amounts){
            amountMap.put(amount.getWaybillId(),amount);
        }
        // 运单调整金额收集
        Map<Integer,AdjustForWaybillTemp> waybillTempMap = Maps.newConcurrentMap();
        for (AdjustForWaybillTemp temp : list) {
            waybillTempMap.put(temp.getWaybillId(),temp);
        }
        // 对账单-调整后金额计算
        List<Waybill> waybills = waybillService.findByWaybillIds(waybillIds,loginUser);
        BigDecimal afterAdjustAmount = BigDecimal.ZERO;
        for (Waybill waybill : waybills){
            WaybillAmount waybillAmount = amountMap.get(waybill.getWaybillId());
            BigDecimal finalWaybillAmount = waybill.getEstimateFreight();
            if( null != waybillAmount ){
                finalWaybillAmount = waybillAmount.getLastCustomerFreightWithTax();
            }
            AdjustForWaybillTemp temp = waybillTempMap.get(waybill.getWaybillId());
            if( null != temp ){
                finalWaybillAmount = finalWaybillAmount
                        .add(validAndSetBigDecimalValue(temp.getAdjustForFreight()))
                        .add(validAndSetBigDecimalValue(temp.getAdjustForCarry()))
                        .add(validAndSetBigDecimalValue(temp.getAdjustForWorkload()))
                        .add(validAndSetBigDecimalValue(temp.getAdjustForUpstairs()))
                        .add(validAndSetBigDecimalValue(temp.getAdjustForFine()))
                        .add(validAndSetBigDecimalValue(temp.getAdjustForCargoLoss()));
            }
            afterAdjustAmount = afterAdjustAmount.add(finalWaybillAmount);
        }
        log.info("获取对账单调整后金额,对账单号:{},调整后金额:{}",reconciliationNo, afterAdjustAmount);
        return afterAdjustAmount;
    }

    private BigDecimal validAndSetBigDecimalValue(BigDecimal amount) {
        return null != amount ? amount : BigDecimal.ZERO;
    }

    private String validAndSetStringValue(String value) {
        return StringUtils.isNotBlank(value)?value:"";
    }

    private Integer validAndSetIntegerValue(Integer value) {
        return null != value ? value : 0;
    }

}
