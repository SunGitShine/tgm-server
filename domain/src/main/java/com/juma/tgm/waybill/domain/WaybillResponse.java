package com.juma.tgm.waybill.domain;

import java.util.List;

/**
 * Created by Administrator on 2017/3/10 0010.
 */
public class WaybillResponse {
    List<DistributedOrder>  distributedOrders;
    List<FilteredOrder> filteredOrders;
    List<String> unDistributedOrderIds;

    public List<DistributedOrder> getDistributedOrders() {
        return distributedOrders;
    }

    public void setDistributedOrders(List<DistributedOrder> distributedOrders) {
        this.distributedOrders = distributedOrders;
    }

    public List<FilteredOrder> getFilteredOrders() {
        return filteredOrders;
    }

    public void setFilteredOrders(List<FilteredOrder> filteredOrders) {
        this.filteredOrders = filteredOrders;
    }

    public List<String> getUnDistributedOrderIds() {
        return unDistributedOrderIds;
    }

    public void setUnDistributedOrderIds(List<String> unDistributedOrderIds) {
        this.unDistributedOrderIds = unDistributedOrderIds;
    }
}
