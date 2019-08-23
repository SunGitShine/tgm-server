package com.juma.tgm.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.giants.common.exception.BusinessException;
import com.juma.auth.conf.domain.BusinessArea;
import com.juma.auth.conf.service.BusinessAreaService;
import com.juma.auth.tenant.domain.Tenant;
import com.juma.auth.tenant.service.TenantService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.conf.domain.Region;
import com.juma.tgm.common.Constants;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.filiale.service.FilialeBillService;
import com.juma.tgm.landingWaybill.domain.AtFenceResultVo;
import com.juma.tgm.project.common.CustomerForProductAndDept;
import com.juma.tgm.project.domain.ProjectFreightRule;
import com.juma.tgm.project.domain.v2.Project;
import com.juma.tgm.project.enumeration.ValuationWayEnum;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.project.vo.ProjectBillVo;
import com.juma.tgm.scatteredWaybill.service.ScatteredWaybillService;
import com.juma.tgm.tools.service.CrmCommonService;
import com.juma.tgm.truck.domain.AdditionalFunction;
import com.juma.tgm.truck.domain.TruckType;
import com.juma.tgm.truck.service.AdditionalFunctionService;
import com.juma.tgm.truck.service.TruckTypeService;
import com.juma.tgm.waybill.domain.CityAdressData;
import com.juma.tgm.waybill.domain.DistanceAndPriceData;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillBo;
import com.juma.tgm.waybill.domain.WaybillDeliveryAddress;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.domain.WaybillReceiveAddress;
import com.juma.tgm.waybill.domain.transformbill.TransformBillMark;
import com.juma.tgm.waybill.domain.vo.ScatteredWaybillCreateVo;
import com.juma.tgm.waybill.domain.vo.WaybillCarrierVo;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillParamService;
import com.juma.tgm.waybill.service.WaybillService;
import com.juma.tgm.waybill.service.WaybillTransformToCarrierService;
import com.juma.vms.external.service.VmsService;
import com.juma.vms.vendor.domain.Vendor;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * 转承运商运单工具类
 *
 * @ClassName: WaybillTransformToCarrierUtils
 * @Description:
 * @author: liang
 * @date: 2018-08-28 18:44
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
@Component
public class WaybillTransformToCarrierUtils implements WaybillTransformToCarrierService {

    private static final Logger log = LoggerFactory.getLogger(WaybillTransformToCarrierUtils.class);
    private static final String LOG_MARKER = "transformBillUtils";


    @Resource
    private WaybillService waybillService;

    @Resource
    private FilialeBillService filialeBillService;

    @Resource
    private ScatteredWaybillService scatteredWaybillService;

    @Resource
    private CustomerInfoService customerInfoService;

    @Resource
    private ProjectService projectService;

    @Resource
    private AdditionalFunctionService additionalFunctionService;

    @Resource
    private WaybillCommonService waybillCommonService;

    @Resource
    private BusinessAreaService businessAreaService;

    @Resource
    private TenantService tenantService;

    @Resource
    private CrmCommonService crmCommonService;

    @Resource
    private WaybillParamService waybillParamService;

    @Resource
    private TruckTypeService truckTypeService;

    @Resource
    private VmsService vmsService;

