package com.experian.pjsm.pesregafinidade.dto;

import java.io.Serializable;

public class TokenDTO implements Serializable {
	private static final long serialVersionUID = -5820526819366290499L;
	
	private final String jwttoken;

	public TokenDTO(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}

}
