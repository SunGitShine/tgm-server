package com.juma.tgm.configure.domain;

import java.math.BigDecimal;

public class PrivateFreightFactorContainer implements Comparable<PrivateFreightFactorContainer> {
    
    private BigDecimal baseWeight = BigDecimal.ZERO;
    
    private BigDecimal baseVolumn = BigDecimal.ZERO;
    
    private PrivateFreightFactor privateFreightFactor;

    public BigDecimal getBaseWeight() {
        return baseWeight;
    }

    public void setBaseWeight(BigDecimal baseWeight) {
        this.baseWeight = baseWeight;
    }

    public BigDecimal getBaseVolumn() {
        return baseVolumn;
    }

    public void setBaseVolumn(BigDecimal baseVolumn) {
        this.baseVolumn = baseVolumn;
    }

    public PrivateFreightFactor getPrivateFreightFactor() {
        return privateFreightFactor;
    }

    public void setPrivateFreightFactor(PrivateFreightFactor privateFreightFactor) {
        this.privateFreightFactor = privateFreightFactor;
    }

    @Override
    public int compareTo(PrivateFreightFactorContainer o) {
        return this.getBaseWeight().compareTo(o.getBaseWeight());
    }
    

}
