package com.focusmate.controller.dto;

public class WXTransactionDTO extends DTO {
	/**
     * 
     */
	private static final long serialVersionUID = -9185444515377467888L;

	String transcationId;

	Integer commandType;

	public String getTranscationId() {
		return transcationId;
	}

	public void setTranscationId(String transcationId) {
		this.transcationId = transcationId;
	}

	public Integer getCommandType() {
		return commandType;
	}

	public void setCommandType(Integer commandType) {
		this.commandType = commandType;
	}
}
