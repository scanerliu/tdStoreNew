package com.tiandu.complaint.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.complaint.entity.TdComplaint;
import com.tiandu.complaint.entity.mapper.TdComplaintMapper;
import com.tiandu.complaint.search.TdComplaintCriteria;
import com.tiandu.complaint.service.TdComplaintService;

@Service("tdComplaintService")
public class TdComplatintServiceImpl implements TdComplaintService{

	@Autowired
	private TdComplaintMapper tdComplaintMapper;
	
	
	@Override
	public TdComplaint findOne(Integer id) {
		return tdComplaintMapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer save(TdComplaint e) {
		if(null != e)
		{
			if(null != e.getId()){
				return tdComplaintMapper.updateByPrimaryKey(e);
			}else{
				return tdComplaintMapper.insert(e);
			}
		}
		return null;
	}

	@Override
	public Integer deleteByPrimaryKey(Integer id) {
		return tdComplaintMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<TdComplaint> findBySearchCriteria(TdComplaintCriteria sc) {
		Integer count= tdComplaintMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		return tdComplaintMapper.findBySearchCriteria(sc);
	}

}
