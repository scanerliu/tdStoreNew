package com.tiandu.custom.entity.mapper;

import java.util.List;

import com.tiandu.custom.entity.TdUserIntegralLog;
import com.tiandu.custom.search.TdUserIntegralLogSearchCriteria;

public interface TdUserIntegralLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdUserIntegralLog record);

    int insertSelective(TdUserIntegralLog record);

    TdUserIntegralLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdUserIntegralLog record);

    int updateByPrimaryKey(TdUserIntegralLog record);
    
    public List<TdUserIntegralLog> findBySearchCriteria(TdUserIntegralLogSearchCriteria sc);
    public int countByCriteria(TdUserIntegralLogSearchCriteria sc);
}