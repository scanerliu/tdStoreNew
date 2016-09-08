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

import com.tiandu.article.service.TdArticleCategoryService;
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
@RequestMapping("/product")
public class CProductController extends BaseController {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private TdProductService tdProductService;
	
	@Autowired
	TdProductAttributeOptionService tdProductAttributeOptionService; 
	
	@Autowired
	private TdProductTypeService tdProductTypeService;
	
	@Autowired
	private TdProductAttachmentService tdProductAttachmentService;
	
	@Autowired
	private TdProductDescriptionService tdProductDescriptionService; 
	
	@Autowired
	private TdProductAttributeService tdProductAttributeService; 
	
	@Autowired
	private TdProductTypeAttributeService tdProductTypeAttributeService; 
	
	@Autowired
	private TdProductStatService tdProductStatService;
	
	@Autowired
	private TdProductSkuService tdProductSkuService;
	
	@Autowired
	private TdProductCommentService tdProductCommentService;
	
	@Autowired
	private TdDistrictService tdDistrictService; 
	
	@Autowired
	private TdUserCollectionService tdUserCollectionService;
	
	@Autowired
	private TdBrandService tdBrandService;
	
	@Autowired
	private ConfigUtil configUtil;
	
	@Autowired
	private TdArticleTitleService tdArticleTitleService;
	
	@Autowired
	private TdArticleCategoryService tdArticleCategoryService;
	
	/*
	 * 商品列表页
	 */
	@RequestMapping("/list")
	public String list(TdProductCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.addAttribute("system", this.getSystem());
		
		TdBrandSearchCriteria bsc = new TdBrandSearchCriteria(); 
		bsc.setFlag(false);
		bsc.setStatus(Byte.valueOf("1"));
		List<TdBrand> brandList = tdBrandService.findBySearchCriteria(bsc);
		tdBrandService.upperFirstLetterList(brandList);
		modelMap.addAttribute("brandList", brandList);
		modelMap.addAttribute("sc", sc);
		
		modelMap.addAttribute("articleList", tdArticleCategoryService.getFooterArticleCategory());
	    return "/client/product/list";
	}
	/*
	 * 商品列表数据页
	 */
	@RequestMapping("/search")
	public String search(TdProductCriteria sc,HttpServletRequest req,ModelMap map)
	{
		sc.setKind(ConstantsUtils.PRODUCT_KIND_COMMON);
		sc.setStatus(Byte.valueOf("1"));
		sc.setOnshelf(true);
		map.addAttribute("productList", tdProductService.findBySearchCriteria(sc));
		map.addAttribute("sc", sc);
		return "/client/product/listbody";
	}
	
	/*
	 * 猜你喜欢列表数据页
	 */
	@RequestMapping("/enjoysearch")
	public String enjoysearch(TdProductCriteria sc,HttpServletRequest req,ModelMap map)
	{
		sc.setKind(ConstantsUtils.PRODUCT_KIND_COMMON);
		sc.setStatus(Byte.valueOf("1"));
		sc.setOnshelf(true);
		if(null==sc.getTypeId()){
			sc.setHotRecommend(1);
		}
		map.addAttribute("productList", tdProductService.findBySearchCriteria(sc));
		map.addAttribute("sc", sc);
		return "/client/product/enjoylistbody";
	}
	
	/*
	 * 推荐列表数据页
	 */
	@RequestMapping("/recommendsearch")
	public String recommendsearch(TdProductCriteria sc,HttpServletRequest req,ModelMap map)
	{
		sc.setKind(ConstantsUtils.PRODUCT_KIND_COMMON);
		sc.setStatus(Byte.valueOf("1"));
		sc.setOnshelf(true);
		map.addAttribute("recommendList", tdProductService.findBySearchCriteria(sc));
		map.addAttribute("sc", sc);
		return "/client/product/recommendlistbody";
	}
	
	/*
	 * 商品类型数据页
	 */
	@RequestMapping("/searchproducttype")
	public String searchproducttype(TdProductTypeCriteria sc,HttpServletRequest req,ModelMap map)
	{
		sc.setFlag(false);
		sc.setStatus(Byte.valueOf("1"));
		map.addAttribute("producttypelist", tdProductTypeService.findAll(sc));
		map.addAttribute("sc", sc);
		return "/client/product/producttypelist";
	}
	/*
	 * 商品品牌数据页
	 */
	@RequestMapping("/searchproductbrand")
	public String searchproductbrand(TdBrandSearchCriteria sc, HttpServletRequest req,ModelMap map)
	{
		sc.setFlag(false);
		sc.setStatus(Byte.valueOf("1"));
		List<TdBrand> brandList = tdBrandService.findBySearchCriteria(sc);
		tdBrandService.upperFirstLetterList(brandList);
		map.addAttribute("sc", sc);
		map.addAttribute("brandList", brandList);
		return "/client/product/productbrandlist";
	}
	
