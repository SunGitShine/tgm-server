package com.juma.tgm.tools.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.extension.SPI;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.fastjson.JSON;
import com.giants.common.exception.BusinessException;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-05-09 15:40
 **/
@Activate(
	group = {"consumer", "provider"}
)
@SPI
public class DubboCallLogFilter implements Filter {
	private static Logger logger = LoggerFactory.getLogger(DubboCallLogFilter.class);
	private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

	public DubboCallLogFilter() {
		threadLocal.set(UUID.randomUUID().toString().replace("-",""));
	}

	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		String serviceName = invoker.getInterface().getName();
		String methodName = invocation.getMethodName();
		Object[] argumentValues = invocation.getArguments();
		Result result = null;
		String prefix = RpcContext.getContext().isConsumerSide() ? "==>" : "<==";

		try {
			try {
				logger.info("{}{} dubboCall=[{}] paramters={}", new Object[]{threadLocal.get(), prefix, serviceName + "#" + methodName, JSON.toJSONString(argumentValues)});
				result = invoker.invoke(invocation);
				if (serviceName.equals("com.juma.common.storage.service.DistributedFileStorageService")) {
					return result;
				}

				if (!result.hasException()) {
					logger.info("{}{} dubboCall=[{}] paramters={}  result={}", new Object[]{threadLocal.get(), prefix, serviceName + "#" + methodName, JSON.toJSONString(argumentValues), JSON.toJSONString(result.getValue())});
				} else if (result.getException() instanceof BusinessException) {
					logger.error("{}{} dubboCall=[{}] paramters={}  resultException={}", new Object[]{threadLocal.get(), prefix, serviceName + "#" + methodName, JSON.toJSONString(argumentValues), result.getException().getMessage()});
				} else {
					logger.error("{}{} dubboCall=[{}] paramters={}  resultException={}", new Object[]{threadLocal.get(), prefix, serviceName + "#" + methodName, JSON.toJSONString(argumentValues), result.getException().getMessage()});
				}
			} catch (RpcException var12) {
				logger.error(threadLocal.get() + prefix + "dubbo调用发生异常", var12);
			}

			return result;
		} finally {
			;
		}
	}
}

