package com.experian.pjsm.pesregafinidade.controllers;

import java.net.URI;

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

import com.experian.pjsm.pesregafinidade.dto.AfinidadeDTO;
import com.experian.pjsm.pesregafinidade.dto.AfinidadeFormDTO;
import com.experian.pjsm.pesregafinidade.services.AfinidadeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/afinidade")
public class AfinidadeController {

	@Autowired
	private AfinidadeService afinidadeService;
	
	
	@ApiOperation(value = "Inclui uma nova afinidade com os dados informados")
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "Afinidade cadastrada com sucesso."),
		    @ApiResponse(code = 400, message = "Foram informados dados inválidos."),
		    @ApiResponse(code = 500, message = "Erro inesperado ao processar a requisição."),
	})
	@PostMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<?> saveAfinidade(@RequestBody AfinidadeFormDTO afinidadeDTO, UriComponentsBuilder uriBuilder) {
		try {
			AfinidadeDTO afinidade = this.afinidadeService.saveAfinidade(afinidadeDTO);
			URI uri = uriBuilder.path("/afinidade/{id}").buildAndExpand(afinidade.getId()).toUri();
			
			return ResponseEntity.created(uri).body(afinidade);
		} catch (ValidationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
	    } catch (Exception e) {
	    	return ResponseEntity.internalServerError().body("Erro inesperado ao processar a requisição.");
		}
	}
	
	@ApiOperation(value = "Busca a afinidade que possui o id informado")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Afinidade encontrada com o id informado."),
		    @ApiResponse(code = 204, message = "Não foi encontrada afinidade com o id informado."),
		    @ApiResponse(code = 400, message = "Foram informados dados inválidos."),
		    @ApiResponse(code = 500, message = "Erro inesperado ao processar a requisição."),
	})
	@GetMapping(value="/{id}", produces="application/json")
	public ResponseEntity<?> buscaAfinidade(@PathVariable Long id) {
		try {
			AfinidadeDTO afinidade = this.afinidadeService.buscaAfinidade(id);
			if(afinidade == null) {
				return ResponseEntity.noContent().build();			
			}
			
			return ResponseEntity.ok().body(afinidade);
		} catch (ValidationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
	    } catch (Exception e) {
	    	return ResponseEntity.internalServerError().body("Erro inesperado ao processar a requisição.");
		}
	}
}
