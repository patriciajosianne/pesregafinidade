package com.experian.pjsm.pesregafinidade.dto;

import java.util.List;

public class PessoaConsultaDTO {

	private String nome;
	private String telefone;
	private Integer idade;
	private String scoreDescricao; 
	private List<String> estados;
	
	public PessoaConsultaDTO(String nome, String telefone, Integer idade,
			String scoreDescricao, List<String> estados) {
		this.nome = nome;
		this.telefone = telefone;
		this.idade = idade;
		this.scoreDescricao = scoreDescricao;
		this.estados = estados;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	
	public String getScoreDescricao() {
		return scoreDescricao;
	}
	public void setScoreDescricao(String scoreDescricao) {
		this.scoreDescricao = scoreDescricao;
	}
	
	public List<String> getEstados() {
		return estados;
	}
	public void setEstados(List<String> estados) {
		this.estados = estados;
	}
	
}
