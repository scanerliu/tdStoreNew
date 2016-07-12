package com.tiandu.order.search;

import com.tiandu.common.search.SearchCriteria;

public class TdShoppingcartSearchCriteria extends SearchCriteria {

	private Integer uid; //会员id

    private Integer itemType; //商品类型，1-普通商品 2-积分兑换商品

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getItemType() {
		return itemType;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

    
}
