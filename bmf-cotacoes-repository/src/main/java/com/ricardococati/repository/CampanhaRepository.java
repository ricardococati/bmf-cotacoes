package com.ricardococati.repository;

import com.ricardococati.model.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface CampanhaRepository extends MongoRepository<Campanha, Integer> {

    Campanha findById(Integer id);

    List<Campanha> findAllCampanhaByDataVigenciaInicioAndTimeDoCoracao(LocalDate date, String timeDoCoracao);

    List<Campanha> findAllCampanhaByDataVigenciaInicioAndDataVigenciaTerminoAndTimeDoCoracao(LocalDate dataVigenciaInicio, LocalDate dataVigenciaTermino, String timeDoCoracao);

    List<Campanha> findByIdIn(List<Integer> ids);
}
