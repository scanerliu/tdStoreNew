package com.tiandu.menu.entity;

import java.util.Date;
import java.util.List;

public class TdIndexFloor {
    private Integer fid;

    private String title;

    private Integer sort;

    private Integer status;

    private Date updateTime;

    private Integer updateBy;
    
    private List<TdIndexFloorType> typeList;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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

	public List<TdIndexFloorType> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<TdIndexFloorType> typeList) {
		this.typeList = typeList;
	}
    
}