package com.experian.pjsm.pesregafinidade.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.experian.pjsm.pesregafinidade.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
