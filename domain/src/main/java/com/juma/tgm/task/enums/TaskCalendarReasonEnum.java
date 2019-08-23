package com.juma.tgm.task.enums;

public class TaskCalendarReasonEnum {

    public enum ReasonType {

        NotDelivery(1,"不配送"),RecoverDelivery(2,"恢复配送");

        private int code;

        private String desc;

        ReasonType(int code,String desc) {
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

    public enum ReasonSort {

        VendorReason(1,"承运商原因"),CustomerReason(2,"客户原因"),CompanyReason(3,"公司原因");

        private int code;

        private String desc;

        ReasonSort(int code,String desc) {
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
