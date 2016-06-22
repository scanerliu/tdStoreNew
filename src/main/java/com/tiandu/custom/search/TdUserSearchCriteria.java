package com.tiandu.custom.search;

import com.tiandu.common.search.SearchCriteria;

public class TdUserSearchCriteria extends SearchCriteria {

	private String name; //用户名

    private Byte ustatus; //用户状态，1-正常，2-屏蔽

    private Byte utype; //用户类型，1-普通会员，2-平台用户
    
    
}
