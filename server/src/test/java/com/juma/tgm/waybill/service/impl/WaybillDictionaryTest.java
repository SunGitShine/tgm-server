package com.juma.tgm.waybill.service.impl;

import com.alibaba.fastjson.JSON;
import com.juma.tgm.waybill.dao.WaybillParamDao;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.WaybillAmount;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.domain.vo.WaybillParamFilter;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.tgm.waybill.service.WaybillAmountService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.testng.collections.Lists;

import java.util.List;
import java.util.Map;

/**
 * 功能 :
 * @author : Bruce(刘正航) 16:28 2019-07-29
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/META-INF/spring-standalone-beans.xml"
})
public class WaybillDictionaryTest {

    @Autowired
    private WaybillParamDao waybillParamDao;

    @Autowired
    private TruckRequireService truckRequireService;

    @Autowired
    private WaybillAmountService waybillAmountService;

    @Test
    public void waybillParams(){
        WaybillParamFilter filter = new WaybillParamFilter();
        List<Integer> ids = Lists.newArrayList(1757973,1496006);
        filter.setWaybillIds(ids);
        List<WaybillParam> params = waybillParamDao.selectByExample(filter);
        Assert.notEmpty(params);
        System.out.println(JSON.toJSONString(params));
    }

    @Test
    public void truckRequireTest(){
        List<Integer> waybillIds = Lists.newArrayList();
        waybillIds.add(1757973);
        waybillIds.add(1496006);
        Map<Integer, TruckRequire> truckRequireMap = truckRequireService.findDictionaryByWaybillIds(waybillIds);
        Assert.notEmpty(truckRequireMap);
        Map<Integer, WaybillAmount> waybillAmountMap = waybillAmountService.findDictionaryByWaybillIds(waybillIds);
        Assert.notEmpty(waybillAmountMap);
    }

}
