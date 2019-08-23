package com.juma.tgm.waybill.domain.map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WaybillMapTracePoint implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5778205065405366876L;

    /** 车的位置 */
    private String coordinates;

    private List<WaybillAddress> nodes = new ArrayList<WaybillAddress>();

    public String getCoordinates() {
        return coordinates;
    }

    public List<WaybillAddress> getNodes() {
        return nodes;
    }

    public void setNodes(List<WaybillAddress> nodes) {
        this.nodes = nodes;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public void addNode(WaybillAddress node) {
        nodes.add(node);
    }

}
