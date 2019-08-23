package com.juma.tgm.capacity.service;

import com.giants.common.tools.Page;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.capacity.domian.vo.CapacityFilter;
import com.juma.tgm.capacity.domian.vo.CapacityQuery;
import com.juma.tgm.common.query.QueryCond;

/**
 * 派车使用
 */

public interface CapacityService {

    /**
     * 获取运力
     *
     * @param queryCond
     * @param loginUser
     * @return
     */
    Page<CapacityQuery> searchCapacity(QueryCond<CapacityFilter> queryCond, LoginUser loginUser);
}
