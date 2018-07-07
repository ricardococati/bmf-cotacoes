package com.ricardococati.mongodb.repository;

import com.ricardococati.model.Campanha;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CampanhaRepository extends MongoRepository<Campanha, Integer> {

    Campanha findById(Integer id);

    List<Campanha> findAllCampanhaByDataVigenciaInicioAndTimeDoCoracao(LocalDate date, String timeDoCoracao);

    List<Campanha> findAllCampanhaByDataVigenciaInicioAndDataVigenciaTerminoAndTimeDoCoracao(LocalDate dataVigenciaInicio, LocalDate dataVigenciaTermino, String timeDoCoracao);

    List<Campanha> findByIdIn(List<Integer> ids);
}
