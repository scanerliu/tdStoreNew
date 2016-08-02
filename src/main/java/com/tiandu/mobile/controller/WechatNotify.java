package com.tiandu.mobile.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tiandu.common.tencent.common.Signature;
import com.tiandu.common.utils.DecriptUtils;
import com.tiandu.common.utils.WebUtils;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.search.TdUserSearchCriteria;
import com.tiandu.custom.service.TdUserService;

@Controller
@RequestMapping(value = "/wechat")
public class WechatNotify {
	
	private final static String TOKEN = "2rattfOUOHUWQkqRGxY8aBzk3jfi4MDJ";
	private static Integer INCREASENO = 0;
	private static final Integer MAX_INCREASE_NO = 9;
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private TdUserService tdUserSerivce;
	
	@RequestMapping(value = "/notify",method = RequestMethod.GET)
	public void wxConform(HttpServletResponse response,String signature,String timestamp,String nonce,String echostr) throws IOException
	{
		ModelMap signMap = new ModelMap();
		signMap.put("token", TOKEN);
		signMap.put("timestamp", timestamp);
		signMap.put("nonce", nonce);
		String sign = Signature.getSign(signMap);
		logger.error("signature:" + signature +"|timestamp:" + timestamp+ "|nonce:" + nonce+"|echostr:" + echostr+"|sign:" + sign);
		
		ArrayList<String> signStr = new ArrayList<String>();
		signStr.add(TOKEN);
		signStr.add(timestamp);
		signStr.add(nonce);
//		signStr.add(TOKEN);
//		signStr.add("1470030617");
//		signStr.add("2069949203");
		Collections.sort(signStr);
		String resultSign = DecriptUtils.SHA1(signStr.get(0) + signStr.get(1) + signStr.get(2));
		logger.error("signature:" + signature +"|timestamp:" + timestamp+ "|nonce:" + nonce+"|echostr:" + echostr+"|resultSign:" + resultSign);
		if(StringUtils.isNotBlank(signature) && signature.equalsIgnoreCase(resultSign))
		{
			response.getWriter().print(echostr);
		}
		response.getWriter().print(echostr);
	}
	
	@RequestMapping(value = "/notify", method = RequestMethod.POST)
	public void wxNotify(HttpServletRequest request) throws Exception
	{
		logger.error("error-------------------->进入通知Controller!");
		Map<String,Object> map = this.parseXml(request);
		logger.error("error-------------------->"+map);
		String event		= map.get("Event").toString();
		String openid		= map.get("FromUserName").toString();
		String eventKey		= map.get("EventKey").toString();
		String messageType	= map.get("MsgType").toString();
		logger.error("error-------------------->event:" + event +"|openid:" + openid+ "|eventKey:" + eventKey+ "|messageType:" + messageType);
		if("subscribe".equals(event)&& "event".equalsIgnoreCase(messageType))
		{
			String[] evenKeys = eventKey.split("_");
			logger.error("error-------------------->evenKeys："+ evenKeys);
			if(evenKeys != null && evenKeys.length > 1)
			{
				logger.error("error-------------------->进入新增用户!");
				this.initUserByOpenIdAndSpreadId(openid, Integer.parseInt(evenKeys[1]));
				logger.error("error-------------------->进入新增用户end!");
			}
		}
	}
	
	public synchronized String getUniqueNoWithHeader(String headStr)
	{
		if (headStr == null)
		{
			headStr = "";
		}
		INCREASENO++;
		if(INCREASENO > MAX_INCREASE_NO)
			INCREASENO = 0;
		return Long.toString(System.currentTimeMillis())+ Integer.toString(INCREASENO);
	}
	
	public TdUser initUserByOpenIdAndSpreadId(String opendI,Integer SpreadId)
	{
		TdUserSearchCriteria sc = new TdUserSearchCriteria();
		sc.setJointId(opendI);
		List<TdUser> joinUser = tdUserSerivce.findBySearchCriteria(sc);
		if(joinUser == null || joinUser.size() < 1)
		{
			TdUser user = new TdUser();
			Date currentDate = new Date();
			user.setUname(getUniqueNoWithHeader("W"));
			String password = WebUtils.generatePassword(user.getUname(), "123456");
			user.setUpassword(password);
			user.setJointId(opendI);
			user.setUverification(Byte.valueOf("1"));
			user.setUstatus(Byte.valueOf("1"));
			user.setUtype(Byte.valueOf("2"));//普通会员
			user.setUparentId(SpreadId);
			user.setCreateTime(currentDate);
			user.setUpdateBy(0);
			user.setUpdateTime(currentDate);
			user.setSupplierType(Byte.valueOf("0"));
			tdUserSerivce.insert(user);
			return user;
		}
		return null;
	}
	
	public Map<String,Object> parseXml(HttpServletRequest request) throws Exception {  
	    // 将解析结果存储在HashMap中  
	    Map<String,Object> map = new HashMap<String,Object>();  
	   
	    // 从request中取得输入流  
	    InputStream inputStream = request.getInputStream();  
	    // 读取输入流  
	    SAXReader reader = new SAXReader();  
	    Document document = reader.read(inputStream);  
	    // 得到xml根元素  
	    Element root = document.getRootElement();  
	    // 得到根元素的所有子节点  
	    @SuppressWarnings("unchecked")
		List<Element> elementList = root.elements();  
	   
	    // 遍历所有子节点  
	    for (Element e : elementList)  
	        map.put(e.getName(), e.getText());  
	   
	    // 释放资源  
	    inputStream.close();  
	    inputStream = null;  
	    return map;  
	}

}
