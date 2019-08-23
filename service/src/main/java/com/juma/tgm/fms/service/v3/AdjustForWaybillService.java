package com.juma.tgm.fms.service.v3;

import com.giants.common.exception.BusinessException;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.fms.domain.v3.AdjustForWaybillTemp;
import com.juma.tgm.fms.domain.v3.vo.*;
import org.mybatis.generator.my.page.Page;

import java.util.List;

/**
 * 临时表操作+调整单-运单相关数据查询
 * 功能 : 
 * CRUD
 * @author : Bruce(刘正航) 17:42 2019-05-10
 */
public interface AdjustForWaybillService {

    /**分页查询临时表数据**/
    Page<AdjustForWaybillVO> findPageByFilter(QueryCond<AdjustForWaybillTempFilter> queryCond, LoginEmployee loginUser) throws BusinessException;

    /**查询临时表列表数据**/
    List<AdjustForWaybillTemp> findByFilter(QueryCond<AdjustForWaybillTempVO> filter, LoginEmployee loginUser) throws BusinessException;

    /**临时表列表查询**/
    List<AdjustForWaybillTemp> listTempInfos(AdjustForMasterAddVO vo, LoginEmployee loginUser) throws BusinessException;

    /**添加临时表数据**/
    void addTemp(AdjustForWaybillTemp temp, LoginEmployee loginUser) throws BusinessException;

    /**批量添加临时表数据**/
    void addTempBatch(Integer adjustId, List<AdjustForWaybillTemp> list, LoginEmployee loginUser) throws BusinessException;

    /**批量更新临时表数据**/
    void updateTempBatch(Integer adjustId, List<AdjustForWaybillTemp> list, LoginEmployee loginUser) throws BusinessException;

    /**删除临时表数据**/
    void deleteTempById(Integer adjustTempId, LoginEmployee loginUser) throws BusinessException;

    /**删除临时表数据**/
    void deleteTempByWaybillNo(Integer adjustId, String waybillNo, LoginEmployee loginUser) throws BusinessException;

    /**根据调整单主键删除临时表数据**/
    void deleteByAdjustId(Integer adjustId, LoginEmployee loginEmployee) throws BusinessException;

    /**从调整单详情,覆盖信息到临时表**/
    void coverTempByUser(Integer adjustId, LoginEmployee loginUser) throws BusinessException;

    /**校验调整单创建参数**/
    void validWaybillTempParams(AdjustForMasterAddVO vo, final LoginEmployee loginUser);

    /**校验提交的调整单/运单数据**/
    AdjustItemValidHolder validWaybillForAdjust(AdjustAttachVO vo, List<AdjustForWaybillTemp> list, AdjustItemValidHolder holder, LoginEmployee loginUser) throws BusinessException;

    /**校验调整单-运单数据**/
    void validWaybillDatas(AdjustForMasterAddVO vo, AdjustItemValidHolder holder, List<AdjustForWaybillTemp> temps, LoginEmployee loginUser) throws BusinessException;

    /**添加调整单 查询准备数据**/
    void validWaybillTempInfo(AdjustForMasterAddVO vo, LoginEmployee loginUser) throws BusinessException;
}
