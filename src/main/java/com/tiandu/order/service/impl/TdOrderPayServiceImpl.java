package com.tiandu.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.order.entity.TdOrderPay;
import com.tiandu.order.entity.mapper.TdOrderPayMapper;
import com.tiandu.order.service.TdOrderPayService;

/**
 * 
 * @author Max
 * 
 * 创建时间：2016年7月29日 下午5:29:08
 */
@Service("tdOrderPayService")
public class TdOrderPayServiceImpl implements TdOrderPayService{

	@Autowired
	private TdOrderPayMapper tdOrderPayMapper;
	
	
	@Override
	public Integer save(TdOrderPay record) {
		if(null != record){
			return tdOrderPayMapper.insert(record);
		}
		return null;
	}

}
