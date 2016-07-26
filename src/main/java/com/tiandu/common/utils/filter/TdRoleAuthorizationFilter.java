package com.tiandu.common.utils.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

public class TdRoleAuthorizationFilter extends AuthorizationFilter {

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

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)	throws Exception {
		Subject subject = getSubject(request, response);
        String[] rolesArray = (String[]) mappedValue;

        if (rolesArray == null || rolesArray.length == 0) {
            return true;
        }

        Set<String> roles = CollectionUtils.asSet(rolesArray);
        return subject.hasAllRoles(roles);
	}

}
