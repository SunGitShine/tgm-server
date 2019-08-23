package com.juma.tgm.waybill.dao;

import com.giants.common.tools.PageCondition;
import com.juma.tgm.waybill.domain.view.WaybillViewVo;

import java.util.List;

/**
 * @ClassName: WaybillViewDao
 * @Description:
 * @author: liang
 * @date: 2018-01-30 14:58
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public interface WaybillViewDao {

    int searchCount(PageCondition pageCondition);

    List<WaybillViewVo> searchForApp(PageCondition pageCondition);
}
