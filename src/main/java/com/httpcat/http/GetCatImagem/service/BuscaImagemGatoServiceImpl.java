package com.httpcat.http.GetCatImagem.service;

import com.httpcat.Entity.Cat;
import com.httpcat.generic.client.AbstractGetClient;
import com.httpcat.http.GetCatImagem.config.BuscaImagemGatoConfiguration;
import com.httpcat.http.GetCatImagem.dto.RequestGatoImagem;
import com.httpcat.http.GetCatImagem.dto.ResponseRacaGatoImagem;
import com.httpcat.repository.RacasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class BuscaImagemGatoServiceImpl
		extends AbstractGetClient<ResponseRacaGatoImagem[], BuscaImagemGatoConfiguration>
		implements BuscaImagemGatoService {
//TODO: não ao não funcionamento do sistema [API cats] a funcionalidade não pode ser concluída


	private final RacasRepository repository;

	@Autowired
	public BuscaImagemGatoServiceImpl(RestTemplate restTemplate, BuscaImagemGatoConfiguration configuration, RacasRepository repository) {
		super(restTemplate, configuration);
		this.repository = repository;
	}

	@Override
	public List<Optional<ResponseRacaGatoImagem>> buscar(RequestGatoImagem request) {
		List<Cat> allCats = repository.findAll();
		List<Optional<ResponseRacaGatoImagem>> arrayList = new ArrayList<>();

		request.getExpand().put("raca", "sss");
		allCats.forEach(cat -> {
			request.getExpand().replace("raca", cat.getId());
			this.configuration.setUrlParams(request.getExpand());
			ResponseRacaGatoImagem[] response = getImagemRaca().join().getBody();
			ArrayList<ResponseRacaGatoImagem> responseRacaGatoImagems = new ArrayList<>(Arrays.asList(Objects.requireNonNull(response)));
			if (!responseRacaGatoImagems.isEmpty()) {
				Optional<ResponseRacaGatoImagem> responseRacaGatoImagem = Optional.ofNullable(responseRacaGatoImagems.get(0));
				arrayList.add(responseRacaGatoImagem);
			}
		});

		return arrayList;
	}

	@Override
	public List<ResponseRacaGatoImagem> buscar() {
		ResponseRacaGatoImagem[] responseRacaGato = this.get().getBody();
		return new ArrayList<>(Arrays.asList(Objects.requireNonNull(responseRacaGato)));
	}

	@Async
	public CompletableFuture<ResponseEntity<ResponseRacaGatoImagem[]>> getImagemRaca(){
		return CompletableFuture.supplyAsync(this::get);
	}
}
