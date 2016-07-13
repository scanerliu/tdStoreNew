package com.tiandu.product.entity;

import java.util.List;

public class TdProductTypeAttribute {
	private Integer id;

	private Integer typeId;

	private Integer attriId;
	
	// 关联规格值
	List<TdProductAttribute> tdProductAttributeList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getAttriId() {
		return attriId;
	}

	public void setAttriId(Integer attriId) {
		this.attriId = attriId;
	}

	public List<TdProductAttribute> getTdProductAttributeList() {
		return tdProductAttributeList;
	}

	public void setTdProductAttributeList(List<TdProductAttribute> tdProductAttributeList) {
		this.tdProductAttributeList = tdProductAttributeList;
	}

}