package com.httpcat.service;

import com.httpcat.client.ClientRacasImpl;
import com.httpcat.dto.RacaDto;
import com.httpcat.security.GenerateHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatService extends GenerateHeaders {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ClientRacasImpl clientRacas;
	
//	public ResponseEntity<RacaDto[]> getAllRacas(){
//		return restTemplate.exchange(
//				getUrl(),
//				HttpMethod.GET,
//				getRequest(),
//				RacaDto[].class);
//	}

//	public ResponseEntity<RacaDto[]> getRaca(String raca) {
//		return restTemplate.exchange(
//				getUrl(raca),
//				HttpMethod.GET,
//				getRequest(),
//				RacaDto[].class
//		);
//	}

	public ResponseEntity<RacaDto[]> getRaca(String raca) {
		return clientRacas.getAllRacas(raca, RacaDto[].class);
	}



	public ResponseEntity<RacaDto[]> getAllRacas() {
		return clientRacas.getAllRacas(RacaDto[].class, HttpMethod.GET);
	}
}
