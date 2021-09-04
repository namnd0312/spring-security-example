package com.namnd.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.namnd.configs.enums.MessageEnum;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseDTO{
	private String code;
	
	private String desc;
	
	private Object result;
	
	
	public static ResponseDTO ok(Object result) {
		return new ResponseDTO("00", "OK", result);
	}
	
	public static ResponseDTO ok() {
		return new ResponseDTO("00", "OK");
	}
	
	public static ResponseDTO error(MessageEnum messageEnum) {
		return new ResponseDTO(messageEnum.getCode(), messageEnum.getMessage());
	}
	
	public static ResponseDTO error(MessageEnum messageEnum, String result) {
		return new ResponseDTO(messageEnum.getCode(), messageEnum.getMessage(), result);
	}



	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public Object getResult() {
		return result;
	}


	public void setResult(Object result) {
		this.result = result;
	}


	public ResponseDTO() {
		super();
	}


	public ResponseDTO(String code, String desc, Object result) {
		super();
		this.code = code;
		this.desc = desc;
		this.result = result;
	}

	public ResponseDTO(String code, String desc) {
		super();
		this.code = code;
		this.desc = desc;
	}
}
