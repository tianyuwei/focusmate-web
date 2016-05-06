package com.focusmate.wxpay.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tianyuwei
 * @version id: WXNotifyDTO, v 0.1 16/5/6 下午2:08 tianyuwei Exp $$
 */
@XmlRootElement(name = "xml")
public class WXNotifyDTO implements Serializable {
    private String appid;       // 公众账号ID
    private String openid;      // 用户标识
    private String mch_id;      // 商户号
    private String is_subscribe; // 是否关注公众账号
    private String nonce_str;   // 随机字符串
    private String product_id;  // 商品ID
    private String sign;        // 签名

    @XmlElement
    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    @XmlElement
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @XmlElement
    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    @XmlElement
    public String getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    @XmlElement
    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    @XmlElement
    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    @XmlElement
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
