package com.tiandu.product.search;

import com.tiandu.common.search.SearchCriteria;

public class TdProductSkuCriteria extends SearchCriteria {
	private Integer id;
	private String productName;
	private boolean isAddProductPackage = false;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public boolean isAddProductPackage() {
		return isAddProductPackage;
	}

	public void setAddProductPackage(boolean isAddProductPackage) {
		this.isAddProductPackage = isAddProductPackage;
	}

	
}
