package com.tiandu.product.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tiandu.common.utils.ConstantsUtils;
import com.tiandu.order.vo.SkuSpecialVO;
import com.tiandu.product.entity.TdProduct;
import com.tiandu.product.entity.TdProductAttribute;
import com.tiandu.product.entity.TdProductAttributeOption;
import com.tiandu.product.entity.TdProductSku;
import com.tiandu.product.entity.TdProductTypeAttribute;
import com.tiandu.product.entity.mapper.TdProductMapper;
import com.tiandu.product.search.TdProductCriteria;
import com.tiandu.product.service.TdProductService;
import com.tiandu.product.vo.AttributeOptionsVO;
import com.tiandu.product.vo.ProductJsonVO;
import com.tiandu.product.vo.TdProductSkuVO;

/**
 * TdProductService 实现类
 * @author Max
 *
 */
@Service("tdProductService")
public class TdProductServiceImpl implements TdProductService{

	@Autowired
	private TdProductMapper tdProductMapper;
	
	@Override
	public Integer save(TdProduct e) {
		if(null != e)
		{
			if(null != e.getId()){
//				tdProductMapper.updateByPrimaryKey(e);
				tdProductMapper.updateByPrimaryKeySelective(e);
			}else{
				tdProductMapper.insert(e);
			}
		}
		return null;
	}

	@Override
	public Integer deleteByPrimaryKey(Integer id) {
		return tdProductMapper.deleteByPrimaryKey(id);
	}

