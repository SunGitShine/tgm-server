package com.juma.tgm.project.vo;

import com.alibaba.fastjson.JSON;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.project.domain.v2.enums.ProjectEnum;
import org.apache.commons.lang.math.NumberUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目列表按钮控制
 */

public class ProjectButtonCtrl implements Serializable {

    // 编辑按钮
    private boolean modifyBtn;
    // 启动按钮
    private boolean startUpBtn;
    // 撤销启动申请
    private boolean revokeStartBtn;
    // 修改启动申请
    private boolean modifyStartBtn;
    // 暂停按钮
    private boolean suspendBtn;
    // 撤销暂停申请
    private boolean revokeSuspendBtn;
    // 修改暂停申请
    private boolean modifySuspendBtn;
    // 恢复按钮
    private boolean recoveryBtn;
    // 撤销恢复申请
    private boolean revokeRecoveryBtn;
    // 修改恢复申请
    private boolean modifyRecoveryBtn;
    // 结束按钮
    private boolean endBtn;
    // 撤销结束申请
    private boolean revokeEndBtn;
    // 修改结束申请
    private boolean modifyEndBtn;
    // 转正式运行
    private boolean toRealRunBtn;
    // 变更合同
    private boolean changeContractBtn;
    // 续签合同
    private boolean renewalContractBtn;
    // 删除项目
    private boolean deleteBtn;

    public ProjectButtonCtrl() {

    }

    // projectEndTime:项目结束时间，project.projectEndDate或projectWorkflow.excute_time,有审批流程则为执行时间
    // term:期限
    public ProjectButtonCtrl(Integer projectStatus, Integer projectType, Integer auditStatus,
                             Integer projectWorkflowType, Date excuteTime, Integer term, Integer createUserId,
                             Integer projectManagerUserId, Boolean isCityManager, LoginUser loginUser) {
        if (null == projectStatus || null == projectType) {
            return;
        }

        isCityManager = isCityManager == null ? false : isCityManager;

        // 申请启动相关控制
        startUpBtn(projectStatus, auditStatus, projectWorkflowType, createUserId, projectManagerUserId, isCityManager, loginUser);

        // 编辑按钮：项目不是未启动状态，只有当前登录人是项目经理或城市经理时，可编辑
        if ((null == projectManagerUserId || NumberUtils.compare(projectManagerUserId, loginUser.getUserId()) != 0) && !isCityManager) {
            return;
        }

        this.modifyBtn = true;

        // 申请暂停相关控制
        suspendBtn(projectStatus, auditStatus, projectWorkflowType);
        // 申请恢复相关控制
        recoveryBtn(projectStatus, auditStatus, projectWorkflowType);
        // 申请结束相关控制
        endBtn(projectStatus, auditStatus, projectWorkflowType);

        // 转正式运行：试运行、运行中、未在审批流程或审批流程已结束
        this.toRealRunBtn =
                this.suspendBtn  && this.endBtn && (projectType == ProjectEnum.ProjectType.TEST_RUN.getCode());
        // 合同变更：正式运行、运行中、未在审批流程或审批流程已结束
        this.changeContractBtn =
                this.suspendBtn && this.endBtn && (projectType == ProjectEnum.ProjectType.REAL_RUN.getCode());

        // 项目状态为已结束相关控制
        renewalContractBtn(projectStatus, excuteTime, term);

    }


