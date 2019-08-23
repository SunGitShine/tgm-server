package com.juma.tgm.waybill.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.giants.common.SpringContextHelper;
import com.giants.common.collections.CollectionUtils;
import com.giants.common.exception.BusinessException;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.Constants.Coordsys;
import com.juma.tgm.gaode.domain.*;
import com.juma.tgm.gaode.domain.GaodeKeywordsResponse.Poi;
import com.juma.tgm.waybill.domain.CityAdressData;
import com.juma.tgm.waybill.domain.DistanceAndPriceData;
import com.juma.tgm.waybill.service.GaoDeMapService;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 重要提示：此类有许多接口已经被第三方调用，不能更改，若先接口不满足要求，可以新增v2、v3...
 */

public class GaoDeMapServiceImpl implements GaoDeMapService {

    private static Logger log = LoggerFactory.getLogger(GaoDeMapServiceImpl.class);
    private static final String ONLY = "ONLY";

    private String[] ak;

    public GaoDeMapServiceImpl(String akStr) {
        this.setAk(akStr.split(","));
    }

    @Override
    public String getLatLng(String address, String city) {
        String gaodeResponse = gaodeTipsResponse(city, address, ONLY);
        GaodeKeywordsResponse response = JSON.parseObject(gaodeResponse, GaodeKeywordsResponse.class);
        List<Poi> geocodes = response.getPois();
        if (geocodes != null && !geocodes.isEmpty()) {
            return geocodes.get(0).getLocation();
        }
        log.info("GaoDeMap getLatLng 未返回坐标数值...");
        return null;
    }

    @Override
    public JSONObject getRouteInfoByLatLng(String startCoordinates, String endCoordinates) {
        String url = Constants.GAODEDRIVING + "&origin=" + startCoordinates + "&destination=" + endCoordinates + "&key=" + getRandomAk();
        return requestGet(url);
    }

    @Override
    public DistanceAndPriceData getRouteDistanceByLatLng(JSONObject routeInfo) {
        DistanceAndPriceData data = new DistanceAndPriceData();
        if(routeInfo == null || routeInfo.get("status") == null){
            log.info("高德通过经纬度获取距离接口返回异常，高德返回数据：{}", routeInfo == null ? null : routeInfo.toJSONString());
            return data;
        }
        if (!routeInfo.get("status").toString().equals("1")) {
            log.info("GaoDeMap routeinfo mistake = " + routeInfo.get("info"));
            return data;
        }
        JSONObject resultJo = routeInfo.getJSONObject("route");
        JSONArray routes = resultJo.getJSONArray("paths");
        JSONObject object = routes.getJSONObject(0);
        data.setDistance(object.getInteger("distance"));
        data.setDuration(String.valueOf(object.getInteger("duration")));
        data.setTolls(object.getBigDecimal("tolls"));
        data.setTollDistance(object.getInteger("toll_distance"));
        return data;
    }

    @Override
    public String convertCoordinate(String coordinate, String coordsys) {
        if (StringUtils.isBlank(coordinate)) {
            return null;
        }

        if (!checkIsConvertCoordinate(coordsys)) {
            return null;
        }

        try {
            // 处理经纬度，根据高德要求，需转换的原坐标经纬度小数不能超过6位
            String[] split = coordinate.split(",");
            String lng = split[0];
            String lat = split[1];
            coordinate = new BigDecimal(lng).setScale(6, BigDecimal.ROUND_DOWN) + "," + new BigDecimal(lat).setScale(6, BigDecimal.ROUND_DOWN);
            String url = Constants.GAODECONVERT + "?locations=" + coordinate.replace(" ", "+") + "&coordsys=" + coordsys.toLowerCase() + "&key=" + getRandomAk();
            String ret = Request.Get(url).socketTimeout(7000).connectTimeout(5000).execute().returnContent().asString();
            CoordinateConvert convert = JSON.parseObject(ret, CoordinateConvert.class);
            if (convert.getStatus() == 1) {
                return convert.getLocations();
            }
        } catch (Exception e) {
            log.warn("坐标转换异常", e);
        }
        return null;
    }

    // 根据坐标类型判断是否能够转换
    private boolean checkIsConvertCoordinate(String coordsys) {
        if (StringUtils.isBlank(coordsys)) {
            return false;
        }
        for (Coordsys coord : Constants.Coordsys.values()) {
            if (coordsys.equalsIgnoreCase(coord.toString())) {
                return true;
            }
        }
        log.info("坐标转换类型不存在，不能转换，coordsys：{}", coordsys);
        return false;
    }

