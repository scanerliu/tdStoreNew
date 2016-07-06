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
 * @author L. Gao
 *
 */
@Controller
@RequestMapping("/admin/article")
public class ArticleTitleController extends BaseController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private TdArticleTitleService tdArticleTitleService;
	
	@Autowired
	private TdArticleCategoryService tdArticleCategoryService;
	
	@Autowired
	private TdArticleContentService tdArticleContentService;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/admin/article/list";
	}
	
	@RequestMapping("/search")
	public String search(TdArticleTitleSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		List<TdArticleTitle> articleTitleList = tdArticleTitleService.findBySearchCriteria(sc);
		for(TdArticleTitle tat : articleTitleList){
			tat.setTdArticleCategory(tdArticleCategoryService.findOne(tat.getCid()));
			tat.setUser(tdUserService.findOne(tat.getUid()));
		}
		
	    modelMap.addAttribute("articleTitleList", articleTitleList);
	    modelMap.addAttribute("sc", sc) ;
		return "/admin/article/listbody";
	}
	
	@RequestMapping("/edit")
	public String edit(Integer aid, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdArticleTitle tdArticleTitle = null;
		if(null!=aid && aid != 0){
			tdArticleTitle = tdArticleTitleService.findOne(aid);
			if(tdArticleContentService.findOne(aid) != null){
				tdArticleTitle.setArticleContent(tdArticleContentService.findOne(aid).getContent());
			}
			if(tdArticleTitle.getCid() != null){
				tdArticleTitle.setTdArticleCategory(tdArticleCategoryService.findOne(tdArticleTitle.getCid()));
			}
			if(tdArticleTitle.getUpdateBy() != null){
				tdArticleTitle.setUpdatePerson(tdUserService.findOne(tdArticleTitle.getUpdateBy()));
			}
		}else{
			tdArticleTitle = new TdArticleTitle();			
		}
		// 资讯目录列表
		TdArticleCategorySearchCriteria sc2 = new TdArticleCategorySearchCriteria();
		sc2.setFlag(false);
		List<TdArticleCategory> articleCategoryList = tdArticleCategoryService.findBySearchCriteria(sc2);
		
		modelMap.addAttribute("tdArticleTitle", tdArticleTitle);
		modelMap.addAttribute("articleCategoryList", articleCategoryList);
		modelMap.addAttribute("type", "article");
		return "/admin/article/articleForm";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> save(TdArticleTitle articleTitle, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=articleTitle){
			try {
				articleTitle.setUpdateTime(new Date());
				// 设置修改后的参数
				if(articleTitle.getAid() != null){
					TdArticleTitle tat = tdArticleTitleService.findOne(articleTitle.getAid());
					if(tat != null){
						tat.setCid(articleTitle.getCid());
						tat.setTitle(articleTitle.getTitle());
						tat.setKeyword(articleTitle.getKeyword());
						tat.setSummary(articleTitle.getSummary());
						tat.setImageUrl(articleTitle.getImageUrl());
						tat.setStatus(articleTitle.getStatus());
						tat.setSort(articleTitle.getSort());
						tat.setHotRecommend(articleTitle.getHotRecommend());
						tat.setUpdateTime(articleTitle.getUpdateTime());
						tat.setUpdateBy(this.getCurrentUser().getUid());
						tdArticleTitleService.save(tat);
						// 保存对应的内容表
						TdArticleContent tac = tdArticleContentService.findOne(tat.getAid());
						tac.setContent(articleTitle.getArticleContent());
						tdArticleContentService.save(tac, true);
						res.put("msg", "资讯修改成功。");
					}
				}else{
					articleTitle.setUid(this.getCurrentUser().getUid());
					articleTitle.setUpdateBy(this.getCurrentUser().getUid());
					// 待修改
					articleTitle.setRegionId(1);
					tdArticleTitleService.save(articleTitle);
					TdArticleContent articleContent = new TdArticleContent();
					// 保存对应的内容，待debug数据
					articleContent.setAid(articleTitle.getAid());
					articleContent.setContent(articleTitle.getArticleContent());
					tdArticleContentService.save(articleContent, false);
					res.put("msg", "资讯添加成功。");
				}
				res.put("code", "1");
				return res;
			}catch (Exception e) {
				logger.error("资讯保存失败错误信息:"+e);
				res.put("code", "0");
				res.put("msg", "资讯保存失败。");
				e.printStackTrace();
				return res;
			}
		}else{
			res.put("code", "0");
			res.put("msg", "资讯保存失败。");
			return res;
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String aidStr, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> res = new HashMap<String, String>();
		if (null != aidStr && !aidStr.equals("")) {
			String[] aidStrArray = aidStr.split(",");
			try {
				for(String aid : aidStrArray){
					tdArticleTitleService.delete(Integer.parseInt(aid));	
					tdArticleContentService.delete(Integer.parseInt(aid));
				}
				res.put("code", "1");
				res.put("msg", "资讯删除成功。");
				return res;
			} catch (Exception e) {
				logger.error("资讯删除失败，错误信息:" + e);
				res.put("code", "0");
				res.put("msg", "资讯删除失败。");
				e.printStackTrace();
				return res;
			}
		} else {
			logger.error("资讯删除失败，错误信息:cidStr为空。");
			res.put("code", "0");
			res.put("msg", "资讯删除失败。");
			return res;
		}
	}
}
