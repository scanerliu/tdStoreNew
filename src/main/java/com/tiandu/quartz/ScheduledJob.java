package com.tiandu.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;


public class ScheduledJob extends QuartzJobBean{

	private CampaignCount campaignCount; 
	private LevelOneRegionUserCount levelOneRegionUserCount; 
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		this.getCampaignCount().countCampain();
		String message = this.getLevelOneRegionUserCount().makeMessage();
		this.getLevelOneRegionUserCount().sendMessage(message);
	}

	public CampaignCount getCampaignCount() {
		return campaignCount;
	}

	public void setCampaignCount(CampaignCount campaignCount) {
		this.campaignCount = campaignCount;
	}

	public LevelOneRegionUserCount getLevelOneRegionUserCount() {
		return levelOneRegionUserCount;
	}

	public void setLevelOneRegionUserCount(LevelOneRegionUserCount levelOneRegionUserCount) {
		this.levelOneRegionUserCount = levelOneRegionUserCount;
	}
}
