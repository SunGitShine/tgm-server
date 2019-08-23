package com.juma.tgm.common;

import com.juma.auth.user.domain.LoginUser;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Constants {

    public static final String SYSTEM_NAME = "TMS";

    public static final String REQUIRED = "REQUIRED";

    /**需要前端弹窗的异常,业务标识**/
    public static final String NEED_POP_UP = "NEED_POP_UP";

    public static final LoginUser SYS_LOGIN_USER = new LoginUser(2, 1);

    public static final String ADMINISTRATOR = "ADMINISTRATOR";

    public static final String SYS_TGM_DRIVER_KEY = "SYS_TGM_DRIVER_KEY";
    public static final String SYS_TGM_CUSTOMER_KEY = "SYS_TGM_CUSTOMER_KEY";
    public static final String SYS_WALLET_ACCOUNT_KEY = "SYS_WALLET_ACCOUNT_KEY";
    public static final String SYS_TGM_MANAGE__KEY = "SYS_TGM_MANAGE__KEY";
    public static final String SYS_CRM_MANAGE__KEY = "";

    public static final String AUTH_KEY_TGM_MANAGE = "TGM_MANAGE";

    public static final String AUTH_KEY_TGM_DRIVER = "TGM_DRIVER";

    public static final String AUTH_KEY_TGM_CUSTOMER = "TGM_CUSTOMER";

    public static final String TGM_ROLE_KEY = "DRIVER";

    public static final String DATA_CENTER_WAYBILL = "DATA_CENTER_WAYBILL";

    public static final String CUSTOMER_MANAGER_PERMISSION_KEY = "CUSTOMER_MANAGE";

    public static final String CITY_MANAGER_PERMISSION_KEY = "CITY_MANAGE";

    /**
     * 用车人角色
     */
    public static final String ROLE_KEY_CARGO_OWNER = "CARGO_OWNER";

    public static final String TGM_GOODS_TYPE = "GOODS_TYPE";

    public static SimpleDateFormat HHMM = new SimpleDateFormat("HH:mm");
    public static SimpleDateFormat YYMMDD = new SimpleDateFormat("yy/MM/dd");
    public static SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
    public static SimpleDateFormat YYYYMMDDHHMM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static SimpleDateFormat YYYYMMDDHHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");

    public enum SMSType {
        BINDING("BINDING");// 绑定

        private String type;

        private SMSType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

    }

    public enum Coordsys {
        GPS, MAPBAR, BAIDU;
    }

    public static final String BAIDUGEOCODER = "http://api.map.baidu.com/geocoder/v2/?output=json";// 坐标和位置相互转换
    public static final String BAIDUDIRECTION = "http://api.map.baidu.com/direction/v1?mode=driving&output=json";// 线路信息

    public static final String GAODEGEO = "http://restapi.amap.com/v3/geocode/geo";// 地理编码
    public static final String GAODEREGEO = "http://restapi.amap.com/v3/geocode/regeo";// 逆地理编码
    public static final String GAODEKEYWORDS = "http://restapi.amap.com/v3/place/text";// 关键字搜索
    public static final String GAODINPUTTIPS = "http://restapi.amap.com/v3/assistant/inputtips";// 输入提示
    public static final String GAODEDRIVING = "http://restapi.amap.com/v3/direction/driving?extensions=all&output=json";// 线路信息
    public static final String GAODEDISTRICT = "http://restapi.amap.com/v3/config/district";
    public static final String GAODEDISTANCE = "http://restapi.amap.com/v3/distance"; // 距离测量
    public static final String GAODECONVERT = "http://restapi.amap.com/v3/assistant/coordinate/convert"; // 坐标转换
    public static final String GAODEIPADDRESS = "http://restapi.amap.com/v3/ip"; // ip定位

    public static final String AMAP_REPORT_LOCATION = "http://yuntuapi.amap.com/datamanage/data/create";
    public static final String AMAP_AROUND_LOCATION = "http://yuntuapi.amap.com/datasearch/around";
    public static final String MAP_VIEW_LOCATION = "http://truckLbs.jumaps.com";
    public static final String WEIXIN_GETOPENID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";

    public static final String STAR_CUSTOMER = "CUSTOMER";
    public static final String STAR_DRIVER = "DRIVER";

    public static final String APP_USER_PRVEFIEX = "UID";
    public static final String APP_USER_CONFIRM_RECEIVED_FREIGHT = "UID_TGM_FREIGHT";

    public static final String TRUCK_TYPE = "TRUCK_TYPE";
    public static final String TRUCK_LENGTH = "TRUCK_LENGTH";
    public static final String TRUCK_LOAD = "TRUCK_LOAD";
    public static final String TRUCK_BRAND = "TRUCK_BRAND";
    public static final Integer TRUCK_RESIDUE = 5;
    public static final String COLD_CHAIN = "COLD_CHAIN";
    public static final String TAX_RATE = "TAX_RATE";
    public static final String WAYBILL_PARAM = "WAYBILL_PARAM";
    public static final String DEFAULT_REGION_CODE = "32000";
    public static final String DEFAULT_FRIGHT_REGION_CODE = "22000";
    public static final String DEFAULT_FRIGHT_CODE_KEY = "DEFAULT_FRIGHT_CODE_KEY";
    public static final String SUPER_AUTH = "SUPER_AUTH";
    public static final String MOVE_UP = "UP";
    public static final String MOVE_DOWN = "DOWN";
    public static final Integer EXPORT_DEFAULT_NO = 100;
    public static final Integer DEFAULT_DISTANCE = 2000;
    // 默认的列表拆分数量
    public static final Integer DEFAULT_LIST_SIZE = 200;
    // 强制重新登录版本号
    public static final String TMS_APK_VERSION_KEY = "tms_apk_version_key";
    public static final String VERSIONCHECK = "2.1.1";
    public static final String VERSION_CHECK_KEY = "TGM_VERSION_CHECK_KEY";
    public static final String WECHAT_ERROR_PAGE_URL = "http://wechat.lovedriver.jumaps.com/error.html";
    public static final String WECHAT_WAYBILL_INFO_PAGE_URL = "http://wechat.lovedriver.jumaps.com/forward/detail.html#/?fromWeixinPush=yes&waybillId=";
    public static final String WECHAT_WAYBILL_EVALUATE_PAGE_URL = "http://wechat.lovedriver.jumaps.com/forward/evaluate.html#/?fromWeixinPush=yes&waybillId=";
    //用户中心参数配置
    public static final String TMS_GOODS_TYPE ="TMS_GOODS_TYPE";
    public static final String TMS_DELIVERY_ADDRESS_TYPE ="TMS_DELIVERY_ADDRESS_TYPE";
    /**
     * 经纪人业绩
     */
    public static final String CUSTOMER_PERFORMANCE_URL = "http://dataInterface.jumaps.com/dataCommon/%s/getManagerAchievement";

    /**
     * 高德地图key
     */
    public static final String AMAP_KEY = "88849e0bf91150659e86cb0bd0d05e35";

    /**
     * 用户中心参数配置-大客户行业
     */
    public static final String INDUSTRY_TYPE = "INDUSTRY_TYPE";
    /** 入城证参数获取KEY */
    public static final String ENTRY_CITY_LICENSE_TYPE = "ENTRY_CITY_LICENSE_TYPE";
    /** 司机资质KEY */
    public static final String DRIVER_QUALIFICATION = "DRIVER_QUALIFICATION";
    /** 重要通知客户端KEY */
    public static final String NOTICE_APPLICATION = "NOTICE_APPLICATION";
    /** 调用自动匹配url */
    public static final String WAYBILL_AUTO_MATCH_URL = "http://truckMatch.jumaps.com/truckMatch/postData.html";
    /** 数据导出基础url */
    public static final String DATA_EXPORT_BASE_URL = "http://dataInterface.jumaps.com/export";
      /** 数据中心ops地址 */
    public static final String OPS_URL = "http://dvs.jumaps.com/rest/{0}/getDataByTemplate?user=admin&token=dd966744-94c8-4399-a92c-a0209eef619a&data=";
    /** 自动匹配按钮控制KEY */
    public static final String WAYBILL_AUTO_MATCH_BUTTON_CONTROL = "WAYBILL_AUTO_MATCH_BUTTON_CONTROL";
    /** 自动匹配返回数据列表KEY */
    public static final String WAYBILL_AUTO_MATCH_LIST = "WAYBILL_AUTO_MATCH_LIST";
    /** 自动匹配返回数据列表时时间KEY */
    public static final String WAYBILL_AUTO_MATCH_DATE = "WAYBILL_AUTO_MATCH_DATE";
    /** 自动匹配系统计算返回数据列表KEY */
    public static final String TRUCK_MATCH_RESULT = "truck_match_result";
    /**
     * 客户经理-运单抢单超时-推送消息
     */
    public static final String MSG_CUST_MAN_BILL_HANDLE_TIME_OUT = "CUST_MAN_BILL_HANDLE_TIME_OUT";

    /** SCM:厢型KEY */
    public static final String SCM_BOX_LEVEL = "boxLevel";
    /** SCM:车厢长度KEY */
    public static final String SCM_BOX_TYPE = "boxType";
    /** 字典表：报销类型KEY */
    public static final String COST_REIMBURSED_TYPE = "COST_REIMBURSED_TYPE";
    /** 字典表：收款账户KEY */
    public static final String RECEIVABLE_ACCOUNT_KEY = "RECEIVABLE_ACCOUNT_KEY";
    /** 尾板 */
    public static final Integer TAIL_BOARD_VALUE = 1;

    /**
     * 客户经理-调度无法指派车辆-推送消息
     */
    public static final String CUST_MAN_ASSIGN_CAR_FEEDBACK_APP = "CUST_MAN_ASSIGN_CAR_FEEDBACK_APP";

    /**
     * 客户经理-调度无法指派车辆-短信
     */
    public static final String CUST_MAN_ASSIGN_CAR_FEEDBACK_SMS = "CUST_MAN_ASSIGN_CAR_FEEDBACK_SMS";

    /**
     * 客户经理-修改<b>待配送</b>运单计划用车时间-司机端推送消息key
     */
    public static final String PLAN_DELIVERY_TIME_CHANGE_APP = "PLAN_DELIVERY_TIME_CHANGE_APP";

    /**
     * 订单取消原因字典表key
     */
    public static final String WAYBILL_CANCEL_REASON_KEY = "WAYBILL_CANCEL_REASON_KEY";

    /**
     * 报备类型字典表key
     */
    public static final String REPORT_INFO_TYPE_KEY = "REPORT_INFO_TYPE_KEY";

    /**
     * TGM渠道parentCode
     */
    public static final String TGM_SOURCE_CHANNEL_CODE = "CUSTOMER_MANAGER";

    /**
     * 允许换车的时间限制，分钟为单位
     */
    public static final Integer TIME_LIMIT_ALLOW_UPDATE_CAR = 30;

    /**
     * 允许换车的时间限制字典KEY
     */
    public static final String TIME_LIMIT_ALLOW_UPDATE_CAR_KEY = "TIME_LIMIT_ALLOW_UPDATE_CAR_KEY";

    /**
     * 运单导入含税费用的最大值字典KEY
     */
    public static final String OFFLINE_WAYBILL_COST_UPPER_LINIT_KEY = "OFFLINE_WAYBILL_COST_UPPER_LINIT_KEY";

    /**
     * 上传照片半地址开头
     */
    public static final String UPLOAD_IMAGE_URL_START = "upload/images";

    /**
     * 删除标志-已删除
     */
    public static final byte flag_true = 1;

    /**
     * 删除标志-未删除
     */
    public static final byte flag_false = 0;

    /**
     * 货物类型最大长度
     */
    public static final int GOODS_TYPE_MAX_LENGTH = 45;

    /**
     * 运单派车冗余时间(秒)
     */
    public static final int REDUNDANCY_TIME = 1 * 60 * 60;

    /**
     * 超时时间key
     */
    public final static String COMPETE_BILL_KEY_EXPIRE_TIME = "CUST_MAN_TIME_OUT_VALUE";

    /**
     * 标志已上传过线路修改信息
     */
    public final static byte FLAG_UPLOAD_CHANGE_DELIVERY_POINT = 2;

    /**
     * 修改取货地报备类型
     */
    public static final Integer UPDATE_DELIVERY_ADDRESS_REPORT_TYPE = 4;

    /**
     * 电子围栏半径字典表KEY
     */
    public static final String WAYBILL_FENCE_RADIUS_KEY = "WAYBILL_FENCE_RADIUS_KEY";

    /**
     * 电子围栏开始时间冗余字典表KEY
     */
    public static final String WAYBILL_FENCE_START_TIME__KEY = "WAYBILL_FENCE_START_TIME__KEY";

    /**
     * 电子围栏默认半径
     */
    public static final Double WAYBILL_FENCE_RADIUS_DEFAULT = 500D;

    /**
     * 重要通知：单独选择标记
     */
    public static final Integer TGM_SINGLE_SELECT = 99;

    /**
     * 重要通知应用KEY
     */
    public static final String TGM_NOTICE_KEY = "NOTICE";

    /**
     * 地图标注图片KEY
     */
    public static final String TGM_LABEL_URL_KEY = "TGM_LABEL_URL_KEY";

    /**
     * 落地配地图标注图片KEY
     */
    public static final String TGM_LANDING_WAYBILL_LABEL_URL_KEY = "TGM_LANDING_WAYBILL_LABEL_URL_KEY";

    /**
     * 两位小数
     */
    public static final DecimalFormat DECIMAL_2_FORMAT = new DecimalFormat("#.##");
    /**
     * 整数
     */
    public static final DecimalFormat DECIMAL_0_FORMAT = new DecimalFormat("#");

    /**
     * 默认部门CODE
     */
    public static final String DEFAULT_DEPARTMENT_CODE = "00";

    /**
     * 地址最大长度
     */
    public static final int ADDRESS_MAX_LENGTH = 255;

    /**
     * 地址最大个数
     */
    public static final int ADDRESS_MAX_SIZE = 30;

    /**
     * 整车最大方量；单位：立方米
     */
    public static final double CAPACITY_FULL_VOLUME_SIZE = 18;

    /**
     * 整车最大载重；单位：吨
     */
    public static final double CAPACITY_FULL_WEIGHT_SIZE = 5;

    /**
     * 是否入城
     */
    public static final int FENCE_TYPE_AT_CITY = 110;

    /**
     * 是否在业务区域内
     */
    public static final int FENCE_TYPE_AT_BUSINESS_AREA = 100;

    /**
     * 是否在禁货区域
     */
    public static final int FENCE_TYPE_AT_FORBIDDEN_AREA = 120;

    /**
     * 零担预估完成时间
     */
    public static final int SCATTERED_ESTIMATE_FINISH_TIME = 4;

    /**
     * 整车预估完成时间
     */
    public static final int SCATTERED_FULL_ESTIMATE_FINISH_TIME = 6;

    /**
     * 已开通业务范围电子围栏类型
     */
    public static final Integer OPEN_SERVICE_FENCE_TYPE = 100;

    /**
     * 入城区域电子围栏类型
     */
    public static final Integer OPEN_ENTRY_FENCE_TYPE = 110;

    /**
     * 强制结束时长KEY
     */
    public static final String CONSTRAINT_FINISH_WAYBILL_DAY_KEY = "CONSTRAINT_FINISH_WAYBILL_DAY_KEY";

    /**
     * 落地配运单派单倒计时key
     */
    public static final String SCATTERED_WAYBILL_WAIT_ASSIGN_TIME = "SCATTERED_WAYBILL_WAIT_ASSIGN_TIME";

    /**
     * 落地配配置
     */
    public static final String SCATTERED_WAYBILL_CONFIG = "SCATTERED_WAYBILL_CONFIG";

    /**
     * 租户key-> 希地物流
     */
    public static final String TENANT_KEY_XIDI_LOGISTICS = "XIDI-LOGISTICS";

    /**
     * 租户key-> 驹马物流
     */
    public static final String TENANT_KEY_JUMA_LOGISTICS = "JUMA-LOGISTICS";

    /**
     * 租户key-> 越好冷链
     */
    public static final String TENANT_KEY_YUEHAO_COLDCHAIN = "YUEHAO-COLDCHAIN";

    /**
     * 项目账目类型
     */
    public static final String PROJECT_ACCOUNTS_TYPE = "PROJECT_ACCOUNTS_TYPE";

    /**
     * 希地租户id
     */
    public static final String TENANT_CODE_XIDI_LOGISTICS = "000000001";

    /**
     * 已配送完成的状态集
     */
    public static final Integer[] DELIVERIED_STATUS = new Integer[] { 5, 6, 7 };

    /**
     * 客户经理和企业客户不匹配
     */
    public static final String CUSTOMER_MANAGER_CUSTOMER_NOT_MATCH = "CUSTOMER_MANAGER_CUSTOMER_NOT_MATCH";

    /**
     * 运力不匹配
     */
    public static final String TRANSPORT_CAPACITY_NOT_MATCH = "TRANSPORT_CAPACITY_NOT_MATCH";

    /**
     * 用车要求入城证key
     */
    public static final String ADDTIONAL_FUNCTION_ENTRY_LICENSE = "9";

    /**
     * 设备类型 key
     */
    public static final String DEVICE_PRIORITY = "DEVICE_PRIORITY";

    /**
     * 用车要求默认选中值 key
     */
    public static final String ADDFUNCTION_DEFAULT_CHECKED_KEY = "ADDFUNCTION_DEFAULT_CHECKED_KEY";

    /**
     * 转运单甲方车牌号
     */
    public static final String VENDOR_WAYBILL_PLATENUMBER = "转运单无车牌号";

    /**
     * 点击到仓的时间间隔KEY（分钟）
     */
    public static final String DRIVER_CLICK_ARRIVE_DOPT_TIME_LIMIT_KEY = "DRIVER_CLICK_ARRIVE_DOPT_TIME_LIMIT_KEY";

    /**
     * 到仓与离仓之间的时间间隔KEY（分钟）
     */
    public static final String DRIVER_CLICK_LEAVE_DOPT_TIME_LIMIT_KEY = "DRIVER_CLICK_LEAVE_DOPT_TIME_LIMIT_KEY";

    /**
     * 项目结束后可以续签合同的有效期限（天）
     */
    public static final String PROJECT_RENEWAL_CONTRACT_TERM_KEY = "PROJECT_RENEWAL_CONTRACT_TERM_KEY";

    /**
     * 运单可以改价的时间限制的默认值
     */
    public static final Integer ALLOW_CHANGE_PRICE_DEFAULT_TIME_LIMIT = 1;

    /**
     * 运单可以改价的时间限制
     */
    public static final String ALLOW_CHANGE_PRICE_TIME_LIMIT_KEY = "ALLOW_CHANGE_PRICE_TIME_LIMIT_KEY";
    /**
     * 运单可以改价的金额上限-默认值
     */
    public static final Double ALLOW_WAYBILL_CHANGE_PRICE_CEILING_LIMIT = 30000D;
    /**
     * 运单可以改价的金额上限
     */
    public static final String ALLOW_WAYBILL_CHANGE_PRICE_CEILING_LIMIT_KEY = "ALLOW_WAYBILL_CHANGE_PRICE_CEILING_LIMIT_KEY";
    /**毛利率下限**/
    public static final String ALLOW_WAYBILL_GROSS_PROFIT_FLOOR = "ALLOW_WAYBILL_GROSS_PROFIT_FLOOR";
    /**毛利率上限**/
    public static final String ALLOW_WAYBILL_GROSS_PROFIT_CEILING = "ALLOW_WAYBILL_GROSS_PROFIT_CEILING";
    /**毛利率下限:默认值**/
    public static final String ALLOW_WAYBILL_GROSS_PROFIT_FLOOR_DEFAULT = "-20%";
    /**毛利率上限:默认值**/
    public static final String ALLOW_WAYBILL_GROSS_PROFIT_CEILING_DEFAULT = "20%";

    /**
     * 运单创建调整单时可以改价的下限
     */
    public static final String ALLOW_CHANGE_PRICE_FLOOR_LIMIT_KEY = "ALLOW_CHANGE_PRICE_FLOOR_LIMIT_KEY";

    /**
     * 运单创建调整单时可以改价的上限
     */
    public static final String ALLOW_CHANGE_PRICE_CEILING_LIMIT_KEY = "ALLOW_CHANGE_PRICE_CEILING_LIMIT_KEY";

    /**
     * 客户侧含税金额上限限制KEY
     */
    public static final String TMS_FREIGHT_WITH_TAX_UPPER_LIMIT_KEY = "TMS_FREIGHT_WITH_TAX_UPPER_LIMIT_KEY";

    /**
     * 运单快捷查询参数
     */
    public enum WaybillQuickQueryParameterEnum {
        STATUS_VIEW_KEY("statusViewList", "status_view", "运单状态"),
        RECEIVABLE_RECONCILICATION_STATUS_KEY(
                "receivableReconcilicationStatusList",
                "receivable_reconcilication_status",
                "客户对账状态"),
        RECEIPT_STATUS_KEY("receiptStatusList", "receipt_status", "客户收款状态"),
        RECONCILIATION_STATUS_KEY("reconciliationStatusList", "reconciliation_status", "承运商对账状态"),
        SETTLEMENT_STATUS_KEY("settlementStatusList", "settlement_status", "承运商结算状态");

        private String key;
        private String columnName;
        private String name;

        private WaybillQuickQueryParameterEnum(String key, String columnName, String name) {
            this.key = key;
            this.columnName = columnName;
            this.name = name;
        }

        public String getKey() {
            return key;
        }

        public String getColumnName() {
            return columnName;
        }

        public String getName() {
            return name;
        }
    }
}
