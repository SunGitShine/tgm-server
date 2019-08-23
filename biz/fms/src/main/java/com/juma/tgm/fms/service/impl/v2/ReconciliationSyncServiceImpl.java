package com.juma.tgm.fms.service.impl.v2;

import com.alibaba.fastjson.JSON;
import com.giants.common.exception.BusinessException;
import com.juma.auth.employee.domain.Employee;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.user.domain.User;
import com.juma.auth.user.service.UserService;
import com.juma.fms.core.domain.ReceiptStatusDO;
import com.juma.message.gateway.service.MessageServiceProvider;
import com.juma.tgm.common.BigDecimalUtil;
import com.juma.tgm.common.Constants;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.fms.domain.v2.ReconciliationItemNew;
import com.juma.tgm.fms.domain.v2.ReconciliationNew;
import com.juma.tgm.fms.domain.v2.enums.ReconciliationEnums;
import com.juma.tgm.fms.service.v2.ReconciliationService;
import com.juma.tgm.fms.service.v2.ReconciliationSyncService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.service.WaybillCommonService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;


@Service
public class ReconciliationSyncServiceImpl implements ReconciliationSyncService {


    private static final String SMS_RECEIPT_KEY = "TMS_RECONCILIATION_RECEIPT_SMS";

    private static final String APP_RECEIPT_KEY = "TMS_RECONCILIATION_RECEIPT_APP";

    private static final int FIXED_TENANT_ID_ZHUANGCHE = 2;

    private static final Integer RENTER_TYPE_VENDOR=1;// 承运商

    private static final Logger logger = LoggerFactory.getLogger(ReconciliationSyncServiceImpl.class);

    @Autowired
    private MessageServiceProvider messageServiceProvider;

    @Autowired
    private ReconciliationService reconciliationService;

    @Autowired
    private WaybillCommonService waybillCommonService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CustomerInfoService customerInfoService;

    @Override
    public void settlement(String reconciliationNo, String plateNumber, Integer settlementState, BigDecimal settlement,Integer renterType, Integer renterId) {
        ReconciliationItemNew reconciliationItemNew = null;
        if( renterType.equals(RENTER_TYPE_VENDOR )) {
            plateNumber = null;// 做一个容错
            reconciliationItemNew = reconciliationService.findReconciliationItemNewByVendorIdAndReconciliationNo( reconciliationNo , renterId );
        }
        else {
            reconciliationItemNew = reconciliationService.findReconciliationItemNewByPlateNumberAndReconciliationNo(reconciliationNo, plateNumber);
        }
        reconciliationItemNew.setPayStatus(settlementState.byteValue());
        reconciliationItemNew.setHasPayFreight(settlement);
        reconciliationService.updateReconciliationItemNew(reconciliationItemNew);
        Waybill.SettlementStatus settlementStatus = Waybill.SettlementStatus.getSettlementStatusByCode(reconciliationItemNew.getPayStatus());
        if (settlementStatus == null) {
            throw new BusinessException("WaybillSettlementStatusError", "errors.common.prompt", "结算状态码同步错误:" + settlement);
        }
        //已结算
        this.updateWaybillSettlementStatus(reconciliationNo, plateNumber, renterId ,settlementStatus );
    }

    @Override
    public void receipt(String reconciliationNo, BigDecimal receipt) {
        ReconciliationNew reconciliationNew = reconciliationService.findReconciliationNewByReconciliationNo(reconciliationNo);
        BigDecimalUtil.nullToZero(reconciliationNew);
        // 增量 +
        BigDecimal hasReceiveFreight = reconciliationNew.getHasReceiveFreight().add(receipt);
        reconciliationNew.setHasReceiveFreight(hasReceiveFreight);
        if (reconciliationNew.getCustomerFinalBeforeTax().compareTo(hasReceiveFreight) > 0) {
            reconciliationNew.setReceiveStatus(Integer.valueOf(ReconciliationEnums.ShipperPayStatus.PART.getCode()).byteValue());
            this.updateWaybillReceiptStatus(reconciliationNo, Waybill.ReceiptStatus.SEGMENT_COLLECTION);
        } else {
            // 更新运单 收款完成
            // 原来的运单只有 对客户的结算状态，没有分别对客户和司机进行收款和结算
            this.updateWaybillReceiptStatus(reconciliationNo, Waybill.ReceiptStatus.HAS_COLLECTION);
            reconciliationNew.setReceiveStatus(Integer.valueOf(ReconciliationEnums.ShipperPayStatus.DONE.getCode()).byteValue());
        }
        reconciliationService.updateReconciliationNew(reconciliationNew);// 更新
        try {
            this.receiptMsgEvent(reconciliationNew, hasReceiveFreight, receipt);
        } catch (Exception e) {
            // 发送收款短信，这里需要处理掉异常，如果发送失败则记录日志，不重复消费消息
            logger.error(e.getMessage(), e);
        } finally {
            logger.info("对账单:" + reconciliationNo + ",收到" + receipt + "元，累积收款:" + hasReceiveFreight + ",触发发送收款短信事件!");
        }
    }

