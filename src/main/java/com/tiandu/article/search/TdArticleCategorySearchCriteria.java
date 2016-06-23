package com.tiandu.article.search;

import com.tiandu.common.search.SearchCriteria;

public class TdArticleCategorySearchCriteria extends SearchCriteria {
	private Integer cid;
	private Byte status;
	private Integer parentId;

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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

}
