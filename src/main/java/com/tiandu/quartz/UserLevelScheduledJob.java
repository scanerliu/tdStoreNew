package com.tiandu.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;


public class UserLevelScheduledJob extends QuartzJobBean{

	private UserLevelFlush userLevelFlush; 
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		this.getUserLevelFlush().updateUserLevelUped();
	}

	public UserLevelFlush getUserLevelFlush() {
		return userLevelFlush;
	}

	public void setUserLevelFlush(UserLevelFlush userLevelFlush) {
		this.userLevelFlush = userLevelFlush;
	}

}
