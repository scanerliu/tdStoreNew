package com.tiandu.custom.entity.mapper;

import java.util.List;

import com.tiandu.custom.entity.TdDrawapply;
import com.tiandu.custom.search.TdDrawapplySearchCriteria;

public interface TdDrawapplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdDrawapply record);

    int insertSelective(TdDrawapply record);

    TdDrawapply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdDrawapply record);

    int updateByPrimaryKey(TdDrawapply record);

	Integer countByCriteria(TdDrawapplySearchCriteria sc);

	List<TdDrawapply> findBySearchCriteria(TdDrawapplySearchCriteria sc);
}