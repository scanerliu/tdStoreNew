package com.tiandu.custom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.custom.entity.TdUserIntegralLog;
import com.tiandu.custom.entity.mapper.TdUserIntegralLogMapper;
import com.tiandu.custom.search.TdUserIntegralLogSearchCriteria;
import com.tiandu.custom.service.TdUserIntegralLogService;

@Service("tdUserIntegralLogService")
public class TdUserIntegralLogServiceImpl implements TdUserIntegralLogService {

	@Autowired
	TdUserIntegralLogMapper tdUserIntegralLogMapper;

	@Override
	public int insert(TdUserIntegralLog u) {
		return tdUserIntegralLogMapper.insert(u);
	}

	@Override
	public int updateByPrimaryKeySelective(TdUserIntegralLog record) {
		return tdUserIntegralLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public TdUserIntegralLog findOne(Integer id) {
		return tdUserIntegralLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TdUserIntegralLog> findBySearchCriteria(TdUserIntegralLogSearchCriteria sc) {
		Integer count = tdUserIntegralLogMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		return tdUserIntegralLogMapper.findBySearchCriteria(sc);
	}

	@Override
	public Integer save(TdUserIntegralLog userIntegralLog) {
		if(null!=userIntegralLog){
			if(null!=userIntegralLog.getId()){//更新
				return tdUserIntegralLogMapper.updateByPrimaryKey(userIntegralLog);
			}else{
				return tdUserIntegralLogMapper.insert(userIntegralLog);
			}
		}
		return null;
	}
	
}
