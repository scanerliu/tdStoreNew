package com.tiandu.custom.search;

import com.tiandu.common.search.SearchCriteria;

public class TdUserSupplierSearchCriteria extends SearchCriteria {
	private Integer id;

	private Byte status;
	
	private Integer userId;

	private boolean isAssociationUser;

	private boolean isAssociationUpdatePerson;

	private String searchName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public boolean isAssociationUser() {
		return isAssociationUser;
	}

	public void setAssociationUser(boolean isAssociationUser) {
		this.isAssociationUser = isAssociationUser;
	}

	public boolean isAssociationUpdatePerson() {
		return isAssociationUpdatePerson;
	}

	public void setAssociationUpdatePerson(boolean isAssociationUpdatePerson) {
		this.isAssociationUpdatePerson = isAssociationUpdatePerson;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	

}
