package com.juma.tgm.waybill.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.giants.common.lang.exception.CategoryCodeFormatException;
import com.juma.auth.conf.domain.BusinessAreaNode;
import com.juma.auth.conf.service.BusinessAreaService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.businessArea.service.TgmBusinessAreaService;
import org.testng.Assert;
import org.testng.annotations.Test;
import testng.BaseTestNGTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: TgmBusinessAreaTest
 * @Description:
 * @author: liang
 * @date: 2017-09-07 11:03
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class TgmBusinessAreaTest extends BaseTestNGTest{

    @Resource
    private TgmBusinessAreaService tgmBusinessAreaService;

    @Resource
    private BusinessAreaService businessAreaService;

    @Test
    public void getLogicBusinessAreaTree(){
        LoginUser loginUser = new LoginUser(2, 2975);

        List<BusinessAreaNode> allList = tgmBusinessAreaService.getLogicBusinessAreaTree(loginUser);

        Assert.assertNotNull(allList, "数据不能为空.");

        System.out.println("逻辑业务区域树:" + JSONObject.toJSONString(allList));
    }

    @Test
    public void getLogicBusinessAreaParallel(){
        LoginUser loginUser = new LoginUser(2, 2975);

        List<BusinessAreaNode> allList = tgmBusinessAreaService.getLogicBusinessAreaParallel(loginUser);

        Assert.assertNotNull(allList, "数据不能为空.");

        System.out.println("逻辑业务区域树:" + JSONObject.toJSONString(allList));
    }

    @Test
    public void getAllChildren() throws CategoryCodeFormatException {
        LoginUser loginUser = new LoginUser(2, 2975);
//
//        List<BusinessArea> allList = new ArrayList<>();
//        List<BusinessArea> rootList = businessAreaService.findAllLevelsBusinessArea(Constants.ROOT_BUSINESS_AREA_CODE, loginUser);
//
//
//        List<BusinessArea> children = null;
//        for(BusinessArea ba : rootList){
//            children = businessAreaService.findChildBusinessArea(ba.getBusinessAreaId(), loginUser);
//
//            allList.addAll(children);
//        }
//        Assert.assertNotNull(allList, "数据不能为空.");
//
//        System.out.println("逻辑业务区域树:" + JSONObject.toJSONString(allList));

        List<BusinessAreaNode> treeNode = businessAreaService.findBusinessAreaTree(loginUser.getTenantId());

        Assert.assertNotNull(treeNode, "数据不能为空.");

        System.out.println("逻辑业务区域树:" + JSONObject.toJSONString(treeNode));

    }
}
