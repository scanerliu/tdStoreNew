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

import com.tiandu.comment.entity.TdProductComment;
import com.tiandu.comment.search.TdProductCommentCrateria;
import com.tiandu.comment.service.TdProductCommentService;
/**
 * 评论
 * 
 * @author Max
 *
 */
@Controller
@RequestMapping("/admin/productcomment")
public class TdCommentController {

	@Autowired
	private TdProductCommentService tdProductCommentService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest req,ModelMap map)
	{
		return "/admin/comment/list";
	}
	
	@RequestMapping("/search")
	public String search(TdProductCommentCrateria sc,HttpServletRequest req,ModelMap map)
	{
		map.addAttribute("commentList", tdProductCommentService.findBySearchCriteria(sc));
		map.addAttribute("sc", sc);
		
		return "/admin/comment/listbody";
	}

	@RequestMapping("/edit")
	public String edit(Integer id,HttpServletRequest req,ModelMap map) throws Exception
	{
		if(null != id)
		{
			map.addAttribute("comment",tdProductCommentService.findOne(id));
		}
		return "/admin/comment/editform";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> save(TdProductComment tdProductComment,HttpServletRequest req)
	{
		Map<String,Object> res = new HashMap<>();
		res.put("code", 0);
		
		if(null != tdProductComment)
		{
			tdProductCommentService.save(tdProductComment);
			res.put("code", 1);
		}
		return res;
	}
	
	@RequestMapping(value= "/delete",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> delete(Integer id,HttpServletRequest req,ModelMap map)
	{
		Map<String,Object> res = new HashMap<>();
		res.put("code", 0);
		
		if(null != id)
		{
			tdProductCommentService.deleteByPrimaryKey(id);
			res.put("ode", 1);
		}
		return res;
	}
	
	
	@ModelAttribute
    public void getModel(@RequestParam(value = "commentId", required = false) Integer commentId,
                        Model model) {
        if (null != commentId) {
            model.addAttribute("tdProductComment", tdProductCommentService.findOne(commentId));
        }
    }
	
}
