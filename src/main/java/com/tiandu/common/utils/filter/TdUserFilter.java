package com.tiandu.common.utils.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.UserFilter;

import com.tiandu.common.utils.ConstantsUtils;

public class TdUserFilter extends UserFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;  
        HttpServletResponse httpResponse = (HttpServletResponse) response;
		if (com.tiandu.common.utils.WebUtils.isAjaxRequest(httpRequest)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("code", "-1");
			map.put("msg", "您尚未登录或登录时间过长,请重新登录!");
			com.tiandu.common.utils.WebUtils.responseJson(httpResponse, map);  
	    } else { 
	    	String uri = httpRequest.getRequestURI();
        	String contextPath = httpRequest.getContextPath();
        	//后台登录切换登录地址
        	if(uri.indexOf(contextPath+ConstantsUtils.TDSTORE_ADMIN_FILTER)==0){
        		setLoginUrl(ConstantsUtils.TDSTORE_ADMIN_LOGIN_URL);
        	//移动端登录切换登录地址
        	}else if(uri.indexOf(contextPath+ConstantsUtils.TDSTORE_MOBILE_FILTER)==0){
        		setLoginUrl(ConstantsUtils.TDSTORE_MOBILE_LOGIN_URL);
        	}
	    	saveRequestAndRedirectToLogin(request, response);
	    }
        return false;
	}

}