    @Override
    public void dispatch(TransformBillMark bill, LoginUser loginUser) throws BusinessException {
        log.info(LOG_MARKER, "进入运单转运逻辑");
        TransformBillBo transformBillBo = new TransformBillBo(bill);

        //转运信息
        WaybillCarrierVo waybillCarrierVo = null;

        //确定要转运到的业务类型
        if (bill instanceof WaybillBo) {
            log.info(LOG_MARKER, "来自专车业务");
            //来自专车流程单
            WaybillBo currentBill = (WaybillBo) bill;
            waybillCarrierVo = currentBill.getWaybillCarrierVo();
            this.decidedBusinessType(currentBill.getWaybill(), waybillCarrierVo);

        } else if (bill instanceof ProjectBillVo) {
            log.info(LOG_MARKER, "来自项目业务");
            //来自项目单
            ProjectBillVo currentBill = (ProjectBillVo) bill;
            waybillCarrierVo = currentBill.getWaybillCarrierVo();
            this.decidedBusinessType(currentBill.getWaybill(), waybillCarrierVo);

        } else if (bill instanceof ScatteredWaybillCreateVo) {
            log.info(LOG_MARKER, "来自希地业务");
            //来自希地单
            ScatteredWaybillCreateVo currentBill = (ScatteredWaybillCreateVo) bill;
            waybillCarrierVo = currentBill.getWaybillCarrierVo();
            this.decidedBusinessType(currentBill.getWaybill(), waybillCarrierVo);

        } else {
            log.info(LOG_MARKER, "不支持的运单来源类型:" + JSON.toJSONString(bill));
            throw new BusinessException("TransformBillSourceTypeNotSupport", "errors.common.prompt", "不支持的转运单来源类型");
        }

        // 子公司不存在，不能转单
        if (null == waybillCarrierVo.getProjectId()) {
            // 非项目运单
            CustomerForProductAndDept customerForProductAndDept = crmCommonService.loadCustomerForProductAndDeptByCustomerId(waybillCarrierVo.getCustomerId(), loginUser);
            if (null == customerForProductAndDept || null == customerForProductAndDept.getDeparmentId()) {
                throw new BusinessException("departmentIdRequired", "errors.common.prompt", "客户没有分公司，请联系相关人员配置");
            }
            waybillCarrierVo.setDepartmentId(customerForProductAndDept.getDeparmentId());
            waybillCarrierVo.setPayToCompany(customerForProductAndDept.getDeparmentId());
        } else {
            // 项目运单
            Project project = projectService.getProjectV2(waybillCarrierVo.getProjectId());
            if (null == project) {
                throw new BusinessException("departmentIdRequired", "errors.common.prompt", "承运商项目不存在，请联系相关人员配置");
            }

            if (null == project.getPayToCompany()) {
                throw new BusinessException("departmentIdRequired", "errors.common.prompt", "承运商项目运营主体不存在，请联系相关人员配置");
            }

            if (null == project.getContractToCompany()) {
                throw new BusinessException("departmentIdRequired", "errors.common.prompt", "承运商项目签约主体不存在，请联系相关人员配置");
            }
            waybillCarrierVo.setDepartmentId(project.getContractToCompany());
            waybillCarrierVo.setPayToCompany(project.getPayToCompany());
        }

        LoginUser loginUserTemp = new LoginUser();
        BeanUtils.copyProperties(loginUser, loginUserTemp);

        // 按需要转运的业务类型组装运单参数
        //执行转单逻辑
        if (NumberUtils.compare(waybillCarrierVo.getBusinessType().getCode(), WaybillCarrierVo.BusinessType.JUMA_LOGISTICS.getCode()) == 0) {
            log.info(LOG_MARKER, "转运到专车业务");
            WaybillBo waybillBo = transformBillBo.toWaybillBo();
            this.transformToJuMaLogistics(waybillBo, loginUserTemp);
        } else if (NumberUtils.compare(waybillCarrierVo.getBusinessType().getCode(), WaybillCarrierVo.BusinessType.PROJECT.getCode()) == 0) {
            log.info(LOG_MARKER, "转运到项目业务");
            ProjectBillVo projectBillVo = transformBillBo.toProjectBillVo();
            this.transformToProjectWaybill(projectBillVo, loginUserTemp);
        } else if (NumberUtils.compare(waybillCarrierVo.getBusinessType().getCode(), WaybillCarrierVo.BusinessType.SCATTED.getCode()) == 0) {
            log.info(LOG_MARKER, "转运到希地业务");
            ScatteredWaybillCreateVo scatteredWaybillCreateVo = transformBillBo.toScatteredWaybillCreateVo();
            this.transformToScatteredWaybill(scatteredWaybillCreateVo, loginUserTemp);
        } else {
            log.info(LOG_MARKER, "不支持的运单转运类型:" + JSON.toJSONString(waybillCarrierVo));
            throw new BusinessException("TransformBillDestinationTypeNotSupport", "errors.common.prompt", "不支持的运单转运类型");
        }

    }


    /**
     * 确定承运商业务类型
     *
     * @param waybill
     * @param waybillCarrierVo
     */
    private void decidedBusinessType(Waybill waybill, WaybillCarrierVo waybillCarrierVo) {
        Tenant tenant = tenantService.loadTenant(waybillCarrierVo.getCustomerTenantId());
        if (tenant == null) throw new BusinessException("vendorNotExist", "errors.common.prompt", "租户不存在");
        //承运商是希地
        if (StringUtils.equals(tenant.getTenantKey(), Constants.TENANT_KEY_XIDI_LOGISTICS)) {
            waybillCarrierVo.setCarrierTenantKey(Constants.TENANT_KEY_XIDI_LOGISTICS);
            if (waybillCarrierVo.getProjectId() != null) {
                //--有项目
                waybillCarrierVo.setBusinessType(WaybillCarrierVo.BusinessType.PROJECT);
            } else {
                //--无项目
                waybillCarrierVo.setBusinessType(WaybillCarrierVo.BusinessType.SCATTED);
            }
            return;
        }

        //承运商是专车
        if (StringUtils.equals(tenant.getTenantKey(), Constants.TENANT_KEY_JUMA_LOGISTICS)) {
            waybillCarrierVo.setCarrierTenantKey(Constants.TENANT_KEY_JUMA_LOGISTICS);
            //----有项目
            if (waybillCarrierVo.getProjectId() != null) {
                //项目业务
                waybillCarrierVo.setBusinessType(WaybillCarrierVo.BusinessType.PROJECT);
            } else {
                //----无项目
                waybillCarrierVo.setBusinessType(WaybillCarrierVo.BusinessType.JUMA_LOGISTICS);
            }
            return;
        }

        //余下的全部是配送
        if (waybillCarrierVo.getProjectId() != null) {
            //----有项目
            waybillCarrierVo.setBusinessType(WaybillCarrierVo.BusinessType.PROJECT);
            return;
        }
        //----无项目
        waybillCarrierVo.setBusinessType(WaybillCarrierVo.BusinessType.JUMA_LOGISTICS);

    }


