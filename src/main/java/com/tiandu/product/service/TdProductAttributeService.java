package com.tiandu.product.service;

import java.util.List;

import com.tiandu.product.entity.TdProductAttribute;
import com.tiandu.product.search.TdProductAttributeCriteria;

public interface TdProductAttributeService {

	Integer deleteByPrimaryKey(Integer attriId);

	Integer save(TdProductAttribute e);
	TdProductAttribute findOne(Integer id);
	
	List<TdProductAttribute> findBySearchCriteria(TdProductAttributeCriteria sc);
}
