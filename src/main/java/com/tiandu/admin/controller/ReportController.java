package com.tiandu.admin.controller;

import java.math.BigDecimal;
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
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.entity.TdUserAccount;
import com.tiandu.custom.entity.TdUserAccountLog;
import com.tiandu.custom.search.TdUserAccountCriteria;
import com.tiandu.custom.search.TdUserAccountLogSearchCriteria;
import com.tiandu.custom.search.TdUserSearchCriteria;
import com.tiandu.custom.service.TdUserAccountLogService;
import com.tiandu.custom.service.TdUserAccountService;
import com.tiandu.custom.service.TdUserService;
import com.tiandu.custom.vo.AgeCustomerCountVO;
import com.tiandu.custom.vo.RegionCustomerCountVO;
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
	
	@Autowired
	private TdUserAccountService tdUserAccountService;
	
	@Autowired
	private TdUserService tdUserService;

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
	
	@RequestMapping("/user/acountLogList")
	public String acountLogList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currentUser = this.getCurrentUser();
		TdUserAccount userAccount = tdUserAccountService.findByUid(currentUser.getUid());
		if(userAccount == null ){
			modelMap.addAttribute("amount", 0);
		}else if(userAccount.getAmount() == null){
			modelMap.addAttribute("amount", 0);
		}else{
			modelMap.addAttribute("amount", userAccount.getAmount());
		}
		return "/admin/acountLog/acountLogList";
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
	
	/*
	 * 会员收入
	 */
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
	
	/*
	 * 收支明细
	 */
	@RequestMapping("/user/acountLog/search")
	public String saleSearch(TdUserAccountLogSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		Date endDate = sc.getEndDate();
		if(endDate != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar endCalendar = Calendar.getInstance();
			endCalendar.setTime(endDate);
			endCalendar.add(Calendar.DATE, 1);
			endDate = endCalendar.getTime();
			sc.setEndDate(endDate);
		}
		
		List<TdUserAccountLog> acountLogList = tdUserAccountLogService.findBySearchCriteria(sc);
		
		modelMap.addAttribute("acountLogList", acountLogList);
		modelMap.addAttribute("sc", sc);
		return "/admin/acountLog/acountLogListBody";
	}
	/*
	 * 钱包信息列表
	 */
	@RequestMapping("/user/accountlist")
	public String acountlist(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		//统计客户钱包总金额
		BigDecimal totalAmount = tdUserAccountService.countTotalAmount();
		modelMap.addAttribute("totalAmount", totalAmount);
		return "/admin/report/accountList";
	}
	/*
	 * 钱包信息列表body
	 */
	@RequestMapping("/user/accountsearch")
	public String accountsearch(TdUserAccountCriteria sc ,HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		List<TdUserAccount> accountList = tdUserAccountService.findBySearchCriteria(sc);
		modelMap.addAttribute("accountList", accountList);
		modelMap.addAttribute("sc", sc);
		return "/admin/report/accountListBody";
	}
	
	/*
	 * 统计客户地区信息
	 */
	@RequestMapping("/user/regionlist")
	public String customerlist(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/admin/report/userregionlist";
	}
	/*
	 * 统计客户地区信息
	 */
	@RequestMapping("/user/countregion")
	public String countregion(TdUserSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		List<RegionCustomerCountVO> reportList = tdUserService.countByProvinceId(sc);
		modelMap.addAttribute("reportList", reportList);
		return "/admin/report/countregion";
	}
	
	/*
	 * 统计客户地区信息
	 */
	/*@RequestMapping("/user/countgenterlist")
	public String countgenterlist(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/admin/report/usergenterlist";
	}*/
	/**
	 * 统计男女比例
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	/*@RequestMapping("/user/countgenter")
	public String countgenter(TdUserSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		List<RegionCustomerCountVO> reportList = tdUserService.countgenterByCriteria(sc);
		modelMap.addAttribute("reportList", reportList);
		return "/admin/report/countgenter";
	}*/
	@RequestMapping("/user/countagelist")
	public String countgenterlist(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/admin/report/useragelist";
	}
	/**
	 * 统计年龄段人数
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/user/countage")
	public String countage(TdUserSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		AgeCustomerCountVO report = tdUserService.countageByCriteria(sc);
		modelMap.addAttribute("report", report);
		return "/admin/report/countage";
	}
	
	
}
