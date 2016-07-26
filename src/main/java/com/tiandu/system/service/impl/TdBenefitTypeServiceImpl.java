package com.tiandu.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.system.entity.TdBenefit;
import com.tiandu.system.entity.TdBenefitType;
import com.tiandu.system.entity.mapper.TdBenefitMapper;
import com.tiandu.system.entity.mapper.TdBenefitTypeMapper;
import com.tiandu.system.search.TdBenefitTypeSearchCriteria;
import com.tiandu.system.service.TdBenefitService;
import com.tiandu.system.service.TdBenefitTypeService;

@Service("tdBenefitTypeService")
public class TdBenefitTypeServiceImpl implements TdBenefitTypeService {

	@Autowired
	TdBenefitTypeMapper tdBenefitTypeMapper;
	
	@Autowired
	private TdBenefitService tdBenefitService;

	@Override
	public int insert(TdBenefitType type) {
		return tdBenefitTypeMapper.insert(type);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return tdBenefitTypeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public TdBenefitType findOne(Integer id) {
		return tdBenefitTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int save(TdBenefitType record) {
		if(null==record.getId()){
			return tdBenefitTypeMapper.insert(record);
		}else{
			return tdBenefitTypeMapper.updateByPrimaryKey(record);
		}
	}
	
	@Override
	public int saveFull(TdBenefitType record) {
		int result = 0;
		List<TdBenefit> benefitList = record.getBenefitList();
//		if(null==record.getId()){
//			result = tdBenefitTypeMapper.insert(record);
//		}else{
//			result = tdBenefitTypeMapper.updateByPrimaryKey(record);
//		}
		tdBenefitTypeMapper.updateByPrimaryKey(record);
		//保存分润设置
		if(null!=benefitList&&benefitList.size()>0){
			result = tdBenefitService.saveAll(benefitList);
		}
		return result;
	}

	@Override
	public List<TdBenefitType> findBySearchCriteria(TdBenefitTypeSearchCriteria sc) {
		if(sc.isFlag()){
			Integer count = tdBenefitTypeMapper.countBySearchCriteria(sc);
			sc.setTotalCount(count);
		}
		return tdBenefitTypeMapper.findBySearchCriteria(sc);
	}

	
	
}
