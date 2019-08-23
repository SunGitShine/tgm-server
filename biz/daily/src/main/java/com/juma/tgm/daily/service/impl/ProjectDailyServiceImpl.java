package com.juma.tgm.daily.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.common.DateUtils;
import com.juma.tgm.common.dvsSystem.DvsDataVO;
import com.juma.tgm.common.id.IdGeneratorTable;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.daily.dao.ProjectDailyMapper;
import com.juma.tgm.daily.domain.ProjectDaily;
import com.juma.tgm.daily.domain.ProjectDailyExample;
import com.juma.tgm.daily.enums.ProjectDailyEnum;
import com.juma.tgm.daily.service.ProjectDailyService;
import com.juma.tgm.daily.vo.ProjectDailyFilter;
import com.juma.tgm.daily.vo.ProjectDailyVo;

import java.util.*;
import javax.annotation.Resource;

import com.juma.tgm.imageUploadManage.domain.ImageUploadManage;
import com.juma.tgm.imageUploadManage.domain.UploadFile;
import com.juma.tgm.imageUploadManage.service.ImageUploadManageService;
import com.juma.tgm.id.service.IdGeneratorService;
import com.juma.tgm.project.domain.RoadMap;
import com.juma.tgm.project.domain.v2.Project;
import com.juma.tgm.project.service.ProjectMemberService;
import com.juma.tgm.project.domain.v2.enums.ProjectEnum;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.project.service.RoadMapService;
import com.juma.tgm.tool.domain.DvsParam;
import com.juma.tgm.tools.service.AuthCommonService;
import com.juma.tgm.tools.service.BusinessAreaCommonService;
import com.juma.tgm.tools.service.DvsCommonService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillAmount;
import com.juma.tgm.waybill.domain.WaybillVO;
import com.juma.tgm.waybill.service.WaybillAmountService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.vo.ConfirmWaybillAmountVO;
import com.juma.vms.vendor.domain.Vendor;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @ClassName ProjectDailyServiceImpl
 * @Description TODO
 * @Author weilibin
 * @Date 2019-07-18 19:46
 * @Version 1.0.0
 */

@Service
public class ProjectDailyServiceImpl implements ProjectDailyService {


    private static final Logger logger = LoggerFactory.getLogger(ProjectDailyServiceImpl.class);

    @Resource
    private ProjectDailyMapper projectDailyMapper;

    @Autowired
    private DvsCommonService dvsCommonService;

    @Value("${project_daily_list_dvs_sql_id}")
    private String projectDailyListDvsSqlId;

    @Value("${project_daily_count_dvs_sql_id}")
    private String projectDailyCountDvsSqlId;


    @Autowired
    private ProjectService projectService;

    @Autowired
    private CustomerInfoService customerInfoService;

    @Autowired
    private BusinessAreaCommonService businessAreaCommonService;
    @Resource
    private AuthCommonService authCommonService;

    @Resource
    private ProjectMemberService projectMemberService;
    @Resource
    private IdGeneratorService idGeneratorService;

    @Autowired
    private WaybillCommonService waybillCommonService;

    @Autowired
    private ImageUploadManageService imageUploadManageService;

    @Autowired
    private RoadMapService roadMapService;

    @Autowired
    private VmsCommonService vmsCommonService;

    @Autowired
    private WaybillAmountService waybillAmountService;


    @Override
    public Page<ProjectDailyVo> search(QueryCond<ProjectDailyFilter> queryCond,
        LoginEmployee loginEmployee) {
        Page<ProjectDailyVo> projectDailyVoPage = getDataFromDvsSystem(queryCond, loginEmployee);
        return wrapperProjectDailyVO(projectDailyVoPage, loginEmployee);
    }

    private Page<ProjectDailyVo> getDataFromDvsSystem(QueryCond<ProjectDailyFilter> queryCond,
        LoginEmployee loginUser) {
        queryCond = prepareParamForDvsQuery(queryCond, loginUser);
        DvsDataVO countData = dvsCommonService
            .getDataFromDvsSystem(Integer.parseInt(projectDailyCountDvsSqlId),
                convertToProjectDailyCountQueryParam(queryCond));
        DvsDataVO listData = dvsCommonService
            .getDataFromDvsSystem(Integer.parseInt(projectDailyListDvsSqlId),
                convertToProjectDailyListQueryParam(queryCond));

        Page page = new Page<>();
        page.setPageNo(queryCond.getPageNo());
        page.setPageSize(queryCond.getPageSize());
        page.setTotal(JSONObject.parseArray(JSONObject.toJSONString(countData.getDatas()
            .get(0)), Integer.class).get(0));//TODO 从countData中获取总条数
        page.setResults(convertToProjectDailyVoList(listData));

        return page;
    }

