package com.tiandu.order.entity;

import java.math.BigDecimal;

import com.tiandu.product.entity.TdProduct;
import com.tiandu.product.entity.TdProductSku;

public class TdShoppingcartItem {
    private Integer id;

    private Integer uid;

    private Integer productId;

    private Integer productSkuId;
    
    private Integer itemType;

    private BigDecimal price;
    
    private BigDecimal postage;

    private Integer quantity;
    
    /**
     * 管理商品，查询显示
     */
    private TdProduct product;
    /**
     * 管理货品，查询显示
     */
    private TdProductSku productSku;
    
    /**
     * 购物车操作类型
     * 1-加
     * 2-减 
     */
    private Integer optype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductSkuId() {
        return productSkuId;
    }

    public void setProductSkuId(Integer productSkuId) {
        this.productSkuId = productSkuId;
    }

    public Integer getItemType() {
		return itemType;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPostage() {
		return postage;
	}

	public void setPostage(BigDecimal postage) {
		this.postage = postage;
	}

	public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

	public TdProduct getProduct() {
		return product;
	}

	public void setProduct(TdProduct product) {
		this.product = product;
	}

	public TdProductSku getProductSku() {
		return productSku;
	}

	public void setProductSku(TdProductSku productSku) {
		this.productSku = productSku;
	}

	public Integer getOptype() {
		return optype;
	}

	public void setOptype(Integer optype) {
		this.optype = optype;
	}
    
}