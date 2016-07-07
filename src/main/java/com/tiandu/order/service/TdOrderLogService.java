package com.tiandu.order.service;

import java.util.List;

import com.tiandu.order.entity.TdOrderLog;
import com.tiandu.order.search.TdOrderLogSearchCriteria;

public interface TdOrderLogService {
	
	public int save(TdOrderLog log);

	public TdOrderLog findOne(Integer id);
	
	public List<TdOrderLog> findBySearchCriteria(TdOrderLogSearchCriteria sc);
}