    private QueryCond<ProjectDailyFilter> prepareParamForDvsQuery(
        QueryCond<ProjectDailyFilter> queryCond, LoginEmployee loginUser) {
        queryCond.getFilters().setTenantId(loginUser.getTenantId());
        if (CollectionUtils.isEmpty(queryCond.getFilters().getAreaCodeList())) {
            List<String> areaCodeList = new ArrayList<String>();
            areaCodeList.add(loginUser.getLoginDepartment()
                .getBusinessAreas()
                .get(0)
                .getAreaCode());
            queryCond.getFilters().setAreaCodeList(areaCodeList);
        }
        return queryCond;
    }

    private List<ProjectDailyVo> convertToProjectDailyVoList(DvsDataVO listData) {
        if (listData == null || !listData.isSuccess()) {
            return new ArrayList<ProjectDailyVo>(0);
        }
        List<ProjectDailyVo> projectDailyVoList = dvsCommonService
            .convertToList(listData, ProjectDailyVo.class);
        return projectDailyVoList;
    }

    private DvsParam convertToProjectDailyListQueryParam(QueryCond<ProjectDailyFilter> queryCond) {
        DvsParam listDvsParam = new DvsParam();
        HashMap<String, Object> filters = new HashMap<String, Object>();
        listDvsParam.setFilters(filters);
        String paramType = " [sql]";//说明是SQL类型参数
        filters.put("SQL", buildSearchSql(queryCond) + paramType);

        return listDvsParam;
    }


    public static String buildSearchSql(QueryCond<ProjectDailyFilter> queryCondition) {
        SQL sql = new SQL();
        sql.SELECT(                "project_daily_id AS projectDailyId, " +
                "tenant_id AS tenantId , " +
                "area_code AS areaCode, " +
                "project_daily_no AS projectDailyNo, " +
                "project_daily_date AS projectDailyDate, " +
                "project_id AS projectId, " +
                "customer_id AS customerId, " +
                "daily_status AS dailyStatus, " +
                "standing_book_status AS standingBookStatus, " +
                "freight_status AS freightStatus, " +
                "waybill_total AS waybillTotal, " +
                "waybill_not_finish_no AS  waybillNotFinishNo, " +
                "waybill_not_confirmed_no AS waybillNotConfirmedNo, " +
                "customer_freight_with_tax AS customerFreightWithTax, " +
                "vendor_freight_with_tax AS  vendorFreightWithTax, " +
                "customer_freight_not_self_vendor_with_tax AS  customerFreightNotSelfVendorWithTax, " +
                "vendor_freight_not_self_vendor_with_tax AS  vendorFreightNotSelfVendorWithTax, " +
                "last_customer_freight_with_tax AS lastCustomerFreightWithTax, " +
                "last_vendor_freight_with_tax  AS lastVendorFreightWithTax, " +
                "import_waybill_number  AS importWaybillNumber, " +
                "is_have_adjust_for_waybill  AS isHaveAdjustForWaybill, "+
                "is_delete AS isDelete, " +
                "create_user_id  AS createUserId, " +
                "create_time AS createTime, " +
                "last_update_user_id  AS lastUpdateUserId,  " +
                "last_update_time AS  lastUpdateTime " );
        sql.FROM("sync.project_daily_statistic");

        sql.WHERE(buildWhereSql(queryCondition));

        //排序
        sql.ORDER_BY("project_daily_date DESC, project_daily_id DESC");
        //分页
        StringBuilder sb = new StringBuilder(sql.toString());

        sb.append(" limit " + queryCondition.getStartOffset() + "," + queryCondition.getPageSize());

        return sb.toString();
    }