    /**
     * 执行转单
     *
     * @param waybill
     * @param waybillCarrierVo
     * @param waybillParam
     * @param truckRequire
     * @param loginUser
     */
    private void doTransform(Waybill waybill, WaybillCarrierVo waybillCarrierVo, WaybillParam waybillParam, TruckRequire truckRequire, LoginUser loginUser) {

        CustomerInfo customerInfo = customerInfoService.findCusInfoById(waybillCarrierVo.getCustomerId());
        if (customerInfo == null)
            throw new BusinessException("customerNotFoundError", "errors.common.prompt", "承运商客户不存在");
        if (StringUtils.isBlank(customerInfo.getLinkTel()))
            throw new BusinessException("customerPhoneNullError", "errors.common.prompt", "承运商客户联系人手机号不能为空");

        //通过客户确定运单业务区域
        waybill.setAreaCode(customerInfo.getAreaCode());

        //通过客户获取租户信息
        waybill.setTenantId(customerInfo.getTenantId());
        waybill.setTenantCode(customerInfo.getTenantCode());
        waybill.setCustomerManagerId(customerInfo.getCustomerManagerUserId());
        waybill.setCustomerId(customerInfo.getCustomerId());
        //税率
        truckRequire.setTaxRateValue(waybillCarrierVo.getBillTaxRate());
        truckRequire.setTaxRateId(null);
        //派车方式 --调度指派
        waybill.setReceiveWay(Waybill.ReceiveWay.MANUAL_ASSIGN.getCode());
        waybill.setWaybillSource(Waybill.WaybillSource.TRANSFORM_BILL.getCode());

        waybill.setRebateRate(waybillCarrierVo.getRebateRate());
        waybill.setProjectId(waybillCarrierVo.getProjectId());
        waybill.setDepartmentId(waybillCarrierVo.getDepartmentId());
        waybill.setPayToCompany(waybillCarrierVo.getPayToCompany());

        this.transformTruckTypeId(truckRequire, loginUser.getTenantId(), customerInfo.getTenantId());

        loginUser.setTenantId(customerInfo.getTenantId());
        loginUser.setTenantCode(customerInfo.getTenantCode());

        waybill.setWaybillId(null);
        waybill.setWaybillNo(null);
        waybill.setLastUpdateTime(null);
        waybill.setLastUpdateUserId(null);
        waybill.setCreateTime(null);
        waybill.setCreateTime(null);
        waybill.setVendorId(null);

        this.calculateTargetBillPrice(waybill, truckRequire, waybillCarrierVo, waybillParam);
    }

    /**
     * 租户间车型转换
     *
     * @param truckRequire
     * @param sourceTenantId
     * @param carrierTenantId
     * @return
     */
    private void transformTruckTypeId(TruckRequire truckRequire, Integer sourceTenantId, Integer carrierTenantId) {
        if (truckRequire.getTruckTypeId() == null)
            throw new BusinessException("truckTypeIdNullError", "errors.common.prompt", "用车要求车型为空");
        TruckType truckType = truckTypeService.getTruckType(truckRequire.getTruckTypeId());

        if (truckType == null)
            throw new BusinessException("sourceTruckTypeNotExistError", "errors.common.prompt", "车型不存在");

        TruckType carrierTruckType = truckTypeService.findByBoxAndLength(truckType.getVehicleBoxType(), truckType.getTruckLengthId(), carrierTenantId);
        if (carrierTruckType == null)
            throw new BusinessException("carrierTruckTypeNotExist", "errors.common.prompt", "承运商没有匹配的车辆类型");

        truckRequire.setTruckTypeId(carrierTruckType.getTruckTypeId());
    }


    /**
     * 专车运单转运
     *
     * @param waybillBo
     * @return
     */
    public Integer transformToJuMaLogistics(WaybillBo waybillBo, LoginUser loginUser) {

        //通过承运人和客户id和项目id获取承运商转运项目id
        Waybill waybill = waybillBo.getWaybill();
        WaybillCarrierVo waybillCarrierVo = waybillBo.getWaybillCarrierVo();
        WaybillParam waybillParam = waybillBo.getWaybillParam();
        TruckRequire truckRequire = waybillBo.getTruckRequire();

        /**
         * 源运单id
         */
        Integer sourceWaybillId = waybill.getWaybillId();

        waybillBo.setWaybillCarrierVo(null);

        this.doTransform(waybill, waybillCarrierVo, waybillParam, truckRequire, loginUser);

        waybill.setStatusView(Waybill.StatusView.WATING_RECEIVE.getCode());
        waybill.setStatus(Waybill.Status.WATING_RECEIVE.getCode());

        //搬运转换
        this.chooseCarryType(truckRequire, waybillCarrierVo);

        waybillBo.setWaybillCarrierVo(null);

        Integer transformBillId = waybillService.createWaybill(waybillBo, Waybill.WaybillSource.TRANSFORM_BILL, loginUser);

        this.saveTransformBillLinkId(sourceWaybillId, transformBillId, loginUser);

        return transformBillId;
    }


