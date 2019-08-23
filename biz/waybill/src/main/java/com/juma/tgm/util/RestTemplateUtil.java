package com.juma.tgm.util;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: RestTemplateUtil
 * @Description:
 * @author: liang
 * @date: 2017-08-23 14:54
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
@Component
public class RestTemplateUtil {


    /**
     * 获取同步httpclient封装对象
     * @return
     */
    public RestTemplate getRestTemplate(){
        HttpClient httpClient = HttpClientBuilder.create().build();
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.getMessageConverters().add(new FastJsonHttpMessageConverter());

        return restTemplate;
    }



}
