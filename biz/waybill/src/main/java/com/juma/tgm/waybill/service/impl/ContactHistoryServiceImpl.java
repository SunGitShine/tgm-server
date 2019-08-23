/**
* @Title: ContactHistoryServiceImpl.java
* @Package com.juma.tgm.waybill.service.impl
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年7月4日 下午2:40:47
* @version V1.0  
 */
package com.juma.tgm.waybill.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.ExceptionUtil;
import com.juma.tgm.waybill.dao.ContactHistoryDao;
import com.juma.tgm.waybill.domain.AddressHistory;
import com.juma.tgm.waybill.domain.ContactHistory;
import com.juma.tgm.waybill.service.ContactHistoryService;

/**
 * @Description:
 * @author Administrator
 * @date 2016年7月4日 下午2:40:47
 * @version V1.0
 */
@Service
public class ContactHistoryServiceImpl implements ContactHistoryService {

    private static final Logger log = LoggerFactory.getLogger(ContactHistoryServiceImpl.class);

    @Resource
    private ContactHistoryDao contactHistoryDao;

    @Override
    public List<ContactHistory> getContactHistorys(PageCondition pageCondition) {
        return contactHistoryDao.search(pageCondition);
    }

    @Override
    public void batchSave(List<ContactHistory> rows) {
        contactHistoryDao.insertAll(rows);
    }

    @Override
    public ContactHistory getContactHistory(ContactHistory example) {
        List<ContactHistory> rows = contactHistoryDao.findByExample(example);
        return rows.size() > 0 ? rows.get(0) : null;
    }

    @Override
    public void saveContactHistory(ContactHistory entity) {
        contactHistoryDao.insert(entity);
    }

    @Override
    public void updateContactHistory(ContactHistory entity) {
        contactHistoryDao.update(entity);
    }

    @Override
    public void addOrUpdateContactHis(AddressHistory address, ContactHistory contactHistory) {
        log.info("addOrUpdateContactHis is {}", JSON.toJSON(contactHistory));
        if (StringUtils.isBlank(contactHistory.getContactName())
                || StringUtils.isBlank(contactHistory.getContactPhone()) || null == address.getAddressId()) {
            return;
        }

        try {
            contactHistory.setAddressId(address.getAddressId());
            ContactHistory contactHistoryDb = getContactHistory(contactHistory);
            log.info("addOrUpdateContactHis contactHistoryDb is {}", JSON.toJSON(contactHistoryDb));

            String newMd5Digest = DigestUtils.md5Hex(contactHistory.getContactName() + contactHistory.getContactPhone()
                    + contactHistory.getAddressId() + contactHistory.getSpareContactPhone());
            contactHistory.setMd5(newMd5Digest);

            if (null == contactHistoryDb) {
                saveContactHistory(contactHistory);
                return;
            }

            if (!newMd5Digest.equals(contactHistoryDb.getMd5())) {
                saveContactHistory(contactHistory);
                return;
            }

            contactHistory.setContactId(contactHistoryDb.getContactId());
            updateContactHistory(contactHistory);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Integer contactId, LoginUser loginUser) {
        if (null == contactId) {
            return;
        }
        ContactHistory contactHistory = new ContactHistory();
        contactHistory.setContactId(contactId);
        contactHistoryDao.delete(contactHistory);
    }

}
