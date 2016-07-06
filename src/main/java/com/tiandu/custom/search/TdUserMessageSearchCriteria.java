package com.tiandu.custom.search;

import java.util.Date;

import com.tiandu.common.search.SearchCriteria;

public class TdUserMessageSearchCriteria extends SearchCriteria {
	private Integer id;

	private Integer uid;

	private Byte msgType;

	private String title;

	private String username;

	private Date beginDate;

	private Date endDate;

	private Byte status;

	private boolean isAssociationTdUser;

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

	public Byte getMsgType() {
		return msgType;
	}

	public void setMsgType(Byte msgType) {
		this.msgType = msgType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public boolean isAssociationTdUser() {
		return isAssociationTdUser;
	}

	public void setAssociationTdUser(boolean isAssociationTdUser) {
		this.isAssociationTdUser = isAssociationTdUser;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

}
