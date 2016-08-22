package com.tiandu.order.search;

import java.util.Date;

import com.tiandu.common.search.SearchCriteria;

public class TdOrderSearchCriteria extends SearchCriteria {

	private String orderNo; //订单编号

    private Byte orderType; //订单类型，1-普通商品订单，2-代理产品订单,3-图片处理订单

    private Byte orderStatus;  //订单状态，订单状态:-1-已取消，1-新订单，2-已完成
    private Byte payStatus;  // 支付状态：1-已支付，2-未支付，3-部分退款，4全部退款
    private Byte shipmentStatus; //发货状态：1-已发货，2-未发货，3-部分退货，4-全部退货
    private Boolean getUpdateUser = false; //获取更新人信息
    
    //发货时间段查询
    private Date shippmentTime; // 发货时间
    private Date receiptTime; //收货时间
    
    //前台查询使用
    private Integer uid; //用户id
    private Boolean getProductSku = false; //获取订单货品集合
    private Integer fliterType; //查询分类0-全部订单，1-待付款，2-代发货，3-待收货，4-待评价
    private Integer supplierId; // 供应商ID
    private String keyword;	//关键词，匹配订单编号和商品名称
    private Integer jointId; // 联合订单ID
    
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

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Boolean getGetProductSku() {
		return getProductSku;
	}

	public void setGetProductSku(Boolean getProductSku) {
		this.getProductSku = getProductSku;
	}

	public Integer getFliterType() {
		return fliterType;
	}

	public void setFliterType(Integer fliterType) {
		this.fliterType = fliterType;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Date getShippmentTime() {
		return shippmentTime;
	}

	public void setShippmentTime(Date shippmentTime) {
		this.shippmentTime = shippmentTime;
	}

	public Date getReceiptTime() {
		return receiptTime;
	}

	public void setReceiptTime(Date receiptTime) {
		this.receiptTime = receiptTime;
	}

	public Integer getJointId() {
		return jointId;
	}

	public void setJointId(Integer jointId) {
		this.jointId = jointId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
    
}
