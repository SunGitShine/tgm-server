package com.juma.tgm.configure.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.nutz.el.El;
import org.nutz.lang.Lang;
import org.nutz.lang.util.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.FreightEnum;
import com.juma.tgm.configure.dao.FreightFormulaMapper;
import com.juma.tgm.configure.dao.PrivateFreightFactorMapper;
import com.juma.tgm.configure.dao.TenantFreightFactorMapper;
import com.juma.tgm.configure.domain.BlockKm;
import com.juma.tgm.configure.domain.FreightFormula;
import com.juma.tgm.configure.domain.FreightFormulaExample;
import com.juma.tgm.configure.domain.PrivateFreightContext;
import com.juma.tgm.configure.domain.PrivateFreightFactor;
import com.juma.tgm.configure.domain.PrivateFreightFactorContainer;
import com.juma.tgm.configure.domain.PrivateFreightFactorExample;
import com.juma.tgm.configure.domain.TenantFreightFactor;
import com.juma.tgm.configure.domain.TenantFreightFactorExample;
import com.juma.tgm.configure.service.PrivateFreightFactorService;
import com.juma.tgm.project.enumeration.ValuationWayEnum;
import com.juma.tgm.truck.domain.AdditionalFunction;
import com.juma.tgm.truck.service.AdditionalFunctionService;

@Service
public class PrivateFreightFactorServiceImpl implements PrivateFreightFactorService {

    private static final Logger logger = LoggerFactory.getLogger(PrivateFreightFactorServiceImpl.class);

    @Resource
    private PrivateFreightFactorMapper privateFreightFactorMapper;

    @Resource
    private TenantFreightFactorMapper tenantFreightFactorMapper;

    @Resource
    private FreightFormulaMapper freightFormulaMapper;

    @Resource
    private AdditionalFunctionService additionalFunctionService;

    private Map<String, Boolean> buildFunctionContext(PrivateFreightContext c, Context context) {

        logger.info("function ids : {}", c.getFunctionIds());

        Map<String, Boolean> functionMap = new HashMap<String, Boolean>();
        String _ids_ = "";
        if (StringUtils.isNotBlank(c.getFunctionIds())) {
            _ids_ = "," + c.getFunctionIds() + ",";
        }

        List<AdditionalFunction> all = additionalFunctionService.getAllAdditionalFunction();

        for (AdditionalFunction function : all) {

            if (StringUtils.isBlank(function.getFunctionKey())) {
                context.set(function.getFunctionKey().toLowerCase(), BigDecimal.ZERO);
            }
            if (_ids_.contains(function.getAdditionalFunctionId() + "")) {

                functionMap.put(function.getFunctionKey().toLowerCase(), true);
            }
        }

        return functionMap;
    }

    @Override
    public void save(PrivateFreightFactor domain, LoginUser user) throws BusinessException {

        if (domain.getPrivateFreightFactorId() == null) {
            domain.setTenantId(user.getTenantId());
            domain.setTenantCode(user.getTenantCode());
            domain.setCreateTime(new Date());
            domain.setCreateUserId(user.getUserId());
            domain.setIsEnable((byte) 1);

            PrivateFreightFactorExample example = new PrivateFreightFactorExample();
            PrivateFreightFactorExample.Criteria criteria = example.createCriteria();
            // 租户和城市 必检查
            criteria.andTenantIdEqualTo(user.getTenantId());
            criteria.andRegionCodeEqualTo(domain.getRegionCode());
            criteria.andIsEnableEqualTo((byte) 1);
            // 车型看有否
            if (domain.getTruckTypeId() != null) {
                criteria.andTruckTypeIdEqualTo(domain.getTruckTypeId());
                List<PrivateFreightFactor> rows = privateFreightFactorMapper.selectByExample(example);
                if (!rows.isEmpty()) {
                    throw new BusinessException("tgm.configure.private.freight.repeat",
                            "tgm.configure.private.freight.repeat");
                }
            }
            privateFreightFactorMapper.insertSelective(domain);
        } else {
            domain.setLastUpdateUserId(user.getUserId());
            domain.setLastUpdateTime(new Date());
            privateFreightFactorMapper.updateByPrimaryKeySelective(domain);
        }

    }

