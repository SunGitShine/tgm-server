package com.juma.tgm.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.conf.domain.ConfParamOption;
import com.juma.conf.service.ConfParamService;
import com.juma.tgm.common.Constants;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.waybill.dao.WaybillDao;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.vo.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @ClassName: CustomerManagerPerformanceUtil
 * @Description:
 * @author: liang
 * @date: 2017-08-22 11:52
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
@Component
public class CustomerManagerPerformanceUtil {

    //欠款时间分段参数名
    private static final String CONF_KEY_DEBT = "DEBT";

    /**
     * 运单结束参数开始时间点
     */
    private static final String WAYBILL_FINISH_TIME_START_KEY = "finishStartTime";
    /**
     * 运单结束参数结束时间点
     */
    private static final String WAYBILL_FINISH_TIME_END_KEY = "finishEndTime";

    /**
     * 运单结算时间参数开始时间
     */
    private static final String WAYBILL_CHECKOUT_TIME_START_KEY = "checkoutStartTime";

    /**
     * 运单结算时间参数结束时间
     */
    private static final String WAYBILL_CHECKOUT_TIME_END_KEY = "checkoutEndTime";

    //金额格式化
    private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @Resource
    private ConfParamService confParamService;

    @Resource
    private CustomerInfoService customerInfoService;

    @Resource
    private RestTemplateUtil restTemplateUtil;
    @Resource
    private WaybillDao waybillDao;


    //解析时间段配置
    public LinkedHashMap<String, Map<String, String>> sliceTime(String callbackKey) {
        LinkedHashMap<String, Map<String, String>> configs = new LinkedHashMap<>();
        //读取参数
        List<ConfParamOption> params = confParamService.findParamOptions(CustomerManagerPerformanceUtil.CONF_KEY_DEBT);
        if (CollectionUtils.isEmpty(params)) return null;

        Integer temp = 1;
        //解析参数
        Map<String, String> value = null;
        for (ConfParamOption cpo : params) {
            String rawData = cpo.getOptionValue();

            value = this.parseRangeData(rawData);
            if (MapUtils.isEmpty(value)) continue;

            value.put("rangeTime", rawData);
            configs.put(cpo.getOptionName(), value);

            if (StringUtils.isNotBlank(callbackKey) && temp.equals(1)) {
                callbackKey = cpo.getOptionName();
            }

            temp++;
        }

        return configs;
    }

    /**
     * 解析出符合sql规范的条件
     *
     * @param param 参数支持的类型由 x-x,>x,<x
     * @return
     */
    private Map<String, String> parseRangeData(String param) {
        if (StringUtils.isBlank(param)) return null;
        Date now = new Date();
        String startTime = null;
        String endTime = null;

        Date tempStart = DateUtils.setSeconds(DateUtils.setMinutes(DateUtils.setHours(now, 0), 0), 0);
        Date tempEnd = DateUtils.setSeconds(DateUtils.setMinutes(DateUtils.setHours(now, 23), 59), 59);

        Map<String, String> finalData = new HashMap<>();
        //参数类型判断

        if (StringUtils.contains(param, "-")) {
            //x-x类型
            Integer[] datas = this.parseTwoParam(param);

            if (datas.length < 2) return null;

            tempStart = DateUtils.addDays(tempStart, -datas[1]);
            startTime = Constants.YYYYMMDDHHMMSS.format(tempStart);
            finalData.put(WAYBILL_FINISH_TIME_START_KEY, startTime);

            tempEnd = DateUtils.addDays(tempEnd, -datas[0]);
            endTime = Constants.YYYYMMDDHHMMSS.format(tempEnd);
            finalData.put(WAYBILL_FINISH_TIME_END_KEY, endTime);

        } else if (StringUtils.contains(param, ">")) {
            //>x类型
            Integer data = this.parseOneParam(param);
            tempStart = DateUtils.addDays(tempStart, -data);
            startTime = Constants.YYYYMMDDHHMMSS.format(tempStart);
            finalData.put(WAYBILL_FINISH_TIME_END_KEY, startTime);
        }

        return finalData;
    }


    /**
     * 包含-的范围参数
     *
     * @param param
     * @return
     */
    private Integer[] parseTwoParam(String param) {
        String[] params = StringUtils.split(param, "-");
        if (params == null) return null;
        Integer[] data = new Integer[params.length];
        for (int i = 0; i < params.length; i++) {
            if (i == 2) break;
            Integer val = Integer.parseInt(params[i]);
            data[i] = val;
        }

        return data;
    }

    /**
     * 包含 > 的范围参数
     *
     * @param param
     * @return
     */
    private Integer parseOneParam(String param) {
        String[] params = StringUtils.split(param, ">");
        if (params == null) return null;

        Integer d = Integer.parseInt(params[0]);

        return d;
    }

