package com.focusmate.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.focusmate.controller.dto.ResponseResult;
import com.focusmate.datasource.entities.Custom;
import com.focusmate.service.UserService;

@RestController
@RequestMapping(value = "/user", produces = "application/json")
public class UserInfoController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ResponseResult register(@RequestParam(value = "userName", required = true) String userName,
                                   @RequestParam(value = "password", required = true) String password,
                                   @RequestParam(value = "cellPhone") String cellPhone,
                                   @RequestParam(value = "nickName") String nickName,
                                   @RequestParam(value = "trueName") String trueName,
                                   @RequestParam(value = "gender", required = true) Integer gender) {
        if (!this.userService.register(userName, password, cellPhone, nickName, trueName, gender)) {
            return ResponseResult.createFailResponseResult();
        }
        Integer userId = this.userService.login(userName, password);
        ResponseResult result = ResponseResult.createSuccessResponseResult();
        result.setData(userId);
        result.setPath("/user/register");
        return result;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseResult register(@RequestBody HashMap request) {
        String userName = (String) request.get("userName");
        String password = (String) request.get("password");
        String cellPhone = (String) request.get("cellPhone");
        String nickName = (String) request.get("nickName");
        String trueName = (String) request.get("trueName");
        Integer gender = (Integer) request.get("gender");
        if (!this.userService.register(userName, password, cellPhone, nickName, trueName, gender)) {
            return ResponseResult.createFailResponseResult();
        }
        Integer userId = this.userService.login(userName, password);
        ResponseResult result = ResponseResult.createSuccessResponseResult();
        result.setData(userId);
        result.setPath("/user/register");

        return result;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseResult login(@RequestParam(value = "userName", required = true) String userName,
                                @RequestParam(value = "password", required = true) String password) {
        ResponseResult result = null;
        Integer userId = this.userService.login(userName, password);
        if (userId == null) {
            result = ResponseResult.createFailResponseResult();
        } else {
            result = ResponseResult.createSuccessResponseResult();
            result.setData(userId);
            result.setPath("/user/login");

        }
        return result;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseResult login(@RequestBody HashMap request) {
        String userName = (String) request.get("userName");
        String password = (String) request.get("password");
        ResponseResult result = null;
        Integer userId = this.userService.login(userName, password);
        if (userId == null) {
            result = ResponseResult.createFailResponseResult();
        } else {
            result = ResponseResult.createSuccessResponseResult();
            result.setData(userId);
            result.setPath("/user/login");

        }
        return result;
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public ResponseResult requestInfo(@RequestBody HashMap request) {
        Integer userId = (Integer) request.get("userId");
        ResponseResult result = null;

        Custom custom = this.userService.query(userId);

        if (custom == null) {
            result = ResponseResult.createFailResponseResult();
            result.setMessage("User with id " + userId + " not exist");
        } else {
            result = ResponseResult.createSuccessResponseResult();
            custom.getInfo().setPassword(null);
            result.setData(custom);
            result.setPath("/user/query");
        }

        return result;
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResponseResult requestInfo(@RequestParam(value = "userId") Integer userId) {
        ResponseResult result = null;

        Custom custom = this.userService.query(userId);

        if (custom == null) {
            result = ResponseResult.createFailResponseResult();
            result.setMessage("User with id " + userId + " not exist");
        } else {
            result = ResponseResult.createSuccessResponseResult();
            custom.getInfo().setPassword(null);
            result.setData(custom);
            result.setPath("/user/query");

        }

        return result;
    }
}
