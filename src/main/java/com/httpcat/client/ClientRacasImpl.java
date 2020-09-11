package com.httpcat.client;

import com.httpcat.dto.RacaDto;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientRacasImpl extends AbstractRestClient<RacaDto>{

	@Override
	public ResponseEntity<RacaDto[]> getAllRacas(Class<RacaDto[]> typeReturn, HttpMethod method) {
		return super.getAllRacas(typeReturn, method);
	}

	@Override
	public ResponseEntity<RacaDto[]> getAllRacas(String raca, Class<RacaDto[]> typeReturn) {
		return super.getAllRacas(raca, typeReturn);
	}
}
