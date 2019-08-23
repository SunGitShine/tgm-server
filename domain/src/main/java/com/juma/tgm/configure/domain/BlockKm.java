package com.juma.tgm.configure.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @ClassName:   BlockKm   
 * @Description: 分段里程计价
 * @author:      Administrator
 * @date:        2018年1月31日 下午5:34:47  
 *
 * @Copyright:   2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class BlockKm implements Serializable,Comparable<BlockKm> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -269659514344214289L;

    private BigDecimal km;
    
    private BigDecimal freight;

    public BigDecimal getKm() {
        return km;
    }

    public void setKm(BigDecimal km) {
        this.km = km;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    @Override
    public int compareTo(BlockKm o) {
        return this.getKm().compareTo(o.getKm());
    }
    
}