	@Override
	public TdProduct findOne(Integer id) {
		return tdProductMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TdProduct> findBySearchCriteria(TdProductCriteria sc) {
		Integer count = tdProductMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		return tdProductMapper.findBySearchCriteria(sc);
	}

	@Override
	public Integer batchOperProducts(Integer type, String productIds) {
		if(null!=type && type>0 && type<11 && StringUtils.isNotEmpty(productIds)){
			String[] pids = productIds.split(",");
			List<Integer> idlist = new ArrayList<Integer>();
			if(pids.length>0){
				for(String id : pids){
					int iid = Integer.valueOf(id);
					idlist.add(iid);
				}
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("optype", type);
				map.put("ids", idlist);
				return tdProductMapper.updateBatch(map);
			}
			
			return 0;
		} 
		return 0;
	}

	@Override
	public void matchSkuStockWithAttributeOption(List<TdProductSku> skuList, List<TdProductTypeAttribute> taList) {
		if(null!=skuList && skuList.size()>0 && null!=taList && taList.size()>0){
			for(TdProductTypeAttribute typeatrr : taList){
				TdProductAttribute atrribute = typeatrr.getAttribute(); //
				if(null!=atrribute && null!= atrribute.getTdProductAttributeOptionList()){
					List<TdProductAttributeOption> optionList = atrribute.getTdProductAttributeOptionList();
					for(TdProductAttributeOption option : optionList){
						option.setStatus(Byte.valueOf("0"));
						for(TdProductSku sku : skuList){
							boolean status = this.matchstock(atrribute, option, sku);
							if(status){
								option.setStatus(Byte.valueOf("1"));
								break;
							}
						}
					}
				}
			}
		}
		
	}
	
	private boolean matchstock(TdProductAttribute atrribute, TdProductAttributeOption option,TdProductSku sku){
		String opt = sku.getSpecifications();
		JSONObject json;
		try {
			json = new JSONObject(opt);
			String[] keys = json.getNames(json);
			for(String key : keys){
				String val = (String) json.get(key);
				if(key.equals(atrribute.getName())&& val.equals(option.getName())){
					if(sku.getStock()>0){
						return true;
					}else{
						return false;
					}
				}
				
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public ProductJsonVO fromProductSkutoProductJson(List<TdProductSku> skuList) {
		ProductJsonVO product = new ProductJsonVO();
		List<TdProductSkuVO> skList = new ArrayList<TdProductSkuVO>();
		if(null!=skuList && skuList.size()>0){
			for(TdProductSku sku : skuList){
				TdProductSkuVO skuvo = new TdProductSkuVO();
				skuvo.setId(sku.getId());
				skuvo.setProductId(sku.getProductId());
				skuvo.setSalesPrice(sku.getSalesPrice());
				skuvo.setMarketPrice(sku.getMarketPrice());
				skuvo.setSkuCode(sku.getSkuCode());
				skuvo.setStock(sku.getStock());
				String opt = sku.getSpecifications();
				JSONObject json;
				try {
					json = new JSONObject(opt);
					String[] keys = json.getNames(json);
					StringBuffer sk = new StringBuffer();
					if(null!=keys){
						for(String key : keys){
							String val = (String) json.get(key);
							sk.append(val);
							sk.append("_");						
						}
						skuvo.setSpecificationids(sk.substring(0,sk.length()-1));
					}
					skList.add(skuvo);					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			product.setSkuList(skList);
		}
		return product;
	}

	@Override
	public String fromProductSkutoProductJsonString(List<TdProductSku> skuList) {
		ProductJsonVO product = this.fromProductSkutoProductJson(skuList);
		Gson gson = new Gson();
		String json = gson.toJson(product);
		return json;
	}

	@Override
	public int getTotalPageCount(TdProductCriteria sc) {
		Integer count = tdProductMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		return sc.getTotalPageCount();
	}

	@Override
	public void seckillProduct() {
		TdProductCriteria sc = new TdProductCriteria();
		sc.setSeckillEnd(new Date());
		sc.setOnshelf(true);
		sc.setFlag(false);
		
		// 秒杀
		sc.setKind(ConstantsUtils.PRODUCT_KIND_SECKILL);
		List<TdProduct> seckillList = this.findBySearchCriteria(sc);
		if(null != seckillList && seckillList.size() > 0){
			for (TdProduct tdProduct : seckillList) {
				tdProduct.setOnshelf(false);
				tdProductMapper.updateByPrimaryKey(tdProduct);
			}
		}
		
		// 预售
		sc.setKind(ConstantsUtils.PRODUCT_KIND_PRESALE);
		List<TdProduct> presaleList = this.findBySearchCriteria(sc);
		if(null != presaleList && presaleList.size() > 0){
			for (TdProduct tdProduct : presaleList) {
				tdProduct.setOnshelf(false);
				tdProductMapper.updateByPrimaryKey(tdProduct);
			}
		}
		
	}

	@Override
	public int updateStock(Integer productId, int i) {
		TdProduct product = new TdProduct(); 
		product.setId(productId);
		product.setQuantum(i);
		return tdProductMapper.updateStock(product);
		
	}

	@Override
	public String getSpecificationatsJsonString(List<SkuSpecialVO> specialList) {
		if(null!=specialList && specialList.size()>0){
			//去重排序
			List<SkuSpecialVO> listTemp= new ArrayList<SkuSpecialVO>();  
			Iterator<SkuSpecialVO> it=specialList.iterator();
			while(it.hasNext()){  
				SkuSpecialVO a=it.next();  
				if(listTemp.contains(a)){  
					it.remove();  
				}else{  
					listTemp.add(a);  
				}  
			}
			Map<String,Set<String>> speas = new HashMap<String,Set<String>>();
			for(SkuSpecialVO sp : specialList){
				Set<String> gt = speas.get(sp.getSname());
				if(null!=gt){
					gt.add(sp.getSoption());
				}else{
					Set<String> options = new HashSet<String>();
					options.add(sp.getSoption());
					speas.put(sp.getSname(), options);
				}
			}
			List<AttributeOptionsVO> aos = new ArrayList<AttributeOptionsVO>();
			for (Map.Entry<String,Set<String>> entry : speas.entrySet()) {  
				AttributeOptionsVO ao = new AttributeOptionsVO();
				ao.setSname(entry.getKey());
				ao.setSoptions(entry.getValue());
				aos.add(ao);
			}
			Gson gson = new Gson();
			return gson.toJson(aos);
		}
		return "";
	}

	@Override
	public void joinSelfAttributeOption(TdProduct product, List<TdProductTypeAttribute> taList) {
		if(StringUtils.isNotBlank(product.getSpecificationats())){
			Gson gson = new Gson();
			AttributeOptionsVO  aos = null;
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(product.getSpecificationats());
			JsonArray sarr = element.getAsJsonArray();
			Iterator it = sarr.iterator();
			while(it.hasNext()){
				JsonElement e = (JsonElement)it.next();
				aos = gson.fromJson(e, AttributeOptionsVO.class);
				for(TdProductTypeAttribute typeatrr : taList){
					TdProductAttribute atrribute = typeatrr.getAttribute(); //
					if(null!=atrribute && atrribute.getName().equals(aos.getSname())){
						List<TdProductAttributeOption> optionList = null;
						if(null!= atrribute.getTdProductAttributeOptionList()){
							optionList = atrribute.getTdProductAttributeOptionList();//获取系统规格选项值
							for(String aoption : aos.getSoptions()){//循环商品已有选项值
								Boolean exist = false;
								for(TdProductAttributeOption option : optionList){//循环系统规格选项值
									if(option.getName().equals(aoption)){//如果系统中存在相同的，则跳出
										exist = true;
										break;
									}
								}
								if(!exist){//如果系统选择值不存在，则添加进去
									TdProductAttributeOption otp = new TdProductAttributeOption();
									otp.setName(aoption);
									otp.setStatus(Byte.valueOf("1"));
									optionList.add(otp);
								}
							}
						}else{
							optionList = new ArrayList<TdProductAttributeOption>();
							for(String aoption : aos.getSoptions()){//循环商品已有选项值
								TdProductAttributeOption otp = new TdProductAttributeOption();
								otp.setName(aoption);
								otp.setStatus(Byte.valueOf("1"));
								optionList.add(otp);
							}
						}
						
					}
				}
			}
			
		}		
	}

	@Override
	public List<AttributeOptionsVO> getProductAttributeWithOptions(TdProduct product) {
		List<AttributeOptionsVO> aosList = new ArrayList<AttributeOptionsVO>();
		if(StringUtils.isNotBlank(product.getSpecificationats())){
			Gson gson = new Gson();
			AttributeOptionsVO  aos = null;
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(product.getSpecificationats());
			JsonArray sarr = element.getAsJsonArray();
			Iterator it = sarr.iterator();
			while(it.hasNext()){
				JsonElement e = (JsonElement)it.next();
				aos = gson.fromJson(e, AttributeOptionsVO.class);
				aosList.add(aos);
			}
			
		}
		return aosList;
	}
	
	
}
