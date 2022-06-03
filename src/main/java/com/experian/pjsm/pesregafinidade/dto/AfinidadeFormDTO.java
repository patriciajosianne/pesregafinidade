package com.experian.pjsm.pesregafinidade.dto;

import java.util.List;

public class AfinidadeFormDTO {

	private String regiao;
	private List<String> estados;
	
	public AfinidadeFormDTO(String regiao, List<String> estados) {
		this.regiao = regiao;
		this.estados = estados;
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
