package com.juma.tgm.waybill.domain.vo;

import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillParam;

import java.io.Serializable;

/**
 * @ClassName: WaybillTransformToCarrierVo
 * @Description:
 * @author: liang
 * @date: 2018-08-28 18:50
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class WaybillTransformToCarrierVo implements Serializable {

    private Waybill waybill;
    private WaybillParam waybillParam;
    private TruckRequire truckRequire;

}
