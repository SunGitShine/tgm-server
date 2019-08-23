package com.juma.tgm.customerManager.domain;


import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 客户经理代办事项
 * @author  2017-06-15
 * @version 1.0 
 */
public class ManagerSchedule implements Serializable {
	private Integer managerScheduleId;
	private String content;
	private Integer type;
	private Integer handled;
	private Integer waybillId;
	private Integer isDelete;
	private Integer customerManagerId;
	private Date createTime;
	private Integer createUserId;
	private Integer lastUpdateUserId;
	private Date lastUpdateTime;

	public Integer getManagerScheduleId() {
		return managerScheduleId;
	}

	public void setManagerScheduleId(Integer managerScheduleId) {
		this.managerScheduleId = managerScheduleId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getHandled() {
		return handled;
	}

	public void setHandled(Integer handled) {
		this.handled = handled;
	}

	public Integer getWaybillId() {
		return waybillId;
	}

	public void setWaybillId(Integer waybillId) {
		this.waybillId = waybillId;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}


	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getLastUpdateUserId() {
		return lastUpdateUserId;
	}

	public void setLastUpdateUserId(Integer lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getCustomerManagerId() {
		return customerManagerId;
	}

	public void setCustomerManagerId(Integer customerManagerId) {
		this.customerManagerId = customerManagerId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ManagerSchedule that = (ManagerSchedule) o;
		return Objects.equal(managerScheduleId, that.managerScheduleId) &&
				Objects.equal(content, that.content) &&
				Objects.equal(type, that.type) &&
				Objects.equal(handled, that.handled) &&
				Objects.equal(waybillId, that.waybillId) &&
				Objects.equal(isDelete, that.isDelete) &&
				Objects.equal(createTime, that.createTime) &&
				Objects.equal(createUserId, that.createUserId) &&
				Objects.equal(lastUpdateUserId, that.lastUpdateUserId) &&
				Objects.equal(lastUpdateTime, that.lastUpdateTime);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(managerScheduleId, content, type, handled, waybillId, isDelete, createTime, createUserId, lastUpdateUserId, lastUpdateTime);
	}
}