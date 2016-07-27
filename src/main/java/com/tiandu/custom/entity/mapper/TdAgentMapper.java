package com.tiandu.custom.entity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tiandu.custom.entity.TdAgent;
import com.tiandu.custom.search.TdAgentSearchCriteria;

public interface TdAgentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdAgent record);

    int insertSelective(TdAgent record);

    TdAgent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdAgent record);

    int updateByPrimaryKey(TdAgent record);
    
    Integer countByCriteria(TdAgentSearchCriteria sc);
    public List<TdAgent> findBySearchCriteria(TdAgentSearchCriteria sc);

	TdAgent findByUid(Integer uid);

	public TdAgent findByTypeIdAndRegionId(@Param("productTypeId") Integer productTypeId, @Param("regionId") Integer regionId);
}