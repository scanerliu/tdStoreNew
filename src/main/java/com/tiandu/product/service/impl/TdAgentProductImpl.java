package com.tiandu.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.product.entity.TdAgentProduct;
import com.tiandu.product.entity.mapper.TdAgentProductMapper;
import com.tiandu.product.search.TdAgentProductSearchCriteria;
import com.tiandu.product.service.TdAgentProductService;

/**
 * 
 * @author liuxinbing
 *
 */
@Service("tdAgentProductService")
public class TdAgentProductImpl implements TdAgentProductService{

	@Autowired
	private TdAgentProductMapper tdAgentProductMapper;

	@Override
	public Integer deleteByPrimaryKey(Integer id) {
		return tdAgentProductMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer save(TdAgentProduct p) {
		if(null!=p.getId()){//更新
			return tdAgentProductMapper.updateByPrimaryKey(p);
		}else{
			return tdAgentProductMapper.insert(p);
		}
	}

	@Override
	public TdAgentProduct findOne(Integer id) {
		return tdAgentProductMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TdAgentProduct> findBySearchCriteria(TdAgentProductSearchCriteria sc) {
		if(sc.isFlag()){
			Integer count = tdAgentProductMapper.countByCriteria(sc);
			sc.setTotalCount(count);
		}
		return tdAgentProductMapper.findBySearchCriteria(sc);
	}
	
	
}
