package com.juma.tgm.importantNotice.domain;

import java.io.Serializable;
import java.util.List;

public class ImportantNoticeBo implements Serializable {

    private static final long serialVersionUID = 2631520150787408882L;
    private ImportantNotice importantNotice;
    private List<ImportantNotice> noticeList;
    private boolean hasImportNotice = false;
    private String effectiveDate;
    private String expiryDate;

    public ImportantNotice getImportantNotice() {
        return importantNotice;
    }

    public void setImportantNotice(ImportantNotice importantNotice) {
        this.importantNotice = importantNotice;
    }

    public List<ImportantNotice> getNoticeList() {
        return noticeList;
    }

    public void setNoticeList(List<ImportantNotice> noticeList) {
        this.noticeList = noticeList;
    }

    public boolean isHasImportNotice() {
        return hasImportNotice;
    }

    public void setHasImportNotice(boolean hasImportNotice) {
        this.hasImportNotice = hasImportNotice;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