    public AmapGeoResponse geocode(String address, String city) {
        Pattern p = Pattern.compile("\\s+");
        Matcher m = p.matcher(address.trim());
        address = m.replaceAll(" ");
        String url = Constants.GAODEGEO + "?address=" + address.replace(" ", "+") + "&key=" + getRandomAk();
        if (StringUtils.isNotBlank(city)) {
            url += "&city=" + city.trim();
        }
        String ret = "";
        try {
            ret = Request.Get(url).socketTimeout(7000).connectTimeout(5000).execute().returnContent().asString();
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }

        AmapGeoResponse geoResp = JSON.parseObject(ret, AmapGeoResponse.class);
        if (geoResp.getStatus() != 1) {
            log.info("GaoDeMap routeinfo mistake = " + ret);
        }
        return geoResp;
    }

    @Override
    public AmapRegeoResponse regeocode(String startCoordinates) {
        log.info("regeocode请求参数：{}", startCoordinates);
        if (StringUtils.isBlank(startCoordinates)) {
            return null;
        }

        Pattern p = Pattern.compile("\\s+");
        Matcher m = p.matcher(startCoordinates.trim());
        startCoordinates = m.replaceAll(" ");
        String url = Constants.GAODEREGEO + "?location=" + startCoordinates + "&key=" + getRandomAk() + "&extensions=all&batch=false&roadlevel=1&radius=0";
        String ret = "";
        try {
            ret = Request.Get(url).socketTimeout(7000).connectTimeout(5000).execute().returnContent().asString();
            log.info("regeocode请求返回结果：{}", ret);
        }
        catch (Exception e) {
            log.info("请求高德接口错误url:{}", url, e);
        }

        AmapRegeoResponse regeoResp = null;
        try {
            regeoResp = JSON.parseObject(ret, AmapRegeoResponse.class);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        if (regeoResp == null || regeoResp.getStatus() != 1) {
            log.info("GaoDeMap routeinfo mistake = " + ret);
        }
        return regeoResp;
    }

    // GET方式调用地图接口
    private JSONObject requestGet(String httpUrl) {
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            httpURLConnection.setConnectTimeout(5000);
            InputStream in = httpURLConnection.getInputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int buffer = 1024;
            byte[] b = new byte[1024];
            int n = 0;
            while ((n = in.read(b, 0, buffer)) > 0) {
                bos.write(b, 0, n);
            }
            String result = new String(bos.toByteArray(), "UTF-8");
            bos.close();
            in.close();
            httpURLConnection.disconnect();
            JSONObject jsObject = JSONObject.parseObject(result);
            return jsObject;
        } catch (Exception e) {
            log.warn("requestGet ERROR!", e);
        }
        return null;
    }

    // 构造查询多条结果的url
    private StringBuffer buildManyPlaceUrl(String city, String keywords) throws UnsupportedEncodingException {
        Pattern p = Pattern.compile("\\s+");
        Matcher m = p.matcher(keywords.trim());
        keywords = m.replaceAll(" ");
        StringBuffer sf = new StringBuffer(Constants.GAODEKEYWORDS + "?");
        sf.append("citylimit=true&city=").append(city);
        sf.append("&keywords=").append(URLEncoder.encode(keywords.replace(" ", "+"), "utf-8"));
        sf.append("&key=").append(getRandomAk()).append("&extensions=base");
        return sf;
    }

    // 构造查询一条结果的url
    private StringBuffer buildOnePlaceUrl(String city, String keywords) throws UnsupportedEncodingException {
        StringBuffer sf = buildManyPlaceUrl(city, keywords);
        sf.append("&offset=1");
        return sf;
    }

    private String gaodeTipsResponse(String city, String keywords, String numMode) {
        try {
            String url = "";
            if (null != numMode && ONLY.equals(numMode)) {
                url = buildOnePlaceUrl(city, keywords).toString();
            } else {
                url = buildManyPlaceUrl(city, keywords).toString();
            }
            String ret = Request.Get(url).socketTimeout(7000).connectTimeout(5000).execute().returnContent().asString();
            log.info("gaode ret is:{}.", ret);
            return ret;
        } catch (ClientProtocolException e) {
            throw new BusinessException("gaodeErrorTips", "gaode.error.tips");
        } catch (Exception e) {
            throw new BusinessException("gaodeErrorTips", "gaode.error.tips");
        }
    }

    @Override
    public GaodeKeywordsResponse gaodeTips(String city, String keywords) throws BusinessException {
        String tipsStr = gaodeTipsResponse(city, keywords, null);
        GaodeKeywordsResponse response = JSON.parseObject(tipsStr, GaodeKeywordsResponse.class);
        if (!response.getStatus().equals("1")) {
            throw new BusinessException("gaodeErrorTips", "gaode.error.tips");
        }
        return response;
    }