    @Override
    public void disable(Integer privateFreightFactorId, LoginUser user) throws BusinessException {

        PrivateFreightFactor domain = new PrivateFreightFactor();
        domain.setPrivateFreightFactorId(privateFreightFactorId);
        domain.setLastUpdateUserId(user.getUserId());
        domain.setLastUpdateTime(new Date());
        domain.setIsEnable((byte) 0);
        privateFreightFactorMapper.updateByPrimaryKeySelective(domain);

    }

    @Override
    public Page<PrivateFreightFactor> getPager(String regionCode, Integer freightWay, Integer truckTypeId,
            PageCondition condition, LoginUser user) {

        PrivateFreightFactorExample example = new PrivateFreightFactorExample();

        example.setSize(condition.getPageSize());

        example.setStartOffSet(condition.getPageNo() < 1 ? 0 : (condition.getPageNo() - 1) * condition.getPageSize());

        PrivateFreightFactorExample.Criteria criteria = example.createCriteria();

        criteria.andTenantIdEqualTo(user.getTenantId());

        criteria.andIsEnableEqualTo((byte) 1);

        if (StringUtils.isNotBlank(regionCode)) {
            criteria.andRegionCodeEqualTo(regionCode);
        }

        if (freightWay != null) {
            criteria.andFreightWayEqualTo(freightWay);
        }

        if (truckTypeId != null) {
            criteria.andTruckTypeIdEqualTo(truckTypeId);
        }

        long total = privateFreightFactorMapper.countByExample(example);

        List<PrivateFreightFactor> results = privateFreightFactorMapper.selectByExample(example);

        return new Page<PrivateFreightFactor>(condition.getPageNo(), condition.getPageSize(),
                new Long(total).intValue(), results);
    }

    @Override
    public void saveTenantFreightFactor(TenantFreightFactor domain, LoginUser user) throws BusinessException {
        TenantFreightFactorExample example = new TenantFreightFactorExample();
        example.createCriteria().andTenantIdEqualTo(user.getTenantId()).andFreightWayEqualTo(domain.getFreightWay());
        List<TenantFreightFactor> rows = tenantFreightFactorMapper.selectByExample(example);
        if (rows.isEmpty()) {
            domain.setTenantId(user.getTenantId());
            domain.setTenantCode(user.getTenantCode());
            domain.setCreateTime(new Date());
            domain.setCreateUserId(user.getUserId());
            tenantFreightFactorMapper.insertSelective(domain);
        } else {
            domain.setTenantFreightFactorId(rows.get(0).getTenantFreightFactorId());
            domain.setLastUpdateTime(new Date());
            domain.setLastUpdateUserId(user.getUserId());
            tenantFreightFactorMapper.updateByPrimaryKeySelective(domain);
        }
    }

    @Override
    public TenantFreightFactor findByFreightWay(Integer freightWay, LoginUser user) {
        TenantFreightFactorExample example = new TenantFreightFactorExample();
        TenantFreightFactorExample.Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(user.getTenantId());
        if (freightWay != null) {
            criteria.andFreightWayEqualTo(freightWay);
        }
        List<TenantFreightFactor> rows = tenantFreightFactorMapper.selectByExample(example);
        return rows.isEmpty() ? null : rows.get(0);
    }

    @Override
    public PrivateFreightFactor findByPrimaryKey(Integer privateFreightFactorId) {
        return privateFreightFactorMapper.selectByPrimaryKey(privateFreightFactorId);
    }

