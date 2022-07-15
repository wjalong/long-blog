package com.wjalong.common;

import java.util.Date;

public class ResultMsg<T> {

	private String code = "success";
	private String msg = "";
	private T data;
	private Date reponseTime = new Date();

	public ResultMsg() {
	}

	private ResultMsg(String code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public T getData() {
		return data;
	}

	public static <T extends Object> ResultMsg<T> ok(T obj){
		return new ResultMsg<T>("success","成功",obj);
	}

	public static <T extends Object> ResultMsg<T> ok(){
		return new ResultMsg<T>("success","成功",null);
	}

	public static <T extends Object> ResultMsg<T> fail(String errorCode,String errorMsg){
		return new ResultMsg<T>(errorCode, errorMsg, null);
	}

	public static <T extends Object> ResultMsg<T> fail(String errorCode,String errorMsg,T obj){
		return new ResultMsg<T>(errorCode, errorMsg, obj);
	}

	public Date getReponseTime() {
		return reponseTime;
	}
}
