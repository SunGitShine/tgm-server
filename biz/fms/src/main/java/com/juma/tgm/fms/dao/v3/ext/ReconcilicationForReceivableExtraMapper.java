package com.juma.tgm.fms.dao.v3.ext;

import com.giants.common.tools.PageCondition;
import com.juma.tgm.fms.domain.v3.ReconcilicationForReceivable;
import com.juma.tgm.fms.domain.v3.ReconcilicationForReceivableExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface ReconcilicationForReceivableExtraMapper {

    int insertSelective(ReconcilicationForReceivable record);

    /**
     * 查询客户对账单总数
     * @param pageCondition
     * @return
     */
    Integer findReceivableCount(PageCondition pageCondition);

    /**
     * 分页查询客户对账单
     * @param pageCondition
     * @return
     */
    List<ReconcilicationForReceivable> findReceivablePage(PageCondition pageCondition);
}