package com.juma.tgm.tools.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.juma.conf.domain.ConfParamOption;
import com.juma.conf.service.ConfParamService;
import com.juma.tgm.basicTruckType.service.ConfParamInfoService;
import com.juma.tgm.common.Constants;
import com.juma.tgm.waybill.domain.ConfParamInfo;

@Service
public class ConfParamInfoServiceImpl implements ConfParamInfoService {

    private static final Logger log = LoggerFactory.getLogger(ConfParamInfoServiceImpl.class);

    @Resource
    private ConfParamService confParamService;

    @Override
    public ConfParamInfo goodsTypeList() {
        ConfParamInfo info = new ConfParamInfo();
        try {
            info.setOptionList(confParamService.findParamOptions(Constants.TGM_GOODS_TYPE));
        } catch (Exception e) {
            log.warn("获取货物类型异常", e);
        }
        return info;
    }

    @Override
    public Map<String, ConfParamOption> findConfParamInfoByParamKey(String paramKey) {
        Map<String, ConfParamOption> result = new HashMap<String, ConfParamOption>();
        if (StringUtils.isNotBlank(paramKey)) {
            return result;
        }

        List<ConfParamOption> options = confParamService.findParamOptions(paramKey);
        for (ConfParamOption option : options) {
            result.put(option.getOptionValue(), option);
        }
        return result;
    }
}
