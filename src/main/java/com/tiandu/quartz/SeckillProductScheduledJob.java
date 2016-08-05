package com.tiandu.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.tiandu.product.service.TdProductService;

public class SeckillProductScheduledJob extends QuartzJobBean{
	
	private TdProductService tdProductService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		tdProductService.seckillProduct();
	}


	public void setTdProductService(TdProductService tdProductService) {
		this.tdProductService = tdProductService;
	}

	
}
