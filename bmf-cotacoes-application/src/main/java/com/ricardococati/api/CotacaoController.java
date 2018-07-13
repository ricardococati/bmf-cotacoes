package com.ricardococati.api;

import com.ricardococati.model.Campanha;
import com.ricardococati.model.Cotacao;
import com.ricardococati.usecase.CotacaoUsecase;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CotacaoController {

  private CotacaoUsecase cotacaoUsecase;

  @Autowired
  public CotacaoController(CotacaoUsecase campanhaService) {
    this.cotacaoUsecase = campanhaService;
  }

  @ApiOperation(value = "cotacoes", notes = "Lista de cotacoes por nome empresa", response = List.class)
  @RequestMapping(
      value = "/cotacoes/nomres",
      method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<List<Cotacao>> listacotacoes(
      @RequestParam(name = "nomres", required = false) String nomres) {
    List<Cotacao> lista = cotacaoUsecase.findByNomres(nomres);
    if (CollectionUtils.isEmpty(lista)) {
      return new ResponseEntity<>(lista, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<List<Cotacao>>(lista, HttpStatus.OK);
  }

}
