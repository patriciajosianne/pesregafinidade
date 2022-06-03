package com.experian.pjsm.pesregafinidade.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScoreDTO {

	private Long id;
	private String descricao;
	private BigDecimal scoreInicial;
	private BigDecimal scoreFinal;
	
	
	public ScoreDTO(Long id, String descricao, BigDecimal scoreInicial, BigDecimal scoreFinal) {
		this.id = id;
		this.descricao = descricao;
		this.scoreInicial = scoreInicial;
		this.scoreFinal = scoreFinal;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@JsonProperty("scoreDescricao")
	public String getDescricao() {
		return descricao;
	}
	@JsonProperty("scoreDescricao")
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@JsonProperty("inicial")
	public BigDecimal getScoreInicial() {
		return scoreInicial;
	}
	@JsonProperty("inicial")
	public void setScoreInicial(BigDecimal scoreInicial) {
		this.scoreInicial = scoreInicial;
	}
	
	@JsonProperty("final")
	public BigDecimal getScoreFinal() {
		return scoreFinal;
	}
	@JsonProperty("final")
	public void setScoreFinal(BigDecimal scoreFinal) {
		this.scoreFinal = scoreFinal;
	}
	
}