    private static String buildWhereSql(QueryCond<ProjectDailyFilter> queryCondition) {
        StringBuilder stringBuilder = new StringBuilder("is_delete = 0");
        if (queryCondition.getFilters().getTenantId() != null) {
            stringBuilder
                .append(" AND  sync.project_daily_statistic.tenant_id = " + queryCondition.getFilters()
                    .getTenantId());
        }
        if (queryCondition.getFilters().getCustomerId() != null) {
            stringBuilder
                .append(" AND  sync.project_daily_statistic.customer_id = " + queryCondition.getFilters()
                    .getCustomerId());
        }
        if (queryCondition.getFilters().getProjectId() != null) {
            stringBuilder
                .append(" AND  sync.project_daily_statistic.project_id = " + queryCondition.getFilters()
                    .getProjectId());
        }
        if (queryCondition.getFilters().getProjectDailyNo() != null) {
            stringBuilder
                .append(" AND  sync.project_daily_statistic.project_daily_no LIKE '" + queryCondition
                    .getFilters()
                    .getProjectDailyNo() + "'");
        }
        if (queryCondition.getFilters()
            .getProjectDailyDateList() != null && queryCondition.getFilters()
            .getProjectDailyDateList()
            .get(0) != null) {
            stringBuilder.append(" AND  sync.project_daily_statistic.project_daily_date >= '" + DateUtils
                .format(queryCondition.getFilters()
                    .getProjectDailyDateList()
                    .get(0), DateUtils.Parttern.FORMAT_YYMMDD_MID) + "'");
        }
        if (queryCondition.getFilters()
            .getProjectDailyDateList() != null && queryCondition.getFilters()
            .getProjectDailyDateList()
            .get(1) != null) {
            stringBuilder.append(" AND  sync.project_daily_statistic.project_daily_date <= '" + DateUtils
                .format(queryCondition.getFilters()
                    .getProjectDailyDateList()
                    .get(1), DateUtils.Parttern.FORMAT_YYMMDD_MID) + "'");
        }
        if (queryCondition.getFilters().getDailyStatus() != null) {
            stringBuilder
                .append(" AND sync.project_daily_statistic.daily_status = " + queryCondition.getFilters()
                    .getDailyStatus());
        }
        if (queryCondition.getFilters().getFreightStatus() != null) {
            stringBuilder.append(
                " AND sync.project_daily_statistic.freight_status = " + queryCondition.getFilters()
                    .getFreightStatus());
        }
        if (queryCondition.getFilters().getStandingBookStatus() != null) {
            stringBuilder
                .append(" AND sync.project_daily_statistic.standing_book_status = " + queryCondition
                    .getFilters()
                    .getStandingBookStatus());
        }
        if (!CollectionUtils.isEmpty(queryCondition.getFilters().getAreaCodeList())) {
            StringBuilder areaCodeBuilder = new StringBuilder();
            for (String areaCode : queryCondition.getFilters().getAreaCodeList()) {
                areaCodeBuilder
                    .append("OR ( sync.project_daily_statistic.area_code LIKE '" + areaCode + "%' ) ");
            }

            stringBuilder.append(" AND ( " + areaCodeBuilder.substring(2) + " ) ");
        }
        return stringBuilder.toString();
    }

    private DvsParam convertToProjectDailyCountQueryParam(QueryCond<ProjectDailyFilter> queryCond) {
        DvsParam countDvsParam = new DvsParam();
        HashMap<String, Object> filters = new HashMap<String, Object>();
        countDvsParam.setFilters(filters);
        String paramType = " [sql]";//说明是SQL类型参数
        filters.put("SQL", buildCountSql(queryCond) + paramType);
        return countDvsParam;

    }

    private String buildCountSql(QueryCond<ProjectDailyFilter> queryCond) {
        SQL sql = new SQL();
        sql.SELECT("count(project_daily_id) ");
        sql.FROM("sync.project_daily_statistic");
        sql.WHERE(buildWhereSql(queryCond));
        //分页
        StringBuilder sb = new StringBuilder(sql.toString());
        return sb.toString();
    }


