package com.tiandu.admin.controller;

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
import com.tiandu.common.utils.WebUtils;
import com.tiandu.custom.entity.TdRole;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.search.TdRoleSearchCriteria;
import com.tiandu.custom.search.TdUserSearchCriteria;
import com.tiandu.product.entity.TdAgentProduct;
import com.tiandu.product.search.TdAgentProductSearchCriteria;
import com.tiandu.product.service.TdAgentProductService;

@Controller
@RequestMapping("/admin/agentproduct")
public class AgentProductController extends BaseController {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private TdAgentProductService tdAgentProductService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
	    return "/admin/agentproduct/list";
	}
	@RequestMapping("/search")
	public String search(TdAgentProductSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		//获取主菜单
		sc.setGetUpdateUser(true);
		List<TdAgentProduct> productList = tdAgentProductService.findBySearchCriteria(sc);
	    modelMap.addAttribute("productList", productList) ;
	    modelMap.addAttribute("sc", sc) ;
		return "/admin/agentproduct/listbody";
	}
	
	@RequestMapping("/edit")
	public String edit(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdAgentProduct product = null;
		if(null!=id){
			product = tdAgentProductService.findOne(id);		    
		}
		if(null==product){
			product = new TdAgentProduct();
		}
		modelMap.addAttribute("product", product);
		return "/admin/agentproduct/productform";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> save(TdAgentProduct product, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=product){
			Date now = new Date();
			try {
				TdUser currManager = this.getCurrentUser();
				product.setUpdateBy(currManager.getUid());
				product.setUpdateTime(now);
				tdAgentProductService.save(product);
				res.put("code", "1");
				return res;
			}catch (Exception e) {
				logger.error("代理产品保存失败错误信息:"+e);
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
				tdAgentProductService.deleteByPrimaryKey(id);
				res.put("code", "1");
				return res;
			}catch (Exception e) {
				logger.error("代理产品删除失败错误信息:"+e);
				res.put("code", "0");
				return res;
			}
		}else{
			res.put("code", "0");
			return res;
		}
	}
}
