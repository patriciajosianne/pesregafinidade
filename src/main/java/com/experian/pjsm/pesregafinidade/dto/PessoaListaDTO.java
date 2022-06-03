package com.experian.pjsm.pesregafinidade.dto;

import java.util.List;

public class PessoaListaDTO {

	private String nome;
	private String cidade;
	private String estado;
	private String scoreDescricao; 
	private List<String> estados;
	
	public PessoaListaDTO(String nome, String cidade, String estado,
			String scoreDescricao, List<String> estados) {
		this.nome = nome;
		this.cidade = cidade;
		this.estado = estado != null ? estado.toString() : null;
		this.scoreDescricao = scoreDescricao;
		this.estados = estados;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
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
