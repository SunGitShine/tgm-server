package com.juma.tgm.task.enums;

import org.apache.commons.lang.math.NumberUtils;

import java.util.HashMap;
import java.util.Map;


public class TaskEnum {


    public enum TaskAckStatus {

        Waiting_Reply(0,"待回复"),
        Received(1,"已中选"),
        Refused(2,"已拒绝"),
        Invalided(3,"已失效"),
        Expired(4,"已过期");

        TaskAckStatus(int code,String desc) {
            this.code = code;
            this.desc = desc;
        }

        private int code;

        private String desc;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public enum WorkStatus {

        Running(0,"运行中"),
        Finish(1,"已完成"),
        Not_delivery(2,"不配送"),
        Conflict(3,"运力冲突");

        WorkStatus(int code,String desc) {
            this.code = code;
            this.desc = desc;
        }

        private int code;

        private String desc;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public enum DeliveryStatus {

        Not_Sign_In(0,"未签到"),
        Arrive_Depot(1,"已到仓"),
        Leave_Depot(2,"已离仓");

        DeliveryStatus(int code,String desc) {
            this.code = code;
            this.desc = desc;
        }

        private int code;

        private String desc;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public enum  TaskStatus {

        Waiting_Invite(0,"待指派运力"),
        Inviting(1,"邀请中"),
        Waiting_Become(2,"待生效"),
        Running(3,"运行中"),
        Pause(4,"已暂停"),
        Finish(5,"已结束"),
        Cancel(6,"已取消"),
        Expired(7,"已过期");

        private int code;

        private String desc;

        TaskStatus(int code,String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public static TaskStatus getTaskStatusByCode(int code) {
            for ( TaskStatus rs : TaskStatus.values()) {
                if (NumberUtils.compare(rs.code, code) == 0) {
                    return rs;
                }
            }

            return null;
        }

        public static Map<String, String> map() {
            Map<String, String> map = new HashMap<>();
            for ( TaskStatus rs : values()) {
                map.put(rs.getCode() + "", rs.getDesc());
            }
            return map;
        }

    }

    public static void main(String[] args) {
        System.out.println(TaskStatus.getTaskStatusByCode(1).getCode());
    }

}


