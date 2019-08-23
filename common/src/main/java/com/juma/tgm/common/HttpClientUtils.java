package com.juma.tgm.common;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpClientUtils {

    protected static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);
    public static final int CONNECT_TIMEOUT = 60 * 1000;
    public static final int READ_TIMEOUT = 30 * 1000;


    public static final String CONTENT_TYPE_FORM_URLENCODED = "application/x-www-form-urlencoded;charset=utf-8";


    public static final String CONTENT_TYPE_FORM_DATA = "multipart/form-data;charset=utf-8";


    public static final String CONTENT_TYPE_PLAIN = "text/plain;charset=utf-8";

    public static final String CONTENT_TYPE_JSON = "application/json;charset=utf-8";

    /**
     * @Name: post
     * @Description: 发送post请求，并返回响应数据。
     * @Parameters: URL，要访问的url。
     * @Parameters: parms，访问参数,Map形式。
     * @Return: String，响应数据。
     * @Author: PeiFeng
     * @Version: V1.00
     * @Create Date: 2017-8-8
     */
    public static String post(String URL, Map<String, String> params) throws Exception {
        StringBuilder parm = new StringBuilder();
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                parm.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        return post(URL, parm.toString());
    }

    /**
     * @Name: post。
     * @Description: 发送post请求，并返回响应数据。
     * @Parameters: url，要访问的url。
     * @Parameters: parm，访问参数。
     * @Return: String，响应数据。
     * @Author: PeiFeng
     * @Version: V1.00
     * @Create Date: 2017-8-8
     */
    public static String post(String url, String parm) throws Exception {

        HttpURLConnection conn = null;
        // 数据输出流，输出参数信息
        DataOutputStream dataOut = null;
        // 数据输入流，处理服务器响应数据
        BufferedReader dataIn = null;
        String rs = "";

        try {
            URL urlValue = new URL(url);

            // 将url以open方法返回的urlConnection连接强转为HttpURLConnection连接(标识一个url所引用的远程对象连接)
            // 此时cnnection只是为一个连接对象,待连接中
            conn = (HttpURLConnection) urlValue.openConnection();

            // 设置连接输出流为true,默认false (post请求是以流的方式隐式的传递参数)
            conn.setDoOutput(true);
            // 设置连接输入流为true
            conn.setDoInput(true);
            // 设置请求方式为post
            conn.setRequestMethod("POST");
            // post请求缓存设为false
            conn.setUseCaches(false);
            // 设置该HttpURLConnection实例是否自动执行重定向
            conn.setInstanceFollowRedirects(true);
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(READ_TIMEOUT);

            // 设置内容的类型,设置为经过urlEncoded编码过的from参数
            conn.setRequestProperty("Content-Type", HttpClientUtils.CONTENT_TYPE_FORM_URLENCODED);
            conn.setRequestProperty("accept", "*/*");

            conn.connect();

            // 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
            dataOut = new DataOutputStream(conn.getOutputStream());
            // 将参数输出到连接
            dataOut.writeBytes(parm != null ? parm : "");
            // 输出完成后刷新并关闭流
            dataOut.flush();

            // 连接发起请求,处理服务器响应 (从连接获取到输入流并包装为bufferedReader)
            dataIn = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            // 用来存储响应数据
            StringBuilder sb = new StringBuilder();
            // 循环读取流
            while ((line = dataIn.readLine()) != null) {
                sb.append(line).append(System.getProperty("line.separator"));
            }
            rs = sb.toString();

        } catch (Exception e) {
            LOGGER.error("post url:" + url + " error..", e.getMessage());
            LOGGER.debug("StackTrace info :", e);
            throw new Exception(e);
        } finally {
            try {
                // 重要且易忽略步骤 (关闭流,切记!)
                if (dataOut != null) {
                    dataOut.close();
                }
                if (dataIn != null) {
                    dataIn.close();
                }
                // 销毁连接
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rs;
    }

    /**
     * @Name: get。
     * @Description: 发送get请求，并返回响应数据。
     * @Parameters: URL，要访问的url。
     * @Return: String，响应数据。
     * @Version: V1.00
     */
    public static String get(String url) throws Exception {

        HttpURLConnection conn = null;
        BufferedReader dataIn = null;

        String rs = "";
        try {
            // 把字符串转换为URL请求地址
            URL urlValue = new URL(url);
            // 打开连接
            conn = (HttpURLConnection) urlValue.openConnection();
            // 连接会话
            conn.connect();
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(READ_TIMEOUT);
            // 获取输入流
            dataIn = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = dataIn.readLine()) != null) {// 循环读取流
                sb.append(line);
            }
            rs = sb.toString();

        } catch (Exception e) {
            LOGGER.error("get url:" + url + " error..", e);
            throw new Exception(e);
        } finally {
            try {
                // 重要且易忽略步骤 (关闭流,切记!)
                if (dataIn != null) {
                    dataIn.close();
                }
                // 销毁连接
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rs;
    }


    public static String retryExec(String url, Map<String, String> params, int retryTimes, String requestType) {
        int times = 0;
        String rs = "";
        while (times < retryTimes) {
            try {
                if (StringUtils.endsWithIgnoreCase("post", requestType)) {
                    rs = HttpClientUtils.post(url, params);
                } else {
                    rs = get(url);
                }

            } catch (Exception e) {
                LOGGER.error("retryExec error, retryTime is " + times, e.getMessage());
                times++;
            }
            if (StringUtils.isNotBlank(rs)) {
                break;
            }
        }
        return rs;
    }


}
