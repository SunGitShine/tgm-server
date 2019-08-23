package com.juma.tgm.common;

import com.giants.common.exception.BusinessException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 公共验证方法
 * 
 * @author weilibin
 *
 */

public class BaseUtil {

    private final static Logger log = LoggerFactory.getLogger(BaseUtil.class);

    private final static Pattern MOBILEPHONE = Pattern.compile("^1[3456789]\\d{9}$");

    private final static Pattern MOBILEPHONE_HIDDEN = Pattern.compile("^(\\d{3})\\d{4}(\\d{4})$");

    /**
     * 
     * @Description 验证是否同一个人
     * @author Libin.Wei
     * @Date 2017年1月4日 下午1:58:44
     * @param id1
     *            参数为null不做判断
     * @param id2
     *            参数为null不做判断
     * @throws BusinessException
     */
    public static void checkSelf(Integer id1, Integer id2) throws BusinessException {
        if (null == id1 || null == id2) {
            return;
        }

        if (!id1.equals(id2)) {
            throw new BusinessException("waybillCanNotSelf", "waybill.error.waybillCanNotSelf");
        }
    }

    /**
     * 当前登录人需要是运单客户经理
     * 
     * @param loginUserId
     * @param waybillCustManId
     * @throws BusinessException
     */
    public static void checkCustomerManagerWaybill(Integer loginUserId, Integer waybillCustManId)
            throws BusinessException {
        if (null == loginUserId || null == waybillCustManId) {
            return;
        }

        if (!loginUserId.equals(waybillCustManId)) {
            throw new BusinessException("waybillNeedCustomerManager", "waybill.error.waybillNeedCustomerManager");
        }
    }

    /**
     * 
     * @Description 构造字符串，添加首尾逗号
     * @author Libin.Wei
     * @Date 2017年1月4日 下午1:59:14
     * @param str
     * @return String
     */
    public static String addComma(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }

        if (!str.startsWith(",")) {
            str = "," + str;
        }

        if (!str.endsWith(",")) {
            str = str + ",";
        }

