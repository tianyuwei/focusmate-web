package com.focusmate.wxpay.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * User: rizenguo Date: 2014/10/29 Time: 14:18
 */
public class RandomStringGenerator {

    /**
     * 获取一定长度的随机字符串
     * 
     * @param length
     *            指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String generateOutTradeNo() {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        // add the merchant ID
        sb.append(WxPayConfigure.getMchid());

        // add the current time
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        sb.append(formatter.format(new Date()).toString());

        // add the random number
        sb.append(random.nextInt(999));

        return sb.toString();
    }
}
