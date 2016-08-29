package com.tiandu.product.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.product.entity.TdProductAttribute;
import com.tiandu.product.entity.TdProductType;
import com.tiandu.product.entity.TdProductTypeAttribute;
import com.tiandu.product.entity.mapper.TdProductTypeMapper;
import com.tiandu.product.search.TdProductAttributeCriteria;
import com.tiandu.product.search.TdProductTypeCriteria;
import com.tiandu.product.service.TdProductAttributeService;
import com.tiandu.product.service.TdProductTypeAttributeService;
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
	
	@Autowired
	private TdProductTypeAttributeService tdProductTypeAttributeService;
	
	@Autowired
	private TdProductAttributeService tdProductAttributeService;
	
	
	
	@Override
	public Integer deleteByPrimaryKey(Integer id) {
		return tdProduceTypeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public TdProductType findOne(Integer id) {
		
		return tdProduceTypeMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public TdProductType findOneWithParents(Integer id) {
		TdProductType type = tdProduceTypeMapper.selectByPrimaryKey(id);
		if(null!=type&& null!=type.getParentId() && type.getParentId()>0){//查找上一级分类
			TdProductType uptype = tdProduceTypeMapper.selectByPrimaryKey(type.getParentId());
			if(null!=uptype&& null!=uptype.getParentId() && uptype.getParentId()>0){//查找上二级分类
				TdProductType up2type = tdProduceTypeMapper.selectByPrimaryKey(uptype.getParentId());
				if(null!=up2type){//查找上二级分类
					uptype.setParent(up2type);
				}
			}
			type.setParent(uptype);
		}
		return type;
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
	public List<TdProductType> findAll(TdProductTypeCriteria sc) {
		// 查找第一级分类
//		TdProductTypeCriteria sc = new TdProductTypeCriteria();
		sc.setFlag(false);
		sc.setParentId(0);
		List<TdProductType> productList = this.findBySearchCriteria(sc);
		
		if(null != productList && productList.size() > 0)
		{
			for (TdProductType tdProductType : productList) 
			{
				// 查找二级分类
				sc.setParentId(tdProductType.getId());
				List<TdProductType> secondList = this.findBySearchCriteria(sc);
				if(null != secondList && secondList.size() > 0)
				{
					for (TdProductType productType : secondList) 
					{
						// 查找三级分类
						sc.setParentId(productType.getId());
						List<TdProductType> thirdList = this.findBySearchCriteria(sc);
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
		List<TdProductType> typeList = new ArrayList<>();
		
		typeList = tdProduceTypeMapper.findByParentId(id);
		
		if(id != 0){
			for (TdProductType tdProductType : typeList) {
				tdProductType.setSubList(tdProduceTypeMapper.findByParentId(tdProductType.getId()));
			}
		}
		
 		return typeList;
	}
	
	/**
	 * @author Max
	 * 查找属性已关联的属性集合
	 * 
	 */
	public List<TdProductAttribute> findProducrAttribute(Integer typeId)
	{
		List<TdProductAttribute> productAttrList = new ArrayList<>();
		if(null != typeId)
		{
			// 查出所有属性
			List<TdProductTypeAttribute> typeAttrList = tdProductTypeAttributeService.findByTypeId(typeId);
			
			// 查出分类已有的属性集合
			TdProductAttributeCriteria sc = new TdProductAttributeCriteria();
			sc.setFlag(false);
			sc.setStatus((byte)1);
			List<TdProductAttribute> attrList = tdProductAttributeService.findBySearchCriteria(sc);
			
			if(null != attrList && attrList.size() > 0 )
			{
				if(null != typeAttrList && typeAttrList.size() > 0)
				{
					// 循环所有属性
					for (TdProductAttribute tdProductAttribute : attrList) 
					{
						// 循环所有已有属性
						for (TdProductTypeAttribute typeAttr : typeAttrList) 
						{
							if(tdProductAttribute.getAttriId() == typeAttr.getAttriId())
							{
								// 添加已有属性集合
								productAttrList.add(tdProductAttribute);
							}
						}
					}
				}
			}
			
		}
		return productAttrList;
	}
	
	/**
	 * @author Max
	 * 查找分类未关联的属性集合
	 * 
	 */
	public List<TdProductAttribute> findNotProducrAttribute(Integer typeId)
	{
		List<TdProductAttribute> productAttrList = new ArrayList<>();
		
		// 查出所有属性
		TdProductAttributeCriteria sc = new TdProductAttributeCriteria();
		sc.setFlag(false);
		sc.setStatus((byte)1);
		List<TdProductAttribute> attrList = tdProductAttributeService.findBySearchCriteria(sc);
		
		// 查出分类已有的属性集合
		List<TdProductTypeAttribute> typeAttrList = tdProductTypeAttributeService.findByTypeId(typeId);
		
		if(null != attrList && attrList.size() > 0 )
		{
			productAttrList = attrList;
			if(null != typeAttrList && typeAttrList.size() > 0)
			{
				// 循环所有属性
				for (int i = 0; i < attrList.size(); i++) 
				{
					for (int j = 0; j < typeAttrList.size(); j++) 
					{
						TdProductAttribute tdProductAttribute = attrList.get(i);
						TdProductTypeAttribute typeAttr = typeAttrList.get(j);
						
						if(tdProductAttribute.getAttriId() == typeAttr.getAttriId())
						{
							// 去掉已有的属性
							productAttrList.remove(i);
							if(i != 0 ){i--;}
						}
					}
				}
			}
		}
		return productAttrList;
	}

	@Override
	public List<TdProductType> findThirdType(TdProductTypeCriteria sc) {
		sc.setFlag(false);
		sc.setParentId(0);
		List<TdProductType> productList = this.findBySearchCriteria(sc);
		
		List<TdProductType> thirdTypeList = new ArrayList<>();
		
		if(null != productList && productList.size() > 0)
		{
			for (TdProductType tdProductType : productList) 
			{
				// 查找二级分类
				sc.setParentId(tdProductType.getId());
				List<TdProductType> secondList = this.findBySearchCriteria(sc);
				if(null != secondList && secondList.size() > 0)
				{
					for (TdProductType productType : secondList) 
					{
						// 查找三级分类
						sc.setParentId(productType.getId());
						List<TdProductType> thirdList = this.findBySearchCriteria(sc);
						if(null != thirdList && thirdList.size() > 0)
						{
							thirdTypeList.addAll(thirdList);
						}
					}
				}
			}
		}
		return thirdTypeList;
	}

}
