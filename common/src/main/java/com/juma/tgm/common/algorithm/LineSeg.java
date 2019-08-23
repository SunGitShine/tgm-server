package com.juma.tgm.common.algorithm;

public class LineSeg {

    private int left;
    
    private int right;

    public LineSeg(){}
    
    public LineSeg(int left,int right) {
        this.left = left;
        this.right = right;
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
    
}
