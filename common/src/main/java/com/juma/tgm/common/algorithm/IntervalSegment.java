package com.juma.tgm.common.algorithm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * 区间段
 */
public class IntervalSegment {

    private static final Logger log = LoggerFactory.getLogger(IntervalSegment.class);

    
    private static List<IntervalNode> nodes = new ArrayList<IntervalNode>();

    static {

        nodes.add(new IntervalNode(500, 900, 1));
        nodes.add(new IntervalNode(900, 1300, 2));
        nodes.add(new IntervalNode(1300, 1700, 3));
        nodes.add(new IntervalNode(1700, 2200, 4));
        nodes.add(new IntervalNode(2200, 2400, 5));
        nodes.add(new IntervalNode(0, 500, 5));

    }
    
    
    public static float computeWeight(Date startDate,Date endDate) {
        log.info("startDate value : " + startDate + "," + "endDate value : " + endDate);
        if(startDate == null || endDate == null) {
            return 0;
        }
        if(endDate.getTime() <= startDate.getTime()) {
            return 0.5f;
        }
        
        int days = (int)betweenMilliSecond(startDate, endDate)/(1000 * 60 * 60 * 24);
        
        float remainder = betweenMilliSecond(startDate, endDate)%(1000 * 60 * 60 * 24);//余数
        
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        
        int left = fromCalendar.get(Calendar.HOUR_OF_DAY) * 100 + fromCalendar.get(Calendar.MINUTE);
        int right = endCalendar.get(Calendar.HOUR_OF_DAY) * 100 + endCalendar.get(Calendar.MINUTE) - 1;//减一分钟
        if(remainder == 0) {
            return days *2;
        } else {
            return days *2 + computeWeight(left, right);
        }
    }
    
    private static float betweenMilliSecond(Date startDate,Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();  
        fromCalendar.setTime(startDate);  
        fromCalendar.set(Calendar.SECOND, 0);  
        fromCalendar.set(Calendar.MILLISECOND, 0);  
  
        Calendar toCalendar = Calendar.getInstance();  
        toCalendar.setTime(endDate);  
        toCalendar.set(Calendar.SECOND, 0);  
        toCalendar.set(Calendar.MILLISECOND, 0);
        return toCalendar.getTime().getTime() - fromCalendar.getTime().getTime();
    }

    public static float computeWeight(int left, int right) {
        //拆
        List<LineSeg> segs = new ArrayList<LineSeg>();
        if (left > right) {
            segs.add(new LineSeg(left, 2400));
            segs.add(new LineSeg(0, right));
        } else {
            segs.add(new LineSeg(left, right));
        }

        Set<Integer> weights = new HashSet<Integer>();
        //计算落点   有一些不必要的循环在里面
        for (LineSeg seg : segs) {
            for (IntervalNode node : nodes) {
                if (seg.getLeft() >= node.getRight() || seg.getRight() <= node.getLeft()) continue;
                if (seg.getRight() < node.getRight()) {
                    weights.add(node.getWeight());
                    break;
                } else {
                    weights.add(node.getWeight());
                }
            }
        }

        //系数
        log.info("use:{}", weights);
        if (weights.isEmpty()) return 0;
        if (weights.size() == 5) return 2f;
        if (weights.size() == 4) return 1.5f;
        if (weights.contains(3) && weights.contains(4) && weights.contains(5)) {
            return 1.5f;
        }
        if ((weights.contains(2) && weights.contains(3)) || (weights.contains(4) && weights.contains(5))) {
            return 1f;
        }
        if (weights.size() == 3) return 1f;
        return 0.5f;

    }

    public static void main(String[] args) {
        //System.out.println(IntervalSegment.computeWeight(2000, 1500));
        
        Calendar d1 = Calendar.getInstance();
        d1.set(2017, 6, 21, 9, 00, 00);
        
        Calendar d2 = Calendar.getInstance();
        d2.set(2017, 6, 21, 9, 00, 00);
        
        System.out.println(IntervalSegment.computeWeight(d1.getTime(),d2.getTime()));
    }
}
