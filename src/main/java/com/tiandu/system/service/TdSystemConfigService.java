package com.tiandu.system.service;

import java.util.List;
import java.util.Map;

import com.tiandu.system.entity.TdSystemConfig;
import com.tiandu.system.search.TdSystemConfigSearchCriteria;

public interface TdSystemConfigService {

	public int insert(TdSystemConfig u);
	public TdSystemConfig findOne(Integer id);
	public TdSystemConfig findByKey(String key);
	public int save(TdSystemConfig record);
	public List<TdSystemConfig> findBySearchCriteria(TdSystemConfigSearchCriteria sc);
	/**
	 * 获取系统配置
	 * @return
	 */
	public Map<String,String> getConfigMap();
}
