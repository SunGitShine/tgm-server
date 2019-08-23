package com.juma.tgm.waybill.service.impl;

import com.giants.common.exception.BusinessException;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.juma.auth.conf.domain.BusinessArea;
import com.juma.auth.conf.domain.BusinessAreaNode;
import com.juma.auth.conf.service.BusinessAreaService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.businessArea.service.TgmBusinessAreaService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @ClassName: TgmBusinessAreaServiceImpl
 * @Description:
 * @author: liang
 * @date: 2017-09-07 10:40
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
@Service
public class TgmBusinessAreaServiceImpl implements TgmBusinessAreaService {

    private static final Logger log = LoggerFactory.getLogger(TgmBusinessAreaServiceImpl.class);

    @Resource
    private BusinessAreaService businessAreaService;


    @Override
    public List<BusinessArea> findAllParentNode(int childId, LoginUser loginUser) throws BusinessException {
        List<BusinessArea> parentNode = new ArrayList<>();
        this.getTreeNodeById(parentNode, childId, loginUser);
        return parentNode;
    }

    @Override
    public List<BusinessAreaNode> getLogicBusinessAreaTree(LoginUser loginUser) throws BusinessException {

        List<BusinessAreaNode> rootTrees = null;
        try {
            rootTrees = businessAreaService.findBusinessAreaTree(loginUser.getTenantId());
        } catch (Exception e) {
            log.warn("获取业务区域树错误.", e);

            return null;
        }
        List<BusinessAreaNode> parallelTree = this.removeNotLogicLeaf(rootTrees);

        return this.getTreeNode(parallelTree);
    }

    @Override
    public List<BusinessAreaNode> getLogicBusinessAreaParallel(LoginUser loginUser) throws BusinessException {

        List<BusinessAreaNode> rootTrees = null;
        try {
            rootTrees = businessAreaService.findBusinessAreaTree(loginUser.getTenantId());
        } catch (Exception e) {
            log.warn("获取业务区域树错误.", e);

            return null;
        }
        List<BusinessAreaNode> parallelTree = this.removeNotLogicLeaf(rootTrees);

        return parallelTree;
    }


    /**
     * 删除非逻辑区域的叶子节点
     *
     * @param rootTrees
     */
    private List<BusinessAreaNode> removeNotLogicLeaf(List<BusinessAreaNode> rootTrees) {
        if (CollectionUtils.isEmpty(rootTrees)) return null;

        //1、找到所有的逻辑业务区域
        //2、获取所有的平行业务节点
        //3、以逻辑业务区域为叶子节点重新组装树

        Set<BusinessAreaNode> rows = new HashSet<>();
        this.parallelList(rootTrees, rows);

        for (BusinessAreaNode ban : rows) {
            ban.setChildren(null);
        }


        //逻辑业务节点
        Set<BusinessAreaNode> logicNodeOnly = this.findLogicNode(rows);

        //通过逻辑业务区域重新组装树
        Set<BusinessAreaNode> logicTreeNode = this.filterLogicTreeNode(rows, logicNodeOnly);

        List<BusinessAreaNode> destList = new ArrayList<BusinessAreaNode>(logicTreeNode);
        return destList;

    }

    /**
     * 获取所有需要组装成逻辑树的所有节点
     *
     * @param allNodes
     * @param logicNodes
     * @return
     */
    private Set<BusinessAreaNode> filterLogicTreeNode(Set<BusinessAreaNode> allNodes, final Set<BusinessAreaNode> logicNodes) {
        Set<BusinessAreaNode> parallelTreeNodes = new HashSet<>();

        for (final BusinessAreaNode ban : logicNodes) {

            this.buildTree(allNodes, parallelTreeNodes,ban);

        }

        parallelTreeNodes.addAll(logicNodes);
        return parallelTreeNodes;
    }

    private void buildTree(Set<BusinessAreaNode> allNodes, Set<BusinessAreaNode> targetTreeNodes, final BusinessAreaNode child){

        //找到当前节点的所有父节点
        Predicate<BusinessAreaNode> predicate = new Predicate<BusinessAreaNode>() {
            @Override
            public boolean apply(BusinessAreaNode node) {
                if(child.getParentBusinessAreaId() == null) return false;

                return NumberUtils.compare(node.getBusinessAreaId(), child.getParentBusinessAreaId()) == 0;
            }
        };

        Collection<BusinessAreaNode> result = Collections2.filter(allNodes, predicate);

        if(CollectionUtils.isEmpty(result)) return ;

        targetTreeNodes.addAll(result);

        for(BusinessAreaNode ban : result){
            if(ban.getParentBusinessAreaId() == null) continue;

            this.buildTree(allNodes, targetTreeNodes, ban);
        }

    }

