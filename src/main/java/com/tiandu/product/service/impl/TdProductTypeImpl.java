package com.tiandu.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.product.entity.TdProductType;
import com.tiandu.product.entity.mapper.TdProductTypeMapper;
import com.tiandu.product.search.TdProductTypeCriteria;
import com.tiandu.product.service.TdProductTypeService;

/**
 * 商品分类Service实现类
 * @author Max
 *
 */
@Service("tdProductTypeService")
public class TdProductTypeImpl implements TdProductTypeService{

	@Autowired
	private TdProductTypeMapper tdProduceTypeMapper;
	
	@Override
	public Integer deleteByPrimaryKey(Integer id) {
		return tdProduceTypeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public TdProductType findOne(Integer id) {
		
		return tdProduceTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer save(TdProductType e) {
		if(null != e)
		{
			if(null != e.getId())
			{
				// 更新
				return tdProduceTypeMapper.updateByPrimaryKey(e);
			}else{
				// 新增
				return tdProduceTypeMapper.insert(e);
			}
		}
		return null;
	}

	@Override
	public List<TdProductType> findBySearchCriteria(TdProductTypeCriteria sc) {
//		Integer count = tdProduceTypeMapper.countByCriteria(sc);
//		sc.setTotalCount(count);
		return tdProduceTypeMapper.findBySearchCriteria(sc);
	}

	/**
	 * 查找商品分类（支持查找三级分类）
	 * @author Max
	 */
	@Override
	public List<TdProductType> findAll() {
		// 查找第一级分类
		List<TdProductType> productList = this.findByParentId(0);
		
		if(null != productList && productList.size() > 0)
		{
			for (TdProductType tdProductType : productList) 
			{
				// 查找二级分类
				List<TdProductType> secondList = this.findByParentId(tdProductType.getId());
				if(null != secondList && secondList.size() > 0)
				{
					for (TdProductType productType : secondList) 
					{
						// 查找三级分类
						List<TdProductType> thirdList = this.findByParentId(productType.getId());
						if(null != thirdList && thirdList.size() > 0)
						{
							productType.setSubList(thirdList);
						}
					}
				}
				tdProductType.setSubList(secondList);
			}
		}
		
		return productList;
	}

	@Override
	public List<TdProductType> findByParentId(Integer id) {
		return tdProduceTypeMapper.findByParentId(id);
	}

}
