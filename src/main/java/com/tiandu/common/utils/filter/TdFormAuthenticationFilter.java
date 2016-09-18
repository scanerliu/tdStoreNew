package com.tiandu.common.utils.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.tiandu.common.utils.ConstantsUtils;


public class TdFormAuthenticationFilter extends FormAuthenticationFilter {
	private static final Logger log = Logger.getLogger(TdFormAuthenticationFilter.class);
	
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }
                //allow them to see the login page ;)
                return true;
            }
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
                        "Authentication url [" + getLoginUrl() + "]");
            }

            saveRequestAndRedirectToLogin(request, response);
            return false;
        }
	}

	@Override
	protected void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
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
        	}else{
        		setLoginUrl(ConstantsUtils.TDSTORE_LOGIN_URL);
        	}
        	super.saveRequestAndRedirectToLogin(request, response);
        }
	}
	
	
    
}
