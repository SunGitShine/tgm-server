package com.juma.tgm.configure.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.monitor.service.FenceService;
import com.juma.monitor.truck.domain.Fence;
import com.juma.tgm.base.domain.Paging;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.common.Constants;
import com.juma.tgm.configure.dao.ServiceConfItemMapper;
import com.juma.tgm.configure.domain.ServiceConf;
import com.juma.tgm.configure.domain.ServiceConfItem;
import com.juma.tgm.configure.domain.ServiceConfItemExample;
import com.juma.tgm.configure.service.ServiceConfItemService;
import com.juma.tgm.configure.service.ServiceConfService;

@Service
public class ServiceConfItemServiceImpl implements ServiceConfItemService {

    private final Logger log = LoggerFactory.getLogger(ServiceConfItemServiceImpl.class);
    @Resource
    private ServiceConfItemMapper serviceConfItemMapper;
    @Resource
    private ServiceConfService serviceConfService;
    @Resource
    private FenceService fenceService;

    @Override
    public Page<ServiceConfItem> search(PageCondition pageCondition, LoginUser loginUser) {
        Map<String, Object> filters = pageCondition.getFilters();
        if (null == filters || null == filters.get("serviceConfId") || null == filters.get("fenceType")) {
            return new Page<ServiceConfItem>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0,
                    new ArrayList<ServiceConfItem>());
        }

        Integer serviceConfId = BaseUtil.strToNum(filters.get("serviceConfId").toString());
        Integer fenceType = BaseUtil.strToNum(filters.get("fenceType").toString());

        ServiceConfItemExample example = new ServiceConfItemExample();
        Paging paging = new Paging(pageCondition, "create_time", null);
        example.setStartOffSet(paging.getStartOffSet());
        example.setSize(paging.getPageSize());
        example.setOrderByClause(paging.getOrder());

        example.createCriteria().andServiceConfIdEqualTo(serviceConfId).andFenceTypeEqualTo(fenceType);

        long total = serviceConfItemMapper.countByExample(example);
        List<ServiceConfItem> list = serviceConfItemMapper.selectByExample(example);

