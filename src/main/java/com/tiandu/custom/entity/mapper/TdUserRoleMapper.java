package com.tiandu.custom.entity.mapper;

import com.tiandu.custom.entity.TdUserRole;

public interface TdUserRoleMapper {
    int insert(TdUserRole record);

    int insertSelective(TdUserRole record);
}