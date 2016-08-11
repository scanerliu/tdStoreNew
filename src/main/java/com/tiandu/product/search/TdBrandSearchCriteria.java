package com.tiandu.product.search;

import com.tiandu.common.search.SearchCriteria;

public class TdBrandSearchCriteria extends SearchCriteria {

	private boolean getUpdateUser = false;	//查询更新人信息
	
	private Byte status; //状态 1-正常，2-屏蔽
	
	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public boolean isGetUpdateUser() {
		return getUpdateUser;
	}

	public void setGetUpdateUser(boolean getUpdateUser) {
		this.getUpdateUser = getUpdateUser;
	}
	
	
}
