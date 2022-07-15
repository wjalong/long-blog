package com.wjalong.util.web;

import lombok.Data;

import java.io.Serializable;

@Data
public class SessionUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5533926578698002138L;
	private Integer userId;
	private String token;
}
