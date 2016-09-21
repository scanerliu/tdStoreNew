package com.tiandu.menu.entity.mapper;

import com.tiandu.menu.entity.TdIndexFloorType;

public interface TdIndexFloorTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdIndexFloorType record);

    int insertSelective(TdIndexFloorType record);

    TdIndexFloorType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdIndexFloorType record);

    int updateByPrimaryKey(TdIndexFloorType record);

	void deleteByFloorId(Integer id);
}