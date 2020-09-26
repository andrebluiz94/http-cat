package com.httpcat.generic.client;

import com.httpcat.generic.config.HttpConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractGetClient<RequestType, ResponseType, Configuration extends HttpConfiguration> {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private Configuration configuration;
	private static Integer TYPE_RETURN_CALLER = 1;

	public ResponseEntity<ResponseType> get(RequestType requestType) {
		Class<ResponseType> typeReturn = (Class<ResponseType>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[TYPE_RETURN_CALLER];
		return restTemplate.exchange(
				configuration.getUrl(),
				HttpMethod.GET,
				configuration.buildHeadersAuthentication(),
				typeReturn
		);
	}
}
