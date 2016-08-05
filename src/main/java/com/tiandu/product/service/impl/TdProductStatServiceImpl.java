package com.tiandu.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.product.entity.TdProductStat;
import com.tiandu.product.entity.mapper.TdProductStatMapper;
import com.tiandu.product.service.TdProductStatService;

@Service("tdProductStatService")
public class TdProductStatServiceImpl implements TdProductStatService{

	@Autowired
	private TdProductStatMapper tdProductStatMapper;

	@Override
	public Integer Insert(TdProductStat stat) {
		if(null != stat)
		{
			if(this.findOne(stat.getProductId()) == null){
				return tdProductStatMapper.insert(stat);
			}
		}
		return null;
	}

	@Override
	public TdProductStat findOne(Integer id) {
		return tdProductStatMapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer deleteByPrimaryKey(Integer productId) {
		return tdProductStatMapper.deleteByPrimaryKey(productId);
	}

	@Override
	public Integer updateByPrimaryKey(TdProductStat record) {
		return tdProductStatMapper.updateByPrimaryKey(record);
	}

	@Override
	public Integer updateByCount(Integer id, Integer quantity) {
		TdProductStat stat = this.findOne(id);
		if(null != stat){
			stat.setBuyCount(stat.getBuyCount()+quantity);
			stat.setBuyTimes(stat.getBuyTimes()+1);
		}
		return this.updateByPrimaryKey(stat);
	}
	
}
