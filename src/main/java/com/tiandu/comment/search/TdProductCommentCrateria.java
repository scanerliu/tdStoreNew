package com.tiandu.comment.search;

import com.tiandu.common.search.SearchCriteria;

public class TdProductCommentCrateria extends SearchCriteria{

	private Byte status;
	private Integer fliter;	//过滤查询1-好评，2-中评，3-差评
	
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

	public Integer getFliter() {
		return fliter;
	}

	public void setFliter(Integer fliter) {
		this.fliter = fliter;
	}
	
}