    private Page<ProjectDailyVo> wrapperProjectDailyVO(Page<ProjectDailyVo> projectDailyVoPage,
        LoginUser loginUser) {
        List<ProjectDailyVo> projectDailyVoList = (List<ProjectDailyVo>) projectDailyVoPage
            .getResults();
        boolean isCityManager = authCommonService
            .isPermission(Constants.CITY_MANAGER_PERMISSION_KEY, (LoginEmployee) loginUser);
        for (ProjectDailyVo projectDailyVo : projectDailyVoList) {
            if (projectDailyVo == null) {
                continue;
            }
            if (projectDailyVo.getProjectId() != null) {
                Project project = projectService.getProjectV2(projectDailyVo.getProjectId());
                projectDailyVo.setProjectName((project == null ? "" : project.getName()));
            }
            if (projectDailyVo.getCustomerId() != null) {
                CustomerInfo customerInfo = customerInfoService
                    .findCusInfoById(projectDailyVo.getCustomerId());
                projectDailyVo
                    .setCustomerName((customerInfo == null ? "" : customerInfo.getCustomerName()));
            }
            if (StringUtils.isNotEmpty(projectDailyVo.getAreaCode())) {
                projectDailyVo.setAreaName(businessAreaCommonService
                    .loadAreaName(projectDailyVo.getAreaCode(), loginUser));
            }

            projectDailyVo.setCouldUpdate(couldUpdate(isCityManager, projectDailyVo, loginUser));

        }
        logger.info("wrapperProjectDailyVO:" + JSONObject.toJSONString(projectDailyVoPage));
        return projectDailyVoPage;
    }

    private boolean couldUpdate(boolean isCityManager, ProjectDailyVo projectDailyVo,
        LoginUser loginUser) {

        if (projectDailyVo.getDailyStatus()
            .equals(ProjectDailyEnum.DailyStatus.HAS_EXPIRED.getCode())) {
            return false;
        }

        if (!isCityManager && !projectMemberService.existUser(projectDailyVo.getProjectId(),
            loginUser.getUserId())) {
            return false;
        }

        if (projectDailyVo.getWaybillTotal().equals(0)) {
            return true;
        }

        if (projectDailyVo.getWaybillTotal().equals(projectDailyVo.getImportWaybillNumber())) {
            return false;
        }

        return true;
    }


    @Override
    public ProjectDailyVo getProjectDailyVo(Integer projectDailyId) {
        if (null == projectDailyId) {
            return null;
        }

        ProjectDaily projectDaily = projectDailyMapper.selectByPrimaryKey(projectDailyId);
        if (null == projectDaily) {
            return null;
        }

        ProjectDailyVo vo = new ProjectDailyVo();
        BeanUtils.copyProperties(projectDaily, vo);
        return vo;
    }

    @Override
    public List<ProjectDaily> listProjectDailyByProjectIdAndDailyDate(Integer projectId,
        Date startProjectDailyDate,
        Date endProjectDailyDate) {
        if (null == projectId || null == startProjectDailyDate || null == endProjectDailyDate) {
            return new ArrayList<>();
        }

        return projectDailyMapper.selectByExample(new ProjectDailyExample().createCriteria()
            .andProjectIdEqualTo(projectId)
            .andProjectDailyDateBetween(startProjectDailyDate, endProjectDailyDate)
            .example());
    }

    @Override
    public void insert(ProjectDaily projectDaily, LoginUser loginUser) throws BusinessException {
        projectDaily.setIsDelete(false);
        projectDaily.setCreateUserId(loginUser == null ? 0 : loginUser.getUserId());
        projectDaily.setCreateTime(new Date());

        projectDailyMapper.insertSelective(projectDaily);
    }

    @Override
    public void update(ProjectDaily projectDaily, LoginUser loginUser) throws BusinessException {
        projectDaily.setLastUpdateTime(new Date());
        projectDaily.setLastUpdateUserId(loginUser == null ? 0 : loginUser.getUserId());
        projectDailyMapper.updateByPrimaryKeySelective(projectDaily);
    }

    @Override
    public int batchInsert(List<ProjectDaily> projectDailies) {
        return projectDailyMapper.insertBatch(projectDailies);
    }

