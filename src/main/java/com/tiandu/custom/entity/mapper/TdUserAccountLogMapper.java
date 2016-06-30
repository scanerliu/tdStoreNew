package com.tiandu.custom.entity.mapper;

import com.tiandu.custom.entity.TdUserAccountLog;

public interface TdUserAccountLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdUserAccountLog record);

    int insertSelective(TdUserAccountLog record);

    TdUserAccountLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdUserAccountLog record);

    int updateByPrimaryKey(TdUserAccountLog record);
}