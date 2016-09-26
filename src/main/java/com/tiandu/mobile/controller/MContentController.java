package com.tiandu.mobile.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiandu.article.entity.TdArticleCategory;
import com.tiandu.article.entity.TdArticleContent;
import com.tiandu.article.entity.TdArticleTitle;
import com.tiandu.article.search.TdArticleCategorySearchCriteria;
import com.tiandu.article.search.TdArticleTitleSearchCriteria;
import com.tiandu.article.service.TdArticleCategoryService;
import com.tiandu.article.service.TdArticleContentService;
import com.tiandu.article.service.TdArticleTitleService;
import com.tiandu.common.controller.BaseController;

/**
 * 
 * @author liuxinbing
 *
 */
@Controller
@RequestMapping("/mobile/article")
public class MContentController extends BaseController {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private TdArticleTitleService tdArticleTitleService;
	
	@Autowired
	private TdArticleContentService tdArticleContentService;
	
	@Autowired
	private TdArticleCategoryService tdArticleCategoryService;
	
	/**
	 * 内容页
	 * @param id
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/list")
	public String list(TdArticleCategorySearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
	{
		TdArticleCategory cat = tdArticleCategoryService.findOne(sc.getCid());
		modelMap.addAttribute("cat", cat);
		// 系统配置
		modelMap.addAttribute("system", getSystem());
		modelMap.addAttribute("sc", sc);
		return "/mobile/article/list";
	}
	
	/**
	 * 内容页
	 * @param id
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/search")
	public String search(TdArticleTitleSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
	{
		TdArticleCategory cat = tdArticleCategoryService.findOne(sc.getCid());
		modelMap.addAttribute("cat", cat);
		sc.setStatus((byte)1);
		List<TdArticleTitle> artList = tdArticleTitleService.findBySearchCriteria(sc);
		modelMap.addAttribute("artList", artList);
		modelMap.addAttribute("sc", sc);
		return "/mobile/article/listBody";
	}
	/**
	 * 内容页
	 * @param id
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/item{id}")
	public String item(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
	{
		if(null!=id && id>0){
			TdArticleTitle articleTitle = tdArticleTitleService.findOne(id);
			TdArticleContent articleContent = tdArticleContentService.findOne(id);
			modelMap.addAttribute("articleTitle", articleTitle);
			modelMap.addAttribute("articleContent", articleContent);
		}
		// 系统配置
		modelMap.addAttribute("system", getSystem());
		return "/mobile/article/item";
	}
	

}
