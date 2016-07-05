package com.tiandu.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.product.entity.TdProduct;
import com.tiandu.product.entity.mapper.TdProductMapper;
import com.tiandu.product.search.TdProductCriteria;
import com.tiandu.product.service.TdProductService;

/**
 * TdProductService 实现类
 * @author Max
 *
 */
@Service("tdProductService")
public class TdProductServiceImpl implements TdProductService{

	@Autowired
	private TdProductMapper tdProductMapper;
	
	@Override
	public Integer save(TdProduct e) {
		if(null != e)
		{
			if(null != e.getId()){
				tdProductMapper.updateByPrimaryKey(e);
			}else{
				tdProductMapper.insert(e);
			}
		}
		return null;
	}

	@Override
	public Integer deleteByPrimaryKey(Integer id) {
		return tdProductMapper.deleteByPrimaryKey(id);
	}

	@Override
	public TdProduct findOne(Integer id) {
		return tdProductMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TdProduct> findBySearchCriteria(TdProductCriteria sc) {
		Integer count = tdProductMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		return tdProductMapper.findBySearchCriteria(sc);
	}

	
}
