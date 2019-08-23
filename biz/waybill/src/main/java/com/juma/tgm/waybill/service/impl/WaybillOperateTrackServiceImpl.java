package com.juma.tgm.waybill.service.impl;

import com.alibaba.fastjson.JSON;
import com.giants.common.collections.CollectionUtils;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.auth.user.service.UserService;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.gaode.domain.AmapRegeoResponse;
import com.juma.tgm.mq.service.MqService;
import com.juma.tgm.operateLog.enumeration.OperateTypeEnum;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.waybill.dao.WaybillOperateTrackMapper;
import com.juma.tgm.waybill.domain.DistanceAndPriceData;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillDeliveryAddress;
import com.juma.tgm.waybill.domain.WaybillOperateTrack;
import com.juma.tgm.waybill.domain.WaybillOperateTrack.Column;
import com.juma.tgm.waybill.domain.WaybillOperateTrackExample;
import com.juma.tgm.waybill.domain.WaybillOperateTrackNotRequieParam;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.domain.WaybillReceiveAddress;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateApplication;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateType;
import com.juma.tgm.waybill.service.GaoDeMapService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillDeliveryAddressService;
import com.juma.tgm.waybill.service.WaybillOperateTrackService;
import com.juma.tgm.waybill.service.WaybillParamService;
import com.juma.tgm.waybill.service.WaybillReceiveAddressService;
import com.juma.tgm.waybill.vo.WaybillOperateTrackFilter;
import com.juma.tgm.waybill.vo.WaybillOperateTrackQuery;
import com.juma.vms.driver.domain.Driver;
import com.juma.vms.truck.domain.Truck;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class WaybillOperateTrackServiceImpl implements WaybillOperateTrackService {

    private final Logger log = LoggerFactory.getLogger(WaybillOperateTrackServiceImpl.class);
    @Resource
    private WaybillOperateTrackMapper waybillOperateTrackMapper;
    @Resource
    private WaybillDeliveryAddressService waybillDeliveryAddressService;
    @Resource
    private WaybillReceiveAddressService waybillReceiveAddressService;
    @Resource
    private GaoDeMapService gaoDeMapService;
    @Resource
    private UserService userService;
    @Resource
    private VmsCommonService vmsCommonService;
    @Resource
    private WaybillCommonService waybillCommonService;
    @Resource
    private WaybillParamService waybillParamService;
    @Resource
    private MqService mqService;

    @Override
    public Page<WaybillOperateTrackQuery> search(QueryCond<WaybillOperateTrackFilter> queryCond) {
        List<WaybillOperateTrackQuery> result = new ArrayList<>();
        WaybillOperateTrackFilter filters = queryCond.getFilters();
        if (null == filters || null == filters.getWaybillId()) {
            return new Page<>(queryCond.getPageNo(), queryCond.getPageSize(), 0, result);
        }

        WaybillOperateTrackExample example = new WaybillOperateTrackExample().createCriteria()
            .andWaybillIdEqualTo(filters.getWaybillId())
            .example();

        example.setSize(queryCond.getPageSize());
        example.setStartOffSet(queryCond.getStartOffset());
        example.setOrderByClause(Column.declareTime.asc());

        long count = waybillOperateTrackMapper.countByExample(example);
        List<WaybillOperateTrack> rows = waybillOperateTrackMapper.selectByExample(example);

        for (WaybillOperateTrack track : rows) {
            // 地址二次补全
            try {
                if (StringUtils.isNotBlank(track.getOperateAddressCoordinates()) && StringUtils.isBlank(track.getOperateAddress())) {
                    this.updateOperateAddress(track);
                }
            } catch (Exception e) {

            }

            WaybillOperateTrackQuery query = new WaybillOperateTrackQuery();
            BeanUtils.copyProperties(track, query);

            query.setDistanceStr(track.getDistance() == null ? null : new BigDecimal(track.getDistance())
                .divide(new BigDecimal("1000"), 2, BigDecimal.ROUND_HALF_UP).toString());

            User user = userService.loadUser(track.getCreateUserId());
            if (null != user) {
                if (Constants.SYS_LOGIN_USER.getUserId().equals(user.getUserId())) {
                    query.setOperateUserName("系统");
                    result.add(query);
                    continue;
                }

                if (null == user.getName() && track.getOperateApplication().equals(OperateApplication.DRIVER_SYS.getCode())) {
                    Driver driver = vmsCommonService.loadDriverByUserId(user.getUserId());
                    query.setOperateUserName(driver.getName());
                    query.setPhone(driver.getPhone());
                } else {
                    query.setOperateUserName(user.getName());
                    query.setPhone(user.getMobileNumber());
                }
            }
            result.add(query);
        }
        return new Page<>(queryCond.getPageNo(), queryCond.getPageSize(), Integer.parseInt(String.valueOf(count)), result);
    }

    @Override
    public List<WaybillOperateTrackQuery> listByWaybillIdAndTypes(Integer waybillId, List<Integer> operateTypeList,
            List<Integer> notIncludeapplicationList) {
        if (null == waybillId) {
            return new ArrayList<WaybillOperateTrackQuery>();
        }

        WaybillOperateTrackExample example = new WaybillOperateTrackExample().createCriteria()
            .andWaybillIdEqualTo(waybillId)
            .andOperateTypeIn(operateTypeList)
            .andOperateApplicationNotIn(notIncludeapplicationList)
            .example();

        return buildWaybillOperateTrack(waybillOperateTrackMapper.selectByExample(example));
    }

    @Override
    public void insert(WaybillOperateTrack waybillOperateTrack, LoginUser loginUser) {
        if (null == waybillOperateTrack) {
            return;
        }

        // 获取车牌号与司机ID
        Waybill waybill = waybillCommonService.getWaybillById(waybillOperateTrack.getWaybillId());
        waybillOperateTrack.setDriverId(waybill == null ? null : waybill.getDriverId());
        waybillOperateTrack.setPlateNumber(waybill == null ? null : waybill.getPlateNumber());

        waybillOperateTrack.setCreateTime(new Date());
        waybillOperateTrack.setDeclareTime(
            waybillOperateTrack.getDeclareTime() == null ? new Date() : waybillOperateTrack.getDeclareTime());
        waybillOperateTrack
            .setCreateUserId(loginUser == null ? Constants.SYS_LOGIN_USER.getUserId() : loginUser.getUserId());
        this.insertData(waybillOperateTrack);

        // 改价信息不同步
        if (waybillOperateTrack.getOperateType().equals(OperateType.UPDATE_FREIGHT.getCode())) {
            return;
        }

        // 转运方信息同步
        this.modifyTransformBillOprate(waybillOperateTrack);
        // 承运商信息同步
        this.modifyVendorBillOprate(waybillOperateTrack);
    }

    private void insertData(WaybillOperateTrack waybillOperateTrack) {
        if (null == waybillOperateTrack.getWaybillId() || null == waybillOperateTrack.getOperateType()
            || null == waybillOperateTrack.getOperateApplication()) {
            return;
        }

        // 校验是不是已经存在操作记录
        WaybillOperateTrack track = this
            .findOperateTrackBy(waybillOperateTrack.getWaybillId(), waybillOperateTrack.getOperateType(),
                waybillOperateTrack.getOperateApplication());
        if (null != track) {
            // 只记录第一次的操作
            return;
        }

        waybillOperateTrackMapper.insert(waybillOperateTrack);
    }

    // 乙方的操作记录：来源与甲方的操作记录
    private void modifyTransformBillOprate(WaybillOperateTrack waybillOperateTrack) {
        Waybill waybill = waybillCommonService.findWaybillByTransformBillId(waybillOperateTrack.getWaybillId());
        if (null == waybill) {
            return;
        }
        waybillOperateTrack.setWaybillId(waybill.getWaybillId());
        this.insertData(waybillOperateTrack);
    }

    // 甲方的操作记录：来源与乙方的操作记录
    private void modifyVendorBillOprate(WaybillOperateTrack waybillOperateTrack) {
        WaybillParam transformBillParam = waybillParamService.findByWaybillId(waybillOperateTrack.getWaybillId());
        if (null == transformBillParam || null == transformBillParam.getTransformBillLinkId()) {
            return;
        }

        if (null != waybillOperateTrack.getOperateType() && NumberUtils.compare(waybillOperateTrack.getOperateType(),
                WaybillOperateTrackEnum.OperateType.TRANSFORM_BILL.getCode()) == 0) {
            waybillOperateTrack.setOperateType(WaybillOperateTrackEnum.OperateType.MANUAL_ASSIGN.getCode());
        }

        waybillOperateTrack.setWaybillId(transformBillParam.getTransformBillLinkId());
        this.insertData(waybillOperateTrack);
    }

    @Override
    public void insert(Integer waybillId, OperateType operateType, OperateApplication operatePort,
            WaybillOperateTrackNotRequieParam notRequieParam, LoginUser loginUser) {
        // 运单操作记录的顺序控制
        checkTrackOrderContrl(waybillId, operateType);

        WaybillOperateTrack track = new WaybillOperateTrack();
        track.setWaybillId(waybillId);
        track.setOperateType(operateType == null ? null : operateType.getCode());
        track.setOperateApplication(operatePort == null ? null : operatePort.getCode());
        track.setCreateUserId(loginUser == null ? Constants.SYS_LOGIN_USER.getUserId() : loginUser.getUserId());
        track.setCreateTime(new Date());
        track.setDeclareTime(new Date());
        track.setOperateAddressCoordinates(notRequieParam == null ? null : notRequieParam.getCoordinate());
        track.setRemark(notRequieParam == null ? null : (StringUtils.isBlank(notRequieParam.getRemark()) ? null
                : (notRequieParam.getRemark().length() > 1024 ? null : notRequieParam.getRemark())));
        track.setDeviceNo(notRequieParam == null ? null : notRequieParam.getDeviceNo());
        track.setDeviceType(notRequieParam == null ? null : notRequieParam.getDeviceType());
        track.setDataSource(WaybillOperateTrackEnum.DataSource.UNKNOWN.getCode());
        this.insertData(track);


        // 改价信息不同步
        if (!track.getOperateType().equals(OperateType.UPDATE_FREIGHT.getCode())) {
            // 转运方信息同步
            this.modifyTransformBillOprate(track);
            // 承运商信息同步
            this.modifyVendorBillOprate(track);
        }

        try {
            // 为了不降低用户的体验度，通过高的获取结构化地址数据、计算距离仍然使用异步，以MQ的方式
            mqService.sendWaybillOperateTrack(track);
        } catch (Exception e) {
            log.warn("运单操作轨迹记录失败：{}", JSON.toJSONString(track));
        }
    }

    // 运单操作记录的顺序控制：离仓->到仓->配送完成
    private void checkTrackOrderContrl(Integer waybillId, OperateType operateType) {
        if (null == waybillId || null == operateType) {
            return;
        }

        if (operateType.equals(OperateType.LEAVE_DEPOT)) {
            List<WaybillOperateTrack> tracks = this
                .ListByWayBillIdAndOperateType(waybillId, OperateType.ARRIVE_DEPOT.getCode());
            if (tracks.isEmpty()) {
                throw new BusinessException("noArriveDepotErr", "errors.common.prompt", "离开取货地之前，请先点击到达取货地");
            }

            return;
        }

        if (operateType.equals(OperateType.DELIVERYING)) {
            List<WaybillOperateTrack> tracks = this
                .ListByWayBillIdAndOperateType(waybillId, OperateType.LEAVE_DEPOT.getCode());
            if (tracks.isEmpty()) {
                throw new BusinessException("noLeaveDepotErr", "errors.common.prompt", "完成配送之前，请先点击离开取货地");
            }
        }
    }

    @Override
    public List<WaybillOperateTrack> ListByWayBillIdAndOperateType(int waybillId, int operateType) {
        WaybillOperateTrackExample example = new WaybillOperateTrackExample().createCriteria()
            .andWaybillIdEqualTo(waybillId)
            .andOperateTypeEqualTo(operateType)
            .example();
        return waybillOperateTrackMapper.selectByExample(example);
    }

    @Override
    public void updateByMq(WaybillOperateTrack track) {
        track = updateOperateAddress(track);
        if (track == null) {
            return;
        }

        // 改价信息不同步
        if (track.getOperateType().equals(OperateType.UPDATE_FREIGHT.getCode())) {
            return;
        }

        // 转运方信息同步
        this.modifyTransformBillOprate(track);
        // 承运商信息同步
        this.modifyVendorBillOprate(track);
    }

    private WaybillOperateTrack updateOperateAddress(WaybillOperateTrack track) {
        if (null == track) {
            return null;
        }

        Waybill wb = waybillCommonService.getWaybillById(track.getWaybillId());
        if (null == wb) {
            return null;
        }

        track = buildActualAddress(track.getWaybillId(), track);

        // 距离
        DistanceAndPriceData data = gaoDeMapService
                .getDistanceSimple(track.getActualAddressCoordinates(), track.getOperateAddressCoordinates());
        if (null != data) {
            track.setDistance(data.getDistance() == null ? null : Integer.valueOf(data.getDistance()));
        }

        // 结构化地址
        AmapRegeoResponse regeocode = gaoDeMapService.regeocode(track.getOperateAddressCoordinates());
        if (null != regeocode) {
            track.setOperateAddress(regeocode.getRegeocode().getFormattedAddress());
        }

        // 获取车牌号与司机ID
        track.setDriverId(wb.getDriverId());
        if (StringUtils.isNotBlank(wb.getPlateNumber())) {
            track.setPlateNumber(wb.getPlateNumber());
        } else {
            Truck truck = vmsCommonService.loadTruckByTruckId(wb.getTruckId());
            track.setPlateNumber(truck == null ? null : truck.getPlateNumber());
        }

        waybillOperateTrackMapper.updateByPrimaryKeySelective(track);

        // 转运方信息同步
        this.modifyTransformBillOprate(track);
        // 承运商信息同步
        this.modifyVendorBillOprate(track);

        return track;
    }

    // 构造WaybillOperateTrack
    private WaybillOperateTrack buildActualAddress(Integer waybillId, WaybillOperateTrack track) {
        if (null == track.getOperateType()) {
            return track;
        }

        if (track.getOperateType().equals(WaybillOperateTrackEnum.OperateType.ARRIVE_DEPOT.getCode())
                || track.getOperateType().equals(WaybillOperateTrackEnum.OperateType.LEAVE_DEPOT.getCode())) {
            WaybillDeliveryAddress deliveryAddress = waybillDeliveryAddressService.findByWaybillId(waybillId);
            if (null == deliveryAddress) {
                return track;
            }

            track.setActualAddressCoordinates(deliveryAddress.getCoordinates());
            track.setActualAddress(deliveryAddress.getAddressDetail());
            return track;
        }

        if (track.getOperateType().equals(WaybillOperateTrackEnum.OperateType.DELIVERYING.getCode())
                || track.getOperateType().equals(WaybillOperateTrackEnum.OperateType.ARRIVE_DESTINATION.getCode())
                || track.getOperateType().equals(WaybillOperateTrackEnum.OperateType.LEAVE_DESTINATION.getCode())) {
            List<WaybillReceiveAddress> list = waybillReceiveAddressService.findAllByWaybillId(waybillId);
            if (list.isEmpty() || null == list.get(list.size() - 1)) {
                return track;
            }

            track.setActualAddressCoordinates(list.get(list.size() - 1).getCoordinates());
            track.setActualAddress(list.get(list.size() - 1).getAddressDetail());
            return  track;
        }
        return track;
    }

    // 组装显示冗余字段
    private List<WaybillOperateTrackQuery> buildWaybillOperateTrack(List<WaybillOperateTrack> list) {
        List<WaybillOperateTrackQuery> result = new ArrayList<>();
        List<Integer> hasOperateTypeList = new ArrayList<Integer>();
        for (WaybillOperateTrack track : list) {
            WaybillOperateTrackQuery query = new WaybillOperateTrackQuery();
            BeanUtils.copyProperties(track, query);

            if (hasOperateTypeList.contains(track.getOperateType())) {
                continue;
            }
            hasOperateTypeList.add(track.getOperateType());

            User user = userService.loadUser(track.getCreateUserId());
            query.setOperateUserName(user == null ? null : user.getName());

            query.setDistanceStr(track.getDistance() == null ? null
                : new BigDecimal(track.getDistance()).divide(new BigDecimal("1000"), 2, BigDecimal.ROUND_HALF_UP)
                    .toString());
            result.add(query);
        }
        return result;
    }

    @Override
    public List<WaybillOperateTrack> listByWaybillId(Integer waybillId) {
        WaybillOperateTrackExample example = new WaybillOperateTrackExample().createCriteria()
            .andWaybillIdEqualTo(waybillId)
            .example();
        return waybillOperateTrackMapper.selectByExample(example);
    }

    @Override
    public WaybillOperateTrack findOperateTrackBy(int waybillId, int operateType, int application) {
        WaybillOperateTrackExample example = new WaybillOperateTrackExample().createCriteria()
            .andWaybillIdEqualTo(waybillId)
            .andOperateTypeEqualTo(operateType)
            .andOperateApplicationEqualTo(application)
            .example();
        List<WaybillOperateTrack> list = waybillOperateTrackMapper.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<WaybillOperateTrack> listOperateTrackBy(Integer waybillId, OperateApplication operateApplication,
        OperateType operateType) {
        if (null == waybillId) {
            return new ArrayList<>();
        }

        return waybillOperateTrackMapper.selectByExample(new WaybillOperateTrackExample().createCriteria()
            .andWaybillIdEqualTo(waybillId)
            .andOperateApplicationEqualTo(null == operateApplication ? null : operateApplication.getCode())
            .andOperateTypeEqualTo(operateType == null ? null : operateType.getCode())
            .example());
    }

    @Override
    public void batchInsert(List<WaybillOperateTrack> listWaybillOperateTrack) throws BusinessException {
        if (CollectionUtils.isEmpty(listWaybillOperateTrack)) {
            return;
        }

        waybillOperateTrackMapper.updateBatchByPrimaryKeySelective(listWaybillOperateTrack);

        // 转运方信息同步
        for (WaybillOperateTrack waybillOperateTrack : listWaybillOperateTrack) {
            this.modifyTransformBillOprate(waybillOperateTrack);
        }
    }
}
