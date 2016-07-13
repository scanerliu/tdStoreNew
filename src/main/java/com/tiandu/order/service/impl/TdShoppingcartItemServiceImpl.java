package com.tiandu.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.common.utils.ConstantsUtils;
import com.tiandu.order.entity.TdOrder;
import com.tiandu.order.entity.TdOrderAddress;
import com.tiandu.order.entity.TdOrderLog;
import com.tiandu.order.entity.TdOrderProduct;
import com.tiandu.order.entity.TdOrderShipment;
import com.tiandu.order.entity.TdOrderShipmentItem;
import com.tiandu.order.entity.TdOrderSku;
import com.tiandu.order.entity.TdShoppingcartItem;
import com.tiandu.order.entity.mapper.TdOrderAddressMapper;
import com.tiandu.order.entity.mapper.TdOrderLogMapper;
import com.tiandu.order.entity.mapper.TdOrderMapper;
import com.tiandu.order.entity.mapper.TdOrderProductMapper;
import com.tiandu.order.entity.mapper.TdOrderShipmentItemMapper;
import com.tiandu.order.entity.mapper.TdOrderShipmentMapper;
import com.tiandu.order.entity.mapper.TdOrderSkuMapper;
import com.tiandu.order.entity.mapper.TdShoppingcartItemMapper;
import com.tiandu.order.search.TdOrderSearchCriteria;
import com.tiandu.order.search.TdShoppingcartSearchCriteria;
import com.tiandu.order.service.TdOrderService;
import com.tiandu.order.service.TdShoppingcartItemService;
import com.tiandu.order.vo.OperResult;
import com.tiandu.order.vo.OrderRefund;

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
		TdShoppingcartItem pitem = tdShoppingcartItemMapper.selectByUidAndSkuId(item.getUid(), item.getProductSkuId());
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
