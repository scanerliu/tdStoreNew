package com.tiandu.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.tiandu.order.service.TdOrderService;


public class OrderReceiptScheduledJob extends QuartzJobBean{

	private TdOrderService tdOrderService; 
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		tdOrderService.receiptOrderBySystemJob();
	}

	public void setTdOrderService(TdOrderService tdOrderService) {
		this.tdOrderService = tdOrderService;
	}
	
}
