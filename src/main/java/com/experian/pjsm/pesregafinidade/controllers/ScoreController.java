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

import com.experian.pjsm.pesregafinidade.dto.ScoreDTO;
import com.experian.pjsm.pesregafinidade.dto.ScoreFormDTO;
import com.experian.pjsm.pesregafinidade.services.ScoreService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/score")
public class ScoreController {

	@Autowired
	private ScoreService scoreService;
	
	@ApiOperation(value = "Inclui um novo score com os dados informados")
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "Score cadastrado com sucesso."),
		    @ApiResponse(code = 400, message = "Foram informados dados inválidos."),
		    @ApiResponse(code = 500, message = "Erro inesperado ao processar a requisição."),
	})
	@PostMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<?> saveScore(@RequestBody ScoreFormDTO scoreDTO, UriComponentsBuilder uriBuilder) {
		try {
			ScoreDTO score = this.scoreService.saveScore(scoreDTO);
			URI uri = uriBuilder.path("/score/{id}").buildAndExpand(score.getId()).toUri();
			
			return ResponseEntity.created(uri).body(score);
		} catch (ValidationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
	    } catch (Exception e) {
	    	return ResponseEntity.internalServerError().body("Erro inesperado ao processar a requisição.");
		}
	}
	
	@ApiOperation(value = "Busca o score que possui o id informado")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Score encontrado com o id informado."),
		    @ApiResponse(code = 204, message = "Não foi encontrado score com o id informado."),
		    @ApiResponse(code = 400, message = "Foram informados dados inválidos."),
		    @ApiResponse(code = 500, message = "Erro inesperado ao processar a requisição."),
	})
	@GetMapping(value="/{id}", produces="application/json")
	public ResponseEntity<?> buscaScore(@PathVariable Long id) {
		try {
			ScoreDTO score = this.scoreService.buscaScore(id);
			if(score == null) {
				return ResponseEntity.noContent().build();			
			}
			
			return ResponseEntity.ok().body(score);
		} catch (ValidationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
	    } catch (Exception e) {
	    	return ResponseEntity.internalServerError().body("Erro inesperado ao processar a requisição.");
		}
	}
	
}
