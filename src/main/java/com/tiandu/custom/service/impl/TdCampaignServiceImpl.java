package com.tiandu.custom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.common.db.DBContextHolder;
import com.tiandu.custom.entity.TdCampaign;
import com.tiandu.custom.entity.mapper.TdCampaignMapper;
import com.tiandu.custom.search.TdCampaignSearchCriteria;
import com.tiandu.custom.service.TdCampaignService;
import com.tiandu.custom.service.TdUserService;

/**
 * 
 * @author L. Gao
 *
 */
@Service("tdCampaignService")
public class TdCampaignServiceImpl implements TdCampaignService {
	
	@Autowired
	TdCampaignMapper campaignMapper;
	
	@Autowired
	TdUserService tdUserService;
	
	@Override
	public int insert(TdCampaign u) {
		DBContextHolder.setDbType(DBContextHolder.DB_RW);
		return campaignMapper.insert(u);
	}

	@Override
	public TdCampaign findOne(Integer id) {
		return campaignMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TdCampaign> findBySearchCriteria(TdCampaignSearchCriteria sc) {
		if(sc.getRegionId() != null && sc.getRegionId() == -1){
			sc.setRegionId(null);
		}
		if(sc.getCreateBy() != null && sc.getCreateBy() == -1){
			sc.setCreateBy(null);
		}
		if(sc.getUpdateBy() != null && sc.getUpdateBy() == -1){
			sc.setUpdateBy(null);
		}
		
		sc.setAssociationTdDistrict(true);
		sc.setAssociationcCreatePerson(true);
		sc.setAssociationUpdatePerson(true);
		Integer count = campaignMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		List<TdCampaign> campaignList = campaignMapper.findBySearchCriteria(sc);
		return campaignList;
	}
	
	
	@Override
	public Integer save(TdCampaign tdCampaign) {
		if(null!=tdCampaign){
			if(null!=tdCampaign.getId()){//更新
				return campaignMapper.updateByPrimaryKeyWithBLOBs(tdCampaign);
			}else{
				return campaignMapper.insert(tdCampaign);
			}
		}
		return null;
	}

	@Override
	public Integer delete(Integer id) {
		return campaignMapper.deleteByPrimaryKey(id);
	}

}
