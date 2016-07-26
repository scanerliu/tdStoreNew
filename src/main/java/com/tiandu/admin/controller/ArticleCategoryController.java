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

import com.tiandu.article.entity.TdArticleCategory;
import com.tiandu.article.search.TdArticleCategorySearchCriteria;
import com.tiandu.article.service.TdArticleCategoryService;
import com.tiandu.common.controller.BaseController;
import com.tiandu.district.entity.TdDistrict;

/**
 * 
 * @author L. Gao
 *
 */
@Controller
@RequestMapping("/admin/articlecategory")
public class ArticleCategoryController extends BaseController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private TdArticleCategoryService tdArticleCategoryService;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		// 获取新闻类别列表
		return "/admin/articleCategory/list";
	}
	
	@RequestMapping("/search")
	public String search(TdArticleCategorySearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		List<TdArticleCategory> articleCategoryList = tdArticleCategoryService.findBySearchCriteria(sc);
	    modelMap.addAttribute("articleCategoryList", articleCategoryList);
	    modelMap.addAttribute("sc", sc) ;
		return "/admin/articleCategory/listbody";
	}
	
	@RequestMapping("/edit")
	public String edit(Integer cid, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdArticleCategory articleCategory = null;
		if(null!=cid){
			articleCategory = tdArticleCategoryService.findOne(cid);		    
		}
		if(null==articleCategory){
			articleCategory = new TdArticleCategory();
		}
		// 上级目录
		List<TdArticleCategory> parentArticleCategoryList = null;
		if(cid != null && cid != 0){
			parentArticleCategoryList = tdArticleCategoryService.getOtherArticleCategoryAll(cid);			
		}else{
			parentArticleCategoryList = tdArticleCategoryService.getArticleCategoryAll();
		}
		modelMap.addAttribute("parentArticleCategoryList", parentArticleCategoryList);
		modelMap.addAttribute("articleCategory", articleCategory);
		return "/admin/articleCategory/articleCategoryform";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> save(TdArticleCategory articleCategory, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=articleCategory){
			try {
				articleCategory.setUpdateTime(new Date());
				// 设置修改后的参数
				if(articleCategory.getCid() != null){
					TdArticleCategory tac = tdArticleCategoryService.findOne(articleCategory.getCid());
					if(tac != null){
						tac.setName(articleCategory.getName());
						tac.setParentId(articleCategory.getParentId());
						tac.setSort(articleCategory.getSort());
						tac.setStatus(articleCategory.getStatus());
						tac.setUpdateTime(new Date());
						tac.setUpdateBy(this.getCurrentUser().getUid());
						tdArticleCategoryService.save(tac);
						res.put("msg", "资讯类别修改成功。");
					}
				}else{
					articleCategory.setUpdateBy(this.getCurrentUser().getUid());
					articleCategory.setArticles(0);
					articleCategory.setUpdateTime(new Date());
					tdArticleCategoryService.save(articleCategory);
					res.put("msg", "资讯类别添加成功。");
				}
				res.put("code", "1");
				return res;
			}catch (Exception e) {
				logger.error("资讯类别保存失败错误信息:"+e);
				res.put("code", "0");
				res.put("msg", "资讯类别保存失败。");
				return res;
			}
		}else{
			res.put("code", "0");
			res.put("msg", "资讯类别保存失败。");
			return res;
		}
	}
	
	// 判断是否有下级目录，判断该目录下是否有资讯存在
	@RequestMapping(value = "/hasChrildrenOrExistArticle", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> hasChrildren(String cidStr, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> res = new HashMap<String, String>();
		String[] cidArray = cidStr.split(",");
		for(String s : cidArray){
			if(s != null && !s.equals("")){
				if(tdArticleCategoryService.getChildrenCount(Integer.parseInt(s)) > 0){
					res.put("code", "0");
					res.put("msg", "资讯类别下级目录不为空。");
					return res;
				}
				TdArticleCategory articleCategory = tdArticleCategoryService.findOne(Integer.parseInt(s));
				if(articleCategory != null && articleCategory.getArticles() > 0){
					res.put("code", "0");
					res.put("msg", "该资讯类别下有咨询。");
					return res;
				}
			}
		}
		res.put("cidStr", cidStr);
		res.put("code", "1");
		return res;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String cidStr, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> res = new HashMap<String, String>();
		if (null != cidStr && !cidStr.equals("")) {
			String[] cidStrArray = cidStr.split(",");
			try {
				for(String cid : cidStrArray){
					tdArticleCategoryService.delete(Integer.parseInt(cid));				
				}
				res.put("code", "1");
				res.put("msg", "资讯目录删除成功。");
				return res;
			} catch (Exception e) {
				logger.error("资讯目录删除失败，错误信息:" + e);
				res.put("code", "0");
				res.put("msg", "资讯目录删除失败。");
				return res;
			}
		} else {
			logger.error("资讯目录删除失败，错误信息:cidStr为空。");
			res.put("code", "0");
			res.put("msg", "资讯目录删除失败。");
			return res;
		}
	}
	
	
}
