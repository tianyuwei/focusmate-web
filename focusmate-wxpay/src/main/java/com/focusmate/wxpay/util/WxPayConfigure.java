package com.focusmate.wxpay.util;

/**
 * User: rizenguo Date: 2014/10/29 Time: 14:40 这里放置各种配置数据
 */
public class WxPayConfigure {

    // sdk的版本号
    private static final String sdkVersion = "java sdk 1.0.1";

    // 这个就是自己要保管好的私有Key了（切记只能放在自己的后台代码里，不能放在任何可能被看到源代码的客户端程序中）
    // 每次自己Post数据给API的时候都要用这个key来对所有字段进行签名，生成的签名会放在Sign这个字段，API收到Post数据的时候也会用同样的签名算法对Post过来的数据进行签名和验证
    // 收到API的返回的时候也要用这个key来对返回的数据算下签名，跟API的Sign数据进行比较，如果值不一致，有可能数据被第三方给篡改

    private static String key = "a10adc3849ba56abbe56e056f20f883a";

    // 微信分配的公众号ID（开通公众号之后可以获取到）
    private static String appID = "wx81d0b50201b4b646";

    // 微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
    private static String mchID = "1270076001";

    // 受理模式下给子商户分配的子商户号
    private static String subMchID = "";

    // HTTPS证书的本地路径
    private static String certLocalPath = "apiclient_cert.p12";

    // HTTPS证书密码，默认密码等于商户号MCHID
    private static String certPassword = "1270076001";

    // 是否使用异步线程的方式来上报API测速，默认为异步模式
    private static boolean useThreadToDoReport = true;

    // 机器IP
    private static String ip = "121.43.100.100";

    // 以下是几个API的路径：

    // 1)支付结果通知回调url，用于商户接收支付结果
    public static String NOTIFY_API = "http://cs.focusmate.cn:8080/focusmate-web/wxpay/result-notify";

    public static boolean isUseThreadToDoReport() {
        return useThreadToDoReport;
    }

    public static void setUseThreadToDoReport(boolean useThreadToDoReport) {
        WxPayConfigure.useThreadToDoReport = useThreadToDoReport;
    }

    public static String HttpsRequestClassName = "com.focusmate.wxpay.util.HttpsRequest";

    public static void setKey(String key) {
        WxPayConfigure.key = key;
    }

    public static void setAppID(String appID) {
        WxPayConfigure.appID = appID;
    }

    public static void setMchID(String mchID) {
        WxPayConfigure.mchID = mchID;
    }

    public static void setSubMchID(String subMchID) {
        WxPayConfigure.subMchID = subMchID;
    }

    public static void setCertLocalPath(String certLocalPath) {
        WxPayConfigure.certLocalPath = certLocalPath;
    }

    public static void setCertPassword(String certPassword) {
        WxPayConfigure.certPassword = certPassword;
    }

    public static void setIp(String ip) {
        WxPayConfigure.ip = ip;
    }

    public static String getKey() {
        return key;
    }

    public static String getAppid() {
        return appID;
    }

    public static String getMchid() {
        return mchID;
    }

    public static String getSubMchid() {
        return subMchID;
    }

    public static String getCertLocalPath() {
        return certLocalPath;
    }

    public static String getCertPassword() {
        return certPassword;
    }

    public static String getIP() {
        return ip;
    }

    public static void setHttpsRequestClassName(String name) {
        HttpsRequestClassName = name;
    }

    public static String getSdkVersion() {
        return sdkVersion;
    }

}
