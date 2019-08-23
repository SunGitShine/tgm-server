package com.juma.tgm.waybill.service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybill.domain.WaybillBo;

/**
 * 
 * @Description: 运单异常接口
 * @author weilbiin
 * @date 2016年5月19日 下午7:27:34
 * @version V1.0
 */

public interface WaybillExceptionService {

    /**
     * 
     * @Title: searchDetails @Description: 分页获取运单异常信息 @param
     * pageCondition @return Page<WaybillExceptionBo> @throws
     */
    Page<WaybillBo> searchDetails(PageCondition pageCondition, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 
     * 异常原因填写
     * 
     * @author Libin.Wei
     * @Date 2017年11月20日 下午1:41:09
     * @param waybillId
     * @param reason
     * @param loginUser
     * @throws BusinessException
     */
    void updateReason(int waybillId, String reason, LoginUser loginUser) throws BusinessException;
}
