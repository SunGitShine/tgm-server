package com.juma.tgm.operateLog.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.auth.user.service.UserService;
import com.juma.tgm.base.domain.Paging;
import com.juma.tgm.operateLog.dao.OperateLogMapper;
import com.juma.tgm.operateLog.domain.OperateLog;
import com.juma.tgm.operateLog.domain.OperateLogExample;
import com.juma.tgm.operateLog.domain.OperateLogExample.Criteria;
import com.juma.tgm.operateLog.vo.OperateLogQuery;

@Service
public class OperateLogServiceImpl implements OperateLogService {

    @Resource
    private OperateLogMapper operateLogMapper;
    @Resource
    private UserService userService;

    @Override
    public Page<OperateLogQuery> search(PageCondition pageCondition, LoginUser loginUser) throws BusinessException {
        List<OperateLogQuery> result = new ArrayList<OperateLogQuery>();
        if (null == pageCondition || null == pageCondition.getFilters()) {
            return new Page<OperateLogQuery>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0, result);
        }

        Map<String, Object> filters = pageCondition.getFilters();
        if (null == filters.get("logSign") || !StringUtils.isNumeric(filters.get("logSign").toString())) {
            return new Page<OperateLogQuery>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0, result);
        }
        if (null == filters.get("relationTableId")
                || !StringUtils.isNumeric(filters.get("relationTableId").toString())) {
            return new Page<OperateLogQuery>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0, result);
        }

        OperateLogExample example = this.buildQueryExample(pageCondition);

        int total = operateLogMapper.countByExample(example);
        List<OperateLog> rows = operateLogMapper.selectByExample(example);
        for (OperateLog l : rows) {
            OperateLogQuery q = new OperateLogQuery();
            q.setOperateLog(l);
            User user = userService.loadUser(l.getCreateUserId());
            if (null != user) {
                q.setOperateUserName(user.getName());
            }

            result.add(q);
        }

        return new Page<OperateLogQuery>(pageCondition.getPageNo(), pageCondition.getPageSize(), total, result);
    }

    // 组装查询条件
    private OperateLogExample buildQueryExample(PageCondition pageCondition) {
        OperateLogExample example = new OperateLogExample();
        Map<String, Object> filters = pageCondition.getFilters();
        Criteria criteria = example.createCriteria();
        criteria.andLogSignEqualTo(Byte.valueOf(filters.get("logSign").toString()));
        criteria.andRelationTableIdEqualTo(Integer.valueOf(filters.get("relationTableId").toString()));
        if (null != filters.get("operateType") && StringUtils.isNumeric(filters.get("operateType").toString())) {
            criteria.andOperateTypeEqualTo(Byte.valueOf(filters.get("operateType").toString()));
        }

        if (null != filters.get("operateApplicatoin")
                && StringUtils.isNumeric(filters.get("operateApplicatoin").toString())) {
            criteria.andOperateApplicatoinEqualTo(Byte.valueOf(filters.get("operateApplicatoin").toString()));
        }

        if (null != filters.get("createUserId") && StringUtils.isNumeric(filters.get("createUserId").toString())) {
            criteria.andCreateUserIdEqualTo(Integer.valueOf(filters.get("createUserId").toString()));
        }

        Paging page = new Paging(pageCondition, " create_time ", null);
        example.setStartOffSet(page.getStartOffSet());
        example.setSize(page.getPageSize());
        if (null != filters.get("orderByClause")) {
            example.setOrderByClause(filters.get("orderByClause").toString());
        }
        return example;
    }

    @Override
    public OperateLogQuery getOperateLogQuery(Integer operateLogId) throws BusinessException {
        if (null == operateLogId) {
            return null;
        }

        OperateLog operateLog = operateLogMapper.selectByPrimaryKey(operateLogId);
        if (null == operateLog) {
            return null;
        }

        OperateLogQuery q = new OperateLogQuery();
        q.setOperateLog(operateLog);
        User user = userService.loadUser(operateLog.getCreateUserId());
        if (null != user) {
            q.setOperateUserName(user.getName());
        }

        return q;
    }

    @Override
    public void insert(OperateLog operateLog, LoginUser loginUser) {
        if (null == operateLog || null == loginUser) {
            return;
        }

        if (null == operateLog.getLogSign()) {
            return;
        }

        if (null == operateLog.getRelationTableId()) {
            return;
        }

        if (null == operateLog.getOperateType()) {
            return;
        }

        if (null == operateLog.getOperateApplicatoin()) {
            return;
        }

        operateLog.setCreateUserId(loginUser.getUserId());
        operateLog.setCreateTime(new Date());

        operateLogMapper.insert(operateLog);
    }

    @Override
    public void insertByDubboAsync(OperateLog operateLog, LoginUser loginUser) throws BusinessException {
        this.insert(operateLog, loginUser);
    }

}
