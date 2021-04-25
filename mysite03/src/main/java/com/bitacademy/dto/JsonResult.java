package com.bitacademy.dto;

public class JsonResult {
	private String result; //success or fail
	private Object data; // if result = success data else null
	private String message;	// if result = success null else error message
	
	private JsonResult() {
		
	}
	
	private JsonResult(String msg) {
		this.result="fail";
		this.message=msg;
	}
	private JsonResult(Object data) {
		this.result="success";
		this.data=data;
		
	}
	public static JsonResult fail(String msg) {
		return new JsonResult(msg); 
	}
	
	public static JsonResult success(Object data) {
		return  new JsonResult(data);
		
	}
	public String getResult() {
		return result;
	}
	
	public Object getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}

	
	

}
