package com.tiandu.article.entity.mapper;

import java.util.List;

import com.tiandu.article.entity.TdAdsense;
import com.tiandu.article.search.TdAdsenseSearchCriteria;

public interface TdAdsenseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdAdsense record);

    int insertSelective(TdAdsense record);

    TdAdsense selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdAdsense record);

    int updateByPrimaryKey(TdAdsense record);
    
    public Integer countByCriteria(TdAdsenseSearchCriteria sc);
    public List<TdAdsense> findBySearchCriteria(TdAdsenseSearchCriteria sc);
    public TdAdsense findByName(String name);
}