package com.tiandu.article.search;

import com.tiandu.common.search.SearchCriteria;

public class TdArticleTitleSearchCriteria extends SearchCriteria {
	private Integer cid;
	private Byte status;
	private Integer regionId;

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}
	
}
