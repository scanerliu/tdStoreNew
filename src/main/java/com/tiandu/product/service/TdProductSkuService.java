package com.tiandu.product.service;

import java.util.List;

import com.tiandu.product.entity.TdProductSku;
import com.tiandu.product.search.TdProductSkuCriteria;

public interface TdProductSkuService {

	Integer deleteByPrimaryKey(Integer id);
	
	Integer save(TdProductSku sku);
	
	TdProductSku findOne(Integer id);
	
	TdProductSku findOneWithProduct(Integer id);
	
	List<TdProductSku> findByProductId(Integer proId);
	
	Integer deleteByProductId(Integer proId);
	
	public List<TdProductSku> findBySearchCriteria(TdProductSkuCriteria sc);

	/**
	 * 更新货品库存
	 * @param productSkuId 货品id
	 * @param num 增减数量,负数为减，正数为加
	 * @return
	 */
	public int updateStock(Integer productSkuId, int num);
}
