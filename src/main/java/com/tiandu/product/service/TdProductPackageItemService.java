package com.tiandu.product.service;

import java.util.List;

import com.tiandu.product.entity.TdProductPackageItem;
import com.tiandu.product.search.TdProductPackageItemSearchCriteria;

public interface TdProductPackageItemService {

	Integer deleteByPrimaryKey(Integer id);
	
	Integer save(TdProductPackageItem sku);
	
	TdProductPackageItem findOne(Integer id);
	
	Integer deleteByProductId(Integer proId); 
	
	public Integer countByCriteria(TdProductPackageItemSearchCriteria sc);
	public List<TdProductPackageItem> findBySearchCriteria(TdProductPackageItemSearchCriteria sc);
}
