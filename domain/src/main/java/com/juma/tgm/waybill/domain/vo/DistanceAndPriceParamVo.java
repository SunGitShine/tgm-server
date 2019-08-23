package com.juma.tgm.waybill.domain.vo;

import com.juma.tgm.waybill.domain.CityAdressData;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillParam;

import java.io.Serializable;
import java.util.List;

/**
 * @author rx
 * @version V1.0
 * @Description:
 * @date 2016/05/20 14:41
 */
public class DistanceAndPriceParamVo implements Serializable {

    private static final long serialVersionUID = -7509735555100996712L;
    private CityAdressData srcAddress;
    private List<CityAdressData> toAddress;
    private TruckRequire truckRequire;
    private WaybillParam waybillParam;
    private Waybill waybill;

    public CityAdressData getSrcAddress() {
        return srcAddress;
    }

    public void setSrcAddress(CityAdressData srcAddress) {
        this.srcAddress = srcAddress;
    }

    public List<CityAdressData> getToAddress() {
        return toAddress;
    }

    public void setToAddress(List<CityAdressData> toAddress) {
        this.toAddress = toAddress;
    }

    public TruckRequire getTruckRequire() {
        return truckRequire;
    }

    public void setTruckRequire(TruckRequire truckRequire) {
        this.truckRequire = truckRequire;
    }

    public WaybillParam getWaybillParam() {
        return waybillParam;
    }

    public void setWaybillParam(WaybillParam waybillParam) {
        this.waybillParam = waybillParam;
    }

    public Waybill getWaybill() {
        return waybill;
    }

    public void setWaybill(Waybill waybill) {
        this.waybill = waybill;
    }

}
