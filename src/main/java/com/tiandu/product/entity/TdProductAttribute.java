package com.tiandu.product.entity;

import java.util.List;

public class TdProductAttribute {
	private Integer attriId;

	private String name;

	private Byte status;

	private List<TdProductAttributeOption> tdProductAttributeOptionList;

	public Integer getAttriId() {
		return attriId;
	}

	public void setAttriId(Integer attriId) {
		this.attriId = attriId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public List<TdProductAttributeOption> getTdProductAttributeOptionList() {
		return tdProductAttributeOptionList;
	}

	public void setTdProductAttributeOptionList(List<TdProductAttributeOption> tdProductAttributeOptionList) {
		this.tdProductAttributeOptionList = tdProductAttributeOptionList;
	}

}