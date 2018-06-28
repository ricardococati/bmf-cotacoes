package com.ricardococati.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Document(collection = "campanha")
@NoArgsConstructor
@AllArgsConstructor
public class Campanha implements Serializable {

  private static final long serialVersionUID = -3739959736137472637L;

  @Id
  private Integer id;
  private String timeDoCoracao;
  private String nomeCampanha;
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "yyyy-MM-dd",
      locale = "pt-BR",
      timezone = "Brazil/East")
  private LocalDate dataVigenciaInicio;
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "yyyy-MM-dd",
      locale = "pt-BR",
      timezone = "Brazil/East")
  private LocalDate dataVigenciaTermino;

  @Override
  public String toString() {
    return "Campanha{"
        + "id="
        + id
        + ", timeDoCoracao='"
        + timeDoCoracao
        + '\''
        + ", nomeCampanha='"
        + nomeCampanha
        + '\''
        + ", dataVigenciaInicio="
        + dataVigenciaInicio
        + ", dataVigenciaTermino="
        + dataVigenciaTermino
        + '}';
  }
}
