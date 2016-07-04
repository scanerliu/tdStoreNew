package com.tiandu.psku.entity.mapper;

import com.tiandu.psku.entity.TdPskuStock;

public interface TdPskuStockMapper {
    int deleteByPrimaryKey(Integer skuId);

    int insert(TdPskuStock record);

    int insertSelective(TdPskuStock record);

    TdPskuStock selectByPrimaryKey(Integer skuId);

    int updateByPrimaryKeySelective(TdPskuStock record);

    int updateByPrimaryKey(TdPskuStock record);
}