    /**
     * @param truckRequire     原用车要求
     * @param waybillCarrierVo
     */
    private void chooseCarryType(TruckRequire truckRequire, WaybillCarrierVo waybillCarrierVo) {
        if (truckRequire == null) return;
        String funStr = truckRequire.getAdditionalFunctionIds();
        if (StringUtils.isEmpty(funStr)) return;
        String[] fids = StringUtils.split(funStr, ",");
        if (ArrayUtils.isEmpty(fids)) return;
        Arrays.sort(fids);
        //配送标记的为空
        //希地标记
        //专车标记

        //有小工/司机搬运转配送
        //有小工/司机搬运转希地
        AdditionalFunction driverCarryFun = additionalFunctionService.findAdditionFunctionByKey(AdditionalFunction.FunctionKeys.DRIVER_CARRY.name());
        AdditionalFunction laborerCarryFun = additionalFunctionService.findAdditionFunctionByKey(AdditionalFunction.FunctionKeys.LABORER_CARRY.name());
        AdditionalFunction carryFun = additionalFunctionService.findAdditionFunctionByKey(AdditionalFunction.FunctionKeys.CARRY.name());
        if ((Arrays.binarySearch(fids, driverCarryFun.getAdditionalFunctionId().toString()) >= 0 || Arrays.binarySearch(fids, laborerCarryFun.getAdditionalFunctionId().toString()) >= 0)
            && (StringUtils.isEmpty(waybillCarrierVo.getCarrierTenantKey()) || StringUtils.equals(waybillCarrierVo.getCarrierTenantKey(), Constants.TENANT_KEY_XIDI_LOGISTICS))) {
            //转搬运
            this.replaceDriverCarryOrLaborCarry2Carry(truckRequire);
        } else if ((Arrays.binarySearch(fids, carryFun.getAdditionalFunctionId().toString()) >= 0) && (StringUtils.equals(waybillCarrierVo.getCarrierTenantKey(), Constants.TENANT_KEY_JUMA_LOGISTICS))) {
            //有搬运转转专车
            this.replaceCarry2DriverCarry(truckRequire);
        }

    }

    /**
     * 搬运->司机搬运
     *
     * @param truckRequire
     */
    private void replaceCarry2DriverCarry(TruckRequire truckRequire) {

        String funStr = truckRequire.getAdditionalFunctionIds();

        if (truckRequire != null && StringUtils.isNotBlank(funStr)) {
            String[] fids = StringUtils.split(funStr, ",");
            if (ArrayUtils.isNotEmpty(fids)) {
                AdditionalFunction carryFun = additionalFunctionService.findAdditionFunctionByKey(AdditionalFunction.FunctionKeys.CARRY.name());
                Arrays.sort(fids);
                int carryIdx = Arrays.binarySearch(fids, carryFun.getAdditionalFunctionId().toString());
                if (carryIdx >= 0) {
                    //如果有搬运则转为司机搬运
                    AdditionalFunction driverCarryFun = additionalFunctionService.findAdditionFunctionByKey(AdditionalFunction.FunctionKeys.DRIVER_CARRY.name());
                    fids[carryIdx] = driverCarryFun.getAdditionalFunctionId().toString();

                    //替换原有用车要求
                    funStr = ArrayUtils.toString(fids);
                    funStr = funStr.replace("{", "").replace("}", "");

                    truckRequire.setAdditionalFunctionIds(funStr);
                }
            }
        }
    }

    /**
     * 计算承运商的费用
     *
     * @param waybill
     * @param truckRequire
     */
    private void calculateCarrierFee(Waybill waybill, TruckRequire truckRequire) {
        if (waybill.getEstimateFreight() == null) return;

        //税后费用
        BigDecimal withTaxFee = waybill.getEstimateFreight();
        BigDecimal withoutTaxFee = withTaxFee;
        BigDecimal rebateRate = waybill.getRebateRate();
        BigDecimal taxRate = truckRequire.getTaxRateValue();
        BigDecimal rebateFee = null;

        if (taxRate != null) {
            withoutTaxFee = withTaxFee.divide(BigDecimal.ONE.add(taxRate), 2, RoundingMode.HALF_UP);
        }

        if (rebateRate == null) {
            waybill.setAfterTaxFreight(withoutTaxFee);
            waybill.setShow4DriverFreight(withoutTaxFee);
        } else {
            waybill.setAfterTaxFreight(withoutTaxFee);

            rebateFee = withTaxFee.multiply(rebateRate);
            BigDecimal driverPrice = withoutTaxFee.subtract(rebateFee);

            waybill.setRebateFee(rebateFee);
            waybill.setShow4DriverFreight(driverPrice);
        }

    }


    /**
     * 项目运单转运
     *
     * @param projectBillVo
     * @return
     */
    public List<Integer> transformToProjectWaybill(ProjectBillVo projectBillVo, LoginUser loginUser) {

        Waybill waybill = projectBillVo.getWaybill();
        WaybillCarrierVo waybillCarrierVo = projectBillVo.getWaybillCarrierVo();
        WaybillParam waybillParam = projectBillVo.getWaybillParam();
        TruckRequire truckRequire = projectBillVo.getTruckRequire();
        com.juma.tgm.project.domain.v2.Project project = projectBillVo.getProject();

        /**
         * 源运单id
         */
        Integer sourceWaybillId = waybill.getWaybillId();
        projectBillVo.setWaybillCarrierVo(null);

        //搬运转换
        this.chooseCarryType(truckRequire, waybillCarrierVo);

        this.doTransform(waybill, waybillCarrierVo, waybillParam, truckRequire, loginUser);

        if (StringUtils.equals(waybillCarrierVo.getCarrierTenantKey(), Constants.TENANT_KEY_XIDI_LOGISTICS)) {
            this.buildXidiBusinessData(projectBillVo.getDeliveryAddress(), projectBillVo.getReceiveAddress(), loginUser, waybill, truckRequire);
        }

        project.setProjectId(waybillCarrierVo.getProjectId());

        projectBillVo.setWaybillCarrierVo(null);

        //运费规则覆盖
        waybillParam.setProjectFreightRuleJson(null);

        //计算承运商费用
        List<Integer> transformBillIds = filialeBillService.createProjectBill(projectBillVo, loginUser);

        if (CollectionUtils.size(transformBillIds) != 1) {
            log.info(LOG_MARKER, "项目单转运单建单数量错误");
            throw new BusinessException("waybillAmountCreateError", "errors.common.prompt", "项目单转运单建单数量错误");
        }
        this.saveTransformBillLinkId(sourceWaybillId, transformBillIds.get(0), loginUser);

        return transformBillIds;
    }

