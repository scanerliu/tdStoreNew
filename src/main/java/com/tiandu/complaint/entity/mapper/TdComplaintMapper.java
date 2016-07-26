package com.tiandu.complaint.entity.mapper;

import java.util.List;

import com.tiandu.complaint.entity.TdComplaint;
import com.tiandu.complaint.search.TdComplaintCriteria;

public interface TdComplaintMapper {
    int deleteByPrimaryKey(Integer id);

	int insert(TdComplaint record);

	int insertSelective(TdComplaint record);

	TdComplaint selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(TdComplaint record);

	int updateByPrimaryKey(TdComplaint record);

    Integer countByCriteria(TdComplaintCriteria sc);
    List<TdComplaint> findBySearchCriteria(TdComplaintCriteria sc);
    
}