package com.experian.pjsm.pesregafinidade.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.experian.pjsm.pesregafinidade.ApplicationConfigTest;
import com.experian.pjsm.pesregafinidade.dto.ScoreFormDTO;
import com.experian.pjsm.pesregafinidade.entities.Score;
import com.experian.pjsm.pesregafinidade.repositories.ScoreRepository;

@DisplayName("ScoreServiceTest")
public class ScoreServiceTest extends ApplicationConfigTest {

	@MockBean
	private ScoreRepository scoreRepository;
	
	@Autowired
	private ScoreService scoreService;
	
	
	@Test
	@DisplayName("deve salvar um score")
	public void saveScore() throws Exception {
		ScoreFormDTO scoreFormDTO = new ScoreFormDTO("Insuficiente", new BigDecimal(0), new BigDecimal(200));
		
		Score score = Mockito.mock(Score.class);
		Mockito.when(score.getId()).thenReturn(1L);
		
		Mockito.when(scoreRepository.contaScorePeloIntervalo(ArgumentMatchers.any(BigDecimal.class), ArgumentMatchers.any(BigDecimal.class))).thenReturn(0L);
		Mockito.when(scoreRepository.saveAndFlush(ArgumentMatchers.any(Score.class))).thenReturn(score);
	
		
		this.scoreService.saveScore(scoreFormDTO);
		
		Mockito.verify(scoreRepository, Mockito.times(1)).saveAndFlush(ArgumentMatchers.any(Score.class));
		Mockito.verify(scoreRepository, Mockito.times(1)).contaScorePeloIntervalo(ArgumentMatchers.any(BigDecimal.class), ArgumentMatchers.any(BigDecimal.class));
	}
	
	@Test
	@DisplayName("deve buscar um score")
	public void buscaScore() throws Exception {
		Long id = 1L;

		Score score = Mockito.mock(Score.class);
		
		Optional<Score> scoreOptional = Optional.of(score);
		Mockito.when(scoreRepository.findById(ArgumentMatchers.eq(id))).thenReturn(scoreOptional);
		
		this.scoreService.buscaScore(id);
		
		Mockito.verify(scoreRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(id));
	}
	
	@Test
	@DisplayName("deve buscar uma descrição pelo score")
	public void buscaDescricaoByScore() {
		BigDecimal valorScore = new BigDecimal(150);
		
		Score score = Mockito.mock(Score.class);
		Mockito.when(score.getDescricao()).thenReturn("");
		
		Mockito.when(scoreRepository.buscaScore(ArgumentMatchers.eq(valorScore))).thenReturn(score);
		
		this.scoreService.buscaDescricaoByScore(valorScore);
		
		Mockito.verify(scoreRepository, Mockito.times(1)).buscaScore(ArgumentMatchers.eq(valorScore));
	}
	
}
