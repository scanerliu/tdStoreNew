package com.tiandu.comment.search;

import com.tiandu.common.search.SearchCriteria;

public class TdProductCommentCrateria extends SearchCriteria{

	private Byte status;
	
	private Integer uid;

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	
	
}
