package com.juma.tgm.adjust.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.juma.fms.v2.core.payment.payable.domain.WayBillStatus;
import com.juma.tgm.waybill.domain.WaybillAmount;
import com.juma.vms.vendor.enumeration.VendorSourceEnum;
import org.apache.commons.lang.math.NumberUtils;
import org.joda.time.Instant;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-05-21 20:10
 **/
public class Test {

	/**
	 * 多线程处理list
	 *
	 * @param data  数据list
	 * @param threadNum  线程数
	 */
	public synchronized void handleList(List<String> data, int threadNum) {
		int length = data.size();
		int tl = length % threadNum == 0 ? length / threadNum : (length
			/ threadNum + 1);

		for (int i = 0; i < threadNum; i++) {
			int end = (i + 1) * tl;
			HandleThread thread = new HandleThread("线程[" + (i + 1) + "] ",  data, i * tl, end > length ? length : end);
			thread.start();
		}
	}

	class HandleThread extends Thread {
		private String threadName;
		private List<String> data;
		private int start;
		private int end;

		public HandleThread(String threadName, List<String> data, int start, int end) {
			this.threadName = threadName;
			this.data = data;
			this.start = start;
			this.end = end;
		}

		public void run() {
			//这里处理数据
			List<String> subList = data.subList(start, end);
			for(int a=0; a<subList.size(); a++){

				System.out.println(threadName+"处理了   "+subList.get(a)+"  ！");
				//	System.out.println(threadName+"处理了"+subList.size()+"条！");
			}
		}

	}

	public static void main(String[] args) {
//		removeList();
//		randomSecond();
//		formatDecimal();
//		forLoop();
		System.out.println(Boolean.parseBoolean(null));
		try {
			NumberFormat nf= NumberFormat.getPercentInstance();
			Number optionAmount = nf.parse(null);
			System.out.println(optionAmount.doubleValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(new BigDecimal("3%"));
		System.out.println(Objects.equals(VendorSourceEnum.SELF_SUPPORT.getCode(), new Byte("1")));
	}

	private static void forLoop() {
		List<WaybillAmount> needFreshs = Lists.newArrayList();
		for (int i = 0;i < 50000;i++){
			WaybillAmount waybillAmount = new WaybillAmount();
			waybillAmount.setWaybillId(i);
			needFreshs.add(waybillAmount);
		}
		List<WaybillAmount> insertBatch = Lists.newArrayList();
		for (WaybillAmount waybillAmount : needFreshs){
			insertBatch.add(waybillAmount);
			if( insertBatch.size() == 2000 ){
				System.out.println("执行插入");
				insertBatch.clear();
			}
		}
	}

	private static void formatDecimal() {
		DecimalFormat format = new DecimalFormat();
		format.applyPattern("##%");
		System.out.println(format.format(0.3213));
		format.applyPattern("##.##%");
		System.out.println(format.format(0.3256));
	}

	private static void randomSecond() {
		System.out.println(System.nanoTime()+"|"+System.nanoTime());
		System.out.println(System.nanoTime());
		System.out.println(Instant.now());
	}

	private static void removeList() {
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");

		System.out.println(JSON.toJSONString(list));
		NumberFormat nf= NumberFormat.getPercentInstance();
		try {
			Number m=nf.parse("-20%");
			System.out.println(m.doubleValue());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		NumberFormat num = NumberFormat.getPercentInstance();
		num.setMaximumIntegerDigits(3);
		num.setMaximumFractionDigits(2);
		System.out.println(num.format(-0.2));
		System.out.println(NumberUtils.isDigits("20%"));

		System.out.println(BigDecimal.valueOf(0.385432).divide(BigDecimal.ONE,2,RoundingMode.HALF_UP));
		System.out.println(BigDecimal.valueOf(0.385432).divide(BigDecimal.ONE,2,BigDecimal.ROUND_HALF_UP));
		System.out.println(BigDecimal.valueOf(0.385432).setScale(2,BigDecimal.ROUND_HALF_UP));
		System.out.println(BigDecimal.valueOf(0.385432).setScale(2, RoundingMode.HALF_UP));

		System.out.println(BigDecimal.valueOf(13.62)
				.divide(BigDecimal.ONE.add(BigDecimal.valueOf(0.06)),2, RoundingMode.HALF_UP)
				.multiply(BigDecimal.valueOf(0.03)));

		// 开票:【调整金额  ÷（1+承运商开票税率）】—【调整金额  ÷（1+客户侧税率）】
		BigDecimal priceIncludingTax = BigDecimal.ZERO;
		if( true && BigDecimal.valueOf(-1).compareTo(BigDecimal.valueOf(15.6)) != 0 ){
			priceIncludingTax = BigDecimal.valueOf(18.9).divide(BigDecimal.ONE.add(BigDecimal.valueOf(0.2)),2, RoundingMode.HALF_UP)
					.subtract(
							BigDecimal.valueOf(18.9).divide(BigDecimal.ONE.add(BigDecimal.valueOf(0.06)),2, RoundingMode.HALF_UP)
					);
		}else{
			// 不开票: 调整金额 — 【调整金额 ÷（ 1+进项税率）】
			priceIncludingTax = BigDecimal.valueOf(18.9)
					.subtract(BigDecimal.valueOf(18.9).divide(BigDecimal.ONE.add(BigDecimal.valueOf(0.06)),2, RoundingMode.HALF_UP));
		}

		System.out.println(priceIncludingTax.multiply(BigDecimal.valueOf(-100L)).longValue());
	}

	private void removeList(List<String> list){

		List<String> newList = new ArrayList<>(list);

		Iterator<String> iterable = newList.iterator();
		while (iterable.hasNext()){
			if(iterable.next().equals("c")){
				iterable.remove();
			}
		}

		System.out.println(JSON.toJSONString(newList));
	}

	private void removeDumplicated(List<String> list){
		List<WayBillStatus> wayBillStatuses = JSON.parseArray("[{\"isFrozen\":1,\"payableNo\":\"YFD201906171709291001\",\"wayBillNo\":\"2019061458189014501\"},{\"isFrozen\":0,\"payableNo\":\"YFD201906171823511001\",\"wayBillNo\":\"2019061458189014501\"},{\"isFrozen\":0,\"payableNo\":\"YFD201906172011041001\",\"wayBillNo\":\"2019061458189014501\"},{\"isFrozen\":0,\"payableNo\":\"YFD201906172021381001\",\"wayBillNo\":\"2019061458189014501\"}]",WayBillStatus.class);
		Map<String,WayBillStatus> wayBillStatusMap = Maps.newConcurrentMap();
		for (WayBillStatus status : wayBillStatuses){
			WayBillStatus oldStatus = wayBillStatusMap.get(status.getWayBillNo());
			if( null == oldStatus ){
				wayBillStatusMap.put(status.getWayBillNo(),status);
			}else if(Integer.valueOf(1).equals(status.getIsFrozen())){
				wayBillStatusMap.put(status.getWayBillNo(),status);
			}
		}
		System.out.println(JSON.toJSONString(wayBillStatusMap));
	}
}
