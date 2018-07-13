package com.ricardococati.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cotacao")
@EqualsAndHashCode(callSuper = false)
public class Cotacao {

  private static final long serialVersionUID = 505011356059052924L;
  @Id
  private String id;
  private Long tipoRegistro;
  private LocalDate dtpreg;
  private String codbdi;
  private String codneg;
  private Long tpmerc;
  private String nomres;
  private String especi;
  private String prazot;
  private String modref;
  private BigDecimal preabe;
  private BigDecimal premax;
  private BigDecimal premin;
  private BigDecimal premed;
  private BigDecimal preult;
  private BigDecimal preofc;
  private BigDecimal preofv;
  private Long totneg;
  private Long quatot;
  private BigDecimal voltot;
  private BigDecimal preexe;
  private Long indopc;
  private LocalDate datven;
  private Long fatcot;
  private BigDecimal ptoexe;
  private String codisi;
  private Long dismes;

}
