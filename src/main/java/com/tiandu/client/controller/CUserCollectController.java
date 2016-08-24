package com.tiandu.client.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiandu.common.controller.BaseController;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.search.TdUserCollectionCriteria;
import com.tiandu.custom.service.TdUserCollectionService;

/**
 * 会员商品收藏
 */
@Controller
@RequestMapping("/user")
public class CUserCollectController extends BaseController{

	@Autowired
	private TdUserCollectionService tdUserCollectionService;
	
	@RequestMapping("/collect/list")
	public String collectList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
	{
		// 系统配置
		modelMap.addAttribute("system", getSystem());
		modelMap.addAttribute("menucode", "shipment") ;//菜单code
		return "/client/user/collect/list";	
	}
	
	@RequestMapping("/collect/search")
	public String search(TdUserCollectionCriteria sc,HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
	{
		TdUser user = getCurrentUser();
		if(null==user)
		{
			return "redirect:/login";
		}
		sc.setUid(user.getUid());
		
		modelMap.addAttribute("collectList", tdUserCollectionService.findBySearchCriteria(sc));
		modelMap.addAttribute("sc", sc);
		
		return "/client/user/collect/listbody";
	}
	
	@RequestMapping(value="/collect/delete",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> delete(Integer id,HttpServletRequest req,ModelMap map)
	{
		Map<String,Object> res = new HashMap<>();
		res.put("code", 0);
		
		if(null != id)
		{
			tdUserCollectionService.deleteByPrimaryKey(id);
			res.put("code", 1);
		}
		
		return res;
	}
	
}
