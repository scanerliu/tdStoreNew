package com.tiandu.client.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiandu.article.entity.TdAdsense;
import com.tiandu.article.search.TdAdvertisementSearchCriteria;
import com.tiandu.article.service.TdAdsenseService;
import com.tiandu.article.service.TdAdvertisementService;
import com.tiandu.common.controller.BaseController;
import com.tiandu.common.tencent.common.TdUserQRcodeTools;
import com.tiandu.common.utils.ConstantsUtils;
import com.tiandu.common.utils.MessageSender;
import com.tiandu.common.utils.TwoDimensionCode;
import com.tiandu.common.utils.WeChatRedPackUtils;
import com.tiandu.common.utils.WebUtils;
import com.tiandu.complaint.search.TdComplaintCriteria;
import com.tiandu.complaint.service.TdComplaintService;
import com.tiandu.custom.entity.TdAgent;
import com.tiandu.custom.entity.TdCampaign;
import com.tiandu.custom.entity.TdExperienceStore;
import com.tiandu.custom.entity.TdMembership;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.entity.TdUserAccount;
import com.tiandu.custom.entity.TdUserAccountLog;
import com.tiandu.custom.entity.TdUserAddress;
import com.tiandu.custom.entity.TdUserCampaign;
import com.tiandu.custom.entity.TdUserIntegral;
import com.tiandu.custom.entity.TdUserIntegralLog;
import com.tiandu.custom.entity.TdUserMessage;
import com.tiandu.custom.entity.TdUserSupplier;
import com.tiandu.custom.search.TdAgentSearchCriteria;
import com.tiandu.custom.search.TdCampaignSearchCriteria;
import com.tiandu.custom.search.TdUserAccountLogSearchCriteria;
import com.tiandu.custom.search.TdUserAddressCriteria;
import com.tiandu.custom.search.TdUserCampaignCriteria;
import com.tiandu.custom.search.TdUserIntegralLogSearchCriteria;
import com.tiandu.custom.search.TdUserMessageSearchCriteria;
import com.tiandu.custom.search.TdUserSearchCriteria;
import com.tiandu.custom.search.TdUserSupplierSearchCriteria;
import com.tiandu.custom.service.TdAgentService;
import com.tiandu.custom.service.TdCampaignService;
import com.tiandu.custom.service.TdExperienceStoreService;
import com.tiandu.custom.service.TdMembershipService;
import com.tiandu.custom.service.TdUserAccountLogService;
import com.tiandu.custom.service.TdUserAccountService;
import com.tiandu.custom.service.TdUserAddressService;
import com.tiandu.custom.service.TdUserCampaignService;
import com.tiandu.custom.service.TdUserIntegralService;
import com.tiandu.custom.service.TdUserMessageService;
import com.tiandu.custom.service.TdUserSignService;
import com.tiandu.custom.service.TdUserSupplierService;
import com.tiandu.custom.vo.WithDrawVO;
import com.tiandu.district.entity.TdDistrict;
import com.tiandu.district.search.TdDistrictSearchCriteria;
import com.tiandu.district.service.TdDistrictService;
import com.tiandu.order.entity.TdShoppingcartItem;
import com.tiandu.order.search.TdShoppingcartSearchCriteria;
import com.tiandu.order.service.TdShoppingcartItemService;
import com.tiandu.order.vo.OperResult;
import com.tiandu.order.vo.ShoppingcartVO;
import com.tiandu.order.vo.SkuSpecialVO;
import com.tiandu.product.entity.TdBrand;
import com.tiandu.product.entity.TdProduct;
import com.tiandu.product.entity.TdProductAttachment;
import com.tiandu.product.entity.TdProductAttribute;
import com.tiandu.product.entity.TdProductAttributeOption;
import com.tiandu.product.entity.TdProductDescription;
import com.tiandu.product.entity.TdProductSku;
import com.tiandu.product.entity.TdProductStat;
import com.tiandu.product.entity.TdProductType;
import com.tiandu.product.entity.TdProductTypeAttribute;
import com.tiandu.product.search.TdBrandSearchCriteria;
import com.tiandu.product.search.TdProductAttributeOptionCriteria;
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
import com.tiandu.system.utils.ConfigUtil;

/**
 * 
 * @author liuxinbing
 *
 */
@Controller
@RequestMapping("/user")
public class CUserController extends BaseController {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private TdMembershipService tdMembershipService;
	
	@Autowired
	private TdUserSignService tdUserSignService;
	
	@Autowired
	private TdShoppingcartItemService tdShoppingcartItemService;
	
	@Autowired
	private TdUserMessageService tdUserMessageService;
	
	@Autowired
	private TdUserAddressService tdUserAddressService;
	
	@Autowired
	private TdDistrictService tdDistrictService;
	
	@Autowired
	private TdExperienceStoreService tdExperienceStoreService;
	
	@Autowired
	TdAgentService tdAgentService;
	
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
	TdProductAttributeService tdProductAttributeService; 
	
	@Autowired
	TdProductTypeAttributeService tdProductTypeAttributeService; 
	
	@Autowired
	private TdProductStatService tdProductStatService;
	
	@Autowired
	TdProductSkuService tdProductSkuService;
	
	@Autowired
	TdUserAccountService tdUserAccountService;
	
	@Autowired
	TdUserIntegralService tdUserIntegralService;
	
	@Autowired
	TdCampaignService tdCampaignService;
	
	@Autowired
	TdUserCampaignService tdUserCampaignService;
	
	@Autowired
	TdUserSupplierService tdUserSupplierService; 
	
	@Autowired
	private TdAdsenseService tdAdsenseService;
	
	@Autowired
	private TdAdvertisementService tdAdvertisementService;
	
	@Autowired
	private TdComplaintService tdComplaintService;
	
	@Autowired
	private TdUserQRcodeTools tdUserQRcodeTools;
	@Autowired
	private TdUserAccountLogService tdUserAccountLogService;
	@Autowired
	private TdBrandService tdBrandService;
	@Autowired
	private ConfigUtil configUtil;
	
	// 个人中心
	@RequestMapping("/center")
	public String center(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currentUser = this.getCurrentUser();
		if(null==currentUser)
		{
			return "redirect:/login";
		}
		
		//会员等级
		TdMembership membership = tdMembershipService.findOne(currentUser.getMembershipId());
		modelMap.addAttribute("membership", membership);
		//会员账号信息
		TdUserAccount account = tdUserAccountService.findByUid(currentUser.getUid());
		currentUser.setUserAccount(account);
		//会员积分信息
		TdUserIntegral integral = tdUserIntegralService.findOne(currentUser.getUid());
		modelMap.addAttribute("integral", integral);
		
		modelMap.addAttribute("currentUser", currentUser);
		// 系统配置
		modelMap.addAttribute("system", getSystem());
	    return "/client/user/center";
	}
	
