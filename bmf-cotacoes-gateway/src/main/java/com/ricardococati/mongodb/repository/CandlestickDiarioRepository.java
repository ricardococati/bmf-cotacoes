package com.ricardococati.mongodb.repository;

import com.ricardococati.model.CandlestickDiario;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CandlestickDiarioRepository extends MongoRepository<CandlestickDiario, Integer> {

    List<CandlestickDiario> findByCodneg(String codneg);
}
