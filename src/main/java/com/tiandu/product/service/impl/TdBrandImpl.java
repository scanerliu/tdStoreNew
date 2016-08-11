package com.tiandu.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.common.utils.PinYinUtils;
import com.tiandu.product.entity.TdBrand;
import com.tiandu.product.entity.mapper.TdBrandMapper;
import com.tiandu.product.search.TdBrandSearchCriteria;
import com.tiandu.product.service.TdBrandService;

/**
 * 
 * @author liuxinbing
 *
 */
@Service("tdBrandService")
public class TdBrandImpl implements TdBrandService{

	@Override
	public void upperFirstLetterList(List<TdBrand> brandList) {
		if(null!=brandList){
			for(TdBrand brand : brandList){
				String letter = PinYinUtils.getUpperFirstLetter(brand.getName());
				brand.setLetter(letter);
			}
		}
		
	}

	@Autowired
	private TdBrandMapper tdBrandMapper;

	@Override
	public Integer delete(Integer id) {
		return tdBrandMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer save(TdBrand p) {
		if(null!=p.getId()){//更新
			return tdBrandMapper.updateByPrimaryKey(p);
		}else{
			return tdBrandMapper.insert(p);
		}
	}

	@Override
	public TdBrand findOne(Integer id) {
		return tdBrandMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TdBrand> findBySearchCriteria(TdBrandSearchCriteria sc) {
		if(sc.isFlag()){
			Integer count = tdBrandMapper.countByCriteria(sc);
			sc.setTotalCount(count);
		}
		return tdBrandMapper.findBySearchCriteria(sc);
	}
	
	
}
