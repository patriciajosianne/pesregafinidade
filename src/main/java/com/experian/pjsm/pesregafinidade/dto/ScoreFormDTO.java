package com.experian.pjsm.pesregafinidade.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScoreFormDTO {

	private String descricao;
	private BigDecimal scoreInicial;
	private BigDecimal scoreFinal;
	
	
	public ScoreFormDTO(String descricao, BigDecimal scoreInicial, BigDecimal scoreFinal) {
		this.descricao = descricao;
		this.scoreInicial = scoreInicial;
		this.scoreFinal = scoreFinal;
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
