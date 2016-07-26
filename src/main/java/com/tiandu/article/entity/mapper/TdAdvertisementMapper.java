package com.tiandu.article.entity.mapper;

import java.util.List;

import com.tiandu.article.entity.TdAdvertisement;
import com.tiandu.article.search.TdAdvertisementSearchCriteria;

public interface TdAdvertisementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdAdvertisement record);

    int insertSelective(TdAdvertisement record);

    TdAdvertisement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdAdvertisement record);

    int updateByPrimaryKey(TdAdvertisement record);
    
    public Integer countByCriteria(TdAdvertisementSearchCriteria sc);
    public List<TdAdvertisement> findBySearchCriteria(TdAdvertisementSearchCriteria sc);
}