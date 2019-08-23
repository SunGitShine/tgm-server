package com.juma.tgm.contactHistoryService.service.impl;

import javax.annotation.Resource;

import org.testng.annotations.Test;

import com.juma.tgm.waybill.domain.AddressHistory;
import com.juma.tgm.waybill.domain.ContactHistory;
import com.juma.tgm.waybill.service.ContactHistoryService;

import testng.BaseTestNGTest;

/**
 * @ClassName ContactHistoryServiceImpl.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年8月21日 上午9:40:13
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class ContactHistoryServiceImplTest extends BaseTestNGTest {

    @Resource
    private ContactHistoryService contactHistoryService;

    @Test
    public void addOrUpdateContactHis() {
        AddressHistory address = new AddressHistory();
        address.setAddressId(359);
        ContactHistory contactHistory = new ContactHistory();
        contactHistory.setContactName("吉利");
        contactHistory.setContactPhone("15882104180");
        contactHistory.setSpareContactPhone("15882104181");
        
        contactHistoryService.addOrUpdateContactHis(address, contactHistory);
    }
}
