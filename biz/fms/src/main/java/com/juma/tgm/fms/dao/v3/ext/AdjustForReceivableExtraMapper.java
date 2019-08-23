package com.juma.tgm.fms.dao.v3.ext;

import com.giants.common.tools.PageCondition;
import com.juma.tgm.fms.domain.v3.AdjustForReceivable;
import com.juma.tgm.fms.domain.v3.AdjustForReceivableExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface AdjustForReceivableExtraMapper {

    int insertSelective(AdjustForReceivable record);

    /**
     * 查询运单最早的一条改价记录
     * @param waybillId
     * @return
     */
    AdjustForReceivable findFirst(Integer waybillId);

    /**
     * 分页查询改价记录
     * 条件：运单id（waybillId）
     * @param pageCondition
     * @return
     */
    List<AdjustForReceivable> findAdjustBypage(PageCondition pageCondition);

    /**
     * 查询改价记录总数
     * 条件：运单id（waybillId）
     * @param pageCondition
     * @return
     */
    Integer findAdjustCount(PageCondition pageCondition);

    /**
     * 查询最后一条改价记录
     * @param waybillId
     * @return
     */
    AdjustForReceivable findLast(Integer waybillId);
}