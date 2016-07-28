package com.tiandu.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.custom.entity.TdAgent;
import com.tiandu.custom.search.TdAgentSearchCriteria;
import com.tiandu.product.entity.TdProductSku;
import com.tiandu.product.entity.mapper.TdProductSkuMapper;
import com.tiandu.product.search.TdProductSkuCriteria;
import com.tiandu.product.service.TdProductSkuService;

@Service("tdProductSkuService")
public class TdProductSkuServiceImpl implements TdProductSkuService{

	@Autowired
	private TdProductSkuMapper tdProductSkuMapper;

	@Override
	public Integer deleteByPrimaryKey(Integer id) {
		return tdProductSkuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer save(TdProductSku sku) {
		if(null != sku)
		{
			if(null != sku.getId()){
				return tdProductSkuMapper.updateByPrimaryKey(sku);
			}else{
				return tdProductSkuMapper.insert(sku);
			}
		}
		return null;
	}

	@Override
	public TdProductSku findOne(Integer id) {
		return tdProductSkuMapper.selectByPrimaryKey(id);
	}

	@Override
	public TdProductSku findOneWithProduct(Integer id) {
		return tdProductSkuMapper.selectByPrimaryKeyWithProduct(id);
	}

	@Override
	public List<TdProductSku> findByProductId(Integer proId) {
		return tdProductSkuMapper.selectByProductId(proId);
	}

	@Override
	public Integer deleteByProductId(Integer proId) {
		return tdProductSkuMapper.deleteByProductId(proId);
	}

	@Override
	public List<TdProductSku> findBySearchCriteria(TdProductSkuCriteria sc) {
		if(sc.getProductName() != null && sc.getProductName().equals("")){
			sc.setProductName(null);
		}
		Integer count = tdProductSkuMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		List<TdProductSku> skuList = tdProductSkuMapper.findBySearchCriteria(sc);
		return skuList;
	}

	@Override
	public int updateStock(Integer productSkuId, int num) {
		TdProductSku sku = new TdProductSku(); 
		sku.setId(productSkuId);
		sku.setStock(num);
		return tdProductSkuMapper.updateStock(sku);
	}
	
	
}
