package com.juma.tgm.truck.vo;

import com.juma.tgm.truck.domain.TruckFleetTruck;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TruckFleetTruckFilter
 * @Description TODO
 * @Author weilibin
 * @Date 2019-05-17 17:26
 * @Version 1.0.0
 */

public class TruckFleetTruckFilter extends TruckFleetTruck {

    private List<Integer> listTruckId = new ArrayList<Integer>();

    public List<Integer> getListTruckId() {
        return listTruckId;
    }

    public void setListTruckId(List<Integer> listTruckId) {
        this.listTruckId = listTruckId;
    }
}