        return new Page<ServiceConfItem>(pageCondition.getPageNo(), pageCondition.getPageSize(), (int) total, list);
    }

    @Override
    public ServiceConfItem findByServiceConfAndFence(Integer serviceConfId, Integer fenceId) {
        if (null == serviceConfId || null == fenceId) {
            return null;
        }

        ServiceConfItemExample example = new ServiceConfItemExample();
        example.createCriteria().andServiceConfIdEqualTo(serviceConfId).andFenceIdEqualTo(fenceId);

        List<ServiceConfItem> list = serviceConfItemMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ServiceConfItem> listByServiceConf(Integer serviceConfId, Integer fenceType) {
        if (null == serviceConfId) {
            return new ArrayList<ServiceConfItem>();
        }

        ServiceConfItemExample example = new ServiceConfItemExample();
        example.createCriteria().andServiceConfIdEqualTo(serviceConfId).andFenceTypeEqualTo(fenceType);

        return serviceConfItemMapper.selectByExample(example);
    }

    @Override
    public void insert(Integer fenceId, Integer serviceConfId, LoginUser loginUser) throws BusinessException {
        if (null == fenceId || null == serviceConfId) {
            return;
        }

        // 存在则不再添加
        ServiceConfItem confItem = findByServiceConfAndFence(serviceConfId, fenceId);
        if (null != confItem) {
            return;
        }

        Fence fence = fenceService.queryFence(fenceId);
        if (null == fence) {
            return;
        }

        ServiceConfItem item = new ServiceConfItem();
        item.setServiceConfId(serviceConfId);
        item.setFenceId(fenceId);
        item.setFenceType(fence.getFenceType());
        item.setFenceName(fence.getFenceName());
        item.setFenceAddress(fence.getAddr());
        item.setCreateUserId(loginUser == null ? null : loginUser.getUserId());

        serviceConfItemMapper.insertSelective(item);
    }

    @Override
    public void update(Integer serviceConfItemId, LoginUser loginUser) throws BusinessException {
        if (null == serviceConfItemId) {
            return;
        }

        ServiceConfItem item = serviceConfItemMapper.selectByPrimaryKey(serviceConfItemId);
        if (null == item) {
            return;
        }

        Fence fence = fenceService.queryFence(item.getFenceId());
        if (null == fence) {
            return;
        }

        item.setFenceType(fence.getFenceType());
        item.setFenceType(fence.getFenceType());
        item.setFenceName(fence.getFenceName());
        item.setFenceAddress(fence.getAddr());
        item.setLastUpdateUserId(loginUser == null ? null : loginUser.getUserId());

        serviceConfItemMapper.updateByPrimaryKeySelective(item);
    }

    @Override
    public void delete(Integer serviceConfItemId, LoginUser loginUser) throws BusinessException {
        ServiceConfItem serviceConfItem = serviceConfItemMapper.selectByPrimaryKey(serviceConfItemId);
        if (null == serviceConfItem) {
            return;
        }

        serviceConfItemMapper.deleteByPrimaryKey(serviceConfItemId);

        // 调用在途监控的围栏删除功能
        fenceService.deleteFence(serviceConfItem.getFenceId());

        // 判断删除之后还有无电子围栏
        List<ServiceConfItem> list = listByServiceConf(serviceConfItem.getServiceConfId(), serviceConfItem.getFenceType());
        if (!list.isEmpty()) {
            return;
        }

        ServiceConf serviceConf = new ServiceConf();
        serviceConf.setServiceConfId(serviceConfItem.getServiceConfId());
        if (serviceConfItem.getFenceType().equals(Constants.OPEN_SERVICE_FENCE_TYPE)) {
            serviceConf.setIsOpenServiceRegion(false);
        } else if (serviceConfItem.getFenceType().equals(Constants.OPEN_ENTRY_FENCE_TYPE)) {
            serviceConf.setIsOpenEntryRegion(false);
        } else if (serviceConfItem.getFenceType().equals(Constants.FENCE_TYPE_AT_FORBIDDEN_AREA)) {
            serviceConf.setIsProhibitionZone(false);
        }
        serviceConfService.update(serviceConf, loginUser);
    }

    @Override
    public void doCallBack(Integer serviceConfId, Integer fenceId, LoginUser loginUser) throws BusinessException {
        log.info("电子围栏回调成功serviceConfId:{}, fenceId:{}", serviceConfId, fenceId);
        if (null == serviceConfId || null == fenceId) {
            return;
        }

        Fence fence = fenceService.queryFence(fenceId);
        if (null == fence) {
            return;
        }

        ServiceConfItem serviceConfItem = findByServiceConfAndFence(serviceConfId, fenceId);
        if (null == serviceConfItem) {
            log.info("电子围栏回调添加");
            insert(fenceId, serviceConfId, loginUser);
        } else {
            log.info("电子围栏回调修改");
            update(serviceConfItem.getServiceConfItemId(), loginUser);
        }

        // 更新主表开通围栏的状态
        ServiceConf serviceConf = new ServiceConf();
        serviceConf.setServiceConfId(serviceConfId);
        if (fence.getFenceType().equals(Constants.OPEN_SERVICE_FENCE_TYPE)) {
            serviceConf.setIsOpenServiceRegion(true);
        } else if (fence.getFenceType().equals(Constants.OPEN_ENTRY_FENCE_TYPE)) {
            serviceConf.setIsOpenEntryRegion(true);
        } else if (fence.getFenceType().equals(Constants.FENCE_TYPE_AT_FORBIDDEN_AREA)) {
            serviceConf.setIsProhibitionZone(true);
        }
        serviceConfService.update(serviceConf, loginUser);
    }
}
