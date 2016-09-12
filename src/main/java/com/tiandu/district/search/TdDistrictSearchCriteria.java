package com.tiandu.district.search;

import com.tiandu.common.search.SearchCriteria;

public class TdDistrictSearchCriteria extends SearchCriteria {

	private Integer upid;
	private Integer provinceId;	//省id
	private Integer cityId;	//市id
	private Integer regionId; //区id
	
	private Integer totalLevel; //总共几级
	private String callback;//回调函数


	public Integer getUpid() {
		return upid;
	}

	public void setUpid(Integer upid) {
		this.upid = upid;
	}


	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getTotalLevel() {
		return totalLevel;
	}

	public void setTotalLevel(Integer totalLevel) {
		this.totalLevel = totalLevel;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}
	
}
