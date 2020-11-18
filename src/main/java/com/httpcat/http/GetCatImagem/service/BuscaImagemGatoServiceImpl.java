package com.httpcat.http.GetCatImagem.service;

import com.httpcat.Entity.Cat;
import com.httpcat.http.generic.client.AbstractGetClient;
import com.httpcat.http.GetCatImagem.config.BuscaImagemGatoConfiguration;
import com.httpcat.http.GetCatImagem.dto.RequestGatoImagem;
import com.httpcat.http.GetCatImagem.dto.ResponseRacaGatoImagem;
import com.httpcat.repository.RacasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

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
		allCats.forEach(cat -> {
			ResponseRacaGatoImagem[] response;

			request.getExpand().put("raca", "sss");
			request.getExpand().replace("raca", cat.getId());
			this.configuration.setUrlParams(request.getExpand());

			CompletableFuture<ResponseEntity<ResponseRacaGatoImagem[]>> imagemRaca = getImagemRaca();
			try {
				response = imagemRaca.join().getBody();
			}catch (CompletionException e){
				throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
			}

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
