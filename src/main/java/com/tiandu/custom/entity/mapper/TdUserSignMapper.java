package com.tiandu.custom.entity.mapper;

import java.util.List;

import com.tiandu.custom.entity.TdUserSign;
import com.tiandu.custom.search.TdUserSignSearchCriteria;

public interface TdUserSignMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdUserSign record);

    int insertSelective(TdUserSign record);

    TdUserSign selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdUserSign record);

    int updateByPrimaryKey(TdUserSign record);
    
    Integer countByCriteria(TdUserSignSearchCriteria sc);
    public List<TdUserSign> findBySearchCriteria(TdUserSignSearchCriteria sc);
}