	// 用户签到
	@RequestMapping(value="/sign", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> sign(HttpServletRequest request, HttpServletResponse response) {
		TdUser currentUser = this.getCurrentUser();
		Map<String,String> res = new HashMap<String,String>(); 
		Map<String, String> signBackData;
		try {
			signBackData = tdUserService.saveSign(currentUser.getUid());
		} catch (ParseException e) {
			logger.error("签到失败!");
			res.put("code", "0");
			res.put("msg", "签到失败。");
			e.printStackTrace();
			return res;
		}
		if(signBackData.get("code").equals("0")){
			logger.error("签到失败!");
			res.put("code", "0");
			res.put("msg", signBackData.get("msg"));
		}else if(signBackData.get("code").equals("1")){
			res.put("code", "1");
			res.put("msg", "签到成功。");
			res.put("gettedIntegral", signBackData.get("signIntegral"));
		}
		return res;
	}
	// 获取用户系统消息数量
	@RequestMapping(value="/messagecount", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> messagecount(HttpServletRequest request, HttpServletResponse response) {
		TdUser currentUser = this.getCurrentUser();
		Map<String,String> res = new HashMap<String,String>(); 
		TdUserMessageSearchCriteria sc = new TdUserMessageSearchCriteria();
		sc.setUid(currentUser.getUid());
		sc.setStatus(Byte.valueOf("1"));
		Integer count = tdUserMessageService.countByCriteria(sc);
		res.put("code", "1");
		res.put("count", count.toString());
		return res;
	}
	
	@RequestMapping(value="/getChangeUserPasswordValidCode", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> getChangeUserPasswordValidCode(HttpServletRequest request) {
		Map<String,String> res = new HashMap<String,String>();
		Random random = new Random();
		String smscode = String.format("%04d", random.nextInt(9999));
		request.getSession().setAttribute("changePasswordValidCode", smscode);
		List<String> phoneNums =new ArrayList<>();
		phoneNums.add(this.getCurrentUser().getUtel());
		List<String> datas =new ArrayList<>();
		datas.add(smscode);
		datas.add("1");
		MessageSender ms = new MessageSender();
		ms.init();
		boolean isSendSuccess = ms.send(phoneNums, "1", datas);
		if(isSendSuccess){
			res.put("code", "1");
			res.put("msg", "发送验证码成功!");			
		}else{
			res.put("code", "0");
			res.put("msg", "验证码获取失败!");
		}
		return res;
	}
	
	@RequestMapping(value="/getChangePhoneNumValidCode", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> getChangePhoneNumValidCode(HttpServletRequest request, String phone) {
		Map<String,String> res = new HashMap<String,String>();
		Random random = new Random();
		String smscode = String.format("%04d", random.nextInt(9999));
		request.getSession().setAttribute("changePasswordValidCode", smscode);
		List<String> phoneNums =new ArrayList<>();
		phoneNums.add(phone);
		List<String> datas =new ArrayList<>();
		datas.add(smscode);
		datas.add("1");
		MessageSender ms = new MessageSender();
		ms.init();
		boolean isSendSuccess = ms.send(phoneNums, "1", datas);
		if(isSendSuccess){
			res.put("code", "1");
			res.put("msg", "发送验证码成功!");			
		}else{
			res.put("code", "1"); // 用于替换下两行
			res.put("msg", "发送验证码成功!");// 用于替换下两行
			//res.put("code", "0");
			//res.put("msg", "验证码获取失败!");
		}
		return res;
	}
	
	/*
	 * 消息列表
	 */
	@RequestMapping("/messageList")
	public String messageList(Byte msgType, String active, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currentUser = this.getCurrentUser();
		if(msgType == null){
			//获取购物车
			Map<String, List<TdUserMessage>> messageMap = tdUserService.getMessageList(currentUser.getUid());
			List<TdUserMessage> systemMessageList = messageMap.get("systemMessageList");
			List<TdUserMessage> orderMessageList = messageMap.get("orderMessageList");
			List<TdUserMessage> experienceStoreMessageList = messageMap.get("experienceStoreMessageList");
			modelMap.addAttribute("systemMessageList", systemMessageList);
			modelMap.addAttribute("orderMessageList", orderMessageList);
			modelMap.addAttribute("experienceStoreMessageList", experienceStoreMessageList);
		}
		if(active != null && !active.equals("")){
			modelMap.addAttribute("active", active);			
		}
	    return "/mobile/user/MessageList";
	}
	
	/*
	 * 消息详情
	 */
	@RequestMapping("/messageDetail")
	public String messageDetail(Integer messageId, String active, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currentUser = this.getCurrentUser();
		if(active != null && !active.equals("")){
			modelMap.addAttribute("active", active);
		}
		TdUserMessage message = tdUserMessageService.findOne(messageId);
		if(message.getStatus().equals(Byte.valueOf("1"))){
			message.setStatus(Byte.valueOf("2"));
			tdUserMessageService.save(message);			
		}
		modelMap.addAttribute("message", tdUserMessageService.findOne(messageId));
		if(!message.getMsgType().equals(Byte.valueOf("3"))){
			return "/mobile/user/systemOrOrderMessageDetail";		
		}else{
			TdExperienceStore experienceStore = tdExperienceStoreService.findOne(message.getRelateId());
			
			modelMap.addAttribute("experienceStore", experienceStore);
			return "/mobile/user/experienceStoreMessageDetail";
		}
	}
	
	/*
	 * 用户信息
	 */
	@RequestMapping("/info")
	public String info(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currentUser = this.getCurrentUser();
		modelMap.addAttribute("currentUser", currentUser);
		// 系统配置
		modelMap.addAttribute("system", getSystem());
		return "/client/user/info";		
	}
	/**
	 * 保存个人信息
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/saveInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveInfo(TdUser user, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>();
		res.put("code", "0");
		res.put("msg", "个人信息修改失败！");
		TdUser currentUser = this.getCurrentUser();
		String avatar = user.getUavatar();
		if(StringUtils.isNotBlank(avatar)){
			user.setUavatar(avatar);
		}else{
			user.setUavatar(null);
		}
		/*currentUser.setUavatar(avatar);
		currentUser.setUnick(user.getUnick());
		currentUser.setUgenter(user.getUgenter());
		currentUser.setUbirthday(user.getUbirthday());
		currentUser.setUtel(user.getUtel());*/
		user.setUid(currentUser.getUid());
		if(tdUserService.saveUserInfo(user) == 1){
			res.put("msg", "个人信息修改成功！");
			res.put("code", "1");
		}
		return res;
	}
	
	/*
	 * 修改密码
	 */
	@RequestMapping("/changePassword")
	public String changePassword(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		// 系统配置
		modelMap.addAttribute("system", getSystem());
		return "/client/user/changePassword";		
	}
	
	// 保存修改密码
	@RequestMapping(value="/savePassword", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> savePassword(String oldPassword, String valideCode, String newPassword, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>();
		/*String changePasswordValidCode = (String) request.getSession().getAttribute("changePasswordValidCode");
		if(changePasswordValidCode == null || !valideCode.equals(changePasswordValidCode)){
			res.put("info", "验证码错误！");
			res.put("status", "n");
			request.getSession().removeAttribute("changePasswordValidCode");
			return res;
		}*/
		
		TdUser currentUser = this.getCurrentUser();
		currentUser = tdUserService.findOne(currentUser.getUid());
		String md5OldPassword = WebUtils.generatePassword(currentUser.getUname(), oldPassword);
		if(!md5OldPassword.equals(currentUser.getUpassword())){
			res.put("msg", "原始密码错误！");
			res.put("code", "0");
			return res;
		}
		Date now = new Date();
		currentUser.setUpdateBy(currentUser.getUid());
		currentUser.setUpdateTime(now);
		currentUser.setCreateTime(now);
		currentUser.setUpassword(newPassword);
    	tdUserService.saveUserPassword(currentUser);
    	res.put("msg", "密码修改成功！");
		res.put("code", "1");
		return res;
	}
	/*
	 * 修改手机号
	 */
	@RequestMapping("/changePhoneNum")
	public String changePhoneNum(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currentUser = this.getCurrentUser();
		List<Integer> reginIds = new ArrayList<>();
		if(currentUser.getUregionId() != null){
			/*TdDistrict dis = tdDistrictService.findOne(currentUser.getUregionId());
			reginIds.add(dis.getId());
			if(!dis.getLevel().equals(Byte.valueOf("1"))){
				dis = tdDistrictService.findOne(dis.getUpid());
				reginIds.add(dis.getId());
				if(!dis.getLevel().equals(Byte.valueOf("1"))){
					dis = tdDistrictService.findOne(dis.getUpid());
					reginIds.add(dis.getId());
				}
			}*/
			Map<String, Object> regionMap = tdUserAddressService.getUserDistrictIdByRegionId(new HashMap<String,Object>(),currentUser.getUregionId());
			modelMap.addAllAttributes(regionMap);
		}
		// 为没有地区的会员默认初始化为北京
		/*if(reginIds.size() == 0){
			reginIds.add(1);
		}
		if(reginIds.size() > 0){
			modelMap.addAttribute("levelCount", reginIds.size());
			reginIds.add(0);
			String disIdStr = "";
			for(int i = reginIds.size() - 1; i >= 0; i --){
				disIdStr += reginIds.get(i);
				if(i > 0){
					disIdStr += ",";
				}
			}
			modelMap.addAttribute("disIdStr", disIdStr);
		}*/
		// 系统配置
		modelMap.addAttribute("system", getSystem());
		return "/client/user/changePhoneNum";		
	}
	
	/*
	 * 修改手机号及地区
	 */
	@RequestMapping("/savePhoneNum")
	@ResponseBody
	public Map<String, String> savePhoneNum(TdUser user, String valideCode, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		Map<String, String> res = new HashMap<>();
		try{
			String changePasswordValidCode = (String) request.getSession().getAttribute("changePasswordValidCode");
			if(changePasswordValidCode == null || !valideCode.equals(changePasswordValidCode)){
				res.put("msg", "验证码错误！");
				res.put("status", "0");
				return res;
			}
			StringBuffer sb = new StringBuffer();
			sb.append("["+user.getUprovinceId()+"]");
			sb.append("["+user.getUcityId()+"]");
			if(null==user.getUregionId()){
				user.setUregionId(user.getUcityId());
			}else{
				sb.append("["+user.getUregionId()+"]");
			}			
			
			TdUser currentUser = this.getCurrentUser();
			TdUser u = new TdUser();
			u.setUid(currentUser.getUid());
			u.setUtel(user.getUtel());
			u.setUregionId(user.getUregionId());
			u.setUverification(Byte.valueOf("1"));
			u.setUprovinceId(user.getUprovinceId());
			u.setUregionPath(sb.toString());
			tdUserService.saveUserInfo(u);
		} catch(Exception e){
			res.put("status", "n");
			res.put("info", "保存失败！");
			logger.error("用户修改手机号及地区失败！");
			request.getSession().removeAttribute("changePasswordValidCode");
			e.printStackTrace();
			return res;
		}
		res.put("status", "y");
		res.put("info", "修改成功！");
		request.getSession().removeAttribute("changePasswordValidCode");
		return res;
	}
	/**
	 * 收货地址
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/shoppingaddress")
	public String shoppingAddress(HttpServletRequest request,ModelMap map,Integer redirect)
	{
		TdUser tdUser = this.getCurrentUser();
		if(tdUser == null)
		{
			return "redirect:/login";
		}
		if(redirect != null && redirect > 0)
		{
			request.getSession().setAttribute("redirect", redirect);
		}
		// 系统配置
		map.addAttribute("system", getSystem());
		map.addAttribute("redirect", redirect);
		return "/client/user/shoppingAddressList";
	}
	
	/**
	 * 收货地址listbody
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/sarchshoppingaddress")
	public String sarchshoppingaddress(TdUserAddressCriteria sc, HttpServletRequest request,ModelMap map)
	{
		TdUser tdUser = this.getCurrentUser();
		sc.setUid(tdUser.getUid());
		sc.setFlag(false);
		List<TdUserAddress> userAddresses = tdUserAddressService.findBySearchCriteria(sc);
		map.addAttribute("address_list", userAddresses);
		return "/client/user/shoppingAddressListBody";
	}
	
	/**
	 *设置默认地址 
	 * @param tdUserAddress
	 * @return
	 */
	@RequestMapping(value = "/defaultshoppingaddress" ,method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> defaultshoppingaddress(Integer addressId, HttpServletRequest request, ModelMap map)
	{
		Map<String,String> result = new HashMap<String,String>();
		result.put("code", ConstantsUtils.RETURN_CODE_SUCCESS.toString());
		TdUser tdUser = this.getCurrentUser();
		if(addressId != null && addressId > 0)
		{
			tdUserAddressService.setIsDefaultFalse(tdUser.getUid());
			TdUserAddress userAddress = tdUserAddressService.findOne(addressId);
			userAddress.setIsDefault(true);
			tdUserAddressService.save(userAddress);
		}
		result.put("msg", "成功");
		return result;
	}
	
	/**
	 * 新增收货地址
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/editshoppingaddress")
	public String editshoppingaddress(Integer addressId,ModelMap map,HttpServletRequest request)
	{
		TdUser tdUser = this.getCurrentUser();
		// 系统配置
		if(addressId != null && addressId > 0)
		{
			TdUserAddress userAddress = tdUserAddressService.findOne(addressId);
			if(userAddress != null && userAddress.getUid() == tdUser.getUid())
			{
				map.addAttribute("address",userAddress);
				Integer regionId = userAddress.getRegionId();
				Map<String, Object> regionMap = tdUserAddressService.getUserDistrictIdByRegionId(new HashMap<String,Object>(),regionId);
				map.addAllAttributes(regionMap);
			}
		}else{
			TdUserAddress userAddress = new TdUserAddress();
			map.addAttribute("address",userAddress);
		}
		return "/client/user/shoppingAddressForm";
	}
	
	/**
	 * 地址保存并设置默认地址 
	 * @param tdUserAddress
	 * @return
	 */
	@RequestMapping(value = "/shoppingAddressSave" ,method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> shoppingAddressSave(TdUserAddress tdUserAddress, HttpServletRequest request)
	{
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("code", ConstantsUtils.RETURN_CODE_FAILURE);
		TdUser tdUser = this.getCurrentUser();
		if(tdUser == null)
		{
			result.put("msg", "未登录");
			return result;
		}
		if(tdUserAddress.getUid() != null && tdUserAddress.getUid() != tdUser.getUid())
		{
			result.put("msg", "参数错误");
			return result;
		}
		tdUserAddress.setUid(tdUser.getUid());
		if(tdUserAddress != null && tdUserAddress.getIsDefault() != null && tdUserAddress.getIsDefault() == true)
		{
			tdUserAddressService.setIsDefaultFalse(tdUser.getUid());
		}
		tdUserAddressService.save(tdUserAddress);
		Object rediret = request.getSession().getAttribute("redirect");
		if(null!=rediret){
			Integer diret = (Integer) rediret;
			if(diret==1){//购物车下单
				result.put("redirct", "/shoppingcart/confirmorder");
			}else if(diret==2){//立即购买下单
				result.put("redirct", "/shoppingcart/buynow");
			}else{
				result.put("redirct", "");
			}
			request.getSession().removeAttribute("redirect");
		}else{
			result.put("redirct", "");
		}
		result.put("code", ConstantsUtils.RETURN_CODE_SUCCESS);
		result.put("msg", "成功");
		return result;
	}
	
	/**
	 * 地址的地区选择
	 * @param provinceId 省
	 * @param cityId 市
	 * @return
	 */
	@RequestMapping(value = "/shoppingAddressDistrict",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> shippingAddressDistrict(Integer provinceId,Integer cityId)
	{
		Map<String,Object> resMap = new HashMap<String,Object>();
		resMap.put("code",ConstantsUtils.RETURN_CODE_FAILURE);
		TdUser tdUser = this.getCurrentUser();
		if(tdUser == null)
		{
			resMap.put("msg","请先登录");
			return resMap;
		}
		if(provinceId == null && cityId == null)
		{
			List<TdDistrict> provinceList = tdDistrictService.getDistrictByUpid(0);
			resMap.put("code",ConstantsUtils.RETURN_CODE_SUCCESS);
			resMap.put("provinceList", provinceList);
			return resMap;
		}
		if (provinceId != null && provinceId != -1 && cityId == null)
		{
			List<TdDistrict> cityList = tdDistrictService.getDistrictByUpid(provinceId);
			// 是否是直辖市
			if (tdDistrictService.findOne(provinceId) != null && tdDistrictService.isCentralCity(tdDistrictService.findOne(provinceId).getName()))
			{
				resMap.put("isCentralCity", true);
			}
			else
			{
				resMap.put("isCentralCity", false);
			}
			resMap.put("cityList", cityList);
			resMap.put("code",ConstantsUtils.RETURN_CODE_SUCCESS);
			return resMap;
		}
		if (cityId != null && cityId != -1) {
			List<TdDistrict> areaList = tdDistrictService.getDistrictByUpid(cityId);
			// 若用户在选择了与市不匹配的省时，可视为只选择了省
			if (tdDistrictService.findOne(cityId) != null && provinceId != null && provinceId != -1
					&& tdDistrictService.findOne(cityId).getUpid().equals(provinceId)) {
				resMap.put("districtList", areaList);
				resMap.put("code",ConstantsUtils.RETURN_CODE_SUCCESS);
				return resMap;
			} else {
				cityId = -1;
			}
		}
		
		resMap.put("provinceId", provinceId);
		resMap.put("cityId", cityId);
		resMap.put("code",ConstantsUtils.RETURN_CODE_SUCCESS);
		return resMap;
	}
	
	/**
	 * 用户地址删除
	 * @param addressId
	 * @return
	 */
	@RequestMapping(value="/shoppingAddressDelete",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> shoppingAddressDelete(Integer addressId)
	{
		Map<String,Object> resMap = new HashMap<String,Object>();
		resMap.put("code",ConstantsUtils.RETURN_CODE_FAILURE);
		TdUser tdUser = this.getCurrentUser();
		if(tdUser == null)
		{
			resMap.put("msg","请先登录");
			return resMap;
		}
		TdUserAddress userAddress = tdUserAddressService.findOne(addressId);
		if(addressId == null || userAddress == null)
		{
			resMap.put("msg","参数错误 -1");
			return resMap;
		}
		if(userAddress.getUid() != tdUser.getUid())
		{
			resMap.put("msg","参数错误 -2");
			return resMap;
		}
		tdUserAddressService.delete(addressId);
		resMap.put("code",ConstantsUtils.RETURN_CODE_SUCCESS);
		resMap.put("msg","成功");
		return resMap;
	}
	
	/**
	 * 用户钱包
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/account")
	public String userAccount(ModelMap map)
	{
		TdUser tdUser = this.getCurrentUser();
		map.addAttribute("menucode", "account");
		// 系统配置
		map.addAttribute("system", getSystem());
		TdUserAccount userAccount = tdUserAccountService.findOne(tdUser.getUid());
		if(userAccount == null)
		{
			userAccount = new TdUserAccount();
			userAccount.setUid(tdUser.getUid());
			userAccount.setAmount(new BigDecimal(0));
			userAccount.setStatus(TdUserAccount.ACCOUNT_STATUS_ACTIVE);
			userAccount.setUpdateBy(0);
			tdUserAccountService.insert(userAccount);
		}
		map.addAttribute("account",userAccount);
		return "/client/user/account";
	}
	/**
	 * 钱包流水记录
	 * @param sc
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/searchaccount")
	public String searchaccountlog(TdUserAccountLogSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currUser = this.getCurrentUser();
		sc.setUid(currUser.getUid());
		List<TdUserAccountLog> logList = tdUserAccountLogService.findBySearchCriteria(sc);
	    modelMap.addAttribute("logList", logList) ;
	    modelMap.addAttribute("sc", sc) ;
		return "/client/user/account_listbody";
	}
	/**
	 * 用户提现
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/withdraw")
	public String withdraw(HttpServletRequest request, HttpServletResponse response, ModelMap map)
	{
		TdUser tdUser = this.getCurrentUser();
		map.addAttribute("menucode", "account");
		// 系统配置
		map.addAttribute("system", getSystem());
		TdUserAccount userAccount = tdUserAccountService.findOne(tdUser.getUid());
		if(userAccount == null)
		{
			map.addAttribute("errmsg", "数据错误，请重新操作！");
			return "/client/error";
		}
		Integer withdrawfee = configUtil.getWithDrawFee();
		BigDecimal withdrawfeemin = configUtil.getWithDrawFeeMin();
		String feetip = "按提现金额的"+withdrawfee+"‰收取，最低收取"+withdrawfeemin+"元";
		map.addAttribute("feetip", feetip);
		map.addAttribute("account",userAccount);
		return "/client/user/withdraw";
	}
	/**
	 * 用户提现
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/dowithdraw", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> dowithdraw(WithDrawVO withDraw, HttpServletRequest request, HttpServletResponse response, ModelMap map)
	{
		Map<String,String> res = new HashMap<String,String>();
		res.put("code", "0");
		res.put("msg", "提现操作失败！");
		TdUser tdUser = this.getCurrentUser();
		Date now = new Date();
		// 系统配置
		TdUserAccount userAccount = tdUserAccountService.findOne(tdUser.getUid());
		if(userAccount == null)
		{
			res.put("msg", "提现操作失败：数据错误，请重新操作！");
			return res;
		}
		if(userAccount.getAmount() == null || userAccount.getAmount().compareTo(withDraw.getAmount())<0)
		{
			res.put("msg", "提现操作失败：账户金额不足！");
			return res;
		}
		//提现手续费计算
		Integer withdrawfee = configUtil.getWithDrawFee();
		BigDecimal withdrawfeemin = configUtil.getWithDrawFeeMin();
		BigDecimal amount = withDraw.getAmount();
		BigDecimal fee = amount.multiply(new BigDecimal(withdrawfee)).divide(new BigDecimal(1000));
		if(fee.compareTo(withdrawfeemin)<0){//应收手续费小于最低手续费，以最低手续费为准
			fee = withdrawfeemin;
		}
		if(fee.compareTo(amount)>=0){//手续费大于等于提现金额时，通知提现失败
			res.put("msg", "提现操作失败：提现金额过小，不划算！");
			return res;
		}
		withDraw.setAmount(amount.subtract(fee));
		//微信红包对接
		if(withDraw.getType().equals(1)){
			String rootPath = request.getServletContext().getRealPath("/");
			withDraw.setOpenId(tdUser.getJointId());
			withDraw.setClientIp(request.getLocalAddr());
			withDraw.setCapath(rootPath+ConstantsUtils.WECHAT_CHARGE_CACERT);
			OperResult result = WeChatRedPackUtils.sendRedPack(withDraw);
			if(result.isFlag()){
				userAccount.setUpdateBy(1);
				userAccount.setUpdateTime(now);
				TdUserAccountLog alog = new TdUserAccountLog();
				alog.setPreamount(userAccount.getAmount());
				alog.setUid(userAccount.getUid());
				alog.setUpamount(BigDecimal.ZERO.subtract(amount));
				alog.setType(TdUserAccountLog.USERACCOUNTLOG_TYPE_WITHDRAWALS);
		    	alog.setCreateTime(now);
		    	alog.setNote("用户提现，提现金额："+amount+" 元，其中包含手续费："+fee+" 元");
				tdUserAccountService.addAmount(userAccount, alog);
				res.put("code", "1");
				res.put("msg", "微信红包已经成功发送，请及时查收。");
			}else{
				res.put("msg", "微信红包发送失败："+result.getFailMsg());
			}
		}
		return res;
	}
	/**
	 * 用户收益
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/profit")
	public String userprofit(HttpServletRequest request, HttpServletResponse response, ModelMap map)
	{
		map.addAttribute("menucode", "profit");
		// 系统配置
		map.addAttribute("system", getSystem());
		return "/client/user/profit";
	}
	/**
	 * 用户收益记录
	 * @param sc
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/searchprofit")
	public String searchprofit(TdUserAccountLogSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currUser = this.getCurrentUser();
		sc.setUid(currUser.getUid());
		sc.setType(TdUserAccountLog.USERACCOUNTLOG_TYPE_PROFIT_INCOME);
		List<TdUserAccountLog> logList = tdUserAccountLogService.findBySearchCriteria(sc);
		modelMap.addAttribute("logList", logList) ;
		modelMap.addAttribute("sc", sc) ;
		return "/client/user/profit_listbody";
	}
		
	/*
	 * 实体店消息审核
	 */
	@RequestMapping(value="/verifyExperienceStoreApply", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> verifyExperienceStoreApply(TdExperienceStore estore, Byte status, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		estore.setStatus(status);
		int affectedNum = tdUserService.saveVerifyExperienceStoreApply(estore, status);
		if(affectedNum == 1){
			res.put("code", "1");
			res.put("msg", "审核成功！");
		}else{
			res.put("code", "0");
			res.put("msg", "审核失败！");
		}
		return res;
	}
	
	/*
	 * 推荐人
	 */
	@RequestMapping("/recommendPeople")
	public String recommendPeople(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currentUser = this.getCurrentUser();
		TdUser upOneRecommendPerson;
		TdUser upTwoRecommendPerson;
		TdUser upThreeRecommendPerson;
		if(currentUser.getUparentId() != null){
			upOneRecommendPerson = tdUserService.findOne(currentUser.getUparentId());
			modelMap.addAttribute("upOneRecommendPerson", upOneRecommendPerson);
			if(upOneRecommendPerson!= null && upOneRecommendPerson.getUparentId() != null){
				upTwoRecommendPerson = tdUserService.findOne(upOneRecommendPerson.getUparentId());
				modelMap.addAttribute("upTwoRecommendPerson", upTwoRecommendPerson);
				if(upTwoRecommendPerson != null && upTwoRecommendPerson.getUparentId() != null){
					upThreeRecommendPerson = tdUserService.findOne(upTwoRecommendPerson.getUparentId());
					modelMap.addAttribute("upThreeRecommendPerson", upThreeRecommendPerson);
				}
			}
		}
		/*TdUser parent = tdUserService.findParentTreeByUid(currentUser.getUid());
		modelMap.addAttribute("parent", parent);*/
		modelMap.addAttribute("menucode", "recommend");
		// 系统配置
		modelMap.addAttribute("system", getSystem());
		return "/client/user/recommendPeople";		
	}
	
	/*
	 * 商品管理
	 */
	@RequestMapping("/productmanage")
	public String productManage(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currUser = this.getCurrentUser();
		modelMap.addAttribute("customer", currUser);
		TdAgent agent = tdAgentService.findByUid(currUser.getUid());
		if(null!=agent){
			modelMap.addAttribute("isAgent", true);
		}
		modelMap.addAttribute("system", getSystem());
		return "/client/user/productManage";	
	}
	
	/*
	 * 查看我的商品
	 */
	@RequestMapping("/myproduct")
	public String lookMyProduct(Integer op, Integer type, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.addAttribute("system", getSystem());
		modelMap.addAttribute("op", op);
		modelMap.addAttribute("type", type);		
		return "/client/user/myProduct";	
	}
	
	/*
	 * 搜索我的商品
	 */
	@RequestMapping("/searchmyproduct")
	public String searchMyProduct(TdProductCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currentUser = this.getCurrentUser();
		sc.setUid(currentUser.getUid());
		List<TdProduct> productList = tdProductService.findBySearchCriteria(sc);
		modelMap.addAttribute("productList", productList);			
		modelMap.addAttribute("sc", sc);
		return "/client/user/myProductListbody";	
	}
	
	/*
	 * 查看供应商商品
	 */
	@RequestMapping("/supplierproduct")
	public String lookSupplierProduct(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.addAttribute("system", getSystem());
		return "/client/user/supplierProduct";	
	}
	
	/**
	 * 搜索代理商商品(单类代理才有，全国代理可以调价，上下架)
	 * @param sc
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/searchsupplierproduct")
	public String searchsupplierproduct(TdProductCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currentUser = this.getCurrentUser();
		TdAgent agent = tdAgentService.findByUid(currentUser.getUid());
		sc.setTypeId(agent.getProductTypeId());
		sc.setKind(ConstantsUtils.PRODUCT_KIND_COMMON);
		sc.setStatus(Byte.valueOf("1"));
		List<TdProduct> productList = tdProductService.findBySearchCriteria(sc);
		modelMap.addAttribute("productList", productList);		
		modelMap.addAttribute("sc", sc);		
		modelMap.addAttribute("agent", agent);		
		return "/client/user/supplierProductListBody";	
	}
	/**
	 * 全国代理修改价格
	 * @param id
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping("/editproductprice")
	public String editproductprice(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		TdUser currentUser = this.getCurrentUser();
		TdAgent agent = tdAgentService.findByUid(currentUser.getUid());
		if(null==agent || !agent.getLevel().equals(1)){//全国单代才有权限修改价格
			map.put("errmsg", "您没有权限进行商品价格修改！");
			return "/client/error";
		}
		if(null != id && id != 0)
		{
			// 商品主要信息
			TdProduct product = tdProductService.findOne(id);
			if(null==product || !product.getTypeId().equals(agent.getProductTypeId())){
				map.put("errmsg", "商品未找到！");
				return "/client/error";
			}
			map.addAttribute("tdProduct", product);
			
			// 商品对应的货品
			List<TdProductSku> productSkuList = tdProductSkuService.findByProductId(id);
			map.addAttribute("productSkuList", productSkuList);
			
		}else{
			map.put("errmsg", "商品未找到！");
			return "/client/error";
		}
		return "/client/user/productpriceform";	
	}
	
	/*
	 * 商品保存
	 */
	@RequestMapping(value="/saveproductprice", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> saveproductprice(TdProduct product, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>();
		TdUser currentUser = this.getCurrentUser();
		try{
			if(null==product.getId()){
				res.put("code", "0");
				res.put("msg", "商品价格保存失败:商品不能为空!");
				return res;
			}
			if(null==product.getSkuList()||product.getSkuList().size()<1){
				res.put("code", "0");
				res.put("msg", "商品保存失败:商品规格不能为空!");
				return res;
			}
			// 商品
			Date now = new Date();
			TdUser user = getCurrentUser();
			if(null != user)
			{
//				tdProduct.setBrandId(0);
				product.setUpdateBy(user.getUid());
			}
			product.setUpdateTime(now);
			
			
			//设置库存，价格
			BigDecimal lowPrice = BigDecimal.ZERO;
			int i=0;
			for(TdProductSku ps : product.getSkuList()){
				if(null!=ps.getId()&&null!=ps.getSalesPrice()){
					if(i==0){
						lowPrice = ps.getSalesPrice();
					}else if(lowPrice.compareTo(ps.getSalesPrice())>0){
						lowPrice = ps.getSalesPrice();
					}
					i++;
				}
			}
			product.setPrice(lowPrice);	
			
			//设置其它属性为null,不更新
			product.setTypeId(null);
			product.setSpecification(null);
			product.setDefaultSkuId(null);
			product.setOnshelf(null);
			product.setStatus(null);
			product.setNewRecommend(null);
			product.setFineRecommend(null);
			product.setHotRecommend(null);
			product.setTypeRecommend(null);
			product.setSort(null);			
			tdProductService.save(product);
			// 货品	atrributeArray格式：规格1=gv1,规格2=gv21	 保存规格格式：{"颜色":"红色","尺码":"38"}
			for(TdProductSku ps : product.getSkuList()){
				if(null!=ps.getId()&&null!=ps.getSalesPrice()){
					TdProductSku sku = new TdProductSku();
					sku.setId(ps.getId());
					sku.setSalesPrice(ps.getSalesPrice());
					sku.setUpdateBy(currentUser.getUid());
					sku.setUpdateTime(now);
					tdProductSkuService.save(sku);
				}
			}
			
		}catch(Exception e){
			logger.error("商品价格保存失败。");
			e.printStackTrace();
			res.put("code", "0");
			res.put("msg", "商品价格保存失败:"+e.getMessage());
			return res;
		}
		res.put("code", "1");
		res.put("msg", "商品价格保存成功。");
		
		return res;
	}
	
	/*
	 * 商品批量操作
	 */
	@RequestMapping(value="/batchoperproducts", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> batchoperproducts(String ids, Integer type, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>();
		TdUser currentUser = this.getCurrentUser();
		try{
			if(type==11){//批量删除
				if(StringUtils.isNotEmpty(ids)){
					String[] pids = ids.split(",");
					if(pids.length>0){
						for(String id : pids){
							int iid = Integer.valueOf(id);
							TdProduct product = tdProductService.findOne(iid);
							if(null!=product && product.getUid().equals(currentUser.getUid())){
								tdProductService.deleteByPrimaryKey(iid);								
							}
						}
					}
				}
				res.put("code", "1");
				return res;				
			}else{//上下架操作
				if(null!=type && type>0 && type<3 && StringUtils.isNotEmpty(ids)){
					int count = tdProductService.batchOperProducts(type,ids);
					if(count>0){
						res.put("code", "1");
						return res;
					}else{
						res.put("code", "0");
						res.put("msg", "操作失败：更新失败！");
						return res;
					}
					
				}else{
					res.put("code", "0");
					res.put("msg", "操作失败：操作非法！");
					return res;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			res.put("code", "0");
			res.put("msg", "操作失败："+e.getMessage());
		}
		return res;
	}
	
	/*
	 * 参加竞选
	 */
	@RequestMapping("/joinElection")
	public String joinElection(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currentUser = this.getCurrentUser();
		modelMap.addAttribute("currentUser", currentUser);
		return "/client/user/electionMaterial";	
	}
	
	
	/*
	 * 刷新活动描述
	 */
	@RequestMapping(value="/flushDescription", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> flushDescription(Integer selectedDistrictId,  HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		Map<String,String> res = new HashMap<String,String>();
		TdCampaignSearchCriteria sc = new TdCampaignSearchCriteria();
		sc.setFlag(false);
		sc.setRegionId(selectedDistrictId);
		List<TdCampaign> campaignList = tdCampaignService.findBySearchCriteria(sc);
		if(campaignList == null || campaignList.size() != 1){
			res.put("code", "0");
			res.put("msg", "服务器异常，地区活动数据错误。");
			return res;
		}else{
			TdCampaign  campaign =  tdCampaignService.findOne(campaignList.get(0).getId());
			res.put("code", "1");
			res.put("note", campaign.getNote());
			return res;
		}
	}
	
	/*
	 * 商品上下架
	 */
	@RequestMapping(value="/goOnshelf", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> goOnshelf(Integer id, Boolean onshelf, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>();
		try{
			TdProduct product = tdProductService.findOne(id);
			product.setOnshelf(onshelf);
			tdProductService.save(product);
			res.put("code", "1");
			if(onshelf){
				res.put("msg", "上架成功。");
			}else{
				res.put("msg", "下架成功。");
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("上下架失败");
			res.put("code", "0");
			if(onshelf){
				res.put("msg", "上架失败。");
			}else{
				res.put("msg", "下架失败。");
			}
		}
		return res;
	}
	
	/*
	 * 保存会员竞选
	 */
	@RequestMapping(value="/saveElection", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> saveElection(TdUserCampaign userCampaign, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>();
		TdUser currentUser = this.getCurrentUser();
		try{
			userCampaign.setUid(currentUser.getUid());
			TdCampaignSearchCriteria sc = new TdCampaignSearchCriteria();
			sc.setFlag(false);
			sc.setRegionId(userCampaign.getRegionId());
			List<TdCampaign> tdCampaignList = tdCampaignService.findBySearchCriteria(sc);
			TdCampaign theCampaign = tdCampaignList.get(0);
			userCampaign.setCid(theCampaign.getId());
			userCampaign.setCreateTime(new Date());
			tdUserCampaignService.save(userCampaign);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("竞选保存失败。");
			res.put("msg", "竞选提交失败。");
			return res;
		}
		res.put("msg", "竞选提交成功。");
		return res;
	}
	
	
	/*
	 * 是否已经参加过竞选
	 */
	@RequestMapping(value="/hasJoinedCampaing", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> hasJoinedCampaing(HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>();
		TdUser currentUser = this.getCurrentUser();
		TdUserCampaignCriteria sc = new TdUserCampaignCriteria();
		sc.setFlag(false);
		sc.setUid(currentUser.getUid());
		List<TdUserCampaign> userCampaignList = tdUserCampaignService.findBySearchCriteria(sc);
		if(userCampaignList == null || userCampaignList.size() == 0){
			res.put("code", "0"); //未参加
		}else if(userCampaignList.size() == 1){
			res.put("code", "1"); //已参加
			res.put("msg", "竞选进行中，您目前的排名是：" + userCampaignList.get(0).getLevel());
		}else{
			res.put("code", "1"); //数据出错
			res.put("msg", "服务器会员活动数据出错，原因：不唯一");
		}
		return res;
	}
	
	/*
	 * 指定地区是否有活动
	 */
	@RequestMapping(value="/hasElection", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> hasElection(Integer selectedDistrictId, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>();
		try{
			TdCampaignSearchCriteria sc = new TdCampaignSearchCriteria();
			sc.setFlag(false);
			sc.setRegionId(selectedDistrictId);
			List<TdCampaign> tdCampaignList = tdCampaignService.findBySearchCriteria(sc);
			if(tdCampaignList == null || tdCampaignList.size() == 0){
				res.put("msg", "该地区暂无活动。");
				res.put("code", "0");
				return res;
			}else if(tdCampaignList.size() > 1){
				res.put("msg", "系统数据出错：该地区不止一个活动，请通知管理员。");
				res.put("code", "0");
				return res;
			}else{
				res.put("code", "1");
			}
		}catch(Exception e){
		}
		return res;
	}
	
	/*
	 * 商品保存
	 */
	@RequestMapping(value="/saveproduct", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> saveProduct(TdProduct product,  Integer[] attId, String detail, String  packDetail,
			String afterSale, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>();
		TdUser currentUser = this.getCurrentUser();
		try{
			// 商品
			Date now = new Date();
			if(null != currentUser)
			{
//				tdProduct.setBrandId(0);
				product.setUpdateBy(currentUser.getUid());
			}
			product.setUpdateTime(now);
			
			boolean isUpdate = false;
			if(product.getId() != null){
				isUpdate = true;
			}
			if(isUpdate){
				tdProductSkuService.deleteByProductId(product.getId());
			}else{
				product.setCreateTime(now);
				product.setUid(currentUser.getUid());
				product.setOnshelf(false);
				product.setFineRecommend(0);
				product.setHotRecommend(0);
				product.setNewRecommend(0);
				product.setTypeRecommend(0);
				product.setDefaultSkuId(0);
				product.setSpecification(true);
				product.setSort(0);
				product.setStatus(Byte.valueOf("2"));
				//保存商品类型Tree
				if(null!=product.getTypeId()&&product.getTypeId()>0){
					TdProductType type = tdProductTypeService.findOneWithParents(product.getTypeId());
					String typeIdTree = type.getParentIdTree();
					product.setTypeIdTree(typeIdTree);
				}
			}
			//设置库存，价格
			Integer totalStock = 0;
			BigDecimal lowPrice = BigDecimal.ZERO;
			int i=0;
			if(null!=product.getSkuList()&&product.getSkuList().size()>0){//开启规格
				List<SkuSpecialVO> specialList = new ArrayList<SkuSpecialVO>();
				for(TdProductSku ps : product.getSkuList()){
					if(null!=ps.getStock()&&null!=ps.getSalesPrice()){
						totalStock = totalStock + ps.getStock();
						if(i==0){
							lowPrice = ps.getSalesPrice();
						}else if(lowPrice.compareTo(ps.getSalesPrice())>0){
							lowPrice = ps.getSalesPrice();
						}
						i++;
						//计算规格及属性值
						ps.setSpecifications(ps.getSpecialJsonBySpecialKey(ps.getSpecifications()));
						specialList.addAll(ps.getSpecialList());
					}
				}
				//获取规格选择项
				String specifiations = tdProductService.getSpecificationatsJsonString(specialList);
				product.setSpecificationats(specifiations);
				product.setPrice(lowPrice);
				product.setQuantum(totalStock);
				product.setSpecification(true);
			}else{//关闭规格
				if(null==product.getPrice()||null==product.getHighPrice()||null==product.getLowPrice()||null==product.getSupplierPrice()){
					res.put("code", "0");
					res.put("msg", "商品保存失败:请正确设置商品价格！");
					return res;
				}
				product.setSpecification(false);
				List<TdProductSku> skuList = new ArrayList<TdProductSku>();
				TdProductSku sku = new TdProductSku();
				sku.setHighPrice(product.getHighPrice());
				sku.setLowPrice(product.getLowPrice());
				sku.setSalesPrice(product.getPrice());
				sku.setSkuCode(product.getSkuCode());
				sku.setMarketPrice(product.getMarketPrice());
				sku.setSupplierPrice(product.getSupplierPrice());
				sku.setStock(product.getQuantum());
				sku.setSpecifications("");
				skuList.add(sku);
				product.setSkuList(skuList);
			}
			
			tdProductService.save(product);
			// 货品	atrributeArray格式：规格1=gv1,规格2=gv21	 保存规格格式：{"颜色":"红色","尺码":"38"}
			for(TdProductSku ps : product.getSkuList()){
				if(null!=ps.getStock()&&null!=ps.getSalesPrice()){
					ps.setProductId(product.getId());
					ps.setStatus(Byte.valueOf("1"));
					ps.setUpdateBy(currentUser.getUid());
					ps.setUpdateTime(now);
					//ps.setSpecifications(ps.getSpecialJsonBySpecialKey(ps.getSpecifications()));
					tdProductSkuService.save(ps);
				}
			}
			// 修改展示图片
			if(null != attId)
			{
				for (Integer attaId : attId) {
					TdProductAttachment attachment = tdProductAttachmentService.findOne(attaId);
					attachment.setProductId(product.getId());
					tdProductAttachmentService.save(attachment);
				}
			}
			// 保存其他信息
			TdProductDescriptionCriteria sc = new TdProductDescriptionCriteria();
			sc.setProductId(product.getId());
			sc.setFlag(false);
			
			// 图文详情
			sc.setType(1);
			TdProductDescription imgDetail = tdProductDescriptionService.findByProductId(sc);
			if(null == imgDetail)
			{
				imgDetail = new TdProductDescription();
				imgDetail.setProductId(product.getId());
				imgDetail.setType((byte) 1);
			}
			imgDetail.setDescription(detail);
			imgDetail.setUpdateBy(currentUser.getUid());
			imgDetail.setUpdateTime(now);
			
			// 包装配送
			sc.setType(2);
			TdProductDescription packdetail = tdProductDescriptionService.findByProductId(sc);
			if(null == packdetail)
			{
				packdetail = new TdProductDescription();
				packdetail.setProductId(product.getId());
				packdetail.setType((byte) 2);
			}
			packdetail.setDescription(packDetail);
			packdetail.setUpdateBy(currentUser.getUid());
			packdetail.setUpdateTime(now);
			
			// 售后说明
			sc.setType(3);
			TdProductDescription afterSaleDetail = tdProductDescriptionService.findByProductId(sc);
			
			if(null == afterSaleDetail)
			{
				afterSaleDetail = new TdProductDescription();
				afterSaleDetail.setProductId(product.getId());
				afterSaleDetail.setType((byte) 3);
			}
			afterSaleDetail.setDescription(afterSale);
			afterSaleDetail.setUpdateBy(currentUser.getUid());
			afterSaleDetail.setUpdateTime(now);
			
			tdProductDescriptionService.save(imgDetail);
			tdProductDescriptionService.save(packdetail);
			tdProductDescriptionService.save(afterSaleDetail);
			
			// 新增商品时添加统计表
			TdProductStat stat = tdProductStatService.findOne(product.getId());
			if(null == stat)
			{
				stat = new TdProductStat();
				stat.setBuyCount(0);
				stat.setBuyTimes(0);
				stat.setNegativeRate(0);
				stat.setNeutralRate(0);
				stat.setPositiveRate(0);
				stat.setProductId(product.getId());
				stat.setReviewCount(0);
				stat.setReviewScore(new BigDecimal(0));
				stat.setShowreviewCount(0);
				stat.setViewCount(0);
				tdProductStatService.Insert(stat);
			}
			
		}catch(Exception e){
			logger.error("商品保存失败。");
			e.printStackTrace();
			res.put("code", "0");
			res.put("msg", "商品保存失败:"+e.getMessage());
			return res;
		}
		res.put("code", "1");
		res.put("msg", "商品保存成功。");
		
		return res;
	}
	
	/*
	 * 下级会员
	 */
	@RequestMapping("/downUserList")
	public String downThreeUserList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.addAttribute("menucode", "chirldren");
		// 系统配置
		modelMap.addAttribute("system", getSystem());
		return "/client/user/downUserList";	
	}
	
	@RequestMapping(value="/getDownUsers")
	public String getDownUsers(TdUserSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
		TdUser currentUser = this.getCurrentUser();
		String parentIdsStr = "["+ currentUser.getUid() +"]"; // 下一二三级的id
		String oneIdsStr = ""; // 下一级的id
		String twoIdsStr = ""; // 下二级的id
		String threeIdsStr = "";// 下三级的id
		sc.setFlag(false);
		sc.setParentIdsStr(parentIdsStr);
		List<TdUser> downOneUserList = tdUserService.findBySearchCriteria(sc);
		//查询类别clevelType
		if(null==sc.getClevelType()){
			sc.setClevelType(0);
		}
		//查询条件id
		StringBuffer queryParentIds = new StringBuffer();
		if(sc.getClevelType().equals(0)||sc.getClevelType().equals(1)){
			queryParentIds.append("["+ currentUser.getUid() +"]");
		}
		if(downOneUserList != null){
			for(TdUser user : downOneUserList){
				if(sc.getClevelType().equals(0)||sc.getClevelType().equals(2)){
					queryParentIds.append("["+ user.getUid() +"]");
				}
				oneIdsStr += "["+ user.getUid() +"]";
			}
		}
		if(StringUtils.isNotBlank(oneIdsStr)){
			sc.setParentIdsStr(oneIdsStr);
			List<TdUser> downTwoUserList = tdUserService.findBySearchCriteria(sc);
			if(downTwoUserList != null){
				for(TdUser user : downTwoUserList){
					if(sc.getClevelType().equals(0)||sc.getClevelType().equals(3)){
						queryParentIds.append("["+ user.getUid() +"]");
					}
					twoIdsStr += "["+ user.getUid() +"]";
				}
			}			
		}
		if(sc.getClevelType().equals(0)||sc.getClevelType().equals(3)){
			if(StringUtils.isNotBlank(twoIdsStr)){
				sc.setParentIdsStr(twoIdsStr);
				List<TdUser> downThreeUserList = tdUserService.findBySearchCriteria(sc);
				if(downThreeUserList != null){
					for(TdUser user : downThreeUserList){
						threeIdsStr += "["+ user.getUid() +"]";
					}
				}			
			}
		}
		parentIdsStr = queryParentIds.toString();
		sc.setParentIdsStr(parentIdsStr);
		sc.setFlag(true);
		List<TdUser> downUserList = new ArrayList<TdUser>();
		if(StringUtils.isNotBlank(parentIdsStr)){
			downUserList = tdUserService.findBySearchCriteria(sc); // 下一二三级分页会员			
		}
		for(TdUser user : downUserList){
			if(oneIdsStr.contains(user.getUid().toString())){
				user.setClevel(1);
			}else if(twoIdsStr.contains(user.getUid().toString())){
				user.setClevel(2);
			}else if(threeIdsStr.contains(user.getUid().toString())){
				user.setClevel(3);
			}
		}
		modelMap.addAttribute("userList", downUserList);
		modelMap.addAttribute("sc", sc);
		return "/client/user/downUserListBody";
	}
	
	/*
	 * 供应商资质认证
	 */
	@RequestMapping("/supplierApply")
	public String supplierApply(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currentUser = this.getCurrentUser();
		TdUserSupplierSearchCriteria sc = new TdUserSupplierSearchCriteria();
		sc.setFlag(false);
		sc.setUserId(currentUser.getUid());
		List<TdUserSupplier> userSupplierList = tdUserSupplierService.findBySearchCriteria(sc);
		if(userSupplierList != null && userSupplierList.size() > 1){
			logger.error("供应商资质认证数据出错：供应商资质认证数据不唯一");
		}
		if(userSupplierList != null && userSupplierList.size() > 0){
			modelMap.addAttribute("userSupplier", userSupplierList.get(0));
			String[] imgList = userSupplierList.get(0).getImages().split(":");
			modelMap.addAttribute("imgList", imgList);
		}
		return "/client/user/supplierApply";	
	}
	
	/*
	 * 推广
	 */
	@RequestMapping("/mySpread")
	public String mySpread(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException, JSONException {
		TdUser currentUser = this.getCurrentUser();
		modelMap.addAttribute("currentUser", currentUser);
		/*String spreadUrl = "http://www.cqupt.edu.cn";
		String imgName = UUID.randomUUID().toString() + ".png";
		String spreadImgPath = request.getServletContext().getRealPath("/") + "static/imgs/spread" + imgName;
		TwoDimensionCode tdc = new TwoDimensionCode();
		tdc.encoderQRCode(spreadUrl, spreadImgPath, "png");
		modelMap.addAttribute("spreadImg", "static/imgs/spread" + imgName);*/
		modelMap.addAttribute("menucode", "spreed");
		// 系统配置
		modelMap.addAttribute("system", getSystem());
		return "/client/user/mySpread";
	}
	
	@RequestMapping(value = "/mySpread/qrcode")
	public void spreadQRcode(String title, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, JSONException
	{
		TdUser currentUser = this.getCurrentUser();
//		tdUserQRcodeTools.QRcodeByUidAndResponse(currentUser.getUid(), response);
//		TdUserQRcodeTools QRcodeTools = new TdUserQRcodeTools();
		tdUserQRcodeTools.QRcodeByUidAndResponse(currentUser.getUid(), title, request, response);
	}
	
	/*
	 * 保存供应商资质认证申请
	 */
	@RequestMapping("/saveUserSupplierApply")
	@ResponseBody
	public Map<String, String> saveUserSupplierApply(TdUserSupplier userSupplier, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		Map<String, String> res = new HashMap<>();
		try{
			TdUser currentUser = this.getCurrentUser();
			userSupplier.setUid(currentUser.getUid());
			userSupplier.setCreateTime(new Date());
			userSupplier.setUpdateTime(new Date());
			userSupplier.setUpdateBy(currentUser.getUid());
			String imgs[] = userSupplier.getImages().split(":");
			String images = "";
			for(int i = 0; i < imgs.length; i ++){
				imgs[i] = imgs[i].replaceFirst("/", "");
				imgs[i] = imgs[i].substring(imgs[i].indexOf("/"));
				images = images + imgs[i];
				if(i < imgs.length - 1){
					images = images + ":";
				}
			}
			userSupplier.setImages(images);
			if(userSupplier.getId() == null){
				tdUserSupplierService.save(userSupplier);				
			}else{
				tdUserSupplierService.save(userSupplier);
			}
			res.put("code", "1");
			res.put("msg", "资质上传成功！");
			return res;
		} catch(Exception e){
			res.put("code", "0");
			res.put("msg", "资质上传失败！");
			logger.error("供应商资质上传保存失败！");
			return res;
		}
	}
	
	/*
	 * 加载对应商品类别的规格
	 */
	@RequestMapping("/flushAttribute")
	public String flushAttribute(Integer typeId, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		
		List<TdProductTypeAttribute> typeAttributeList = tdProductTypeAttributeService.findByTypeId(typeId);
		List<TdProductAttribute> attributeList = new ArrayList<>();
		if(typeAttributeList != null){
			for(TdProductTypeAttribute ta : typeAttributeList){
				TdProductAttribute attribute = tdProductAttributeService.findOne(ta.getAttriId());
				// 设置attribute的option
				if(attribute != null){
					TdProductAttributeOptionCriteria sc = new TdProductAttributeOptionCriteria();
					sc.setFlag(false);
					sc.setAttriId(attribute.getAttriId());
					List<TdProductAttributeOption> aoList = tdProductAttributeOptionService.findBySearchCriteria(sc);
					attribute.setTdProductAttributeOptionList(aoList);
				}
				attributeList.add(attribute);
			}
		}
		modelMap.addAttribute("attributeList", attributeList);
		return "/client/user/productAttributeTemplate";	
	}
	
	/*
	 * 上传商品
	 */
	@RequestMapping("/editproduct")
	public String editProduct(Integer id, Integer ktype, HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		TdProductTypeCriteria tsc = new TdProductTypeCriteria();
		tsc.setStatus((byte) 1);
		List<TdProductType> productTypeList = tdProductTypeService.findAll(tsc);
		map.addAttribute("productTypeList", productTypeList);
		
		if(null != id && id != 0)
		{
			// 商品主要信息
			TdProduct product = tdProductService.findOne(id);
			map.addAttribute("tdProduct", product);
			
			// 商品图片
			map.addAttribute("imgList", tdProductAttachmentService.findByProductId(id));
			
			TdProductDescriptionCriteria sc = new TdProductDescriptionCriteria();
			sc.setProductId(id);
			sc.setFlag(false);
			// 图文详情
			sc.setType(1);
			map.addAttribute("detail", tdProductDescriptionService.findByProductId(sc));
			// 包装配送
			sc.setType(2);
			map.addAttribute("packDetail", tdProductDescriptionService.findByProductId(sc));
			// 售后
			sc.setType(3);
			map.addAttribute("afterSale", tdProductDescriptionService.findByProductId(sc));
			
			map.addAttribute("productStat", tdProductStatService.findOne(id));
			// 商品规格
			// 商品对应的货品
			List<TdProductSku> productSkuList = tdProductSkuService.findByProductId(id);
			map.addAttribute("productSkuList", productSkuList);
			//商品类型规格
			List<TdProductTypeAttribute> taList = tdProductTypeAttributeService.findByTypeIdWithOptions(product.getTypeId());
			if(taList.size()>0){
				//匹配货品库存状态
				tdProductService.matchSkuStockWithAttributeOption(productSkuList,taList);
				if(product.getSpecification()){//开启规格，合并自定义属性
					tdProductService.joinSelfAttributeOption(product,taList);
				}
			}
			map.addAttribute("taList", taList);
			if(!product.getSpecification() && null!=productSkuList && productSkuList.size()>0){//未开启规格
				TdProductSku sku = productSkuList.get(0);
				product.setSkuCode(sku.getSkuCode());
				product.setHighPrice(sku.getHighPrice());
				product.setLowPrice(sku.getLowPrice());
				product.setMarketPrice(sku.getMarketPrice());
				product.setSupplierPrice(sku.getSupplierPrice());
			}else{
				
			}
		}else{
			TdProduct product = new TdProduct();
			if(null!=ktype && ktype.equals(2)){
				product.setKind(Byte.valueOf("3"));
			}
			map.addAttribute("tdProduct", product);
		}
		
		//品牌数据
		TdBrandSearchCriteria bsc = new TdBrandSearchCriteria();
		bsc.setFlag(false);
		List<TdBrand> brandList = tdBrandService.findBySearchCriteria(bsc);
		map.addAttribute("brandList", brandList);
		return "/client/user/productform";	
	}
	
	@RequestMapping("/producttypeattributes")
	public String producttypeattributes(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		if(null != id && id != 0)
		{
			//商品类型规格
			List<TdProductTypeAttribute> taList = tdProductTypeAttributeService.findByTypeIdWithOptions(id);
			map.addAttribute("taList", taList);
		}
		return "/client/user/productTypeAttributes";	
	}
	
	/*
	 * 加载地区
	 */
	@RequestMapping("/getDistrictSelections")
	public String getDistrictSelections(Integer parentId, String nextLiId, Integer selectedId, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdDistrictSearchCriteria sc = new TdDistrictSearchCriteria();
		sc.setFlag(false);
		sc.setUpid(parentId);
		List<TdDistrict> districtSelections = tdDistrictService.findBySearchCriteria(sc);
		modelMap.addAttribute("districtSelections", districtSelections);
		modelMap.addAttribute("nextLiId", nextLiId);
		modelMap.addAttribute("selectedId", selectedId);
		// 此下拉框是否可以继续响应下一级下拉框
		TdDistrict selectedDistrict = tdDistrictService.findOne(parentId);
		if(selectedDistrict == null){
			// 首次进入
			modelMap.addAttribute("canGetNextDistrictSelections", true);
		}else{
			if(selectedDistrict.getLevel().equals(Byte.valueOf("1"))){
				if(isCentralCity(selectedDistrict.getName())){
					modelMap.addAttribute("canGetNextDistrictSelections", false);
				}else{
					modelMap.addAttribute("canGetNextDistrictSelections", true);
				}
			}else{
				modelMap.addAttribute("canGetNextDistrictSelections", false);
			}
		}
		
		return "/client/user/districtSelectTemplate";	
	}
	
	/**
	 * 
	 * @author Max
	 * 我的店铺
	 * 
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		
		TdUser user = this.getCurrentUser();
		if(null == user){
			return "redirect:/mobile/login";
		}
		
		// 轮播广告
		TdAdvertisementSearchCriteria sc = new TdAdvertisementSearchCriteria();
		sc.setCreateTime(new Date());
		sc.setEndTime(new Date());
		sc.setOrderBy("2");
		sc.setStatus((byte)1);
		sc.setRegionId(user.getUregionId());
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
		
		// 系统配置
		map.addAttribute("system", getSystem());
		
		// 股东竞选
		TdComplaintCriteria csc = new TdComplaintCriteria();
		sc.setStatus((byte)1);
		map.addAttribute("complaintList", tdComplaintService.findBySearchCriteria(csc));
		
		// 热销推荐
		TdProductCriteria psc = new TdProductCriteria();
		psc.setHotRecommend(1);
		psc.setKind((byte)1);
		psc.setOnshelf(true);
		psc.setUid(user.getUid());
		map.addAttribute("productList", tdProductService.findBySearchCriteria(psc));
		
	    return "/client/user/index";
	}
	
	/**
	 * 图形验证码是否正确
	 * @param vcode
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/verifyCode")
	@ResponseBody
	public Map<String, String> verifyCode(String vcode, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> res = new HashMap<>();
		String rand = (String)request.getSession().getAttribute("rand");
		vcode = vcode.toLowerCase();
		if(rand == null){
			res.put("code", "0");
			res.put("msg", "请重新获取图形验证码");
			return res;
		}
		if(rand.equals(vcode)){
			res.put("code", "1");
			request.getSession().removeAttribute("rand");
		}else{
			res.put("code", "0");
			res.put("msg", "图形验证码错误");
		}
		return res;
	}
	
	
	// 是否是直辖市
	private boolean isCentralCity(String cityName) {
		if(cityName.indexOf("北京")>-1 || cityName.indexOf("天津")>-1 || cityName.indexOf("上海")>-1 || cityName.indexOf("重庆")>-1){
			return true;
		}
		return false;
	}
}
