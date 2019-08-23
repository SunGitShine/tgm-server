package com.juma.tgm.tools.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.giants.common.exception.BusinessException;
import com.google.gson.Gson;
import com.juma.tgm.common.HttpClientUtils;
import com.juma.tgm.common.dvsSystem.DvsDataVO;
import com.juma.tgm.tool.domain.DvsParam;
import com.juma.tgm.tools.service.DvsCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DvsCommonServiceImpl implements DvsCommonService {

    private static final Logger logger = LoggerFactory.getLogger(DvsCommonServiceImpl.class);

    @Value("${dvs.juma.base.url}")
    private String dvsBaseUrl;



    /**
     * 此方式实现从dvs系统获取数据，不实现和具体业务相关的转换等其他工作
     *
     * @param sqlId
     * @param param
     * @return
     * @throws BusinessException
     */
    @Override
    public DvsDataVO getDataFromDvsSystem(Integer sqlId, DvsParam param) throws BusinessException {
        String response = null;
        try {

            String baseUrl = dvsBaseUrl.replace("{0}", sqlId + "");
            String url = baseUrl + URLEncoder.encode(JSON.toJSONString(Collections.singletonList(param)), "UTF-8");
            logger.info("请求opsUrl:" + url + " dvs system sqlId :" + sqlId + " param " + JSON.toJSONString(Collections.singletonList(param)));
            response = HttpClientUtils.get(url);
            logger.info("ops返回：" + response);
            // String result = StringEscapeUtils.unescapeJava(result);
            Gson gson = new Gson();
            DvsDataVO dvsDataVO = gson.fromJson(response, DvsDataVO.class);
            return dvsDataVO;
        } catch (Exception e) {
            logger.error("error", "ops返回的数据:=" + JSON.toJSONString(response));
            logger.error(e.getMessage(), e.getCause());
        }
        return new DvsDataVO();
    }

    @Override
    public List<JSONObject> convertToJSONObject(DvsDataVO listData) {
        List<JSONObject> result = new ArrayList<JSONObject>(listData.getDatas().size());
        JSONObject json ;
       for(Object[] tempDataArray : listData.getDatas()){
           json = new JSONObject();
            for (int index = 0 ;index<listData.getMetas().size();index++){
                json.put(listData.getMetas().get(index).getName(),tempDataArray[index]);
            }
            result.add(json);
       }
        return result;
    }

    @Override
    public List convertToList(DvsDataVO listData, Class clazz) {
        List<JSONObject> jsonObjectList = convertToJSONObject(listData);
        List result = JSONObject.parseArray(jsonObjectList.toString(),clazz);
        return result;
    }
}
