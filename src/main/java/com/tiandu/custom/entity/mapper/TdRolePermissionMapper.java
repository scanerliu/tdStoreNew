package com.tiandu.custom.entity.mapper;

import java.util.List;

import com.tiandu.custom.entity.TdRole;
import com.tiandu.custom.entity.TdRolePermission;

public interface TdRolePermissionMapper {
    int insert(TdRolePermission record);

    int insertSelective(TdRolePermission record);
    
    int deleteByRole(TdRole record);
    
    int insertRolePermissions(TdRole record);
    
    public List<TdRolePermission> findRolePermissionsByRoleId(Integer roleId);
}