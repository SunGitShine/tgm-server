package com.juma.tgm.fms.dao.v3.ext;

import com.juma.tgm.fms.domain.v3.vo.AdjustForItemExtFilter;
import com.juma.tgm.fms.domain.v3.vo.AdjustForItemFilter;
import com.juma.tgm.fms.domain.v3.vo.AdjustFreightVo;

public interface AdjustForItemExtMapper {

    /**
     * 调整金额
     *
     * @param adjustForItemExtFilter
     *
     * @return
     */
    AdjustFreightVo selectAdjustFreightByExample(AdjustForItemExtFilter adjustForItemExtFilter);
}