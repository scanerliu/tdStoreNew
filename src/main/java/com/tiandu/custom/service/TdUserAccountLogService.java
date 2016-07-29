package com.tiandu.custom.service;

import java.util.List;

import com.tiandu.custom.entity.TdUserAccountLog;
import com.tiandu.custom.search.TdUserAccountLogSearchCriteria;

public interface TdUserAccountLogService {

	public int insert(TdUserAccountLog u);
	public int updateByPrimaryKeySelective(TdUserAccountLog record);
	public TdUserAccountLog findOne(Integer id);
	public List<TdUserAccountLog>  findBySearchCriteria(TdUserAccountLogSearchCriteria sc);
	public List<TdUserAccountLog>  findGroupBySearchCriteria(TdUserAccountLogSearchCriteria sc);
	
}
