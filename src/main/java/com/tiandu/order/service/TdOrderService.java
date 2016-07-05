package com.tiandu.order.service;

import java.util.List;

import com.tiandu.order.entity.TdOrder;
import com.tiandu.order.search.TdOrderSearchCriteria;

public interface TdOrderService {

	public Integer deleteByPrimaryKey(Integer orderId);

	public Integer save(TdOrder e);
	public TdOrder findOne(Integer id);
	public TdOrder findDetail(Integer id);
	
	public List<TdOrder> findBySearchCriteria(TdOrderSearchCriteria sc);
}
