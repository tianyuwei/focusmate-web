package com.focusmate.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.focusmate.datasource.entities.WxTransaction;
import com.focusmate.datasource.repository.WxTransactionRepository;
import com.focusmate.service.util.Enums;

@Service
public class WXPayService {
    private final Logger    logger = LoggerFactory.getLogger(WXPayService.class);

    @Autowired
    WxTransactionRepository wxTransactionRepository;

    /**
     * Order Transcation for wxpay
     * 
     * @param customId
     * @param stationId
     * @param fee
     */
    @Transactional
    public void orderTransaction(String transcationId, String customId, Integer stationId, Integer fee) {
        logger.debug("Order Transcation for wxpay in the center of server");

        /* create a wxpay transcation */
        WxTransaction transcation = new WxTransaction();
        transcation.setTranscationId(transcationId);
        transcation.setCustomId(customId);
        transcation.setStationId(stationId);
        transcation.setStatus(Enums.WxPayEnum.STATUS_ORDER);
        transcation.setFee(fee);
        transcation.setOrderTime(new Date());

        wxTransactionRepository.save(transcation);
    }

    @Transactional
    public WxTransaction updateTranscationStatus(String transcationId, Integer status) {

        WxTransaction transcation = wxTransactionRepository.findByTranscationId(transcationId);
        if (null == transcation) {
            logger.error("Set WXPay transcation status failed, transcation id {} doesn't exist" + transcationId);
            return null;
        }

        /* 防止微信服务器结果通知，重复设置订单状态 */
        if (status == Enums.WxPayEnum.STATUS_PAY && transcation.getStatus() != Enums.WxPayEnum.STATUS_ORDER) {
            return null;
        }

        if (status == Enums.WxPayEnum.STATUS_FINISH || status == Enums.WxPayEnum.STATUS_TIMTOUT
            || status == Enums.WxPayEnum.STATUS_REMOTE_RESET || status == Enums.WxPayEnum.STATUS_REMOTE_STOP
            || status == Enums.WxPayEnum.STATUS_NOT_FIT) {
            transcation.setFinishTime(new Date());
        }

        /* change the status of transcation */
        transcation.setStatus(status);

        return wxTransactionRepository.save(transcation);
    }

    public WxTransaction getWxTransaction(Integer stationId) {

        List<WxTransaction> transcations = wxTransactionRepository.findByStatusAndStationId(Enums.WxPayEnum.STATUS_PAY,
            stationId);

        if (null == transcations || transcations.isEmpty()) {
            return null;
        }

        return transcations.get(0);
    }
}
