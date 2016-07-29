package com.tiandu.admin.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiandu.common.controller.BaseController;
import com.tiandu.custom.entity.TdUserAccountLog;
import com.tiandu.custom.search.TdUserAccountLogSearchCriteria;
import com.tiandu.custom.service.TdUserAccountLogService;
import com.tiandu.order.entity.TdOrderSku;
import com.tiandu.order.search.TdOrderSkuSearchCriteria;
import com.tiandu.order.service.TdOrderService;
import com.tiandu.order.service.TdOrderSkuService;
import com.tiandu.product.entity.TdProduct;
import com.tiandu.product.search.TdProductCriteria;
import com.tiandu.product.service.TdProductService;
import com.tiandu.report.SaleProductReportEntity;

/**
 * 统计
 * @author L. Gao
 *
 */
@Controller
@RequestMapping("/admin/report")
public class ReportController extends BaseController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private TdProductService tdProductService;
	
	@Autowired
	private TdOrderService tdOrderService;
	
	@Autowired
	private TdOrderSkuService tdOrderSkuService;
	
	@Autowired
	private TdUserAccountLogService tdUserAccountLogService;
	

	@RequestMapping("/unsaleList")
	public String unsaleList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/admin/report/unsaleList";
	}
	
	@RequestMapping("/saleList")
	public String saleList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/admin/report/saleList";
	}
	
	@RequestMapping("/user/incomeList")
	public String incomeList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/admin/report/userIncomeList";
	}
	
	/*
	 * 未销售
	 */
	@RequestMapping("/unsale/search")
	public String unsaleSearch(TdProductCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdOrderSkuSearchCriteria ssc = new TdOrderSkuSearchCriteria();
		ssc.setFlag(false);
		List<TdOrderSku> orderskuList = tdOrderSkuService.findBySearchCriteria(ssc);
		
		Set<Integer> orderskuIdSet = new HashSet<>();
		if(orderskuList != null){
			for(TdOrderSku os : orderskuList){
				orderskuIdSet.add(os.getProductId());
			}
			sc.setOrderskuIdSet(orderskuIdSet);
		}
		
		List<TdProduct> saleProductList = tdProductService.findBySearchCriteria(sc);
		modelMap.addAttribute("saleProductList", saleProductList);
		modelMap.addAttribute("sc", sc);
		return "/admin/report/unsaleListBody";
	}
	
	/*
	 * 销售
	 */
	@RequestMapping("/sale/search")
	public String saleSearch(TdOrderSkuSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		Date endTime = sc.getEndTime();
		if(endTime != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar endCalendar = Calendar.getInstance();
			endCalendar.setTime(endTime);
			endCalendar.add(Calendar.DATE, 1);
			endTime = endCalendar.getTime();
			sc.setEndTime(endTime);
		}
		sc.setIsGroup(true);
		sc.setOrderStatus(Byte.valueOf("2"));
		
		List<SaleProductReportEntity> saleProductReportList = tdOrderSkuService.findGroupBySearchCriteria(sc);
		
		modelMap.addAttribute("saleProductReportList", saleProductReportList);
		modelMap.addAttribute("sc", sc);
		return "/admin/report/saleListBody";
	}
	
	@RequestMapping("/user/incomeSearch")
	public String userIncomeSearch(TdUserAccountLogSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		Date endDate = sc.getEndDate();
		if(endDate != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar endCalendar = Calendar.getInstance();
			endCalendar.setTime(endDate);
			endCalendar.add(Calendar.DATE, 1);
			endDate = endCalendar.getTime();
			sc.setEndDate(endDate);
		}
		
		List<TdUserAccountLog> userAccountLogList = tdUserAccountLogService.findGroupBySearchCriteria(sc);
		
		modelMap.addAttribute("userAccountLogList", userAccountLogList);
		modelMap.addAttribute("sc", sc);
		return "/admin/report/userIncomeListBody";
	}
	
}
