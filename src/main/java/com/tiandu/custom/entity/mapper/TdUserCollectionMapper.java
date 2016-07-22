package com.tiandu.custom.entity.mapper;

import java.util.List;

import com.tiandu.custom.entity.TdUserCollection;
import com.tiandu.custom.search.TdUserCollectionCriteria;

public interface TdUserCollectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdUserCollection record);

    int insertSelective(TdUserCollection record);

    TdUserCollection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdUserCollection record);

    int updateByPrimaryKey(TdUserCollection record);
    
    Integer countByCriteria(TdUserCollectionCriteria sc);
    List<TdUserCollection> findBySearchCriteria(TdUserCollectionCriteria sc);
}