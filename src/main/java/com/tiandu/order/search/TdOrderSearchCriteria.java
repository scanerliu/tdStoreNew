package com.tiandu.order.search;

import com.tiandu.common.search.SearchCriteria;

public class TdOrderSearchCriteria extends SearchCriteria {

	private String orderNo; //订单编号

    private Byte orderType; //订单类型，1-普通商品订单，2-代理产品订单,3-图片处理订单

    private Byte orderStatus;  //订单状态，订单状态:-1-已取消，1-新订单，2-已完成
    private Byte payStatus;  // 支付状态：1-已支付，2-未支付，3-部分退款，4全部退款
    private Byte shipmentStatus; //发货状态：1-已发货，2-未发货，3-部分退货，4-全部退货
    private Boolean getUpdateUser = false; //获取更新人信息

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Byte getOrderType() {
		return orderType;
	}

	public void setOrderType(Byte orderType) {
		this.orderType = orderType;
	}

	public Byte getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Byte orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Byte getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Byte payStatus) {
		this.payStatus = payStatus;
	}

	public Byte getShipmentStatus() {
		return shipmentStatus;
	}

	public void setShipmentStatus(Byte shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}

	public Boolean getGetUpdateUser() {
		return getUpdateUser;
	}

	public void setGetUpdateUser(Boolean getUpdateUser) {
		this.getUpdateUser = getUpdateUser;
	}
    
    
}
