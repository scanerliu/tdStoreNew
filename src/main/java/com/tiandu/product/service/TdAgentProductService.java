package com.tiandu.product.service;

import java.util.List;

import com.tiandu.product.entity.TdAgentProduct;
import com.tiandu.product.search.TdAgentProductSearchCriteria;

public interface TdAgentProductService {

	Integer deleteByPrimaryKey(Integer attriId);

	Integer save(TdAgentProduct e);
	TdAgentProduct findOne(Integer id);
	
	List<TdAgentProduct> findBySearchCriteria(TdAgentProductSearchCriteria sc);
}
