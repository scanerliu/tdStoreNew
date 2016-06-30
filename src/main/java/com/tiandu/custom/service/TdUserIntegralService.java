package com.tiandu.custom.service;

import com.tiandu.custom.entity.TdUserIntegral;
import com.tiandu.custom.entity.TdUserIntegralLog;

public interface TdUserIntegralService {

	public int insert(TdUserIntegral u);
	public int updateByPrimaryKeySelective(TdUserIntegral record);
	public TdUserIntegral findOne(Integer id);
	/**
	 * 积分更改增加或删除
	 * @param record
	 * @param log
	 * @return
	 */
	public boolean addIntegral(TdUserIntegral record, TdUserIntegralLog log);
	
}
