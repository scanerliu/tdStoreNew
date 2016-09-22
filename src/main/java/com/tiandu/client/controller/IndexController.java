package com.tiandu.client.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiandu.article.entity.TdAdsense;
import com.tiandu.article.entity.TdAdvertisement;
import com.tiandu.article.entity.TdArticleTitle;
import com.tiandu.article.search.TdAdvertisementSearchCriteria;
import com.tiandu.article.search.TdArticleTitleSearchCriteria;
import com.tiandu.article.service.TdAdsenseService;
import com.tiandu.article.service.TdAdvertisementService;
import com.tiandu.article.service.TdArticleTitleService;
import com.tiandu.common.controller.BaseController;
import com.tiandu.common.utils.WebUtils;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.service.TdUserService;
import com.tiandu.custom.vo.LoginForm;
import com.tiandu.menu.entity.TdIndexFloor;
import com.tiandu.menu.search.TdIndexFloorSearchCriteria;
import com.tiandu.menu.service.TdIndexFloorService;

@Controller
@RequestMapping("")
public class IndexController extends BaseController {
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private TdUserService tdUserService;
	
	@Autowired
	private TdAdsenseService tdAdsenseService;
	
	@Autowired
	private TdAdvertisementService tdAdvertisementService;
	
	@Autowired
	private TdArticleTitleService tdArticleTitleService;
	
