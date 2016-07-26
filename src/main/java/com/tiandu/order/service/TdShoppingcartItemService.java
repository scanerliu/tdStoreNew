package com.tiandu.order.service;

import java.util.List;

import com.tiandu.order.entity.TdShoppingcartItem;
import com.tiandu.order.search.TdShoppingcartSearchCriteria;

/**
 * 
 * @author liuxinbing
 *
 */
public interface TdShoppingcartItemService {

	/**
	 * 加入商品到购物车
	 * @param uid 用户id
	 * @param item 购物车详情
	 * @return
	 */
	public boolean addToShoppingcart(TdShoppingcartItem item);
	/**
	 * 从购物车移除商品
	 * @param uid
	 * @param itemId
	 * @return
	 */
	public boolean removeFromShoppingcart(Integer uid, Integer itemId);	
	/**
	 * 从购物车移除商品
	 * @param uid
	 * @param itemIds
	 * @return
	 */
	public boolean removeItemsFromShoppingcart(Integer uid, String itemIds);	
	
	/**
	 * 更新商品数量
	 * @param uid
	 * @param itemId
	 * @return
	 */
	public int updateShoppingcartItem(TdShoppingcartItem item);	
	/**
	 * 加减商品数量
	 * @param item
	 * @return
	 */
	public int addShoppingcartItemNum(TdShoppingcartItem item);	
	
	/**
	 * 获取购物车商品详情
	 * @param uid 会员id
	 * @return
	 */
	public List<TdShoppingcartItem> findByUid(Integer uid);
	/**
	 * 获取购物车商品详情
	 * @param sc 
	 * @return
	 */
	public List<TdShoppingcartItem> findBySearchCriteria(TdShoppingcartSearchCriteria sc);
	/**
	 * 清空购物车
	 * @param uid
	 * @return
	 */
	public int deleteByUid(Integer uid);
}