    // 申请启动相关按钮控制
    private void startUpBtn(Integer projectStatus, Integer auditStatus, Integer projectWorkflowType, Integer createUserId,
                            Integer projectManagerUserId, Boolean isCityManager, LoginUser loginUser) {
        // 启动控制,项目状态为未启动
        if (projectStatus != ProjectEnum.ProjectStatus.NO_START.getCode()) {
            return;
        }

        // 编辑与删除按钮：当前登录人是项目创建人或项目经理、城市经理，可删除
        if ((null != createUserId && NumberUtils.compare(createUserId, loginUser.getUserId()) == 0)
                || (null != projectManagerUserId && NumberUtils.compare(projectManagerUserId, loginUser.getUserId()) == 0)
                || isCityManager) {
            this.modifyBtn = true;
            this.deleteBtn = true;
        }

        // 当前登录人不是项目经理且不是城市经理，以下按钮都不展示
        if ((null == projectManagerUserId || NumberUtils.compare(projectManagerUserId, loginUser.getUserId()) != 0) && !isCityManager) {
            return;
        }

        // 启动审核已通过
        if (null != auditStatus && auditStatus == ProjectEnum.AuditStatus.AGREE.getCode()
                && null != projectWorkflowType && projectWorkflowType == ProjectEnum.WorkflowType.START.getCode()) {
            this.deleteBtn = false;
            return;
        }

        // 工作流为启动审核
        if (null != projectWorkflowType && projectWorkflowType == ProjectEnum.WorkflowType.START.getCode()) {
            // 审核中状态，显示
            if (null != auditStatus && auditStatus == ProjectEnum.AuditStatus.SUBMIT.getCode()) {
                this.revokeStartBtn = true;
            }
            // 被驳回状态，显示
            if (null != auditStatus && auditStatus == ProjectEnum.AuditStatus.REJECT.getCode()) {
                this.modifyStartBtn = true;
            }
        }

        // 启动申请按钮与撤销申请、修改申请互斥，与删除按钮并存
        this.startUpBtn = !(this.revokeStartBtn || this.modifyStartBtn);
        this.deleteBtn = this.startUpBtn;
    }

    // 申请暂停相关控制
    private void suspendBtn(Integer projectStatus, Integer auditStatus, Integer projectWorkflowType) {
        // 暂停控制，项目状态为运行中
        if (projectStatus != ProjectEnum.ProjectStatus.RUNING.getCode()) {
            return;
        }

        // 结束审批状态为审核中、已通过、被驳回，申请暂停不可用
        if (null != auditStatus && (auditStatus == ProjectEnum.AuditStatus.SUBMIT.getCode()
                || auditStatus == ProjectEnum.AuditStatus.AGREE.getCode()
                || auditStatus == ProjectEnum.AuditStatus.REJECT.getCode())
                && null != projectWorkflowType && projectWorkflowType == ProjectEnum.WorkflowType.END.getCode()) {
            return;
        }


        // 暂停审批状态为已通过
        if (null != auditStatus && auditStatus == ProjectEnum.AuditStatus.AGREE.getCode()
                && null != projectWorkflowType && projectWorkflowType == ProjectEnum.WorkflowType.PAUSE.getCode()) {
            return;
        }

        this.suspendBtn = true;

        // 工作流为暂停审核
        if (null == projectWorkflowType || projectWorkflowType != ProjectEnum.WorkflowType.PAUSE.getCode()) {
            return;
        }

        // 审核中状态，显示
        if (null != auditStatus && auditStatus == ProjectEnum.AuditStatus.SUBMIT.getCode()) {
            this.revokeSuspendBtn = true;
        }

        // 被驳回状态，显示
        if (null != auditStatus && auditStatus == ProjectEnum.AuditStatus.REJECT.getCode()) {
            this.modifySuspendBtn = true;
        }

        // 暂停申请按钮与撤销申请、修改申请互斥
        this.suspendBtn = !(this.revokeSuspendBtn || this.modifySuspendBtn);
    }

    // 申请恢复相关控制
    private void recoveryBtn(Integer projectStatus, Integer auditStatus, Integer projectWorkflowType) {
        // 恢复控制，项目状态为已暂停
        if (projectStatus != ProjectEnum.ProjectStatus.PAUSE.getCode()) {
            return;
        }

        // 结束审核状态为审核中、已通过、被驳回，恢复不可用
        if (null != auditStatus
                && (auditStatus == ProjectEnum.AuditStatus.SUBMIT.getCode()
                || auditStatus == ProjectEnum.AuditStatus.AGREE.getCode()
                || auditStatus == ProjectEnum.AuditStatus.REJECT.getCode())
                && null != projectWorkflowType && projectWorkflowType == ProjectEnum.WorkflowType.END.getCode()) {
            return;
        }

        // 恢复审核通过
        if (null != auditStatus && auditStatus == ProjectEnum.AuditStatus.AGREE.getCode()
                && null != projectWorkflowType && projectWorkflowType == ProjectEnum.WorkflowType.RECOVER.getCode()) {
            return;
        }

        this.recoveryBtn = true;
        // 工作流为恢复审核
        if (null == projectWorkflowType || projectWorkflowType != ProjectEnum.WorkflowType.RECOVER.getCode()) {
            return;
        }

        // 审核中状态
        if (null != auditStatus && auditStatus == ProjectEnum.AuditStatus.SUBMIT.getCode()) {
            this.revokeRecoveryBtn = true;
        }

        // 被驳回状态
        if (null != auditStatus && auditStatus == ProjectEnum.AuditStatus.REJECT.getCode()) {
            this.modifyRecoveryBtn = true;
        }

        // 恢复申请按钮与撤销申请、修改申请互斥
        this.recoveryBtn = !(this.revokeRecoveryBtn || this.modifyRecoveryBtn);
    }

