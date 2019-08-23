package com.juma.tgm.sop.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.sop.dao.ElementMapper;
import com.juma.tgm.sop.dao.SopMapper;
import com.juma.tgm.sop.dao.StepMapper;
import com.juma.tgm.sop.domain.Element;
import com.juma.tgm.sop.domain.ElementExample;
import com.juma.tgm.sop.domain.Sop;
import com.juma.tgm.sop.domain.SopExample;
import com.juma.tgm.sop.domain.Step;
import com.juma.tgm.sop.domain.StepExample;
import com.juma.tgm.sop.service.SopService;

@Service
public class SopServiceImpl implements SopService {

    @Resource
    private ElementMapper elementMapper;

    @Resource
    private SopMapper sopMapper;

    @Resource
    private StepMapper stepMapper;

    private Element findElementByKey(String elementKey) {
        ElementExample example = new ElementExample();
        example.createCriteria().andElementKeyEqualTo(elementKey).andIsDeleteEqualTo(false);
        List<Element> rows = elementMapper.selectByExample(example);
        return rows.isEmpty() ? null : rows.get(0);
    }

    @Override
    public void updateElement(Element element, LoginUser loginUser) throws BusinessException {
        if (element == null || element.getElementId() == null || StringUtils.isBlank(element.getElementKey())
                || StringUtils.isBlank(element.getElementName()) || StringUtils.isBlank(element.getElementType())) {
            throw new BusinessException("errors.paramCanNotNull", "errors.paramCanNotNull");
        }
        Element ele = findElementByKey(element.getElementKey());
        if (ele != null && !ele.getElementId().equals(element.getElementId())) {
            throw new BusinessException("errors.exsitErr", "errors.exsitErr", "元素" + element.getElementKey());
        }
        element.setLastUpdateTime(new Date());
        element.setLastUpdateUserId(loginUser.getUserId());
        elementMapper.updateByPrimaryKeySelective(element);
    }
    
    @Override
    public void addElement(Element element, LoginUser loginUser) {
        if (element == null || StringUtils.isBlank(element.getElementKey())
                || StringUtils.isBlank(element.getElementName()) || StringUtils.isBlank(element.getElementType())) {
            throw new BusinessException("errors.paramCanNotNull", "errors.paramCanNotNull");
        }
        Element ele = findElementByKey(element.getElementKey());
        if (ele != null) {
            throw new BusinessException("errors.exsitErr", "errors.exsitErr", "元素" + element.getElementKey());
        }
        element.setIsDelete(false);
        element.setCreateTime(new Date());
        element.setCreateUserId(loginUser.getUserId());
        elementMapper.insert(element);
    }

    @Override
    public void deleteElement(Integer elementId, LoginUser loginUser) {
        Element ele = elementMapper.selectByPrimaryKey(elementId);
        if(ele == null) return;
        ele.setIsDelete(true);
        ele.setLastUpdateUserId(loginUser.getUserId());
        ele.setLastUpdateTime(new Date());
        elementMapper.updateByPrimaryKey(ele);
    }

    @Override
    public void saveSop(Sop sop, LoginUser loginUser) {
        if (sop == null || StringUtils.isBlank(sop.getSopJson())) {
            throw new BusinessException("errors.paramCanNotNull", "errors.paramCanNotNull");
        }
        String md5 = DigestUtils.md5Hex(loginUser.getTenantId() + sop.getSopJson());
        Sop s = findNewestVersionSopByTenantId(loginUser.getTenantId());
        if (s == null) {
            sop.setSopId(null);
            sop.setIsDelete(false);
            sop.setTenantId(loginUser.getTenantId());
            sop.setTenantCode(loginUser.getTenantCode());
            sop.setCreateTime(new Date());
            sop.setCreateUserId(loginUser.getUserId());
            sop.setMd5(md5);
            sopMapper.insert(sop);
        } else {
            if (s.getMd5().equals(md5)) {
                s.setSopId(s.getSopId());
                s.setLastUpdateTime(new Date());
                s.setLastUpdateUserId(loginUser.getUserId());
                sopMapper.updateByPrimaryKey(s);
            } else {
                sop.setSopId(null);
                sop.setIsDelete(false);
                sop.setTenantId(loginUser.getTenantId());
                sop.setTenantCode(loginUser.getTenantCode());
                sop.setCreateTime(new Date());
                sop.setCreateUserId(loginUser.getUserId());
                sop.setMd5(md5);
                sopMapper.insert(sop);
            }
        }
    }

