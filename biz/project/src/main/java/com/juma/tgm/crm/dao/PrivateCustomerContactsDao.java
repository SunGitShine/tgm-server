package com.juma.tgm.crm.dao;

import java.util.List;

import com.giants.dal.dao.mybatis.MybatisDao;
import com.juma.tgm.crm.domain.PrivateCustomerContacts;

/*
 * 
 * @author  2017-03-13
 * @version 1.0 
 */

public interface PrivateCustomerContactsDao extends MybatisDao<PrivateCustomerContacts> {
    
    List<PrivateCustomerContacts> findPrivateCustomerContactsBy(Integer privateCustomerId);
    
    void deleteByPrivateCustomerId(Integer privateCustomerId);
    
    PrivateCustomerContacts findPrivateCustomerContactsByPhone(String phone);
    
}