    // 申请结束相关控制
    private void endBtn(Integer projectStatus, Integer auditStatus, Integer projectWorkflowType) {
        // 结束控制，项目状态为运行中或已暂停
        if (projectStatus != ProjectEnum.ProjectStatus.RUNING.getCode()
                && projectStatus != ProjectEnum.ProjectStatus.PAUSE.getCode()) {
            return;
        }

        // 结束申请与暂停申请或回复申请并存
        this.endBtn = this.suspendBtn || this.recoveryBtn;

        // 工作流为结束审核
        if (null != projectWorkflowType && projectWorkflowType == ProjectEnum.WorkflowType.END.getCode()) {
            // 项审核中状态，显示
            if (null != auditStatus && auditStatus == ProjectEnum.AuditStatus.SUBMIT.getCode()) {
                this.revokeEndBtn = true;
            }
            // 被驳回状态，显示
            if (null != auditStatus && auditStatus == ProjectEnum.AuditStatus.REJECT.getCode()) {
                this.modifyEndBtn = true;
                this.endBtn = false;
            }
        }
    }

    // 项目状态为已结束相关控制
    private void renewalContractBtn(Integer projectStatus, Date excuteTime, Integer term) {
        // 项目状态为已结束且项目运作类型为正式运行，显示
        if (projectStatus != ProjectEnum.ProjectStatus.END.getCode()) {
            return;
        }

        this.renewalContractBtn = true;
        // 判断有效期限
        if (null != excuteTime && null != term && DateUtil.compare(new Date(), DateUtil.addDays(excuteTime,
                term), DateUtil.YYYYMMDD) == 1) {
            this.renewalContractBtn = false;
            this.modifyBtn = false;
        }
    }

    public boolean isModifyBtn() {
        return modifyBtn;
    }

    public void setModifyBtn(boolean modifyBtn) {
        this.modifyBtn = modifyBtn;
    }

    public boolean isStartUpBtn() {
        return startUpBtn;
    }

    public void setStartUpBtn(boolean startUpBtn) {
        this.startUpBtn = startUpBtn;
    }

    public boolean isRevokeStartBtn() {
        return revokeStartBtn;
    }

    public void setRevokeStartBtn(boolean revokeStartBtn) {
        this.revokeStartBtn = revokeStartBtn;
    }

    public boolean isModifyStartBtn() {
        return modifyStartBtn;
    }

    public void setModifyStartBtn(boolean modifyStartBtn) {
        this.modifyStartBtn = modifyStartBtn;
    }

    public boolean isSuspendBtn() {
        return suspendBtn;
    }

    public void setSuspendBtn(boolean suspendBtn) {
        this.suspendBtn = suspendBtn;
    }

    public boolean isRevokeSuspendBtn() {
        return revokeSuspendBtn;
    }

    public void setRevokeSuspendBtn(boolean revokeSuspendBtn) {
        this.revokeSuspendBtn = revokeSuspendBtn;
    }

    public boolean isModifySuspendBtn() {
        return modifySuspendBtn;
    }

    public void setModifySuspendBtn(boolean modifySuspendBtn) {
        this.modifySuspendBtn = modifySuspendBtn;
    }

    public boolean isRecoveryBtn() {
        return recoveryBtn;
    }

    public void setRecoveryBtn(boolean recoveryBtn) {
        this.recoveryBtn = recoveryBtn;
    }

    public boolean isRevokeRecoveryBtn() {
        return revokeRecoveryBtn;
    }

    public void setRevokeRecoveryBtn(boolean revokeRecoveryBtn) {
        this.revokeRecoveryBtn = revokeRecoveryBtn;
    }

