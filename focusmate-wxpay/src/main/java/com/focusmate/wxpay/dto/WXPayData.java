package com.focusmate.wxpay.dto;

import java.io.InputStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.focusmate.wxpay.util.Signature;
import com.focusmate.wxpay.util.Util;

public class WXPayData {
    private final Logger logger = LoggerFactory.getLogger(WXPayData.class);

    public WXPayData() {
    }

    private Map<String, Object> values = new Hashtable<String, Object>();

    /**
     * 设置某个字段的值
     * 
     * @param key
     * @param value
     */
    public void SetValue(String key, Object value) {
        values.put(key, value);
    }

    /**
     * 根据字段名获取某个字段的值
     * 
     * @param key
     * @return
     */
    public Object GetValue(String key) {
        if (values.containsKey(key)) {
            return values.get(key);
        }
        return null;
    }

    /**
     * 判断某个字段是否已设置
     * 
     * @param key
     * @return
     */
    public boolean IsSet(String key) {

        if (values.containsKey(key)) {
            return true;
        }
        return false;
    }

    /**
     * Map格式转化成url参数格式
     * 
     * @return 格式串, 该串不包含sign字段值
     * @throws Exception
     */
    public String ToXml() {

        try {
            // 数据为空时不能转化为xml格式
            if (0 == values.size()) {
                throw new Exception("WxPayData数据为空!");
            }

            StringBuilder xml = new StringBuilder();
            xml.append("<xml>");
            for (Iterator<String> itr = values.keySet().iterator(); itr.hasNext();) {
                String key = itr.next();
                Object value = values.get(key);

                // 字段值不能为null，会影响后续流程
                if (value == null) {
                    throw new Exception("WxPayData内部含有值为null的字段!");
                }

                if (value instanceof Integer) {
                    xml.append("<" + key + ">" + value + "</" + key + ">");
                } else if (value instanceof String) {
                    xml.append("<" + key + ">" + "<![CDATA[" + value + "]]></" + key + ">");
                } else// 除了string和int类型不能含有其他数据类型
                {
                    throw new Exception("WxPayData字段数据类型错误!");
                }
            }
            xml.append("</xml>");
            return xml.toString();

        } catch (Exception e) {
            logger.error("parse wxpay data to XML failed, reason:" + e.getMessage());
            return "<xml><return_code><![CDATA[FAIL]]><return_msg></return_msg><![CDATA[失败]]></return_code></xml>";
        }

    }

    /**
     * 将xml转为WxPayData对象并返回对象内部的数据
     * 
     * @param xml待转换的xml串
     * @return 经转换得到的
     * @throws Exception
     */
    public Map<String, Object> FromXML(String xml) throws Exception {

        if (null == xml || xml.isEmpty()) {
            throw new Exception("将空的xml串转换为WxPayData不合法!");
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream is = Util.getStringStream(xml);
        Document document = builder.parse(is);

        // 获取到document里面的全部结点
        NodeList allNodes = document.getFirstChild().getChildNodes();
        Node node;
        int i = 0;
        while (i < allNodes.getLength()) {
            node = allNodes.item(i);
            if (node instanceof Element) {
                values.put(node.getNodeName(), node.getTextContent());
            }
            i++;
        }

        try {
            // 错误是没有签名,无需验证
            if (IsSet("return_code") && !GetValue("return_code").toString().equals("SUCCESS")) {
                return values;
            }

            checkSign();// 验证签名,不通过会抛异常
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return values;
    }

    /**
     * 生成签名
     * 
     * @return 签名, sign字段不参加签名
     */
    public String makeSign() {
        return Signature.getSign(values);
    }

    public boolean checkSign() throws Exception {

        // 如果没有设置签名，则跳过检测
        if (!IsSet("sign")) {
            logger.error("WxPayData签名不存在!");
            throw new Exception("WxPayData签名不存在!");
        }
        // 如果设置了签名但是签名为空，则抛异常
        else if (GetValue("sign") == null || GetValue("sign").toString().equals("")) {
            logger.error("WxPayData签名存在但不合法!");
            throw new Exception("WxPayData签名存在但不合法!");
        }

        // 获取接收到的签名
        String return_sign = GetValue("sign").toString();

        // 在本地计算新的签名
        String cal_sign = makeSign();

        if (cal_sign.equals(return_sign)) {
            return true;
        }

        logger.error("WxPayData签名验证错误!");
        throw new Exception("WxPayData签名验证错误!");
    }
}
