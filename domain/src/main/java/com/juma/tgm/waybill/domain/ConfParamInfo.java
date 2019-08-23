package com.juma.tgm.waybill.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.juma.conf.domain.ConfParam;
import com.juma.conf.domain.ConfParamOption;

/**
 * 星级评价信息
 * 
 * @author weilibin
 *
 */

public class ConfParamInfo implements Serializable {

    private static final long serialVersionUID = -3660007839039485660L;
    private ConfParam conf;
    private List<ConfParamOption> optionList = new ArrayList<ConfParamOption>();

    public ConfParam getConf() {
        return conf;
    }

    public void setConf(ConfParam conf) {
        this.conf = conf;
    }

    public List<ConfParamOption> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<ConfParamOption> optionList) {
        this.optionList = optionList;
    }

}
