package com.tiandu.common.utils.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

public class TdPermissionAuthenticationFilter extends AuthorizationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)	throws Exception {
		Subject subject = getSubject(request, response);
        String[] perms = (String[]) mappedValue;

        boolean isPermitted = true;
        if (perms != null && perms.length > 0) {
            if (perms.length == 1) {
                if (!subject.isPermitted(perms[0])) {
                    isPermitted = false;
                }
            } else {
                if (!subject.isPermittedAll(perms)) {
                    isPermitted = false;
                }
            }
        }
        return isPermitted;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;  
        HttpServletResponse httpResponse = (HttpServletResponse) response;
		Subject subject = getSubject(request, response);
        if (subject.getPrincipal() == null) {
        	if (com.tiandu.common.utils.WebUtils.isAjaxRequest(httpRequest)) {
        		Map<String, String> map = new HashMap<String, String>();
        		map.put("code", "-1");
        		map.put("msg", "您尚未登录或登录时间过长,请重新登录!");
        		com.tiandu.common.utils.WebUtils.responseJson(httpResponse, map);  
            } else { 
            	saveRequestAndRedirectToLogin(request, response);
            }
        } else {
        	if (com.tiandu.common.utils.WebUtils.isAjaxRequest(httpRequest)) {
        		Map<String, String> map = new HashMap<String, String>();
        		map.put("code", "-2");
        		map.put("msg", "您没有足够的权限执行该操作!");
        		com.tiandu.common.utils.WebUtils.responseJson(httpResponse, map);  
            } else {
            	String unauthorizedUrl = getUnauthorizedUrl();
                if (StringUtils.hasText(unauthorizedUrl)) {
                    WebUtils.issueRedirect(request, response, unauthorizedUrl);
                } else {
                    WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }
        }
        return false;
	}

}
