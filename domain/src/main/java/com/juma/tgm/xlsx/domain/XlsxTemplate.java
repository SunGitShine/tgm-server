package com.juma.tgm.xlsx.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * xlsx_template - xlsx_template
 * 
 * @author  2017-08-02
 * @version 1.0 
 */
public class XlsxTemplate implements Serializable {
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7597329174009489245L;
    private Integer templateId;
	private String name;
	private Integer startDataIndex;
	private Integer createUserId;
	private Date createTime;
	private Integer lastUpdateUserId;
	private Date lastUpdateTime;

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getLastUpdateUserId() {
		return lastUpdateUserId;
	}

	public void setLastUpdateUserId(Integer lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

    public Integer getStartDataIndex() {
        return startDataIndex;
    }

    public void setStartDataIndex(Integer startDataIndex) {
        this.startDataIndex = startDataIndex;
    }

}