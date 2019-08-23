/**
* @Title: AddressHistoryServiceImpl.java
* @Package com.juma.tgm.waybill.service.impl
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年7月4日 下午2:41:30
* @version V1.0  
 */
package com.juma.tgm.waybill.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybill.dao.AddressHistoryDao;
import com.juma.tgm.waybill.domain.AddressHistory;
import com.juma.tgm.waybill.domain.AddressHistory.AddressType;
import com.juma.tgm.waybill.domain.ContactHistory;
import com.juma.tgm.waybill.domain.WaybillDeliveryAddress;
import com.juma.tgm.waybill.domain.WaybillReceiveAddress;
import com.juma.tgm.waybill.service.AddressHistoryService;
import com.juma.tgm.waybill.service.ContactHistoryService;

/**
 * @Description:
 * @author Administrator
 * @date 2016年7月4日 下午2:41:30
 * @version V1.0
 */
@Service
public class AddressHistoryServiceImpl implements AddressHistoryService {

    private static final Logger log = LoggerFactory.getLogger(AddressHistoryServiceImpl.class);

    @Resource
    private AddressHistoryDao addressHistoryDao;
    @Resource
    private ContactHistoryService contactHistoryService;

    @Override
    public List<AddressHistory> getAddressHistorys(PageCondition pageCondition, LoginUser loginUser) {
        pageCondition.getFilters().put("userId", loginUser.getUserId());
        return addressHistoryDao.search(pageCondition);
    }

    @Override
    public void batchSave(List<AddressHistory> rows) {
        addressHistoryDao.insertAll(rows);
    }

    @Override
    public AddressHistory getAddressHistory(AddressHistory example) {
        List<AddressHistory> rows = addressHistoryDao.findByExample(example);
        return rows.size() > 0 ? rows.get(0) : null;
    }

    @Override
    public void saveAddressHistory(AddressHistory entity) {
        addressHistoryDao.insert(entity);
    }

    @Override
    public void updateAddressHistory(AddressHistory entity) {
        entity.setLastUpdateTime(new Date());
        addressHistoryDao.update(entity);
    }

    @Override
    public void addOrUpdateressHistory(List<WaybillDeliveryAddress> sourceAddr, List<WaybillReceiveAddress> targetAddr,
            LoginUser loginUser) {
        for (WaybillDeliveryAddress address : sourceAddr) {

            AddressHistory h = new AddressHistory();
            h.setUserId(loginUser.getUserId());
            h.setAddressName(address.getAddressName());
            h.setAddressDetail(address.getAddressDetail());
            h.setRegionCode(address.getRegionCode());
            h.setLocation(address.getCoordinates());

            ContactHistory c = new ContactHistory();
            c.setContactName(address.getContactName());
            c.setContactPhone(address.getContactPhone());
            if(address.getSpareContactPhone() == null){
                c.setSpareContactPhone("");
            }else{
                c.setSpareContactPhone(address.getSpareContactPhone());
            }
            checkAddressAndContact(h, c, AddressType.START);
        }
        
        if(targetAddr != null) {
            for (WaybillReceiveAddress address : targetAddr) {

                AddressHistory h = new AddressHistory();
                h.setUserId(loginUser.getUserId());
                h.setAddressName(address.getAddressName());
                h.setAddressDetail(address.getAddressDetail());
                h.setRegionCode(address.getRegionCode());
                h.setLocation(address.getCoordinates());

                ContactHistory c = new ContactHistory();
                c.setContactName(address.getContactName());
                c.setContactPhone(address.getContactPhone());
                c.setSpareContactPhone("");

                checkAddressAndContact(h, c, AddressType.END);
            }
        }
    }

    private void checkAddressAndContact(AddressHistory address, ContactHistory contactHistory,
            AddressType addressType) {
        address.setAddressType(addressType.getCode());
        AddressHistory addressHistoryDb = getAddressHistory(address);
        try {
            if (null == addressHistoryDb) {
                saveAddressHistory(address);
                contactHistoryService.addOrUpdateContactHis(address, contactHistory);
            } else {
                if (StringUtils.isBlank(addressHistoryDb.getLocation())) {
                    addressHistoryDb.setLocation(address.getLocation());
                }
                updateAddressHistory(addressHistoryDb);
                if (StringUtils.isNotBlank(contactHistory.getContactName())
                        || StringUtils.isNotBlank(contactHistory.getContactPhone())) {
                    contactHistoryService.addOrUpdateContactHis(addressHistoryDb, contactHistory);
                }
            }
        } catch (Exception e) {
//            if (ExceptionUtil.businessException(e)) {
//                e.printStackTrace();
//            }
            log.warn(e.getMessage(), e);
        }
    }

    @Override
    public List<AddressHistory> getAddressAndContactHistory(PageCondition pageCondition, LoginUser loginUser) {
        List<AddressHistory> result = new ArrayList<AddressHistory>();
        List<AddressHistory> rows = getAddressHistorys(pageCondition, loginUser);
        for (AddressHistory address : rows) {
            if (StringUtils.isNotBlank(address.getLocation())) {
                pageCondition.getFilters().put("addressId", address.getAddressId());
                address.getContact().addAll(contactHistoryService.getContactHistorys(pageCondition));
                result.add(address);
            }
        }
        return result;
    }

}
