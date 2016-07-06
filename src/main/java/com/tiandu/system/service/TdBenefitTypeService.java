package com.tiandu.system.service;

import java.util.List;

import com.tiandu.system.entity.TdBenefitType;
import com.tiandu.system.search.TdBenefitTypeSearchCriteria;

public interface TdBenefitTypeService {

	public int insert(TdBenefitType type);
	public int deleteByPrimaryKey(Integer id);
	public TdBenefitType findOne(Integer id);
	public int save(TdBenefitType record);
	public int saveFull(TdBenefitType record);
	public List<TdBenefitType> findBySearchCriteria(TdBenefitTypeSearchCriteria sc);
}
