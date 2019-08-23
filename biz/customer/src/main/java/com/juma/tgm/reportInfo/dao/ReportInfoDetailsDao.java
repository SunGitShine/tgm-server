package com.juma.tgm.reportInfo.dao;

import com.giants.dal.dao.mybatis.MybatisDao;
import com.juma.tgm.driver.domain.ReportInfoDetails;

import javax.annotation.Resource;
import java.util.List;


/**
 * @ClassName ReportInfoDetailsDao.java
 * @Description 路况报备详情
 * @author Libin.Wei
 * @Date 2017年4月27日 下午5:04:06
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */
@Resource
public interface ReportInfoDetailsDao extends MybatisDao<ReportInfoDetails>{
    /**
     *
     * 按时间排序查询订单号关联的全部报备点
     *
     * @author Shawn_lin
     * @Date 2017年6月21日 下午6:17:09
     * @param
     * @return
     */
    List<ReportInfoDetails> findAllBy(ReportInfoDetails example);
}
