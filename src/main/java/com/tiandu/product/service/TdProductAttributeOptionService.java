package com.tiandu.product.service;

import com.tiandu.product.entity.TdProductAttributeOption;

public interface TdProductAttributeOptionService {

	Integer deleteByPrimaryKey(Integer attriId);
	TdProductAttributeOption findOne(Integer id);
	
	Integer save(TdProductAttributeOption e);
	
	Integer deleteByAttriId(Integer id);
}
