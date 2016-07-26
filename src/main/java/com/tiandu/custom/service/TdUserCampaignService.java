package com.tiandu.custom.service;

import java.util.List;

import com.tiandu.custom.entity.TdUserCampaign;
import com.tiandu.custom.search.TdUserCampaignCriteria;

/**
 * 
 * @author Max
 * 
 * 竞选表Service
 * 
 * 创建时间：2016年7月14日 下午5:36:58
 */
public interface TdUserCampaignService {

	int deleteByPrimaryKey(Integer id);
	
	TdUserCampaign findOne(Integer id);
	
	Integer save(TdUserCampaign e);
	
	List<TdUserCampaign> findBySearchCriteria(TdUserCampaignCriteria sc);
	
	
}
