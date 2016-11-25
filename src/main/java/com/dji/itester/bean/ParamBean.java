package com.dji.itester.bean;

/**
 * 传入参数的bean文件
 * 
 * @author Charlie.Chen
 *
 */
public class ParamBean {

	String email; 
	String password;
	String expResu;// 期望结果
	String actResu;// 实际结果
	
	String pass;// 是否通过
	String desc;// 描述

	
	public ParamBean() {
	}
	
	public ParamBean(String email, String password, String expResu, String actResu, String pass, String desc) {
		super();
		this.email = email;
		this.password = password;
		this.expResu = expResu;
		this.actResu = actResu;
		this.pass = pass;
		this.desc = desc;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public String getExpResu() {
		return expResu;
	}
	public void setExpResu(String expResu) {
		this.expResu = expResu;
	}

	public String getActResu() {
		return actResu;
	}
	public void setActResu(String actResu) {
		this.actResu = actResu;
	}

	
	@Override
	public String toString() {
		return "ParamBean [email=" + email + ", password=" + password + ", expResu=" + expResu + ", actResu=" + actResu
				+ ", pass=" + pass + ", desc=" + desc + "]";
	}

	public String getPss() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}