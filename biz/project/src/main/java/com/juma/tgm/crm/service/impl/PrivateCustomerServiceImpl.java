package com.juma.tgm.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.tgm.crm.dao.PrivateCustomerContactsDao;
import com.juma.tgm.crm.dao.PrivateCustomerDao;
import com.juma.tgm.crm.domain.PrivateCustomer;
import com.juma.tgm.crm.domain.PrivateCustomerContacts;
import com.juma.tgm.crm.service.PrivateCustomerService;

@Service
public class PrivateCustomerServiceImpl implements PrivateCustomerService {

    @Resource
    private PrivateCustomerDao privateCustomerDao;

    @Resource
    private PrivateCustomerContactsDao privateCustomerContactsDao;

    @Override
    public Page<PrivateCustomer> searchDetails(PageCondition pageCondition) {
        //按电话查询
        if (pageCondition.getFilters() != null && pageCondition.getFilters().containsKey("contactsPhone")) {
            String phone = (String) pageCondition.getFilters().get("contactsPhone");
            PrivateCustomerContacts privateCustomerContacts = privateCustomerContactsDao.findPrivateCustomerContactsByPhone(phone);
            if (privateCustomerContacts != null) {
                pageCondition.getFilters().put("privateCustomerId", privateCustomerContacts.getPrivateCustomerId());
            } else {
                pageCondition.getFilters().put("name", "nothing");//没有对应联系电话的客户
            }
        }
        int total = privateCustomerDao.searchCount(pageCondition);
        List<PrivateCustomer> results = privateCustomerDao.search(pageCondition);
        return new Page<>(pageCondition.getPageNo(), pageCondition.getPageSize(), total, results);
    }

    @Override
    public PrivateCustomer findPrivateCustomerById(Integer privateCustomerId) {
        PrivateCustomer privateCustomer = privateCustomerDao.get(privateCustomerId);
        if (privateCustomer == null) {
            throw new BusinessException("private.customer.notFound", "private.customer.notFound");
        }
        List<PrivateCustomerContacts> rows = privateCustomerContactsDao.findPrivateCustomerContactsBy(privateCustomerId);
        privateCustomer.setPrivateCustomerContacts(rows);
        return privateCustomer;
    }

    @Override
    public void updatePrivateCustomer(PrivateCustomer privateCustomer) {
        if (privateCustomer.getPrivateCustomerId() == null) {
            throw new BusinessException("private.customer.notFound", "private.customer.notFound");
        }
        PrivateCustomer _privateCustomer = privateCustomerDao.get(privateCustomer.getPrivateCustomerId());
        if (_privateCustomer == null) {
            throw new BusinessException("private.customer.notFound", "private.customer.notFound");
        }
        privateCustomerDao.update(privateCustomer);
        if (privateCustomer.getPrivateCustomerContacts() != null && !privateCustomer.getPrivateCustomerContacts().isEmpty()) {
            privateCustomerContactsDao.deleteByPrivateCustomerId(privateCustomer.getPrivateCustomerId());
            privateCustomerContactsDao.insertAll(privateCustomer.getPrivateCustomerContacts());
        }
    }

}
