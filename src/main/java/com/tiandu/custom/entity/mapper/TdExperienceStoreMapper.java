package com.tiandu.custom.entity.mapper;

import java.util.List;

import com.tiandu.custom.entity.TdExperienceStore;
import com.tiandu.custom.search.TdExperienceStoreSearchCriteria;

public interface TdExperienceStoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdExperienceStore record);

    int insertSelective(TdExperienceStore record);

    TdExperienceStore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdExperienceStore record);

    int updateByPrimaryKey(TdExperienceStore record);
    
    Integer countByCriteria(TdExperienceStoreSearchCriteria sc);
    public List<TdExperienceStore> findBySearchCriteria(TdExperienceStoreSearchCriteria sc);
}