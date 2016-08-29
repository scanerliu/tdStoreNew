package com.tiandu.product.entity;

import java.math.BigDecimal;

public class TdProductPackageItem {
    private Integer id;

    private Integer productId;

    private Integer skuId;

    private Integer sort = 0;

    private Integer quantity;
    
    private Integer preprouductId;
    
    private String productName;
    
    private String productImage;
    
    private BigDecimal price;
    
    private String specifications;
    
    private String skuCode;
    
    private TdProduct prouduct;
    private TdProduct preProuduct;
    

    private TdProductSku productSku;
    
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

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

	public Integer getPreprouductId() {
		return preprouductId;
	}

	public void setPreprouductId(Integer preprouductId) {
		this.preprouductId = preprouductId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public TdProduct getPreProuduct() {
		return preProuduct;
	}

	public void setPreProuduct(TdProduct preProuduct) {
		this.preProuduct = preProuduct;
	}

	public TdProduct getProuduct() {
		return prouduct;
	}

	public void setProuduct(TdProduct prouduct) {
		this.prouduct = prouduct;
	}

	public TdProductSku getProductSku() {
		return productSku;
	}

	public void setProductSku(TdProductSku productSku) {
		this.productSku = productSku;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
    
}