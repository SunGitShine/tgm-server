package com.juma.tgm.project.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.tgm.project.dao.ProjectMapper;
import com.juma.tgm.project.dao.ProjectMemberMapper;
import com.juma.tgm.project.domain.v2.Project;
import com.juma.tgm.project.domain.v2.ProjectMember;
import com.juma.tgm.project.domain.v2.ProjectMemberExample;
import com.juma.tgm.project.service.ProjectMemberService;
import com.juma.tgm.project.vo.v2.ProjectMemberUserVo;
import com.juma.tgm.project.vo.v2.ProjectMemberVo;
import com.juma.tgm.tools.service.AuthCommonService;

/**
 * Title: ProjectMemberServiceImpl
 * Description:
 * Created by gzq on 2019/2/20.
 */
@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {

    @Autowired
    private ProjectMemberMapper projectMemberMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private AuthCommonService authCommonService;


    @Override
    public ProjectMemberVo getProjectMemer(Integer projectId) {
        if(null == projectId){
            return null;
        }
        ProjectMemberVo projectMember = new ProjectMemberVo();
        projectMember.setProjectId(projectId);
        List<ProjectMember> projectMembers = projectMemberMapper.selectByExample(new ProjectMemberExample().createCriteria()
                .andProjectIdEqualTo(projectId).andIsProjectManagerEqualTo(false).example());
        List<ProjectMemberUserVo> projectMemberVos = new ArrayList<ProjectMemberUserVo>();
        if(CollectionUtils.isNotEmpty(projectMembers)){
            for (ProjectMember member:projectMembers) {
                ProjectMemberUserVo vo = new ProjectMemberUserVo();
                BeanUtils.copyProperties(member,vo);
                User user = authCommonService.loadUser(member.getUserId());
                if(null != user){
                    vo.setUserName(user.getName());
                    vo.setUserPhone(user.getMobileNumber());
                }
                projectMemberVos.add(vo);
            }
        }
        projectMember.setProjectMember(projectMemberVos);
        return projectMember;
    }

    @Override
    public void operateAddOrUpdate(ProjectMemberVo projectMember, LoginUser loginUser) throws BusinessException {
        if (projectMember == null){
            throw new BusinessException("projectMembersNull", "errors.paramCanNotNullWithName", "运营专员");
        }
        if (projectMember.getProjectId() == null){
            throw new BusinessException("projectIdNull", "errors.paramCanNotNullWithName", "项目id");
        }
        if (projectMember.getProjectMember() == null){
            throw new BusinessException("projectMemberNull", "errors.paramCanNotNullWithName", "运营专员");
        }
        Project project = projectMapper.selectByPrimaryKey(projectMember.getProjectId());
        if (project == null){
            throw new BusinessException("projectNull", "errors.paramCanNotNullWithName", "项目");
        }
        projectMemberMapper.deleteByExample(new ProjectMemberExample().createCriteria()
                .andProjectIdEqualTo(projectMember.getProjectId()).andIsProjectManagerEqualTo(false).example());
        List<Integer> userIdList = new ArrayList<Integer>();
        for(ProjectMember member:projectMember.getProjectMember()) {
            if(userIdList.contains(member.getUserId())){
                throw new BusinessException("userIdUniqueness", "存在多个相同运营专员");
            }
            userIdList.add(member.getUserId());
            member.setProjectId(projectMember.getProjectId());
            member.setTenantId(loginUser.getTenantId());
            member.setCreateTime(new Date());
            member.setCreateUserId(loginUser.getUserId());
            member.setIsProjectManager(false);
            projectMemberMapper.insert(member);
        }
    }

    @Override
    public List<Integer> findProjectIdsByUserId(Integer userId, Integer tenantId) {

        List<Integer> projectIdList = new ArrayList<>();
        //只查询当前登录人是运营专员或项目经理的项目
        ProjectMemberExample example = new ProjectMemberExample();
        ProjectMemberExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andTenantIdEqualTo(tenantId);
        List<ProjectMember> projectMembers = projectMemberMapper.selectByExample(example);
        for(ProjectMember projectMember : projectMembers){
            projectIdList.add(projectMember.getProjectId());
        }
        return projectIdList;
    }

    @Override
    public boolean existUser(Integer projectId, Integer userId) {
        ProjectMemberExample example = new ProjectMemberExample();
        ProjectMemberExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andProjectIdEqualTo(projectId);
        List<ProjectMember> projectMembers = projectMemberMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(projectMembers)){
            return true;
        }else {
            return false;
        }
    }
}
