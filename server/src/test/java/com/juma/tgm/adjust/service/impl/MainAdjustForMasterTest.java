package com.juma.tgm.adjust.service.impl;

import com.bruce.tool.rpc.http.core.Https;
import com.giants.common.exception.BusinessException;
import com.juma.tgm.fms.domain.v3.AdjustForWaybillTemp;
import com.juma.tgm.fms.domain.v3.vo.WaybillVendorExportVO;
import me.about.poi.reader.XlsxReader;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.List;

/**
 * 功能 : 
 *
 * @author : Bruce(刘正航) 16:14 2019-05-23
 */
public class MainAdjustForMasterTest {
    public static void main(String[] args) {
        testImport();
    }

    public void testDecimal(){
        AdjustForWaybillTemp temp = new AdjustForWaybillTemp();
        BigDecimal adjustAmount = BigDecimal.ZERO
                .add(validAndSetBigDecimalValue(temp.getAdjustForFreight()))
                .add(validAndSetBigDecimalValue(temp.getAdjustForCarry()))
                .add(validAndSetBigDecimalValue(temp.getAdjustForWorkload()))
                .add(validAndSetBigDecimalValue(temp.getAdjustForUpstairs()))
                .add(validAndSetBigDecimalValue(temp.getAdjustForFine()))
                .add(validAndSetBigDecimalValue(temp.getAdjustForCargoLoss()));
        System.out.println(adjustAmount);
    }

    private static BigDecimal validAndSetBigDecimalValue(BigDecimal amount) {
        return null != amount ? amount : BigDecimal.ZERO;
    }

    public static void testImport(){
        // 多个sheet的,不会报错, 一个sheet的会报错
        List<WaybillVendorExportVO> list = null;
        byte[] dataBytes = Https.create()
                .url("https://marryself.oss-cn-hangzhou.aliyuncs.com/%E8%BF%90%E5%8D%95%E5%8E%9F%E5%A7%8B%E6%95%B0%E6%8D%AE-%E6%89%BF%E8%BF%90%E5%95%86-Full-1.xlsx?Expires=1559627537&OSSAccessKeyId=TMP.AgG9etVTCLRjoSViwexobtBX2a-kNkdenXVoLiL138avnI3NZ0svWDFJWHnGMC4CFQDEZnigTTdAFKGhtTUg16W2y3SkRQIVAN2ywBffxTK5SVP_p486oxBSbEmG&Signature=fdsWkycsYETe6Xdjlxzj5rFhqgM%3D")
                .download();
        try (ByteArrayInputStream bais = new ByteArrayInputStream(dataBytes)){
            list = XlsxReader.fromInputStream(bais, WaybillVendorExportVO.class);
        } catch (Exception e) {
            System.out.println(new String(e.getMessage().getBytes(Charset.forName("GBK"))));
            throw new BusinessException("ExcelAnalysisImportError",e.getMessage());
        }
    }
}
