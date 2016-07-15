package com.tiandu.product.vo;

import java.math.BigDecimal;

public class TdProductSkuVO {

	private String specificationids;
	
	private Integer id;

    private Integer productId;

    private String skuCode;

    private BigDecimal salesPrice;
    
    private Integer stock;

	public String getSpecificationids() {
		return specificationids;
	}

	public void setSpecificationids(String specificationids) {
		this.specificationids = specificationids;
	}

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

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public BigDecimal getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(BigDecimal salesPrice) {
		this.salesPrice = salesPrice;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
    
}
