package com.tiandu.custom.search;

import java.util.Date;

import com.tiandu.common.search.SearchCriteria;

public class TdUserAccountLogSearchCriteria extends SearchCriteria {

	private Integer uid; //用户id
	
	private Byte type; //操作类型 1-后台调整，2-分润收入，3-订单退货退款，4-用户提现
	private Integer filterType = 0 ; //时间端  1-三天内，2-一周内
	
	private Integer incomeType;	//明细分类：1-收入，2-支出
	
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

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Integer getFilterType() {
		return filterType;
	}

	public void setFilterType(Integer filterType) {
		this.filterType = filterType;
	}

	public Integer getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(Integer incomeType) {
		this.incomeType = incomeType;
	}
	
}
