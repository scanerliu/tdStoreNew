package com.tiandu.menu.entity.mapper;

import com.tiandu.menu.entity.TdProductMenuType;

public interface TdProductMenuTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdProductMenuType record);

    int insertSelective(TdProductMenuType record);

    TdProductMenuType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdProductMenuType record);

    int updateByPrimaryKey(TdProductMenuType record);

	int deleteByMenuId(Integer id);
}