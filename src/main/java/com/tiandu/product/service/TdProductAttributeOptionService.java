package com.tiandu.product.service;

import java.util.List;

import com.tiandu.product.entity.TdProductAttributeOption;
import com.tiandu.product.search.TdProductAttributeOptionCriteria;

public interface TdProductAttributeOptionService {

	Integer deleteByPrimaryKey(Integer attriId);
	TdProductAttributeOption findOne(Integer id);
	
	Integer save(TdProductAttributeOption e);
	
	Integer deleteByAttriId(Integer id);
	
	List<TdProductAttributeOption> findBySearchCriteria(TdProductAttributeOptionCriteria sc);
}
