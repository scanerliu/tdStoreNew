package com.tiandu.custom.search;

import com.tiandu.common.search.SearchCriteria;

public class TdRoleSearchCriteria extends SearchCriteria {

	private Byte type; //类型：1-平台角色，2-会员角色
	
	private Boolean getUpdateUser = false; //获取更新人信息

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Boolean getGetUpdateUser() {
		return getUpdateUser;
	}

	public void setGetUpdateUser(Boolean getUpdateUser) {
		this.getUpdateUser = getUpdateUser;
	}
	
	
	
}