        return str;
    }

    /**
     * 
     * @Description 构造字符串，去掉首尾逗号
     * @author Libin.Wei
     * @Date 2017年1月4日 下午2:00:29
     * @param str
     * @return String
     */
    public static String delComma(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }

        if (str.startsWith(",")) {
            str = str.substring(1, str.length());
        }

        if (str.endsWith(",")) {
            str = str.substring(0, (str.length() - 1));
        }
        return str;
    }

    /**
     * 
     * @Description 获取数字字符串的数字并转为Integer类型
     * @author Libin.Wei
     * @Date 2017年1月4日 下午1:42:37
     * @param string
     * @return Integer
     */
    public static Integer getInt(String string) {
        if (StringUtils.isBlank(string)) {
            return 0;
        }

        if (string.contains(",")) {
            String[] strs = string.split(",");
            String result = null;
            for (String str : strs) {
                if (StringUtils.isNotBlank(str)) {
                    result = str;
                    return strToNum(result);
                }

            }
        }

        return strToNum(string);
    }

    /**
     * 
     * @Description String类型转为int类型
     * @author Libin.Wei
     * @Date 2017年1月4日 下午1:50:40
     * @param str
     * @return int
     */
    public static int strToNum(String str) {
        if (StringUtils.isBlank(str)) {
            return 0;
        }

        if (StringUtils.isNumeric(str) && str.length() <= 11) {
            return Integer.parseInt(str);
        }

        return 0;
    }

    /**
     * 
     * @Description 判断String的内容是不是数字类型(包含小数)
     * @author Libin.Wei
     * @Date 2017年1月4日 下午1:46:06
     * @param target
     * @return boolean
     */
    public static boolean isNumOrDecimal(String target) {
        if (StringUtils.isBlank(target)) {
            return false;
        }

        Pattern pattern = Pattern.compile("^\\d+$|^\\d+\\.\\d+$|-\\d+$");
        return pattern.matcher(target).matches();
    }

    /**
     * 
     * @Description 判断String的内容是否包含数字
     * @author Libin.Wei
     * @Date 2017年1月6日 上午9:42:32
     * @param target
     * @return boolean
     */
    public static boolean isIncludNum(String target) {
        if (StringUtils.isBlank(target)) {
            return false;
        }

        Pattern pattern = Pattern.compile(".*\\d+.*");
        return pattern.matcher(target).matches();
    }

    /**
     * 
     * @Description 计算两个参数的相差比例((param1 - param2)/ param1)
     * @author Libin.Wei
     * @Date 2017年1月4日 上午11:59:16
     * @param param1
     * @param param2
     * @param percentFlag
     *            是否返回百分数
     * @return 保留四位小数(percentFlag=true时保留两位小数)，四舍五入
     */
    public static BigDecimal calCompareResult(BigDecimal param1, BigDecimal param2, boolean percentFlag) {
        log.info("计算两个参数的相差比例calCompareResult:param1:{},param2:{}", param1, param2);
        if (null == param1 || null == param2) {
            return BigDecimal.ZERO;
        }

        if (param1.compareTo(BigDecimal.ZERO) != 1) {
            return BigDecimal.ZERO;
        }

        BigDecimal result = (param1.subtract(param2)).divide(param1, 4, RoundingMode.HALF_UP);
        log.info("计算两个参数的相差比例calCompareResult:result:{}", result);
        if (percentFlag) {
            return result.multiply(new BigDecimal(100));
        }
        return result;
    }

    /**
     * 
     * @Description 验证手机号格式
     * @author Libin.Wei
     * @Date 2017年1月16日 下午1:31:18
     * @param phone
     *            手机号码
     * @return 手机号为空的时候返回false
     */
    public static boolean checkMobilePhone(String phone) {
        if (StringUtils.isBlank(phone)) {
            return false;
        }

        Matcher m = MOBILEPHONE.matcher(phone);
        return m.matches();
    }

    /**
     * 
     * @Description 验证座机号码格式
     * @author Libin.Wei
     * @Date 2017年1月16日 下午1:31:18
     * @param phone
     *            座机号码
     * @return 座机号为空的时候返回false
     */
    public static boolean checkTelephone(String phone) {
        if (StringUtils.isBlank(phone)) {
            return false;
        }

        Pattern telephone = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");
        Pattern telephoneHasAreaCode = Pattern.compile("^[0][1-9]{2,3}[0-9]{5,10}$");
        Pattern telephoneHasAreaCodeLine = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");
        return (telephone.matcher(phone).find() || telephoneHasAreaCode.matcher(phone).find()
                || telephoneHasAreaCodeLine.matcher(phone).find());
    }

    /**
     * 
     * @Description 将逗号分隔的obj转成LIST
     * @author Libin.Wei
     * @Date 2017年2月23日 下午8:06:37
     * @param obj
     * @return List<Integer>
     */
    public static List<Integer> convertToIntList(Object obj) {
        List<Integer> result = new ArrayList<Integer>();
        if (null == obj) {
            return result;
        }

        if (obj instanceof String) {
            String[] split = obj.toString().split(",");
            for (String str : split) {
                result.add(strToNum(str));
            }
        }
        return result;
    }

    /**
     * 
     * @Description 将逗号分隔的obj转成LIST
     * @author Libin.Wei
     * @Date 2017年2月23日 下午8:06:37
     * @param obj
     * @return List<Integer>
     */
    public static List<String> convertToStrList(Object obj) {
        List<String> result = new ArrayList<String>();
        if (null == obj) {
            return result;
        }

        if (obj instanceof String) {
            String[] split = obj.toString().split(",");
            for (String str : split) {
                result.add(str);
            }
        }
        return result;
    }

    /**
     * 
     * @Description 处理特殊城市(香港、澳门等)
     * @author Libin.Wei
     * @Date 2017年3月6日 下午5:48:10
     * @param city
     * @return
     */
    public static String buildSpecialCity(String city) {
        if (StringUtils.isBlank(city)) {
            return null;
        }

        if (city.startsWith("香港")) {
            return "32000";
        } else if (city.startsWith("澳门")) {
            return "33000";
        }
        return null;
    }

    public static String interceptPhoneNumber(String mobileNumber) {
        if (StringUtils.isBlank(mobileNumber)) {
            return mobileNumber;
        }

        if (!checkMobilePhone(mobileNumber)) {
            return mobileNumber;
        }

        return mobileNumber.replaceAll(MOBILEPHONE_HIDDEN.toString(), "$1****$2");
    }

    // 获取图片url的半地址
    public static String buildImgUrl(String imageUrl) {
        if (StringUtils.isBlank(imageUrl)) {
            return imageUrl;
        }
        int index = imageUrl.indexOf(Constants.UPLOAD_IMAGE_URL_START);
        if (index == -1) {
            return imageUrl;
        }
        return imageUrl.substring(index, imageUrl.length());
    }

    /**
     * 
     * 检查字符串的长度是否小于指定的长度，去除字符串里的所有空格
     * 
     * @author Libin.Wei
     * @Date 2018年8月8日 下午2:17:20
     * @param str
     * @param designatedLen
     *            指定长度
     * @return boolean true:小于；false:不小于
     */
    public static boolean checkStrLenLessThanDesignated(String str, int designatedLen) {
        if (StringUtils.isBlank(str)) {
            return false;
        }

        if (designatedLen < 0) {
            return false;
        }

        int temp = 0;
        char[] array = str.trim().toCharArray();
        for (char c : array) {
            if (Character.isWhitespace(c)) {
                continue;
            }
            temp++;
        }

        if (temp < designatedLen) {
            return true;
        }

        return false;
    }


    /**
     * 计算不含税费用
     *
     * @param freightWithTax 含税费用
     * @param taxValue 税率（小数）
     * @return 不含税费用
     */
    public static BigDecimal calFreightWithNotTax(BigDecimal freightWithTax, BigDecimal taxValue) {
       if (null == freightWithTax || null == taxValue) {
           return freightWithTax;
       }

       return  freightWithTax.divide(new BigDecimal("1").add(taxValue), 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 计算不含税费用
     * originAmount/(1+taxRate)
     * @param originAmount 含税费用
     * @param taxRate 税率（小数）
     * @return 不含税费用
     */
    public static BigDecimal calculateAmountWithoutTax(BigDecimal originAmount, BigDecimal taxRate) {
        if (null == originAmount || null == taxRate) {
            return originAmount;
        }
        return transferToCent(originAmount.divide(taxRate.add(BigDecimal.ONE), 2, RoundingMode.HALF_UP));
    }

    /**转换为分**/
    public static BigDecimal transferToCent(BigDecimal originAmount){
        if( null == originAmount ){
            return BigDecimal.ZERO;
        }
        return originAmount.multiply(new BigDecimal(100));
    }

    /**
     * 格式化浮点数
     * @param value 传入值
     * @return
     */
    public static String formatDecimal(Double value){
        return formatDecimal(value,"##.##%");
    }

    /**
     * 格式化浮点数
     * @param value 传入值
     * @return
     */
    public static String formatDecimal(Double value, String pattern){
        if( null == value ){ return ""; }
        DecimalFormat format = new DecimalFormat();
        format.applyPattern(pattern);
        return format.format(value);
    }
}
