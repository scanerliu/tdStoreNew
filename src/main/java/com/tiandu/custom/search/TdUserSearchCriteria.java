package com.tiandu.custom.search;

import com.tiandu.common.search.SearchCriteria;

public class TdUserSearchCriteria extends SearchCriteria {

	private String name; //用户名

    private Byte ustatus; //用户状态，1-正常，2-屏蔽

    private Byte utype; //用户类型，1-普通会员，2-平台用户
    
    private Boolean getUpdateUser = false; //获取更新人信息

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getUstatus() {
		return ustatus;
	}

	public void setUstatus(Byte ustatus) {
		this.ustatus = ustatus;
	}

	public Byte getUtype() {
		return utype;
	}

	public void setUtype(Byte utype) {
		this.utype = utype;
	}

	public Boolean getGetUpdateUser() {
		return getUpdateUser;
	}

	public void setGetUpdateUser(Boolean getUpdateUser) {
		this.getUpdateUser = getUpdateUser;
	}
    
    
}
