package com.focusmate.controller.dto;

import java.io.Serializable;

public class ResponseResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3876653558299675409L;

	private int status;

	private String message;

	private String path;

	private Object data;

	private static final int SUCCESS = 1;

	private static final int FAIL = 8;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	private ResponseResult() {

	}

	public static ResponseResult createSuccessResponseResult() {
		ResponseResult result = new ResponseResult();
		result.setStatus(SUCCESS);
		result.setMessage("Success");
		return result;
	}

	public static ResponseResult createFailResponseResult() {
		ResponseResult result = new ResponseResult();
		result.setStatus(FAIL);
		return result;
	}
}
