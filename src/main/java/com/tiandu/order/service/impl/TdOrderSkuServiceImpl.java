package com.tiandu.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.order.entity.TdOrderSku;
import com.tiandu.order.entity.mapper.TdOrderSkuMapper;
import com.tiandu.order.service.TdOrderSkuService;

@Service("orderSkuService")
public class TdOrderSkuServiceImpl implements TdOrderSkuService{

	@Autowired
	TdOrderSkuMapper tdOrderSkuMapper;

	@Override
	public TdOrderSku findOne(Integer id) {
		return tdOrderSkuMapper.selectByPrimaryKey(id);
	}
	
	
}
