package com.juma.tgm.capacity.service.impl;

import com.alibaba.fastjson.JSON;
import com.giants.common.tools.Page;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.capacity.domian.vo.CapacityFilter;
import com.juma.tgm.capacity.domian.vo.CapacityQuery;
import com.juma.tgm.capacity.service.CapacityService;
import com.juma.tgm.common.query.QueryCond;
import org.testng.annotations.Test;
import testng.BaseTestNGTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class CapacityServiceImplTest extends BaseTestNGTest {

    @Resource
    private CapacityService capacityService;

    @Test
    public void search() {
        QueryCond<CapacityFilter> queryCond = new QueryCond<>();
        CapacityFilter filter = new CapacityFilter();
//        filter.setCapacityStatus(true);
        List<String> areaCodeList = new ArrayList<>();
        areaCodeList.add("00");
        filter.setAreaCodeList(areaCodeList);
        filter.setWaybillId(1513108);
        queryCond.setFilters(filter);
        queryCond.setPageNo(1);
        queryCond.setPageSize(8);

        Page<CapacityQuery> page = capacityService.searchCapacity(queryCond, new LoginUser(2, 1));
        System.out.println(JSON.toJSONString(page));
    }
}
