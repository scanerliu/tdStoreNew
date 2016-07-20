package com.tiandu.order.vo;

/**
 * 订单提交操作实体类(购物车结算使用)
 * @author liuxinbing
 *
 */
public class OrderForm {

	private Integer addressId;	//收货地址id
	
	private Byte paymentId; //支付方式
	
	private Boolean usePoints; //使用积分抵充金额
	
	private String  userMsg; //客户留言
	
	/**
	 * 商品id，立即下单使用
	 */
	private Integer productId;
	

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public Byte getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Byte paymentId) {
		this.paymentId = paymentId;
	}

	public Boolean getUsePoints() {
		return usePoints;
	}

	public void setUsePoints(Boolean usePoints) {
		this.usePoints = usePoints;
	}

	public String getUserMsg() {
		return userMsg;
	}

	public void setUserMsg(String userMsg) {
		this.userMsg = userMsg;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	
}
