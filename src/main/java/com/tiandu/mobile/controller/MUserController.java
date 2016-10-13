package com.tiandu.mobile.controller;

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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import com.tiandu.common.utils.WeChatRedPackUtils;
import com.tiandu.common.utils.WebUtils;
import com.tiandu.complaint.search.TdComplaintCriteria;
import com.tiandu.complaint.service.TdComplaintService;
import com.tiandu.custom.entity.TdAgent;
import com.tiandu.custom.entity.TdBrancheCompany;
import com.tiandu.custom.entity.TdCampaign;
import com.tiandu.custom.entity.TdDrawapply;
import com.tiandu.custom.entity.TdExperienceStore;
import com.tiandu.custom.entity.TdMembership;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.entity.TdUserAccount;
import com.tiandu.custom.entity.TdUserAccountLog;
import com.tiandu.custom.entity.TdUserAddress;
import com.tiandu.custom.entity.TdUserCampaign;
import com.tiandu.custom.entity.TdUserMessage;
import com.tiandu.custom.entity.TdUserSupplier;
import com.tiandu.custom.search.TdCampaignSearchCriteria;
import com.tiandu.custom.search.TdDrawapplySearchCriteria;
import com.tiandu.custom.search.TdUserAccountLogSearchCriteria;
import com.tiandu.custom.search.TdUserAddressCriteria;
import com.tiandu.custom.search.TdUserCampaignCriteria;
import com.tiandu.custom.search.TdUserMessageSearchCriteria;
import com.tiandu.custom.search.TdUserSearchCriteria;
import com.tiandu.custom.search.TdUserSupplierSearchCriteria;
import com.tiandu.custom.service.TdAgentService;
import com.tiandu.custom.service.TdBrancheCompanyService;
import com.tiandu.custom.service.TdCampaignService;
import com.tiandu.custom.service.TdDrawapplyService;
import com.tiandu.custom.service.TdExperienceStoreService;
import com.tiandu.custom.service.TdMembershipService;
import com.tiandu.custom.service.TdUserAccountLogService;
import com.tiandu.custom.service.TdUserAccountService;
import com.tiandu.custom.service.TdUserAddressService;
import com.tiandu.custom.service.TdUserCampaignService;
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
import com.tiandu.product.entity.TdProduct;
import com.tiandu.product.entity.TdProductAttachment;
import com.tiandu.product.entity.TdProductAttribute;
import com.tiandu.product.entity.TdProductAttributeOption;
import com.tiandu.product.entity.TdProductDescription;
import com.tiandu.product.entity.TdProductSku;
import com.tiandu.product.entity.TdProductStat;
import com.tiandu.product.entity.TdProductType;
import com.tiandu.product.entity.TdProductTypeAttribute;
import com.tiandu.product.search.TdProductAttributeOptionCriteria;
import com.tiandu.product.search.TdProductCriteria;
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
@RequestMapping("/mobile/user")
public class MUserController extends BaseController {
	
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
	private TdBrancheCompanyService tdBrancheCompanyService;
	@Autowired
	private TdDrawapplyService tdDrawapplyService;
	@Autowired
	private ConfigUtil configUtil;
	
	// 个人中心
	@RequestMapping("/center")
	public String center(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currentUser = this.getCurrentUser();
		if(null==currentUser)
		{
			return "redirect:/mobile/login";
		}
		modelMap.addAttribute("currentUser", currentUser);
		TdMembership membership = tdMembershipService.findOne(currentUser.getMembershipId());
		modelMap.addAttribute("membership", membership);
		//判断是否是分公司
		TdBrancheCompany branch  = tdBrancheCompanyService.findByUid(currentUser.getUid());
		if(null!=branch && null!=branch.getId()){
			modelMap.addAttribute("isbranch", true);
		}
		// 系统配置
		modelMap.addAttribute("system", getSystem());
	    return "/mobile/user/center";
	}
	
