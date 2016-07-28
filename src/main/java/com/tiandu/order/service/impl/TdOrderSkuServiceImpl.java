package com.tiandu.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.order.entity.TdOrderSku;
import com.tiandu.order.entity.mapper.TdOrderMapper;
import com.tiandu.order.entity.mapper.TdOrderSkuMapper;
import com.tiandu.order.search.TdOrderSkuSearchCriteria;
import com.tiandu.order.service.TdOrderSkuService;
import com.tiandu.report.SaleProductReportEntity;

/**
 * 
 * @author L. Gao
 *
 */
@Service("tdOrderSkuService")
public class TdOrderSkuServiceImpl implements TdOrderSkuService{

	@Autowired
	private TdOrderMapper tdOrderMapper;
	@Autowired
	private TdOrderSkuMapper tdOrderSkuMapper;
	

	@Override
	public List<TdOrderSku> findBySearchCriteria(TdOrderSkuSearchCriteria sc) {
		if(sc.isFlag()){
			Integer count = tdOrderSkuMapper.countByCriteria(sc);
			sc.setTotalCount(count);
		}
		return tdOrderSkuMapper.findBySearchCriteria(sc);
	}


	@Override
	public List<SaleProductReportEntity> findGroupBySearchCriteria(TdOrderSkuSearchCriteria sc) {
		if(sc.isFlag()){
			Integer count = tdOrderSkuMapper.countGroupByCriteria(sc);
			if(count == null){
				count = 0;
			}
			sc.setTotalCount(count);
		}
		return tdOrderSkuMapper.findGroupBySearchCriteria(sc);
	}
	
}
