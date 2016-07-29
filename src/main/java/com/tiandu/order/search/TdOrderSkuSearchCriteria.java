package com.tiandu.order.search;

import java.util.Date;

import com.tiandu.common.search.SearchCriteria;

public class TdOrderSkuSearchCriteria extends SearchCriteria {
	private String productName;
	private Date beginTime;
	private Date endTime;
	private Byte orderStatus;
	private Boolean isGroup = false;
	private Boolean isAsc; // 是否升序

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Byte getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Byte orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Boolean getIsGroup() {
		return isGroup;
	}

	public void setIsGroup(Boolean isGroup) {
		this.isGroup = isGroup;
	}

	public Boolean getIsAsc() {
		return isAsc;
	}

	public void setIsAsc(Boolean isAsc) {
		this.isAsc = isAsc;
	}
	
	
}
