package com.juma.tgm.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class G2linkUtils {
	
	private static Logger log = LoggerFactory.getLogger(G2linkUtils.class);

	public static String sign(String appSecret, Map<String,String> parameterMap) {
		if(parameterMap == null || parameterMap.isEmpty()) return null;
		List<String> sortList = new ArrayList<String>();
        for(String key : parameterMap.keySet()) {
            sortList.add(key);
        }
        Collections.sort(sortList);
		StringBuilder buffer = new StringBuilder(appSecret);
		for(String key : sortList) {
			buffer.append(key).append(parameterMap.get(key));
		}
		buffer.append(appSecret);
		return DigestUtils.md5Hex(buffer.toString()).toUpperCase();
	}
	
	public static String post(String url,String appKey,String appSecret,String method,Map<String,Object> parameterMap) {
		String ret = null;
		Map<String,String> tmp = new HashMap<String,String>();
		String data = JSON.toJSONString(parameterMap);
		tmp.put("data", data);
		String dateStr = DateUtil.format();
		tmp.put("timestamp", dateStr);
		tmp.put("method", method);
		tmp.put("app_key", appKey);
		tmp.put("sign", sign(appSecret,tmp));
		Form form = Form.form();
		for(String key:tmp.keySet()){
			form.add(key, tmp.get(key));
		}
		try {
			ret = Request.Post(url).bodyForm(form.build(),Consts.UTF_8).execute().handleResponse(new ResponseHandler<String>() {
				@Override
				public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
					HttpEntity entity = response.getEntity();
					return entity !=null?EntityUtils.toString(entity,Consts.UTF_8):null;
				}
			});
			log.info(method + " result :" + ret);
			log.info("{} return:{}.",method,ret);
		} catch (IOException e) {
			log.warn(e.getMessage(), e);
		}
		return ret;
	}
	
	
	/*
	 * f76e72   ab179020b82d2fdcd4cea176796f7156
	 */
	public static void main(String[] args) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		System.out.println(DateUtil.format(new Date(new Date().getTime() - 120*60*60*1000)));
		System.out.println(DateUtil.format(new Date()));
		parameterMap.put("startTime", DateUtil.format(new Date(new Date().getTime() - 120*60*60*1000)));
		parameterMap.put("endTime", DateUtil.format(new Date()));
		parameterMap.put("shipperCode", "juma");//juma 20161122juma
		
		/*parameterMap.put("projectCode", "jumaxm");
		parameterMap.put("orderCode", "Jumalt11240007");
		parameterMap.put("vehicle", "京231453");
		parameterMap.put("vehicleNo", "1");
		parameterMap.put("operTime", DateUtil.format(new Date()));
		parameterMap.put("drivername", "1");
		parameterMap.put("drivercode", "1");
		parameterMap.put("driverphones", "1");
		parameterMap.put("operation", "2");*/
		
		parameterMap.put("orgType", 3);
		parameterMap.put("orgNum", "20161122juma");
		parameterMap.put("pageNo", 1);
		parameterMap.put("pageSize", 20);
		//post("http://demo.api.g2link.cn:8081/router","50e499","05ACBAB5D8FCAE6C2836B83462F5448E","g2matrix.supplydistribute.queryloadingDetail",parameterMap);
		post("http://api.g2link.cn:8081/router","2548dd","6225C183B73BA6979FADC8AFE4843E1D","g2matrix.supplydistribute.queryOrders",parameterMap);
		//post("http://demo.api.g2link.cn:8081/router","50e499","05ACBAB5D8FCAE6C2836B83462F5448E","g2matrix.supplydistribute.queryCancelOrders",parameterMap);
		//post("http://demo.api.g2link.cn:8081/router","50e499","05ACBAB5D8FCAE6C2836B83462F5448E","g2matrix.supplydistribute.queryOrderStatus",parameterMap);
		//post("http://demo.api.g2link.cn:8081/router","50e499","05ACBAB5D8FCAE6C2836B83462F5448E","g2matrix.supplydistribute.scheduleOrders1",parameterMap);
	}
	
}
