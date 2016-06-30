package com.tiandu.admin.controller;

import java.util.Date;
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
import com.tiandu.product.entity.TdProductType;
import com.tiandu.product.entity.TdProductTypeAttribute;
import com.tiandu.product.entity.TypeAttribute;
import com.tiandu.product.search.TdProductTypeCriteria;
import com.tiandu.product.service.TdProductTypeAttributeService;
import com.tiandu.product.service.TdProductTypeService;
/**
 * 
 * @author Max
 *
 */
@Controller
@RequestMapping("/admin/producttype")
public class ProductTypeController extends BaseController{

	@Autowired
	private TdProductTypeService tdProductTypeService;
	
//	@Autowired
//	private TdProductAttributeService tdProductAttributeService;
	@Autowired
	
	private TdProductTypeAttributeService tdProductTypeAttributeService;
	
	@RequestMapping(value="/list")
	public String list(HttpServletRequest req,ModelMap map){
		
		return "/admin/product/type/productList";
	}
	
	@RequestMapping(value="/search")
	public String productTypeSearch(TdProductTypeCriteria sc,HttpServletRequest req,ModelMap map)
	{
		map.addAttribute("productTypeList", tdProductTypeService.findAll());
		map.addAttribute("sc", sc);
		return "/admin/product/type/ptoductTypebody";
	}
	
	@RequestMapping(value="/edit")
	public String productTypeEdit(Integer id,HttpServletRequest req,ModelMap map)
	{
		map.addAttribute("productTypeList", tdProductTypeService.findAll());
		if(null != id)
		{
			map.addAttribute("productType", tdProductTypeService.findOne(id));
		}
		return "/admin/product/type/producttypeform";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> productSave(TdProductType tdProductType,HttpServletRequest req)
	{
		Map<String,Object> res = new HashMap<String, Object>();
		res.put("code", 0);
		
		if(null != tdProductType)
		{
			if(null == tdProductType.getId())
			{
				// 新增，设置添加时间
				tdProductType.setCreateTime(new Date());
			}
			// 设置修改人及修改时间
			TdUser user = getCurrentUser();
			if(null != user)
			{
				tdProductType.setUpdateBy(user.getUid());
				tdProductType.setUpdateTime(new Date());
				
				tdProductTypeService.save(tdProductType);
				res.put("code", 1);
				return res;
			}
		}
		return res;
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public Map<String,Object> producttypeDelete(Integer id,HttpServletRequest req)
	{
		Map<String,Object> res = new HashMap<>();
		res.put("code", 0);
		if(null != id)
		{
			tdProductTypeService.deleteByPrimaryKey(id);
			res.put("code", 1);
		}
		
		return res;
	}
	
	@RequestMapping("/attribute/edit")
	public String typeAttrEdit(Integer typeId, HttpServletRequest req,ModelMap map)
	{
		
		map.addAttribute("attrList", tdProductTypeService.findProducrAttribute(typeId));
		map.addAttribute("otherList", tdProductTypeService.findNotProducrAttribute(typeId));
		map.addAttribute("typeId", typeId);
		
		return "/admin/product/type/attributeform";
	}
	
	@RequestMapping(value="/attribute/save",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> typeAttrSave(TypeAttribute typeAttribute ,HttpServletRequest req)
	{
		Map<String,Object> res = new HashMap<>();
		res.put("code", 0);
		
		if(null != typeAttribute)
		{
			// 删除原有关联，重新储存
			tdProductTypeAttributeService.deleteByTypeId(typeAttribute.getTypeId());
			
			String attrIds = typeAttribute.getAttrIds();
			// 截取选择的属性ID
			String[] ids = attrIds.split(",");
			for (String str : ids) {
				TdProductTypeAttribute attribute = new TdProductTypeAttribute();
				
				attribute.setAttriId(Integer.parseInt(str));
				attribute.setTypeId(typeAttribute.getTypeId());
				
				tdProductTypeAttributeService.save(attribute);
			}
			res.put("code", 1);
			res.put("typeId", typeAttribute.getTypeId());
		}
		
		
		return res;
	}
	
	
	@ModelAttribute
    public void getModel(@RequestParam(value = "productId", required = false) Integer productId,
                        Model model) {
        if (null != productId) {
            model.addAttribute("tdProductType", tdProductTypeService.findOne(productId));
        }
    }
}
