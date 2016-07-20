package com.tiandu.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.order.entity.TdShoppingcartItem;
import com.tiandu.order.entity.mapper.TdShoppingcartItemMapper;
import com.tiandu.order.search.TdShoppingcartSearchCriteria;
import com.tiandu.order.service.TdShoppingcartItemService;

/**
 * 
 * @author liuxinbing
 *
 */
@Service("tdShoppingcartItemService")
public class TdShoppingcartItemServiceImpl implements TdShoppingcartItemService{

	@Autowired
	private TdShoppingcartItemMapper tdShoppingcartItemMapper;

	@Override
	public boolean addToShoppingcart(TdShoppingcartItem item) {
		TdShoppingcartItem pitem = tdShoppingcartItemMapper.selectByUidAndSkuId(item);
		if(null!=pitem){//已经存在购物车时，累加数量
			TdShoppingcartItem uitem = new TdShoppingcartItem();
			uitem.setId(pitem.getId());
			uitem.setQuantity(pitem.getQuantity()+pitem.getQuantity());
			tdShoppingcartItemMapper.updateByPrimaryKeySelective(uitem);
		}else{
			tdShoppingcartItemMapper.insert(item);
		}
		return true;
	}

	@Override
	public boolean removeFromShoppingcart(Integer uid, Integer itemId) {
		tdShoppingcartItemMapper.deleteByPrimaryKey(itemId);
		return true;
	}
	
	@Override
	public boolean removeItemsFromShoppingcart(Integer uid, String itemIds) {
		String[] ids = itemIds.split(",");
		if(null!=ids && ids.length>0){
			for(String id : ids){
				Integer iid = Integer.valueOf(id);
				if(null!=iid){
					tdShoppingcartItemMapper.deleteByPrimaryKey(iid);
				}
			}
			return true;
		}		
		return false;
	}

	@Override
	public List<TdShoppingcartItem> findByUid(Integer uid) {
		return tdShoppingcartItemMapper.findByUid(uid);
	}
	
	@Override
	public int deleteByUid(Integer uid) {
		// TODO Auto-generated method stub
		return tdShoppingcartItemMapper.deleteByUid(uid);
	}

	@Override
	public List<TdShoppingcartItem> findBySearchCriteria(TdShoppingcartSearchCriteria sc) {
		return tdShoppingcartItemMapper.findBySearchCriteria(sc);
	}

	@Override
	public int updateShoppingcartItem(TdShoppingcartItem item) {
		return tdShoppingcartItemMapper.updateByPrimaryKeySelective(item);
	}

	@Override
	public int addShoppingcartItemNum(TdShoppingcartItem item) {
		TdShoppingcartItem dbitem = tdShoppingcartItemMapper.selectByPrimaryKey(item.getId());
		if(null==dbitem || dbitem.getUid()!=item.getUid()){
			return 0;
		}
		return tdShoppingcartItemMapper.updateNumByPrimaryKey(item);
	}
	
}
