package com.httpcat.client;

import com.httpcat.util.impl.CreateUrlImpl;
import com.httpcat.util.impl.GenerateHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

public abstract class AbstractRestClient<ReturnDto> {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CreateUrlImpl createUrl;

	@Value("${cat.authorization.key}")
	private String key;

	GenerateHeaders header = new GenerateHeaders();


	public ResponseEntity<ReturnDto> getAll(String itens, HttpMethod method) {
		Class<ReturnDto> typeReturn = (Class<ReturnDto>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return restTemplate.exchange(
				createUrl.getUrl(itens),
				method,
				header.getHeaders(key),
				typeReturn
		);
	}

	public ResponseEntity<ReturnDto> getAll(Map<String, String> item, HttpMethod method) {
		Class<ReturnDto> typeReturn = (Class<ReturnDto>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return restTemplate.exchange(
				createUrl.getUrl(item),
				method,
				header.getHeaders(key),
				typeReturn
		);
	}

	;


	public ResponseEntity<ReturnDto> getAllRacas(HttpMethod method) {
		Class<ReturnDto> typeReturn = (Class<ReturnDto>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return restTemplate.exchange(
				createUrl.getUrl(),
				method,
				header.getHeaders(key),
				typeReturn
		);
	}

}
