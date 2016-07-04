package com.tiandu.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.product.entity.TdProductAttachment;
import com.tiandu.product.entity.mapper.TdProductAttachmentMapper;
import com.tiandu.product.service.TdProductAttachmentService;

@Service("tdProductAttachmentService")
public class TdProductAttachmentServiceImpl implements TdProductAttachmentService{

	@Autowired
	private TdProductAttachmentMapper tdProductAttachmentMapper;
	
	@Override
	public List<TdProductAttachment> findByProductId(Integer proId) {
		return tdProductAttachmentMapper.findByProductId(proId);
	}

	@Override
	public Integer save(TdProductAttachment e) {
		if(null != e)
		{
			if(null != e.getId()){
				tdProductAttachmentMapper.updateByPrimaryKey(e);
			}else{
				tdProductAttachmentMapper.insert(e);
			}
		}
		return null;
	}

	@Override
	public TdProductAttachment findOne(Integer id) {
		return tdProductAttachmentMapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer deleteByProductId(Integer proId) {
		return tdProductAttachmentMapper.deleteByProductId(proId);
	}

}
