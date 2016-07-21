package com.tiandu.mobile.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
 * 
 * @author Max
 * 会员商品收藏
 * 
 * 创建时间：2016年7月21日 下午5:21:05
 */
@Controller
@RequestMapping("/mobile/user")
public class MobileUserCollectController extends BaseController{

	@Autowired
	private TdUserCollectionService tdUserCollectionService;
	
	@RequestMapping("/collect/list")
	public String List(HttpServletRequest req,ModelMap map)
	{
		// 系统配置
		map.addAttribute("system", getSystem());
		return "/mobile/user/collect/collect";	
	}
	
	@RequestMapping("/collect/search")
	public String search(TdUserCollectionCriteria sc,HttpServletRequest req,ModelMap map)
	{
		TdUser user = getCurrentUser();
		if(null==user)
		{
			return "redirect:/mobile/login";
		}
		sc.setUid(user.getUid());
		
		map.addAttribute("collectList", tdUserCollectionService.findBySearchCriteria(sc));
		map.addAttribute("sc", sc);
		
		return "/mobile/user/collect/collect_list";
	}
	
	@RequestMapping("/collect/search/more")
	public String searchMore(TdUserCollectionCriteria sc,Integer page,HttpServletRequest req,ModelMap map)
	{
		TdUser user = getCurrentUser();
		if(null==user)
		{
			return "redirect:/mobile/login";
		}
		sc.setUid(user.getUid());
		sc.setPageNo(page);
		
		int count = tdUserCollectionService.getTotalPageCount(sc);
		if(count >= page)
		{
			map.addAttribute("collectList", tdUserCollectionService.findBySearchCriteria(sc));
			map.addAttribute("sc", sc);
		}
		
		return "/mobile/user/collect/collect_list_more";
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
