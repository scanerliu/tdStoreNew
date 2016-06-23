package com.tiandu.article.service;

import java.util.List;

import com.tiandu.article.entity.TdAdsense;
import com.tiandu.article.search.TdAdsenseSearchCriteria;

public interface TdAdsenseService {
	
	public void deleteByPrimaryKey(Integer id);
	public int insert(TdAdsense e);
	public TdAdsense findOne(Integer id);
	public List<TdAdsense> findBySearchCriteria(TdAdsenseSearchCriteria sc);
	public Integer save(TdAdsense e);
}
