package com.juma.tgm.tools.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.juma.auth.user.domain.LoginUser;
import com.juma.common.storage.service.DistributedFileStorageService;
import com.juma.tgm.cms.service.ExportTaskService;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.tools.service.UploadTmsService;

import me.about.poi.ExcelDataFormatter;
import me.about.poi.writer.XlsxWriter;

@Service
public class UploadTmsServiceImpl implements UploadTmsService {

    private static final Logger log = LoggerFactory.getLogger(UploadTmsServiceImpl.class);

    @Resource
    private DistributedFileStorageService distributedFileStorageService;

    @Resource
    private ExportTaskService exportTaskService;

    @Override
    public String uploadExcel(String fileName, Object obj, Class<?> T, Integer taskId, LoginUser loginUser) {
        if (StringUtils.isBlank(fileName)) {
            fileName = "fileName";
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            if (!(obj instanceof List)) {
                throw new Exception("数据类型不是LIST集合...");
            }

            List<T> list = (List<T>) obj;
            if (list.isEmpty()) {
                throw new Exception("没有导出数据...");
            }

            fileName = fileName + DateUtil.format(DateUtil.YYYYMMDDHHMMSS_SIMPLE) + ".xlsx";
            Workbook wb = XlsxWriter.writeToWorkBook(list, new ExcelDataFormatter());

            wb.write(os);
            byte[] byteArray = os.toByteArray();
            return distributedFileStorageService.putInputBytes("upload/file", fileName, byteArray, "xlsx", false);
        } catch (Exception e) {
            exportTaskService.failed(taskId, "没有上传或上传失败:" + e.getMessage(), loginUser);
        } finally {
            try {
                os.flush();
                os.close();
            } catch (IOException e) {
                log.warn(e.getMessage(), e);
            }
        }
        return null;
    }
}
