package com.tiandu.product.search;

import com.tiandu.common.search.SearchCriteria;

public class TdProductCriteria extends SearchCriteria{

	public String name;	//商品名称
	public Byte status;	//商品状态，1-正常，2-待审核，3-审核不通过
	public Boolean onshelf;	//是否上架
	public Integer uid; //供应商id
	public Byte kind;  //商品类型 1-普通商品，2-商品包，3-零元购，4-10元购，5-预售，6-秒杀商品'
	
	private Integer typeId; 
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Boolean getOnshelf() {
		return onshelf;
	}

	public void setOnshelf(Boolean onshelf) {
		this.onshelf = onshelf;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Byte getKind() {
		return kind;
	}

	public void setKind(Byte kind) {
		this.kind = kind;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	
	
}
