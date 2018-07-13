package com.ricardococati.usecase;

import com.ricardococati.model.Campanha;
import com.ricardococati.model.Cotacao;
import com.ricardococati.mongodb.repository.CotacaoRepository;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CotacaoUsecase implements Serializable {

	private CotacaoRepository cotacaoRepository;

	@Autowired
	public CotacaoUsecase(CotacaoRepository cotacaoRepository) {
		this.cotacaoRepository = cotacaoRepository;
	}

	private static final long serialVersionUID = 25671121174988145L;

	public List<Cotacao> findByNomres(String nomres) {
		return this.cotacaoRepository.findByNomres(nomres);
	}
}
