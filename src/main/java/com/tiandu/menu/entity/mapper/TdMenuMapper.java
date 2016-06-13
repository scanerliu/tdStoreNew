package com.tiandu.menu.entity.mapper;

import java.util.List;

import com.tiandu.menu.entity.TdMenu;
import com.tiandu.menu.search.TdMenuSearchCriteria;

public interface TdMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdMenu record);

    int insertSelective(TdMenu record);

    TdMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdMenu record);

    int updateByPrimaryKey(TdMenu record);
    
    public List<TdMenu> findByTdMenuSearchCriteria(TdMenuSearchCriteria sc);
}