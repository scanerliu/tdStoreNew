package com.tiandu.report;

import java.math.BigDecimal;
import java.util.Date;

/*
 * 销售商品统计类
 */
public class SaleProductReportEntity {
	private String pname;
	private Date ctime;
	private Integer totalNum; // 销量
	private BigDecimal totalPrice; // 销售额
	private BigDecimal totalSupplierPrice; // 成本额
	private BigDecimal totalProfit; // 利润额

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getTotalSupplierPrice() {
		return totalSupplierPrice;
	}

	public void setTotalSupplierPrice(BigDecimal totalSupplierPrice) {
		this.totalSupplierPrice = totalSupplierPrice;
	}

	public BigDecimal getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(BigDecimal totalProfit) {
		this.totalProfit = totalProfit;
	}

}