    /**
     * 小工搬运/司机搬运->搬运
     *
     * @param truckRequire
     */
    private void replaceDriverCarryOrLaborCarry2Carry(TruckRequire truckRequire) {
        String funStr = truckRequire.getAdditionalFunctionIds();

        if (truckRequire != null && StringUtils.isNotBlank(funStr)) {
            String[] fids = StringUtils.split(funStr, ",");
            if (ArrayUtils.isNotEmpty(fids)) {
                AdditionalFunction driverCarryFun = additionalFunctionService.findAdditionFunctionByKey(AdditionalFunction.FunctionKeys.DRIVER_CARRY.name());
                AdditionalFunction laborCarryFun = additionalFunctionService.findAdditionFunctionByKey(AdditionalFunction.FunctionKeys.LABORER_CARRY.name());
                Arrays.sort(fids);
                int driverCarryIdx = Arrays.binarySearch(fids, driverCarryFun.getAdditionalFunctionId().toString());
                int laborCarryIdx = Arrays.binarySearch(fids, laborCarryFun.getAdditionalFunctionId().toString());
                if (driverCarryIdx >= 0 || laborCarryIdx >= 0) {
                    fids = (String[]) ArrayUtils.removeElement(fids, driverCarryFun.getAdditionalFunctionId().toString());
                    fids = (String[]) ArrayUtils.removeElement(fids, laborCarryFun.getAdditionalFunctionId().toString());
                    //如果有司机/小工搬运则替换为搬运
                    AdditionalFunction carryFun = additionalFunctionService.findAdditionFunctionByKey(AdditionalFunction.FunctionKeys.CARRY.name());
                    fids = (String[]) ArrayUtils.add(fids, carryFun.getAdditionalFunctionId().toString());

                    //替换原有用车要求
                    funStr = ArrayUtils.toString(fids);
                    funStr = funStr.replace("{", "").replace("}", "");

                    truckRequire.setAdditionalFunctionIds(funStr);
                }
            }
        }
    }

    /**
     * 希地运单转运
     *
     * @param scatteredWaybillCreateVo
     * @return
     */
    public List<Integer> transformToScatteredWaybill(ScatteredWaybillCreateVo scatteredWaybillCreateVo, LoginUser loginUser) {
        Waybill waybill = scatteredWaybillCreateVo.getWaybill();
        TruckRequire truckRequire = scatteredWaybillCreateVo.getTruckRequire();
        WaybillCarrierVo waybillCarrierVo = scatteredWaybillCreateVo.getWaybillCarrierVo();
        WaybillParam waybillParam = scatteredWaybillCreateVo.getWaybillParam();

        /**
         * 源运单id
         */
        Integer sourceWaybillId = waybill.getWaybillId();

        scatteredWaybillCreateVo.setWaybillCarrierVo(null);
        // 用车要求转换
        this.chooseCarryType(truckRequire, waybillCarrierVo);
        this.doTransform(waybill, waybillCarrierVo, waybillParam, truckRequire, loginUser);

        this.buildXidiBusinessData(scatteredWaybillCreateVo.getSrcAddress(), scatteredWaybillCreateVo.getDestAddress(), loginUser, waybill, truckRequire);
        //转运单状态直接为希地调度指派
        waybill.setStatus(Waybill.Status.NO_DRIVER_ANSWER.getCode());
        waybill.setStatusView(Waybill.StatusView.WATING_RECEIVE.getCode());

//        this.calculateCarrierFee(waybill, truckRequire);

        List<Integer> transformBillIds = scatteredWaybillService.createScatteredWaybillForCustomerManager(scatteredWaybillCreateVo, loginUser);
        if (CollectionUtils.size(transformBillIds) != 1) {
            log.info(LOG_MARKER, "希地转运单建单数量错误");
            throw new BusinessException("waybillAmountCreateError", "errors.common.prompt", "希地转运单建单数量错误");
        }
        this.saveTransformBillLinkId(sourceWaybillId, transformBillIds.get(0), loginUser);

        return transformBillIds;
    }

