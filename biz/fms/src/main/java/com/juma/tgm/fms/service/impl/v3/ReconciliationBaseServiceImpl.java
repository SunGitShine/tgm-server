package com.juma.tgm.fms.service.impl.v3;

import java.util.List;

import com.juma.tgm.waybill.domain.Waybill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giants.common.tools.PageCondition;
import com.juma.tgm.fms.dao.v3.ReconciliationBaseMapper;
import com.juma.tgm.fms.domain.v3.vo.ReceivableApplyVo;
import com.juma.tgm.fms.service.v3.ReconciliationBaseService;

@Service
public class ReconciliationBaseServiceImpl implements ReconciliationBaseService {

	@Autowired
	private ReconciliationBaseMapper reconciliationBaseMapper;

	@Override
	public List<ReceivableApplyVo> findReceivableApplyPage(PageCondition pageCondition) {
		return reconciliationBaseMapper.findReceivableApplyPage(pageCondition);
	}

	@Override
	public List<Waybill> findWaybillByFilter(PageCondition pageCondition) {
		return reconciliationBaseMapper.findWaybillByFilter(pageCondition);
	}

	@Override
	public Integer findReceivableApplyCount(PageCondition pageCondition) {
		return reconciliationBaseMapper.findReceivableApplyCount(pageCondition);
	}
}
