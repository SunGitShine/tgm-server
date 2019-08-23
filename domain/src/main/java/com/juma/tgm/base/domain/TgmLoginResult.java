package com.juma.tgm.base.domain;

//import com.juma.auth.authority.domain.LoginResult;

//public class TgmLoginResult extends LoginResult {
    public class TgmLoginResult {

    private static final long serialVersionUID = -4073230303984707730L;
    private String versionCheck;
    private String sessionId;
    private String contactPhone;

    public String getVersionCheck() {
        return versionCheck;
    }

    public void setVersionCheck(String versionCheck) {
        this.versionCheck = versionCheck;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

}
