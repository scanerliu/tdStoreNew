package com.tiandu.product.service;

import com.tiandu.product.entity.TdProductDescription;
import com.tiandu.product.search.TdProductDescriptionCriteria;

public interface TdProductDescriptionService {
	
	Integer save(TdProductDescription e);
	
	TdProductDescription findOne(Integer id);

	TdProductDescription findByProductId(TdProductDescriptionCriteria sc);
	
	Integer deleteByProductId(Integer proId);
}
