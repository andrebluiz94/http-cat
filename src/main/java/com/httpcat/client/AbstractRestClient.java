package com.httpcat.client;

import com.httpcat.dto.RacaDto;
import com.httpcat.security.Abstract.GenerateHeaders;
import com.httpcat.util.impl.CreateUrlImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public abstract class AbstractRestClient<ReturnDto> {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CreateUrlImpl createUrl;

	@Value("${cat.authorization.key}")
	private String key;

	GenerateHeaders header = new GenerateHeaders();


	public ResponseEntity<ReturnDto> getAll(String itens,HttpMethod method, Class<ReturnDto> returnDto) {
		return restTemplate.exchange(
				createUrl.getUrl(itens),
				method,
				header.getHeaders(key),
				returnDto
		);
	}

	public ResponseEntity<ReturnDto> getAll(Map<String, String> item, HttpMethod method, Class<ReturnDto> returnDto){
		return restTemplate.exchange(
				createUrl.getUrl(item),
				method,
				header.getHeaders(key),
				returnDto);
	};

	public ResponseEntity<ReturnDto[]> getAllRacas(Class<ReturnDto[]> typeReturn, String raca) {
		return restTemplate.exchange(
				createUrl.getUrl(raca),
				HttpMethod.GET,
				header.getHeaders(key),
				typeReturn
		);
	}


	public ResponseEntity<RacaDto[]> getAllRacas(Class<RacaDto[]> typeReturn, HttpMethod method) {
		return restTemplate.exchange(
				createUrl.getUrl(),
				method,
				header.getHeaders(key),
				typeReturn
		);
	}

	public ResponseEntity<ReturnDto[]> getAllRacas(String raca, Class<ReturnDto[]> typeReturn) {
		return restTemplate.exchange(
				createUrl.getUrl(raca),
				HttpMethod.GET,
				header.getHeaders(key),
				typeReturn
		);
	}
}
