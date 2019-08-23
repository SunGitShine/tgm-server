package com.juma.tgm.xlsx.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * xlsx_title_field_mapping - xlsx_title_field_mapping
 * 
 * @author  2017-08-02
 * @version 1.0 
 */
public class XlsxTitleFieldMapping implements Serializable {
	/**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6576354941954913037L;
    private Integer mappingId;
	private Integer templateId;
	private String title;
	private String field;
	private Date createTime;
	private Date lastUpdateTime;
	private Integer createUserId;
	private Integer lastUpdateUserId;

	public Integer getMappingId() {
		return mappingId;
	}

	public void setMappingId(Integer mappingId) {
		this.mappingId = mappingId;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
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

}