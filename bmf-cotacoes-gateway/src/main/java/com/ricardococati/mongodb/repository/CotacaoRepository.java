package com.ricardococati.mongodb.repository;

import com.ricardococati.model.Campanha;
import com.ricardococati.model.Cotacao;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CotacaoRepository extends MongoRepository<Cotacao, Integer> {

    List<Cotacao> findByNomres(String nomres);
}
