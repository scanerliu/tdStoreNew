package com.tiandu.mobile.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tiandu.article.entity.TdAdsense;
import com.tiandu.article.search.TdAdvertisementSearchCriteria;
import com.tiandu.article.service.TdAdsenseService;
import com.tiandu.article.service.TdAdvertisementService;
import com.tiandu.common.controller.BaseController;
import com.tiandu.common.tencent.common.TdUserQRcodeTools;
import com.tiandu.common.utils.ConstantsUtils;
import com.tiandu.common.utils.MessageSender;
import com.tiandu.common.utils.WebUtils;
import com.tiandu.complaint.search.TdComplaintCriteria;
import com.tiandu.complaint.service.TdComplaintService;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.vo.LoginForm;
import com.tiandu.custom.vo.WeChatCodeResponse;
import com.tiandu.custom.vo.WeChatUserInfoResponse;
import com.tiandu.product.search.TdProductCriteria;
import com.tiandu.product.service.TdProductService;

/** 
 * @author liuxinbing
 */
@Controller
@RequestMapping("/mobile")
public class MobileController extends BaseController {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private TdAdsenseService tdAdsenseService;
	
	@Autowired
	private TdAdvertisementService tdAdvertisementService;
	
	@Autowired
	private TdComplaintService tdComplaintService;
	
	@Autowired
	private TdProductService tdProductService;
	
	@Autowired
	private TdUserQRcodeTools tdUserQRcodeTools;
	
