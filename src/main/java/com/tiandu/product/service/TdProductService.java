package com.tiandu.product.service;

import java.util.List;

import com.tiandu.product.entity.TdProduct;
import com.tiandu.product.search.TdProductCriteria;

public interface TdProductService {

	Integer save(TdProduct e);
	
	Integer deleteByPrimaryKey(Integer id);
	
	TdProduct findOne(Integer id);
	
	List<TdProduct> findBySearchCriteria(TdProductCriteria sc);
	
}
