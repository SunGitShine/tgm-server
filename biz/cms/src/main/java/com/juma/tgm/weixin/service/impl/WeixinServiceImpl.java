package com.juma.tgm.weixin.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.juma.tgm.common.Constants;
import com.juma.tgm.weixin.service.WeixinService;

@Service
public class WeixinServiceImpl implements WeixinService {

    private static final Logger log = LoggerFactory.getLogger(WeixinServiceImpl.class);

    @Value("${wechat.appid}")
    private String appId;
    @Value("${wechat.appserect}")
    private String appSerect;

    @Override
    public String getCustomerOpenId(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        String url = Constants.WEIXIN_GETOPENID_URL + "?appid=" + appId + "&secret=" + appSerect + "&code=" + code
                + "&grant_type=authorization_code";
        try {
            String ret = Request.Get(url).socketTimeout(7000).connectTimeout(5000).execute().returnContent().asString();
            if (StringUtils.isNotBlank(ret)) {
                JSONObject object = JSONObject.parseObject(ret);
                return (String) object.get("openid");
            }
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        return null;
    }

}
