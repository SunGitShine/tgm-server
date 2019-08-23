package com.juma.tgm.waybill.external.service.impl;

import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.service.WaybillCommonService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import me.about.poi.Mapping;
import me.about.poi.User;
import me.about.poi.reader.XlsxReader;
import me.about.poi.writer.XssfWriter;
import org.testng.annotations.Test;

import com.juma.tgm.external.domain.WaybillExternalExample;

import testng.BaseTestNGTest;

/**
 * @ClassName WaybillExternalServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年7月20日 下午2:33:53
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class WaybillExternalServiceImplTest extends BaseTestNGTest {

    @Resource
    private WaybillCommonService waybillCommonService;

//    @Resource
//    private WaybillExternalService waybillExternalService;
//    
//    @Test
//    public void countWaybillByCustomer() {
//        WaybillExternalExample example = new WaybillExternalExample();
//        example.setCrmCustomerId(87299);
//        int num = waybillExternalService.countWaybillByCustomer(example);
//        System.out.println(num);
//    }

    public static void main(String[] args) throws Exception {
        List<WaybillExportVo> result2018 = new ArrayList<>();
        List<WaybillExportVo> result2019 = new ArrayList<>();
        List<WaybillExportVo> rows =  new XlsxReader().fromInputStream(new FileInputStream("/Users/weilibin/Downloads"
            + "/运单号汇总的1.xlsx"), WaybillExportVo.class, 1);
        List<WaybillExportVo> rows4 =  new XlsxReader().fromInputStream(new FileInputStream("/Users/weilibin/Downloads"
            + "/位4excel.xlsx"), WaybillExportVo.class, 1);
        List<WaybillExportVo> rows3 =  new XlsxReader().fromInputStream(new FileInputStream("/Users/weilibin/Downloads"
            + "/位3excel.xlsx"), WaybillExportVo.class, 1);

        List<WaybillExportVo> rows5 =  new XlsxReader().fromInputStream(new FileInputStream("/Users/weilibin/Downloads"
            + "/运单号汇总的2.xlsx"), WaybillExportVo.class, 1);
        List<WaybillExportVo> rows6 =  new XlsxReader().fromInputStream(new FileInputStream("/Users/weilibin/Downloads"
            + "/位5excel.xlsx"), WaybillExportVo.class, 1);

        rows4.addAll(rows3);

        Map<String, WaybillExportVo> map2018 = new HashMap<>();
        for (WaybillExportVo vo : rows4) {
            map2018.put(vo.getWaybillNo(), vo);
        }

        Map<String, WaybillExportVo> map2019 = new HashMap<>();
        for (WaybillExportVo vo : rows6) {
            map2019.put(vo.getWaybillNo(), vo);
        }


        for (WaybillExportVo vo : rows) {
            String key = vo.getWaybillNo() + "`";
            WaybillExportVo vo1 = map2018.get(key);
            if (null != vo1) {
                vo1.setWaybillNo(vo.getWaybillNo());
                result2018.add(vo1);
            }
        }

        for (WaybillExportVo vo : rows5) {
            String key = vo.getWaybillNo() + "`";
            WaybillExportVo vo1 = map2019.get(key);
            if (null != vo1) {
                vo1.setWaybillNo(vo.getWaybillNo());
                result2019.add(vo1);
            }
        }








        FileOutputStream out = new FileOutputStream("/Users/weilibin/运单号汇总.xlsx");
        (new XssfWriter()).appendToSheet("2018", result2018).appendToSheet("2019", result2019).writeToOutputStream(out);

    }
}
