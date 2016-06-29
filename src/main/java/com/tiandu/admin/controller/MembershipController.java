package com.tiandu.admin.controller;

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

import com.alibaba.druid.util.StringUtils;
import com.tiandu.common.controller.BaseController;
import com.tiandu.custom.entity.TdMembership;
import com.tiandu.custom.search.TdMembershipSearchCriteria;
import com.tiandu.custom.service.TdMembershipService;

/**
 * 
 * @author L. Gao
 *
 */
@Controller
@RequestMapping("/admin/membership")
public class MembershipController extends BaseController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private TdMembershipService tdMembershipService;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/admin/membership/list";
	}

	@RequestMapping("/search")
	public String search(TdMembershipSearchCriteria sc, HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		List<TdMembership> membershipList = tdMembershipService.findBySearchCriteria(sc);
		modelMap.addAttribute("membershipList", membershipList);
		modelMap.addAttribute("sc", sc);
		return "/admin/membership/listbody";
	}

	@RequestMapping("/edit")
	public String edit(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdMembership membership = null;
		if (null != id) {
			membership = tdMembershipService.findOne(id);
		}
		if (null == membership) {
			membership = new TdMembership();
		}
		modelMap.addAttribute("membership", membership);
		return "/admin/membership/membershipForm";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> save(TdMembership membership, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> res = new HashMap<String, String>();
		try {
			// 设置修改后的参数
			if (membership.getId() != null) {
				TdMembership ms = tdMembershipService.findOne(membership.getId());
				if (ms != null) {
					ms.setName(membership.getName());
					ms.setLevel(membership.getLevel());
					ms.setUpgradeAgents(membership.getUpgradeAgents());
					ms.setTip(membership.getTip());
					ms.setStatus(membership.getStatus());
					tdMembershipService.save(ms);
					res.put("msg", "会员等级修改成功。");
				}
			} else {
				tdMembershipService.save(membership);
				res.put("msg", "会员等级添加成功。");
			}
			res.put("code", "1");
			return res;
		} catch (Exception e) {
			logger.error("会员等级保存失败错误信息:" + e);
			res.put("code", "0");
			res.put("msg", "会员等级保存失败。");
			e.printStackTrace();
			return res;
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String idStr, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> res = new HashMap<String, String>();
		if (!org.apache.commons.lang.StringUtils.isEmpty(idStr.trim())) {
			String[] idStrArray = idStr.split(",");
			try {
				for (String id : idStrArray) {
					tdMembershipService.delete(Integer.parseInt(id));
				}
				res.put("code", "1");
				res.put("msg", "会员等级删除成功。");
				return res;
			} catch (Exception e) {
				logger.error("会员等级删除失败，错误信息:" + e);
				res.put("code", "0");
				res.put("msg", "会员等级删除失败。");
				e.printStackTrace();
				return res;
			}
		} else {
			logger.error("会员等级删除失败，错误信息:idStr为空。");
			res.put("code", "0");
			res.put("msg", "会员等级删除失败。");
			return res;
		}
	}

}
