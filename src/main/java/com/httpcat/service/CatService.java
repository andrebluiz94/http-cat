package com.httpcat.service;

import com.httpcat.client.ClientRacasImpl;
import com.httpcat.dto.RacaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CatService {

	@Autowired
	private ClientRacasImpl clientRacas;


	public ResponseEntity<RacaDto[]> getRaca(String raca) {
		return clientRacas.getAll(raca, HttpMethod.GET, RacaDto[].class);
	}



	public ResponseEntity<RacaDto[]> getAllRacas() {
		return clientRacas.getAllRacas(RacaDto[].class, HttpMethod.GET);
	}

	public ResponseEntity<RacaDto[]> getRaca(Map<String, String> raca) {
		return clientRacas.getAll(raca, HttpMethod.GET, RacaDto[].class);
	}
}
