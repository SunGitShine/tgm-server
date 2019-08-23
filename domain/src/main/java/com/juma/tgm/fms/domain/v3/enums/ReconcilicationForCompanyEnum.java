package com.juma.tgm.fms.domain.v3.enums;

public class ReconcilicationForCompanyEnum {

    public enum CompanyType {
        CONTRACT(1,"签约方"),PAY(2,"运营方");
        
        private int code;
        
        private String descr;
        
        private CompanyType(int code,String descr) {
            this.code = code;
            this.descr = descr;
        }
        
        public int getCode() {
            return code;
        }
        public void setCode(int code) {
            this.code = code;
        }
        public String getDescr() {
            return descr;
        }
        public void setDescr(String descr) {
            this.descr = descr;
        }
        
    }
    
}
