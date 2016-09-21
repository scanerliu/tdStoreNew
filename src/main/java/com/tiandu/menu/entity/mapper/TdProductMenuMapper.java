package com.tiandu.menu.entity.mapper;

import java.util.List;

import com.tiandu.menu.entity.TdProductMenu;
import com.tiandu.menu.search.TdProductMenuSearchCriteria;

public interface TdProductMenuMapper {
    int deleteByPrimaryKey(Integer mid);

    int insert(TdProductMenu record);

    int insertSelective(TdProductMenu record);

    TdProductMenu selectByPrimaryKey(Integer mid);

    int updateByPrimaryKeySelective(TdProductMenu record);

    int updateByPrimaryKey(TdProductMenu record);

	List<TdProductMenu> findByTdProductMenuSearchCriteria(TdProductMenuSearchCriteria sc);

	Integer countByCriteria(TdProductMenuSearchCriteria sc);
}