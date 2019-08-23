package com.juma.tgm.project.service;

import java.util.List;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.project.vo.v2.ProjectMemberVo;

/**
 * Title: ProjectMemberService
 * Description:
 * Created by gzq on 2019/2/20.
 */

public interface ProjectMemberService {

    /**
     * 根据项目ID获取
     *
     * @param projectId
     * @return
     */
    ProjectMemberVo getProjectMemer(Integer projectId);

    /**
     * 运营专员添加或修改
     *
     * @param projectMember
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    void operateAddOrUpdate(ProjectMemberVo projectMember, LoginUser loginUser) throws BusinessException;

    /**
     * 查询项目经理或运营专员的项目id列表
     * @param userId
     * @return
     */
    List<Integer> findProjectIdsByUserId(Integer userId, Integer tenantId);

    boolean existUser(Integer projectId, Integer userId);
}
