package com.tiandu.product.entity.mapper;

import java.util.List;
import java.util.Map;

import com.tiandu.product.entity.TdProduct;
import com.tiandu.product.search.TdProductCriteria;

public interface TdProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdProduct record);

    int insertSelective(TdProduct record);

    TdProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdProduct record);

    int updateByPrimaryKey(TdProduct record);
    
    Integer countByCriteria(TdProductCriteria sc);
    List<TdProduct> findBySearchCriteria(TdProductCriteria sc);
    /**
     * 批量更新
     * @param map
     * @return
     */
    Integer updateBatch(Map map);
    /**
     * 批量删除
     * @param map
     * @return
     */
    Integer deleteBatch(Map map);

	int updateStock(TdProduct product);
}