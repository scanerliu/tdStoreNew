package com.tiandu.admin.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.tiandu.common.controller.BaseController;
import com.tiandu.custom.entity.TdDrawapply;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.entity.TdUserAccount;
import com.tiandu.custom.entity.TdUserAccountLog;
import com.tiandu.custom.entity.TdUserSupplier;
import com.tiandu.custom.search.TdDrawapplySearchCriteria;
import com.tiandu.custom.search.TdUserSupplierSearchCriteria;
import com.tiandu.custom.service.TdDrawapplyService;
import com.tiandu.custom.service.TdUserAccountService;
import com.tiandu.custom.service.TdUserService;
import com.tiandu.custom.service.TdUserSupplierService;

/**
 * 
 * @author L. Gao
 *
 */
@Controller
@RequestMapping("/admin/supplier")
public class UserSupplierController extends BaseController {

	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private TdUserSupplierService tdUserSupplierService;
	
	@Autowired
	private TdUserService tdUserService;
	
	@Autowired
	TdUserAccountService tdUserAccountService;
	
	@Autowired
	private TdDrawapplyService tdDrawapplyService;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/admin/userSupplier/userSupplierList";
	}
	
	@RequestMapping("/search")
	public String search(TdUserSupplierSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		List<TdUserSupplier> userSupplierList = tdUserSupplierService.findBySearchCriteria(sc);
		
	    modelMap.addAttribute("userSupplierList", userSupplierList);
	    modelMap.addAttribute("sc", sc) ;
		return "/admin/userSupplier/userSupplierListbody";
	}
	
	@RequestMapping("/edit")
	public String edit(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUserSupplier tdUserSupplier = null;
		if(null!=id && id != 0){
			tdUserSupplier = tdUserSupplierService.findOne(id);
			if(tdUserSupplier.getUid() != null){
				tdUserSupplier.setUser(tdUserService.findOne(tdUserSupplier.getUid()));
			}
			if(tdUserSupplier.getUpdateBy() != null){
				tdUserSupplier.setUpdatePerson(tdUserService.findOne(tdUserSupplier.getUpdateBy()));
			}
			if(tdUserSupplier.getImages() != null){
				String[] imgArray = tdUserSupplier.getImages().split(":");
				List<String> imgList = new ArrayList<>();
				for(String img : imgArray){
					imgList.add(img);
				}
				modelMap.addAttribute("imgList", imgList);
			}
		}else{
			tdUserSupplier = new TdUserSupplier();	
		}
		modelMap.addAttribute("tdUserSupplier", tdUserSupplier);
		return "/admin/userSupplier/userSupplierForm";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> save(TdUserSupplier userSupplier, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=userSupplier){
			try {
				TdUserSupplier tus = tdUserSupplierService.findOne(userSupplier.getId());
				tus.setUpdateBy(this.getCurrentUser().getUid());
				tus.setUpdateTime(new Date());
				tus.setNote(userSupplier.getNote());
				tus.setStatus(userSupplier.getStatus());
				tdUserSupplierService.save(tus);
				res.put("msg", "供应商资质审核成功。");
				res.put("code", "1");
				return res;
			}catch (Exception e) {
				logger.error("供应商资质审核失败错误信息:"+e);
				res.put("code", "0");
				res.put("msg", "供应商资质审核失败。");
				e.printStackTrace();
				return res;
			}
		}else{
			res.put("code", "0");
			res.put("msg", "供应商资质审核失败。");
			return res;
		}
	}
	
	@RequestMapping(value="/goCheck", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> goCheck(Integer id, String replyContent, Boolean isPass, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(id == null){
			res.put("code", "0");
			res.put("msg", "资质审核失败。");
			logger.error("由于审核Id为空，资质审核失败。");
			return res;
		}
		TdUserSupplier tus = tdUserSupplierService.findOne(id);
		if(isPass){
			tus.setStatus(Byte.valueOf("2"));
			//修改用户供应商类型
			Integer userid = tus.getUid();
//			TdUser suser = tdUserService.findOne(userid);
			TdUser suser = new TdUser();
			suser.setUid(userid);
			suser.setUavatar(null);
			suser.setSupplierType(tus.getSupplierType());
			tdUserService.updateByPrimaryKeySelective(suser);
		}else{
			tus.setStatus(Byte.valueOf("3"));
		}
		tus.setUpdateTime(new Date());
		tus.setUpdateBy(this.getCurrentUser().getUid());
		tus.setReply(replyContent);
		tdUserSupplierService.save(tus);
		res.put("code", "1");
		res.put("msg", "资质审核成功。");
		return res;
	}
	
	@RequestMapping("/drawapply")
	public String drawapply(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/admin/userSupplier/drawapplyList";
	}
	
	@RequestMapping("/searchdrawapply")
	public String searchdrawapply(TdDrawapplySearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		List<TdDrawapply> drawapplyList = tdDrawapplyService.findBySearchCriteria(sc);
	    modelMap.addAttribute("drawapplyList", drawapplyList);
	    modelMap.addAttribute("sc", sc) ;
		return "/admin/userSupplier/drawapplyListbody";
	}
	
	@RequestMapping("/editdrawapply")
	public String editdrawapply(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdDrawapply drawapply = null;
		if(null!=id && id != 0){
			drawapply = tdDrawapplyService.findOne(id);
		}
		if(null!=drawapply){
			modelMap.addAttribute("drawapply", drawapply);
			return "/admin/userSupplier/drawapplyForm";
		}
		return "redirect:404";
	}
	
	@RequestMapping(value="/savedrawapply", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> savedrawapply(TdDrawapply drawapply, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		Date now = new Date();
		if(null!=drawapply && null!=drawapply.getId()){
			try {
				TdDrawapply tus = tdDrawapplyService.findOne(drawapply.getId());
				if(null!=tus && (tus.getStatus()==1||tus.getStatus()==2)){
					tus.setUpdateBy(this.getCurrentUser().getUid());
					tus.setUpdateTime(now);
					tus.setStatus(drawapply.getStatus());
					tus.setRemark(drawapply.getRemark());
					tdDrawapplyService.save(tus);
					if(tus.getStatus()==3||tus.getStatus()==5){//拒绝打款或者打款失败的返回金额到用户钱包
						//提现减去账号金额
						TdUserAccount userAccount = tdUserAccountService.findOne(tus.getUid());
						userAccount.setUpdateBy(1);
						userAccount.setUpdateTime(now);
						TdUserAccountLog alog = new TdUserAccountLog();
						alog.setPreamount(userAccount.getAmount());
						alog.setUid(userAccount.getUid());
						alog.setUpamount(tus.getAmount());
						alog.setType(TdUserAccountLog.USERACCOUNTLOG_TYPE_DRAWAPPLY_REFUND);
						alog.setCreateTime(now);
						alog.setNote("用户提现失败，返回提现金额："+tus.getAmount()+" 元");
						tdUserAccountService.addAmount(userAccount, alog);						
					}
				}
				res.put("msg", "更新成功。");
				res.put("code", "1");
				return res;
			}catch (Exception e) {
				logger.error("提现记录更新成功失败错误信息:"+e);
				res.put("code", "0");
				res.put("msg", "更新失败。");
				e.printStackTrace();
				return res;
			}
		}else{
			res.put("code", "0");
			res.put("msg", "更新失败。");
			return res;
		}
	}
}