    @Override
    public Page<Element> searchElements(PageCondition cond, LoginUser loginUser) {
        ElementExample example = new ElementExample();
        example.setStartOffSet((cond.getPageNo() - 1) * cond.getPageSize());
        example.setSize(cond.getPageSize());
        example.setOrderByClause(" create_time desc ");
        ElementExample.Criteria criteria = example.createCriteria().andIsDeleteEqualTo(false);
        if (cond.getFilters().containsKey("stepId")) {
            criteria.andStepIdEqualTo(Integer.valueOf(cond.getFilters().get("stepId").toString()));
        }
        long total = elementMapper.countByExample(example);
        List<Element> rows = elementMapper.selectByExample(example);
        return new Page<Element>(cond.getPageNo(), cond.getPageSize(), new Long(total).intValue(), rows);
    }

    @Override
    public Page<Sop> searchSops(PageCondition cond, LoginUser loginUser) {
        SopExample example = new SopExample();
        example.setStartOffSet((cond.getPageNo() - 1) * cond.getPageSize());
        example.setSize(cond.getPageSize());
        example.setOrderByClause(" create_time desc ");
        SopExample.Criteria  criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(loginUser.getTenantId()).andIsDeleteEqualTo(false);
        
        if (cond.getFilters().containsKey("sopId")) {
            criteria.andSopIdEqualTo(Integer.valueOf(cond.getFilters().get("sopId").toString()));
        }
        long total = sopMapper.countByExample(example);
        List<Sop> rows = sopMapper.selectByExample(example);
        return new Page<Sop>(cond.getPageNo(), cond.getPageSize(), Integer.parseInt(total + ""), rows);
    }


    @Override
    public Sop getSop(Integer sopId) {
        return sopMapper.selectByPrimaryKey(sopId);
    }

    @Override
    public List<Element> findElementByStepId(Integer stepId) {
        ElementExample example = new ElementExample();
        example.setOrderByClause(" create_time desc ");
        example.createCriteria().andStepIdEqualTo(stepId).andIsDeleteEqualTo(false);
        return elementMapper.selectByExample(example);
    }

    @Override
    public List<Step> findAllStep() {
        StepExample example = new StepExample();
        example.setOrderByClause(" create_time desc ");
        example.createCriteria().andIsDeleteEqualTo(false);
        return stepMapper.selectByExample(example);
    }

    @Override
    public Step getStep(Integer stepId) {
        return stepMapper.selectByPrimaryKey(stepId);
    }

    @Override
    public Element getElement(Integer elementId) {
        return elementMapper.selectByPrimaryKey(elementId);
    }
    
    public static void main(String[] args) {
        
        String str = "[{'elements':[{'display':true,'elementId':2,'elementKey':'planDeliveryTime2','elementName':'用车时间2','elementType':'text','stepId':1},{'editable':true,'elementId':1,'elementKey':'planDeliveryTime','elementName':'用车时间','elementType':'text','stepId':1}],'stepId':1,'stepKey':'a','stepName':'下单'}]";
        Map<String, String> map = JSON.parseObject(str, new TypeReference<HashMap<String, String>>() {});
        System.out.println(map);
    }

    @Override
    public Sop findNewestVersionSopByTenantId(Integer tenantId) {
        SopExample example = new SopExample();
        example.setSize(1);
        example.setStartOffSet(0);
        example.setOrderByClause(" create_time desc ");
        example.createCriteria().andTenantIdEqualTo(tenantId).andIsDeleteEqualTo(false);
        List<Sop> rows = sopMapper.selectByExample(example);
        return rows.isEmpty() ? null : rows.get(0);
    }

}
