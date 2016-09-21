package com.tiandu.menu.entity;

import com.tiandu.product.entity.TdProductType;

public class TdIndexFloorType {
    private Integer id;

    private Integer fid;

    private Integer tid;

    private Integer sort;
    
    private TdProductType productType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
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