package com.tiandu.product.service;

import java.util.List;

import com.tiandu.product.entity.TdProduct;
import com.tiandu.product.entity.TdProductSku;
import com.tiandu.product.entity.TdProductTypeAttribute;
import com.tiandu.product.search.TdProductCriteria;
import com.tiandu.product.vo.ProductJsonVO;

public interface TdProductService {

	Integer save(TdProduct e);
	
	Integer deleteByPrimaryKey(Integer id);
	
	TdProduct findOne(Integer id);
	
	// 获取总页数
	int getTotalPageCount(TdProductCriteria sc);
	List<TdProduct> findBySearchCriteria(TdProductCriteria sc);
	/**
	 * 批量操作商品 
	 * @param type 操作类型 1-上架，2-下架，3-热门推荐，4-取消热门推荐，5-新品推荐，6-取消热门推荐，7-精品推荐，8-取消精品推荐，9-分类推荐，10-取消分类推荐
	 * @param productIds 商品id，多个逗号隔开
	 * @return
	 */
	public Integer batchOperProducts(Integer type, String productIds);

	/**
	 * 匹配货品库存状态
	 * @param skuList
	 * @param taList
	 */
	public void matchSkuStockWithAttributeOption(List<TdProductSku> skuList, List<TdProductTypeAttribute> taList);
	
	/**
	 * 转换货品到json格式对象
	 * @param skuList
	 * @return
	 */
	public ProductJsonVO fromProductSkutoProductJson(List<TdProductSku> skuList);
	/**
	 * 转换货品到json格式String
	 * @param skuList
	 * @return
	 */
	public String fromProductSkutoProductJsonString(List<TdProductSku> skuList);
	
}
