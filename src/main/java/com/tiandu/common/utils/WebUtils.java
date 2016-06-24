package com.tiandu.common.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class WebUtils {

	/**
	 * 生成用户密码
	 * @param name 用户名
	 * @param password 用户密码明文
	 * @return
	 */
	public static String generatePassword(String name, String password){
		String temp = name+MD5.md5(password);
		temp += ConstantsUtils.PRIMARY_MEMBER_KEY;
		return MD5.md5(temp);
	}
	
	public static Boolean isAjaxRequest(HttpServletRequest request){
		String requestType = request.getHeader("X-Requested-With");
		if(StringUtils.isNotEmpty(requestType)){
			return true;
		}
		return false;
	}
	
	public static void responseJson(HttpServletResponse response, Map<String, String> map){
		Gson gson = new Gson();
		String json = gson.toJson(map);
		response.setCharacterEncoding("UTF-8");  
		response.setContentType("text/html; charset=utf-8");  
		PrintWriter out = null;  
		try {  
		    out = response.getWriter();  
		    out.append(json.toString());  
		} catch (IOException e) {  
		    e.printStackTrace();  
		} finally {  
		    if (out != null) {  
		        out.close();  
		    }  
		}  
		 
	}
}
