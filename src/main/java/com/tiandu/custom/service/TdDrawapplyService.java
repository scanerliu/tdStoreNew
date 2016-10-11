package com.tiandu.custom.service;

import java.util.List;

import com.tiandu.custom.entity.TdDrawapply;
import com.tiandu.custom.search.TdDrawapplySearchCriteria;

public interface TdDrawapplyService {

	public int insert(TdDrawapply u);
	public TdDrawapply findOne(Integer id);
	public List<TdDrawapply> findBySearchCriteria(TdDrawapplySearchCriteria sc);
	public Integer save(TdDrawapply TdAgent);
	public Integer delete(Integer id);
	public int countByCriteria(TdDrawapplySearchCriteria sc);
}
