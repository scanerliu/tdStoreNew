package com.tiandu.payment.alipay;

public class Constants {
	
    public static final String KEY_SERVICE = "service"; // 接口名称 *
    public static final String KEY_PARTNER = "partner"; // 身份ID *
    public static final String KEY_CHARSET = "_input_charset"; // 编码 *
    public static final String KEY_NOTIFY_URL = "notify_url"; // 服务器异步通知页面路径
    public static final String KEY_RETURN_URL = "return_url"; //页面跳转同步通知页面路径
    public static final String KEY_OUT_TRADE_NO = "out_trade_no"; // 订单号 *
    public static final String KEY_SUBJECT = "subject"; // 商品名称 *
    public static final String KEY_TOTAL_FEE = "total_fee"; // 金额 *
    public static final String KEY_PAYMENT_TYPE = "payment_type"; // 支付类型 * 
    
    // 下面三个参数的优先级别是：seller_id>seller_account_name>seller_email
    public static final String KEY_SELLER_ID = "seller_id"; // 卖家支付宝用户号 *
    public static final String KEY_SELLER_EMAIL = "seller_email"; //卖家支付宝账号
    public static final String KEY_SELLER_ACCOUNT_NAME = "seller_account_name"; //卖家支付宝账号别名 
    
    public static final String KEY_SHOW_URL = "show_url"; // 商品展示网址（手机支付不可空）
    
    public static final String KEY_PRICE = "price"; // 商品单价
    public static final String KEY_QUANTITY = "quantity"; // 数量
    public static final String KEY_BODY = "body"; // 商品描述
    
    
    public static final String KEY_ITB_PAY = "it_b_pay"; //超时时间
    
    public static final String KEY_TRADE_NO = "trade_no";  // 支付宝交易号
    
    
    public static final String METHOD_POST = "POST";
    
    public static final String PAY_BUTTON_NAME = "到支付宝支付";
    
}
