package com.juma.tgm.tools.service.impl;

import com.bruce.tool.common.util.LogUtils;
import com.bruce.tool.rpc.http.core.Https;
import com.bruce.tool.rpc.http.handler.Arithmetic;
import com.bruce.tool.rpc.http.handler.RetryHandler;
import com.bruce.tool.rpc.http.handler.Timeout;
import com.bruce.tool.rpc.http.handler.response.RetryResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.junit.Test;
import org.springframework.util.Base64Utils;

import java.io.*;

/**
 * 功能 :
 *
 * @author : Bruce(刘正航) 下午4:43 2018/8/10
 */
@Slf4j
public class HttpTest {

    @Test
    public void download() throws IOException {
        String imgsrc = Https.create()
                .timeout(10,10,10)
                .url("http://c.hiphotos.baidu.com/zhidao/pic/item/a50f4bfbfbedab6471a3f9ccf636afc378311eec.jpg")
                .add("","")
                .addBody("","")
                .get();

        System.out.println(imgsrc);

        LogUtils.info(log, Arithmetic.HMAC_SHA_256.name().matches("^[A-Z][A-Z0-9]*(_[A-Z0-9]+)*$"));
        byte[] result = Https.create()
                .url("http://c.hiphotos.baidu.com/zhidao/pic/item/a50f4bfbfbedab6471a3f9ccf636afc378311eec.jpg")
                .download();

        String base64Str = Base64Utils.encodeToString(result);
        byte[] origin = Base64Utils.decodeFromString(base64Str);
        String base64Str2 = Base64Utils.encodeToString(origin);
        File file = new File(System.getProperty("user.home")+"/Desktop/test.jpg");
        if( (!file.exists() && file.createNewFile()) || file.exists() ){
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(origin);
            fos.close();
        }
        System.out.println(base64Str);
        System.out.println(base64Str2);
    }

    @Test
    public void upload2() throws IOException {
        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("name","测试文件上传");

        File file = new File(System.getProperty("user.home")+"/Desktop/test.xls");
        if(file.exists()){
            String TYPE = "application/octet-stream";
            RequestBody fileBody = RequestBody.create(MediaType.parse(TYPE),file);

            RequestBody requestBody = builder
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("file",file.getName(),fileBody)
                    .build();

            Request request = new Request.Builder()
                    .url("http://127.0.0.1:8086/tool/upload")
                    .post(requestBody)
                    .build();
            client.newCall(request).execute();
        }
    }

    /**测试文件上传**/
    @Test
    public void upload() throws IOException {
        FileInputStream in = new FileInputStream(new File(System.getProperty("user.home")+"/Desktop/test.xls"));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int length = -1;
        byte[] buf = new byte[1024];
        while( (length = in.read(buf)) > -1 ){
            bos.write(buf,0,length);
        }
        String result = Https.create().print(true)
                .url("http://127.0.0.1:8086/tool/upload")
                .timeout(Timeout.builder()
                        .connectTimeout(60)
                        .writeTimeout(60)
                        .readTimeout(60).build())
                .add("name","测试文件上传")
                // 方式一: 文件
                .addBody("file",new File(System.getProperty("user.home")+"/Desktop/test.xls"))
                // 方式二: 字节数组
                .addBody("upload",bos.toByteArray())
                .upload();
        System.out.println(result);
    }

    /**测试重试机制**/
    @Test
    public void retry() {
        Https.create().url("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdms/")
                .retry(6, new Integer[]{500, 1000}, new RetryHandler() {
                    @Override
                    public RetryResponse needRetry(byte[] response) {
                        String content = new String(response);
                        return RetryResponse.builder()
                                .need(content.contains("by zero"))
                                .reason("测试原因")
                                .build();
                    }
                }).post();

        Https.create().url("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdms/")
                .retry(new RetryHandler() {
                    @Override
                    public RetryResponse needRetry(byte[] response) {
                        String content = new String(response);
                        return RetryResponse.builder()
                                .need(content.contains("by zero"))
                                .reason("测试原因")
                                .build();
                    }
                }).post();


        Https.create().url("")
                .addBody("","")
                .postJson();
    }

}
