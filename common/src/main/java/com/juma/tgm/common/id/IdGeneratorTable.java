package com.juma.tgm.common.id;

import java.text.DecimalFormat;

public class IdGeneratorTable {

    /**
     * 
     * 业务标识+时间(yyyyMMddHHmmss)+随机
     *
     */
    
    public enum  IdType {
        
        AP("reconcilication_for_payable","应付"),AR("reconcilication_for_receivable","应收"),
        APS("reconcilication_for_sub_payable","应付子账单"),XM("project","项目编号"),CO("reconcilication_for_company","签约、运营主体间的对账"),
        TZD("adjust_for_master","运单调整单"),RW("task_scheduled","任务计划"),RB("project_daily","日报");

        private String tableName;
        
        private String descr;
        
        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getDescr() {
            return descr;
        }

        public void setDescr(String descr) {
            this.descr = descr;
        }

        private IdType(String tableName,String descr) {
            this.tableName = tableName;
            this.descr = descr;
        }
        
    } 
    
    
    public static void main(String[] args) {
        System.out.println(IdGeneratorTable.IdType.AR.name());
        StringBuilder abc = new StringBuilder();
        DecimalFormat format = new DecimalFormat("000000");
        abc.append(format.format(123 % 100000));
        System.out.println(format.format(123 % 100000));
    }
    
}
