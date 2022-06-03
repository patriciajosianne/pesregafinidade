package com.experian.pjsm.pesregafinidade.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.experian.pjsm.pesregafinidade.entities.Afinidade;

public interface AfinidadeRepository extends JpaRepository<Afinidade, Long> {

	public List<Afinidade> findByRegiao(String regiao); 
}
