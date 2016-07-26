package com.tiandu.product.search;

import com.tiandu.common.search.SearchCriteria;

public class TdProductDescriptionCriteria extends SearchCriteria{

	private Integer productId;
	
	private Integer type;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
