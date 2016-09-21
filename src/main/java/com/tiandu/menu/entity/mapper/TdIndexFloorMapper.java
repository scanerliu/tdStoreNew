package com.tiandu.menu.entity.mapper;

import java.util.List;

import com.tiandu.menu.entity.TdIndexFloor;
import com.tiandu.menu.search.TdIndexFloorSearchCriteria;

public interface TdIndexFloorMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(TdIndexFloor record);

    int insertSelective(TdIndexFloor record);

    TdIndexFloor selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(TdIndexFloor record);

    int updateByPrimaryKey(TdIndexFloor record);

	Integer countByCriteria(TdIndexFloorSearchCriteria sc);

	List<TdIndexFloor> findByTdIndexFloorSearchCriteria(TdIndexFloorSearchCriteria sc);
}