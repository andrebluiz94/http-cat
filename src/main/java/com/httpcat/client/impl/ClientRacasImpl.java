package com.httpcat.client.impl;

import com.httpcat.client.AbstractRestClient;
import com.httpcat.dto.RacaDto;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientRacasImpl extends AbstractRestClient<RacaDto[]> {

	@Override
	public ResponseEntity<RacaDto[]> getAll(String itens, HttpMethod method) {
		return super.getAll(itens, method);
	}

	@Override
	public ResponseEntity<RacaDto[]> getAllRacas(HttpMethod method) {
		return super.getAllRacas(method);
	}
}
