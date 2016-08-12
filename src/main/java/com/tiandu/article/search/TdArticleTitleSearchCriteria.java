package com.tiandu.article.search;

import com.tiandu.common.search.SearchCriteria;

public class TdArticleTitleSearchCriteria extends SearchCriteria {
	private Integer cid;
	private Byte status;

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
	
	

}
