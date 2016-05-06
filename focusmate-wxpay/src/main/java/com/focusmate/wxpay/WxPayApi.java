package com.focusmate.wxpay;

import com.focusmate.wxpay.dto.WXPayData;
import com.focusmate.wxpay.util.HttpsRequest;
import com.focusmate.wxpay.util.RandomStringGenerator;
import com.focusmate.wxpay.util.WxPayConfigure;

public class WxPayApi {

    public static WXPayData UnifiedOrder(WXPayData inputObj) throws Exception {

        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

        // 检测必填参数
        if (!inputObj.IsSet("out_trade_no")) {
            throw new Exception("缺少统一支付接口必填参数out_trade_no！");
        } else if (!inputObj.IsSet("body")) {
            throw new Exception("缺少统一支付接口必填参数body！");
        } else if (!inputObj.IsSet("total_fee")) {
            throw new Exception("缺少统一支付接口必填参数total_fee！");
        } else if (!inputObj.IsSet("trade_type")) {
            throw new Exception("缺少统一支付接口必填参数trade_type！");
        }

        // 关联参数
        if (inputObj.GetValue("trade_type").toString().equals("JSAPI") && !inputObj.IsSet("openid")) {
            throw new Exception("统一支付接口中，缺少必填参数openid！trade_type为JSAPI时，openid为必填参数！");
        }
        if (inputObj.GetValue("trade_type").toString().equals("NATIVE") && !inputObj.IsSet("product_id")) {
            throw new Exception("统一支付接口中，缺少必填参数product_id！trade_type为JSAPI时，product_id为必填参数！");
        }

        // 异步通知url未设置，则使用配置文件中的url
        if (!inputObj.IsSet("notify_url")) {
            inputObj.SetValue("notify_url", WxPayConfigure.NOTIFY_API);// 异步通知url
        }

        inputObj.SetValue("appid", WxPayConfigure.getAppid());// 公众账号ID
        inputObj.SetValue("mch_id", WxPayConfigure.getMchid());// 商户号
        inputObj.SetValue("spbill_create_ip", WxPayConfigure.getIP());// 终端ip
        inputObj.SetValue("nonce_str", RandomStringGenerator.getRandomStringByLength(32));// 随机字符串
        inputObj.SetValue("sign", inputObj.makeSign()); // 签名

        String xml = inputObj.ToXml();

        // 发送POST请求
        String responseString = new HttpsRequest().sendPost(url, xml);

        // 将XML结果转换成对象返回
        WXPayData result = new WXPayData();
        result.FromXML(responseString);

        return result;
    }

    public static WXPayData OrderQuery(WXPayData inputObj) throws Exception {

        String url = "https://api.mch.weixin.qq.com/pay/orderquery";

        // 检测必填参数
        if (!inputObj.IsSet("out_trade_no") && !inputObj.IsSet("transaction_id")) {
            throw new Exception("订单查询接口中，out_trade_no、transaction_id至少填一个！");
        }

        inputObj.SetValue("appid", WxPayConfigure.getAppid());// 公众账号ID
        inputObj.SetValue("mch_id", WxPayConfigure.getMchid());// 商户号
        inputObj.SetValue("nonce_str", RandomStringGenerator.getRandomStringByLength(32));// 随机字符串
        inputObj.SetValue("sign", inputObj.makeSign());// 签名

        String xml = inputObj.ToXml();

        // 发送POST请求
        String responseString = new HttpsRequest().sendPost(url, xml);

        // 将XML结果转换成对象返回
        WXPayData result = new WXPayData();
        result.FromXML(responseString);

        return result;
    }
}
