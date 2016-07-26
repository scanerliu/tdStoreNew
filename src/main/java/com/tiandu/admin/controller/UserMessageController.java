package com.tiandu.admin.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiandu.common.controller.BaseController;
import com.tiandu.custom.entity.TdUserMessage;
import com.tiandu.custom.search.TdUserMessageSearchCriteria;
import com.tiandu.custom.service.TdUserMessageService;
import com.tiandu.custom.service.TdUserService;

/**
 * 
 * @author L. Gao
 *
 */
@Controller
@RequestMapping("/admin/message")
public class UserMessageController extends BaseController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private TdUserMessageService tdUserMessageService;

	@Autowired
	private TdUserService tdUserService;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/admin/userMessage/userMessageList";
	}

	@RequestMapping("/search")
	public String search(TdUserMessageSearchCriteria sc, HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		List<TdUserMessage> userMessageList = tdUserMessageService.findBySearchCriteria(sc);
		modelMap.addAttribute("userMessageList", userMessageList);
		modelMap.addAttribute("sc", sc);
		return "/admin/userMessage/userMessageListbody";
	}
	
	@RequestMapping("/edit")
	public String edit(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUserMessage tdUserMessage = tdUserMessageService.findOne(id);
		modelMap.addAttribute("tdUserMessage", tdUserMessage);
		return "/admin/userMessage/userMessageForm";
	}
	
	@RequestMapping("/sendUserMessage")
	public String edit(String idStr, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.addAttribute("idStr", idStr);
		return "/admin/userMessage/sendUserMessageForm";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> save(TdUserMessage userMessage, String idStr, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=userMessage){
			// 去掉时分秒
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String nowStr = sdf.format(now);
			try {
				now = sdf.parse(nowStr);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			userMessage.setCreateTime(now);
			// 待修改，现在只添加系统消息，系统消息的relateId为0;
			userMessage.setRelateId(0);
			userMessage.setStatus(Byte.valueOf("1"));
			if (null != idStr && !idStr.equals("")) {
				String[] idStrArray = idStr.split(",");
				try {
					for(String id : idStrArray){
						userMessage.setUid(Integer.parseInt(id));
						userMessage.setId(null);
						tdUserMessageService.save(userMessage);	
					}
					res.put("code", "1");
					res.put("msg", "消息发布成功。");
					return res;
				} catch (Exception e) {
					logger.error("消息发布失败，错误信息:" + e);
					res.put("code", "0");
					res.put("msg", "消息发布失败。");
					e.printStackTrace();
					return res;
				}
			}else{
				res.put("code", "0");
				res.put("msg", "消息发布失败。");
				logger.error("消息发布事变，原因：待发布的会员为空！");
				return res;
			}
		}else{
			res.put("code", "0");
			res.put("msg", "消息发布失败。");
			logger.error("消息发布失败");
			return res;
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String idStr, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> res = new HashMap<String, String>();
		if (null != idStr && !idStr.equals("")) {
			String[] idStrArray = idStr.split(",");
			try {
				for(String id : idStrArray){
					tdUserMessageService.delete(Integer.parseInt(id));	
				}
				res.put("code", "1");
				res.put("msg", "消息删除成功。");
				return res;
			} catch (Exception e) {
				logger.error("消息删除失败，错误信息:" + e);
				res.put("code", "0");
				res.put("msg", "消息删除失败。");
				e.printStackTrace();
				return res;
			}
		} else {
			logger.error("消息删除失败，错误信息:idStr为空。");
			res.put("code", "0");
			res.put("msg", "消息删除失败。");
			return res;
		}
	}
	
}
