package com.tiandu.product.search;

import com.tiandu.common.search.SearchCriteria;

public class TdAgentProductSearchCriteria extends SearchCriteria {

	private boolean getUpdateUser = false;	//查询更新人信息

	private Integer sortBy;	//排序方式 1-sort 升序，2-sort降序
	
	public boolean isGetUpdateUser() {
		return getUpdateUser;
	}

	public void setGetUpdateUser(boolean getUpdateUser) {
		this.getUpdateUser = getUpdateUser;
	}

	public Integer getSortBy() {
		return sortBy;
	}

	public void setSortBy(Integer sortBy) {
		this.sortBy = sortBy;
	}
	
	
}
