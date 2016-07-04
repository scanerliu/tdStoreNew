package com.tiandu.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.system.entity.TdBenefit;
import com.tiandu.system.entity.mapper.TdBenefitMapper;
import com.tiandu.system.search.TdBenefitSearchCriteria;
import com.tiandu.system.service.TdBenefitService;

@Service("tdBenefitService")
public class TdBenefitServiceImpl implements TdBenefitService {

	@Autowired
	TdBenefitMapper tdBenefitMapper;

	@Override
	public int insert(TdBenefit benefit) {
		return tdBenefitMapper.insert(benefit);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return tdBenefitMapper.deleteByPrimaryKey(id);
	}

	@Override
	public TdBenefit findOne(Integer id) {
		return tdBenefitMapper.selectByPrimaryKey(id);
	}

	@Override
	public int save(TdBenefit record) {
		if(null==record.getId()){
			return tdBenefitMapper.insert(record);
		}else{
			return tdBenefitMapper.updateByPrimaryKey(record);
		}
	}

	@Override
	public List<TdBenefit> findBySearchCriteria(TdBenefitSearchCriteria sc) {
		Integer count = tdBenefitMapper.countBySearchCriteria(sc);
		sc.setTotalCount(count);
		return tdBenefitMapper.findBySearchCriteria(sc);
	}

	
	
}
