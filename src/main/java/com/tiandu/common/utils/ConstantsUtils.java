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
	
	
	/**---------------------------订单状态---------------------------**/
	/**
	 * 订单状态：-1 已取消
	 */
	public static final Byte ORDER_STATUS_CANCEL = -1;
	/**
	 * 订单状态：1 新订单
	 */
	public static final Byte ORDER_STATUS_NEW = 1;
	/**
	 * 订单状态：2 已完成
	 */
	public static final Byte ORDER_STATUS_COMPLETE = 2;
	/**
	 * 订单支付状态： 1 已支付
	 */
	public static final Byte ORDER_PAY_STATUS_PAYED = 1;
	/**
	 * 订单支付状态： 2 未支付
	 */
	public static final Byte ORDER_PAY_STATUS_UNPAY = 2;
	/**
	 * 订单支付状态： 3 部分退款
	 */
	public static final Byte ORDER_PAY_STATUS_PART_REFUND = 3;
	/**
	 * 订单支付状态： 4 全部退款
	 */
	public static final Byte ORDER_PAY_STATUS_ALL_REFUND = 4;
	/**
	 * 订单发货状态： 1 已发货
	 */
	public static final Byte ORDER_SHIPMENT_STATUS_SHIPPED = 1;
	/**
	 * 订单发货状态： 2 未发货
	 */
	public static final Byte ORDER_SHIPMENT_STATUS_UNSHIPPED = 2;
	/**
	 * 订单发货状态： 3 部分退货
	 */
	public static final Byte ORDER_SHIPMENT_STATUS_PART_RETURN = 3;
	/**
	 * 订单发货状态： 4 全部退货
	 */
	public static final Byte ORDER_SHIPMENT_STATUS_All_RETURN = 4;
	
	/**发货单类型**/
	/**
	 * 发货单类型： 1 发货单
	 */
	public static final Byte ORDERSHIPMENT_TYPE_SHIP = 1;
	/**
	 * 发货单类型：2 退货单
	 */
	public static final Byte ORDERSHIPMENT_TYPE_RETURN = 1;
}