    @Override
    public Boolean createProjectDaily(Integer projectId) {
        if (null == projectId) {
            return Boolean.FALSE;
        }
        //查询是否存在
        ProjectDailyExample example = new ProjectDailyExample().createCriteria()
            .andProjectIdEqualTo(projectId)
            .andProjectDailyDateEqualTo(new Date())
            .example();
        Long count = projectDailyMapper.countByExample(example);
        if (null != count && 0 != count) {
            return Boolean.TRUE;
        }
        Project project = projectService
            .findTop1ByProjectIdAndProjectStatus(projectId, ProjectEnum.ProjectStatus.RUNING);
        if (null == project || null == project.getTenantId() || null == project.getAreaCode() || null == project
                .getCustomerId() || null == project.getProjectId()) {
            return Boolean.FALSE;
        }
        ProjectDaily daily = new ProjectDaily();
        BeanUtils.copyProperties(project, daily);
        daily.setProjectDailyNo(idGeneratorService.genProjectDailyId(IdGeneratorTable.IdType.RB));
        daily.setCreateTime(new Date());
        daily.setDailyStatus(ProjectDailyEnum.DailyStatus.UNEXPIRED.getCode());
        daily.setFreightStatus(ProjectDailyEnum.FreightStatus.NOT_CONFIRMED.getCode());
        daily.setProjectDailyDate(new Date());
        daily.setStandingBookStatus(ProjectDailyEnum.StandingBookStatus.NO_UPLOADED.getCode());
        daily.setLastUpdateTime(new Date());
        //定时任务，最后更新设置为系统管理员
        daily.setLastUpdateUserId(1);
        daily.setCreateUserId(1);
        daily.setIsDelete(false);
        projectDailyMapper.insert(daily);
        return Boolean.TRUE;
    }

    @Override
    public Boolean createProjectDailyOnImport(Integer projectId, Date dailyDate) {
        if (null == projectId || null == dailyDate) {
            return Boolean.FALSE;
        }
        Project project = projectService.getProjectV2(projectId);
        if (null == project || null == project.getTenantId() || null == project.getAreaCode() || null == project
                .getCustomerId() || null == project.getProjectId()) {
            return Boolean.FALSE;
        }
        //查询是否存在
        List<ProjectDaily> projectDailys = projectDailyMapper
            .selectByExample(new ProjectDailyExample()
                .createCriteria()
                .andProjectIdEqualTo(projectId)
                .andProjectDailyDateEqualTo(dailyDate)
                .example());
        if (null != projectDailys && 0 != projectDailys.size()) {
            ProjectDaily projectDaily = projectDailys.get(0);
            if (!projectDaily.getIsDelete()) {
                // 如果日报未被删除且运费状态为 未确认，修改为部分确认
                if(projectDaily.getFreightStatus().equals(ProjectDailyEnum.FreightStatus.NOT_CONFIRMED.getCode())){
                    projectDaily.setFreightStatus(ProjectDailyEnum.FreightStatus.PART_CONFIRMED.getCode());
                    projectDailyMapper.updateByPrimaryKey(projectDaily);
                }
                return Boolean.TRUE;
            }
            // 日报被删除时，恢复为未删除，日报状态为已过期，运费状态 为已确认
            projectDaily.setIsDelete(Boolean.FALSE);
            projectDailyMapper.updateByPrimaryKey(projectDaily);
            return Boolean.TRUE;
        }
        ProjectDaily daily = new ProjectDaily();
        BeanUtils.copyProperties(project, daily);
        daily.setProjectDailyNo(idGeneratorService.genProjectDailyId(IdGeneratorTable.IdType.RB));
        daily.setCreateTime(new Date());
        daily.setDailyStatus(ProjectDailyEnum.DailyStatus.UNEXPIRED.getCode());
        daily.setFreightStatus(ProjectDailyEnum.FreightStatus.PART_CONFIRMED.getCode());
        daily.setProjectDailyDate(dailyDate);
        daily.setStandingBookStatus(ProjectDailyEnum.StandingBookStatus.NO_UPLOADED.getCode());
        daily.setLastUpdateTime(new Date());
        //定时任务，最后更新设置为系统管理员
        daily.setLastUpdateUserId(1);
        daily.setCreateUserId(1);
        daily.setIsDelete(false);
        projectDailyMapper.insert(daily);
        return Boolean.TRUE;
    }


    @Override
    public int deleteProjectDaily(List<ProjectDaily> projectDailies) {
        return projectDailyMapper.updateBatchByPrimaryKeySelective(projectDailies);
    }

    @Override
    public ProjectDailyVo searchProjectDaily(ProjectDailyVo projectDailyVo) {
        if (projectDailyVo == null || projectDailyVo.getProjectDailyId() == null) {
            return projectDailyVo;
        }

        //使用最新的日报信息
        ProjectDailyVo dailyVo = this.getProjectDailyVo(projectDailyVo.getProjectDailyId());

        Project projectV2 = projectService.getProjectV2(dailyVo.getProjectId());
        dailyVo.setProjectProfitRate(projectV2.getProfitRate());//项目承诺毛利率
        return dailyVo;
    }

