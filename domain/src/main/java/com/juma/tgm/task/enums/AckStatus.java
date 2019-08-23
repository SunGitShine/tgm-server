package com.juma.tgm.task.enums;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-07-08 10:23
 **/
public enum AckStatus {

	Waiting_Back(0,"待回复"),
	Confirmed(1,"已确认"),
	Denied(2,"已拒绝"),
	Lose_Efficacy(3,"已失效"),
	Overdue(4,"已过期");


	private int code;

	private String desc;

	AckStatus(int code,String desc) {
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

	public static AckStatus getAckStatusByCode(int code) {
		for ( AckStatus rs : AckStatus.values()) {
			if (NumberUtils.compare(rs.code, code) == 0) {
				return rs;
			}
		}

		return null;
	}

	public static Map<String, String> map() {
		Map<String, String> map = new HashMap<>();
		for ( AckStatus rs : values()) {
			map.put(rs.getCode() + "", rs.getDesc());
		}
		return map;
	}
}
