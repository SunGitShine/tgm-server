package com.juma.tgm.fms.service.impl.v3;

import com.giants.common.exception.BusinessException;
import com.juma.auth.employee.domain.Employee;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.user.domain.User;
import com.juma.auth.user.service.UserService;
import com.juma.message.gateway.service.MessageServiceProvider;
import com.juma.tgm.common.Constants;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.fms.domain.v3.ReconcilicationForReceivable;
import com.juma.tgm.fms.domain.v3.enums.ReconcilicationForReceivableEnum;
import com.juma.tgm.fms.service.v3.ReconciliationReceivableSyncService;
import com.juma.tgm.fms.service.v3.ReconcilicationForReceivableService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.service.WaybillCommonService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReconciliationReceivableSyncServiceImpl implements ReconciliationReceivableSyncService {


    private static final String SMS_RECEIPT_KEY = "TMS_RECONCILIATION_RECEIPT_SMS";

    private static final String APP_RECEIPT_KEY = "TMS_RECONCILIATION_RECEIPT_APP";

    private static final int FIXED_TENANT_ID_ZHUANGCHE = 2;

    private static final Integer RENTER_TYPE_VENDOR=1;// 承运商

    private static final Logger logger = LoggerFactory.getLogger(ReconciliationReceivableSyncServiceImpl.class);

    @Autowired
    private MessageServiceProvider messageServiceProvider;

    @Autowired
    private ReconcilicationForReceivableService reconciliationService;

    @Autowired
    private WaybillCommonService waybillCommonService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CustomerInfoService customerInfoService;

    @Override
    public void receipt(String reconciliationNo, BigDecimal receivableFreight, BigDecimal hasReceiveFreight, Integer receiveStatus) {

        ReconcilicationForReceivable reconciliation = reconciliationService.findReconciliationByReconciliationNo(reconciliationNo);
        //更新运单(部分收款不更新运单)
        if(!receiveStatus.equals(2)){
            this.updateWaybillReceiptStatus(reconciliationNo, receiveStatus);
        }

        //更新对账单
        reconciliation.setReceiveStatus(receiveStatus);
        reconciliationService.updateReconciliation(reconciliation);
        try {
            this.receiptMsgEvent(reconciliation.getCustomerId(), hasReceiveFreight, receivableFreight);
        } catch (Exception e) {
            // 发送收款短信，这里需要处理掉异常，如果发送失败则记录日志，不重复消费消息
            logger.error(e.getMessage(), e);
        } finally {
            logger.info("对账单:" + reconciliationNo + "，累积收款:" + hasReceiveFreight + ",触发发送收款短信事件!");
        }
    }

    @Override
    public void invoice(String reconciliationNo) {
        ReconcilicationForReceivable reconciliation = reconciliationService.findReconciliationByReconciliationNo(reconciliationNo);
        if(reconciliation == null){
            throw new BusinessException("reconciliation", "errors.reconciliation.notExist");
        }
        reconciliation.setInvoiceStatus(ReconcilicationForReceivableEnum.InvoiceStatus.HAS_INVOICESTATUS.getCode());
        reconciliationService.updateReconciliation(reconciliation);
    }

    /**
     * 修改运单收款状态
     *
     * @param reconciliationNo
     */
    private void updateWaybillReceiptStatus(String reconciliationNo, Integer receiptStatus) {

        waybillCommonService.modifyWaybillReceiptStatusByReceivableReconcilicationNo(reconciliationNo, receiptStatus, Constants.SYS_LOGIN_USER);
    }


    /**
     * 修改运单结算状态
     *
     * @param reconciliationNo
     * @param settlementStatus
     */
    private void updateWaybillSettlementStatus(String reconciliationNo, String plateNumber, Integer vendorId, Waybill.SettlementStatus settlementStatus ) {
        waybillCommonService.modifyWaybillSettlementStatusByReconciliationNo(reconciliationNo, plateNumber, vendorId ,settlementStatus,
            Constants.SYS_LOGIN_USER);
    }


    /***
     *
     *
     * 收款 短信事件,当收到客户款项时候触发
     *
     *
     * @param hasReceiveFreight 累积已经收款
     *
     * @param receipt 此次收款
     * */
    private void receiptMsgEvent(Integer customerId, BigDecimal hasReceiveFreight, BigDecimal receipt) {

        CustomerInfo customerInfo;
        try {
            customerInfo = customerInfoService.findCusInfoById(customerId);
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