    @Override
    public List<WaybillVO> searchProjectDailyWaybills(ProjectDaily projectDaily) {
        if (projectDaily == null || projectDaily.getProjectDailyId() == null) {
            return new ArrayList<>();
        }
        ProjectDailyVo projectDailyVo = this.getProjectDailyVo(projectDaily.getProjectDailyId());

        if (projectDailyVo == null || projectDailyVo.getProjectDailyDate() == null || projectDailyVo
            .getProjectId() == null) {
            return new ArrayList<>();
        }
        Date dailyDate = projectDailyVo.getProjectDailyDate();
        Date startTime = DateUtil.dayStartReturnDate(dailyDate);
        Date endTime = DateUtil.dayEndReturnDate(dailyDate);
        PageCondition pageCondition = new PageCondition();
        Map<String, Object> filters = new HashMap<>();
        filters.put("projectId", projectDailyVo.getProjectId());
        filters.put("startTime", startTime);
        filters.put("endTime", endTime);
        //运单状态 待配送、配送中、已完成的运单自动归集到当日日报中 "运单状态 1: 派车中, 2: 待配送, 3: 配送中, 4: 待支付, 5: 已完成, 6: 已取消"
        List<Integer> statusViewList = Arrays
            .asList(Waybill.StatusView.WATING_DELIVERY.getCode(), Waybill.StatusView.DELIVERYING
                .getCode(), Waybill.StatusView.FINISH.getCode());
        filters.put("statusViewList", statusViewList);
        pageCondition.setFilters(filters);
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(500);
        List<Waybill> waybills = waybillCommonService.search(pageCondition);

        List<WaybillVO> waybillVOS = new ArrayList<>();
        for (Waybill waybill : waybills) {
            WaybillVO waybillVO = new WaybillVO();
            BeanUtils.copyProperties(waybill, waybillVO);

            RoadMap roadMap = roadMapService.getRoadMap(waybillVO.getRoadMapId());
            waybillVO.setRoadMapName(roadMap != null ? roadMap.getName() : null);

            Vendor vendor = vmsCommonService.loadVendorByVendorId(waybill.getVehicleToVendor());
            waybillVO.setVendorName(vendor != null ? vendor.getVendorName() : null);
            waybillVO.setVendorSource(vendor != null ? vendor.getVendorSource().intValue() : null);

            WaybillAmount waybillAmount = waybillAmountService
                .loadByWaybillId(waybillVO.getWaybillId());
            waybillVO
                .setAmountStatus(waybillAmount != null ? waybillAmount.getAmountStatus() : null);
            waybillVO.setCustomerFreightWithTax(
                waybillAmount != null ? waybillAmount.getCustomerFreightWithTax() : null);
            waybillVO.setVendorFreightWithTax(
                waybillAmount != null ? waybillAmount.getVendorFreightWithTax() : null);

            waybillVO.setSystemTime(new Date());
            waybillVOS.add(waybillVO);
        }
        return waybillVOS;
    }

    @Override
    public void updateProjectDailyWaybil(WaybillVO waybillVO, Integer projectDailyId,
        LoginEmployee loginEmployee) {

        if (projectDailyId == null || waybillVO == null || waybillVO.getWaybillId() == null
            || waybillVO
            .getCustomerFreightWithTax() == null) {
            throw new BusinessException("errors.updateProjectDailyWaybil", "项目日报运单确认运价参数有误");
        }

        ProjectDailyVo projectDailyVo = this.getProjectDailyVo(projectDailyId);
        if (projectDailyVo == null) {
            throw new BusinessException("errors.updateProjectDailyWaybil", "项目日报不存在");
        }

        if (ProjectDailyEnum.DailyStatus.HAS_EXPIRED.getCode().equals(projectDailyVo.getDailyStatus())) {
            throw new BusinessException("errors.updateProjectDailyWaybil", "项目日报已到期无法确认运费");
        }

        //运单确认运费
        ConfirmWaybillAmountVO confirmWaybillAmountVO = new ConfirmWaybillAmountVO();
        confirmWaybillAmountVO.setWaybillId(waybillVO.getWaybillId());
        confirmWaybillAmountVO.setCustomerAmount(waybillVO.getCustomerFreightWithTax());
        confirmWaybillAmountVO.setVendorAmount(waybillVO.getVendorFreightWithTax());
        waybillCommonService.confirmWaybillAmount(confirmWaybillAmountVO, loginEmployee);


        //更新日报状态
        ProjectDaily projectDaily = new ProjectDaily();
        projectDaily.setProjectDailyId(projectDailyId);
        projectDaily.setFreightStatus(ProjectDailyEnum.FreightStatus.PART_CONFIRMED.getCode());
        this.update(projectDaily, loginEmployee);
    }

