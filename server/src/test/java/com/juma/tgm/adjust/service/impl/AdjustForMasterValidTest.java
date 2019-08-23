package com.juma.tgm.adjust.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.tgm.fms.domain.v3.AdjustForItem;
import com.juma.tgm.fms.domain.v3.AdjustForMaster;
import com.juma.tgm.fms.domain.v3.AdjustForWaybillTemp;
import com.juma.tgm.fms.domain.v3.enums.AdjustMasterType;
import com.juma.tgm.fms.domain.v3.enums.AdjustStatus;
import com.juma.tgm.fms.domain.v3.vo.AdjustAttachVO;
import com.juma.tgm.fms.domain.v3.vo.AdjustForItemFilter;
import com.juma.tgm.fms.domain.v3.vo.AdjustForMasterFilter;
import com.juma.tgm.fms.service.v3.AdjustForMasterAddService;
import com.juma.vms.transport.enumeration.ApprovalStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * 功能 :
 * @author : Bruce(刘正航) 16:15 2019-07-23
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/META-INF/spring-standalone-beans.xml"
})
public class AdjustForMasterValidTest {

    @Autowired
    private AdjustForMasterAddService adjustForMasterAddService;

    /**这里需要启动manage,通过断点方式获取json字符串,在这里使用**/
    private static final LoginEmployee loginEmployee = new Gson().fromJson(
            "{\"employeeId\":2904,\"maxInactiveInterval\":3600,\"loginDepartment\":{\"departmentId\":1479,\"departmentCode\":\"00\",\"departmentName\":\"驹马配送\",\"businessAreas\":[{\"areaCode\":\"00\",\"areaName\":\"全国\"}]},\"authDepartments\":[{\"departmentId\":1479,\"departmentName\":\"驹马配送\"}],\"sessionId\":\"757EB2EB0BCF4DF09BB0BFC4F4C5BD21\",\"tenantId\":19,\"tenantCode\":\"000000004\",\"userId\":1,\"loginName\":\"admin\",\"userName\":\"超级管理员\",\"mobileNumber\":\"18030574832\",\"isTest\":true,\"isSysUser\":true}",
            LoginEmployee.class);

    @Test
    public void testWaybillAdjustRecords(){
        AdjustAttachVO vo = new AdjustAttachVO();
        vo.setAdjustId(174);
        vo.setAdjustForWho(AdjustMasterType.VENDOR.getCode());
        List<Integer> waybillIds = Lists.newArrayList();
        waybillIds.add(1756426);
        waybillIds.add(1757965);
        Map<Integer, Integer> integerIntegerMap = fetchWaybillAdjustRecords(vo, loginEmployee, waybillIds);
        AdjustForWaybillTemp temp = new AdjustForWaybillTemp();
        temp.setWaybillId(1757965);
        System.out.println(validNonCurrAdjust(vo,temp,integerIntegerMap,loginEmployee));
        System.out.println(JSON.toJSONString(temp));
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
}
