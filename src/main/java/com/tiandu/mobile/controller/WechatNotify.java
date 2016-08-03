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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tiandu.common.tencent.entity.TextMessage;
import com.tiandu.common.utils.DecriptUtils;
import com.tiandu.common.utils.WebUtils;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.search.TdUserSearchCriteria;
import com.tiandu.custom.service.TdUserService;

@Controller
@RequestMapping(value = "/wechat")
public class WechatNotify {
	
	private final static String KToUserName			= "ToUserName";
	private final static String KFromUserName		= "FromUserName";
	private final static String KMsgType			= "MsgType";
	private final static String KEvent				= "Event";
	private final static String KEventKey			= "EventKey";
	
	private final static String TOKEN				= "2rattfOUOHUWQkqRGxY8aBzk3jfi4MDJ";
	
	private final static String EVENTYPE_SUBSCRIBE	= "subscribe";
//	private final static String MSGTYPE_TEXT		= "text";
	private final static String MSGTYPE_EVENT		= "event";
	
	private final static String CONTENTMSG			= "欢迎关注一路上创客联盟!\n";
	
	private static Integer INCREASENO				= 0;
	private static final Integer MAX_INCREASE_NO	= 9;
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private TdUserService tdUserSerivce;
	
	/**
	 * 验证
	 * @param response
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @throws IOException
	 */
	@RequestMapping(value = "/notify",method = RequestMethod.GET)
	public void wxConform(HttpServletResponse response,String signature,String timestamp,String nonce,String echostr) throws IOException
	{
		ArrayList<String> signStr = new ArrayList<String>();
		signStr.add(TOKEN);
		signStr.add(timestamp);
		signStr.add(nonce);
		Collections.sort(signStr);
		String resultSign = DecriptUtils.SHA1(signStr.get(0) + signStr.get(1) + signStr.get(2));
		logger.error("signature:" + signature +"|timestamp:" + timestamp+ "|nonce:" + nonce+"|echostr:" + echostr+"|resultSign:" + resultSign);
		if(StringUtils.isNotBlank(signature) && signature.equalsIgnoreCase(resultSign))
		{
			response.getWriter().print(echostr);
		}
		response.getWriter().print(echostr);
	}
	
	/**
	 * 事件推送
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/notify", method = RequestMethod.POST)
	public void wxNotify(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		logger.error("WX:STEP-------------------->进入通知Controller!");
		Map<String,Object> map = this.parseXml(request);
		logger.error("WX:MAP-------------------->"+map);
		String event		= map.get(KEvent).toString();
		String msgType		= map.get(KMsgType).toString();
		
		//事件类型
		if (MSGTYPE_EVENT.equals(msgType))
		{
			if (EVENTYPE_SUBSCRIBE.equals(event))//关注微信号
			{
				SubScribe(map,response);
			}
		}
		else//其他事件或者消息
		{
			TextMessage msg	= new TextMessage();
			String openId		= map.get(KFromUserName).toString();
			String WxId			= map.get(KToUserName).toString();
			msg.setToUserName(openId);
			msg.setFromUserName(WxId);
			response.getWriter().println(msg.toString());
		}
	}
	
	
	public void SubScribe(Map<String,Object> map,HttpServletResponse response) throws IOException
	{
		response.setContentType("text/xml;charset=UTF-8");
		TextMessage msg	= new TextMessage();
		String openId		= map.get(KFromUserName).toString();
		String eventKey		= map.get(KEventKey).toString();
		String WxId			= map.get(KToUserName).toString();
		if(StringUtils.isBlank(openId))
		{
			return;
		}
		if(StringUtils.isBlank(eventKey))
		{
			return;
		}
		else if(eventKey.contains("qrscene_"))
		{
			msg.setToUserName(openId);
			msg.setFromUserName(WxId);
			String spreadStr = eventKey.replace("qrscene_", "");
			Integer spreadId = this.parseInt(spreadStr);
			TdUser spreadUser = tdUserSerivce.findOne(spreadId);
			if(!isVaildUser(spreadUser))
			{
				response.getWriter().println(msg.toString());
				return;
			}
			TdUserSearchCriteria sc = new TdUserSearchCriteria();
			sc.setJointId(openId);
			List<TdUser> joinUser = tdUserSerivce.findBySearchCriteria(sc);
			if(joinUser == null || joinUser.size() < 1)
			{
				Integer passwordInt = (int)(Math.random()*(999999 - 100000 + 1) + 100000);
				TdUser newUser = initUserByOpenIdAndSpreadId(openId,spreadId,passwordInt.toString());
				if(newUser != null)
				{
					msg.setContent(CONTENTMSG + "你已扫描"+ (StringUtils.isBlank(spreadUser.getUnick()) ? "" : (spreadUser.getUnick() + "的")) +"二维码关注成功!\n你的账号：" +newUser.getUname()+"\n密码："+ passwordInt);
				}
			}
		}
		response.getWriter().println(msg.toString());
		
	}
	
	public void replyMsg(HttpServletResponse response)
	{
		
	}
	
	
	/**
	 * 验证用户有效性
	 * @param user
	 * @return
	 */
	public Boolean isVaildUser(TdUser user)
	{
		if(user == null)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Stirng 转 Integer
	 * @param intStr
	 * @return
	 */
	public Integer parseInt(String intStr)
	{
		if(StringUtils.isBlank(intStr))
		{
			return 0;
		}
		Integer resultInt = null;
		try {
			resultInt = Integer.parseInt(intStr);
		}
		catch (NumberFormatException e)
		{
			logger.error("WX:ERROR------>" + e.getMessage());
		}
		finally
		{
			if(resultInt == null)
			{
				resultInt = 0;
			}
		}
		return resultInt;
	}
	
	
	/**
	 * 获取唯一字符串
	 * @return
	 */
	public synchronized String getUniqueNo()
	{
		INCREASENO++;
		if(INCREASENO > MAX_INCREASE_NO)
			INCREASENO = 0;
		return Long.toString(System.currentTimeMillis())+ Integer.toString(INCREASENO);
	}
	
	/**
	 * 新增用户
	 * @param opendI
	 * @param SpreadId
	 * @return
	 */
	public TdUser initUserByOpenIdAndSpreadId(String opendI,Integer SpreadId,String password)
	{
		TdUser user = new TdUser();
		Date currentDate = new Date();
		user.setUname(getUniqueNo());
		password = WebUtils.generatePassword(user.getUname(), password);
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
	
	/**
	 * XML转MAP
	 * @param request
	 * @return
	 * @throws Exception
	 */
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
