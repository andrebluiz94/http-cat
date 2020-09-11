package com.httpcat.client;

import com.httpcat.dto.RacaDto;
import com.httpcat.security.GenerateHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractRestClient<ReturnDto> extends GenerateHeaders {

	@Autowired
	private RestTemplate restTemplate;


	public ResponseEntity<ReturnDto[]> getAllRacas(String raca, Class<ReturnDto[]> typeReturn) {
		return restTemplate.exchange(
				getUrl(raca),
				HttpMethod.GET,
				getRequest(),
				typeReturn
		);
	}

	public ResponseEntity<ReturnDto> getAllRacas(Class<ReturnDto> typeReturn) {
		return restTemplate.exchange(
				getUrl(),
				HttpMethod.GET,
				getRequest(),
				typeReturn
		);
	}

	public ResponseEntity<RacaDto[]> getAllRacas(Class<RacaDto[]> typeReturn, HttpMethod method) {
		return restTemplate.exchange(
				getUrl(),
				method,
				getRequest(),
				typeReturn
		);
	}
}
