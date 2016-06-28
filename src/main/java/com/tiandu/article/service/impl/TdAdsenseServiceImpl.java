package com.tiandu.article.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.article.entity.TdAdsense;
import com.tiandu.article.entity.mapper.TdAdsenseMapper;
import com.tiandu.article.search.TdAdsenseSearchCriteria;
import com.tiandu.article.service.TdAdsenseService;
import com.tiandu.common.db.DBContextHolder;

@Service("tdAdsenseService")
public class TdAdsenseServiceImpl implements TdAdsenseService{

	@Autowired
	TdAdsenseMapper adsenseMapper;
	
	
	public int insert(TdAdsense e)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_RW);
		return adsenseMapper.insert(e);
	}
	
	public TdAdsense findOne(Integer id)
	{
		return adsenseMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TdAdsense> findBySearchCriteria(TdAdsenseSearchCriteria sc) {
		Integer count = adsenseMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		return adsenseMapper.findBySearchCriteria(sc);
	}
	
	public Integer save(TdAdsense e)
	{
		if(null != e)
		{
			if(null != e.getId())
			{
				// 更新
				return adsenseMapper.updateByPrimaryKey(e);
			}else{
				// 新增
				return adsenseMapper.insert(e);
			}
		}
		return null;
	}

	@Override
	public void deleteByPrimaryKey(Integer id) {
		if(null != id)
		{
			adsenseMapper.deleteByPrimaryKey(id);
		}
		
	}

	@Override
	public TdAdsense findByName(String name) {
		return adsenseMapper.findByName(name);
	}
	
}
