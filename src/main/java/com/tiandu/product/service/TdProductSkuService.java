package com.tiandu.product.service;

import java.util.List;

import com.tiandu.product.entity.TdProductSku;

public interface TdProductSkuService {

	Integer deleteByPrimaryKey(Integer id);
	
	Integer save(TdProductSku sku);
	
	TdProductSku findOne(Integer id);
	
	TdProductSku findOneWithProduct(Integer id);
	
	List<TdProductSku> findByProductId(Integer proId);
	
	Integer deleteByProductId(Integer proId);
}
