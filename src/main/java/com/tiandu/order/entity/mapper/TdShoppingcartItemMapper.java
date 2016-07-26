package com.tiandu.order.entity.mapper;

import java.util.List;

import com.tiandu.order.entity.TdShoppingcartItem;
import com.tiandu.order.search.TdShoppingcartSearchCriteria;

public interface TdShoppingcartItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdShoppingcartItem record);

    int insertSelective(TdShoppingcartItem record);

    TdShoppingcartItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdShoppingcartItem record);

    int updateByPrimaryKey(TdShoppingcartItem record);
    /**
     * 更新商品数量（加减1）
     * @param record
     * @return
     */
    int updateNumByPrimaryKey(TdShoppingcartItem record);
    
    public TdShoppingcartItem selectByUidAndSkuId(TdShoppingcartItem record);
    
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