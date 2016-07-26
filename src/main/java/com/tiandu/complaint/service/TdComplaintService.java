package com.tiandu.complaint.service;

import java.util.List;

import com.tiandu.complaint.entity.TdComplaint;
import com.tiandu.complaint.search.TdComplaintCriteria;

public interface TdComplaintService {

	TdComplaint findOne(Integer id);
	
	Integer save(TdComplaint e);
	
	Integer deleteByPrimaryKey(Integer id);
	
	List<TdComplaint> findBySearchCriteria(TdComplaintCriteria sc);

	int insert(TdComplaint complaint);
	
}