    //个人回款接口数据

    /**
     * 获取欠款
     * 如果不传时间范围则是所有的欠款数
     *
     * @param employeeId
     * @param filters
     * @return
     */
    public BigDecimal getDebtAmount(int employeeId, Map<String, Object> filters) {
        if (MapUtils.isEmpty(filters)) {
            filters = new HashMap<>();
        }
        //客户经理id
        //运单状态条件
        filters.put("statusView", Waybill.StatusView.FINISH.getCode());
        filters.put("reconciliationStatus", Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode());
        filters.put("isCheckout", 0);
        filters.put("customerManagerId", employeeId);
        filters.put("backstage", true);
        BigDecimal rst = waybillDao.countAmount(filters);
        if (rst == null) {
            rst = new BigDecimal("0");
        }
        return rst;
    }


    /**
     * 获取收款
     *
     * @param employeeId
     * @param filters
     * @return
     */
    private BigDecimal getGathering(int employeeId, Map<String, Object> filters) {
        if (MapUtils.isEmpty(filters)) {
            filters = new HashMap<>();
        }
        //客户经理id
        //运单状态条件
        filters.put("isCheckout", 1);
        filters.put("customerManagerId", employeeId);
        filters.put("backstage", true);
        BigDecimal rst = waybillDao.countAmount(filters);
        if (rst == null) {
            rst = new BigDecimal("0");
        }
        return rst;
    }

    /**
     * 按客户id分组统计一个时间段内，运单数量和欠款总数
     *
     * @param employeeId
     * @param pageCondition
     * @return
     */
    private List<CustomerManagerDebtDetailVo> countCustomerDebt(int employeeId, PageCondition pageCondition) {
        Map<String, Object> filters = pageCondition.getFilters();
        if (MapUtils.isEmpty(filters)) {
            filters = new HashMap<>();
        }
        filters.put("statusView", Waybill.StatusView.FINISH.getCode());
        filters.put("reconciliationStatus", Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode());
        filters.put("isCheckout", 0);
        filters.put("customerManagerId", employeeId);
        filters.put("backstage", true);
        Collection<CustomerManagerDebtDetailVo> data = waybillDao.findCustomerDebtBill(pageCondition);
        if (data == null) return null;

        List<CustomerManagerDebtDetailVo> rstData = new ArrayList<>(data);
        return rstData;
    }

    //总欠款

    /**
     * 个人回款页面数据
     *
     * @param loginEmployee
     * @return 总欠款，本周回款
     */
    public CustomerManagerDebtVo getDebtTotal(LoginEmployee loginEmployee) {
        CustomerManagerDebtVo debtVo = new CustomerManagerDebtVo();
        //总欠款
        Map<String, Object> filters = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        Date endDay = DateUtils.addDays(calendar.getTime(), -1);//定时任务在午夜之后运行
        Date tempEnd = DateUtils.setSeconds(DateUtils.setMinutes(DateUtils.setHours(endDay, 23), 59), 59);
        filters.put(WAYBILL_FINISH_TIME_END_KEY, Constants.YYYYMMDDHHMMSS.format(tempEnd));

        BigDecimal debtAmount = this.getDebtAmount(loginEmployee.getEmployeeId(), filters);
        debtVo.setTotalDebt(Constants.DECIMAL_2_FORMAT.format(debtAmount));
        //本周回款
        BigDecimal gatheringAmount = this.getCurrentWeekGathering(loginEmployee.getEmployeeId());
        debtVo.setCurrentWeekGathering(Constants.DECIMAL_2_FORMAT.format(gatheringAmount));

        return debtVo;
    }


    /**
     * 获取-本周回款
     *
     * @param employeeId
     * @return
     */
    public BigDecimal getCurrentWeekGathering(int employeeId) {
        Map<String, Object> filters = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        //星期一
        Date monday = DateUtils.addDays(calendar.getTime(), -(dayOfWeek - 1));
        Date tempStart = DateUtils.setSeconds(DateUtils.setMinutes(DateUtils.setHours(monday, 0), 0), 0);
        //截止日期
        Date endDay = DateUtils.addDays(calendar.getTime(), -1);//定时任务在午夜之后运行
        Date tempEnd = DateUtils.setSeconds(DateUtils.setMinutes(DateUtils.setHours(endDay, 23), 59), 59);

        filters.put(WAYBILL_CHECKOUT_TIME_START_KEY, Constants.YYYYMMDDHHMMSS.format(tempStart));
        filters.put(WAYBILL_CHECKOUT_TIME_END_KEY, Constants.YYYYMMDDHHMMSS.format(tempEnd));

        return this.getGathering(employeeId, filters);
    }

