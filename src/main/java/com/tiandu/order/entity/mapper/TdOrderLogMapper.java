package com.tiandu.order.entity.mapper;

import com.tiandu.order.entity.TdOrderLog;

public interface TdOrderLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdOrderLog record);

    int insertSelective(TdOrderLog record);

    TdOrderLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdOrderLog record);

    int updateByPrimaryKey(TdOrderLog record);
}