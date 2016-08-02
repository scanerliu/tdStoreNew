package com.tiandu.product.search;

import com.tiandu.common.search.SearchCriteria;

public class TdProductPackageItemSearchCriteria extends SearchCriteria {
	private Integer id;
	private Integer productId;
	private Integer preprouductId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getPreprouductId() {
		return preprouductId;
	}

	public void setPreprouductId(Integer preprouductId) {
		this.preprouductId = preprouductId;
	}
	
	

}
