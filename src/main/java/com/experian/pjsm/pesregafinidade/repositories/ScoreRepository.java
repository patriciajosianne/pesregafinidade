package com.experian.pjsm.pesregafinidade.repositories;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.experian.pjsm.pesregafinidade.entities.Score;

public interface ScoreRepository extends JpaRepository<Score, Long> {

	@Query("SELECT s FROM Score s WHERE s.scoreInicial <= :score AND s.scoreFinal >= :score")
	public Score buscaScore(BigDecimal score);
	
	@Query(" SELECT COUNT(s.id) 														" +
		   "	FROM Score s 															" +
		   " WHERE (s.scoreInicial <= :scoreIni AND s.scoreFinal >= :scoreIni)			" +
		   " 	OR (s.scoreInicial <= :scoreFim AND s.scoreFinal >= :scoreFim)			")
	public Long contaScorePeloIntervalo(BigDecimal scoreIni, BigDecimal scoreFim);
}