	@Autowired
	private TdIndexFloorService tdIndexFloorService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		// 系统配置
		modelMap.addAttribute("system", getSystem());
		//首页轮播广告
		TdAdvertisementSearchCriteria sc = new TdAdvertisementSearchCriteria();
		sc.setCreateTime(new Date());
		sc.setEndTime(new Date());
		sc.setStatus(Byte.valueOf("1"));
		TdAdsense adsense = tdAdsenseService.findByName("首页轮播广告");
		if(null != adsense)
		{
			sc.setAdsId(adsense.getId());
			sc.setOrderBy("2");
			modelMap.addAttribute("adList", tdAdvertisementService.findBySearchCriteria(sc));
		}
		//公告新闻
		TdArticleTitleSearchCriteria asc =new TdArticleTitleSearchCriteria();
		asc.setCid(13);//网站公告id
		asc.setStatus((byte)1);
		List<TdArticleTitle> artList = tdArticleTitleService.findBySearchCriteria(asc);
		modelMap.addAttribute("artList", artList);
		asc.setCid(14);//公司新闻id
		List<TdArticleTitle> newsList = tdArticleTitleService.findBySearchCriteria(asc);
		modelMap.addAttribute("newsList", newsList);
		//首页中间广告
		sc.setPageSize(3);
		TdAdsense adsense2 = tdAdsenseService.findByName("首页中间三图广告");
		if(null != adsense2)
		{
			sc.setAdsId(adsense2.getId());
			sc.setOrderBy("2");
			modelMap.addAttribute("middeadList", tdAdvertisementService.findBySearchCriteria(sc));
		}
		//查询楼层
		TdIndexFloorSearchCriteria fsc = new TdIndexFloorSearchCriteria();
		fsc.setFlag(false);
		fsc.setStatus(1);
		List<TdIndexFloor> floorList = tdIndexFloorService.findByTdIndexFloorSearchCriteria(fsc);
		modelMap.addAttribute("floorList", floorList);
	    return "/client/index";
	}
	/**
	 * 首页楼层广告
	 * @param sc
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/floorads")
	public String floorads(TdAdvertisementSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		Date now = new Date();
		sc.setCreateTime(now);
		sc.setEndTime(now);
		sc.setStatus(Byte.valueOf("1"));
		sc.setOrderBy("2");
		//楼层轮播广告
		sc.setFlag(false);
		sc.setAdsId(14);//楼层轮播广告位id
		List<TdAdvertisement> floorcycleadList = tdAdvertisementService.findBySearchCriteria(sc);
		modelMap.addAttribute("floorcycleadList", floorcycleadList);
		//pc楼层中间第一幅广告
		sc.setFlag(true);
		sc.setPageSize(1);
		sc.setAdsId(15);//pc楼层中间第一幅广告位id
		List<TdAdvertisement> floorcenteroneadList = tdAdvertisementService.findBySearchCriteria(sc);
		modelMap.addAttribute("floorcenteroneadList", floorcenteroneadList);
		//pc楼层中间二三幅广告
		sc.setPageSize(2);
		sc.setAdsId(16);//pc楼层中间二三幅广告位id
		List<TdAdvertisement> floorcentertwoadList = tdAdvertisementService.findBySearchCriteria(sc);
		modelMap.addAttribute("floorcentertwoadList", floorcentertwoadList);
		//pc楼层中间二三幅广告
		sc.setPageSize(3);
		sc.setAdsId(17);//pc楼层中间二三幅广告位id
		List<TdAdvertisement> floorrightadList = tdAdvertisementService.findBySearchCriteria(sc);
		modelMap.addAttribute("floorrightadList", floorrightadList);
		
	    return "/client/indexfloor";
	}
	
	@RequestMapping("/unauthorized")
	public String unauthorized(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/unauthorized";
	}
	@RequestMapping("/login")
	public String login(LoginForm loginForm, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.addAttribute("system", this.getSystem());
		modelMap.addAttribute("loginForm", loginForm);
		return "/client/login";
	}
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String logining(LoginForm loginForm, HttpServletRequest request, HttpServletResponse response) {
		if(null!=loginForm && StringUtils.isNotEmpty(loginForm.getUsername())&&StringUtils.isNotEmpty(loginForm.getPassword())){
			TdUser cuser = new TdUser();
			cuser.setUname(loginForm.getUsername());
			cuser.setUpassword(WebUtils.generatePassword(loginForm.getUsername(), loginForm.getPassword()));
			Subject user = SecurityUtils.getSubject();
		    UsernamePasswordToken token = new UsernamePasswordToken(cuser.getUname(),cuser.getUpassword());
		    if(null!=loginForm.getRememberMe()){
		    	token.setRememberMe(true);
		    }else{
		    	token.setRememberMe(false);
		    }
		    
		    try {
		      user.login(token);
		      Date now = new Date();
		      String hostip = request.getRemoteHost();
		      //记录登陆时间 host ip
		      TdUser muser = tdUserService.selectByUname(cuser.getUname());
		      if(null!=muser && null!=muser.getUid()){
			      TdUser upuser = new TdUser();
			      upuser.setUid(muser.getUid());
			      upuser.setLastLoginIp(hostip);
			      upuser.setLastLoginTime(now);
			      tdUserService.updateByPrimaryKeySelective(upuser);
		      }
		      return "redirect:/user/center";
		    }catch (AuthenticationException e) {
		    	logger.error("登录失败错误信息:"+e);
		    	token.clear();
		    	return "redirect:/login";
		    }
		}else{
			
			return "redirect:/login";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		SecurityUtils.getSubject().logout();
		return "redirect:/login";
	}
	
	@RequestMapping("/forgetpassword")
	public String forgetpassword(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/client/forgetpassword";
	}
	@RequestMapping("/forgetpassword2")
	@ResponseBody
	public  Map<String, String> forgetpassword2(String utel, String valideCode, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		Map<String, String> res = new HashMap<String, String>();
		res.put("code", "0");
		try{
			String changePasswordValidCode = (String) request.getSession().getAttribute("changePasswordValidCode");
			
			if(changePasswordValidCode == null || !valideCode.equals(changePasswordValidCode)){
				request.getSession().removeAttribute("changePasswordValidCode");
				res.put("msg", "验证码错误！");
				return  res;
			}
			
			if(StringUtils.isBlank(utel)){
				res.put("msg", "手机号码不能为空！");
				return  res;
			}
			
			TdUser user = tdUserService.findByUtel(utel);
			if(null == user){
				res.put("msg", "没有找到该用户！");
				return  res;
			}
			String sign = WebUtils.generatePassword(utel, utel);
			res.put("token", user.getUid().toString());
			res.put("sign", sign);
			res.put("code", "1");
			
			return res;
		} catch(Exception e){
			request.getSession().removeAttribute("changePasswordValidCode");
			e.printStackTrace();
			res.put("msg", "数据错误！");
			return  res;
		}
		
	}
	
	@RequestMapping("/repassword")
	@ResponseBody
	public Map<String, String> repassword(TdUser user, String sign, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		Map<String, String> res = new HashMap<>();
		try{
			if(null==user || null == user.getUid()){
				res.put("msg", "重置密码失败：用户信息错误！");
				res.put("code", "0");
				return res;
			}
			
			TdUser puser = tdUserService.findOne(user.getUid());
			if(null == puser || null == puser.getUid()){
				res.put("msg", "重置密码失败：用户查找失败！");
				res.put("code", "0");
				return res;
			}
			String sign2 = WebUtils.generatePassword(puser.getUtel(), puser.getUtel());
			if(StringUtils.isBlank(sign) || !sign2.equals(sign)){
				res.put("msg", "重置密码失败：数据错误！");
				res.put("code", "0");
				return res;
			}
			
			TdUser u = new TdUser();
			u.setUname(puser.getUname());
			u.setUid(puser.getUid());
			u.setUpassword(user.getUpassword());
			u.setUpdateBy(1);
			u.setUpdateTime(new Date());
			tdUserService.saveUserPassword(u);
			
			res.put("msg", "重置密码成功。");
			res.put("code", "1");
			return res;
		} catch(Exception e){
			res.put("msg", "重置密码失败："+e.getMessage());
			res.put("code", "0");
			return res;
		}
		
	}
}