	/**
	 * @author Max
	 */
	@RequestMapping("")
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		
		return "redirect:/mobile/index";
		/*// 轮播广告
		TdAdvertisementSearchCriteria sc = new TdAdvertisementSearchCriteria();
		sc.setCreateTime(new Date());
		sc.setEndTime(new Date());
		sc.setOrderBy("2");
		sc.setStatus((byte)1);
		TdAdsense adsense = tdAdsenseService.findByName("触屏首页轮播大图广告");
		if(null != adsense)
		{
			sc.setAdsId(adsense.getId());
			map.addAttribute("adList", tdAdvertisementService.findBySearchCriteria(sc));
		}
		
		adsense = tdAdsenseService.findByName("触屏竞选内容广告");
		if(null != adsense)
		{
			sc.setAdsId(adsense.getId());
			map.addAttribute("compAdList", tdAdvertisementService.findBySearchCriteria(sc));
		}
		
		adsense = tdAdsenseService.findByName("触屏精品专区广告");
		if(null != adsense)
		{
			sc.setAdsId(adsense.getId());
			map.addAttribute("hotAdList", tdAdvertisementService.findBySearchCriteria(sc));
		}
		
		adsense = tdAdsenseService.findByName("触屏首页类别展示广告");
		if(null != adsense)
		{
			sc.setAdsId(adsense.getId());
			map.addAttribute("productTypeAdList", tdAdvertisementService.findBySearchCriteria(sc));
		}
		
		// 系统配置
		map.addAttribute("system", getSystem());
		
		// 股东竞选
		TdComplaintCriteria csc = new TdComplaintCriteria();
		sc.setStatus((byte)1);
		map.addAttribute("complaintList", tdComplaintService.findBySearchCriteria(csc));
		
		// 热销推荐
		TdProductCriteria psc = new TdProductCriteria();
		psc.setHotRecommend(1);
		psc.setOnshelf(true);
		psc.setKind((byte)1);
		map.addAttribute("productList", tdProductService.findBySearchCriteria(psc));
		
	    return "/mobile/index";*/
	}
	
	/**
	 * @author Max
	 */
	@RequestMapping("/index")
	public String index1(HttpServletRequest request, HttpServletResponse response, ModelMap map) {

		// 轮播广告
		TdAdvertisementSearchCriteria sc = new TdAdvertisementSearchCriteria();
		sc.setCreateTime(new Date());
		sc.setEndTime(new Date());
		TdAdsense adsense = tdAdsenseService.findByName("触屏首页轮播大图广告");
		if(null != adsense)
		{
			sc.setAdsId(adsense.getId());
			sc.setOrderBy("2");
			map.addAttribute("adList", tdAdvertisementService.findBySearchCriteria(sc));
		}
		
		adsense = tdAdsenseService.findByName("触屏竞选内容广告");
		if(null != adsense)
		{
			sc.setAdsId(adsense.getId());
			sc.setOrderBy("2");
			map.addAttribute("compAdList", tdAdvertisementService.findBySearchCriteria(sc));
		}
		
		adsense = tdAdsenseService.findByName("触屏精品专区广告");
		if(null != adsense)
		{
			sc.setAdsId(adsense.getId());
			sc.setOrderBy("2");
			map.addAttribute("hotAdList", tdAdvertisementService.findBySearchCriteria(sc));
		}
		
		adsense = tdAdsenseService.findByName("触屏首页类别展示广告");
		if(null != adsense)
		{
			sc.setAdsId(adsense.getId());
			sc.setFlag(false);
			map.addAttribute("productTypeAdList", tdAdvertisementService.findBySearchCriteria(sc));
		}
		
		// 系统配置
		map.addAttribute("system", getSystem());
		
		// 股东竞选
		TdComplaintCriteria csc = new TdComplaintCriteria();
		sc.setStatus((byte)1);
		map.addAttribute("complaintList", tdComplaintService.findBySearchCriteria(csc));
		
		// 热销推荐
		TdProductCriteria psc = new TdProductCriteria();
		psc.setHotRecommend(1);
		psc.setOnshelf(true);
		psc.setKind((byte)1);
		map.addAttribute("productList", tdProductService.findBySearchCriteria(psc));
		
		return "/mobile/index";
	}
	@RequestMapping("/unauthorized")
	public String unauthorized(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/unauthorized";
	}
	@RequestMapping("/login")
	public String login(LoginForm loginForm, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.addAttribute("loginForm", loginForm);
		return "/mobile/login";
	}
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String logining(LoginForm loginForm, HttpServletRequest request, HttpServletResponse response) {
		if(null!=loginForm && StringUtils.isNotEmpty(loginForm.getUsername())&&StringUtils.isNotEmpty(loginForm.getPassword())){
			TdUser cuser = new TdUser();
			cuser.setUname(loginForm.getUsername());
			cuser.setUpassword(WebUtils.generatePassword(loginForm.getUsername(), loginForm.getPassword()));
			Subject user = SecurityUtils.getSubject();
		    UsernamePasswordToken token = new UsernamePasswordToken(cuser.getUname(),cuser.getUpassword());
		    token.setRememberMe(true);
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
		      return "redirect:/mobile/index";
		    }catch (AuthenticationException e) {
		    	logger.error("登录失败错误信息:"+e);
		    	token.clear();
		    	return "redirect:/mobile/login";
		    }
		}else{
			
			return "redirect:/mobile/login";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		SecurityUtils.getSubject().logout();
		return "redirect:/mobile/login";
	}
	
	@RequestMapping("/forgetpassword")
	public String forgetpassword(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/mobile/forgetpassword";
	}
	@RequestMapping("/forgetpassword2")
	public String forgetpassword2(String utel, String valideCode, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		try{
			String changePasswordValidCode = (String) request.getSession().getAttribute("changePasswordValidCode");
			
			if(changePasswordValidCode == null || !valideCode.equals(changePasswordValidCode)){
				request.getSession().removeAttribute("changePasswordValidCode");
				String errmsg = "验证码错误！";
				modelMap.addAttribute("errmsg", errmsg) ;
				return  "/mobile/error";
			}
			
			if(StringUtils.isBlank(utel)){
				String errmsg = "手机号码不能为空！";
				modelMap.addAttribute("errmsg", errmsg) ;
				return  "/mobile/error";
			}
			
			TdUser user = tdUserService.findByUtel(utel);
			if(null == user){
				String errmsg = "没有找到该用户！";
				modelMap.addAttribute("errmsg", errmsg) ;
				return  "/mobile/error";
			}
			String sign = WebUtils.generatePassword(utel, utel);
			modelMap.addAttribute("user", user);
			modelMap.addAttribute("sign", sign);
			
			return "/mobile/forgetpassword2";
		} catch(Exception e){
			request.getSession().removeAttribute("changePasswordValidCode");
			e.printStackTrace();
			String errmsg = "未填写收货地址！";
			modelMap.addAttribute("errmsg", errmsg) ;
			return  "/mobile/error";
		}
		
	}
	
	@RequestMapping(value="/getChangePhoneNumValidCode", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> getChangePhoneNumValidCode(HttpServletRequest request, String phone) {
		Map<String,String> res = new HashMap<String,String>();
		Random random = new Random();
		String smscode = String.format("%06d", random.nextInt(999999));
		request.getSession().setAttribute("changePasswordValidCode", smscode);
		List<String> phoneNums =new ArrayList<>();
		phoneNums.add(phone);
		List<String> datas =new ArrayList<>();
		datas.add(smscode);
		datas.add("1");
		MessageSender ms = new MessageSender();
		ms.init();
		boolean isSendSuccess = ms.send(phoneNums, ConstantsUtils.SMS_TEMPLATE_VALIDCODE, datas);
		if(isSendSuccess){
			res.put("code", "1");
			res.put("msg", "发送验证码成功!");			
		}else{
			res.put("code", "0");
			res.put("msg", "验证码获取失败,请稍后再试!");
		}
		return res;
	}
	
	@RequestMapping("/repassword")
	@ResponseBody
	public Map<String, String> repassword(TdUser user, String sign, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		Map<String, String> res = new HashMap<>();
		try{
			if(null==user || null == user.getUid()){
				res.put("info", "重置密码失败：用户信息错误！");
				res.put("status", "0");
				return res;
			}
			
			TdUser puser = tdUserService.findOne(user.getUid());
			if(null == puser || null == puser.getUid()){
				res.put("info", "重置密码失败：用户查找失败！");
				res.put("status", "0");
				return res;
			}
			String sign2 = WebUtils.generatePassword(puser.getUtel(), puser.getUtel());
			if(StringUtils.isBlank(sign) || !sign2.equals(sign)){
				res.put("info", "重置密码失败：数据错误！");
				res.put("status", "0");
				return res;
			}
			
			TdUser u = new TdUser();
			u.setUname(puser.getUname());
			u.setUid(puser.getUid());
			u.setUpassword(user.getUpassword());
			u.setUpdateBy(1);
			u.setUpdateTime(new Date());
			tdUserService.saveUserPassword(u);
			
			res.put("info", "重置密码成功。");
			res.put("status", "1");
			return res;
		} catch(Exception e){
			res.put("info", "重置密码失败："+e.getMessage());
			res.put("status", "0");
			return res;
		}
		
	}
	
	@RequestMapping("/app")
	public String app(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/mobile/app";
	}
	@RequestMapping("/wechatlogin")
	public String wechatlogin(String code, String state, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
		if(StringUtils.isNotBlank(code)){
			String wechat_access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+ConstantsUtils.WECHAT_APPID+"&secret="+ConstantsUtils.WECHAT_APPSECRET+"&code="+code+"&grant_type=authorization_code";
			logger.error("wechat login url:"+wechat_access_token_url);
			RestTemplate restTemplate = new RestTemplate();
			try {
				ResponseEntity<String> codesponse = restTemplate.getForEntity(wechat_access_token_url, String.class);
				logger.error("wechat login response:"+codesponse.getBody());
				Gson gson = new Gson();
				WeChatCodeResponse codeResponse = gson.fromJson(codesponse.getBody(), WeChatCodeResponse.class);
				
//				WeChatCodeResponse codeResponse = new WeChatCodeResponse();
//				codeResponse.setOpenid("oS3MPvwqj8BQs3IH2nNrwcyUsuJw");
				if(null!=codeResponse&&null!=codeResponse.getOpenid()){//获取openid
					TdUser cuser = tdUserService.findByJoinCode(codeResponse.getOpenid());
					if(null!=cuser){//匹配到用户
						boolean updateuserinfo = false;
						if(StringUtils.isBlank(cuser.getUnick())){//昵称为空时，获取微信信息
							String access_tocken = tdUserQRcodeTools.validAccessToken();
							String wechat_user_info_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_tocken+"&openid="+codeResponse.getOpenid()+"&lang=zh_CN";
							logger.error("wechat get userinfo url:"+wechat_user_info_url);
							ResponseEntity<String> userinfosponse = restTemplate.getForEntity(wechat_user_info_url, String.class);
							String responsebody = new String(userinfosponse.getBody().getBytes("ISO-8859-1"),"UTF-8");
							logger.error("wechat get userinfo response:"+responsebody);
							WeChatUserInfoResponse uinfoResponse = gson.fromJson(responsebody, WeChatUserInfoResponse.class);
							if(null!=uinfoResponse&&null!=uinfoResponse.getOpenid()){
								updateuserinfo = true;
								cuser.setUnick(uinfoResponse.getNickname());
								cuser.setUgenter(uinfoResponse.getGenter());
								cuser.setUavatar(uinfoResponse.getHeadimgurl());
							}							
						}
						Subject user = SecurityUtils.getSubject();
						UsernamePasswordToken token = new UsernamePasswordToken(cuser.getUname(),cuser.getUpassword());
					    token.setRememberMe(true);
					    try {
					      user.login(token);
					      Date now = new Date();
					      String hostip = request.getRemoteHost();
					      //记录登陆时间 host ip
					      //TdUser muser = tdUserService.selectByUname(cuser.getUname());
					      if(null!=cuser && null!=cuser.getUid()){
						      TdUser upuser = new TdUser();
						      upuser.setUid(cuser.getUid());
						      upuser.setLastLoginIp(hostip);
						      upuser.setLastLoginTime(now);
						      if(updateuserinfo){
						    	  upuser.setUnick(cuser.getUnick());
						    	  upuser.setUgenter(cuser.getUgenter());
						    	  upuser.setUavatar(cuser.getUavatar());
						      }
						      tdUserService.updateByPrimaryKeySelective(upuser);
					      }
					      if(StringUtils.isNotBlank(state)){
					    	  switch (state) {
								case "1"://主页
									return "redirect:/mobile/index";
								case "2"://零元专区
									return "redirect:/mobile/product/zero";
								case "3"://新品专区
									return "redirect:/mobile/product/new";
								case "4"://秒杀预售
									return "redirect:/product/seckill";
								case "5"://个人中心
									return "redirect:/mobile/user/center";
								case "6"://分享二维码
									return "redirect:/mobile/user/mySpread";
								case "7"://成为供应商
									return "redirect:/mobile/agent/producttype?agentId=1";
								case "8"://成为代理
									return "redirect:/mobile/agent/list";
								case "9"://成为分公司
									return "redirect:/mobile/agent/list";
								case "10"://股东竞选
									return "redirect:/mobile/campaign/list";
								case "11"://关于我们
									return "redirect:/mobile/article/item3";
								case "12"://退货退款
									return "redirect:/mobile/article/item2";
								case "13"://投诉维权
									return "redirect:/mobile/article/item4";
								case "14"://联盟合作
									return "redirect:/mobile/article/item5";
								case "15"://公司公告
									return "redirect:/mobile/article/list?cid=13";
								default:
									break;
							  }
					      }
					      return "redirect:/mobile/index";
					    }catch (AuthenticationException e) {
					    	logger.error("wechat login error:"+e.getMessage());
					    	token.clear();
					    	return "redirect:/mobile/index";
					    }
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("wechat login error:"+e.getMessage());
			}
			
		}
		return "redirect:/mobile/index";
	}
	
}