    /**
     * 希地业务参数
     *
     * @param srcAddress
     * @param destAddress
     * @param loginUser
     * @param waybill
     * @param truckRequire
     */
    private void buildXidiBusinessData(List<WaybillDeliveryAddress> srcAddress, List<WaybillReceiveAddress> destAddress, LoginUser loginUser, Waybill waybill, TruckRequire truckRequire) {
        CityAdressData sourceCityAddress = this.buildWaybillDeliveryAddress2CityAddressData(srcAddress);
        List<CityAdressData> destCityAddress = this.buildWaybillReceiveAddress2CityAdressData(destAddress);
        AtFenceResultVo atFenceResult = scatteredWaybillService.isAtFenceArea(sourceCityAddress, destCityAddress, loginUser);
        if (atFenceResult != null) {
            //禁货区域
            if (atFenceResult.isAtForbiddenArea()) {
                StringBuilder tip = new StringBuilder("");
                if (NumberUtils.compare(atFenceResult.getForBiddenType().getCode(), AtFenceResultVo.ForbiddenType.sourceArea.getCode()) == 0) {
                    tip.append(atFenceResult.getForBiddenType().getDesc());
                } else if (NumberUtils.compare(atFenceResult.getForBiddenType().getCode(), AtFenceResultVo.ForbiddenType.destinationArea.getCode()) == 0) {
                    tip.append("第" + atFenceResult.getForbiddenAreaIndex() + "个配送地");
                } else {
                    throw new BusinessException("atFenceTypeError", "errors.common.prompt", "禁货入城区域类型错误");
                }
                tip.append("在禁货区");

                throw new BusinessException("deliveryAddressError", "errors.common.prompt", tip.toString());
            }

            //是否在业务区域内
            if (!atFenceResult.getAtBusinessArea()) {
                throw new BusinessException("deliveryAddressBusinessError", "errors.common.prompt", "该地区暂未开通业务");
            }

            //希地单需要判断入城证（入城区域）
            this.addEntryLicenseFunction(atFenceResult.getAtCity(), truckRequire);

            DistanceAndPriceData rst = new DistanceAndPriceData();
            this.buildRegionData(sourceCityAddress, destCityAddress, rst, loginUser);
            //预估距离
            waybill.setEstimateDistance(rst.getDistance());
            //regionCode
            waybill.setRegionCode(rst.getRegionCode());
            //高速费用
            waybill.setTolls(rst.getTolls());
        }
    }


    /**
     * 取货地转城市地址信息
     * for 查询入城/禁货区域
     *
     * @param srcAddress 取货地
     * @return
     */
    private CityAdressData buildWaybillDeliveryAddress2CityAddressData(List<WaybillDeliveryAddress> srcAddress) {

        if (CollectionUtils.isEmpty(srcAddress))
            throw new BusinessException("srcAddressNullError", "errors.paramCanNotNullWithName", "转运单取货地");

        WaybillDeliveryAddress deliveryAddress = srcAddress.get(0);

        CityAdressData cityAdressData = new CityAdressData();

        cityAdressData.setCoordinate(deliveryAddress.getCoordinates());
        cityAdressData.setRegionCode(deliveryAddress.getRegionCode());
        cityAdressData.setAddress(deliveryAddress.getAddressName());
        cityAdressData.setAddressDetail(deliveryAddress.getAddressDetail());
        cityAdressData.setCity(deliveryAddress.getCityname());

        return cityAdressData;
    }

    /**
     * 配送地转城市地址信息
     * for 查询入城/禁货区域
     *
     * @param destAddress
     * @return
     */
    private List<CityAdressData> buildWaybillReceiveAddress2CityAdressData(List<WaybillReceiveAddress> destAddress) {
        if (CollectionUtils.isEmpty(destAddress))
            throw new BusinessException("srcAddressNullError", "errors.paramCanNotNullWithName", "转运单配送地");

        List<CityAdressData> destCityAddresses = new ArrayList<>();

        CityAdressData cityAdressData = null;

        for (WaybillReceiveAddress receiveAddress : destAddress) {
            cityAdressData = new CityAdressData();

            cityAdressData.setCity(receiveAddress.getCityname());
            cityAdressData.setAddressDetail(receiveAddress.getAddressDetail());
            cityAdressData.setAddress(receiveAddress.getAddressName());
            cityAdressData.setRegionCode(receiveAddress.getRegionCode());
            cityAdressData.setCoordinate(receiveAddress.getCoordinates());

            destCityAddresses.add(cityAdressData);
        }

        return destCityAddresses;
    }

    /**
     * 用车要求添加入城证
     *
     * @param inCity
     * @param truckRequire
     */
    private void addEntryLicenseFunction(boolean inCity, TruckRequire truckRequire) {
        if (!inCity) return;

        if (truckRequire == null) return;
        AdditionalFunction function = additionalFunctionService.findAdditionFunctionByKey(AdditionalFunction.FunctionKeys.ENTRY_LICENSE.name());

        if (function == null) {
            log.info("找不到入城证配置:{}", new String[]{AdditionalFunction.FunctionKeys.ENTRY_LICENSE.name()});
            return;
        }

        //没有填用车要求
        if (StringUtils.isBlank(truckRequire.getAdditionalFunctionIds())) {
            truckRequire.setAdditionalFunctionIds(function.getAdditionalFunctionId() + "");
            return;
        }

        //填写了用车要求
        //是否有入城证id
        String[] ids = StringUtils.split(truckRequire.getAdditionalFunctionIds(), ",");

        Arrays.sort(ids);
        if (Arrays.binarySearch(ids, function.getAdditionalFunctionId() + "") > 0) return;

        String[] nIds = (String[]) ArrayUtils.add(ids, function.getAdditionalFunctionId() + "");

        StringBuffer sb = new StringBuffer();
        for (String id : nIds) {
            sb.append(id);
            sb.append(",");
        }
        String target = StringUtils.removeEnd(sb.toString(), ",");
        truckRequire.setAdditionalFunctionIds(target);
    }