	/*
	 * 商品详情数据页
	 */
	@RequestMapping("/item{id}")
	public String item(@PathVariable("id") Integer id,HttpServletRequest req,ModelMap map)
	{
		TdUser user = getCurrentUser();
		map.addAttribute("collect", false);
		if(null != user)
		{
			TdUserCollectionCriteria csc = new TdUserCollectionCriteria();
			csc.setItemId(id);
			csc.setUid(user.getUid());
			// 是否收藏
			List<TdUserCollection> collect = tdUserCollectionService.findBySearchCriteria(csc);
			if(null != collect && collect.size() > 0)
			{
				map.addAttribute("collect", true);
			}
		}
		
		TdProduct product  = tdProductService.findOne(id);
		if(product.getOnshelf()==false||!product.getStatus().equals(Byte.valueOf("1"))){
			return "redirect:404";
		}
		//货品
		List<TdProductSku> skuList = tdProductSkuService.findByProductId(id);
		if(skuList.size()>0){
			//商品类型规格
			/*List<TdProductTypeAttribute> taList = tdProductTypeAttributeService.findByTypeIdWithOptions(product.getTypeId());
			if(taList.size()>0){
				//匹配货品库存状态
				tdProductService.matchSkuStockWithAttributeOption(skuList,taList);
			}*/
			List<AttributeOptionsVO> taList = tdProductService.getProductAttributeWithOptions(product);
			map.addAttribute("taList", taList);
			if(!product.getSpecification()){
				product.setDefaultSkuId(skuList.get(0).getId());
				product.setQuantum(skuList.get(0).getStock());
			}
		}
		//货品规格库存json
		String productjson = tdProductService.fromProductSkutoProductJsonString(skuList);
		//商品图片
		List<TdProductAttachment> attachmentList = tdProductAttachmentService.findByProductId(id);
		//获取服务地区
		TdUser currUser = this.getCurrentUser();
		TdDistrict region = null;
		if(null!=currUser && null!=currUser.getUregionId()){
			region = tdDistrictService.findOne(currUser.getUregionId());
		}
		if(null==region){
			//默认重庆江北区
			region = tdDistrictService.findOne(349);
		}
		map.addAttribute("region", region);
		//获取商品类型
		TdProductType productType = tdProductTypeService.findOne(product.getTypeId());
		map.addAttribute("productType", productType);
		
		//全积分兑换商品
		Integer integralexchangerate = configUtil.getIntegralExchangerate(); //积分抵扣金额比例
		map.addAttribute("integralexchangerate", integralexchangerate);
		if(ConstantsUtils.PRODUCT_KIND_POINT_EXCHANGE.equals(product.getKind())){
			Integer points = product.getPrice().multiply(new BigDecimal(integralexchangerate)).setScale(0, BigDecimal.ROUND_FLOOR).intValue();
			product.setExchangepoints(points);
		}
		//相关推荐商品
		/*TdProductCriteria sc = new TdProductCriteria();
		sc.setKind(ConstantsUtils.PRODUCT_KIND_COMMON);
		sc.setPageSize(3);
		sc.setStatus(Byte.valueOf("1"));
		sc.setOnshelf(true);
		sc.setOrderBy("type_recommend desc, sort desc");
		map.addAttribute("recommendList", tdProductService.findBySearchCriteria(sc));*/		
		
		//查询商品统计
		TdProductStat productStat = tdProductStatService.findOne(product.getId());
		map.addAttribute("productStat", productStat);
		
		map.addAttribute("product", product);
		map.addAttribute("productjson", productjson);
		map.addAttribute("attachmentList", attachmentList);
		map.addAttribute("system", getSystem());
		return "/client/product/productdetail";
	}
	/*
	 * 商品图文详情页
	 */
	@RequestMapping("/describe/{id}")
	public String describe(@PathVariable("id") Integer id,HttpServletRequest req,ModelMap map)
	{
		TdProductDescriptionCriteria sc = new TdProductDescriptionCriteria();
		sc.setProductId(id);
		List<TdProductDescription>  descList  = tdProductDescriptionService.findBySearchCriteria(sc);
		//图文说明
		TdProductDescription productdesc = new TdProductDescription();
		//配送说明
		TdProductDescription delivedesc = new TdProductDescription();
		//售后说明
		TdProductDescription servicedesc = new TdProductDescription();
		for(TdProductDescription desc : descList){
			if(Byte.valueOf("1").equals(desc.getType())){
				productdesc = desc;
			}else if(Byte.valueOf("2").equals(desc.getType())){
				delivedesc = desc;
			}else if(Byte.valueOf("3").equals(desc.getType())){
				servicedesc = desc;
			}
		}
		
		//查询商品统计
		TdProductStat productStat = tdProductStatService.findOne(id);
		map.addAttribute("productStat", productStat);
		
		map.addAttribute("productdesc", productdesc);
		map.addAttribute("delivedesc", delivedesc);
		map.addAttribute("servicedesc", servicedesc);
		return "/client/product/productdescribe";
	}
	
