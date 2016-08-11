package com.tiandu.product.service;

import java.util.List;

import com.tiandu.product.entity.TdBrand;
import com.tiandu.product.search.TdBrandSearchCriteria;

public interface TdBrandService {

	public Integer delete(Integer id);
	public Integer save(TdBrand e);
	public TdBrand findOne(Integer id);	
	public List<TdBrand> findBySearchCriteria(TdBrandSearchCriteria sc);
	/**
	 * 获取品牌名称首字母大写
	 * @param brandList
	 */
	public void upperFirstLetterList(List<TdBrand> brandList);
}
