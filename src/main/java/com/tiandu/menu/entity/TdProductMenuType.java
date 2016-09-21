package com.tiandu.menu.entity;

import com.tiandu.product.entity.TdProductType;

public class TdProductMenuType {
    private Integer id;

    private Integer mid;

    private Integer tid;

    private Integer sort;
    
    private TdProductType productType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

	public TdProductType getProductType() {
		return productType;
	}

	public void setProductType(TdProductType productType) {
		this.productType = productType;
	}
    
}