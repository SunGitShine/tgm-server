package com.juma.tgm.adjust.service.impl;

import lombok.Builder;
import lombok.Data;
import org.testng.collections.Lists;

import java.util.List;

/**
 * 功能 : 
 *
 * @author : Bruce(刘正航) 09:37 2019-05-22
 */
@Data
@Builder
public class OperatorType {
    private Integer adjustType;
    private Integer adjustMasterType;
    private List<Integer> waybills = Lists.newArrayList();
}
