package com.tiandu.article.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.article.entity.TdAdvertisement;
import com.tiandu.article.entity.mapper.TdAdvertisementMapper;
import com.tiandu.article.search.TdAdvertisementSearchCriteria;
import com.tiandu.article.service.TdAdvertisementService;

@Service("tdAdvertService")
public class TdAdvertisementServiceImpl implements TdAdvertisementService{

	
	@Autowired
	TdAdvertisementMapper tdAdvertMapper;
	
	@Override
	public void deleteByPrimaryKey(Integer id) {
		if(null != id){
			tdAdvertMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public Integer insert(TdAdvertisement record) {
		if(null != record)
		{
			tdAdvertMapper.insert(record);
		}
		return null;
	}

	public TdAdvertisement findOne(Integer id) {
		if(null != id)
		{
			return tdAdvertMapper.selectByPrimaryKey(id);
		}
		return null;
	}


	@Override
	public List<TdAdvertisement> findBySearchCriteria(TdAdvertisementSearchCriteria sc) {
		if(sc.isFlag()){
			Integer count = tdAdvertMapper.countByCriteria(sc);
			sc.setTotalCount(count);
		}
		return tdAdvertMapper.findBySearchCriteria(sc);
	}


	@Override
	public Integer save(TdAdvertisement e) {
		if(null != e)
		{
			if(null != e.getId()){
				return tdAdvertMapper.updateByPrimaryKey(e);
			}else{
				return tdAdvertMapper.insert(e);
			}
		}
		return null;
	}
	
}
