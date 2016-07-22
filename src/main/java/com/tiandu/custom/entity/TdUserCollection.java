package com.tiandu.custom.entity;

import java.util.Date;

import com.tiandu.product.entity.TdProduct;

public class TdUserCollection {
    private Integer id;

    private Integer uid;

    private Byte itemType;

    private Integer itemId;

    private Date createTime;
    
    /**
     * 收藏商品
     */
    private TdProduct product;

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

    public Byte getItemType() {
        return itemType;
    }

    public void setItemType(Byte itemType) {
        this.itemType = itemType;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public TdProduct getProduct() {
		return product;
	}

	public void setProduct(TdProduct product) {
		this.product = product;
	}
    
    
}