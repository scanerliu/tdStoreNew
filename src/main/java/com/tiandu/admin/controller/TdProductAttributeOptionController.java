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

import com.tiandu.common.controller.BaseController;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.product.entity.TdProductAttributeOption;
import com.tiandu.product.search.TdProductAttributeOptionCriteria;
import com.tiandu.product.service.TdProductAttributeOptionService;
import com.tiandu.product.service.TdProductAttributeService;

@Controller
@RequestMapping("/admin/attribute/option")
public class TdProductAttributeOptionController extends BaseController{

	@Autowired
	private TdProductAttributeOptionService tdProductAttributeOptionService;
	
	@Autowired
	private TdProductAttributeService tdProductAttributeService;
	
//	@RequestMapping("/list")
//	public String list(Integer attriId,HttpServletRequest req,ModelMap map)
//	{
//		map.addAttribute("attriId",attriId);
//		return "/admin/product/option/list";
//	}
	
	
	@RequestMapping("/search")
	public String search(TdProductAttributeOptionCriteria sc,Integer attriId,HttpServletRequest req,ModelMap map)
	{
		sc.setAttriId(attriId);
		sc.setFlag(false);
		map.addAttribute("optionList", tdProductAttributeOptionService.findBySearchCriteria(sc));
		map.addAttribute("sc", sc);
		map.addAttribute("attriId", attriId);
		
		return "/admin/product/option/listbody";
	}
	
	@RequestMapping("/edit")
	public String edit(Integer attriId,Integer id,HttpServletRequest req,ModelMap map)
	{
		if(null != attriId)
		{
			map.addAttribute("attribute", tdProductAttributeService.findOne(attriId));
		}
		if(null != id)
		{
			map.addAttribute("option", tdProductAttributeOptionService.findOne(id));
		}
		map.addAttribute("attriId", attriId);
		
		return "/admin/product/option/optionform";
	}
	
	
	@RequestMapping(value="/save",method= RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> save(TdProductAttributeOption tdProductAttributeOption,
					HttpServletRequest req,ModelMap map){
		
		Map<String,Object> res = new HashMap<>();
		res.put("code", 0);
		
		if(null != tdProductAttributeOption)
		{
			TdUser user = getCurrentUser();
			if(null != user)
			{
				tdProductAttributeOption.setUid(user.getUid());
				tdProductAttributeOptionService.save(tdProductAttributeOption);
				
				res.put("code", 1);
				res.put("attriId", tdProductAttributeOption.getAttriId());
				
			}
		}
		return res;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(Integer id,HttpServletRequest req)
	{
		Map<String,Object> res = new HashMap<>();
		res.put("code", 0);
		if(null != id)
		{
			TdProductAttributeOption option = tdProductAttributeOptionService.findOne(id);
			if(null != option)
			{
				res.put("attriId", option.getAttriId());
				tdProductAttributeOptionService.deleteByPrimaryKey(id);
				res.put("code", 1);
			}
		}
		
		return res;
	}
	
	
	@ModelAttribute
    public void getModel(@RequestParam(value = "optionId", required = false) Integer optionId,
                        Model model) {
        if (null != optionId) {
            model.addAttribute("tdProductAttributeOption", tdProductAttributeOptionService.findOne(optionId));
        }
    }
	
}
