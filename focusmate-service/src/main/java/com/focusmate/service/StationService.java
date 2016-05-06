package com.focusmate.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.focusmate.controller.dto.DTO;
import com.focusmate.controller.dto.StationCodeVerifyResultDTO;
import com.focusmate.datasource.entities.Custom;
import com.focusmate.datasource.entities.Flowing;
import com.focusmate.datasource.entities.Station;
import com.focusmate.datasource.entities.Transaction;
import com.focusmate.datasource.repository.CustomRepository;
import com.focusmate.datasource.repository.FlowingRepository;
import com.focusmate.datasource.repository.StationRepository;
import com.focusmate.datasource.repository.TransactionRepository;

@Service
public class StationService {
    private final Logger     logger = LoggerFactory.getLogger(StationService.class);
    @Autowired
    StationRepository        stationRepository;

    @Autowired
    TransactionRepository    transactionRepository;

    @Autowired
    private CustomRepository customRepository;

    @Autowired
    FlowingRepository        flowingRepository;

    public Station stationLogin(String username, String password) {
        Station station = stationRepository.findByUserNameAndPassword(username, password);
        if (station != null)
            return station;
        else
            return null;
    }

    @Transactional
    public StationCodeVerifyResultDTO verifyCode(String stationName, String codeId) {
        StationCodeVerifyResultDTO dto = new StationCodeVerifyResultDTO();

        try {
            /* Check whether the station is normal (0 means normal) */
            Station station = stationRepository.findByUserName(stationName);
            if (null == station || station.getStatus() != 0) {
                dto.setFailResult();
                dto.setReason("网点状态异常");
                return dto;
            }
            /* Check whether the code is correct */
            Transaction transaction = transactionRepository.findByOrderCode(codeId);
            if (null == transaction) {
                dto.setFailResult();
                dto.setReason("条形码不正确");
                return dto;
            }
            /*
             * Check the transaction status
             */
            if (transaction.getStatus() == 2) {
                dto.setSuccessResult();
                dto.setReason("该订单已被确认");
                dto.setCode(transaction.getFlowingId().toString());
                dto.setPrice(station.getPrice().toString());
                return dto;
            }
            if (transaction.getStatus() == 0) {
                dto.setFailResult();
                dto.setReason("该订单已过期");
                return dto;
            }
            /* Check whether the app custom's cash is enough */
            Custom custom = customRepository.findOne(transaction.getCustomId());
            if (null != custom && custom.getCash() < station.getPrice()) {
                dto.setFailResult();
                dto.setReason("当前账户余额不足");
                return dto;
            }

            /* create a flowing for this code verify */
            Flowing flowing = new Flowing();
            flowing.setCustomId(custom.getId());
            flowing.setStationId(station.getId());
            flowing.setService(0);// 0 means the service of washing car
            flowing.setStatus(1);// 1 means cold the cash
            flowing.setSum(station.getPrice() * -1);
            flowing.setStartTime(new Date());// set current time
            Flowing result_flowing = flowingRepository.save(flowing);

            /* update the transaction based on current created flowing */
            transaction.setFlowingId(result_flowing.getId());
            transaction.setStationId(station.getId());
            transaction.setStatus(2);// 2 means order is beginning
            transactionRepository.save(transaction);

            /* froze the custom's cash */
            int currentCash = custom.getCash() - station.getPrice();
            int currentFrozenCash = custom.getFrozenCash() + station.getPrice();
            custom.setCash(currentCash);
            custom.setFrozenCash(currentFrozenCash);
            customRepository.save(custom);

            dto.setSuccessResult();
            dto.setPrice(station.getPrice().toString());
            dto.setCode(result_flowing.getId().toString());
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Station Code Verify failed CaseID='{}', exception is: {}", codeId, e.getMessage());
            dto.setFailResult();
            dto.setReason("Server Internal Error");
            return dto;
        }
    }

    @Transactional
    public DTO balanceCost(String stationName, String codeId, int flowingId) {
        DTO dto = new DTO();
        /* Check whether the code is correct */
        Transaction transaction = transactionRepository.findByOrderCode(codeId);
        if (null == transaction) {
            dto.setFailResult();
            dto.setReason("条形码不正确");
            return dto;
        }
        if (!transaction.getFlowingId().equals(flowingId)) {
            dto.setFailResult();
            dto.setReason("流水号不正确");
            return dto;
        }
        /* Check the transaction status ,2 mean this custom's order is beginning */
        if (transaction.getStatus() != 2) {
            dto.setFailResult();
            dto.setReason("该订单状态异常");
            return dto;
        }

        Flowing flowing = flowingRepository.findOne(flowingId);

        /* banlance custom's cost */
        Custom custom = customRepository.findOne(flowing.getCustomId());
        custom.setFrozenCash(custom.getFrozenCash() + flowing.getSum());
        custom.setConsumeSum(custom.getConsumeSum() - flowing.getSum());
        customRepository.save(custom);

        /* update the transaction */
        transaction.setStatus(0);// 0 mean finish the deal
        transaction.setFinishTime(new Date());
        transactionRepository.save(transaction);

        /* update the flowing */
        flowing.setStatus(0);// 0 mean finish the deal
        flowing.setFinishTime(new Date());
        flowingRepository.save(flowing);

        dto.setSuccessResult();
        return dto;
    }
}
