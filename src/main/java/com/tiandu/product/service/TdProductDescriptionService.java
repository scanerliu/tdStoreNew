package com.tiandu.product.service;

import java.util.List;

import com.tiandu.product.entity.TdProductDescription;
import com.tiandu.product.search.TdProductDescriptionCriteria;

public interface TdProductDescriptionService {
	
	Integer save(TdProductDescription e);
	
	TdProductDescription findOne(Integer id);

	TdProductDescription findByProductId(TdProductDescriptionCriteria sc);
	
	Integer deleteByProductId(Integer proId);
	
	public List<TdProductDescription> findBySearchCriteria(TdProductDescriptionCriteria sc);
}