    /**
     * 获取距离区域信息
     *
     * @param srcAddress
     * @param destAddresses
     * @param rst
     * @param loginUser
     */
    private void buildRegionData(CityAdressData srcAddress, List<CityAdressData> destAddresses, DistanceAndPriceData rst, LoginUser loginUser) {
        DistanceAndPriceData distanceData = waybillCommonService.getGaodeMapInfo(srcAddress, destAddresses);

        if (distanceData == null) return;

        rst.setDistance(distanceData.getDistance());
        rst.setDuration(distanceData.getDuration());
        rst.setRegionCode(distanceData.getRegionCode());
        rst.setTollDistance(distanceData.getTollDistance());
        rst.setTolls(distanceData.getTolls());

        //获取业务区域
        if (distanceData.getRegion() == null) return;

        Region region = distanceData.getRegion();
        rst.setRegionCode(StringUtils.left(region.getRegionCode(), 5));
        BusinessArea businessArea = businessAreaService.loadBelongingBusinessArea(loginUser.getTenantId(), region);
        distanceData.setRegion(null);
        if (businessArea == null) {
            //找不到业务区域则将运单归属到总部
            businessArea = businessAreaService.loadLogicBusinessArea("00", loginUser);
        }

        if (businessArea == null) return;
        //行政区域到市一级
        rst.setWaybillAreaCode(businessArea.getAreaCode());

    }


    /**
     * 转运参数校验
     *
     * @param waybillCarrierVo
     */
    public void transformBillCheck(WaybillCarrierVo waybillCarrierVo) throws BusinessException {
        if (waybillCarrierVo == null)
            throw new BusinessException("waybillCarrierVoNullError", "errors.paramCanNotNullWithName", "转承运商参数");

        //承运商的客户
        if (waybillCarrierVo.getCustomerId() == null)
            throw new BusinessException("carrierCustomerIdNullError", "errors.paramCanNotNullWithName", "承运商客户");
        //承运商客户的租户
        if (waybillCarrierVo.getCustomerTenantId() == null)
            throw new BusinessException("carrierCustomerTenantIdNullError", "errors.paramCanNotNullWithName", "承运商客户所属租户");

        if (waybillCarrierVo.getVendorId() == null)
            throw new BusinessException("carrierIdNullError", "errors.paramCanNotNullWithName", "承运商");

        if (NumberUtils.compare(waybillCarrierVo.getSettlementType(), WaybillCarrierVo.TransformCarrierSettlementType.SETTLEMENT_TYPE_FEE.getCode()) == 0) {
            //转运费方式结算
            //转运费不能空
            if (waybillCarrierVo.getVendorFees() == null)
                throw new BusinessException("transformVendorFeesNullError", "errors.paramCanNotNullWithName", "承运商转运费参数");
        } else if (NumberUtils.compare(waybillCarrierVo.getSettlementType(), WaybillCarrierVo.TransformCarrierSettlementType.SETTLEMENT_TYPE_RATE.getCode()) == 0) {
            //费率方式结算
            //转运费率不能为空
            if (waybillCarrierVo.getVendorFeeRate() == null)
                throw new BusinessException("transformVendorFeeRateNullError", "errors.paramCanNotNullWithName", "承运商转运费率参数");
        } else {
            throw new BusinessException("transformCarrySettlementTypeError", "errors.paramErrorWithName", "转运结算方式");
        }

    }


    /**
     * 回写转运单关联id到源运单
     *
     * @param sourceWaybillId
     * @param transformBillId
     */
    private void saveTransformBillLinkId(Integer sourceWaybillId, Integer transformBillId, LoginUser loginUser) {
        WaybillParam sourceWaybillParam = waybillParamService.findByWaybillId(sourceWaybillId);
        if (sourceWaybillParam == null) {
            log.info(LOG_MARKER, "waybillParam不存在,运单号:{}", sourceWaybillId);
            throw new BusinessException("sourceWaybillIdNotExist", "errors.common.prompt", "waybillParam不存在");
        }
        //转运单关联
        sourceWaybillParam.setTransformBillLinkId(transformBillId);

        waybillParamService.update(sourceWaybillParam, loginUser);

    }

    @Override
    public void saveTransformBillParam(Waybill waybill, WaybillParam sourceWaybillParam, WaybillCarrierVo waybillCarrierVo, LoginUser loginUser) throws BusinessException {
        //保存承运商信息
        Vendor vendor = vmsService.loadByVenorIdTenant(waybillCarrierVo.getVendorId(), loginUser.getTenantId());

        if (vendor == null) {
            log.info(LOG_MARKER, "承运商不存在,承运商id:{}", waybillCarrierVo.getVendorId());
            throw new BusinessException("vendorNotExist", "errors.common.prompt", "承运商不存在");
        }
        waybill.setVendorId(vendor.getVendorId());

        //甲方结算价计算
        this.calculateSourceBillSettlementPrice(waybill, sourceWaybillParam, waybillCarrierVo);

        waybillCommonService.update(waybill, loginUser);

        //根据转运结算方式确定转运费用
        if (NumberUtils.compare(waybillCarrierVo.getSettlementType(), WaybillCarrierVo.TransformCarrierSettlementType.SETTLEMENT_TYPE_RATE.getCode()) == 0) {
            //服务费率方式
            sourceWaybillParam.setTransformBillVendorFeeRate(waybillCarrierVo.getVendorFeeRate());
            waybillParamService.update(sourceWaybillParam, loginUser);
        }

    }

