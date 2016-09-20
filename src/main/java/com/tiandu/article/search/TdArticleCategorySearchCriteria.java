package com.tiandu.article.search;

import com.tiandu.common.search.SearchCriteria;

public class TdArticleCategorySearchCriteria extends SearchCriteria {
	private Integer cid;
	private Byte status;
	private Integer parentId;
	
	private String name;
	
	private Integer aid; //文章id，前端参数，展示效果用

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}
	
}
