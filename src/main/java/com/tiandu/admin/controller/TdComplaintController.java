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
import com.tiandu.complaint.entity.TdComplaint;
import com.tiandu.complaint.search.TdComplaintCriteria;
import com.tiandu.complaint.service.TdComplaintService;
import com.tiandu.custom.entity.TdUser;

@Controller
@RequestMapping("/admin/complaint")
public class TdComplaintController extends BaseController{
	
	@Autowired
	private TdComplaintService tdComplaintService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest req,ModelMap map)
	{
		return "/admin/complaint/list";
	}
	
	@RequestMapping("/search")
	public String search(TdComplaintCriteria sc,HttpServletRequest req,ModelMap map)
	{
		map.addAttribute("complaintList", tdComplaintService.findBySearchCriteria(sc));
		map.addAttribute("sc", sc);
		
		return "/admin/complaint/listbody";
	}

	@RequestMapping("/edit")
	public String edit(Integer id,HttpServletRequest req,ModelMap map)
	{
		if(null != id)
		{
			map.addAttribute("complaint", tdComplaintService.findOne(id));
		}
		return "/admin/complaint/editform";
	}
	
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> save(TdComplaint tdComplaint,HttpServletRequest req)
	{
		Map<String,Object> res = new HashMap<>();
		res.put("code", 0);
		
		if(null != tdComplaint)
		{
			TdUser user = getCurrentUser();
			tdComplaint.setUpdateBy(user.getUid());
			tdComplaint.setCreateTime(new Date());
			
			tdComplaintService.save(tdComplaint);
			res.put("code", 1);
		}
		return res;
	}
	
	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> delete(Integer id,HttpServletRequest req)
	{
		Map<String,Object> res = new HashMap<>();
		res.put("code", 0);
		
		if(null != id)
		{
			tdComplaintService.deleteByPrimaryKey(id);
			res.put("code", 1);
		}
		return res;
	}
	
	
	
	
	@ModelAttribute
    public void getModel(@RequestParam(value = "complaintId", required = false) Integer complaintId,
                        Model model) {
        if (null != complaintId) {
            model.addAttribute("tdComplaint", tdComplaintService.findOne(complaintId));
        }
    }
	
}
