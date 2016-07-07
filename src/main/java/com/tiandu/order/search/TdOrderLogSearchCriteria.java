package com.tiandu.order.search;

import com.tiandu.common.search.SearchCriteria;

public class TdOrderLogSearchCriteria extends SearchCriteria {

	private Integer orderId; //订单id
    private Byte operType; //操作类型,1-客户下单，2-订单取消，3-订单完成，4-订单退货，5-订单退款
    private boolean getUpdateUser = false; //获取更新人信息
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Byte getOperType() {
		return operType;
	}
	public void setOperType(Byte operType) {
		this.operType = operType;
	}
	public boolean isGetUpdateUser() {
		return getUpdateUser;
	}
	public void setGetUpdateUser(boolean getUpdateUser) {
		this.getUpdateUser = getUpdateUser;
	}
	
}
