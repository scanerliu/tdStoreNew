package com.tiandu.custom.search;

import com.tiandu.common.search.SearchCriteria;

public class TdMembershipSearchCriteria extends SearchCriteria {
	private Integer id;

	private Byte status;

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

}
