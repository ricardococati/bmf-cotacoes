package com.ricardococati.usecase;

import com.ricardococati.model.CandlestickDiario;
import com.ricardococati.mongodb.repository.CandlestickDiarioRepository;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandlestickDiarioUsecase implements Serializable {

	private static final long serialVersionUID = 66671121174988145L;
	private CandlestickDiarioRepository repository;

	@Autowired
	public CandlestickDiarioUsecase(CandlestickDiarioRepository repository) {
		this.repository = repository;
	}

	public List<CandlestickDiario> findByCodneg(String codneg) {
		return this.repository.findByCodneg(codneg);
	}
}
