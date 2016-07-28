package com.tiandu.order.service;

import java.util.List;

import com.tiandu.order.entity.TdOrderSku;
import com.tiandu.order.search.TdOrderSkuSearchCriteria;
import com.tiandu.report.SaleProductReportEntity;

public interface TdOrderSkuService {

	public List<TdOrderSku> findBySearchCriteria(TdOrderSkuSearchCriteria sc);
	
	public List<SaleProductReportEntity> findGroupBySearchCriteria(TdOrderSkuSearchCriteria sc);
	
}
