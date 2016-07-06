package com.tiandu.custom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.custom.entity.TdUserAccountLog;
import com.tiandu.custom.entity.mapper.TdUserAccountLogMapper;
import com.tiandu.custom.search.TdUserAccountLogSearchCriteria;
import com.tiandu.custom.service.TdUserAccountLogService;

@Service("tdUserAccountLogService")
public class TdUserAccountLogServiceImpl implements TdUserAccountLogService {

	@Autowired
	TdUserAccountLogMapper tdUserAccountLogMapper;

	@Override
	public int insert(TdUserAccountLog u) {
		return tdUserAccountLogMapper.insert(u);
	}

	@Override
	public int updateByPrimaryKeySelective(TdUserAccountLog record) {
		return tdUserAccountLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public TdUserAccountLog findOne(Integer id) {
		return tdUserAccountLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TdUserAccountLog> findBySearchCriteria(TdUserAccountLogSearchCriteria sc) {
		Integer count = tdUserAccountLogMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		return tdUserAccountLogMapper.findBySearchCriteria(sc);
	}
	
}
