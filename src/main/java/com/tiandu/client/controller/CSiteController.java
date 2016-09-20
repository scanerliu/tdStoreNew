package com.tiandu.client.controller;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.PathVariable;
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
import com.tiandu.comment.entity.TdProductComment;
import com.tiandu.comment.search.TdProductCommentCrateria;
import com.tiandu.comment.service.TdProductCommentService;
import com.tiandu.common.controller.BaseController;
import com.tiandu.common.utils.ConstantsUtils;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.entity.TdUserCollection;
import com.tiandu.custom.search.TdUserCollectionCriteria;
import com.tiandu.custom.service.TdUserCollectionService;
import com.tiandu.district.entity.TdDistrict;
import com.tiandu.district.service.TdDistrictService;
import com.tiandu.product.entity.TdBrand;
import com.tiandu.product.entity.TdProduct;
import com.tiandu.product.entity.TdProductAttachment;
import com.tiandu.product.entity.TdProductDescription;
import com.tiandu.product.entity.TdProductSku;
import com.tiandu.product.entity.TdProductStat;
import com.tiandu.product.entity.TdProductType;
import com.tiandu.product.search.TdBrandSearchCriteria;
import com.tiandu.product.search.TdProductCriteria;
import com.tiandu.product.search.TdProductDescriptionCriteria;
import com.tiandu.product.search.TdProductTypeCriteria;
import com.tiandu.product.service.TdBrandService;
import com.tiandu.product.service.TdProductAttachmentService;
import com.tiandu.product.service.TdProductAttributeOptionService;
import com.tiandu.product.service.TdProductAttributeService;
import com.tiandu.product.service.TdProductDescriptionService;
import com.tiandu.product.service.TdProductService;
import com.tiandu.product.service.TdProductSkuService;
import com.tiandu.product.service.TdProductStatService;
import com.tiandu.product.service.TdProductTypeAttributeService;
import com.tiandu.product.service.TdProductTypeService;
import com.tiandu.product.vo.AttributeOptionsVO;
import com.tiandu.system.utils.ConfigUtil;

/**
 * 
 * @author liuxinbing
 *
 */
@Controller
@RequestMapping("/site")
public class CSiteController extends BaseController {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private ConfigUtil configUtil;
	
	@Autowired
	private TdArticleTitleService tdArticleTitleService;
	
	@Autowired
	private TdArticleContentService tdArticleContentService;
	
	@Autowired
	private TdArticleCategoryService tdArticleCategoryService;
	
	/*
	 * 主页
	 */
	@RequestMapping("/list")
	public String list(TdArticleCategorySearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.addAttribute("system", this.getSystem());
		sc.setParentId(12);//网站说明id
		sc.setStatus(Byte.valueOf("1"));//正常状态
		sc.setFlag(false);
		List<TdArticleCategory> catList = tdArticleCategoryService.findBySearchCriteria(sc);
		modelMap.addAttribute("catList", catList);
		modelMap.addAttribute("sc", sc);
		TdArticleTitleSearchCriteria asc =new TdArticleTitleSearchCriteria();
		asc.setCid(12);//网站说明id
		asc.setFlag(false);
		asc.setStatus((byte)1);
		List<TdArticleTitle> artList = tdArticleTitleService.findBySearchCriteria(asc);
		modelMap.addAttribute("artList", artList);
	    return "/client/site/list";
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
		return "/client/site/listbody";
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
		return "/client/site/item";
	}
	
	@RequestMapping("/banner")
	public String banner(TdArticleTitleSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		sc.setCid(12);//网站说明id
		sc.setStatus((byte)1);
		List<TdArticleTitle> artList = tdArticleTitleService.findBySearchCriteria(sc);
		modelMap.addAttribute("artList", artList);
	    return "/client/site/banner";
	}

}
