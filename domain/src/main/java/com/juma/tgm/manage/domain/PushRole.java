package com.juma.tgm.manage.domain;

import com.juma.tgm.base.domain.BaseDomain;

/**
 * push_role - PUSH角色
 * 
 * @author 2016-05-09
 * @version 1.0
 */
public class PushRole extends BaseDomain {

    private static final long serialVersionUID = -5280120682153378544L;
    private Integer pushRoleId;
    private Integer messageConfId;
    private Integer roleId;

    public Integer getPushRoleId() {
        return pushRoleId;
    }

    public void setPushRoleId(Integer pushRoleId) {
        this.pushRoleId = pushRoleId;
    }

    public Integer getMessageConfId() {
        return messageConfId;
    }

    public void setMessageConfId(Integer messageConfId) {
        this.messageConfId = messageConfId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}