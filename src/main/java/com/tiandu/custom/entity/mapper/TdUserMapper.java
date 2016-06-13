package com.tiandu.custom.entity.mapper;

import com.tiandu.custom.entity.TdUser;

public interface TdUserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(TdUser record);

    int insertSelective(TdUser record);

    TdUser selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(TdUser record);

    int updateByPrimaryKey(TdUser record);
    
    TdUser selectByUname(String uname);
}