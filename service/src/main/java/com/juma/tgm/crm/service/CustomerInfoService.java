package com.juma.tgm.crm.service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.Department;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.cms.wx.domain.Chanel;
import com.juma.crm.customer.domain.*;
import com.juma.tgm.crm.domain.*;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.domain.CustomerInfoBo;
import com.juma.tgm.customer.domain.TruckCustomer;
import com.juma.tgm.customer.domain.vo.ScatteredCustomerVo;
import com.juma.tgm.customer.domain.vo.SearchEnterpriseUserAndCargoOwner;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author weilibin
 * @version V1.0
 * @Description: 大客户管理
 * @date 2016年7月1日 上午9:43:54
 */

public interface CustomerInfoService {

    /**
     * @param pageCondition
     * @return Page<Project>
     * @Title: searchDetails
     * @Description: 分页获取项目列表
     */
    Page<CrmCustomerInfo> searchDetails(PageCondition pageCondition, LoginUser loginUser);

    /**
     * @throws
     * @Title: searchUserUnderCustomer
     * @Description: 用户挂载的客户
     * @param: @param pageCondition
     * @param: @return
     * @return: Page<UserUnderCustomer>
     */
    Page<UserUnderCustomer> searchUserUnderCustomer(PageCondition pageCondition, LoginUser loginUser);

    /**
     * @throws
     * @Title: findCrmCustomerInfoByCustomerId
     * @Description: CRM基本信息  业务信息
     * @param: @param customerId
     * @param: @return
     * @return: CrmCustomerInfo
     */
    CrmCustomerInfo findCrmCustomerInfoByCustomerId(Integer customerId, LoginUser loginUser);


    /**
     * 企业客户列表（不含已淘汰的）
     *
     * @param pageCondition
     * @return List<CrmCustomerInfo>
     * @author Libin.Wei
     * @Date 2017年5月8日 下午3:51:38
     */
    List<CrmCustomerInfo> listCrmCustomerInfo(PageCondition pageCondition);

    /**
     * @param pageCondition
     * @param loginEmployee
     * @return List<CustomerInfoExport>
     * @Description 大客户导出列表
     * @author Libin.Wei
     * @Date 2017年2月21日 下午4:08:33
     */
    void asyncExport(final PageCondition pageCondition, final Integer exportTaskId, final LoginUser loginUser) throws Exception;

    /**
     * @param pageCondition
     * @return int
     * @Title: searchCount
     * @Description: 获取总条数
     */
    int searchCount(PageCondition pageCondition);

    //没有找到调用方，暂时注释掉
    //    /**
    //     *
    //     * @Title: findCusInfoBoById
    //     * @Description: 根据ID获取
    //     * @param customerId
    //     * @return CustomerInfoBo
    //     */
    //    CustomerInfoBo findCusInfoBoById(Integer customerId) throws BusinessException;

    /**
     * @param customerName
     * @return CustomerInfo
     * @return isDelete
     * @Title: findCusInfoByName
     * @Description: 根据项目名称获取一条
     */
    CustomerInfo findCusInfoByName(String customerName, Boolean isDelete, LoginUser loginUser) throws BusinessException;

    /**
     * 根据项目名称获取
     *
     * @param customerName 企业客户名称
     * @return List<CustomerInfo>
     * @author Libin.Wei
     * @Date 2017年4月20日 下午2:57:42
     */
    List<CustomerInfo> listCusInfoByName(String customerName, Boolean isDelete, LoginUser loginUser);

    /**
     * @param customerInfoBo
     * @param loginEmployee
     * @Title: insert
     * @Description: 添加
     */
    void insert(CustomerInfo customerInfo, LoginUser loginUser) throws BusinessException;

    /**
     * @param crmCustomerInfo
     * @param loginEmployee
     * @Title: update
     * @Description: 基础修改
     */
    void update(CustomerInfo customerInfo, LoginUser loginUser) throws BusinessException;

