package com.experian.pjsm.pesregafinidade.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.experian.pjsm.pesregafinidade.dto.PessoaConsultaDTO;
import com.experian.pjsm.pesregafinidade.dto.PessoaDTO;
import com.experian.pjsm.pesregafinidade.dto.PessoaFormDTO;
import com.experian.pjsm.pesregafinidade.dto.PessoaListaDTO;
import com.experian.pjsm.pesregafinidade.entities.Pessoa;
import com.experian.pjsm.pesregafinidade.enumeration.Estado;
import com.experian.pjsm.pesregafinidade.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private AfinidadeService afinidadeService;
	
	@Autowired
	private ScoreService scoreService;
	
	
	public PessoaDTO savePessoa(PessoaFormDTO pessoaDTO) throws Exception {
		this.validaDados(pessoaDTO);
		Pessoa pes = new Pessoa();
		pes.setNome(pessoaDTO.getNome());
		pes.setTelefone(pessoaDTO.getTelefone());
		pes.setIdade(pessoaDTO.getIdade());
		pes.setCidade(pessoaDTO.getCidade());
		pes.setEstado(Estado.valueOf(pessoaDTO.getEstado()));
		pes.setRegiao(pessoaDTO.getRegiao());
		pes.setScore(pessoaDTO.getScore());
		pes = this.pessoaRepository.saveAndFlush(pes);
		
		if((pes.getId() != null) && (pes.getId() > 0L)) {
			return new PessoaDTO(pes.getId(), pes.getNome(), pes.getTelefone(), pes.getIdade(), pes.getCidade(), 
					pes.getEstado().toString(), pes.getRegiao(), pes.getScore(), pes.getDataInclusao());
		} else {
			throw new Exception("Erro inesperado ao cadastrar a pessoa.");
		}
	}

	private void validaDados(PessoaFormDTO pessoaDTO) throws ValidationException {
		if((pessoaDTO.getNome() == null) || (pessoaDTO.getNome().trim().isEmpty())) {
			throw new ValidationException("O preenchimento do nome é obrigatório.");
		}	
		
		if((pessoaDTO.getEstado() == null) || (pessoaDTO.getEstado().trim().isEmpty())) {
			throw new ValidationException("O preenchimento do estado é obrigatório.");
		} else {
			try {
				Estado.valueOf(pessoaDTO.getEstado());
			} catch (IllegalArgumentException e) {
				throw new ValidationException("O estado informado é inválido.");
			}
		}
		
		if((pessoaDTO.getScore() == null) || (pessoaDTO.getScore().compareTo(BigDecimal.ZERO) < 0) || (pessoaDTO.getScore().compareTo(new BigDecimal(1000)) > 0)) {
			throw new ValidationException("O preenchimento do score é obrigatório e deve ser um valor maior ou igual a 0 e menor ou igual a 1000.");
		}	
		
		if((pessoaDTO.getRegiao() == null) || (pessoaDTO.getRegiao().trim().isEmpty())) {
			throw new ValidationException("O preenchimento da região é obrigatório.");
		}	
	}

	public PessoaConsultaDTO buscaPessoa(Long id) throws Exception {
		if((id == null) || (id <= 0L)) {
			throw new ValidationException("O id é obrigatório e deve ser um valor maior que zero.");
		}
		Pessoa pes = this.pessoaRepository.findById(id).orElse(null);
		if(pes != null) {
			String scoreDescricao = this.scoreService.buscaDescricaoByScore(pes.getScore());
			List<Estado> estados = this.afinidadeService.buscaEstadosByRegiao(pes.getRegiao());
			List<String> estadosStr = new ArrayList<String>();
			if(estados != null) {
				estados.forEach(est -> estadosStr.add(est.toString()));
			}
			return new PessoaConsultaDTO(pes.getNome(), pes.getTelefone(), pes.getIdade(), scoreDescricao, estadosStr);
		} else {
			return null;
		}
	}

	public List<PessoaListaDTO> buscaTodos() {
		List<Pessoa> pessoas = this.pessoaRepository.findAll();
		if(pessoas != null) {
			List<PessoaListaDTO> retorno = new ArrayList<PessoaListaDTO>();
			pessoas.forEach(pes -> {
				String scoreDescricao = this.scoreService.buscaDescricaoByScore(pes.getScore());
				List<Estado> estados = this.afinidadeService.buscaEstadosByRegiao(pes.getRegiao());
				List<String> estadosStr = new ArrayList<String>();
				if(estados != null) {
					estados.forEach(est -> estadosStr.add(est.toString()));
				}
				retorno.add(new PessoaListaDTO(pes.getNome(), pes.getCidade(), pes.getEstado().toString(), scoreDescricao, estadosStr));
			});
			return retorno;
		}
		return null;
	}
}
