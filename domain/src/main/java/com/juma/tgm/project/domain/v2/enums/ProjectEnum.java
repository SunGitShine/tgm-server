package com.juma.tgm.project.domain.v2.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 项目中状态、类型枚举
 */
public class ProjectEnum {

		// 审核状态
		public enum AuditStatus {

		Append(0,"UNSUBMIT","未提交"),SUBMIT(1,"AUDITING","审核中"),REJECT(2,"REJECT","被驳回"),AGREE(3,"AUDITED","已通过"),CANCEL(4,"CANCEL","已取消"),PROCESS(6,"PROCESS","处理中");

		private int code;
		private String workFlowKey;
		private String descr;

		private AuditStatus(int code, String workFlowKey, String descr) {
			this.code = code;
			this.workFlowKey = workFlowKey;
			this.descr = descr;
		}

		public int getCode() {
			return code;
		}

		public String getWorkFlowKey() {
			return workFlowKey;
		}
		public String getDescr() {
			return descr;
		}

		public static String getdescByCode(Integer code) {
			if (null == code) {
				return null;
			}

			for (AuditStatus a : AuditStatus.values()) {
				if (a.getCode() == code) {
					return a.getDescr();
				}
			}

			return null;
		}

		public static Integer getCodeByWorkFlowKey(String workFlowKey) {
			if (StringUtils.isBlank(workFlowKey)) {
				return null;
			}

			for (AuditStatus a : AuditStatus.values()) {
				if (a.getWorkFlowKey().equals(workFlowKey)) {
					return a.getCode();
				}
			}

			return null;
		}
	}

	//项目类型
	public enum ProjectType{

		TEST_RUN(1,"试运行"),REAL_RUN(2,"正式运行");

		private int code;
		private String descr;

		private ProjectType(int code, String descr) {
			this.code = code;
			this.descr = descr;
		}

		public int getCode() {
			return code;
		}

		public String getDescr() {
			return descr;
		}

		public static String getdescByCode(Integer code) {
			if (null == code) {
				return null;
			}

			for (ProjectType p : ProjectType.values()) {
				if (p.getCode() == code) {
					return p.getDescr();
				}
			}

			return null;
		}
	}

	//项目状态
	public enum ProjectStatus{

		NO_START(1,"未启动"),RUNING(2,"运行中"),PAUSE(3,"已暂停"),END(4,"已结束");

		private int code;
		private String descr;

		private ProjectStatus(int code, String descr) {
			this.code = code;
			this.descr = descr;
		}

		public int getCode() {
			return code;
		}

		public String getDescr() {
			return descr;
		}

		public static String getdescByCode(Integer code) {
			if (null == code) {
				return null;
			}

			for (ProjectStatus p : ProjectStatus.values()) {
				if (p.getCode() == code) {
					return p.getDescr();
				}
			}

			return null;
		}
	}

	public enum WorkflowType{

		START(1,"启动"),PAUSE(2,"暂停"),RECOVER(3,"恢复"),END(4,"结束");

		private int code;
		private String descr;

		private WorkflowType(int code, String descr) {
			this.code = code;
			this.descr = descr;
		}

		public int getCode() {
			return code;
		}

		public String getDescr() {
			return descr;
		}

		public static String getdescByCode(Integer code) {
			if (null == code) {
				return null;
			}

			for (WorkflowType w : WorkflowType.values()) {
				if (w.getCode() == code) {
					return w.getDescr();
				}
			}

			return null;
		}
	}
}
