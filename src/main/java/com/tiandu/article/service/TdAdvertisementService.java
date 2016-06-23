package com.tiandu.article.service;

import java.util.List;

import com.tiandu.article.entity.TdAdvertisement;
import com.tiandu.article.search.TdAdvertisementSearchCriteria;

public interface TdAdvertisementService {

	
	public void deleteByPrimaryKey(Integer id);

	public Integer insert(TdAdvertisement record);
	
    public List<TdAdvertisement> findBySearchCriteria(TdAdvertisementSearchCriteria sc);
    public Integer save(TdAdvertisement e);
}
