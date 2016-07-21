package com.tiandu.custom.search;

import com.tiandu.common.search.SearchCriteria;

public class TdUserAddressCriteria extends SearchCriteria {

	private Integer uid;
	
	private Boolean isDefault;
	
	public TdUserAddressCriteria(Integer uid,Boolean isDefault)
	{
		this.uid = uid;
		this.isDefault = isDefault;
	}
	public TdUserAddressCriteria()
	{
		super();
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
	
}
