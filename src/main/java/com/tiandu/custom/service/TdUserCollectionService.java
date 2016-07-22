package com.tiandu.custom.service;

import java.util.List;

import com.tiandu.custom.entity.TdUserCollection;
import com.tiandu.custom.search.TdUserCollectionCriteria;

public interface TdUserCollectionService {
	
	int deleteByPrimaryKey(Integer id);
	
	TdUserCollection findOne(Integer id);
	
	Integer save(TdUserCollection collect);
	
	int getTotalPageCount(TdUserCollectionCriteria sc);
	List<TdUserCollection> findBySearchCriteria(TdUserCollectionCriteria sc);
}
