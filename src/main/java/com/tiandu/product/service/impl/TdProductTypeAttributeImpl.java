package com.tiandu.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.product.entity.TdProductTypeAttribute;
import com.tiandu.product.entity.mapper.TdProductTypeAttributeMapper;
import com.tiandu.product.search.TdProductTypeAttributeCriteria;
import com.tiandu.product.service.TdProductTypeAttributeService;

@Service("tdProductTypeAttributeService")
public class TdProductTypeAttributeImpl implements TdProductTypeAttributeService{

	@Autowired
	private TdProductTypeAttributeMapper typeAttributeMapper;

	@Override
	public List<TdProductTypeAttribute> findByTypeId(Integer typeId) {
		if(null != typeId)
		{
			// 找出当前类别关联的属性表
			return typeAttributeMapper.findByTypeId(typeId);
		}
		return null;
	}

	@Override
	public List<TdProductTypeAttribute> findBySearchCriteria(TdProductTypeAttributeCriteria sc) {
		Integer count = typeAttributeMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		return typeAttributeMapper.findBySearchCriteria(sc);
	}

	@Override
	public Integer save(TdProductTypeAttribute e) {
		if(null != e)
		{
			if(null != e.getId()){
				return typeAttributeMapper.updateByPrimaryKey(e);
			}else{
				return typeAttributeMapper.insert(e);
			}
		}
		return null;
	}

	@Override
	public int deleteByTypeId(Integer typeId) {
		return typeAttributeMapper.deleteByTypeId(typeId);
	}
	
	
	
	
}
