package com.juma.tgm.common;

/**
 *
 *
 * @ClassName: ManagerScheduleConstants
 * @Description:
 * @author: liang
 * @date: 2017-06-15 13:47
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class ManagerScheduleConstants {


    //类型名称枚举


//
//    //typeMap
//    public static final Map<String, ManagerScheduleRoot> typeMap = new HashMap<>();
//
//    //运单处理模板
//    /**
//     * 方便遍历模板
//     */
//    public static final Map<String, WaybillHandleProcessType> waybillHandleProcessTemplate = new HashMap<>();
//
//    static {
//        registerWaybillHandleProcessTemplate();
//
//
////        typeMap.addAll(waybillHandleProcessTemplate);
//
//    }
//
//    /**
//     * 注册运单处理类型模板
//     */
//    private static void registerWaybillHandleProcessTemplate(){
//
//        waybillHandleProcessTemplate.put(IncreaseCarryFeeTpl.class.getName(), new IncreaseCarryFeeTpl());
//        waybillHandleProcessTemplate.put(AssignFeedbackTpl.class.getName(), new AssignFeedbackTpl());
//
//        typeMap.putAll(waybillHandleProcessTemplate);
//
//    }
//
//    public interface ManagerScheduleRoot {}
//
//    /**
//     * 分类-运单处理
//     */
//    public abstract static class WaybillHandleProcessType implements ManagerScheduleRoot {
//        public final int type = 1;
//        public final String typeName = "运单处理";
//    }
//
//    /**
//     * 模板-运单处理-增加运费
//     */
//    public final static class IncreaseCarryFeeTpl extends  WaybillHandleProcessType {
//        public final String content = "尾号%s的运单增加了%s元搬运费";
//    }
//
//    /**
//     * 模板-运单处理-派车反馈
//     */
//    public final static class AssignFeedbackTpl extends WaybillHandleProcessType {
//        public final String content = "运单无法派车，调度已添加派车反馈，请及时与货主沟通";
//    }
//
//    public static void main(String[] args) {
//
////        for(String key : typeMap.keySet()){
////            System.out.println(key);
////            typeMap.get(key);
////        }
//
//    }


}
