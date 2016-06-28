package com.tiandu.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.product.entity.TdProductAttributeOption;
import com.tiandu.product.entity.mapper.TdProductAttributeOptionMapper;
import com.tiandu.product.service.TdProductAttributeOptionService;

@Service("tdProductAttributeOptionService")
public class TdProductAttributeOptionImpl implements TdProductAttributeOptionService{

	
	@Autowired
	private TdProductAttributeOptionMapper optionMapper;

	@Override
	public Integer deleteByPrimaryKey(Integer attriId) {
		
		return optionMapper.deleteByPrimaryKey(attriId);
	}

	@Override
	public TdProductAttributeOption findOne(Integer id) {
		return optionMapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer save(TdProductAttributeOption e) {
		 if(null != e)
		 {
			 if(null != e.getId()){
				 return optionMapper.updateByPrimaryKey(e);
			 }else{
				 return optionMapper.insert(e);
			 }
		 }
		return null;
	}

	@Override
	public Integer deleteByAttriId(Integer id) {
		return optionMapper.deleteByAttriId(id);
	}
	
	
}
