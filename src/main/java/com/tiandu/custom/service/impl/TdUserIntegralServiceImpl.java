package com.tiandu.custom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.custom.entity.TdUserIntegral;
import com.tiandu.custom.entity.TdUserIntegralLog;
import com.tiandu.custom.entity.TdUserSign;
import com.tiandu.custom.entity.mapper.TdUserIntegralLogMapper;
import com.tiandu.custom.entity.mapper.TdUserIntegralMapper;
import com.tiandu.custom.service.TdUserIntegralService;

@Service("tdUserIntegralService")
public class TdUserIntegralServiceImpl implements TdUserIntegralService {

	@Autowired
	TdUserIntegralMapper tdUserIntegralMapper;
	
	@Autowired
	TdUserIntegralLogMapper tdUserIntegralLogMapper;

	@Override
	public int insert(TdUserIntegral u) {
		return tdUserIntegralMapper.insert(u);
	}

	@Override
	public int updateByPrimaryKeySelective(TdUserIntegral record) {
		return tdUserIntegralMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public TdUserIntegral findOne(Integer id) {
		return tdUserIntegralMapper.selectByPrimaryKey(id);
	}

	@Override
	public synchronized boolean addIntegral(TdUserIntegral record, TdUserIntegralLog log) {
		TdUserIntegral point = tdUserIntegralMapper.selectByPrimaryKey(record.getUid());
		int pointcount = point.getIntegral()+log.getIntegral();
		log.setPreintegral(point.getIntegral());
		log.setUid(point.getUid());
		record.setIntegral(pointcount);		
		tdUserIntegralMapper.updateByPrimaryKey(record);
		tdUserIntegralLogMapper.insert(log);
		return true;
	}

	@Override
	public Integer save(TdUserIntegral userIntegral) {
		if(null != userIntegral){
			if(null != userIntegral.getUid()){//更新
				return tdUserIntegralMapper.updateByPrimaryKey(userIntegral);
			}else{
				return tdUserIntegralMapper.insert(userIntegral);
			}
		}
		return null;
	}
	

	
	
	
}
