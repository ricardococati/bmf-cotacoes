package com.ricardococati.usecase;

import com.ricardococati.model.Campanha;
import com.ricardococati.mongodb.repository.CampanhaRepository;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class CampanhaUsecase implements Serializable {
	
	private static List<Campanha> campanhas = new ArrayList<>();

	private CampanhaRepository campanhaRepository;

	@Autowired
	public CampanhaUsecase(CampanhaRepository campanhaRepository) {
		this.campanhaRepository = campanhaRepository;
	}

	private static final long serialVersionUID = 25671121174988145L;

	@Transactional
	public Campanha save(final Campanha campanha) {
		final List<Campanha> campanhasExistentes = this.campanhaRepository.findAllCampanhaByDataVigenciaInicioAndDataVigenciaTerminoAndTimeDoCoracao(campanha.getDataVigenciaInicio(), campanha.getDataVigenciaTermino(), campanha.getTimeDoCoracao());

		final List<Campanha> novasCampanhas = this.alterarCampanhas(campanhasExistentes, campanha);

		novasCampanhas.forEach(this.campanhaRepository::save);

		return campanha;
	}

	public List<Campanha> findAll(List<Integer> ids) {
		if (!CollectionUtils.isEmpty(ids))
			return this.findCampanhasByIds(ids);
		return this.campanhaRepository.findAll();
	}

	public Campanha findById(Integer id) {
		return this.campanhaRepository.findById(id);
	}

	@Transactional
	public void delete(Integer id) {
		this.campanhaRepository.delete(id);
	}

	public Campanha edit(Campanha campanha) {
		return this.save(campanha);
	}

	public List<Campanha> findAllCampanhasByTimeDoCoracao(String timeDoCoracao) {
		return this.campanhaRepository.findAllCampanhaByDataVigenciaInicioAndTimeDoCoracao(LocalDate.now(), timeDoCoracao);
	}

	private List<Campanha> alterarCampanhas(List<Campanha> campanhas, Campanha campanha) {

		List<LocalDate> listaDatas = new ArrayList<>();
		List<Campanha> listaAtualizada = new ArrayList<>();

		if (!CollectionUtils.isEmpty(campanhas)) {
			campanhas.forEach(camp -> listaDatas.add(camp.getDataVigenciaTermino()));

			campanhas.forEach(camp -> {
				listaDatas.remove(camp.getDataVigenciaTermino());
				camp.setDataVigenciaTermino(camp.getDataVigenciaTermino().plusDays(1));

				listaDatas.add(camp.getDataVigenciaTermino());
				listaAtualizada.add(camp);
			});

			if (listaDatas.contains(campanha.getDataVigenciaTermino())) {
				alterarCampanhas(listaAtualizada, campanha);
			}
		}
		campanhas.add(campanha);
		return campanhas;
	}

	public List<Campanha> findCampanhasByIds(List<Integer> ids) {
		return this.campanhaRepository.findByIdIn(ids);
	}
}
