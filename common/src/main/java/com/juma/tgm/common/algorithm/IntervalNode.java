package com.juma.tgm.common.algorithm;

/**
 * 
 * @ClassName:   IntervalData   
 * @Description: 区间节点
 * @author:      Administrator
 * @date:        2017年7月10日 下午2:42:27  
 *
 * @Copyright:   2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class IntervalNode {

    private int left;
    
    private int right;
    
    private int weight;
    
    public IntervalNode() {}
    
    public IntervalNode(int left,int right,int weight) {
        this.left = left;
        this.right = right;
        this.weight = weight;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    
}
