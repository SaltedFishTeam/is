package com.is.json.status;

public class Status {
	
	public final static int SUCCESS = 0;
	public final static int FAILED = -1;
	public 
	final static String SUCCESS_LOGIN_MSG = "登录成功";
	public static String SUCCESS_REGISTER_MSG = "注册成功";

	public final static String FAILED_LOGIN_MSG = "登录失败";
	public final static String FAILED_REGISTER_MSG = "注册失败";
	//success
	private String status;
	
	//成功0
	private int code;
	
	//错误原因
	private String msg;

	private Object o;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int success2) {
		this.code = success2;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Status(String status, int code, String msg) {
		super();
		this.status = status;
		this.code = code;
		this.msg = msg;
	}
	
	public Object getO() {
		return o;
	}
	public void setO(Object o) {
		this.o = o;
	}
}
