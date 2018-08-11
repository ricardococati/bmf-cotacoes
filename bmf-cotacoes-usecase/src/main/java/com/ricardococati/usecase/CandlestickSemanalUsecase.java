package com.ricardococati.usecase;

import com.ricardococati.model.CandlestickSemanal;
import com.ricardococati.mongodb.repository.CandlestickSemanalRepository;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandlestickSemanalUsecase implements Serializable {

	private static final long serialVersionUID = 66671121174988145L;
	private CandlestickSemanalRepository repository;

	@Autowired
	public CandlestickSemanalUsecase(CandlestickSemanalRepository repository) {
		this.repository = repository;
	}

	public List<CandlestickSemanal> findByCodneg(String codneg) {
		return this.repository.findByCodneg(codneg);
	}
}
