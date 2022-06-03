package com.experian.pjsm.pesregafinidade.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.experian.pjsm.pesregafinidade.dto.AfinidadeDTO;
import com.experian.pjsm.pesregafinidade.dto.AfinidadeFormDTO;
import com.experian.pjsm.pesregafinidade.entities.Afinidade;
import com.experian.pjsm.pesregafinidade.enumeration.Estado;
import com.experian.pjsm.pesregafinidade.repositories.AfinidadeRepository;

@Service
public class AfinidadeService {

	@Autowired
	private AfinidadeRepository afinidadeRepository;
	
	public AfinidadeDTO saveAfinidade(AfinidadeFormDTO afinidadeDTO) throws Exception {
		this.validaDados(afinidadeDTO);
		Afinidade afinidade = new Afinidade();
		afinidade.setRegiao(afinidadeDTO.getRegiao());
		afinidade.setEstados(new ArrayList<Estado>());
		for (String estado : afinidadeDTO.getEstados()) {
			afinidade.getEstados().add(Estado.valueOf(estado));
		}
		afinidade = this.afinidadeRepository.saveAndFlush(afinidade);
		
		if((afinidade.getId() != null) && (afinidade.getId() > 0L)) {
			List<String> estadosStr = new ArrayList<String>();
			afinidade.getEstados().forEach(est -> estadosStr.add(est.toString()));
			return new AfinidadeDTO(afinidade.getId(), afinidade.getRegiao(), estadosStr);
		} else {
			throw new Exception("Erro inesperado ao cadastrar a pessoa.");
		}
	}
	
	private void validaDados(AfinidadeFormDTO afinidadeDTO) throws ValidationException {
		if((afinidadeDTO.getRegiao() == null) || (afinidadeDTO.getRegiao().trim().isEmpty())) {
			throw new ValidationException("O preenchimento da região é obrigatório.");
		}	
		
		if((afinidadeDTO.getEstados() == null) || (afinidadeDTO.getEstados().isEmpty())) {
			throw new ValidationException("O preenchimento dos estados é obrigatório.");
		} else {
			try {
				afinidadeDTO.getEstados().forEach(est -> Estado.valueOf(est));
			} catch (IllegalArgumentException e) {
				throw new ValidationException("Um ou mais estado informado é inválido.");
			}
		}
	}
	
	public AfinidadeDTO buscaAfinidade(Long id) throws Exception {
		if((id == null) || (id <= 0L)) {
			throw new ValidationException("O id é obrigatório e deve ser um valor maior que zero.");
		}
		Afinidade afinidade = this.afinidadeRepository.findById(id).orElse(null);
		if(afinidade != null) {
			List<String> estadosStr = new ArrayList<String>();
			afinidade.getEstados().forEach(est -> estadosStr.add(est.toString()));
			return new AfinidadeDTO(afinidade.getId(), afinidade.getRegiao(), estadosStr);
		} else {
			return null;
		}
	}
	
	public List<Estado> buscaEstadosByRegiao(String regiao){
		Set<Estado> estados = new HashSet<Estado>();
		List<Afinidade> afinidadesRegiao = this.afinidadeRepository.findByRegiao(regiao);
		if(afinidadesRegiao != null) {
			afinidadesRegiao.forEach(af -> {
				estados.addAll(af.getEstados());
			});
		}
		return estados.stream().collect(Collectors.toCollection(ArrayList::new)); 
	}
	
}
