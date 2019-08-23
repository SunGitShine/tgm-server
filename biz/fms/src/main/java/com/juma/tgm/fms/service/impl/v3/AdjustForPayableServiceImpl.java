package com.juma.tgm.fms.service.impl.v3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.auth.user.service.UserService;
import com.juma.tgm.fms.dao.v3.AdjustForPayableMapper;
import com.juma.tgm.fms.domain.v3.AdjustForPayable;
import com.juma.tgm.fms.domain.v3.AdjustForPayableExample;
import com.juma.tgm.fms.domain.v3.AdjustForPayableExample.Criteria;
import com.juma.tgm.fms.domain.v3.bo.AdjustForPayableQuery;
import com.juma.tgm.fms.service.v3.AdjustForPayableService;

@Service
public class AdjustForPayableServiceImpl implements AdjustForPayableService {

    @Resource
    private AdjustForPayableMapper adjustForPayableMapper;
    @Resource
    private UserService userService;

    @Override
    public Page<AdjustForPayableQuery> search(PageCondition pageCondition, LoginUser loginUser) {
        List<AdjustForPayableQuery> result = new ArrayList<AdjustForPayableQuery>();
        if (null == pageCondition || null == pageCondition.getFilters()) {
            return new Page<AdjustForPayableQuery>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0, result);
        }

        Object obj = pageCondition.getFilters().get("waybillId");
        if (null == obj || StringUtils.isBlank(obj.toString()) || !StringUtils.isNumeric(obj.toString())) {
            return new Page<AdjustForPayableQuery>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0, result);
        }

        AdjustForPayableExample example = new AdjustForPayableExample();
        Criteria criteria = example.createCriteria();
        criteria.andWaybillIdEqualTo(Integer.valueOf(obj.toString()));

        example.setStartOffSet(pageCondition.getStartOffSet());
        example.setSize(pageCondition.getPageSize());
        example.setOrderByClause(" adjust_time desc ");

        int total = adjustForPayableMapper.countByExample(example);
        List<AdjustForPayable> rows = adjustForPayableMapper.selectByExample(example);
        for (AdjustForPayable a : rows) {
            AdjustForPayableQuery query = new AdjustForPayableQuery();
            if (null != a.getAdjustUserId()) {
                User user = userService.loadUser(a.getAdjustUserId());
                query.setAdjustUserName(user == null ? null : user.getName());
            }
            query.setAdjustForPayable(a);
            result.add(query);
        }

        return new Page<AdjustForPayableQuery>(pageCondition.getPageNo(), pageCondition.getPageSize(), total, result);
    }

    @Override
    public AdjustForPayable findFirstByWaybillId(Integer waybillId) {
        return this.findOneByWaybillId(waybillId, "asc ");
    }

    @Override
    public AdjustForPayable findLastByWaybillId(Integer waybillId) {
        return this.findOneByWaybillId(waybillId, "desc ");

    }

    // 根据运单ID获取一条数据
    private AdjustForPayable findOneByWaybillId(Integer waybillId, String orderSort) {
        if (null == waybillId || StringUtils.isBlank(orderSort)) {
            return null;
        }

        AdjustForPayableExample example = new AdjustForPayableExample();
        Criteria criteria = example.createCriteria();
        criteria.andWaybillIdEqualTo(waybillId);
        example.setStartOffSet(0);
        example.setSize(1);
        example.setOrderByClause(" adjust_id " + orderSort);

        List<AdjustForPayable> list = adjustForPayableMapper.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void insert(AdjustForPayable adjustForPayable, LoginUser loginUser) throws BusinessException {
        this.check(adjustForPayable, loginUser);

        // 如果已经有过调价，则在第一条数据中获取初始价格信息
        AdjustForPayable firstByWaybillId = this.findFirstByWaybillId(adjustForPayable.getWaybillId());
        if (null != firstByWaybillId) {
            adjustForPayable.setPayableWithTax(firstByWaybillId.getPayableWithTax());
            adjustForPayable.setDriverTransportFee(firstByWaybillId.getDriverTransportFee());
            adjustForPayable.setTemporaryTransportFee(firstByWaybillId.getTemporaryTransportFee());
        }

        adjustForPayable.setAdjustTime(new Date());
        adjustForPayable.setAdjustUserId(loginUser.getUserId());
        adjustForPayableMapper.insert(adjustForPayable);
    }

    private void check(AdjustForPayable adjustForPayable, LoginUser loginUser) {
        if (null == adjustForPayable) {
            throw new BusinessException("paramCanNotNull", "errors.paramCanNotNull");
        }

        if (null == loginUser) {
            throw new BusinessException("loginUserCanNotNull", "errors.loginUserCanNotNull");
        }

        if (null == adjustForPayable.getWaybillId()) {
            throw new BusinessException("waybillIdCanNotNull", "adjustForPayable.error.waybillIdCanNotNull");
        }

        if (StringUtils.isBlank(adjustForPayable.getAdjustRemark())) {
            throw new BusinessException("adjustRemarkCanNotNull", "adjustForPayable.error.adjustRemarkCanNotNull");
        }

        if (StringUtils.isBlank(adjustForPayable.getAdjustRemark())) {
            throw new BusinessException("adjustRemarkCanNotNull", "adjustForPayable.error.adjustRemarkCanNotNull");
        }

        if (adjustForPayable.getAdjustRemark().length() > 255) {
            throw new BusinessException("adjustRemarkTooLong", "adjustForPayable.error.adjustRemarkTooLong", 255);
        }

        this.checkDecimal(adjustForPayable.getPayableWithTax(), "初始结算价");
        this.checkDecimal(adjustForPayable.getPayableWithTaxAdjust(), "修改后的结算价");
        this.checkDecimal(adjustForPayable.getDriverTransportFee(), "初始司机搬运费");
        this.checkDecimal(adjustForPayable.getDriverTransportFeeAdjust(), "修改后的司机搬运费");
        this.checkDecimal(adjustForPayable.getTemporaryTransportFee(), "初始小工搬运费");
        this.checkDecimal(adjustForPayable.getTemporaryTransportFeeAdjust(), "修改后的小工搬运费");
    }

    // decimal数据校验
    private void checkDecimal(BigDecimal decimal, String decimalCnName) {
        if (null == decimal) {
            return;
        }

        if (decimal.compareTo(new BigDecimal("9999999999.99")) == 1) {
            throw new BusinessException("decimalTooLong", "adjustForPayable.error.decimalTooLong", decimalCnName);
        }
    }
}