    @Override
    public GaodeInputtipsResponse gaodeInputtips(String location, String keywords) throws BusinessException {
        if (StringUtils.isBlank(location) || StringUtils.isBlank(keywords)) {
            throw new BusinessException("validationFailure", "errors.validation.failure");
        }
        String url = Constants.GAODINPUTTIPS + "?key=" + getRandomAk() + "&keywords=" + keywords + "&location=" + location + "&datatype=all";
        try {
            String ret = Request.Get(url).socketTimeout(7000).connectTimeout(5000).execute().returnContent().asString();
            GaodeInputtipsResponse response = JSON.parseObject(ret, GaodeInputtipsResponse.class);
            log.info("gaode inputtips ret is:{}.", ret);
            return response;
        } catch (ClientProtocolException e) {
            throw new BusinessException("gaodeErrorTips", "gaode.error.tips");
        } catch (Exception e) {
            throw new BusinessException("gaodeErrorTips", "gaode.error.tips");
        }
    }

    @Override
    public GaodeDistrictResponse gaodeDistrict(String keywords, String level, int subdistrict) throws BusinessException {
        String url = Constants.GAODEDISTRICT + "?subdistrict=" + subdistrict + "&level=" + level + "&keywords=" + keywords + "&key=" + getRandomAk();
        try {
            String ret = Request.Get(url).socketTimeout(7000).connectTimeout(5000).execute().returnContent().asString();
            GaodeDistrictResponse response = JSON.parseObject(ret, GaodeDistrictResponse.class);
            log.info("gaode ret is:{}.", ret);
            return response;
        } catch (ClientProtocolException e) {
            throw new BusinessException("gaodeErrorTips", "gaode.error.tips");
        } catch (Exception e) {
            throw new BusinessException("gaodeErrorTips", "gaode.error.tips");
        }
    }

    @Override
    public void reportLocation(DriverLocation data) {
        Form form = Form.form();
        form.add("key", getRandomAk());
        form.add("tableid", "578f2bd57bbf1978ba687a53");
        form.add("data", data.toJson());
        String str = null;
        try {
            str = Request.Post(Constants.AMAP_REPORT_LOCATION).socketTimeout(7000).connectTimeout(5000).bodyForm(form.build(), Consts.UTF_8).execute().returnContent().asString();
            log.info("report location ret is : {}.", str);
        } catch (Exception e) {
            throw new BusinessException("gaodeErrorTips", "gaode.error.tips");
        }
    }

    @Override
    public List<DriverLocation> searchLocation(String city, String location) {
        String url = Constants.AMAP_AROUND_LOCATION + "?key=" + getRandomAk() + "&tableid=578f2bd57bbf1978ba687a53&filter=waybill_id:0&city=" + city + "&center=" + location
                + "&radius=50000";
        try {
            String ret = Request.Get(url).socketTimeout(7000).connectTimeout(5000).execute().returnContent().asString();
            AmapAroundResponse response = JSON.parseObject(ret, AmapAroundResponse.class);
            log.info("gaode ret is:{}.", ret);
            return response.getDatas();
        } catch (ClientProtocolException e) {
            throw new BusinessException("gaodeErrorTips", "gaode.error.tips");
        } catch (Exception e) {
            throw new BusinessException("gaodeErrorTips", "gaode.error.tips");
        }
    }

    @Override
    public DistanceAndPriceData getDistance(String startCoordinates, String endCoordinates) {
        log.info("getDistance cal:{},{}", startCoordinates, endCoordinates);
        if (StringUtils.isNotBlank(startCoordinates) && StringUtils.isNotBlank(endCoordinates)) {
            DistanceAndPriceData data = getRouteDistanceByLatLng(getRouteInfoByLatLng(startCoordinates, endCoordinates));
            buildData(data, endCoordinates);
            return data;
        } else if (StringUtils.isNotBlank(endCoordinates)) {
            DistanceAndPriceData data = new DistanceAndPriceData();
            buildData(data, endCoordinates);
            return data;
        }
        return null;
    }

    // 构造操作位置
    private void buildData(DistanceAndPriceData data, String endCoordinates) {
        AmapRegeoResponse regeocode = regeocode(endCoordinates);
        data.setCalDetail(regeocode.getRegeocode().getFormattedAddress());
        data.setProvince(regeocode.getRegeocode().getAddressComponent().getProvince());
    }