    public boolean isModifyRecoveryBtn() {
        return modifyRecoveryBtn;
    }

    public void setModifyRecoveryBtn(boolean modifyRecoveryBtn) {
        this.modifyRecoveryBtn = modifyRecoveryBtn;
    }

    public boolean isEndBtn() {
        return endBtn;
    }

    public void setEndBtn(boolean endBtn) {
        this.endBtn = endBtn;
    }

    public boolean isRevokeEndBtn() {
        return revokeEndBtn;
    }

    public void setRevokeEndBtn(boolean revokeEndBtn) {
        this.revokeEndBtn = revokeEndBtn;
    }

    public boolean isModifyEndBtn() {
        return modifyEndBtn;
    }

    public void setModifyEndBtn(boolean modifyEndBtn) {
        this.modifyEndBtn = modifyEndBtn;
    }

    public boolean isToRealRunBtn() {
        return toRealRunBtn;
    }

    public void setToRealRunBtn(boolean toRealRunBtn) {
        this.toRealRunBtn = toRealRunBtn;
    }

    public boolean isChangeContractBtn() {
        return changeContractBtn;
    }

    public void setChangeContractBtn(boolean changeContractBtn) {
        this.changeContractBtn = changeContractBtn;
    }

    public boolean isRenewalContractBtn() {
        return renewalContractBtn;
    }

    public void setRenewalContractBtn(boolean renewalContractBtn) {
        this.renewalContractBtn = renewalContractBtn;
    }

    public boolean isDeleteBtn() {
        return deleteBtn;
    }

    public void setDeleteBtn(boolean deleteBtn) {
        this.deleteBtn = deleteBtn;
    }

