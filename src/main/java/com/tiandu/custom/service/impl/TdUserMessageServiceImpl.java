package com.tiandu.custom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.common.db.DBContextHolder;
import com.tiandu.custom.entity.TdUserMessage;
import com.tiandu.custom.entity.mapper.TdUserMessageMapper;
import com.tiandu.custom.search.TdUserMessageSearchCriteria;
import com.tiandu.custom.service.TdUserMessageService;
import com.tiandu.custom.service.TdUserService;

/**
 * 
 * @author L. Gao
 *
 */
@Service("tdUserMessageService")
public class TdUserMessageServiceImpl implements TdUserMessageService {
	
	@Autowired
	TdUserMessageMapper userMessageMapper;
	
	@Autowired
	TdUserService tdUserService;
	
	@Override
	public int insert(TdUserMessage u) {
		DBContextHolder.setDbType(DBContextHolder.DB_RW);
		return userMessageMapper.insert(u);
	}

	@Override
	public TdUserMessage findOne(Integer id) {
		return userMessageMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TdUserMessage> findBySearchCriteria(TdUserMessageSearchCriteria sc) {
		if(sc.getMsgType() != null && sc.getMsgType().equals(Byte.valueOf("-1"))){
			sc.setMsgType(null);
		}
		if(sc.getStatus() != null && sc.getStatus().equals(Byte.valueOf("-1"))){
			sc.setStatus(null);
		}
		if(sc.getTitle() != null && sc.getTitle().equals("")){
			sc.setTitle(null);
		}
		if(sc.getUsername() != null && sc.getUsername().equals("")){
			sc.setUsername(null);
		}
		sc.setAssociationTdUser(true);
		Integer count = userMessageMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		List<TdUserMessage> userMessageList = userMessageMapper.findBySearchCriteria(sc);
		return userMessageList;
	}
	
	
	@Override
	public Integer save(TdUserMessage TdUserMessage) {
		if(null!=TdUserMessage){
			if(null!=TdUserMessage.getId()){//更新
				return userMessageMapper.updateByPrimaryKeyWithBLOBs(TdUserMessage);
			}else{
				return userMessageMapper.insert(TdUserMessage);
			}
		}
		return null;
	}

	@Override
	public Integer delete(Integer id) {
		return userMessageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer countByCriteria(TdUserMessageSearchCriteria sc) {
		return userMessageMapper.countByCriteria(sc);
	}

}
