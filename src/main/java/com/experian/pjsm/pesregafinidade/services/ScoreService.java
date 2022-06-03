package com.experian.pjsm.pesregafinidade.services;

import java.math.BigDecimal;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.experian.pjsm.pesregafinidade.dto.ScoreDTO;
import com.experian.pjsm.pesregafinidade.dto.ScoreFormDTO;
import com.experian.pjsm.pesregafinidade.entities.Score;
import com.experian.pjsm.pesregafinidade.repositories.ScoreRepository;

@Service
public class ScoreService {

	@Autowired
	private ScoreRepository scoreRepository;
	
	public ScoreDTO saveScore(ScoreFormDTO scoreDTO) throws Exception {
		this.validaDados(scoreDTO);
		Score score = new Score();
		score.setDescricao(scoreDTO.getDescricao());
		score.setScoreInicial(scoreDTO.getScoreInicial());
		score.setScoreFinal(scoreDTO.getScoreFinal());
		score = this.scoreRepository.saveAndFlush(score);
		
		if((score.getId() != null) && (score.getId() > 0L)) {
			return new ScoreDTO(score.getId(), score.getDescricao(), score.getScoreInicial(), score.getScoreFinal());
		} else {
			throw new Exception("Erro inesperado ao cadastrar a pessoa.");
		}
	}
	
	private void validaDados(ScoreFormDTO scoreDTO) throws ValidationException {
		if((scoreDTO.getDescricao() == null) || (scoreDTO.getDescricao().trim().isEmpty())) {
			throw new ValidationException("O preenchimento da descrição é obrigatório.");
		}	
		
		if((scoreDTO.getScoreInicial() == null) || (scoreDTO.getScoreInicial().compareTo(BigDecimal.ZERO) < 0) || (scoreDTO.getScoreInicial().compareTo(new BigDecimal(1000)) > 0)) {
			throw new ValidationException("O preenchimento do score inicial é obrigatório e deve ser um valor maior ou igual a 0 e menor ou igual a 1000.");
		}
		
		if((scoreDTO.getScoreFinal() == null) || (scoreDTO.getScoreFinal().compareTo(BigDecimal.ZERO) < 0) || (scoreDTO.getScoreFinal().compareTo(new BigDecimal(1000)) > 0)) {
			throw new ValidationException("O preenchimento do score final é obrigatório e deve ser um valor maior ou igual a 0 e menor ou igual a 1000.");
		}
		
		if(scoreDTO.getScoreInicial().compareTo(scoreDTO.getScoreFinal()) >= 0) {
			throw new ValidationException("O score final deve ser maior que o score inicial.");
		}
		
		if(this.scoreRepository.contaScorePeloIntervalo(scoreDTO.getScoreInicial(), scoreDTO.getScoreFinal()) > 0L) {
			throw new ValidationException("Já existe score cadastrado que utiliza este intervalo ou parte dele.");
		}
	}
	
	public ScoreDTO buscaScore(Long id) throws Exception {
		if((id == null) || (id <= 0L)) {
			throw new ValidationException("O id é obrigatório e deve ser um valor maior que zero.");
		}
		Score score = this.scoreRepository.findById(id).orElse(null);
		if(score != null) {
			return new ScoreDTO(score.getId(), score.getDescricao(), score.getScoreInicial(), score.getScoreFinal());
		} else {
			return null;
		}
	}

	public String buscaDescricaoByScore(BigDecimal score) {
		Score retorno = this.scoreRepository.buscaScore(score);
		return retorno != null ? retorno.getDescricao() : "";
	}

}