package com.tiandu.custom.entity.mapper;

import java.util.List;

import com.tiandu.custom.entity.TdRole;
import com.tiandu.custom.search.TdRoleSearchCriteria;

public interface TdRoleMapper {
	public int deleteByPrimaryKey(Integer id);

	public int insert(TdRole record);

	public int insertSelective(TdRole record);

	public TdRole selectByPrimaryKey(Integer id);
	public TdRole selectByPrimaryKeyPermiision(Integer id);

	public int updateByPrimaryKeySelective(TdRole record);

	public int updateByPrimaryKey(TdRole record);
    
    public List<TdRole> findBySearchCriteria(TdRoleSearchCriteria sc);
    public Integer 		countByCriteria(TdRoleSearchCriteria sc);
}