package com.juma.tgm.fms.domain.v3.enums;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;

import com.juma.tgm.waybill.domain.Waybill;

/**
 * @ClassName ReconcilicationForReceivableEnum.java
 * @Description 应收对账枚举
 * @author qiang.xie
 * @Date 2018年11月23日 上午10:40:29
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class ReconcilicationForReceivableEnum {

    // 对账单审核状态
    public enum ApprovalStatusStatus {

		Append(0,"未提交"),SUBMIT(1,"审核中"),REJECT(2,"被驳回"),AGREE(3,"已通过");

        private int code;
        private String descr;

        private ApprovalStatusStatus(int code, String descr) {
            this.code = code;
            this.descr = descr;
        }

        public int getCode() {
            return code;
        }

        public String getDescr() {
            return descr;
        }
    }

    /**
     * 收款状态
     */
    public enum ReceiptStatus {
        NOT_COLLECTION(1, "未收款"), SEGMENT_COLLECTION(2, "部分收款"), HAS_COLLECTION(3, "已收款");

        private int code;

        private String desc;

		ReceiptStatus(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

		public static ReconcilicationForReceivableEnum.ReceiptStatus getReceiptStatusByCode(int code) {
			for (ReconcilicationForReceivableEnum.ReceiptStatus rs : ReconcilicationForReceivableEnum.ReceiptStatus.values()) {
				if (NumberUtils.compare(rs.code, code) == 0) {
					return rs;
				}
			}

			return null;
		}

		public static Map<String, String> map() {
			Map<String, String> map = new HashMap<String, String>();
			for (ReconcilicationForReceivableEnum.ReceiptStatus rs : values()) {
				map.put(rs.getCode() + "", rs.getDesc());
			}
			return map;
		}
    }

    /**
     * 开票状态
     */
    public enum InvoiceStatus {
        NOT_INVOICESTATUS(1, "未开票"), HAS_INVOICESTATUS(2, "已开票");

        private int code;

        private String desc;

        InvoiceStatus(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

		public static ReconcilicationForReceivableEnum.InvoiceStatus getInvoiceStatusByCode(int code) {
			for (ReconcilicationForReceivableEnum.InvoiceStatus rs : ReconcilicationForReceivableEnum.InvoiceStatus.values()) {
				if (NumberUtils.compare(rs.code, code) == 0) {
					return rs;
				}
			}

			return null;
		}

		public static Map<String, String> map() {
			Map<String, String> map = new HashMap<String, String>();
			for (ReconcilicationForReceivableEnum.InvoiceStatus rs : values()) {
				map.put(rs.getCode() + "", rs.getDesc());
			}
			return map;
		}
    }

	/**
	 * 结算状态
	 */
	public enum SettlementStatus {
		NOT_CLEAR(0, "未结算"), PREPARE_CLEAR(2, "预结算"), HAS_CLEAR(1, "已结算");

		private int code;
		private String desc;

		SettlementStatus(int code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		public int getCode() {
			return code;
		}

		public String getDesc() {
			return desc;
		}

		public static ReconcilicationForReceivableEnum.SettlementStatus getSettlementStatusByCode(int code) {
			for (ReconcilicationForReceivableEnum.SettlementStatus ss : ReconcilicationForReceivableEnum.SettlementStatus.values()) {
				if (NumberUtils.compare(ss.code, code) == 0) {
					return ss;
				}
			}

			return null;
		}

		public static Map<String, String> map() {
			Map<String, String> map = new HashMap<String, String>();
			for (ReconcilicationForReceivableEnum.SettlementStatus ss : values()) {
				map.put(ss.getCode() + "", ss.getDesc());
			}
			return map;
		}
	}

	/**
	 * 对账状态
	 */
	public enum ReconciliationStatus {

		NOT_RECONCILIATION(1, "未对账"), IN_THE_ACCOUNT(4, "对账中"), HAS_RECONCILIATION(2, "已对账");

		private int code;
		private String descr;

		private ReconciliationStatus(int code, String descr) {
			this.code = code;
			this.descr = descr;
		}

		public int getCode() {
			return code;
		}

		public String getDescr() {
			return descr;
		}

		public static ReconcilicationForReceivableEnum.ReconciliationStatus getReconciliationStatusByCode(int code) {
			for (ReconcilicationForReceivableEnum.ReconciliationStatus rs : ReconcilicationForReceivableEnum.ReconciliationStatus.values()) {
				if (NumberUtils.compare(code, rs.getCode()) == 0) {
					return rs;
				}
			}
			return null;
		}

		public static Map<String, String> map() {
			Map<String, String> map = new HashMap<String, String>();
			for (ReconcilicationForReceivableEnum.ReconciliationStatus rs : values()) {
				map.put(rs.getCode() + "", rs.getDescr());
			}
			return map;
		}
	}
}
