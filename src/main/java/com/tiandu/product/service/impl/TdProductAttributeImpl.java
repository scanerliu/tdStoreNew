package com.tiandu.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.product.entity.TdProductAttribute;
import com.tiandu.product.entity.mapper.TdProductAttributeMapper;
import com.tiandu.product.search.TdProductAttributeCriteria;
import com.tiandu.product.service.TdProductAttributeOptionService;
import com.tiandu.product.service.TdProductAttributeService;

@Service("tdProductAttributeService")
public class TdProductAttributeImpl implements TdProductAttributeService{

	@Autowired
	private TdProductAttributeMapper tdProductAttributeMapper;
	
	@Autowired
	private TdProductAttributeOptionService tdProductAttributeOptionService;
	
	@Override
	public Integer deleteByPrimaryKey(Integer id) {
		if(null != id)
		{
			// 删除类型前先删除该类型
			tdProductAttributeOptionService.deleteByAttriId(id);
		}
		return tdProductAttributeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer save(TdProductAttribute e) {
		if(null != e)
		{
			if(null == e.getAttriId()){
				tdProductAttributeMapper.insert(e);
			}else{
				tdProductAttributeMapper.updateByPrimaryKey(e);
			}
		}
		return null;
	}

	@Override
	public TdProductAttribute findOne(Integer id) {
		return tdProductAttributeMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TdProductAttribute> findBySearchCriteria(TdProductAttributeCriteria sc) {
		Integer count = tdProductAttributeMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		return tdProductAttributeMapper.findBySearchCriteria(sc);
	}

}
