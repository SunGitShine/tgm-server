package com.juma.tgm.project.vo.v2;

import com.juma.tgm.project.domain.v2.ProjectMember;

import java.io.Serializable;

public class ProjectMemberUserVo extends ProjectMember implements Serializable {

    private String userName;

    private String userPhone;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}