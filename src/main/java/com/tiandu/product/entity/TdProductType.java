package com.tiandu.product.entity;

import java.util.Date;
import java.util.List;

public class TdProductType {
	private Integer id;

	private String name;

	private Integer parentId;

	private Date createTime;

	private Date updateTime;

	private Integer updateBy;

	private Byte status;

	private String imageUrl;

	private Integer sort;

	private List<TdProductType> subList;
	
	private TdProductType parent;

	// 关联类别规格
	private List<TdProductTypeAttribute> tdProductTypeAttributeList;

	private List<TdProductAttribute> tdProductAttributeList;

	private Integer specifiactionNum;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl == null ? null : imageUrl.trim();
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public List<TdProductType> getSubList() {
		return subList;
	}

	public void setSubList(List<TdProductType> subList) {
		this.subList = subList;
	}

	public List<TdProductTypeAttribute> getTdProductTypeAttributeList() {
		return tdProductTypeAttributeList;
	}

	public void setTdProductTypeAttributeList(List<TdProductTypeAttribute> tdProductTypeAttributeList) {
		this.tdProductTypeAttributeList = tdProductTypeAttributeList;
	}

	public Integer getSpecifiactionNum() {
		return specifiactionNum;
	}

	public void setSpecifiactionNum(Integer specifiactionNum) {
		this.specifiactionNum = specifiactionNum;
	}

	public List<TdProductAttribute> getTdProductAttributeList() {
		return tdProductAttributeList;
	}

	public void setTdProductAttributeList(List<TdProductAttribute> tdProductAttributeList) {
		this.tdProductAttributeList = tdProductAttributeList;
	}

	public TdProductType getParent() {
		return parent;
	}

	public void setParent(TdProductType parent) {
		this.parent = parent;
	}

	/**
	 * 获取类型的上级分类id树，如[1][5][7]
	 * @return
	 */
	public String getParentIdTree() {
		StringBuffer sb = new StringBuffer();
		if(null!=this.getId()){
			if(null!=this.getParent()){
				if(null!=this.getParent().getParentId() && this.getParent().getParentId()>0){
					sb.append("["+this.getParent().getParentId()+"]");
				}
				sb.append("["+this.getParent().getId()+"]");
			}
			sb.append("["+this.getId()+"]");
		}
		return sb.toString();
	}
}