    @Override
    public BigDecimal calFreight(String regionCode, Integer freightWay, Integer truckTypeId, PrivateFreightContext c,
            LoginUser user) throws BusinessException {

        logger.info("customer context : {}.", c);

        BigDecimal km = c.getKm() == null ? BigDecimal.ZERO : c.getKm();
        BigDecimal volumn = c.getVolumn() == null ? BigDecimal.ZERO : c.getVolumn();
        BigDecimal weight = c.getWeight() == null ? BigDecimal.ZERO : c.getWeight();
        BigDecimal unloadingMin = c.getUnloadingMin() == null ? BigDecimal.ZERO : c.getUnloadingMin();
        BigDecimal shipmentMin = c.getShipmentMin() == null ? BigDecimal.ZERO : c.getShipmentMin();
        BigDecimal pointNum = c.getPointNum();
        BigDecimal tollsFee = c.getTollsFee() == null ? BigDecimal.ZERO : c.getTollsFee();
        BigDecimal collectionPayment = c.getCollectionPayment() == null ? BigDecimal.ZERO : c.getCollectionPayment();

        if (StringUtils.isBlank(regionCode) || freightWay == null) {
            throw new BusinessException("errors.validation.failure", "errors.validation.failure");
        }

        if (FreightEnum.ZHENGCHE.getCode() == freightWay) {
            if (truckTypeId == null) {
                throw new BusinessException("errors.validation.failure", "errors.validation.failure");
            }
        }

        PrivateFreightFactorExample example = new PrivateFreightFactorExample();

        PrivateFreightFactorExample.Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(user.getTenantId());
        criteria.andRegionCodeEqualTo(regionCode);
        criteria.andFreightWayEqualTo(freightWay);
        criteria.andIsEnableEqualTo((byte) 1);

        if (truckTypeId != null) {
            criteria.andTruckTypeIdEqualTo(truckTypeId);
        }

        List<PrivateFreightFactor> results = privateFreightFactorMapper.selectByExample(example);
        if (results.isEmpty()) {
            throw new BusinessException("tgm.configure.private.freight.error", "tgm.configure.private.freight.error");
        }

        FreightFormula formula = findFormula(freightWay, user);
        // 公式没有配置
        if (formula == null) {
            throw new BusinessException("tgm.configure.freight.formula.error", "tgm.configure.freight.formula.error");
        }

        // 排序
        List<PrivateFreightFactorContainer> container = new ArrayList<PrivateFreightFactorContainer>();
        for (PrivateFreightFactor privateFreightFactor : results) {
            // 基础吨位与方位
            BigDecimal baseWeight = BigDecimal.ZERO;
            BigDecimal baseVolumn = BigDecimal.ZERO;

            if (FreightEnum.LINGDAN.getCode() == freightWay) {
                String json = privateFreightFactor.getFactorJson();
                if (StringUtils.isBlank(json)) continue;
                Map<String, String> map = JSON.parseObject(json, new TypeReference<HashMap<String, String>>() {});
                logger.info("[calculate freight] private freight factor json is {}.", json);
                if (map.containsKey("base_weight") && StringUtils.isNotBlank(String.valueOf(map.get("base_weight")))) {
                    baseWeight = new BigDecimal(map.get("base_weight").toString());
                }
                if (map.containsKey("base_volumn") && StringUtils.isNotBlank(String.valueOf(map.get("base_volumn")))) {
                    baseVolumn = new BigDecimal(map.get("base_volumn").toString());
                }
            }

            PrivateFreightFactorContainer lingdan = new PrivateFreightFactorContainer();
            lingdan.setPrivateFreightFactor(privateFreightFactor);
            lingdan.setBaseWeight(baseWeight);
            lingdan.setBaseVolumn(baseVolumn);
            container.add(lingdan);
        }
        Collections.sort(container);
        logger.info("[calculate freight] sort order is {}.", container);

        // 零担有多条记录 取较大值
        List<PrivateFreightFactor> resultsPrivate = new ArrayList<PrivateFreightFactor>();

        if (FreightEnum.LINGDAN.getCode() == freightWay) {
            // 重量
            Iterator<PrivateFreightFactorContainer> it4 = container.iterator();
            while (it4.hasNext()) {
                PrivateFreightFactorContainer lindan = it4.next();
                if (weight.compareTo(lindan.getBaseWeight()) == -1 || weight.compareTo(lindan.getBaseWeight()) == 0) {
                    resultsPrivate.add(lindan.getPrivateFreightFactor());
                    break;
                }
            }
            // 体积
            Iterator<PrivateFreightFactorContainer> it3 = container.iterator();
            while (it3.hasNext()) {
                PrivateFreightFactorContainer lindan = it3.next();
                if (volumn.compareTo(lindan.getBaseVolumn()) == -1 || volumn.compareTo(lindan.getBaseVolumn()) == 0) {
                    resultsPrivate.add(lindan.getPrivateFreightFactor());
                    break;
                }
            }
        }

        // 整车 只有一条记录
        if (FreightEnum.ZHENGCHE.getCode() == freightWay) {
            for (PrivateFreightFactorContainer privateFreightFactor : container) {
                resultsPrivate.add(privateFreightFactor.getPrivateFreightFactor());
            }
        }

        if (resultsPrivate.isEmpty()) {
            throw new BusinessException("tgm.configure.private.freight.rule.error",
                    "tgm.configure.private.freight.rule.error");
        }

        logger.info("[calculate freight] final  result is {}.", resultsPrivate);
        List<BigDecimal> stack = new ArrayList<BigDecimal>();

        for (PrivateFreightFactor privateFreightFactor : resultsPrivate) {

            String json = privateFreightFactor.getFactorJson();
            if (StringUtils.isBlank(json)) continue;
            Map<String, String> map = JSON.parseObject(json, new TypeReference<HashMap<String, String>>() {});
            logger.info("[calculate freight] private freight factor json is {}.", json);

            Context context = Lang.context();
            context.set("carry", 0);
            context.set("collection_payment", 0);
            context.set("need_back_storage", 0);
            context.set("over_time_fee", 0);
            context.set("need_back_storage", 0);
            context.set("collection_payment", 0);
            context.set("carry", 0);
            context.set("entry_license", 0);
            context.set("need_receipt", 0);

            context.set("tollsFee", tollsFee);
            context.set("km", km);
            context.set("unloadingMin", unloadingMin == null ? 0 : unloadingMin);
            context.set("shipmentMin", shipmentMin == null ? 0 : shipmentMin);
            context.set("pointNum", pointNum == null ? 0 : pointNum);

            List<BlockKm> sortList = new ArrayList<BlockKm>();

            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                // 是否有分段计价
                if ("kmMap".equals(key)) {
                    Map<String, String> kmMap = JSON.parseObject(entry.getValue(),
                            new TypeReference<HashMap<String, String>>() {});
                    for (Map.Entry<String, String> kmMapItem : kmMap.entrySet()) {
                        BlockKm kmBlock = new BlockKm();
                        kmBlock.setKm(new BigDecimal(kmMapItem.getKey()));
                        kmBlock.setFreight(new BigDecimal(kmMapItem.getValue() + ""));
                        sortList.add(kmBlock);
                    }
                    if (!sortList.isEmpty()) {
                        Collections.sort(sortList);// 倒序
                        // 分段计算超公里
                        BigDecimal overKmFreight = BigDecimal.ZERO;
                        BigDecimal overKm = km.subtract(new BigDecimal(map.get("base_km").toString()));
                        BigDecimal calculatedKm = BigDecimal.ZERO;// 已经计算过的公里
                        logger.info("[calculate freight] over km : {}", overKm);

                        if (overKm.compareTo(BigDecimal.ZERO) == 1) {
                            for (BlockKm bk : sortList) {
                                BigDecimal diffKm = overKm.subtract(bk.getKm());
                                if (diffKm.compareTo(BigDecimal.ZERO) == 1) {// 大于
                                    overKmFreight = overKmFreight
                                            .add(bk.getKm().subtract(calculatedKm).multiply(bk.getFreight()));
                                    calculatedKm = bk.getKm();
                                } else {
                                    overKmFreight = overKmFreight
                                            .add(overKm.subtract(calculatedKm).multiply(bk.getFreight()));
                                    break;
                                }
                            }
                        }
                        logger.info("[calculate freight] over km freight: {}", overKmFreight);
                        // 分段计费合计
                        context.set("overKmFreight", overKmFreight);
                    }
                } else {
                    context.set(key, new BigDecimal(entry.getValue() + ""));
                }
            }

            // 超时 计费
            BigDecimal base_shipment_time = BigDecimal.ZERO;

            BigDecimal base_shipment_fee = BigDecimal.ZERO;

            if (map.containsKey("base_shipment_time") && map.get("base_shipment_time") != null) {
                base_shipment_time = new BigDecimal(map.get("base_shipment_time"));
            }

            if (map.containsKey("base_shipment_fee") && map.get("base_shipment_fee") != null) {
                base_shipment_fee = new BigDecimal(map.get("base_shipment_fee"));
            }

            if (shipmentMin.compareTo(base_shipment_time) == 1) {
                context.set("over_time_fee", shipmentMin.subtract(base_shipment_time)
                        .divide(new BigDecimal(30 + ""), 0, BigDecimal.ROUND_UP).multiply(base_shipment_fee));// 除以0.5 * 60分钟 base_shipment_time 如果取小时
            }

            // 覆盖 用车要求 动态条件计费

            Map<String, Boolean> functionMap = buildFunctionContext(c, context);
            logger.info("[calculate freight] functionMap is {}", functionMap);

            // 返程费 NEED_BACK_STORAGE
            if (functionMap.containsKey("need_back_storage") && functionMap.get("need_back_storage")) {
                if (map.containsKey("need_back_storage") && map.get("need_back_storage") != null) {
                    context.set("need_back_storage", km.multiply(new BigDecimal(map.get("need_back_storage"))));
                    context.set("tollsFee", tollsFee.multiply(new BigDecimal(2 + "")));// 高速费两倍
                }
            } else {
                context.set("need_back_storage", 0);
            }

            // 代收货款 COLLECTION_PAYMENT
            if (functionMap.containsKey("collection_payment") && functionMap.get("collection_payment")) {
                BigDecimal base_collection_payment = BigDecimal.ZERO;
                BigDecimal collection_payment_rate = BigDecimal.ZERO;
                if (map.containsKey("collection_payment_rate") && map.get("collection_payment_rate") != null) {
                    collection_payment_rate = new BigDecimal(map.get("collection_payment_rate"))
                            .multiply(new BigDecimal("0.01"));
                }
                if (map.containsKey("base_collection_payment") && map.get("base_collection_payment") != null) {
                    base_collection_payment = new BigDecimal(map.get("base_collection_payment"));
                }
                collectionPayment = collectionPayment.multiply(collection_payment_rate);

                context.set("collection_payment", collectionPayment.compareTo(base_collection_payment) == 1
                        ? collectionPayment : base_collection_payment);
            } else {
                context.set("collection_payment", 0);
            }

            // 搬运费
            if (functionMap.containsKey("carry") && functionMap.get("carry")) {
                BigDecimal carry_weight = BigDecimal.ZERO;
                BigDecimal carry_volumn = BigDecimal.ZERO;
                if (map.containsKey("carry_weight") && map.get("carry_weight") != null) {
                    carry_weight = new BigDecimal(map.get("carry_weight")).multiply(weight);
                }
                if (map.containsKey("carry_volumn") && map.get("carry_volumn") != null) {
                    carry_volumn = new BigDecimal(map.get("carry_volumn")).multiply(volumn);
                }
                context.set("carry", carry_weight.compareTo(carry_volumn) == 1 ? carry_weight : carry_volumn);
            }

            // 入城证
            if (functionMap.containsKey("entry_license") && functionMap.get("entry_license")) {
                if (map.containsKey("entry_license") && map.get("entry_license") != null) {
                    context.set("entry_license", new BigDecimal(map.get("entry_license")));
                }
            } else {
                context.set("entry_license", 0);
            }

            // 回单费
            if (functionMap.containsKey("need_receipt") && functionMap.get("need_receipt")) {
                if (map.containsKey("need_receipt") && map.get("need_receipt") != null) {
                    context.set("need_receipt", new BigDecimal(map.get("need_receipt")));
                }
            } else {
                context.set("need_receipt", 0);
            }

            logger.info("[calculate freight] context is {}", context.toString());

            String formulaStr = formula.getFormula()
                    + "+need_back_storage+tollsFee+collection_payment+carry+need_receipt+entry_license+over_time_fee";
            checkFormulaContext(formulaStr, context);
            logger.info("[calculate freight] context is {}", context.toString());
            logger.info("[calculate freight] formula is {}", formulaStr);
            Object o = El.eval(context, formulaStr);
            BigDecimal v = new BigDecimal(o + "");
            stack.add(v);
        }

