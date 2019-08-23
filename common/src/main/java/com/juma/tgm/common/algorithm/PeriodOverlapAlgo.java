package com.juma.tgm.common.algorithm;

import java.util.Date;

/**
 * 
 * @ClassName: PeriodOverlapAlgo
 * @Description: 时间段重叠算法
 * @author: Administrator
 * @date: 2019年4月18日 下午6:12:38
 *
 * @Copyright: 2019 www.jumapeisong.com Inc. All rights reserved.
 */
public class PeriodOverlapAlgo {

    public static boolean isOverlap(Date start1, Date end1, Date start2, Date end2) {
        if (start1 == null || end1 == null || start2 == null || end2 == null) {
            throw new IllegalArgumentException("参数错误");
        }
        long t1 = start1.getTime();
        long t2 = end1.getTime();
        long t3 = start2.getTime();
        long t4 = end2.getTime();

        if (t1 > t2 || t3 > t4) {
            throw new IllegalArgumentException("参数错误");
        }

        if (t1 > t4 || t2 < t3) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        Date passStartDate = new Date("10/01/2019 02:00:00");
        Date passEndDate = new Date("10/01/2019 06:00:00");
        Date sourceStartDate = new Date("10/01/2019 01:30:00");
        Date sourceEndDate = new Date("10/01/2019 01:59:00");
        System.out.println(isOverlap(passStartDate, passEndDate, sourceStartDate, sourceEndDate));
    }
}
