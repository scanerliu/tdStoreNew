package com.tiandu.custom.entity.mapper;

import com.tiandu.custom.entity.TdUserIntegral;

public interface TdUserIntegralMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(TdUserIntegral record);

    int insertSelective(TdUserIntegral record);

    TdUserIntegral selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(TdUserIntegral record);

    int updateByPrimaryKey(TdUserIntegral record);
}