    /**
     * 找到所有的逻辑业务区域节点
     *
     * @param allNodes
     * @return
     */
    private Set<BusinessAreaNode> findLogicNode(Set<BusinessAreaNode> allNodes) {
        if (CollectionUtils.isEmpty(allNodes)) return null;

        Set<BusinessAreaNode> logicNodeOnly = new HashSet<>();

        for (BusinessAreaNode ban : allNodes) {
            if (ban.isLogic()) {
                logicNodeOnly.add(ban);
            }
        }

        return logicNodeOnly;
    }

    /**
     * 重新组装逻辑树
     *
     * @param parallelNode
     * @return
     */
    private List<BusinessAreaNode> getTreeNode(List<BusinessAreaNode> parallelNode) {
        Collections.sort(parallelNode, new Comparator<BusinessAreaNode>() {
            @Override
            public int compare(BusinessAreaNode first, BusinessAreaNode second) {
                //负数 第一个值小于第二个，0 等于, 正数第一个值大于第二个
                return NumberUtils.compare(first.getBusinessAreaId(), second.getBusinessAreaId());
            }
        });
        List<BusinessAreaNode> logicTree = this.conversionTreeStructure(parallelNode, null);

        return logicTree;
    }

    /**
     * 组装树
     *
     * @param businessAreaNodeList
     * @return
     */
    private List<BusinessAreaNode> conversionTreeStructure(List<BusinessAreaNode> businessAreaNodeList,
                                                           List<String> rootAreaCodeList) {
        if (CollectionUtils.isNotEmpty(businessAreaNodeList)) {

            List<BusinessAreaNode> resourceTreeResult = new ArrayList<BusinessAreaNode>();

            Map<Integer, BusinessAreaNode> BusinessAreaTreeMap = new HashMap<Integer, BusinessAreaNode>();

            for (BusinessAreaNode businessAreaNode : businessAreaNodeList) {

                if (businessAreaNode.getParentBusinessAreaId() == null
                        || (CollectionUtils.isNotEmpty(rootAreaCodeList) && rootAreaCodeList.contains(businessAreaNode
                        .getAreaCode()))) {
                    resourceTreeResult.add(businessAreaNode);
                } else if (BusinessAreaTreeMap.get(businessAreaNode.getParentBusinessAreaId()) != null) {
                    BusinessAreaTreeMap.get(businessAreaNode.getParentBusinessAreaId()).addChild(businessAreaNode);
                }

                if (!businessAreaNode.isLeaf()) {
                    BusinessAreaTreeMap.put(businessAreaNode.getBusinessAreaId(), businessAreaNode);
                }

            }

            BusinessAreaTreeMap = null;
            return resourceTreeResult;

        }
        return null;
    }



    private void parallelList(List<BusinessAreaNode> rootTrees, Set<BusinessAreaNode> rows) {
        if (rootTrees == null) {
            return;
        }
        // 深度优先算法
        Deque<BusinessAreaNode> dfs = new LinkedList<>();
        //遍历每个根节点
        for (BusinessAreaNode areaNode : rootTrees) {
            dfs.push(areaNode);
        }
        //当前根节点的每个子节点
        while (!dfs.isEmpty()) {
            BusinessAreaNode node = dfs.pop();
            rows.add(node);
            if (CollectionUtils.isNotEmpty(node.getChildren())) {
                List<BusinessAreaNode> nodeLeafs = node.getChildren();
                if (CollectionUtils.isEmpty(nodeLeafs)) {
                    continue;
                }
                for (BusinessAreaNode n : nodeLeafs) {
                    dfs.add(n);
                }
                rows.addAll(nodeLeafs);
            }
        }
    }


    /**
     * 获取所有父节点
     * @param businessAreas
     * @param id
     * @param loginUser
     */
    private void getTreeNodeById(List<BusinessArea> businessAreas, int id, LoginUser loginUser){

        BusinessArea ba = businessAreaService.loadBusinessArea(id, loginUser);

        if(ba == null) return;
        businessAreas.add(ba);

        if(ba.getParentBusinessAreaId() == null) return;

        this.getTreeNodeById(businessAreas, ba.getParentBusinessAreaId(), loginUser);

    }

}