    @Override
    public void uploadStandingBook(List<UploadFile> uploadFiles, Integer projectDailyId,
        LoginEmployee loginEmployee) {

        if (projectDailyId == null) {
            throw new BusinessException("errors.uploadStandingBook", "项目日报不存在");
        }

        ProjectDailyVo projectDailyVo = this.getProjectDailyVo(projectDailyId);
        if (projectDailyVo == null) {
            throw new BusinessException("errors.uploadStandingBook", "项目日报不存在");
        }

        if (ProjectDailyEnum.DailyStatus.HAS_EXPIRED.getCode().equals(projectDailyVo.getDailyStatus())) {
            throw new BusinessException("errors.uploadStandingBook", "项目日报已到期无法操作台账");
        }

        imageUploadManageService.updateByAllDelAndAllAdd(uploadFiles, projectDailyId,
            ImageUploadManage.ImageUploadManageSign.PROJECT_DAILY_ACCOUNT, loginEmployee);
        //上传台账后需要更新台账状态
        ProjectDaily projectDaily = new ProjectDaily();
        projectDaily.setProjectDailyId(projectDailyId);
//
        projectDaily.setStandingBookStatus(
            uploadFiles.size() > 0 ? ProjectDailyEnum.StandingBookStatus.HAS_UPLOADED.getCode()
                : ProjectDailyEnum.StandingBookStatus.NO_UPLOADED.getCode());

        this.update(projectDaily, loginEmployee);
    }

    @Override
    public List<ImageUploadManage> searchStandingBook(Integer projectDailyId) {
        return imageUploadManageService.listByRelationIdAndSign(projectDailyId,
            ImageUploadManage.ImageUploadManageSign.PROJECT_DAILY_ACCOUNT
                .getCode());
    }


    @Override
    public List<ProjectDaily> getListByFreightStatus(ProjectDailyEnum.FreightStatus freightStatus) {
        ProjectDailyExample example = new ProjectDailyExample().createCriteria()
                .andDailyStatusEqualTo(ProjectDailyEnum.DailyStatus.UNEXPIRED.getCode())
                .andFreightStatusEqualTo(freightStatus.getCode())
                .andIsDeleteEqualTo(Boolean.FALSE)
                .example();
        return projectDailyMapper.selectByExample(example);
    }

    @Override
    public List<ProjectDaily> getListByFreightStatus() {
        ProjectDailyExample example = new ProjectDailyExample().createCriteria()
                .andProjectDailyDateNotEqualTo(new Date())
                .andDailyStatusEqualTo(ProjectDailyEnum.DailyStatus.UNEXPIRED.getCode())
                .andIsDeleteEqualTo(Boolean.FALSE)
                .example();
        return projectDailyMapper.selectByExample(example);
    }

    @Override
    public List<ProjectDaily> getListByFreightStatus(ProjectDailyEnum.FreightStatus freightStatus,
        Date date) {
        if (null == date) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -1);
            date = calendar.getTime();
        }
        ProjectDailyExample example = new ProjectDailyExample().createCriteria()
            .andDailyStatusEqualTo(ProjectDailyEnum.DailyStatus.UNEXPIRED.getCode())
            .andIsDeleteEqualTo(Boolean.FALSE)
            .andFreightStatusEqualTo(freightStatus.getCode())
            .andProjectDailyDateEqualTo(date)
            .example();
        return projectDailyMapper.selectByExample(example);

    }


    @Override
    public int updateDailysByPrimaryKey(List<ProjectDaily> projectDailies) {
        return projectDailyMapper.updateBatchByPrimaryKey(projectDailies);
    }


}
