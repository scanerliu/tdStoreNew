package com.tiandu.system.search;

import com.tiandu.common.search.SearchCriteria;

public class TdBenefitSearchCriteria extends SearchCriteria {

	private boolean getUpdateUser = false;	//查询更新人信息
	private Integer typeId;		//利润设置类型

	public boolean isGetUpdateUser() {
		return getUpdateUser;
	}

	public void setGetUpdateUser(boolean getUpdateUser) {
		this.getUpdateUser = getUpdateUser;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	
}
