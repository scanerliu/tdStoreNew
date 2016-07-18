package com.tiandu.order.vo;

import java.math.BigDecimal;
import java.util.List;

import com.tiandu.order.entity.TdShoppingcartItem;

/**
 * 商品属性键值对
 * @author liuxinbing
 *
 */
public class SkuSpecialVO {
	
	private String sname;	//属性名称
	
	private String soption;	 //属性值

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSoption() {
		return soption;
	}

	public void setSoption(String soption) {
		this.soption = soption;
	}
	
}
