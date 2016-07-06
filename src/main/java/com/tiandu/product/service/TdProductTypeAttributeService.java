package com.tiandu.product.service;

import java.util.List;

import com.tiandu.product.entity.TdProductTypeAttribute;
import com.tiandu.product.search.TdProductTypeAttributeCriteria;

public interface TdProductTypeAttributeService {

	Integer save(TdProductTypeAttribute e);
	
	int deleteByTypeId(Integer typeId);
	List<TdProductTypeAttribute> findByTypeId(Integer typeId);
	List<TdProductTypeAttribute> findBySearchCriteria(TdProductTypeAttributeCriteria sc);
}
