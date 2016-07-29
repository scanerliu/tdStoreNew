package com.tiandu.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.system.entity.TdSystemConfig;
import com.tiandu.system.entity.mapper.TdSystemConfigMapper;
import com.tiandu.system.search.TdSystemConfigSearchCriteria;
import com.tiandu.system.service.TdSystemConfigService;

@Service("tdSystemConfigService")
public class TdSystemConfigServiceImpl implements TdSystemConfigService {

	@Autowired
	TdSystemConfigMapper systemConfigMapper;

	public int insert(TdSystemConfig u) {
		return systemConfigMapper.insert(u);
	}

	public TdSystemConfig findOne(Integer id) {
		return systemConfigMapper.selectByPrimaryKey(id);
	}

	@Override
	public int save(TdSystemConfig record) {
		if(null==record.getId()){
			record.setUpdateTime(new Date());
			return systemConfigMapper.insert(record);
		}else{
			record.setUpdateTime(new Date());
			return systemConfigMapper.updateByPrimaryKey(record);
		}
	}

	@Override
	public TdSystemConfig findByKey(String key) {
		return systemConfigMapper.findByKey(key);
	}

	public List<TdSystemConfig> findBySearchCriteria(TdSystemConfigSearchCriteria sc) {
		return systemConfigMapper.findBySearchCriteria(sc);
	}

	@Override
	public Map<String, String> getConfigMap() {
		TdSystemConfigSearchCriteria sc = new TdSystemConfigSearchCriteria();
		sc.setFlag(false);
		List<TdSystemConfig> list = this.findBySearchCriteria(sc);
		Map map = new HashMap<String,String>();
		for(TdSystemConfig systemConfig : list){
			map.put(systemConfig.getConfigKey(), systemConfig.getConfigValue());
		}
		return map;
	}

	
}
