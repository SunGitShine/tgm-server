package com.juma.tgm.importantNotice.service;

import java.util.List;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.tgm.importantNotice.domain.ImportantNotice;

/**
 * 重要通知
 * 
 * @author weilibin
 *
 */
public interface ImportantNoticeService {

    /**
     * 
     * 分页获取通知列表
     * 
     * @author Libin.Wei
     * @Date 2017年5月23日 上午9:40:32
     * @param pageQueryCondition
     * @return Page<ImportantNotice>
     */
    Page<ImportantNotice> search(PageCondition pageCondition, LoginUser loginUser);

    /**
     * 
     * 根据主键ID获取
     * 
     * @author Libin.Wei
     * @Date 2017年5月23日 上午9:42:02
     * @param noticeId
     * @return ImportantNotice
     */
    ImportantNotice getImportantNotice(Integer noticeId);

    /**
     * 
     * 未读人员列表
     * 
     * @author Libin.Wei
     * @Date 2017年8月10日 下午2:46:32
     * @param noticeId
     * @return List<User>
     * @throws BusinessException
     */
    Page<User> unReadUserList(Integer noticeId, int pageNo, int pageSize) throws BusinessException;

    /**
     * 
     * @Title: insert @Description: 新增通知 @param importantNoticeBo @param
     * loginEmployee @throws
     */
    void insert(ImportantNotice importantNotice, LoginUser loginUser) throws BusinessException;

    /**
     * @Title: updateToDisable
     * @Description: 启用
     * @param noticeId
     * @param loginEmployee
     */
    void updateToEnable(Integer noticeId, LoginUser loginUser) throws BusinessException;

    /**
     * @Title: updateToDisable
     * @Description: 停用
     * @param noticeId
     * @param loginEmployee
     */
    void updateToDisable(Integer noticeId, LoginUser loginUser) throws BusinessException;
}
