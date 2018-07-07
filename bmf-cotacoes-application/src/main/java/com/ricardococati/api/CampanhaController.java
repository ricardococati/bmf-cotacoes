package com.ricardococati.api;

import com.ricardococati.usecase.CampanhaUsecase;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import com.ricardococati.model.Campanha;

import io.swagger.annotations.ApiOperation;

@RestController
public class CampanhaController {

	private CampanhaUsecase campanhaUsecase;

	@Autowired
	public CampanhaController(CampanhaUsecase campanhaService) {
		this.campanhaUsecase = campanhaService;
	}


	@ApiOperation(value = "campanhas", notes = "Lista de Campanhas" , response = List.class)
    @RequestMapping(value="/campanhas" , method=RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Campanha>> listaCampanhas(@RequestParam(name = "ids", required = false) List<Integer> ids) {
		List<Campanha> lista = campanhaUsecase.findAll(ids);
		if (CollectionUtils.isEmpty(lista)){
			return new ResponseEntity<>(lista, HttpStatus.NOT_FOUND);
		}
	    return new ResponseEntity<List<Campanha>>(lista, HttpStatus.OK);
    }

	@RequestMapping(path = "/campanhas/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Campanha> findById(@PathVariable("id") Integer id) {
		Campanha campanha = this.campanhaUsecase.findById(id);
		if (Objects.isNull(campanha)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(campanha, HttpStatus.OK);
	}

	@ApiOperation(value = "campanhas", notes = "Adiciona Campanhas" , response = Campanha.class)
	@RequestMapping(path = "/campanhas", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Campanha> addCampanha(@RequestBody Campanha campanha) {
		return new ResponseEntity<>(this.campanhaUsecase.save(campanha), HttpStatus.CREATED);
	}

	@ApiOperation(value = "campanhas", notes = "Remove Campanhas" , response = Void.class)
	@RequestMapping(path = "/campanhas/{id}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> removeCampanha(@PathVariable("id") Integer id) {
		this.campanhaUsecase.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "campanhasll", notes = "Atualiza Campanhas" , response = Campanha.class)
	@RequestMapping(path = "/campanhas", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Campanha> updateCampanha(@RequestBody Campanha campanha) {
		Campanha campanhaUpdated = this.campanhaUsecase.edit(campanha);
		return new ResponseEntity<>(campanhaUpdated, HttpStatus.ACCEPTED);
	}

	@RequestMapping(path = "/campanhas/time/{time}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Campanha>> findAllCampanhasByTime(@PathVariable("time") String time) {
		List<Campanha> campanhas = this.campanhaUsecase.findAllCampanhasByTimeDoCoracao(time);
		if (CollectionUtils.isEmpty(campanhas)) {
			return new ResponseEntity<>(campanhas, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(campanhas, HttpStatus.OK);
	}

}
