package com.tiandu.system.entity.mapper;

import java.util.List;

import com.tiandu.system.entity.TdSystemConfig;
import com.tiandu.system.search.TdSystemConfigSearchCriteria;

public interface TdSystemConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdSystemConfig record);

    int insertSelective(TdSystemConfig record);

    TdSystemConfig selectByPrimaryKey(Integer id);
    public TdSystemConfig findByKey(String key);

    int updateByPrimaryKeySelective(TdSystemConfig record);

    int updateByPrimaryKey(TdSystemConfig record);
    
    public List<TdSystemConfig> findBySearchCriteria(TdSystemConfigSearchCriteria sc);
}