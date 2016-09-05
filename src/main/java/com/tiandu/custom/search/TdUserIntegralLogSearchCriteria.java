package com.tiandu.custom.search;

import com.tiandu.common.search.SearchCriteria;

public class TdUserIntegralLogSearchCriteria extends SearchCriteria {

	private Integer uid; //用户id
	
	private Integer incomeType;	//明细分类：1-收入，2-支出

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(Integer incomeType) {
		this.incomeType = incomeType;
	}
	
}
