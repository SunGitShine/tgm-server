package com.juma.tgm.fms.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * reconciliation - 对帐主表
 * 
 * @author  2017-10-16
 * @version 1.0 
 */
public class Reconciliation implements Serializable {

    private static final long serialVersionUID = -8955947585395477725L;
    private Integer reconciliationId;
	private String reconciliationNo;
	private Integer reconciliationStatus;
	private Date submitTime;
	private Integer submitter;
	private BigDecimal totalFreight;
	private String processInstanceId;
	private Integer createUserId;
	private Date createTime;
	private Integer tenantId;
	private String tenantCode;
	
	
	private List<Integer> reconciliationIds = new ArrayList<Integer>();

	/**
	 * 对账单-对账状态
	 */
	public enum ReconciliationStatus {
	    
	    Append(0,"未提交"),SUBMIT(1,"审核中"),REJECT(2,"被驳回"),AGREE(3,"已通过");
	    
	    private int code;
	    
	    private String msg;
	    
	    private ReconciliationStatus(int code,String msg) {
	        this.code = code;
	        this.msg = msg;
	    }
        public int getCode() {
            return code;
        }
        public void setCode(int code) {
            this.code = code;
        }
        public String getMsg() {
            return msg;
        }
        public void setMsg(String msg) {
            this.msg = msg;
        }
	    
	}
	
	public Integer getReconciliationId() {
		return reconciliationId;
	}

	public void setReconciliationId(Integer reconciliationId) {
		this.reconciliationId = reconciliationId;
	}

	public String getReconciliationNo() {
		return reconciliationNo;
	}

	public void setReconciliationNo(String reconciliationNo) {
		this.reconciliationNo = reconciliationNo;
	}

	public Integer getReconciliationStatus() {
		return reconciliationStatus;
	}

	public void setReconciliationStatus(Integer reconciliationStatus) {
		this.reconciliationStatus = reconciliationStatus;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
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

    public Integer getSubmitter() {
        return submitter;
    }

    public void setSubmitter(Integer submitter) {
        this.submitter = submitter;
    }

    public BigDecimal getTotalFreight() {
        return totalFreight;
    }

    public void setTotalFreight(BigDecimal totalFreight) {
        this.totalFreight = totalFreight;
    }

    public List<Integer> getReconciliationIds() {
        return reconciliationIds;
    }

    public void setReconciliationIds(List<Integer> reconciliationIds) {
        this.reconciliationIds = reconciliationIds;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}