package com.ricardococati.mongodb.repository;

import com.ricardococati.model.CandlestickSemanal;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CandlestickSemanalRepository extends MongoRepository<CandlestickSemanal, Integer> {

    List<CandlestickSemanal> findByCodneg(String codneg);
}