    //欠款统计
//    public CustomerManagerDebtVo getDebtOverView(LoginEmployee loginEmployee) {
//        CustomerManagerDebtVo debtVo = this.getDebtTotal(loginEmployee);
//        //概览数据
//        //--累计欠款
//        //超期数据
//        BigDecimal overdueDebt = this.getOverdueDebt(loginEmployee.getEmployeeId());
//        debtVo.setOverdueDebt(Constants.DECIMAL_2_FORMAT.format(overdueDebt));
//        //分段数据
//        List<CustomerManagerDebtOverviewVo> listData = this.getSeparationDebt(loginEmployee.getEmployeeId());
//        debtVo.setSeparationDebt(listData);
//
//        return debtVo;
//    }

    /**
     * 个人运费
     *
     * @param employeeId
     * @return
     */
    public OverallAchieveMent getCustomerPerformance(int employeeId) {
        RestTemplate rest = restTemplateUtil.getRestTemplate();
        String url = String.format(Constants.CUSTOMER_PERFORMANCE_URL, employeeId);
        String rawData = rest.getForObject(url, String.class);

        if (StringUtils.isBlank(rawData)) return null;

        Map<String, String> tempData = JSONObject.parseObject(rawData, new TypeReference<Map<String, String>>() {});
        String realData = tempData.get("data");
        if (StringUtils.isBlank(realData)) return null;

        OverallAchieveMent overall = JSONObject.parseObject(realData, OverallAchieveMent.class);

        //去除无效的数据（去除第一次有运费之前的且去除最后一次有运费之后的数据）
        List<ManagerAchieveMent> monthData = overall.getMonthData();
        this.trimEmptyData(monthData);

        return overall;
    }

    /**
     * 去除无效的数据（去除第一次有运费之前的且去除最后一次有运费之后的数据）
     *
     * @param monthData
     */
    private void trimEmptyData(List<ManagerAchieveMent> monthData) {
        if (CollectionUtils.isEmpty(monthData)) return;

        int b = 0;
        boolean bFlag = true;
        int e = monthData.size() - 1;
        boolean eFlag = true;
        ManagerAchieveMent bData = null;
        ManagerAchieveMent eData = null;
        Set<ManagerAchieveMent> trimedData = new HashSet<>();
        for (int i = 0; i<monthData.size(); i++) {
            //去除列表开始

            if(bFlag) {
                bData = monthData.get(b);
                if (NumberUtils.compare(0, bData.getOrderMoney()) >= 0 && NumberUtils.compare(0, bData.getCustomerCount()) >= 0 && NumberUtils.compare(0, bData.getOrderCount()) >= 0) {
                    trimedData.add(bData);
                    b++;
                } else {
                    bFlag = false;
                }
            }

            //去除列表结尾
            if(eFlag) {
                eData = monthData.get(e);
                if (NumberUtils.compare(0, eData.getOrderMoney()) >= 0 && NumberUtils.compare(0, eData.getCustomerCount()) >= 0 && NumberUtils.compare(0, eData.getOrderCount()) >= 0)
                {
                    trimedData.add(eData);
                    e--;
                }else{
                    eFlag = false;
                }
            }

            if(!bFlag && !eFlag) break;

        }
        monthData.removeAll(trimedData);
    }

    /**
     * 超期欠款
     *
     * @param employeeId
     * @return
     */
    public BigDecimal getOverdueDebt(int employeeId) {
        Map<String, Object> filters = new HashMap<>();
        //第一个配置项的结束时间以前的数据
        Map<String, String> overdueTime = null;
        String firstKey = null;
        LinkedHashMap<String, Map<String, String>> configs = this.sliceTime(firstKey);

        overdueTime = configs.get(firstKey);
        if (MapUtils.isEmpty(overdueTime)) {
            return null;
        }

        String overdueTimeParam = overdueTime.get(WAYBILL_FINISH_TIME_START_KEY);
        filters.put(WAYBILL_FINISH_TIME_END_KEY, overdueTimeParam);
        return this.getDebtAmount(employeeId, filters);
    }

    /**
     * 分段欠款
     *
     * @param employeeId
     * @return
     */
    public List<CustomerManagerDebtOverviewVo> getSeparationDebt(int employeeId) {
        List<CustomerManagerDebtOverviewVo> separationDebt = new ArrayList<>();
        //获取时间切片
        LinkedHashMap<String, Map<String, String>> configs = this.sliceTime(null);
        Map<String, Object> filter = null;
        CustomerManagerDebtOverviewVo vo = null;
        for (String key : configs.keySet()) {
            vo = new CustomerManagerDebtOverviewVo();
            filter = new HashMap<>();
            Map<String, String> val = configs.get(key);
            filter.putAll(val);

            vo.setTimeRange(val.get("rangeTime"));
            vo.setTimeRangeName(key);
            BigDecimal fee = this.getDebtAmount(employeeId, filter);
            if (fee == null) {
                vo.setFee("0");
            } else {
                vo.setFee(decimalFormat.format(fee));
            }

            separationDebt.add(vo);
        }

        return separationDebt;
    }