    // 构造操作位置坐标
    private String buildCoordinate(String address, String city) {
        if (StringUtils.isNotBlank(address)) {
            AmapGeoResponse geocode = geocode(address, city);
            if (null != geocode) {
                List<Geocode> list = geocode.getGeocodes();
                if (CollectionUtils.isNotEmpty(list)) {
                    return list.get(0).getLocation();
                }
            }
        }
        return null;
    }

    @Override
    public DistanceAndPriceData getDistanceInfo(CityAdressData formAddress, List<CityAdressData> toAddresss) {

        DistanceAndPriceData result = new DistanceAndPriceData();
        // 得到坐标位置
        Map<Integer, String> map = new HashMap<Integer, String>();
        List<DistanceAndPriceData> list = new ArrayList<DistanceAndPriceData>();

        // 取货地
        map.put(0, formAddress.getCoordinate());
        result.setStartCoordinates(formAddress.getCoordinate());

        // 目的地
        StringBuilder endCoordinates = new StringBuilder();
        int length = toAddresss.size();
        for (int i = 0; i < length; i++) {
            CityAdressData data = toAddresss.get(i);
            String endCoordinate = data.getCoordinate();
            if (StringUtils.isBlank(endCoordinate)) {
                endCoordinate = buildCoordinate(data.getAddress(), data.getCity());
            }
            map.put((i + 1), endCoordinate);
            endCoordinates.append(endCoordinate).append("|");
        }
        // 计算距离与预估时间
        try {
            Integer distances = 0, duration = 0, tollDistance = 0;
            BigDecimal tolls = BigDecimal.ZERO;
            if (length > 2) {
                CountDownLatch latch = new CountDownLatch(length);
                for (int i = 0; i < length; i++) {
                    getList(list, latch, map.get(i), map.get(i + 1));
                }
                latch.await();
                if (CollectionUtils.isNotEmpty(list)) {
                    for (DistanceAndPriceData data : list) {
                        distances += Integer.valueOf(data.getDistance() == null ? 0 : data.getDistance());
                        duration += Integer.parseInt(data.getDuration() == null ? 0 + "": data.getDuration());
                        tollDistance += data.getTollDistance();
                        tolls = tolls.add(data.getTolls());
                    }
                }
                log.info("多线程获取数据得到list:" + list.toString());
            } else {
                log.info("目的地数量少，没有使用多线程...");
                for (int i = 0; i < length; i++) {
                    DistanceAndPriceData data = getRouteDistanceByLatLng(getRouteInfoByLatLng(map.get(i), map.get(i + 1)));
                    if (null != data) {
                        distances += Integer.valueOf(data.getDistance() == null ? 0 : data.getDistance());
                        duration += Integer.parseInt(data.getDuration() == null ? 0 + "": data.getDuration());
                        tollDistance += data.getTollDistance();
                        tolls = tolls.add(data.getTolls());
                    }
                }
            }
            result.setEndCoordinates(endCoordinates.substring(0, endCoordinates.length() - 1));
            result.setDistance(new BigDecimal(distances).divide(new BigDecimal(1000), BigDecimal.ROUND_UP).intValue());
            result.setTollDistance(new BigDecimal(tollDistance).divide(new BigDecimal(1000), BigDecimal.ROUND_UP).intValue());
            result.setTolls(tolls);
            result.setDuration((duration / 60) + "");
        } catch (InterruptedException e) {
            log.warn(e.getMessage(), e);
        }
        return result;
    }

    // 多线程获取数据
    private void getList(final List<DistanceAndPriceData> list, final CountDownLatch latch, final String startCoordinates, final String endCoordinates) {
        SpringContextHelper.getSpringBean(TaskExecutor.class).execute(new Runnable() {
            @Override
            public void run() {
                try {
                    DistanceAndPriceData data = getRouteDistanceByLatLng(getRouteInfoByLatLng(startCoordinates, endCoordinates));
                    list.add(data);
                } catch (Exception e) {
                    log.warn("多线程获取数据异常srcJo:" + startCoordinates + ",toJo:" + endCoordinates, e);
                } finally {
                    latch.countDown();
                }
            }
        });
    }

