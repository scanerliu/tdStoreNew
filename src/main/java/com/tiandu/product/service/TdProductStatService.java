package com.tiandu.product.service;

import com.tiandu.product.entity.TdProductStat;

public interface TdProductStatService {

	Integer Insert(TdProductStat stat);
	
	Integer updateByPrimaryKey(TdProductStat record);
	
	TdProductStat findOne(Integer id);
	
	Integer deleteByPrimaryKey(Integer productId);
}