    @Override
    public void invoice(String reconciliationNo, String invoiceNo) {
        ReconciliationNew reconciliationNew = reconciliationService.findReconciliationNewByReconciliationNo(reconciliationNo);
        reconciliationNew.setInvoiceNo(invoiceNo);
        reconciliationService.updateReconciliationNew(reconciliationNew);
    }

    @Override
    public void receipt(ReceiptStatusDO receiptStatusDO) {
        logger.info("收款通知dubbo:{}", JSON.toJSONString(receiptStatusDO));
        if(receiptStatusDO == null) return ;

        BigDecimal receipt = receiptStatusDO.getReceipt();
        if (receipt == null || receipt.equals(BigDecimal.ZERO)) {
            throw new RuntimeException("错误的收款金额,收款金额为空或者为0");
        }

        String reconciliationNo = receiptStatusDO.getReconciliationNo();
        if (StringUtils.isBlank(reconciliationNo)) {
            throw new RuntimeException("错误的运单号，运单号为空");
        }

        this.receipt(reconciliationNo, receipt);
    }

    /**
     * 修改运单收款状态
     *
     * @param reconciliationNo
     */
    private void updateWaybillReceiptStatus(String reconciliationNo, Waybill.ReceiptStatus receiptStatus) {
//        List<Waybill> waybills = waybillCommonService.findByReconciliationNo(reconciliationNo);
//        if(CollectionUtils.isEmpty(waybills)) return ;
//
//        for (Waybill waybill : waybills) {
//            waybill.setReceiptStatus(receiptStatus.getCode());
//        }
//        waybillCommonService.batchUpdateHasReconciliation(waybills); // 批量更新 到已结算
        waybillCommonService.modifyWaybillReceiptStatusByReconciliationNo(reconciliationNo, receiptStatus, Constants.SYS_LOGIN_USER);

    }


    /**
     * 修改运单结算状态
     *
     * @param reconciliationNo
     * @param settlementStatus
     */
    private void updateWaybillSettlementStatus(String reconciliationNo, String plateNumber, Integer vendorId, Waybill.SettlementStatus settlementStatus ) {
        waybillCommonService.modifyWaybillSettlementStatusByReconciliationNo(reconciliationNo, plateNumber, vendorId ,settlementStatus,Constants.SYS_LOGIN_USER);
    }


    /***
     *
     *
     * 收款 短信事件,当收到客户款项时候触发
     *
     *
     * @param reconciliationNew 对账单
     *
     * @param hasReceiveFreight 累积已经收款
     *
     * @param receipt 此次收款
     * */
    private void receiptMsgEvent(ReconciliationNew reconciliationNew, BigDecimal hasReceiveFreight, BigDecimal receipt) {

        CustomerInfo customerInfo;
        try {
            customerInfo = customerInfoService.findCusInfoById(reconciliationNew.getCustomerId());
        } catch (Exception e) {
            logger.error("从crm 获取客户信息失败", e);
            return;
        }
        Integer employeeId = customerInfo.getCustomerManagerUserId();//CustomerManagerUserId是employeeId;
        Employee employee = employeeService.loadEmployee(employeeId);
        if (employee == null) {
            logger.info("employeeId：" + employeeId + "无法在用户中心查找到对应的用户!");
            return;
        }
        User user = userService.findUser(employee.getUserId());
        if (user == null) {
            logger.info("错误的userId：" + employee.getUserId() + "无法在用户中心查找到对应的用户!");
            return;
        }

        DecimalFormat format = new DecimalFormat("#,###.##");

        Map<String, Object> modelParamMap = new HashMap<>();
//        modelParamMap.put("reconciliationNo", reconciliationNew.getReconciliationNo());
        modelParamMap.put("receipt", format.format(receipt));
        modelParamMap.put("customerName", customerInfo.getCustomerName());
//        modelParamMap.put("hasReceiveFreight", hasReceiveFreight);
//        modelParamMap.put("customerFinalBeforeTax", reconciliationNew.getCustomerFinalBeforeTax());
        this.doSendMsg(modelParamMap, customerInfo, user);
    }

    private void doSendMsg(Map<String, Object> modelParamMap, CustomerInfo customerInfo, User user) {
        if (StringUtils.isNotBlank(user.getMobileNumber())) {
            //使用市场助手title
            messageServiceProvider.pushSmsMessage(FIXED_TENANT_ID_ZHUANGCHE, SMS_RECEIPT_KEY, modelParamMap, user.getMobileNumber());//user.getMobileNumber()
        } else {
            logger.info("crmCustomerId:" + customerInfo.getCrmCustomerId() + ",对应的客户:" + user.getName() + "没有手机号，无法发送收款通知短信!");
        }
        //使用市场助手title
        messageServiceProvider.pushAppMessage(FIXED_TENANT_ID_ZHUANGCHE, APP_RECEIPT_KEY, modelParamMap, user.getUserId().toString());
    }

}
