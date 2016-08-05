package com.tiandu.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.order.entity.TdJointOrder;
import com.tiandu.order.entity.mapper.TdJointOrderMapper;
import com.tiandu.order.service.TdJointOrderService;

@Service("tdJointOrderService")
public class TdJointOrderServiceImpl implements TdJointOrderService{

	@Autowired
	private TdJointOrderMapper tdJointOrderMapper;
	
	@Override
	public TdJointOrder findOne(Integer id) {
		return tdJointOrderMapper.selectByPrimaryKey(id);
	}

	@Override
	public TdJointOrder findByJno(String jno) {
		return tdJointOrderMapper.findByJno(jno);
	}

}
