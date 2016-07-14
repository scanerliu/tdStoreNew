package com.tiandu.custom.search;

import java.util.Date;

import com.tiandu.common.search.SearchCriteria;

public class TdUserSignSearchCriteria extends SearchCriteria {

	private Integer id;

	private Integer uid;

	private Date createDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
