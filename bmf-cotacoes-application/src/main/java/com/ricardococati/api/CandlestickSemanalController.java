package com.ricardococati.api;

import com.ricardococati.model.CandlestickSemanal;
import com.ricardococati.model.Cotacao;
import com.ricardococati.usecase.CandlestickSemanalUsecase;
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
public class CandlestickSemanalController {

  private CandlestickSemanalUsecase usecase;

  @Autowired
  public CandlestickSemanalController(CandlestickSemanalUsecase usecase) {
    this.usecase = usecase;
  }

  @ApiOperation(value = "candlestickSemanal", notes = "Lista de candlestickSemanal por codigo de negocio", response = List.class)
  @RequestMapping(
      value = "/candlestickSemanal/codneg",
      method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<List<CandlestickSemanal>> listaCandlestickSemanal(
      @RequestParam(name = "codneg", required = false) String codneg) {
    List<CandlestickSemanal> lista = usecase.findByCodneg(codneg);
    if (CollectionUtils.isEmpty(lista)) {
      return new ResponseEntity<>(lista, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<List<CandlestickSemanal>>(lista, HttpStatus.OK);
  }

}
