package com.tiandu.product.search;

import com.tiandu.common.search.SearchCriteria;

public class TdProductCriteria extends SearchCriteria{

	private Integer parentId;

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
