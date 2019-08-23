package com.juma.tgm.main;

import com.alibaba.fastjson.JSONObject;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.tgm.customerManager.domain.FixedDemand;
import com.juma.tgm.customerManager.service.FixedDemandService;
import com.juma.tgm.customerManager.service.vo.FixedDemandVo;
import com.juma.tgm.truck.service.TruckTypeFreightService;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillBo;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.service.WaybillService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName: CustomerInfoTranformer
 * @Description: tgm-server 单独启动类
 * @author: liang
 * @date: 2017-03-10 10:56
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class CustomerInfoTransformer {
    private static final Logger logger = LoggerFactory.getLogger(CustomerInfoTransformer.class);

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/spring-standalone-beans.xml");

        logger.info("====================================初始化完成，准备开始迁移=================================");
        FixedDemandService fixedDemandService = (FixedDemandService) context.getBean("fixedDemandServiceImpl");
        PageCondition pageCondition = new PageCondition();
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(500);
        //1、执行添加司机结算价列的脚本
        //2、实行批量更新历史司机结算价
        //--获取所有固定需求
        Page pageData = fixedDemandService.getFixedDemandList(new LoginEmployee(), pageCondition);
        Collection<FixedDemandVo> datas = pageData.getResults();
        if(CollectionUtils.isEmpty(datas)){
            throw new BusinessException("fixedDemandNull","errors.common.prompt","找不到固定需求数据");
        }
        //--计算司机结算价
        List<FixedDemand> dest = buildCalculateDriverFee(datas,context);
        //--更新固定需求
        fixedDemandService.updateBatch(dest);
        logger.info("====================================数据迁移完成=================================");

        System.exit(0);

    }

    private static List<FixedDemand> buildCalculateDriverFee(Collection<FixedDemandVo> datas, ApplicationContext context){
        TruckTypeFreightService truckTypeFreightService = (TruckTypeFreightService)context.getBean("truckTypeFreightServiceImpl");
        WaybillService waybillService = (WaybillService)context.getBean("waybillServiceImpl");
        TruckRequire truckRequire = null;
        Waybill waybill = null;
        WaybillBo bo = null;
        List<FixedDemand> demandList = new ArrayList<>();
        for(FixedDemandVo vo : datas){
            FixedDemand fd = vo.getFixedDemand();
            try {
                truckRequire = JSONObject.parseObject(fd.getRequireJson(), TruckRequire.class);
            } catch (Exception e) {
                logger.info("用车要求json格式化错误.id:{0}", new String[]{fd.getFixedDemandId().toString()});
                continue;
            }

            waybill = new Waybill();
            waybill.setEstimateFreight(fd.getEstimateFreight());

            bo = new WaybillBo();
            bo.setTruckRequire(truckRequire);
            bo.setWaybill(waybill);
            //司机结算价
            //--获取税后价格
            BigDecimal afterTaxFee = truckTypeFreightService.getAfterTaxFright(truckRequire, waybill);
            if (afterTaxFee == null) continue;//没有税后价格则不能计算司机结算价

            waybill.setAfterTaxFreight(afterTaxFee);
            //--获取司机结算价格
            WaybillParam waybillParam = waybillService.settingExtraFee(bo, null);


            fd.setShow4DriverFreight(waybillParam.getShow4DriverFreight());
            demandList.add(fd);
        }

        return demandList;
    }

}
