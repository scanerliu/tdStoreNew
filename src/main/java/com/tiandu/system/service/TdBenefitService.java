package com.tiandu.system.service;

import java.util.List;

import com.tiandu.system.entity.TdBenefit;
import com.tiandu.system.search.TdBenefitSearchCriteria;

public interface TdBenefitService {

	public int insert(TdBenefit benefit);
	public int deleteByPrimaryKey(Integer id);
	public TdBenefit findOne(Integer id);
	public int save(TdBenefit record);
	public List<TdBenefit> findBySearchCriteria(TdBenefitSearchCriteria sc);
}
