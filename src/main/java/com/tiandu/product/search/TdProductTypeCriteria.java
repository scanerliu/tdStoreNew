package com.tiandu.product.search;

import com.tiandu.common.search.SearchCriteria;

public class TdProductTypeCriteria extends SearchCriteria{

	private Integer parentId;
	
	private Byte status;
	
	private Integer sctype; //搜索类型，展示用 1-普通商品，2-商品包，3-零元购，4-10元购，5-预售，6-秒杀商品7-全积分兑换，8-部分积分兑换
	
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getSctype() {
		return sctype;
	}

	public void setSctype(Integer sctype) {
		this.sctype = sctype;
	}
	
}
