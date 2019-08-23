package com.juma.tgm.task.enums;

public class TaskCalendarEnum {

    public enum WorkStatus {

        Running(0,"运行中")
        ,Finish(1,"已完成")
        ,NotDelivery(2,"不配送")
        ,Conflict(3,"运力冲突");

        private int code;

        private String desc;

        WorkStatus(int code,String desc) {
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

    }
}
