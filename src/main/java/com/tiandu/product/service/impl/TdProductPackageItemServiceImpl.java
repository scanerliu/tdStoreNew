package com.tiandu.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.product.entity.TdProductPackageItem;
import com.tiandu.product.entity.mapper.TdProductPackageItemMapper;
import com.tiandu.product.search.TdProductPackageItemSearchCriteria;
import com.tiandu.product.service.TdProductPackageItemService;

@Service("tdProductPackageItemServiceImpl")
public class TdProductPackageItemServiceImpl implements TdProductPackageItemService{

	@Autowired
	private TdProductPackageItemMapper tdProductPackageItemMapper;

	@Override
	public Integer deleteByPrimaryKey(Integer id) {
		return tdProductPackageItemMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer save(TdProductPackageItem sku) {
		if(null!=sku.getId()){//更新
			return tdProductPackageItemMapper.updateByPrimaryKey(sku);
		}else{
			return tdProductPackageItemMapper.insert(sku);
		}
	}

	@Override
	public TdProductPackageItem findOne(Integer id) {
		return tdProductPackageItemMapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer deleteByProductId(Integer proId) {
		return tdProductPackageItemMapper.deleteByProductId(proId);
	}
	
	@Override
	public Integer countByCriteria(TdProductPackageItemSearchCriteria sc) {
		return tdProductPackageItemMapper.countByCriteria(sc);
	}
	
	@Override
	public List<TdProductPackageItem> findBySearchCriteria(TdProductPackageItemSearchCriteria sc) {
		Integer count = tdProductPackageItemMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		List<TdProductPackageItem> productPackageItemList = tdProductPackageItemMapper.findBySearchCriteria(sc);
		return productPackageItemList;
	}

	
	
}
