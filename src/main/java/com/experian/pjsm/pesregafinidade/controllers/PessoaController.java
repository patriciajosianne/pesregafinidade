package com.experian.pjsm.pesregafinidade.controllers;

import java.net.URI;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.experian.pjsm.pesregafinidade.dto.PessoaConsultaDTO;
import com.experian.pjsm.pesregafinidade.dto.PessoaDTO;
import com.experian.pjsm.pesregafinidade.dto.PessoaFormDTO;
import com.experian.pjsm.pesregafinidade.dto.PessoaListaDTO;
import com.experian.pjsm.pesregafinidade.services.PessoaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/pessoa", produces="application/json")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	
	
	@ApiOperation(value = "Inclui uma nova pessoa com os dados informados")
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "Pessoa cadastrada com sucesso."),
		    @ApiResponse(code = 400, message = "Foram informados dados inválidos."),
		    @ApiResponse(code = 500, message = "Erro inesperado ao processar a requisição."),
	})
	@PostMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<?> savePessoa(@RequestBody PessoaFormDTO pessoaDTO, UriComponentsBuilder uriBuilder) {
		try {
			PessoaDTO pes = this.pessoaService.savePessoa(pessoaDTO);
			URI uri = uriBuilder.path("/pessoa/{id}").buildAndExpand(pes.getId()).toUri();
			
			return ResponseEntity.created(uri).body(pes);
		} catch (ValidationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
	    } catch (Exception e) {
	    	return ResponseEntity.internalServerError().body("Erro inesperado ao processar a requisição.");
		}
	}
	
	@ApiOperation(value = "Busca a pessoa que possui o id informado")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Pessoa encontrada com o id informado."),
		    @ApiResponse(code = 204, message = "Não foi encontrada pessoa com o id informado."),
		    @ApiResponse(code = 400, message = "Foram informados dados inválidos."),
		    @ApiResponse(code = 500, message = "Erro inesperado ao processar a requisição."),
	})
	@GetMapping(value="/{id}", produces="application/json")
	public ResponseEntity<?> buscaPessoa(@PathVariable Long id) {
		try {
			PessoaConsultaDTO pes = this.pessoaService.buscaPessoa(id);
			if(pes == null) {
				return ResponseEntity.noContent().build();			
			}
			
			return ResponseEntity.ok().body(pes);
		} catch (ValidationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
	    } catch (Exception e) {
	    	return ResponseEntity.internalServerError().body("Erro inesperado ao processar a requisição.");
		}
	}
	
	@ApiOperation(value = "Lista todas as pessoas cadastradas")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Foram encontradas pessoas cadastradas."),
		    @ApiResponse(code = 204, message = "Não foram encontradas pessoas cadastradas."),
	})
	@GetMapping(produces="application/json")
	public ResponseEntity<List<PessoaListaDTO>> buscaTodos() {
		List<PessoaListaDTO> pessoas = pessoaService.buscaTodos();
		if((pessoas == null) || (pessoas.isEmpty())) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(pessoas);
	}
	
}
