package com.ricardococati.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "candlestickDiario")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CandlestickDiario {

  private static final long serialVersionUID = 505011356059052924L;
  @Id
  private String id;
  private LocalDate dtpreg;
  private String codneg;
  private BigDecimal preabe;
  private BigDecimal premax;
  private BigDecimal premin;
  private BigDecimal preult;
  private BigDecimal voltot;
  private Integer semana;
  private Boolean semanaGerada;

}
