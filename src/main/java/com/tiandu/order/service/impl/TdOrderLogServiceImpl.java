package com.tiandu.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.order.entity.TdOrderLog;
import com.tiandu.order.entity.mapper.TdOrderLogMapper;
import com.tiandu.order.search.TdOrderLogSearchCriteria;
import com.tiandu.order.service.TdOrderLogService;

/**
 * 
 * @author liuxinbing
 *
 */
@Service("tdOrderLogService")
public class TdOrderLogServiceImpl implements TdOrderLogService{

	@Autowired
	private TdOrderLogMapper tdOrderLogMapper;

	@Override
	public int save(TdOrderLog log) {
		return tdOrderLogMapper.insert(log);
	}
	@Override
	public TdOrderLog findOne(Integer id) {
		return tdOrderLogMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<TdOrderLog> findBySearchCriteria(TdOrderLogSearchCriteria sc) {
		return tdOrderLogMapper.findBySearchCriteria(sc);
	}

	
}
