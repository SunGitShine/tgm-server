package com.juma.tgm.util;

import com.alibaba.fastjson.JSON;
import com.giants.common.exception.BusinessException;
import com.juma.tgm.project.vo.ProjectBillVo;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillBo;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.domain.transformbill.TransformBillMark;
import com.juma.tgm.waybill.domain.vo.ScatteredWaybillCreateVo;
import com.juma.tgm.waybill.domain.vo.WaybillCarrierVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * 将抽象转运运单转化为具体业务运单结构
 *
 * @ClassName: TransformBillBo
 * @Description:
 * @author: liang
 * @date: 2018-08-31 20:30
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class TransformBillBo implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(TransformBillBo.class);
    private static final String LOG_MARKER = "transformBillBo";

    private TransformBillMark transformBill;


    public TransformBillBo(TransformBillMark transformBill) {
        this.transformBill = transformBill;
    }

    //转化为专车运单结构
    public WaybillBo toWaybillBo() {
        WaybillBo waybillBo = new WaybillBo();

        // 执行重新组装
        if (transformBill instanceof WaybillBo) {
            //FIXME 不能依赖引用传递
            WaybillBo waybillBoSource = (WaybillBo) transformBill;

            Waybill transformBill = new Waybill();
            BeanUtils.copyProperties(waybillBoSource.getWaybill(), transformBill);
            waybillBo.setWaybill(transformBill);

            TruckRequire transformRequire = new TruckRequire();
            BeanUtils.copyProperties(waybillBoSource.getTruckRequire(), transformRequire);
            waybillBo.setTruckRequire(transformRequire);

            WaybillParam transformParam = new WaybillParam();
            BeanUtils.copyProperties(waybillBoSource.getWaybillParam(), transformParam);
            waybillBo.setWaybillParam(transformParam);

            WaybillCarrierVo transformCarrierVo = new WaybillCarrierVo();
            BeanUtils.copyProperties(waybillBoSource.getWaybillCarrierVo(), transformCarrierVo);
            waybillBo.setWaybillCarrierVo(transformCarrierVo);

            waybillBo.setDeliveryAddress(waybillBoSource.getDeliveryAddress());
            waybillBo.setReceiveAddress(waybillBoSource.getReceiveAddress());

        } else if (transformBill instanceof ProjectBillVo) {
            ProjectBillVo projectBillVo = (ProjectBillVo) transformBill;

            Waybill transformBill = new Waybill();
            BeanUtils.copyProperties(projectBillVo.getWaybill(), transformBill);
            transformBill.setProjectId(projectBillVo.getProject().getProjectId());
            waybillBo.setWaybill(transformBill);

            TruckRequire transformRequire = new TruckRequire();
            BeanUtils.copyProperties(projectBillVo.getTruckRequire(), transformRequire);
            waybillBo.setTruckRequire(transformRequire);

            WaybillParam transformParam = new WaybillParam();
            BeanUtils.copyProperties(projectBillVo.getWaybillParam(), transformParam);
            waybillBo.setWaybillParam(transformParam);

            WaybillCarrierVo transformCarrierVo = new WaybillCarrierVo();
            BeanUtils.copyProperties(projectBillVo.getWaybillCarrierVo(), transformCarrierVo);
            waybillBo.setWaybillCarrierVo(transformCarrierVo);

            waybillBo.setReceiveAddress(projectBillVo.getReceiveAddress());
            waybillBo.setDeliveryAddress(projectBillVo.getDeliveryAddress());
//            waybillBo.setCreateBatchAmount(projectBillVo.getCreateBatchAmount());

        } else if (transformBill instanceof ScatteredWaybillCreateVo) {
            ScatteredWaybillCreateVo scatteredWaybillCreateVo = (ScatteredWaybillCreateVo) transformBill;

            Waybill transformBill = new Waybill();
            BeanUtils.copyProperties(scatteredWaybillCreateVo.getWaybill(), transformBill);
            waybillBo.setWaybill(transformBill);

            TruckRequire transformRequire = new TruckRequire();
            BeanUtils.copyProperties(scatteredWaybillCreateVo.getTruckRequire(), transformRequire);
            waybillBo.setTruckRequire(transformRequire);

            WaybillParam transformParam = new WaybillParam();
            BeanUtils.copyProperties(scatteredWaybillCreateVo.getWaybillParam(), transformParam);
            waybillBo.setWaybillParam(transformParam);

            WaybillCarrierVo transformCarrierVo = new WaybillCarrierVo();
            BeanUtils.copyProperties(scatteredWaybillCreateVo.getWaybillCarrierVo(), transformCarrierVo);
            waybillBo.setWaybillCarrierVo(transformCarrierVo);

            waybillBo.setReceiveAddress(scatteredWaybillCreateVo.getDestAddress());
            waybillBo.setDeliveryAddress(scatteredWaybillCreateVo.getSrcAddress());
//            waybillBo.setCreateBatchAmount(scatteredWaybillCreateVo.getCreateBatchAmount());
        } else {
            log.warn(LOG_MARKER, "不支持的运单转运类型:" + JSON.toJSONString(transformBill));
            throw new BusinessException("TransformBillDestinationTypeNotSupport", "errors.common.prompt", "不支持的运单转运类型");
        }

        return waybillBo;
    }


    //转化为项目单结构
    public ProjectBillVo toProjectBillVo() {
        ProjectBillVo projectBillVo = new ProjectBillVo();

        if (transformBill instanceof ProjectBillVo) {
            ProjectBillVo projectBillSource = (ProjectBillVo) transformBill;

            Waybill transformBill = new Waybill();
            BeanUtils.copyProperties(projectBillSource.getWaybill(), transformBill);
            projectBillVo.setWaybill(transformBill);

            TruckRequire transformRequire = new TruckRequire();
            BeanUtils.copyProperties(projectBillSource.getTruckRequire(), transformRequire);
            projectBillVo.setTruckRequire(transformRequire);

            WaybillParam transformParam = new WaybillParam();
            BeanUtils.copyProperties(projectBillSource.getWaybillParam(), transformParam);
            projectBillVo.setWaybillParam(transformParam);

            WaybillCarrierVo transformCarrierVo = new WaybillCarrierVo();
            BeanUtils.copyProperties(projectBillSource.getWaybillCarrierVo(), transformCarrierVo);
            projectBillVo.setWaybillCarrierVo(transformCarrierVo);

            projectBillVo.setProject(projectBillSource.getProject());

            projectBillVo.setReceiveAddress(projectBillSource.getReceiveAddress());
            projectBillVo.setDeliveryAddress(projectBillSource.getDeliveryAddress());

        } else if (transformBill instanceof WaybillBo) {
            WaybillBo waybillBo = (WaybillBo) transformBill;

            Waybill transformBill = new Waybill();
            BeanUtils.copyProperties(waybillBo.getWaybill(), transformBill);
            projectBillVo.setWaybill(transformBill);

            TruckRequire transformRequire = new TruckRequire();
            BeanUtils.copyProperties(waybillBo.getTruckRequire(), transformRequire);
            projectBillVo.setTruckRequire(transformRequire);

            WaybillParam transformParam = new WaybillParam();
            BeanUtils.copyProperties(waybillBo.getWaybillParam(), transformParam);
            projectBillVo.setWaybillParam(transformParam);

            WaybillCarrierVo transformCarrierVo = new WaybillCarrierVo();
            BeanUtils.copyProperties(waybillBo.getWaybillCarrierVo(), transformCarrierVo);
            projectBillVo.setWaybillCarrierVo(transformCarrierVo);

            projectBillVo.setReceiveAddress(projectBillVo.getReceiveAddress());
            projectBillVo.setDeliveryAddress(projectBillVo.getDeliveryAddress());
//            projectBillVo.setCreateBatchAmount(projectBillVo.getCreateBatchAmount());
        } else if (transformBill instanceof ScatteredWaybillCreateVo) {
            ScatteredWaybillCreateVo scatteredWaybillCreateVo = (ScatteredWaybillCreateVo) transformBill;

            Waybill transformBill = new Waybill();
            BeanUtils.copyProperties(scatteredWaybillCreateVo.getWaybill(), transformBill);
            projectBillVo.setWaybill(transformBill);

            TruckRequire transformRequire = new TruckRequire();
            BeanUtils.copyProperties(scatteredWaybillCreateVo.getTruckRequire(), transformRequire);
            projectBillVo.setTruckRequire(transformRequire);

            WaybillParam transformParam = new WaybillParam();
            BeanUtils.copyProperties(scatteredWaybillCreateVo.getWaybillParam(), transformParam);
            projectBillVo.setWaybillParam(transformParam);

            WaybillCarrierVo transformCarrierVo = new WaybillCarrierVo();
            BeanUtils.copyProperties(scatteredWaybillCreateVo.getWaybillCarrierVo(), transformCarrierVo);
            projectBillVo.setWaybillCarrierVo(transformCarrierVo);

            projectBillVo.setReceiveAddress(scatteredWaybillCreateVo.getDestAddress());
            projectBillVo.setDeliveryAddress(scatteredWaybillCreateVo.getSrcAddress());
//            projectBillVo.setCreateBatchAmount(scatteredWaybillCreateVo.getCreateBatchAmount());
        } else {
            log.warn(LOG_MARKER, "不支持的运单转运类型:" + JSON.toJSONString(transformBill));
            throw new BusinessException("TransformBillDestinationTypeNotSupport", "errors.common.prompt", "不支持的运单转运类型");
        }

        return projectBillVo;
    }

    //转化为希地单结构
    public ScatteredWaybillCreateVo toScatteredWaybillCreateVo() {
        ScatteredWaybillCreateVo scatteredWaybillCreateVo = new ScatteredWaybillCreateVo();

        if (transformBill instanceof ScatteredWaybillCreateVo) {
            ScatteredWaybillCreateVo scatteredWaybillSource = (ScatteredWaybillCreateVo) transformBill;

            Waybill transformBill = new Waybill();
            BeanUtils.copyProperties(scatteredWaybillSource.getWaybill(), transformBill);
            scatteredWaybillCreateVo.setWaybill(transformBill);

            TruckRequire transformRequire = new TruckRequire();
            BeanUtils.copyProperties(scatteredWaybillSource.getWaybill(), transformRequire);
            scatteredWaybillCreateVo.setTruckRequire(transformRequire);

            WaybillParam transformParam = new WaybillParam();
            BeanUtils.copyProperties(scatteredWaybillSource.getWaybillParam(), transformParam);
            scatteredWaybillCreateVo.setWaybillParam(transformParam);

            WaybillCarrierVo transformCarrierVo = new WaybillCarrierVo();
            BeanUtils.copyProperties(scatteredWaybillSource.getWaybillCarrierVo(), transformCarrierVo);
            scatteredWaybillCreateVo.setWaybillCarrierVo(transformCarrierVo);

            scatteredWaybillCreateVo.setSrcAddress(scatteredWaybillSource.getSrcAddress());
            scatteredWaybillCreateVo.setDestAddress(scatteredWaybillSource.getDestAddress());

        } else if (transformBill instanceof WaybillBo) {
            WaybillBo waybillBo = (WaybillBo) transformBill;

            Waybill transformBill = new Waybill();
            BeanUtils.copyProperties(waybillBo.getWaybill(), transformBill);
            scatteredWaybillCreateVo.setWaybill(transformBill);

            TruckRequire transformRequire = new TruckRequire();
            BeanUtils.copyProperties(waybillBo.getTruckRequire(), transformRequire);
            scatteredWaybillCreateVo.setTruckRequire(transformRequire);

            WaybillParam transformParam = new WaybillParam();
            BeanUtils.copyProperties(waybillBo.getWaybillParam(), transformParam);
            scatteredWaybillCreateVo.setWaybillParam(transformParam);

            WaybillCarrierVo transformCarrierVo = new WaybillCarrierVo();
            BeanUtils.copyProperties(waybillBo.getWaybillCarrierVo(), transformCarrierVo);
            scatteredWaybillCreateVo.setWaybillCarrierVo(transformCarrierVo);

            scatteredWaybillCreateVo.setDestAddress(waybillBo.getReceiveAddress());
            scatteredWaybillCreateVo.setSrcAddress(waybillBo.getDeliveryAddress());
//            scatteredWaybillCreateVo.setCreateBatchAmount(waybillBo.getCreateBatchAmount());
        } else if (transformBill instanceof ProjectBillVo) {
            ProjectBillVo projectBillVo = (ProjectBillVo) transformBill;

            Waybill transformBill = new Waybill();
            BeanUtils.copyProperties(projectBillVo.getWaybill(), transformBill);
            transformBill.setProjectId(projectBillVo.getProject().getProjectId());
            scatteredWaybillCreateVo.setWaybill(transformBill);

            TruckRequire transformRequire = new TruckRequire();
            BeanUtils.copyProperties(projectBillVo.getTruckRequire(), transformRequire);
            scatteredWaybillCreateVo.setTruckRequire(transformRequire);

            WaybillParam transformParam = new WaybillParam();
            BeanUtils.copyProperties(projectBillVo.getWaybillParam(), transformParam);
            scatteredWaybillCreateVo.setWaybillParam(transformParam);


            WaybillCarrierVo transformCarrierVo = new WaybillCarrierVo();
            BeanUtils.copyProperties(projectBillVo.getWaybillCarrierVo(), transformCarrierVo);
            scatteredWaybillCreateVo.setWaybillCarrierVo(transformCarrierVo);

            scatteredWaybillCreateVo.setDestAddress(projectBillVo.getReceiveAddress());
            scatteredWaybillCreateVo.setSrcAddress(projectBillVo.getDeliveryAddress());
//            scatteredWaybillCreateVo.setCreateBatchAmount(projectBillVo.getCreateBatchAmount());
        } else {
            log.warn(LOG_MARKER, "不支持的运单转运类型:" + JSON.toJSONString(transformBill));
            throw new BusinessException("TransformBillDestinationTypeNotSupport", "errors.common.prompt", "不支持的运单转运类型");
        }

        return scatteredWaybillCreateVo;
    }

}
