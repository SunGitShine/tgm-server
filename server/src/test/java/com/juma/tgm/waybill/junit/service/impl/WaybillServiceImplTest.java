package com.juma.tgm.waybill.junit.service.impl;

import com.juma.tgm.waybill.service.WaybillService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName WaybillServiceImplTest
 * @Description TODO
 * @Author weilibin
 * @Date 2019-06-10 18:31
 * @Version 1.0.0
 */

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:/META-INF/spring-standalone-beans.xml"
})
public class WaybillServiceImplTest {

    @Resource
    private WaybillService waybillService;

    @Test
    public void addPriceWaybill() {
        waybillService.addPriceWaybill(1513131, 10000, null);
    }
}