    @Override
    public DistanceAndPriceData getDistanceSimple(String startCoordinates, String endCoordinates) {
        if (StringUtils.isNotBlank(startCoordinates) && StringUtils.isNotBlank(endCoordinates)) {
            String url = Constants.GAODEDISTANCE + "?origins=" + startCoordinates + "&type=0&destination=" + endCoordinates + "&key=" + getRandomAk();
            JSONObject routeInfo = requestGet(url);
            if (!routeInfo.get("status").toString().equals("1")) {
                log.info("GaoDeMap routeinfo mistake = " + routeInfo.get("info"));
                throw new RuntimeException("GaoDeMap routeinfo mistake = " + routeInfo.get("info"));
            }
            JSONArray routes = routeInfo.getJSONArray("results");
            DistanceAndPriceData data = new DistanceAndPriceData();
            data.setDistance(routes.getJSONObject(0).getInteger("distance"));
            data.setDuration(String.valueOf(routes.getJSONObject(0).getInteger("duration")));
            return data;
        }
        return null;
    }

    private String getRandomAk() {
        int len = getAk().length;
        int i = getRandomNumberInRange(0, len - 1);
        return getAk()[i];
    }

    public int getRandomNumberInRange(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public String[] getAk() {
        return ak;
    }

    public void setAk(String[] ak) {
        this.ak = ak;
    }

    // 测试代码
    public static void main(String[] args) {
        /*
         * GaodeTipResponse response = new GaoDeMapServiceImpl().gaodeTips("成都", "天府"); System.out.println(response);
         */

        /*
         * GaodeDistrictResponse response = new GaoDeMapServiceImpl().gaodeDistrict(null,null,1);//"四川省", "province" System.out.println(response);
         */

        // DriverLocation data = new DriverLocation();
        // data.setName("天府宝座2");
        // data.setUserId(1);
        // data.setLocation("104.090777,30.589384");
        // System.out.println(new
        // GaoDeMapServiceImpl("").getRandomNumberInRange(0, 1));
        GaoDeMapService gaoDeMapService = new GaoDeMapServiceImpl("af942ed9bc76ca6ac360da2a6946eb89,c077f6f2dda23730e65e8cacf1bd2af4,8693897e6868d91005a0045ef2663647");
        // gaoDeMapService.regeocode("104.089682,30.588892");
        gaoDeMapService.lineDistance("104.090474446615,30.58853515625", "104.065837,30.657349");
        // gaoDeMapService.convertCoordinate("104.14122927220902,30.599613098977894",Constants.Coordsys.GPS);
        // System.out.println(gaoDeMapService.gaodeInputtips("116.481488,39.990464",
        // "南京路"));// "南京路"
        /*
         * long beginA = System.currentTimeMillis(); for (int i = 1; i <= 100; i++) { long begin = System.currentTimeMillis();
         * gaoDeMapService.gaodeInputtips("116.481488,39.990464", "南京路"); long end = System.currentTimeMillis(); System.out.println("第" + i + "耗时：" + (end - begin) + "MS"); } long
         * endA = System.currentTimeMillis(); System.out.println("总耗时：" + (endA - beginA) + "MS");
         */
        // begin = System.currentTimeMillis();
//         DistanceAndPriceData data =
//         gaoDeMapService.getRouteDistanceByLatLng(gaoDeMapService.getRouteInfoByLatLng("103.987514,30.601487",
//         "104.04283,30.525011"));
//         System.out.println(JSON.toJSON(data));
        // end = System.currentTimeMillis();
        // System.out.println((end - begin) + "MS");
        // System.out.println(new
        // GaoDeMapServiceImpl("af942ed9bc76ca6ac360da2a6946eb89,c077f6f2dda23730e65e8cacf1bd2af4,8693897e6868d91005a0045ef2663647").searchLocation("成都","104.090828,30.589472").size());
    }

    @Override
    public GaodeDistanceResponse lineDistance(String origins, String destination) {
        String url = Constants.GAODEDISTANCE + "?origins=" + origins + "&type=1&destination=" + destination + "&key=" + getRandomAk();
        try {
            String ret = Request.Get(url).socketTimeout(7000).connectTimeout(5000).execute().returnContent().asString();
            GaodeDistanceResponse response = JSON.parseObject(ret, GaodeDistanceResponse.class);
            log.info("gaode ret is:{}.", ret);
            return response;
        } catch (ClientProtocolException e) {
            throw new BusinessException("gaodeErrorTips", "gaode.error.tips");
        } catch (Exception e) {
            throw new BusinessException("gaodeErrorTips", "gaode.error.tips");
        }
    }

    @Override
    public IpAddressComponent findRegionCodeByIpAddress(String ipAddress) {
        if (StringUtils.isBlank(ipAddress)) {
            return null;
        }

        String url = Constants.GAODEIPADDRESS + "?ip=" + ipAddress + "&key=" + getRandomAk();
        JSONObject jsonObject = requestGet(url);

        return JSON.parseObject(JSON.toJSONString(jsonObject), IpAddressComponent.class);
    }
}
