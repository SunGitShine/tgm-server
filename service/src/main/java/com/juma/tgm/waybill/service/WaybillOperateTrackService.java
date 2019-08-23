package com.juma.tgm.waybill.service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.waybill.domain.WaybillOperateTrack;
import com.juma.tgm.waybill.domain.WaybillOperateTrackNotRequieParam;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateApplication;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateType;
import com.juma.tgm.waybill.vo.WaybillOperateTrackFilter;
import com.juma.tgm.waybill.vo.WaybillOperateTrackQuery;
import java.util.List;

/**
 * 运单操作轨迹
 *
 * @author weilibin
 */

public interface WaybillOperateTrackService {

    /**
     * 分页列表
     *
     * @param queryCond
     *
     * @return Page<WaybillOperateTrack>
     */
    Page<WaybillOperateTrackQuery> search(QueryCond<WaybillOperateTrackFilter> queryCond);

    /**
     * 根据运单ID、操作类型列表、不包含的操作应用获取获取
     *
     * @param waybillId                 运单ID
     * @param operateTypeList           操作类型列表；为空时，获取所有操作类型的轨迹
     * @param notIncludeapplicationList 不包含的操作应用列表；为空时，获取所有操作应用的轨迹
     *
     * @return
     *
     * @author Libin.Wei
     * @Date 2017年6月16日 上午11:27:12
     */
    List<WaybillOperateTrackQuery> listByWaybillIdAndTypes(Integer waybillId, List<Integer> operateTypeList,
        List<Integer> notIncludeapplicationList);

    /**
     * 添加轨迹
     *
     * @param track     轨迹信息
     * @param loginUser 当前登录人
     */
    void insert(WaybillOperateTrack track, LoginUser loginUser) throws BusinessException;

    /**
     * 添加轨迹，通过MQ完善详情信息
     *
     * @param waybillId      运单ID
     * @param operateType    操作类型
     * @param operatePort    操作人
     * @param notRequieParam 非必填参数
     * @param loginUser      当前登录人Id
     */
    void insert(Integer waybillId, OperateType operateType, OperateApplication operatePort,
        WaybillOperateTrackNotRequieParam notRequieParam, LoginUser loginUser) throws BusinessException;

    /**
     * MQ完善信息
     *
     * @param track
     */
    void updateByMq(WaybillOperateTrack track);

    /**
     * 通过运单id和操作类型获取操作轨迹
     *
     * @param waybillId   运单id
     * @param operateType 操作类型
     *
     * @return
     */
    List<WaybillOperateTrack> ListByWayBillIdAndOperateType(int waybillId, int operateType);

    /**
     * 过运单id获取操作轨迹
     *
     * @param waybillId
     *
     * @return
     */
    List<WaybillOperateTrack> listByWaybillId(Integer waybillId);

    /**
     * 根据条件查询
     *
     * @param waybillId
     * @param operateType
     * @param application
     *
     * @return
     *
     * @author Libin.Wei
     * @Date 2017年8月8日 下午3:51:11
     */
    WaybillOperateTrack findOperateTrackBy(int waybillId, int operateType, int application);

    /**
     * 根据条件查询
     *
     * @param waybillId
     * @param operateApplication 非必填
     * @param operateType        非必填
     *
     * @return
     */
    List<WaybillOperateTrack> listOperateTrackBy(Integer waybillId, OperateApplication operateApplication,
        OperateType operateType);

    /**
     * 批量添加
     *
     * @param listWaybillOperateTrack
     *
     * @throws BusinessException
     * @author Libin.Wei
     * @Date 2017年12月5日 下午4:17:13
     */
    void batchInsert(List<WaybillOperateTrack> listWaybillOperateTrack) throws BusinessException;
}
