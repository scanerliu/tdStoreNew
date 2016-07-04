package com.tiandu.common.utils;

public class ConstantsUtils {

	public final static String PRIMARY_MEMBER_KEY = "td_key_2016_05_20****15##%%@&50";
	public final static Byte CONF_DATA_TYPE_BOOLEAN = 3;
	
	/**
	 * 代理类型-1 单类代理  2 分公司 3-三级分销
	 */
	public static final Integer AGENT_GROUPID_AGENT = 1;
	/**
	 * 代理类型-1 单类代理  2 分公司 3-三级分销
	 */
	public static final Integer AGENT_GROUPID_BRANCH = 2;
	/**
	 * 代理类型-1 单类代理  2 分公司 3-三级分销
	 */
	public static final Integer AGENT_GROUPID_DISTRIBUTION = 3;
	
	/**
	 * 分润类型1-区县单类代理(代金券、供应商和分销商)
	 */
	public static final Byte BENEFIT_TYPE_DISTRICT_PRODUCT_GENT_GIFT = 1;
	/**
	 * 分润类型2-省、直辖市级单类产品代理和供应商
	 */
	public static final Byte BENEFIT_TYPE_PROVINCE_PRODUCT_GENT = 2;
	/**
	 * 分润类型3-全国级单类产品代理和供应商
	 */
	public static final Byte BENEFIT_TYPE_COUNTRY_PRODUCT_GENT = 3;
	/**
	 * 分润类型4-区县级单类产品代理和供应商
	 */
	public static final Byte BENEFIT_TYPE_DISTRICT_PRODUCT_GENT = 4;
	/**
	 * 分润类型5-免费申请单类产品社区、村级体验中心
	 */
	public static final Byte BENEFIT_TYPE_EXPERIENCE_STORE_PRODUCT_GENT = 5;
	/**
	 * 分润类型6-申请成为县级分公司
	 */
	public static final Byte BENEFIT_TYPE_DISTRICT_BRANCH = 6;
	/**
	 * 分润类型7-申请成为省级分公司
	 */
	public static final Byte BENEFIT_TYPE_PROVINCE_BRANCH = 7;
}
