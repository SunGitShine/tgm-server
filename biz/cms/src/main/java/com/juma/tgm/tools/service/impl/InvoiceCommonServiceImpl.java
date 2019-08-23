package com.juma.tgm.tools.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bruce.tool.rpc.http.core.Https;
import com.giants.common.exception.BusinessException;
import com.google.common.collect.Lists;
import com.juma.tgm.fms.domain.v3.vo.InvoiceAmount;
import com.juma.tgm.tools.service.InvoiceCommonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * 功能 : 
 *
 * @author : Bruce(刘正航) 12:58 2019-05-16
 */
@Slf4j
@Service
public class InvoiceCommonServiceImpl implements InvoiceCommonService {

    /**开票系统基础访问地址**/
    @Value("${tms.customer.reconciliation.invoice.url}")
    private String BASE_URL;

    /**开票系统-api调用开关**/
    @Value("${tms.customer.reconciliation.invoice.switch}")
    private String openInvoiceDoor;

    /**获取开票数据金额**/
    private static final String INVOICE_AMOUNT_INFO_URL = "/invoice/datapool/amount.html";
    /**开票数据冻结**/
    private static final String INVOICE_FROZEN_INFO_URL = "/invoice/datapool/frozen.html";
    /**开票数据解冻**/
    private static final String INVOICE_UNFROZEN_INFO_URL = "/invoice/datapool/unfreeze.html";

    /**根据运单号:获取开票信息**/
    @Override
    public List<InvoiceAmount> fetchInvoiceAmount(final String reconciliationNo) throws BusinessException {
        log.info("客户调整单-查询开票信息,调整单:{}",reconciliationNo);
        String result;
        try {
            result = Https.create()
                    .print(true)
                    .url(BASE_URL + INVOICE_AMOUNT_INFO_URL)
                    .add("sourceDocumentNo",reconciliationNo)
                    .get();
        } catch (Exception e) {
            log.warn("请求错误,错误信息:{}",e.getMessage());
            throw new BusinessException("FetchInvoiceNotNull","开票信息获取失败");
        }

        if(StringUtils.isBlank(result) ){
            log.info("客户调整单-查询开票信息,调整单:{}, 请求返回结果为空",reconciliationNo);
            return Lists.newArrayList();
        }

        JSONObject jsonObject = JSON.parseObject(result);
        if( !Integer.valueOf(0).equals(jsonObject.get("code")) ){
            log.info("客户调整单-查询开票信息,调整单:{}, 错误信息:{}",reconciliationNo,result);
            return Lists.newArrayList();
        }

        List<InvoiceAmount> list = JSON.parseArray(jsonObject.getString("data"),InvoiceAmount.class);
        if(CollectionUtils.isEmpty(list)){
            log.info("客户调整单-查询开票信息,调整单:{}, 错误信息:{}",reconciliationNo,result);
            return Lists.newArrayList();
        }
        log.info("客户调整单-冻结开票信息,调整单:{},数据解析成功",reconciliationNo);
        return list;
    }

    /**对账单调整后的金额 >= 该对账单的（已开票金额+开票中金额）true**/
    @Override
    public boolean validReceivableInvoiceAmount(final String reconcilicationNo, final BigDecimal afterAdjustAmount) throws BusinessException {
        log.info("客户调整单-查询已开票金额,调整单:{},调整后金额:{}",reconcilicationNo,afterAdjustAmount);
        // 配置开关
        if( StringUtils.isNotBlank(openInvoiceDoor) && !Boolean.parseBoolean(openInvoiceDoor) ){
            log.info("客户调整单-查询已开票金额,调整单:{}, API开关未打开",reconcilicationNo);
            return true;
        }
        List<InvoiceAmount> invoiceAmounts = fetchInvoiceAmount(reconcilicationNo);
        if( CollectionUtils.isEmpty(invoiceAmounts) ){
            return true;
        }
        // 如果是多条数据(转运单), 任何一条不满足, 都不能调价
        for (InvoiceAmount invoiceAmount : invoiceAmounts) {
            if( null == invoiceAmount ){ continue; }
            BigDecimal invoicedAmount = BigDecimal.ZERO;
            BigDecimal invoicingAmount = BigDecimal.ZERO;
            if( null != invoiceAmount.getInvoicedAmount() ){
                invoicedAmount = invoiceAmount.getInvoicedAmount();
            }
            if( null != invoiceAmount.getInvoicingAmount() ){
                invoicingAmount = invoiceAmount.getInvoicingAmount();
            }
            if(afterAdjustAmount.compareTo(invoicedAmount.add(invoicingAmount)) < 0){
                return false;
            }
        }
        return true;
    }

    /**根据对账单号:冻结开票信息**/
    @Override
    public boolean frozenReceivableInvoice(final String reconciliationNo) throws BusinessException {
        log.info("客户调整单-冻结开票信息,调整单:{}",reconciliationNo);
        // 配置开关
        if( StringUtils.isNotBlank(openInvoiceDoor) && !Boolean.parseBoolean(openInvoiceDoor) ){
            log.info("客户调整单-冻结开票信息,调整单:{}, API开关未打开",reconciliationNo);
            return true;
        }
        String result;
        try {
            result = Https.create()
                    .print(true)
                    .url(BASE_URL + INVOICE_FROZEN_INFO_URL)
                    .addBody("sourceDocumentNo",reconciliationNo)
                    .post();
        } catch (Exception e) {
            log.warn("请求错误,错误信息:{}",e.getMessage());
            throw new BusinessException("FrozenReconciliationFail","冻结开票信息失败");
        }

        if(StringUtils.isBlank(result) ){
            log.info("客户调整单-冻结开票信息,调整单:{}, 请求返回结果为空",reconciliationNo);
            return false;
        }

        JSONObject jsonObject = JSON.parseObject(result);
        if( !Integer.valueOf(0).equals(jsonObject.get("code")) ){
            log.info("客户调整单-冻结开票信息,调整单:{}, 错误信息:{}", reconciliationNo, result);
            return false;
        }
        log.info("客户调整单-冻结开票信息,调整单:{}, 成功冻结",reconciliationNo);
        return true;
    }

    /**根据对账单号:解冻开票信息**/
    @Override
    public boolean unfrozenReceivableInvoice(final String reconciliationNo) throws BusinessException {
        log.info("客户调整单-解冻开票信息,调整单:{}",reconciliationNo);
        // 配置开关
        if( StringUtils.isNotBlank(openInvoiceDoor) && !Boolean.parseBoolean(openInvoiceDoor) ){
            log.info("客户调整单-解冻开票信息,调整单:{}, API开关未打开",reconciliationNo);
            return true;
        }
        String result;
        try {
            result = Https.create()
                    .print(true)
                    .url(BASE_URL + INVOICE_UNFROZEN_INFO_URL)
                    .addBody("sourceDocumentNo",reconciliationNo)
                    .post();
        } catch (Exception e) {
            log.warn("请求错误,错误信息:{}",e.getMessage());
            throw new BusinessException("UnFrozenReconciliationFail","解冻开票信息失败");
        }

        if(StringUtils.isBlank(result) ){
            log.info("客户调整单-解冻开票信息,调整单:{}, 请求返回结果为空",reconciliationNo);
            return false;
        }

        JSONObject jsonObject = JSON.parseObject(result);
        if( !Integer.valueOf(0).equals(jsonObject.get("code")) ){
            log.info("客户调整单-解冻开票信息,调整单:{}, 错误信息:{}", reconciliationNo, result);
            return false;
        }
        log.info("客户调整单-解冻开票信息,调整单:{}, 成功解冻",reconciliationNo);
        return true;
    }

}
