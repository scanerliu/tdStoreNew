package com.tiandu.district.search;

import com.tiandu.common.search.SearchCriteria;

public class TdDistrictSearchCriteria extends SearchCriteria {

	private Integer upid;

	private Byte level;

	public Integer getUpid() {
		return upid;
	}

	public void setUpid(Integer upid) {
		this.upid = upid;
	}

	public Byte getLevel() {
		return level;
	}

	public void setLevel(Byte level) {
		this.level = level;
	}

}
