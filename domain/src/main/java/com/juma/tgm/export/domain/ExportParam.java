package com.juma.tgm.export.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ExportParam.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年6月4日 下午2:39:12
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class ExportParam implements Serializable {

    private static final long serialVersionUID = -4964960635595720278L;
    private Map<String, Object> filters;
    private List<ExportDynamicParameter> exportDynamicParameters = new ArrayList<ExportDynamicParameter>();

    public Map<String, Object> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, Object> filters) {
        this.filters = filters;
    }

    public List<ExportDynamicParameter> getExportDynamicParameters() {
        return exportDynamicParameters;
    }

    public void setExportDynamicParameters(List<ExportDynamicParameter> exportDynamicParameters) {
        this.exportDynamicParameters = exportDynamicParameters;
    }

}
