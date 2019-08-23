package com.juma.tgm.waybill.dao;

import com.giants.dal.dao.mybatis.MybatisDao;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.domain.erp.ErpWaybillResult;
import com.juma.tgm.waybill.domain.vo.WaybillParamFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WaybillParamDao extends MybatisDao<WaybillParam> {

    /**
     * 根据运单ID获取费用
     * 
     * @param waybillId
     * @return WaybillParam
     */
    WaybillParam findByWaybillId(Integer waybillId);

    /**
     * 根据运单ID集合获取费用
     * 
     * @param list
     * @return WaybillParam
     */
    WaybillParam findCountCost(List<Integer> list);

    /**
     * 
     * erp结果
     * @author Libin.Wei
     * @Date 2017年11月13日 下午4:20:50
     * @param rows
     */
    void batchUpdateErpResult(@Param("list") List<ErpWaybillResult> rows);
    
    /**
     * 
     * @Title: findProjectWaybill   
     * @Description: 项目运单
     * @param:       
     * @return: List<WaybillParam>      
     * @throws
     */
    List<WaybillParam> findProjectWaybill();

    /**批量从查询接口实现**/
    List<WaybillParam> selectByExample(WaybillParamFilter filter);
}
