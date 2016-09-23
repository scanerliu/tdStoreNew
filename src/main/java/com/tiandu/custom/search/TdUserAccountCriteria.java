package com.tiandu.custom.search;

import com.tiandu.common.search.SearchCriteria;

public class TdUserAccountCriteria extends SearchCriteria {
	
	private Integer uid; //钱包用户id
	
	private Byte status; //钱包状态
	
	private Integer updateBy; //更新人
	
	private String username; //用户名称
	private Integer sortBy;//排序方式1-金额降序，2-金额升序

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public Integer getSortBy() {
		return sortBy;
	}

	public void setSortBy(Integer sortBy) {
		this.sortBy = sortBy;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "TdUserAccountCriteria [uid=" + uid + ", status=" + status + ", updateBy=" + updateBy + "]";
	}
	
}