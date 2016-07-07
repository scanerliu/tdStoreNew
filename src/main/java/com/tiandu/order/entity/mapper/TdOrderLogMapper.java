package com.tiandu.order.entity.mapper;

import java.util.List;

import com.tiandu.order.entity.TdOrderLog;
import com.tiandu.order.search.TdOrderLogSearchCriteria;

public interface TdOrderLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdOrderLog record);

    int insertSelective(TdOrderLog record);

    TdOrderLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdOrderLog record);

    int updateByPrimaryKey(TdOrderLog record);
    
    public List<TdOrderLog> findBySearchCriteria(TdOrderLogSearchCriteria sc);
}