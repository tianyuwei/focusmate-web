package com.focusmate.controller.dto;

public class StationCodeVerifyResultDTO extends DTO {

    /**
     * 
     */
    private static final long serialVersionUID = -1305133710477447363L;

    private String price;

    private String code;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
