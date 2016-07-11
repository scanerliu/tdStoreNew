package com.tiandu.article.search;

import com.tiandu.common.search.SearchCriteria;

public class TdAdvertisementSearchCriteria extends SearchCriteria{

    private Byte status;
    
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
    
	
	
}
