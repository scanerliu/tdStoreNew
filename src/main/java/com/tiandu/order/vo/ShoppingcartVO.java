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
	
	public List<TdShoppingcartItem> getItemList() {
		return itemList;
	}
	
	public void setItemList(List<TdShoppingcartItem> itemList) {
		this.itemList = itemList;
	}
	
}
