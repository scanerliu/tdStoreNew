package com.tiandu.custom.entity.mapper;

import java.util.List;

import com.tiandu.custom.entity.TdCampaign;
import com.tiandu.custom.search.TdCampaignSearchCriteria;

public interface TdCampaignMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdCampaign record);

    int insertSelective(TdCampaign record);

    TdCampaign selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdCampaign record);

    int updateByPrimaryKeyWithBLOBs(TdCampaign record);

    int updateByPrimaryKey(TdCampaign record);
    
    Integer countByCriteria(TdCampaignSearchCriteria sc);
    public List<TdCampaign> findBySearchCriteria(TdCampaignSearchCriteria sc);
}