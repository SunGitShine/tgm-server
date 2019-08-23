package com.juma.tgm.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.PageCondition;
import com.giants.dal.dao.mybatis.MybatisDao;
import com.juma.cms.wx.domain.Chanel;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.customer.domain.vo.SearchEnterpriseUserAndCargoOwner;

/**
 * 
 * @Description: 项目DAO
 * @author weilibin
 * @date 2016年6月30日 下午6:38:05
 * @version V1.0
 */
public interface CustomerInfoDao extends MybatisDao<CustomerInfo> {

    /**
     * 增加客户订单数量
     * 
     * @param count
     * @param customerId
     * @throws BusinessException
     */
    void increaseWaybillCount(@Param("count") int count, @Param("customerId") int customerId);

    /**
     * 获取相应渠道下的企业客户
     * 
     * @param chanel
     * @return
     */
    List<CustomerInfo> findBySourceChanelCode(@Param("chanel") List<Chanel> chanel);

    /**
     * 判断企业客户是否属于客户经理
     * 
     * @param customerId
     * @param managerId
     * @return
     */
    int countByCustomerIdAndManagerId(@Param("customerId") int customerId, @Param("managerId") int managerId);

    /**
     *
     * @param pageCondition
     * @return
     */
    List<SearchEnterpriseUserAndCargoOwner> searchEnterpriseUserAndCargoOwner(PageCondition pageCondition);


    /**
     * 落地配-经纪人-客户管理
     * @param pageCondition
     * @return
     */
    List<CustomerInfo> scatteredCustomerSearch(PageCondition pageCondition);

    /**
     * 落地配-经纪人-客户管理
     * @param pageCondition
     * @return
     */
    int scatteredCustomerSearchCount(PageCondition pageCondition);

    /**
     * 
     * 根据crm货主ID批量更新
     * @author Libin.Wei
     * @Date 2018年1月18日 上午10:20:33
     * @throws BusinessException
     */
    void updateBatch(@Param("example") List<CustomerInfo> example);

    /**
     * 
     * 根据业务区域或客户经理迁移
     * @author Libin.Wei
     * @Date 2018年1月18日 上午10:40:08
     * @param toCustomerInfo
     * @param fromCustomerInfo
     * @throws BusinessException
     */
    void updateByAreaOrManager(@Param("toCustomerInfo") CustomerInfo toCustomerInfo, @Param("fromCustomerInfo") CustomerInfo fromCustomerInfo);
}
