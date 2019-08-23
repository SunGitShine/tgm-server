package com.juma.tgm.sop.service;

import java.util.List;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.sop.domain.Element;
import com.juma.tgm.sop.domain.Sop;
import com.juma.tgm.sop.domain.Step;

public interface SopService {
    
    Step getStep(Integer stepId);

    void addElement(Element element,LoginUser loginUser) throws BusinessException;
    
    void updateElement(Element element,LoginUser loginUser) throws BusinessException;
    
    void deleteElement(Integer elementId,LoginUser loginUser) throws BusinessException;
    
    Element getElement(Integer elementId);
    
    void saveSop(Sop sop,LoginUser loginUser) throws BusinessException;
    
    Page<Element> searchElements(PageCondition cond,LoginUser loginUser);
    
    Page<Sop> searchSops(PageCondition cond,LoginUser loginUser);
    
    Sop getSop(Integer sopId);
    
    List<Step> findAllStep();
    
    List<Element> findElementByStepId(Integer stepId);
    
    Sop findNewestVersionSopByTenantId(Integer tenantId);
    
}
