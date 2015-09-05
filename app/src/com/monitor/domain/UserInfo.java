package com.monitor.domain;

import java.io.Serializable;

import com.monitor.exception.UserMsgException;

/*
 * ���ڴ洢�û���Ϣ
 * */
public class UserInfo implements Serializable{
	private int user_id;
	private String username;
	private String nickname;
	private String password;
	private String password2;
	private String gender;
	private String phone;
	private String email;
	
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * ����û���Ϣ��ʽ�Ƿ���ȷ
	 * 
	 * @throws UserMsgException
	 */
	public void checkValue() throws UserMsgException{
		if(null == username || "".equals(username)){
			throw new UserMsgException("�û�������Ϊ��");
		}
		if(null == nickname || "".equals(nickname)){
			throw new UserMsgException("�ǳƲ���Ϊ��");
		}
		if(null == password || "".equals(password)){
			throw new UserMsgException("���벻��Ϊ��");
		}
		if(null == password2 || "".equals(password2)){
			throw new UserMsgException("ȷ�����벻��Ϊ��");
		}
		if(!password.toString().equals(password2.toString())){
			throw new UserMsgException("�������벻һ��");
		}
		if(null == phone || "".equals(phone)){
			throw new UserMsgException("�ֻ����벻��Ϊ��");
		}
		if(11 != phone.length()){
			throw new UserMsgException("�ֻ����볤�Ȳ���");
		}
		if(null == email || "".equals(email)){
			throw new UserMsgException("���䲻��Ϊ��");
		}
		if(!email.matches("^\\w+@\\w+(\\.\\w+)+$")){
			throw new UserMsgException("�����ʽ����ȷ");
		}
		
	}
}
