package com.httpcat.service.impl;

import com.httpcat.client.impl.ClientRacasImpl;
import com.httpcat.dto.RacaDto;
import com.httpcat.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class CatServiceImpl implements CatService {

	@Autowired
	private ClientRacasImpl clientRacas;


	public ResponseEntity<RacaDto[]> getRaca(String raca) {
		return clientRacas.getAll(raca, HttpMethod.GET);
	}

	public ResponseEntity<RacaDto[]> getAllRacas() {
		return clientRacas.getAllRacas(HttpMethod.GET);
	}

	public ResponseEntity<RacaDto[]> getRaca(Map<String, String> raca) {
		return clientRacas.getAll(raca, HttpMethod.GET);
	}

	@Override
	public RacaDto saveRacas() {
		ResponseEntity<RacaDto[]> allRacas = clientRacas.getAllRacas(HttpMethod.GET);

		return null;
	}
}
