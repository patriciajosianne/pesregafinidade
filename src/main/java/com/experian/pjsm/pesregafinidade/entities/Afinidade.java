package com.experian.pjsm.pesregafinidade.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.experian.pjsm.pesregafinidade.enumeration.Estado;

@Entity
@Table(name = "afinidade")
public class Afinidade implements Serializable {
	private static final long serialVersionUID = -1900176476543673053L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String regiao;
	
	@ElementCollection(targetClass = Estado.class)
	@CollectionTable(name = "afinidade_estados", joinColumns = @JoinColumn(name = "afinidade_id"))
	@Column(name = "estado")
	@Enumerated(EnumType.STRING)
	private List<Estado> estados;
	
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
	
	public List<Estado> getEstados() {
		return estados;
	}
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

}
