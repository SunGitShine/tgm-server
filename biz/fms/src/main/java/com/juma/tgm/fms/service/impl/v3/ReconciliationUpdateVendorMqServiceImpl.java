package com.juma.tgm.fms.service.impl.v3;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.juma.tgm.fms.dao.v3.ReconciliationUpdateVendorMqMapper;
import com.juma.tgm.fms.domain.v3.ReconciliationUpdateVendorMq;
import com.juma.tgm.fms.service.v3.ReconciliationUpdateVendorMqService;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-05-28 15:14
 **/
@Service
public class ReconciliationUpdateVendorMqServiceImpl implements ReconciliationUpdateVendorMqService {

	@Resource
	private ReconciliationUpdateVendorMqMapper vendorMqMapper;

	@Override
	public void insert(ReconciliationUpdateVendorMq vendorMq) {
		vendorMqMapper.insertSelective(vendorMq);
	}
}
