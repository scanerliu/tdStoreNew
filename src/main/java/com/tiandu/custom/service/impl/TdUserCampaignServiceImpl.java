package com.tiandu.custom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.custom.entity.TdUserCampaign;
import com.tiandu.custom.entity.mapper.TdUserCampaignMapper;
import com.tiandu.custom.search.TdUserCampaignCriteria;
import com.tiandu.custom.service.TdUserCampaignService;
/**
 * 
 * @author Max
 * 
 * 创建时间：2016年7月14日 下午5:37:42
 * 
 */
@Service("tdUserCampaignService")
public class TdUserCampaignServiceImpl implements TdUserCampaignService{

	@Autowired
	private TdUserCampaignMapper tdUserCampaignMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return tdUserCampaignMapper.deleteByPrimaryKey(id);
	}

	@Override
	public TdUserCampaign findOne(Integer id) {
		return tdUserCampaignMapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer save(TdUserCampaign e) {
		if(null != e)
		{
			if(null != e.getId()){
				return tdUserCampaignMapper.updateByPrimaryKey(e);
			}else{
				return tdUserCampaignMapper.insert(e);
			}
		}
		return null;
	}

	@Override
	public List<TdUserCampaign> findBySearchCriteria(TdUserCampaignCriteria sc) {
		Integer count = tdUserCampaignMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		return tdUserCampaignMapper.findBySearchCriteria(sc);
	}

}
