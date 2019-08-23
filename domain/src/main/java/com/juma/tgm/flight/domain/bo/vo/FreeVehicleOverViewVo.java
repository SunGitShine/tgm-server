package com.juma.tgm.flight.domain.bo.vo;

import com.juma.server.vm.domain.bo.FlightBo;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: FreeVehicleOverViewVo
 * @Description:
 * @author: liang
 * @date: 2017-07-10 16:54
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class FreeVehicleOverViewVo implements Serializable {

    private Date point;

    private Map<String, FreeVehicleInfoVo> detailList = new HashMap<>();


    public void addDetail(FlightBo flightBo, String boxTypeName){

        FreeVehicleInfoVo info = null;
        //有则更新数据
        if(detailList.containsKey(boxTypeName)){
            info = detailList.get(boxTypeName);
            info.addBoxTypeCount(1);
            info.setBoxTypeName(boxTypeName);
        }else{
            //没有类型则增加
            info = new FreeVehicleInfoVo();
            info.setBoxType(flightBo.getVehicleBoxType().intValue());
            info.setBoxTypeName(boxTypeName);
            info.addBoxTypeCount(1);

            this.detailList.put(boxTypeName, info);
        }

        if (flightBo.getGoCityLicenseType() != null && NumberUtils.compare(0, flightBo.getGoCityLicenseType()) != 0) {
            info.addEntryLicenseCount(1);
        }
    }

    public Collection<FreeVehicleInfoVo> getDetailList(){
        if(MapUtils.isEmpty(this.detailList)) return null;

        return this.detailList.values();
    }


    public Date getPoint() {
        return point;
    }

    public void setPoint(Date point) {
        this.point = point;
    }


    public void setDetailList(Map<String, FreeVehicleInfoVo> detailList) {
        this.detailList = detailList;
    }

    public Integer getTotalCount() {
        int count = 0;
        if(MapUtils.isEmpty(this.detailList)){
            return count;
        }

        for(FreeVehicleInfoVo info : this.getDetailList()){
            count += info.getBoxTypeCount();
        }

        return count;
    }

}
