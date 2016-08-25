package com.tiandu.payment.alipay;

public class AlipayConfig {
    // ISO-8859-1编码
    public static final String ISO_8859_1 = "ISO-8859-1";
    
    // 编码方式
    public static final String CHARSET = "gbk";
    
    // 合作身份者ID-2088421287138091
    public static final String PARTNER = "2088421287138091";

    // 收款支付宝账号-1683998877@qq.com
    public static String SELLER_EMAIL = "1683998877@qq.com";
    
    // 收款支付宝ID
    public static String SELLER_ID = PARTNER;

    // 商户的私钥-83vls39l7x75iyr2ls7k86ak3vhwm7rv
    public static String KEY = "83vls39l7x75iyr2ls7k86ak3vhwm7rv";

    // 签名方式
    public static String SIGN_TYPE = "MD5";
    
    // 即时到账
    public static final String CREATE_TRADE_SERVICE = "create_direct_pay_by_user";
    
    // 手机网站支付
    public static final String CREATE_MOBILE_SERVICE = "alipay.wap.create.direct.pay.by.user";
    
    // 商品购买
    public static final String PAYMENT_TYPE = "1";
    
    // 合作伙伴名称-重庆市一路上电子商务有限公司
    public static final String SUBJECT = "重庆市一路上电子商务有限公司";
    
    // 货物数量
    public static final String DEFAULT_QUANTITY = "1";
    
    // 用户确认收货过期自动确认收货时间
    public static final String RECORD_POST = "1d";
    
    // 支付超时时间
    public static final String PAY_POST = "1d";
    
    
    /*
     * 支付请求地址
     */
    public static final String REAUESTURL = "https://mapi.alipay.com/gateway.do";
    
    /*
     * 支付宝消息验证地址
     */
    public static final String HTTPS_VERIFY_URL = REAUESTURL + "?service=notify_verify&";
}
