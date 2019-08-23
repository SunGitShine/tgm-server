package com.juma.tgm.user.dao;

import com.giants.dal.dao.mybatis.MybatisDao;
import com.juma.tgm.user.domain.UserRouteDetail;
import org.apache.ibatis.annotations.Param;

public interface UserRouteDetailDao extends MybatisDao<UserRouteDetail> {

    /**
     * 通过masterId 删除
     * @param masterId
     */
    void deleteByMasterId(@Param("masterId") int masterId);

}
