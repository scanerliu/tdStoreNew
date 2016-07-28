package com.tiandu.order.service;

import org.springframework.stereotype.Service;

import com.tiandu.order.entity.TdOrderSku;

/**
 * 
 * @author Max
 * 
 * 创建时间：2016年7月27日 下午5:06:29
 */
public interface TdOrderSkuService {

	TdOrderSku findOne(Integer id);
}
