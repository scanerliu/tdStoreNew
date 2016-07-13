package com.tiandu.product.search;

import com.tiandu.common.search.SearchCriteria;

public class TdBrandSearchCriteria extends SearchCriteria {

	private boolean getUpdateUser = false;	//查询更新人信息

	public boolean isGetUpdateUser() {
		return getUpdateUser;
	}

	public void setGetUpdateUser(boolean getUpdateUser) {
		this.getUpdateUser = getUpdateUser;
	}
	
	
}
