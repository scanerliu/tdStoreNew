package com.tiandu.order.service;

import com.tiandu.order.entity.TdJointOrder;

public interface TdJointOrderService {

	TdJointOrder findOne(Integer id);
	
	TdJointOrder findByJno(String jno);
	
}
