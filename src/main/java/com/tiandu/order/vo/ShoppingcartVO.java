package com.tiandu.order.vo;

import java.math.BigDecimal;
import java.util.List;

import com.tiandu.order.entity.TdShoppingcartItem;

public class ShoppingcartVO {
	/**
	 * 商品个数
	 */
	private Integer totalcount = 0;
	/**
	 * 总金额
	 */
	private BigDecimal totalAmount = BigDecimal.ZERO;
	/**
	 * 总运费
	 */
	private BigDecimal totalPostage = BigDecimal.ZERO;
	/**
	 * 普通商品可以积分抵扣总金额
	 */
	private BigDecimal totalCommonPointAmount = BigDecimal.ZERO;
	/**
	 * 部分积分兑换商品可积分抵扣总金额
	 */
	private BigDecimal totalPartPointAmount = BigDecimal.ZERO;
	/**
	 * 抵扣可以使用积分总数
	 */
	private Integer totalPointsUsed = 0;
	/**
	 * 可以使用积分抵扣金额
	 */
	private BigDecimal totalPointAmount = BigDecimal.ZERO;
	
	private List<TdShoppingcartItem> itemList;
	
	public Integer getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(Integer totalcount) {
		this.totalcount = totalcount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public BigDecimal getTotalPostage() {
		return totalPostage;
	}
	
	public void setTotalPostage(BigDecimal totalPostage) {
		this.totalPostage = totalPostage;
	}
	
	public Integer getTotalPointsUsed() {
		return totalPointsUsed;
	}

	public void setTotalPointsUsed(Integer totalPointsUsed) {
		this.totalPointsUsed = totalPointsUsed;
	}

	public BigDecimal getTotalPointAmount() {
		return totalPointAmount;
	}

	public void setTotalPointAmount(BigDecimal totalPointAmount) {
		this.totalPointAmount = totalPointAmount;
	}

	public BigDecimal getTotalCommonPointAmount() {
		return totalCommonPointAmount;
	}

	public void setTotalCommonPointAmount(BigDecimal totalCommonPointAmount) {
		this.totalCommonPointAmount = totalCommonPointAmount;
	}

	public BigDecimal getTotalPartPointAmount() {
		return totalPartPointAmount;
	}

	public void setTotalPartPointAmount(BigDecimal totalPartPointAmount) {
		this.totalPartPointAmount = totalPartPointAmount;
	}

	public List<TdShoppingcartItem> getItemList() {
		return itemList;
	}
	
	public void setItemList(List<TdShoppingcartItem> itemList) {
		this.itemList = itemList;
	}
	
}
