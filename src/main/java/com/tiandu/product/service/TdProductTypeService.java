package com.tiandu.product.service;

import java.util.List;

import com.tiandu.product.entity.TdProductAttribute;
import com.tiandu.product.entity.TdProductType;
import com.tiandu.product.search.TdProductTypeCriteria;
/**
 * 
 * 商品分类Service
 * @author Max
 *
 */
public interface TdProductTypeService {

	public Integer deleteByPrimaryKey(Integer id);
	public TdProductType findOne(Integer id);
	public Integer save(TdProductType e);
	public List<TdProductType> findBySearchCriteria(TdProductTypeCriteria sc);
	
	/**
	 * 获取所有分类
	 * @return
	 */
	public List<TdProductType> findAll(TdProductTypeCriteria sc);
	
	public List<TdProductType> findByParentId(Integer id);
	
	// 查找分类已有属性
	List<TdProductAttribute> findProducrAttribute(Integer typeId);
	// 查找分类为关联属性
	List<TdProductAttribute> findNotProducrAttribute(Integer typeId);
	
	
}
