package com.tiandu.custom.search;

import com.tiandu.common.search.SearchCriteria;

public class TdCampaignSearchCriteria extends SearchCriteria {
	private Integer id;

	private Integer regionId;

	private Integer createBy;

	private Integer updateBy;

	private boolean isAssociationTdDistrict;
	private boolean isAssociationcCreatePerson;
	private boolean isAssociationUpdatePerson;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public boolean isAssociationTdDistrict() {
		return isAssociationTdDistrict;
	}

	public void setAssociationTdDistrict(boolean isAssociationTdDistrict) {
		this.isAssociationTdDistrict = isAssociationTdDistrict;
	}

	public boolean isAssociationcCreatePerson() {
		return isAssociationcCreatePerson;
	}

	public void setAssociationcCreatePerson(boolean isAssociationcCreatePerson) {
		this.isAssociationcCreatePerson = isAssociationcCreatePerson;
	}

	public boolean isAssociationUpdatePerson() {
		return isAssociationUpdatePerson;
	}

	public void setAssociationUpdatePerson(boolean isAssociationUpdatePerson) {
		this.isAssociationUpdatePerson = isAssociationUpdatePerson;
	}

}
