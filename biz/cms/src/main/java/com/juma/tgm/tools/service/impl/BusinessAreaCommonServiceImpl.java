package com.juma.tgm.tools.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.juma.auth.conf.domain.BusinessArea;
import com.juma.auth.conf.domain.BusinessAreaNode;
import com.juma.auth.conf.service.BusinessAreaService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.tools.service.BusinessAreaCommonService;

@Service
public class BusinessAreaCommonServiceImpl implements BusinessAreaCommonService {

    private static final Logger log = LoggerFactory.getLogger(BusinessAreaCommonServiceImpl.class);

    @Resource
    private BusinessAreaService businessAreaService;

    @Override
    public String loadAllLinkNodeAreaName(String areaCode, LoginUser loginUser) {
        if (StringUtils.isBlank(areaCode) || null == loginUser || null == loginUser.getTenantId()) {
            return null;
        }

        BusinessArea businessArea = businessAreaService.loadBusinessArea(areaCode, loginUser);
        if (null == businessArea) {
            return null;
        }

        if (null == businessArea.getParentBusinessAreaId()) {
            return businessArea.getAreaName();
        }

        return loadAreaName(businessArea.getParentBusinessAreaId(), businessArea.getAreaName(), loginUser);
    }

    // 递归获取
    private String loadAreaName(Integer businessAreaId, String areaName, LoginUser loginUser) {
        BusinessArea businessArea = businessAreaService.loadBusinessArea(businessAreaId, loginUser);

        if (null == businessArea) {
            return areaName;
        }

        areaName = businessArea.getAreaName() + "-" + areaName;
        if (null == businessArea.getParentBusinessAreaId()) {
            return areaName;
        }
        return loadAreaName(businessArea.getParentBusinessAreaId(), areaName, loginUser);
    }

    @Override
    public String loadPreAndSelfAreaName(String areaCode, LoginUser loginUser) {
        if (StringUtils.isBlank(areaCode) || null == loginUser || null == loginUser.getTenantId()) {
            return null;
        }

        BusinessArea businessArea = businessAreaService.loadBusinessArea(areaCode, loginUser);
        if (null == businessArea) {
            return null;
        }

        if (null == businessArea.getParentBusinessAreaId()) {
            return businessArea.getAreaName();
        }

        BusinessArea parentBusinessArea = businessAreaService.loadBusinessArea(businessArea.getParentBusinessAreaId(),
                loginUser);
        if (null == parentBusinessArea) {
            return businessArea.getAreaName();
        }

        return parentBusinessArea.getAreaName() + "-" + businessArea.getAreaName();
    }

    @Override
    public String loadLogicAndSelfAreaName(String areaCode, LoginUser loginUser) {
        try {
            if (StringUtils.isBlank(areaCode) || null == loginUser || null == loginUser.getTenantId()) {
                return null;
            }

            BusinessArea businessArea = businessAreaService.loadBusinessArea(areaCode, loginUser);
            if (null == businessArea) {
                return null;
            }

            BusinessArea logicBusinessArea = businessAreaService.loadLogicBusinessArea(areaCode, loginUser);
            if (null == logicBusinessArea || logicBusinessArea.equals(businessArea)) {
                return businessArea.getAreaName();
            }

            return logicBusinessArea.getAreaName() + "-" + businessArea.getAreaName();
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public String loadAreaName(String areaCode, LoginUser loginUser) {
        if (StringUtils.isBlank(areaCode) || null == loginUser || null == loginUser.getTenantId()) {
            return null;
        }

        BusinessArea businessArea = businessAreaService.loadBusinessArea(areaCode, loginUser);
        if (null == businessArea) {
            return null;
        }

        return businessArea.getAreaName();
    }

    @Override
    public List<String> listTopLevelAreaCode(LoginUser loginUser) {
        List<String> result = new ArrayList<String>();
        try {
            List<BusinessAreaNode> areaTree = businessAreaService.findBusinessAreaTree(loginUser.getTenantId());
            for (BusinessAreaNode businessAreaNode : areaTree) {
                result.add(businessAreaNode.getAreaCode());
            }
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }

        return result;
    }

    @Override
    public List<BusinessArea> listChildBusinessArea(Integer parentBusinessAreaId, LoginUser loginUser) {
        if (null != parentBusinessAreaId && parentBusinessAreaId.equals(0)) {
            parentBusinessAreaId = null;
        }
        return businessAreaService.findChildBusinessArea(parentBusinessAreaId, loginUser);
    }
}
