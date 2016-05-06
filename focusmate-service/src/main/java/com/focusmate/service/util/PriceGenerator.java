package com.focusmate.service.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.focusmate.datasource.entities.TimeDiscount;

public class PriceGenerator {

    private final static Logger logger = LoggerFactory.getLogger(PriceGenerator.class);

    public static Integer getSale(List<TimeDiscount> discountArray, Integer price) {

        Integer rebate = 100;

        try {

            /*
             * 忽略日期，只取时间, 86,400,000 ms (= 1000 ms/sec × 60 sec/min × 60 min/hr
             * × 24 hr/day).
             */
            int currentTime = (int) (new Date().getTime() % (24 * 60 * 60 * 1000L));

            /* 判断当前时间坐落于哪个时间段 */
            for (TimeDiscount timeDiscount : discountArray) {
                int startTime = (int) (timeDiscount.getDurationStart().getTime() % (24 * 60 * 60 * 1000L));
                int endTime = (int) (timeDiscount.getDurationEnd().getTime() % (24 * 60 * 60 * 1000L));

                /* 检测时间段，是否存在跨24点时间段 */
                if (startTime > endTime) {
                    int clock0 = 0;
                    int clock24 = (int) (24 * 60 * 60 * 1000);
                    if (currentTime > startTime && currentTime <= clock24) {
                        rebate = timeDiscount.getDiscount();
                        break;
                    }
                    if (currentTime > clock0 && currentTime <= endTime) {
                        rebate = timeDiscount.getDiscount();
                        break;
                    }
                }
                /* 正常时间段判断 */
                if (currentTime > startTime && currentTime <= endTime) {
                    rebate = timeDiscount.getDiscount();
                    break;
                }
            }
        } catch (Exception e) {
            logger.error("Station's discount has errors, exception is: {}", e.getMessage());
        }

        BigDecimal rebate_rate = new BigDecimal(Float.toString(rebate / 100.0f));

        /* 对折扣部分的价格做一个整型的转换 */
        int rebate_price = price - (int) ((BigDecimal.ONE.subtract(rebate_rate).floatValue()) * price);

        return rebate_price;
    }
}
