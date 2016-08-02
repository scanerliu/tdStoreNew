package com.tiandu.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.tiandu.order.service.TdOrderService;


public class OrderCompleteScheduledJob extends QuartzJobBean{

	private TdOrderService tdOrderService; 
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		tdOrderService.completeOrderBySystemJob();
	}

	public void setTdOrderService(TdOrderService tdOrderService) {
		this.tdOrderService = tdOrderService;
	}
	
}
