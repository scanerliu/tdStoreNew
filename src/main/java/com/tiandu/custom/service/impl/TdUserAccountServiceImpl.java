package com.tiandu.custom.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.custom.entity.TdUserAccount;
import com.tiandu.custom.entity.TdUserAccountLog;
import com.tiandu.custom.entity.mapper.TdUserAccountLogMapper;
import com.tiandu.custom.entity.mapper.TdUserAccountMapper;
import com.tiandu.custom.search.TdUserAccountCriteria;
import com.tiandu.custom.service.TdUserAccountService;

@Service("tdUserAccountService")
public class TdUserAccountServiceImpl implements TdUserAccountService {

	@Autowired
	TdUserAccountMapper tdUserAccountMapper;
	
	@Autowired
	TdUserAccountLogMapper tdUserAccountLogMapper;

	@Override
	public int insert(TdUserAccount u) {
		u.setUpdateTime(new Date());
		return tdUserAccountMapper.insert(u);
	}

	@Override
	public int updateByPrimaryKeySelective(TdUserAccount record) {
		record.setUpdateTime(new Date());
		return tdUserAccountMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public TdUserAccount findOne(Integer id) {
		return tdUserAccountMapper.selectByPrimaryKey(id);
	}

	@Override
	public synchronized boolean addAmount(TdUserAccount record, TdUserAccountLog log) {
		TdUserAccount account = tdUserAccountMapper.selectByPrimaryKey(record.getUid());
		BigDecimal totalamount = account.getAmount().add(log.getUpamount());
		log.setPreamount(account.getAmount());
		log.setUid(account.getUid());
		record.setAmount(totalamount);		
		tdUserAccountMapper.updateByPrimaryKey(record);
		tdUserAccountLogMapper.insert(log);
		return true;
	}

	@Override
	public TdUserAccount findByUid(Integer uId) {
		if(uId == null){
			return null;
		}
		return tdUserAccountMapper.selectByPrimaryKey(uId);
	}
	
	@Override
	public List<TdUserAccount> findBySearchCriteria(TdUserAccountCriteria sc) {
		if(sc.isFlag()){
			Integer count = tdUserAccountMapper.countByCriteria(sc);
			sc.setTotalCount(count);
		}
		return tdUserAccountMapper.findBySearchCriteria(sc);
	}

	@Override
	public BigDecimal countTotalAmount() {
		return tdUserAccountMapper.countTotalAmount();
	}
	
}