    /**
     * @param crmCustomerInfo
     * @param loginEmployee
     * @Title: update
     * @Description: 修改
     */
    void update(CrmCustomerInfo crmCustomerInfo, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * @param customerId
     * @param loginEmployee
     * @Title: delete
     * @Description: 删除
     */
    void delete(Integer customerId, LoginUser loginUser) throws BusinessException;

    /**
     * @param List<CustomerInfo>
     * @Title: findAll
     * @Description: 获取项目列表
     */
    List<CustomerInfo> loadAll();

    /**
     * @param customerId
     * @Title: findCusInfoById
     * @Description: 获取一条
     */
    CustomerInfo findCusInfoById(Integer customerId) throws BusinessException;

    /**
     *
     * @Title: findByUserId
     * @Description: 根据userID获取
     * @param userId
     * @return List<CustomerInfo>
     */
    //    @Deprecated
    //    List<CustomerInfo> findByUserId(Integer userId);

    /**
     * @param customerId
     * @param loginEmployee
     * @Title: updateToEnable
     * @Description: 启用
     */
    void updateToEnable(Integer customerId, LoginEmployee loginEmployee);

    /**
     * @param customerId
     * @param loginEmployee
     * @Title: updateToDisable
     * @Description: 禁用
     */
    void updateToDisable(Integer customerId, LoginEmployee loginEmployee);

    /**
     * @param customerId
     * @throws BusinessException
     * @Title: checkCustomerUsable
     * @Description: 根据ID检查项目是否存在或可用
     */
    CustomerInfo checkCustomerUsable(Integer customerId) throws BusinessException;

    /**
     * 通过customerManagerUserId查询企业客户信息
     *
     * @param customerInfo          企业客户信息
     * @param customerManagerUserId 客户经理ID
     * @return CustomerInfoResp
     * @author Libin.Wei
     * @Date 2017年4月17日 下午3:39:23
     */
    CustomerInfoResp findByEmployeeId(CustomerInfo customerInfo, Integer employeeId);

    /**
     * 仅仅更新大客户基础数据
     *
     * @param customerInfo  customerId 不能为空
     * @param loginEmployee
     */
    void updateCustomerInfoOnly(CustomerInfo customerInfo, LoginEmployee loginEmployee);


    /**
     * 通过loginUserId查询企业客户信息
     *
     * @param loginUserId
     * @return
     */
    List<CustomerInfo> findByLoginUserId(int loginUserId);


    /**
     * 查询当前可用用户id
     *
     * @param pageCondition
     * @return
     */
    Page<CustomerInfo> findCustomerFromTgm(PageCondition pageCondition, LoginUser loginUser) throws BusinessException;


    /**
     * 通过客户经理id获取客户列表
     *
     * @param customerManagerId
     * @return
     */
    List<CustomerInfo> findCustomerInfoByCustomerManagerId(int customerManagerId);

    /********************************crm系统维护大客户信息************************/

    /**
     * 获取客户列表
     *
     * @param condition
     * @param loginEmployee
     * @return
     */
    Page<ConsignorCustomerWholeInfoVo> searchCrmCustomer(PageCondition condition, LoginEmployee loginEmployee) throws BusinessException;

    //确认没有调用这个接口
    //    /**
    //     * 通过多种状态获取客户列表
    //     *
    //     * @param condition
    //     * @param loginEmployee
    //     * @return
    //     * @throws BusinessException
    //     */
    //    Page<ConsignorCustomerInfo> searchCrmCustomerWithMultipleStatus(PageCondition condition, LoginEmployee loginEmployee) throws BusinessException;


    /**
     * 获取状态数量统计
     *
     * @param condition
     * @param loginEmployee
     * @return
     */
    List<CustomerStatusCount> countCustomer(PageCondition condition, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 添加大客户信息到crm 并返回crm用户id
     * @param CrmConsignorInfoBo
     * @param loginUser
     * @return
     */
    //    Integer addCustomerInfoToCrm(CrmConsignorInfoBo CrmConsignorInfoBo, LoginUser loginUser) throws BusinessException;


    /**
     * 更新客户信息
     *
     * @param consignorBaseInfoVo
     * @param loginEmployee
     * @throws BusinessException
     */
    void updateBaseInfo(ConsignorBaseInfoVo consignorBaseInfoVo, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 获取客户全部信息
     *
     * @param crmCustomerId
     * @param loginEmployee
     * @return
     */
    ConsignorCustomerWholeInfoWithTgmInfo getConsignorCustomerWholeInfoWithTgmInfo(int crmCustomerId, LoginEmployee loginEmployee);


    /**
     * 增加拜访记录
     *
     * @param consignorVisitRecordVo
     * @param loginEmployee
     * @throws BusinessException
     */
    void saveVisitationRecord(ConsignorVisitRecordVo consignorVisitRecordVo, LoginEmployee loginEmployee) throws BusinessException;


    /**
     * 修改联系人
     *
     * @param consignorContactsInfo
     * @param loginEmployee
     * @throws BusinessException
     */
    ConsignorContactsInfo updateContact(ConsignorContactsInfo consignorContactsInfo, LoginEmployee loginEmployee) throws BusinessException;


    /**
     * 新增联系人
     *
     * @param consignorContactsInfo
     * @param loginEmployee
     * @return
     * @throws BusinessException
     */
    ConsignorContactsInfo addContactsInfo(ConsignorContactsInfo consignorContactsInfo, LoginEmployee loginEmployee) throws BusinessException;


    /**
     * 删除联系人
     *
     * @param consignorContactsInfo
     * @param loginEmployee
     */
    void deleteContactsInfo(ConsignorContactsInfo consignorContactsInfo, LoginEmployee loginEmployee) throws BusinessException;


    /**
     * 淘汰客户
     *
     * @param consignorEliminationVo 淘汰记录(视为拜访记录)
     * @param loginEmployee          登录用户
     */
    void insertEliminationRecord(ConsignorEliminationVo consignorEliminationVo, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 查询最后一次淘汰记录
     *
     * @param customerId 客户ID
     * @return 淘汰记录
     */
    ConsignorVisitRecord findEliminationRecord(Integer customerId);

    /**
     * 新增企业客户-包含crm信息
     *
     * @param customerInfoBo 必填：consignorBaseInfoVo
     * @param loginEmployee
     * @throws BusinessException
     */
    CustomerInfo addCustomerInfo(CustomerInfoBo customerInfoBo, LoginEmployee loginEmployee) throws BusinessException;


    /**
     * 通过crm Id获取tgm客户信息
     *
     * @param crmId
     * @return
     */
    CustomerInfo findByCrmId(int crmId);

    /**
     * 通过crm Id获取所有tgm客户信息
     *
     * @param crmId
     * @return
     */
    CustomerInfo findAllByCrmId(int crmId);

    /**
     * 增加客户订单数量
     *
     * @param count
     * @param customerId
     * @throws BusinessException
     */
    void increaseWaybillCount(int count, int customerId) throws BusinessException;

    /**
     * 保存本地企业客户信息（crmCustomerId不能为空）
     *
     * @param customerInfo
     * @return
     * @throws BusinessException
     */
    int addLocalBasicCustomerInfo(CustomerInfo customerInfo, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 通过tgm customerId 获取 crm系统中的基础信息
     *
     * @param tgmCustomerId
     * @return
     * @throws BusinessException
     */
    com.juma.crm.customer.domain.CustomerInfo getCrmCustomerInfoByTgmCustomerId(int tgmCustomerId) throws BusinessException;

    /**
     * 通过crmCustomerId获取联系人列表
     *
     * @param crmCustomerId
     * @return
     */
    List<ConsignorContactsInfo> findContactByCustomerId(int crmCustomerId);

    /**
     * 客户来源列表
     *
     * @param code
     * @return
     */
    List<Chanel> findCustomerSourceByCode(String code);

    /**
     * 获取客户来源
     *
     * @param code
     * @return
     */
    Chanel getChanelByCode(String code);

    /**
     * 后台接口
     * 获取所有企业客户及其客户经理;客户经理只有：名字和id
     *
     * @return
     * @throws BusinessException
     */
    CustomerInfoResp findAllCustomerInfoWithManager() throws BusinessException;


    /**
     * 通过联系人名称搜索客户经理下企业客户的联系人列表
     *
     * @param condition
     * @param loginEmployee
     * @return
     * @throws BusinessException
     */
    Page<ConsignorContactsInfo> searchConsignorContactsInfo(PageCondition condition, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 判断企业客户是否属于客户经理
     *
     * @param customerId
     * @param managerId
     * @return
     * @throws BusinessException
     */
    boolean customerBelongToManager(Integer customerId, Integer managerId) throws BusinessException;

    /**
     * 淘汰企业客户
     *
     * @param elimination
     * @param loginEmployee
     * @throws BusinessException
     */
    void eliminate(ConsignorCustomerElimination elimination, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 获取最后一条淘汰纪录
     *
     * @param customerId
     * @return
     * @throws BusinessException
     */
    ConsignorCustomerElimination findLastEliminationByCustomerId(int customerId) throws BusinessException;

    /**
     * 通过企业客户名称和用车人名称搜索TGM相应数据
     *
     * @return
     * @throws BusinessException
     */
    Page<SearchEnterpriseUserAndCargoOwner> searchEnterpriseUserAndCargoOwner(PageCondition pageCondition, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 获取TMS系统但客户信息
     *
     * @param pageCondition
     * @return List<CustomerInfo>
     * @author Libin.Wei
     * @Date 2017年6月15日 下午1:42:15
     */
    List<CustomerInfo> listCustomerInfo(PageCondition pageCondition);

    /**
     * @throws
     * @Title: getRebateRate
     * @Description: 客户返点率
     * @return: BigDecimal
     */
    BigDecimal getRebateRate(Integer tgmCustomerId);

    /**
     * 落地配客户管理
     *
     * @param pageCondition
     * @param type          成单状态
     * @return
     * @throws BusinessException
     */
    Page<ScatteredCustomerVo> scatteredCustomerSearch(PageCondition pageCondition, ScatteredCustomerVo.CustomerType type) throws BusinessException;

    /**
     * 统计客户成单量
     *
     * @param customerManagerId
     * @param type              成单状态
     * @return
     * @throws BusinessException
     */
    ScatteredCustomerVo.OverViewDataVo countOverViewData(int customerManagerId, ScatteredCustomerVo.CustomerType type) throws BusinessException;

    /**
     * 获取crm联系人货主
     *
     * @param crmCustomerId
     * @param loginEmployee
     * @return
     * @throws BusinessException
     */
    List<TruckCustomer> findCargoOwnerFromCrm(int crmCustomerId, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 通过货主获取联系人信息
     *
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    ConsignorContactsInfo findConsignorContactsByUser(LoginUser loginUser) throws BusinessException;

    /**
     * 添加联系人到客户下
     *
     * @param customerInfo
     * @param loginUser
     * @throws BusinessException
     */
    void addContactToCustomer(CustomerInfo customerInfo, LoginUser loginUser) throws BusinessException;

    /**
     *
     * 根据crm货主ID批量更新
     * @author Libin.Wei
     * @Date 2018年1月18日 上午10:20:33
     * @throws BusinessException
     */
    void updateBatch(List<CustomerInfo> example, LoginUser loginUser) throws BusinessException;

    /**
     *
     * 根据业务区域或客户经理迁移
     * @author Libin.Wei
     * @Date 2018年1月18日 上午10:40:08
     * @param toCustomerInfo
     * @param fromCustomerInfo
     * @throws BusinessException
     */
    void update(CustomerInfo toCustomerInfo, CustomerInfo fromCustomerInfo) throws BusinessException;

    /**
     * 通过客户id查询子公司信息
     * @param crmCustomerId
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    Department findDepartmentByCustomerId(Integer crmCustomerId, LoginUser loginUser) throws BusinessException;

}
