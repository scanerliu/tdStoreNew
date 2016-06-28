package com.tiandu.product.search;

import com.tiandu.common.search.SearchCriteria;

public class TdProductTypeCriteria extends SearchCriteria{

	private Integer parentId;

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
