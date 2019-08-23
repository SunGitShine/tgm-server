package com.juma.tgm.tools.service.impl;

import com.alibaba.fastjson.JSON;
import com.bruce.tool.rpc.http.core.Https;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.juma.tgm.base.domain.OpsResponse;
import com.juma.tgm.common.Constants;
import com.juma.tgm.tools.service.BigDataCommonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Title: BigDataServiceImpl
 * Description:大数据中心公共接口实现
 * Created by gzq on 2019/7/23.
 */
@Service
@Slf4j
public class BigDataCommonServiceImpl implements BigDataCommonService {

    @Override
    public <T> List<T> getOpsData(String opsId, String param, Class<T> clazz) {
        String response = null;
        try {
            String baseUrl = Constants.OPS_URL.replace("{0}", opsId);
            String url = baseUrl + URLEncoder.encode(param, "UTF-8");
            log.info("ops request url : {}",url);
            response = Https.create().url(url).get();
            Gson gson = new Gson();
            OpsResponse opsResponse = gson.fromJson(response, OpsResponse.class);
            return parseResponse(opsResponse, clazz);
        } catch (Exception e) {
            log.warn("ops response error: opsId {} ,response {}",opsId,JSON.toJSONString(response));
            log.warn(e.getMessage(), e);
        }
        return Lists.newArrayList();
    }

    public static <T> List<T> parseResponse(OpsResponse response, Class<T> clazz) {
        if(null == response || CollectionUtils.isEmpty(response.getDatas())) {
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>(response.getDatas().size());
        List<String> fieldList = new ArrayList<>();
        for(OpsResponse.Meta meta : response.getMetas()) {
            fieldList.add(meta.getName());
        }
        for(Object[] objects : response.getDatas()) {
            HashMap<String,Object> map = new HashMap<>();
            int i = 0;
            for(String field : fieldList) {
                map.put(field,objects[i]);
                i ++;
            }
            T t = JSON.parseObject(JSON.toJSONString(map), clazz);
            result.add(t);
        }
        return result;
    }

}
