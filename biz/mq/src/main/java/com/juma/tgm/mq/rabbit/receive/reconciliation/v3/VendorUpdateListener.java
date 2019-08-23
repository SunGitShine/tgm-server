package com.juma.tgm.mq.rabbit.receive.reconciliation.v3;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.juma.tgm.fms.service.v3.AdjustForItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.alibaba.fastjson.JSONObject;
import com.juma.tgm.fms.domain.v3.ReconciliationUpdateVendorMq;
import com.juma.tgm.fms.service.v3.ReconciliationForPayableSyncService;
import com.juma.tgm.fms.service.v3.ReconciliationUpdateVendorMqService;
import com.juma.tgm.mq.domain.ReconcilicationUpdateVendorMQ;
import com.juma.tgm.mq.domain.ReconcilicationWaybill;
import com.juma.tgm.waybill.service.WaybillService;

/**
 * @description: ${description}
 * 应付对账单结算承运商和合同承运商不一致
 * 由FMS通知TMS正确的承运商，更新子对账单、运单、调整单承运商信息
 * @author: xieqiang
 *
 * @create: 2019-05-23 16:08
 **/
public class VendorUpdateListener implements MessageListener, ApplicationContextAware {

	private static final Logger LOGGER = LoggerFactory.getLogger( VendorUpdateListener.class );

	private ApplicationContext applicationContext;

	@Override
	public void onMessage(Message message) {

		LOGGER.info("应付对账单承运商修改监听start");
		ReconciliationForPayableSyncService rePayService = applicationContext.getBean(ReconciliationForPayableSyncService.class);
		WaybillService waybillService = applicationContext.getBean(WaybillService.class);
		ReconciliationUpdateVendorMqService vendorMqService = applicationContext.getBean(ReconciliationUpdateVendorMqService.class);
		AdjustForItemService adjustForItemService = applicationContext.getBean(AdjustForItemService.class);
		String jsonStr = new String(message.getBody(), Charset.forName("utf-8"));
		try {
			ReconcilicationUpdateVendorMQ vendorMQ = JSONObject.parseObject(jsonStr, ReconcilicationUpdateVendorMQ.class);
			if(vendorMQ == null){
				return;
			}
			List<ReconcilicationWaybill> reconcilicationWaybillList = vendorMQ.getReconcilicationWaybillList();
			if(reconcilicationWaybillList == null || reconcilicationWaybillList.isEmpty()){
				return;
			}
			//子对账单号列表
			List<String> subReconcilicationNos = new ArrayList<>();
			for(ReconcilicationWaybill reWaybill : reconcilicationWaybillList){
				//更新运单承运商列表
				waybillService.batchUpdateVendor(vendorMQ.getVendorId(), reWaybill.getWaybillNoList());
				//更新应付对账单附表承运商id和名称
				rePayService.updatePayableItemVendor(reWaybill.getWaybillNoList(), vendorMQ.getVendorId(), vendorMQ.getVendorName());
				subReconcilicationNos.add(reWaybill.getSubReconcilicationNo());
				//同步调整单
				adjustForItemService.batchUpdateVendor(vendorMQ.getVendorId(), reWaybill.getWaybillNoList());
			}
			//更新子对账单
			rePayService.updateSubPayableVendor(subReconcilicationNos, vendorMQ.getVendorId());

			ReconciliationUpdateVendorMq vendorMq = new ReconciliationUpdateVendorMq();
			BeanUtils.copyProperties(vendorMQ, vendorMq);
			vendorMq.setExcuteResult("成功");
			vendorMq.setCreateTime(new Date());
			vendorMq.setMqData(jsonStr);
			vendorMqService.insert(vendorMq);

		}catch (Exception e) {
			// 只输出日志，不抛出异常 避免不能ACK 导致 mq 消息阻塞
			LOGGER.error("VendorUpdateListener,更新应付对账单的承运商异常:" + e.getMessage(), e);
			ReconciliationUpdateVendorMq vendorMq = new ReconciliationUpdateVendorMq();
			vendorMq.setMqData(jsonStr);
			vendorMq.setCreateTime(new Date());
			vendorMq.setExcuteResult("失败：" + e.getMessage());
			vendorMqService.insert(vendorMq);

		}finally {
			LOGGER.info("VendorUpdateListener,更新应付对账单的承运商同步消息：" + jsonStr);
		}

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
