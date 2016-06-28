package com.tiandu.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiandu.product.entity.TdProductAttribute;
import com.tiandu.product.search.TdProductAttributeCriteria;
import com.tiandu.product.service.TdProductAttributeService;
/**
 * 
 * @author Max
 *
 */
@Controller
@RequestMapping("/admin/productattribute")
public class TdProductAttributeController {

	
	@Autowired
	private TdProductAttributeService tdProductAttributeService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest req,ModelMap map)
	{
		return "/admin/product/attribute/list";
	}
	
	@RequestMapping("/search")
	public String search(TdProductAttributeCriteria sc,HttpServletRequest req,ModelMap map)
	{
		map.addAttribute("attributorList", tdProductAttributeService.findBySearchCriteria(sc));
		map.addAttribute("sc", sc);
		return "/admin/product/attribute/listbody";
	}
	
	@RequestMapping("/edit")
	public String edit(Integer id,HttpServletRequest req,ModelMap map)
	{
		if(null != id)
		{
			map.addAttribute("attribute", tdProductAttributeService.findOne(id));
		}
		return "/admin/product/attribute/editform";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> save(TdProductAttribute tdProductAttribute,HttpServletRequest req,ModelMap map)
	{
		Map<String,Object> res = new HashMap<>();
		res.put("code", 0);
		
		if(null != tdProductAttribute)
		{
			tdProductAttributeService.save(tdProductAttribute);
			res.put("code", 1);
		}
		return res;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(Integer id,HttpServletRequest req,ModelMap map)
	{
		Map<String,Object> res = new HashMap<>();
		res.put("code", 0);
		
		if(null != id)
		{
			tdProductAttributeService.deleteByPrimaryKey(id);
			res.put("code", 1);
		}
		return res;
	}
	
	@ModelAttribute
    public void getModel(@RequestParam(value = "attriId", required = false) Integer attriId,
                        Model model) {
        if (null != attriId) {
            model.addAttribute("tdProductAttribute", tdProductAttributeService.findOne(attriId));
        }
    }
	
}