	// 用户签到
	@RequestMapping(value="/sign", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> sign(Integer uid, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		Map<String, String> signBackData;
		try {
			signBackData = tdUserService.saveSign(uid);
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
	
	// 保存修改密码
	@RequestMapping(value="/savePassword", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> sign(String oldPassword, String valideCode, String newPassword, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>();
		String changePasswordValidCode = (String) request.getSession().getAttribute("changePasswordValidCode");
		if(changePasswordValidCode == null || !valideCode.equals(changePasswordValidCode)){
			res.put("info", "验证码错误！");
			res.put("status", "n");
			request.getSession().removeAttribute("changePasswordValidCode");
			return res;
		}
		
		TdUser currentUser = this.getCurrentUser();
		currentUser = tdUserService.findOne(currentUser.getUid());
		String md5OldPassword = WebUtils.generatePassword(currentUser.getUname(), oldPassword);
		if(!md5OldPassword.equals(currentUser.getUpassword())){
			res.put("info", "原始密码错误！");
			res.put("status", "n");
			return res;
		}
		Date now = new Date();
		currentUser.setUpdateBy(currentUser.getUid());
		currentUser.setUpdateTime(now);
		currentUser.setCreateTime(now);
		currentUser.setUpassword(newPassword);
    	tdUserService.saveUserPassword(currentUser);
    	res.put("info", "密码修改成功！");
		res.put("status", "y");
		request.getSession().removeAttribute("changePasswordValidCode");
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
			res.put("code", "1"); // 用于替换下两行
			res.put("msg", "发送验证码成功!");// 用于替换下两行
			//res.put("code", "0");
			//res.put("msg", "验证码获取失败!");
		}
		return res;
	}
	
	
	/*
	 * 我的购物车
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currUser = this.getCurrentUser();
		//获取购物车
		ShoppingcartVO shoppingcart  = getShoppingcart(currUser.getUid());
		modelMap.addAttribute("shoppingcart", shoppingcart) ;
	    return "/mobile/shoppingcart/list";
	}
	
	/*
	 * 消息列表
	 */
	@RequestMapping("/messageList")
	public String messageList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
	    return "/mobile/user/messageList";
	}
	/*
	 * 消息列表
	 */
	@RequestMapping("/searchmessages")
	public String searchmessages(TdUserMessageSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currentUser = this.getCurrentUser();
		sc.setUid(currentUser.getUid());
		List<TdUserMessage> messageList = tdUserMessageService.findBySearchCriteria(sc);
		modelMap.put("sc", sc);
		modelMap.put("messageList", messageList);
		return "/mobile/user/messageListBody";
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
		if(null==message || message.getUid()!=currentUser.getUid()){
			modelMap.addAttribute("errmsg", "数据错误，请重新操作！");
			return "/mobile/error";
		}
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
		return "/mobile/user/info";		
	}
	
	/*
	 * 修改密码
	 */
	@RequestMapping("/changePassword")
	public String changePassword(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/mobile/user/changePassword";		
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
		return "/mobile/user/changePhoneNum";		
	}
	
	/**
	 * 收货地址
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/shoppingAddress")
	public String shoppingAddress(HttpServletRequest request,ModelMap map,Integer addressId)
	{
		TdUser tdUser = this.getCurrentUser();
		if(tdUser == null)
		{
			return "redirect:/mobile/login";
		}
		if(addressId != null && addressId > 0)
		{
			tdUserAddressService.setIsDefaultFalse(tdUser.getUid());
			TdUserAddress userAddress = tdUserAddressService.findOne(addressId);
			userAddress.setIsDefault(true);
			tdUserAddressService.save(userAddress);
		}
		if(addressId != null && addressId < 0)
		{
			map.addAttribute("shopping",addressId);
			request.getSession().setAttribute("shopping", addressId);
		}
		Integer randomNo = (Integer)request.getSession().getAttribute("shopping");
		
		if(randomNo != null)
		{
			map.addAttribute("shopping",randomNo);
			if(randomNo > -100)
			{
				map.addAttribute("returnPath", "confirmorder");
			}
			else
			{
				map.addAttribute("returnPath", "buynow");
			}
		}
		// 系统配置
		map.addAttribute("system", getSystem());
		TdUserAddressCriteria sc = new TdUserAddressCriteria();
		sc.setUid(tdUser.getUid());
		List<TdUserAddress> userAddresses = tdUserAddressService.findBySearchCriteria(sc);
		map.addAttribute("address_list", userAddresses);
		return "/mobile/user/shoppingAddress";
	}
	
	/**
	 * 新增收货地址
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/shoppingAddressAdd")
	public String shoppingAddressAdd(Integer addressId,ModelMap map,HttpServletRequest request)
	{
		TdUser tdUser = this.getCurrentUser();
		if(tdUser == null)
		{
			return "redirect:/modile/login";
		}
		// 系统配置
		map.addAttribute("system", getSystem());
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
		}
		
		if(addressId != null && addressId < 0)
		{
			map.addAttribute("shopping",addressId);
			request.getSession().setAttribute("shopping", addressId);
		}
		
		return "/mobile/user/shoppingAddressAdd";
	}
	
	/**
	 * 地址保存并设置默认地址 
	 * @param tdUserAddress
	 * @return
	 */
	@RequestMapping(value = "/shoppingAddressSave" ,method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> shoppingAddressSave(TdUserAddress tdUserAddress)
	{
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("code", ConstantsUtils.RETURN_CODE_SUCCESS);
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
		resMap.put("status",ConstantsUtils.RETURN_CODE_FAILURE);
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
		resMap.put("status",ConstantsUtils.RETURN_CODE_SUCCESS);
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
		map.addAttribute("curruser",tdUser);
		return "/mobile/user/account";
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
		return "/mobile/user/account_listbody";
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
		// 系统配置
		map.addAttribute("system", getSystem());
		TdUserAccount userAccount = tdUserAccountService.findOne(tdUser.getUid());
		if(userAccount == null)
		{
			map.addAttribute("errmsg", "数据错误，请重新操作！");
			return "/mobile/error";
		}
		Integer withdrawfee = configUtil.getWithDrawFee();
		BigDecimal withdrawfeemin = configUtil.getWithDrawFeeMin();
		String feetip = "按提现金额的"+withdrawfee+"‰收取，最低收取"+withdrawfeemin+"元";
		map.addAttribute("feetip", feetip);
		map.addAttribute("account",userAccount);
		return "/mobile/user/withdraw";
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
	 * 用户大额提现申请
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/drawapply")
	public String drawapply(HttpServletRequest request, HttpServletResponse response, ModelMap map)
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
		Integer withdrawfee = configUtil.getDrawApplyFee();
		BigDecimal withdrawfeemin = configUtil.getDrawApplyFeeMin();
		String feetip = "按提现金额的"+withdrawfee+"‰收取，最低收取"+withdrawfeemin+"元";
		map.addAttribute("feetip", feetip);
		map.addAttribute("account",userAccount);
		return "/mobile/user/drawapply";
	}
	
	/**
	 * 用户提现
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/dodrawapply", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> dodrawapply(TdDrawapply drawApply, HttpServletRequest request, HttpServletResponse response, ModelMap map)
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
		if(userAccount.getAmount() == null || userAccount.getAmount().compareTo(drawApply.getAmount())<0)
		{
			res.put("msg", "提现操作失败：账户金额不足！");
			return res;
		}
		//提现手续费计算
		Integer withdrawfee = configUtil.getDrawApplyFee();
		BigDecimal withdrawfeemin = configUtil.getDrawApplyFeeMin();
		BigDecimal amount = drawApply.getAmount();
		BigDecimal fee = amount.multiply(new BigDecimal(withdrawfee)).divide(new BigDecimal(1000));
		if(fee.compareTo(withdrawfeemin)<0){//应收手续费小于最低手续费，以最低手续费为准
			fee = withdrawfeemin;
		}
		if(fee.compareTo(amount)>=0){//手续费大于等于提现金额时，通知提现失败
			res.put("msg", "提现操作失败：提现金额过小，不划算！");
			return res;
		}
		//提现减去账号金额
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
		//保存提现申请
		drawApply.setCreateTime(now);
		drawApply.setUid(tdUser.getUid());
		drawApply.setStatus(1);
		drawApply.setUpdateBy(tdUser.getUid());
		drawApply.setUpdateTime(now);
		drawApply.setFee(fee);
		tdDrawapplyService.save(drawApply);
		
		res.put("code", "1");
		res.put("msg", "提现申请提交成功，请等待工作人员处理.");
		return res;
	}
	
	/**
	 * 用户提现申请记录
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/drawapplylist")
	public String drawapplylist(HttpServletRequest request, HttpServletResponse response, ModelMap map)
	{
		TdUser tdUser = this.getCurrentUser();
		map.addAttribute("menucode", "drawapply");
		// 系统配置
		map.addAttribute("system", getSystem());
		return "/mobile/user/drawapplylist";
	}
	/**
	 * 钱包流水记录
	 * @param sc
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/searchdrawapply")
	public String searchdrawapply(TdDrawapplySearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currUser = this.getCurrentUser();
		sc.setUid(currUser.getUid());
		List<TdDrawapply> logList = tdDrawapplyService.findBySearchCriteria(sc);
	    modelMap.addAttribute("logList", logList) ;
	    modelMap.addAttribute("sc", sc) ;
		return "/mobile/user/drawapplylistbody";
	}
	/**
	 * 钱包流水记录
	 * @param sc
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/drawapplydetail")
	public String drawapplydetail(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currUser = this.getCurrentUser();
		TdDrawapply drawapply = null;
		if(null!=id && id>0){
			drawapply = tdDrawapplyService.findOne(id);
		}
		if(null==drawapply||!drawapply.getUid().equals(currUser.getUid())){
			return "redirect:404";
		}
		// 系统配置
		modelMap.addAttribute("system", getSystem());
		modelMap.addAttribute("drawapply", drawapply) ;
		return "/mobile/user/drawapplydetail";
	}
	
	/**
	 * 用户收益
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/profit")
	public String userprofit(HttpServletRequest request, HttpServletResponse response, ModelMap map)
	{
		// 系统配置
		map.addAttribute("system", getSystem());
		return "/mobile/user/profit";
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
		return "/mobile/user/profit_listbody";
	}
	
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
	
	
	
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> add(TdShoppingcartItem item, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=item && null!=item.getId() && null!=item.getOptype()){
			try {
				TdUser currUser = this.getCurrentUser();
				item.setUid(currUser.getUid());
				tdShoppingcartItemService.addShoppingcartItemNum(item);
				//获取购物车
				ShoppingcartVO shoppingcart  = getShoppingcart(currUser.getUid());
				
				res.put("code", "1");
				res.put("totalAmount", shoppingcart.getTotalAmount().toString());
				res.put("totalPostage", shoppingcart.getTotalPostage().toString());
				return res;
			}catch (Exception e) {
				logger.error("购物车增减失败错误信息:"+e);
				res.put("code", "0");
				return res;
			}
		}else{
			res.put("code", "0");
			return res;
		}
	}
	@RequestMapping(value="/change", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> change(TdShoppingcartItem item, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=item){
			try {
				TdUser currUser = this.getCurrentUser();
				item.setUid(currUser.getUid());
				tdShoppingcartItemService.updateShoppingcartItem(item);
				//重新计算
				//获取购物车
				ShoppingcartVO shoppingcart  = getShoppingcart(currUser.getUid());
				
				res.put("code", "1");
				res.put("totalAmount", shoppingcart.getTotalAmount().toString());
				res.put("totalPostage", shoppingcart.getTotalPostage().toString());
				return res;
			}catch (Exception e) {
				logger.error("购物车更新失败错误信息:"+e);
				res.put("code", "0");
				return res;
			}
		}else{
			res.put("code", "0");
			return res;
		}
	}
	@RequestMapping(value="/remove", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> remove(String ids, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(StringUtils.isNotEmpty(ids)){
			try {
				TdUser currUser = this.getCurrentUser();
				tdShoppingcartItemService.removeItemsFromShoppingcart(currUser.getUid(), ids);
				res.put("code", "1");
				return res;
			}catch (Exception e) {
				logger.error("购物车删除失败错误信息:"+e);
				res.put("code", "0");
				return res;
			}
		}else{
			res.put("code", "0");
			return res;
		}
	}
	
	private ShoppingcartVO getShoppingcart(Integer uid){
		ShoppingcartVO cart = new ShoppingcartVO();
		//重新计算
		TdShoppingcartSearchCriteria sc = new TdShoppingcartSearchCriteria();
		sc.setFlag(false);
		sc.setUid(uid);
		List<TdShoppingcartItem> itemList = tdShoppingcartItemService.findBySearchCriteria(sc);
		cart.setItemList(itemList);
		if(null!=itemList && itemList.size()>0){
			for(TdShoppingcartItem item : itemList){
				//累加每个商品的运费
				BigDecimal postage = (null!=item.getPostage())?item.getPostage():BigDecimal.ZERO;
				cart.setTotalPostage(postage.add(cart.getTotalPostage()));
				//累加单个商品总金额（价格*数量）
				BigDecimal quantity = new BigDecimal(item.getQuantity());
				BigDecimal amount = item.getPrice().multiply(quantity);
				cart.setTotalAmount(amount.add(cart.getTotalAmount()).add(postage));
			}
		}
		return cart;
	}
	
	/*
	 * 实体店消息审核
	 */
	@RequestMapping(value="/verifyExperienceStoreApply", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> verifyExperienceStoreApply(TdExperienceStore estore, Byte status, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>();
		TdExperienceStore pestore =  tdExperienceStoreService.findOne(estore.getId());
		if(null==pestore){
			res.put("code", "0");
			res.put("msg", "审核失败！");
			return res;
		}
//		estore.setStatus(status);
//		int affectedNum = tdUserService.saveVerifyExperienceStoreApply(estore, status);
		Date now = new Date();
		pestore.setStatus(status);
		pestore.setUpdateTime(now);
		pestore.setUpdateBy(this.getCurrentUser().getUid());
		tdExperienceStoreService.save(pestore);
		res.put("code", "1");
		res.put("msg", "审核成功！");
		//发送站内信
		TdUserMessage userMessage = new TdUserMessage();
		userMessage.setTitle("体验店申请消息");
		userMessage.setCreateTime(now);
		userMessage.setRelateId(0);
		userMessage.setStatus(Byte.valueOf("1"));
		userMessage.setUid(pestore.getUid());
		userMessage.setMsgType(Byte.valueOf("1"));
		if(Byte.valueOf("1").equals(status)){
			userMessage.setContent("恭喜您！您申请的的体验店已经通过审核。");
		}else{
			userMessage.setContent("很遗憾！您申请的的体验店审核不通过。");
		}
		userMessage.setId(null);
		tdUserMessageService.save(userMessage);	
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
		return "/mobile/user/recommendPeople";		
	}
	
	/*
	 * 商品管理
	 */
	@RequestMapping("/productManage")
	public String productManage(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currUser = this.getCurrentUser();
		modelMap.addAttribute("customer", currUser);
		
		TdAgent agent = tdAgentService.findByUid(currUser.getUid());
		if(null!=agent){
			modelMap.addAttribute("isAgent", true);
		}
		return "/mobile/user/productManage";	
	}
	
	/*
	 * 查看我的商品
	 */
	@RequestMapping("/lookMyProduct")
	public String lookMyProduct(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/mobile/user/myProduct";	
	}
	
	/*
	 * 搜索我的商品
	 */
	@RequestMapping("/searchMyProduct")
	public String searchMyProduct(TdProductCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		int pageNo = sc.getPageNo();
		TdUser currentUser = this.getCurrentUser();
		sc.setPageSize(3);
		/*TdAgentSearchCriteria asc = new TdAgentSearchCriteria();
		asc.setFlag(false);
		asc.setUid(currentUser.getUid());
		List<TdAgent> agentList = tdAgentService.findBySearchCriteria(asc);
		List<Integer> productTypeIdList = new ArrayList<Integer>();
		if(agentList != null && agentList.size() > 0){
			for(TdAgent agent : agentList){
				productTypeIdList.add(agent.getProductTypeId());
			}
		}
		if(productTypeIdList.size() > 0){
			sc.setProductTypeIds(productTypeIdList);
		}*/
		TdAgent agent = tdAgentService.findByUid(currentUser.getUid());
		sc.setTypeId(agent.getProductTypeId());
		sc.setKind(ConstantsUtils.PRODUCT_KIND_COMMON);
		sc.setStatus(Byte.valueOf("1"));
		List<TdProduct> productList = tdProductService.findBySearchCriteria(sc);
		if(sc.getPageNo() == pageNo){
			modelMap.addAttribute("productList", productList);			
		}else{
			modelMap.addAttribute("sc", sc);
		}
		return "/mobile/user/myProductTemplate";	
	}
	
	/*
	 * 查看供应商商品
	 */
	@RequestMapping("/lookSupplierProduct")
	public String lookSupplierProduct(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/mobile/user/SupplierProduct";	
	}
	
	/*
	 * 参加竞选
	 */
	@RequestMapping("/joinElection")
	public String joinElection(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currentUser = this.getCurrentUser();
		modelMap.addAttribute("currentUser", currentUser);
		return "/mobile/user/electionMaterial";	
	}
	
	/*
	 * 搜索供应商商品
	 */
	@RequestMapping("/searchSupplierProduct")
	public String searchSupplierProduct(TdProductCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		int pageNo = sc.getPageNo();
		TdUser currentUser = this.getCurrentUser();
		sc.setPageSize(3);
		sc.setUid(currentUser.getUid());
		List<TdProduct> productList = tdProductService.findBySearchCriteria(sc);
		if(sc.getPageNo() == pageNo){
			modelMap.addAttribute("productList", productList);			
		}else{
			modelMap.addAttribute("sc", sc);
		}
		return "/mobile/user/supplierProductTemplate";	
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
	@RequestMapping(value="/saveProduct", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> saveProduct(Boolean isFreeProduct, TdProduct product,  String[] attachment, TdProductDescription productDescription, TdProductSku productSku, String attributeAssembleStr, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>();
		TdUser currentUser = this.getCurrentUser();
		Date now = new Date();
		String[] atrributeArray = null;
		if(StringUtils.isNotBlank(attributeAssembleStr)){
			atrributeArray = attributeAssembleStr.split(",");
		}
		try{
			// 商品
			if(isFreeProduct != null && isFreeProduct){
				product.setKind(Byte.valueOf("3"));
			}else{
				product.setKind(Byte.valueOf("1"));
			}
			product.setUid(currentUser.getUid());
			if(null!=atrributeArray && atrributeArray.length>0){
				List<SkuSpecialVO> specialList = new ArrayList<SkuSpecialVO>();
				for(String at : atrributeArray){
					if(StringUtils.isNotBlank(at)){
						String[] gga = at.split("\\|");
						for(String ggas : gga){
							try {
								String[] attr = ggas.split("_");
								SkuSpecialVO special = new SkuSpecialVO();
								special.setSname(attr[0]);
								special.setSoption(attr[1]);
								specialList.add(special);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
				//获取规格选择项
				String specifiations = tdProductService.getSpecificationatsJsonString(specialList);
				product.setSpecificationats(specifiations);
				product.setSpecification(true);
				product.setQuantum(productSku.getStock() * atrributeArray.length);
			}else{
				product.setSpecification(false);
				product.setQuantum(productSku.getStock());
			}
			product.setStatus(Byte.valueOf("2"));
			if(attachment != null && attachment.length > 0){
				product.setImageUrl(attachment[0]);
			}
			product.setCreateTime(now);
			product.setUpdateTime(now);
			product.setUpdateBy(currentUser.getUid());
			product.setPrice(productSku.getSalesPrice());
			//保存商品类型Tree
			if(null!=product.getTypeId()&&product.getTypeId()>0){
				TdProductType type = tdProductTypeService.findOneWithParents(product.getTypeId());
				String typeIdTree = type.getParentIdTree();
				product.setTypeIdTree(typeIdTree);
			}
			tdProductService.save(product);
			// 商品图片
			if(attachment != null && attachment.length > 0){
				for(String a : attachment){
					TdProductAttachment pa = new TdProductAttachment();
					pa.setAttachment(a);
					// 格式：/tdStore/static/imgs/product/2016/7/21/78c60dcc-0938-4128-85fb-1291d75be098.jpg
					String fileName = a.substring(a.lastIndexOf("/") + 1);
					pa.setFilename(fileName);
					pa.setProductId(product.getId());
					tdProductAttachmentService.save(pa);
				}
			}
			// 商品描述
			productDescription.setType(Byte.valueOf("1"));
			productDescription.setProductId(product.getId());
			productDescription.setUpdateBy(currentUser.getUid());
			productDescription.setUpdateTime(now);
			tdProductDescriptionService.save(productDescription);
			productDescription.setId(null);
			productDescription.setType(Byte.valueOf("2"));
			productDescription.setDescription("");
			tdProductDescriptionService.save(productDescription);
			productDescription.setId(null);
			productDescription.setType(Byte.valueOf("3"));
			productDescription.setDescription("");
			tdProductDescriptionService.save(productDescription);
			
			// 货品	atrributeArray格式：规格1=gv1,规格2=gv21	 保存规格格式：{"颜色":"红色","尺码":"38"}
			if(null!=atrributeArray && atrributeArray.length>0){
				for(String at : atrributeArray){
					TdProductSku ps = new TdProductSku();
					ps.setHighPrice(productSku.getHighPrice());
					ps.setLowPrice(productSku.getLowPrice());
					ps.setMarketPrice(productSku.getMarketPrice());
					ps.setProductId(product.getId());
					ps.setSalesPrice(productSku.getSalesPrice());
					ps.setStatus(Byte.valueOf("2"));
					ps.setStock(productSku.getStock());
					ps.setSupplierPrice(productSku.getSupplierPrice());
					ps.setUpdateBy(currentUser.getUid());
					ps.setUpdateTime(now);
					ps.setSkuCode(product.getCode());
					// 设置规格
					JSONObject jo = new JSONObject();
					String[] gga = at.split("\\|");
					for(String ggas : gga){
						try {
							String[] attr = ggas.split("_");
							jo.put(attr[0], attr[1]);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					ps.setSpecifications(jo.toString());
					tdProductSkuService.save(ps);
					
				}
			}else{
				TdProductSku ps = new TdProductSku();
				ps.setHighPrice(productSku.getHighPrice());
				ps.setLowPrice(productSku.getLowPrice());
				ps.setMarketPrice(productSku.getMarketPrice());
				ps.setProductId(product.getId());
				ps.setSalesPrice(productSku.getSalesPrice());
				ps.setStatus(Byte.valueOf("2"));
				ps.setStock(productSku.getStock());
				ps.setSupplierPrice(productSku.getSupplierPrice());
				ps.setUpdateBy(currentUser.getUid());
				ps.setUpdateTime(now);
				ps.setSkuCode(product.getCode());
				tdProductSkuService.save(ps);
			}
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
			res.put("status", "n");
			res.put("info", "商品保存失败。");
		}
		res.put("status", "y");
		res.put("info", "商品保存成功。");
		
		return res;
	}
	
	/*
	 * 下级会员
	 */
	@RequestMapping("/downUserList")
	public String downThreeUserList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.addAttribute("isTheFirstTime", "yes");
		modelMap.addAttribute("pageNo", "1");
		return "/mobile/user/downUserList";	
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
		return "/mobile/user/supplierApply";	
	}
	
	/*
	 * 推广
	 */
	@RequestMapping("/mySpread")
	public String mySpread(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException, JSONException {
		TdUser currentUser = this.getCurrentUser();
//		if(null==currentUser){
//			String fromUser = request.getParameter("fr");
//			if(StringUtils.isNotBlank(fromUser)){
//				TdUser frUser = tdUserService.selectByUname(fromUser);
//				if(null!=frUser){
//					currentUser = frUser;
//				}else{
//					return "/moblie/index";
//				}
//			}
//		}
		modelMap.addAttribute("currentUser", currentUser);
//		String spreadUrl = "http://www.cqupt.edu.cn";
//		String imgName = UUID.randomUUID().toString() + ".png";
//		String spreadImgPath = request.getServletContext().getRealPath("/") + "static/imgs/spread" + imgName;
//		TwoDimensionCode tdc = new TwoDimensionCode();
//		tdc.encoderQRCode(spreadUrl, spreadImgPath, "png");
//		modelMap.addAttribute("spreadImg", "static/imgs/spread" + imgName);
		return "/mobile/user/mySpread";
	}
	
	@RequestMapping(value = "/mySpread/qrcode")
	public void spreadQRcode(String title, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, JSONException
	{
		TdUser currentUser = this.getCurrentUser();
//		if(null==currentUser){
//			String fromUser = request.getParameter("fr");
//			if(StringUtils.isNotBlank(fromUser)){
//				TdUser frUser = tdUserService.selectByUname(fromUser);
//				if(null!=frUser){
//					currentUser = frUser;
//				}
//			}
//		}
//		tdUserQRcodeTools.QRcodeByUidAndResponse(currentUser.getUid(), response);
//		TdUserQRcodeTools QRcodeTools = new TdUserQRcodeTools();
		tdUserQRcodeTools.QRcodeByUidAndResponse(currentUser.getUid(), title, request, response);
	}
	
	/*
	 * 保存供应商资质认证申请
	 */
	@RequestMapping("/saveUserSupplierApply")
	@ResponseBody
	public Map<String, String> saveUserSupplierApply(TdUserSupplier userSupplier, String[] attachments, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		Map<String, String> res = new HashMap<>();
		try{
			Date now = new Date();
			TdUser currentUser = this.getCurrentUser();
			if(null==userSupplier.getId()){
				userSupplier.setCreateTime(now);
				userSupplier.setUid(currentUser.getUid());
			}else{
				TdUserSupplier presuppiler = tdUserSupplierService.findOne(userSupplier.getId());
				if(null==presuppiler || !presuppiler.getUid().equals(currentUser.getUid())){
					res.put("code", "0");
					res.put("msg", "资质认证更新失败：未找到数据！");
					return res;
				}
			}
			userSupplier.setUpdateTime(now);
			userSupplier.setUpdateBy(currentUser.getUid());
			String images = "";
			if(null!=attachments && attachments.length>0){
				for(int i = 0; i < attachments.length; i ++){
					images = images + attachments[i];
					if(i < attachments.length - 1){
						images = images + ":";
					}
				}
			}
			userSupplier.setImages(images);
			tdUserSupplierService.save(userSupplier);	
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
	 * 修改手机号及地区
	 */
	@RequestMapping("/savePhoneNum")
	@ResponseBody
	public Map<String, String> savePhoneNum(TdUser user, String valideCode, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		Map<String, String> res = new HashMap<>();
		try{
			String changePasswordValidCode = (String) request.getSession().getAttribute("changePasswordValidCode");
			
			if(changePasswordValidCode == null || !valideCode.equals(changePasswordValidCode)){
				res.put("info", "验证码错误！");
				res.put("status", "n");
				request.getSession().removeAttribute("changePasswordValidCode");
				return res;
			}
			
			TdUser currentUser = this.getCurrentUser();
			//检查手机号码是否重复
			TdUser puser = tdUserService.findByUtel(user.getUtel());
			if(null != puser && !puser.getUid().equals(currentUser.getUid())){
				res.put("status", "n");
				res.put("info", "保存失败:手机号码已被使用！");
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
					attributeList.add(attribute);
				}
				
			}
		}
		modelMap.addAttribute("attributeList", attributeList);
		return "/mobile/user/productAttributeTemplate";	
	}
	
	/*
	 * 上传商品
	 */
	@RequestMapping("/addProduct")
	public String addProduct(Boolean isFreeProduct, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		/*TdProductTypeCriteria tsc = new TdProductTypeCriteria();
		tsc.setStatus((byte) 1);
		List<TdProductType> productTypeList = tdProductTypeService.findAll(tsc);
		modelMap.addAttribute("productTypeList", productTypeList);*/
		modelMap.addAttribute("isFreeProduct", isFreeProduct);
		return "/mobile/user/addProduct";	
	}
	
	@RequestMapping(value="/getDownUsers", method = RequestMethod.POST)
	@ResponseBody
	public String getDownUsers(Integer pageNo, HttpServletRequest request, HttpServletResponse response){
		TdUser currentUser = this.getCurrentUser();
		String parentIdsStr = "["+ currentUser.getUid() +"]"; // 下一二三级的id
		String oneIdsStr = ""; // 下一级的id
		String twoIdsStr = ""; // 下二级的id
		String threeIdsStr = "";// 下三级的id
		TdUserSearchCriteria sc = new TdUserSearchCriteria();
		sc.setFlag(false);
		sc.setParentIdsStr(parentIdsStr);
		List<TdUser> downOneUserList = tdUserService.findBySearchCriteria(sc);
		if(downOneUserList != null){
			for(TdUser user : downOneUserList){
				parentIdsStr += "["+ user.getUid() +"]";
				oneIdsStr += "["+ user.getUid() +"]";
			}
		}
		if(!oneIdsStr.equals("")){
			sc.setParentIdsStr(oneIdsStr);
			List<TdUser> downTwoUserList = tdUserService.findBySearchCriteria(sc);
			if(downTwoUserList != null){
				for(TdUser user : downTwoUserList){
					parentIdsStr += "["+ user.getUid() +"]";
					twoIdsStr += "["+ user.getUid() +"]";
				}
			}			
		}
		
		if(!twoIdsStr.equals("")){
			sc.setParentIdsStr(twoIdsStr);
			List<TdUser> downThreeUserList = tdUserService.findBySearchCriteria(sc);
			if(downThreeUserList != null){
				for(TdUser user : downThreeUserList){
					threeIdsStr += "["+ user.getUid() +"]";
				}
			}			
		}
		
		sc.setParentIdsStr(parentIdsStr);
		sc.setPageNo(pageNo);
		sc.setFlag(true);
		sc.setPageSize(3);
		List<TdUser> downUserList = new ArrayList<>();
		if(!parentIdsStr.equals("")){
			downUserList = tdUserService.findBySearchCriteria(sc); // 下一二三级分页会员			
		}
		
		// 生成json数据
		JSONObject jsonData = new JSONObject();
		try {
			jsonData.put("hasData", "yes");
			if(sc.getPageNo()!=pageNo){
				jsonData.put("hasData", "no");
				return jsonData.toString();
			}else{
				jsonData.put("totalCount", sc.getTotalCount());
			}
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JSONArray contentJsonArray = new JSONArray();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for(TdUser user : downUserList){
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("uavatar", user.getUavatar()!=null?user.getUavatar():"/static/default/images/defaultimg.jpg");
				jsonObject.put("unick", user.getUnick()!=null?user.getUnick():"");
				jsonObject.put("ugenter", user.getUgenter()!=null?user.getUgenter().toString():"");
				if(user.getUgenter() != null){
					if(user.getUgenter().equals(Byte.valueOf("1"))){
						jsonObject.put("gender", "man");
					}else if(user.getUgenter().equals(Byte.valueOf("2"))){
						jsonObject.put("gender", "woman");
					}else{
						jsonObject.put("gender", "");
					}
				}
				jsonObject.put("ugenterStr", user.getUgenterStr());
				jsonObject.put("uname", user.getUname());
				jsonObject.put("ubirthday", user.getUbirthdayStr()!=null?user.getUbirthdayStr():"");
				if(oneIdsStr.contains(user.getUid().toString())){
					jsonObject.put("level", "1");
				}else if(twoIdsStr.contains(user.getUid().toString())){
					jsonObject.put("level", "2");
				}else if(threeIdsStr.contains(user.getUid().toString())){
					jsonObject.put("level", "3");
				}
			} catch (JSONException e) {
				logger.error("下级会员解析失败");
				e.printStackTrace();
			}
			contentJsonArray.put(jsonObject);
		}
		try {
			jsonData.put("addedData", contentJsonArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonData.toString();
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
		
		return "/mobile/user/districtSelectTemplate";	
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
		
	    return "/mobile/user/index";
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
