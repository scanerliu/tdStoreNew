package com.tiandu.admin.controller;

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

import com.tiandu.express.entity.TdExpress;
import com.tiandu.express.search.TdExpressSearchCriteria;
import com.tiandu.express.service.TdExpressService;

/**
 * 
 * @author L. Gao
 *
 */
@Controller
@RequestMapping("/admin/express")
public class ExpressController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private TdExpressService tdExpressService;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		// 获取快递公司列表
		return "/admin/express/list";
	}
	
	@RequestMapping("/search")
	public String search(TdExpressSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		List<TdExpress> expressList = tdExpressService.findBySearchCriteria(sc);
	    modelMap.addAttribute("expressList", expressList);
	    modelMap.addAttribute("sc", sc) ;
		return "/admin/express/listbody";
	}
	
	@RequestMapping("/edit")
	public String edit(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdExpress express = null;
		if(null!=id){
			express = tdExpressService.findOne(id);		    
		}
		if(null==express){
			express = new TdExpress();
		}
		modelMap.addAttribute("express", express);
		return "/admin/express/expressform";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> save(TdExpress express, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=express){
			try {
				tdExpressService.save(express);
				res.put("code", "1");
				return res;
			}catch (Exception e) {
				logger.error("快递公司保存失败错误信息:"+e);
				res.put("code", "0");
				return res;
			}
		}else{
			res.put("code", "0");
			return res;
		}
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> delete(Integer id, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=id){
			try {
				tdExpressService.delete(id);
				res.put("code", "1");
				res.put("msg", "该公司已被成功删除。");
				return res;
			}catch (Exception e) {
				logger.error("快递公司删除失败错误信息:"+e);
				res.put("code", "0");
				res.put("msg", "该公司删除失败。");
				return res;
			}
		}else{
			res.put("code", "0");
			res.put("msg", "该公司删除失败。");
			return res;
		}
	}
	
	@RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> batchDelete(String idStr, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> res = new HashMap<String, String>();
		String[] ids = idStr.split(",");
		try {
			for (String id : ids) {
				tdExpressService.delete(Integer.parseInt(id));
			}
			res.put("code", "1");
			res.put("msg", "批量删除快递公司成功。");
			return res;
		} catch (Exception e) {
			logger.error("批量删除快递公司失败错误信息:" + e);
			res.put("code", "0");
			res.put("msg", "批量删除快递公司失败。");
			return res;
		}
	}
}
