package com.tiandu.mobile.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiandu.common.controller.BaseController;
import com.tiandu.common.utils.ConstantsUtils;
import com.tiandu.custom.entity.TdMembership;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.entity.TdUserAddress;
import com.tiandu.custom.entity.TdUserMessage;
import com.tiandu.custom.search.TdUserAddressCriteria;
import com.tiandu.custom.service.TdMembershipService;
import com.tiandu.custom.service.TdUserAddressService;
import com.tiandu.custom.service.TdUserMessageService;
import com.tiandu.custom.service.TdUserSignService;
import com.tiandu.district.entity.TdDistrict;
import com.tiandu.district.service.TdDistrictService;
import com.tiandu.order.entity.TdShoppingcartItem;
import com.tiandu.order.search.TdShoppingcartSearchCriteria;
import com.tiandu.order.service.TdShoppingcartItemService;
import com.tiandu.order.vo.ShoppingcartVO;

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
		modelMap.addAttribute("message", tdUserMessageService.findOne(messageId));
		if(!message.getMsgType().equals(Byte.valueOf("3"))){
			return "/mobile/user/systemOrOrderMessageDetail";		
		}else{
			return "";
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
	
	/**
	 * 收货地址
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/shippingAddress")
	public String shippingAddress(ModelMap map,Integer addressId)
	{
		TdUser tdUser = this.getCurrentUser();
		if(tdUser == null)
		{
			return "redirect:/mobile/login";
		}
		// 系统配置
		if(addressId != null)
		{
			tdUserAddressService.setIsDefaultFalse(tdUser.getUid());
			TdUserAddress userAddress = tdUserAddressService.findOne(addressId);
			userAddress.setIsDefault(true);
			tdUserAddressService.save(userAddress);
		}
		map.addAttribute("system", getSystem());
		TdUserAddressCriteria sc = new TdUserAddressCriteria();
		sc.setUid(tdUser.getUid());
		List<TdUserAddress> userAddresses = tdUserAddressService.findBySearchCriteria(sc);
		map.addAttribute("address_list", userAddresses);
		return "/mobile/user/shippingAddress";
	}
	
	/**
	 * 新增收货地址
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/shippingAddressAdd")
	public String shippingAddressAdd(Integer addressId,ModelMap map)
	{
		TdUser tdUser = this.getCurrentUser();
		if(tdUser == null)
		{
			return "redirect:/modile/login";
		}
		// 系统配置
		map.addAttribute("system", getSystem());
		if(addressId != null)
		{
			TdUserAddress userAddress = tdUserAddressService.findOne(addressId);
			if(userAddress != null)
			{
				map.addAttribute("address",userAddress);
				Integer regionId = userAddress.getRegionId();
				Map<String, Object> regionMap = getUserDistrictIdByUserAddress(new HashMap<String,Object>(),regionId);
				map.addAllAttributes(regionMap);
			}
		}
		
		return "/mobile/user/shippingAddressAdd";
	}
	
	/**
	 * 地址保存并设置默认地址 
	 * @param tdUserAddress
	 * @return
	 */
	@RequestMapping(value = "/shippingAddressSave" ,method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> shippingAddressSave(TdUserAddress tdUserAddress)
	{
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("code", ConstantsUtils.RETURN_CODE_SUCCESS);
		TdUser tdUser = this.getCurrentUser();
		if(tdUser == null)
		{
			result.put("msg", "未登录");
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
	 * 地区选择
	 * @param provinceId 省
	 * @param cityId 市
	 * @return
	 */
	@RequestMapping(value = "/shippingAddressDistrict",method = RequestMethod.POST)
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
	
	private Map<String,Object> getUserDistrictIdByUserAddress(Map<String,Object> resMap,Integer districtId)
	{
		TdDistrict district = tdDistrictService.findOne(districtId);
		if(district != null && district.getUpid() != 0)
		{
			resMap.put(district.getUpid().toString() , districtId);
			this.getUserDistrictIdByUserAddress(resMap, district.getUpid());
		}
		else
		{
			Integer frist = (Integer)resMap.get(districtId.toString());
			Integer second = (Integer)resMap.get(frist.toString());
			resMap.clear();
			if(second != null)
			{
				resMap.put("district",second);
				resMap.put("city", frist);
				resMap.put("province", districtId);
			}
			else
			{
				resMap.put("city", frist);
				resMap.put("province", districtId);
			}
		}
		
		return resMap;
	}
	
	/**
	 * 地址删除
	 * @param addressId
	 * @return
	 */
	@RequestMapping(value="/shippingAddressDelete",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> shippingAddressDelete(Integer addressId)
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
			resMap.put("msg","参数错误");
			return resMap;
		}
		tdUserAddressService.delete(addressId);
		resMap.put("status",ConstantsUtils.RETURN_CODE_SUCCESS);
		resMap.put("msg","成功");
		return resMap;
	}
	
	
	@RequestMapping(value="/saveInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveInfo(TdUser user, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>();
		res.put("msg", "个人信息修改失败！");
		TdUser currentUser = this.getCurrentUser();
		currentUser.setUavatar(user.getUavatar());
		currentUser.setUnick(user.getUnick());
		currentUser.setUgenter(user.getUgenter());
		currentUser.setUbirthday(user.getUbirthday());
		currentUser.setUtel(user.getUtel());
		if(tdUserService.saveUserInfo(currentUser) == 1){
			res.put("msg", "个人信息修改成功！");			
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
}
