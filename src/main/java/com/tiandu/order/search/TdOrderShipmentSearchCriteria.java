package com.tiandu.order.search;

import com.tiandu.common.search.SearchCriteria;

public class TdOrderShipmentSearchCriteria extends SearchCriteria {

	private Integer orderId; //订单id
    private Byte type; //类型，1-发货单，2-退货单
    private boolean getUpdateUser = false; //获取更新人信息
    private boolean getOrder = false; //获取订单信息
    private Integer uid;	//用户id
    private Integer supplyId; // 供应商ID
    private Byte status; //退货单状态：1-申请，2-同意，3-不同意，4-已录物流号，5-完成
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	public boolean isGetUpdateUser() {
		return getUpdateUser;
	}
	public void setGetUpdateUser(boolean getUpdateUser) {
		this.getUpdateUser = getUpdateUser;
	}
	public boolean isGetOrder() {
		return getOrder;
	}
	public void setGetOrder(boolean getOrder) {
		this.getOrder = getOrder;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getSupplyId() {
		return supplyId;
	}
	public void setSupplyId(Integer supplyId) {
		this.supplyId = supplyId;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	
}
