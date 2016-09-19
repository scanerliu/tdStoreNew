package com.tiandu.quartz;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.tiandu.order.service.TdOrderService;


public class OrderCompleteScheduledJob extends QuartzJobBean{
	private final Logger logger = Logger.getLogger(getClass());

	private TdOrderService tdOrderService; 
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		logger.error("auto complete order job start:");
		tdOrderService.completeOrderBySystemJob();
		logger.error("auto complete order job end.");
	}

	public void setTdOrderService(TdOrderService tdOrderService) {
		this.tdOrderService = tdOrderService;
	}
	
}
