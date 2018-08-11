package com.ricardococati.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "candlestickSemanal")
@EqualsAndHashCode(callSuper = false)
public class CandlestickSemanal {

  private static final long serialVersionUID = 505011356059052924L;
  @Id
  private String id;
  private LocalDate dtpregini;
  private LocalDate dtpregfim;
  private Integer semana;
  private String codneg;
  private BigDecimal preabe;
  private BigDecimal premax;
  private BigDecimal premin;
  private BigDecimal preult;
  private BigDecimal voltot;

}
