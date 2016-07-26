package com.tiandu.psku.entity.mapper;

import com.tiandu.psku.entity.TdPskuStockLog;

public interface TdPskuStockLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdPskuStockLog record);

    int insertSelective(TdPskuStockLog record);

    TdPskuStockLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdPskuStockLog record);

    int updateByPrimaryKey(TdPskuStockLog record);
}