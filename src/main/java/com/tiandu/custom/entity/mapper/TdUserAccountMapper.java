package com.tiandu.custom.entity.mapper;

import com.tiandu.custom.entity.TdUserAccount;

public interface TdUserAccountMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(TdUserAccount record);

    int insertSelective(TdUserAccount record);

    TdUserAccount selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(TdUserAccount record);

    int updateByPrimaryKey(TdUserAccount record);
}