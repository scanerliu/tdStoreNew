package com.tiandu.custom.search;

import java.util.Date;

import com.tiandu.common.search.SearchCriteria;

public class TdUserAccountLogSearchCriteria extends SearchCriteria {

	private Integer uid; //用户id
	
	private String username;
	
	private Date beginDate;
	
	private Date endDate;
	
	private Boolean isAsc = false;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getIsAsc() {
		return isAsc;
	}

	public void setIsAsc(Boolean isAsc) {
		this.isAsc = isAsc;
	}
	
	
	
    
}
