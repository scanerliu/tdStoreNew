package com.tiandu.custom.entity.mapper;

import java.util.List;

import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.search.TdUserSearchCriteria;

public interface TdUserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(TdUser record);

    int insertSelective(TdUser record);

    TdUser selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(TdUser record);

    int updateByPrimaryKey(TdUser record);
    int updateManagerByPrimaryKey(TdUser record);
    int updateCustomerByPrimaryKey(TdUser record);
    
    TdUser selectByUname(String uname);
    
    public List<TdUser> findBySearchCriteria(TdUserSearchCriteria sc);
    public Integer 		countByCriteria(TdUserSearchCriteria sc);
    public TdUser selectByPrimaryKeyWithRoles(Integer uid);
    
    public int saveUserPassword(TdUser record);
    public int saveUserStatus(TdUser record);
    
    public TdUser selectDetailByPrimaryKey(Integer uid);

	public List<TdUser> selectByUtel(String utel);
}