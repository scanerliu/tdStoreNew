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
import com.tiandu.custom.entity.TdUser;
import com.tiandu.product.entity.TdBrand;
import com.tiandu.product.search.TdBrandSearchCriteria;
import com.tiandu.product.service.TdBrandService;

@Controller
@RequestMapping("/admin/brand")
public class BrandController extends BaseController {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private TdBrandService tdBrandService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
	    return "/admin/brand/list";
	}
	@RequestMapping("/search")
	public String search(TdBrandSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		//获取主菜单
		sc.setGetUpdateUser(true);
		List<TdBrand> brandList = tdBrandService.findBySearchCriteria(sc);
	    modelMap.addAttribute("brandList", brandList) ;
	    modelMap.addAttribute("sc", sc) ;
		return "/admin/brand/listbody";
	}
	
	@RequestMapping("/edit")
	public String edit(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdBrand brand = null;
		if(null!=id && id>0){
			brand = tdBrandService.findOne(id);		    
		}
		if(null==brand){
			brand = new TdBrand();
		}
		modelMap.addAttribute("brand", brand);
		return "/admin/brand/brandform";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> save(TdBrand brand, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=brand){
			Date now = new Date();
			try {
				TdUser currManager = this.getCurrentUser();
				brand.setUpdateBy(currManager.getUid());
				brand.setUpdateTime(now);
				tdBrandService.save(brand);
				res.put("code", "1");
				return res;
			}catch (Exception e) {
				logger.error("商品品牌保存失败错误信息:"+e);
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
				tdBrandService.delete(id);
				res.put("code", "1");
				return res;
			}catch (Exception e) {
				logger.error("商品品牌删除失败错误信息:"+e);
				res.put("code", "0");
				return res;
			}
		}else{
			res.put("code", "0");
			return res;
		}
	}
}
