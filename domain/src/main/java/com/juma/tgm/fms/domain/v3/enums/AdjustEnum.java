package com.juma.tgm.fms.domain.v3.enums;

import org.apache.commons.lang.StringUtils;

import com.juma.vms.transport.domain.TransportCapacityEnum;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-05-13 10:58
 **/
public class AdjustEnum {

	public static enum AuditStatus {
		Append(0, "UNSUBMIT", "未提交"),
		SUBMIT(1, "AUDITING", "审核中"),
		REJECT(2, "REJECT", "被驳回"),
		AGREE(3, "AUDITED", "已通过"),
		REVOKE(4, "REVOKE", "已撤销"),
		CANCEL(5, "CANCEL", "已放弃"),
		PROCESS(6, "PROCESS", "处理中");

		private int code;
		private String workFlowKey;
		private String descr;

		private AuditStatus(int code, String workFlowKey, String descr) {
			this.code = code;
			this.workFlowKey = workFlowKey;
			this.descr = descr;
		}

		public int getCode() {
			return this.code;
		}

		public String getWorkFlowKey() {
			return this.workFlowKey;
		}

		public String getDescr() {
			return this.descr;
		}

		public static String getdescByCode(Integer code) {
			if (null == code) {
				return null;
			} else {
				AdjustEnum.AuditStatus[] arr$ = values();
				int len$ = arr$.length;

				for(int i$ = 0; i$ < len$; ++i$) {
					AdjustEnum.AuditStatus a = arr$[i$];
					if (a.getCode() == code) {
						return a.getDescr();
					}
				}

				return null;
			}
		}

		public static Integer getCodeByWorkFlowKey(String workFlowKey) {
			if (StringUtils.isBlank(workFlowKey)) {
				return null;
			} else {
				AdjustEnum.AuditStatus[] arr$ = values();
				int len$ = arr$.length;

				for(int i$ = 0; i$ < len$; ++i$) {
					AdjustEnum.AuditStatus a = arr$[i$];
					if (a.getWorkFlowKey().equals(workFlowKey)) {
						return a.getCode();
					}
				}

				return null;
			}
		}
	}
}
