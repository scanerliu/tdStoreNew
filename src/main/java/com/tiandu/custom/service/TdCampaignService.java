package com.tiandu.custom.service;

import java.util.List;

import com.tiandu.custom.entity.TdCampaign;
import com.tiandu.custom.search.TdCampaignSearchCriteria;

/**
 * 
 * @author L. Gao
 *
 */
public interface TdCampaignService {

	public int insert(TdCampaign u);
	public TdCampaign findOne(Integer id);
	public List<TdCampaign> findBySearchCriteria(TdCampaignSearchCriteria sc);
	public Integer save(TdCampaign TdAgent);
	public Integer delete(Integer id);
}
