package com.tiandu.menu.entity;

import java.util.Date;
import java.util.List;

import com.tiandu.product.entity.TdProductType;

public class TdProductMenu {
    private Integer mid;

    private String name;

    private Integer sort;

    private Integer status;

    private Date updateTime;

    private Integer updateBy;
    
    private List<TdProductMenuType> typeList;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

	public List<TdProductMenuType> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<TdProductMenuType> typeList) {
		this.typeList = typeList;
	}
    
    
}