package com.tiandu.product.search;

import com.tiandu.common.search.SearchCriteria;

public class TdProductTypeCriteria extends SearchCriteria{

	private Integer parentId;
	
	private Byte status;

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}
	
	
}
