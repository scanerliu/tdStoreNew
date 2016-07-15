package com.tiandu.custom.entity.mapper;

import java.util.List;

import com.tiandu.custom.entity.TdUserCampaign;
import com.tiandu.custom.search.TdUserCampaignCriteria;

/**
 * 
 * @author Max
 * 
 * 创建时间：2016年7月14日 下午5:51:29
 */
public interface TdUserCampaignMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdUserCampaign record);

    int insertSelective(TdUserCampaign record);

    TdUserCampaign selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdUserCampaign record);

    int updateByPrimaryKeyWithBLOBs(TdUserCampaign record);

    int updateByPrimaryKey(TdUserCampaign record);
    
    Integer countByCriteria(TdUserCampaignCriteria sc);
    List<TdUserCampaign> findBySearchCriteria(TdUserCampaignCriteria sc);
}