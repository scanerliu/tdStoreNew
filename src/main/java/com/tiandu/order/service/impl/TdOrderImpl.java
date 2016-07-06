package com.tiandu.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.order.entity.TdOrder;
import com.tiandu.order.entity.mapper.TdOrderMapper;
import com.tiandu.order.search.TdOrderSearchCriteria;
import com.tiandu.order.service.TdOrderService;
import com.tiandu.product.entity.TdAgentProduct;
import com.tiandu.product.entity.mapper.TdAgentProductMapper;
import com.tiandu.product.search.TdAgentProductSearchCriteria;

/**
 * 
 * @author liuxinbing
 *
 */
@Service("tdOrderService")
public class TdOrderImpl implements TdOrderService{

	@Autowired
	private TdOrderMapper tdOrderMapper;

	@Override
	public Integer deleteByPrimaryKey(Integer orderId) {
		return tdOrderMapper.deleteByPrimaryKey(orderId);
	}

	@Override
	public Integer save(TdOrder e) {
		if(null!=e.getOrderId()){//更新
			return tdOrderMapper.updateByPrimaryKey(e);
		}else{
			return tdOrderMapper.insert(e);
		}
	}

	@Override
	public TdOrder findOne(Integer id) {
		return tdOrderMapper.selectByPrimaryKey(id);
	}

	@Override
	public TdOrder findDetail(Integer id) {
		return tdOrderMapper.findDetail(id);
	}

	@Override
	public List<TdOrder> findBySearchCriteria(TdOrderSearchCriteria sc) {
		if(sc.isFlag()){
			Integer count = tdOrderMapper.countBySearchCriteria(sc);
			sc.setTotalCount(count);
		}
		return tdOrderMapper.findBySearchCriteria(sc);
	}

}
