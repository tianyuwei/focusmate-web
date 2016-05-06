package com.focusmate.service;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.focusmate.datasource.entities.Custom;
import com.focusmate.datasource.entities.UserInformation;
import com.focusmate.datasource.repository.CustomRepository;
import com.focusmate.datasource.repository.UserInformationRepository;

@Service
public class UserService {

    @Autowired
    private UserInformationRepository userInformationRepository;

    @Autowired
    private CustomRepository          customRepository;

    @PostConstruct
    protected void init() {

    }

    public UserInformation getUserInformation(Integer userId) {
        return this.userInformationRepository.findOne(userId);
    }

    public Custom getAccountInfo(Integer userId) {
        return this.customRepository.findByUserInformationUserId(userId);
    }

    @Transactional
    public boolean register(String userName, String password, String cellPhone, String nickName, String trueName,
                            Integer gender) {
        UserInformation info = this.userInformationRepository.findByUserNameAndPassword(userName, password);
        if (info != null) {
            return false;
        }

        info = new UserInformation();
        info.setCellPhone(cellPhone);
        info.setUserName(userName);
        info.setPassword(password);
        info.setNickName(nickName);
        info.setTrueName(trueName);
        info.setGender(gender);
        info = this.userInformationRepository.saveAndFlush(info);

        Custom custom = new Custom();
        custom.setCash(0);
        custom.setConsumeSum(0);
        custom.setCreateDate(new Date(System.currentTimeMillis()));
        custom.setInfo(info);
        custom.setFrozenCash(0);
        custom.setRechargeSum(0);
        custom.setWithdrawSum(0);
        this.customRepository.saveAndFlush(custom);
        return true;
    }

    public Custom query(Integer userId) {
        Custom custom = this.customRepository.findByUserInformationUserId(Integer.valueOf(userId));
        return custom;
    }

    public Integer login(String userName, String password) {
        UserInformation info = this.userInformationRepository.findByUserNameAndPassword(userName, password);
        if (info == null) {
            return null;
        }
        return info.getUserId();
    }

}
