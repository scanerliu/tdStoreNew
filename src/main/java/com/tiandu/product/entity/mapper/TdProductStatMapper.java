package com.tiandu.product.entity.mapper;

import com.tiandu.product.entity.TdProductStat;

public interface TdProductStatMapper {
    int deleteByPrimaryKey(Integer productId);

    int insert(TdProductStat record);

    int insertSelective(TdProductStat record);

    TdProductStat selectByPrimaryKey(Integer productId);

    int updateByPrimaryKeySelective(TdProductStat record);

    int updateByPrimaryKey(TdProductStat record);
}