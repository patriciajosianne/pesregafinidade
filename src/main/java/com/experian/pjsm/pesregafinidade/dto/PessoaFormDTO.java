package com.experian.pjsm.pesregafinidade.dto;

import java.math.BigDecimal;

public class PessoaFormDTO {

	private String nome;
	private String telefone;
	private Integer idade;
	private String cidade;
	private String estado;
	private String regiao;
	private BigDecimal score;
	
	public PessoaFormDTO(String nome, String telefone, Integer idade,
			String cidade, String estado, String regiao, BigDecimal score) {
		this.nome = nome;
		this.telefone = telefone;
		this.idade = idade;
		this.cidade = cidade;
		this.estado = estado;
		this.regiao = regiao;
		this.score = score;
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
	
	public String getRegiao() {
		return regiao;
	}
	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}
	
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}

}