        Collections.sort(stack, new Comparator<BigDecimal>() {
            @Override
            public int compare(BigDecimal o1, BigDecimal o2) {
                return o2.compareTo(o1);
            }
        });

        return stack.isEmpty() ? BigDecimal.ZERO : stack.get(0);
    }

    private void checkFormulaContext(String formula, Context context) {
        StringBuilder buffer = new StringBuilder();
        char[] charArray = formula.toCharArray();
        for (char c : charArray) {
            if (Character.isSpaceChar(c)) {
                continue;
            }
            if (Character.isLetter(c) || c == '_') {
                buffer.append(c);
                continue;
            }
            if (buffer.length() != 0) {
                if (!context.has(buffer.toString())) {
                    throw new BusinessException("tgm.configure.freight.formula.key.notfound.error",
                            "tgm.configure.freight.formula.key.notfound.error", buffer.toString());
                }
                buffer.setLength(0);
            }
        }
    }

    private FreightFormula findFormula(Integer freightWay, LoginUser user) {
        FreightFormulaExample example = new FreightFormulaExample();
        example.createCriteria().andFreightWayEqualTo(freightWay).andTenantIdEqualTo(user.getTenantId());
        List<FreightFormula> results = freightFormulaMapper.selectByExample(example);
        return results.isEmpty() ? null : results.get(0);
    }

    public static void main(String[] args) {
        /*Context context = Lang.context();
        context.set("a", "");
        String a = "a_aa+b C+c+e*(d/2-1)";
        StringBuilder buffer = new StringBuilder();
        char[] charArray = a.toCharArray();
        for (char c : charArray) {
            if (Character.isSpaceChar(c)) {
                continue;
            }
            if (Character.isLetter(c) || c == '_') {
                buffer.append(c);
                continue;
            }
            if (buffer.length() != 0) {
                if (!context.has(buffer.toString())) {
                    System.out.println(buffer.toString());
                }
                buffer.setLength(0);
            }
        }*/

        BigDecimal cal = new PrivateFreightFactorServiceImpl().calFreightForProject(null,
                "{\"truckTypeId\":9,\"roadMapId\":986,\"valuationWay\":1,\"factorJson\":\"{\\\"initiateRate\\\":11,\\\"byDay\\\":111,\\\"byWeight\\\":111,\\\"byTicket\\\":111,\\\"byTime\\\":111}\"}");
        System.out.println(cal);
        Map<String, Boolean> functionMap = new HashMap<String, Boolean>();
        System.out.println(functionMap.containsKey("collection_payment") && functionMap.get("collection_payment"));
        /*
         * BigDecimal a = new BigDecimal("41.40"); BigDecimal b = new BigDecimal("10"); BigDecimal c = new BigDecimal("20"); System.out.println(a.subtract(b).divide(new
         * BigDecimal("30"),0,BigDecimal.ROUND_UP)); Context context = Lang.context(); context.set("a", a); context.set("b", b); System.out.println(El.eval(context, "a/b"));
         */

        /*
         * Map<String, String> jsonMap = JSON.parseObject( "{\"need_receipt\":\"0\",\"kmMap\":{\"5\":\"1\",\"9999\":\"5\"}}", new TypeReference<HashMap<String, String>>() {}); for
         * (Map.Entry<String, String> entry : jsonMap.entrySet()) { System.out.println(entry.getKey() + "->:" + entry.getValue()); System.out.println(entry.getValue()); Map<String,
         * Integer> jsonMap1 = JSON.parseObject(entry.getValue(), new TypeReference<HashMap<String, Integer>>() {}); System.out.println(jsonMap1);
         * 
         * for (Map.Entry<String, Integer> entry1 : jsonMap1.entrySet()) { System.out.println(entry1.getKey() + ">>>>" + entry1.getValue()); }
         * 
         * }
         */

        /*
         * Object o = El.eval(context, formulaStr); List<BlockKm> sortList = new ArrayList<BlockKm>(); BlockKm b1 = new BlockKm(); b1.setFreight(new BigDecimal("4")); b1.setKm(new
         * BigDecimal("5"));
         * 
         * BlockKm b2 = new BlockKm(); b2.setFreight(new BigDecimal("5")); b2.setKm(new BigDecimal("10"));
         * 
         * BlockKm b3 = new BlockKm(); b3.setFreight(new BigDecimal("6")); b3.setKm(new BigDecimal("20"));
         * 
         * BlockKm b4 = new BlockKm(); b4.setFreight(new BigDecimal("7")); b4.setKm(new BigDecimal("25"));
         * 
         * sortList.add(b1); sortList.add(b2); sortList.add(b3); sortList.add(b4); Collections.sort(sortList);
         * 
         * BigDecimal overKmFreight = BigDecimal.ZERO; BigDecimal overKm = new BigDecimal("9"); BigDecimal calculatedKm = BigDecimal.ZERO;// 已经计算过的公里 System.out.println(
         * "over km : " + overKm); if (overKm.compareTo(BigDecimal.ZERO) == 1) { // 与分段的第一段进行比较
         * 
         * for (BlockKm bk : sortList) { BigDecimal diffKm = overKm.subtract(bk.getKm()); if (diffKm.compareTo(BigDecimal.ZERO) == 1) {// 大于 overKmFreight =
         * overKmFreight.add(bk.getKm().subtract(calculatedKm).multiply(bk.getFreight())); calculatedKm = bk.getKm(); } else { overKmFreight =
         * overKmFreight.add(overKm.subtract(calculatedKm).multiply(bk.getFreight())); break; } } } System.out.println("a:"); System.out.println(overKmFreight);
         */
    }

    /**
     * 
     * <p>
     * Title: calFreightForProject
     * </p>
     * <p>
     * Description: 计价系数*计价维度单价
     * </p>
     * 
     * @param paramJson
     * @param ruleJson
     * @return
     * @see com.juma.tgm.configure.service.PrivateFreightFactorService#calFreightForProject(java.lang.String, java.lang.String)
     */
    @Override
    public BigDecimal calFreightForProject(String paramJson, String ruleJson) {
        BigDecimal f = BigDecimal.ZERO;
        // 兼容老数据，默认按工作量
        Integer valuationWay = ValuationWayEnum.WORKLOAD.getCode();
        if (StringUtils.isBlank(ruleJson)) return f;
        String _paramJson_ = StringUtils.isBlank(paramJson) ? "{}" : paramJson;
        Map<String, String> paramMap = JSON.parseObject(_paramJson_, new TypeReference<HashMap<String, String>>() {});
        Map<String, String> ruleMap = JSON.parseObject(ruleJson, new TypeReference<HashMap<String, String>>() {});
        if (ruleMap.containsKey("factorJson")) {
            ruleMap = JSON.parseObject(ruleMap.get("factorJson"), new TypeReference<HashMap<String, String>>() {});
            if (ruleMap.containsKey("valuationWay") && ruleMap.get("valuationWay") != null) {
                valuationWay = Integer.valueOf(ruleMap.get("valuationWay"));
            }

            // 一口价
            if (NumberUtils.compare(valuationWay, ValuationWayEnum.FIXED_PRICE.getCode()) == 0) {
                if (ruleMap.isEmpty()) {
                    return f;
                }
                return new BigDecimal(ruleMap.entrySet().iterator().next().getValue());
            }
            
            //只有起步价
            if (ruleMap.size() == 1 && ruleMap.containsKey("initiateRate")) {
                return new BigDecimal(ruleMap.get("initiateRate"));
            }
            
            for (Map.Entry<String, String> entry : ruleMap.entrySet()) {
                String key = entry.getKey();
                if (StringUtils.isBlank(entry.getValue())) continue;
                if (paramMap.containsKey(key)) {
                    f = f.add(new BigDecimal(entry.getValue()).multiply(new BigDecimal(paramMap.get(key))));
                } else {
                    f = f.add(new BigDecimal(entry.getValue()));
                }
            }
        }
        return f;
    }

}
