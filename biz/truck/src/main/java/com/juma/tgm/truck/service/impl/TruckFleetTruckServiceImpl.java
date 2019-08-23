package com.juma.tgm.truck.service.impl;

import com.giants.common.collections.CollectionUtils;
import com.giants.common.tools.Page;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.truck.dao.TruckFleetTruckMapper;
import com.juma.tgm.truck.domain.TruckFleetTruck;
import com.juma.tgm.truck.domain.TruckFleetTruck.Column;
import com.juma.tgm.truck.domain.TruckFleetTruckExample;
import com.juma.tgm.truck.service.TruckFleetTruckService;
import com.juma.tgm.truck.vo.TruckFleetTruckFilter;
import com.juma.tgm.truck.vo.TruckFleetTruckVo;
import com.juma.vms.truck.domain.Truck;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * Created by RX on 2016/5/12 0012. 车队货车ServiceImpl
 */
@Service
public class TruckFleetTruckServiceImpl implements TruckFleetTruckService {

    @Resource
    private TruckFleetTruckMapper truckFleetTruckMapper;
    @Resource
    private VmsCommonService vmsCommonService;

    @Override
    public Page<TruckFleetTruckVo> search(QueryCond<TruckFleetTruckFilter> queryCond, LoginUser loginUser) {
        List<TruckFleetTruckVo> result = new ArrayList<>();
        TruckFleetTruckFilter filters = queryCond.getFilters();

        TruckFleetTruckExample example = new TruckFleetTruckExample().createCriteria()
            .andTruckFleetIdEqualTo(filters.getTruckFleetId())
            .andPlateNumberLike(StringUtils.isBlank(filters.getPlateNumber()) ? null : (filters.getPlateNumber() + "%"))
            .example();

        example.setSize(queryCond.getPageSize());
        example.setStartOffSet(queryCond.getStartOffset());
        example.setOrderByClause(Column.truckFleetTruckId.desc());

        long count = truckFleetTruckMapper.countByExample(example);
        List<TruckFleetTruck> rows = truckFleetTruckMapper.selectByExample(example);
        for (TruckFleetTruck t : rows) {
            TruckFleetTruckVo vo = new TruckFleetTruckVo();
            BeanUtils.copyProperties(t, vo);
            result.add(vo);
        }

        return new Page<>(queryCond.getPageNo(), queryCond.getPageSize(), Integer.parseInt(String.valueOf(count)),
            result);
    }

    @Override
    public List<TruckFleetTruckVo> listByTruckFleetId(Integer truckFleetId) {
        List<TruckFleetTruckVo> result = new ArrayList<>();
        if (null == truckFleetId) {
            return result;
        }

        TruckFleetTruckExample example = new TruckFleetTruckExample().createCriteria()
            .andTruckFleetIdEqualTo(truckFleetId)
            .example();
        List<TruckFleetTruck> trucks = truckFleetTruckMapper.selectByExample(example);
        for (TruckFleetTruck t : trucks) {
            TruckFleetTruckVo vo = new TruckFleetTruckVo();
            BeanUtils.copyProperties(t, vo);
            result.add(vo);
        }
        return result;
    }

    @Override
    public Page<TruckFleetTruck> listByTruckFleetIds(List<Integer> truckFleetIds, Integer pageNo, Integer pageSize) {
        pageNo = (pageNo == null ? 1 : (pageNo < 1 ? 1 : pageNo));
        pageSize = (pageSize == null ? Integer.MAX_VALUE : pageSize);
        if (CollectionUtils.isEmpty(truckFleetIds)) {
            return new Page<TruckFleetTruck>(pageNo, pageSize, 0, new ArrayList<TruckFleetTruck>());
        }

        TruckFleetTruckExample example = new TruckFleetTruckExample().createCriteria()
            .andTruckFleetIdIn(truckFleetIds)
            .example();

        example.setSize(pageSize);
        example.setStartOffSet((pageNo - 1) * pageSize);

        long count = truckFleetTruckMapper.countByExample(example);
        List<TruckFleetTruck> rows = truckFleetTruckMapper.selectByExample(example);
        return new Page<>(pageNo, pageSize, Integer.parseInt(String.valueOf(count)), rows);
    }

    @Override
    public List<TruckFleetTruck> listByTruckId(Integer truckId) {
        if (null == truckId) {
            return new ArrayList<TruckFleetTruck>();
        }

        TruckFleetTruckExample example = new TruckFleetTruckExample().createCriteria()
            .andTruckIdEqualTo(truckId)
            .example();
        return truckFleetTruckMapper.selectByExample(example);
    }

    @Override
    public void insert(Integer truckFleetId, List<Integer> listTruckId) {
        List<TruckFleetTruck> fleetTruckList = new ArrayList<>();
        for (Integer truckId : listTruckId) {
            if (null == truckId) {
                continue;
            }

            TruckFleetTruck truckFleetTruck = new TruckFleetTruck();
            truckFleetTruck.setTruckFleetId(truckFleetId);
            truckFleetTruck.setTruckId(truckId);
            Truck truck = vmsCommonService.loadTruckByTruckId(truckId);
            truckFleetTruck.setPlateNumber(truck == null ? "" : truck.getPlateNumber());
            fleetTruckList.add(truckFleetTruck);
        }

        if (fleetTruckList.isEmpty()) {
            return;
        }

        truckFleetTruckMapper.insertBatch(fleetTruckList);
    }

    @Override
    public void delete(Integer truckFleetTruckId) {
        if (null == truckFleetTruckId) {
            return;
        }

        truckFleetTruckMapper.deleteByPrimaryKey(truckFleetTruckId);
    }

    @Override
    public void deleteByTruckFleetId(Integer truckFleetId) {
        if (null == truckFleetId) {
            return;
        }

        TruckFleetTruckExample example = new TruckFleetTruckExample().createCriteria()
            .andTruckFleetIdEqualTo(truckFleetId)
            .example();

        truckFleetTruckMapper.deleteByExample(example);
    }

    @Override
    public void deleteByTruckId(Integer truckId) {
        if (null == truckId) {
            return;
        }

        TruckFleetTruckExample example = new TruckFleetTruckExample().createCriteria()
            .andTruckIdEqualTo(truckId)
            .example();
        truckFleetTruckMapper.deleteByExample(example);
    }

    @Override
    public void changeTruckFleetTrucks(Integer truckFleetId, List<Integer> listTruckID) {
        deleteByTruckFleetId(truckFleetId);
        insert(truckFleetId,listTruckID);
    }

}
