package com.tiandu.article.search;

import java.util.Date;

import com.tiandu.common.search.SearchCriteria;

public class TdAdvertisementSearchCriteria extends SearchCriteria{

    private Byte status;
    
    private Date createTime;
    
    private Date endTime;
    
    private Integer adsId;

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getAdsId() {
		return adsId;
	}

	public void setAdsId(Integer adsId) {
		this.adsId = adsId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
    
	
	
}
