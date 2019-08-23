package com.juma.tgm.costReimbursed.service.impl;

import com.giants.common.SpringContextHelper;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.conf.domain.ConfParamOption;
import com.juma.conf.service.ConfParamService;
import com.juma.tgm.cms.service.ExportTaskService;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.costReimbursed.dao.CostReimbursedDao;
import com.juma.tgm.costReimbursed.domain.CostReimbursed;
import com.juma.tgm.costReimbursed.domain.CostReimbursed.CostReimbursedKey;
import com.juma.tgm.costReimbursed.domain.CostReimbursedExport;
import com.juma.tgm.costReimbursed.service.CostReimbursedService;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.driver.service.DriverService;
import com.juma.tgm.imageUploadManage.domain.ImageUploadManage;
import com.juma.tgm.imageUploadManage.service.ImageUploadManageService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.service.TruckService;
import com.juma.tgm.waybill.domain.DistanceAndPriceData;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillService;
import com.juma.vms.driver.enumeration.DriverTypeEnum;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CostReimbursedServiceImpl implements CostReimbursedService {

    private static final Logger log = LoggerFactory.getLogger(CostReimbursedServiceImpl.class);

    @Resource
    private CostReimbursedDao costReimbursedDao;
    @Resource
    private DriverService driverService;
    @Resource
    private TruckService truckService;
    @Resource
    private WaybillService waybillService;
    @Resource
    private ConfParamService confParamService;
    @Resource
    private ImageUploadManageService imageUploadManageService;
    @Resource
    private ExportTaskService exportTaskService;
    @Value("${dataBase.name}")
    private String dataBaseName;
    @Resource
    private WaybillCommonService waybillCommonService;
    @Resource
    private VmsCommonService vmsCommonService;

    @Override
    public Page<CostReimbursed> search(PageCondition pageCondition, LoginUser loginUser) {
        pageCondition.getFilters().put("tenantId", loginUser.getTenantId());
        int total = costReimbursedDao.searchCount(pageCondition);
        List<CostReimbursed> rows = costReimbursedDao.search(pageCondition);
        for (CostReimbursed costReimbursed : rows) {
            // 报销类型
            ConfParamOption option = confParamService.findParamOption(Constants.COST_REIMBURSED_TYPE,
                    costReimbursed.getCostReimbursedType() + "");
            if (null != option) {
                costReimbursed.setCostReimbursedTypeName(option.getOptionName());
            }

            Driver driver = driverService.getDriver(costReimbursed.getDriverId());
            if (null != driver) {
                costReimbursed.setDriverName(driver.getNickname());
                costReimbursed.setDriverPhone(driver.getContactPhone());
            }

            Truck truck = truckService.getTruck(costReimbursed.getTruckId());
            if (null != truck) {
                costReimbursed.setPlateNumber(truck.getPlateNumber());
            }

            // 图片信息
            List<ImageUploadManage> urlList = imageUploadManageService.listByRelationIdAndSign(
                    costReimbursed.getCostReimbursedId(),
                    ImageUploadManage.ImageUploadManageSign.COST_REIMBURSED.getCode());
            for (ImageUploadManage imageUploadManage : urlList) {
                costReimbursed.getListUrl().add(imageUploadManage.getImageUploadUrl());
            }
            costReimbursed.setCount(urlList.size());
        }
        return new Page<CostReimbursed>(pageCondition.getPageNo(), pageCondition.getPageSize(), total, rows);
    }

    @Override
    public CostReimbursed getCostReimbursed(Integer costReimbursedId) {
        if (null == costReimbursedId) {
            return null;
        }

        CostReimbursed costReimbursed = costReimbursedDao.get(costReimbursedId);
        if (null == costReimbursed) {
            return null;
        }

        // 报销类型
        ConfParamOption option = confParamService.findParamOption(Constants.COST_REIMBURSED_TYPE,
                costReimbursed.getCostReimbursedType() + "");
        if (null != option) {
            costReimbursed.setCostReimbursedTypeName(option.getOptionName());
        }
        return costReimbursed;
    }

    @Override
    public CostReimbursed findCostReimbursedAndUrlById(Integer costReimbursedId) {
        CostReimbursed costReimbursed = getCostReimbursed(costReimbursedId);
        if (null == costReimbursed) {
            return null;
        }

        // 图片信息
        List<ImageUploadManage> urlList = imageUploadManageService.listByRelationIdAndSign(
                costReimbursed.getCostReimbursedId(),
                ImageUploadManage.ImageUploadManageSign.COST_REIMBURSED.getCode());
        for (ImageUploadManage imageUploadManage : urlList) {
            costReimbursed.getListUrl().add(imageUploadManage.getImageUploadUrl());
        }
        return costReimbursed;
    }

    @Override
    public void insert(CostReimbursed costReimbursed, LoginUser loginUser) throws BusinessException {
        if (StringUtils.isBlank(costReimbursed.getCostReimbursedKey())) {
            throw new BusinessException("costReimbursedKeyRequired", "costReimbursed.error.costReimbursedKeyRequired");
        }

        if (null == costReimbursed.getCostReimbursedType()) {
            throw new BusinessException("costReimbursedTypeRequired",
                    "costReimbursed.error.costReimbursedTypeRequired");
        }

        if (null == costReimbursed.getCostProduceTime()) {
            throw new BusinessException("costProduceTimeRequired", "costReimbursed.error.costProduceTimeRequired");
        }

        if (null == costReimbursed.getReimbursedAmount()) {
            throw new BusinessException("reimbursedAmountRequired", "costReimbursed.error.reimbursedAmountRequired");
        }

        if (costReimbursed.getReimbursedAmount().compareTo(BigDecimal.ZERO) != 1) {
            throw new BusinessException("reimbursedAmountMustMoreThanZero",
                    "costReimbursed.error.reimbursedAmountMustMoreThanZero");
        }

        if (costReimbursed.getReimbursedAmount().compareTo(new BigDecimal("999999.99")) == 1) {
            throw new BusinessException("reimbursedAmountMoreThanMax",
                    "costReimbursed.error.reimbursedAmountMoreThanMax");
        }

        // 司机信息
        Driver driver = driverService.findDriverByUserId(loginUser.getUserId());
        if (null == driver) {
            throw new BusinessException("driverNotfound", "driver.error.not.found");
        }

        Integer driverType = vmsCommonService.findDriverRunType(driver.getDriverId());
        if (NumberUtils.compare(driverType, DriverTypeEnum.OWN_SALE.getCode()) != 0) {
            throw new BusinessException("driverNotOwnSale", "driver.error.driverNotOwnSale");
        }

        costReimbursed.setDriverId(driver.getDriverId());
        costReimbursed.setTenantId(loginUser.getTenantId());
        costReimbursed.setTenantCode(loginUser.getTenantCode());

        // 运单信息
        Waybill waybill = waybillService.getWaybill(costReimbursed.getWaybillId());
        if (null != waybill) {
            costReimbursed.setWaybillNo(waybill.getWaybillNo());

            // 车辆ID
            Truck truck = truckService.getTruck(waybill.getTruckId());
            if (null != truck) {
                costReimbursed.setTruckId(truck.getTruckId());
            }
        }

        costReimbursed.setCreateUserId(loginUser.getUserId());
        costReimbursed.setAuditResult(CostReimbursed.AuditResult.WAITING_AUDIT.getCode());
        if (null == costReimbursed.getDeclareTime()) {
            costReimbursed.setDeclareTime(new Date());
        }

        // 生成流水号
        costReimbursed.setCostReimbursedNo(buildCostReimbursedNo());

        costReimbursedDao.insert(costReimbursed);

        // 添加图片
        List<String> listUrl = costReimbursed.getListUrl();
        if (!listUrl.isEmpty()) {
            ImageUploadManage imageUploadManage = new ImageUploadManage();
            imageUploadManage
                    .setImageUploadManageSign(ImageUploadManage.ImageUploadManageSign.COST_REIMBURSED.getCode());
            imageUploadManage.setRelationId(costReimbursed.getCostReimbursedId());
            for (String url : listUrl) {
                imageUploadManage.setImageUploadUrl(url);
                imageUploadManageService.insert(imageUploadManage, loginUser);
            }
        }
    }

    @Override
    public void insertBatch(List<CostReimbursed> listCostReimbursed, LoginUser loginUser) throws BusinessException {
        for (CostReimbursed costReimbursed : listCostReimbursed) {
            try {
                costReimbursed.setCostReimbursedKey(CostReimbursed.CostReimbursedKey.DRIVER_COST_REIMBURSED.toString());
                insert(costReimbursed, loginUser);
            } catch (Exception e) {
                log.warn(e.getMessage(), e);
                String errorMsg = costReimbursed.getRecordNum() + "";
                if (e instanceof BusinessException) {
                    BusinessException be = (BusinessException) e;
                    errorMsg = errorMsg + "," + be.getErrorMessage();
                }

                throw new BusinessException("costReimbursedSaveError", "costReimbursed.error.costReimbursedSaveError",
                        errorMsg);
            }
        }
    }

    // 生成费用报销流水号，默认每天10W单
    private String buildCostReimbursedNo() {
        StringBuilder costReimbursedNo = new StringBuilder("");
        costReimbursedNo.append(DateUtil.format(DateUtil.YYYYMMDD_SIMPLE));
        Integer nextSeq = costReimbursedDao.getNextSequence(dataBaseName);
        Integer random = ThreadLocalRandom.current().nextInt(10000, 99999);
        costReimbursedNo.append(random);
        DecimalFormat format = new DecimalFormat("000000");
        costReimbursedNo.append(format.format(nextSeq % 100000));
        return costReimbursedNo.toString();
    }

    @Override
    public void update(CostReimbursed costReimbursed, LoginUser loginUser) throws BusinessException {
        if (null != costReimbursed.getReimbursedAmount()) {
            if (costReimbursed.getReimbursedAmount().compareTo(BigDecimal.ZERO) != 1) {
                throw new BusinessException("reimbursedAmountMustMoreThanZero",
                        "costReimbursed.error.reimbursedAmountMustMoreThanZero");
            }

            if (costReimbursed.getReimbursedAmount().compareTo(new BigDecimal("999999.99")) == 1) {
                throw new BusinessException("reimbursedAmountMoreThanMax",
                        "costReimbursed.error.reimbursedAmountMoreThanMax");
            }
        }
        costReimbursed.setLastUpdateUserId(loginUser.getUserId());
        costReimbursedDao.update(costReimbursed);
    }

    @Override
    public List<CostReimbursed> listByKeyAndTypeAndWaybillId(CostReimbursedKey costReimbursedKey,
            Integer costReimbursedType, Integer waybillId, LoginUser loginUser) {
        if (null == costReimbursedKey || null == waybillId) {
            return new ArrayList<CostReimbursed>();
        }

        Integer tenantId = null;
        if (null == loginUser) {
            Waybill waybill = waybillCommonService.getWaybillById(waybillId);
            if (null != waybill) {
                tenantId = waybill.getTenantId();
            }
        }

        CostReimbursed example = new CostReimbursed();
        example.setCostReimbursedKey(costReimbursedKey.toString());
        example.setTenantId(loginUser == null ? tenantId : loginUser.getTenantId());
        example.setCostReimbursedType(costReimbursedType);
        example.setWaybillId(waybillId);
        example.setIsDelete(false);
        return costReimbursedDao.findByExample(example);
    }

    @Override
    public List<CostReimbursed> listByKeyAndDriverId(CostReimbursedKey costReimbursedKey, Integer costReimbursedType,
            Integer driverId, LoginUser loginUser) {
        if (null == costReimbursedKey) {
            return new ArrayList<CostReimbursed>();
        }

        CostReimbursed example = new CostReimbursed();
        example.setCostReimbursedKey(costReimbursedKey.toString());
        example.setTenantId(loginUser.getTenantId());
        example.setCostReimbursedType(costReimbursedType);
        example.setDriverId(driverId);
        example.setIsDelete(false);
        return costReimbursedDao.findByExample(example);
    }

    @Override
    public CostReimbursed getByCostReimbursedNO(String costReimbursedNo) {
        return costReimbursedDao.getByCostReimbursedNo(costReimbursedNo);
    }

    @Override
    public void asyncCostReimbursedExport(final PageCondition pageCondition, final Integer exportTaskId,
            final LoginUser loginUser) throws Exception {
        SpringContextHelper.getSpringBean(TaskExecutor.class).execute(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                StringBuffer exceptionMsg = new StringBuffer("");
                try {
                    List<CostReimbursedExport> result = new ArrayList<CostReimbursedExport>();

                    pageCondition.setPageNo(1);
                    pageCondition.setPageSize(Integer.MAX_VALUE);
                    Page<CostReimbursed> page = search(pageCondition, loginUser);
                    // log.debug("司机导出page总数：{}", page.getTotal());
                    for (CostReimbursed costReimbursed : page.getResults()) {
                        CostReimbursedExport costReimbursedExport = new CostReimbursedExport();
                        costReimbursedExport.setCostReimbursedNo(costReimbursed.getCostReimbursedNo());
                        costReimbursedExport.setCostProduceTime(DateUtil.format(costReimbursed.getCostProduceTime()));
                        costReimbursedExport.setCount(costReimbursed.getCount());
                        costReimbursedExport.setDeclareTime(DateUtil.format(costReimbursed.getDeclareTime()));
                        costReimbursedExport.setDriverName(costReimbursed.getDriverName());
                        costReimbursedExport.setPlateNumber(costReimbursed.getPlateNumber());
                        costReimbursedExport.setWaybillNo(costReimbursed.getWaybillNo());
                        costReimbursedExport.setReimbursedAmount(costReimbursed.getReimbursedAmount());
                        ConfParamOption option = confParamService.findParamOption(Constants.COST_REIMBURSED_TYPE,
                                costReimbursed.getCostReimbursedType() + "");
                        if (null != option) {
                            costReimbursedExport.setCostReimbursedTypePlus(option.getOptionName());
                        }
                        result.add(costReimbursedExport);
                    }
                    exportTaskService.uploadExcelAndUpdateExportTask(exportTaskId, "costReimbursed", result, CostReimbursedExport.class, startTime, loginUser);
                } catch (Exception e) {
                    exportTaskService.failed(exportTaskId, exceptionMsg.toString(), loginUser);
                    log.warn(e.getMessage(), e);
                }
            }
        });
    }

    @Override
    public void recountThePrice(Integer waybillId, LoginUser loginUser) throws BusinessException {
        if (null == waybillId) {
            return;
        }
        DistanceAndPriceData data = waybillService.recountThePrice(waybillId, null, null, loginUser);

        Waybill waybill = new Waybill();
        waybill.setWaybillId(waybillId);
        waybill.setCalculatedFreight(data.getPrice());
        waybillCommonService.update(waybill, loginUser);
    }

}
