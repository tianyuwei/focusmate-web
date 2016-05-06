package com.focusmate.controller.dto;

import java.io.Serializable;

public class DTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3133777975647204878L;

    public static final String SUCCESS = "succeed";

    public static final String FAIL = "failed";

    private String result;

    private String reason;

    public String getResult() {
        return result;
    }

    public void setSuccessResult() {
        this.result = SUCCESS;
    }

    public void setFailResult() {
        this.result = FAIL;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
