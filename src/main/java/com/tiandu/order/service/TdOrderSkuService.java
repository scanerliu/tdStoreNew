package com.tiandu.order.service;

import java.util.List;

import com.tiandu.order.entity.TdOrderSku;
import com.tiandu.order.search.TdOrderSkuSearchCriteria;
import com.tiandu.report.SaleProductReportEntity;

/**
 * 
 * @author Max
 * 
 * 创建时间：2016年7月27日 下午5:06:29
 */
public interface TdOrderSkuService {

	public List<TdOrderSku> findBySearchCriteria(TdOrderSkuSearchCriteria sc);
	
	public List<SaleProductReportEntity> findGroupBySearchCriteria(TdOrderSkuSearchCriteria sc);
	
	TdOrderSku findOne(Integer id);
	
}
