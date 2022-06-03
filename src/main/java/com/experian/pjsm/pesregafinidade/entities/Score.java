package com.experian.pjsm.pesregafinidade.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "score")
public class Score implements Serializable {
	private static final long serialVersionUID = -7079472641127692606L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private BigDecimal scoreInicial;
	private BigDecimal scoreFinal;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public BigDecimal getScoreInicial() {
		return scoreInicial;
	}
	public void setScoreInicial(BigDecimal scoreInicial) {
		this.scoreInicial = scoreInicial;
	}	
	
	public BigDecimal getScoreFinal() {
		return scoreFinal;
	}
	public void setScoreFinal(BigDecimal scoreFinal) {
		this.scoreFinal = scoreFinal;
	}
	
}