	/*
	 * 商品图文详情页
	 */
	@RequestMapping("/searchcomments")
	public String searchcomments(TdProductCommentCrateria sc,HttpServletRequest req,ModelMap map)
	{
		sc.setStatus(Byte.valueOf("1"));
		List<TdProductComment>  commentList  = tdProductCommentService.findBySearchCriteria(sc);
		map.addAttribute("commentList", commentList);
		map.addAttribute("sc", sc);
		return "/client/product/productcommentlistBody";
	}
	
	
	/**
	 * 
	 * @author Max
	 * 商品收藏
	 * 
	 */
	@RequestMapping(value="/collect", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> collect(Integer productId,HttpServletRequest req,ModelMap map)
	{
		Map<String,Object> res = new HashMap<>();
		res.put("code", 0);
		
		TdUser user = getCurrentUser();
		if(null == user)
		{
			res.put("msg", "请先登录");
			return res;
		}
		
		res.put("code", 1);
		
		TdUserCollectionCriteria sc = new TdUserCollectionCriteria();
		sc.setItemId(productId);
		sc.setUid(user.getUid());
		List<TdUserCollection> list = tdUserCollectionService.findBySearchCriteria(sc);
		
		// 已收藏
		if(null != list && list.size() > 0){
			tdUserCollectionService.deleteByPrimaryKey(list.get(0).getId());
		}else{
			TdUserCollection collection = new TdUserCollection();
			collection.setItemId(productId);
			collection.setUid(user.getUid());
			collection.setItemType((byte)1);
			collection.setCreateTime(new Date());
			
			tdUserCollectionService.save(collection);
		}
		
		return res;
	}
	
	
	//积分兑换列表
	@RequestMapping("/point/list")
	public String point(HttpServletRequest request, HttpServletResponse response,ModelMap map)
	{
		// 系统配置
		map.addAttribute("system", getSystem());
		return "/client/product/point_list";
	}
	
	//积分兑换列表内容
	@RequestMapping("/point/search")
	public String pointsearch(TdProductCriteria sc,HttpServletRequest request, HttpServletResponse response, ModelMap map)
	{
		sc.setOnshelf(true);
		sc.setStatus(Byte.valueOf("1"));
		if(null==sc.getPointType()){
			sc.setPointType(0);
		}
		map.addAttribute("pointList", tdProductService.findBySearchCriteria(sc));
		map.addAttribute("sc", sc);
		return "/client/product/point_listbody";
	}
	
	/*
	 * 零元购列表页
	 */
	@RequestMapping("/zerolist")
	public String zerolist(TdProductCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.addAttribute("system", this.getSystem());
		modelMap.addAttribute("sc", sc);
		modelMap.addAttribute("articleList", tdArticleCategoryService.getFooterArticleCategory());
	    return "/client/product/zerolist";
	}
	/*
	 * 零元购列表数据页
	 */
	@RequestMapping("/zerosearch")
	public String zerosearch(TdProductCriteria sc,HttpServletRequest req,ModelMap map)
	{
		sc.setKind(ConstantsUtils.PRODUCT_KIND_ZEROBUY);
		sc.setStatus(Byte.valueOf("1"));
		sc.setOnshelf(true);
		map.addAttribute("productList", tdProductService.findBySearchCriteria(sc));
		map.addAttribute("sc", sc);
		return "/client/product/zerolistbody";
	}
	/*
	 * 新品列表页
	 */
	@RequestMapping("/newlist")
	public String newlist(TdProductCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.addAttribute("system", this.getSystem());
		modelMap.addAttribute("sc", sc);
		modelMap.addAttribute("articleList", tdArticleCategoryService.getFooterArticleCategory());
		return "/client/product/newlist";
	}
	/*
	 * 新品列表数据页
	 */
	@RequestMapping("/newsearch")
	public String newsearch(TdProductCriteria sc,HttpServletRequest req,ModelMap map)
	{
		sc.setKind(ConstantsUtils.PRODUCT_KIND_COMMON);
		sc.setNewRecommend(1);
		sc.setStatus(Byte.valueOf("1"));
		sc.setOnshelf(true);
		map.addAttribute("productList", tdProductService.findBySearchCriteria(sc));
		map.addAttribute("sc", sc);
		return "/client/product/newlistbody";
	}
	
	/*
	 * 新品列表页
	 */
	@RequestMapping("/killlist")
	public String killlist(TdProductCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.addAttribute("system", this.getSystem());
		modelMap.addAttribute("sc", sc);
		modelMap.addAttribute("articleList", tdArticleCategoryService.getFooterArticleCategory());
		return "/client/product/killlist";
	}
	/*
	 * 新品列表数据页
	 */
	@RequestMapping("/killsearch")
	public String killsearch(TdProductCriteria sc,HttpServletRequest req,ModelMap map)
	{
		Date now = new Date();
		if(null==sc.getKind()){
			sc.setKind(ConstantsUtils.PRODUCT_KIND_SECKILL);
		}
		sc.setStatus(Byte.valueOf("1"));
		sc.setOnshelf(true);
		sc.setStartTime(now);
		sc.setEndTime(now);
		map.addAttribute("productList", tdProductService.findBySearchCriteria(sc));
		map.addAttribute("sc", sc);
		return "/client/product/killlistbody";
	}

}