    public static void main(String[] args) {
        // START(1,"启动"),PAUSE(2,"暂停"),RECOVER(3,"恢复"),END(4,"结束");
        // NO_START(1,"未启动"),RUNING(2,"运行中"),PAUSE(3,"已暂停"),END(4,"已结束");
        // Append(0,"UNSUBMIT","未提交"),SUBMIT(1,"AUDITING","审核中"),REJECT(2,"REJECT","被驳回"),AGREE(3,"AUDITED","已通过"),CANCEL(4,"CANCEL","已取消");
        //        未启动	试运行		编辑、对账权限、启动申请、删除
//        ProjectButtonCtrl(Integer projectStatus, Integer projectType, Integer auditStatus,
//                Integer projectWorkflowType, Date excuteTime, Integer term, Integer createUserId,
//                Integer projectManagerUserId, Boolean isCityManager, LoginUser loginUser)
//        ProjectButtonCtrl ctrl = new ProjectButtonCtrl(1, 1, null, null, new Date(), 15, 1, 2, true, new LoginUser(2,1));
//        System.out.println("未启动 试运行 编辑、对账权限、启动申请、删除");
//        System.out.println(JSON.toJSONString(ctrl));
////        未启动	试运行	启动-审核中	编辑、对账权限、撤销启动
//        ctrl = new ProjectButtonCtrl(1, 1, 1, 1, new Date(), 15,1, 1, false, new LoginUser(2,1));
//        System.out.println("未启动 试运行 启动-审核中 编辑、对账权限、撤销启动");
//        System.out.println(JSON.toJSONString(ctrl));
//////        未启动	试运行	启动-被驳回	编辑、对账权限、修改申请
//        ctrl = new ProjectButtonCtrl(1, 1, 2, 1, new Date(), 15,1, 2, true, new LoginUser(2,1));
//        System.out.println("未启动 试运行 启动-被驳回 编辑、对账权限、修改申请");
//        System.out.println(JSON.toJSONString(ctrl));
//        //        未启动	试运行	启动-已取消	编辑、对账权限、修改申请、删除
//        ctrl = new ProjectButtonCtrl(1, 1, 4, 1, new Date(), 15);
//        System.out.println("未启动 试运行 启动-已取消 编辑、对账权限、申请启动、删除");
//        System.out.println(JSON.toJSONString(ctrl));
////        未启动	试运行	启动-已通过	编辑、对账权限
//        ctrl = new ProjectButtonCtrl(1, 1, 3, 1, new Date(), 15);
//        System.out.println("未启动 试运行 启动-已通过 编辑、对账权限");
//        System.out.println(JSON.toJSONString(ctrl));
////        运行中	试运行		编辑、对账权限、转正式运行、申请暂停、申请结束
//        ctrl = new ProjectButtonCtrl(2, 1, 3, 1, new Date(), 15);
//        System.out.println("运行中 试运行 编辑、对账权限、转正式运行、申请暂停、申请结束");
//        System.out.println(JSON.toJSONString(ctrl));
////        运行中	试运行	暂停-审核中	编辑、对账权限、撤销暂停
//        ctrl = new ProjectButtonCtrl(2, 1, 1, 2, new Date(), 15);
//        System.out.println("运行中 试运行 暂停-审核中 编辑、对账权限、撤销暂停");
//        System.out.println(JSON.toJSONString(ctrl));
////        运行中	试运行	暂停-被驳回	编辑、对账权限、转正式运行、修改申请、申请结束
//        ctrl = new ProjectButtonCtrl(2, 1, 2, 2, new Date(), 15);
//        System.out.println("运行中 试运行 暂停-被驳回 编辑、对账权限、修改申请");
//        System.out.println(JSON.toJSONString(ctrl));
//        //        运行中	试运行	暂停-已取消	编辑、对账权限、转正式运行、修改申请、申请结束
//        ctrl = new ProjectButtonCtrl(2, 1, 4, 2, new Date(), 15);
//        System.out.println("运行中 试运行 暂停-已取消 编辑、对账权限、转正式运行、申请暂停、申请结束");
//        System.out.println(JSON.toJSONString(ctrl));
////        运行中	试运行	暂停-已通过	编辑、对账权限
//        ctrl = new ProjectButtonCtrl(2, 1, 3, 2, new Date(), 15);
//        System.out.println("运行中 试运行 暂停-已通过 编辑、对账权限");
//        System.out.println(JSON.toJSONString(ctrl));
////        已暂停	试运行		编辑、对账权限、申请恢复、申请结束
//        ctrl = new ProjectButtonCtrl(3, 1, 3, 2, new Date(), 15);
//        System.out.println("已暂停 试运行 编辑、对账权限、申请恢复、申请结束");
//        System.out.println(JSON.toJSONString(ctrl));
////        已暂停	试运行	恢复-审核中	编辑、对账权限、撤销恢复
//        ctrl = new ProjectButtonCtrl(3, 1, 1, 3, new Date(), 15);
//        System.out.println("已暂停 试运行 恢复-审核中 编辑、对账权限、撤销恢复");
//        System.out.println(JSON.toJSONString(ctrl));
////        已暂停	试运行	恢复-被驳回	编辑、对账权限、修改申请、申请结束
//        ctrl = new ProjectButtonCtrl(3, 1, 2, 3, new Date(), 15);
//        System.out.println("已暂停 试运行 恢复-被驳回 编辑、修改申请");
//        System.out.println(JSON.toJSONString(ctrl));
//        //        已暂停	试运行	恢复-已取消	编辑、对账权限、修改申请、申请结束
//        ctrl = new ProjectButtonCtrl(3, 1, 4, 3, new Date(), 15);
//        System.out.println("已暂停 试运行 恢复-已取消 编辑、对账权限、申请恢复、申请结束");
//        System.out.println(JSON.toJSONString(ctrl));
////        已暂停	试运行	恢复-已通过	编辑、对账权限
//        ctrl = new ProjectButtonCtrl(3, 1, 3, 3, new Date(), 15);
//        System.out.println("已暂停 试运行 恢复-已通过 编辑、对账权限");
//        System.out.println(JSON.toJSONString(ctrl));
////        运行中	试运行		编辑、对账权限、转正式运行、申请暂停、申请结束
//        ctrl = new ProjectButtonCtrl(2, 1, 3, 3, new Date(), 15);
//        System.out.println("运行中 试运行 编辑、对账权限、转正式运行、申请暂停、申请结束");
//        System.out.println(JSON.toJSONString(ctrl));
////        运行中	试运行	结束-审核中	编辑、对账权限、撤销结束
//        ctrl = new ProjectButtonCtrl(2, 1, 1, 4, new Date(), 15);
//        System.out.println("运行中 试运行 结束-审核中 编辑、对账权限、撤销结束");
//        System.out.println(JSON.toJSONString(ctrl));
////        运行中	试运行	结束-被驳回	编辑、对账权限、转正式运行、申请暂停、修改申请
//        ctrl = new ProjectButtonCtrl(2, 1, 2, 4, new Date(), 15);
//        System.out.println("运行中 试运行 结束-被驳回 编辑、对账权限、修改申请");
//        System.out.println(JSON.toJSONString(ctrl));
//        //        运行中	试运行	结束-已取消	编辑、对账权限、转正式运行、申请暂停、修改申请
//        ctrl = new ProjectButtonCtrl(2, 1, 4, 4, new Date(), 15);
//        System.out.println("运行中 试运行 结束-已取消 编辑、对账权限、转正式运行、申请暂停、申请结束");
//        System.out.println(JSON.toJSONString(ctrl));
////        运行中	试运行	结束-已通过	编辑、对账权限
//        ctrl = new ProjectButtonCtrl(2, 1, 3, 4, new Date(), 15);
//        System.out.println("运行中 试运行 结束-已通过 编辑、对账权限");
//        System.out.println(JSON.toJSONString(ctrl));
////        已结束	试运行		编辑、对账权限
//        ctrl = new ProjectButtonCtrl(4, 1, 3, 4, new Date(), 15);
//        System.out.println("已结束 试运行 编辑、对账权限");
//        System.out.println(JSON.toJSONString(ctrl));
////        已结束	试运行	结束-已通过	对账权限
//        ctrl = new ProjectButtonCtrl(4, 1, 3, 4, DateUtil.addDays(new Date(), -16), 15);
//        System.out.println("已结束 试运行 结束-已通过 对账权限");
//        System.out.println(JSON.toJSONString(ctrl));
////        已暂停	试运行	结束-审核中	编辑、对账权限、撤销结束
//        ctrl = new ProjectButtonCtrl(3, 1, 1, 4, new Date(), 15);
//        System.out.println("已暂停 试运行 结束-审核中 编辑、对账权限、撤销结束");
//        System.out.println(JSON.toJSONString(ctrl));
////        已暂停	试运行	结束-被驳回	编辑、对账权限、申请恢复、修改申请
//        ctrl = new ProjectButtonCtrl(3, 1, 2, 4, new Date(), 15);
//        System.out.println("已暂停 试运行 结束-被驳回 编辑、对账权限、修改申请");
//        System.out.println(JSON.toJSONString(ctrl));
//        //        已暂停	试运行	结束-已取消	编辑、对账权限、申请恢复、修改申请
//        ctrl = new ProjectButtonCtrl(3, 1, 4, 4, new Date(), 15);
//        System.out.println("已暂停 试运行 结束-已取消 编辑、对账权限、申请恢复、申请结束");
//        System.out.println(JSON.toJSONString(ctrl));
////        已暂停	试运行	结束-已通过	编辑、对账权限
//        ctrl = new ProjectButtonCtrl(3, 1, 3, 4, new Date(), 15);
//        System.out.println("已暂停 试运行 结束-已通过 编辑、对账权限");
//        System.out.println(JSON.toJSONString(ctrl));
////        已结束	试运行		编辑、对账权限
//        ctrl = new ProjectButtonCtrl(4, 1, 3, 4, new Date(), 15);
//        System.out.println("已结束 试运行 编辑、对账权限");
//        System.out.println(JSON.toJSONString(ctrl));
////        已结束	试运行		对账权限
//        ctrl = new ProjectButtonCtrl(4, 1, 3, 4, DateUtil.addDays(new Date(), -16), 15);
//        System.out.println("已结束 试运行 对账权限");
//        System.out.println(JSON.toJSONString(ctrl));
////
////        未启动	正式运行		编辑、对账权限、申请启动、删除
//        ctrl = new ProjectButtonCtrl(1, 2, null, null, new Date(), 15);
//        System.out.println("未启动 正式运行 编辑、对账权限、申请启动、删除");
//        System.out.println(JSON.toJSONString(ctrl));
////        未启动	正式运行	启动-审核中	编辑、对账权限、撤销启动
//        ctrl = new ProjectButtonCtrl(1, 2, 1, 1, new Date(), 15);
//        System.out.println("未启动 正式运行 启动-审核中 编辑、对账权限、撤销启动");
//        System.out.println(JSON.toJSONString(ctrl));
////        未启动	正式运行	启动-被驳回	编辑、对账权限、修改申请、删除
//        ctrl = new ProjectButtonCtrl(1, 2, 2, 1, new Date(), 15);
//        System.out.println("未启动 正式运行 启动-被驳回 编辑、对账权限、修改申请");
//        System.out.println(JSON.toJSONString(ctrl));
//        //        未启动	正式运行	启动-已取消	编辑、对账权限、修改申请、删除
//        ctrl = new ProjectButtonCtrl(1, 2, 4, 1, new Date(), 15);
//        System.out.println("未启动 正式运行 启动-已取消 编辑、对账权限、申请启动、删除");
//        System.out.println(JSON.toJSONString(ctrl));
////        未启动	正式运行	启动-已通过	编辑、对账权限
//        ctrl = new ProjectButtonCtrl(1, 2, 3, 1, new Date(), 15);
//        System.out.println("未启动 正式运行 启动-已通过 编辑、对账权限");
//        System.out.println(JSON.toJSONString(ctrl));
////        运行中	正式运行		编辑、对账权限、合同变更、申请暂停、申请结束
//        ctrl = new ProjectButtonCtrl(2, 2, 3, 1, new Date(), 15);
//        System.out.println("运行中 正式运行 编辑、对账权限、合同变更、申请暂停、申请结束");
//        System.out.println(JSON.toJSONString(ctrl));
////        运行中	正式运行	暂停-审核中	编辑、对账权限、撤销暂停
//        ctrl = new ProjectButtonCtrl(2, 2, 1, 2, new Date(), 15);
//        System.out.println("运行中 正式运行 暂停-审核中 编辑、对账权限、撤销暂停");
//        System.out.println(JSON.toJSONString(ctrl));
////        运行中	正式运行	暂停-被驳回	编辑、对账权限、合同变更、修改申请、申请结束
//        ctrl = new ProjectButtonCtrl(2, 2, 2, 2, new Date(), 15);
//        System.out.println("运行中 正式运行 暂停-被驳回 编辑、对账权限、修改申请");
//        System.out.println(JSON.toJSONString(ctrl));
//        //        运行中	正式运行	暂停-已取消	编辑、对账权限、合同变更、修改申请、申请结束
//        ctrl = new ProjectButtonCtrl(2, 2, 4, 2, new Date(), 15);
//        System.out.println("运行中 正式运行 暂停-已取消 编辑、对账权限、合同变更、申请暂停、申请结束");
//        System.out.println(JSON.toJSONString(ctrl));
////        运行中	正式运行	暂停-已通过	编辑、对账权限
//        ctrl = new ProjectButtonCtrl(2, 2, 3, 2, new Date(), 15);
//        System.out.println("运行中 正式运行 暂停-已通过 编辑、对账权限");
//        System.out.println(JSON.toJSONString(ctrl));
////        已暂停	正式运行		编辑、对账权限、申请恢复、申请结束
//        ctrl = new ProjectButtonCtrl(3, 2, 3, 2, new Date(), 15);
//        System.out.println("已暂停 正式运行 编辑、对账权限、申请恢复、申请结束");
//        System.out.println(JSON.toJSONString(ctrl));
////        已暂停	正式运行	恢复-审核中	编辑、对账权限、撤销恢复
//        ctrl = new ProjectButtonCtrl(3, 2, 1, 3, new Date(), 15);
//        System.out.println("已暂停 正式运行 恢复-审核中 编辑、对账权限、撤销恢复");
//        System.out.println(JSON.toJSONString(ctrl));
////        已暂停	正式运行	恢复-被驳回	编辑、对账权限、修改申请、申请结束
//        ctrl = new ProjectButtonCtrl(3, 2, 2, 3, new Date(), 15);
//        System.out.println("已暂停 正式运行 恢复-被驳回 编辑、对账权限、修改申请");
//        System.out.println(JSON.toJSONString(ctrl));
//        //        已暂停	正式运行	恢复-已取消	编辑、对账权限、修改申请、申请结束
//        ctrl = new ProjectButtonCtrl(3, 2, 4, 3, new Date(), 15);
//        System.out.println("已暂停 正式运行 恢复-已取消 编辑、对账权限、申请恢复、申请结束");
//        System.out.println(JSON.toJSONString(ctrl));
////        已暂停	正式运行	恢复-已通过	编辑、对账权限
//        ctrl = new ProjectButtonCtrl(3, 2, 3, 3, new Date(), 15);
//        System.out.println("已暂停 正式运行 恢复-已通过 编辑、对账权限");
//        System.out.println(JSON.toJSONString(ctrl));
////        运行中	正式运行		编辑、对账权限、合同变更、申请暂停、申请结束
//        ctrl = new ProjectButtonCtrl(2, 2, 3, 3, new Date(), 15);
//        System.out.println("运行中 正式运行 编辑、对账权限、合同变更、申请暂停、申请结束");
//        System.out.println(JSON.toJSONString(ctrl));
////        运行中	正式运行	结束-审核中	编辑、对账权限、撤销结束
//        ctrl = new ProjectButtonCtrl(2, 2, 1, 4, new Date(), 15);
//        System.out.println("运行中 正式运行 结束-审核中 编辑、对账权限、撤销结束");
//        System.out.println(JSON.toJSONString(ctrl));
//        ctrl = new ProjectButtonCtrl(2, 2, 2, 4, new Date(), 15);
//        System.out.println("运行中 正式运行 结束-被驳回 编辑、对账权限、修改申请");
//        System.out.println(JSON.toJSONString(ctrl));
//        ctrl = new ProjectButtonCtrl(2, 2, 4, 4, new Date(), 15);
//        System.out.println(" 运行中 正式运行 结束-已取消 编辑、对账权限、合同变更、申请暂停、申请结束");
//        System.out.println(JSON.toJSONString(ctrl));
//        ctrl = new ProjectButtonCtrl(2, 2, 3, 4, new Date(), 15);
//        System.out.println("运行中 正式运行 结束-已通过 编辑、对账权限");
//        System.out.println(JSON.toJSONString(ctrl));
//        ctrl = new ProjectButtonCtrl(4, 2, 3, 4, new Date(), 15);
//        System.out.println("已结束 正式运行 编辑、对账权限、合同续签");
//        System.out.println(JSON.toJSONString(ctrl));
//        ctrl = new ProjectButtonCtrl(4, 2, 3, 4, DateUtil.addDays(new Date(), -16), 15);
//        System.out.println("已结束 正式运行 对账权限");
//        System.out.println(JSON.toJSONString(ctrl));
//        ctrl = new ProjectButtonCtrl(3, 2, 1, 4, new Date(), 15);
//        System.out.println("已暂停 正式运行 结束-审核中 编辑、对账权限、撤销结束");
//        System.out.println(JSON.toJSONString(ctrl));
//        ctrl = new ProjectButtonCtrl(3, 2, 2, 4, new Date(), 15);
//        System.out.println("已暂停 正式运行 结束-被驳回 编辑、对账权限、修改申请");
//        System.out.println(JSON.toJSONString(ctrl));
//        ctrl = new ProjectButtonCtrl(3, 2, 4, 4, new Date(), 15);
//        System.out.println("已暂停 正式运行 结束-已取消 编辑、对账权限、申请恢复、申请结束");
//        System.out.println(JSON.toJSONString(ctrl));
//        ctrl = new ProjectButtonCtrl(3, 2, 3, 4, new Date(), 15);
//        System.out.println("已暂停 正式运行 结束-已通过 编辑、对账权限");
//        System.out.println(JSON.toJSONString(ctrl));
//        ctrl = new ProjectButtonCtrl(4, 2, 3, 4, new Date(), 15);
//        System.out.println("已结束 正式运行 编辑、对账权限、合同续签");
//        System.out.println(JSON.toJSONString(ctrl));
//        ctrl = new ProjectButtonCtrl(4, 2, 3, 4, DateUtil.addDays(new Date(), -16), 15);
//        System.out.println("已结束 正式运行  对账权限");
//        System.out.println(JSON.toJSONString(ctrl));
//        ctrl = new ProjectButtonCtrl(2, 2, 3, 4, new Date(), 15);
//        System.out.println("运行中 正式运行 编辑、对账权限、合同变更、申请暂停、申请结束");
//        System.out.println(JSON.toJSONString(ctrl));
    }
}
