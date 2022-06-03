package com.experian.pjsm.pesregafinidade.dto;

import java.util.List;

public class AfinidadeDTO {

	private Long id;
	private String regiao;
	private List<String> estados;
	
	public AfinidadeDTO(Long id, String regiao, List<String> estados) {
		this.id = id;
		this.regiao = regiao;
		this.estados = estados;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getRegiao() {
		return regiao;
	}
	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}
	
	public List<String> getEstados() {
		return estados;
	}
	public void setEstados(List<String> estados) {
		this.estados = estados;
	}
	
}