    /**
     * 计算甲方结算价
     *
     * @param waybill
     * @param waybillCarrierVo
     */
    private void calculateSourceBillSettlementPrice(Waybill waybill, WaybillParam waybillParam, WaybillCarrierVo waybillCarrierVo) {

        //结算价
        BigDecimal carrierPrice = null;

        if (waybill.getProjectId() == null) {
            //非项目
            if (NumberUtils.compare(waybillCarrierVo.getSettlementType(), WaybillCarrierVo.TransformCarrierSettlementType.SETTLEMENT_TYPE_FEE.getCode()) == 0) {
                //----指定费用
                //--------甲方结算价 = 填写的费用
                carrierPrice = waybillCarrierVo.getVendorFees();

            } else if (NumberUtils.compare(waybillCarrierVo.getSettlementType(), WaybillCarrierVo.TransformCarrierSettlementType.SETTLEMENT_TYPE_RATE.getCode()) == 0) {
                //----按费率
                //--------甲方结算价 = 预估运费 * (1 - 费率)
                carrierPrice = waybill.getEstimateFreight().multiply(BigDecimal.ONE.subtract(waybillCarrierVo.getVendorFeeRate()));
            } else {
                log.info("转运单结算方式错误;{}", JSON.toJSONString(waybillCarrierVo));
                throw new BusinessException("transformBillSettlementTypeError", "errors.common.prompt", "转运单结算方式错误");
            }

        } else {
            //项目
            if (NumberUtils.compare(waybillCarrierVo.getSettlementType(), WaybillCarrierVo.TransformCarrierSettlementType.SETTLEMENT_TYPE_FEE.getCode()) == 0) {
                //----指定费用
                //--------甲方结算价 = 填写的价格
                carrierPrice = waybillCarrierVo.getVendorFees();

            } else if (NumberUtils.compare(waybillCarrierVo.getSettlementType(), WaybillCarrierVo.TransformCarrierSettlementType.SETTLEMENT_TYPE_RATE.getCode()) == 0) {
                //----按费率
                //--------甲方结算价 = 填写工作量后计算
                //处理项目一口价的情况
                boolean flag = this.isProjectFixedPrice(waybillParam);
                if (flag) {
                    carrierPrice = waybill.getEstimateFreight().multiply(BigDecimal.ONE.subtract(waybillCarrierVo.getVendorFeeRate()));
                }
            } else {
                log.info("转运单结算方式错误;{}", JSON.toJSONString(waybillCarrierVo));
                throw new BusinessException("transformBillSettlementTypeError", "errors.common.prompt", "转运单结算方式错误");
            }

        }

        waybill.setShow4DriverFreight(carrierPrice);
    }


    /**
     * 是否为一口价
     *
     * @param waybillParam
     */
    private boolean isProjectFixedPrice(WaybillParam waybillParam) {
        if (StringUtils.isBlank(waybillParam.getProjectFreightRuleJson())) return false;

        ProjectFreightRule projectFreightRule = JSONObject.parseObject(waybillParam.getProjectFreightRuleJson(), ProjectFreightRule.class);

        if (projectFreightRule == null) return false;

        if (projectFreightRule.getValuationWay() == null) return false;

        if (NumberUtils.compare(projectFreightRule.getValuationWay(), ValuationWayEnum.FIXED_PRICE.getCode()) == 0) {
            return true;
        }

        return false;
    }


    /**
     * 计算乙方费用信息
     *
     * @param waybill
     * @param truckRequire
     * @param waybillCarrierVo
     */
    private void calculateTargetBillPrice(Waybill waybill, TruckRequire truckRequire, WaybillCarrierVo waybillCarrierVo, WaybillParam waybillParam) {

        //结算价

        //预估运费
        BigDecimal targetEstimatePrice = null;
        //费用信息
        //原运单非项目计价
        if (waybill.getProjectId() == null) {
            if (NumberUtils.compare(waybillCarrierVo.getSettlementType(), WaybillCarrierVo.TransformCarrierSettlementType.SETTLEMENT_TYPE_RATE.getCode()) == 0) {
                //转运费率方式
                //----乙方预估运费 = 甲方结算价
                targetEstimatePrice = waybill.getShow4DriverFreight();
            } else if (NumberUtils.compare(waybillCarrierVo.getSettlementType(), WaybillCarrierVo.TransformCarrierSettlementType.SETTLEMENT_TYPE_FEE.getCode()) == 0) {
                //指定费用方式
                //----乙方预估运费 = 甲方结算价
                targetEstimatePrice = waybill.getShow4DriverFreight();
            } else {
                log.info("转运单结算方式错误;{}", JSON.toJSONString(waybillCarrierVo));
                throw new BusinessException("transformBillSettlementTypeError", "errors.common.prompt", "转运单结算方式错误");
            }

        } else {
            //原运单项目计价
            //----指定费用方式
            if (NumberUtils.compare(waybillCarrierVo.getSettlementType(), WaybillCarrierVo.TransformCarrierSettlementType.SETTLEMENT_TYPE_FEE.getCode()) == 0) {
                //----乙方预估运费 = 填写的价格
                targetEstimatePrice = waybill.getShow4DriverFreight();
            } else if (NumberUtils.compare(waybillCarrierVo.getSettlementType(), WaybillCarrierVo.TransformCarrierSettlementType.SETTLEMENT_TYPE_RATE.getCode()) == 0) {
                //----服务费率方式
                //----乙方预估运费 = 甲方结算价(一口价)
                if (this.isProjectFixedPrice(waybillParam)) {
                    targetEstimatePrice = waybill.getShow4DriverFreight();
                }
            }

        }

        waybill.setEstimateFreight(targetEstimatePrice);
        waybill.setCalculatedFreight(targetEstimatePrice);
        //计算乙方价格
        this.calculateCarrierFee(waybill, truckRequire);

    }
}


