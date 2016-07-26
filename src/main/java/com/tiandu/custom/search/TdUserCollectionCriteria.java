package com.tiandu.custom.search;

import com.tiandu.common.search.SearchCriteria;

public class TdUserCollectionCriteria extends SearchCriteria{

	private Integer uid;
	
	private Integer itemId;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	
}
