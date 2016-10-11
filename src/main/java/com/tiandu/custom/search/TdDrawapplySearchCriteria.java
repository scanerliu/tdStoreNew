package com.tiandu.custom.search;

import java.util.Date;

import com.tiandu.common.search.SearchCriteria;

public class TdDrawapplySearchCriteria extends SearchCriteria {
	private Integer uid; //用户id
	
	private String username; //用户姓名
	
	private Integer status; // 提现状态 1-申请，2-同意提现，3-不同意提现，4-打款完成

	private Date startTime; //开始时间

	private Date endTime; //结束时间

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
