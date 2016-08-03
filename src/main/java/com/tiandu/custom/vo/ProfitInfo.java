package com.tiandu.custom.vo;
/**
 * 收益信息
 * @author liuxinbing
 *
 */
public class ProfitInfo {
	/**
	 * 订单编号
	 */
	private String orderNo;
	/**
	 * 商品名称
	 */
	private String productName;
	/**
	 * 买方昵称
	 */
	private String buyUserName;
	
	/**
	 * 总金额
	 */
	private String totalAmount;
	
	/**
	 * 收益
	 */
	private String profit;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBuyUserName() {
		return buyUserName;
	}

	public void setBuyUserName(String buyUserName) {
		this.buyUserName = buyUserName;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}
	
}
