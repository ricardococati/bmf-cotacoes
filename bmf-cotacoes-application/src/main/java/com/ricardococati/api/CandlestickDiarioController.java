package com.ricardococati.api;

import com.ricardococati.model.CandlestickDiario;
import com.ricardococati.usecase.CandlestickDiarioUsecase;
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
public class CandlestickDiarioController {

  private CandlestickDiarioUsecase usecase;

  @Autowired
  public CandlestickDiarioController(CandlestickDiarioUsecase usecase) {
    this.usecase = usecase;
  }

  @ApiOperation(value = "candlestickDiario", notes = "Lista de candlestickDiario por codigo de negocio", response = List.class)
  @RequestMapping(
      value = "/candlestickDiario/codneg",
      method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<List<CandlestickDiario>> listaCandlestickDiario(
      @RequestParam(name = "codneg", required = false) String codneg) {
    List<CandlestickDiario> lista = usecase.findByCodneg(codneg);
    if (CollectionUtils.isEmpty(lista)) {
      return new ResponseEntity<>(lista, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<List<CandlestickDiario>>(lista, HttpStatus.OK);
  }

}
