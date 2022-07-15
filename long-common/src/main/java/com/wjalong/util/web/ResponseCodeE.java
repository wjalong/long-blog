package com.wjalong.util.web;

public enum ResponseCodeE {
	OK("200","成功"),
	//系统异常代码
	SYS_500("500","网络异常，请重新尝试"),
	BIZ_PARAM_MISS("901","缺失关键参数"),
	BIZ_PARAM_FORMAT_ERROR("902","{param}内容格式不符合"),
	//用户模块2xx
	USER_LOGIN_ERROR("701", "手机号或密码错误"),
	USER_MOBILE_EXIST("702", "手机已被注册"),
	USER_MOBILE_FORMAT_EROR("703", "手机号格式错误"),
	USER_SMSCODE_VALIDAET_EROR("704", "手机号格式错误"),
	//sms
	SMS_VALIDATE_ERROR("705","验证码错误");
    // 成员变量  
    private String code;
    private String msg;  
    // 构造方法  
    private ResponseCodeE(String code, String msg) {
        this.code = code;  
        this.msg = msg;  
    }  
    // 普通方法  
    public static String getDesc(Integer code) {
        for (ResponseCodeE c : ResponseCodeE.values()) {  
            if (c.getCode() .equals(code)  ) {  
                return c.msg;  
            }  
        }  
        return null;  
    }
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}  
    // get set 方法  
	
   
} 
