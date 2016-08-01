package com.tiandu.mobile.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Maps;
import com.tiandu.common.tencent.common.Signature;

@Controller
@RequestMapping(value = "/wechat")
public class WechatNotify {
	
	private final static String TOKEN = "2rattfOUOHUWQkqRGxY8aBzk3jfi4MDJ";
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@RequestMapping(value = "/notify",method = RequestMethod.GET)
	public void wxConform(HttpServletResponse response,String signature,String timestamp,String nonce,String echostr) throws IOException
	{
		ModelMap signMap = new ModelMap();
		signMap.put("token", TOKEN);
		signMap.put("timestamp", timestamp);
		signMap.put("nonce", nonce);
		String sign = Signature.getSign(signMap);
		logger.error("signature:" + signature +"|timestamp:" + timestamp+ "|nonce:" + nonce+"|echostr:" + echostr+"|sign:" + sign);
		if(StringUtils.isNotBlank(signature) && signature.equalsIgnoreCase(sign))
		{
			response.getWriter().print(echostr);
		}
		response.getWriter().print(echostr);
	}
	
	@RequestMapping(value = "/notify", method = RequestMethod.POST)
	public void wxNotify(Model model)
	{
		HashMap<String,Object> map = Maps.newHashMap(model.asMap());
		String event	= map.get("event").toString();
		String openid	= map.get("fromusername").toString();
		String eventKey	= map.get("eventkey").toString();
		logger.error("event:" + event +"|openid:" + openid+ "|eventKey:" + eventKey);
		if("subscribe".equals(event))
		{
			
		}
	}
}
