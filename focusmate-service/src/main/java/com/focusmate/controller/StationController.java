package com.focusmate.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.focusmate.controller.dto.DTO;
import com.focusmate.controller.dto.StationCodeVerifyResultDTO;
import com.focusmate.controller.dto.StationResultDTO;
import com.focusmate.controller.dto.WXTransactionDTO;
import com.focusmate.datasource.entities.Station;
import com.focusmate.datasource.entities.Workshop;
import com.focusmate.datasource.entities.WxTransaction;
import com.focusmate.datasource.repository.StationRepository;
import com.focusmate.datasource.repository.TimeDiscountRepository;
import com.focusmate.datasource.repository.WorkshopRepository;
import com.focusmate.service.StationService;
import com.focusmate.service.WXPayService;
import com.focusmate.service.util.PriceGenerator;

@RestController
@RequestMapping(value = "/station")
public class StationController {
    private final Logger           logger = LoggerFactory.getLogger(StationController.class);

    @Autowired
    private StationService         stationService;

    @Autowired
    private WXPayService           wxPayService;

    @Autowired
    private StationRepository      stationRepository;

    @Autowired
    private WorkshopRepository     workshopRepository;

    @Autowired
    private TimeDiscountRepository timeDiscountRepository;

    /**
     * This method used to station login
     * 
     * @param usr
     * @param psw
     * @param request
     * @return StationResultDTO
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public StationResultDTO loginStation(@RequestParam(value = "usr") String usr,
                                         @RequestParam(value = "psw") String psw, HttpServletRequest request) {
        logger.debug("Station login Request to do the login action");

        Station station = stationService.stationLogin(usr, psw);
        StationResultDTO dto = new StationResultDTO();
        if (station != null) {
            request.getSession().setAttribute("station_id", station.getId());
            request.getSession().setAttribute("station_state", "Idel");
            dto.setSuccessResult();
            dto.setReason("login success");
            dto.setStation(station);
        } else {
            dto.setFailResult();
            dto.setReason("login failed, usrname or password is wrong");
            dto.setStation(null);
        }
        return dto;
    }

    /**
     * 站点状态报告
     * 
     * @param usr
     * @param state
     * @param request
     * @return
     */
    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public DTO reportStationStatus(@RequestParam(value = "usr") String usr,
                                   @RequestParam(value = "state") String state, HttpServletRequest request) {
        if (null == usr || null == state) {
            DTO dto = new DTO();
            dto.setFailResult();
            dto.setReason("参数内容不能为空");
            return dto;
        }
        /* check and parse the state */
        switch (state) {
            case "Idel":
                request.getSession().setAttribute("station_state", "Idel");
                break;
            case "Busy":
                request.getSession().setAttribute("station_state", "Busy");
                break;
            case "Error":
                request.getSession().setAttribute("station_state", "Error");
                break;
            default:
                DTO dto = new DTO();
                dto.setFailResult();
                dto.setReason("state参数错误");
                return dto;
        }
        DTO dto = new DTO();
        dto.setSuccessResult();
        return dto;
    }

    @RequestMapping(value = "/plc-report", method = RequestMethod.GET)
    public DTO reportWorkshopStatus(@RequestParam(value = "usr") String usr,
                                    @RequestParam(value = "status") String status, HttpServletRequest request) {
        if (null == usr || null == status) {
            DTO dto = new DTO();
            dto.setFailResult();
            dto.setReason("参数内容不能为空");
            return dto;
        }

        Integer stationId = (Integer) request.getSession().getAttribute("station_id");

        /* 创建新的 plc 记录 */
        Workshop workshop = new Workshop();
        workshop.setStationId(stationId);
        workshop.setStatus(status);
        workshop.setRecordTime(new Date());
        workshopRepository.save(workshop);

        DTO dto = new DTO();
        dto.setSuccessResult();
        return dto;
    }

    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public StationCodeVerifyResultDTO verifyCode(@RequestParam(value = "usr") String usr,
                                                 @RequestParam(value = "caseid") String caseid,
                                                 HttpServletRequest request) {
        if (null == usr || null == caseid) {
            StationCodeVerifyResultDTO dto = new StationCodeVerifyResultDTO();
            dto.setFailResult();
            dto.setReason("参数内容不能为空");
            return dto;
        }

        String station_State = (String) request.getSession().getAttribute("station_state");
        if (!("Idel").equals(station_State)) {
            StationCodeVerifyResultDTO dto = new StationCodeVerifyResultDTO();
            dto.setFailResult();
            dto.setReason("网点正忙，请稍后再试");
            return dto;
        }
        return stationService.verifyCode(usr, caseid);
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public DTO cleanCost(@RequestParam(value = "usr") String usr, @RequestParam(value = "caseid") String caseid,
                         @RequestParam(value = "code") int code, @RequestParam(value = "method") int method) {
        if (null == usr || null == caseid) {
            DTO dto = new DTO();
            dto.setFailResult();
            dto.setReason("参数内容不能为空");
            return dto;
        }

        /* when the method is 0, do the balance cost */
        if (method == 0) {
            return stationService.balanceCost(usr, caseid, code);
        }

        DTO dto = new DTO();
        dto.setFailResult();
        dto.setReason("method内容错误");
        return dto;

    }

    @RequestMapping(value = "/getwxpay", method = RequestMethod.GET)
    public DTO getPaySign(@RequestParam(value = "usr") String usr, HttpServletRequest request) {
        if (null == usr) {
            DTO dto = new DTO();
            dto.setFailResult();
            dto.setReason("参数内容不能为空");
            return dto;
        }
        Integer stationId = (Integer) request.getSession().getAttribute("station_id");
        Station station = stationRepository.findOne(stationId);

        WXTransactionDTO dto = new WXTransactionDTO();
        dto.setCommandType(station.getCommandType());// 设置plc命令类型

        /* 检查plc命令的类型 ，更新访问时间 */
        if (0 != station.getCommandType()) {
            station.setCommandType(0);
        }
        station.setVisitDate(new Date());
        stationRepository.save(station);

        /* 查询微信订单 */
        WxTransaction transaction = wxPayService.getWxTransaction(station.getId());
        if (null == transaction) {
            dto.setFailResult();
            dto.setReason("当前无订单");
            return dto;
        }

        dto.setSuccessResult();
        dto.setReason("微信订单");
        dto.setTranscationId(transaction.getTranscationId());

        return dto;
    }

    @RequestMapping(value = "/clearwx", method = RequestMethod.GET)
    public DTO cleanWXCost(@RequestParam(value = "transactionId") String transactionId,
                           @RequestParam(value = "status") Integer status) {

        /* 检查参数合法性 */
        if (null == transactionId || null == status) {
            DTO dto = new DTO();
            dto.setFailResult();
            dto.setReason("参数内容不能为空");
            return dto;
        }

        /* 更新微信订单状态 */
        WxTransaction transaction = wxPayService.updateTranscationStatus(transactionId, status);
        if (null == transaction) {
            DTO dto = new DTO();
            dto.setFailResult();
            dto.setReason("订单号不存在");
            return dto;
        }

        DTO dto = new DTO();
        dto.setSuccessResult();
        return dto;
    }

    /**
     * 时间折扣，测试接口
     * 
     * @param price
     * @param request
     * @return
     */
    @RequestMapping(value = "/discount", method = RequestMethod.GET)
    public String discount(@RequestParam(value = "price") Integer price, HttpServletRequest request) {

        return PriceGenerator.getSale(timeDiscountRepository.findByProductId(4), price).toString();
    }
}