    /**
     * 欠款详情
     *
     * @param pageCondition timeRange
     * @param employeeId
     * @return
     */
    public CustomerManagerDebtOverviewVo getDebtOverviewVo(PageCondition pageCondition, int employeeId) {
        CustomerManagerDebtOverviewVo debtOverviewVo = new CustomerManagerDebtOverviewVo();
        Map<String, Object> filters = pageCondition.getFilters();
        if (filters == null) throw new BusinessException("paramError", "errors.paramCanNotNullWithName", "时间段");

        String timeRange = (String) filters.get("timeRange");
        if (StringUtils.isBlank(timeRange))
            throw new BusinessException("paramError", "errors.paramCanNotNullWithName", "时间段");

        debtOverviewVo.setTimeRange(timeRange);

        Page<CustomerManagerDebtDetailVo> pageData = new Page<>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0);
        debtOverviewVo.setCustomerList(pageData);

        //该分段下的欠款金额
        Map<String, String> filter = this.parseRangeData(timeRange);

        filters.putAll(filter);
        filters.put("customerManagerId", employeeId);
        BigDecimal fee = this.getDebtAmount(employeeId, filters);
        if (fee == null) {
            debtOverviewVo.setFee("0");
        } else {
            debtOverviewVo.setFee(decimalFormat.format(fee));
        }
        int count = waybillDao.countCustomerDebtBill(pageCondition);
        if (count <= 0) return debtOverviewVo;

        //每个公司的欠款金额
        List<CustomerManagerDebtDetailVo> customerDebt = this.countCustomerDebt(employeeId, pageCondition);

        pageData.setTotal(count);
        pageData.setResults(customerDebt);

        this.buildCustomerInfo(customerDebt);

        return debtOverviewVo;
    }

    /**
     * 组装客户信息
     *
     * @param customerDebt
     */
    private void buildCustomerInfo(List<CustomerManagerDebtDetailVo> customerDebt) {
        if (CollectionUtils.isEmpty(customerDebt)) return;

        for (CustomerManagerDebtDetailVo vo : customerDebt) {
            CustomerInfo info = customerInfoService.findCusInfoById(vo.getCustomerId());
            if (info == null) continue;

            vo.setCustomerInfo(info);
        }

    }

    /**
     * 欠款明细
     *
     * @param pageCondition timeRange customerId
     * @param employeeId
     * @return
     */
    public CustomerManagerDebtDetailVo getDebtDetail(PageCondition pageCondition, int employeeId) {
        CustomerManagerDebtDetailVo detail = new CustomerManagerDebtDetailVo();
        Map<String, Object> filters = pageCondition.getFilters();
        if (filters == null) throw new BusinessException("paramError", "errors.paramCanNotNullWithName", "客户id");
        if (filters.get("customerId") == null)
            throw new BusinessException("paramError", "errors.paramCanNotNullWithName", "客户id");
        String timeRange = (String) filters.get("timeRange");
        if (StringUtils.isBlank(timeRange))
            throw new BusinessException("paramError", "errors.paramCanNotNullWithName", "时间段");

        Page<Waybill> pageData = new Page<>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0);

        //该分段下的欠款金额
        Map<String, String> filter = this.parseRangeData(timeRange);

        filters.putAll(filter);
        filters.put("customerManagerId", employeeId);
        //这个查询不需要分页
        int pageSize = pageCondition.getPageSize();
        pageCondition.setPageSize(1);
        int pageNo = pageCondition.getPageNo();
        pageCondition.setPageNo(1);
        List<CustomerManagerDebtDetailVo> customerDebt = this.countCustomerDebt(employeeId, pageCondition);

        if (CollectionUtils.isEmpty(customerDebt)) return detail;
        this.buildCustomerInfo(customerDebt);

        detail = customerDebt.get(0);

        detail.setWaybills(pageData);

//        int count = waybillDao.countCustomerDebtBill(pageCondition);
//
//        if (count <= 0) return detail;

        //重新设置分页参数
        pageCondition.setPageNo(pageNo);
        pageCondition.setPageSize(pageSize);
        //获取具体的运单
        Collection<Waybill> allBill = waybillDao.findDebtWaybill(pageCondition);

        pageData.setTotal(detail.getBillCount());
        pageData.setResults(allBill);

        return detail;
    }

}
