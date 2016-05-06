package com.focusmate.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.focusmate.datasource.entities.Product;
import com.focusmate.datasource.repository.ProductRepository;
import com.focusmate.datasource.repository.TimeDiscountRepository;
import com.focusmate.service.WXPayService;
import com.focusmate.service.util.Enums;
import com.focusmate.service.util.PriceGenerator;
import com.focusmate.wxpay.WxPayApi;
import com.focusmate.wxpay.dto.WXPayData;
import com.focusmate.wxpay.util.RandomStringGenerator;
import com.focusmate.wxpay.util.WxPayConfigure;

@RestController
@RequestMapping(value = "/wxpay")
public class WXPayController {
    private final Logger           logger = LoggerFactory.getLogger(StationController.class);

    @Autowired
    WXPayService                   wxPayService;

    @Autowired
    ProductRepository              productRepository;

    @Autowired
    private TimeDiscountRepository timeDiscountRepository;

    /**
     * 扫码支付模式一回调处理类. 接收微信支付后台发送的扫码结果，调用统一下单接口并将下单结果返回给微信支付后台
     * 
     * @param parametersDTO
     * @return
     */
    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    public String WXPayNotice(@RequestBody String requestData) {
        logger.debug("This URL is used to request by WXPAY Server");

        /* 将请求的XML数据转换成object */
        WXPayData notifyData = new WXPayData();
        try {
            notifyData.FromXML(requestData);
        } catch (Exception e) {
            e.printStackTrace();
            // 若签名错误，则立即返回结果给微信支付后台
            WXPayData res = new WXPayData();
            res.SetValue("return_code", "FAIL");
            res.SetValue("return_msg", e.getMessage());
            logger.error("Sign check error : " + res.ToXml());
            return res.ToXml();
        }

        // 检查openid和product_id是否返回
        if (!notifyData.IsSet("openid") || !notifyData.IsSet("product_id")) {
            WXPayData res = new WXPayData();
            res.SetValue("return_code", "FAIL");
            res.SetValue("return_msg", "回调数据异常");
            logger.error("The Pay result is error : " + res.ToXml());
            return res.ToXml();

        }

        // 调统一下单接口，获得下单结果
        WXPayData unifiedOrderResult;
        String openid = notifyData.GetValue("openid").toString();
        String product_id = notifyData.GetValue("product_id").toString();
        String is_subscribe = notifyData.GetValue("is_subscribe").toString();

        try {
            unifiedOrderResult = UnifiedOrder(openid, product_id, is_subscribe);
        } catch (Exception e) {// 若在调统一下单接口时抛异常，立即返回结果给微信支付后台
            WXPayData res = new WXPayData();
            res.SetValue("return_code", "FAIL");
            res.SetValue("return_msg", e.getMessage());
            logger.error("UnifiedOrder failure : " + res.ToXml());
            e.printStackTrace();

            return res.ToXml();
        }

        // 统一下单成功,则返回成功结果给微信支付后台
        WXPayData data = new WXPayData();
        data.SetValue("return_code", "SUCCESS");
        data.SetValue("return_msg", "OK");
        data.SetValue("appid", WxPayConfigure.getAppid());
        data.SetValue("mch_id", WxPayConfigure.getMchid());
        data.SetValue("nonce_str", RandomStringGenerator.getRandomStringByLength(32));
        data.SetValue("prepay_id", unifiedOrderResult.GetValue("prepay_id"));
        data.SetValue("result_code", "SUCCESS");
        data.SetValue("err_code_des", "OK");
        data.SetValue("sign", data.makeSign());

        return data.ToXml();
    }

    /**
     * 支付结果通知回调处理类, 负责接收微信支付后台发送的支付结果并对订单有效性进行验证， 将验证结果反馈给微信支付后台
     * 
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/result-notify", method = RequestMethod.POST)
    public String ResultNotify(@RequestBody String requestData) {

        /* 将请求的XML数据转换成object */
        WXPayData notifyData = new WXPayData();
        try {
            notifyData.FromXML(requestData);
        } catch (Exception e) {
            e.printStackTrace();
            // 若签名错误，则立即返回结果给微信支付后台
            WXPayData res = new WXPayData();
            res.SetValue("return_code", "FAIL");
            res.SetValue("return_msg", e.getMessage());
            logger.error("Sign check error : " + res.ToXml());
            return res.ToXml();
        }

