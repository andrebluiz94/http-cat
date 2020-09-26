package com.httpcat.service.impl;

import com.httpcat.Entity.Cat;
import com.httpcat.generic.impl.ClientRacasImpl;
import com.httpcat.dto.RacaDTO;
import com.httpcat.mapper.MapperBase;
import com.httpcat.repository.RacasRepository;
import com.httpcat.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CatServiceImpl implements CatService {

	@Autowired
	private ClientRacasImpl clientRacas;

	@Autowired
	private MapperBase<RacaDTO, Cat> mapperRacaCat;

	@Autowired
	private MapperBase<Cat, RacaDTO> mapperCatRaca;

	@Autowired
	private RacasRepository racasRepository;

	public ResponseEntity<List<RacaDTO>> getRaca(String raca) {
		return clientRacas.getAll(raca, HttpMethod.GET);
	}

	public ResponseEntity<List<RacaDTO>> getAllRacas() {
		return clientRacas.getAllRacas(HttpMethod.GET);
	}

	public ResponseEntity<List<RacaDTO>> getRaca(Map<String, String> raca) {
		return clientRacas.getAll(raca, HttpMethod.GET);
	}

	@Override
	public ResponseEntity<List<RacaDTO>> getAllRacasImagemOculos(Map<String, String> racaQuery) {
		List<Cat> all = racasRepository.findAll();
		List<RacaDTO> racaDTOS = new ArrayList<>();
		for(Cat raca: all){
			RacaDTO map = mapperCatRaca.map(raca);
			ResponseEntity<List<RacaDTO>> allRacasGlaasses = clientRacas.getAllRacasGlaasses(map.getName(), racaQuery, HttpMethod.GET);
			System.out.println(Objects.requireNonNull(allRacasGlaasses.getBody()).toString());
		}

		return null;
	}

	@Override
	public ResponseEntity<List<RacaDTO>> getAllRacasImagemChapeu() {
		return null;
	}

	@Override
	public ResponseEntity<List<RacaDTO>> saveRacas() {
		ResponseEntity<List<RacaDTO>> allRacas = clientRacas.getAllRacas(HttpMethod.GET);
		for (RacaDTO raca : Objects.requireNonNull(allRacas.getBody())) {
			Cat cat = mapperRacaCat.map(raca);
			racasRepository.save(cat);
		}
		return ResponseEntity.ok(allRacas).getBody();
	}

	@Override
	public ResponseEntity<List<RacaDTO>> getBaseRacas() {
		List<Cat> all = racasRepository.findAll();
		List<RacaDTO> racaDTOS = new ArrayList<>();
		for (Cat cat : all){
			RacaDTO dto = this.mapperCatRaca.map(cat);
			racaDTOS.add(dto);
		}
		return ResponseEntity.ok(racaDTOS);
	}


}
