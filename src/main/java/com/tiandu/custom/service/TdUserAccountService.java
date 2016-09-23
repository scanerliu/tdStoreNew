package com.tiandu.custom.service;

import java.math.BigDecimal;
import java.util.List;

import com.tiandu.custom.entity.TdUserAccount;
import com.tiandu.custom.entity.TdUserAccountLog;
import com.tiandu.custom.search.TdUserAccountCriteria;

public interface TdUserAccountService {

	public int insert(TdUserAccount u);
	public int updateByPrimaryKeySelective(TdUserAccount record);
	public TdUserAccount findOne(Integer id);
	/**
	 * 积分更改增加或删除
	 * @param record
	 * @param log
	 * @return
	 */
	public boolean addAmount(TdUserAccount record, TdUserAccountLog log);
	
	public TdUserAccount findByUid(Integer uId);
	
	public BigDecimal countTotalAmount();
	
	public List<TdUserAccount> findBySearchCriteria(TdUserAccountCriteria sc);
	
}