        // 检查支付结果中transaction_id是否存在
        if (!notifyData.IsSet("transaction_id")) {
            // 若transaction_id不存在，则立即返回结果给微信支付后台
            WXPayData res = new WXPayData();
            res.SetValue("return_code", "FAIL");
            res.SetValue("return_msg", "支付结果中微信订单号不存在");
            logger.error("The Pay result is error : " + res.ToXml());
            return res.ToXml();
        }

        String transaction_id = notifyData.GetValue("transaction_id").toString();
        String trad_no = notifyData.GetValue("out_trade_no").toString();
        boolean isQueried = false;
        // 查询订单，判断订单真实性
        try {
            isQueried = QueryOrder(transaction_id);
        } catch (Exception e) {// 若在调订单查询接口时抛异常，立即返回结果给微信支付后台
            e.printStackTrace();

            WXPayData res = new WXPayData();
            res.SetValue("return_code", "FAIL");
            res.SetValue("return_msg", "订单查询失败");
            logger.error("Order query failure : " + res.ToXml());
            return res.ToXml();
        }
        if (!isQueried) {
            // 若订单查询失败，则立即返回结果给微信支付后台
            WXPayData res = new WXPayData();
            res.SetValue("return_code", "FAIL");
            res.SetValue("return_msg", "订单查询失败");
            logger.error("Order query failure : " + res.ToXml());
            return res.ToXml();
        }
        // 查询订单成功
        else {
            /* 设置交易状态 */
            wxPayService.updateTranscationStatus(trad_no, Enums.WxPayEnum.STATUS_PAY);

            WXPayData res = new WXPayData();
            res.SetValue("return_code", "SUCCESS");
            res.SetValue("return_msg", "OK");
            logger.info("order query success : " + res.ToXml());
            return res.ToXml();
        }
    }

    /**
     * 统一下单
     * 
     * @param openId
     * @param productId
     * @return
     * @throws Exception
     */
    private WXPayData UnifiedOrder(String openId, String productId, String is_subscribe) throws Exception {

        /* 检查productId是否存在 */
        Product product = productRepository.findOne(Integer.parseInt(productId));
        if (null == product) {
            throw new Exception("该产品不存在");
        }

        // 统一下单
        WXPayData req = new WXPayData();
        req.SetValue("body", product.getTitle());

        String trade_no = RandomStringGenerator.generateOutTradeNo();
        req.SetValue("out_trade_no", trade_no);

        // 判断是否关注公众帐号
        int fee = 0;
        if (is_subscribe.equals("Y")) {
            fee = product.getSale();
        } else {
            fee = product.getPrice();
        }
        req.SetValue("total_fee",
            PriceGenerator.getSale(timeDiscountRepository.findByProductId(Integer.parseInt(productId)), fee));

        req.SetValue("attach", product.getAttach());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        req.SetValue("time_start", formatter.format(date).toString());
        req.SetValue("time_expire", formatter.format(date.getTime() + 1000 * 60 * 30).toString());// 设置半个小时后失效

        req.SetValue("goods_tag", "test");
        req.SetValue("trade_type", "NATIVE");
        req.SetValue("openid", openId);
        req.SetValue("product_id", product.getId());

        /* 在微信服务器上下单 */
        WXPayData result = WxPayApi.UnifiedOrder(req);

        // 若下单失败，则立即抛出异常，返回结果给微信支付后台
        if (!result.IsSet("appid") || !result.IsSet("mch_id") || !result.IsSet("prepay_id")) {
            throw new Exception("统一下单失败");
        }

        /* 内部下单 */
        wxPayService.orderTransaction(trade_no, openId, product.getStationId(), fee);

        return result;
    }

    /**
     * 查询订单
     * 
     * @param transaction_id
     * @return
     * @throws Exception
     */
    private boolean QueryOrder(String transaction_id) throws Exception {
        WXPayData req = new WXPayData();
        req.SetValue("transaction_id", transaction_id);
        WXPayData res = WxPayApi.OrderQuery(req);
        if (res.IsSet("return_code") && res.GetValue("return_code").toString().equals("SUCCESS")
            && res.IsSet("result_code") && res.GetValue("result_code").toString().equals("SUCCESS")) {
            return true;
        } else {
            return false;
        }
    }
}
