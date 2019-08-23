package com.juma.tgm.base.domain;

import java.io.Serializable;

import com.giants.common.tools.PageCondition;

/**
 * 查询条件
 * 
 * @author weilibin
 *
 */

public class PageConditionDomain implements Serializable {

    private static final long serialVersionUID = 2867574758955873544L;
    private PageCondition pageCondition;

    private boolean flag;

    public PageCondition getPageCondition() {
        return pageCondition;
    }

    public void setPageCondition(PageCondition pageCondition) {
        this.pageCondition = pageCondition;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
