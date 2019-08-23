package com.juma.tgm.base.domain;

import java.io.Serializable;

public class VersionCheck implements Serializable {

    private static final long serialVersionUID = -7033848739445035766L;
    private String versionCheck;

    public String getVersionCheck() {
        return versionCheck;
    }

    public void setVersionCheck(String versionCheck) {
        this.versionCheck = versionCheck;
    }

}
