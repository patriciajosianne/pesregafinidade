package com.experian.pjsm.pesregafinidade.services;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.experian.pjsm.pesregafinidade.ApplicationConfigTest;
import com.experian.pjsm.pesregafinidade.dto.PessoaFormDTO;
import com.experian.pjsm.pesregafinidade.entities.Pessoa;
import com.experian.pjsm.pesregafinidade.enumeration.Estado;
import com.experian.pjsm.pesregafinidade.repositories.PessoaRepository;

@DisplayName("PessoaServiceTest")
public class PessoaServiceTest extends ApplicationConfigTest {

	@MockBean
	private PessoaRepository pessoaRepository;
	
	@MockBean
	private AfinidadeService afinidadeService;
	
	@MockBean
	private ScoreService scoreService;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Test
	@DisplayName("deve salvar uma pessoa")
	public void savePessoa() throws Exception {
		PessoaFormDTO pessoaFormDTO = new PessoaFormDTO("Nome de teste", null, null, null, "MG", "sudeste", new BigDecimal(1000));
		
		Pessoa pessoa = Mockito.mock(Pessoa.class);
		Mockito.when(pessoa.getId()).thenReturn(1L);
		Mockito.when(pessoa.getEstado()).thenReturn(Estado.valueOf("MG"));
		
		Mockito.when(pessoaRepository.saveAndFlush(ArgumentMatchers.any(Pessoa.class))).thenReturn(pessoa);
		
		this.pessoaService.savePessoa(pessoaFormDTO);
		
		Mockito.verify(pessoaRepository, Mockito.times(1)).saveAndFlush(ArgumentMatchers.any(Pessoa.class));
	}
	
	@Test
	@DisplayName("deve buscar uma pessoa")
	public void buscaPessoa() throws Exception {
		Long idPessoa = 1L;
		
		Pessoa pessoa = Mockito.mock(Pessoa.class);
		Mockito.when(pessoa.getScore()).thenReturn(BigDecimal.TEN);
		Mockito.when(pessoa.getRegiao()).thenReturn("sudeste");
		
		Optional<Pessoa> pessoaOptional = Optional.of(pessoa);
		Mockito.when(pessoaRepository.findById(ArgumentMatchers.eq(idPessoa))).thenReturn(pessoaOptional);
		Mockito.when(scoreService.buscaDescricaoByScore(ArgumentMatchers.any(BigDecimal.class))).thenReturn("Insuficiente");
		Mockito.when(afinidadeService.buscaEstadosByRegiao(ArgumentMatchers.any(String.class))).thenReturn(Collections.emptyList());
		
		this.pessoaService.buscaPessoa(idPessoa);
		
		Mockito.verify(pessoaRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(idPessoa));
		Mockito.verify(scoreService, Mockito.times(1)).buscaDescricaoByScore(ArgumentMatchers.any(BigDecimal.class));
		Mockito.verify(afinidadeService, Mockito.times(1)).buscaEstadosByRegiao(ArgumentMatchers.any(String.class));
		
	}
	
	@Test
	@DisplayName("deve buscar todas pessoas cadastradas")
	public void buscaTodos() throws Exception {
		List<Pessoa> pessoas = IntStream
				.range(0, 2)
				.mapToObj(value -> {
					Pessoa pes = new Pessoa();
					pes.setId(1L);
					pes.setRegiao("sudeste");
					pes.setScore(BigDecimal.TEN);
					pes.setEstado(Estado.valueOf("MG"));
					return pes;
				}).collect(Collectors.toList());
		
		Mockito.when(pessoaRepository.findAll()).thenReturn(pessoas);
		Mockito.when(scoreService.buscaDescricaoByScore(ArgumentMatchers.any(BigDecimal.class))).thenReturn("Insuficiente");
		Mockito.when(afinidadeService.buscaEstadosByRegiao(ArgumentMatchers.any(String.class))).thenReturn(Collections.emptyList());
		
		this.pessoaService.buscaTodos();
		
		Mockito.verify(pessoaRepository, Mockito.times(1)).findAll();
		Mockito.verify(scoreService, Mockito.times(2)).buscaDescricaoByScore(ArgumentMatchers.any(BigDecimal.class));
		Mockito.verify(afinidadeService, Mockito.times(2)).buscaEstadosByRegiao(ArgumentMatchers.any(String.class));
	}

}
