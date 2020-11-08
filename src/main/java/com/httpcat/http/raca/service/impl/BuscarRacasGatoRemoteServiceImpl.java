package com.httpcat.http.raca.service.impl;

import com.httpcat.Entity.Cat;
import com.httpcat.generic.client.AbstractGetClient;
import com.httpcat.http.raca.config.BuscarRacasGatoConfiguration;
import com.httpcat.http.raca.dto.ResponseRacaGato;
import com.httpcat.http.raca.service.BuscarRacasGatoRemoteService;
import com.httpcat.mapper.MapperBase;
import com.httpcat.repository.RacasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static java.util.Objects.requireNonNull;

@Service
public class BuscarRacasGatoRemoteServiceImpl
		extends AbstractGetClient<ResponseRacaGato[], BuscarRacasGatoConfiguration>
		implements BuscarRacasGatoRemoteService {

	private final RacasRepository repository;

	@Autowired
	MapperBase<ResponseRacaGato, Cat> mapperBase;

	@Autowired
	public BuscarRacasGatoRemoteServiceImpl(RestTemplate restTemplate, BuscarRacasGatoConfiguration configuration, RacasRepository repository) {
		super(restTemplate, configuration);
		this.repository = repository;
	}

	@Override
	public List<ResponseRacaGato> buscar() {

		CompletableFuture<ResponseRacaGato[]> completableFuture = getCatsAsync();
		ResponseRacaGato[] responseRacaGatoes;
		try {
			responseRacaGatoes = completableFuture.join();
		} catch (CompletionException e) {
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
		}

		List<ResponseRacaGato> catList = new ArrayList<>();
		Collections.addAll(catList, responseRacaGatoes);

		return catList;
	}

	@Override
	public List<ResponseRacaGato> buscaESalva() {
		List<ResponseRacaGato> buscar = buscar();
		List<Cat> catList = new LinkedList<>();
		buscar.parallelStream().forEach(responseRacaGato -> {
			Cat map = mapperBase.map(responseRacaGato);
			catList.add(map);
		});
		Optional.of(repository.saveAll(catList)).orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
		return buscar;
	}


	public List<ResponseRacaGato> buscarImagens() {
		ResponseRacaGato[] responseRacaGato = this.get().getBody();
		return new ArrayList<>(Arrays.asList(requireNonNull(responseRacaGato)));
	}

	@Async
	public CompletableFuture<ResponseRacaGato[]> getCatsAsync() {
		return CompletableFuture.supplyAsync(
				() -> this.get().getBody());
	}
}
