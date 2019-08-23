package com.juma.tgm.truck.dao;

import com.giants.dal.dao.GiantsDao;
import com.juma.tgm.truck.domain.AdditionalFunction;

import java.util.List;

/**
 * Created by RX on 2016/5/12 0012. 货车符加功能DAO
 */
public interface AdditionalFunctionDao extends GiantsDao<AdditionalFunction> {

    /**
     * 根据附件功能ID得到所有的符加功能
     * 
     * @param list
     *            附件功能ID集合
     * @return
     */
    List<AdditionalFunction> findByIds(List<Integer> list);
}
