package com.experian.pjsm.pesregafinidade.dto;

import java.io.Serializable;

public class LoginFormDTO implements Serializable {
	private static final long serialVersionUID = -579326929873561735L;
	
	private String username;
	private String password;

	public LoginFormDTO() {
	}

	public LoginFormDTO(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
