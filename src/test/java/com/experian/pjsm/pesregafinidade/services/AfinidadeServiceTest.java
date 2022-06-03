package com.experian.pjsm.pesregafinidade.services;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.experian.pjsm.pesregafinidade.ApplicationConfigTest;
import com.experian.pjsm.pesregafinidade.dto.AfinidadeFormDTO;
import com.experian.pjsm.pesregafinidade.entities.Afinidade;
import com.experian.pjsm.pesregafinidade.enumeration.Estado;
import com.experian.pjsm.pesregafinidade.repositories.AfinidadeRepository;

@DisplayName("AfinidadeServiceTest")
public class AfinidadeServiceTest extends ApplicationConfigTest {

	@MockBean
	private AfinidadeRepository afinidadeRepository;
	
	@Autowired
	private AfinidadeService afinidadeService;
	
	
	@Test
	@DisplayName("deve salvar uma afinidade")
	public void saveAfinidade() throws Exception {
		AfinidadeFormDTO afinidadeFormDTO = new AfinidadeFormDTO("sudeste", Arrays.asList("MG", "SP", "RJ", "ES"));
		
		Afinidade afinidade = Mockito.mock(Afinidade.class);
		Mockito.when(afinidade.getId()).thenReturn(1L);
		Mockito.when(afinidade.getEstados()).thenReturn(Collections.emptyList());
		
		Mockito.when(afinidadeRepository.saveAndFlush(ArgumentMatchers.any(Afinidade.class))).thenReturn(afinidade);
		
		this.afinidadeService.saveAfinidade(afinidadeFormDTO);
		
		Mockito.verify(afinidadeRepository, Mockito.times(1)).saveAndFlush(ArgumentMatchers.any(Afinidade.class));
	}
	
	@Test
	@DisplayName("deve buscar uma afinidade")
	public void buscaAfinidade() throws Exception {
		Long id = 1L;

		Afinidade afinidade = Mockito.mock(Afinidade.class);
		Mockito.when(afinidade.getEstados()).thenReturn(Arrays.asList(Estado.valueOf("SP"), Estado.valueOf("RJ"), Estado.valueOf("MG"), Estado.valueOf("ES")));
		
		Optional<Afinidade> afinidadeOptional = Optional.of(afinidade);
		Mockito.when(afinidadeRepository.findById(ArgumentMatchers.eq(id))).thenReturn(afinidadeOptional);
		
		this.afinidadeService.buscaAfinidade(id);
		
		Mockito.verify(afinidadeRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(id));
	}
	
	@Test
	@DisplayName("deve os estados de determinada região")
	public void buscaEstadosByRegiao(){
		String regiao = "sudeste";
		
		Mockito.when(afinidadeRepository.findByRegiao(ArgumentMatchers.eq(regiao))).thenReturn(Collections.emptyList());
		
		this.afinidadeService.buscaEstadosByRegiao(regiao);
		
		Mockito.verify(afinidadeRepository, Mockito.times(1)).findByRegiao(ArgumentMatchers.any(String.class));
	}
	
}
