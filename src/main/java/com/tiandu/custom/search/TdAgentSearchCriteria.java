package com.tiandu.custom.search;

import com.tiandu.common.search.SearchCriteria;

public class TdAgentSearchCriteria extends SearchCriteria {
	private Integer id;

	private String username;

	private Integer level;

	private Integer regionId;

	private Integer productTypeId;

	private boolean isAssociationUser;

	private boolean isAssociationRegion;

	private boolean isAssociationProductType;

	private Integer uid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	public boolean isAssociationUser() {
		return isAssociationUser;
	}

	public void setAssociationUser(boolean isAssociationUser) {
		this.isAssociationUser = isAssociationUser;
	}

	public boolean isAssociationRegion() {
		return isAssociationRegion;
	}

	public void setAssociationRegion(boolean isAssociationRegion) {
		this.isAssociationRegion = isAssociationRegion;
	}

	public boolean isAssociationProductType() {
		return isAssociationProductType;
	}

	public void setAssociationProductType(boolean isAssociationProductType) {
		this.isAssociationProductType = isAssociationProductType;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

}
