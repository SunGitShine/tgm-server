package com.juma.tgm.landing.util;

import java.math.BigDecimal;

import org.springframework.util.Assert;

import com.juma.tgm.configure.domain.FreightRule;
import com.juma.tgm.configure.domain.PackFreightRule;

public class FreightRuleFunction {

    /**
     * 零担计价
     */
    public static BigDecimal computeForCarpool(FreightRule rule, BigDecimal realMiles, BigDecimal realVolume,
            BigDecimal realWeight) {

        Assert.notNull(rule, "rule 不能为空");
        Assert.notNull(rule.getBaseMiles(), "baseMiles不能为空");
        Assert.notNull(rule.getBaseVolume(), "baseMiles不能为空");
        Assert.notNull(rule.getBaseWeight(), "baseMiles不能为空");
        Assert.notNull(rule.getOverMilesUnit(), "overMilesUnit不能为空");
        Assert.notNull(rule.getOverWeightUnit(), "overWeightUnit不能为空");
        Assert.notNull(rule.getOverMilesUnit(), "overMilesUnit不能为空");
        Assert.notNull(realMiles, "realMiles不能为空");
        Assert.notNull(realVolume, "realVolume不能为空");
        Assert.notNull(realWeight, "realWeight不能为空");

        BigDecimal overMilesFreight = BigDecimal.ZERO;
        BigDecimal diffMiles =  realMiles.subtract(new BigDecimal(rule.getBaseMiles()));
        if(diffMiles.compareTo(BigDecimal.ZERO) == 1) {
            overMilesFreight =  rule.getOverMilesUnit().multiply(diffMiles);
        }

        BigDecimal f = rule.getBaseFreight().add(overMilesFreight);

        BigDecimal volumeConst = BigDecimal.ONE;
        BigDecimal diffVolume = realVolume.subtract(new BigDecimal(rule.getBaseVolume()).divide(new BigDecimal(1000+"")));
        if(diffVolume.compareTo(BigDecimal.ZERO) == 1) {
            volumeConst = diffVolume.divide(new BigDecimal("0.5"),2,BigDecimal.ROUND_HALF_UP).multiply(rule.getOverVolumeUnit()).add(BigDecimal.ONE);
        }

        BigDecimal weightConst = BigDecimal.ONE;
        BigDecimal diffWeight = realWeight.subtract(new BigDecimal(rule.getBaseWeight()).divide(new BigDecimal(1000+"")));
        if(diffWeight.compareTo(BigDecimal.ZERO) == 1) {
            weightConst = diffWeight.divide(new BigDecimal("0.5"),2,BigDecimal.ROUND_HALF_UP).multiply(rule.getOverWeightUnit()).add(BigDecimal.ONE);
        }

        return f.multiply(volumeConst).multiply(weightConst);

    }

    /**
     * 整车计价
     */
    public static BigDecimal computeForPack(PackFreightRule rule, BigDecimal realMiles) {

        Assert.notNull(rule, "rule 不能为空");
        Assert.notNull(rule.getBaseMiles(), "baseMiles不能为空");
        Assert.notNull(rule.getOverMilesUnit(), "overMilesUnit不能为空");
        Assert.notNull(realMiles, "realMiles不能为空");

        BigDecimal overMilesFreight = BigDecimal.ZERO;
        BigDecimal diffMiles = realMiles.subtract(new BigDecimal(rule.getBaseMiles()));
        if(diffMiles.compareTo(BigDecimal.ZERO) == 1) {
            overMilesFreight = diffMiles.multiply(rule.getOverMilesUnit());
        }
        return rule.getBaseFreight().add(overMilesFreight);
    }
    
    public static void main(String[] args) {
        FreightRule rule = new FreightRule();
        rule.setBaseMiles(51);
        rule.setBaseVolume(2);
        rule.setBaseWeight(2);
        rule.setBaseWeight(5);
        rule.setBaseFreight(new BigDecimal(50+""));
        rule.setOverMilesUnit(new BigDecimal(3.5+""));
        rule.setOverVolumeUnit(new BigDecimal(0.1+""));
        rule.setOverWeightUnit(new BigDecimal(0.1+""));
        
        System.out.println(computeForCarpool(rule, new BigDecimal(50), new BigDecimal(1), new BigDecimal(10)));
        
        BigDecimal n = new BigDecimal("2.58");
        System.out.println(n.setScale(0, BigDecimal.ROUND_HALF_UP).setScale(2));
        
    }
}
